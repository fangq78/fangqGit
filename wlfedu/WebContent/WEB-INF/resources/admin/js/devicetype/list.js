$(function(){

	// 声明数据表
	$('#deviceType').DataTable({
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

	// 查询物资编码
	$("#btnSearch").on("click", function(){
		App.search();
	});
	
	App.search=function() {
		var code = $('#code').val().trim();
		var name = $('#name').val().trim();
		formPost("/admin/devicetype/list",{
			code: code,
			name: name
		});
	}
	
	// 新增物资编码
	$("#btnAdd").on("click", function(){
		formPost("/admin/devicetype/add");
	});

	// 删除物资编码
	App.deleteDeviceType = function(code, name) {
		
		// 确认是否删除
		showConfirm("删除物资编码，其所属的工具也将同时被删除。<br>是否真的要删除物资编码【" + code + "】？", function() {

			// 实行
			ajaxPost("/admin/devicetype/delete/" + code, {}, function(){

				// 删除成功
				showMessage("删除成功。", function(){
					var table = $('#deviceType').DataTable();
					table.row($('.row'+ code)).remove().draw();
				});
			});
		});
	}

	// 修改物资编码
	App.editDeviceType = function(code) {
		formPost("/admin/devicetype/edit/" + code);
	}
});