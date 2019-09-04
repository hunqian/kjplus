package com.kjplus.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kjplus.constant.Constant;
import com.kjplus.dao.IOrgDao;
import com.kjplus.dto.OrgDto;
import com.kjplus.eto.OrgEto;
import com.kjplus.model.OrgEbo;
import com.kjplus.service.IOrgService;
import com.ybk.exception.DataException;

/**
 * 
 * @author niuzhiwei
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestOrgService {

	@Autowired
	private IOrgDao orgDao;

	@Autowired
	private IOrgService orgService;

	// 测试添加org
	//@Test
	public void testAddOrg() {

		try {
			OrgEbo orgEbo = null;
			OrgEto org0 = new OrgEto();
			org0.setName("xx卫生局");
			org0.setAlias("xx卫生局");
			org0.setOrgTypeId(365);
			org0.setPrvnId(9);
			org0.setCityId(130);
			orgEbo = orgService.addOrg(org0);
			
			org0 = new OrgEto();
			org0.setName("xx卫生中心");
			org0.setAlias("xx卫中心");
			org0.setOrgTypeId(366);
			org0.setPrvnId(9);
			org0.setCityId(130);
			org0.setPid(orgEbo.getId());
			orgService.addOrg(org0);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	// 测试列表org
	//@Test
	public void testListOrg() {

		try {
			int page = 0;
			int paging = 10;
			int cityId = 0;
			int prvnId = 0;
			String flag = Constant.FLAG_YES;
			String orgName = "";
			List<OrgDto> orgs = orgService.listOrg(orgName, flag, cityId, prvnId, true, page, paging);
			for(OrgDto org: orgs){
				System.out.println("org:" + org.getName());
				for(OrgDto org2: org.getSubs()){
					System.out.println("\torg2:" + org2.getName() + ",pname=" + org2.getPname());
				}
			}
			int total = orgService.getTotalOrg(orgName, flag, cityId, prvnId,true);
			System.out.println("total:" + total);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试添加org
	@Test
	public void testUpdateOrg() {

		try {
			OrgEto org0 = new OrgEto();
			org0.setName("等等卫生局");
			org0.setAlias("等等卫生局");
			org0.setOrgTypeId(365);
			org0.setPrvnId(4);
			org0.setCityId(75);
			orgService.updateOrg(7, org0);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	// 测试列表org
	@Test
	public void testListOrgByIds() {

		try {
			List<Integer> orgIds = new ArrayList<Integer>();
			orgIds.add(2);
			orgIds.add(3);
			List<OrgDto> orgs = orgService.listOrgByIds(orgIds);
			for(OrgDto org: orgs){
				System.out.println("org:" + org.getName());
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
}
