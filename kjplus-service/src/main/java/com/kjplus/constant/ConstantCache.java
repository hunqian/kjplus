package com.kjplus.constant;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConstantCache {

	// 表单配置数据
	public static final String CACHE_TABLE_DATA = ResourceBundle.getBundle("cache", Locale.CHINA).getString(
			"cache_table_data");

	// 预约数据
	public static final String CACHE_APPOINT_DATA = ResourceBundle.getBundle("cache", Locale.CHINA).getString(
			"cache_appoint_data");

	// 组织服务列表
	public static final String CACHE_SERVICE_LIST_DATA = ResourceBundle.getBundle("cache", Locale.CHINA).getString(
			"cache_service_list_data");

	// 组织日历信心
	public static final String CACHE_CLANDLER_INFO_DATA = ResourceBundle.getBundle("cache", Locale.CHINA).getString(
			"cache_clandler_info_data");

	public static final String CACHE_USER_LIST_DATA = ResourceBundle.getBundle("cache", Locale.CHINA).getString(
			"cache_user_list_data");

}
