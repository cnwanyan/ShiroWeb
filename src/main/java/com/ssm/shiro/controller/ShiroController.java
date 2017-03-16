package com.ssm.shiro.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shiro")
public class ShiroController {
	
	@RequestMapping("/login")
	public String login(@RequestParam("username")String username,@RequestParam("password")String password){
		Subject currentUser=SecurityUtils.getSubject();
		if(!currentUser.isAuthenticated()){
			UsernamePasswordToken token=new UsernamePasswordToken(username, password);
			//token.setRememberMe(true);  设置是否记住
			try {
				currentUser.login(token);
			} catch (AuthenticationException e) {
				System.out.println("登陆失败："+e.getMessage());
			}
		}
		return "redirect:/list.jsp";
	}
}
