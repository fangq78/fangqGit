$(function(){

	// 声明数据表
	$('#workshop').DataTable({
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
		// 提交请求
		formPost("/admin/workshop/list",{
			depotId:depotId
		});
	});
	
	// 新增车间
	$("#btnAdd").on("click", function(){
		formPost("/admin/workshop/add");
	});

	// 删除车间
	App.deleteWorkshop = function(id, name) {
		
		// 确认是否删除
		showConfirm("删除车间会使使用该车间的页面无法正常使用。<br>是否真的要删除车间【" + name + "】？", function() {

			// 实行
			ajaxPost("/admin/workshop/delete/" + id, {}, function(){

				// 删除成功
				showMessage("删除成功。", function(){
					var table = $('#workshop').DataTable();
					table.row($('.row'+ id)).remove().draw();
				});
			});
		});
	}

	// 修改频道
	App.editWorkshop = function(id) {
		formPost("/admin/workshop/edit/" + id);
	}
});