<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html lang="zh">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>饿了登录</title>

<link rel="stylesheet" type="text/css" href="css/styles.css"> 

</head>
<body>


<div class="wrapper">

	<div class="container" id="divmsg">
		<h1>Welcome to 饿了后台</h1>
		<form class="form" >
			<a href="login.jsp" style='text-decoration:none;'><input type="button" id="login-button" value="店铺登录"></input></a>
		<form class="form">
			<a href="regist.jsp" style='text-decoration:none;'><input type="button" id="login-button" value="店铺注册"></input></a>
		</form>
	</div>
	
	<ul class="bg-bubbles">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
	
</div>

<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
</script>

</body>
</html>