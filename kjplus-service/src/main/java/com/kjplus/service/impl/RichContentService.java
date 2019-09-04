package com.kjplus.service.impl;

import com.kjplus.dao.IRichContentDao;
import com.kjplus.dto.RichContentDto;
import com.kjplus.eto.RichContentEto;
import com.kjplus.model.*;
import com.kjplus.model.inner.RichContentInnerEbo;
import com.kjplus.service.*;
import com.ybk.exception.DataException;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ybk.basic.util.Util;

import java.util.*;

@Service("richContentService")
public class RichContentService implements IRichContentService {

	@Autowired
	private IRichContentDao contentDao;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private IDeptService deptService;
	@Autowired
	private IStaffService staffService;

	private static Logger logger = Logger.getLogger(RichContentService.class);

	// 获取通用内容列表
	public List<RichContentDto> listContent(String mainTypeCode, int orgId,String title, int page, int paging)
			throws DataException {

		List<RichContentDto> norContentList = new ArrayList<RichContentDto>();
		// 下面参数需要做适当的对照
		List<RichContentInnerEbo> richContentInners = contentDao.listContent(mainTypeCode,orgId, title, page, paging);
		if (richContentInners == null)
			return null;
		Map<String, RichContentDto> richContentMap = new HashMap<String, RichContentDto>();
		List<Integer> norContentIds = new ArrayList<Integer>();
		for (RichContentInnerEbo richContentInner : richContentInners) {
			RichContentDto richContentDto = richContentMap.get(richContentInner.getId() + "");
			norContentIds.add(richContentInner.getId());
			if (richContentDto == null) {
				richContentDto = new RichContentDto();
				BeanUtils.copyProperties(richContentInner, richContentDto);
				richContentMap.put(richContentDto.getId() + "", richContentDto);
				norContentList.add(richContentDto);
			}
		}

		return norContentList;
	}

	// 通过ID或者Code获取通用内容
	public RichContentEbo getContentByIdOrCode(int id, String Code) throws DataException {
		boolean isNull = id <= 0 && Util.isNull(Code) ? true : false;
		if (isNull)
			return null;
		return contentDao.getContentByIdOrCode(id, Code);
	}

	// 添加通用内容信息
	public void addContent(RichContentEto norContent) throws DataException {
		String code = null;
		if (Util.isNull(norContent.getMainTypeCode())) {
			throw new DataException("请选择通用内容所属类型" + norContent.getMainTypeCode());
		} else if (norContent.getMainId() == 0) {
			throw new DataException("请选择通用内容所属类型的具体ID" + norContent.getMainId());
		} else {
			RichContentEbo nor1 = getContentByMainIdType(norContent.getMainId(), norContent.getMainTypeCode());
			if (nor1 != null) {
				throw new DataException("您的详情已经存在，不可重复添加" + norContent.getId());
			} else {
				RichContentEbo nor = null;
				// 产生一个64位的Code
				code = Util.genDigiCodeStr(RichContentEto.CODE_LEN);
				nor = getContentByIdOrCode(0, code);
				while (nor != null) {
					code = Util.genDigiCodeStr(RichContentEto.CODE_LEN);
					nor = getContentByIdOrCode(0, code);
				}
			}
			try {
				RichContentEbo norEbo = null;
				norEbo = new RichContentEbo();
				BeanUtils.copyProperties(norContent, norEbo);
				norEbo.setCode(code);
				contentDao.addContent(norEbo);
			} catch (DataException e) {
				logger.error(e.getMessage());
				throw new DataException(e.getMessage());
			}
		}
	}

	// 修改通用内容信息
	public void updateContent(RichContentEbo content) throws DataException {
		String code = content.getCode();
		RichContentEbo norContentByIdOrCode = getContentByIdOrCode(0, code);

		if (norContentByIdOrCode == null) {
			throw new DataException("该详情不存在" + code);
		}

		/*if (Util.isNull(content.getMainTypeCode())) {
			throw new DataException("请选择通用内容所属类型" + content.getMainTypeCode());
		} else {
			if (content.getMainTypeCode().equals("ORG")) {
				OrgEbo orgById = orgService.getOrgById(content.getMainId());
				if (orgById == null) {
					throw new DataException("系统无此组织" + content.getMainId());
				}
			} else if (content.getMainTypeCode().equals("DEPT")) {
				DepartmentEbo departmentById = deptService.getDepartmentById(content.getMainId());
				if (departmentById == null) {
					throw new DataException("系统无此部门" + content.getMainId());
				}
			} else if (content.getMainTypeCode().equals("STAFF")) {
				StaffEbo staffById = staffService.getStaffById(content.getMainId());
				if (staffById == null) {
					throw new DataException("系统无此医生" + content.getMainId());
				}
			}
		}*/
		contentDao.updateContent(content);
	}

	// 获取通用内容总数据数
	public int getTotalContent(String mainTypeCode,int orgId, String title) throws DataException {
		return contentDao.getTotalContent(mainTypeCode,orgId, title);
	}

	// 通过mainID,mainTypeCode获取通用内容
	public RichContentEbo getContentByMainIdType(int mainId, String mainTypeCode) throws DataException {
		if (Util.isNull(mainTypeCode)) {
			throw new DataException("请选择通用内容所属类型" + mainTypeCode);
		} else if (mainId == 0) {
			throw new DataException("请选择通用内容所属类型的具体ID" + mainId);
		} else {
			return contentDao.getContentByMainIdAndMainType(mainId, mainTypeCode);

		}
	}
}
