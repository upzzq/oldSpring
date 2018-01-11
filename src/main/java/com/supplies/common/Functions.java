package com.supplies.common;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;

import com.supplies.vo.ActiveUserVO;

public class Functions {
	
	public static String principal(Session session){
		PrincipalCollection principalCollection = (PrincipalCollection)session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
		ActiveUserVO user = (ActiveUserVO)principalCollection.getPrimaryPrincipal();
		return user.getUsername();
	}
}
