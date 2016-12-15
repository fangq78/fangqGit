<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>铁路盘点系统</title>
	<jsp:include page="../include/commonCss.jsp"></jsp:include>
	<c:set var="MenuCode" value="A3001" scope="request"/>
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
						<h3 class="box-title">工具管理 > 物资编码列表 > 编辑物资编码</h3>
					</div>
					<div class="box-body form-horizontal">
						<div class="form-group">
							<label class="col-sm-3 control-label" for="code" ><code>*</code>物资编码</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="code" value="${deviceType.code}" maxlength="20" disabled placeholder="物资编码" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="name" ><code>*</code>物资编码名称</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="name" value="${deviceType.name}" maxlength="20"  placeholder="物资编码名称" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="comment">说明</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="comment" value="${deviceType.comment}" maxlength="100" placeholder="说明" >
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
	<script src="${pageContext.request.contextPath}/resources/admin/js/devicetype/edit.js"></script>
</body>
</html>
