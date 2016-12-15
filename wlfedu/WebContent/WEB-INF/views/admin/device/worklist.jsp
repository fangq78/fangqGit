<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>铁路盘点系统</title>
	<jsp:include page="../include/commonCss.jsp"></jsp:include>
	<c:set var="MenuCode" value="A3102" scope="request"/>
</head>
<body class="hold-transition skin-blue-light sidebar-mini">
	<div class="wrapper">
		<jsp:include page="../include/header.jsp"></jsp:include>
		<jsp:include page="../include/system_menu.jsp"></jsp:include>
		<div class="content-wrapper">			
			<section class="content">
				<div class="box box-primary">
					<div class="box-header with-border">
					<h3 class="box-title">工具管理 > 工具基本信息 > 工具出工信息</h3>
					</div>
					<div class="box-body form-horizontal">
						<div class="box-body form-vertical">
							<div class="form-group">
								<label class="col-sm-1 control-label" for="id">工具编码</label>
								<div class="col-sm-2">
									<input type="text" class="form-control" id="id" value="${cond.id}" placeholder="工具编码" >
								</div>
								<label class="col-sm-1 control-label" for="name">工具名称</label>
								<div class="col-sm-2">
									<input type="text" class="form-control" id="name" value="${cond.name}" placeholder="工具名称" >
								</div>
								<label class="col-sm-1 control-label" for="useFlag">状态</label>
								<div class="col-sm-2">
									<select class="form-control" id="useFlag" name ="useFlag" >
										<option value=-1></option>
										<option value=1 <c:if test="${cond.useFlag==1}">selected</c:if>>出工</option>
										<option value=0 <c:if test="${cond.useFlag==0}">selected</c:if>>收工</option>
									</select>
								</div>
								<label class="col-sm-1 control-label" for="padName">Pad名</label>
								<div class="col-sm-2">
									<input type="text" class="form-control" id="padName" value="${cond.padName}" placeholder="Pad名" >
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-1 control-label" for="depotId" >所属段</label>
								<div class="col-sm-2">
									<select class="form-control" id="depotId" name ="depotId" >
										<option value=0></option>
										<c:forEach items="${depots}" var="temp" >
											<option value="${temp.id}"
												<c:if test="${cond.depotId==temp.id}">selected</c:if>
											>${temp.name}</option>
										</c:forEach>
									</select>
								</div>
								<label class="col-sm-1 control-label" for="workshopId" >所属车间</label>
								<div class="col-sm-2">
									<select class="form-control" id="workshopId" name ="workshopId" >
										<option value=0></option>
										<c:forEach items="${workshops}" var="temp" >
											<option value="${temp.id}"
												<c:if test="${cond.workshopId==temp.id}">selected</c:if>
											>${temp.name}</option>
										</c:forEach>
									</select>
								</div>
								<label class="col-sm-1 control-label" for="teamId" >所属班组</label>
								<div class="col-sm-2">
									<select class="form-control" id="teamId" name ="teamId" >
										<option value=0></option>
										<c:forEach items="${teams}" var="temp" >
											<option value="${temp.id}"
												<c:if test="${cond.teamId==temp.id}">selected</c:if>
											>${temp.name}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-sm-1">
									<button type="button" class="btn btn-info btn-block" id="btnSearch" >查询</button>
								</div>
							</div>
						<div class="box-body form-vertical">
							<div class="table-area">
								<table id="device" class="table table-bordered table-striped">
									<thead>
										<tr>
											<th>工具编号</th>
											<th>所属段</th>
											<th>所属车间</th>
											<th>所属班组</th>
											<th>工具名称</th>
											<th>状态</th>
											<th>用户名</th>
											<th>日时</th>
											<th>PAD名</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${devices}" var="item" varStatus="s">
											<tr class="row${item.id}" >
												<td>${item.formatId}</td>
												<td>${item.depotName}</td>
												<td>${item.workshopName}</td>
												<td>${item.teamName}</td>
												<td>${item.name}</td>
												<c:if test="${item.useFlag==1}"><td><font color='red'>出工</font></td></c:if>
												<c:if test="${item.useFlag!=1}"><td>收工</td></c:if>
												<td>${item.lastestUserName}</td>
												<td>${item.lastestDateTime}</td>
												<td>${item.padName}</td>
												<td>
													<button class="btn btn-primary btn-sm" onclick="App.usingDetailsModal(${item.id})" >出工详细</button>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="box-footer">
					<button type="button" id="back" class="btn btn-default">返回</button>
				</div>
			</section>
		</div>
		<jsp:include page="../include/footer.jsp"></jsp:include>
	</div>
	<div class="modal fade" id="usingDetailsModal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" ></h4>
				</div>
				<div class="modal-body">
					<div class="table-area">
						<table id="details" class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>操作用户</th>
									<th>操作日时</th>
									<th>状态</th>
									<th>PAD名</th>
								</tr>
							</thead>
							<tbody>
								
							</tbody>
						</table>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../include/commonJs.jsp"></jsp:include>
	<script src="${pageContext.request.contextPath}/resources/admin/js/device/worklist.js"></script>
</body>
</html>
