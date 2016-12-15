$(function(){
	ajaxPost("/admin/category/menu",{}, function(data){
		// 菜单做成
		var menuList = data.menu;
		for (var i = 0 ; i < menuList.length; i++) {
			var path = "";
			if (menuList[i].type == 0)  {
				path = '/admin/graphic/list/';
			} else {
				path = '/admin/photos/list/';
			}
			var li = '<li class="' + menuList[i].code + '"><a href="' + App.ContextPath + path + menuList[i].code + ' "><i class="fa fa-circle-o"></i>' + menuList[i].name + '</a></li>';
			$("#menu" + menuList[i].type).append(li);
		}
		$('.' + App.MenuCode).addClass("active").parents(".treeview").addClass("active");
	});
});