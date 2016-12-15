<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/admin/css/system_menu.css">
<aside class="main-sidebar">
	<section class="sidebar">
		<ul class="sidebar-menu">
			<li class="treeview">
				<a href="#">
					<i class="fa fa-fw fa-bank"></i>
					<span>组织管理</span>
					<i class="fa fa-angle-left pull-right"></i>
				</a>
				<ul class="treeview-menu">
					<li class="A1001"><a href="${pageContext.request.contextPath}/admin/depot/list"><i class="fa fa-angle-double-right"></i>段信息</a></li>
					<li class="A1101"><a href="${pageContext.request.contextPath}/admin/workshop/list"><i class="fa fa-angle-double-right"></i>车间信息</a></li>
					<li class="A1201"><a href="${pageContext.request.contextPath}/admin/team/list"><i class="fa fa-angle-double-right"></i>班组信息</a></li>
				</ul>
			</li>
			<li class="treeview">
				<a href="#">
					<i class="fa fa-fw fa-users"></i>
					<span>用户管理</span>
					<i class="fa fa-angle-left pull-right"></i>
				</a>
				<ul class="treeview-menu">
					<li class="A2001"><a href="${pageContext.request.contextPath}/admin/manageruser/list"><i class="fa fa-angle-double-right"></i>管理员信息</a></li>
					<li class="A2101"><a href="${pageContext.request.contextPath}/admin/teamuser/list"><i class="fa fa-angle-double-right"></i>班组人员信息</a></li>
				</ul>
			</li>
			<li class="treeview">
				<a href="#">
					<i class="fa fa-fw fa-gavel"></i>
					<span>工具管理</span>
					<i class="fa fa-angle-left pull-right"></i>
				</a>
				<ul class="treeview-menu">
					<li class="A3001"><a href="${pageContext.request.contextPath}/admin/devicetype/list"><i class="fa fa-angle-double-right"></i>物资编码信息</a></li>
					<li class="A3101"><a href="${pageContext.request.contextPath}/admin/devicebasic/list"><i class="fa fa-angle-double-right"></i>工具基本信息</a></li>
					<li class="A3102"><a href="${pageContext.request.contextPath}/admin/device/worklist?id=-1"><i class="fa fa-angle-double-right"></i>工具出工信息</a></li>
				</ul>
			</li>
	<!-- 		<li class="treeview">
				<a href="#">
					<i class="fa fa-fw fa-globe"></i>
					<span>院校管理</span>
					<i class="fa fa-angle-left pull-right"></i>
				</a>
				<ul class="treeview-menu">
					<li class="A20011"><a href="${pageContext.request.contextPath}/admin/community/list"><i class="fa fa-circle-o"></i>社区院校管理</a></li>
					<li class="A21011"><a href="${pageContext.request.contextPath}/admin/systemuser/list"><i class="fa fa-circle-o"></i>社区管理员</a></li>
					<li class="A22011"><a href="${pageContext.request.contextPath}/admin/user/list"><i class="fa fa-circle-o"></i>学员管理</a></li>
					<li class="A23011"><a href="${pageContext.request.contextPath}/admin/manager/list"><i class="fa fa-circle-o"></i>院校管理权限</a></li>
				</ul>
			</li>
			<li class="treeview">
				<a href="#">
					<i class="fa fa-fw fa-envelope-o"></i>
					<span>通知公告</span>
					<i class="fa fa-angle-left pull-right"></i>
				</a>
				<ul class="treeview-menu">
					<li class="A30011"><a href="${pageContext.request.contextPath}/admin/notice/list"><i class="fa fa-circle-o"></i>公告管理</a></li>
				</ul>
			</li>
			<li class="treeview">
				<a href="#">
					<i class="fa fa-fw fa-file"></i>
					<span>文章管理</span>
					<i class="fa fa-angle-left pull-right"></i>
				</a>
				<ul class="treeview-menu">
					<li class="A4001"><a href="${pageContext.request.contextPath}/admin/category/list"><i class="fa fa-circle-o"></i>文章分类管理</a></li>
				</ul>
			</li>
			<li class="treeview">
				<a href="#">
					<i class="fa fa-fw fa-mortar-board"></i>
					<span>课程管理</span>
					<i class="fa fa-angle-left pull-right"></i>
				</a>
				<ul class="treeview-menu">
					<li class="A5001"><a href="${pageContext.request.contextPath}/admin/channel/list"><i class="fa fa-circle-o"></i>频道管理</a></li>
					<li class="A5101"><a href="${pageContext.request.contextPath}/admin/course/list"><i class="fa fa-circle-o"></i>课程管理</a></li>
				</ul>
			</li>
			<li class="treeview">
				<a href="#">
					<i class="fa fa-fw fa-send"></i>
					<span>广告活动</span>
					<i class="fa fa-angle-left pull-right"></i>
				</a>
				<ul class="treeview-menu">
					<li class="A6001"><a href="${pageContext.request.contextPath}/admin/advert/list"><i class="fa fa-circle-o"></i>广告管理</a></li>
					<li class="A6101"><a href="${pageContext.request.contextPath}/admin/item/list"><i class="fa fa-circle-o"></i>礼品管理</a></li>
					<li class="A6201"><a href="${pageContext.request.contextPath}/admin/item/changelist"><i class="fa fa-circle-o"></i>兑换记录</a></li>
					<li class="A6301"><a href="${pageContext.request.contextPath}/admin/item/change"><i class="fa fa-circle-o"></i>兑换礼品</a></li>
				</ul>
			</li>
			<li class="treeview">
				<a href="#">
					<i class="fa fa-fw fa-bar-chart"></i>
					<span>统计查询</span>
					<i class="fa fa-angle-left pull-right"></i>
				</a>
				<ul class="treeview-menu">
					<li class="A7001"><a href="${pageContext.request.contextPath}/admin/report/userstudy"><i class="fa fa-circle-o"></i>学员学习报表</a></li>
					<li class="A7002"><a href="${pageContext.request.contextPath}/admin/report/itemchange"><i class="fa fa-circle-o"></i>礼品领取报表</a></li>
					<li class="A7003"><a href="${pageContext.request.contextPath}/admin/report/advertclicks"><i class="fa fa-circle-o"></i>广告点击报表</a></li>
				</ul>
			</li>  --> 
		</ul>
	</section>
</aside>