package com.kjplus.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.ybk.basic.util.DateUtil;
import org.ybk.basic.util.Util;

import com.kjplus.constant.Constant;
import com.kjplus.constant.ShareConstant;
import com.kjplus.constant.UploadConstant;
import com.kjplus.dto.IDNameDto;
import com.kjplus.dto.MblSrvPackageDto;
import com.kjplus.dto.ServAsgnDto;
import com.kjplus.dto.ServAsgnPackageDto;
import com.kjplus.eto.FileRepoEto;
import com.kjplus.eto.SrvAsgnEto;
import com.kjplus.model.AdminUserEbo;
import com.kjplus.model.DepartmentEbo;
import com.kjplus.model.DocumentInfoEbo;
import com.kjplus.model.ServAsgnEbo;
import com.kjplus.model.StaffDeptEbo;
import com.kjplus.model.inner.DocumentInfoInnerEbo;
import com.kjplus.model.inner.FileRepoInnerEbo;
import com.kjplus.model.inner.ServicePackageInnerEbo;
import com.kjplus.service.IDeptService;
import com.kjplus.service.IDocInfoService;
import com.kjplus.service.IFileRepoService;
import com.kjplus.service.IServiceAssignService;
import com.kjplus.service.IServicePackageService;
import com.kjplus.service.IStaffService;
import com.kjplus.service.IUserMapService;
import com.ybk.exception.DataException;

/**
 * 签约记录列表 添加签约
 * 
 * @author songyuebin
 * 
 */
@Api(tags = "签约", description = "关于签约的接口")
@Controller
public class SrvAssignCtrl {

	@Autowired
	private IServiceAssignService srvAssignService;
	@Autowired
	private IUserMapService userMapService;
	@Autowired
	private IStaffService staffService;
	@Autowired
	private IDeptService deptService;
	@Autowired
	private IServicePackageService srvPackageService;
	@Autowired
	private IDocInfoService docInfoService;
	@Autowired
	private IFileRepoService fileRepoService;

	private static Hashtable<String, String> DIRS = new Hashtable<String, String>();

