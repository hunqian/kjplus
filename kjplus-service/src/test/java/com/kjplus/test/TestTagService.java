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
import com.kjplus.dao.ITagDao;
import com.kjplus.dto.TagDto;
import com.kjplus.eto.TagEto;
import com.kjplus.model.TagEbo;
import com.kjplus.service.ITagService;
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
public class TestTagService {

	@Autowired
	private ITagService tagService;
	@Autowired
	private ITagDao tagDao;

	@Test
	public void testListTag() {
		try {
			Integer mainId = null;
			/*int main = 0;
			if(mainId == null)
				main=0;
			else
				main = mainId.intValue();*/
			String mainType="p";
			List<TagEbo> tags = tagService.listTag(mainId, mainType);
			System.out.println("[tag]" + tags);
			for (TagEbo tag : tags) {
				System.out.println("标签id:" + tag.getId());
				System.out.println("标签类型id:" + tag.getRefValId());
				System.out.println("标签类型值:" + tag.getRefValName());
			}
			System.out.println("查询成功！");
			System.out.println(tags.size());
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	// 添加标签
	// @Test
	public void testAddTag() {
		try {
			TagEto tag = new TagEto();
			tag.setMainId(1);
			tag.setMainType(Constant.TAG_TYPE_PERSON);
			tag.setRefValId(347);
			tagService.addTag(tag);

			tag.setRefValId(348);
			tagService.addTag(tag);
			System.out.println("标签目录添加成功");
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 添加标签
	@Test
	public void testAddTags() {
		try {
			List<Integer> refValIds = new ArrayList<Integer>();
			refValIds.add(347);
			refValIds.add(348);
			tagService.addTags(16, Constant.TAG_TYPE_PERSON, refValIds);
			System.out.println("添加标签组成功");
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 查询标签列表
	//@Test
	public void testListTagDto() {
		try {
			List<Integer> mainIds = new ArrayList<Integer>();
			mainIds.add(1);
			List<TagEbo> tags = tagService.listTagArray(mainIds, Constant.TAG_TYPE_PERSON);
			System.out.println("[tag]" + tags);
			for (TagEbo tag : tags) {
				System.out.println("标签id:" + tag.getId() );
				System.out.println("标签类型id:" + tag.getRefValId() + "," + tag.getRefValName());
			}
			System.out.println("查询成功！");
			System.out.println(tags.size());
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 查询已为居民标记的标签
	//@Test
	public void testListTagArray() {
		try {
			List<Integer> mainIds = new ArrayList<Integer>();
			mainIds.add(4);
			StringBuffer buf = new StringBuffer();
			for (int i = 0; i < mainIds.size(); i++) {
				if (i != 0)
					buf.append(",");
				buf.append(mainIds.get(i));
			}
			List<TagEbo> tags = tagDao.listTagArray(buf.toString(), Constant.TAG_TYPE_PERSON);
			System.out.println("[tag]" + tags);
			for (TagEbo tag : tags) {
				System.out.println("标签id:" + tag.getId());
				System.out.println("标签类型id:" + tag.getRefValId());
				System.out.println("标签类型值:" + tag.getRefValName());
			}
			System.out.println("查询成功！");
			System.out.println(tags.size());
		} catch (DataException e) {
			e.printStackTrace();
		}
	}


	// 查询标签列表,返回所有标签以及标签的选中状态
	//@Test
	public void testListCheckedTag() {
		try {
			List<TagDto> tags = tagService.listTagChecked(4, Constant.TAG_TYPE_PERSON,"PERSON_TYPE");
			System.out.println("[tag]" + tags);
			for (TagDto tag : tags) {
				System.out.println("标签id:" + tag.getId() );
				System.out.println("标签类型id:" + tag.getRefValId() + "," + tag.getRefValName() + "," + tag.getChecked());
			}
			System.out.println("查询成功！");
			System.out.println(tags.size());
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetTotalTagPrsns() {
		try {

			int total = tagService.getTotalTagPrsns("P");
			System.out.println("total" + total);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}	
	
	
}
