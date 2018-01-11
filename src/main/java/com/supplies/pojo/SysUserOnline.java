package com.supplies.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.supplies.shiro.session.OnlineSession;

@Table(name="sys_user_online")
public class SysUserOnline implements Serializable {
	
	@Id
	@GeneratedValue(generator="assigned")
    private String id;

	@Column(name="user_id")
    private Long userId;

    private String username;

    private String host;

    @Column(name="system_host")
    private String systemHost;
    
    @Column(name="user_agent")
    private String userAgent;

    private String status;
    
    @Column(name="start_timestsamp")
    private Date startTimestsamp;

    @Column(name="last_access_time")
    private Date lastAccessTime;

    private Long timeout;

    private OnlineSession session;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getSystemHost() {
        return systemHost;
    }

    public void setSystemHost(String systemHost) {
        this.systemHost = systemHost;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartTimestsamp() {
        return startTimestsamp;
    }

    public void setStartTimestsamp(Date startTimestsamp) {
        this.startTimestsamp = startTimestsamp;
    }

    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

	public OnlineSession getSession() {
		return session;
	}

	public void setSession(OnlineSession session) {
		this.session = session;
	}

   
}