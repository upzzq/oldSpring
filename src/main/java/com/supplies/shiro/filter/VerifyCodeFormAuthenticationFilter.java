package com.supplies.shiro.filter;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.supplies.config.SuppliesManagerConfigurer;

public class VerifyCodeFormAuthenticationFilter extends FormAuthenticationFilter {

	@Autowired
	private SuppliesManagerConfigurer suppliesManagerConfigurer;
	private boolean yzm = true;//是否验证验证码
	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		
		if(!yzm){
			return super.onAccessDenied(request, response);
		}
		//没有验证码
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpSession session = hrequest.getSession();
		String verCode = (String)session.getAttribute("verCode");
		if(StringUtils.isBlank(verCode)){
			return super.onAccessDenied(request, response);
		}
		SavedRequest savedRequest = WebUtils.getSavedRequest(request);
		String yzm = request.getParameter("yzm");
		if(!StringUtils.isBlank(verCode) && !StringUtils.isBlank(yzm) && !verCode.equals(yzm)){
			hrequest.setAttribute("shiroLoginFailure", "randomCodeError");
			return true;
		}
		return super.onAccessDenied(request, response);
	}
	public boolean isYzm() {
		return yzm;
	}
	public void setYzm(boolean yzm) {
		this.yzm = yzm;
	}
	
	

}
