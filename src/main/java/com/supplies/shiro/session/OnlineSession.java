package com.supplies.shiro.session;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.shiro.session.mgt.SimpleSession;

public class OnlineSession extends SimpleSession{
	private static final long serialVersionUID = -7125642695178165650L;

	private static int bitIndexCounter = 0;
	
	/**
	 * 源码复制并修改，注意命名规则
	 */
    private static final int USER_ID_BIT_MASK = 1 << bitIndexCounter++;
    private static final int USER_AGENT_BIT_MASK = 1 << bitIndexCounter ++;
    private static final int STATUS_BIT_MASK = 1 << bitIndexCounter++;
    private static final int USERNAME_BIT_MASK = 1 << bitIndexCounter++;
    private static final int REMEMBER_ME_BIT_MASK = 1 << bitIndexCounter++;
	
	public static enum OnlineStatus {
        on_line("在线"), hidden("隐身"), force_logout("强制退出");
        private final String info;

        private OnlineStatus(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }
    }
	
	
	
	public OnlineSession() {
		super();
	}

	public OnlineSession(String host) {
		super(host);
	}

	/**
	 * 当前登陆用户信息
	 */
	private Long userId = 0L;
	
	private String username;
	
	/**
	 * 用户浏览类型
	 */
	private String userAgent;
	
	/**
	 * 在线状态
	 */
	private OnlineStatus status = OnlineStatus.on_line;
	/**
	 * 用户登陆时的系统Ip
	 */
	private String systemHost;

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

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public OnlineStatus getStatus() {
		return status;
	}

	public void setStatus(OnlineStatus status) {
		this.status = status;
	}

	public String getSystemHost() {
		return systemHost;
	}

	public void setSystemHost(String systemHost) {
		this.systemHost = systemHost;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	 /**
	  * 源码复制并修改部分内容
	  * @param out
	  * @throws IOException
	  */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        short alteredFieldsBitMask = getAlteredFieldsBitMask();
        out.writeShort(alteredFieldsBitMask);
        if (userId != null) {
            out.writeObject(userId);
        }
        if (userAgent != null) {
            out.writeObject(userAgent);
        }
        if (username != null) {
            out.writeObject(username);
        }
        if (status != null) {
            out.writeObject(status);
        }
        
    }

    /**
	  * 源码复制并修改部分内容
	  * @param out
	  * @throws IOException
	  */
    @SuppressWarnings({"unchecked"})
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        short bitMask = in.readShort();

        if (isFieldPresent(bitMask, USER_ID_BIT_MASK)) {
            this.userId = (Long) in.readObject();
        }
        if (isFieldPresent(bitMask, USER_AGENT_BIT_MASK)) {
            this.userAgent = (String) in.readObject();
        }
        if (isFieldPresent(bitMask, STATUS_BIT_MASK)) {
            this.status = (OnlineStatus) in.readObject();
        }
        if (isFieldPresent(bitMask, USERNAME_BIT_MASK)) {
            this.username = (String) in.readObject();
        }
    }

    /**
     * Returns a bit mask used during serialization indicating which fields have been serialized. Fields that have been
     * altered (not null and/or not retaining the class defaults) will be serialized and have 1 in their respective
     * index, fields that are null and/or retain class default values have 0.
     *
     * @return a bit mask used during serialization indicating which fields have been serialized.
     * @since 1.0
     */
    private short getAlteredFieldsBitMask() {
        int bitMask = 0;
        bitMask = userId != null ? bitMask | USER_ID_BIT_MASK : bitMask;
        bitMask = userAgent != null ? bitMask | USER_AGENT_BIT_MASK : bitMask;
        bitMask = status != null ? bitMask | STATUS_BIT_MASK : bitMask;
        bitMask = username != null ? bitMask | USERNAME_BIT_MASK : bitMask;
        return (short) bitMask;
    }

    /**
     * Returns {@code true} if the given {@code bitMask} argument indicates that the specified field has been
     * serialized and therefore should be read during deserialization, {@code false} otherwise.
     *
     * @param bitMask      the aggregate bitmask for all fields that have been serialized.  Individual bits represent
     *                     the fields that have been serialized.  A bit set to 1 means that corresponding field has
     *                     been serialized, 0 means it hasn't been serialized.
     * @param fieldBitMask the field bit mask constant identifying which bit to inspect (corresponds to a class attribute).
     * @return {@code true} if the given {@code bitMask} argument indicates that the specified field has been
     *         serialized and therefore should be read during deserialization, {@code false} otherwise.
     * @since 1.0
     */
    private static boolean isFieldPresent(short bitMask, int fieldBitMask) {
        return (bitMask & fieldBitMask) != 0;
    }
	
	
	
}
