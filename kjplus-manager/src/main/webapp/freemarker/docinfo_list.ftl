<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
	<base id="base" href="${base}">
	<meta charset="utf-8" />
	<title>档案列表</title>
	<meta name="courses" content="档案列表" />
	<meta name="description" content="档案列表" />
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
	<style>
		.icon-cogs>a{color: #fff !important;}
		dd{display:inline-block;}
		.userData dd{padding-left:30px;}
		.uData dd:first-child{vertical-align: middle;}
		.uData dd{padding-left:10px;}
		.uData dd:last-child{padding-left:50px;}
		.btn-info{margin:2px;width:70px;}
		.dropdown-default li{cursor: pointer;}
		.exam{padding:2px 12px;}
		.exam:hover{background-color:#abbac3;}
	</style>
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
						<li class="active">档案</li>
					</ul>
					<!-- .breadcrumb -->

					
				</div>

				<div class="page-content">
					<div class="table-header">档案列表</div>
					<div class="table-responsive">
						<table id="docinfolist-table"
							class="table table-striped table-bordered table-hover"
							style="width:100%">
							<thead style="width:100%">
								<tr align="center">
                                    <th>ID</th>
                                    <th>Code</th>
                                    <th>状态</th>
                                    <th>头图</th>
                                    <th>prsnCode</th>
                                    <th>姓名</th>
                                    <th>性别</th>
                                    <th>生日</th>
                                    <th>身份证号</th>
                                    <th>手机号</th>
                                    <th>民族</th>
                                    <th>家庭住址</th>
                                    <th>家庭医生</th>
                                    <th>服务包</th>
                                    <th>建档日</th>
                                    <th>居民标签</th>
                                    <th>UID</th>
                                    <th>UNickName</th>
                                    <th>固定电话</th>
                                    <th>工作单位</th>
                                    <th>血型</th>
                                    <th>联系人姓名</th>
                                    <th>联系方式</th>
                                    <th>具体住址</th>
                                    <th>组织ID</th>
                                    <th>组织名称</th>
                                    <th>创建人</th>
                                    <th>创建人ID</th>
                                    <th>创建时间</th>
                                    <th>操作</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="5" align="center">从服务端载入数据...</td>
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
	<!-- 血压测量 -->
	<div id="examBloodAddDialog" class="hide" style="width:400px">
            <dl>
				<dd class="clearfix" style="display: inline-block;">
				<strong>收缩压：</strong> <input type="text" placeholder="请输入收缩压"
					class="text" id="th_shrinkpress" maxlength="255" style="width: 160px;">
				</select>
				</dd>
			 </dl>
            <dl>
				<dd class="clearfix" style="display: inline-block;">
				<strong>舒张压：</strong> <input type="text" placeholder="请输入舒张压"
					class="text" id="th_diastolepress" maxlength="255" style="width: 160px;">
				</select>
				</dd>
			 </dl>
            <dl>
				<dd class="clearfix" style="display: inline-block;">
				<strong>心率：</strong> <input type="text" placeholder="请输入心率"
					class="text" id="th_heartrate" maxlength="255" style="width: 160px;">
				</select>
				</dd>
			 </dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>医生：</strong>
						<select id="th_staffid" style="width: 160px">
                            <option value ="0">全部</option>
                            <#list listStaffDto as s>
								<option value ="${s.stafId}">${s.stafName}</option>
							</#list>
					</select>
				</dd>
            </dl>
		</div>
		<!-- 血糖测量 -->
		<div id="examGlycemicAddDialog" class="hide" style="width:400px">
			
            <dl>
            	<dd class="clearfix" style="display: inline-block;">
					<strong>血糖类型：</strong>
						<select id="th_glycemictype" style="width: 160px">
                            <option value ="0">全部</option>
                            <option value ="K">空腹</option>
                            <option value ="C">餐后</option>
					</select>
				</dd>
			</dl>
            <dl>
				<dd class="clearfix" style="display: inline-block;">
				<strong>血糖值：</strong> <input type="text" placeholder="请输入血糖值"
					class="text" id="th_glycemic" maxlength="255" style="width: 160px;">
				</select>
			</dd>
			</dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>医生：</strong>
						<select id="th_staffid" style="width: 160px">
                            <option value ="0">全部</option>
                            <#list listStaffDto as s>
								<option value ="${s.stafId}">${s.stafName}</option>
							</#list>
					</select>
				</dd>
            </dl>
		</div>
	
	<!-- /.main-container -->
	<div id="doc_tag" class="hide">
	</div><!-- #dialog-table-def -->
		
	<!-- 添加docinfo -->	
	<div id="servAssAddDialog" class="hide" style="width:400px">
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>服务：</strong>
					<select id="th_srvid" style="width: 160px">
							<option value ="0">全部</option>
						<#list servCatg as s>
							<option value ="${s.id}">${s.name}</option>
						</#list>
					</select>
				</dd>
			</dl>
       	 	<dl>
                <dd class="clearfix" style="display: inline-block;">
					<strong>对应部门：</strong>
					<select id="th_deptid" style="width: 160px">
							<option value ="0">全部</option>
						<#list listDepartmentDto as d>
							<option value ="${d.id}">${d.name}</option>
						</#list>
					</select>
            	</dd>
				<dd class="clearfix" style="display: inline-block;">
					<strong>医生：</strong>
						<select id="th_stafid" style="width: 160px">
                            <option value =""></option>
					</select>
				</dd>
            </dl>
		</div>
		
			<!-- 为创建的档案绑定用户 -->	
		<div id="bindingUserDialog" class="hide" style="width:400px">
			<dl class="userData">
				<dd class="clearfix" style="display: inline-block;">
					<strong>用户手机号：</strong> <input type="text" placeholder="请输入手机号码"
						class="text" id="th_phonenum" maxlength="20" style="width: 120px;">
					</select>
					<input type="button" id="th_phonenum_btn" value="查询">
				</dd>
			</dl>
			<dl class="userData">
				
				<dd class="clearfix" style="display: inline-block;">
					<strong>用户名：</strong> 
				</dd>
				<dd class="clearfix" style="display: inline-block;">
					<strong>手机号：</strong> 
				</dd>
			</dl>
		</div>
		
		<div id="uploadImageDialog" class="hide" style="width:400px">
			<input type="hidden" id="th_uface" value="">
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
	<!-- 异步上传图片 -->
	<script src="${base}/assets/js/jquery.form.js" type="text/javascript"></script>
	
	<script src="${base}/js/fuelux.menutree.js"></script>
	<script src="${base}/assets/js/fuelux/fuelux.tree.min.js"></script>
	<!--my javascript-->
	<script src="${base}/js/docinfo_list.js?v=1222"></script>
	<script src="${base}/js/websocket.js"></script>

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
