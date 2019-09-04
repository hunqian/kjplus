package com.kjplus.test;



import java.util.List;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kjplus.dto.DepartmentDto;
import com.kjplus.eto.DepartmentEto;
import com.kjplus.model.DepartmentEbo;
import com.kjplus.service.IDeptService;
import com.ybk.exception.DataException;

/**
 * 
 * @author wangyao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestDeptService {

	@Autowired
	private IDeptService deptService;

	// 测试添加dept
	//@Test
	public void testAddDepartment() {
		try {
			DepartmentEto dept = new DepartmentEto();
			dept.setName("内科");
			dept.setOrgId(1001);
			dept.setMemo("这是我们的内科");
			DepartmentEbo d = deptService.addDepartment(dept);

			DepartmentEto dept1 = new DepartmentEto();
			dept1.setName("血液内科");
			dept1.setOrgId(1001);
			dept1.setMemo("这是我们的血液内科");
			dept1.setPid(d.getId());
			deptService.addDepartment(dept1);

			DepartmentEto dept2 = new DepartmentEto();
			dept2.setName("保健科");
			dept2.setOrgId(1001);
			dept2.setMemo("这是我们的保健科");
			deptService.addDepartment(dept2);

			System.out.println("添加成功");
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试列表dept
	@Test
	public void testListDepartmentDto() {
		try {
			String deptType = "G";
			List<DepartmentDto> deptList = deptService.listDepartmentDto("",deptType, 1,null,0,10);
			for (DepartmentDto d : deptList) {
				System.out.println("[dept]" + d.getName());
				for (DepartmentDto d2 : d.getSubs()) {
					System.out.println("\t[dept]" + d2.getName());
				}
			}
			System.out.println("查询成功");
			System.out.println(deptList.toString());
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	//测试查询部门总数
	//@Test
	public void testGetTotalDept(){
		
		String deptName = null;
		int orgid = 1001;
		int totalDept = 0;
		try {
			totalDept = deptService.getTotalDepartment(deptName, orgid,null);
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(totalDept);
		
	}
	
	// 测试修改dept
	@Test
	public void testUpdateDepartment() {
		try {
			DepartmentEbo dept = new DepartmentEbo();
			dept.setCode("95236223");
			//dept.setDeptType("V");
			//dept.setPid(6);
			//dept.setFlag("N");
			dept.setName("血液科");
			dept.setOrgId(3);
			//dept.setMemo("这是我们的血液科");
			deptService.updateDept(dept);
			System.out.println("修改成功");
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

}
