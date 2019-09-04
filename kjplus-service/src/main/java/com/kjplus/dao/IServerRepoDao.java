package com.kjplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kjplus.model.ServerRepoEbo;
import com.ybk.exception.DataException;

public interface IServerRepoDao {
	
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
	public ServerRepoEbo getSrvRepoByIdOrCode(@Param("id") int id, @Param("code") String code) throws DataException;

	public void addSrvRepoEbo(ServerRepoEbo srvRepoEbo) throws DataException;
	
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
	public List<ServerRepoEbo> listSrvRepo(@Param("name") String name) throws DataException;

}
