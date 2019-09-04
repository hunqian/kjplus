package com.kjplus.dao;

import com.kjplus.dto.*;
import com.kjplus.model.*;
import com.ybk.exception.DataException;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("srvProjectDao")
public interface ISrvProjectDao {

    /**
     * 获取服务对象
     * @param id   服务项目表 id
     * @param code 服务项目表 code
     * @param flag 是否有效
     * @return 返回SrvProjectEbo对象
     * @throws DataException
     */
    public SrvProjectEbo getSrvPrjByCodeOrId(@Param("id") int id, @Param("code") String code, @Param("flag") String flag) throws DataException;

    /**
     * 添加一个项目
     *
     * @param srvProject 传入SrvProjectEbo对象
     * @throws DataException
     */
    public void addSrvProject(SrvProjectEbo srvProject) throws DataException;

    /**
     * 查询服务项目列表
     * @param code     服务项目编号
     * @param typeCode 服务参照类型
     * @param flag     是否有效
     * @return 返回List<SrvProjectDto>对象
     * @throws DataException
     */
    public List<SrvProjectDto> listSrvProject(@Param("code") String code, @Param("typeCode") String typeCode, @Param("flag") String flag) throws DataException;

    /**
     * 修改服务项目
     * @param id   项目表id
     * @param code 项目表编号
     * @param flag 项目是否有效
     * @throws DataException
     */
    public void updateSrvProjectByFlag(@Param("id") int id, @Param("code") String code, @Param("flag") String flag) throws DataException;


    /**
     *
     * @param id
     *      t_srv_project_cfg表id
     * @return
     *      返回SrvProjectCfgEbo对象
     * @throws DataException
     */
    public SrvProjectCfgEbo getSrvProjectCfgById(@Param("id") int id) throws DataException;

    /**
     * 获取组织 对应项标签 的配置
     *
     * @param prjId 服务项目表id
     * @param tagId 标签表id
     * @param orgId 组织表id
     * @return 返回SrvProjectConfigEbo对象
     * @throws DataException
     */
    public SrvProjectCfgEbo getSrvProjectCfg(@Param("prjId") int prjId, @Param("tagId") int tagId, @Param("orgId") int orgId) throws DataException;


    /**
     * 添加服务项目配置
     *
     * @param srvProjectCfg 传入SrvProjectConfigEbo对象
     * @throws DataException
     */
    public void addSrvProjectCfg(SrvProjectCfgEbo srvProjectCfg) throws DataException;


    /**
     * 服务项目配置信息雷彪
     * @param prjId  服务项目的id
     * @param orgId  组织id
     * @param tagId  标签id
     * @param isNorm 是否规范管理
     * @return
     * @throws DataException
     */
    public List<SrvProjectCfgInfoDto> listSrvProjectCfgInfo(@Param("prjId") int prjId, @Param("orgId") int orgId, @Param("tagId") int tagId, @Param("isNorm") String isNorm) throws DataException;

    /**
     * 修改服务项目配置
     *  @param id
     *      服务项目配置表id
     * @param prjId
     *      服务项目表id
     * @param tagId
     *      标签id
     * @param orgId
     *      组织id
     * @param isNorm
     *      是否规范管理
     * @throws DataException
     */
    public void updateSrvProjectCfgByisNorm(@Param("id") int id,@Param("prjId") int prjId, @Param("tagId") int tagId, @Param("orgId") int orgId, @Param("isNorm") String isNorm) throws DataException;

}
