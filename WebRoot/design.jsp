<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.waimai.common.*,java.util.List,com.waimai.model.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理</title>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <script type="text/javascript" src="js/libs/modernizr.min.js"></script>
    <script language="javascript" type="text/javascript" src="js/jquery-1.11.3.js"></script>
	
    <%Shop shop=null;
	  Type type=null;
	  List typeslist= null;
	  typeslist=(List)session.getAttribute(Data.TYPES);
	  shop=(Shop)session.getAttribute(Data.SHOPS);
	 
	  if(shop!=null){
		  int sid = shop.getSid();
	      int typelistlen=typeslist.size();%>
</head>
<body>
<div class="topbar-wrap white">
    <div class="topbar-inner clearfix">
        <div class="topbar-logo-wrap clearfix">
            <h1 class="topbar-logo none"><a href="index.html" class="navbar-brand">后台管理</a></h1>
            <ul class="navbar-list clearfix">
                <li><a class="on" href="design.jsp">首页</a></li>
            </ul>
        </div>
        <div class="top-info-wrap">
            <ul class="top-info-list clearfix">
                <li><a href="#">管理员</a></li>
                <li><a href="#">修改密码</a></li>
                <li><a href="#">退出</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container clearfix">
    <div class="sidebar-wrap">
        <div class="sidebar-title">
            <h1>菜单</h1>
        </div>
        <div class="sidebar-content">
            <ul class="sidebar-list">
                <li>
                    <a href="#"><i class="icon-font">&#xe000;</i>店铺信息</a>
                    <ul class="sub-menu">
                        <li><a href="design.jsp">店铺信息</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="icon-font">&#xe008;</i>订单管理</a>
                    <ul class="sub-menu">
                        <li><a href="servlet/ShowDoingOrder?sid=<%=sid %>">未完成</a></li>
                        <li><a href="servlet/ShowDoneOrder?sid=<%=sid %>">已完成</a></li>
 
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="icon-font">&#xe001;</i>菜品管理</a>
                    <ul class="sub-menu">
                    	<%for(int i=0;i<typelistlen;i++){
							type=(Type)typeslist.get(i);%>
                        <li><a href="servlet/SearchDish?typeid=<%=type.getTid()%>"><%=type.getName()%></a></li>
                        <%}%>
                        <li><a href="servlet/SearchType?sid=<%=sid%>">编辑类别</a></li>
 
                    </ul>
                </li>
                
                
                <li>
                    <a href="#"><i class="icon-font">&#xe018;</i>系统管理</a>
                    <ul class="sub-menu">
                       <li><a href="#">系统管理</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="design.jsp">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">店铺信息</span></div>
        </div>
        <table id="addAddress" class="insert-tab" style="width:550px;display:none">
			<form action="servlet/EditShop" id="updatafrom" method="post">
				<tbody>
                <tr>
                	<td style="text-align:center">店 铺名称</td>
                	<td>
                    <input style="width:450px;height:30px;" type="text"  placeholder="输入店铺名称" name="shopname" >
                    </td>
                </tr>
                <tr>
                	<td style="text-align:center">店铺头像外链</td>
                	<td>
                    <input style="width:450px;height:30px;" type="text"  placeholder="输入店铺头像外链" name="shopimg" >
                    </td>
                </tr>
				<tr>
                	<td style="text-align:center">电话</td>
                	<td>
                    <input style="width:450px;height:30px;" type="text"  placeholder="输入电话" name="phonenumber">
                    </td>
                </tr>
                <tr>
                	<td style="text-align:center">地址</td>
                	<td>
                    <input style="width:450px;height:30px;" type="text"  placeholder="输入地址" name="address">
                    </td>
                </tr>
                <tr>
                	<td style="text-align:center">简介</td>
                	<td>
                    <textarea  style="width:450px;height:100px;margin-top:2px;font-size:20px" name="remark"></textarea>
                    </td>
                </tr>
                <tr>
                	<td style="text-align:right;">
                    <input type="submit" id="sureAddAddress" value="确定">
                    </td>
                    <td style="text-align:left">
                    <input type="button" id="cancelAddAddress" value="取消">
                    </td>
                </tr>
                <input type="hidden" name="sid" value="<%=sid%>"></input>
               </form>
			
  </tbody></table>
  
  <table id="shopAddress" class="insert-tab" style="width:550px;font-size:20px;margin-left:100px;">
				<tbody>
                <tr>
                	<td style="text-align:right">店铺名称:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                	<td>
                    <%=shop.getName()%>
                    </td>
                </tr>
                <tr>
                	<td style="text-align:right">店铺头像外链:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                	<td>
                    <%=shop.getImg()%>
                    </td>
                </tr>
				<tr>
                	<td style="text-align:right">电话:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                	<td>
                    <%=shop.getPhone()%>
                    </td>
                </tr>
                <tr>
                	<td style="text-align:right">地址:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                	<td>
                    <%=shop.getLocation()%>
                    </td>
                </tr>
                <tr>
                	<td style="text-align:right">简介:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                	<td>
                    <%=shop.getRemark()%>
                    </td>
                </tr>
                <%}
				else{
					response.sendRedirect("index.jsp");
				}%>
                <tr>
                	<td style="text-align:right;">
                    <input type="button" style="margin-right:25px;" id="changeAddress" value="修改店铺信息">
                    </td>
                </tr>
			
  </tbody></table>
    </div>
    <!--/main-->
</div>
<script type="text/javascript">
$(function () {
    $("#changeAddress").bind("click", function () {
		$("#shopAddress").hide();
        $("#addAddress").show();
        $("#sureAddAddress").bind("click",function(){
		    $("#updatafrom" ).submit();
        });
	    $("#cancelAddAddress").bind("click",function(){
            $("#addAddress").hide();
            $("#shopAddress").show();
        });
    })
});
</script>

</body>
</html>