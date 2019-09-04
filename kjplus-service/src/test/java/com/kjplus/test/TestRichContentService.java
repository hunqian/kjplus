package com.kjplus.test;


import java.util.List;

import com.kjplus.eto.RichContentEto;

import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kjplus.constant.Constant;
import com.kjplus.dto.RichContentDto;
import com.kjplus.model.RichContentEbo;
import com.kjplus.service.IInfoService;
import com.kjplus.service.IRichContentService;
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
public class TestRichContentService {

	@Autowired
	private IInfoService infoService;
	@Autowired
	private IRichContentService contentService;

	// 添加通用内容
	//@Test
	public void testAddNorContent() {
		try {
			RichContentEto cntentEto = new RichContentEto();
			cntentEto.setMainId(3);
			cntentEto.setMainTypeCode(Constant.RICH_CONTENT_DEPT);
			cntentEto.setTitle("热血沸腾");
			cntentEto.setShowPic1("");
			cntentEto.setShowPic2("");
			cntentEto.setShowPic3("");
			cntentEto.setShowPic4("");
			cntentEto.setShowPic5("");
			cntentEto.setContent("热血高校");
			contentService.addContent(cntentEto);
			System.out.println("[ID] " + cntentEto);
			System.out.println("添加成功");
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 修改通用内容
	//@Test
	public void testUNorContent() {
		try {
			RichContentEbo norContentEbo = new RichContentEbo();
			norContentEbo.setCode("123456");
			norContentEbo.setTitle("热血沸腾1");
			norContentEbo.setShowPic1("a");
			norContentEbo.setShowPic2("b");
			norContentEbo.setShowPic3("c");
			norContentEbo.setShowPic4("d");
			norContentEbo.setShowPic5("e");
			norContentEbo.setContent("热血高校1");
			contentService.updateContent(norContentEbo);
			System.out.println("[ID] " + norContentEbo);
			System.out.println("修改成功");
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 获取通用内容数据
	//@Test
	public void testListContent() {
		try {
			String mainTypeCode = null;
			String title = null;
			int orgId = 1;
			List<RichContentDto> contents = contentService.listContent(mainTypeCode,orgId, title, 0, 10);
			int total = contentService.getTotalContent(mainTypeCode,orgId, title);
			System.out.println("[total]" + total);
			for (RichContentDto n1 : contents) {
				System.out.println("[title] " + n1.getTitle() + ",mainId=" + n1.getMainId() + ",content="
						+ n1.getContent() + ",mainType=" + n1.getMainTypeCode());
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 通过ID或者Code查询通用内容数据
	// @Test
	public void testGetContentByIdOrCode() {
		try {
			int id = 2;
			String code = "GHREBM";
			RichContentEbo n1 = contentService.getContentByIdOrCode(id, code);
			System.out.println("[title] " + n1.getTitle() + ",mainId=" + n1.getMainId() + ",content=" + n1.getContent()
					+ ",mainType=" + n1.getMainTypeCode());
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 通过mainId或者mainTypeCode查询通用内容数据
	// @Test
	public void testGetContentByMainIdAndMainType() {
		try {
			int mainId = 2;
			String MainTypeCode = "ORG";
			RichContentEbo n1 = contentService.getContentByMainIdType(mainId, MainTypeCode);
			if (n1 != null) {
				System.out.println("[title] " + n1.getTitle() + ",mainId=" + n1.getMainId() + ",content="
						+ n1.getContent() + ",mainType=" + n1.getMainTypeCode());
			}

		} catch (DataException e) {
			e.printStackTrace();
		}
	}

}
