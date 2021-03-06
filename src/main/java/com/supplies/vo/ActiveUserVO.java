package com.supplies.vo;

import java.util.List;

import com.supplies.pojo.SysPermission;

public class ActiveUserVO {

	private String userid;//用户id（主键）
	private String usercode;// 用户账号
	private String username;// 用户名称

	private List<SysPermission> menus;// 菜单
	private List<SysPermission> permissions;// 权限
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<SysPermission> getMenus() {
		return menus;
	}
	public void setMenus(List<SysPermission> menus) {
		this.menus = menus;
	}
	public List<SysPermission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<SysPermission> permissions) {
		this.permissions = permissions;
	}
	
	
}
