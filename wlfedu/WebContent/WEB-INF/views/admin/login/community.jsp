<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>铁路盘点系统 </title>
	<jsp:include page="../include/commonCss.jsp"></jsp:include>
</head>

<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<b>社区管理员登录</b>
		</div>
		<div class="login-box-body">
			<div class="errorDiv"></div>
			<div class="login-box-msg">欢迎访问铁路盘点系统</div>
			<form id="userForm" action="${pageContext.request.contextPath}/admin/login/doCommunity" method="post"> 
				<div class="form-group has-feedback">
					<select class="form-control" id="community" name="community">
						<option value='-1'>请选择社区院校</option>
						<c:forEach items="${communityList}" var="item" varStatus="s">
							<option value="${item.id}" <c:if test="${communityId == item.id }">selected</c:if>>${item.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group has-feedback">
					<input type="text" class="form-control" placeholder="用户名" id="name" name="name" value="${name}">
				</div>
				<div class="form-group has-feedback">
					<input type="password" class="form-control" placeholder="密码" id="password" name="password">
				</div>
				<div class="row">
					<div class="col-xs-12">
						<button type="button" id="login" class="btn btn-primary btn-block btn-flat">登录</button>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 copyright" >
						Copyright © 2016. All rights reserved.
					</div>
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="../include/commonJs.jsp"></jsp:include>
	<script src="${pageContext.request.contextPath}/resources/admin/js/login/community.js"></script>
</body>
</html>