package com.kjplus.test;

import java.util.Hashtable;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ybk.basic.util.DateUtil;

import com.kjplus.constant.Constant;
import com.kjplus.dao.IStaffDao;
import com.kjplus.dto.*;
import com.kjplus.eto.StaffEto;
import com.kjplus.model.StaffDeptEbo;
import com.kjplus.model.StaffEbo;
import com.kjplus.service.IStaffService;
import com.ybk.exception.DataException;

/**
 * 
 * @author wangyao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestStaffService {

	@Autowired
	private IStaffService staffService;
	@Autowired
	private IStaffDao staffDao;

	// 测试列表staff
	@Test
	public void testAddStaff() {
		try {
			StaffEto staff1 = new StaffEto();
			staff1.setName("医生00");
			staff1.setSex("M");
			staff1.setBirthday("19940101");
			staff1.setTypeId(344);
			staff1.setDeptId(3);
			staff1.setOrgId(1);
			staff1.setIdCard("222402199401017777");
			staff1.setMobileNum("12312333200");
			staff1.setStatus("Y");
			staff1.setRegDate(DateUtil.newTime());
			staff1.setHeadIconUrl(null);
			staff1.setMemo("张三擅长内科");
			staffService.addStaff(staff1);

			System.out.println("添加成功");
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试列表dept
	@Test
	public void testListDepartmentDto() {
		try {
			List<StaffDto> staffList = staffService.listStaffDto(1, -1,null,null,null, 0, -1);
			for (StaffDto s : staffList) {
				System.out.println("==========================================");
				System.out.println("医生姓名："+s.getStafName());
				System.out.println("医生所在："+s.getDeptName());
				for( DeptSimpleDto gd :s.getDepts())
					System.out.println("==医生所在团体："+ gd.getName());
				for(TagDto t :s.getTags())
					System.out.println("+++医生标签："+ t.getRefValName());
				System.out.println("==========================================");
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试列表deptStaff
	@Test
	public void testListStaffDeptDto() {
		try {
			String deptType = Constant.DEPT_TYPE_GRUOP;
			// 先返回所有的，然后进行筛选
			List<DeptStaffDto> depts = staffService.listDeptStaffDto(6, deptType, false, true);
			System.out.println("查询结果 = " + depts);
			Hashtable<String, DeptStaffDto> stafDeptHash = new Hashtable<String, DeptStaffDto>();
			// Constant.DEPT_TYPE_VIRTUAL
			DeptStaffDto d = null;
			for (int i = 0; i < depts.size(); i++) {
				d = depts.get(i);
				// 保留staf和组织类型的对应，假设只有一个是R对应的
				for (StaffSimpleDto ss : d.getStafs()) {
					stafDeptHash.put(ss.getStafId() + d.getDeptType(), d);
				}
				/*
				 * //获取实体部门 if
				 * (Constant.DEPT_TYPE_VIRTUAL.equals(d.getDeptType()))
				 * depts.remove(i); else i++;
				 */
			}

			/*
			 * for (DeptStaffDto d2 : depts) { for (StaffSimpleDto s :
			 * d2.getStafs()) { if (stafDeptHash.containsKey(s.getStafId() +
			 * Constant.DEPT_TYPE_REAL)) { d = stafDeptHash.get(s.getStafId() +
			 * Constant.DEPT_TYPE_REAL); s.setRealDept(d.getDeptName()); } } }
			 */

			// fm操作
			for (DeptStaffDto d2 : depts) {
				System.out.println("[dept]" + d2.getDeptName());
				for (StaffSimpleDto s : d2.getStafs()) {
					System.out.println("\t[staff]" + s.getStafName() + "," + s.getRealDept());
				}
			}

			System.out.println("查询成功");
		} catch (DataException de) {
			de.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testListStaffDto() {
		try {
			String staffName = "1";
			List<StaffDto> staffs = staffService.listStaffDto(1, 0, staffName,null,null,0, 10);
			System.out.println("++" + staffs.size());
			for (StaffDto staff : staffs)
				System.out.println(staff);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test
	public void testGetTotalStaffDto() {
		try {
			int orgId = 1001;
			int deptId = -1;
			int total = staffService.getTotalStaffDto(orgId, deptId,null,null);
			System.out.println("total:" + total);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test
	public void testUpdateStaff() {
		try {
			StaffEbo staff = new StaffEbo();
			staff.setName("王五555");
			staff.setId(1);
			staffDao.updateStaff(staff);
			System.out.println("修改成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testListStaffDeptMapEbo() {
		try {
			int staffId = 2;
			int deptId = 0;
			List<StaffDeptEbo> listStaffDept = staffService.listStaffDeptMapEbo(staffId, deptId);
			for (StaffDeptEbo staffDeptEbo : listStaffDept) {
				System.out.println(staffDeptEbo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
