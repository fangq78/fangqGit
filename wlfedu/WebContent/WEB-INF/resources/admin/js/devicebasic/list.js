$(function(){

	// 声明数据表
	$('#basic').DataTable({
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

	$("#code").autocomplete({
		source: function( request, response ) {
			//3.获取到输入的内容之后，就要通过ajax传给后台
			$.ajax({
				url: App.ContextPath +"/admin/devicetype/ajaxDeviceTypes",
				dataType: "json",
                data: {
                	limit: 10,
                    code: request.term
                },
                success: function(data) {
                	$(".ui-helper-hidden-accessible").children().remove();
                	response($.map(data.deviceTypes, function(item) {
                		return { label:item.code +'-' + item.name , value:item.code  }
                	}));
                }
			});
		},        
		search: function() {
          // custom minLength
		  var code = $.trim($("#code").val());
          if ( code.length < 5 ) {
            return false;
          }
        },
        focus: function() {
          // prevent value inserted on focus
          return false;
        },
        select: function( event, ui ) {
        	$("#code").val(ui.item.label);
        },
        minLength: 2, 
        autoFocus: false, 
        delay: 500 
	});

//	//1.页面加载之后，找到文本框的内容对它触发一个事件
//	$("#code").keyup(function (){
//		//2.获取到文本框的内容,注意去空格
//		var code = $.trim($("#code").val());
//		if (code.length<5) {
//			$("#dcodes").hide();
//			return;
//		}
//		//3.获取到输入的内容之后，就要通过ajax传给后台
//		ajaxPost("/admin/devicetype/ajaxDeviceTypes",{code:code},function(data){
//			deviceTypes = data.deviceTypes;
//			//显示展示div,把它清空
//			$("#dcodes").show().html("");
//			if (data == "") {
//				//$("#dcodes").text("没有相关数据!");
//			} else {
//				for (var i = 0 ; i < deviceTypes.length; i++) {        
//					$("#dcodes").append("<li>"+ deviceTypes[i].code+"</li>");//内层循环追加li
//				}   
//				//4.鼠标移上去之后，加一个背景
//				$("li").hover(function () {
//					$(this).addClass("li1");
//				}, function () {
//					$(this).removeClass("li1");
//				});
//			}
//		});
//	});
	
	$("#btnSearch").on("click", function(){
		var code = $('#code').val().trim();
		var factory = $('#factory').val().trim();
		// 提交请求
		formPost("/admin/devicebasic/list",{
			code:code,
			factory:factory
		});
	});
	
	// 新增工具基本信息
	$("#btnAdd").on("click", function(){
		formPost("/admin/devicebasic/add");
	});

	// 删除工具基本信息
	App.deleteBasic = function(id) {
		
		// 确认是否删除
		showConfirm("该删除操作会同时将工具详细信息删除！<br>是否真的要删除工具基本信息？", function() {

			// 实行
			ajaxPost("/admin/devicebasic/delete/" + id, {}, function(){

				// 删除成功
				showMessage("删除成功。", function(){
					var table = $('#basic').DataTable();
					table.row($('.row'+ id)).remove().draw();
				});
			});
		});
	}

	// 修改基本信息
	App.editBasic = function(id) {
		formPost("/admin/devicebasic/edit/" + id);
	}
	
	// 工具详细
	App.details = function(basicId) {
		var code = $('#code').val().trim();
		var factory = $('#factory').val().trim();
		formPost("/admin/device/list/",{basicId:basicId,from:"basic",searchCode:code,searchFactory:factory});
	}
});