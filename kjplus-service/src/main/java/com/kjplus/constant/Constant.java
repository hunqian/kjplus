package com.kjplus.constant;

import java.util.Locale;
import java.util.ResourceBundle;

public class Constant {

	//所有需要用户session验证的，url前面加/usck/
	public static final String USER_CHECK_URI_PREFIX = "/usck/";
	public static final String USER_CHECK_URI = "/usck";
	public static final String USER_CHECK_HEAD = "usckhead";
	public static final String USER_CHECK_HEAD_TAG = "iy5k3695783o8j8prt10aw388lhoaqzo";
	
	// 常量
	public static final String FLAG_YES = "Y";
	public static final String FLAG_NO = "N";
	public static final String FLAG_ERR = "E";
	public static final String FLAG_SET = "S";
	public static final String FLAG_DEL = "D";
	public static final String FLAG_UN = "U";
	public static final String FLAG_VALID = "V";
	public static final String FLAG_FINISH = "F";
	public static final String FLAG_PENDING = "P";
	public static final String FLAG_LOCK = "L";
	public static final String FLAG_COMPLETE = "T";
	public static final String FLAG_CANCEL = "C";
	public static final String FLAG_REFUND = "R";

	public static final Double DEFAULT_POINT = 0.0;

	// 权限类型 CITY城市权限/AREA区权限/PNAL个人
	public static final String AUTHORITY_TYPE_CITY = "CITY";
	public static final String AUTHORITY_TYPE_AREA = "AREA";
	public static final String AUTHORITY_TYPE_PERSONAL = "PNAL";

	// R实体/G医疗团队/V虚拟
	public static final String DEPT_TYPE_REAL = "R";
	public static final String DEPT_TYPE_VIRTUAL = "V";
	public static final String DEPT_TYPE_GRUOP = "G";

	// rich_content
	public static final String RICH_CONTENT_ORG = "RT_ORG";
	public static final String RICH_CONTENT_DEPT = "RT_DEPT";
	public static final String RICH_CONTENT_STAFF = "RT_STAFF";

	// 数据类型常量
	public static final String DATA_TYPE_STRING = "STRING";
	public static final String DATA_TYPE_DATE = "DATE";
	public static final String DATA_TYPE_INTEGER = "INTEGER";
	public static final String DATA_TYPE_FLOAT = "FLOAT";
	public static final String DATA_TYPE_DOUBLE = "DOUBLE";

	// R参照/T输入
	public static final String DATA_LINE_REF = "R";
	public static final String DATA_LINE_TEXT = "T";

	// 服务周期类型，Y年/Q季/M月/D天
	public static final String PERIOD_TYPE_YEAR = "Y";
	public static final String PERIOD_TYPE_SEASON = "Q";
	public static final String PERIOD_TYPE_MONTHS = "M";
	public static final String PERIOD_TYPE_SKY = "D";

	//签约本身状态  拒签R/S待审核/Y已签约/B解约
	public static final String SRV_ASSIGN_STATUS_REFUSE = "R";
	public static final String SRV_ASSIGN_STATUS_APPLY = "S";
	public static final String SRV_ASSIGN_STATUS_AGREE = "Y";
	public static final String SRV_ASSIGN_STATUS_BREAK = "B";

	// 数组 ARRAY 对象 Object
	public static final String JSON_TYPE_ARRAY = "ARRAY";
	public static final String JONG_TYPE_OBJECT = "OBJECT";

	// 部门类型
	public static final String DEPT_TYPE_VR = "R";
	public static final String DEPT_TYPE_ENTITY = "T";

	// 需要打标签的对象
	public static final String TAG_TYPE_PERSON = "P";
	public static final String TAG_TYPE_STAFF = "S";
	public static final String TAG_TYPE_DOC = "D";
		
	// 活动A/通知N/提醒R
	public static final String BIZ_TYPE_ACTIVITY = "A";
	public static final String BIZ_TYPE_INFORM = "N";
	public static final String BIZ_TYPE_REMIND = "R";

	// 日历主表 main_id 的创建类型 根据类型进行对应查找 存入
	public static final String CREATE_TYPE_ORG = "ORG";
	public static final String CREATE_TYPE_DEPT = "DEPT";
	public static final String CREATE_TYPE_STAFF = "STAFF";
	public static final String CREATE_TYPE_USER = "USER";
	public static final String CREATE_TYPE_ADMINUSER = "ADMINUSER";

	// 预约类型 S医生/O组织/D部门   注：与日历入口的使用者类型统一
	public static final String APPIONT_TYPE_ORG = "ORG";
	public static final String APPIONT_TYPE_DEPT = "DEPT";
	public static final String APPIONT_TYPE_STAFF = "STAFF";

	// S服务/M其他
	public static final String SRV_ENTRY_TYPE_SERVICE = "S";
	public static final String SRV_ENTRY_TYPE_OTHER = "M";

	// 参与人id,可能是居民/医生/医生团队/部门
	public static final String PARTICIPANT_PERSON = "P";
	public static final String PARTICIPANT_STAFF = "S";
	public static final String PARTICIPANT_STAFF_TEAM = "T";
	public static final String PARTICIPANT_DEPARMENT = "D";

