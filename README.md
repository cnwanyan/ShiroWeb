#ShiroWeb

**************************************

		shiro的工作流程
		
		shiroFilter拦截所有请求
		
		在filterChainDefinitions中所有配置为匿名的或者不包括在配置范围中的页面都可以直接访问。否则将重定向至LoginUrl

		
 **************************************
 
 		为什么spring-shiro中 shiroFilter中的bean的id必须与web.xml中的filter-name一致
 		
 		
 		若不一致，则会抛出spring上下文中找不到bean的异常
 		因为shiro会来IOC容器中查找和<filter-name>的名字对应的filter bean
 		详情请看org.springframework.web.filter.DelegatingFilterProxy源码
 		{@code web.xml} will usually contain a {@code DelegatingFilterProxy} definition,
 		with the specified {@code filter-name} corresponding to a bean name in
		Spring's root application context. All calls to the filter proxy will then
		be delegated to that bean in the Spring context, which is required to implement
		the standard Servlet Filter interface.
		获取filter的方式在源码中显示为
		Filter delegate = wac.getBean(getTargetBeanName(), Filter.class);
		getTargetBeanName() 的默认值在源码中有写到
		Set the name of the target bean in the Spring application context.
		The target bean must implement the standard Servlet Filter interface.
	 	<p>By default, the {@code filter-name} as specified for the
	 	DelegatingFilterProxy in {@code web.xml} will be used.
	 	所以 shiroFilter中的bean的id必须与web.xml中的filter-name一致
	 	
	 	总结：
	 	DelegatingFilterProxy实际上市filter的一个代理对象，默认情况下，Spring会到IOC容器中查找和<filter-name>对应的filter bean 。
	 	也可以通过配置targetBeanName的初始化参数来配置filter bean 的 id
	 	
*****************************************

		filterChainDefinitions的配置
		
		url=拦截器[参数]
		anon 拦截器表示匿名访问
		authc 拦截器表示需要身份认证通过后才能访问
		
		
		url模式使用ant风格模式
			?匹配一个字符
			*匹配0个或多个字符
			**匹配0个或多个路径
		
		url权限顺序 采取第一次匹配的优先方式
		即从头开始 从上往下的顺序
		
*****************************************

		认证流程
		
		1.获取当前的subject，调用SubjectUtils.getSubject()
		2.判定当前的是否已经被认证,调用 Subject的isAuthenticated()
		3.若没有被认证，则把用户名密码封装为UsernamePasswordToken对象
			(1)创建一个表单页面
			(2)把请求提交到SpringMvc的handler(即controller)
			(3)然后获取用户名和密码
		4.执行登录：调用Subject的login(AuthenticationToken)方法
		5.自定义realm的方法，从数据库中获取对应的记录，返回给shiro
			(1)实际上需要继承AuthenticatingRealm类
			(2)实现doGetAuthenticationInfo(AuthenticationToken)方法.
		6.由shiro完成对密码的比对
		
*****************************************