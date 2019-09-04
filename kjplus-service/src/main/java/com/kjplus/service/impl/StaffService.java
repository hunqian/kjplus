package com.kjplus.service.impl;

import java.util.*;

import com.kjplus.model.inner.DeptStaffInnerEbo;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.ybk.basic.util.Util;

import com.kjplus.annotation.*;
import com.kjplus.constant.Constant;
import com.kjplus.dao.*;
import com.kjplus.dto.*;
import com.kjplus.eto.AdminUserEto;
import com.kjplus.eto.StaffEto;
import com.kjplus.eto.UserEto;
import com.kjplus.model.*;
import com.kjplus.service.*;
import com.ybk.exception.DataException;

@Service("staffService")
public class StaffService implements IStaffService {

	@Autowired
	private IStaffDao staffDao;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private ISysBaseService baseService;
	@Autowired
	private IDeptService deptService;
	@Autowired
	private ITagService tagService;
	@Autowired
	private IUserMapService userMapService;
	@Autowired
	private IAdminUserService adminUserService;
	@Autowired
	private IUserService userService;

	private static Logger logger = Logger.getLogger(StaffService.class);

	// 通过编号查询staff
	public StaffEbo getStaffByCode(String code) throws DataException {
		if (Util.isNull(code))
			return null;
		return staffDao.getStaffByCode(code);
	}

	// 通过id查询staff
	public StaffEbo getStaffById(int staffId) throws DataException {
		if (staffId <= 0)
			return null;
		return staffDao.getStaffById(staffId);
	}

