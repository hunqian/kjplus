package com.kjplus.rest;

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
import org.ybk.basic.util.HttpRequestUtil;

import com.kjplus.constant.Constant;
import com.kjplus.dao.IInfoDao;
import com.kjplus.dao.IZanDao;
import com.kjplus.dto.SysRegionsDto;
import com.kjplus.eto.ZanHisEto;
import com.kjplus.model.InfoReferenceEbo;
import com.kjplus.service.IInfoService;
import com.kjplus.service.ISysRegionsService;
import com.kjplus.service.IZanService;
import com.mq.util.Util;
import com.ybk.exception.DataException;

//公共服务的操作，比如点赞，关注等
@Controller
public class CommCtrl {

	@Autowired
	private IZanDao zanDao;
	@Autowired
	private IInfoDao infoDao;
	@Autowired
	private IZanService zanService;
	@Autowired
	private IInfoService infoService;
	@Autowired
	private ISysRegionsService regionsService;
	
	private static Logger logger = Logger.getLogger(CommCtrl.class);
		
	//INFO点赞或者关注
	@RequestMapping(value = "/infozanfucusjson.html")
	public @ResponseBody Map<String, Object> commzanfucusJson(HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		try{
			String zanorfocus = request.getParameter("zanorfocus");
			String inforefcode = request.getParameter("inforefcode");
			if(Util.isNull(inforefcode) || Util.isNull(zanorfocus)){
				map.put("result", -1);
				map.put("message", "请指定资讯码和点赞关注类型!");
				return map;
			}
			InfoReferenceEbo ref = infoService.getReferenceByIdOrCode(0, inforefcode);
			if(ref == null){
				map.put("result", -1);
				map.put("message", "请资讯码对应的资讯不存在!");
				return map;
			}
			zanorfocus = zanorfocus.toUpperCase().substring(0,1);
			//暂时为uid
			int uid = 1;
			
			boolean res = false;
			ZanHisEto zan = new ZanHisEto();
			zan.setMainId(ref.getId());
			if("Z".equals(zanorfocus))
				zan.setMainType(Constant.ZAN_INFO_REF);
			else if("F".equals(zanorfocus))
				zan.setMainType(Constant.FOCUS_INFO_REF);
			zan.setUid(uid);
			zan.setIpAddr(HttpRequestUtil.getLocalIp(request));
			res = zanService.zan(zan);
			//根据返回的结果进行处理
			if("Z".equals(zanorfocus)){
				if(res)
					infoDao.changeReferZanFocusView(ref.getId(), Constant.FLAG_YES, null, null);
				else
					infoDao.changeReferZanFocusView(ref.getId(), Constant.FLAG_NO, null, null);
			}else if("F".equals(zanorfocus)){
				if(res)
					infoDao.changeReferZanFocusView(ref.getId(), null, Constant.FLAG_YES, null);
				else
					infoDao.changeReferZanFocusView(ref.getId(), null, Constant.FLAG_NO, null);
			}
			
			zan = new ZanHisEto();
			zan.setMainId(ref.getInfoId());
			if("Z".equals(zanorfocus))
				zan.setMainType(Constant.ZAN_INFO);
			else if("F".equals(zanorfocus))
				zan.setMainType(Constant.FOCUS_INFO);
			zan.setUid(uid);
			zan.setIpAddr(HttpRequestUtil.getLocalIp(request));
			res = zanService.zan(zan);
			//根据返回的结果进行处理
			if("Z".equals(zanorfocus)){
				if(res)
					infoDao.changeInfoZanFocusView(ref.getInfoId(), Constant.FLAG_YES, null, null);
				else
					infoDao.changeInfoZanFocusView(ref.getInfoId(), Constant.FLAG_NO, null, null);
			}else if("F".equals(zanorfocus)){
				if(res)
					infoDao.changeInfoZanFocusView(ref.getInfoId(), null, Constant.FLAG_YES, null);
				else
					infoDao.changeInfoZanFocusView(ref.getInfoId(), null, Constant.FLAG_NO, null);
			}
			//点赞关注成功
			if(res)
				map.put("data", 1);
			else
				map.put("data", -1);
			map.put("result", 1);
			map.put("message", "点赞或者关注成功!");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	//列表行政区域
	@RequestMapping(value = "/listregions.html")
	public @ResponseBody Map<String, Object> listRegionsJson(HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		try{
			
			int pid = Util.parseNumVl(request.getParameter("pid"),0);
			List<SysRegionsDto> regions = regionsService.listRegions(pid);
			map.put("data", regions);
			map.put("result", 1);
			map.put("message", "获取行者区域成功!");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}	
}
