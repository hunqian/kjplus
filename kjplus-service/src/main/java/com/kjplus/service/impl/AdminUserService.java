package com.kjplus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ybk.basic.util.StringUtil;
import org.ybk.basic.util.Util;

import com.kjplus.constant.Constant;
import com.kjplus.dao.IAdminUserDao;
import com.kjplus.dto.AdminUserDto;
import com.kjplus.eto.AdminUserEto;
import com.kjplus.eto.UserEto;
import com.kjplus.model.AdminUserEbo;
import com.kjplus.model.OrgEbo;
import com.kjplus.model.UserEbo;
import com.kjplus.model.UserMapEbo;
import com.kjplus.model.inner.AdminUserInnerEbo;
import com.kjplus.service.IAdminUserService;
import com.kjplus.service.IOrgService;
import com.kjplus.service.IUserMapService;
import com.kjplus.service.IUserService;
import com.kjplus.util.DataValUtil;
import com.mq.util.DateUtil;
import com.ybk.exception.DataException;

@Service("adminUserService")
public class AdminUserService implements IAdminUserService {

	private static Logger logger = Logger.getLogger(AdminUserService.class);

	@Autowired
	private IAdminUserDao adminUserDao;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private IUserMapService userMapService;
	@Autowired
	private IUserService userService;

	public AdminUserEbo login(String user, String password) throws DataException {

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
		if (Util.isNotNull(mobieStr) && mobieStr.equals(user))
			checkType = 3;

		AdminUserEbo adminUser = adminUserDao.login(user, user, password2, user, checkType);
		if (adminUser != null) {
			// 判断用户是否为锁定状态
			if (Constant.LOCKED_FLAG.equalsIgnoreCase(adminUser.getStatus()))
				throw new DataException("[err]用户被锁定!");
			else
				return adminUser;
		} else
			throw new DataException("[err]用户名或密码不匹配!");
	}

	public AdminUserEbo getUserByUid(int uid) throws DataException {
		if (uid <= 0)
			return null;
		else
			return adminUserDao.getUserByUid(uid);
	}

