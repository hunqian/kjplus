package com.kjplus.service.impl;

import com.kjplus.constant.Constant;
import com.kjplus.dao.IInfoDao;
import com.kjplus.dto.InfoCatalogDto;
import com.kjplus.dto.InfoContentDto;
import com.kjplus.dto.InfoDto;
import com.kjplus.dto.InfoReferenceDto;
import com.kjplus.eto.*;
import com.kjplus.model.*;
import com.kjplus.model.inner.InfoCatalogInnerEbo;
import com.kjplus.model.inner.InfoInnerEbo;
import com.kjplus.model.inner.InfoReferenceInnerEbo;
import com.kjplus.service.*;
import com.kjplus.util.DataValUtil;
import com.ybk.exception.DataException;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.ybk.basic.util.Util;

import java.util.*;

@Service("infoService")
public class InfoService implements IInfoService {

	private static Logger logger = Logger.getLogger(InfoService.class);
	@Autowired
	private ISysBaseService baseService;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private IDeptService deptService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IInfoDao infoDao;
	@Autowired
	private IZanService zanService;

	public InfoEbo getInfoByIdOrCode(int id, String infoCode) throws DataException {
		boolean isNull = id <= 0 && Util.isNull(infoCode) ? true : false;
		if (isNull)
			return null;
		return infoDao.getInfoByIdOrCode(id, infoCode);
	}

	// 获得InfoDTo
	public InfoDto getInfoDto(int id) throws DataException {
		InfoEbo infoEbo = getInfoByIdOrCode(id, null);
		if (infoEbo == null)
			return null;
		InfoDto info = new InfoDto();
		try {
			BeanUtils.copyProperties(infoEbo, info);
			info.setContent(getInfoContent(id));
		} catch (BeansException be) {
			logger.error(be);
		}
		return info;
	}

	// 通过id或者infoCode获取InfoReferenceEbo对象
	public InfoReferenceEbo getReferenceByIdOrCode(int id, String refCode) throws DataException {
		boolean isNull = id <= 0 && Util.isNull(refCode) ? true : false;
		if (isNull)
			return null;
		return infoDao.getInfoReferenceByIdOrCode(id, refCode);
	}

	public InfoCatalogEbo getCatalogByIdOrCode(int id, String code) throws DataException {
		boolean isNull = id <= 0 && Util.isNull(code) ? true : false;
		if (isNull)
			return null;
		return infoDao.getInfoCatalogByIdOrCode(id, code);

	}

	@Transactional(value = "txManager", rollbackFor = { RuntimeException.class, Exception.class, DataException.class }, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, readOnly = true, timeout = 3)
	public InfoEbo addInfo(InfoEto info) throws DataException {

		if (info.getOrgId() != null && info.getOrgId().intValue() > 0) {
			OrgEbo org = orgService.getOrgById(info.getOrgId());
			if (org == null)
				throw new DataException("系统无此组织，id为：" + info.getOrgId());
		}
		if (info.getDeptId() != null && info.getDeptId() > 0) {
			DepartmentEbo dept = deptService.getDepartmentById(info.getDeptId());
			if (dept == null)
				throw new DataException("系通无此部门,id为" + info.getDeptId());
		}

		if (info.getPubId() != null && info.getPubId().intValue() > 0) {
			UserEbo user = userService.getUserById(info.getPubId());
			if (user == null)
				throw new DataException("系统无此user用户，id为：" + info.getPubId());
		}
		// 判断新闻是否有此类型
		InfoCatalogEbo cata = getCatalogByIdOrCode(info.getInfoCatgId(), null);
		if (cata == null) {
			throw new DataException("系统无此类型，id为：" + info.getInfoCatgId());
		}
		String code = null;
		InfoEbo info2 = null;
		// 产生一个64位长度
		code = Util.genDigiCodeStr(InfoEto.CODE_LEN);
		info2 = getInfoByIdOrCode(0, code);
		while (info2 != null) {
			code = Util.genDigiCodeStr(InfoEto.CODE_LEN);
			info2 = getInfoByIdOrCode(0, code);
		}
		InfoEbo infoEbo = null;
		try {
			infoEbo = new InfoEbo();
			BeanUtils.copyProperties(info, infoEbo);
			infoEbo.setInfoCatgId(info.getInfoCatgId());
			infoEbo.setInfoCode(code);
			infoDao.addInfo(infoEbo);

			// 需要添加内容
			addInfoContent(infoEbo.getId(), info.getContent());

			// 只有在orgid不为0的时候添加引用
			if (info.getOrgId() != null && info.getOrgId().intValue() > 0)
				addReference(infoEbo.getId(), info.getInfoCatgId(), info.getOrgId());
		} catch (DataException e) {
			logger.error(e.getMessage());
			throw new DataException(e.getMessage());
		}
		return infoEbo;
	}

