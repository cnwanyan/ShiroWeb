package com.ssm.shiro.factory;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapFactory {
	
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
