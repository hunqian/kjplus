<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
	<base id="base" href="${base}">
	<meta charset="utf-8" />
	<title>签约列表</title>
	<meta name="courses" content="签约列表" />
	<meta name="description" content="签约列表" />
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
						<li class="active">签约</li>
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
					<div class="table-header">签约列表</div>
					<div class="table-responsive">
						<table id="servasslist-table"
							class="table table-striped table-bordered table-hover"
							style="width:100%">
							<thead style="width:100%">
								<tr>
									<th>ID</th>
                                    <th>居民ID</th>
                                    <th>居民姓名</th>
                                    <th>手机号码</th>
                                    <th>医生ID</th>
                                    <th>医生编码</th>
                                    <th>医生姓名</th>
                                    <th>医生手机号码</th>
                                    <th>组织ID</th>
                                    <th>组织名称</th>
                                    <th>科室ID</th>
                                    <th>科室类型</th>
                                    <th>科室名称</th>
                                    <th>服务ID</th>
                                    <th>服务编号</th>
                                    <th>服务名称</th>
                                    <th>服务价格</th>
                                    <th>请求时间</th>
                                    <th>申请状态</th>
                                    <th>审核结果</th>
                                    <th>操作时间</th>
                                    <th>服务开始时间</th>
                                    <th>服务结束时间</th>
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

	<div id="servAssAddDialog" class="hide" style="width:400px">
			<dl>
                <dd class="clearfix" style="display: inline-block;">
					<strong>居民姓名：</strong>
					<select id="th_prsnid" style="width: 160px">
						<option value ="0">全部</option>
					<#list doc as d>
						<option value ="${d.prsnId}">${d.prsnName}</option>
					</#list>
					</select>
                </dd>
            </dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>服务：</strong>
					<select id="th_srvid" style="width: 160px">

						<option value =""></option>

					</select>
				</dd>
                <dd class="clearfix" style="display: inline-block;">
					<strong>对应部门：</strong>
					<select id="th_deptid" style="width: 160px">

						<option value =""></option>

					</select>
            	</dd>
            </dl>
       	 	<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>医生：</strong>
						<select id="th_stafid" style="width: 160px">
                            <option value =""></option>
					</select>
				</dd>
            </dl>
		</div>
		
		
		<div id="servAssEditDialog" class="hide" style="width:400px">
			<dl>
                <dd class="clearfix" style="display: inline-block;">
					<strong>居民姓名：</strong>
					<select id="th_prsnid1" style="width: 160px">
						<option value ="0">全部</option>
					<#list doc as d>
						<option value ="${d.prsnId}">${d.prsnName}</option>
					</#list>
					</select>
                </dd>
            </dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>服务：</strong>
					<select id="th_srvid1" style="width: 160px">

						<option value =""></option>

					</select>
				</dd>
                <dd class="clearfix" style="display: inline-block;">
					<strong>对应部门：</strong>
					<select id="th_deptid1" style="width: 160px">

						<option value =""></option>

					</select>
            	</dd>
            </dl>
       	 	<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>医生：</strong>
						<select id="th_stafid1" style="width: 160px">
                            <option value =""></option>
					</select>
				</dd>
            </dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>操作时间：</strong> <input type="text" placeholder="请输入服务操作时间"
													class="text" id="th_opertime1" maxlength="255" style="width: 480px;">
				</dd>
			</dl>
		</div>
		
		<div id="servStatusDialog" class="hide" style="width:400px">
			<dl>
                <dd class="clearfix" style="display: inline-block;">
                    <strong>状态：</strong>
                    <select id="th_status" style="width: 160px">
                        <option value ="">全部</option>
                        <option value ="R">驳回</option>
                        <option value ="Y">确认</option>
                    </select>
                </dd>
			</dl>
			<dl>
				<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>审核结果：</strong> <textarea name="th_memo" id="th_memo" cols="30" rows="5" class=""></textarea>
				</dd>
			</dl>
			</dl>
		</div>
		
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
	<script src="${base}/js/servass_list.js"></script>
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
