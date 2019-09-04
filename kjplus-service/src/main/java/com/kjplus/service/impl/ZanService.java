package com.kjplus.service.impl;

import java.util.*;

import com.kjplus.constant.Constant;
import com.kjplus.dto.ZanInfoParentDto;
import com.kjplus.dto.ZanInfoSonDto;
import com.kjplus.model.inner.ZanInfoInnerEbo;
import com.kjplus.model.inner.ZanStatusInnerEbo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ybk.basic.util.DateUtil;
import org.ybk.basic.util.Util;

import com.kjplus.dao.IZanDao;
import com.kjplus.eto.*;
import com.kjplus.model.*;
import com.kjplus.service.*;
import com.ybk.exception.DataException;

@Service("zanService")
public class ZanService implements IZanService {

	@Autowired
	private IZanDao zanDao;
	@Autowired
	private IInfoService infoService;

	public ZanEbo getZanByIdOrCode(int id, String zanCode) throws DataException {
		boolean isNull = id <= 0 && Util.isNull(zanCode) ? true : false;
		if (isNull)
			return null;
		return zanDao.getZanByIdOrCode(id, zanCode);
	}

	public ZanEbo addZan(int mainId, String mainType, String desc) throws DataException {
		ZanEto zanEto = new ZanEto();
		zanEto.setMainId(mainId);
		zanEto.setMainType(mainType);
		zanEto.setMainDesc(desc);
		String code = zanEto.getCode();
		ZanEbo zanEbo2 = null;
		// 产生一个32位长度
		if (Util.isNull(code)) {
			code = Util.genDigiCodeStr(ZanEto.CODE_LEN);
			zanEbo2 = getZanByIdOrCode(0, code);
			while (zanEbo2 != null) {
				code = Util.genDigiCodeStr(ZanEto.CODE_LEN);
				zanEbo2 = getZanByIdOrCode(0, code);
			}
		} else {
			zanEbo2 = getZanByIdOrCode(0, code);
			if (zanEbo2 != null)
				return zanEbo2;
		}
		ZanEbo zanEbo = new ZanEbo();
		zanEbo.setCode(code);
		BeanUtils.copyProperties(zanEto, zanEbo);
		zanDao.addZan(zanEbo);
		return zanEbo;
	}

	public boolean zan(ZanHisEto zan) throws DataException {
		// 此处可以放开匿名点赞
		/*
		 * UserEbo user = userService.getUserById(zan.getUid()); if (user ==
		 * null) throw new DataException("系统无此user用户，id为：" + zan.getUid());
		 */
		if (zan.getZanId() <= 0 && zan.getMainId() <= 0) {
			throw new DataException("[err]zanid和主业务对象id不能同时为空");
		}
		
		if (zan.getZanId() <= 0) {
			List<ZanEbo> zs = zanDao.listZanByIdAndType(zan.getMainId(), zan.getMainType());
			if (zs.size() != 0) {
				zan.setZanId(zs.get(0).getId());
			} else {
				ZanEbo z = addZan(zan.getMainId(), zan.getMainType(), "");
				zan.setZanId(z.getId());
			}
		} else {
			ZanEbo zan2 = getZanByIdOrCode(zan.getZanId(), null);
			if (zan2 == null)
				throw new DataException("系统无此zan，id为：" + zan.getZanId());
		}
		int totalZan = zanDao.getZanHisNumByUid(zan.getUid(), zan.getZanId());
		if(totalZan > 0){
			zanDao.updateZanNum(zan.getZanId(), "N");
			zanDao.delZanHis(zan.getZanId(), zan.getUid());
			return false;
		}else{
			ZanHisEbo zanHis = new ZanHisEbo();
			BeanUtils.copyProperties(zan, zanHis);
			zanHis.setZanTime(DateUtil.getCurTimeInSec());
			// 更新数量
			zanDao.updateZanNum(zan.getZanId(), "Y");
			zanDao.addZanHis(zanHis);
			return true;
		}
	}

	public String unZan(int mainId, String mainType, int uid) throws DataException {
		List<ZanEbo> zans = null;
		zans = zanDao.listZanByIdAndType(mainId, mainType);
		if(zans.size() <= 0)
            return mainType;
		for (ZanEbo zan : zans) {
			zanDao.updateZanNum(zan.getId(), "N");
			zanDao.delZanHis(zan.getId(), uid);
		}
        return mainType;
    }

    //获取点赞和关注文章的用户, String mainType
	@SuppressWarnings("unused")
	public List<ZanInfoParentDto> listZanAndFoInfo(String infoCode, String infoFlag, int uid) throws DataException{
		List<ZanInfoInnerEbo> inners=zanDao.listZanAndFoInfo(infoCode, infoFlag,  uid);
		Map<String,Object> infoMaps=new HashMap<String,Object>();
		String code=null;
		ZanInfoParentDto infoParentDto=null;
		ZanInfoSonDto infoSonDto=null;
		List<ZanInfoParentDto>  infoParentDtos=new ArrayList<ZanInfoParentDto>();
		for (ZanInfoInnerEbo inner :inners) {
			code=inner.getinfoCode();
			if(!infoMaps.containsKey(code)){
				infoParentDto=new ZanInfoParentDto();
				BeanUtils.copyProperties(inner,infoParentDto);
				infoSonDto=new ZanInfoSonDto();
				BeanUtils.copyProperties(inner,infoSonDto);
				if (infoSonDto == null)
					infoSonDto.setZanStatus(Constant.FLAG_NO);
				else
					infoSonDto.setZanStatus(Constant.FLAG_YES);
				infoParentDto.getZanItem().add(infoSonDto);
				infoParentDtos.add(infoParentDto);
				infoMaps.put(code,infoParentDto);

			}else{
				infoParentDto=(ZanInfoParentDto)infoMaps.get(code);
				infoSonDto=new ZanInfoSonDto();
				BeanUtils.copyProperties(inner,infoSonDto);
				if (infoSonDto == null)
					infoSonDto.setZanStatus(Constant.FLAG_NO);
				else
					infoSonDto.setZanStatus(Constant.FLAG_YES);
				infoParentDto.getZanItem().add(infoSonDto);
			}
		}
		return infoParentDtos;
	}
	
    //获得多个用户和zan状态
    public List<ZanStatusInnerEbo> listZanHisStatus(List<Integer> mainIds, String mainType , int uid) throws DataException {
    	List<ZanStatusInnerEbo> statuses = new ArrayList<ZanStatusInnerEbo>();
    	if(mainIds == null || mainIds.size() <= 0)
    		return statuses;
    	if(Util.isNull(mainType))
    		return statuses;
    	if(uid <= 0)
    		return statuses;
    	StringBuffer buf = new StringBuffer();
    	for(int i=0;i<mainIds.size();i++){
    		if(i != 0)
    			buf.append(",");
    		buf.append(mainIds.get(i));
    	}
    	return zanDao.listZanHisStatus(buf.toString(), mainType, uid);
    }
}
