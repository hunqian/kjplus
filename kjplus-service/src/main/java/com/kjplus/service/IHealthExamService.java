package com.kjplus.service;

import java.util.List;

import com.kjplus.dto.HealthExamDto;
import com.kjplus.eto.HealthExamEto;
import com.kjplus.model.HealthExamEbo;
import com.kjplus.qto.HealthExamQto;
import com.ybk.exception.DataException;

public interface IHealthExamService {

	// 获取健康检查记录列表
	public List<HealthExamDto> listHealthExam(HealthExamQto hExamQto, int page, int paging) throws DataException;

	// 根据Id或Code获取指定健康检查记录
	public HealthExamEbo getHealthExamByIdOrCode(int id, String code) throws DataException;

	// 添加健康检查记录
	public HealthExamEbo addHealthExam(HealthExamEto hExamEto) throws DataException;

	// 更新健康检查记录信息
	public void updateHealthExam(HealthExamEbo hExamEbo) throws DataException;

	// 获取健康检查记录表数据总数
	public int getTotalHealthExam(HealthExamQto hExamQto) throws DataException;
}
