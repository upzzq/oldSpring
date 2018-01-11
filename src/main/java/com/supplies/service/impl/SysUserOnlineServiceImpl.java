package com.supplies.service.impl;

import org.springframework.stereotype.Service;

import com.supplies.pojo.SysUserOnline;
import com.supplies.service.SysUserOnlineService;

@Service
public class SysUserOnlineServiceImpl implements SysUserOnlineService{

	/*@Autowired
	private SysUserOnlineMapper sysUserOnlineMapper;*/
	/**
	 * 上线
	 */
	@Override
	public void online(SysUserOnline userOnline) {
		//sysUserOnlineMapper.insert(userOnline);
	}
	
	/**
	 * 下线
	 */
	@Override
	public void offline(SysUserOnline userOnline) {
		/*userOnline = sysUserOnlineMapper.selectOne(userOnline);
		if(userOnline != null){
			sysUserOnlineMapper.delete(userOnline);
		}*/
		
	}

	@Override
	public SysUserOnline findOne(SysUserOnline userOnline) {
		//return sysUserOnlineMapper.selectOne(userOnline);
		return null;
	}

}
