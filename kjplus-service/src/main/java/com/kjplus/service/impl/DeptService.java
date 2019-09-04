package com.kjplus.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ybk.basic.util.Util;

import com.kjplus.constant.Constant;
import com.kjplus.dao.IDeptDao;
import com.kjplus.dto.DepartmentDto;
import com.kjplus.eto.DepartmentEto;
import com.kjplus.model.DepartmentEbo;
import com.kjplus.service.IDeptService;
import com.ybk.exception.DataException;

@Service("deptService")
public class DeptService implements IDeptService {

	@Autowired
	private IDeptDao deptDao;

	private static Logger logger = Logger.getLogger(DeptService.class);

	// 通过编号查询部门
	public DepartmentEbo getDepartmentByCode(String code) throws DataException {
		if (Util.isNull(code))
			return null;
		return deptDao.getDepartmentByCode(code);
	}

	// 通过id查询部门
	public DepartmentEbo getDepartmentById(int deptId) throws DataException {
		if (deptId <= 0)
			return null;
		return deptDao.getDepartmentById(deptId);
	}

	// 添加部门信息
	public DepartmentEbo addDepartment(DepartmentEto dept) throws DataException {
		if (dept == null)
			throw new DataException("[err]添加部门对象不能空");
		if (Util.isNull(dept.getName()))
			throw new DataException("[err]部门名称不能空");
		if (dept.getOrgId() <= 0)
			throw new DataException("[err]请指定org类型!");
		DepartmentEbo department = null;
		String code = dept.getCode();
		// 产生一个8位长度
		if (Util.isNull(code)) {
			code = Util.genDigiCodeStr(DepartmentEto.CODE_LEN);
			department = getDepartmentByCode(code);
			while (department != null) {
				code = Util.genDigiCodeStr(DepartmentEto.CODE_LEN);
				department = getDepartmentByCode(code);
			}
		} else {
			department = getDepartmentByCode(code);
			if (department != null)
				return department;
		}

		if (dept.getPid() > 0) {
			department = getDepartmentById(dept.getPid());
			if (department == null)
				throw new DataException("[err]上级pid=" + dept.getPid() + "对应的dept不存在!");
		}

		DepartmentEbo departmentEbo = new DepartmentEbo();
		try {
			BeanUtils.copyProperties(dept, departmentEbo);
			departmentEbo.setCode(code);
			departmentEbo.setFlag(Constant.FLAG_YES);
			deptDao.addDepartment(departmentEbo);
			return departmentEbo;
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	// 获取部门列表
	public List<DepartmentDto> listDepartmentDto(String deptName, String deptType, int orgid, String flag, int page, int paging) throws DataException {
		List<DepartmentDto> deptList = null;
		try {
			deptList = deptDao.listDepartmentDto(deptName, deptType, orgid, flag, page, paging);
			System.out.println(1);
		} catch (Exception e) {
			logger.error(e);
		}
		return deptList;
	}

	public int getTotalDepartment(String deptName, int orgid, String flag) {
		try {
			return deptDao.getTotalDept(deptName, orgid, flag);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return 0;
	}

	// 修改部门信息
	public void updateDept(DepartmentEbo dept) throws DataException {
		// TODO Auto-generated method stub
		if (dept == null)
			throw new DataException("[err]添加部门对象不能空");
		if (Util.isNull(dept.getName()))
			throw new DataException("[err]部门名称不能空");
		if (dept.getOrgId() <= 0)
			throw new DataException("[err]请指定org类型!");

		deptDao.updateDepartment(dept);
	}
}
