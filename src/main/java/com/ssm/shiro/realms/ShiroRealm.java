package com.ssm.shiro.realms;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssm.shiro.entity.User;
import com.ssm.shiro.service.UserService;


public class ShiroRealm extends AuthenticatingRealm{
	
private static Logger logger = Logger.getLogger(ShiroRealm.class);
	    
	@Autowired
	private UserService userService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		//把AuthenticationToken转换为UsernamePasswordToken
		UsernamePasswordToken upToken=(UsernamePasswordToken) token;
		//从UsernamePassword中获取username
		String username=upToken.getUsername();
		//调用数据库方法，从数据库中查询username对应的用户记录
		User us=new User();
		us.setName(username);
		User user = this.userService.login(us);
		//若用户不存在，则可抛出用户不存在异常
		logger.info("数据库中获取的用户数据为"+user);
		if(user==null){
			logger.info("用户不存在");
			throw new UnknownAccountException("用户不存在");
		}
		//根据用户信息的情况决定是否需要抛出其他异常
		if("cs".equals(user.getUsername())){
			logger.info("该用户被锁定");
			throw new LockedAccountException("用户被锁定");
		}
		
		//根据用户的情况来构建AuthenticationToken对象并返回,通常使用的实现类为SimpleAuthenticationInfo
		//1.principals 认证的实体信息
		//2.credentials 密码
		//3.realm 当前realm对象的name 调用父类的getName方法即可
		AuthenticationInfo info=new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
		return info;
	}

}
