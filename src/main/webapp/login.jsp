<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();
 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head> 
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>login</h4>
	<form method="post" action="/ShiroWeb/shiro/login">
		username:<input type="text" name="username"/><br><br>
		password:<input type="password" name="password"/><br><br>
		<input type="submit" value="提交">
	</form>
</body>
</html>