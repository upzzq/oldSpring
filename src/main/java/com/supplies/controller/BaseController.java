package com.supplies.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.supplies.vo.ActiveUserVO;

public class BaseController {
	
	public ActiveUserVO getLoginUser(){
		//从shiro session 中获取 ActiveUser
		ActiveUserVO user =  (ActiveUserVO) SecurityUtils.getSubject().getPrincipal();
		return user;
	}
}