	// 添加资讯分类
	public InfoCatalogEbo addCatalog(InfoCatalogEto catalog) throws DataException {

		// 做空值验证
		DataValUtil.dataValidation(catalog.getClass(), catalog);
		InfoCatalogEbo cata = null;
		String code = catalog.getCode();

		// 产生一个32位长度不重复的编号
		if (Util.isNull(code)) {
			code = Util.genSessCode(InfoCatalogEto.CODE_LEN);
			cata = getCatalogByIdOrCode(0, code);
			while (cata != null) {
				code = Util.genSessCode(InfoCatalogEto.CODE_LEN);
				cata = getCatalogByIdOrCode(0, code);
			}
		} else {
			cata = getCatalogByIdOrCode(0, code);
			if (cata != null)
				return cata;
		}

		InfoCatalogEbo infoCataLog1 = null;
		// 判断是否有上级
		if (catalog.getPid() != null && catalog.getPid().intValue() > 0) {
			infoCataLog1 = getCatalogByIdOrCode(catalog.getPid(), null);
			if (infoCataLog1 == null) {
				throw new DataException("系统无此上级类型");
			}
		}

		if (catalog.getInfoTypeId() != null && catalog.getInfoTypeId().intValue() > 0) {
			SysRefValueEbo refVl = baseService.getRefVlById(catalog.getInfoTypeId());
			if (refVl == null) {
				throw new DataException("系统无此参照,编号为:" + catalog.getInfoTypeId());
			}
		}

		OrgEbo org = null;
		if (catalog.getOrgId() != null && catalog.getOrgId().intValue() > 0) {
			org = orgService.getOrgById(catalog.getOrgId());
			if (org == null)
				throw new DataException("系统无此组织");
		}

		InfoCatalogEbo cata1 = null;
		try {
			cata1 = new InfoCatalogEbo();
			BeanUtils.copyProperties(catalog, cata1);
			cata1.setCode(code);
			cata1.setFlag(Constant.FLAG_YES);
			infoDao.addInfoCatalog(cata1);
		} catch (DataException e) {
			logger.error(e.getMessage());
		}
		return cata1;
	}

	// 列表一个组织下的分类,返回一个树状
	public List<InfoCatalogDto> listCatalog(int orgid, int pid, String name, String flag) throws DataException {
		return listCatalog(orgid, pid, name, flag, false, true);
	}

	public int getTotalCatalogByOrg(int orgid, int pid, String name, String flag) throws DataException {
		return infoDao.getTotalCatalogByOrg(orgid, pid, name, flag);
	}

