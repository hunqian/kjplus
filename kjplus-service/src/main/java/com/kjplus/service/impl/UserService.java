package com.kjplus.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ybk.basic.util.StringUtil;
import org.ybk.basic.util.Util;

import com.kjplus.constant.Constant;
import com.kjplus.dao.IUserDao;
import com.kjplus.dto.PersonServiceDto;
import com.kjplus.dto.UserDto;
import com.kjplus.dto.UserPersonDto;
import com.kjplus.eto.UserEto;
import com.kjplus.model.SysRefValueEbo;
import com.kjplus.model.UserEbo;
import com.kjplus.model.UserPersonEbo;
import com.kjplus.model.inner.UserPersonInnerEbo;
import com.kjplus.service.ISysBaseService;
import com.kjplus.service.IUserService;
import com.mq.util.DateUtil;
import com.ybk.exception.DataException;

@Service("userService")
public class UserService implements IUserService {

	@Autowired
	private IUserDao userDao;
	@Autowired
	private ISysBaseService baseService;
	
	private static Logger logger = Logger.getLogger(UserService.class);

	public UserEbo getUserById(int uid) throws DataException {
		if (uid <= 0)
			return null;
		return userDao.getUser(uid, null, null);
	}

	// 通过用户名和手机号查询用户
	public UserEbo getUserByNameOrMobile(String userName,String mobileNum) throws DataException {
		boolean isNull = Util.isNull(userName) && Util.isNull(mobileNum) ? true:false;
		if (isNull)
			return null;
		return userDao.getUser(0, userName, mobileNum);
	}

