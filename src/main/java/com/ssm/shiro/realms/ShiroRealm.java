package com.ssm.shiro.realms;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssm.shiro.entity.User;
import com.ssm.shiro.service.UserService;


public class ShiroRealm extends AuthorizingRealm{
	
private static Logger logger = Logger.getLogger(ShiroRealm.class);
	    
	@Autowired
	private UserService userService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		logger.info("进入FirstRealm doGetAuthenticationInfo");
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
		//4.credentialsSalt 盐值
		logger.info("登录成功,登录用户为:"+user.getName());
		ByteSource credentialsSalt=ByteSource.Util.bytes(user.getUsername());
		AuthenticationInfo info=new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(),credentialsSalt, getName());
		
		return info;
	}

	
	//shiro回调方法，用于判断权限
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		System.err.println("进入权限回调");
				//1. 从 PrincipalCollection 中来获取登录用户的信息
				Object principal = principals.getPrimaryPrincipal();
				
				//2. 利用登录的用户的信息来用户当前用户的角色或权限(可能需要查询数据库)
				Set<String> roles = new HashSet<>();
				roles.add(principal.toString());
				if("root".equals(principal)){
					roles.add("user");
				}
				//3. 创建 SimpleAuthorizationInfo, 并设置其 reles 属性.
				SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
				
				//4. 返回 SimpleAuthorizationInfo 对象. 
				return info;
	}

}
