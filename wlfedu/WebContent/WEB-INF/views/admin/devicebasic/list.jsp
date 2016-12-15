<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>铁路盘点系统</title>
	<jsp:include page="../include/commonCss.jsp"></jsp:include>
	<c:set var="MenuCode" value="A3101" scope="request"/>
</head>
<body class="hold-transition skin-blue-light sidebar-mini">
	<div class="wrapper">
		<jsp:include page="../include/header.jsp"></jsp:include>
		<jsp:include page="../include/system_menu.jsp"></jsp:include>
		<div class="content-wrapper">			
			<section class="content">
				<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title">工具管理 > 工具基本信息</h3>
					</div>
					<div class="box-body form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="code">物资编码/名称</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="code" value="${cond.code}" placeholder="物资编码" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="factory">生产厂家</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="factory" value="${cond.factory}" placeholder="生产厂家" >
							</div>
							<div class="col-sm-3"></div>
							<div class="col-sm-1">
								<button type="button" class="btn btn-info btn-block" id="btnSearch" >查询</button>
							</div>
						</div>
					<div class="box-body form-horizontal">
						<div class="table-area">
							<div class="form-group">
								<div class="col-sm-3">
									<button class="btn btn-success" id="btnAdd" type="button">添加新的基本信息</button>
								</div>
							</div>
							<table id="basic" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>物资编码</th>
										<th>物资编码名称</th>
										<th>生产厂家</th>
										<th>品牌</th>
										<th>型号</th>
										<th>工具数量</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${basics}" var="item" varStatus="s">
										<tr class="row${item.id}">
											<td>${item.code}</td>
											<td>${item.codeName}</td>
											<td>${item.factory}</td>
											<td>${item.brand}</td>
											<td>${item.model}</td>
											<td>${item.count}</td>
											<td>
												<button class="btn btn-primary btn-sm" onclick="App.details(${item.id})" >工具详细</button>
												<button class="btn btn-primary btn-sm" onclick="App.editBasic(${item.id})" >編集</button>
												<button class="btn btn-danger btn-sm" onclick="App.deleteBasic(${item.id})">削除</button>
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
	<script src="${pageContext.request.contextPath}/resources/admin/js/devicebasic/list.js"></script>
</body>
</html>
