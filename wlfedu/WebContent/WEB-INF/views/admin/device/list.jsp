<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>铁路盘点系统</title>
	<jsp:include page="../include/commonCss.jsp"></jsp:include>
	<c:if test="${from=='basic'}">
		<c:set var="MenuCode" value="A3101" scope="request"/>
	</c:if>
</head>
<body class="hold-transition skin-blue-light sidebar-mini">
	<div class="wrapper">
		<jsp:include page="../include/header.jsp"></jsp:include>
		<jsp:include page="../include/system_menu.jsp"></jsp:include>
		<div class="content-wrapper">			
			<section class="content">
				<div class="box box-primary">
					<div class="box-header with-border">
					<c:if test="${from=='basic'}">
						<h3 class="box-title">工具管理 > 工具基本信息 > 工具信息列表</h3>
					</c:if>
					</div>
					<input id="basicId" type="hidden"  value="${bcond.id}">
					<input id="from" type="hidden"  value="${from}">
					<input id="searchCode" type="hidden"  value="${searchCode}">
					<input id="searchFactory" type="hidden"  value="${searchFactory}">
					<div class="box-body form-horizontal">
						<div class="box-body form-vertical">
							<div class="form-group">
								<label class="col-sm-1 control-label" for="factory">物资编码</label>
								<div class="col-sm-2">
									<input type="text" class="form-control" id="code" value="${bcond.code}" disabled placeholder="物资编码" >
								</div>
								<label class="col-sm-1 control-label" for="codeName">编码名称</label>
								<div class="col-sm-2">
									<input type="text" class="form-control" id="codeName" value="${bcond.codeName}" disabled placeholder="物资编码名称" >
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-1 control-label" for="factory">生产厂家</label>
								<div class="col-sm-2">
									<input type="text" class="form-control" id="factory" value="${bcond.factory}"  disabled placeholder="生产厂家" >
								</div>
								<label class="col-sm-1 control-label" for="brand">品牌</label>
								<div class="col-sm-2">
									<input type="text" class="form-control" id="brand" value="${bcond.brand}" disabled placeholder="品牌" >
								</div>
								<label class="col-sm-1 control-label" for="model">型号</label>
								<div class="col-sm-2">
									<input type="text" class="form-control" id="model" value="${bcond.model}" disabled placeholder="型号" >
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
								<div class="form-group">
									<div class="col-sm-10">
										<button class="btn btn-success" id="btnBatchAdd" type="button">批量添加工具</button>
										<button class="btn btn-success" id="btnBatchEdit" type="button">批量修改工具</button>
										<button class="btn btn-success" id="btnBatchDelete" type="button">批量删除工具</button>
									</div>
								</div>
								<table id="device" class="table table-bordered table-striped">
									<thead>
										<tr>
											<th></th>
											<th>工具编号</th>
											<th>所属段</th>
											<th>所属车间</th>
											<th>所属班组</th>
											<th>工具名称</th>
											<th>二维码编码</th>
											<th>二维码</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${devices}" var="item" varStatus="s">
											<tr class="row${item.id}" >
												<td><input type="checkbox" name="selected" id="${item.id}" /></td>
												<td>${item.formatId}</td>
												<td>${item.depotName}</td>
												<td>${item.workshopName}</td>
												<td>${item.teamName}</td>
												<td>${item.name}</td>
												<td>${item.genCode}</td>
												<td><img class="preview-image" src="${pageContext.request.contextPath}${item.genImageUrl}"></td>
												<td>
													<button class="btn btn-primary btn-sm" onclick="App.edit(${item.id},'basic')" >編集</button>
													<button class="btn btn-danger btn-sm" onclick="App.deleteDevice(${item.id})">削除</button>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<c:if test="${from=='basic'}">
					<div class="box-footer">
						<button type="button" id="back" class="btn btn-default">返回</button>
					</div>
				</c:if>
			</section>
		</div>
		<jsp:include page="../include/footer.jsp"></jsp:include>
	</div>
	<jsp:include page="../include/commonJs.jsp"></jsp:include>
	<script src="${pageContext.request.contextPath}/resources/admin/js/device/list.js"></script>
</body>
</html>
