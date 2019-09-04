<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
	<base id="base" href="${base}">
	<meta charset="utf-8" />
	<title>医生列表</title>
	<meta name="courses" content="医生列表" />
	<meta name="description" content="医生列表" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />

	<!-- basic styles -->
	<link rel="stylesheet" href="${base}/assets/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${base}/assets/css/font-awesome.min.css" />
	<!--[if IE 7]>
	<link rel="stylesheet" href="${base}/assets/css/font-awesome-ie7.min.css" />
	<![endif]-->
	<link rel="stylesheet" href="${base}/assets/css/jquery-ui-1.10.3.full.min.css" />
	<!-- page specific plugin styles -->

	<!-- fonts -->
	<!--<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />-->
	<!-- ace styles -->

	<link rel="stylesheet" href="${base}/assets/css/ace.min.css" />
	<link rel="stylesheet" href="${base}/assets/css/ace-rtl.min.css" />
	<link rel="stylesheet" href="${base}/assets/css/ace-skins.min.css" />
	<link rel="stylesheet" href="${base}/assets/css/jquery.timepicker.css" />
	<link rel="stylesheet" href="${base}/assets/css/jquery-ui-timepicker-addon-1.6.3.css" />

	<!--[if lte IE 8]>
	<link rel="stylesheet" href="${base}/assets/css/ace-ie.min.css" />
	<![endif]-->

	<!-- inline styles related to this page -->
	<!-- ace settings handler -->
	<script src="${base}/assets/js/ace-extra.min.js"></script>

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
		<script src="${base}/assets/js/html5shiv.js"></script>
		<script src="${base}/assets/js/respond.min.js"></script>
	<![endif]-->
</head>

