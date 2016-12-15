$(function(){

	// 返回
	$("#back").on("click", function(){
		formPost("/admin/teamuser/list");
	});

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
	
	// 提交按钮
	$("#submit").on("click", function(){

		var errors = [];

		// 用户名
		var name = $('#name').val().trim();
		if(name == '') {
			errors.push('请输入用户登录名');
		} 
		
		// 用户名
		var realname = $('#realName').val().trim();
		if(realname == '') {
			errors.push('请输入用户名');
		} 
		
		// 登录密码
		var password = $('#password').val().trim();
		if(password == '') {
			errors.push('请输入登录密码');
		}

		var depotId = $('#depotId option:selected').val();
		var workshopId = $('#workshopId option:selected').val();
		var teamId = $('#teamId option:selected').val();
		if (depotId<1) {
			errors.push('请输入所属段');
		}
		if (workshopId<1) {
			errors.push('请输入所属车间');
		}
		if (teamId<1) {
			errors.push('请输入所属班组');
		}
		
		if (name!='') {
			ajaxPost("/admin/teamuser/ajaxCheckName",{name:name},function(data){
				has = data.has;
				if(has==1) {
					errors.push('用户登录名不能重复');
					showErrorMsg(errors);
					return;
				} else {
					// 有错误时结束处理。
					if (errors.length > 0) {
						showErrorMsg(errors);
						return;
					} else {
						// 提交请求
						formPost("/admin/teamuser/doAdd", {
							name: name,
							realName: realname,
							password: password,
							depotId:depotId,
							workshopId:workshopId,
							teamId:teamId,
							mobile: $('#mobile').val().trim()
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