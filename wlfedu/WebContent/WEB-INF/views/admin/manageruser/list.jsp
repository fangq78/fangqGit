<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>铁路盘点系统</title>
	<jsp:include page="../include/commonCss.jsp"></jsp:include>
	<c:set var="MenuCode" value="A2001" scope="request"/>
</head>
<body class="hold-transition skin-blue-light sidebar-mini">
	<div class="wrapper">
		<jsp:include page="../include/header.jsp"></jsp:include>
		<jsp:include page="../include/system_menu.jsp"></jsp:include>
		<div class="content-wrapper">
			<section class="content">
				<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title">组织管理 > 管理员列表</h3>
					</div>
					<div class="box-body form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="name">用户名</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="name" value="${cond.name}" placeholder="用户名" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="type">用户类型</label>
							<div class="col-sm-2">
								<select class="form-control" id="type">
									<option value="-1" <c:if test="${cond.type == -1}">selected</c:if> > </option>
									<option value="0" <c:if test="${cond.type == 0}">selected</c:if> >系统管理员</option>
									<option value="1" <c:if test="${cond.type == 1}">selected</c:if>>车间管理员</option>
								</select>
							</div>
							<div id="divSS" <c:if test="${cond.type ==1}">style="display: none"</c:if>>
								<div class="col-sm-3"></div>
								<div class="col-sm-1">
									<button type="button" class="btn btn-info btn-block" id="btnSearch1" >查询</button>
								</div>
							</div>
						</div>
						<div id="depotgroup" class="form-group" <c:if test="${cond.type <1}">style="display: none"</c:if> >
							<label class="col-sm-2 control-label" for="depotId" >所属段</label>
							<div class="col-sm-3">
								<select class="form-control" id="depotId" name ="depotId" >
									<option value=0></option>
									<c:forEach items="${depots}" var="temp" >
										<option value="${temp.id}"
											<c:if test="${cond.depotId==temp.id}">selected</c:if>
										>${temp.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div id="workshopgroup" class="form-group" <c:if test="${cond.type <1}">style="display: none"</c:if> >
							<label class="col-sm-2 control-label" for="workshopId" >所属车间</label>
							<div class="col-sm-3">
								<select class="form-control" id="workshopId" name ="workshopId" >
									<option value=0></option>
									<c:forEach items="${workshops}" var="temp" >
										<option value="${temp.id}"
											<c:if test="${cond.workshopId==temp.id}">selected</c:if>
										>${temp.name}</option>
									</c:forEach>
								</select>
							</div>
							<div id="divWS" <c:if test="${cond.type <1}">style="display: none"</c:if>>
								<div class="col-sm-3"></div>
								<div class="col-sm-1">
									<button type="button" class="btn btn-info btn-block" id="btnSearch2" >查询</button>
								</div>
							</div>
						</div>
						<hr/>
						<div class="table-area">
							<div class="form-group">
								<div class="col-sm-3">
									<button class="btn btn-success" id="btnAdd" type="button">添加新的管理员</button>
								</div>
							</div>
							<table id="mainTbl" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>用户名</th>
										<th>用户类型</th>
										<th>所属段</th>
										<th>所属车间</th>
										<th>联系电话</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${users}" var="temp" >
										<tr class="row${temp.id}" >
											<td>${temp.name}</td>
											<td>
												<span>${temp.type == 0 ? "系统管理员" : "车间管理员"}</span>
											</td>
											<td>${temp.depotName}</td>
											<td>${temp.workshopName}</td>
											<td>${temp.mobile}</td>
											<td>
												<button class="btn btn-primary btn-sm" type="button" onclick="App.editUser(${temp.id})" >編集</button>
												<button class="btn btn-primary btn-sm" type="button" onclick="App.showModal(${temp.id})" >设置密码</button>
												<button class="btn btn-danger btn-sm" type="button" onclick="App.deleteUser(${temp.id}, '${temp.name}')">削除</button>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</section>
		</div>
		<jsp:include page="../include/footer.jsp"></jsp:include>
	</div>
	<div class="modal fade" id="pwdModal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">设置系统用户密码</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="password" class="control-label">用户密码:</label>
						<input type="password" class="form-control" id="password" >
					</div>
					<div class="form-group">
						<label for="re-password" class="control-label">确认密码:</label>
						<input type="password" class="form-control" id="re-password" >
					</div>
					<input type="hidden" class="user-id" >
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary set-password">提交</button>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../include/commonJs.jsp"></jsp:include>
	<script src="${pageContext.request.contextPath}/resources/admin/js/manageruser/list.js"></script>
</body>
</html>
