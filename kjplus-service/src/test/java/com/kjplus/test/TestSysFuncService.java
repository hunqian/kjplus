package com.kjplus.test;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.kjplus.model.inner.SysRoleInnerEbo;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kjplus.eto.SysFuncEto;
import com.kjplus.dto.FuncDto;
import com.kjplus.model.*;
import com.kjplus.service.ISysFuncService;
import com.ybk.exception.DataException;

/**
 * 
 * @author niuzhiwei service层测试格式
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestSysFuncService {

	@Autowired
	private ISysFuncService funcService;

	// 测试通过id获取funcEbo对象
	//@Test
	public void testGetFuncById() {
		try {
			SysFuncEbo func = funcService.getFuncById(6);
			System.out.println(func);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 罗列菜单
	//@Test
	public void testListFunc() {
		try {
			String type = "A";
			List<FuncDto> fucs = funcService.listFunc(0, type);
			for (FuncDto f : fucs) {
				System.out.println(f.toString());
				for (FuncDto f2 : f.getSubs()) {
					System.out.println("\t" + f2.toString());
				}
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试角色对应的菜单
	//@Test
	public void testListRoleFunc() {
		try {
			List<FuncDto> fucs = funcService.listRoleFunc(2);
			for (FuncDto f : fucs) {
				System.out.println(f.toString());
				for (FuncDto f2 : f.getSubs()) {
					System.out.println("\t" + f2.toString());
				}
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试通过code获取funcEbo对象
	//@Test
	public void testGetFuncByCode() {
		try {
			String code = "001";
			SysFuncEbo fucs = funcService.getFuncByCode(code);
			System.out.println(fucs);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试添加菜单
	// @Test
	public void testAddSysFunc() {

		try {
			String t = "A";
			SysFuncEto f = null;
			SysFuncEbo func = null;
			f = new SysFuncEto(0, t, "001", "基础数据", "/");
			func = funcService.addFunc(f);

			f = new SysFuncEto(func.getId(), t, "002", "参照类型", "reftypes.html");
			funcService.addFunc(f);

			f = new SysFuncEto(func.getId(), t, "003", "功能菜单", "menufunc.html");
			funcService.addFunc(f);

			f = new SysFuncEto(func.getId(), t, "004", "角色", "role.html");
			SysFuncEbo func2 = funcService.addFunc(f);

			f = new SysFuncEto(func2.getId(), t, "0041", "角色1", "role1.html");
			funcService.addFunc(f);

			f = new SysFuncEto(0, t, "005", "代理&用户", "/");
			func = funcService.addFunc(f);

		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试添加user*role*func
	@Test
	public void testListUserFunc() {
		try {
			List<FuncDto> funcs = funcService.listUserFunc(22);
			for (FuncDto f : funcs) {
				System.out.println(f.toString());
				for (FuncDto f1 : f.getSubs()) {
					System.out.println("\t" + f1.toString());
					for (FuncDto f2 : f1.getSubs()) {
						System.out.println("\t\t" + f2.toString());
					}
				}
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 获得用户的默认第一个访问页面
	//@Test
	public void testGetDefaultFunc() {
		try {
			String func = funcService.getDefaultFunc(2);
			System.out.println(func);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试通过code获取角色
	//@Test
	public void testGetRole() {
		try {
			String code = "R_ALL";
			SysRoleEbo role = funcService.getRole(code);
			System.out.println(role);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	//@Test
	public void testListUserRole() {
		try {
			String type = "A";
			//int uid, int roleId
			List<SysRoleInnerEbo>  userRoles = funcService.listUserRole(22,0,type);
			for (SysRoleInnerEbo userRole:userRoles) {
				System.out.println(userRole);
			}
			
			List<Integer> uids = new ArrayList<Integer>();
			uids.add(19);
			uids.add(22);
			uids.add(25);
			
			Map<Integer,List<SysRoleInnerEbo>> res = funcService.listRelatedUserRole(uids,"A");
			Iterator<Integer> iters = res.keySet().iterator();
			Integer k = null;
			while(iters.hasNext()){
				k = iters.next();
				System.out.println("[k]" + k);
				List<SysRoleInnerEbo> roles = res.get(k);
				for(SysRoleInnerEbo r:roles){
					System.out.println("\t[r]" + r.getName());
				}
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}


    //@Test
    public void testAddUserRole() {
        try {
        	List<Integer> roleIds = new ArrayList<Integer>();
        	roleIds.add(1);
        	roleIds.add(2);
        	roleIds.add(3);
            funcService.addUserRoles(1,roleIds,true);
        } catch (DataException e) {
            e.printStackTrace();
        }
    }


}
