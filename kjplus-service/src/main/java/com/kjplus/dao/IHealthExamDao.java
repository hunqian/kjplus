package com.kjplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.model.HealthExamEbo;
import com.kjplus.model.inner.HealthExamInnerEbo;
import com.kjplus.qto.HealthExamQto;
import com.ybk.exception.DataException;

@Repository("healthExaminationDao")
public interface IHealthExamDao {

	/**
	 * 获取健康体检记录列表
	 * 
	 * @param hExamQto
	 *            筛选参数对象
	 * @return
	 */
	public List<HealthExamInnerEbo> listHealthExam(@Param("hExamQto") HealthExamQto hExamQto, @Param("page") int page, @Param("paging") int paging) throws DataException;

	/**
	 * 根据Id或Code获取指定的体检记录
	 * 
	 * @param id
	 *            体检记录信息表Id
	 * @param code
	 *            体检记录信息编码
	 * @return
	 */
	public HealthExamEbo getHealthExamByIdOrCode(@Param("id") int id, @Param("code") String code);

	/**
	 * 添加健康检查记录
	 * 
	 * @param hExamEbo
	 */
	public void addHealthExam(HealthExamEbo hExamEbo);

	/**
	 * 更新健康检查记录信息
	 * 
	 * @param hExamEbo
	 */
	public void updateHealthExam(HealthExamEbo hExamEbo);

	/**
	 * 获取健康检查记录数据总数
	 * 
	 * @param hExamQto
	 * @return
	 */
	public int getTotalHealthExam(@Param("hExamQto") HealthExamQto hExamQto);

}
