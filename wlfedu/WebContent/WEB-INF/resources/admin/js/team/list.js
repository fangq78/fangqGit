$(function(){

	// 声明数据表
	$('#team').DataTable({
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

	$("#btnSearch").on("click", function(){
		var depotId = $('#depotId option:selected').val();
		var workshopId = $('#workshopId option:selected').val();
		// 提交请求
		formPost("/admin/team/list",{
			depotId:depotId,
			workshopId:workshopId
		});
	});
	
	$('#depotId').on("change",function(){
		$('#workshopId option').each(function(){
			if( $(this).val() != '0'){
			 $(this).remove();
			}
		});
		var depotId = $('#depotId option:selected').val();
		if(depotId>0) {
			ajaxPost("/admin/team/ajaxWorkshops",{depotId:depotId},function(data){
				workshops = data.workshops;
				for (var i = 0 ; i < workshops.length; i++) {
					$("#workshopId").append("<option value='" + workshops[i].id+"'>"+workshops[i].name+"</option>");
				}
			});
		}
	});
	
	// 新增班组
	$("#btnAdd").on("click", function(){
		formPost("/admin/team/add");
	});

	// 删除班组
	App.deleteTeam = function(id, name) {
		
		// 确认是否删除
		showConfirm("删除班组会使使用该班组的页面无法正常使用。<br>是否真的要删除班组【" + name + "】？", function() {

			// 实行
			ajaxPost("/admin/team/delete/" + id, {}, function(){

				// 删除成功
				showMessage("删除成功。", function(){
					var table = $('#team').DataTable();
					table.row($('.row'+ id)).remove().draw();
				});
			});
		});
	}

	// 修改班组
	App.editTeam = function(id) {
		formPost("/admin/team/edit/" + id);
	}
});