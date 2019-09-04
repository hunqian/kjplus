package com.kjplus.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ybk.basic.util.DateUtils;
import org.ybk.basic.util.Util;

import com.kjplus.constant.Constant;
import com.kjplus.constant.ShareConstant;
import com.kjplus.constant.UploadConstant;
import com.kjplus.dto.ExamBloodDto;
import com.kjplus.dto.ExamDataDto;
import com.kjplus.dto.ExamGlycemicDto;
import com.kjplus.dto.FollowupMainDto;
import com.kjplus.eto.FollowupMainEto;
import com.kjplus.eto.FollowupResEto;
import com.kjplus.model.AdminUserEbo;
import com.kjplus.model.FollowupMainEbo;
import com.kjplus.model.SysRefValueEbo;
import com.kjplus.model.TableCfgHeadEbo;
import com.kjplus.model.inner.FileRepoInnerEbo;
import com.kjplus.qto.FollowupMainQto;
import com.kjplus.service.IFileRepoService;
import com.kjplus.service.IFollowupService;
import com.kjplus.service.ISysBaseService;
import com.kjplus.service.ITableService;
import com.kjplus.service.IUserMapService;
import com.ybk.exception.DataException;

@Api(tags = "随访", description = "关于随访的接口")
@Controller
public class FollowupCtrl {

	@Autowired
	private IFollowupService followupService;
	@Autowired
	private ISysBaseService baseService;
	@Autowired
	private ITableService tableService;
	@Autowired
	private IUserMapService UserMapService;
	@Autowired
	private IFileRepoService fileRepoService;