	// 注册
	public void register(AdminUserEto adminUserEto) throws DataException {

		// 类型验证判断
		DataValUtil.dataValidation(adminUserEto.getClass(), adminUserEto);
		String userName = adminUserEto.getUserName();
		String mobile = adminUserEto.getMobileNum();
		AdminUserEbo u2 = null;
		if (Util.isNull(userName) && Util.isNull(mobile))
			throw new DataException("[err]用户名和手机号不能同时为空!");

		if (Util.isNull(adminUserEto.getPassWord()))
			throw new DataException("[err]密码不能为空");

		if (Util.isNull(userName)) {
			// 产生一个随机的用户名
			userName = Util.genAuthCode(AdminUserEto.MAX_USERNAME_LEN);
			u2 = adminUserDao.getUserByUnameMobile(userName, null);
			while (u2 != null) {
				userName = Util.genAuthCode(AdminUserEto.MAX_USERNAME_LEN);
				u2 = adminUserDao.getUserByUnameMobile(userName, null);
			}
		} else {
			// 查询是否存在此管理员
			u2 = adminUserDao.getUserByUnameMobile(userName, null);
			if (u2 != null)
				throw new DataException("[err]该用户已经存在：userName =" + userName);
		}

		if (Util.isNotNull(mobile)) {
			// 手机号格式验证 具体格式
			if (adminUserEto.getMobileNum().length() != 11)
				throw new DataException("[err]手机号不符合要求且不能为空");
			// TODO：需要进行手机格式验证

			// 查询是否存在此管理员
			u2 = adminUserDao.getUserByUnameMobile(null, mobile);
			if (u2 != null)
				throw new DataException("[err]该用户已经存在：mobile =" + mobile);
		}

		// 此处需要对password进行转换md5加密后重设。
		adminUserEto.setPassWord(StringUtil.md5(adminUserEto.getPassWord()));
		String email = adminUserEto.getEmail();
		if (Util.isNotNull(email)) {
			// 页面验证后邮箱格式验证
			if (!email.contains("@"))
				throw new DataException("[err]邮箱格式不对");
		}

		Integer orgId = adminUserEto.getOrgId();
		OrgEbo orgEbo = orgService.getOrgById(orgId);
		if (orgEbo == null)
			throw new DataException("[err]该组织不存在：orgId =" + orgId);

		try {
			AdminUserEbo adminEbo = new AdminUserEbo();
			BeanUtils.copyProperties(adminUserEto, adminEbo);
			adminEbo.setUserName(userName);
			adminEbo.setOrgid(adminUserEto.getOrgId());
			adminUserDao.register(adminEbo);
			logger.info("管理员添加成功" + adminEbo);
			
			//添加管理员对应的普通用户
			//建立医生的普通用户
			UserEbo u = userService.getUserByNameOrMobile(userName, adminEbo.getMobileNum());
			if(u == null){
				UserEto user = new UserEto();
				user.setUserName(userName);
				user.setNickName(userName);
				user.setMobileNum(adminEbo.getMobileNum());
				user.setPassWord(Constant.DEFAULT_PASSWORD);
				user.setFace(adminEbo.getFace());
				user.setUserType(Constant.USER_TYPE_STAFF);//医生用户
				user.setOrgId(adminEbo.getOrgid());
				u = userService.addUser(user);
			}
			//建立管理员与普通用户的关系
			UserMapEbo userMap = new UserMapEbo();
			userMap.setMainId(adminEbo.getUid());
			userMap.setMainType(Constant.USER_TYPE_ADMIN);
			userMap.setUid(u.getUid());
			userMap.setUserType(Constant.USER_TYPE_UNIVERSIAL);
			userMapService.addUserMap(userMap);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	// userName模糊查询
	public List<AdminUserDto> listUser(String userName, String nickName, String userType, String status, int orgid, int page, int paging) throws DataException {

		List<AdminUserInnerEbo> userList = new ArrayList<AdminUserInnerEbo>();
		List<AdminUserDto> users = new ArrayList<AdminUserDto>();
		try {
			userList = adminUserDao.listUser(userName, nickName, userType, status, orgid, page, paging);
			for (AdminUserInnerEbo u : userList) {
				AdminUserDto a = new AdminUserDto();
				BeanUtils.copyProperties(u, a);
				a.setRegTime(DateUtil.formatDateTime(u.getRegTime()));
				a.setEffectFrom(DateUtil.formatDate(u.getEffectFrom()));
				a.setEffectTo(DateUtil.formatDate(u.getEffectTo()));
				a.setCreateTime(DateUtil.formatDateTime(u.getCreateTime()));
				users.add(a);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return users;
	};

	// 获得总数
	public int getTotalUser(String userName, String nickName, String userType, String status, int orgid) throws DataException {
		int totalUser = 0;
		try {
			totalUser = adminUserDao.getTotalUser(userName, nickName, userType, status, orgid);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return totalUser;
	}

	// 管理员用户名,电话获取
	public AdminUserEbo getUserByUnameMobile(String userName, String mobile) throws DataException {
		return adminUserDao.getUserByUnameMobile(userName, mobile);
	};

	public void updateAdminUser(AdminUserEbo adminUserEbo) throws DataException {

		AdminUserEbo admin = getUserByUnameMobile(adminUserEbo.getUserName(), null);
		if (admin == null)
			throw new DataException("该用户不存在，userName=" + adminUserEbo.getUserName());
		if (Util.isNull(adminUserEbo.getPassWord()))
			adminUserEbo.setPassWord(admin.getPassWord());
		else
			adminUserEbo.setPassWord(StringUtil.md5(adminUserEbo.getPassWord()));
		if (Util.isNotNull(adminUserEbo.getEmail())) {
			if (!adminUserEbo.getEmail().contains("@"))
				throw new DataException("[err]邮箱格式不对");
		}
		// 判断是否修改手机号
		if (!(admin.getMobileNum().equals(adminUserEbo.getMobileNum()))) {
			if (Util.isNotNull(adminUserEbo.getMobileNum())) {
				// 手机号格式验证 具体格式
				if (adminUserEbo.getMobileNum().length() != 11)
					throw new DataException("[err]手机号不符合要求且不能为空");
				// 查询是否存在此管理员
				AdminUserEbo u2 = adminUserDao.getUserByUnameMobile(null, adminUserEbo.getMobileNum());
				if (u2 != null)
					throw new DataException("[err]该用户已经存在：mobileNum =" + adminUserEbo.getMobileNum());
			}
		}
		if (Util.isNotNull(adminUserEbo.getStatus())) {
			if (adminUserEbo.getStatus().equals("Y")) {
				adminUserEbo.setStatus(Constant.FLAG_YES);
			} else if (adminUserEbo.getStatus().equals("N")) {
				adminUserEbo.setStatus(Constant.FLAG_NO);
			} else {
				throw new DataException("[err]该用户状态格式有问题");
			}
		}
		adminUserDao.updateAdminUser(adminUserEbo);
	};

}
