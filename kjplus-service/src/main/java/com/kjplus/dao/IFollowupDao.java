package com.kjplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.model.CodeRepoEbo;
import com.kjplus.model.FollowupExamEbo;
import com.kjplus.model.FollowupMainEbo;
import com.kjplus.model.FollowupRecEbo;
import com.kjplus.model.inner.FollowupMainInnerEbo;
import com.kjplus.qto.FollowupMainQto;
import com.ybk.exception.DataException;

@Repository("followupDao")
public interface IFollowupDao {

	/**
	 * 
	 * @param id
	 *            <p>
	 *            t_code_repo表id
	 * @param code
	 *            <p>
	 *            t_code_repo表编号
	 * @return <p>
	 *         返回CodeRepoEbo对象
	 * @throws DataException
	 */
	public CodeRepoEbo getCodeRepoByOrCode(@Param("id") Integer id, @Param("code") String code) throws DataException;

	/**
	 * 
	 * @param id
	 *            t_followup_exam表id
	 * @param code
	 *            <p>
	 *            t_followup_exam表id
	 * @return <p>
	 *         返回FollowupExamEbo对象
	 * @throws DataException
	 */
	public FollowupExamEbo getFollowupExamByIdOrCode(@Param("id") Integer id, @Param("code") String code) throws DataException;

	/**
	 * 
	 * @param id
	 *            <p>
	 *            t_followup_rec表id
	 * @param code
	 *            <p>
	 *            t_followup_rec表id
	 * @return <p>
	 *         返回FollowupRecEbo对象
	 * @throws DataException
	 */
	public FollowupRecEbo getFollowupRecByIdOrCode(@Param("id") Integer id, @Param("code") String code) throws DataException;

	/**
	 * 
	 * @param id
	 *            <p>
	 *            t_followup_main表id
	 * @param code
	 *            <p>
	 *            t_followup_main表id
	 * @return <p>
	 *         返回FollowupMainEbo对象
	 * @throws DataException
	 */
	public FollowupMainEbo getFollowupMainByIdOrCode(@Param("id") Integer id, @Param("code") String code) throws DataException;

	/**
	 * 
	 * @param selfCode
	 *            <p>
	 *            t_followup_main表self_code
	 * @param orgId
	 *            <p>
	 *            t_followup_main表org_id
	 * @return <p>
	 *         返回FollowupMainEbo对象
	 * @throws DataException
	 */
	public FollowupMainEbo getFollowupMainBySelfCode(@Param("selfCode") String selfCode, @Param("orgId") int orgId) throws DataException;

	// 添加随访记录
	public void addFollowupMain(FollowupMainEbo followupMainEbo) throws DataException;

	// 添加随访结论记录
	public void addOrModifyFollowupRes(@Param("res") FollowupMainEbo followupMainEbo) throws DataException;

	// 列表随访记录 　
	public List<FollowupMainInnerEbo> listFollowupMainInnerEbo(@Param("fuQto") FollowupMainQto fuQto, @Param("page") int page, @Param("paging") int paging) throws DataException;

	// 随访记录总数 　
	public int getTotalFollowupMain(@Param("fuQto") FollowupMainQto fupQto) throws DataException;

	/**
	 * 更新随访记录
	 * 
	 * @param fMainEbo
	 */
	public void updateFollowupMain(@Param("fMainEbo") FollowupMainEbo fMainEbo);

}
