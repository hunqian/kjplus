<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
	<base id="base" href="${base}">
	<meta charset="utf-8" />
	<title>微信账号列表</title>
	<meta name="courses" content="微信账号列表" />
	<meta name="description" content="微信账号列表" />
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
						<li class="active">微信</li>
					</ul>
					<!-- .breadcrumb -->
				</div>

				<div class="page-content">
					<div class="table-header">微信账户列表</div>
					<div class="table-responsive">
						<table id="wxacclist-table"
							class="table table-striped table-bordered table-hover"
							style="width:100%">
							<thead style="width:100%">
								<tr>
									<th>ID</th>
                                    <th>名称</th>
                                    <th>昵称</th>
                                    <th>URL</th>
                                    <th>编码</th>
                                    <th>介绍</th>
                                    <th>appid</th>
                                    <th>appsecret</th>
                                    <th>token</th>
                                    <th>encoaseskeyding</th>
                                    <th>类型</th>
                                    <th>模式</th>
                                    <th>组织ID</th>
                                    <th>组织</th>
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

	<div id="wxAccAddDialog" class="hide" style="width:400px">
		<input type="hidden" name="th_id" id="th_id">
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>名称：</strong> <input type="text" placeholder="请输入名称"
						class="text" id="th_account" maxlength="255" style="width: 160px;">
					</select>
				</dd>
				<dd class="clearfix" style="display: inline-block;">
					<strong>昵称：</strong> <input type="text" placeholder="请输入昵称"
						class="text" id="th_name" maxlength="255" style="width: 160px;">
					</select>
				</dd>
			</dl>
			
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>类型：</strong>
						<select id="th_typeid" style="width: 160px">
							<option value ="A">订阅号</option>
							<option value ="B">订阅订证号</option>
							<option value ="R">服务号</option>
							<option value ="S">服务认证号</option>
					</select>
				</dd>
				<dd class="clearfix" style="display: inline-block;">
					<strong>模式：</strong> 
					<select id="th_modeid" style="width: 160px">
						<option value="S">安全</option>
						<option value="T">明文</option>
						<option value="C">兼容</option>
					</select>
				</dd>
			</dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>介绍：</strong> <input type="text" placeholder="请输入介绍"
						class="text" id="th_introduction" maxlength="255" style="width: 360px;">
					</select>
				</dd>
			</dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
						<strong>appid：</strong> <input type="text" placeholder="请输入appid"
						class="text" id="th_appid" maxlength="255" style="width: 360px;">
					</select>
				</dd>
			</dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>appsecret：</strong> <input type="text" placeholder="请输入appsecret"
						class="text" id="th_appsecret" maxlength="255" style="width: 360px;">
					</select>
				</dd>
			</dl>
		
			<#--  <dl>
				<dd class="clearfix" style="display: inline-block;">
						<strong>token：</strong> <input type="text" placeholder="请输入token"
						class="text" id="th_token" maxlength="255" style="width: 360px;">
					</select>
				</dd>
			</dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>encoaseskeyding：</strong> <input type="text" placeholder="请输入encoaseskeyding"
						class="text" id="th_encoaseskeyding" maxlength="255" style="width: 360px;">
					</select>
				</dd>
			</dl>
			-->	

		</div>

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
	<!--my javascript-->
	<script src="${base}/js/wxacc_list.js"></script>


	<!-- inline scripts related to this page -->
	<script type="text/javascript">
			var catgidlist = [];
			jQuery(function($) {
				var curpage = "${curpage!''}";
				var cp = $(".nav-list a[href='"+curpage+"']");
				cp.parent().attr("class","active");
				cp.parent().parent().parent().attr("class","active open");				
			});
			
			
			$('#editor_details').css({'height':'50px','witdh':'300px'}).ace_wysiwyg({
				toolbar_place: function(toolbar) {
					return $(this).closest('.widget-box').find('.widget-header').prepend(toolbar).children(0).addClass('inline');
				},
				toolbar:
				[
					'strikethrough',
					null,
					'insertunorderedlist',
					'insertorderedlist',
					null,
					'justifyleft',
					'justifycenter',
					'justifyright',
					null,
					{name:'insertImage', className:'btn-success'},
					null
				],
				speech_button:false
			});
			$('#editor_info').css({'height':'50px','witdh':'400px'}).ace_wysiwyg({
				toolbar:
				[
					'font',
					null,
					'fontSize',
					null,
					{name:'bold', className:'btn-info'},
					{name:'italic', className:'btn-info'},
					{name:'strikethrough', className:'btn-info'},
					{name:'underline', className:'btn-info'},
					null,
					{name:'insertunorderedlist', className:'btn-success'},
					{name:'insertorderedlist', className:'btn-success'},
					{name:'outdent', className:'btn-purple'},
					{name:'indent', className:'btn-purple'},
					null,
					{name:'justifyleft', className:'btn-primary'},
					{name:'justifycenter', className:'btn-primary'},
					{name:'justifyright', className:'btn-primary'},
					{name:'justifyfull', className:'btn-inverse'},
					null,
					{name:'createLink', className:'btn-pink'},
					{name:'unlink', className:'btn-pink'},
					null,
					{name:'insertImage', className:'btn-success'},
					null,
					'foreColor',
					null,
					{name:'undo', className:'btn-grey'},
					{name:'redo', className:'btn-grey'}
				],
				speech_button:false
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
