package com.kjplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.model.ElectricMeterEbo;
import com.ybk.exception.DataException;

@Repository("electricMeterDao")
public interface IElectricMeterDao {

	public ElectricMeterEbo getElectricMeterEboByIdOrCode(@Param("id") int id, @Param("code") String code)throws DataException;

	//添加
	public void addElectricMeter(ElectricMeterEbo electricMeterEbo)throws DataException;

	public List<ElectricMeterEbo> listElectricMeterEbo(@Param("province") String province, @Param("city") String city
			, @Param("area") String area, @Param("addr") String addr,@Param("page") int page,@Param("paging") int paging)throws DataException;

	public int getTotalElectric(@Param("province") String province, @Param("city") String city
			, @Param("area") String area, @Param("addr") String addr)throws DataException;

}
