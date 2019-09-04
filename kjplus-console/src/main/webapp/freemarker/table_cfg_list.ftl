<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
	<base id="base" href="${base}">
	<meta charset="utf-8" />
	<title>表格配置</title>
	<meta name="courses" content="表格配置" />
	<meta name="description" content="表格配置" />
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
						<li class="active">表格配置</li>
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
					<div class="table-header">标题配置列表</div>
					<div class="table-responsive">
						<table id="tablecfgheadlist-table"
							class="table table-striped table-bordered table-hover"
							style="width:100%">
							<thead style="width:100%">
								<tr>
									<th>ID</th>
                                    <th>编码</th>
                                    <th>名称</th>
                                    <th>类型ID</th>
                                    <th>类型名称</th>
                                    <th>是否有效</th>
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
					<div class="table-header">行配置列表</div>
					<div class="table-responsive">
						<table id="tablecfglinelist-table"
							class="table table-striped table-bordered table-hover"
							style="width:100%">
							<thead style="width:100%">
								<tr>
									<th>ID</th>
                                    <th>编码</th>
                                    <th>标题</th>
                                    <th>行序号</th>
                                    <th>行内x序</th>
                                    <th>行内y序</th>
                                    <th>参数类型ID</th>
                                    <th>参数类型名称</th>
                                    <th>多选</th>
                                    <th>值类型</th>
                                    <th>占位符</th>
                                     <th>标题宽(%)</th>
                                     <th>内容宽(%)</th>
                                     <th>标准类型ID</th>
                                     <th>标准类型</th>
                                     <th>必选</th>
                                     <th>状态</th>
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

	<div id="tableLineAddDialog" class="hide" style="width:400px">
		<input type="hidden" name="th_code" id="th_code">
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>标题：</strong> <input type="text" placeholder="请输入标题"
						class="text" id="th_linetitle" maxlength="255" style="width: 160px;">
					</select>
				</dd>
			</dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>行序号：</strong> <input type="text" placeholder="请输入行序号"
						class="text" id="th_lineseq" maxlength="255" style="width: 160px;">
					</select>
				</dd>
				<dd class="clearfix" style="display: inline-block;">
					<strong>行序号版本：</strong> <input type="text" placeholder="请输入行序号版本"
						class="text" id="th_linepos" maxlength="255" style="width: 160px;">
					</select>
				</dd>
			</dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>参数类型：</strong>
					<select id="th_linereftype" style="width: 160px">
						<option value =""></option>
					</select>
				</dd>                
				<dd class="clearfix" style="display: inline-block;">
					<strong>是否对应多个参数类型：</strong>
					<select id="th_linemultiref" style="width: 160px">
						<option value ="">全部</option>
						<option value ="Y">是</option>
						<option value ="N">否</option>
					</select>
				</dd>
			</dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>值类型：</strong>
					<select id="th_lineinputvltype" style="width: 160px">
						<option value =""></option>
					</select>
				</dd>
				<dd class="clearfix" style="display: inline-block;">
					<strong>占位符：</strong> <input type="text" placeholder="请输入占位符"
						class="text" id="th_lineinputvlrepl" maxlength="255" style="width: 160px;">
					</select>
				</dd>
			</dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>状态：</strong>
					<select id="th_lineflag" style="width: 160px">
						<option value ="">全部</option>
						<option value ="Y">有效</option>
						<option value ="N">无效</option>
					</select>
				</dd>
			</dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>标题宽度：</strong> <input type="text" placeholder="请输入标题宽度"
						class="text" id="th_linetitlewidth" maxlength="255" style="width: 160px;">
					</select>
				</dd>
				<dd class="clearfix" style="display: inline-block;">
					<strong>内容宽度：</strong> <input type="text" placeholder="请输入内容宽度"
						class="text" id="th_linecontentwidth" maxlength="255" style="width: 160px;">
					</select>
				</dd>
			</dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>种植类：</strong> <input type="text" placeholder="请输入种植类"
						class="text" id="th_linetypevl" maxlength="255" style="width: 160px;">
					</select>
				</dd>
				<dd class="clearfix" style="display: inline-block;">
					<strong>IsReq：</strong> <input type="text" placeholder="请输入IsReq"
						class="text" id="th_isreq" maxlength="255" style="width: 160px;">
					</select>
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
	<script src="${base}/js/table_cfg_list.js"></script>


</body>
</html>