	// 列表一个组织下的分类,返回一个树状
	public List<InfoCatalogDto> listCatalog(int orgid, int pid, String name, String flag, boolean treeStyle,
			boolean childNearStyle) throws DataException {
		List<InfoCatalogDto> catgs = new ArrayList<InfoCatalogDto>();
		List<InfoCatalogInnerEbo> catgList = infoDao.listInfoCatalogByOrg(orgid, pid, name, flag);
		if (catgList == null || catgList.size() == 0)
			return catgs;
		InfoCatalogInnerEbo ci = null;
		InfoCatalogDto c = null;
		InfoCatalogDto pc = null;
		Hashtable<Integer, InfoCatalogDto> catgHash = new Hashtable<Integer, InfoCatalogDto>();
		for (int i = 0; i < catgList.size(); i++) {
			ci = catgList.get(i);
			c = new InfoCatalogDto();
			BeanUtils.copyProperties(ci, c);
			if (!treeStyle) {
				catgs.add(c);
			} else {
				if (ci.getPid() != null && catgHash.containsKey(ci.getPid())) {
					pc = catgHash.get(ci.getPid());
					pc.getSubs().add(c);
				} else {
					catgs.add(c);
				}
			}
			catgHash.put(ci.getId(), c);
		}

		if (!treeStyle && childNearStyle) {
			for (int i = 0; i < catgs.size(); i++) {
				c = catgs.get(i);
				if (c.getPid() == null || c.getPid().intValue() == 0)
					continue;
				if (!catgHash.containsKey(c.getPid()))
					continue;
				for (int j = 0; j < i; j++) {
					if (catgs.get(j).getId().intValue() != c.getPid().intValue())
						continue;
					if (catgs.get(j).getPid().intValue() == c.getPid().intValue())
						continue;
					else {
						catgs.remove(i);
						catgs.add(j + 1, c);
						break;
					}
				}
			}
		}
		return catgs;
	}

	// 添加引用
	public InfoReferenceEbo addReference(int infoId, int catgId, int orgId) throws DataException {
		InfoReferenceEbo infoReference = new InfoReferenceEbo();
		String code = infoReference.getRefCode();
		InfoReferenceEbo inforef2 = null;

		inforef2 = infoDao.getInfoReference(infoId, catgId, orgId);
		if (inforef2 != null) {
			if (Constant.FLAG_NO.equals(inforef2.getFlag())) {
				// 更新标志返回
				infoDao.updateReferFlag(inforef2.getId(), null, -1, -1, -1, Constant.FLAG_YES);
			}
			return inforef2;
		}

		// 产生一个64位长度
		if (Util.isNull(code)) {
			code = Util.genDigiCodeStr(InfoReferenceEto.CODE_LEN);
			inforef2 = getReferenceByIdOrCode(0, code);
			while (inforef2 != null) {
				code = Util.genDigiCodeStr(InfoReferenceEto.CODE_LEN);
				inforef2 = getReferenceByIdOrCode(0, code);
			}
		} else {
			inforef2 = getReferenceByIdOrCode(0, code);
			if (inforef2 != null)
				return inforef2;
		}
		infoReference.setOrgId(orgId);
		infoReference.setRefCode(code);
		infoReference.setInfoCatgId(catgId);
		infoReference.setInfoId(infoId);
		infoReference.setViewNum(0);
		infoReference.setZanNum(0);
		infoReference.setFocusNum(0);
		infoReference.setFlag("Y");
		infoDao.addInfoReference(infoReference);
		return infoReference;
	}

	public void addInfoContent(int infoId, String content) throws DataException {
		if (infoId <= 0 || Util.isNull(content))
			return;
		// 先要删除
		InfoEbo info = infoDao.getInfoByIdOrCode(infoId, null);
		if (info != null)
			infoDao.delInfoContent(infoId);
		// 需要计算总的记录数量
		int total = content.length() / IInfoService.CONTENT_LEN;
		if (total * IInfoService.CONTENT_LEN != content.length())
			total += 1;
		for (int i = 0; i < total; i++) {
			InfoContentEbo infoContent = new InfoContentEbo();
			infoContent.setInfoId(infoId);
			infoContent.setInfoSeq(i + 1);
			if (i == total - 1)
				infoContent.setInfoContent(content.substring(i * IInfoService.CONTENT_LEN));
			else
				infoContent.setInfoContent(content.substring(i * IInfoService.CONTENT_LEN, (i + 1)
						* IInfoService.CONTENT_LEN));
			infoDao.addInfoContent(infoContent);
		}
	}

