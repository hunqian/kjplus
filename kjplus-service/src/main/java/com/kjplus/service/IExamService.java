package com.kjplus.service;

import java.util.List;
import java.util.Map;

import com.kjplus.dto.ExamMainSimpleDto;
import com.kjplus.eto.ExamBloodEto;
import com.kjplus.eto.ExamGlycemicEto;
import com.kjplus.eto.ExamUrineEto;
import com.kjplus.model.ExamBloodEbo;
import com.kjplus.model.ExamGlycemicEbo;
import com.kjplus.model.ExamMainEbo;
import com.kjplus.model.ExamUrineEbo;
import com.kjplus.model.inner.ExamMainInnerEbo;
import com.kjplus.qto.ExamQto;
import com.ybk.exception.DataException;

public interface IExamService {

	// 通过id，exmainId获取ExamBloodEbo 对象
	public ExamBloodEbo getExamBloodEboByIdOrMainId(int id, int exmainId) throws DataException;

	// 添加血压和主表
	public ExamMainInnerEbo addExamBloodEbo(ExamBloodEto examBlood) throws DataException;

	// 通过id，exmainId获取ExamGlycemicEbo 对象
	public ExamGlycemicEbo getExamGlycemicEboByIdOrMainId(int id, int exmainId) throws DataException;

	// 添加血糖和主表
	public ExamMainInnerEbo addExamGlycemicEbo(ExamGlycemicEto examGlycemic) throws DataException;

	// 通过id，code获取ExamMainEbo 对象
	public ExamMainEbo getExamMainEboByIdOrCode(int id, String code) throws DataException;

	// 获取某次测试的记录
	public ExamMainInnerEbo getExamMainInnerEbo(int exmainId, int flpeId) throws DataException;

	// 列表测试记录
	/*
	 * public List<ExamMainSimpleDto> listExamMainInnerEbo(String
	 * refTypeCode,int prsnId ,int staffId, int orgId, String firstDay,String
	 * lastDay,int page,int paging) throws DataException;
	 */
	public List<ExamMainSimpleDto> listExamMainInnerEbo(ExamQto examQto, int page, int paging) throws DataException;

	public int gettotalExamMain(String refTypeCode, int prsnId, int staffId, int orgId, String firstDay, String lastDay) throws DataException;

	// 根据随访id获得对应的检查记录 map key=flpeId value = ExamEbo
	public Map<Integer, List<Object>> listExamObjs(List<Integer> flpeIds) throws DataException;

	public ExamUrineEbo getExamUrineEboById(int id) throws DataException;

	// 批量添加尿检查数据
	public void addExamUrine(List<ExamUrineEto> listUrine) throws DataException;

}