	// 获取随访
	@ApiOperation(value = "获取随访")
	@ApiImplicitParams({ @ApiImplicitParam(name = "prsnId", value = "用户档案id", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "flpeMiscType", value = "随访类型", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "flpeTypeCode", value = "随访检查类型", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "page", value = "分页", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "paging", value = "分页", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "startDay", value = "开始时间", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "finishDay", value = "结束时间", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = Constant.USER_CHECK_URI + "/listfollowup.html", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> listFollowup(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);

		AdminUserEbo userEbo = (AdminUserEbo) request.getSession().getAttribute(Constant.KJAPI_SESS);
		if (userEbo == null) {
			map.put("result", ShareConstant.RES_UNLOGIN);
			map.put("message", "用户没有登录！");
			return map;
		}

		// 用户档案id
		int prsnId = Util.parseNumVl(request.getParameter("prsnId"), 0);

		// 用户组织
		int orgId = userEbo.getOrgid();

		// 随访类型 F正规随访/M随机随访
		String flpeMiscType = request.getParameter("flpeMiscType");

		// 随访检查类型 高血压B/糖尿病D/新生儿N 定义成常量
		String flpeTypeCode = request.getParameter("flpeTypeCode");
		int page = Util.parseNumVl(request.getParameter("page"), 0);
		int paging = Util.parseNumVl(request.getParameter("paging"), 10);
		String startDay = request.getParameter("startDay");

		String finishDay = request.getParameter("finishDay");

		// 随访表格
		String tableCfgCode = request.getParameter("tableCfgCode");
		try {

			// 医生Id
			int staffId = UserMapService.getStaffIdByAdminUserId(userEbo.getUid());

			FollowupMainQto fu = new FollowupMainQto();
			SysRefValueEbo base = baseService.getRefVlByCode(flpeTypeCode);
			if (base != null)
				fu.setFlpeTypeId(base.getRefId());

			TableCfgHeadEbo cfgHead = tableService.getCfgHeadByCode(tableCfgCode);
			if (cfgHead != null)
				fu.setTbcfgId(cfgHead.getId());
			fu.setPrsnId(prsnId);
			fu.setOrgId(orgId);
			fu.setStaffId(staffId);
			if (flpeMiscType != null && flpeMiscType != "")
				fu.setFlpeMiscType(flpeMiscType);
			// fu.setStartDay(startDay);
			// fu.setFinishDay(finishDay);
			List<FollowupMainDto> listFollowup = followupService.listFollowupMainDto(fu, page, paging);
			List<Map<String, Object>> fuData = new ArrayList<Map<String, Object>>();
			for (FollowupMainDto fuDto : listFollowup) {
				Map<String, Object> fm = new HashMap<String, Object>();
				fm.put("prsnName", Util.val(fuDto.getPrsnName()));
				fm.put("staffName", Util.val(fuDto.getStaffName()));
				// 随访类型 F正规随访/M随机随访
				if (Util.val(fuDto.getFlpeMiscType()).equals(ShareConstant.FU_TYPE_F))
					fm.put("flpeType", "正规随访");
				else if (Util.val(fuDto.getFlpeMiscType()).equals(ShareConstant.FU_TYPE_M))
					fm.put("flpeType", "随机随访");
				else
					fm.put("flpeType", "");
				// 随访检查类型 高血压B/糖尿病D/新生儿N
				SysRefValueEbo flpeType = baseService.getRefVlById(fuDto.getFlpeTypeId());
				if (flpeType != null)
					fm.put("flpeTypeName", flpeType.getName());
				else
					fm.put("flpeTypeName", "");
				fm.put("flpeTime", DateUtils.formatDateTime(fuDto.getFlpeTime()));
				fm.put("flpeDay", DateUtils.formatDate(fuDto.getFlpeDay()));
				fm.put("code", fuDto.getCode());
				// 随访结论
				SysRefValueEbo baseRes = baseService.getRefVlById(Util.val(fuDto.getResId()));
				if (baseRes != null)
					fm.put("resTypeName", baseRes.getName());
				else
					fm.put("resTypeName", "无");

				if (fuDto.getResMemo() != null && fuDto.getResMemo() != "")
					fm.put("resMemo", fuDto.getResMemo());
				else
					fm.put("resMemo", "");

				if (fuDto.getResStaffName() != null && fuDto.getResStaffName() != "")
					fm.put("resStaffName", fuDto.getResStaffName());
				else
					fm.put("resStaffName", "");

				List<Object> examList = fuDto.getExamList();

				ExamDataDto examData = new ExamDataDto();
				// 传入初始化数据防止 传入对象属性不全
				examData.setGlycemicVal(0.0);
				examData.setMeasureStatus("");
				examData.setShrinkPress(0.0);
				examData.setDiastolePress(0.0);
				examData.setHeartRate(0);
				for (Object ex : examList) {
					if (ex instanceof ExamGlycemicDto) {
						ExamGlycemicDto exGly = (ExamGlycemicDto) ex;
						if (exGly != null) {
							examData.setGlycemicVal(exGly.getGlycemicVal());
							examData.setMeasureStatus(exGly.getMeasureStatus());
						}
					}
					if (ex instanceof ExamBloodDto) {
						ExamBloodDto exBlood = (ExamBloodDto) ex;
						if (exBlood != null) {
							examData.setShrinkPress(exBlood.getShrinkPress());
							examData.setDiastolePress(exBlood.getDiastolePress());
							examData.setHeartRate(exBlood.getHeartRate());
						}
					}
				}
				fm.put("examData", examData);
				fuData.add(fm);
			}
			int total = followupService.getTotalFollowupMain(fu);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("list", fuData);
			map.put("total", total);
			map.put("data", data);
			map.put("result", ShareConstant.RES_OK);
			map.put("message", "获取随访列表成功");
			return map;
		} catch (DataException e) {
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 添加随访记录
	@ApiOperation(value = "添加随访记录")
	@ApiImplicitParams({ @ApiImplicitParam(name = "selfCode", value = "自定义编号", required = true, dataType = "String", paramType = "body"),
			@ApiImplicitParam(name = "prsnId", value = "用户档案id", required = true, dataType = "String", paramType = "body"),
			@ApiImplicitParam(name = "tbcfgCode", value = "表格code 表常量", required = true, dataType = "String", paramType = "body"),
			@ApiImplicitParam(name = "flpeMiscType", value = "常量", required = true, dataType = "String", paramType = "body"),
			@ApiImplicitParam(name = "flpeTypeCode", value = "随访检查类型", required = true, dataType = "String", paramType = "body"),
			@ApiImplicitParam(name = "flpeTime", value = "随访时间戳", required = true, dataType = "Integer", paramType = "body"),
			@ApiImplicitParam(name = "flpeDay", value = "随访日", required = true, dataType = "Date", paramType = "body") })
	@RequestMapping(value = Constant.USER_CHECK_URI + "/addfollowupmain.html", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> addFollowupMain(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);

		AdminUserEbo userEbo = (AdminUserEbo) request.getSession().getAttribute(Constant.KJAPI_SESS);
		if (userEbo == null) {
			map.put("result", ShareConstant.RES_UNLOGIN);
			map.put("message", "用户没有登录！");
			return map;
		}

		// 自定义编号
		String selfCode = request.getParameter("selfCode");
		// 用户档案id
		int prsnId = Util.parseNumVl(request.getParameter("prsnId"), 0);
		if (prsnId <= 0) {
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", "请指定被检测人档案ID");
			return map;
		}
		// 用户组织
		int orgId = userEbo.getOrgid();
		// 医生Id
		int staffId = 0;
		// 表格code 表常量
		String tbcfgCode = request.getParameter("tbcfgCode");
		// 常量
		String flpeMiscType = request.getParameter("flpeMiscType");
		// 随访检查类型 高血压B/糖尿病D/新生儿N 常量
		String flpeTypeCode = request.getParameter("flpeTypeCode");
		// 随访时间戳
		int flpeTime = Util.parseNumVl(request.getParameter("flpeTime"), 0);
		// 随访日
		Date flpeDay = Util.parseDate(request.getParameter("flpeDay"));

		try {

			staffId = UserMapService.getStaffIdByAdminUserId(userEbo.getUid());

			FollowupMainEto fuE = new FollowupMainEto();
			TableCfgHeadEbo table = tableService.getCfgHeadByCode(tbcfgCode);
			if (table != null)
				fuE.setTbcfgId(table.getId());
			SysRefValueEbo base = baseService.getRefVlByCode(flpeTypeCode);
			if (base != null)
				fuE.setFlpeTypeId(base.getId());
			fuE.setSelfCode(selfCode);
			fuE.setPrsnId(prsnId);
			fuE.setOrgId(orgId);
			fuE.setStafffId(staffId);
			if (flpeMiscType != null && flpeMiscType != "")
				fuE.setFlpeMiscType(flpeMiscType);
			if (flpeTime > 0)
				fuE.setFlpeTime(flpeTime);
			if (flpeDay != null)
				fuE.setFlpeDay(flpeDay);
			FollowupMainEbo fuB = followupService.addFollowupMain(fuE);
			// Map<String, Object> data = new HashMap<String, Object>();
			if (fuB != null) {
				// data.put("fuB", fuB);
				map.put("result", ShareConstant.RES_OK);
				map.put("message", "添加随访成功");
				// map.put("data", data);
				return map;
			} else {
				map.put("result", ShareConstant.RES_ERROR);
				map.put("message", "添加随访失败");
				return map;
			}
		} catch (DataException e) {
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 添加或修改随访结论
	@ApiOperation(value = "添加或修改随访结论")
	@ApiImplicitParams({ @ApiImplicitParam(name = "code", value = "随访编号", required = true, dataType = "String", paramType = "body"),
			@ApiImplicitParam(name = "resTypeCode", value = "随访结论code 常量", required = true, dataType = "String", paramType = "body"),
			@ApiImplicitParam(name = "resMemo", value = "随访结论描述", required = true, dataType = "String", paramType = "body"),
			@ApiImplicitParam(name = "staffId", value = "医生Id", required = true, dataType = "Integer", paramType = "body") })
	@RequestMapping(value = "/addormodifyfollowupres.html", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> addOrModifyFollowupRes(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);

		// 随访编号
		String code = request.getParameter("code");
		if (Util.isNull(code)) {
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", "添加或修改随访结论时,随访编号不能为空");
			return map;
		}
		// 随访结论code 常量
		String resTypeCode = request.getParameter("resTypeCode");
		// 随访结论描述
		String resMemo = request.getParameter("resMemo");
		// 医生Id
		int staffId = Util.parseNumVl(request.getParameter("staffId"), 0);
		try {
			FollowupResEto fuE = new FollowupResEto();
			SysRefValueEbo base = baseService.getRefVlByCode(resTypeCode);
			if (base != null)
				fuE.setResId(base.getId());
			fuE.setCode(code);
			fuE.setResMemo(resMemo);
			fuE.setResStaffId(staffId);
			FollowupMainEbo fuB = followupService.addOrModifyFollowupRes(fuE);
			// Map<String, Object> data = new HashMap<String, Object>();
			if (fuB != null) {
				// data.put("fuB", fuB);
				map.put("result", ShareConstant.RES_OK);
				map.put("message", "添加随访结论成功");
				// map.put("data", data);
				return map;
			} else {
				map.put("result", ShareConstant.RES_ERROR);
				map.put("message", "添加随访结论失败");
				return map;
			}
		} catch (DataException e) {
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 查看随访的图片、视屏、音频
	@ApiOperation(value = "查看随访的图片、视屏、音频")
	@ApiImplicitParams({ @ApiImplicitParam(name = "code", value = "随访编号", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "fileType", value = "文件类型", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/getfollowupfile.html", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getFollowUpFile(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);

		// 随访编号
		String code = request.getParameter("code");
		if (Util.isNull(code)) {
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", "添加或修改随访结论时,随访编号不能为空");
			return map;
		}
		String fileType = request.getParameter("fileType");
		/*
		 * if (Util.isNull(fileType)) { map.put("result",
		 * ShareConstant.RES_ERROR); map.put("message", "请选择文件类型"); return map;
		 * }
		 */
		try {
			FollowupMainEbo fu = followupService.getFollowupMainByIdOrCode(0, code);
			if (fu == null) {
				map.put("result", ShareConstant.RES_ERROR);
				map.put("message", "该随访记录不存在");
				return map;
			}
			List<FileRepoInnerEbo> listFile = fileRepoService.listFileRepoInner(0, fu.getId(), ShareConstant.MAIN_TYPE_FLP, fileType, -1, -1);
			List<Map<String, Object>> fileData = new ArrayList<Map<String, Object>>();
			for (FileRepoInnerEbo fl : listFile) {
				Map<String, Object> fm = new HashMap<String, Object>();
				fm.put("fileType", fl.getFileType());
				// 替换路径
				String filePath = fl.getFilePath().substring(UploadConstant.APP_FILE_DIR.length());
				StringBuffer path = new StringBuffer();
				path.append(UploadConstant.APP_HTTP_URL);
				path.append(filePath);
				fm.put("path", path);
				fileData.add(fm);
			}
			map.put("data", fileData);
			map.put("result", ShareConstant.RES_OK);
			map.put("message", "获取随访文件成功");
			return map;
		} catch (DataException e) {
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", e.getMessage());
		}
		return map;
	}

}
