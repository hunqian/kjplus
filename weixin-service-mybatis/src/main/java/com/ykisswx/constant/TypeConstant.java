package com.ykisswx.constant;

//所有的类似常量都放在此处
public class TypeConstant {
	
	//默认来源
	public static final String DEFAULT_SOURCE = "WX";
	
	//订单状态
	public static final String ORDER_YES_FLAG = "Y";			//有效
	//支付状态
	public static final String PAY_YES_FLAG = "Y";			//已支付
	public static final String PAY_NO_FLAG = "N";			//未支付
	//支付类型
	public static final String WX_PAY_CFG = "WX";	//微信支付
	public static final String ZFB_PAY_CFG = "ZFB"; //支付宝支付

	//30分钟
	public static final int QLIB_ANSWER_TIME = 30*60;
	//一天
	public static final int DRAW_DAY_TIME = 24*60*60;

	// 常量
	public static final String FLAG_YES = "Y";
	public static final String FLAG_NO = "N";
	public static final String FLAG_SET = "S";

	//代理状态,A自动审核/P待审核/Y通过审核/N取消代理
	public static final String AGENT_STATUS_AUTO = "A";
	public static final String AGENT_STATUS_PENDING = "P";
	public static final String AGENT_STATUS_YES = "Y";
	public static final String AGENT_STATUS_NO = "N";
	
	//代理类型
	public static final String AGENT_PERSON_TYPE = "P";
	public static final String AGENT_ORGANIZATION_TYPE= "O";
	
	//U待付/P已付/A申请/F已汇款,U待付->P已付->A申请->F已支付
	public static final String FEE_FLAG_UNPAID = "U";
	public static final String FEE_FLAG_PAID = "P";
	public static final String FEE_FLAG_APPIDED = "A";
	public static final String FEE_FLAG_FORWARD = "F";
	public static final String FEE_FLAG_DEL = "N";
	//收入来源
	public static final String FEE_DIRECT_TYPE = "P";
	public static final String FEE_INDIRECT_TYPE= "O";
	
	//因子计算
	public static final String FEE_CTRL_FACTOR = "F";
	//数量计算
	public static final String FEE_CTRL_REBATE = "R";
	
	//F固定时间/S顺移时间,如果是F，就看effect_frm和effect_to的值
	public static final String COUPON_TIMESFT_SHIFT = "S";
	public static final String COUPON_TIMESFT_FIXED = "F";
	
	//有效时间类型Y/M/D
	public static final String COUPON_VALIDTYPE_DAY = "D";
	public static final String COUPON_VALIDTYPE_MONTH = "M";
	public static final String COUPON_VALIDTYPE_YEAR = "Y";
	
	//A管理/B商户
	public static final String COUPON_ADMINTYPE_ADMIN = "A";
	public static final String COUPON_ADMINTYPE_BIZ = "B";
	

	//券模板类型 O其他/C消费/S分享/R注册/F返利/D点击/P促销/N新手
	public static final String COUPONTMPL_TYPE_CONSUM = "C";
	public static final String COUPONTMPL_TYPE_SHARE = "S";
	public static final String COUPONTMPL_TYPE_REGISTER = "R";
	public static final String COUPONTMPL_TYPE_FANLI = "F";
	public static final String COUPONTMPL_TYPE_DIANJI = "D";
	public static final String COUPONTMPL_TYPE_PROMOTION = "P";
	public static final String COUPONTMPL_TYPE_NEW = "N";
	public static final String COUPONTMPL_TYPE_OTHER = "O";
	
	//优惠券模板标志,Y有效/N无效/I初建/P流程/L锁定
	public static final String COUPONTMPL_FLAG_YES = "Y";
	public static final String COUPONTMPL_FLAG_NO = "N";
	public static final String COUPONTMPL_FLAG_INIT = "I";
	public static final String COUPONTMPL_FLAG_PROGRESS = "P";
	public static final String COUPONTMPL_FLAG_LOCK = "L";
	
	//优惠券标志C已消费/未消费Y/未激活N/F冻结/转赠中P/O订单锁定/发放中S
	public static final String COUPON_FLAG_CONSUM = "C";
	public static final String COUPON_FLAG_YES = "Y";
	public static final String COUPON_FLAG_NO = "N";
	public static final String COUPON_FLAG_FROZEN = "F";
	public static final String COUPON_FLAG_PRESENT = "P";
	public static final String COUPON_FLAG_ORDERLOCK = "O";
	public static final String COUPON_FLAG_SENDING = "S";
	
	//没有用户获得券S
	public static final String COUPONTMPL_CHECK_SINGLE = "S";
	public static final String COUPONTMPL_CHECK_MULTI = "M";
	
	//针对产品/商家发券
	public static final String COUPONTMPL_MAINTYPE_PROD = "P";
	public static final String COUPONTMPL_MAINTYPE_MERCHANT = "M";
	//不定
	public static final String COUPONTMPL_MAINTYPE_UNSET = "U";
	
	//Y/N是否是一次性消费
	public static final String ONEPAY_FLAG_NO = "N";
	public static final String ONEPAY_FLAG_YES = "Y";

	//会员邀请发送类型
	public static final String MEMBER_POOL_AGENTREF_SMS = "M";
	public static final String MEMBER_POOL_AGENTREF_EMAIL = "E";
	
	//类型
	public static final String MEMBER_POOL_AGENTREF_YES = "Y";
	public static final String MEMBER_POOL_AGENTREF_NO = "N";
	public static final String MEMBER_POOL_AGENTREF_ACK = "A";
	public static final String MEMBER_POOL_AGENTREF_CLICK = "C";
	
	//SNS类型
	public static final String SNS_TYPE_QQ = "Q";
	public static final String SNS_TYPE_XMLA = "M";
	public static final String SNS_TYPE_WEIBO = "W";
	
	//SNS群组类型
	//公务员
	public static final String SNS_GROUP_TYPE_GWY = "G";
	public static final String SNS_GROUP_TYPE_TEACHER = "T";
	public static final String SNS_GROUP_TYPE_OTHER = "O";
	public static final String SNS_GROUP_TYPE_UNDETERMINED = "N";
	
}