	// 点赞类型
	public static final String ZAN_INFO = "ZI";
	public static final String ZAN_INFO_REF = "ZR";
	// 关注
	public static final String FOCUS_INFO = "FI";
	public static final String FOCUS_INFO_REF = "FR";

	// 聊天类型,文字T/P图片/D文档/V音频/C表情
	public static final String CHAT_TYPE_TEXT = "T";
	public static final String CHAT_TYPE_PICTURE = "P";
	public static final String CHAT_TYPE_DOC = "D";
	public static final String CHAT_TYPE_VIDEO = "V";
	public static final String CHAT_TYPE_COUNTENANACE = "C";
	// 图片类型
	public static final String PIC_TYPE_THUMBNAIL = "T";
	public static final String PIC_TYPE_ORIGINAL = "O";

	// 表情类型 静态表情 为系统默认表情 动态表情 为动态图类型
	public static final String COUNTENACE_TYPE_STATIC = "S";
	public static final String COUNTENACE_TYPE_DYNAMICS = "D";

	// 视频类型
	public static final String VEDIO_TYPE_SMALL = "S";
	public static final String VEDIO_TYPE_BIG = "B";
	
	//消息状态
	public static final String MSG_STATUS_SEND = "S";
	public static final String MSG_STATUS_DELIVERY= "A";
	public static final String MSG_STATUS_READ= "R";

	// ==========================================================================================================

	// 默认的系统orgid
	public static final int SYS_ORG_ID = 0;
	// 一天的描述
	public static final int ONE_DAY_INSEC = 60 * 60 * 60 * 24;
	// 微信发红包发送频率限制
	public static final int REDPACKET_TIME_LIMIT = 108000000;

	public static final int MINIAPP_TOKEN_TIMEOUT = 30 * 60;

	// 接送JSONG_SESS_WEIXIN
	public static final String KJMB_SESS = "KJMB_SESS";
	public static final String KJAPI_SESS = "KJAPI_SESS";
	// console的sess
	public static final String KJCONSOLE_SESS = "KJCONSOLE_SESS";
	// manager的sess
	public static final String KJMGR_SESS = "KJMGR_SESS";
	// 标识请求端身份token
	public static final String KJIA_MINIAPP_TOKEN = "opentoken";

	// 验证码，可以根据实际的需要加各自的业务后缀
	public static final String KJ_SESS_VERICODE = "KJMB_SESS_VERICODE";
	public static final String KJ_MGR_SESS_VERICODE = "KJMGR_SESS_VERICODE";
	public static final String KJ_CONSOLE_SESS_VERICODE = "KJCNSL_SESS_VERICODE";

	// 用户大类区分
	public static final String USER_TYPE_ADMIN = "A";
	public static final String USER_TYPE_MANAGER = "M";
	// 合并admin和manager类型
	public static final String USER_TYPE_CONSOLE = "C";

	// U普通用户
	public static final String USER_TYPE_UNIVERSIAL = "U";
	//医生
	public static final String USER_TYPE_STAFF = "S";
	// admin用户类型,A管理员/G普通用户
	public static final String ADMIN_USER_TYPE_GNRL = "G";
	public static final String ADMIN_USER_TYPE_ADMIN = "A";
	

	public static final String USER_TYPE_AGENT = "A";

	// 菜单type
	public static final String MENU_TYPE_CONSOLE = "A";
	public static final String MENU_TYPE_MANAGER = "M";

	// 来源标志,W微信/U自注册
	public static final String SOURCE_WEIXIN = "W";
	public static final String SOURCE_SELFREG = "U";

	// 手机号码校验正则表达式
	public static final String MOBILE_REX = "1[3-9][0-9]{8,}";
	// 邮箱校验正则表达式
	// public static final String
	// EMAIL_REX="^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
	public static final String EMAIL_REX = "^([a-zA-Z0-9_\\-\\.]+)@[a-zA-Z0-9_\\-\\.]{1,}";

	// public static final String ALL_SITE = "ALL_SITE";

	// "ALL"
	public static final String POLICY_PROD_ALL = "ALL";

	// 查找密码
	public static final String SMS_OP_FINDPASS = "FDPS";
	// 用户注册
	public static final String SMS_OP_REGUSER = "UREG";
	// 通知
	public static final String SMS_OP_NOTIFY = "NTFY";
	// 修改手机号
	public static final String SMS_OP_UPMOBILE = "UPMB";

	// 用户状态是否为锁定状态
	public static final String LOCKED_FLAG = "L";

	// 短信状态- I初始化/S开始发送/C发送成功/E发送失败
	public static final String SMS_STATUS_INIT = "I";
	public static final String SMS_STATUS_SENDING = "S";
	public static final String SMS_STATUS_SUCCESS = "C";
	public static final String SMS_STATUS_ERROR = "E";
	public static final String SMS_STATUS_CHECKED = "D";

	// 点赞DZAN关注GAEN
	public static final String DOT_ZAN = "DZAN";
	public static final String ATTENTION = "GAEN";

