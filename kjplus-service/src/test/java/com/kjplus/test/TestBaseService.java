package com.kjplus.test;


import java.util.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kjplus.constant.Constant;
import com.kjplus.dao.ISysBaseDao;
import com.kjplus.dto.*;
import com.kjplus.eto.SysRefTypeEto;
import com.kjplus.model.*;
import com.kjplus.service.*;
import com.ybk.exception.DataException;

/**
 * 
 * @author niuzhiwei
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestBaseService {

	
	@Autowired
	private ISysBaseService baseService;

	@Autowired
	private ISysBaseDao baseDao;
	
	// 测试添加type对象
	//@Test
	public void testAddPerson() {
		try {
			SysRefTypeEto type = new SysRefTypeEto();
			type.setRefMemo("用于定义多种类型的报表");
			type.setRefName("测试添加value数");
			type.setRefType("TABLE_TYPE");
			type.setFlag("Y");
			SysRefTypeEbo addRefType = baseService.addRefType(type);
			System.out.println("添加成功" + addRefType);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	// 测试添加value对象
	//@Test
	public void testAddRefVl() {
		try {
			int refId = 16;
			String code = "TEST_ADD";
			String refVl = "TEST_ADD";
			String name = "测试添加value";
			String extVlType = "";
			SysRefValueEbo sysRefValueEbo = new SysRefValueEbo();
			sysRefValueEbo.setRefId(refId);
			sysRefValueEbo.setCode(code);
			sysRefValueEbo.setRefVl(refVl);
			sysRefValueEbo.setName(name);
			sysRefValueEbo.setExtVlType(extVlType);
			sysRefValueEbo.setFlag("Y");
			baseDao.addRefVl(sysRefValueEbo);
			System.out.println("添加成功" + sysRefValueEbo);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试查询type对象byCode
	//@Test
	public void testFindtypeByCode() {
		try {
			String code = "CTOO1";
			SysRefTypeEbo refTypeByCode = baseService.getRefTypeByCode(code);
			System.out.println("结果ID：" + refTypeByCode.getId() + "<>详细：" + refTypeByCode.getRefMemo());
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	//列表refType 根据code和name模糊查询
 	@Test
	public void testListRefType() {
		try {
			List<SysRefTypeEbo> listRefType = null;
			String code = "";
			String name = "型";
			String flag = Constant.FLAG_YES;
			int page = 1;
			int paging = 10;
			listRefType = baseService.listRefType(code, name, flag, page, paging);
			for(SysRefTypeEbo typeEbo:listRefType){
				System.out.println("type:" + typeEbo.getRefName());
				List<SysRefValueEbo> vls = baseService.listRefVlByRefCode(typeEbo.getRefCode(), flag);
				for(SysRefValueEbo vl:vls){
					System.out.println("\tvl:" + vl.getRefVl() + "," + vl.getName());
				}
			}
			int total = baseService.getTotalRefType(code, name, flag);
			System.out.println("total:" + total);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	//列表refType 根据code和name模糊查询
 	@Test
	public void testListRefVlByRefPreCode() {
		try {
			String typeCode = "" ;
			String prefix = Constant.RV_APPOINT_IMMUNITY;
			String flag = Constant.FLAG_YES;
			List<SysRefValueEbo> refVls = baseService.listRefVlByRefPreCode(typeCode, prefix, flag);
			for (SysRefValueEbo refVl : refVls) {
				System.out.println(refVl.getName());
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}	
 	
	// 测试查询type列表对应的Types列表
	@Test
	public void testListTypes() {
		try {
			List<Integer> typeIds = new ArrayList<Integer>();
			typeIds.add(18);
			typeIds.add(17);
			List<SysRefTypeDto> types = baseService.listRefTypesWithVl(typeIds);
			for(SysRefTypeDto t: types){
				System.out.println("[t]" + t.getId() + "," + t.getRefName());
				for(SysRefVlDto vl : t.getRefVls()){
					System.out.println("\t[v]" + vl.getRefVlCode() + "," + vl.getRefVlName());
				}
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}	
}
