package com.kjplus.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.ybk.basic.util.DateUtil;

import com.kjplus.constant.Constant;
import com.kjplus.constant.ConstantPageFtl;
import com.kjplus.constant.ConstantUrlWx;
import com.kjplus.dao.IInfoDao;
import com.kjplus.dto.*;
import com.kjplus.eto.InfoReadlogEto;
import com.kjplus.model.*;
import com.kjplus.model.inner.*;
import com.kjplus.service.IInfoService;
import com.kjplus.service.IZanService;
import com.mq.util.Util;
import com.ybk.exception.DataException;

@Controller
public class InfoCtrl {

	@Autowired
	private IInfoService infoService;
	@Autowired
	private IInfoDao infoDao;
	@Autowired
	private IZanService zanService;

	private static Logger logger = Logger.getLogger(InfoCtrl.class);

	@RequestMapping(value = ConstantUrlWx.WX_HEALTH_INFORMATION)
	public ModelAndView healthInformation(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_HEALTH_INFORMATION);
		Map<String, Object> map = mav.getModelMap();
		map.put("code", 200);
		try {
			int orgid = 2;
			int catgid = Util.parseNumVl(request.getParameter("catgid"), 0);
			String flag = Constant.FLAG_YES;
			List<InfoCatalogDto> catgs = infoService.listCatalog(orgid,-1,null, flag);
			map.put("catgs", catgs);
			map.put("catgid", catgid);
			map.put("result", 1);
			map.put("message", "成功获得资讯分类");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	// 返回内容的json
	@RequestMapping(value = "/health_info_json.html")
	public @ResponseBody
	Map<String, Object> healthInformationJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		try {
			int catgid = Util.parseNumVl(request.getParameter("catgid"), 0);
			int page = Util.parseNumVl(request.getParameter("page"), 0);
			int paging = Util.parseNumVl(request.getParameter("paging"), 10);
			InfoCatalogEbo catg = infoDao.getInfoCatalogByIdOrCode(catgid, null);
			if (catg == null) {
				map.put("result", -1);
				map.put("message", "分类编码不匹配!");
				return map;
			}
			int uid = -1;
			UserSess ui = (UserSess) request.getSession().getAttribute(Constant.KJMB_SESS);
			if (ui != null)
				uid = ui.getUid();

			// 注意页面俩次便利获取相信文章的点赞信息
			List<InfoReferenceDto> infos = infoService.listInfoReference(0,catgid, catg.getOrgId(), "Y", "Y", page,
					paging);
			List<Integer> refIds = new ArrayList<Integer>();
			for (InfoReferenceDto ref : infos) {
				refIds.add(ref.getId());
			}

			// ref的
			List<ZanStatusInnerEbo> refFocuses = zanService.listZanHisStatus(refIds, Constant.FOCUS_INFO_REF, uid);
			List<ZanStatusInnerEbo> refZans = zanService.listZanHisStatus(refIds, Constant.ZAN_INFO_REF, uid);
			for (InfoReferenceDto ref : infos) {
				for (int i = 0; i < refFocuses.size(); i++) {
					if (refFocuses.get(i).getMainId().intValue() == ref.getId().intValue()) {
						ref.setMf(true);
						break;
					}
				}
			}

			for (InfoReferenceDto ref : infos) {
				for (int i = 0; i < refZans.size(); i++) {
					if (refZans.get(i).getMainId().intValue() == ref.getId().intValue()) {
						ref.setMz(true);
						break;
					}
				}
			}
  
			map.put("infos", infos);
			map.put("result", 1);
			map.put("message", "成功获得资讯分类");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 文章信息跳转
	@RequestMapping(value = "/showinfo.html")
	public ModelAndView showInfo(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("infopage");
		Map<String, Object> map = mav.getModelMap();
		map.put("code", 200);
		try {
			String refCode = request.getParameter("inforefcode");
			InfoReferenceEbo ref = infoService.getReferenceByIdOrCode(0, refCode);
			if (ref == null) {
				map.put("result", -1);
				map.put("message", "资讯码无效!");
				return mav;
			}

			InfoInnerEbo infoInner = infoService.getInfoByRef(0, refCode);
			InfoDto info = new InfoDto();
			BeanUtils.copyProperties(infoInner, info);
			info.setContent(infoService.getInfoContent(infoInner.getId()));
			
			info.setFocusNum(ref.getFocusNum());
			info.setZanNum(ref.getZanNum());
			map.put("refCode",ref.getRefCode());
			
			// 设置是否关注
			int uid = -1;
			UserSess ui = (UserSess) request.getSession().getAttribute(Constant.KJMB_SESS);
			if (ui != null)
				uid = ui.getUid();
			List<Integer> refIds = new ArrayList<Integer>();
			refIds.add(ref.getId());
			// ref的
			List<ZanStatusInnerEbo> refFocuses = zanService.listZanHisStatus(refIds, Constant.FOCUS_INFO_REF, uid);
			List<ZanStatusInnerEbo> refZans = zanService.listZanHisStatus(refIds, Constant.ZAN_INFO_REF, uid);
			if (refFocuses.size() > 0)
				info.setMf(true);
			if (refZans.size() > 0)
				info.setMz(true);

			// 各自增加一次访问
			infoDao.changeReferZanFocusView(ref.getId(), null, null, Constant.FLAG_YES);
			infoDao.changeInfoZanFocusView(infoInner.getId(), null, null, Constant.FLAG_YES);
			
		
			map.put("info", info);
			map.put("result", 1);
			map.put("message", "成功获得资讯分类");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	// 更新文章阅读时间json
	@RequestMapping(value = "/updateinforeadtimejson.html")
	public @ResponseBody
	Map<String, Object> updateInfoReadTimeJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		try {
			String refCode = request.getParameter("inforefcode");
			
			UserSess ui = (UserSess) request.getSession().getAttribute(Constant.KJMB_SESS);
			if (ui == null) {
				map.put("result", -1);
				map.put("message", "用户没有登录!");
				return map;
			}
			
			InfoReferenceEbo ref = infoService.getReferenceByIdOrCode(0, refCode);
			if (ref == null) {
				map.put("result", -1);
				map.put("message", "资讯码无效!");
				return map;
			}
			
			InfoReadlogEbo logEbo = null;
			List<InfoReadlogEbo> logs = infoDao.listReadlogEbo(ui.getUid(), ref.getInfoId(), Constant.READ_TYPE_ARTICLES);
			if(logs.size() == 0){
				InfoReadlogEto log = new InfoReadlogEto();
				log.setUid(ui.getUid());
				log.setMainCode("57798185808678814859585967521281");
				log.setMainType(Constant.READ_TYPE_ARTICLES);
				logEbo = infoService.addSimpleReadlog(log);
			}else{
				logEbo = logs.get(0);
			}
			infoService.changeReadTimeOrFlag(logEbo.getId(), null, DateUtil.getCurTimeInSec()-logEbo.getReadStartTime());
			map.put("result", 1);
			map.put("message", "成功阅读");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}
}
