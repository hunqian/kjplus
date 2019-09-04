package com.kjplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kjplus.model.FileRepoEbo;
import com.kjplus.model.inner.FileRepoInnerEbo;
import com.ybk.exception.DataException;

public interface IFileRepoDao {

	/**
	 * 
	 * @param id
	 *            <p>
	 *            t_file_repo表id
	 * @param code
	 *            <p>
	 *            t_file_repo表编号
	 * @return <p>
	 *         返回FileRepoEbo对象
	 * @throws DataException
	 */
	public FileRepoEbo getFileRepoByIdOrCode(@Param("id") int id, @Param("code") String code) throws DataException;

	public void addFileRepoEbo(FileRepoEbo fileRepoEbo) throws DataException;

	/**
	 * 
	 * @param srvId
	 *            <p>
	 *            t_file_repo表srv_id
	 * @param mainId
	 *            <p>
	 *            t_file_repo表main_id
	 * @param mainType
	 *            <p>
	 *            t_file_repo表main_type
	 * @param fileType
	 *            <p>
	 *            t_file_repo表file_type
	 * @return <p>
	 *         返回FileRepoEbo对象
	 * @throws DataException
	 */
	public List<FileRepoInnerEbo> listFileRepoInner(@Param("srvId") int srvId,@Param("mainId") int mainId, @Param("mainType") String mainType
			, @Param("fileType") String fileType,@Param("page") int page,@Param("paging") int paging) throws DataException;

}
