package com.kjplus.service.impl;

import com.kjplus.constant.Constant;
import com.kjplus.dao.ISrvProjectDao;
import com.kjplus.dto.SrvProjectCfgInfoDto;
import com.kjplus.dto.SrvProjectDto;
import com.kjplus.eto.OrgEto;
import com.kjplus.eto.SrvProjectCfgEto;
import com.kjplus.eto.SrvProjectEto;
import com.kjplus.model.*;
import com.kjplus.service.IOrgService;
import com.kjplus.service.ISrvProjectService;
import com.kjplus.service.ISysBaseService;
import com.kjplus.service.ITagService;
import com.kjplus.util.DataValUtil;
import com.ybk.exception.DataException;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ybk.basic.util.Util;

import java.util.List;

@Service("srvProjectService")
public class SrvProjectService implements ISrvProjectService {

    private Logger logger = Logger.getLogger(SrvProjectService.class);

    @Autowired
    private ISrvProjectDao srvProjectDao;
    @Autowired
    private ITagService tagService;
    @Autowired
    private IOrgService orgService;
    @Autowired
    private ISysBaseService sysBaseService;


    //通过id code 获取服务项目对象
    public SrvProjectEbo getSrvPrjByCodeOrId(int id, String code) throws DataException {
        boolean isNull = id <= 0 && Util.isNull(code) ? true : false;
        if(isNull)
        	return null;
        return srvProjectDao.getSrvPrjByCodeOrId(id, code, Constant.FLAG_YES);
    }

    //添加服务项目
    public SrvProjectEbo addSrvProject(SrvProjectEto srvProject) throws DataException {
        //做控制验证
        DataValUtil.dataValidation(srvProject.getClass(), srvProject);
        //生成唯一不重复编号
        SrvProjectEbo srvPrj = null;
        String code = srvProject.getCode();
        // 产生一个8位长度
        if (Util.isNull(code)) {
            code = Util.genDigiCodeStr(SrvProjectEto.CODE_LEN);
            srvPrj = getSrvPrjByCodeOrId(0, code);
            while (srvPrj != null) {
                code = Util.genDigiCodeStr(OrgEto.CODE_LEN);
                srvPrj = getSrvPrjByCodeOrId(0, code);
            }
        } else {
            srvPrj = getSrvPrjByCodeOrId(0, code);
            if (srvPrj != null)
                return srvPrj;
        }
        //判断参照是否存在
        SysRefValueEbo refVl = sysBaseService.getRefVlByCode(srvProject.getTypeCode());
        if (refVl == null) {
            throw new DataException("系统无此参照");
        }
        //查询服务项目是否已在数据库中存在
        List<SrvProjectDto> srvPrjs = listSrvProject(null, refVl.getCode(), Constant.FLAG_YES);
        if (srvPrjs.size() > 0)//如果同类型服务存在直接返回空
            throw new DataException("所属类型项目已有请勿重复添加");
        SrvProjectEbo addSrvPrj = null;
        try {
            addSrvPrj = new SrvProjectEbo();
            BeanUtils.copyProperties(srvProject, addSrvPrj);
            addSrvPrj.setCode(code);
            addSrvPrj.setTypeId(refVl.getId());
            srvProjectDao.addSrvProject(addSrvPrj);
        } catch (DataException e) {
            logger.error(e.getMessage());
        }
        return addSrvPrj;
    }

    //查询服务项目列表
    public List<SrvProjectDto> listSrvProject(String code, String typeCode, String flag) throws DataException {

        return srvProjectDao.listSrvProject(code, typeCode, flag);
    }

    //修改服务项目状态
    public void updateSrvProjectByFlag(int id, String code, String flag) throws DataException {
        boolean isNull = (id <= 0 && Util.isNull(code)) || Util.isNull(flag) ? true : false;
        if (isNull)
            return;
        SrvProjectEbo srvPrj = getSrvPrjByCodeOrId(id, code);
        if (srvPrj == null) {
            throw new DataException("系统无此服务项目");
        }
        //同样修改数据库不做操作
        if (srvPrj.getFlag().equals(flag))
            return;
        srvProjectDao.updateSrvProjectByFlag(id, code, flag);
    }


