<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
	<base id="base" href="${base}">
	<meta charset="utf-8" />
	<title>组织服务列表</title>
	<meta name="courses" content="组织服务列表" />
	<meta name="description" content="组织服务列表" />
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
						<li class="active">组织服务</li>
					</ul>
					<!-- .breadcrumb -->
				</div>

				<div class="page-content">
					<div class="table-header">组织服务列表</div>
					<div class="table-responsive">
						<table id="serventrylist-table"
							class="table table-striped table-bordered table-hover"
							style="width:100%">
							<thead style="width:100%">
								<tr>
                                    <th>ID</th>
                                     <th>PID</th>
                                    <th>类别</th>
                                    <th>编码</th>
                                    <th>名称</th>
                                    <th>参照编码</th>
                                    <th>服务类型</th>
                                    <th>状态</th>                                        
                                    <th>描述</th>
                                    <th>接收方可得积分</th>
                                    <th>可获积分</th>                                        
                                    <th>创建时间</th>
                                    <th>组织编码</th>
                                    <th>组织名称</th>
                                    <th>操作</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="5">从组织服务端载入数据...</td>
								</tr>
							</tbody>
						</table>
					</div>
				
					<!-- 组织服务配置列表 -->
					
					
					
					</div>
				<!-- /.page-content -->
				</div>
			</div>
			<!-- /.main-content -->
		<!-- /.main-container-inner -->

		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="icon-double-angle-up icon-only bigger-110"></i>
		</a>
	</div>
	<!-- /.main-container -->
	<!-- 添加组织服务-->
	<div id="servEntryAddDialog" class="hide" style="width:400px">
		<input type="hidden" name="th_code" id="th_code">
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>名称：</strong> <input type="text" placeholder="请输入组织服务名称"
						class="text" id="th_serventryname" maxlength="255" style="width: 200px;">
					</select>
				</dd>
				<dd class="clearfix" style="display: inline-block;">
					<strong>类别：</strong>
						<select id="th_serventrytype" style="width: 200px">
							<option value ="">全部</option>
							<option value ="S">组织服务类</option>
							<option value ="M">其它类</option>
					</select>
				</dd>
			</dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>类型：</strong>
					<select id="th_refVId" style="width: 200px">
						<option value ="">全部</option>
						<#list refVl as v>
							<option value ="${v.id}">${v.name}</option>
						</#list>
					</select>
				</dd>
				<dd class="clearfix" style="display: inline-block;">
					<strong>状态：</strong>
						<select id="th_status" style="width: 200px">
							<option value ="">全部</option>
							<option value ="Y">有效</option>
							<option value ="N">无效</option>
					</select>
				</dd>
			</dl>
			<!-- 组织服务配置窗口 -->		
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>组织服务接受方积分：</strong> <input type="text" placeholder="请输入"
						class="text" id="th_acceptorpoint" maxlength="255" style="width: 360px;">
					</select>
				</dd>
			</dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>组织服务提供方积分：</strong> <input type="text" placeholder="请输入"
						class="text" id="th_providerpoint" maxlength="255" style="width: 360px;">
					</select>
				</dd>
			</dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>描述：</strong> 
					<textarea name="th_serventrymemo" id="th_serventrymemo" cols="60" rows="10" class="text" placeholder="请输入"></textarea>
				</dd>
			</dl>
		
							
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
	<script src="${base}/js/serviceentry_list.js"></script>


</body>
</html>
