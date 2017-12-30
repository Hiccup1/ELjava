<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>饿了登录</title>

<link rel="stylesheet" type="text/css" href="css/styles.css">

<%//获取数据
String result = request.getParameter("result");
%>
<script type="text/javascript">
 function judge() {
	 var result = <%=result%>;
	 if(result != null) {
		 if(result == "0")
			 alert("该用户不存在");
		 else if(result == "2") 
			 alert("密码输入错误");
		 else alert("商家账号和用户账号不通用");
	 }
 }
</script>

</head>
<body onload="judge()">


<div class="wrapper">

	<div class="container" id="divmsg">
		<h1>Welcome to 后台</h1>
		<form class="form" id="formid" action="servlet/UserLogin" method="post">
			<input type="text" placeholder="用户名" id="username" name="username">
			<input type="password" placeholder="密码" id="userpass" name="password">
			<input type="button" id="login-button" value="店铺登录" onclick="login()"></input>
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
function login() {
	var username = document.getElementById("username").value;
	if(username == ""){
		alert("请输入用户名");
		return;
	}
	var password = document.getElementById("userpass").value;
	if(password == ""){
		alert("请输入密码");
		return;
	}
	document.getElementById("formid").submit();
}
</script>

</body>
</html>