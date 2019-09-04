package com.kjplus.test;


import java.util.List;

import com.kjplus.eto.InfoCatalogEto;
import com.kjplus.eto.InfoReadlogEto;
import com.kjplus.model.InfoReadlogEbo;

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
import com.kjplus.dto.InfoCatalogDto;
import com.kjplus.dto.InfoDto;
import com.kjplus.dto.InfoReferenceDto;
import com.kjplus.eto.InfoEto;
import com.kjplus.eto.InfoReferenceEto;
import com.kjplus.model.InfoCatalogEbo;
import com.kjplus.model.InfoEbo;
import com.kjplus.model.InfoReferenceEbo;
import com.kjplus.model.inner.InfoInnerEbo;
import com.kjplus.service.IInfoService;
import com.kjplus.service.IZanService;
import com.ybk.exception.DataException;

/**
 * 
 * @author wangyao
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestInfoService {

	@Autowired
	private IInfoService infoService;
	@Autowired
	private IZanService zanService;

	// 添加info分类
	//@Test
	public void testAddInfoCataLog() {
		try {
			int orgid = 2;
			InfoCatalogEto catg = new InfoCatalogEto();
			catg.setName("卫生中心4");
			catg.setPid(3);
			catg.setOrgId(orgid);
			InfoCatalogEbo c = infoService.addCatalog(catg);
			System.out.println("[cata] " + c);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 列表分类
	//@Test
	public void testListInfoCataLog() {
		try {
			int orgid = 2;
			String flag = null;
			String name = "卫生";
			List<InfoCatalogDto> catgs = infoService.listCatalog(orgid,-1,null, flag);
			int total = infoService.getTotalCatalogByOrg(orgid, -1, name, flag);
			System.out.println("[total]"+total);
			for (InfoCatalogDto c1 : catgs) {
				System.out.println("[c] " + c1.getName() + ",pid=" + c1.getPid() + ",paname=" + c1.getPname()+",type=" + c1.getInfoTypeName());
				for (InfoCatalogDto c2 : c1.getSubs()) {
					System.out.println("\t[c] " + c2.getName() + ",pid" + c2.getPid() + ",type=" + c2.getInfoTypeName());
				}
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试添加info
	 @Test
	public void testAddInfo() {
		try {
			InfoEto info = new InfoEto();
			info.setContent("活动活动活动活动活动活动活动活动活动活动活动活动");
			info.setInfoTitle("活动2222");
			info.setFlag("Y");
			// info.setPubId(1);
			// info.setDeptId(3);
			info.setOrgId(2);
			info.setInfoCatgId(3);
			info.setPubTime(DateUtil.newTime());
			info.setInfoType("A");
			infoService.addInfo(info);
			System.out.println("[info] " + info);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 添加引用,此方法是为了单独测试引用
	// @Test
	public void testAddInfoRefer() {
		try {
			infoService.addReference(6, 1, 1);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试info列表
	@Test
	public void testListInfo() {
		try {
			String title = "";
			String queryDay = "2017-01-17";
			String infoType = "";
			List<InfoDto> infos = infoService.listInfo(null, 0, null, "Y", 1, queryDay, false,null, 0, 10);
			for (InfoDto info : infos) {
				System.out.println("[info] " + info.getId());
				
				System.out.println("\t[content] " + info.getContent());
			}
			int total = infoService.getTotalInfo(title, 0, null, "Y", 1, queryDay,infoType);
			System.out.println("[total] " + total);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试infoReference列表
	//@Test
	public void testListInfoReference() {
		try {
			List<InfoReferenceDto> infoReferences = infoService.listInfoReference(3, 2, -1, "Y", "Y", 0, 10);
			for (InfoReferenceDto infoReference : infoReferences) {
				System.out.println("[infoReference] " + infoReference);
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试添加点赞
	// @Test
	public void testAddInfoZan() {
		try {
			// status为yes：已经点赞过。status为no：未点赞过
			int uid = 2;
			String refCode = "15960161756724778086421440027070";
			String status = zanService.unZan(0, null, uid);
			String type = Constant.DOT_ZAN;
			infoService.addInfoZan(refCode, uid, status, type);
			if (Constant.FLAG_NO.equals(status))
				System.out.println("添加点赞成功");
			else
				System.out.println("取消点赞成功");
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试添加关注
	// @Test
	public void testAddInfoVisit() {
		try {
			String refCode = "15960161756724778086421440027070";
			infoService.addInfoVisit(refCode);
			System.out.println("添加关注成功");
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试添加内容索引
	// @Test
	public void testAddInfoReference() {
		try {
			InfoReferenceEto ref = new InfoReferenceEto();
			ref.setInfoId(6);
			ref.setRefCode("15960161756724778086421440027070");
			ref.setDeptId(4);
			ref.setOrgId(1001);
			InfoReferenceEbo infoRef = infoService.addReference(ref);
			System.out.println("[infoRef] " + infoRef);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试通过id获得内容
	// @Test
	public void testGetContent() {
		try {
			String content = infoService.getInfoContent(14);
			System.out.println("[content] " + content);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 添加阅读记录
	//@Test
	public void testAddInfoReadlog() {
		try {
			InfoReadlogEto readlog = new InfoReadlogEto();
			readlog.setUid(1);
			readlog.setMainCode("57798185808678814859585967521281");
			readlog.setMainType(Constant.READ_TYPE_ARTICLES);
			InfoReadlogEbo addReadlog = infoService.addReadlog(readlog);
			System.out.println("[addReadlog] " + addReadlog);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 修改阅读时间
	//@Test
	public void testChangeReadlog() {
		try {
			int id = 1;
			// 获取结束时间
			Long endTime = System.currentTimeMillis();
			InfoReadlogEbo read = infoService.getReadlogById(id);
			int readTime = (int) (endTime - read.getReadStartTime());
			String flag = Constant.FLAG_YES;
			infoService.changeReadTimeOrFlag(id, flag, readTime);
			System.out.println("[addReadlog] " + "修改成功");
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 修改信息列表
	//@Test
	public void testUpdateInfo() {
		try {
			int id = 16;
			String infoTitle = "你好啊";
			int orgId = 2;
			int infoCataId = 3;
			InfoEbo infoEbo = new InfoEbo();
			infoEbo.setId(id);
			infoEbo.setInfoTitle(infoTitle);
			infoEbo.setOrgId(orgId);
			infoEbo.setInfoCatgId(infoCataId);
			infoService.updateInfo(infoEbo);
			System.out.println("[updateInfo] " + "修改成功");
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 修改infoCatalog列表
	@Test
	public void testUpdateInfoCatalog() {
		try {
			String code = "38tf6c27";
			String name = "你好啊";
			int orgId = 2;
			int infoTypeId = 0;
			int pid = 0;
			String flag = "Y";
			InfoCatalogEbo in = new InfoCatalogEbo();
			in.setPid(pid);
			in.setCode(code);
			in.setName(name);
			in.setFlag(flag);
			in.setOrgId(orgId);
			in.setInfoTypeId(infoTypeId);
			infoService.updateInfoCatalog(in);
			System.out.println("[updateInfoCatalog] " + "修改成功");
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetInfoRef() {
		try {
			int refId = 0;
			String refCode = "71650652569280920880738889383114";
			InfoInnerEbo  infoInner = infoService.getInfoByRef(refId, refCode);
			System.out.println("++"+infoInner);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
}