	// 获取签约记录 (签约管理页面信息) 获取该组织所有的签约记录
	@ApiOperation(value = "获取签约记录 (签约管理页面信息) 获取该组织所有的签约记录")
	@ApiImplicitParams({ @ApiImplicitParam(name = "page", value = "分页", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "paging", value = "分页", required = true, dataType = "Integer", paramType = "query") })
	@RequestMapping(value = Constant.USER_CHECK_URI + "/listsrvassign.html", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> listSrvAssign(HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);
		AdminUserEbo userEbo = (AdminUserEbo) req.getSession().getAttribute(Constant.KJAPI_SESS);
		if (userEbo == null) {
			map.put("result", ShareConstant.RES_UNLOGIN);
			map.put("message", "用户没有登录!无法获取签约记录");
			return map;
		}
		int page = Util.parseNumVl(req.getParameter("page"), 0);
		int paging = Util.parseNumVl(req.getParameter("paging"), 10);
		try {

			List<DocumentInfoInnerEbo> docList = new ArrayList<DocumentInfoInnerEbo>();//docInfoService.listDocInfoInner(userEbo.getOrgid(), null, null, Constant.FLAG_YES, null, null, true, page, paging);
			List<Map<String, Object>> srvAsgnData = new ArrayList<Map<String, Object>>();
			for (DocumentInfoInnerEbo doc : docList) {
				Map<String, Object> ms = new HashMap<String, Object>();
				ms.put("code", doc.getPrsnCode());// 用户code
				ms.put("prsnName", doc.getPrsnName());
				List<ServAsgnDto> srvAssigns = doc.getSrvAssigns();
				// 有记录 同时不是拒签状态
				boolean isAssign = srvAssigns.size() > 0 && !(Util.val(srvAssigns.get(0).getStatus()).equals(ShareConstant.SRV_ASSIGN_STATUS_REFUSE));
				if (isAssign) {
					ServAsgnDto servAsgnDto = srvAssigns.get(0);
					ms.put("staffName", Util.val(servAsgnDto.getStafName()));// 签约医生
					ms.put("depName", Util.val(servAsgnDto.getDeptName()));// 签约团队
					List<Map<String, Object>> listPack = new ArrayList<Map<String, Object>>();
					for (ServAsgnPackageDto p : servAsgnDto.getListPackage()) {
						Map<String, Object> m = new HashMap<String, Object>();
						m.put("assignCode", p.getCode());// 具体签约记录
						m.put("packageName", p.getSrvName());
						m.put("packagePrice", p.getSrvPrice());
						m.put("alias", p.getSrvAlias());
						listPack.add(m);
					}
					ms.put("packages", listPack);
					if (Util.val(servAsgnDto.getStatus()).equals(ShareConstant.SRV_ASSIGN_STATUS_APPLY))// 待审核
						ms.put("status", ShareConstant.PRSN_ASSIGN_STATUS_APPLY);
					else if (Util.val(servAsgnDto.getStatus()).equals(ShareConstant.SRV_ASSIGN_STATUS_AGREE))// 有效
						ms.put("status", ShareConstant.PRSN_ASSIGN_STATUS_YES);
					/*
					 * else if
					 * (Util.val(servAsgnDto.getStatus()).equals(ShareConstant
					 * .SRV_ASSIGN_STATUS_ASK))// 已邀约 ms.put("status",
					 * ShareConstant.PRSN_ASSIGN_STATUS_ASK);
					 */
				} else {// 无有效签约记录
					ms.put("staffName", "");// 签约医生
					ms.put("depName", "");// 签约团队
					List<Map<String, Object>> listPack = new ArrayList<Map<String, Object>>();
					ms.put("packages", listPack);
					ms.put("status", ShareConstant.PRSN_ASSIGN_STATUS_NO);
				}
				srvAsgnData.add(ms);
			}
			int total = 0;//docInfoService.getTotalDocInfoInner(userEbo.getOrgid(), null, null, Constant.FLAG_YES, null, null);
			map.put("data", srvAsgnData);
			map.put("total", total);
			map.put("message", "成功获取签约记录列表数据");
			map.put("result", ShareConstant.RES_OK);
		} catch (Exception e) {
			map.put("status", 0);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 获取该组织居民服务包列表
	@ApiOperation(value = "获取该组织居民服务包列表")
	@RequestMapping(value = Constant.USER_CHECK_URI + "/listservicepackage.html", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> listSrvPackage(HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);
		AdminUserEbo userEbo = (AdminUserEbo) req.getSession().getAttribute(Constant.KJAPI_SESS);
		if (userEbo == null) {
			map.put("result", ShareConstant.RES_UNLOGIN);
			map.put("message", "用户没有登录!无法获取居民服务包");
			return map;
		}
		try {

			// 获取该组织的服务列表
			List<ServicePackageInnerEbo> listPackage = srvPackageService.listSrvPackageInner(null, userEbo.getOrgid(), Constant.FLAG_YES, null, null, 0, null, null, 0, -1);
			List<MblSrvPackageDto> listPackageData = new ArrayList<MblSrvPackageDto>();
			for (ServicePackageInnerEbo p : listPackage) {
				MblSrvPackageDto srv = new MblSrvPackageDto();
				srv.setCode(p.getCode());
				srv.setName(p.getName());
				srv.setAlias(p.getAlias());
				srv.setMemo(p.getMemo());
				srv.setIsDefault(p.getIsDefault());
				listPackageData.add(srv);
			}
			map.put("listPackageData", listPackageData);
			map.put("message", "获取居民服务包成功！");
			map.put("result", ShareConstant.RES_OK);
			return map;
		} catch (DataException e) {
			map.put("status", ShareConstant.RES_ERROR);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 获取具体某个人最新签约状态（已签约、未签约）
	@ApiOperation(value = "获取具体某个人最新签约状态（已签约、未签约）")
	@ApiImplicitParams({ @ApiImplicitParam(name = "prsnCode", value = "人员编码", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = Constant.USER_CHECK_URI + "/getserviceassignbyprsn.html", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getSrvAssignByPrsn(HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);
		AdminUserEbo userEbo = (AdminUserEbo) req.getSession().getAttribute(Constant.KJAPI_SESS);
		if (userEbo == null) {
			map.put("result", ShareConstant.RES_UNLOGIN);
			map.put("message", "用户没有登录!无法获取该居民签约记录");
			return map;
		}
		String prsnCode = req.getParameter("prsnCode");

		try {
			DocumentInfoEbo doc = docInfoService.getDocinfoByIdOrCode(0, prsnCode);
			if (doc == null) {
				map.put("result", ShareConstant.RES_UNLOGIN);
				map.put("message", "该居民档案不存在！");
				return map;
			}
			/*
			 * 获取该用户最新的预约记录 (筛选按最新时间) 包括：已签约,待审核,拒签
			 */
			List<ServAsgnDto> listSrv = srvAssignService.listAsgnDto(null, doc.getPrsnId(), null, 0, null, userEbo.getOrgid(), null, true, true, 0, -1);

			Map<String, Object> sm = new HashMap<String, Object>();

			if (listSrv.size() > 0) {
				for (ServAsgnDto s : listSrv) {
					if (s.getStatus().equals(ShareConstant.SRV_ASSIGN_STATUS_REFUSE)) {// 获取的数据时拒签数据
																						// 不处理
						continue;
					}
					// 如果已签约 和 待审核只会有一条数据
					sm.put("staffName", s.getStafName());
					sm.put("deptName", s.getDeptName());
					if (s.getStatus().equals(ShareConstant.SRV_ASSIGN_STATUS_APPLY))// 待审核
						sm.put("status", ShareConstant.PRSN_ASSIGN_STATUS_APPLY);
					if (s.getStatus().equals(ShareConstant.SRV_ASSIGN_STATUS_AGREE))// 已同意
						sm.put("status", ShareConstant.PRSN_ASSIGN_STATUS_YES);
					List<Map<String, Object>> listPack = new ArrayList<Map<String, Object>>();
					for (ServAsgnPackageDto p : s.getListPackage()) {
						Map<String, Object> m = new HashMap<String, Object>();
						m.put("assignCode", p.getCode());// 具体签约记录
						m.put("packageName", p.getSrvName());
						m.put("packagePrice", p.getSrvPrice());
						m.put("alias", p.getSrvAlias());
						listPack.add(m);
					}
					sm.put("packages", listPack);
				}
			}
			if (sm.isEmpty()) {// 保持数据一致
				sm.put("staffName", "");
				sm.put("deptName", "");
				sm.put("status", ShareConstant.PRSN_ASSIGN_STATUS_NO);// 未签约
				List<Map<String, Object>> listPack = new ArrayList<Map<String, Object>>();
				sm.put("packages", listPack);
			}
			map.put("data", sm);
			map.put("message", "获取该居民最新签约记录成功！");
			map.put("result", ShareConstant.RES_OK);
		} catch (DataException e) {
			map.put("status", ShareConstant.RES_ERROR);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 获取具体某个人的签约记录
	@ApiOperation(value = "获取具体某个人的签约记录")
	@ApiImplicitParams({ @ApiImplicitParam(name = "prsnCode", value = "人员编码", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "page", value = "分页", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "paging", value = "分页", required = true, dataType = "Integer", paramType = "query") })
	@RequestMapping(value = Constant.USER_CHECK_URI + "/listserviceassignbyprsn.html", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> listSrvAssignByPrsn(HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);
		AdminUserEbo userEbo = (AdminUserEbo) req.getSession().getAttribute(Constant.KJAPI_SESS);
		if (userEbo == null) {
			map.put("result", ShareConstant.RES_UNLOGIN);
			map.put("message", "用户没有登录!无法获取该居民签约记录");
			return map;
		}
		String prsnCode = req.getParameter("prsnCode");
		int page = Util.parseNumVl(req.getParameter("page"), 0);
		int paging = Util.parseNumVl(req.getParameter("paging"), 10);

		try {
			DocumentInfoEbo doc = docInfoService.getDocinfoByIdOrCode(0, prsnCode);
			if (doc == null) {
				map.put("result", ShareConstant.RES_UNLOGIN);
				map.put("message", "该居民档案不存在！");
				return map;
			}
			// 查询曾经有效的签约记录 （status=“Y”）
			List<ServAsgnDto> ser = srvAssignService.listAsgnDto(null, doc.getPrsnId(), null, 0, null, userEbo.getOrgid(), Constant.FLAG_YES, false, true, page, paging);

			List<Map<String, Object>> srvAssgnData = new ArrayList<Map<String, Object>>();

			for (ServAsgnDto s : ser) {
				Map<String, Object> sm = new HashMap<String, Object>();
				sm.put("staffName", s.getStafName());
				sm.put("deptName", s.getDeptName());
				if (s.getStatus().equals(ShareConstant.SRV_ASSIGN_STATUS_APPLY))// 待审核
					sm.put("status", ShareConstant.PRSN_ASSIGN_STATUS_APPLY);
				if (s.getStatus().equals(ShareConstant.SRV_ASSIGN_STATUS_AGREE))// 已同意
					sm.put("status", ShareConstant.PRSN_ASSIGN_STATUS_YES);
				List<Map<String, Object>> listPack = new ArrayList<Map<String, Object>>();
				for (ServAsgnPackageDto p : s.getListPackage()) {
					Map<String, Object> m = new HashMap<String, Object>();
					m.put("assignCode", p.getCode());// 具体签约记录
					m.put("packageName", p.getSrvName());
					m.put("packagePrice", p.getSrvPrice());
					m.put("alias", p.getSrvAlias());
					listPack.add(m);
				}
				sm.put("packages", listPack);
				boolean isTime = DateUtil.getDateInSecond(s.getEndDay()) > DateUtil.getDateInSecond(DateUtil.newTime());
				if (isTime)
					sm.put("time", DateUtil.getDateStr(s.getBeginDay()));
				else
					sm.put("time", DateUtil.getDateStr(s.getEndDay()));
				srvAssgnData.add(sm);
			}
			int total = srvAssignService.getTotalServAss(null, doc.getPrsnId(), null, 0, null, userEbo.getOrgid(), Constant.FLAG_YES, false, true);
			map.put("total", total);
			map.put("data", srvAssgnData);
			map.put("message", "获取该居民签约记录成功！");
			map.put("result", ShareConstant.RES_OK);
		} catch (DataException e) {
			map.put("status", ShareConstant.RES_ERROR);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 添加签约记录
	@ApiOperation(value = "获取具体某个人的签约记录")
	@ApiImplicitParams({ @ApiImplicitParam(name = "prsnCode", value = "申请人", required = true, dataType = "String", paramType = "body"),
			@ApiImplicitParam(name = "srvCodes", value = "居民服务包", required = true, dataType = "String", paramType = "body"),
			@ApiImplicitParam(name = "memo", value = "备注", required = true, dataType = "String", paramType = "body") })
	@RequestMapping(value = Constant.USER_CHECK_URI + "/addsrvassign.html", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> addSrvAssign(HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);
		AdminUserEbo userEbo = (AdminUserEbo) req.getSession().getAttribute(Constant.KJAPI_SESS);
		if (userEbo == null) {
			map.put("result", ShareConstant.RES_UNLOGIN);
			map.put("message", "用户没有登录!无法获取签约记录");
			return map;
		}
		SrvAsgnEto servAsgn = new SrvAsgnEto();
		String prsnCode = req.getParameter("prsnCode");// 申请人
		servAsgn.setPrsnCode(prsnCode);
		String srvCodes = req.getParameter("srvCodes");// 居民服务包
		List<String> listPackageCode = new ArrayList<String>();

		if (Util.isNull(srvCodes)) {
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", "请选定服务包！");
			return map;
		} else {
			listPackageCode = Util.str2Array(srvCodes);
		}
		servAsgn.setListPackageCode(listPackageCode);
		// 备注
		String memo = req.getParameter("memo");
		servAsgn.setMemo(memo);
		try {
			// 医生
			int staffId = userMapService.getStaffIdByAdminUserId(userEbo.getUid());
			servAsgn.setStaffId(staffId);
			String depCode = "";
			// 获取医生所在团队
			List<StaffDeptEbo> listStaffDep = staffService.listStaffDeptMapEbo(staffId, 0);
			if (listStaffDep.size() <= 0) {
				map.put("message", "该医生未加入团队，无法签约");
				map.put("result", ShareConstant.RES_ERROR);
				return map;
			}
			DepartmentEbo dep = deptService.getDepartmentById(listStaffDep.get(0).getDeptId());
			if (dep != null)
				depCode = dep.getCode();
			servAsgn.setDeptCode(depCode);
			// 添加签约记录
			srvAssignService.addServAsgn(servAsgn);
			map.put("message", "添加签约数据成功");
			map.put("result", ShareConstant.RES_OK);
		} catch (DataException e) {
			map.put("status", ShareConstant.RES_ERROR);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 解约
	@ApiOperation(value = "解约")
	@ApiImplicitParams({ @ApiImplicitParam(name = "assignCodes", value = "签约", required = true, dataType = "String", paramType = "body"),
			@ApiImplicitParam(name = "status", value = "状态", required = true, dataType = "String", paramType = "body"),
			@ApiImplicitParam(name = "memo", value = "备注", required = true, dataType = "String", paramType = "body") })
	@RequestMapping(value = Constant.USER_CHECK_URI + "/updatesrvassign.html", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> updateSrvAssign(HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);
		AdminUserEbo userEbo = (AdminUserEbo) req.getSession().getAttribute(Constant.KJAPI_SESS);
		if (userEbo == null) {
			map.put("result", ShareConstant.RES_UNLOGIN);
			map.put("message", "用户没有登录!无法获取签约记录");
			return map;
		}
		// (12121,2121,121)
		String assignCodes = req.getParameter("assignCodes");// 签约
		String status = req.getParameter("status");
		String memo = req.getParameter("memo");
		List<String> listAssignCode = new ArrayList<String>();
		listAssignCode = Util.str2Array(assignCodes);
		List<IDNameDto> idNames = new ArrayList<IDNameDto>();
		for (String s : listAssignCode) {
			IDNameDto id = new IDNameDto();
			id.setCode(s);
			idNames.add(id);
		}
		if (Util.isNull(status))// 默认解约
			status = ShareConstant.SRV_ASSIGN_STATUS_BREAK;
		try {
			// 修改
			srvAssignService.updateServAsgnStatus(idNames, status, memo);
			map.put("message", "添加签约数据成功");
			map.put("result", ShareConstant.RES_OK);
		} catch (DataException e) {
			map.put("status", ShareConstant.RES_ERROR);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 上传测试
	@ApiOperation(value = "上传测试")
	@ApiImplicitParams({ @ApiImplicitParam(name = "upload_ass", value = "返回上传测试页面", required = true, dataType = "String", paramType = "body") })
	@RequestMapping(value = "/uploadassform.html", method = RequestMethod.POST)
	public ModelAndView uploadFileForm(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("upload_ass");
		return mav;
	}

	// app端上传普通图片文件,将文件上传请求映射到该方法 签约凭证
	@ApiOperation(value = "app端上传普通图片文件,将文件上传请求映射到该方法 签约凭证")
	@RequestMapping(value = "/uploadAssignfile.html", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> uploadFollowFile(@RequestParam("file") CommonsMultipartFile mFile, @RequestParam("assCode") String assCode, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);

		if (mFile.getSize() == 0) {
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", "请选择上传文件");
			return map;
		}

		try {

			ServAsgnEbo srvAss = srvAssignService.getServAssByIdOrCode(0, assCode);
			if (srvAss == null) {
				map.put("result", ShareConstant.RES_ERROR);
				map.put("message", "该签约记录不存在！");
				return map;
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
			// String toUrl = "";
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
			fileRepoEto.setMainId(srvAss.getId());
			fileRepoEto.setMainType(ShareConstant.MAIN_TYPE_ASSIGN);
			fileRepoService.addFileRepoEbo(fileRepoEto);
			// 开始保存
			map.put("result", ShareConstant.RES_OK);
			map.put("message", "上传签约凭证成功！");
		} catch (Exception e) {
			// logger.error(e);
			// map.put("failure", true);
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", e.getMessage());
		}
		return map;
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

	// 查看签约凭证
	@ApiOperation(value = "查看签约凭证")
	@ApiImplicitParams({ @ApiImplicitParam(name = "assCodes", value = "签约编号", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/getsrvassignfile.html", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getSrvAssignFile(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);

		// 签约编号（1212,23232,2323）
		String assCodes = request.getParameter("assCodes");
		if (Util.isNull(assCodes)) {
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", "获取凭证是,签约编号不能为空");
			return map;
		}

		try {
			List<String> listAss = Util.str2Array(assCodes);
			List<String> removes = new ArrayList<String>();
			ServAsgnEbo srv = null;
			for (String s : listAss) {
				srv = new ServAsgnEbo();
				srv = srvAssignService.getServAssByIdOrCode(0, s);
				if (srv == null)
					removes.add(s);
			}
			listAss.removeAll(removes);
			if (listAss.size() < 0) {
				map.put("result", ShareConstant.RES_ERROR);
				map.put("message", "签约记录均不存在！");
				return map;
			}
			List<Map<String, Object>> assData = new ArrayList<Map<String, Object>>();
			ServAsgnEbo srv2 = null;
			for (String s : listAss) {
				srv2 = new ServAsgnEbo();
				srv2 = srvAssignService.getServAssByIdOrCode(0, s);
				List<FileRepoInnerEbo> listFile = fileRepoService.listFileRepoInner(0, srv2.getId(), ShareConstant.MAIN_TYPE_ASSIGN, null, -1, -1);
				for (FileRepoInnerEbo fl : listFile) {
					Map<String, Object> fm = new HashMap<String, Object>();
					fm.put("fileType", fl.getFileType());
					// 替换路径
					String filePath = fl.getFilePath().substring(UploadConstant.APP_FILE_DIR.length());
					StringBuffer path = new StringBuffer();
					path.append(UploadConstant.APP_HTTP_URL);
					path.append(filePath);
					fm.put("path", path);
					assData.add(fm);
				}
			}
			map.put("data", assData);
			map.put("result", ShareConstant.RES_OK);
			map.put("message", "获取签约凭证成功!");
			return map;
		} catch (DataException e) {
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", e.getMessage());
		}
		return map;
	}

}