<body>
	<#include 'nav_ace.ftl'/>
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>
		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#"> <span
				class="menu-text"></span>
			</a>

			<div class="sidebar" id="sidebar"><#include 'menu_ace.ftl'/></div>

			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

					<ul class="breadcrumb">
						<li><i class="icon-home home-icon"></i> <a href="#">主页</a></li>
						<li class="active">医生</li>
					</ul>
					<!-- .breadcrumb -->

					<div class="nav-search" id="nav-search">
						<form class="form-search">
							<span class="input-icon"> <input type="text"
								placeholder="Search ..." class="nav-search-input"
								id="nav-search-input" autocomplete="off" /> <i
								class="icon-search nav-search-icon"></i>
							</span>
						</form>
					</div>
					<!-- #nav-search -->
				</div>

				<div class="page-content">
					<div class="table-header">医生列表</div>
					<div class="table-responsive">
						<table id="stafflist-table"
							class="table table-striped table-bordered table-hover"
							style="width:100%">
							<thead style="width:100%">
								<tr>
									<th>ID</th>
                                    <th>编码</th>
                                    <th>医生职工号</th>
                                    <th>姓名</th>
                                    <th>性别</th>
                                    <th>生日</th>
                                    <th>电话</th>
                                    <th>类型ID</th>
                                    <th>类型名称</th>
                                    <th>部门ID</th>                                     
                                    <th>部门名称</th>                                     
                                    <th>组织ID</th>                                      
                                    <th>组织名称</th>                                      
                                    <th>身份证</th>
                                    <th>驾驶证注册时间</th>
                                    <th>状态</th>
                                    <th>服务状态</th>
                                    <th>staffDepName</th>
                                    <th>头像</th>
                                    <th>备忘录</th> 
                                    <th>创建时间</th>
                                    <th>操作</th>        
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="5">从服务端载入数据...</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<!-- /.page-content -->
			</div>
			<!-- /.main-content -->

		</div>
		<!-- /.main-container-inner -->

		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="icon-double-angle-up icon-only bigger-110"></i>
		</a>
	</div>
	<!-- /.main-container -->

	<div id="staffEditDialog" class="hide" style="width:400px">
		<input type="hidden" id="th_headiconurl" value="">
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>姓名：</strong> 
					<input type="text" placeholder="请输入姓名" class="text" id="th_name" maxlength="255" style="width: 160px;">
					
				</dd>
				<dd class="clearfix" style="display: inline-block;">
					<strong>性别：</strong> 
					<select id="th_sex" style="width: 160px">
						<option value ="M">男</option>
						<option value ="F">女</option>
					</select>
				</dd>
			</dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>手机号码：</strong> 
					<input type="text" placeholder="请输入手机号码" class="text" id="th_mobileNum" maxlength="255" style="width: 160px;">
				</dd>
			</dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>生日：</strong> 
					<input type="text" placeholder="请输入生日" class="text" id="th_birthday" maxlength="255" style="width: 160px;">
				</dd>
				<dd class="clearfix" style="display: inline-block;">
					<strong>类别：</strong>
					<select id="th_typeid" style="width: 160px">
						<#list refVlByRefId as r>
							<option value ="${r.id}">${r.name}</option>
						</#list>
					</select>
				</dd>
			</dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>组织：</strong>
						<select id="th_orgid" style="width: 160px">
							<#list listOrg as o>
								<option value ="${o.id}">${o.name}</option>
							</#list>
						</select>
				</dd>
				<dd class="clearfix" style="display: inline-block;">
					<strong>部门：</strong>
						<select id="th_deptid" style="width: 160px">
							<option value =""></option>
						</select>
				</dd>
			
			</dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>身份证：</strong> 
					<input type="text" placeholder="请输入身份证" class="text" id="th_idcard" maxlength="255" style="width: 160px;">
				</dd>
				<dd class="clearfix" style="display: inline-block;">
					<strong>备注：</strong> 
					<input type="text" placeholder="请输入备注" class="text" id="th_memo" maxlength="255" style="width: 160px;">
				</dd>
			</dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
				<form method="POST" id="imgfileform">
					<strong>头图上传：</strong> <input type="file" name="imgfile" id="imgfile"/>
				</form>
				</dd>
			</dl>
		</div>

	<!-- basic scripts -->

	<!--[if !IE]> -->
	<script src="${base}/assets/js/jquery-2.0.3.min.js"></script>
	<!-- <![endif]-->

	<!--[if IE]>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<![endif]-->
	<!--[if !IE]> -->

	<script type="text/javascript">
			window.jQuery || document.write("<script src='${base}/assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
	</script>

	<!-- <![endif]-->

	<!--[if IE]>
	<script type="text/javascript">
	 window.jQuery || document.write("<script src='${base}/assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
	</script>
	<![endif]-->

	<script src="${base}/assets/js/jquery-ui-1.10.3.full.min.js"></script>
	<script src="${base}/assets/js/jquery.timepicker.min.js"></script>
	<script src="${base}/assets/js/jquery-ui-timepicker-addon-1.6.3.js"></script>
	<script type="text/javascript">
		if("ontouchend" in document) document.write("<script src='${base}/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
	</script>
	<script src="${base}/assets/js/bootstrap.min.js"></script>
	<script src="${base}/assets/js/typeahead-bs2.min.js"></script>

	<!-- page specific plugin scripts -->
	<script src="${base}/assets/js/jquery.dataTables.min.js"></script>

	<!-- ace scripts -->
	<script src="${base}/assets/js/ace-elements.min.js"></script>
	<script src="${base}/assets/js/ace.min.js"></script>
	<!--图文编辑  -->
	<script src="${base}/assets/js/jquery.hotkeys.min.js"></script>
	<script src="${base}/assets/js/bootstrap-wysiwyg.js"></script>
	<script src="${base}/assets/js/bootbox.min.js"></script>
	<!-- 异步上传图片 -->
	<script src="${base}/assets/js/jquery.form.js" type="text/javascript"></script>
	
	<!--my javascript-->
	<script src="${base}/js/staff_list.js"></script>

	<!-- inline scripts related to this page -->
	<script type="text/javascript">
			var catgidlist = [];
			jQuery(function($) {
				var curpage = "${curpage!''}";
				var cp = $(".nav-list a[href='"+curpage+"']");
				cp.parent().attr("class","active");
				cp.parent().parent().parent().attr("class","active open");				
			});
		</script>
</body>
</html>
