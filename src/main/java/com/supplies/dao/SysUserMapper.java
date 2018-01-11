package com.supplies.dao;

import java.util.List;
import java.util.Map;

import com.supplies.pojo.SysUser;

import tk.mybatis.mapper.common.Mapper;

public interface SysUserMapper extends Mapper<SysUser>{
    
    
    SysUser findByUsername(String usercode);
    
    //List<Map<String,Object>> find();
    List<SysUser> find();
}