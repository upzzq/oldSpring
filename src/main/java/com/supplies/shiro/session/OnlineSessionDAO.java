package com.supplies.shiro.session;

import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import com.supplies.pojo.SysUserOnline;
import com.supplies.service.SysUserOnlineService;

public class OnlineSessionDAO extends EnterpriseCacheSessionDAO{

	/**
     * 上次同步数据库的时间戳
     */
    private static final String LAST_SYNC_DB_TIMESTAMP = OnlineSessionDAO.class.getName() + "LAST_SYNC_DB_TIMESTAMP";
    
    @Autowired
    private SysUserOnlineService sysUserOnlineService;
    
    @Autowired
    private OnlineSessionFactory onlineSessionFactory;
    
    /**
     * 同步session到数据库的周期 单位为毫秒（默认5分钟）
     */
    private long dbSyncPeriod = 5 * 60 * 1000;

	public long getDbSyncPeriod() {
		return dbSyncPeriod;
	}

	public void setDbSyncPeriod(long dbSyncPeriod) {
		this.dbSyncPeriod = dbSyncPeriod;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		SysUserOnline user = new SysUserOnline();
		user.setId(String.valueOf(sessionId));
		user = sysUserOnlineService.findOne(user);
		if(user == null){
			return null;
		}
		return onlineSessionFactory.createSession(user);
	}

	@Override
	protected void doDelete(Session session) {
		// TODO Auto-generated method stub
		super.doDelete(session);
	}
	
	
    
    
}
