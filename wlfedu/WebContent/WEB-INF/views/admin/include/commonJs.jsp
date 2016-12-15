<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="${pageContext.request.contextPath}/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/jQueryUI/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/fastclick/fastclick.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/AdminLTE/js/app.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/fileupload/jquery.ui.widget.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/fileupload/tmpl.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/fileupload/load-image.all.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/fileupload/canvas-to-blob.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/fileupload/jquery.blueimp-gallery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/fileupload/jquery.iframe-transport.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/fileupload/jquery.fileupload.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/fileupload/jquery.fileupload-process.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/fileupload/jquery.fileupload-image.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/fileupload/jquery.fileupload-audio.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/fileupload/jquery.fileupload-video.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/fileupload/jquery.fileupload-validate.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/fileupload/jquery.fileupload-ui.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/datetimepicker/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/datetimepicker/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/ueditor/ueditor.config.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/ueditor/ueditor.all.min.js"> </script>
<script src="${pageContext.request.contextPath}/resources/plugins/ueditor/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript">
var App = new function(){
	this.ContextPath = "${pageContext.request.contextPath}";
};
$(".system-sign-out").on("click", function(){
	formPost("/admin/signout");
});
</script>

<c:if test="${!empty error }">
<script type="text/javascript">
$(function(){
	showErrorMsg('${error}');
});
</script>
</c:if>

<c:if test="${!empty MenuCode }">
<script type="text/javascript">
$(function(){
	$('.${MenuCode}').addClass("active").parents(".treeview").addClass("active");
});
</script>
</c:if>
<script src="${pageContext.request.contextPath}/resources/app/js/common.js"></script>