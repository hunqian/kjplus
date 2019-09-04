package com.ykisswx.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.ybk.basic.util.Util;

import com.ybk.exception.DataException;
import com.ykiss.wx.api.response.OauthGetTokenResponse;
import com.ykisswx.constant.YkWxConstant;
import com.ykisswx.model.WxMemberEbo;
import com.ykisswx.service.IWxApiService;
import com.ykisswx.service.IWxMemberService;
/*import com.ykisswx.model.WxMemberEbo;
 import com.ykisswx.service.IWxApiService;
 import com.ykisswx.service.IWxMemberService;
 */
import com.ykisswx.service.IWxUserService;

public class WxBaseCtrl {

	@Autowired
	private IWxUserService wxUserService;
	@Autowired
	private IWxMemberService wxMemberService;
	@Autowired
	private IWxApiService wxApiService;

	// 刷新sess成功,返回false，未绑定成功
	public boolean refreshWxUserSess(HttpServletRequest request) throws DataException {
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		String openid = (String) request.getSession().getAttribute(YkWxConstant.SESS_OPENID);
		;

		if (Util.isNull(openid)) {
			if (Util.isNotNull(code)) {
				int accid = Util.parseNumVl(request.getParameter("accid"), 0);
				String acccode = request.getParameter("acccode");
				OauthGetTokenResponse tokenResp = wxApiService.fetchTokenResp(accid, acccode, code, state);
				openid = tokenResp.getOpenid();
				request.getSession().setAttribute(YkWxConstant.SESS_OPENID, openid);
			}
		}
		int mid = wxUserService.getMemberUserId(openid);
		if (mid > 0) {
			// 默认登陆
			WxMemberEbo member = wxMemberService.getMemberByMid(mid);
			if (member == null)
				return false;
			request.getSession().setAttribute(YkWxConstant.SESS_MEMBER, member);
			return true;
		} else
			return false;
	}
}
