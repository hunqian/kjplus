<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
	<base id="base" href="${base}">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>组织列表</title>

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

<body class="nav-md">
<div class="container body">
    <div class="main_container">

        <div class="col-md-3 left_col">
            <div class="left_col scroll-view">
                <div class="navbar nav_title" style="border: 0;">
                    <a href="index.html" class="site_title"><i class="fa fa-paw"></i><span>社区卫生服务平台系统</span></a>
                </div>

                <div class="clearfix"></div>

                <!-- menu profile quick info -->
                <div class="profile clearfix">
                    <div class="profile_pic">
                        <img src="${base}/images/img.jpg" alt="..." class="img-circle profile_img">
                    </div>
                    <div class="profile_info my_data">
                        <span class="my_site">北京市海淀区上地社区卫生服务中心</span>
                        <h2 class="my_name">王东 (主治医生)</h2>
                    </div>
                </div>
                <!-- /menu profile quick info -->
                <br />

                <!-- sidebar menu -->
                <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                    <div class="menu_section">
                        <h3>选项</h3>
                        <ul class="nav side-menu">
                            <li>
                                <a><i class="fa fa-home"></i>本区居民列表</a>
                            </li>
                            <li>
                                <a><i class="fa fa-edit"></i>我的签约居民</a>
                            </li>
                            <li>
                                <a><i class="fa fa-camera-retro"></i>随访访视</a>
                            </li>
                            <li>
                                <a><i class="fa fa-phone-square"></i>健康咨询</a>
                            </li>
                            <li>
                                <a><i class="fa fa-plus-square"></i>健康体检</a>
                            </li>
                            <li>
                                <a><i class="fa fa-search-plus"></i>诊疗服务</a>
                            </li>
                            <li>
                                <a><i class="fa fa-reply"></i>转诊服务</a>
                            </li>
                            <li>
                                <a><i class="fa fa-calculator"></i>健康测量<span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <li><a>血压测量</a></li>
                                    <li><a>血糖测量</a></li>
                                    <li><a>尿液检测</a></li>
                                </ul>
                            </li>
                            <li>
                                <a><i class="fa fa-bar-chart"></i>工作统计</a>
                            </li>
                            <li>
                                <a><i class="fa fa-calendar"></i>基础数据</a>
                            </li>
                            <li>
                                <a><i class="fa fa-calendar"></i>组织列表</a>
                            </li>
                        </ul>
                    </div>

                </div>
                <!-- /sidebar menu -->

                <!-- /menu footer buttons -->
                <div class="sidebar-footer hidden-small">
                    <a data-toggle="tooltip" data-placement="top" title="Settings">
                        <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
                    </a>
                    <a data-toggle="tooltip" data-placement="top" title="FullScreen">
                        <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
                    </a>
                    <a data-toggle="tooltip" data-placement="top" title="Lock">
                        <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
                    </a>
                    <a data-toggle="tooltip" data-placement="top" title="Logout" href="login.html">
                        <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
                    </a>
                </div>
                <!-- /menu footer buttons -->
            </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
            <div class="nav_menu">
                <nav>
                    <div class="nav toggle">
                        <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                    </div>

                    <ul class="nav navbar-nav navbar-right">
                        <li class="">
                            <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                <img src="${base}/images/img.jpg" alt="">王东
                                <span class=" fa fa-angle-down"></span>
                            </a>
                            <ul class="dropdown-menu dropdown-usermenu pull-right">
                                <!--<li><a href="javascript:;"> Profile</a></li>-->
                                <!--<li>-->
                                <!--<a href="javascript:;">-->
                                <!--<span class="badge bg-red pull-right">50%</span>-->
                                <!--<span>Settings</span>-->
                                <!--</a>-->
                                <!--</li>-->
                                <!--<li><a href="javascript:;">Help</a></li>-->
                                <li><a href="login.html"><i class="fa fa-sign-out pull-right"></i>退出</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">
            <div>
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2 class="my_location">卫生组织列表</h2>
                                
                                <div class="clearfix"></div>
                            </div>
                            
                            <div class="table-responsive">

                                <table id="orglist-table" class="table table-striped table-bordered table-hover" style="width:100%">
                                    <thead>
                                    <tr>
                                        <!--<th><input type="checkbox" id="check-all" class="flat"></th>-->
                                        <th>组织ID</th>
                                        <th>编码</th>
                                        <th>名称</th>
                                        <th>别名</th>
                                        <th>上级</th>
                                        <th>地区</th>
                                        <th>城市</th>                                        
                                        <th>省份</th>
                                        <th>地址</th>
                                        <th>状态</th>
                                        <th>创建</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="orgAddDialog" class="hide" style="width:400px">
			<dl>
			<dd class="clearfix" style="display: inline-block;">
				<strong>信息标题：</strong> <input type="text" placeholder="请输入名称"
					class="text" id="th_title" maxlength="11" style="width: 360px;">
				</select>
			</dd>
			</dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>分类：</strong>
						<select id="th_catgid" style="width: 160px">
						<option value =""></option>
					</select>
				</dd>
				<dd class="clearfix" style="display: inline-block;">
					<strong>学科：</strong> <select id="th_scatgid" style="width: 160px">
						<option value=""></option>
					</select>
				</dd>
			</dl>
			<dl>
				<dd class="clearfix" style="display: inline-block;">
					<strong>分校：</strong> <select id="th_branchId" style="width: 160px">
						 <option value="">所有分校</option> 
					</select>
				</dd>
				<dd class="clearfix" style="display: inline-block;">
					<strong>创建的人：</strong> <input type="text" placeholder="请输入名称"
						class="text" id="th_creator" maxlength="11" style="width: 80px;">
					</select>
				</dd>
			</dl>
			<dl>
				<!--<dd class="clearfix" style="display: inline-block;">
					  <strong>其他类A：</strong> <select id="th_ctag1" style="width: 160px">
						<option value="">请选择</option>
						<option value="SK">说课</option>
						<option value="JGH">结构化</option>
						<option value="DB">答辩</option>
						<option value="SJ">试讲</option>
					</select>
				</dd>
				-->
				<dd class="clearfix" style="display: inline-block;">
					  <strong>其他类C：</strong> <select id="th_ctag3" style="width: 160px">
						<option value="">请选择</option>
						<option value="BK">备考干货</option>
						<option value="KSB">考试公告</option>
					</select>
				</dd>
			</dl>
		</div>
