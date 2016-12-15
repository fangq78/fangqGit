$(function(){

	// 返回
	$("#back").on("click", function(){
		formPost("/admin/devicetype/list");
	});

	// 提交按钮
	$("#submit").on("click", function(){

		var errors = [];

		// 物资编码
		var code = $('#code').val().trim();
		if(code == '') {
			errors.push('请输入物资编码');
		}
		
		// 物资编码名称
		var name = $('#name').val().trim();
		if(name == '') {
			errors.push('请输入物资编码名称');
		}
		
		var comment = $('#comment').val().trim();
		
		
		// 有错误时结束处理。
		if (errors.length > 0) {
			showErrorMsg(errors);
			return;
		}

		// 提交请求
		formPost("/admin/devicetype/doEdit", {
			code: code,
			name: name,
			comment:comment
		});
	});
});