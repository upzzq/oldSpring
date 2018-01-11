package com.supplies.service;

import com.supplies.pojo.SysUserOnline;

public interface SysUserOnlineService {

	/**
	 * 上线
	 * @param userOnline
	 */
	public void online(SysUserOnline userOnline); 
	
	/**
	 * 下线
	 */
	public void offline(SysUserOnline userOnline);
	
	public SysUserOnline findOne(SysUserOnline userOnline);
}
