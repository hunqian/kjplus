package com.kjplus.service.impl;

import com.kjplus.dao.IDocumentOplogDao;
import com.kjplus.dto.DocumentOplogDto;
import com.kjplus.eto.DocumentOplogEto;
import com.kjplus.model.*;
import com.kjplus.service.*;
import com.kjplus.util.DataValUtil;
import com.mq.util.Util;
import com.ybk.exception.DataException;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("dmtOplogService")
public class DmtOplogService implements IDmtOplogService {

    private Logger logger = Logger.getLogger(DmtOplogService.class);
    @Autowired
    private IDocumentOplogDao documentOplogDao;
    @Autowired
    private IAdminUserService adminUserService;
    @Autowired
    private IOrgService orgService;
    @Autowired
    private ISysBaseService sysBaseService;

    /**
     * 获取当前组织的当前人的什么类型档案的修改次数
     */
    public Integer getDmtOplogNum(int prsnId, int opType, int orgId) throws DataException {
        if (opType <= 0)
            throw new DataException("请输入档案类型");
        if (prsnId <= 0)
            throw new DataException("请输入档案所属人");
        if (orgId <= 0)
            throw new DataException("请输入档案所属组织");
        return documentOplogDao.getDmtOplogNum(prsnId, opType, orgId);
    }

    /**
     * 某人某档案最近一次修改记录
     */
    public DocumentOplogEbo getDmtOplog(int prsnId, int opType, int orgId, String flag) throws DataException {
        boolean isNull = prsnId <= 0 || opType <= 0 ? true : false;
        if (isNull)
            throw new DataException("必要参数不能空");
        List<DocumentOplogEbo> dmtOplogs = documentOplogDao.getDmtOplog(prsnId, opType, orgId, flag);
        if (dmtOplogs.size() > 0)
            return dmtOplogs.get(0);
        else
            return null;
    }

    /**
     * 添加修改记录
     */
    public DocumentOplogEbo addDmtOplog(DocumentOplogEto dmtOplog) throws DataException {
        //做空值验证
        DataValUtil.dataValidation(dmtOplog.getClass(), dmtOplog);
        //查询用户是否存在
        AdminUserEbo adminUser = adminUserService.getUserByUid(dmtOplog.getUpPrsn());
        if (adminUser == null) {
            throw new DataException("系统无此用户");
        }
        SysRefValueEbo refVl = sysBaseService.getRefVlByCode(dmtOplog.getOpTypeCode());
        if (refVl == null) {
            throw new DataException("系统无此参照");
        }
        OrgEbo org = orgService.getOrgByCode(dmtOplog.getOrgCode());
        if (org == null) {
            throw new DataException("系统无此组织");
        }
        
        //获取某人 某档案修改次数
        Integer upNum = getDmtOplogNum(dmtOplog.getPrsnId(), refVl.getId(), org.getId());
        DocumentOplogEbo oplog = null;
        try {
            oplog = new DocumentOplogEbo();
            BeanUtils.copyProperties(dmtOplog, oplog);
            oplog.setPrsnId(dmtOplog.getPrsnId());
            oplog.setOpType(refVl.getId());
            oplog.setUpPrsn(adminUser.getUid());
            oplog.setOrgId(org.getId());
            oplog.setOpSeq(upNum+1);
            documentOplogDao.addDmtOplog(oplog);
        } catch (DataException e) {
            logger.error(e.getMessage());
        }
        return oplog;
    }

    /**
     * 查询修改记录
     * 加入时间查询
     */
    public List<DocumentOplogDto> listDmtOplog(int prsnId, int opType, int orgId, int upPrsn, int upSeq, String flag) throws DataException {

        return documentOplogDao.listDmtOplog(prsnId, opType, orgId, upPrsn, upSeq, flag);
    }

    /**
     * 档案修改记录
     */
    public void updateDmtOplog(int id, String flag) throws DataException {
        boolean isNull = id <= 0 || Util.isNull(flag) ? true : false;
        if (isNull)
            return;
        else
            documentOplogDao.updateDmtOplog(id, flag);
    }

}
