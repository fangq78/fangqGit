<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>铁路盘点系统</title>
	<jsp:include page="../admin/include/commonCss.jsp"></jsp:include>
</head>
<body class="hold-transition">
	<div class="row" style="margin: 50px 0px;">
		<div class="col-xs-2"></div>
		<div class="col-xs-8 text-center">
			<h3>发生系统错误<br>请联系管理员。</h3>
		</div>
		<div class="col-xs-2"></div>
	</div>
	<div class="row">
		<div class="col-xs-4"></div>
		<div class="col-xs-4 text-center" >
			<input type="button" id="goback" class="btn btn-block btn-lg" value='返回' />
		</div>
		<div class="col-xs-4"></div>
	</div>
	<jsp:include page="../admin/include/commonJs.jsp"></jsp:include>
</body>
<script type="text/javascript">
$(function(){
	$("#goback").click(function(){
		history.back();
	});
});
</script>
</html>