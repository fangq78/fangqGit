<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<header class="main-header">
	<span class="logo"> 
		<span class="logo-lg"><b>铁路盘点系统</b></span>
	</span>
	<nav class="navbar navbar-static-top">
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
				<li class="dropdown user user-menu">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<i class="fa fa-fw fa-user"></i>
						<span class="hidden-xs">${sessionScope.system_user.name} </span>
					</a>
				</li>
				<li>
					<a href="#" data-toggle="control-sidebar">
						<i class="fa fa-fw fa-sign-out system-sign-out" title="退出登录"></i>
					</a>
				</li>
			</ul>
		</div>
	</nav>
</header>