	public static final String READ_TYPE_VIDEO = "V";
	public static final String READ_TYPE_ARTICLES = "A";

	// 安卓key
	public static final String ADNORID_KEY = ResourceBundle.getBundle("appkey", Locale.CHINA).getString("Adnorid_key");

	// token过期时间
	public static final Integer TOKEN_OUT_TIME = Integer.valueOf(ResourceBundle.getBundle("appkey", Locale.CHINA).getString(
			"out_time_min")) * 60;

	// 缓存数量 可根据 生产环境 虚拟机 内存 进行 最大存储值调整
	public static final Integer CACHE_MAX = Integer.valueOf(ResourceBundle.getBundle("cache", Locale.CHINA)
			.getString("cache_max"));

	public static final String SPRING_INIT_XML_FILE = ResourceBundle.getBundle("web", Locale.CHINA).getString(
			"SPRING_INIT_XML_FILE");

	// 同一个用户 不同sessionCode 的顺序
	public static final Integer SESSION_SOCKET_SEQ = 1;
	
	//映射主类型
	public static final String UMFROM_MAIN_STAFF = "S";
	//对应类型，A后台管理/U普通用户
	public static final String UMTO_MAIN_USER = "U";
	public static final String UMTO_MAIN_ADMIN = "A";
	
	//电力
	public static final String ELE_MAIN_TYPE = "ELE";
	public static final String ELE_FLPE_TYPE = "P";

	//表数据用途  S存储 /C计算
	public static final String TBL_DATA_COUNT = "C";
	public static final String TBL_DATA_STORAGE = "S";
	
	//日历入口类型 类型 E对应event_type 自己定义 /A活动,cal_info对应t_info/W工作日历(医生可建立多个，但必须有且只有一个默认）/P(自己定义的日历 可以是些杂记，不对外)
	public static final String ENTRY_TYPE_EVENT = "E";
	public static final String ENTRY_TYPE_INFO = "A";
	public static final String ENTRY_TYPE_WORK = "W";
	public static final String ENTRY_TYPE_PRIVATE = "P";

	//日历使用者类型，比如ORG/DEPT/STAFF/ADMINUSER/USER
	public static final String ENTRY_USE_TYPE_ORG = "ORG";
	public static final String ENTRY_USE_TYPE_DEPT = "DEPT";
	public static final String ENTRY_USE_TYPE_STAFF = "STAFF";
	public static final String ENTRY_USE_TYPE_ADMINUSER = "ADMINUSER";
	public static final String ENTRY_USE_TYPE_USER = "USER";
	
	//预约状态  Y有效(申请中)/C确认/D拒绝/R撤销
	public static final String APPOINT_STATUS_APPLY = "Y";
	public static final String APPOINT_STATUS_CONFIRM = "C";
	public static final String APPOINT_STATUS_DECLINE = "D";
	public static final String APPOINT_STATUS_REVOKE = "R";
	
	//随访和签约凭证        文件上传类型   P图片/V视屏/S音频
	public static final String UPLOAD_TYPE_IMAGE = "P";
	public static final String UPLOAD_TYPE_VIDEO = "V";
	public static final String UPLOAD_TYPE_VOICE = "S";
	
	//filerepo业务类型
	//随访
	public static final String MAIN_TYPE_FLP = "FLP";
	//签约凭证
	public static final String MAIN_TYPE_ASSIGN = "ASS";

	//测试类型
	public static final String TP_EAXM_BLOOD = "RV_EXAM_BLOOD";
	public static final String TP_EAXM_GLYCEMIC = "RV_EXAM_GLYCEMIC";
	public static final String TP_EAXM_URINE = "RV_EXAM_URINE";

	//接种
	public static final String RV_APPOINT_IMMUNITY = "RV_APPOINT.IMMUNITY";
	
	//档案汇总数据统计人群类型
	public static final String TAG_PERSON_ET_0TO6 = "TAG_PERSON_ET";//	0-6岁
	public static final String TAG_PERSON_LNR_OVER65 = "TAG_PERSON_LNR"; //65岁以上
	public static final String TAG_PERSON_GXY = "TAG_PERSON_GXY"; //高血压
	public static final String TAG_PERSON_TNB = "TAG_PERSON_TNB";//2型糖尿病
	public static final String TAG_PERSON_YF = "TAG_PERSON_YF";//孕妇
	public static final String TAG_PERSON_CF = "TAG_PERSON_CF";//产妇
	public static final String TAG_PERSON_ZJSB = "TAG_PERSON_ZJSBCF";//严重精神障碍
	public static final String TAG_PERSON_JHB = "TAG_PERSON_JHB";//肺结核
	public static final String TAG_PERSON_CJR = "TAG_PERSON_CJR";//残疾人
	public static final String TAG_PERSON_TK = "TAG_PERSON_TK";//特困
	
	//档案操作类型
	public static final String RV_DOC_NEW = "RV_DOC.NEW";//档案新建
	public static final String RV_DOC_UPDATE = "RV_DOC.UPDATE";//档案更新
	
	//管理员、医生、普通用户默认登录密码
	public static final String DEFAULT_PASSWORD = "123456";//默认密码
	
}