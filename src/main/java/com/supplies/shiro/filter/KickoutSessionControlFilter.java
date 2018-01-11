package com.supplies.shiro.filter;

import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import com.supplies.vo.ActiveUserVO;

//访问控制拦截器
public class KickoutSessionControlFilter extends AccessControlFilter {
	
	private String kickoutUrl = getLoginUrl(); //踢出后的地址
	private boolean kickoutAfter = false; // 踢出之前/登录的之后登录的用户,默认踢出之前登录的用户
	private int maxSession = 1; //同一个账号最大会话数 默认1个
	
	private SessionManager sessionManager;
	private Cache<String, Deque<Serializable>> cache;
	
	
	
	//是否允许访问
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		return false;
	}
	
	//当访问拒绝时,是否已经处理了
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
			throws Exception {
		Subject subject = getSubject(request, response);
		
		if(!subject.isAuthenticated() && !subject.isRemembered()){
			//如果没有登录/记住我,直接进行之后的流程
			return true;
		}
		
		Session session = subject.getSession();
		ActiveUserVO user = (ActiveUserVO)subject.getPrincipal();
		Serializable sessionId = session.getId();
		//同步控制
		Deque<Serializable> deque = cache.get(user.getUsername());
		if(deque == null){
			deque = new LinkedList<Serializable>();
			cache.put(user.getUsername(), deque);
		}
		
		//如果队列里没有此sessionId,且用户没有而被踢出;放入队列
		if(!deque.contains(sessionId) && session.getAttribute("kickout") == null){
			deque.push(sessionId);
		}
		
		//如果队列里的sessionId数超出最大会话数,开始踢人
		while(deque.size() > maxSession){
			Serializable kickoutSessionId = null;
			if(kickoutAfter){ //如果踢出后者
				kickoutSessionId = deque.removeFirst();
			}else{
				//否则踢出前者
				kickoutSessionId = deque.removeLast();
			}
			
			try {
				Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
				if(kickoutSession != null){
					kickoutSession.setAttribute("kickout", true);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//如果被踢出了
		if(session.getAttribute("kickout") != null){
			//会话被踢出了
			try {
				subject.logout();
			} catch (Exception e) {
				e.printStackTrace();
			}
			saveRequest(request);
			WebUtils.issueRedirect(request, response, kickoutUrl + "?kickout=1");
			return false;
		}
		return true;
	}

	public void setKickoutUrl(String kickoutUrl) {
		this.kickoutUrl = kickoutUrl;
	}

	public void setKickoutAfter(boolean kickoutAfter) {
		this.kickoutAfter = kickoutAfter;
	}

	public void setMaxSession(int maxSession) {
		this.maxSession = maxSession;
	}

	public void setSessionManager(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cache = cacheManager.getCache("shiro-kickout-session");
	}
	
	public  Cache<String, Deque<Serializable>> getCacheManager() {
		return this.cache;
	}

	
	

}
