package com.supplies.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.supplies.pojo.SysUser;

public interface SysUserService  extends DisposableBean,InitializingBean {
	
	public List<Map<String, Object>> find();
	
	
	public SysUser findByUsername(String username);
	
	int insert(SysUser record);
}
