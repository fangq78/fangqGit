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
		
		if (code!='') {
			ajaxPost("/admin/devicetype/ajaxCheckCode",{code:code},function(data){
				type = data.type;
				if(type!=null) {
					errors.push('物资编码不能重复');
					showErrorMsg(errors);
					return;
				} else {
					// 有错误时结束处理。
					if (errors.length > 0) {
						showErrorMsg(errors);
						return;
					} else {
						// 提交请求
						formPost("/admin/devicetype/doAdd", {
							code: code,
							name: name,
							comment:comment
						});
					}
				}
			});
		} else {
			// 有错误时结束处理。
			showErrorMsg(errors);
			return;
		}
	});
});