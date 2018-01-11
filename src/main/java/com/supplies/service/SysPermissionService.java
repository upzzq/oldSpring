package com.supplies.service;

import java.util.List;

import com.supplies.pojo.SysPermission;

public interface SysPermissionService {
	
	public List<SysPermission> findPermissionListByUserId(String id);
	
	public List<SysPermission> findMenuListByUserId(String id);
	
	public List<SysPermission> listPermission();
}
