<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
	<base id="base" href="${base}">
	<meta charset="utf-8" />
	<title>积分统计</title>
	<meta name="courses" content="积分统计" />
	<meta name="description" content="积分统计" />
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
						<li class="active">积分统计</li>
					</ul>
					<!-- .breadcrumb -->

				</div>

				<#--  积分的数据汇总	-->
				<div class="page-content">
					<div class="table-header">积分统计列表</div>
					<div class="table-responsive">
						<table id="Integrallist-table"
							class="table table-striped table-bordered table-hover"
							style="width:100%">
							<thead style="width:100%">
								<tr>
									<th>id</th>
									<th>类型</th>
                                    <th>用户名</th>
                                    <th>总赚取积分</th>
                                    <th>总消费积分</th>
                                    <th>剩余积分</th>
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
				<#-- 显示积分的详细情况   消费和获取 -->
				<div class="page-content">
					<div class="table-header">积分详细列表</div>
					<div class="table-responsive">
						<table id="integrallist-table"
							class="table table-striped table-bordered table-hover"
							style="width:100%">
							<thead style="width:100%">
								<tr>
									<th>id</th>
									<th>积分ID</th>
									<th>使用项目</th>
                                    <th>时间</th>
                                    <th>积分数</th>
                                    <th>是否有效</th>
                                    <th>备注</th>
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
		
		
		<div class='bootbox modal' tabindex='-1' role='dialog'>
			<div class='modal-dialog'>
				<div class='modal-content'>
					<div class='modal-body'>
						<div class='bootbox-body'>
						</div>
					</div>
				</div>
			</div>
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
	
	<!--my javascript-->
	<script src="${base}/js/integralstatistic_list.js"></script>
	<!-- inline scripts related to this page -->
	<script type="text/javascript">
			var catgidlist = [];
			jQuery(function($) {
				var curpage = "${curpage!''}";
				var cp = $(".nav-list a[href='"+curpage+"']");
				cp.parent().attr("class","active");
				cp.parent().parent().parent().attr("class","active open");				
			
			
			$('#editor_content').css({'height':'600px','witdh':'1000px'}).ace_wysiwyg({
				/*toolbar_place: function(toolbar) {
					return $(this).closest('.widget-box').find('.widget-header').prepend(toolbar).children(0).addClass('inline');
				},*/
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
			});
		</script>
</body>
</html>