	// 通过infoId获取文字对象
	public String getInfoContent(int infoId) throws DataException {
		List<InfoContentEbo> contents = infoDao.listInfoContent(infoId);
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < contents.size(); i++) {
			buf.append(contents.get(i).getInfoContent());
		}
		return buf.toString();
	}

	// 一次性获得或多条内容
	public List<InfoContentDto> listInfoContents(List<Integer> infoIds) throws DataException {

		List<InfoContentDto> contents = new ArrayList<InfoContentDto>();
		if (infoIds == null || infoIds.size() == 0)
			return contents;

		StringBuffer infoIdsBuf = new StringBuffer();
		for (int i = 0; i < infoIds.size(); i++) {
			if (i > 0)
				infoIdsBuf.append(",");
			infoIdsBuf.append(infoIds.get(i));
		}
		List<InfoContentEbo> icInners = infoDao.listInfoContentArr(infoIdsBuf.toString());
		Hashtable<Integer, InfoContentDto> icHash = new Hashtable<Integer, InfoContentDto>();
		int infoId = 0;
		InfoContentDto icDto = null;
		for (int i = 0; i < icInners.size(); i++) {
			infoId = icInners.get(i).getInfoId();
			if (!icHash.containsKey(infoId)) {
				icDto = new InfoContentDto();
				icHash.put(infoId, icDto);
				contents.add(icDto);
			} else {
				icDto = icHash.get(infoId);
			}
			icDto.setInfoId(infoId);
			icDto.setInfoContent(icDto.getInfoContent() + icInners.get(i).getInfoContent());
		}
		return contents;
	}

	// 添加内容索引
	public InfoReferenceEbo addReference(InfoReferenceEto refer) throws DataException {

		List<InfoReferenceInnerEbo> refers = infoDao.listInfoReference(refer.getInfoId(), 0,
				refer.getOrgId(), null, null, 0, 10);
		if (refers.size() > 0) {
			InfoReferenceInnerEbo r = refers.get(0);
			if (!Constant.FLAG_YES.equals(r.getFlag())) {
				infoDao.updateReferFlag(r.getId(), r.getRefCode(), 0, 0, 0, Constant.FLAG_YES);
			}
			return null;
		}

		InfoEbo info = infoDao.getInfoByIdOrCode(refer.getInfoId(), null);
		if (info == null)
			throw new DataException("系统无此资讯，id为：" + refer.getInfoId());

		OrgEbo org = orgService.getOrgById(info.getOrgId());
		if (org == null)
			throw new DataException("系统无此组织，id为：" + refer.getOrgId());
		if (refer.getDeptId() > 0) {
			DepartmentEbo dept = deptService.getDepartmentById(refer.getDeptId());
			if (dept == null)
				throw new DataException("系通无此部门,id为" + refer.getDeptId());
		}

		if (refer.getInfoCatgId() != null) {
			InfoCatalogEbo infoCatgLog = getCatalogByIdOrCode(refer.getInfoCatgId(), null);
			if (infoCatgLog == null)
				throw new DataException("系通无此类型,id为" + refer.getInfoCatgId());
		}

		String code = refer.getRefCode();
		InfoReferenceEbo refer2 = null;
		// 产生一个64位长度
		if (Util.isNull(code)) {
			code = Util.genDigiCodeStr(InfoReferenceEto.CODE_LEN);
			refer2 = getReferenceByIdOrCode(0, code);
			while (refer2 != null) {
				code = Util.genDigiCodeStr(InfoEto.CODE_LEN);
				refer2 = getReferenceByIdOrCode(0, code);
			}
		} else {
			refer2 = getReferenceByIdOrCode(0, code);
			if (refer2 != null)
				return refer2;
		}
		InfoReferenceEbo infoReference = new InfoReferenceEbo();
		BeanUtils.copyProperties(refer, infoReference);
		infoReference.setRefCode(code);
		infoDao.addInfoReference(infoReference);
		return infoReference;
	}

	// 列表infoz总数
	public int getTotalInfo(String infoTitle, int infoCatgId, String flag, String fetchAll, int orgId, String queryDay,String infoType)
			throws DataException {
		String btime = "";
		String etime = "";

		if (Util.isNotNull(queryDay)) {
			btime = queryDay + " 00:00:00";
			etime = queryDay + " 23:59:59";

		}
		return infoDao.getTotalInfo(infoTitle, infoCatgId, flag, fetchAll, orgId, btime, etime,infoType);
	}

	// 列表info
	public List<InfoDto> listInfo(String infoTitle, int infoCatgId, String flag, String fetchAll, int orgid,
			String queryDay, boolean withContent,String infoType, int startPage, int endPage) throws DataException {
		String btime = "";
		String etime = "";

		if (Util.isNotNull(queryDay)) {
			btime = queryDay + " 00:00:00";
			etime = queryDay + " 23:59:59";
		}
		
		List<InfoDto> infoList = new ArrayList<InfoDto>();
		// 下面参数需要做适当的对照
		List<InfoInnerEbo> infoInners = infoDao.listInfo(infoTitle, infoCatgId, flag, fetchAll, orgid, btime, etime,infoType,
				startPage, endPage);
		if (infoInners == null)
			return null;
		Map<String, InfoDto> infoDtoMap = new HashMap<String, InfoDto>();
		List<Integer> infoIds = new ArrayList<Integer>();
		for (InfoInnerEbo infoInner : infoInners) {
			InfoDto infoDto = infoDtoMap.get(infoInner.getId() + "");
			infoIds.add(infoInner.getId());
			if (infoDto == null) {
				infoDto = new InfoDto();
				BeanUtils.copyProperties(infoInner, infoDto);
				infoDtoMap.put(infoDto.getId() + "", infoDto);
				infoList.add(infoDto);
			}
		}
		if (!withContent)
			return infoList;
		// 开始获得内容
		List<InfoContentDto> contents = listInfoContents(infoIds);
		for (InfoDto info : infoList) {
			for (int i = 0; i < infoList.size(); i++) {
				if (info.getId().intValue() == contents.get(i).getInfoId().intValue()) {
					info.setContent(contents.get(i).getInfoContent());
					break;
				}
			}
		}
		return infoList;
	}

	// 引用资讯列表
	public List<InfoReferenceDto> listInfoReference(int infoId, int infoCatgId, int orgid, String flag,
			String infoFlag, int page, int paging) throws DataException {

		List<InfoReferenceDto> InfoReferenceList = new ArrayList<InfoReferenceDto>();
		List<InfoReferenceInnerEbo> InfoReferenceInners = infoDao.listInfoReference(infoId, infoCatgId, orgid, flag,
				infoFlag, page, paging);

		if (InfoReferenceInners == null)
			return null;

		InfoReferenceDto infoReference = null;
		for (InfoReferenceInnerEbo infoReferenceInner : InfoReferenceInners) {
			infoReference = new InfoReferenceDto();
			BeanUtils.copyProperties(infoReferenceInner, infoReference);
			InfoReferenceList.add(infoReference);
		}
		return InfoReferenceList;
	}

	public void addInfoVisit(String refCode) throws DataException {
		InfoReferenceEbo infoRef = getReferenceByIdOrCode(0, refCode);
		if (infoRef == null)
			throw new DataException("系统无此资讯，编号为：" + refCode);

		infoDao.updateReferFlag(0, refCode, infoRef.getViewNum() + 1, 0, 0, null);
		InfoEbo info = getInfoByIdOrCode(infoRef.getInfoId(), null);
		infoDao.updateInfoFlag(info.getId(), info.getInfoCode(), info.getTotalViewNum() + 1, 0, 0, null);
	}

	public void addInfoZan(String refCode, int uid, String status, String type) throws DataException {
		InfoReferenceEbo infoRef = getReferenceByIdOrCode(0, refCode);
		if (infoRef == null)
			throw new DataException("系统无此资讯，编号为：" + refCode);
		if (Util.isNull(type))
			throw new DataException("请输入类型是关注，还是点赞");
		if (Constant.FLAG_YES.equals(status)) {
			if (Constant.DOT_ZAN.equals(type)) {
				infoDao.updateReferFlag(0, refCode, 0, infoRef.getZanNum() - 1, 0, null);
				InfoEbo info = getInfoByIdOrCode(infoRef.getInfoId(), null);
				infoDao.updateInfoFlag(info.getId(), info.getInfoCode(), 0, 0, info.getTotalZanNum() - 1, null);
				zanService.unZan(infoRef.getId(), Constant.ZAN_INFO, uid);
			} else if (Constant.ATTENTION.equals(type)) {
				// TODO:待实现关注功能
				infoDao.updateReferFlag(0, refCode, 0, 0, infoRef.getFocusNum() - 1, null);
				InfoEbo info = getInfoByIdOrCode(infoRef.getInfoId(), null);
				infoDao.updateInfoFlag(info.getId(), info.getInfoCode(), 0, info.getTotalFocusNum() - 1, 0, null);
				zanService.unZan(infoRef.getId(), Constant.ZAN_INFO, uid);
			}
		} else {
			if (Constant.DOT_ZAN.equals(type)) {
				infoDao.updateReferFlag(0, refCode, 0, infoRef.getZanNum() + 1, 0, null);
				InfoEbo info = getInfoByIdOrCode(infoRef.getInfoId(), null);
				infoDao.updateInfoFlag(info.getId(), info.getInfoCode(), 0, 0, info.getTotalZanNum() + 1, null);
				ZanHisEto zan = new ZanHisEto();
				zan.setMainId(infoRef.getId());
				zan.setMainType(Constant.ZAN_INFO);
				zan.setUid(uid);
				zanService.zan(zan);
			} else if (Constant.ATTENTION.equals(type)) {
				// TODO:待实现关注功能
				infoDao.updateReferFlag(0, refCode, 0, 0, infoRef.getFocusNum() + 1, null);
				InfoEbo info = getInfoByIdOrCode(infoRef.getInfoId(), null);
				infoDao.updateInfoFlag(info.getId(), info.getInfoCode(), 0, info.getTotalFocusNum() + 1, 0, null);
				ZanHisEto zan = new ZanHisEto();
				zan.setMainId(infoRef.getId());
				zan.setMainType(Constant.ZAN_INFO);
				zan.setUid(uid);
				zanService.zan(zan);
			}
		}

	}

	// 通过id获取InfoReadlogEbo对象
	public InfoReadlogEbo getReadlogById(int id) throws DataException {
		if (id <= 0)
			return null;
		else
			return infoDao.getReadlogById(id);
	}

	// 添加阅读记录
	public InfoReadlogEbo addSimpleReadlog(InfoReadlogEto infoReadlog) throws DataException {

		UserEbo user = userService.getUserById(infoReadlog.getUid());
		if (user == null) {
			throw new DataException("系统无此用户，ID为:" + infoReadlog.getUid());
		}
		if (infoReadlog.getMainId() == null || infoReadlog.getMainId().intValue() <= 0)
			throw new DataException("必须指定mainID");

		InfoReadlogEbo readlogEbo = null;
		try {
			readlogEbo = new InfoReadlogEbo();
			BeanUtils.copyProperties(infoReadlog, readlogEbo);
			infoDao.addReadlog(readlogEbo);
		} catch (DataException e) {
			logger.error(e.getMessage());
		}
		return readlogEbo;
	}

	// 添加阅读记录
	public InfoReadlogEbo addReadlog(InfoReadlogEto infoReadlog) throws DataException {
		// 做空值验证
		DataValUtil.dataValidation(infoReadlog.getClass(), infoReadlog);

		UserEbo user = userService.getUserById(infoReadlog.getUid());
		if (user == null) {
			throw new DataException("系统无此用户，ID为:" + infoReadlog.getUid());
		}

		InfoEbo info = null;
		// 阅读的是资讯或文章
		if (Constant.READ_TYPE_ARTICLES.equals(infoReadlog.getMainType())) {
			if (Util.isNull(infoReadlog.getMainCode()))
				throw new DataException("资讯类型资讯编号不能空");
			info = getInfoByIdOrCode(0, infoReadlog.getMainCode());
			if (info == null) {
				throw new DataException("系统无此资讯，编号为:" + infoReadlog.getMainCode());
			}
		} else if (Constant.READ_TYPE_VIDEO.equals(infoReadlog.getMainType())) {// 阅读的是视频
			// TODO:视频类型待实现
			if (Util.isNull(infoReadlog.getMainCode()))
				throw new DataException("视频类型资讯编号不能空");
		}
		InfoReadlogEbo readlogEbo = null;
		try {
			readlogEbo = new InfoReadlogEbo();
			BeanUtils.copyProperties(infoReadlog, readlogEbo);
			if (Constant.READ_TYPE_ARTICLES.equals(infoReadlog.getMainType())) {
				readlogEbo.setMainId(info.getId());
			} else {
				// TODO;视频待实现
			}
			infoDao.addReadlog(readlogEbo);
		} catch (DataException e) {
			logger.error(e.getMessage());
		}
		return readlogEbo;
	}

	// 修改阅读记录状态 阅读时间
	public void changeReadTimeOrFlag(int id, String flag, int readTime) throws DataException {
		if (id <= 0)
			return;
		if (Util.isNull(flag) || readTime <= 0)
			return;
		/*
		 * if (flag == Constant.FLAG_NO && readTime > 0) throw new
		 * DataException("同时修改表参数，状态不吻合");
		 */
		infoDao.changeReadlog(id, flag, readTime);
	}

	public void updateInfo(InfoEbo info) throws DataException {

		if (info.getId() == null || info.getId().intValue() <= 0) {
			throw new DataException("必须指定infoid!");
		}

		if (info.getOrgId() != null && info.getOrgId().intValue() > 0) {
			OrgEbo org = orgService.getOrgById(info.getOrgId());
			if (org == null)
				throw new DataException("系统无此组织，id为：" + info.getOrgId());
		}
		if (info.getDeptId() != null && info.getDeptId() > 0) {
			DepartmentEbo dept = deptService.getDepartmentById(info.getDeptId());
			if (dept == null)
				throw new DataException("系通无此部门,id为" + info.getDeptId());
		}

		InfoCatalogEbo cata = getCatalogByIdOrCode(info.getInfoCatgId(), null);
		if (cata == null) {
			throw new DataException("系统无此类型，id为：" + info.getInfoCatgId());
		}

		
		infoDao.updateInfo(info);
	}

	// 修改infocatalog信息
	public void updateInfoCatalog(InfoCatalogEbo infoCatalogEbo) throws DataException {
		String code = infoCatalogEbo.getCode();
		InfoCatalogEbo infocata = infoDao.getInfoCatalogByIdOrCode(0, code);
		if (infocata == null) {
			throw new DataException("系统无infocatalog型，code为：" + code);
		}
		infoDao.updateInfoCatalog(infoCatalogEbo);
	}
	public InfoInnerEbo getInfoByRef(int refId, String refCode) throws DataException{
		InfoReferenceEbo infoRef = infoDao.getInfoReferenceByIdOrCode(0, refCode);
		if (infoRef == null) {
			throw new DataException("系统无infoRef型，code为：" + refCode);
		}
		InfoEbo infoEbo = getInfoByIdOrCode(infoRef.getInfoId(), null);
		if (infoEbo == null) {
			throw new DataException("系统无次info型，id为：" + infoRef.getInfoId());
		}
		return infoDao.getInfoByRef(refId, refCode);
	}

}
