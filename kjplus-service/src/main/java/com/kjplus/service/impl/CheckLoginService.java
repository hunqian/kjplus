package com.kjplus.service.impl;

import java.util.*;

import com.kjplus.constant.Constant;
import com.kjplus.model.*;
import com.kjplus.service.*;
import com.ybk.exception.DataException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ybk.basic.util.Util;

//安卓端登录检查
@Service("checkLoginService")
public class CheckLoginService implements ICheckLoginService {

	@Autowired
	private IAdminUserService adminUserService;
	@Autowired
	private IAppKeyService appKeyService;

	// 登录方法主入口
	public Map<String, Object> checkLogin(String opentoken, String userName, String passWord) throws DataException {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		SysAppKeyEbo addKey = null;
		if (Util.isNotNull(opentoken)) {
			// 检查token
			boolean res = appKeyService.checkToken(opentoken, Constant.TOKEN_OUT_TIME, true);
			if (!res) {// 如果檢查不通过
				// 通过token获取key
				SysAppKeyEbo appKey = appKeyService.getKey(opentoken);
				if (appKey == null) {
					return checkLoginTwo(userName, passWord);
				} else {
					int orgId = Util.parseNumVl(String.valueOf(appKey.getVisitOrgid()), 0);
					// 通过原openId查询是否有存在的未过期的token
					SysAppKeyEbo key = appKeyService.getKeyByOpenid(appKey.getOpenid());
					if (key != null) {
						// 校验token对应的openId是否存在有效token
						boolean res1 = appKeyService.checkToken(key.getOpentoken(), Constant.TOKEN_OUT_TIME, true);
						if (res1 == true) {// 如果存在有效token直接返回
							map.put("opentoken", key.getOpentoken());
							map.put("status", 1);
							map.put("message", "登录成功");
							return map;
						}
					}
					// 添加key
					addKey = appKeyService.addKey(appKey.getOpenid(), appKey.getVisitUid(), orgId);
					// 判断是否添加成功
					if (addKey != null) {
						// 成功后将opentoken返回
						map.put("opentoken", addKey.getOpentoken());
						map.put("status", 1);
						map.put("message", "登录成功");
						return map;
					} else {
						// 如果添加不成功做用户名密码校验
						return checkLoginTwo(userName, passWord);
					}
				}
			} else {// 如果检查通过
				map.put("opentoken", opentoken);
				map.put("status", 1);
				map.put("message", "登录成功");
				return map;
			}

		} else {

			return checkLoginThree(userName, passWord);
		}

	}

	// 登录第二次检查
	private Map<String, Object> checkLoginTwo(String userName, String passWord) throws DataException {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		if (Util.isNull(userName) || Util.isNull(passWord)) {
			map.put("status", -1);
			map.put("message", "用户名密码不能空");
			return map;
		}
		AdminUserEbo adminUser1 = adminUserService.login(userName, passWord);

		if (adminUser1 != null) {
			// 获取原token通过uid
			SysAppKeyEbo getkey1 = appKeyService.getKeyByUid(adminUser1.getUid(), Constant.FLAG_YES);
			if (getkey1 != null) {
				// 查询是否是有效token
				boolean res1 = appKeyService.checkToken(getkey1.getOpentoken(), Constant.TOKEN_OUT_TIME, true);
				if (!res1) {// 如果token无效
					int orgId1 = Util.parseNumVl(String.valueOf(adminUser1.getOrgid()), 0);
					// 拿到原openId重新生成token
					SysAppKeyEbo addKey = appKeyService.addKey(getkey1.getOpenid(), adminUser1.getUid(), orgId1);
					map.put("opentoken", addKey.getOpentoken());
					map.put("status", 1);
					map.put("message", "登录成功");
				} else {
					map.put("opentoken", getkey1.getOpentoken());
					map.put("status", 1);
					map.put("message", "登录成功");
				}
			}
		}
		return map;
	}

	// 登录第三次检查
	private Map<String, Object> checkLoginThree(String userName, String passWord) throws DataException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		// 用户名密码做空值判断
		if (Util.isNull(userName) || Util.isNull(passWord)) {
			map.put("status", -1);
			map.put("message", "用户名密码不能空");
			return map;
		}
		AdminUserEbo adminUser2 = adminUserService.login(userName, passWord);
		int orgId2 = 0;
		if (adminUser2 != null) {
			// 用户是否有分配的key
			SysAppKeyEbo getkey2 = appKeyService.getKeyByUid(adminUser2.getUid(), Constant.FLAG_YES);
			if (getkey2 != null) {
				// 检查是否有效
				boolean res2 = appKeyService.checkToken(getkey2.getOpentoken(), Constant.TOKEN_OUT_TIME, true);
				if (!res2) {// 如果不是有效
					orgId2 = Util.parseNumVl(String.valueOf(adminUser2.getOrgid()), 0);
					SysAppKeyEbo addkey1 = appKeyService.addKey(getkey2.getOpenid(), adminUser2.getUid(), orgId2);
					map.put("opentoken", addkey1.getOpentoken());
					map.put("status", 1);
					map.put("message", "登录成功");
				} else {// 如果有效
					map.put("opentoken", getkey2.getOpentoken());
					map.put("status", 1);
					map.put("message", "登录成功");
				}
			} else {
				orgId2 = Util.parseNumVl(String.valueOf(adminUser2.getOrgid()), 0);
				String openId = UUID.randomUUID().toString();
				SysAppKeyEbo addKey2 = appKeyService.addKey(openId, adminUser2.getUid(), orgId2);
				map.put("status", 1);
				map.put("message", "登录成功");
				map.put("opentoken", addKey2.getOpentoken());
				return map;
			}
		}
		return map;
	}
	
	// 生成token
	public String genToken(AdminUserEbo userEbo) throws DataException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		
		String openId = UUID.randomUUID().toString();
		SysAppKeyEbo addKey2 = appKeyService.addKey(openId, userEbo.getUid(), userEbo.getOrgid());
		map.put("status", 1);
		map.put("message", "登录成功");
		map.put("opentoken", addKey2.getOpentoken());
		return addKey2.getOpentoken();
	}
}
