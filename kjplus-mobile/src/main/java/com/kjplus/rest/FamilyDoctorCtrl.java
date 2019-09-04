package com.kjplus.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kjplus.constant.Constant;
import com.kjplus.constant.ConstantPageFtl;
import com.kjplus.constant.ConstantUrlWx;
import com.kjplus.dto.DeptStaffDto;
import com.kjplus.dto.MblAssignInfoDto;
import com.kjplus.dto.MblDoctorDto;
import com.kjplus.dto.MblMyFamilyDto;
import com.kjplus.dto.MblSrvPackageDto;
import com.kjplus.dto.PersonServiceDto;
import com.kjplus.dto.StaffDto;
import com.kjplus.dto.StaffSimpleDto;
import com.kjplus.dto.UserPersonDto;
import com.kjplus.dto.UserSess;
import com.kjplus.eto.SrvAsgnEto;
import com.kjplus.model.DepartmentEbo;
import com.kjplus.model.DocumentInfoEbo;
import com.kjplus.model.StaffEbo;
import com.kjplus.model.SysRefValueEbo;
import com.kjplus.model.inner.ServicePackageInnerEbo;
import com.kjplus.service.IDeptService;
import com.kjplus.service.IDocInfoService;
import com.kjplus.service.IServiceAssignService;
import com.kjplus.service.IServicePackageService;
import com.kjplus.service.IStaffService;
import com.kjplus.service.ISysBaseService;
import com.kjplus.service.IUserService;
import com.mq.util.Util;

@Controller
public class FamilyDoctorCtrl {

	private static Logger logger = Logger.getLogger(FamilyDoctorCtrl.class);
	private static final int orgId = 1001;
	@Autowired
	private IStaffService staffService;
	@Autowired
	private IServiceAssignService srvAssignService;
	@Autowired
	private IServicePackageService srvPackageService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IDeptService deptService;
	@Autowired
	private ISysBaseService baseService;
	@Autowired
	private IDocInfoService docInfoService;
	
	//家庭医生 页面数据
	@RequestMapping(value = ConstantUrlWx.WX_FAMILY_DOCTOR)
	public ModelAndView FamilyDoctor(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_FAMILY_DOCTOR);
		Map<String, Object> map = mav.getModelMap();
		map.put("code", 200);
		UserSess userEbo = (UserSess) request.getSession().getAttribute(Constant.KJMB_SESS);
		if (userEbo == null) {
			return new ModelAndView("redirect:" + ConstantUrlWx.WX_MBLOGIN + "?returnUrl="
					+ ConstantUrlWx.WX_FAMILY_DOCTOR);
		}

