package com.kjplus.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ybk.basic.util.DateUtil;
import org.ybk.basic.util.Util;

import com.kjplus.constant.Constant;
import com.kjplus.dao.IDocumentInfoDao;
import com.kjplus.dto.ServAsgnDto;
import com.kjplus.dto.TagDto;
import com.kjplus.eto.DocInfoEto;
import com.kjplus.eto.PersonOplogEto;
import com.kjplus.model.DocumentInfoEbo;
import com.kjplus.model.OrgEbo;
import com.kjplus.model.TagEbo;
import com.kjplus.model.inner.DocumentInfoInnerEbo;
import com.kjplus.service.IActivityEnrolService;
import com.kjplus.service.IDocInfoService;
import com.kjplus.service.IOrgService;
import com.kjplus.service.IPersonOplogService;
import com.kjplus.service.IServiceAssignService;
import com.kjplus.service.ITagService;
import com.ybk.exception.DataException;

@Service("docInfoService")
public class DocInfoService implements IDocInfoService {

	private static Logger logger = Logger.getLogger(DocInfoService.class);
	@Autowired
	private IDocumentInfoDao docInfoDao;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private ITagService tagService;
	@Autowired
	private IActivityEnrolService activityEnrolService;
	@Autowired
	private IServiceAssignService srvAssignService;
	@Autowired
	private IPersonOplogService personOplogService;

	public DocumentInfoEbo addDocInfo(DocInfoEto docEto) throws DataException {

		if (docEto == null)
			throw new DataException("[err]添加建档对象不能空");
		if (Util.isNull(docEto.getPrsnName()))
			throw new DataException("[err]添加建档人名字不能空");
		if (Util.isNull(docEto.getPrsnSex()))
			throw new DataException("[err]添加建档人性别不能空");
		if (Util.isNull(docEto.getIdNumber()))
			throw new DataException("[err]添加建档人身份证不能空");
		if (Util.isNull(docEto.getPersonNum()))
			throw new DataException("[err]添加建档人电话不能空");
		if (Util.isNull(docEto.getMobileNum()))
			throw new DataException("[err]添加建档人手机不能空");

		List<DocumentInfoEbo> dis = null;
		String code = null;
		String prsnCode = null;
		// code
		code = Util.genDigiCodeStr(DocInfoEto.MAX_CODE_LEN);
		dis = docInfoDao.getDocInfoByIdOrCode(0, code);
		while (dis.size() > 0) {
			code = Util.genDigiCodeStr(DocInfoEto.MAX_CODE_LEN);
			dis = docInfoDao.getDocInfoByIdOrCode(0, code);
		}

		dis = docInfoDao.listDocInfo(docEto.getOrgId(), null, docEto.getIdNumber());
		if (dis.size() > 0) {
			return (DocumentInfoEbo) dis.get(0);
		}

		prsnCode = docEto.getPrsnCode();
		if (Util.isNull(prsnCode)) {
			prsnCode = Util.genDigiCodeStr(DocInfoEto.MAX_PRSN_CODE_LEN);
			dis = docInfoDao.listDocInfo(docEto.getOrgId(), prsnCode, docEto.getIdNumber());
			while (dis.size() > 0) {
				prsnCode = Util.genDigiCodeStr(DocInfoEto.MAX_PRSN_CODE_LEN);
				dis = docInfoDao.listDocInfo(docEto.getOrgId(), prsnCode, docEto.getIdNumber());
			}
		} else {
			dis = docInfoDao.listDocInfo(docEto.getOrgId(), prsnCode, null);
			if (dis.size() > 0)
				return (DocumentInfoEbo) dis.get(0);
		}
		DocumentInfoEbo di = new DocumentInfoEbo();
		try {
			BeanUtils.copyProperties(docEto, di);
			di.setCode(code);
			di.setPrsnCode(prsnCode);
			if (Util.isNotNull(docEto.getBirthday()))
				di.setBirthday(DateUtil.parseDateStr(docEto.getBirthday()));
			else
				di.setBirthday(DateUtil.newTime());

			if (Util.isNotNull(docEto.getCreateDay()))
				di.setCreateDay(DateUtil.parseDateStr(docEto.getCreateDay()));
			else
				di.setCreateDay(DateUtil.curDateOffset(0));
			di.setFlag(Constant.FLAG_YES);
			di.setCreateTime(Calendar.getInstance().getTime());
			di.setLastUpdateTime(Calendar.getInstance().getTime());
			docInfoDao.addDocInfo(di);
			
			//添加档案操作记录
			if(di.getPrsnId() > 0){
				PersonOplogEto prsnOplog = new PersonOplogEto();
				prsnOplog.setPrsnId(di.getPrsnId());
				prsnOplog.setOpTypeCode(Constant.RV_DOC_NEW);
				prsnOplog.setOrgid(di.getOrgId());
				prsnOplog.setUid(di.getCreatorId());
				personOplogService.addPrsnOplog(prsnOplog);
			}
			
		} catch (Exception e) {
			logger.error(e);
		}
		return di;
	}

