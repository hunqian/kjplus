package com.kjplus.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.ybk.basic.util.Util;

import com.alibaba.fastjson.JSONObject;
import com.kjplus.constant.Constant;
import com.kjplus.constant.ShareConstant;
import com.kjplus.constant.UploadConstant;
import com.kjplus.dto.ExamBloodDto;
import com.kjplus.dto.ExamGlycemicDto;
import com.kjplus.dto.ExamMainSimpleDto;
import com.kjplus.eto.ExamBloodEto;
import com.kjplus.eto.ExamGlycemicEto;
import com.kjplus.eto.ExamMainEto;
import com.kjplus.eto.FileRepoEto;
import com.kjplus.model.AdminUserEbo;
import com.kjplus.model.DocumentInfoEbo;
import com.kjplus.model.ExamMainEbo;
import com.kjplus.model.SysRefValueEbo;
import com.kjplus.model.inner.ExamMainInnerEbo;
import com.kjplus.qto.ExamQto;
import com.kjplus.service.IDocInfoService;
import com.kjplus.service.IExamService;
import com.kjplus.service.IFileRepoService;
import com.kjplus.service.ISysBaseService;
import com.kjplus.service.IUserMapService;
import com.mq.util.DateUtil;
import com.ybk.exception.DataException;

@Api(tags = "随访", description = "关于随访的接口")
@Controller
public class ExamCtrl {

	@Autowired
	private IExamService examService;
	@Autowired
	private ISysBaseService baseService;
	@Autowired
	private IUserMapService userMapService;
	@Autowired
	private IDocInfoService docInfoService;
	@Autowired
	private IFileRepoService fileRepoService;

	private static Logger logger = Logger.getLogger(FileUploadCtrl.class);

	@SuppressWarnings("unused")
	private ServletContext servletContext;

	// 实现接口中的setServletContext方法
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	private static Hashtable<String, String> DIRS = new Hashtable<String, String>();