		try {

			List<UserPersonDto> userPrsns = userService.listUserPerson(userEbo.getUid(), 0, userEbo.getOrgId(),
					Constant.FLAG_YES, null);
			List<PersonServiceDto> prsnServices = userPrsns.get(0).getPrsnServices();
			// 家庭成员签约状况列表
			List<MblMyFamilyDto> listPrsnData = new ArrayList<MblMyFamilyDto>();
			for (PersonServiceDto p : prsnServices) {
				MblMyFamilyDto prsn = new MblMyFamilyDto();
				prsn.setPrsnCode(p.getPersonCode());
				prsn.setName(p.getPersonName());
				prsn.setRelationTypeName(p.getRelationTypeName());
				// TODO 未签约判断
				prsn.setStaffName(p.getStafName());
				prsn.setSrvPackageName(p.getSrvName());
				prsn.setSrvPackageAlias(p.getSrvAlias());
				prsn.setSrvStatus(p.getSrvStatus());
				listPrsnData.add(prsn);
			}

			// 返回所有部门和医生的信息
			List<DeptStaffDto> depts = staffService.listDeptStaffDto(userEbo.getOrgId(), Constant.DEPT_TYPE_GRUOP,
					false, true);
			//签约团队（页面展示时，获取第一条医生信息）
			List<MblDoctorDto> listDocData = new ArrayList<MblDoctorDto>();
			//用于团队保存医生信息
			List<MblDoctorDto> listDoc = null;
			//保存的团队信息
			MblDoctorDto doc = null;
			for (DeptStaffDto dept : depts) {
				doc = new MblDoctorDto();
				//保存团队信息
				doc.setgDeptCode(dept.getDeptCode());
				doc.setgDeptName(dept.getDeptName());
				//保存团队的医生信息
				listDoc = new ArrayList<MblDoctorDto>();
				for (StaffSimpleDto staff : dept.getStafs()) {
					MblDoctorDto d = new MblDoctorDto();
					d.setStaffCode(staff.getStafCode());
					d.setStaffName(staff.getStafName());
					d.setDeptCode(staff.getRealDeptCode());
					d.setDeptName(staff.getRealDept());
					d.setUrl(staff.getHeadIconUrl());
					SysRefValueEbo rv = baseService.getRefVlById(Util.val(staff.getStafType()));
					if(rv!=null)
						d.setTypeName(rv.getName());
					listDoc.add(d);
				}
				doc.setListDoc(listDoc);
				//　默认展示信息时　展示团队的第一个医生信息
				doc.setStaffCode(listDoc.get(0).getStaffCode());
				doc.setStaffName(listDoc.get(0).getStaffName());
				doc.setDeptCode(listDoc.get(0).getDeptCode());
				doc.setDeptName(listDoc.get(0).getDeptName());
				doc.setUrl(listDoc.get(0).getUrl());
				doc.setTypeName(listDoc.get(0).getTypeName());
				listDocData.add(doc);
			}
			//map.put("depts", depts);
			//map.put("userPsersons", userPrsns);
			map.put("listPrsnData", listPrsnData);//用户列表
			map.put("listDocData", listDocData);//可签约医生列表
			map.put("result", 1);
		} catch (Exception e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	//TODO  
	@RequestMapping(value = ConstantUrlWx.WX_DOCTOR_TEAM)
	public ModelAndView doctorTeam(HttpServletRequest request, HttpServletResponse response, int deptId) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_DOCTOR_TEAM);
		Map<String, Object> map = mav.getModelMap();
		map.put("code", 200);
		System.out.println(deptId);
		try {
			List<StaffDto> staffList = staffService.listStaffDto(orgId, deptId,null,null,null, 0, 10);
			System.out.println(staffList);
			map.put("message", "服务列表加载成功！");
			map.put("staffList", staffList);

		} catch (Exception e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	//家庭医生签约   页面数据
	@RequestMapping(value = ConstantUrlWx.WX_FAMILY_DOCTOR_APPOINT)
	public ModelAndView FamilyDoctorAppoint(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_FAMILY_DOCTOR_APPOINT);
		Map<String, Object> map = mav.getModelMap();
		map.put("code", 200);
		UserSess userEbo = (UserSess) request.getSession().getAttribute(Constant.KJMB_SESS);
		if (userEbo == null) {
			return new ModelAndView("redirect:" + ConstantUrlWx.WX_MBLOGIN + "?returnUrl="
					+ ConstantUrlWx.WX_FAMILY_DOCTOR);
		}
		
		String prsnCode = request.getParameter("prsnCode");
		String deptCode = request.getParameter("deptCode");
		String staffCode = request.getParameter("staffCode");
		//TODO 完善
		String relationTypeName = request.getParameter("relationTypeName");
		try {
			//签约信息
			MblAssignInfoDto assign = new MblAssignInfoDto();
			assign.setRelationTypeName(relationTypeName);
			DocumentInfoEbo doc = docInfoService.getDocinfoByIdOrCode(0, prsnCode);
			if(doc!=null){
				assign.setPrsnCode(doc.getCode());
				assign.setPrsnName(doc.getPrsnName());
			}
			StaffEbo staff = staffService.getStaffByCode(staffCode);	
			if(staff != null){
				assign.setStaffCode(staffCode);
				assign.setStaffName(staff.getName());
			}
			DepartmentEbo dept = deptService.getDepartmentByCode(deptCode);
			if(dept!=null){
				assign.setDeptCode(dept.getCode());
				assign.setDeptName(dept.getName());
			}
			//服务包信息
			List<ServicePackageInnerEbo> listPackage= srvPackageService.listSrvPackageInner(null, userEbo.getOrgId(), Constant.FLAG_YES,null, null, 0, null, null, 0, -1);
			List<MblSrvPackageDto> listPackageData = new ArrayList<MblSrvPackageDto>();
			for(ServicePackageInnerEbo p : listPackage){
				MblSrvPackageDto srv = new MblSrvPackageDto();
				srv.setCode(p.getCode());
				srv.setName(p.getName());
				srv.setAlias(p.getAlias());
				srv.setMemo(p.getMemo());
				srv.setIsDefault(p.getIsDefault());
				listPackageData.add(srv);
			}
			map.put("assign", assign);
			map.put("listPackageData", listPackageData);
			map.put("message", "服务列表加载成功！");

		} catch (Exception e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	@RequestMapping(value = "/service_assign.html")
	public @ResponseBody
	Map<String, Object> check(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		
		String prsnCode = request.getParameter("prsnCode");
		String deptCode = request.getParameter("deptCode");
		String staffCode = request.getParameter("staffCode");
		String serviceCode = request.getParameter("serviceCode");
		
		try {
			SrvAsgnEto srvAsgn = new SrvAsgnEto();
			srvAsgn.setPrsnCode(prsnCode);
			List<String> listPackageCode = Util.str2Array(serviceCode);
			srvAsgn.setListPackageCode(listPackageCode);
			srvAsgn.setDeptCode(deptCode);
			srvAsgn.setStafCode(staffCode);
			srvAssignService.addServAsgn(srvAsgn);
			map.put("result", 1);
			map.put("message", "申请签约成功成功！");
		} catch (Exception e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}
}
