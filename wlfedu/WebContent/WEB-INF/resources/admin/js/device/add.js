$(function(){

	$('#depotId').on("change",function(){
		$('#workshopId option').each(function(){
			if( $(this).val() != '0'){
			 $(this).remove();
			}
		});
		$('#teamId option').each(function(){
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
	
	$('#workshopId').on("change",function(){
		$('#teamId option').each(function(){
			if( $(this).val() != '0'){
			 $(this).remove();
			}
		});
		var workshopId = $('#workshopId option:selected').val();
		ajaxPost("/admin/team/ajaxTeams",{workshopId:workshopId},function(data){
			teams = data.teams;
			for (var i = 0 ; i < teams.length; i++) {
				$("#teamId").append("<option value='" + teams[i].id+"'>"+teams[i].name+"</option>");
			}
		});
	});
	
	// 返回
	$("#back").on("click", function(){
		var basicId = $('#basicId').val().trim();
		var from = $('#from').val().trim();
		// 提交请求
		formPost("/admin/device/list",{
			basicId:basicId,
			from:from
		});
	});

	// 提交按钮
	$("#submit").on("click", function(){

		var errors = [];

		var depotId = $('#depotId option:selected').val();
		var workshopId = $('#workshopId option:selected').val();
		var teamId = $('#teamId option:selected').val();
		if (depotId<1) {
			errors.push('请选择所属段');
		}
		if (workshopId<1) {
			errors.push('请选择所属车间');
		}
		if (teamId<1) {
			errors.push('请选择所属班组');
		}
		
		// 工具名称
		var name = $('#name').val().trim();
		if(name == '') {
			errors.push('请输入工具名称');
		}
		
		// 数量
		var count = $('#count').val().trim();
		if(count == '') {
			errors.push('请输入数量');
		}
		if (isNaN(count)) {
			errors.push('请输入数字');
		}
		
		// 基本信息
		var basicId = $('#basicId').val().trim();
		
		var from = $('#from').val().trim();
		
		// 有错误时结束处理。
		if (errors.length > 0) {
			showErrorMsg(errors);
			return;
		}

		
		// 提交请求
		formPost("/admin/device/doBatchAdd", {
			basicId:basicId,
			name:name,
			depotId:depotId,
			workshopId:workshopId,
			teamId:teamId,
			from:from,
			count:count
		});
	});
});