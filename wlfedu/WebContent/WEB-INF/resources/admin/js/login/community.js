$(function(){

	$("#login").on("click", function(){
		var errors = [];
		// 院校
		if ($('#community option:selected').val() == "-1"){
			errors.push('请选择院校');
		}
		// 用户名
		if ($('#name').val().trim() == ''){
			errors.push('请输入用户名');
		}
		// 密码
		if ($('#password').val().trim() == ''){
			errors.push('请输入密码');
		}
		// 如果有错误，处理结束
		if (errors.length > 0) {
			showErrorMsg(errors);
			return;
		}
		$('#userForm').submit();
	});
});