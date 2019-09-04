package com.kjplus.service;

import com.kjplus.dto.*;
import com.kjplus.eto.*;
import com.kjplus.model.*;
import com.ybk.exception.DataException;

import java.util.List;

public interface ISrvProjectService {
    /**
     * 获取有效服务项目对象
     *
     * @param id   服务项目表 id
     * @param code 服务项目表 code
     * @return 返回SrvProjectEbo对象
     * @throws DataException
     */
    public SrvProjectEbo getSrvPrjByCodeOrId(int id, String code) throws DataException;

    /**
     * 添加一个项目
     *
     * @param srvProject 传入SrvProjectEbo对象
     * @throws DataException
     */
    public SrvProjectEbo addSrvProject(SrvProjectEto srvProject) throws DataException;

    /**
     * 查询服务项目列表
     *
     * @param code     服务项目编号
     * @param typeCode 服务参照类型
     * @param flag     是否有效
     * @return 返回List<SrvProjectDto>对象
     * @throws DataException
     */
    public List<SrvProjectDto> listSrvProject(String code, String typeCode, String flag) throws DataException;

    /**
     * 修改服务项目
     *
     * @param id   项目表id
     * @param code 项目表编号
     * @param flag 项目是否有效
     * @throws DataException
     */
    public void updateSrvProjectByFlag(int id, String code, String flag) throws DataException;


    /**
     *
     * @param id
     *      t_srv_project_cfg表id
     * @return
     *      返回SrvProjectCfgEbo对象
     * @throws DataException
     */
    public SrvProjectCfgEbo getSrvProjectCfgById( int id) throws DataException;

    /**
     * 获取组织 对应项标签 的配置
     *
     * @param prjId 服务项目表id
     * @param tagId 标签表id
     * @param orgId 组织表id
     * @return 返回SrvProjectConfigEbo对象
     * @throws DataException
     */
    public SrvProjectCfgEbo getSrvProjectCfg(int prjId, int tagId, int orgId) throws DataException;


    /**
     * 添加服务项目配置
     *
     * @param srvProjectCfg 传入SrvProjectConfigEbo对象
     * @throws DataException
     */
    public SrvProjectCfgEbo addSrvProjectCfg(SrvProjectCfgEto srvProjectCfg) throws DataException;


    /**
     * 服务项目配置信息雷彪
     *
     * @param prjId  服务项目的id
     * @param orgId  组织id
     * @param tagId  标签id
     * @param isNorm 是否规范管理
     * @return
     * @throws DataException
     */
    public List<SrvProjectCfgInfoDto> listSrvProjectCfgInfo(int prjId, int orgId, int tagId, String isNorm) throws DataException;

    /**
     * 修改服务项目配置
     *
     * @param id     服务项目配置表id
     * @param prjId  服务项目表id
     * @param tagId  标签id
     * @param orgId  组织id
     * @param isNorm 是否规范管理
     * @throws DataException
     */
    public void updateSrvProjectCfg(int id, int prjId, int tagId, int orgId, String isNorm) throws DataException;
}
