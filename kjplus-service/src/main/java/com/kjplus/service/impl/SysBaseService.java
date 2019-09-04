package com.kjplus.service.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ybk.basic.util.Util;

import com.kjplus.constant.Constant;
import com.kjplus.dao.ISysBaseDao;
import com.kjplus.dto.SysRefTypeDto;
import com.kjplus.dto.SysRefVlDto;
import com.kjplus.eto.SysRefTypeEto;
import com.kjplus.model.SysRefTypeEbo;
import com.kjplus.model.SysRefValueEbo;
import com.kjplus.model.inner.SysRefValueInnerEbo;
import com.kjplus.service.ISysBaseService;
import com.kjplus.util.DataValUtil;
import com.ybk.exception.DataException;

@Service("baseService")
public class SysBaseService implements ISysBaseService {

	@Autowired
	private ISysBaseDao baseDao;

	private static Logger logger = Logger.getLogger(SysBaseService.class);

	public SysRefValueEbo getRefVlByCode(String vlCode) throws DataException {
		if (Util.isNull(vlCode))
			return null;
		return baseDao.getRefVlByCode(vlCode);
	}

	public SysRefTypeEbo addRefType(SysRefTypeEto refType) throws DataException {
		// 空值验证
		DataValUtil.dataValidation(refType.getClass(), refType);
		String code = refType.getRefCode();
		if (Util.isNotNull(code)) {
			SysRefTypeEbo t2 = getRefTypeByCode(code);
			if (t2 != null)
				return t2;
		} else {
			code = Util.genSecID(SysRefTypeEto.TYPE_CODE_LEN);
			SysRefTypeEbo t2 = getRefTypeByCode(code);
			while (t2 != null) {
				code = Util.genSecID(SysRefTypeEto.TYPE_CODE_LEN);
				t2 = getRefTypeByCode(code);
			}
		}
		SysRefTypeEbo t = new SysRefTypeEbo();
		t.setFlag(Constant.FLAG_YES);
		t.setRefCode(code);
		t.setRefMemo(refType.getRefMemo());
		t.setRefName(refType.getRefName());
		t.setRefType(refType.getRefType());
		try {
			baseDao.addRefType(t);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return t;
	}

	// 列表refType 模糊查询 code name
	public List<SysRefTypeEbo> listRefType(String code, String name, String flag, int page, int paging) throws DataException {

		List<SysRefTypeEbo> listRefType = new ArrayList<SysRefTypeEbo>();
		try {
			listRefType = baseDao.listRefType(code, name, flag, page, paging);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return listRefType;
	};

	// 添加refVl
	// code要进行唯一性判断
	public SysRefValueEbo addRefVl(Integer refId, String code, String refVl, String name, String extVlType) throws DataException {
		SysRefTypeEbo sysRefTypeEbo = null;
		SysRefValueEbo sysRefValueEbo = null;

		sysRefTypeEbo = getRefTypeById(refId);
		if (sysRefTypeEbo == null) {
			throw new DataException("系统无此类型，refId=" + refId);
		}
		sysRefValueEbo = getRefVlByCode(code);
		if (sysRefValueEbo != null) {
			throw new DataException("系统已经有此类型值:" + code);
		}
		sysRefValueEbo = new SysRefValueEbo();
		sysRefValueEbo.setRefId(refId);
		sysRefValueEbo.setCode(code);
		sysRefValueEbo.setRefVl(refVl);
		sysRefValueEbo.setName(name);
		sysRefValueEbo.setExtVlType(extVlType);
		try {
			// 默认flag为YES
			String flag = Constant.FLAG_YES;
			sysRefValueEbo.setFlag(flag);
			baseDao.addRefVl(sysRefValueEbo);
		} catch (Exception e) {
			logger.error(e);

		}

		return sysRefValueEbo;
	};

	// 列表refType总数 模糊查询后总数
	public int getTotalRefType(String code, String name, String flag) throws DataException {
		int totalRefType = baseDao.getTotalRefType(code, name, flag);
		return totalRefType;
	}

	// 获得参照类型值列表
	public List<SysRefValueEbo> listRefVlByRefCode(String typeCode, String flag) throws DataException {
		SysRefTypeEbo t = getRefTypeByCode(typeCode);
		if (t == null)
			return new ArrayList<SysRefValueEbo>();
		return getRefVlByRefId(t.getId(), null, flag);
	}

	// 获得参照类型值列表,并可以根据父值字段进行是筛选
	public List<SysRefValueEbo> listRefVlByRefPreCode(String typeCode, String prefix, String flag) throws DataException {
		// 空值判断
		Boolean isNull = Util.isNull(typeCode) && Util.isNull(prefix) ? true : false;
		if (isNull)
			return new ArrayList<SysRefValueEbo>();
		SysRefTypeEbo t = getRefTypeByCode(typeCode);
		if (t == null && Util.isNotNull(prefix))
			return getRefVlByRefId(0, prefix + ".", flag);
		else if (Util.isNotNull(prefix))
			return getRefVlByRefId(t.getId(), null, flag);
		else
			return new ArrayList<SysRefValueEbo>();
	}

	public List<SysRefValueEbo> getRefVlByRefId(int refId, String prefix, String flag) throws DataException {
		boolean isNull = refId <= 0 && Util.isNull(prefix) ? true : false;
		if (isNull)
			return null;
		return baseDao.getRefVlByRefId(refId, prefix, flag);
	}

	// 获得SysRefTypeEbo通过Code
	public SysRefTypeEbo getRefTypeByCode(String code) throws DataException {
		if (Util.isNull(code))
			return null;
		return baseDao.getRefTypeByCode(code);
	}

	// 获得类型
	public SysRefTypeEbo getRefTypeById(int refTypeId) throws DataException {
		if (refTypeId <= 0)
			return null;
		return baseDao.getRefTypeById(refTypeId);
	}

	public SysRefValueEbo getRefVlById(int valueId) throws DataException {
		if (valueId <= 0)
			return null;
		return baseDao.getRefVlById(valueId);
	}

	// 罗列一组的参照值
	public List<SysRefTypeDto> listRefTypesWithVl(List<Integer> typeIds) throws DataException {

		List<SysRefTypeDto> types = new ArrayList<SysRefTypeDto>();
		if (typeIds == null || typeIds.size() == 0)
			return types;

		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < typeIds.size(); i++) {
			if (i != 0)
				buf.append(",");
			buf.append(typeIds.get(i));
		}
		Hashtable<Integer, SysRefTypeDto> typeHash = new Hashtable<Integer, SysRefTypeDto>();
		List<SysRefValueInnerEbo> vlList = baseDao.listRefVlInnerEbo(buf.toString());
		SysRefTypeDto t = null;
		SysRefVlDto v = null;
		for (int i = 0; i < vlList.size(); i++) {
			SysRefValueInnerEbo vi = vlList.get(i);
			if (typeHash.containsKey(vi.getRefId())) {
				t = typeHash.get(vi.getRefId());
			} else {
				t = new SysRefTypeDto();
				t.setId(vi.getRefId());
				t.setRefCode(vi.getRefCode());
				t.setRefMemo(vi.getRefMemo());
				t.setRefType(vi.getRefType());
				t.setRefName(vi.getRefName());
				typeHash.put(vi.getRefId(), t);
				types.add(t);
			}
			v = new SysRefVlDto();
			v.setRefVl(vi.getRefVl());
			v.setRefVlCode(vi.getCode());
			v.setRefVlId(vi.getId());
			v.setRefVlName(vi.getName());
			t.getRefVls().add(v);
		}
		return types;
	}

	public List<SysRefValueEbo> listRefVlByRefPreCode(String typeCode, String flag) throws DataException {
		return listRefVlByRefPreCode(typeCode, null, flag);
	}

	public List<SysRefValueEbo> getRefVlByRefId(int refId, String flag) throws DataException {
		return getRefVlByRefId(refId, null, flag);
	}

}
