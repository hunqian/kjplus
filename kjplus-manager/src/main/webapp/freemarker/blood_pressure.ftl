<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>本区居民列表</title>

    <!-- Bootstrap -->
    <link href="./assets/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="./assets/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="./assets/css/nprogress.css" rel="stylesheet">
    <!-- iCheck -->
    <link href="./assets/css/iCheck-flat-green.css" rel="stylesheet">

    <!-- bootstrap-progressbar -->
    <link href="./assets/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
    <!-- JQVMap -->
    <link href="./assets/css/jqvmap.min.css" rel="stylesheet"/>
    <!-- bootstrap-daterangepicker -->
    <link href="./assets/css/daterangepicker.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="./assets/css/custom.min.css" rel="stylesheet">

    <!--my-class-->
    <link href="./css/blood_pressure.css" rel="stylesheet">

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
                        <img src="images/img.jpg" alt="..." class="img-circle profile_img">
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
                                <img src="images/img.jpg" alt="">王东
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
                                <h2 class="my_location">医生/血压测量</h2>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <!--<p class="text-muted font-13 m-b-30">-->
                                <!--The Buttons extension for DataTables provides a common set of options, API methods and styling to display buttons on a page that will interact with a DataTable. The core library provides the based framework upon which plug-ins can built.-->
                                <!--</p>-->

                                <table id="datatable-buttons" class="table table-striped table-bordered bulk_action my_table">
                                    <thead>
                                    <tr>
                                        <th><input type="checkbox" id="check-all" class="flat"></th>
                                        <th>姓名</th>
                                        <th>性别</th>
                                        <th>年龄</th>
                                        <th>收缩压</th>
                                        <th>舒张压</th>
                                        <th>心率</th>
                                        <th>结果分类</th>
                                        <th>操作人</th>
                                        <th>时间</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td><input type="checkbox" class="flat" name="table_records"></td>
                                        <td>张三</td>
                                        <td>男</td>
                                        <td>67</td>
                                        <td>140</td>
                                        <td>89</td>
                                        <td>76</td>
                                        <td>偏高</td>
                                        <td>王东</td>
                                        <td>2017/03/02 16:00</td>
                                    </tr>
                                    <tr>
                                        <td><input type="checkbox" class="flat" name="table_records"></td>
                                        <td>张三</td>
                                        <td>男</td>
                                        <td>67</td>
                                        <td>140</td>
                                        <td>89</td>
                                        <td>76</td>
                                        <td>偏高</td>
                                        <td>王东</td>
                                        <td>2017/03/02 16:00</td>
                                    </tr>
                                    <tr>
                                        <td><input type="checkbox" class="flat" name="table_records"></td>
                                        <td>张三</td>
                                        <td>男</td>
                                        <td>67</td>
                                        <td>140</td>
                                        <td>89</td>
                                        <td>76</td>
                                        <td>偏高</td>
                                        <td>王东</td>
                                        <td>2017/03/02 16:00</td>
                                    </tr>
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

<!-- jQuery -->
<script src="./assets/js/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="./assets/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="./assets/js/fastclick.js"></script>
<!-- NProgress -->
<script src="./assets/js/nprogress.js"></script>
<!-- bootstrap-progressbar -->
<script src="./assets/js/bootstrap-progressbar.min.js"></script>
<!-- iCheck -->
<script src="./assets/js/icheck.min.js"></script>
<!-- Skycons -->
<script src="./assets/js/skycons.js"></script>
<!-- DateJS -->
<script src="./assets/js/date.js"></script>
<!-- bootstrap-daterangepicker -->
<script src="./assets/js/moment.min.js"></script>
<script src="./assets/js/daterangepicker.js"></script>
<!-- Datatables -->
<script src="./assets/js/jquery.dataTables.min.js"></script>
<script src="./assets/js/dataTables.bootstrap.min.js"></script>
<script src="./assets/js/dataTables.buttons.min.js"></script>
<script src="./assets/js/buttons.bootstrap.min.js"></script>
<script src="./assets/js/buttons.flash.min.js"></script>
<script src="./assets/js/buttons.html5.min.js"></script>
<script src="./assets/js/buttons.print.min.js"></script>
<script src="./assets/js/dataTables.fixedHeader.min.js"></script>
<script src="./assets/js/dataTables.keyTable.min.js"></script>
<script src="./assets/js/dataTables.responsive.min.js"></script>
<script src="./assets/js/responsive.bootstrap.js"></script>
<script src="./assets/js/dataTables.scroller.min.js"></script>
<script src="./assets/js/jszip.min.js"></script>
<script src="./assets/js/pdfmake.min.js"></script>
<script src="./assets/js/vfs_fonts.js"></script>

<!-- Custom Theme Scripts -->
<script src="./assets/js/custom.min.js"></script>

<!--my javascript-->
<script src="./js/blood_pressure.js"></script>


</body>
</html>
