package com.kjplus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjplus.constant.Constant;
import com.kjplus.dao.*;
import com.kjplus.model.*;
import com.kjplus.service.*;
import com.ybk.exception.DataException;

@Service("userMapService")
public class UserMapService implements IUserMapService {

	@Autowired
	private IUserMapDao userMapDao;
	@Autowired
	private IOrgService orgService; 
	@Autowired
	private ISysBaseService baseService;
	@Autowired
	private IDeptService deptService;

	public UserMapEbo addUserMap(UserMapEbo userMapEbo) throws DataException {

		UserMapEbo stMapEbo = userMapDao.getUserMap(userMapEbo.getMainId(), userMapEbo.getMainType(),
				userMapEbo.getUid(), userMapEbo.getUserType());
		if (stMapEbo == null) {
			userMapDao.addUserMap(userMapEbo);
		}
		return userMapEbo;
	}

	public int getStaffIdByUid(int uid) throws DataException {
		return getUserMap(0, Constant.UMFROM_MAIN_STAFF, uid,Constant.UMTO_MAIN_USER).getMainId();
	};

	public int getStaffIdByAdminUserId(int uid) throws DataException {
		return getUserMap(0, Constant.UMFROM_MAIN_STAFF, uid,Constant.UMTO_MAIN_ADMIN).getMainId();
	};
	
	public int getUserIdByStaffId(int staffId) throws DataException {
		return getUserMap(staffId, Constant.UMFROM_MAIN_STAFF, 0, Constant.UMTO_MAIN_ADMIN).getUid();
	};

	public int getUserIdByAdminUserId(int adminUserid) throws DataException {
		return getUserMap(adminUserid, "A", 0, Constant.UMTO_MAIN_USER).getUid();
	};

	public UserMapEbo getUserMap(int mainId, String mainType, int uid, String userType) throws DataException {
		UserMapEbo userMapEbo = userMapDao.getUserMap(mainId, mainType, uid, userType);
		if (userMapEbo == null)
			throw new DataException("映射信息不存在");
		return userMapEbo;
	}

	/*
	 * public List<UserMapInnerEbo> listUserMap(int mainId,String mainType,int
	 * uid, String userType) throws DataException { return
	 * userMapDao.listUserMap(mainId, mainType, uid, userType); }
	 */

}
