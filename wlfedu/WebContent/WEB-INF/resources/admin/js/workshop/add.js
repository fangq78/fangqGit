$(function(){

	// 返回
	$("#back").on("click", function(){
		formPost("/admin/workshop/list");
	});

	// 提交按钮
	$("#submit").on("click", function(){

		var errors = [];

		// 段
		depotId =$('#depotId option:selected').val();
		if (depotId<1){
			errors.push('请选择所属段');
		}
		
		// 车间名称
		var name = $('#name').val().trim();
		if(name == '') {
			errors.push('请输入车间名称');
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
		formPost("/admin/workshop/doAdd", {
			depotId: depotId,
			name: name,
			address: address,
			mobile:mobile
		});
	});
});