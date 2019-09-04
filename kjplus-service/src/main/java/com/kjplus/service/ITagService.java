package com.kjplus.service;

import java.util.List;

import com.kjplus.dto.TagDto;
import com.kjplus.eto.TagEto;
import com.kjplus.model.TagEbo;
import com.ybk.exception.DataException;

public interface ITagService {

	// 通过id获取标签
	public TagEbo getTagById(int tagId) throws DataException;

	// 通过mainId查询标签列表
	public List<TagEbo> listTag(int mainId, String mainType) throws DataException;

	// 添加标签
	public void addTag(TagEto tag) throws DataException;
	
	//增加一组refVals
	//purgePrev是否清除已有的
	public void addTags(int mainId, String mainType, List<Integer> refValIds) throws DataException;

	/**
	 * 查询标签列表
	 * 
	 * @param mainIds
	 *            主业务id序列
	 * @return List<TagDto>
	 * @throws DataException
	 */
	public List<TagEbo> listTagArray(List<Integer> mainIds, String mainType) throws DataException;
	
	//罗列返回所有的参照状态，如果是对照checked状态为Y，否则为N
	public List<TagDto> listTagChecked(Integer mainId, String mainType, String refTypeCode) throws DataException;
	
	//统计打标签人数
	public int getTotalTagPrsns(String mainType) throws DataException;

}
