package com.kjplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.dto.IDNameDto;
import com.kjplus.model.*;
import com.kjplus.model.inner.OrgServHeadInnerEbo;
import com.kjplus.model.inner.ServiceAssignInnerEbo;
import com.ybk.exception.DataException;

@Repository("serviceAsgnDao")
public interface IServiceAsgnDao {

	/**
	 * 通过ID获取签约信息
	 * 
	 * @param servAsgnId
	 * @return
	 * @throws DataException
	 */
	public ServAsgnEbo getServAssByIdOrCode(@Param("id") int id, @Param("code") String code) throws DataException;
	
	/**
	 * 获取当前时间的用户签约状态  
	 * @param servAsgnId
	 * @return
	 * @throws DataException
	 */
	public List<ServAsgnEbo> listServAssByPrsnId(@Param("prsnId") int prsnId, @Param("endDay") String endDay) throws DataException;
	
	/**
	 * 添加签约    用户在有效期间只能签约一个团体
	 * 
	 * @param servAsgn
	 *            传入ServAsgnEbo对象
	 * @throws DataException
	 */
	public void addServAsgn(List<ServAsgnEbo> servAsgns) throws DataException;

	/**
	 * 
	 * @param personName
	 *            用户名字
	 * @param personId
	 *            用户id
	 * @param staffName
	 *            医生名字
	 * @param staffCode
	 *            医生编号
	 * @param isDefault
	 *            是否默认服务 
	 * @param endDay           
	 *             默认当前时间 (获取有效时间内的记录)
	 * @param page
	 *            分页开始first
	 * @param paging
	 *            分页结束end
	 * @return 返回List<ServAsgnDto>列表对象
	 * @throws DataException
	 */
	public List<ServiceAssignInnerEbo> listAsgnInner(@Param("personName") String personName, @Param("personId") int personId,
			@Param("staffName") String staffName, @Param("staffId") int staffId, @Param("isDefault") String isDefault,
			@Param("orgId") int orgId, @Param("status") String status,@Param("endDay") String endDay, @Param("page") int page,
			@Param("paging") int paging) throws DataException;

	public int getTotalServAss(@Param("personName") String personName, @Param("personId") int personId,
			@Param("staffName") String staffName, @Param("staffId") int staffId, @Param("isDefault") String isDefault,
			@Param("orgId") int orgId, @Param("status") String status,@Param("endDay") String endDay) throws DataException;

	/**
	 * 修改签约信息
	 * 
	 * @param servAsgn
	 *            传入ServAsgnEbo对象
	 * @throws DataException
	 */
	public void updateServAsgn(ServAsgnEbo servAsgn) throws DataException;

	/**
	 * 
	 * @param servAsgnId
	 *            <p>
	 *            签约服务的id
	 * @param personId
	 *            <p>
	 *            签约服务的pid对应person表的id
	 * @param status
	 *            <p>
	 *            签约服务的状态
	 *            <p>
	 *            Constant.SIGNUP_STATUS_APPLVT = "S";(申请)
	 *            <p>
	 *            Constant.SIGNUP_STATUS_REJECT = "R";(驳回)
	 *            <p>
	 *            Constant.SIGNUP_STATUS_CONFIRM = "Y";(确认)
	 * 
	 * @throws DataException
	 */
	public void updateServAsgnStatus(@Param("idCodes") List<IDNameDto> idCodes, @Param("status") String status,
			@Param("operTime") int operTime, @Param("memo") String memo) throws DataException;

	/**
	 * 
	 * @param id
	 *            t_service_head表id
	 * @return 返回ServHeadEbo对象
	 * @throws DataException
	 */
	public ServHeadEbo getServHeadById(@Param("id") int id) throws DataException;

	/**
	 * 添加协议
	 * 
	 * @param servHead
	 *            传入ServHeadEbo对象
	 * @throws DataException
	 */
	public void addServHead(ServHeadEbo servHead) throws DataException;

	/**
	 * 组织服务列表
	 * 
	 * @param orgId
	 *            组织id
	 * @return ServHeadEbo列表
	 * @throws DataException
	 */
	public List<OrgServHeadInnerEbo> listServHead(@Param("orgId") int orgId, @Param("orgCode") String orgCode,
			@Param("flag") String flag) throws DataException;

	/**
	 * 
	 * @param servHeadId
	 *            协议id
	 * @param orgId
	 *            协议orgid
	 * @param flag
	 *            协议状态
	 * @throws DataException
	 */
	public void updateServHeadByidOrOrgId(@Param("servHeadId") int servHeadId, @Param("orgId") int orgId,
			@Param("flag") String flag) throws DataException;

}
