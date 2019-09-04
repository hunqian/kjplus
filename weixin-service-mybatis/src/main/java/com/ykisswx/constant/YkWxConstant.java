package com.ykisswx.constant;

public class YkWxConstant {

	//
	public static final String SYSTEM_DOMAIN = ".shuakejun.com";
	public static final String KEY_USER_NAME = "kj_u";
	public static final String KEY_USER_ID = "KJUD";
	public static final String KEY_IDENTIFYING_CODE = "KJ_CODE";
	public static final String KEY_IPADDRESS = "KJP";
	public static final String KEY_GLOBAL_USER_ID = "kj_g";
	
	//M厂商用户/P个人用户/A代理用户
	public static final String USER_TYPE_MANAFATURE = "M";// M厂商用户
	public static final String USER_TYPE_PERSON = "P";// P个人用户
	public static final String USER_TYPE_AGENT = "A";// A代理用户

	// 手机号码校验正则表达式
	public static final String MOBILE_REX = "1[3-9][0-9]{8,}";
	// 邮箱校验正则表达式
	// public static final String
	// EMAIL_REX="^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
	public static final String EMAIL_REX = "^([a-zA-Z0-9_\\-\\.]+)@[a-zA-Z0-9_\\-\\.]{1,}";

	public static final String ADMIN_LEVEL_A = "A";
	public static final String ADMIN_LEVEL_B = "B";
	public static final String ADMIN_LEVEL_C = "C";

	// 有效标志
	public static final String VALID_FLAG = "Y";
	public static final String INVALID_FLAG = "N";

	// 锁定标志
	public static final String LOCKED_FLAG = "Y";
	public static final String UNLOCKED_FLAG = "N";

	// 积分数
	public static final int CREDIT_C = 10;
	public static final int CREDIT_R = 10;
	public static final int CREDIT_S = 10;
	public static final int CREDIT_D = 10;

	// 积分类型
	public static final String CREDIT_C_TYPE = "C";
	public static final String CREDIT_R_TYPE = "R";
	public static final String CREDIT_D_TYPE = "D";
	public static final String CREDIT_S_TYPE = "S";

	// paging
	public static final int DEF_PAGING_SIZE = 15;
	public static final int MAX_PAGING_SIZE = 40;

	// 常量
	public static final String FLAG_YES = "Y";
	public static final String FLAG_NO = "N";
	public static final String FLAG_SET = "S";

	//性别
	public static final String SEX_FEMALE = "F";
	public static final String SEX_MALE = "M";
	public static final String SEX_UNKNOWN = "N";
	
	// 商品状态
	public static final String GOODS_FLAG_Y = "Y";
	public static final String GOODS_FLAG_N = "N";
	public static final String GOODS_FLAG_I = "I";
	public static final String GOODS_FLAG_P = "P";
	public static final String GOODS_FLAG_D = "D";
	public static final String GOODS_FLAG_R = "R"; // 管理员查看状态

	// 广告位状态
	public static final String PROMOTE_Y = "Y";
	public static final String PROMOTE_N = "N";
	public static final String PROMOTE_L = "L";

	// audit类型
	public static final String AUDIT_GOODS = "GDAP";

	// 刷课君网站前缀
	public static final String SHUAKEJUN_SITE_CN = "【刷课君】";
	//查找密码
	public static final String SMS_OP_FINDPASS = "FDPS";
	//用户注册
	public static final String SMS_OP_REGUSER = "UREG";
	//通知
	public static final String SMS_OP_NOTIFY = "NTFY";

	public static final String SMS_STATUS_INIT = "I";
	public static final String SMS_STATUS_SENDING = "S";
	public static final String SMS_STATUS_SUCCESS = "C";
	public static final String SMS_STATUS_ERROR = "E";
	public static final String SMS_STATUS_CHECKING = "G";
	public static final String SMS_STATUS_CHECKED = "D";

	//用户sess
	//public static final String ADMIN_SESS_USER = "admin_sess_user";
	//public static final String STUD_SESS_USER = "stud_sess_user";
	//public static final String TEACHER_SESS_USER = "teacher_sess_user";
	// 用户sess
	public static final String SESS_MEMBER = "sess_member";
	public static final String SESS_USER = "sess_member";
	//public static final String KEJUN_SESS_ADMIN_USER = "KEJUN_sess_admin_user";
	public static final String SESS_OPENID = "sess_openid";
	public static final String SESS_ACCID = "sess_accid";
	public static final String SESS_VERICODE = "sess_vericode";
		
	//短信发送类型
	//注册
	public static final String SMS_BIZ_REG = "R";
	//发送，收到约谈类型
	public static final String DATING_TYPE_TO = "T";
	public static final String DATING_TYPE_FROM = "F";
	//项目图片
	public static final String PROJECTINTO_TYPE_M = "M";
	//项目视频
	public static final String PROJECTINFO_TYPE_V = "V";
	
	//30分钟
	public static final int QLIB_ANSWER_TIME = 30*60;
	//一天
	public static final int DRAW_DAY_TIME = 24*60*60;

	//推荐最大层级
	public static final int REF_MAX_LEVEL = 3;
}
