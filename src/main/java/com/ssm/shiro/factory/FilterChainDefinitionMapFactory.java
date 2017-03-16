package com.ssm.shiro.factory;

import java.util.LinkedHashMap;

/**
 * 用来创建权限列表的工厂类
 * @author cs
 *
 */
public class FilterChainDefinitionMapFactory {
	
	
	/**
	 * 可以使用数据库调取权限
	 * @return
	 */
	public LinkedHashMap<String,String> getFilterChainDefinitionMap(){
		LinkedHashMap<String,String> map=new LinkedHashMap<String,String>();
		map.put("/login.jsp", "anon") ;
		map.put("/shiro/login", "anon") ;
		map.put("/shiro/logout", "logout") ;
		map.put("/admin.jsp", "roles[admin]") ;
		map.put("/user.jsp", "roles[root]") ;
		map.put("/**", "authc") ;
		return map;
	}
}
