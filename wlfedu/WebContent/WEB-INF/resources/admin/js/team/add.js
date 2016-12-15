$(function(){

	// 返回
	$("#back").on("click", function(){
		formPost("/admin/team/list");
	});

	$('#depotId').on("change",function(){
		$('#workshopId option').each(function(){
			if( $(this).val() != '0'){
			 $(this).remove();
			}
		});
		var depotId = $('#depotId option:selected').val();
		ajaxPost("/admin/team/ajaxWorkshops",{depotId:depotId},function(data){
			workshops = data.workshops;
			for (var i = 0 ; i < workshops.length; i++) {
				$("#workshopId").append("<option value='" + workshops[i].id+"'>"+workshops[i].name+"</option>");
			}
		});
	});
	
	// 提交按钮
	$("#submit").on("click", function(){

		var errors = [];

		// 所属车间
		workshopId =$('#workshopId option:selected').val();
		if (workshopId<1){
			errors.push('请选择所属车间');
		}
		
		// 班组名称
		var name = $('#name').val().trim();
		if(name == '') {
			errors.push('请输入班组名称');
		}
		
		// 有错误时结束处理。
		if (errors.length > 0) {
			showErrorMsg(errors);
			return;
		}

		// 电话
		var mobile = $('#mobile').val().trim();
		
		// 提交请求
		formPost("/admin/team/doAdd", {
			workshopId: workshopId,
			name: name,
			mobile:mobile
		});
	});
});