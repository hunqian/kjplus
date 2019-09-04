package com.kjplus.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.kjplus.model.OrgEbo;
import com.kjplus.model.inner.OrgInnerEbo;
import com.ybk.exception.DataException;

@Repository("orgDao")
public interface IOrgDao {

	/**
	 * 通过编号查询组织对象
	 * 
	 * @param code
	 *            编号
	 * @return 返回OrgEbo对象
	 * @throws DataException
	 */
	public OrgEbo getOrgByCode(@Param("code") String code) throws DataException;

	/**
	 * 通过组织id查询组织对象
	 * 
	 * @param orgId
	 *            组织id
	 * @return 返回OrgEbo对象
	 * @throws DataException
	 */
	public OrgEbo getOrgById(@Param("orgId") int orgId) throws DataException;

	/**
	 * 添加组织
	 * 
	 * @param org
	 *            传入OrgEbo对象
	 * @throws DataException
	 */
	public void addOrg(OrgEbo org) throws DataException;

	/**
	 * 添加组织
	 * 
	 * @param org
	 *            传入OrgEbo对象
	 * @throws DataException
	 */
	public List<OrgInnerEbo> listOrgInnerEbo(@Param("orgName") String orgName, @Param("flag") String flag
			, @Param("cityId") int cityId, @Param("prvnId") int prvnId
			, @Param("page") int page, @Param("paging") int paging) throws DataException;
	
	/**
	 * 获得组织总数
	 * 
	 * @throws DataException
	 */
	public int getTotalOrg(@Param("orgName") String orgName, @Param("flag") String flag
			, @Param("pid") int pid
			, @Param("cityId") int cityId, @Param("prvnId") int prvnId) throws DataException;


	//修改
	public void updateOrg(@Param("orgId") int orgId, @Param("orgName") String orgName, 
			@Param("orgAlias") String orgAlias, 
			@Param("orgAddr") String orgAddr,@Param("headIconUrl") String headIconUrl,
			@Param("flag") String flag, @Param("orgTypeId") int orgTypeId,
			@Param("areaId") int areaId, @Param("cityId") int cityId, @Param("prvnId") int prvnId) throws DataException;
	
	//根据id列表获得
	public List<OrgInnerEbo> listOrgInnerEboByIds(@Param("orgIds") String orgIds) throws DataException;

}
