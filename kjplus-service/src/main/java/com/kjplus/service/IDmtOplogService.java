package com.kjplus.service;

import com.kjplus.dto.DocumentOplogDto;
import com.kjplus.eto.DocumentOplogEto;
import com.kjplus.model.DocumentOplogEbo;
import com.ybk.exception.DataException;

import java.util.List;

public interface IDmtOplogService {

    /**
     *获取当前组织的当前人的什么类型档案的修改次数
     * @param prsnId
     *      居民id
     * @param opType
     *      修改类型 是个人档案、血糖测量 等
     * @param orgId
     *      属于什么组织
     * @return
     * @throws DataException
     */
    public Integer getDmtOplogNum( int prsnId,  int opType,  int orgId )throws DataException;

    /**
     *   某档案最近一次修改记录
     * @param prsnId
     *        居民id
     * @param opType
     *         档案类型
     * @param orgId
     *         组织地
     * @param flag
     *          是否有效
     * @return
     * @throws DataException
     */
    public DocumentOplogEbo getDmtOplog(int prsnId,int opType, int orgId, String flag )throws DataException;

    /**
     * 添加修改记录
     *
     * @param dmtOplog
     *      传入DocumentOplogEbo对象
     * @throws DataException
     */
    public DocumentOplogEbo addDmtOplog(DocumentOplogEto dmtOplog)throws DataException;


    /**
     * 查询修改记录
     *
     * @param prsnId
     *      居民id
     * @param opType
     *      修改类型 是个人档案、血糖测量 等
     * @param orgId
     *       组织id
     * @param upPrsn
     *      修改人
     * @param upSeq
     *       修改次数
     * @param flag
     *      是否有效
     * @return
     *     返回List<DocumentOplogDto>对象
     * @throws DataException
     */
    public List<DocumentOplogDto> listDmtOplog(int prsnId, int opType,int orgId, int upPrsn, int upSeq,String flag)throws DataException;

    /**
     *
     * @param id
     *      档案修改记录id
     * @param flag
     *      档案修改状态是否生效
     * @throws DataException
     */
    public void updateDmtOplog(int id, String flag)throws DataException;


}