    public SrvProjectCfgEbo getSrvProjectCfgById(@Param("id") int id) throws DataException {
        if (id <= 0)
            return null;
        return srvProjectDao.getSrvProjectCfgById(id);
    }


    public SrvProjectCfgEbo getSrvProjectCfg(int prjId, int tagId, int orgId) throws DataException {
        boolean isNull = prjId <= 0 || tagId <= 0 || orgId <= 0 ? true : false;
        if (isNull)
            return null;
        return srvProjectDao.getSrvProjectCfg(prjId, tagId, orgId);
    }

    public SrvProjectCfgEbo addSrvProjectCfg(SrvProjectCfgEto srvProjectCfg) throws DataException {
        //做空值验证
        DataValUtil.dataValidation(srvProjectCfg.getClass(), srvProjectCfg);
        //查询服务项目是否存在
        SrvProjectEbo prj = getSrvPrjByCodeOrId(0, srvProjectCfg.getPrjCode());
        if (prj == null) {
            throw new DataException("系统无此服务项目");
        }
        //查询标签是否存在
        TagEbo tag = tagService.getTagById(srvProjectCfg.getTagId());
        if (tag == null) {
            throw new DataException("系统无此标签");
        }
        //查询住址是否存在
        OrgEbo org = orgService.getOrgByCode(srvProjectCfg.getOrgCode());
        if (org == null) {
            throw new DataException("系统无此组织");
        }
        //查询配置费否在系统中已经存在
        SrvProjectCfgEbo prjCfg = getSrvProjectCfg(prj.getId(), tag.getId(), org.getId());
        //存在直接返回
        if (prjCfg != null)
            return prjCfg;
        SrvProjectCfgEbo addSrvPrj = null;
        try {
            addSrvPrj = new SrvProjectCfgEbo();
            BeanUtils.copyProperties(srvProjectCfg, addSrvPrj);
            addSrvPrj.setOrgId(org.getId());
            addSrvPrj.setPrjId(prj.getId());
            srvProjectDao.addSrvProjectCfg(addSrvPrj);
        } catch (DataException e) {
            logger.error(e.getMessage());
        }
        return addSrvPrj;
    }

    public List<SrvProjectCfgInfoDto> listSrvProjectCfgInfo(int prjId, int orgId, int tagId, String isNorm) throws DataException {

        return srvProjectDao.listSrvProjectCfgInfo(prjId, orgId, tagId, isNorm);
    }

    //修改服务目录配置
    public void updateSrvProjectCfg(int id, int prjId, int tagId, int orgId, String isNorm) throws DataException {
        if (id <= 0)
            return;
        SrvProjectEbo prj = null;
        if (prjId > 0) {
            prj = getSrvPrjByCodeOrId(prjId, null);
            if (prj == null)
                throw new DataException("系统无此服务项目");
            if (prjId == prj.getId())
                prjId = 0;
        }
        TagEbo tag = null;
        if (tagId > 0) {
            tag = tagService.getTagById(tagId);
            if (tag == null)
                throw new DataException("系统无此标签");
            if (tagId == tag.getId())
                tagId = 0;
        }
        OrgEbo org = null;
        if (orgId > 0) {
            org = orgService.getOrgById(orgId);
            if (org == null)
                throw new DataException("系统无此组织");
            if (orgId == org.getId())
                orgId = 0;
        }

        SrvProjectCfgEbo srvPrjcfg = getSrvProjectCfgById(id);
        if (srvPrjcfg == null) {
            throw new DataException("系统无此服务项目配置");
        }

        if (srvPrjcfg.getIsNorm().equals(isNorm))
            isNorm = null;
        //参数全匹配拦截
        boolean isIntercept = prjId == 0 && tagId == 0 && orgId == 0 && Util.isNull(isNorm) ? true : false;
        if(isIntercept)
            return;
        srvProjectDao.updateSrvProjectCfgByisNorm(id, prjId, tagId, orgId, isNorm);
    }

}