<!-- jQuery -->
<script src="${base}/assets/js/jquery-1.10.2.min.js"></script>
<script src="${base}/assets/js/jquery-ui-1.10.3.full.min.js"></script>
<script src="${base}/assets/js/ace-extra.min.js"></script>
<script src="${base}/assets/js/ace-elements.min.js"></script>
<script src="${base}/assets/js/ace.min.js"></script>

<!-- Bootstrap -->
<script src="${base}/assets/js/bootstrap.min.js"></script>
<!-- Datatables -->
<script src="${base}/assets/js/jquery.dataTables.min.js"></script>
<script src="${base}/assets/js/dataTables.bootstrap.min.js"></script>
<script src="${base}/assets/js/dataTables.buttons.min.js"></script>
<script src="${base}/assets/js/buttons.bootstrap.min.js"></script>
<script src="${base}/assets/js/buttons.flash.min.js"></script>
<script src="${base}/assets/js/buttons.html5.min.js"></script>
<script src="${base}/assets/js/buttons.print.min.js"></script>
<script src="${base}/assets/js/dataTables.fixedHeader.min.js"></script>
<script src="${base}/assets/js/dataTables.keyTable.min.js"></script>
<script src="${base}/assets/js/dataTables.responsive.min.js"></script>
<script src="${base}/assets/js/responsive.bootstrap.js"></script>
<script src="${base}/assets/js/dataTables.scroller.min.js"></script>
<script src="${base}/assets/js/jszip.min.js"></script>

<!-- Custom Theme Scripts -->
<script src="${base}/assets/js/custom.min.js"></script>

<!--my javascript-->
<script src="${base}/js/org_list.js"></script>

</body>
</html>
