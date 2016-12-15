$(function(){

	// 返回
	$("#back").on("click", function(){
		formPost("/admin/manageruser/list");
	});

	$('#type').on("change",function(){
		var type = $('#type option:selected').val();
		$('#depotId option').each(function(){
			if( $(this).val() != '0'){
			 $(this).remove();
			}
		});
		$('#workshopId option').each(function(){
			if( $(this).val() != '0'){
			 $(this).remove();
			}
		});
		//车间管理员
		if (type==1) {
			ajaxPost("/admin/team/ajaxDepots",{},function(data){
				depots = data.depots;
				for (var i = 0 ; i < depots.length; i++) {
					$("#depotId").append("<option value='" + depots[i].id+"'>"+depots[i].name+"</option>");
				}
			});
			$("#depotgroup").show();
			$("#workshopgroup").show();
		} else {
			$("#depotgroup").hide();
			$("#workshopgroup").hide();
		}
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

		// 用户名
		var name = $('#name').val().trim();
		if(name == '') {
			errors.push('请输入用户名');
		} 

		var type = $('#type option:selected').val();
		var depotId = $('#depotId option:selected').val();
		var workshopId = $('#workshopId option:selected').val();
		if (type==1) {
			if (depotId<1) {
				errors.push('请输入所属段');
			}
			if (workshopId<1) {
				errors.push('请输入所属车间');
			}
		}
		
		// 有错误时结束处理。
		if (errors.length > 0) {
			showErrorMsg(errors);
			return;
		}
		
		// 提交请求
		formPost("/admin/manageruser/doEdit", {
			id: $("#id").val(),
			name: $('#name').val().trim(),
			type: $('#type').val(),
			depotId:depotId,
			workshopId:workshopId,
			mobile: $('#mobile').val().trim()
		});
	});
});