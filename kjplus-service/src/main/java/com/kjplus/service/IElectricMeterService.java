package com.kjplus.service;

import java.util.List;

import com.kjplus.dto.ElectricMeterDto;
import com.kjplus.eto.ElectricMeterEto;
import com.kjplus.model.*;
import com.ybk.exception.DataException;

public interface IElectricMeterService {

	public ElectricMeterEbo getElectricMeterEboByIdOrCode(int id, String code) throws DataException;

	// 添加
	public ElectricMeterEbo addElectricMeter(ElectricMeterEto electricMeterEto) throws DataException;

	public List<ElectricMeterEbo> listElectricMeterEbo(String province, String city, String area, String addr,
			int page, int paging) throws DataException;

	public int getTotalElectric(String province, String city, String area, String addr) throws DataException;

	public List<ElectricMeterDto> listElectricMeterDto(String province, String city, String area, String addr,
			int page, int paging) throws DataException;

	
}
