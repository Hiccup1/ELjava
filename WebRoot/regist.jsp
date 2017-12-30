<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>饿了注册</title>

<link rel="stylesheet" type="text/css" href="css/styles.css">

<%//获取数据
String result = request.getParameter("result");
%>
<script type="text/javascript">
 function judge() {
	 var result = <%=result%>;
	 if(result != null) {
		 if(result == "00")
			 alert("注册用户失败");
		 else if(result == "10") 
			 alert("注册店铺失败");
	 }
 }
</script>

</head>
<body onload="judge()">


<div class="wrapper">
	<div class="container" id="divmsg">
		<form class="form" id="formid" action="servlet/UserRegist" method="post">
			<input type="text" placeholder="用户名" id="username" name="username">
			<input type="password" placeholder="密码" id="userpass" name="password">
			<input type="password" placeholder="重复密码" id="againuserpass" name="againpassword">
			<input type="text" placeholder="店铺名称" id="shopname" name="shopname">
			<input type="button" id="login-button" value="店铺注册" onclick="regist()"></input>
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
function regist() {
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
	var againpassword = document.getElementById("againuserpass").value;
	if(againpassword == ""){
		alert("请重复输入密码");
		return;
	}
	if(password != againpassword) {
		alert("两次输入密码不同");
		return;
	}
	var shopname = document.getElementById("shopname").value;
	if(shopname == ""){
		alert("请输入店铺名称");
		return;
	}
	document.getElementById("formid").submit();
}
</script>

</body>
</html>