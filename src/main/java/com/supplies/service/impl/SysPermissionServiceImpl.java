package com.supplies.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supplies.dao.SysPermissionMapper;
import com.supplies.pojo.SysPermission;
import com.supplies.service.SysPermissionService;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {
	
	private final static Logger log = Logger.getLogger(SysPermissionServiceImpl.class);

	@Autowired
	private SysPermissionMapper sysPermissionMapper;
	@Override
	public List<SysPermission> findPermissionListByUserId(String id) {
		
		return sysPermissionMapper.findPermissionListByUserId(id);
	}
	@Override
	public List<SysPermission> findMenuListByUserId(String id) {
		
		return sysPermissionMapper.findMenuListByUserId(id);
	}
	@Override
	public List<SysPermission> listPermission() {
		//return null;
		return sysPermissionMapper.listPermission();
	}

}
