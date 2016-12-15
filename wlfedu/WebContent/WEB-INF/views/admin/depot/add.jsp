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
				<div class="errorDiv"></div>
				<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title">组织管理 > 段列表 > 新增段</h3>
					</div>
					<div class="box-body form-horizontal">
						<div class="form-group">
							<label class="col-sm-3 control-label" for="name"><code>*</code>段名称</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="name" value="${depot.name}" maxlength="100" placeholder="段名称" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="mobile" >电话</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="mobile" value="${depot.mobile}" maxlength="20" placeholder="电话" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="address">地址</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="address" value="${depot.address}" maxlength="200" placeholder="地址" >
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
	<script src="${pageContext.request.contextPath}/resources/admin/js/depot/add.js"></script>
</body>
</html>
