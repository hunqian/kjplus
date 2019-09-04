package com.kjplus.service;

import com.kjplus.dto.ZanInfoParentDto;
import com.kjplus.eto.ZanHisEto;
import com.kjplus.model.ZanEbo;
import com.kjplus.model.inner.ZanStatusInnerEbo;
import com.ybk.exception.DataException;

import java.util.List;

public interface IZanService {

	// 添加点赞记录
	public ZanEbo addZan(int mainId, String mainType, String desc) throws DataException;
	
	//完整的一体方法，可以同时增加zan和zanhis,点赞则unzun
	//返回true是点赞关注成功，false是取消点赞关注
	public boolean zan(ZanHisEto zan) throws DataException;

	//取消点赞
	public String unZan(int mainId, String mainType, int uid) throws DataException;

	//用户点赞和关注文章列表, String mainType
	public List<ZanInfoParentDto> listZanAndFoInfo(String infoCode, String infoFlag, int uid) throws DataException;

    //获得多个用户和zan状态
    public List<ZanStatusInnerEbo> listZanHisStatus(List<Integer> mainIds, String mainType , int uid) throws DataException;
    
    
    
}
