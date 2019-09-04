package com.kjplus.service;

import java.util.List;

import com.kjplus.eto.ServerRepoEto;
import com.kjplus.model.*;
import com.ybk.exception.DataException;

public interface IServerRepoService {

	/**
	 * 
	 * @param id
	 *            <p>
	 *            t_server_repo表id
	 * @param code
	 *            <p>
	 *            t_server_repo表编号
	 * @return
	 *         <p>
	 *         返回ServerRepoEbo对象
	 * @throws DataException
	 */
	public ServerRepoEbo getSrvRepoByIdOrCode(int id,String code) throws DataException;

	public ServerRepoEbo addSrvRepoEbo(ServerRepoEto srvRepoEto) throws DataException;
	
	/**
	 * 
	 * @param name  模糊查询
	 *            <p>
	 *            t_server_repo表srv_name
	 * @return
	 *         <p>
	 *         返回ServerRepoEbo对象
	 * @throws DataException
	 */
	public List<ServerRepoEbo> listSrvRepo(String name) throws DataException;

}
