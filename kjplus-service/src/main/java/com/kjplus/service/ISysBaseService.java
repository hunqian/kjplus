package com.kjplus.service;

import java.util.List;

import com.kjplus.dto.SysRefTypeDto;
import com.kjplus.eto.SysRefTypeEto;
import com.kjplus.model.*;
import com.ybk.exception.DataException;

public interface ISysBaseService {

	// 增加一个refType
	public SysRefTypeEbo addRefType(SysRefTypeEto refType) throws DataException;

	//列表refType  模糊查询
	public List<SysRefTypeEbo> listRefType(String code,String name,String flag, int page, int paging) throws DataException;

	//添加refVl
	//code要进行唯一性判断
	public SysRefValueEbo addRefVl(Integer refId, String code, String refVl, String name, String extVlType) throws DataException;
	
	//列表refType总数    模糊查询后总数
	public int getTotalRefType(String code,String name,String flag) throws DataException;

	// 获得类型
	public SysRefTypeEbo getRefTypeByCode(String code) throws DataException;

	// 获得类型
	public SysRefTypeEbo getRefTypeById(int refTypeId) throws DataException;

	// 通过id获取refValue对象
	public SysRefValueEbo getRefVlById(int valueId) throws DataException;
	
	//通过ID获取refValue列表
	public List<SysRefValueEbo> getRefVlByRefId(int refId, String flag) throws DataException;
	
	public List<SysRefValueEbo> getRefVlByRefId(int refId, String prefix, String flag) throws DataException;
	
	// 通过code获得参照值
	public SysRefValueEbo getRefVlByCode(String vlCode) throws DataException;
	
	//获得参照类型值列表
	public List<SysRefValueEbo> listRefVlByRefCode(String typeCode, String flag) throws DataException ;

	//获得参照类型值列表,并可以根据父值字段进行是筛选
	public List<SysRefValueEbo> listRefVlByRefPreCode(String typeCode, String flag) throws DataException ;
	
	public List<SysRefValueEbo> listRefVlByRefPreCode(String typeCode, String prefix, String flag) throws DataException;
	
	//罗列一组的参照值
	public List<SysRefTypeDto> listRefTypesWithVl(List<Integer> typeIds) throws DataException ;
	
}
