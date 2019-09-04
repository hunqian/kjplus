package com.kjplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.model.ActivityEnrolEbo;
import com.ybk.exception.DataException;

@Repository("activityEnrolDao")
public interface IActivityEnrolDao {

	public ActivityEnrolEbo getActEnrolEbo(@Param("id") int id, @Param("uid") int uid,@Param("userType") String userType,@Param("mainId") int mainId
			,@Param("mainType") String mainType)throws DataException;
	
	public List<ActivityEnrolEbo> listActEnrolEbo(@Param("uid") int uid,@Param("userType") String userType,@Param("mainId") int mainId
			,@Param("mainType") String mainType,@Param("status") String status,@Param("page") int page,@Param("paging") int paging)throws DataException;

	
	public void addActEnrolEbo(ActivityEnrolEbo activityEnrolEbo)throws DataException;
	
	public void updateActEnrolEbo(@Param("id") int id, @Param("uid") int uid,@Param("userType") String userType,@Param("mainId") int mainId
			,@Param("mainType") String mainType,@Param("status") String status)throws DataException;
	
}
