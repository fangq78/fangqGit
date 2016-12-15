$(function(){

	// 返回
	$("#back").on("click", function(){
		formPost("/admin/devicebasic/list");
	});

	// 提交按钮
	$("#submit").on("click", function(){

		var errors = [];

		// 物资编码
		code =$('#code option:selected').val();
		if (code == '' || code == '0'){
			errors.push('请选择物资编码');
		}
		
		// 生产厂家
		var factory = $('#factory').val().trim();
		if(factory == '') {
			errors.push('请输入生产厂家');
		}
		
		// 有错误时结束处理。
		if (errors.length > 0) {
			showErrorMsg(errors);
			return;
		}

		// 品牌
		var brand = $('#brand').val().trim();
		// 型号
		var model = $('#model').val().trim();
		
		// 提交请求
		formPost("/admin/devicebasic/doEdit", {
			id: $('#id').val(),
			code: code,
			factory: factory,
			brand: brand,
			model:model
		});
	});
});