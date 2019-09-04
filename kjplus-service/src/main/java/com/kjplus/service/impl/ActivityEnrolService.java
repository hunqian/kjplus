package com.kjplus.service.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.kjplus.dao.IActivityEnrolDao;
import com.kjplus.dto.ActivityEnrolInnerDto;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ybk.basic.util.Util;

import com.kjplus.model.*;
import com.kjplus.service.*;
import com.ybk.exception.DataException;

@Service("activityEnrolService")
public class ActivityEnrolService implements IActivityEnrolService {


	@Autowired
	private IActivityEnrolDao activityEnrolDao;
	@Autowired
	private IDocInfoService docInfoService;
	@Autowired
	private ICalendarMainService calendarMainService;

	
	public ActivityEnrolEbo getActEnrolEbo(int id, int uid, String userType, int mainId, String mainType)
			throws DataException {
		if("DOC".equals(userType)){		
			DocumentInfoEbo docInfo = docInfoService.getDocinfoByIdOrCode(uid, null);
			if(docInfo==null)
				throw new DataException("该用户没有建档 ，uid="+uid);
		}
		if(Util.isNotNull(mainType)){
			if(mainType.equals("CA"))
				mainType = "CA";
			else if(mainType.equals("A"))
				mainType = "A";
			else
				throw new DataException("输入的活动或服务类型有误");
		}
		ActivityEnrolEbo actEnrol = activityEnrolDao.getActEnrolEbo(id, uid, userType, mainId, mainType);
		return actEnrol;
	}

	public List<ActivityEnrolEbo> listActEnrolEbo(int uid, String userType, int mainId, String mainType, String status,
			int page, int paging) throws DataException {
		if("DOC".equals(userType)){		
			DocumentInfoEbo docInfo = docInfoService.getDocinfoByIdOrCode(uid, null);
			if(docInfo==null)
				throw new DataException("该用户没有建档 ，uid="+uid);
		}
		if(Util.isNotNull(mainType)){
			if(mainType.equals("CA"))
				mainType = "CA";
			else if(mainType.equals("A"))
				mainType = "A";
			else
				throw new DataException("输入的活动或服务类型有误");
		}
		List<ActivityEnrolEbo> listActEnrolEbo = activityEnrolDao.listActEnrolEbo(uid, userType, mainId, mainType, status, page, paging);
		return listActEnrolEbo;
	}

	public ActivityEnrolEbo addActEnrolEbo(ActivityEnrolEbo activityEnrolEbo) throws DataException {
		if("DOC".equals(activityEnrolEbo.getUserType())){		
			DocumentInfoEbo docInfo = docInfoService.getDocinfoByIdOrCode(activityEnrolEbo.getUid(), null);
			if(docInfo==null)
				throw new DataException("该用户没有建档 ，uid="+activityEnrolEbo.getUid());
		}
		if(activityEnrolEbo.getMainType().equals("CA"))
			activityEnrolEbo.setMainType("CA");
		else if(activityEnrolEbo.getMainType().equals("A"))
			activityEnrolEbo.setMainType("A");
		else
			throw new DataException("输入的活动或服务类型有误");
		ActivityEnrolEbo act = getActEnrolEbo(0, activityEnrolEbo.getUid(), activityEnrolEbo.getUserType(),activityEnrolEbo.getMainId(), activityEnrolEbo.getMainType());
		if(act != null)
			throw new DataException("不可重复报名");
		activityEnrolDao.addActEnrolEbo(activityEnrolEbo);
		
		return activityEnrolEbo;
	}

	public void updateActEnrolEbo(int id, int uid, String userType, int mainId, String mainType, String status)
			throws DataException {
		ActivityEnrolEbo actEnrol= getActEnrolEbo(id, uid, userType, mainId, mainType);
		if(actEnrol == null)
			throw new DataException("该条记录不存在");
		activityEnrolDao.updateActEnrolEbo(id, uid, userType, mainId, mainType, status);
	}
	
	public List<ActivityEnrolInnerDto> listActEnrolInner(int uid, String userType,int mainId, String mainType, String status,int page,int paging)throws DataException{
		List<ActivityEnrolEbo> listAct = listActEnrolEbo(uid, userType, mainId, mainType, status, page, paging);
		List<ActivityEnrolInnerDto> listActInner = new ArrayList<ActivityEnrolInnerDto>(); 
		Hashtable<Integer,ActivityEnrolEbo> hash = new Hashtable<Integer,ActivityEnrolEbo>();
		ActivityEnrolInnerDto actIn = new ActivityEnrolInnerDto(); 
		for (ActivityEnrolEbo act: listAct) {
			if("DOC".equals(act.getUserType()) && "CA".equals(act.getMainType()) ){
				if(hash.containsKey(act.getUid())){
					for (ActivityEnrolInnerDto actInner: listActInner) {
						if(actInner.getUid()==act.getUid()){
							actInner.getListCalInfo().add(calendarMainService.getCalInfoByIdOrCode(act.getMainId(), null));
						}
					}
				}else{
					hash.put(act.getUid(), act);
					BeanUtils.copyProperties(act, actIn);
					actIn.getListCalInfo().add(calendarMainService.getCalInfoByIdOrCode(act.getMainId(), null));
					listActInner.add(actIn);
				}
				
			}	
		}
		return listActInner;
	}	
	

}
