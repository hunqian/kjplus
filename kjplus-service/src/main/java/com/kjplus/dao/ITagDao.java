package com.kjplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.model.TagEbo;
import com.ybk.exception.DataException;

@Repository("tagDao")
public interface ITagDao {

	/**
	 * 通过id查询标签
	 * 
	 * @param tagId
	 *            标签id
	 * @return 返回TagEbo对象
	 * @throws DataException
	 */
	public TagEbo getTagById(@Param("tagId") int tagId) throws DataException;
	
	public TagEbo getTag(@Param("mainId") int mainId, @Param("mainType") String mainType
			, @Param("refValId") int refValId) throws DataException;

	/**
	 * 查询标签列表
	 * 
	 * @param mainId
	 *            主业务id
	 * @return List<TagDto>
	 * @throws DataException
	 */
	public List<TagEbo> listTag(@Param("mainId") int mainId, @Param("mainType") String mainType) throws DataException;

	/**
	 * 查询标签列表
	 * 
	 * @param mainIds
	 *            主业务id序列
	 * @return List<TagDto>
	 * @throws DataException
	 */
	public List<TagEbo> listTagArray(@Param("mainIds") String mainIds, @Param("mainType") String mainType)
			throws DataException;

	/**
	 * 添加标签
	 * 
	 * @param tag
	 *            传入TagEto对象
	 * @throws DataException
	 */
	public void addTag(TagEbo tag) throws DataException;
	
	//删除一组
	public void delTags(@Param("mainId") int mainId, @Param("mainType") String mainType) throws DataException;
	
	//统计打标签人数
	public List<TagEbo> getTotalTagPrsns(@Param("mainType") String mainType) throws DataException;
		
}
