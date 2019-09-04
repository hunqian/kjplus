package com.kjplus.interceptor;

import java.io.PrintWriter;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.kjplus.cache.TokenCache;
import com.kjplus.constant.Constant;
import com.kjplus.constant.ShareConstant;
import com.kjplus.dto.AdminUserInfoDto;
import com.kjplus.model.*;
import com.kjplus.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.ybk.basic.util.DateUtil;
import org.ybk.basic.util.StringUtil;
import org.ybk.basic.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//统一对app端所有请求 进行token有效验证 加入缓存概念 针对登录方面 不引入缓存的概念
public class AppInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private IAppKeyService appKeyService;
	@Autowired
	private TokenCache tokenCache;
	@Autowired
	private IAdminUserService adminUserService;
	@Autowired
	private IStaffService staffService;
	@Autowired
	private IDeptService deptService;

	/**
	 * This implementation always returns {@code true}.
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		// 所有需要用户session验证的，url前面加/usck
		PrintWriter writer = null;
		String reqUri = request.getRequestURI();
		if(Util.isNull(reqUri))
			return true;
		if(!reqUri.contains(Constant.USER_CHECK_URI_PREFIX))
			return true;
		/*AdminUserEbo userEbo = (AdminUserEbo) request.getSession().getAttribute(Constant.KJAPI_SESS);
		if(userEbo != null)
			return true;*/
		//System.out.println("appIcep:" + reqUri);
		String opentoken = request.getParameter("opentoken");
		String timestamp = request.getParameter("timestamp");
		// 预处理阶段url不带token 直接拒绝请求,不返回任何数据
		if (Util.isNull(opentoken) || Util.isNull(timestamp)){
			map.put("code", ShareConstant.CONNECT_STATUS);
			map.put("result", ShareConstant.RES_UNLOGIN);//未登录
			map.put("message", "opentoken或者时间戳空!");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			// 将map装潢为json
			String json = JSON.toJSONString(map);
			writer = response.getWriter();
			writer.print(json);
			return false;
		}
		
		String headCheckMd5 = StringUtil.md5(Constant.USER_CHECK_HEAD_TAG + opentoken + timestamp);
		String headCheck = request.getHeader(Constant.USER_CHECK_HEAD);
		if(!headCheckMd5.equals(headCheck)){
			map.put("code", ShareConstant.CONNECT_STATUS);
			map.put("result", ShareConstant.RES_UNLOGIN);//未登录
			map.put("message", "请设置头信息或者头信息非法!");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			// 将map装潢为json
			String json = JSON.toJSONString(map);
			writer = response.getWriter();
			writer.print(json);
			return false;	
		}
		
		
		// 获取为过期token对象
		SysAppKeyEbo ak = appKeyService.getKey(opentoken);
		if(ak == null){
			map.put("code", ShareConstant.CONNECT_STATUS);
			map.put("result", ShareConstant.RES_UNLOGIN);//未登录
			map.put("message", "opentoken非法");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			// 将map装潢为json
			String json = JSON.toJSONString(map);
			writer = response.getWriter();
			writer.print(json);
			return false;
		}
		
		AdminUserEbo au = adminUserService.getUserByUid(ak.getVisitUid());
		if(au == null){
			map.put("code", ShareConstant.CONNECT_STATUS);
			map.put("result", ShareConstant.RES_UNLOGIN);//未登录
			map.put("message", "opentoken非法");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			// 将map装潢为json
			String json = JSON.toJSONString(map);
			writer = response.getWriter();
			writer.print(json);
			return false;
		}
		
		request.getSession().setAttribute(Constant.KJAPI_SESS, au);		
		return true;
	}

	// 添加缓存
	public void addAdUserInfo(SysAppKeyEbo key) {
		
		if (key == null)
			return;
		
		AdminUserInfoDto adminUserInfo = new AdminUserInfoDto();
		adminUserInfo.setTimeOut(key.getLastVisitTime());// 将过期时间加入缓存 以判断
															// 对象是否有理由存在缓存中
		adminUserInfo.setAdUid(key.getVisitUid());
		adminUserInfo.setOrgId(Util.parseNumVl(String.valueOf(key.getVisitOrgid()), 0));
		adminUserInfo.setOpenToken(key.getOpentoken());
		//AdminUserEbo userMap = null;
		//StaffEbo staff = null;
		//DepartmentEbo dept = null;
		//TODO:暂时屏蔽
		/*try {
			userMap = adminUserService.getUserByUid(adminUserInfo.getAdUid());
			if (userMap != null)
				staff = staffService.getStaffById(userMap.getStaffId());
			if (staff != null)
				dept = deptService.getDepartmentById(staff.getDeptId());
		} catch (DataException e) {
			e.printStackTrace();
		}
		if (userMap != null) {
			adminUserInfo.setStaffId(userMap.getStaffId());
			if (staff != null) {
				adminUserInfo.setDeptId(staff.getDeptId());
				adminUserInfo.setStaffCode(staff.getCode());
				if (dept != null)
					adminUserInfo.setDeptCode(dept.getCode());
			}
		}*/
		tokenCache.put(key.getOpentoken(), adminUserInfo);
		return;
	}

	// 获取缓存中做流程控制
	public boolean getAdUserInfo(String opentoken) {
		Cache.ValueWrapper result = null;
		if (Util.isNull(opentoken))
			return true;
		result = tokenCache.get(opentoken);
		if (result == null)
			return true;
		// 判断过期时间
		AdminUserInfoDto userinfo = (AdminUserInfoDto) result.get();
		if (userinfo.getTimeOut() + Constant.TOKEN_OUT_TIME < DateUtil.getCurTimeInSec()) {// 是否过期
			tokenCache.evict(opentoken);// 过期删除
			return true;
		}
		return false;
	}

}