	// 添加检查
	@ApiOperation(value = "添加检查")
	@ApiImplicitParams({ @ApiImplicitParam(name = "prsnId", value = "用户档案id", required = true, dataType = "Integer", paramType = "body"),
			@ApiImplicitParam(name = "flpeId", value = "随访Id", required = true, dataType = "Integer", paramType = "body"),
			@ApiImplicitParam(name = "examDay", value = "随访时间（天）", required = true, dataType = "Date", paramType = "body"),
			@ApiImplicitParam(name = "examTime", value = "随访时间（时间戳）", required = true, dataType = "Integer", paramType = "body"),
			@ApiImplicitParam(name = "typecode", value = "测试类型", required = true, dataType = "String", paramType = "body"),
			@ApiImplicitParam(name = "examjson", value = "检查json", required = true, dataType = "String", paramType = "body") })
	@RequestMapping(value = Constant.USER_CHECK_URI + "/addexam.html", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> addExam(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);

		// 用户自测
		AdminUserEbo userEbo = (AdminUserEbo) request.getSession().getAttribute(Constant.KJAPI_SESS);
		// 用户档案id
		int prsnId = Util.parseNumVl(request.getParameter("prsnId"), 0);
		if (prsnId <= 0) {
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", "请指定被测量人档案ID");
			return map;
		}
		int orgId = 0;
		int staffId = 0;
		// 随访Id
		int flpeId = Util.parseNumVl(request.getParameter("flpeId"), 0);
		// 随访时间（天）
		Date examDay = Util.parseDate(request.getParameter("examDay"));
		// 随访时间（时间戳）
		int examTime = Util.parseNumVl(request.getParameter("examTime"), 0);
		// 测试类型
		String typecode = request.getParameter("typecode");
		if (Util.isNull(typecode)) {
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", "请指定检查类型编码!");
			return map;
		}

		String examjson = request.getParameter("examjson");
		if (Util.isNull(examjson)) {
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", "请指定检查内容!");
			return map;
		}

		try {
			// 用户所在组织
			DocumentInfoEbo docInfo = docInfoService.getDocinfoByIdOrCode(prsnId, null);
			if (docInfo != null) {
				orgId = docInfo.getOrgId();
			}
			if (userEbo != null) {
				// 医生Id 自测默认0
				staffId = userMapService.getStaffIdByAdminUserId(userEbo.getUid());
			}

			int examTypeId = baseService.getRefVlByCode(typecode).getId();

			JSONObject jObj = JSONObject.parseObject(examjson);
			if (jObj == null) {
				map.put("result", ShareConstant.RES_ERROR);
				map.put("message", "请检查内容格式非法!");
				return map;
			}
			ExamMainEto main = new ExamMainEto();
			main.setExamTypeId(examTypeId);
			main.setFlpeId(flpeId);
			main.setPrsnId(prsnId);
			main.setOrgId(orgId);
			main.setStaffId(staffId);
			if (examDay != null)
				main.setExamDay(examDay);
			if (examTime > 0)
				main.setExamTime(examTime);
			ExamMainInnerEbo emInner = new ExamMainInnerEbo();
			if (typecode.equals(ShareConstant.TP_EAXM_BLOOD)) {
				ExamBloodEto be = new ExamBloodEto();
				BeanUtils.copyProperties(main, be);
				// 收缩血压
				double shrinkPress = Util.parseNumVl(jObj.getString(ShareConstant.EX_FD_SHRINK), 0);
				// 舒张血压
				double diastolePress = Util.parseNumVl(jObj.getString(ShareConstant.EX_FD_DIASTOLE), 0);
				// 心律
				int heartRate = Util.parseNumVl(jObj.getString(ShareConstant.EX_FD_HEART_RATE), 0);
				be.setShrinkPress(shrinkPress);
				be.setDiastolePress(diastolePress);
				be.setHeartRate(heartRate);
				emInner = examService.addExamBloodEbo(be);
			} else if (typecode.equals(ShareConstant.TP_EAXM_GLYCEMIC)) {
				ExamGlycemicEto ge = new ExamGlycemicEto();
				BeanUtils.copyProperties(main, ge);
				// 血糖
				double glycemicVal = Util.parseNumVl(jObj.getString(ShareConstant.EX_GV_GLYCEMIC_VAL), 0);
				// 检查状况
				String measureStatus = jObj.getString(ShareConstant.EX_GV_MEASURE_STATUS);
				ge.setGlycemicVal(glycemicVal);
				ge.setMeasureStatus(measureStatus);
				emInner = examService.addExamGlycemicEbo(ge);
			}
			// Map<String, Object> data = new HashMap<String, Object>();
			// data.put("emInner", emInner);
			if (emInner != null) {
				map.put("result", ShareConstant.RES_OK);
				map.put("message", "添加检查成功");
				// map.put("data", data);
				return map;
			} else {
				map.put("result", ShareConstant.RES_ERROR);
				map.put("message", "添加血压失败");
				return map;
			}
		} catch (DataException e) {
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 检查记录列表
	@ApiOperation(value = "添加检查")
	@ApiImplicitParams({ @ApiImplicitParam(name = "prsnId", value = "用户档案id", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "examTypeCode", value = "测试类型", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "page", value = "分页", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "paging", value = "分页", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "firstDay", value = "开始时间", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "lastDay", value = "结束时间", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = Constant.USER_CHECK_URI + "/listmainexam.html", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> listExamMain(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);

		AdminUserEbo userEbo = (AdminUserEbo) request.getSession().getAttribute(Constant.KJAPI_SESS);

		ExamQto examQto = new ExamQto();
		// 用户档案id
		int prsnId = Util.parseNumVl(request.getParameter("prsnId"), 0);
		examQto.setPrsnId(prsnId);
		// 组织
		int orgId = 0;
		examQto.setOrgId(orgId);
		// 医生Id 自测默认0
		int staffId = 0;
		examQto.setStaffId(staffId);
		// 测试类型
		String examTypeCode = request.getParameter("examTypeCode");
		examQto.setExamTypeCode(examTypeCode);
		int page = Util.parseNumVl(request.getParameter("page"), 0);
		int paging = Util.parseNumVl(request.getParameter("paging"), 10);
		// firstDay = "2017-12-13 23:11:20"
		String firstDay = request.getParameter("firstDay");
		examQto.setFirstTime(firstDay);
		// examQto.setFirstTime(DateUtil.parseTimeStrInSec(firstDay));
		String lastDay = request.getParameter("lastDay");
		examQto.setLastTime(lastDay);
		// examQto.setLastTime(DateUtil.parseTimeStrInSec(lastDay));
		try {
			// 自测 用户只能看自己的测量记录
			DocumentInfoEbo docInfo = docInfoService.getDocinfoByIdOrCode(prsnId, null);
			if (docInfo != null) {
				orgId = docInfo.getOrgId();
			}
			// 医生测量 只能看用户在医生组织下的测量记录
			if (userEbo != null) {
				// 医生查看情况下重新赋值 只能看医生组织的记录
				orgId = userEbo.getOrgid();
				staffId = userMapService.getStaffIdByAdminUserId(userEbo.getUid());
			}

			List<ExamMainSimpleDto> listExamInner = examService.listExamMainInnerEbo(examQto, page, paging);
			List<Map<String, Object>> examData = new ArrayList<Map<String, Object>>();
			for (ExamMainSimpleDto ex : listExamInner) {
				Map<String, Object> e = new HashMap<String, Object>();
				e.put("id", ex.getId());
				e.put("examCode", Util.val(ex.getExamCode()));
				e.put("examTypeId", Util.val(ex.getExamTypeId()));
				e.put("examTypeCode", Util.val(ex.getRefCode()));
				e.put("refTypeName", Util.val(ex.getRefTypeName()));
				e.put("flpeId", Util.val(ex.getFlpeId()));
				e.put("prsnId", Util.val(ex.getPrsnId()));
				e.put("prsnName", Util.val(ex.getPrsnName()));
				e.put("staffId", Util.val(ex.getStaffId()));
				e.put("staffName", Util.val(ex.getStaffName()));
				e.put("orgId", Util.val(ex.getOrgId()));
				e.put("orgName", Util.val(ex.getOrgName()));
				e.put("examDay", Util.val(ex.getExamDay()));
				e.put("examTime", Util.val(ex.getExamTime()));
				e.put("digest", Util.val(ex.getDigest()));
				Map<String, Object> extData = new HashMap<String, Object>();
				if (Util.val(ex.getRefCode()).equals(ShareConstant.TP_EAXM_BLOOD)) {
					ExamBloodDto bsd = (ExamBloodDto) ex.getExamObjDto();
					if (bsd != null) {
						extData.put("shrinkPress", bsd.getShrinkPress());
						extData.put("diastolePress", bsd.getDiastolePress());
						extData.put("heartRate", Util.val(bsd.getHeartRate()));
					}
				} else if (Util.val(ex.getRefCode()).equals(ShareConstant.TP_EAXM_GLYCEMIC)) {
					ExamGlycemicDto gsd = (ExamGlycemicDto) ex.getExamObjDto();
					if (gsd != null) {
						extData.put("glycemicVal", gsd.getGlycemicVal());
						extData.put("measureStatus", Util.val(gsd.getMeasureStatus()));
					}
				}
				e.put("extdata", extData);
				examData.add(e);
			}
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("list", examData);
			int total = examService.gettotalExamMain(examTypeCode, prsnId, staffId, orgId, firstDay, lastDay);
			data.put("total", total);
			map.put("data", data);
			map.put("result", ShareConstant.RES_OK);
			map.put("message", "获取测试列表成功");
			return map;
		} catch (DataException e) {
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 上传尿检图片
	@ApiOperation(value = "上传尿检图片")
	@ApiImplicitParams({ @ApiImplicitParam(name = "imgfile", value = "图片文件", required = true, dataType = "file", paramType = "form"),
			@ApiImplicitParam(name = "exmaincode", value = "尿检主表Code", required = true, dataType = "String", paramType = "body") })
	@RequestMapping(value = "/uploadurineimgfile.html", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> uploadimgfile(@RequestParam("imgfile") CommonsMultipartFile mFile, @RequestParam("exmaincode") String exMainCode, HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);

		try {

			ExamMainEbo exMain = examService.getExamMainEboByIdOrCode(0, exMainCode);
			if (exMain == null) {
				map.put("result", ShareConstant.RES_ERROR);
				map.put("message", "该检查记录不存在!");
				return map;
			} else {// 判断是否是尿检
				int exMainTypeId = exMain.getExamTypeId();
				SysRefValueEbo refVl = baseService.getRefVlById(exMainTypeId);
				if (refVl == null || !(Util.val(refVl.getCode()).equals(Constant.TP_EAXM_URINE))) {
					map.put("result", ShareConstant.RES_ERROR);
					map.put("message", "该检查记录不存在或不是尿检记录，无需传入图片!");
					return map;
				}
			}

			String filename = mFile.getOriginalFilename();
			int pos = filename.lastIndexOf(".");
			String mFileType;
			String postfix = "";
			String tofilename = null;

			if (pos < 0) {
				map.put("result", ShareConstant.RES_ERROR);
				map.put("message", "该指定文件后缀!");
				return map;
			}

			UUID uuid = UUID.randomUUID();
			tofilename = uuid.toString();
			postfix = filename.substring(pos);
			mFileType = filename.substring(pos + 1).toLowerCase();
			filename = filename.substring(0, pos);
			String path = "";
			// 判断文件类型
			if (UploadConstant.ALLOW_UPLOAD_PIC.indexOf(mFileType) < 0 && UploadConstant.ALLOW_UPLOAD_VIDEO.indexOf(mFileType) < 0 && UploadConstant.ALLOW_UPLOAD_VOICE.indexOf(mFileType) < 0) {
				map.put("msg", "上传图片格式不正确!");
				map.put("success", false);
				return map;
			}
			String filetype = null;
			List<String> ftps = null;
			if (Util.isNull(filetype)) {
				ftps = Util.str2Array(UploadConstant.ALLOW_UPLOAD_PIC, ";");
				for (String ftp : ftps) {
					if (ftp.equalsIgnoreCase(mFileType)) {
						filetype = ShareConstant.UPLOAD_TYPE_IMAGE;
						break;
					}
				}
			}

			if (Util.isNull(filetype)) {
				ftps = Util.str2Array(UploadConstant.ALLOW_UPLOAD_VIDEO, ";");
				for (String ftp : ftps) {
					if (ftp.equalsIgnoreCase(mFileType)) {
						filetype = ShareConstant.UPLOAD_TYPE_VIDEO;
						break;
					}
				}
			}
			if (Util.isNull(filetype)) {
				ftps = Util.str2Array(UploadConstant.ALLOW_UPLOAD_VOICE, ";");
				for (String ftp : ftps) {
					if (ftp.equalsIgnoreCase(mFileType)) {
						filetype = ShareConstant.UPLOAD_TYPE_VOICE;
						break;
					}
				}
			}
			path = UploadConstant.APP_FILE_DIR + "upload/" + DateUtil.currDay() + "/";

			path = Util.replaceAll(path, "\\", "/");
			String toSrc = path + tofilename + postfix;

			checkDir(path);
			mFile.getFileItem().write(new File(toSrc));

			FileRepoEto fileRepoEto = new FileRepoEto();
			fileRepoEto.setFilePath(toSrc);
			fileRepoEto.setFileType(filetype);
			fileRepoEto.setMainId(exMain.getId());
			fileRepoEto.setMainType(ShareConstant.MAIN_TYPE_URINE);
			fileRepoService.addFileRepoEbo(fileRepoEto);
			// 开始保存
			map.put("result", ShareConstant.RES_OK);
			map.put("message", "上传成功！");
		} catch (Exception e) {
			logger.error(e);
			// map.put("failure", true);
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 尿检图片上传测试
	@ApiOperation(value = "尿检图片上传测试")
	@ApiImplicitParams({ @ApiImplicitParam(name = "upload_urine", value = "返回尿检图片上传测试页面", required = true, dataType = "String", paramType = "body") })
	@RequestMapping(value = "/uploadurineform.html", method = RequestMethod.POST)
	public ModelAndView uploadForm(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("upload_urine");
		return mav;
	}

	private void checkDir(String dir) {
		if (DIRS.containsKey(dir))
			return;
		File f = new File(dir);
		if (!f.exists())
			f.mkdirs();
		DIRS.put(dir, "Y");
		return;
	}

}
