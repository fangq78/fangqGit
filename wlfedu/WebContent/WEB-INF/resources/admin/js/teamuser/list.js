$(function(){

	// 声明数据表
	$('#mainTbl').DataTable({
		"paging": true,
		"lengthChange": false,
		"searching": false,
		"ordering": true,
		"iDisplayLength" : 20,// 每页显示行数
		"info": true,
		"autoWidth": false,
		"language": {
			"info": "共_TOTAL_个结果，当前(_START_～_END_)",
			"infoEmpty": "没有符合条件的记录",
			"paginate": {
				"previous": "前页",
				"next": "次页"
			}
		}
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
	
	// 查询系统用户
	$("#btnSearch").on("click", function(){
		App.search();
	});
	
	App.search=function() {
		var depotId = $('#depotId option:selected').val();
		var workshopId = $('#workshopId option:selected').val();
		var teamId = $('#teamId option:selected').val();
		formPost("/admin/teamuser/list",{
			name: $('#name').val().trim(),
			depotId:depotId,
			workshopId:workshopId,
			teamId:teamId
		});
	}

	// 新增系统用户
	$("#btnAdd").on("click", function(){
		formPost("/admin/teamuser/add");
	});

	// 删除礼品
	App.deleteUser = function(id, name) {
		// 确认是否删除
		showConfirm("删除班组人员可能造成未知问题。<br>是否真的要删除班组人员【" + name + "】么？", function() {
			
			// 实行
			ajaxPost("/admin/teamuser/delete/" + id, {}, function(){
				// 删除成功
				showMessage("删除成功。", function(){
					var table = $('#mainTbl').DataTable();
					table.row($('.row'+ id)).remove().draw();
				});
			});
		});
	}

	// 修改系统用户
	App.editUser = function(id) {
		formPost("/admin/teamuser/edit/" + id);
	}

	// 设置密码对话框,
	App.showModal = function(id) {
		$("#pwdModal").modal("show");
		$(".user-id").val(id);
	};

	// 设置密码
	$('.set-password').on("click", function(e){

		var password = $("#password").val().trim();
		var re_password = $("#re-password").val().trim();

		if (password == '' || re_password == '') {
			alert("请输入密码。");
			return;
		}
		if (password != re_password) {
			alert("密码不一致。");
			return;
		}
		
		// 更新密码
		var param = {
			id : $('#pwdModal .user-id').val(),
			password: password
		};
		ajaxPost("/admin/teamuser/password", param, function(){

			alert("设置成功。");
			$("#pwdModal").modal("hide");

		}, function(data) {
			alert((data.errorMsg) ? data.errorMsg : "请求失败。");
		});
	});
});