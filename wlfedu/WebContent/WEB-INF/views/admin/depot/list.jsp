<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>铁路盘点系统</title>
	<jsp:include page="../include/commonCss.jsp"></jsp:include>
	<c:set var="MenuCode" value="A1001" scope="request"/>
</head>
<body class="hold-transition skin-blue-light sidebar-mini">
	<div class="wrapper">
		<jsp:include page="../include/header.jsp"></jsp:include>
		<jsp:include page="../include/system_menu.jsp"></jsp:include>
		<div class="content-wrapper">			
			<section class="content">
				<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title">组织管理 > 段信息</h3>
					</div>
					<div class="box-body form-horizontal">
						<div class="table-area">
							<div class="form-group">
								<div class="col-sm-3">
									<button class="btn btn-success" id="btnAdd" type="button">添加新的段</button>
								</div>
							</div>
							<table id="depot" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>段名称</th>
										<th>电话</th>
										<th>地址</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${depots}" var="item" varStatus="s">
										<tr class="row${item.id}">
											<td>${item.name}</td>
											<td>${item.mobile}</td>
											<td>${item.address}</td>
											<td>
												<button class="btn btn-primary btn-sm" onclick="App.editDepot(${item.id})" >編集</button>
												<button class="btn btn-danger btn-sm" onclick="App.deleteDepot(${item.id}, '${item.name}')">削除</button>
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
	<script src="${pageContext.request.contextPath}/resources/admin/js/depot/list.js"></script>
</body>
</html>
