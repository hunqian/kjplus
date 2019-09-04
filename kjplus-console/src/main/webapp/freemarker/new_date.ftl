<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>本区居民列表/新建档案</title>

    <!-- Bootstrap -->
    <link href="./assets/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="./assets/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="./assets/css/nprogress.css" rel="stylesheet">
    <!-- iCheck -->
    <link href="./assets/css/green.css" rel="stylesheet">

    <!-- bootstrap-progressbar -->
    <link href="./assets/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
    <!-- JQVMap -->
    <link href="./assets/css/jqvmap.min.css" rel="stylesheet"/>
    <!-- bootstrap-daterangepicker -->
    <link href="./assets/css/daterangepicker.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="./assets/css/custom.min.css" rel="stylesheet">

    <!--my-class-->
    <link href="./css/new_data.css" rel="stylesheet">

</head>

<body class="nav-md">
<div class="container body">
    <div class="main_container">

        <div class="col-md-3 left_col">
            <div class="left_col scroll-view">
                <div class="navbar nav_title" style="border: 0;">
                    <a href="index.html" class="site_title"><i class="fa fa-paw"></i> <span>社区卫生服务平台系统</span></a>
                </div>

                <div class="clearfix"></div>

                <!-- menu profile quick info -->
                <div class="profile clearfix">
                    <div class="profile_pic">
                        <img src="./images/img.jpg" alt="..." class="img-circle profile_img">
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
                                <img src="./production/images/img.jpg" alt="">王东
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
                                <h2 class="my_location">医生/本区居民列表/新建档案</h2>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <table class="table table-striped table-bordered my_table id_my_table">
                                    <thead>
                                    <tr>
                                        <th colspan="12" class="my_table_title my_tc">个人信息</th>
                                    </tr>
                                    </thead>

                                    <tbody class="my_tc">
                                    <tr>
                                        <td>姓名</td>
                                        <td><input type="text"></td>
                                        <td>编号</td>
                                        <td><input type="text"></td>
                                        <td>性别</td>
                                        <td>男</td>
                                        <td>出生日期</td>
                                        <td colspan="2" class="my_date">
                                            <!--日期-->
                                            <div class="col-md-12 my_data_box">

                                                <div class="daterangepicker dropdown-menu ltr single opensright show-calendar picker_1 xdisplay"><div class="calendar left single" style="display: block;"><div class="daterangepicker_input"><input class="input-mini form-control active" type="text" name="daterangepicker_start" value="" style="display: none;"><i class="fa fa-calendar glyphicon glyphicon-calendar" style="display: none;"></i><div class="calendar-time" style="display: none;"><div></div><i class="fa fa-clock-o glyphicon glyphicon-time"></i></div></div><div class="calendar-table"><table class="table-condensed"><thead><tr><th class="prev available"><i class="fa fa-chevron-left glyphicon glyphicon-chevron-left"></i></th><th colspan="5" class="month">Oct 2016</th><th class="next available"><i class="fa fa-chevron-right glyphicon glyphicon-chevron-right"></i></th></tr><tr><th>Su</th><th>Mo</th><th>Tu</th><th>We</th><th>Th</th><th>Fr</th><th>Sa</th></tr></thead><tbody><tr><td class="weekend off available" data-title="r0c0">25</td><td class="off available" data-title="r0c1">26</td><td class="off available" data-title="r0c2">27</td><td class="off available" data-title="r0c3">28</td><td class="off available" data-title="r0c4">29</td><td class="off available" data-title="r0c5">30</td><td class="weekend available" data-title="r0c6">1</td></tr><tr><td class="weekend available" data-title="r1c0">2</td><td class="available" data-title="r1c1">3</td><td class="available" data-title="r1c2">4</td><td class="available" data-title="r1c3">5</td><td class="available" data-title="r1c4">6</td><td class="available" data-title="r1c5">7</td><td class="weekend available" data-title="r1c6">8</td></tr><tr><td class="weekend available" data-title="r2c0">9</td><td class="available" data-title="r2c1">10</td><td class="available" data-title="r2c2">11</td><td class="available" data-title="r2c3">12</td><td class="available" data-title="r2c4">13</td><td class="available" data-title="r2c5">14</td><td class="weekend available" data-title="r2c6">15</td></tr><tr><td class="weekend available" data-title="r3c0">16</td><td class="available" data-title="r3c1">17</td><td class="today active start-date active end-date available" data-title="r3c2">18</td><td class="available" data-title="r3c3">19</td><td class="available" data-title="r3c4">20</td><td class="available" data-title="r3c5">21</td><td class="weekend available" data-title="r3c6">22</td></tr><tr><td class="weekend available" data-title="r4c0">23</td><td class="available" data-title="r4c1">24</td><td class="available" data-title="r4c2">25</td><td class="available" data-title="r4c3">26</td><td class="available" data-title="r4c4">27</td><td class="available" data-title="r4c5">28</td><td class="weekend available" data-title="r4c6">29</td></tr><tr><td class="weekend available" data-title="r5c0">30</td><td class="available" data-title="r5c1">31</td><td class="off available" data-title="r5c2">1</td><td class="off available" data-title="r5c3">2</td><td class="off available" data-title="r5c4">3</td><td class="off available" data-title="r5c5">4</td><td class="weekend off available" data-title="r5c6">5</td></tr></tbody></table></div></div><div class="calendar right" style="display: none;"><div class="daterangepicker_input"><input class="input-mini form-control" type="text" name="daterangepicker_end" value="" style="display: none;"><i class="fa fa-calendar glyphicon glyphicon-calendar" style="display: none;"></i><div class="calendar-time" style="display: none;"><div></div><i class="fa fa-clock-o glyphicon glyphicon-time"></i></div></div><div class="calendar-table"><table class="table-condensed"><thead><tr><th></th><th colspan="5" class="month">Nov 2016</th><th class="next available"><i class="fa fa-chevron-right glyphicon glyphicon-chevron-right"></i></th></tr><tr><th>Su</th><th>Mo</th><th>Tu</th><th>We</th><th>Th</th><th>Fr</th><th>Sa</th></tr></thead><tbody><tr><td class="weekend off available" data-title="r0c0">30</td><td class="off available" data-title="r0c1">31</td><td class="available" data-title="r0c2">1</td><td class="available" data-title="r0c3">2</td><td class="available" data-title="r0c4">3</td><td class="available" data-title="r0c5">4</td><td class="weekend available" data-title="r0c6">5</td></tr><tr><td class="weekend available" data-title="r1c0">6</td><td class="available" data-title="r1c1">7</td><td class="available" data-title="r1c2">8</td><td class="available" data-title="r1c3">9</td><td class="available" data-title="r1c4">10</td><td class="available" data-title="r1c5">11</td><td class="weekend available" data-title="r1c6">12</td></tr><tr><td class="weekend available" data-title="r2c0">13</td><td class="available" data-title="r2c1">14</td><td class="available" data-title="r2c2">15</td><td class="available" data-title="r2c3">16</td><td class="available" data-title="r2c4">17</td><td class="available" data-title="r2c5">18</td><td class="weekend available" data-title="r2c6">19</td></tr><tr><td class="weekend available" data-title="r3c0">20</td><td class="available" data-title="r3c1">21</td><td class="available" data-title="r3c2">22</td><td class="available" data-title="r3c3">23</td><td class="available" data-title="r3c4">24</td><td class="available" data-title="r3c5">25</td><td class="weekend available" data-title="r3c6">26</td></tr><tr><td class="weekend available" data-title="r4c0">27</td><td class="available" data-title="r4c1">28</td><td class="available" data-title="r4c2">29</td><td class="available" data-title="r4c3">30</td><td class="off available" data-title="r4c4">1</td><td class="off available" data-title="r4c5">2</td><td class="weekend off available" data-title="r4c6">3</td></tr><tr><td class="weekend off available" data-title="r5c0">4</td><td class="off available" data-title="r5c1">5</td><td class="off available" data-title="r5c2">6</td><td class="off available" data-title="r5c3">7</td><td class="off available" data-title="r5c4">8</td><td class="off available" data-title="r5c5">9</td><td class="weekend off available" data-title="r5c6">10</td></tr></tbody></table></div></div><div class="ranges" style="display: none;"><div class="range_inputs"><button class="applyBtn btn btn-sm btn-success" type="button">Apply</button> <button class="cancelBtn btn btn-sm btn-default" type="button">Cancel</button></div></div></div>


                                                <fieldset>
                                                    <div class="control-group">
                                                        <div class="controls">
                                                            <div class="col-md-12 xdisplay_inputx form-group has-feedback my_data_dv">
                                                                <input type="text" class="form-control has-feedback-left my_tl" id="single_cal1" placeholder="First Name" aria-describedby="inputSuccess2Status">
                                                                <span id="inputSuccess2Status" class="sr-only">(success)</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </fieldset>

                                            </div>
                                        </td>
                                        <td>本人电话</td>
                                        <td colspan="2"><input type="number"></td>
                                    </tr>
                                    <tr>
                                        <td>身份证号</td>
                                        <td colspan="2"><input type="number"></td>
                                        <td>地址</td>
                                        <td colspan="8"><input type="text"></td>
                                    </tr>
                                    <tr>
                                        <td>既往病史</td>
                                        <td colspan="5"></td>
                                        <td>孕产情况</td>
                                        <td colspan="5"></td>
                                    </tr>
                                    <tr>
                                        <td>联系人</td>
                                        <td><input type="text"></td>
                                        <td>联系人电话</td>
                                        <td><input type="number"></td>
                                        <td>常住类型</td>
                                        <td></td>
                                        <td>民族</td>
                                        <td><input type="text"></td>
                                        <td>血型</td>
                                        <td>O型</td>
                                        <td>RH阴性</td>
                                        <td>XX</td>
                                    </tr>
                                    <tr>
                                        <td>文化程度</td>
                                        <td>本科</td>
                                        <td>职业</td>
                                        <td colspan="2"><input type="text"></td>
                                        <td>婚姻状况</td>
                                        <td>未婚</td>
                                        <td colspan="2">医疗费用支付方式</td>
                                        <td colspan="3">微信支付</td>
                                    </tr>
                                    <tr>
                                        <td>药物过敏史</td>
                                        <td colspan="10" class="my_tl my_disease_history">
                                        </td>
                                        <td class="my_add">
                                            <button type="button" class="btn btn-primary my_add_btn id_add_allergy" data-toggle="modal" data-target=".bs-example-modal-lg"><i class="fa fa-plus-circle"></i></button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>暴露史</td>
                                        <td colspan="10" class="my_tl my_disease_history">
                                        </td>
                                        <td class="my_add">
                                            <button type="button" class="btn btn-primary my_add_btn" data-toggle="modal" data-target=".bs-example-modal-lg"><i class="fa fa-plus-circle"></i></button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>手术史</td>
                                        <td colspan="10" class="my_tl my_disease_history">
                                        </td>
                                        <td class="my_add">
                                            <button type="button" class="btn btn-primary my_add_btn" data-toggle="modal" data-target=".bs-example-modal-lg"><i class="fa fa-plus-circle"></i></button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>外伤史</td>
                                        <td colspan="10" class="my_tl my_disease_history">
                                        </td>
                                        <td class="my_add">
                                            <button type="button" class="btn btn-primary my_add_btn" data-toggle="modal" data-target=".bs-example-modal-lg"><i class="fa fa-plus-circle"></i></button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>输血史</td>
                                        <td colspan="10" class="my_tl my_disease_history">
                                        </td>
                                        <td class="my_add">
                                            <button type="button" class="btn btn-primary my_add_btn" data-toggle="modal" data-target=".bs-example-modal-lg"><i class="fa fa-plus-circle"></i></button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>家族史</td>
                                        <td colspan="10" class="my_tl my_disease_history">
                                        </td>
                                        <td class="my_add">
                                            <button type="button" class="btn btn-primary my_add_btn" data-toggle="modal" data-target=".bs-example-modal-lg"><i class="fa fa-plus-circle"></i></button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>遗传病史</td>
                                        <td colspan="10" class="my_tl my_disease_history">
                                        </td>
                                        <td class="my_add">
                                            <button type="button" class="btn btn-primary my_add_btn" data-toggle="modal" data-target=".bs-example-modal-lg"><i class="fa fa-plus-circle"></i></button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>残疾情况</td>
                                        <td colspan="10" class="my_tl my_disease_history">
                                        </td>
                                        <td class="my_add">
                                            <button type="button" class="btn btn-primary my_add_btn" data-toggle="modal" data-target=".bs-example-modal-lg"><i class="fa fa-plus-circle"></i></button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>厨房排风</td>
                                        <td><input type="text"></td>
                                        <td>燃料类型</td>
                                        <td><input type="text"></td>
                                        <td>饮水</td>
                                        <td colspan="2"><input type="text"></td>
                                        <td>厕所</td>
                                        <td><input type="text"></td>
                                        <td>禽畜栏</td>
                                        <td colspan="2"><input type="text"></td>
                                    </tr>
                                    </tbody>
                                </table>

                                <!--添加按钮弹出框-->
                                <div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">
                                    <div class="modal-dialog modal-lg">
                                        <div class="modal-content">

                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span>
                                                </button>
                                                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                                            </div>
                                            <div class="modal-body">
                                                <!--<h4>Text in a modal</h4>-->
                                                <h4>请填写：</h4>
                                                <textarea name="" id="" cols="30" rows="10" class="form-control id_message"></textarea>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                                <button type="button" class="btn btn-primary id_message_save">保存</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

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
<script src="assets/js/date.js"></script>
<!-- bootstrap-daterangepicker -->
<script src="./assets/js/moment.min.js"></script>
<script src="/assets/js/daterangepicker.js"></script>
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
<script src="./js/new_data.js"></script>

</body>
</html>
