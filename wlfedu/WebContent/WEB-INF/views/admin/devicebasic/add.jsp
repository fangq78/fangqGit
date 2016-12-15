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
				<div class="errorDiv"></div>
				<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title">工具管理 > 基本信息列表 > 新增基本信息</h3>
					</div>
					<div class="box-body form-horizontal">
				      <div class="form-group">
							<label class="col-sm-3 control-label" for="code" ><code>*</code>物资编码</label>
							<div class="col-sm-4">
								<select class="form-control" id="code" name ="code" >
									<option value=0>请选择物资编码</option>
									<c:forEach items="${types}" var="temp" >
										<option value="${temp.code}"
											<c:if test="${code==temp.code}">selected</c:if>
										>${temp.code}-${temp.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="factory"><code>*</code>生产厂家</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="factory" value="${basic.factory}" maxlength="40" placeholder="生产厂家" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="brand" >品牌</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="brand" value="${basic.brand}" maxlength="20" placeholder="品牌" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="model">型号</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="model" value="${basic.model}" maxlength="20" placeholder="型号" >
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
	<script src="${pageContext.request.contextPath}/resources/admin/js/devicebasic/add.js"></script>
</body>
</html>
