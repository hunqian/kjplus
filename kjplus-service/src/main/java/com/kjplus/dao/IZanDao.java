package com.kjplus.dao;

import java.util.List;

import com.kjplus.model.inner.ZanInfoInnerEbo;
import com.kjplus.model.inner.ZanStatusInnerEbo;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.model.*;
import com.ybk.exception.DataException;

@Repository("zanDao")
public interface IZanDao {

    //通过id或code获取点赞
    public ZanEbo getZanByIdOrCode(@Param("id") int id, @Param("code") String code) throws DataException;

    //点赞列表
    public List<ZanEbo> listZanByIdAndType(@Param("mainId") int mainId,
                                           @Param("mainType") String mainType) throws DataException;

    //通过uid查询点赞记录
    public int getZanHisNumByUid(@Param("uid") int uid, @Param("zanId") int zanId) throws DataException;

    //添加点赞
    public void addZan(ZanEbo zan) throws DataException;

    //添加点赞记录
    public void addZanHis(ZanHisEbo zanHis) throws DataException;

    //修改点赞数量
    //zanStatus=Y点赞，N取消点赞
    public void updateZanNum(@Param("zanId") int zanId, @Param("zanStatus") String zanStatus) throws DataException;

    //获取当前点赞数量
    public int getZanHisNum(@Param("zanId") int zanId) throws DataException;

    //删除点赞记录
    public void delZanHis(@Param("zanId") int zanId, @Param("uid") int uid) throws DataException;

    //点赞和关注信息,@Param("mainType") String mainType
    public List<ZanInfoInnerEbo> listZanAndFoInfo(@Param("infoCode") String infoCode, @Param("infoFlag") String infoFlag, @Param("uid") int uid) throws DataException;
    
    //获得多个用户和zan状态
    public List<ZanStatusInnerEbo> listZanHisStatus(@Param("mainIds") String mainIds, @Param("mainType") String mainType
    		, @Param("uid") int uid) throws DataException;

}
