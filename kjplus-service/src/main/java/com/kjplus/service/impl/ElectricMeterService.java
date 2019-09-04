package com.kjplus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ybk.basic.util.Util;

import com.kjplus.constant.Constant;
import com.kjplus.dao.IElectricMeterDao;
import com.kjplus.dto.ElectricMeterDto;
import com.kjplus.eto.ElectricMeterEto;
import com.kjplus.model.*;
import com.kjplus.model.inner.FileRepoInnerEbo;
import com.kjplus.service.*;
import com.kjplus.util.DataValUtil;
import com.ybk.exception.DataException;

@Service("electricMeterService")
public class ElectricMeterService implements IElectricMeterService {

	@Autowired
	private IElectricMeterDao electricMeterDao;
	@Autowired
	private IFileRepoService fileRepoService;

	public ElectricMeterEbo getElectricMeterEboByIdOrCode(int id, String code) throws DataException {
		boolean isNull = id < 0 && Util.isNull(code) ? true : false;
		if (isNull)
			throw new DataException("id和code不能同时为空！");
		return electricMeterDao.getElectricMeterEboByIdOrCode(id, code);
	}

	public ElectricMeterEbo addElectricMeter(ElectricMeterEto electricMeterEto) throws DataException {
		DataValUtil.dataValidation(electricMeterEto.getClass(), electricMeterEto);
		String code = electricMeterEto.getMeterCode();
		ElectricMeterEbo el = null;
		if(Util.isNull(code)){
			code = Util.genDigiCodeStr(ElectricMeterEto.CODE_LEN);
			el = getElectricMeterEboByIdOrCode(0, code);
			while(el != null){
				Util.genDigiCodeStr(ElectricMeterEto.CODE_LEN);
				el = getElectricMeterEboByIdOrCode(0, code);
			}
		}else{
			el = getElectricMeterEboByIdOrCode(0, code);
			if(el != null)
				return el;
		}
		
		electricMeterEto.setMeterCode(code);
		ElectricMeterEbo ele = new ElectricMeterEbo();
		BeanUtils.copyProperties(electricMeterEto, ele);
		ele.setMeterCode(code);
		electricMeterDao.addElectricMeter(ele);
		return ele;
	}

	public List<ElectricMeterEbo> listElectricMeterEbo(String province, String city, String area, String addr,
			int page, int paging) throws DataException {
		List<ElectricMeterEbo> listEle = new ArrayList<ElectricMeterEbo>();
		if (paging <= 0)
			return listEle;
		listEle = electricMeterDao.listElectricMeterEbo(province, city, area, addr, page, paging);
		return listEle;
	}

	public int getTotalElectric(String province, String city, String area, String addr) throws DataException {

		return electricMeterDao.getTotalElectric(province, city, area, addr);
	}

	public List<ElectricMeterDto> listElectricMeterDto(String province, String city, String area, String addr,
			int page, int paging) throws DataException {
		List<ElectricMeterDto> listEleDto = new ArrayList<ElectricMeterDto>();
		List<ElectricMeterEbo> listEleEbo = new ArrayList<ElectricMeterEbo>();
		if (paging <= 0)
			return listEleDto;
		listEleEbo = electricMeterDao.listElectricMeterEbo(province, city, area, addr, page, paging);
		for (ElectricMeterEbo ele : listEleEbo) {
			ElectricMeterDto eleDto = new ElectricMeterDto();
			BeanUtils.copyProperties(ele, eleDto);
			List<FileRepoInnerEbo> listFile = fileRepoService.listFileRepoInner(0, ele.getId(), Constant.ELE_MAIN_TYPE,
					Constant.ELE_FLPE_TYPE, 0, 100);
			eleDto.setListFile(listFile);
			listEleDto.add(eleDto);
		}
		return listEleDto;
	}

}
