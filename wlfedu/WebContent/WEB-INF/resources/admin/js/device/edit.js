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
		
		editIds = $('#editIds').val().trim();
		
		basicId = $('#basicId').val().trim();
		
		// 物资编码
		code =$('#code').val().trim();
		if (code == '' || code == '0'){
			errors.push('请输入物资编码');
		}
		
		// 物资编码名称
		code =$('#codeName').val().trim();
		if (code == ''){
			errors.push('请输入物资编码名称');
		}
		
		// 生产厂家
		var factory = $('#factory').val().trim();
		if(factory == '') {
			errors.push('请输入生产厂家');
		}
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
		// 有错误时结束处理。
		if (errors.length > 0) {
			showErrorMsg(errors);
			return;
		}

		// 提交请求
		formPost("/admin/device/doEdit", {
			basicId: basicId,
			name: name,
			depotId:depotId,
			workshopId:workshopId,
			teamId: teamId,
			strIds:editIds
		});
	});
});