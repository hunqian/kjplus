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

import com.kjplus.eto.DocInfoEto;
import com.kjplus.model.DocumentInfoEbo;
import com.kjplus.model.inner.DocumentInfoInnerEbo;
import com.kjplus.service.IDocInfoService;
import com.kjplus.service.IUserService;

/**
 * 
 * @author rumble
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestDocInfoService {

	@Autowired
	private IDocInfoService docInfoService;
	@Autowired
	private IUserService userService;

	// 增加docInfo
	//@Test
	public void testAddDocInfo() {

		try {
			DocInfoEto docEto = new DocInfoEto();
			docEto.setPrsnName("张三11111");
			docEto.setBirthday("2000-01-01");
			docEto.setCreateDay("2017-11-01");
			docEto.setBloodVl("B");
			docEto.setContactNum("联系1234567");
			docEto.setFamilyAddr("famliy123243243");
			docEto.setHouseAddr("家里aaaaaaaa");
			docEto.setIdNumber("ID123456771");
			docEto.setMobileNum("M12345678");
			docEto.setNationVl("N");
			docEto.setCreatorId(16);
			docEto.setOrgId(1);
			docEto.setPersonNum("123456789090");
			docEto.setPrsnSex("S");
			docEto.setWorkUnit("工作单位aaa");
			docInfoService.addDocInfo(docEto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 修改docInfo
		@Test
		public void testEditDocInfo() {

			try {
				DocumentInfoEbo docEbo = new DocumentInfoEbo();
				docEbo.setCode("948437430822");
				docEbo.setPrsnId(0);
				docEbo.setPrsnName("张三444");
				docInfoService.updateDocInfo(docEbo);
				System.out.println("修改成功");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	//@Test
	public void testListDocInfo() {

		try {
			int orgId = 1;
			String prsnCode = "";
			String idNumber = "";
			List<DocumentInfoEbo> dlList= docInfoService.listDocInfo(orgId, prsnCode, idNumber);
			for (DocumentInfoEbo documentInfoEbo : dlList) {
				System.out.println(documentInfoEbo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 列表docInfo
	@Test
	public void testListDocInnerInfo() {
		try {
			int orgId = 0;
			String status = null;
			String flag = null;
			String name = "";
			String idCard = "";
			boolean isEffect = false;
			List<Integer> tagIds = new ArrayList<Integer>();
			tagIds.add(360);
			List<DocumentInfoInnerEbo> docInfos = docInfoService.listDocInfoInner(orgId, name,status,flag,idCard,tagIds,isEffect,0,0,0,-1);
			for(DocumentInfoInnerEbo info: docInfos){
				System.out.println("[info]" + info.getPrsnName() + ",uname=" + info.getUnickName());
				for(Integer i : tagIds){
					System.out.println("tag:"+i);
				}
				System.out.println("INFO："+info);
			}
			int total = docInfoService.getTotalDocInfoInner(orgId, name, status, flag, idCard, tagIds,0,0);
			System.out.println(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	// docInfo权限
	//@Test
	public void testDocUser() {
		try {
			int prsnId = 16;
			String userType = "U";
			int uid = 1;
			userService.addUserPersonRel(uid, userType, prsnId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 通过ID或Code获取docInfo
		//@Test
		public void testGetDocInfoByIdOrCode() {
			try {
				int id = 21;
				String code = "";
				DocumentInfoEbo doc = docInfoService.getDocinfoByIdOrCode(id, code);
				System.out.println(doc);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
