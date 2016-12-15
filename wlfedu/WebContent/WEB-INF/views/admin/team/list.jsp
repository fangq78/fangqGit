<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>铁路盘点系统</title>
	<jsp:include page="../include/commonCss.jsp"></jsp:include>
	<c:set var="MenuCode" value="A1201" scope="request"/>
</head>
<body class="hold-transition skin-blue-light sidebar-mini">
	<div class="wrapper">
		<jsp:include page="../include/header.jsp"></jsp:include>
		<jsp:include page="../include/system_menu.jsp"></jsp:include>
		<div class="content-wrapper">			
			<section class="content">
				<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title">组织管理 > 班组信息</h3>
					</div>
					<div class="box-body form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="depotId" >所属段</label>
							<div class="col-sm-3">
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
						<div class="form-group" >
							<label class="col-sm-2 control-label" for="workshopId" >所属车间</label>
							<div class="col-sm-3">
								<select class="form-control" id="workshopId" name ="workshopId" >
									<option value=0></option>
									<c:forEach items="${workshops}" var="temp" >
										<option value="${temp.id}"
											<c:if test="${workshopId==temp.id}">selected</c:if>
										>${temp.name}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-sm-2"></div>
							<div class="col-sm-1">
								<button type="button" class="btn btn-info btn-block" id="btnSearch" >查询</button>
							</div>
						</div>
					<div class="box-body form-horizontal">
						<div class="table-area">
							<div class="form-group">
								<div class="col-sm-3">
									<button class="btn btn-success" id="btnAdd" type="button">添加新的班组</button>
								</div>
							</div>
							<table id="team" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>班组名称</th>
										<th>所属段</th>
										<th>所属车间</th>
										<th>电话</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${teams}" var="item" varStatus="s">
										<tr class="row${item.id}">
											<td>${item.name}</td>
											<td>${item.depotName}</td>
											<td>${item.workshopName}</td>
											<td>${item.mobile}</td>
											<td>
												<button class="btn btn-primary btn-sm" onclick="App.editTeam(${item.id})" >編集</button>
												<button class="btn btn-danger btn-sm" onclick="App.deleteTeam(${item.id}, '${item.name}')">削除</button>
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
	<jsp:include page="../include/commonJs.jsp"></jsp:include>
	<script src="${pageContext.request.contextPath}/resources/admin/js/team/list.js"></script>
</body>
</html>
