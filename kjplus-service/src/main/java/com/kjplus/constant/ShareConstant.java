package com.kjplus.constant;

//输出常量文件，可以给app用
public class ShareConstant {

	//连接状况
	public static final int CONNECT_STATUS = 200;//连接服务器正常
	
	//调用结果
	public static final int RES_OK = 1;
	public static final int RES_ERROR = -1;
	public static final int RES_UNLOGIN = 0;
	
	public static final String TP_DOC_TAG = "PERSON_TYPE";
	
	// 常量
	public static final String TP_EAXM_BLOOD = "RV_EXAM_BLOOD";
	public static final String TP_EAXM_GLYCEMIC = "RV_EXAM_GLYCEMIC";
	public static final String TP_EAXM_URINE = "RV_EXAM_URINE";

	//血压检查字段
	public static final String EX_FD_SHRINK = "SSY";
	public static final String EX_FD_DIASTOLE = "KZY";
	public static final String EX_FD_HEART_RATE = "XL";
	
	//血糖检查字段
	public static final String EX_GV_GLYCEMIC_VAL = "XT";
	public static final String EX_GV_MEASURE_STATUS = "MS";
	//空腹
	public static final String EX_GV_MEASURE_STATUS_K = "K";
	//餐后
	public static final String EX_GV_MEASURE_STATUS_C = "C";
	
	//表格类型
	//随访类型表格     TB_FOLLOWUP
	public static final String TB_TYPE_FOLLOWUP = "TB_FOLLOWUP";
	//诊疗类型表格（包括诊疗，转诊等） TB_TREATMENT
	public static final String TB_TYPE_TREATMENT = "TB_TREATMENT";
	
	//随访类型     F正规随访/M随机随访
	public static final String FU_TYPE_F = "F";
	public static final String FU_TYPE_M = "M";
	//随访检查类型    高血压B/糖尿病D/新生儿N
	public static final String FU_EXAMTYPE_B = "RV_FUEXAM_B";
	public static final String FU_EXAMTYPE_D = "RV_FUEXAM_D";
	public static final String FU_EXAMTYPE_N = "RV_FUEXAM_N";
	//随访结论
	public static final String FU_RES_GOOD = "RV_FURES_GOOD";
	public static final String FU_RES_CENTRE = "RV_FURES_CENTRE";
	public static final String FU_RES_BAD = "RV_FURES_BAD";

	//所有需要用户session验证的，url前面加/usck/
	public static final String USER_CHECK_URI_PREFIX = "/usck/";
	public static final String USER_CHECK_URI = "/usck";
	public static final String USER_CHECK_HEAD = "usckhead";
	public static final String USER_CHECK_HEAD_TAG = "iy5k3695783o8j8prt10aw388lhoaqzo";
	
	//随访和签约凭证        文件上传类型   P图片/V视屏/S音频
	public static final String UPLOAD_TYPE_IMAGE = "P";
	public static final String UPLOAD_TYPE_VIDEO = "V";
	public static final String UPLOAD_TYPE_VOICE = "S";
	
	//filerepo业务类型
	//随访
	public static final String MAIN_TYPE_FLP = "FLP";
	//签约凭证
	public static final String MAIN_TYPE_ASSIGN = "ASS";
	//尿检图片
	public static final String MAIN_TYPE_URINE = "URN";

	// R实体/G医疗团队/V虚拟
	public static final String DEPT_TYPE_REAL = "R";
	public static final String DEPT_TYPE_VIRTUAL = "V";
	public static final String DEPT_TYPE_GRUOP = "G";
	
	// S申请/R驳回/Y确认
	public static final String SIGNUP_STATUS_APPLVT = "S";
	public static final String SIGNUP_STATUS_REJECT = "R";
	public static final String SIGNUP_STATUS_CONFIRM = "Y";

	//电力
	public static final String ELE_MAIN_TYPE = "ELE";
	public static final String ELE_FLPE_TYPE = "P";
	
	//服务目录汇总
	public static final String REFTYPE_SRV_CATALOG = "RT_SRVCATG";
	
	//签约本身状态  拒签R/S待审核/Y已签约/B解约
	public static final String SRV_ASSIGN_STATUS_REFUSE = "R";
	public static final String SRV_ASSIGN_STATUS_APPLY = "S";
	public static final String SRV_ASSIGN_STATUS_AGREE = "Y";
	public static final String SRV_ASSIGN_STATUS_BREAK = "B";

	//用户当前签约状态  待审核S/已签约Y/未签约N/A已邀请  
	public static final String PRSN_ASSIGN_STATUS_YES = "Y";
	public static final String PRSN_ASSIGN_STATUS_APPLY = "S";
	public static final String PRSN_ASSIGN_STATUS_NO = "N";
	public static final String PRSN_ASSIGN_STATUS_ASK = "A";
	
}