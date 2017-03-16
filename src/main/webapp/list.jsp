<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
	<h4>list</h4>
	Welcome <shiro:principal></shiro:principal><br><br>
	<shiro:hasRole name="admin">
	<a href="admin.jsp">Admin</a><br><br>
	</shiro:hasRole>
	<shiro:hasRole name="user">
	<a href="user.jsp">User</a><br><br>
	</shiro:hasRole>
	<a href="shiro/logout">Logout</a><br><br>
</body>
</html>