$(function(){

	// 声明数据表
	$('#device').DataTable({
		"paging": true,
//		"scrollY":        '100vh',
//        "scrollCollapse": true,
		"lengthChange": false,
//		"bProcessing":true,
		"iDisplayLength" : 20,// 每页显示行数
		"searching": false,
		"ordering": true,
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

	// 返回
	$("#back").on("click", function(){
		var searchCode = $('#searchCode').val().trim();
		var searchFactory = $('#searchFactory').val().trim();
		formPost("/admin/devicebasic/list",{code:searchCode,factory:searchFactory});
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
	
	$("#btnSearch").on("click", function(){
		var basicId = $('#basicId').val().trim();
		var depotId = $('#depotId option:selected').val();
		var workshopId = $('#workshopId option:selected').val();
		var teamId = $('#teamId option:selected').val();
		var from = $('#from').val().trim();
		// 提交请求
		formPost("/admin/device/list",{
			basicId:basicId,
			depotId:depotId,
			workshopId:workshopId,
			teamId:teamId,
			from:from
		});
	});
	
	$("#btnBatchEdit").on("click", function(){
		var basicId = $('#basicId').val().trim();
		var from = $('#from').val().trim();
		var $checked_row = $("[name=selected]").filter(":checked");
		var array = new Array(); 
		var i = 0; 
		 $checked_row.each(function () { 
			 array[i++] = $(this).attr("id"); 
		 }); 
		 var ids = array.join(",");
		 if (!ids) {
			 alert("请选择要修改的工具");
			 return;
		 }
		formPost("/admin/device/batchEdit",{basicId:basicId,from:from,strIds:ids});
	});
	
	// 批量增加
	$("#btnBatchAdd").on("click", function(){
		var basicId = $('#basicId').val().trim();
		var from = $('#from').val().trim();
		formPost("/admin/device/batchAdd",{basicId:basicId,from:from});
	});

	// 删除工具基本信息
	App.deleteDevice = function(id) {
		// 确认是否删除
		showConfirm("确定要删除此工具吗？", function() {
			// 实行
			ajaxPost("/admin/device/delete/" + id, {}, function(){
				// 删除成功
				showMessage("删除成功。", function(){
					var table = $('#device').DataTable();
					table.row($('.row'+ id)).remove().draw();
				});
			});
		});
	}
	
	// 批量增加
	$("#btnBatchDelete").on("click", function(){
		var $checked_row = $("[name=selected]").filter(":checked");
		var array = new Array(); 
		var i = 0; 
		 $checked_row.each(function () { 
			 array[i++] = $(this).attr("id"); 
		 }); 
		 var ids = array.join(",");
		 if (!ids) {
			 alert("请选择要批量删除的工具");
			 return;
		 }
		// 确认是否删除
		showConfirm("确定要批量删除这些工具吗？", function() {
			// 实行
			ajaxPost("/admin/device/delete/" + ids, {}, function(){
				// 删除成功
				showMessage("删除成功。", function(){
					var table = $('#device').DataTable();
					for (j=0;j<i;j++) {
						if (j==i-1) {
							table.row($('.row'+ array[j])).remove().draw();
						} else {
							table.row($('.row'+ array[j])).remove();
						}
						
					}
					$("[name=selected]:checkbox").attr("checked", false);
				});
			});
		});
	});

	// 修改工具
	App.edit = function(id,from) {
		formPost("/admin/device/edit/" + id,{from:from});
	}
	
	// 工具详细
	App.details = function(id) {
		formPost("/admin/device/list/",{id:id});
	}
});