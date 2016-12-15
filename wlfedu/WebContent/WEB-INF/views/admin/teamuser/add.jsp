<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>铁路盘点系统</title>
	<jsp:include page="../include/commonCss.jsp"></jsp:include>
	<c:set var="MenuCode" value="A2101" scope="request"/>
</head>
<body class="hold-transition skin-blue-light sidebar-mini">
	<div class="wrapper">
		<jsp:include page="../include/header.jsp"></jsp:include>
		<jsp:include page="../include/system_menu.jsp"></jsp:include>
		<div class="content-wrapper">			
			<section class="content">
				<div class="errorDiv"></div>
				<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title">组织管理 > 管理员列表 > 新增班组人员</h3>
					</div>
					<div class="box-body form-horizontal">
						<div class="form-group">
							<label class="col-sm-3 control-label" for="name" ><code>*</code>用户登录名</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="name" value="${user.name}" maxlength="20" placeholder="用户登录名" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="realName" ><code>*</code>用户名</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="realName" value="${user.realName}" maxlength="20" placeholder="用户名" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="password"><code>*</code>登录密码</label>
							<div class="col-sm-4">
								<input type="password" class="form-control" id="password" maxlength="12" placeholder="登录密码" >
							</div>
						</div>
						<div class="form-group" >
							<label class="col-sm-3 control-label" for="depotId" ><code>*</code>所属段</label>
							<div class="col-sm-4">
								<select class="form-control" id="depotId" name ="depotId" >
									<option value=0></option>
									<c:forEach items="${depots}" var="temp" >
										<option value="${temp.id}"
											<c:if test="${depotId==temp.id}">selected</c:if>
										>${temp.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div  class="form-group"  >
							<label class="col-sm-3 control-label" for="workshopId" ><code>*</code>所属车间</label>
							<div class="col-sm-4">
								<select class="form-control" id="workshopId" name ="workshopId" >
									<option value=0></option>
									<c:forEach items="${workshops}" var="temp" >
										<option value="${temp.id}"
											<c:if test="${workshopId==temp.id}">selected</c:if>
										>${temp.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						
						<div  class="form-group"  >
							<label class="col-sm-3 control-label" for="teamId" ><code>*</code>所属班组</label>
							<div class="col-sm-4">
								<select class="form-control" id="teamId" name ="teamId" >
									<option value=0></option>
									<c:forEach items="${teams}" var="temp" >
										<option value="${temp.id}"
											<c:if test="${teamId==temp.id}">selected</c:if>
										>${temp.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-3 control-label" for="mobile">联系电话</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="mobile" value="${user.mobile}" maxlength="20" placeholder="联系电话" >
							</div>
						</div>
					</div>
					<div class="box-footer">
						<button type="button" id="back" class="btn btn-default">返回</button>
						<button type="button" id="submit" class="btn btn-success pull-right">提交</button>	
					</div>
				</div>
			</section>
		</div>
		<jsp:include page="../include/footer.jsp"></jsp:include>
	</div>
	<jsp:include page="../include/commonJs.jsp"></jsp:include>
	<script src="${pageContext.request.contextPath}/resources/admin/js/teamuser/add.js"></script>
</body>
</html>