	// 根据uid列表查询user
	public List<UserEbo> listUserByUids(List<Integer> uids) throws DataException {
		List<UserEbo> users = new ArrayList<UserEbo>();
		if (uids == null || uids.size() == 0)
			return users;
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < uids.size(); i++) {
			if (i != 0)
				buf.append(",");
			buf.append(uids.get(i));
		}
		return userDao.listUserByUids(buf.toString());
	}

	// 创建用户
	public UserEbo addUser(UserEto user) throws DataException {
		if (user == null)
			throw new DataException("[err]空对象!");
		if (Util.isNull(user.getNickName()))
			throw new DataException("[err]必须指定昵称!");
		if (Util.isNull(user.getPassWord()))
			throw new DataException("[err]必须设置密码!");
		if (user.getOrgId() == null || user.getOrgId() <= 0)
			throw new DataException("[err]必须指定所属的组织!");
		if (Constant.FLAG_YES.equals(user.getMobileFlag())) {
			if (Util.isNull(user.getMobileNum()))
				throw new DataException("[err]请指定电话!");
		}

		String mobile = user.getMobileNum();
		UserEbo u2 = null;
		if (Util.isNull(mobile)) {
			// 生成一个随机的
			mobile = "T" + Util.genDigiCodeStr(11);
			u2 = getUserByNameOrMobile(null,mobile);
			while (u2 != null) {
				mobile = "T" + Util.genDigiCodeStr(11);
				u2 = getUserByNameOrMobile(null,mobile);
			}
			user.setMobileFlag(Constant.FLAG_NO);
		}
		String userName = user.getUserName();
		if (userName != null) {
			u2 = getUserByNameOrMobile(userName,mobile);
			if (u2 != null) {
				throw new DataException("[err]用户名已经存在!");
			}
		} else {
			// 可以随机产生一个
			userName = "T" + Util.genSessCode(16);
			u2 = getUserByNameOrMobile(userName,mobile);
			while (u2 != null) {
				userName = "T" + Util.genSessCode(16);
				u2 = getUserByNameOrMobile(userName,mobile);
			}
		}
		UserEbo u = new UserEbo();
		try {
			BeanUtils.copyProperties(user, u);
			u.setCreateTime(Calendar.getInstance().getTime());
			if (u.getRegTime() == null)
				u.setRegTime(Calendar.getInstance().getTime());
			u.setUserName(userName);
			u.setMobileNum(mobile);
			u.setPassWord(StringUtil.md5(user.getPassWord()));
			userDao.addUser(u);
			return getUserByNameOrMobile(userName,mobile);
		} catch (BeansException be) {
			logger.error(be);
		}
		return null;
	}

	public UserEbo login(String user, String password) throws DataException {
		if (Util.isNull(user) || Util.isNull(password))
			throw new DataException("[err]用户名或密码为空!");
		// 此处需要对password进行转换，md5
		String password2 = StringUtil.md5(password);
		// 用户名
		int checkType = 1;
		if (user.contains("@"))
			checkType = 2;
		// 判断是否是手机号码
		String mobieStr = Util.getRexStr(user, Constant.MOBILE_REX);
		// String mobieStr = null;
		if (Util.isNotNull(mobieStr) && user.equals(mobieStr))
			checkType = 3;
		UserEbo userEbo = null;
		if (checkType == 1)
			userEbo = getUserByNameOrMobile(user,null);
		// else if(checkType == 2)
		// userEbo = getUserByUserName(user);
		else if (checkType == 3)
			userEbo = getUserByNameOrMobile(user,null);

		if (userEbo == null)
			throw new DataException("[err]用户名或密码不存在!");

		if (Constant.FLAG_LOCK.equalsIgnoreCase(userEbo.getStatus()))
			throw new DataException("[err]用户被锁定!");
		if (!password2.equals(userEbo.getPassWord()))
			throw new DataException("[err]用户密码不匹配!");
		return userEbo;
	}

	public List<UserPersonDto> listUserPerson(int uid, int psrvId, int orgId, String flag, String srvStatus) throws DataException {

		List<UserPersonInnerEbo> userPersonInners = userDao.listUserPersonInner(uid, psrvId, flag, srvStatus);
		Map<Integer, Object> upMap = new HashMap<Integer, Object>();
		List<UserPersonDto> userPersons = new ArrayList<UserPersonDto>();
		UserPersonDto userPerson = null;
		PersonServiceDto personService = null;
		int userId = 0;
		int personId = 0;
		for (UserPersonInnerEbo up : userPersonInners) {
			userId = up.getUid();
			personId = up.getPersonId();
			if (!upMap.containsKey(userId)) {
				userPerson = new UserPersonDto();
				BeanUtils.copyProperties(up, userPerson);
				personService = new PersonServiceDto();
				BeanUtils.copyProperties(up, personService);
				userPerson.getPrsnServices().add(personService);
				userPersons.add(userPerson);
				upMap.put(userId, userPerson);
				upMap.put(personId, userPerson);
			} else {
				if (!upMap.containsKey(personId)) {
					personService = new PersonServiceDto();
					BeanUtils.copyProperties(up, personService);
					userPerson.getPrsnServices().add(personService);
				}
				upMap.put(personId, userPerson);
			}
		}
		return userPersons;
	}

	// 查看用户列表信息
	public List<UserDto> listUser(int uid, String nickName, int orgid, String status, String mobileNum, String userType, int startPage, int endPage) throws DataException {

		List<UserEbo> userList = new ArrayList<UserEbo>();
		List<UserDto> users = new ArrayList<UserDto>();
		try {
			userList = userDao.listUser(uid, nickName, orgid, status, mobileNum, userType, startPage, endPage);
			for (UserEbo userEbo : userList) {
				UserDto userDto = new UserDto();
				BeanUtils.copyProperties(userEbo, userDto);
				userDto.setRegTime(DateUtil.formatDate(userEbo.getRegTime()));
				userDto.setEffectFrom(DateUtil.formatDate(userEbo.getEffectFrom()));
				userDto.setEffectTo(DateUtil.formatDate(userEbo.getEffectTo()));
				userDto.setCreateTime(DateUtil.formatDateTime(userEbo.getCreateTime()));
				users.add(userDto);
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return users;
	}

	// 获取用户数据总数
	public int getTotalUser(int uid, String nickName, int orgid, String flag, String mobileNum, String userType) throws DataException {
		int totalUser = 0;
		try {
			totalUser = userDao.getTotalUser(uid, nickName, orgid, flag, mobileNum, userType);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return totalUser;
	}

	// 增加用户和person对应
	public void addUserPersonRel(int uid, String userType, int prsnId) throws DataException {
		List<UserPersonEbo> ups = userDao.listUserPerson(uid, userType, prsnId);
		if (ups.size() > 0)
			return;
		UserPersonEbo up = new UserPersonEbo();
		up.setUid(uid);
		up.setUserType(userType);
		up.setPrsnId(prsnId);
		up.setCreateTime(Calendar.getInstance().getTime());
		userDao.addUserPersonRel(up);
	}
	
	// 列表 微信用户与档案用户的关系
	public List<UserPersonEbo> listUserPersonMap(int uid, String userType, int prsnId) throws DataException{
		return userDao.listUserPerson(uid, userType, prsnId);
	}

	// 删除用户和person对应
	public void delUserPersonRel(int uid, String userType, int prsnId) throws DataException {
		userDao.delUserPerson(uid, userType, prsnId);
	}

	// 上传头像
	public void uploadFace(int uid, String face) throws DataException {
		// TODO Auto-generated method stub
		userDao.uploadFace(uid, face);
	}

	// 获取微信用户自己的prsnId
	public int getSelfPrsnIdByUserId(int uid) throws DataException{
		UserEbo userEbo = getUserById(uid);
		if(userEbo == null)
			return 0;
		SysRefValueEbo refVl= baseService.getRefVlByCode("RV_MYSELF");
		if(refVl == null)
			return 0;
		UserPersonEbo map = userDao.getSelfPrsnIdByUserId(uid, "U",refVl.getId());
		if(map == null)
			return 0;
		return map.getPrsnId();
	}
	
}