	//加入事务
	@Transactional(value = "txManager", rollbackFor = { RuntimeException.class, Exception.class, DataException.class }, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, readOnly = true, timeout = 3)
	public StaffEbo addStaff(StaffEto staff) throws DataException {
		if (staff == null)
			throw new DataException("[err]添加staff对象不能空");
		if (Util.isNull(staff.getName()))
			throw new DataException("[err]人员名称不能空");
		if (staff.getOrgId() <= 0)
			throw new DataException("[err]请指定组织!");
		if (staff.getDeptId() <= 0)
			throw new DataException("[err]请指定科室类型!");
		if (staff.getTypeId() <= 0)
			throw new DataException("[err]请指定人员类型!");

		StaffEbo staffEbo = null;
		String code = staff.getCode();
		// 产生一个8位长度
		if (Util.isNull(code)) {
			code = Util.genDigiCodeStr(StaffEto.CODE_LEN);
			staffEbo = getStaffByCode(code);
			while (staffEbo != null) {
				code = Util.genDigiCodeStr(StaffEto.CODE_LEN);
				staffEbo = getStaffByCode(code);
			}
		} else {
			staffEbo = getStaffByCode(code);
			if (staffEbo != null)
				return staffEbo;
		}
		OrgEbo org = orgService.getOrgById(staff.getOrgId());
		if (org == null)
			throw new DataException("[err]无此组织，ID为：" + staff.getOrgId());

		SysRefValueEbo refVl = baseService.getRefVlById(staff.getTypeId());
		if (refVl == null)
			throw new DataException("[err]无此类型，ID为：" + staff.getTypeId());

		DepartmentEbo dept = deptService.getDepartmentById(staff.getDeptId());
		if (dept == null)
			throw new DataException("[err]无此科室类型，ID为：" + staff.getDeptId());
		StaffEbo stfEbo = new StaffEbo();
		try {
			BeanUtils.copyProperties(staff, stfEbo);
			stfEbo.setCode(code);
			stfEbo.setFlag(Constant.FLAG_YES);
			stfEbo.setCreateTime(Calendar.getInstance().getTime());
			staffDao.addStaff(stfEbo);
			
			/*
			 * 分别建立医生与管理员，与普通用户的关系
			 * */
			// 添加医生对应的管理员用户
			//根据用户名，电话号码查询是否存在管理员账户
			AdminUserEbo admin = adminUserService.getUserByUnameMobile(stfEbo.getName(), stfEbo.getMobileNum());
			if(admin == null){//不存在的话建立一个新的管理员账户
				AdminUserEto adminUser = new AdminUserEto();
				adminUser.setUserName(stfEbo.getName());
				adminUser.setPassWord(Constant.DEFAULT_PASSWORD);
				adminUser.setMobileNum(stfEbo.getMobileNum());
				adminUser.setFace(stfEbo.getHeadIconUrl());
				adminUser.setNickName(stfEbo.getName());
				adminUser.setOrgId(stfEbo.getOrgId());
				adminUserService.register(adminUser);
				admin =  adminUserService.getUserByUnameMobile(stfEbo.getName(), stfEbo.getMobileNum());
			}
			//医生用户与管理员用户建立关系
			UserMapEbo userMapEbo = new UserMapEbo();
			userMapEbo.setMainId(stfEbo.getId());
			userMapEbo.setMainType(Constant.USER_TYPE_STAFF);
			userMapEbo.setUid(admin.getUid());
			userMapEbo.setUserType(Constant.USER_TYPE_ADMIN);
			userMapService.addUserMap(userMapEbo);
			
			//建立医生的普通用户
			UserEbo u = userService.getUserByNameOrMobile(stfEbo.getName(), stfEbo.getMobileNum());
			if(u == null){
				UserEto user = new UserEto();
				user.setUserName(stfEbo.getName());
				user.setNickName(stfEbo.getName());
				user.setMobileNum(stfEbo.getMobileNum());
				user.setPassWord(Constant.DEFAULT_PASSWORD);
				user.setFace(stfEbo.getHeadIconUrl());
				user.setUserType(Constant.USER_TYPE_STAFF);//医生用户
				user.setOrgId(stfEbo.getOrgId());
				u = userService.addUser(user);
			}
			//建立医生与普通用户的关系
			UserMapEbo userMap = new UserMapEbo();
			userMap.setMainId(stfEbo.getId());
			userMap.setMainType(Constant.USER_TYPE_STAFF);
			userMap.setUid(u.getUid());
			userMap.setUserType(Constant.USER_TYPE_UNIVERSIAL);
			userMapService.addUserMap(userMap);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return stfEbo;
	}

	@PerformanceLog(methodType = MethodType.SELECT, bizType = BizType.PERSON)
	public List<StaffDto> listStaffDto(int orgId, int deptId,String staffName,String flag,String status, int page, int paging) throws DataException {
		
		//查询数据库数据
		List<DeptStaffInnerEbo> deptStaffInners = staffDao.listStaffDeptDto(orgId, deptId,staffName,flag,status, page, paging);
		//整合数据（一个医生对应多个团队）
		List<StaffDto> staffs = new ArrayList<StaffDto>();
		//医生信息筛选  
		Hashtable<Integer, StaffDto> staffHash = new Hashtable<Integer, StaffDto>();
		StaffDto sd = null;
		DeptSimpleDto dsd = null;
		//用于医生标签筛选
		List<Integer> stfIds = new ArrayList<Integer>();
		//整合团队信息
		for (DeptStaffInnerEbo innerEbo : deptStaffInners) {
			
			if (staffHash.containsKey(innerEbo.getStafId()))
				sd = staffHash.get(innerEbo.getStafId());
			else {
				//保存医生部门信息
				sd = new StaffDto();
				BeanUtils.copyProperties(innerEbo, sd);
				sd.setTypeId(innerEbo.getStafTypeId());
				sd.setTypeName(innerEbo.getStafTypeVl());
				sd.setOrgId(innerEbo.getOrgId());
				sd.setMemo(innerEbo.getStafMemo());
				staffs.add(sd);
				staffHash.put(innerEbo.getStafId(), sd);
				stfIds.add(innerEbo.getStafId());
			}
			//保存医生团体信息
			dsd = new DeptSimpleDto();
			dsd.setId(innerEbo.getgDeptId());
			dsd.setCode(innerEbo.getgDeptCode());
			dsd.setDeptType(innerEbo.getgDeptType());
			dsd.setName(innerEbo.getgDeptName());
			dsd.setMemo(innerEbo.getgDeptMemo());
			sd.getDepts().add(dsd);
		}
		//查询医生标信息
		List<TagEbo> tags = tagService.listTagArray(stfIds, Constant.TAG_TYPE_STAFF);
		for (TagEbo t : tags) {
			if (!staffHash.containsKey(t.getMainId()))
				continue;
			sd = staffHash.get(t.getMainId());
			sd.getTags().add(new TagDto(t.getId(), t.getRefValId(), t.getRefValName()));
		}
		return staffs;
	}

	// 获得orgid下所有的,部门id部门列表,以及该部门所对应的staff列表
	// fetchDeptHiarch:是否返回层级树状部门
	// omitZeroStaff：是否忽略无staff部门
	public List<DeptStaffDto> listDeptStaffDto(int orgId, String deptType, boolean fetchDeptHiarch,
			boolean omitZeroStaff) throws DataException {
		List<DeptStaffDto> deptStaffDtos = new ArrayList<DeptStaffDto>();
		//查询数据
		List<DeptStaffInnerEbo> deptStaffInnerEbos = staffDao.listStaffDeptDto(orgId, 0,null,null,null, 0, 10);
		HashMap<Integer, DeptStaffDto> maps = new HashMap<Integer, DeptStaffDto>();
		// 对实体部门的处理
		if (Constant.DEPT_TYPE_REAL.equals(deptType) || Util.isNull(deptType)) {
			StaffSimpleDto stafObj = null;
			DeptStaffDto deptStaffDto = null;
			int deptid = 0;
			// 查询全部 或者只查询部门
			for (DeptStaffInnerEbo innerEbo : deptStaffInnerEbos) {
				deptid = innerEbo.getDeptId();
				if (!maps.containsKey(deptid) /* || !fetchDeptHiarch */) {
					deptStaffDto = new DeptStaffDto();
					BeanUtils.copyProperties(innerEbo, deptStaffDto);
					stafObj = new StaffSimpleDto();
					BeanUtils.copyProperties(innerEbo, stafObj);
					stafObj.setStafType(innerEbo.getStafTypeId()); 	
					stafObj.setRealDeptId(innerEbo.getDeptId());
					stafObj.setRealDeptCode(innerEbo.getDeptCode());
					stafObj.setRealDept(innerEbo.getDeptName());
					deptStaffDto.getStafs().add(stafObj);
					deptStaffDtos.add(deptStaffDto);
				} else {
					deptStaffDto = maps.get(deptid);
					stafObj = new StaffSimpleDto();
					BeanUtils.copyProperties(innerEbo, stafObj);
					stafObj.setStafType(innerEbo.getStafTypeId());
					stafObj.setRealDeptId(innerEbo.getDeptId());
					stafObj.setRealDeptCode(innerEbo.getDeptCode());
					stafObj.setRealDept(innerEbo.getDeptName());
					deptStaffDto.getStafs().add(stafObj);
				}
				maps.put(deptid, deptStaffDto);
			}
		}

		// 对团体进行处理
		if (Constant.DEPT_TYPE_GRUOP.equals(deptType) || Util.isNull(deptType)) {
			StaffSimpleDto gStafObj = null;
			DeptStaffDto gDeptStaffDto = null;
			int gDeptid = 0;
			for (DeptStaffInnerEbo innerEbo : deptStaffInnerEbos) {
				if (Constant.DEPT_TYPE_GRUOP.equals(Util.val(innerEbo.getgDeptType()))) {// 处理团体部门
					gDeptid = innerEbo.getgDeptId();
					if (!maps.containsKey(gDeptid) /* || !fetchDeptHiarch */) {// 实体部门与团队id不同
						gDeptStaffDto = new DeptStaffDto();
						// BeanUtils.copyProperties(innerEbo, gDeptStaffDto);
						gDeptStaffDto.setDeptId(innerEbo.getgDeptId());
						gDeptStaffDto.setDeptCode(innerEbo.getgDeptCode());
						gDeptStaffDto.setDeptName(innerEbo.getgDeptName());
						gDeptStaffDto.setDeptType(innerEbo.getgDeptType());
						gDeptStaffDto.setDeptMemo(innerEbo.getDeptMemo());
						gStafObj = new StaffSimpleDto();
						BeanUtils.copyProperties(innerEbo, gStafObj);
						gStafObj.setStafType(innerEbo.getStafTypeId());
						gStafObj.setRealDeptId(innerEbo.getDeptId());
						gStafObj.setRealDeptCode(innerEbo.getDeptCode());
						gStafObj.setRealDept(innerEbo.getDeptName());
						gDeptStaffDto.getStafs().add(gStafObj);
						deptStaffDtos.add(gDeptStaffDto);
					} else {
						gDeptStaffDto = maps.get(gDeptid);
						gStafObj = new StaffSimpleDto();
						BeanUtils.copyProperties(innerEbo, gStafObj);
						gStafObj.setStafType(innerEbo.getStafTypeId());
						gStafObj.setRealDeptId(innerEbo.getDeptId());
						gStafObj.setRealDeptCode(innerEbo.getDeptCode());
						gStafObj.setRealDept(innerEbo.getDeptName());
						gDeptStaffDto.getStafs().add(gStafObj);
					}
					maps.put(gDeptid, gDeptStaffDto);
				}
			}
		}
		for (int i = 0; i < deptStaffDtos.size();) {
			if (omitZeroStaff && deptStaffDtos.get(i).getStafs().size() == 0)
				deptStaffDtos.remove(i);
			else
				i++;
		}
		return deptStaffDtos;
	}

	// 通过staff名称,部门id，类型id，组织id查询部门列表
	public int getTotalStaffDto(int orgId, int deptId,String flag,String status) throws DataException {
		// orgId和deptId均可为空
		int count = staffDao.getTotalStaffDto(orgId, deptId,flag,status);
		return count;
	}

	// 更新列表
	/*
	 * public void updateStaff(int staffId, StaffEbo staff) throws DataException
	 * { staffDao.updateStaff(staffId, staff); };
	 */
	public void updateStaff(StaffEbo staff) throws DataException {
		boolean isNull = staff.getId() <= 0 && Util.isNull(staff.getCode()) ? true : false;
		if (isNull)
			throw new DataException("[err]修改医生信息时，id和code不能同时为空");
		staffDao.updateStaff(staff);
	}

	public List<StaffDeptEbo> listStaffDeptMapEbo(int staffId, int deptId) throws DataException {
		List<StaffDeptEbo> listStaffDeptEbo = new ArrayList<StaffDeptEbo>();
		if (staffId < 0 && deptId < 0)
			return listStaffDeptEbo;
		if (staffId > 0) {
			StaffEbo staff = getStaffById(staffId);
			if (staff == null)
				return listStaffDeptEbo;
		}
		if (deptId > 0) {
			DepartmentEbo dep = deptService.getDepartmentById(deptId);
			if (dep == null)
				return listStaffDeptEbo;
		}
		listStaffDeptEbo = staffDao.listStaffDeptMapEbo(staffId, deptId);
		return listStaffDeptEbo;
	}
}
