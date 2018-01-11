package com.supplies.shiro.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.supplies.pojo.SysPermission;
import com.supplies.pojo.SysUser;
import com.supplies.service.SysPermissionService;
import com.supplies.service.SysUserService;
import com.supplies.vo.ActiveUserVO;

public class SuppliesRealm extends AuthorizingRealm {
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysPermissionService sysPermissionService;

	@Override
	public void setName(String name) {
		super.setName("suppliesRealm");
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken authctoken = (UsernamePasswordToken) token;
		SysUser user = sysUserService.findByUsername(authctoken.getUsername());
		if(user == null){
			return null;
		}
		if("1".equals(user.getLocked())){
			throw new LockedAccountException("账号已被锁定");
		}
		ActiveUserVO userVO = new ActiveUserVO();
		userVO.setUsercode(user.getUsercode());
		userVO.setUserid(user.getId());
		userVO.setUsername(user.getUsername());
		userVO.setMenus(sysPermissionService.findMenuListByUserId(user.getId()));
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userVO, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), this.getName());
		return simpleAuthenticationInfo;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//获取登录后的授权信息
		ActiveUserVO user = (ActiveUserVO) principals.getPrimaryPrincipal();
		List<SysPermission> permissionList = sysPermissionService.findPermissionListByUserId(user.getUsercode());
		List<String> permissions = new ArrayList<String>();
		for (SysPermission s : permissionList) {
			//将数据库中的权限标签放入集合
			permissions.add(s.getPercode());
		}
		//添加权限信息
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addStringPermissions(permissions);
		return simpleAuthorizationInfo; 
	}

	
	//清除缓存
	public void clearCached() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}
	
	public static void main(String[] args) {
		Md5Hash md5 = new Md5Hash("admin","aa",1);
		System.out.println(md5.toString());
	}
	

}
