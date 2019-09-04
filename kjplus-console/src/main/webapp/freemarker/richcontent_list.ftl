<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
	<base id="base" href="${base}">
	<meta charset="utf-8" />
	<title>通用内容列表</title>
	<meta name="courses" content="通用内容列表" />
	<meta name="description" content="通用内容列表" />
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
						<li class="active">通用内容</li>
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
					<div class="table-header">通用内容列表</div>
					<div class="table-responsive">
						<table id="richcontentlist-table"
							class="table table-striped table-bordered table-hover"
							style="width:100%">
							<thead style="width:100%">
								<tr>
									<th>ID</th>
                                    <th>编码</th>
                                    <th>类型名称ID</th>
                                    <th>类型名称</th>
                                    <th>标题</th>
                                    <th>轮播图1</th>
                                    <th>轮播图2</th>
                                    <th>轮播图3</th>                                        
                                    <th>轮播图4</th>
                                    <th>轮播图5</th>
                                    <th>内容</th>
                                    <th>创建时间</th>
                                    <th>组织ID</th>
                                    
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
	<!-- 添加详情 -->
	<div id="richContentAddDialog" class="hide" style="width:400px">
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>部门：</strong>
						<select id="th_deptid" style="width: 160px">
							<#list listDepartment as d>
								<option value ="${d.id}">${d.name}</option>
							</#list>
					</select>
				</dd>
				<dd class="clearfix" style="display: inline-block;">
					<strong>医生：</strong>
						<select id="th_staffid" style="width: 160px">
							
								<option value =""></option>
							
						</select>
				</dd>
			</dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>标题：</strong> <input type="text" placeholder="请输入标题"
						class="text" id="th_title" maxlength="255" style="width: 480px;">
					</select>
				</dd>
			</dl>
				<input type="hidden" id="th_uface1" value="">
				<input type="hidden" id="th_uface2" value="">
				<input type="hidden" id="th_uface3" value="">
				<input type="hidden" id="th_uface4" value="">
				<input type="hidden" id="th_uface5" value="">
			<dl>
				
					<strong>头像上传：</strong>
					<dd class="clearfix" style="display: inline-block;"> 
					<form method="POST" id="imgfileform1">
					<input type="file" name="imgfile" id="imgfile1"/>
					</form>
					</dd>
					<dd class="clearfix" style="display: inline-block;">
					<form method="POST" id="imgfileform2">
					<input type="file" name="imgfile" id="imgfile2"/>
					</form>
					</dd>
					<dd class="clearfix" style="display: inline-block;">
					<form method="POST" id="imgfileform3">
					<input type="file" name="imgfile" id="imgfile3"/>
					</form>
					</dd>
					<dd class="clearfix" style="display: inline-block;">
					<form method="POST" id="imgfileform4">
					<input type="file" name="imgfile" id="imgfile4"/>
					</form>
					</dd>
					<dd class="clearfix" style="display: inline-block;">
					<form method="POST" id="imgfileform5">
					<input type="file" name="imgfile" id="imgfile5"/>
					</form>
					</dd>
				
			</dl>
			
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>内容：</strong> 
					<textarea name="th_content" id="th_content" cols="30" rows="5" class=""></textarea>
				</dd>
			</dl>
		</div>
		<!-- 修改详情 -->
		<div id="richContentEditDialog" class="hide" style="width:400px">
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>标题：</strong> <input type="text" placeholder="请输入标题"
						class="text" id="th_title1" maxlength="255" style="width: 480px;">
					</select>
				</dd>
			</dl>
				<input type="hidden" id="th_uface6" value="">
				<input type="hidden" id="th_uface7" value="">
				<input type="hidden" id="th_uface8" value="">
				<input type="hidden" id="th_uface9" value="">
				<input type="hidden" id="th_uface10" value="">
			<dl>
				
					<strong>头像上传：</strong>
					<dd class="clearfix" style="display: inline-block;">
					<form method="POST" id="imgfileform6">
					<input type="file" name="imgfile" id="imgfile6"/>
					</form>
					</dd>
					<dd class="clearfix" style="display: inline-block;">
					<form method="POST" id="imgfileform7">
					<input type="file" name="imgfile" id="imgfile7"/>
					</form>
					</dd>
					<dd class="clearfix" style="display: inline-block;">
					<form method="POST" id="imgfileform8">
					<input type="file" name="imgfile" id="imgfile8"/>
					</form>
					</dd>
					<dd class="clearfix" style="display: inline-block;">
					<form method="POST" id="imgfileform9">
					<input type="file" name="imgfile" id="imgfile9"/>
					</form>
					</dd>
					<dd class="clearfix" style="display: inline-block;">
					<form method="POST" id="imgfileform10"> 
					<input type="file" name="imgfile" id="imgfile10"/>
					</form>
					</dd>
				
			</dl>
			
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>内容：</strong> 
					<textarea name="th_content1" id="th_content1" cols="30" rows="5" class=""></textarea>
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

	<div id="infoContentDialog" class="hide" style="heigh:600px;width:800px">
		<div class="widget-box">
			<div class="widget-body">
				<div class="widget-main no-padding">
					<div class="wysiwyg-editor" id="editor_content" style="heigh:600px;max-height:450px"></div>
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
	<script src="${base}/js/richContent_list.js"></script>
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
