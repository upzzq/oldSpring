package com.supplies.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supplies.dao.SysUserMapper;
import com.supplies.pojo.SysUser;
import com.supplies.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService{
	
	private final static Logger log = Logger.getLogger(SysUserServiceImpl.class);

	@Autowired
	private SysUserMapper sysUserMapper;
	
	
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Map<String, Object>> find() {
		return null;
		//return sysUserMapper.find();
	}


	@Override
	public SysUser findByUsername(String username) {
		
		return sysUserMapper.findByUsername(username);
	}

	@Override
	public int insert(SysUser record) {
		
		return 0;
	}

	
	
	
	
	
}
