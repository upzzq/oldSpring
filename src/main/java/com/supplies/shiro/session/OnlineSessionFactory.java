package com.supplies.shiro.session;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;

import com.supplies.pojo.SysUserOnline;
import com.supplies.utils.IpUtils;

public class OnlineSessionFactory implements SessionFactory{

	@Override
	public Session createSession(SessionContext initData) {
		OnlineSession session = new OnlineSession();
		if(initData != null && initData instanceof WebSessionContext){
			WebSessionContext sessionContext = (WebSessionContext)initData;
			HttpServletRequest request = (HttpServletRequest) sessionContext.getServletRequest();
			if(request != null){
				session.setHost(IpUtils.getIpAddr(request));
				session.setUserAgent(request.getHeader("User-Agent"));
				session.setSystemHost(request.getLocalAddr() + ":" + request.getLocalPort());
			}
		}
		return session;
	}
	
	public Session createSession(SysUserOnline sysUserOnline){
		return sysUserOnline.getSession();
	}

}
