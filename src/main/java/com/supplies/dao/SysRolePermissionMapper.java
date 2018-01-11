package com.supplies.dao;

import java.util.List;
import java.util.Map;

import com.supplies.pojo.SysRolePermission;

import tk.mybatis.mapper.common.Mapper;

public interface SysRolePermissionMapper extends Mapper<SysRolePermission>{
    
    /*List<Map<String, Object>> find();*/
    List<SysRolePermission> find();
}