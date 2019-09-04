package com.kjplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.model.ExamBloodEbo;
import com.kjplus.model.ExamGlycemicEbo;
import com.kjplus.model.ExamMainEbo;
import com.kjplus.model.ExamUrineEbo;
import com.kjplus.model.inner.ExamMainInnerEbo;
import com.kjplus.qto.ExamQto;
import com.ybk.exception.DataException;

@Repository("examDao")
public interface IExamDao {

	/**
	 * 
	 * @param id
	 *            <p>
	 *            t_exam_blood表id
	 * @param exmainId
	 *            <p>
	 *            t_exam_main表编号
	 * @return <p>
	 *         返回ExamBloodEbo对象
	 * @throws DataException
	 */
	public ExamBloodEbo getExamBloodEboByIdOrMainId(@Param("id") int id, @Param("exmainId") int exmainId) throws DataException;

	// 添加血测试
	public void addExamBloodEbo(ExamBloodEbo examBlood) throws DataException;

	/**
	 * 
	 * @param id
	 *            <p>
	 *            t_exam_glycemic表id
	 * @param exmainId
	 *            <p>
	 *            t_exam_main表编号
	 * @return <p>
	 *         返回ExamGlycemicEbo对象
	 * @throws DataException
	 */
	public ExamGlycemicEbo getExamGlycemicEboByIdOrMainId(@Param("id") int id, @Param("exmainId") int exmainId) throws DataException;

	// 添加血测试
	public void addExamGlycemicEbo(ExamGlycemicEbo examGlycemic) throws DataException;

	/**
	 * 
	 * @param id
	 *            <p>
	 *            t_exam_main表id
	 * @param exmainId
	 *            <p>
	 *            t_exam_main表编号
	 * @return <p>
	 *         返回ExamMainEbo对象
	 * @throws DataException
	 */
	public ExamMainEbo getExamMainEboByIdOrCode(@Param("id") int id, @Param("code") String code) throws DataException;

	// 添加血测试
	public void addExamMainEbo(ExamMainEbo examMain) throws DataException;

	/**
	 * 
	 * @param exmainId
	 *            <p>
	 *            t_exam_main表编号
	 * @param flpeId
	 *            <p>
	 *            t_followup_main表编号
	 * @return <p>
	 *         返回ExamMainInnerEbo对象
	 * @throws DataException
	 */
	public ExamMainInnerEbo getExamMainInnerEbo(@Param("exmainId") int exmainId, @Param("flpeId") int flpeId) throws DataException;

	/**
	 * 
	 * @param firstDay
	 *            <p>
	 *            开始时间（天）
	 * @param lastDay
	 *            <p>
	 *            结束时间（天）（用于查询某一事件段内的测试）
	 * @return <p>
	 *         列表ExamMainInnerEbo对象
	 * @throws DataException
	 */

	/*
	 * public List<ExamMainInnerEbo> listExamMainInnerEbo(@Param("examTypeId")
	 * int examTypeId,@Param("prsnId") int prsnId ,@Param("staffId") int
	 * staffId,@Param("orgId") int orgId ,@Param("firstDay") String
	 * firstDay,@Param("lastDay") String lastDay ,@Param("page") int
	 * page,@Param("paging") int paging) throws DataException;
	 */
	public List<ExamMainInnerEbo> listExamMainInnerEbo(@Param("examQto") ExamQto examQto, @Param("page") int page, @Param("paging") int paging)
			throws DataException;

	public int gettotalExamMain(@Param("examTypeId") int examTypeId, @Param("prsnId") int prsnId, @Param("staffId") int staffId, @Param("orgId") int orgId,
			@Param("firstDay") String firstDay, @Param("lastDay") String lastDay) throws DataException;

	public List<ExamMainInnerEbo> listExamMainInnerEboByFlpeId(@Param("flpeIdsStr") String flpeIdsStr) throws DataException;

	/**
	 * 
	 * @param id
	 *            <p>
	 *            t_exam_urine_line表id
	 * @param exmainId
	 *            <p>
	 *            t_exam_main表编号
	 * @return <p>
	 *         返回ExamUrineEbo对象
	 * @throws DataException
	 */
	public ExamUrineEbo getExamUrineEboById(@Param("id") int id) throws DataException;

	// 批量添加尿检查数据
	public void addExamUrineEbo(List<ExamUrineEbo> listUrine) throws DataException;

}
