<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/resource/bootstrap-4.3.1/dist/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="/resource/bootstrap-4.3.1/dist/js/bootstrap.js"></script>
</head>
<body>

		<nav class="nav fixed-top justify-content-center" style="background:#2FFFFF;height:50px " > 欢迎登录 </nav>
	<div class="container-fulid" style="margin-top:80px;height:600px">
		 <div class="container" >
		 <div class="row">
		 	${error }
		 </div>
		 	<div class="row">
		 		<div class="col-md-6 offset-3" style="background:url(/resource/images/login_backup.jpg);">
		 			<form modelAttribute="user" max="8" min="2" id="form" action="" method="post" >
						  <div class="form-group">
						    <label >用户名</label>
						    <input name="username"  class="form-control" 
						     aria-describedby="emailHelp" remote="/user/checkname" />
						  </div>
						  
						  <div class="form-group">
						    <label>密码</label>
						    <input  type="password" name="password" class="form-control"  />
						  </div>
						  <button type="submit" class="btn btn-primary">登录</button>
						  <a href="register"> <font color="#FF0033">没有账号，点击这里直接去注册</font> </a>
						</form>
						
		 		</div>
		 	</div>
		 </div>
	</div>
	<nav class="nav fixed-bottom justify-content-center"  style="background:#2FFFFF;height:50px "  > cms 网站 </nav>
	
</body>
</html>