$(function(){

	// 声明数据表
	$('#depot').DataTable({
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

	// 新增频道
	$("#btnAdd").on("click", function(){
		formPost("/admin/depot/add");
	});

	// 删除频道
	App.deleteDepot = function(id, name) {
		
		// 确认是否删除
		showConfirm("删除段会使使用该段的页面无法正常使用。<br>是否真的要删除段【" + name + "】？", function() {

			// 实行
			ajaxPost("/admin/depot/delete/" + id, {}, function(){

				// 删除成功
				showMessage("删除成功。", function(){
					var table = $('#depot').DataTable();
					table.row($('.row'+ id)).remove().draw();
				});
			});
		});
	}

	// 修改频道
	App.editDepot = function(id) {
		formPost("/admin/depot/edit/" + id);
	}
});