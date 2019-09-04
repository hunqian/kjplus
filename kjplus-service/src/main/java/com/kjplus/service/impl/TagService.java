package com.kjplus.service.impl;


import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjplus.constant.Constant;
import com.kjplus.dao.ITagDao;
import com.kjplus.dto.TagDto;
import com.kjplus.eto.TagEto;
import com.kjplus.model.*;
import com.kjplus.service.*;
import com.ybk.exception.DataException;

@Service("tagService")
public class TagService implements ITagService {

	private static Logger logger = Logger.getLogger(ServiceAssignService.class);

	@Autowired
	private ITagDao tagDao;
	@Autowired
	private ISysBaseService baseService;
	@Autowired
	private IDocInfoService docInfoService;
	
	public TagEbo getTagById(int tagId) throws DataException {
		return tagDao.getTagById(tagId);
	}

	public List<TagEbo> listTag(int mainId, String mainType) throws DataException {
		return tagDao.listTag(mainId, mainType);
	}

	public void addTag(TagEto tag) throws DataException {
		// 查询参照
		SysRefValueEbo refVl = baseService.getRefVlById(tag.getRefValId());
		if (refVl == null)
			throw new DataException("[err]系统无此参照类型,传入的参照编号为:" + tag.getRefValId());

		TagEbo tag2 = new TagEbo();
		BeanUtils.copyProperties(tag, tag2);
		try {
			tagDao.addTag(tag2);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	//增加一组refVals
	//purgePrev是否清除已有的
	public void addTags(int mainId, String mainType, List<Integer> refValIds) throws DataException {
		tagDao.delTags(mainId, mainType);
		for(Integer refValId : refValIds){
			if(refValId == null || refValId.intValue() <=0 )
				continue;
			TagEbo t = tagDao.getTag(mainId, mainType, refValId);
			if(t != null)
				continue;
			TagEbo tag2 = new TagEbo();
			tag2.setMainId(mainId);
			tag2.setMainType(mainType);
			tag2.setRefValId(refValId);
			tagDao.addTag(tag2);
		}
	}
	
	/**
	 * 查询标签列表
	 * 
	 * @param mainIds
	 *            主业务id序列
	 * @return List<TagDto>
	 * @throws DataException
	 */
	public List<TagEbo> listTagArray(List<Integer> mainIds, String mainType) throws DataException {
		List<TagEbo> tags = new ArrayList<TagEbo>();
		if (mainIds == null || mainIds.size() == 0)
			return tags;
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < mainIds.size(); i++) {
			if (i != 0)
				buf.append(",");
			buf.append(mainIds.get(i));
		}
		tags = tagDao.listTagArray(buf.toString(), mainType);
		return tags;
	}

	//罗列返回所有的参照状态，如果是对照checked状态为Y，否则为N
	public List<TagDto> listTagChecked(Integer mainId, String mainType, String refTypeCode) throws DataException {
		List<Integer> mainIds = new ArrayList<Integer>();
		mainIds.add(mainId);
		List<TagDto> tags = new ArrayList<TagDto>();
		Hashtable<Integer, TagDto> tagHash = new Hashtable<Integer, TagDto>(); 
		//已经确定的
		List<TagEbo> ctags = listTagArray(mainIds, mainType);
		for(TagEbo ct: ctags){
			TagDto t = new TagDto();
			t.setId(ct.getId());
			t.setRefValId(ct.getRefValId());
			t.setRefValName(ct.getRefValName());
			tags.add(t);
			tagHash.put(ct.getRefValId(), t);
		}
		List<SysRefValueEbo> refs = baseService.listRefVlByRefCode(refTypeCode, Constant.FLAG_YES);
		for(SysRefValueEbo ref: refs){
			if(tagHash.containsKey(ref.getId()))
				continue;
			
			TagDto t = new TagDto();
			t.setRefValId(ref.getId());
			t.setRefValName(ref.getName());
			t.setChecked(Constant.FLAG_NO);
			tags.add(t);
			tagHash.put(ref.getId(), t);
		}
		return tags;
	}
	
	//统计打标签人数
	public int getTotalTagPrsns(String mainType) throws DataException{
		List<TagEbo> tags = tagDao.getTotalTagPrsns(mainType);
		List<TagEbo> tagRemoves = tagDao.getTotalTagPrsns(mainType);
		//档案用户标签查询
		if("D".equals(mainType)){
			DocumentInfoEbo doc = null;
			for (TagEbo tagEbo : tags) {
				doc = docInfoService.getDocinfoByIdOrCode(tagEbo.getMainId(), null);
				if(doc == null)//不存在就复制到查重数据中
					tagRemoves.add(tagEbo);
			}
			tags.removeAll(tagRemoves);
		}
		//TODO 居民用户查询
		return tags.size();
	}
	
	
}
