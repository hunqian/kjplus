package com.kjplus.service;

import java.util.List;

import com.kjplus.dto.ActivityEnrolInnerDto;
import com.kjplus.model.*;
import com.ybk.exception.DataException;

public interface IActivityEnrolService {


	public ActivityEnrolEbo getActEnrolEbo(int id,int uid, String userType, int mainId , String mainType)throws DataException;
	
	public List<ActivityEnrolEbo> listActEnrolEbo(int uid, String userType,int mainId, String mainType, String status,int page,int paging)throws DataException;
	
	//获取个人所参加的活动信息  TODO mainId是否对应t_cal_info中的id 还是t_cal_activity中id
	public List<ActivityEnrolInnerDto> listActEnrolInner(int uid, String userType,int mainId, String mainType, String status,int page,int paging)throws DataException;
	
	public ActivityEnrolEbo addActEnrolEbo(ActivityEnrolEbo activityEnrolEbo)throws DataException;
	
	public void updateActEnrolEbo( int id, int uid,String userType,int mainId,String mainType,String status)throws DataException;

}
