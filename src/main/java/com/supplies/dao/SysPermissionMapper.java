package com.supplies.dao;

import java.util.List;

import com.supplies.pojo.SysPermission;

import tk.mybatis.mapper.common.Mapper;

public interface SysPermissionMapper extends Mapper<SysPermission>{
    
    List<SysPermission> findPermissionListByUserId(String id);
    
    List<SysPermission> findMenuListByUserId(String id);
    
    /**
     * 查询所有 permission
     * @return
     */
    List<SysPermission> listPermission();
}