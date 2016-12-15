$(function(){

	// 返回
	$("#back").on("click", function(){
		formPost("/admin/depot/list");
	});

	// 提交按钮
	$("#submit").on("click", function(){

		var errors = [];

		// 段名称
		var name = $('#name').val().trim();
		if(name == '') {
			errors.push('请输入段名称');
		}
		
		// 有错误时结束处理。
		if (errors.length > 0) {
			showErrorMsg(errors);
			return;
		}

		// 地址
		var address = $('#address').val().trim();
		// 电话
		var mobile = $('#mobile').val().trim();
		
		// 提交请求
		formPost("/admin/depot/doAdd", {
			name: name,
			address: address,
			mobile:mobile
		});
	});
});