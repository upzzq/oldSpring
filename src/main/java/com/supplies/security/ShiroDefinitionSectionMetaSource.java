package com.supplies.security;

import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.supplies.pojo.SysPermission;
import com.supplies.service.SysPermissionService;

public class ShiroDefinitionSectionMetaSource implements FactoryBean<Ini.Section>{

	@Autowired
    private SysPermissionService sysPermissionService;
    //注入默认的授权定义
    private String filterChainDefinitions;
    //格式化数据，符合shiro的格式，如：perms["admin"]
    public static final String PREMISSION_FORMAT = "perms[\"{0}\"]";
	
	
	@Override
	public Section getObject() throws Exception {
		 Ini ini = new Ini();
	     ini.load(filterChainDefinitions);
	     Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
	     List<SysPermission> listPermission = sysPermissionService.listPermission();
	     for (SysPermission permission : listPermission) {
			if(StringUtils.isBlank(permission.getUrl())){
				continue;
			}
			//如果资源路径的值为逗号分隔，则循环构造元数据。
            if(permission.getUrl().indexOf(",") != -1) {
                String[] peths = permission.getUrl().split(",");
                for(String path : peths) {
                    putDefinitionSection(section, path, permission.getPercode());
                }
            } else {
                putDefinitionSection(section, permission.getUrl(), permission.getPercode());
            }
		}
	     /*section.put("/**","authc,kickout,ForceLogout");*/
	     return section;
	}

	@Override
	public Class<?> getObjectType() {
		return this.getClass();
	}

	@Override
	public boolean isSingleton() {
		return false;
	}
	
	
	private void putDefinitionSection(Section section, String key, String value) {
        System.out.println("加载数据库权限：【key=" + key + "\tvalue=" + value + "】");
        section.put(key, MessageFormat.format(PREMISSION_FORMAT, value));
    }
	
	public void setFilterChainDefinitions(String filterChainDefinitions) {
        this.filterChainDefinitions = filterChainDefinitions;
    }

}