	// 查询一个orgid中的idNumber对应的
	public List<DocumentInfoEbo> listDocInfo(int orgId, String prsnCode, String idNumber) throws DataException {
		if (orgId != 0) {
			OrgEbo orgEbo = orgService.getOrgById(orgId);
			if (orgEbo == null) {
				throw new DataException("该组织不存在。");
			}
		}
		List<DocumentInfoEbo> docInfoList = docInfoDao.listDocInfo(orgId, prsnCode, idNumber);
		return docInfoList;
	};

	// 罗列组合
	public List<DocumentInfoInnerEbo> listDocInfoInner(int orgId, String name,String status, String flag, String idCard, List<Integer> tagIds,boolean isEffect,int startTime, int endTime, int page, int paging) throws DataException {
		if (orgId != 0) {
			OrgEbo orgEbo = orgService.getOrgById(orgId);
			if (orgEbo == null) {
				throw new DataException("该组织不存在。");
			}
		}
		List<DocumentInfoInnerEbo> docInfoInnerList = null;
		if (tagIds == null || tagIds.size() == 0)
			docInfoInnerList = docInfoDao.listDocInfoInner(orgId, name,status, flag, idCard,startTime,endTime, page, paging);
		else {
			StringBuffer buf = new StringBuffer();
			for (int i = 0; i < tagIds.size(); i++) {
				if (i != 0)
					buf.append(",");
				buf.append(tagIds.get(i));
			}
			docInfoInnerList = docInfoDao.listDocInfoTagInner(orgId, name,status, flag, idCard, buf.toString(),startTime,endTime, page, paging);
		}
		List<Integer> mainIds = new ArrayList<Integer>();
		Hashtable<Integer, DocumentInfoInnerEbo> diHash = new Hashtable<Integer, DocumentInfoInnerEbo>();
		for (DocumentInfoInnerEbo di : docInfoInnerList) {
			mainIds.add(di.getPrsnId());
			diHash.put(di.getPrsnId(), di);
		}
		List<TagEbo> tags = tagService.listTagArray(mainIds, Constant.TAG_TYPE_PERSON);
		for (TagEbo t : tags) {
			if (!diHash.containsKey(t.getMainId()))
				continue;
			TagDto td = new TagDto();
			BeanUtils.copyProperties(t, td);
			diHash.get(t.getMainId()).getTags().add(td);
		}
		List<ServAsgnDto> listSrv = srvAssignService.listAsgnDto(null, 0, null, 0, null, orgId, null, isEffect,true,0, -1);
		for(ServAsgnDto srv : listSrv){
			if (!diHash.containsKey(srv.getPersonId()))
				continue;
			ServAsgnDto srvAsgn = new ServAsgnDto();
			BeanUtils.copyProperties(srv, srvAsgn);
			diHash.get(srv.getPersonId()).getSrvAssigns().add(srvAsgn);
		}
		return docInfoInnerList;
	}

	// 获得总数
	public int getTotalDocInfoInner(int orgId, String name,String status, String flag, String idCard, List<Integer> tagIds, int startTime, int endTime) throws DataException {

		if (tagIds == null || tagIds.size() == 0)
			return docInfoDao.getTotalDocInfoInner(orgId, name,status, flag, idCard,startTime,endTime);
		else {
			StringBuffer buf = new StringBuffer();
			for (int i = 0; i < tagIds.size(); i++) {
				if (i != 0)
					buf.append(",");
				buf.append(tagIds.get(i));
			}
			return docInfoDao.getTotalDocInfoTagInner(orgId, name, status,flag, idCard, buf.toString(),startTime,endTime);
		}
	}

	// 修改建档信息
	public void updateDocInfo(DocumentInfoEbo dInfoEbo) throws DataException {

		if (dInfoEbo == null)
			throw new DataException("[err]修改建档对象不能空");
		boolean isNull = dInfoEbo.getPrsnId() <= 0 && Util.isNull(dInfoEbo.getCode()) ? true : false;
		if (isNull)
			throw new DataException("[err]修改建档对象时 ，prsnId和code不能同时为空");
		dInfoEbo.setLastUpdateTime(Calendar.getInstance().getTime());
		docInfoDao.editDocInfo(dInfoEbo);
	}

	// 通过ID或者Code后去DocInfo
	public DocumentInfoEbo getDocinfoByIdOrCode(int id, String code) throws DataException {
		boolean isNull = id <= 0 && Util.isNull(code) ? true : false;
		if (isNull)
			return null;
		return docInfoDao.getDocinfoByIdOrCode(id, code);
	};

}
