<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
	<base id="base" href="${base}">
	<meta charset="utf-8" />
	<title>组织人员列表</title>
	<meta name="courses" content="管理人员列表" />
	<meta name="description" content="管理人员列表" />
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
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />
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
						<li class="active">管理员用户</li>
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
					<div class="table-header">管理员用户列表</div>
					<div class="table-responsive">
						<table id="adminuserlist-table"
							class="table table-striped table-bordered table-hover"
							style="width:100%">
							<thead style="width:100%">
								<tr>
									<th>UID</th>
                                    <th>用户名</th>
                                    <th>邮箱</th>
                                    <th>手机号</th>
                                    <th>注册时间</th>
                                    <th>状态</th>
                                    <th>用户类型</th>
                                    <th>头像</th>
                                    <th>有效开始</th>
                                    <th>失效时间</th>
				    				<th>昵称</th>
                                    <th>组织ID</th>
                                    <th>组织名称</th>
                                    <th>创建时间</th>
                                    <th>对应角色</th>
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
	<div id="user_role_def" class="hide">
	</div><!-- #dialog-table-def -->

	<!-- /.main-container -->
	<div id="user_role_menu" class="hide">
		<div id="roleMenuTree" class="tree"></div>
	</div><!-- #dialog-rolemenu -->
		
	<!-- 添加adminuser -->	
	<div id="addAdminUserDialog" class="hide" style="width:400px">
		<input type="hidden" name="th_id" id="th_id">
		<input type="hidden" name="th_code" id="th_code">
		<input type="hidden" id="th_uface1" value="">
			<dl>
			<dd class="clearfix" style="display: inline-block;">
				<strong>用户名：</strong> <input type="text" placeholder="请输入用户名"
					class="text" id="th_username" maxlength="255" style="width: 160px;">
				</select>
			</dd>
			<dd class="clearfix" style="display: inline-block;">
				<strong>密码：</strong> <input type="text" placeholder="请输入密码"
					class="text" id="th_password" maxlength="255" style="width: 160px;">
				</select>
			</dd>
			</dl>
			<dl>
			<dd class="clearfix" style="display: inline-block;">
				<strong>昵称：</strong> <input type="text" placeholder="请输入昵称"
					class="text" id="th_nickname" maxlength="255" style="width: 160px;">
				</select>
			</dd>
			<dd class="clearfix" style="display: inline-block;">
				<strong>手机号：</strong> <input type="text" placeholder="请输入手机号"
					class="text" id="th_mobilenum" maxlength="255" style="width: 160px;">
				</select>
			</dd>
			</dl>
			<dl>
			<dd class="clearfix" style="display: inline-block;">
				<strong>邮箱：</strong> <input type="text" placeholder="请输入邮箱"
					class="text" id="th_email" maxlength="255" style="width: 160px;">
				</select>
			</dd>
			<dl>
			<dd class="clearfix" style="display: inline-block;">
					<strong>组织：</strong>
						<select id="th_orgid" style="width: 160px">
									<!--<option value ="0">平台</option>-->
							<#list listOrg as o>
								<option value ="${o.id}">${o.name}</option>
							</#list>
					</select>
				</dd>
			</dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
				<form method="POST" id="imgfileform1">
					<strong>头像上传：</strong> <input type="file" name="imgfile" id="imgfile1"/>
				</form>
				</dd>
			</dl>
		</div>
		
	<!-- 修改adminUser  -->
	<div id="adminUserUpdateDialog" class="hide" style="width:400px">
	<input type="hidden" id="th_uface" value="">
		
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>用户名：</strong> <input type="text" placeholder="请输入userName"
						class="text" id="th_uusername" maxlength="255" style="width: 160px;">
					</select>
				</dd>
				<dd class="clearfix" style="display: inline-block;">
					<strong>密码：</strong> <input type="text" placeholder="请输入passWord"
						class="text" id="th_upassword" maxlength="255" style="width: 160px;">
					</select>
				</dd>
			</dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>昵称：</strong> <input type="text" placeholder="请输入nickName"
						class="text" id="th_unickname" maxlength="255" style="width: 160px;">
					</select>
				</dd>
				<dd class="clearfix" style="display: inline-block;">
					<strong>邮箱：</strong> <input type="text" placeholder="请输入email"
						class="text" id="th_uemail" maxlength="255" style="width: 160px;">
					</select>
				</dd>
			</dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>电话号码：</strong> <input type="text" placeholder="请输入mobileNum"
						class="text" id="th_umobilenum" maxlength="255" style="width: 160px;">
					</select>
				</dd>
				<dd class="clearfix" style="display: inline-block;">
					<strong>状态：</strong>
					 <select id="th_ustatus" style="width: 160px">
						<option value ="">全部</option>
						<option value ="Y">有效</option>
						<option value ="N">无效</option>
					</select>
				</dd>
			</dl>
			<dl>
			<dd class="clearfix" style="display: inline-block;">
					<strong>组织：</strong>
						<select id="th_orgid1" style="width: 160px">
								<!--<option value ="0">平台</option>-->
							<#list listOrg as o>
								<option value ="${o.id}">${o.name}</option>
							</#list>
					</select>
				</dd>
			</dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
				<form method="POST" id="imgfileform">
					<strong>头像上传：</strong> <input type="file" name="imgfile" id="imgfile"/>
				</form>
				</dd>
			</dl>
		</div>
		
	<!-- #dialog-confirm -->

	<div id="update_tablestatus" class="hide">
		<p class="bigger-110 bolder center grey">
			<i class="icon-hand-right blue bigger-120"></i> 确认?
		</p>
	</div>
	<!-- #dialog-confirm -->

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
	<script src="${base}/assets/js/jquery.hotkeys.min.js"></script>
	<script src="${base}/assets/js/bootstrap-wysiwyg.js"></script>
	<script src="${base}/assets/js/bootbox.min.js"></script>
	<script src="${base}/assets/js/jquery.form.js" type="text/javascript"></script>
	<script src="${base}/js/fuelux.menutree.js"></script>
	<script src="${base}/assets/js/fuelux/fuelux.tree.min.js"></script>
	<!--my javascript-->
	<script src="${base}/js/adminuser_list.js"></script>

	<!-- inline scripts related to this page -->
	<script type="text/javascript">
			var catgidlist = [];
			jQuery(function($) {
				var curpage = "${curpage!''}";
				var cp = $(".nav-list a[href='"+curpage+"']");
				cp.parent().attr("class","active");
				cp.parent().parent().parent().attr("class","active open");				
			});
			
			$('[data-toggle="buttons"] .btn').on('click', function(e){
				var target = $(this).find('input[type=radio]');
				var which = parseInt(target.val());
				var toolbar = $('#editor1').prev().get(0);
				if(which == 1 || which == 2 || which == 3) {
					toolbar.className = toolbar.className.replace(/wysiwyg\-style(1|2)/g , '');
					if(which == 1) $(toolbar).addClass('wysiwyg-style1');
					else if(which == 2) $(toolbar).addClass('wysiwyg-style2');
				}
			});
			
			//Add Image Resize Functionality to Chrome and Safari
	//webkit browsers don't have image resize functionality when content is editable
	//so let's add something using jQuery UI resizable
	//another option would be opening a dialog for user to enter dimensions.
	if ( typeof jQuery.ui !== 'undefined' && /applewebkit/.test(navigator.userAgent.toLowerCase()) ) {
		
		var lastResizableImg = null;
		function destroyResizable() {
			if(lastResizableImg == null) return;
			lastResizableImg.resizable( "destroy" );
			lastResizableImg.removeData('resizable');
			lastResizableImg = null;
		}

		var enableImageResize = function() {
			$('.wysiwyg-editor')
			.on('mousedown', function(e) {
				var target = $(e.target);
				if( e.target instanceof HTMLImageElement ) {
					if( !target.data('resizable') ) {
						target.resizable({
							aspectRatio: e.target.width / e.target.height,
						});
						target.data('resizable', true);
						
						if( lastResizableImg != null ) {//disable previous resizable image
							lastResizableImg.resizable( "destroy" );
							lastResizableImg.removeData('resizable');
						}
						lastResizableImg = target;
					}
				}
			})
			.on('click', function(e) {
				if( lastResizableImg != null && !(e.target instanceof HTMLImageElement) ) {
					destroyResizable();
				}
			})
			.on('keydown', function() {
				destroyResizable();
			});
	    }
		
		enableImageResize();

		/**
		//or we can load the jQuery UI dynamically only if needed
		if (typeof jQuery.ui !== 'undefined') enableImageResize();
		else {//load jQuery UI if not loaded
			$.getScript($path_assets+"/js/jquery-ui-1.10.3.custom.min.js", function(data, textStatus, jqxhr) {
				if('ontouchend' in document) {//also load touch-punch for touch devices
					$.getScript($path_assets+"/js/jquery.ui.touch-punch.min.js", function(data, textStatus, jqxhr) {
						enableImageResize();
					});
				} else	enableImageResize();
			});
		}
		*/
	}
		</script>
</body>
</html>
