$(function(){

	// 声明数据表
	$('#mainTbl').DataTable({
		"paging": true,
		"lengthChange": false,
		"searching": false,
		"ordering": true,
		"info": true,
		"iDisplayLength" : 20,// 每页显示行数
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
			$("#divSS").hide();
			$("#divWS").show();
		} else {
			$("#depotgroup").hide();
			$("#workshopgroup").hide();
			$("#divSS").show();
			$("#divWS").hide();
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
	
	// 查询系统用户
	$("#btnSearch1").on("click", function(){
		App.search();
	});
	
	$("#btnSearch2").on("click", function(){
		App.search();
	});
	
	App.search=function() {
		var depotId = $('#depotId option:selected').val();
		var workshopId = $('#workshopId option:selected').val();
		formPost("/admin/manageruser/list",{
			name: $('#name').val().trim(),
			type: $('#type').val(),
			depotId:depotId,
			workshopId:workshopId
		});
	}

	// 新增系统用户
	$("#btnAdd").on("click", function(){
		formPost("/admin/manageruser/add");
	});

	// 删除礼品
	App.deleteUser = function(id, name) {
		// 确认是否删除
		showConfirm("删除系统用户有可能造成未知问题。<br>是否真的要删除系统用户【" + name + "】么？", function() {
			
			// 实行
			ajaxPost("/admin/manageruser/delete/" + id, {}, function(data){
				if (data.duplicate!=null) {
					alert("不能删除正在登陆的管理员！");
					return;
				}
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
		formPost("/admin/manageruser/edit/" + id);
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
		ajaxPost("/admin/manageruser/password", param, function(){

			alert("设置成功。");
			$("#pwdModal").modal("hide");

		}, function(data) {
			alert((data.errorMsg) ? data.errorMsg : "请求失败。");
		});
	});
});