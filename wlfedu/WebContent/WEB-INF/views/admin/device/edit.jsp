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
					<c:if test="${!empty editIds}">
						<h3 class="box-title">工具管理 > 工具基本信息 > 工具信息列表 >批量修改工具</h3>
					</c:if>
					<c:if test="${empty editIds}">
						<h3 class="box-title">工具管理 > 工具基本信息 > 工具信息列表 >修改工具</h3>
					</c:if>
					</div>
					<input id="editIds" type="hidden"  value="${editIds}">
					<input id="basicId" type="hidden"  value="${basic.id}">
					<input id="from" type="hidden"  value="${from}">
					<div class="box-body form-horizontal">
				      <div class="form-group">
							<label class="col-sm-3 control-label" for="code" >物资编码</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="code" value="${basic.code}" disabled placeholder="物资编码编码" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="codeName">物资编码名称</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="codeName" value="${basic.codeName}" disabled placeholder="物资编码名称" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="factory">生产厂家</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="factory" value="${basic.factory}" disabled placeholder="生产厂家" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="brand" >品牌</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="brand" value="${basic.brand}"  disabled maxlength="20" placeholder="品牌" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="model">型号</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="model" value="${basic.model}" disabled maxlength="20" placeholder="型号" >
							</div>
						</div>
						<div class="form-group" >
							<label class="col-sm-3 control-label" for="depotId" ><code>*</code>所属段</label>
							<div class="col-sm-4">
								<select class="form-control" id="depotId" name ="depotId" >
									<option value=0></option>
									<c:forEach items="${depots}" var="temp" >
										<option value="${temp.id}"
											<c:if test="${device.depotId==temp.id}">selected</c:if>
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
											<c:if test="${device.workshopId==temp.id}">selected</c:if>
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
											<c:if test="${device.teamId==temp.id}">selected</c:if>
										>${temp.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="name"><code>*</code>工具名称</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="name" value="${device.name}"  maxlength="150" placeholder="工具名称" >
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
	<script src="${pageContext.request.contextPath}/resources/admin/js/device/edit.js"></script>
</body>
</html>
