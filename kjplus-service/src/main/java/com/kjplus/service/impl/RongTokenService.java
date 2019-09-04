package com.kjplus.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ybk.basic.util.Util;

import com.kjplus.dao.IRongTokenDao;
import com.kjplus.dto.RongTokenDto;
import com.kjplus.model.RongTokenEbo;
import com.kjplus.model.UserEbo;
import com.kjplus.model.inner.RongTokenInnerEbo;
import com.kjplus.service.IRongTokenService;
import com.kjplus.service.IUserService;
import com.kjplus.util.DataValUtil;
import com.ybk.exception.DataException;

@Service("rongTokenService")
public class RongTokenService implements IRongTokenService {

	private static Logger logger = Logger.getLogger(RongTokenService.class);
	@Autowired
	private IRongTokenDao rongTokenDao;
	@Autowired
	private IUserService userService;

	// 获取rongToken列表
	public List<RongTokenDto> listRongToken(int uid) throws DataException {
		// 获取所有rongToken
		List<RongTokenInnerEbo> listRongToken = rongTokenDao.listRongToken(uid);
		List<RongTokenDto> listTokenDtos = new ArrayList<RongTokenDto>();
		RongTokenDto rDto = null;
		if (listRongToken != null) {
			for (RongTokenInnerEbo rongTokenEbo : listRongToken) {
				rDto = new RongTokenDto();
				rDto.setId(rongTokenEbo.getId());
				rDto.setUid(rongTokenEbo.getUid());
				rDto.setrToken(rongTokenEbo.getrToken());
				rDto.setReqTime(rongTokenEbo.getReqTime());
				rDto.setResTime(rongTokenEbo.getResTime());
				rDto.setFlag(rongTokenEbo.getFlag());
				rDto.setIsBlock(rongTokenEbo.getIsBlock());
				rDto.setIsBlack(rongTokenEbo.getIsBlack());
				rDto.setNickName(rongTokenEbo.getNickName());
				rDto.setFace(rongTokenEbo.getFace());
				listTokenDtos.add(rDto);
			}
		}
		return listTokenDtos;
	}

	// 根据uid查询指定rongToken
	public RongTokenDto getRongTokenByUid(int uid) throws DataException {
		if (uid <= 0) {
			return null;
		}
		RongTokenDto rongTokenDto = new RongTokenDto();
		RongTokenInnerEbo rongTokenByUid = rongTokenDao.getRongTokenByUid(uid);
		if (rongTokenByUid != null) {
			BeanUtils.copyProperties(rongTokenByUid, rongTokenDto);
		}
		return rongTokenDto;
	}

	// 根据uid查询指定rongToken
	public RongTokenDto getRongTokenByToken(String rongToken) throws DataException {
		if (Util.isNull(rongToken)) {
			return null;
		}
		RongTokenDto rongTokenDto = new RongTokenDto();
		RongTokenInnerEbo rongTokenByUid = rongTokenDao.getRongTokenByToken(rongToken);
		if (rongTokenByUid != null) {
			BeanUtils.copyProperties(rongTokenByUid, rongTokenDto);
		}
		return rongTokenDto;
	}

	// 为用户添加rongToken
	public RongTokenEbo addRongToken(RongTokenEbo rongTokenEbo) throws DataException {

		// 做控制验证
		DataValUtil.dataValidation(rongTokenEbo.getClass(), rongTokenEbo);

		// 判断uid不能为空并且数据库中存在该用户
		int uid = rongTokenEbo.getUid();
		if (uid <= 0) {
			return null;
		} else {
			UserEbo userById = userService.getUserById(uid);
			if (userById == null) {
				throw new DataException("该用户不存在");
			}
		}
		// 判断数据库中是否已经存在该用户的token
		RongTokenDto rongTokenByUid = getRongTokenByUid(rongTokenEbo.getUid());
		if (rongTokenByUid.getUid() != 0) {
			throw new DataException("该用户已有对应的token，不能重复添加");
		} else {
			// 为用户随机生成token
			String rongToken = getUUID();
			// 响应时间 = 当前时间 - 请求时间
			int reqTime = rongTokenEbo.getReqTime();
			int curTime = (int) System.currentTimeMillis();
			int resTime = curTime - reqTime;
			rongTokenEbo.setResTime(resTime);
			rongTokenEbo.setrToken(rongToken);
			rongTokenDao.addRongToken(rongTokenEbo);
		}

		return rongTokenEbo;
	}

	// 修改指定用户的rongToken相关信息
	public void updateRongToken(RongTokenEbo rongTokenEbo) throws DataException {

		if (rongTokenEbo.getUid() == 0) {
			return;
		}
		RongTokenDto rongTokenByUid = getRongTokenByUid(rongTokenEbo.getUid());
		if (rongTokenByUid.getUid() == 0) {
			return;
		}
		// 响应时间 = 当前时间 - 请求时间
		int reqTime = rongTokenEbo.getReqTime();
		int curTime = (int) System.currentTimeMillis();
		int resTime = curTime - reqTime;
		rongTokenEbo.setResTime(resTime);
		rongTokenDao.updateRongToken(rongTokenEbo);
	}

	// 产生永不重复的消息ID(uuid)
	private String getUUID() {
		synchronized (this) {
			// 获取uuid
			UUID uuid = UUID.randomUUID();
			// 通过正则表达式将 - 替换掉 随机生成 0 -9 的一位数字做补充 充分保证uuid的永不重复
			String rToken = uuid.toString().replaceAll("\\-", "" + Math.abs(new Random().nextInt() % 9));
			try {
				// 查询生成的token是否存在
				RongTokenDto rongTokenByUid = getRongTokenByToken(rToken);
				if (rongTokenByUid.getrToken() == null) {// 如果不存在返回
					return rToken;
				} else {// 否则递归调用本方法继续生成
					getUUID();
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return null;
	}

}
