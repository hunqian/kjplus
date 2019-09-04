package com.kjplus.service;

import java.util.List;

import com.kjplus.eto.FileRepoEto;
import com.kjplus.model.*;
import com.kjplus.model.inner.FileRepoInnerEbo;
import com.ybk.exception.DataException;

public interface IFileRepoService {

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
	public FileRepoEbo getFileRepoByIdOrCode(int id, String code) throws DataException;

	public FileRepoEbo addFileRepoEbo(FileRepoEto fileRepoEto) throws DataException;

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
	public List<FileRepoInnerEbo> listFileRepoInner(int srvId, int mainId, String mainType, String fileType, int page, int paging)
			throws DataException;

}
