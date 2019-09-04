<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
	<base id="base" href="${base}">
	<meta charset="utf-8" />
	<title>资讯列表</title>
	<meta name="courses" content="资讯列表" />
	<meta name="description" content="资讯列表" />
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
						<li class="active">资讯</li>
					</ul>
					<!-- .breadcrumb -->

					
				</div>

				<div class="page-content">
					<div class="table-header">资讯列表</div>
					<div class="table-responsive">
						<table id="infolist-table"
							class="table table-striped table-bordered table-hover"
							style="width:100%">
							<thead style="width:100%">
								<tr>
									<th>ID</th>
                                    <th>编码</th>
                                    <th>头图</th>
                                    <th>标题</th>
                                    <th>简介</th>
                                    <th>类别</th>
                                    <th>类型ID</th>
                                    <th>类型名称</th>
                                    <th>总查看数</th>
                                    <th>总赞数</th>
                                    <th>总关注数</th>
                                    <th>状态</th>                                        
                                    <th>发布人</th>
                                    <th>部门</th>
                                    <th>组织ID</th>
                                    <th>组织名称</th>
                                    <th>内容</th>
                                    <th>发布时间</th>
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

	<div id="infoAddDialog" class="hide" style="width:400px">
		<input type="hidden" name="th_id" id="th_id">
		<input type="hidden" name="th_code" id="th_code">
			<dl>
			<dd class="clearfix" style="display: inline-block;">
				<strong>标题：</strong> <input type="text" placeholder="请输入标题"
					class="text" id="th_title" maxlength="255" style="width: 480px;">
				</select>
			</dd>
			</dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>简介：</strong> <textarea name="th_desc" id="th_desc" cols="30" rows="5" class=""></textarea>
				</dd>
			</dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>对应部门：</strong>
						<select id="th_deptid" style="width: 160px">
								<option value ="">全部</option>
							<#list listDepartmentDto as d>
								<option value ="${d.id}">${d.name}</option>
							</#list>
					</select>
				</dd>
				<dd class="clearfix" style="display: inline-block;">
					<strong>资讯类型：</strong>
						<select id="th_catgid" style="width: 160px">
							<option value ="">全部</option>
						<#list listCatalog as c>
							<option value ="${c.id}">${c.name}</option>
						</#list>
					</select>
				</dd>
			</dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>类别：</strong>
						<select id="th_infotype" style="width: 160px">
							<option value ="">全部</option>
							<option value ="I">资讯</option>
							<option value ="A">活动</option>
					</select>
				</dd>
			</dl>
			
			<dl>
				<dd class="clearfix" style="display: inline-block;">
				<input type="hidden" id="th_uface" value="">
				<form method="POST" id="imgfileform">
					<strong>头图上传：</strong> <input type="file" name="imgfile" id="imgfile"/>
				</form>
				</dd>
			</dl>
		</div>
		
		<div id="infoCatgDialog" class="hide" style="width:200px">
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>请选择引用的资讯类型：</strong>
						<select id="th_infocatg" style="width: 160px">
							<#list listCatalog as c>
								<option value ="${c.id}">${c.name}</option>
							</#list>
					</select>
				</dd>
			</dl>
		</div>
	<!-- basic scripts -->

	<div id="modify_defstatus" class="hide">
		<!--
			<div class="alert alert-info bigger-110">
						<p><b>要上线该组织吗?</b></p>
			</div>
	
			<div class="space-6"></div>
			-->
		<p class="bigger-110 bolder center grey">
			<i class="icon-hand-right blue bigger-120"></i> 确认?
		</p>
	</div>
	<!-- #dialog-confirm -->

	<div id="update_tablestatus" class="hide">
		<p class="bigger-110 bolder center grey">
			<i class="icon-hand-right blue bigger-120"></i> 确认?
		</p>
	</div>
	<!-- #dialog-confirm -->

	<div id="infoContentDialog" class="hide" style="height:600px;width:800px">
		<div class="widget-box">
			<div class="widget-body">
				<div class="widget-main no-padding">
					<div class="wysiwyg-editor" id="editor_content" style="height:600px;max-height:500px"></div>
				</div>
			</div>
		</div>
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
	<!--图文编辑  -->
	<script src="${base}/assets/js/jquery.hotkeys.min.js"></script>
	<script src="${base}/assets/js/bootstrap-wysiwyg.js"></script>
	<script src="${base}/assets/js/bootbox.min.js"></script>
	
	<!-- 异步上传图片 -->
	<script src="${base}/assets/js/jquery.form.js" type="text/javascript"></script>
	
	<!--my javascript-->
	<script src="${base}/js/info_list.js?v=1222"></script>
	<!-- inline scripts related to this page -->
	<script type="text/javascript">
			var catgidlist = [];
			jQuery(function($) {
				var curpage = "${curpage!''}";
				var cp = $(".nav-list a[href='"+curpage+"']");
				cp.parent().attr("class","active");
				cp.parent().parent().parent().attr("class","active open");				
			
			});
			$(".wysiwyg-toolbar btn-toolbar center").hide();
		</script>
</body>
</html>
