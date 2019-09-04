<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>我的日历</title>
		<base id="base" href="${base}">
		<meta name="keywords" content="我的日历" />
		<meta name="description" content="我的日历" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<!-- basic styles -->

		<link href="${base}/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="${base}/assets/css/font-awesome.min.css" />

		<!--[if IE 7]>
		  <link rel="stylesheet" href="${base}/assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

		<!-- page specific plugin styles -->

		<link rel="stylesheet" href="${base}/assets/css/fullcalendar.css" />

		<!-- fonts -->
		<!-- ace styles -->

		<link rel="stylesheet" href="${base}/assets/css/ace.min.css" />
		<link rel="stylesheet" href="${base}/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${base}/assets/css/ace-skins.min.css" />

		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="${base}/assets/css/ace-ie.min.css" />
		<![endif]-->

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
			.fc-event-container >.label-grey{
				height: 41px !important;
			}
			.fc-event-container >.label-success{
				height: 61.5px !important;
			}
			.fc-event-container >.label-purple{
				height: 101.5px !important;
			}
			.fc-event-container >.label-yellow{
				height: 122px !important;
			}
			.fc-event-container >.label-pink{
				height: 142.5px !important;
			}
			.fc-event-container >.label-info{
				height: 163px !important;
			}
			.inputOne{
				border: none!important;
    			background-color: #a0a0a0!important;
    			color: white!important;
			}
			.Btn1, .Btn2{
				position: absolute;
				left: 200px;
				border: none;
				background-color: #a0a0a0;
				color: white;
			}
			.cover{
				position: fixed;
    			top: 0;
    			left: 26%;
    			right: auto;
    			width: 600px;
    			padding-top: 30px;
    			padding-bottom: 30px;
    			z-index: 1050;
    			padding: 10px;
    			margin-right: auto;
    			margin-left: auto;
			}
			.entrycover{
				position: fixed;
    			top: 0;
    			left: 26%;
    			right: auto;
    			width: 600px;
    			padding-top: 30px;
    			padding-bottom: 30px;
    			z-index: 1050;
    			padding: 10px;
    			margin-right: auto;
    			margin-left: auto;
			}
			.cover-content{
				position: relative;
    			background-color: #fff;
    			outline: 0;
    			background-clip: padding-box;
			}
			.entrycover-content{
				position: relative;
    			background-color: #fff;
    			outline: 0;
    			background-clip: padding-box;
			}
			.cover-header{
				min-height: 16.428571429px;
    			padding: 15px;
    			border-bottom: 1px solid #e5e5e5;
			}
			.entrycover-header{
				min-height: 16.428571429px;
    			padding: 15px;
    			border-bottom: 1px solid #e5e5e5;
			}
			.entrycover-body{
				position: relative;
    			padding: 20px;
			}
			.cover-body{
				position: relative;
    			padding: 20px;
			}
			.cover-input{
				display: block;
    			width: 100%;
    			height: 34px;
    			vertical-align: middle;
    			transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
			}
			.entrycover-input{
				display: block;
    			width: 100%;
    			height: 34px;
    			vertical-align: middle;
    			transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
			}
			.cover-footer{
				padding-top: 12px;
    			padding-bottom: 14px;
    			border-top-color: #e4e9ee;
    			box-shadow: none;
    			background-color: #eff3f8;
    			padding: 19px 20px 20px;
    			margin-top: 15px;
    			text-align: right;
    			border-top: 1px solid #e5e5e5;
			}
			.entrycover-footer{
				padding-top: 12px;
    			padding-bottom: 14px;
    			border-top-color: #e4e9ee;
    			box-shadow: none;
    			background-color: #eff3f8;
    			padding: 19px 20px 20px;
    			margin-top: 15px;
    			text-align: right;
    			border-top: 1px solid #e5e5e5;
			}
			.btn-pri{
				background-color: #428bca!important;
    			border-color: #428bca;
			}
			.btn-entrypri{
				background-color: #428bca!important;
    			border-color: #428bca;
			}
			.btn-pri:hover{
				background-color: #1b6aaa!important;
    			border-color: #428bca;
			}
			.btn-entrypri:hover{
				background-color: #1b6aaa!important;
    			border-color: #428bca;
			}
			#cove{
				position: fixed;
				top: 0;
				left: 0;
				z-index: 14;
				width: 100%;
				height: 100%;
				background: #ccc none repeat scroll 0% 0%;
				opacity: 0.5;
			}
			#entrycove{
				position: fixed;
				top: 0;
				left: 0;
				z-index: 14;
				width: 100%;
				height: 100%;
				background: #ccc none repeat scroll 0% 0%;
				opacity: 0.5;
			}
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
							<li>
								<i class="icon-home home-icon"></i>
								<a href="#">主页</a>
							</li>
							<li class="active">日历</li>
						</ul><!-- .breadcrumb -->

					</div>

					<div class="page-content">
						<div class="page-header">
							<h1>
								我的日历
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<div class="row">
									<div class="col-sm-9">
										<div class="space"></div>

										<div id="calendar"></div>
									</div>

									<div class="col-sm-3">
										<div class="widget-box transparent">
											<div class="widget-header">
												<h4>拖放事件</h4>
												<button class="btn coverShow">编辑</button>
											</div>

											<div class="widget-body">
												<div class="widget-main no-padding">
													<div id="external-events">
													</div>
												</div>
											</div>
										</div>
										
										<div class="widget-box transparent">
											<div class="widget-header">
												<h4>活动</h4>
												<button class="btn coverShow">编辑</button>
											</div>

											<div class="widget-body">
												<div class="widget-main no-padding">
													<div id="external-events-activity">
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>

								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
					<!--编辑活动弹出框-->
					<div class="cover" style="display: none;">
						<div class="cover-content">
							<div class="cover-header">
								<h4 class="cover-title"> 编辑时间以及出诊人数： </h4>
							</div>
							<div class="cover-body">
								<div class="form-body">
									<form class="cover-form">
									<strong>类型：</strong>
										<select id="ty_defId" name="th_usecode" style="width:120px;">";
											<option value="0"></option>
											<#list entrys as entry>
												<option value="${entry.id}">${entry.name}</option>
											</#list>
										</select>
										<dl>
											<dd class="clearfix" style="display: inline-block;">
												<strong>标题：</strong> <input type="text" placeholder="请输入标题"
													class="text" id="ty_title" maxlength="255" style="width: 160px;">
												</select>
											</dd>
										</dl>
										<dl>
											<dd class="clearfix" style="display: inline-block;">
												<strong>持续时间(分)：</strong> <input type="text" placeholder="请输入标题"
													class="text" id="ty_timeInterval" maxlength="255" style="width: 160px;">
												</select>
											</dd>
										</dl>
										<dl>
											<dd class="clearfix" style="display: inline-block;">
												<strong>最大可参与人数：</strong> <input type="text" placeholder="请输入标题"
													class="text" id="ty_maxPerson" maxlength="255" style="width: 160px;">
												</select>
											</dd>
										</dl>
										<dl>
											<dd class="clearfix" style="display: inline-block;">
												<strong>显示样式：</strong>
												<select id="ty_showClass" name="th_usecode" style="width:120px;">";
													<option value="label-yellow">yellow</option>
													<option value="label-pink">pink</option>
													<option value="label-info">info</option>
												</select>
											</dd>
										</dl>
										<dl>
											<dd class="clearfix" style="display: inline-block;">
												<strong>描述：</strong>
													<textarea rows="8" cols="*" id="ty_memo"></textarea>
												</select>
											</dd>
										</dl>
										
									</form>
								</div>
							</div>
							<div class="cover-footer">
								<button class="btn btn-close">关闭</button>
								<button class="btn btn-pri">确定</button>
							</div>
						</div>
					</div>
					
					<!--添加日历类型弹出框-->
					<div class="entrycover" style="display: none;">
						<div class="cover-content">
							<div class="cover-header">
								<h4 class="cover-title"> 添加日历类型： </h4>
							</div>
							<div class="cover-body">
								<div class="form-body">
									<form class="cover-form">
									<strong>日历类型：</strong>
										<select id="entry_refId" style="width:120px;">";
											<option value="0"></option>
											<#list entrytypes as value>
												<option value="${value.id}">${value.name}</option>
											</#list>
										</select>
										<dl>
											<dd class="clearfix" style="display: inline-block;">
												<strong>日历类型名：</strong> <input type="text" placeholder="请输入日历类型名"
													class="text" id="entry_title" maxlength="255" style="width: 160px;">
												</select>
											</dd>
										</dl>
									</form>
								</div>
							</div>
							<div class="cover-footer">
								<button class="btn btn-entryclose">关闭</button>
								<button class="btn btn-entrypri">确定</button>
							</div>
						</div>
					</div>
					
				</div><!-- /.main-content -->
				<div id="cove" style="display: none;"> </div>
				<div id="entrycove" style="display: none;"> </div>
				
				<div class="ace-settings-container" id="ace-settings-container">
					
				<!--		<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="save-calendar-btn">
						<i class="icon-cog bigger-150">保存日历</i>
					</div>
					<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
						<i class="icon-cog bigger-150">选择</i>
					</div>  -->
					 
					<div class="inline position-relative align-left">
					
					<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="add-calendarentry-btn">
						<i class="icon-cog bigger-150">添加日历类型</i>
					</div>
					
					<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="save-calendar-btn">
						<i class="icon-cog bigger-150">保存日历</i>
					</div>
						<a href="#" class="btn-message btn btn-xs dropdown-toggle" data-toggle="dropdown">
							<span class="bigger-110" id="ace-settings-btn">选择日历类型</span>
							<i class="icon-caret-down icon-on-right"></i>
						</a>
						
						
					<div  id="ace-settings-box">	
						<ul class="dropdown-menu dropdown-lighter dropdown-caret dropdown-125">
							<#list entrys as entry>
								<li>
									<div>			
			    						&nbsp;<input type="checkbox" class="ace ace-checkbox-2" id="entry_sel_${entry.id}" value="${entry.id}"/>
			    						<label class="lbl" for="ace-settings-navbar">${entry.name}</label>
		    						</div> 
	    						</li>
    						</#list>
						</ul>
					</div>  
					</div> 
				
						
					<!--	<div class="ace-settings-box" id="ace-settings-box">
					<div>
							<div class="pull-left">
								<select id="skin-colorpicker" class="hide">
									<option data-skin="default" value="#438EB9">#438EB9</option>
									<option data-skin="skin-1" value="#222A2D">#222A2D</option>
									<option data-skin="skin-2" value="#C6487E">#C6487E</option>
									<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
								</select>
							</div>  
						
						</div>
					
						
						<#list entrys as entry>
							<div>
								<input type="checkbox" class="ace ace-checkbox-2" id="entry_sel_${entry.id}" value="${entry.id}"/>
								<label class="lbl" for="ace-settings-navbar">${entry.name}</label>
							</div>
						</#list>

					</div>
						-->
				
				
					
		</div><!-- /#ace-settings-container -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->

		<!-- <![endif]-->

		<!--[if IE]>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<![endif]-->

	<!--[if !IE]> -->
	<script src="${base}/assets/js/jquery-2.0.3.min.js"></script>
	<!-- <![endif]-->

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

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='${base}/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="${base}/assets/js/bootstrap.min.js"></script>
		<script src="${base}/assets/js/typeahead-bs2.min.js"></script>

		<!-- page specific plugin scripts -->

		<script src="${base}/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
		<script src="${base}/assets/js/jquery.ui.touch-punch.min.js"></script>
		<script src="${base}/assets/js/fullcalendar.min.js"></script>
		<script src="${base}/assets/js/bootbox.min.js"></script>

		<!-- ace scripts -->

		<script src="${base}/assets/js/ace-elements.min.js"></script>
		<script src="${base}/assets/js/ace.min.js"></script>
		<script src="${base}/assets/js/ace.min.js"></script>
		
		
		<script src="${base}/assets/js/fuelux/fuelux.spinner.min.js"></script>
		
		
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
	<script src="${base}/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
	<script src="${base}/assets/js/jquery.ui.touch-punch.min.js"></script>
	<script src="${base}/assets/js/fullcalendar.min.js"></script>
	<script src="${base}/assets/js/bootbox.min.js"></script>
	<script src="${base}/js/mycal.js"></script>
	
		<!-- inline scripts related to this page -->

		<script type="text/javascript">
		

			jQuery(function($) {

				$('.inputOne').blur(function(){
					$('.modal-dialog').show();
					console.log(111);
					var eventObject = {
						title: $(this).val()
					};
					console.log(eventObject);
						$(this).parent().data('eventObject', eventObject);
					});	
			});
			
		</script>
	</body>
</html>
