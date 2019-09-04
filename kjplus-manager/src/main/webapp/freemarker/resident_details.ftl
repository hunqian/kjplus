<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>本区居民列表详情</title>

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
    <!-- bootstrap-wysiwyg -->
    <link href="../vendors/google-code-prettify/bin/prettify.min.css" rel="stylesheet">
    <!-- Select2 -->
    <link href="../vendors/select2/dist/css/select2.min.css" rel="stylesheet">
    <!-- Switchery -->
    <link href="../vendors/switchery/dist/switchery.min.css" rel="stylesheet">
    <!-- starrr -->
    <link href="../vendors/starrr/dist/starrr.css" rel="stylesheet">
    <!-- JQVMap -->
    <link href="./assets/css/jqvmap.min.css" rel="stylesheet"/>
    <!-- bootstrap-daterangepicker -->
    <link href="./assets/css/daterangepicker.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="./assets/css/custom.min.css" rel="stylesheet">

    <!--my-class-->
    <link href="./css/resident_details.css" rel="stylesheet">

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
                                <h2 class="my_location">医生/本区居民列表</h2>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">

                                <div class="" role="tabpanel" data-example-id="togglable-tabs">
                                        <ul id="myTab" class="nav nav-tabs bar_tabs my_nav" role="tablist">
                                            <li role="presentation" class="active"><a href="#tab_content1" id="home-tab" role="tab" data-toggle="tab" aria-expanded="true">个人基本信息</a>
                                            </li>
                                            <li role="presentation" class=""><a href="#tab_content2" role="tab" id="profile-tab" data-toggle="tab" aria-expanded="false">随访访视</a>
                                            </li>
                                            <li role="presentation" class=""><a href="#tab_content3" role="tab" id="profile-tab2" data-toggle="tab" aria-expanded="false">体检</a>
                                            </li>
                                            <li role="presentation" class=""><a href="#tab_content4" role="tab" id="profile-tab3" data-toggle="tab" aria-expanded="false">诊疗</a>
                                            </li>
                                            <li role="presentation" class=""><a href="#tab_content5" role="tab" id="profile-tab4" data-toggle="tab" aria-expanded="false">转诊</a>
                                            </li>
                                            <li role="presentation" class=""><a href="#tab_content6" role="tab" id="profile-tab5" data-toggle="tab" aria-expanded="false">测量记录</a>
                                            </li>
                                            <li role="presentation" class=""><a href="#tab_content7" role="tab" id="profile-tab6" data-toggle="tab" aria-expanded="false">咨询记录</a>
                                            </li>
                                            <li role="presentation" class="my_edit_box" style="float: right;">
                                                <a href="javascript:;">
                                                    <button class="my_edit id_my_edit">编辑</button>
                                                    <button class="my_save id_my_save" style="display: none;">保存</button>
                                                </a>
                                            </li>
                                        </ul>
                                        <div id="myTabContent" class="tab-content">
                                            <div role="tabpanel" class="tab-pane fade active in" id="tab_content1" aria-labelledby="home-tab">
                                                <table class="table table-striped table-bordered my_table id_my_table">
                                                    <thead>
                                                        <tr>
                                                            <th colspan="12" class="my_table_title my_tc">个人信息</th>
                                                        </tr>
                                                    </thead>

                                                    <tbody class="my_tc">
                                                    <tr>
                                                        <td>姓名</td>
                                                        <td><input type="text" value="张三" readonly="readonly"></td>
                                                        <td>编号</td>
                                                        <td><input type="text" value="13122435" readonly="readonly"></td>
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
                                                        <td colspan="2"><input type="number" value="1388888888" readonly="readonly"></td>
                                                    </tr>
                                                    <tr>
                                                        <td>身份证号</td>
                                                        <td colspan="2"><input type="number" value="110999888888888888" readonly="readonly"></td>
                                                        <td>地址</td>
                                                        <td colspan="8"><input type="text" value="中关村创业大厦" readonly="readonly"></td>
                                                    </tr>
                                                    <tr>
                                                        <td>既往病史</td>
                                                        <td colspan="5"></td>
                                                        <td>孕产情况</td>
                                                        <td colspan="5"></td>
                                                    </tr>
                                                    <tr>
                                                        <td>联系人</td>
                                                        <td><input type="text" value="李四" readonly="readonly"></td>
                                                        <td>联系人电话</td>
                                                        <td><input type="number" value="13888888888" readonly="readonly"></td>
                                                        <td>常住类型</td>
                                                        <td></td>
                                                        <td>民族</td>
                                                        <td><input type="text" value="汉族" readonly="readonly"></td>
                                                        <td>血型</td>
                                                        <td>O型</td>
                                                        <td>RH阴性</td>
                                                        <td>XX</td>
                                                    </tr>
                                                    <tr>
                                                        <td>文化程度</td>
                                                        <td>本科</td>
                                                        <td>职业</td>
                                                        <td colspan="2"><input type="text" value="程序员" readonly="readonly"></td>
                                                        <td>婚姻状况</td>
                                                        <td>未婚</td>
                                                        <td colspan="2">医疗费用支付方式</td>
                                                        <td colspan="3">微信支付</td>
                                                    </tr>
                                                    <tr>
                                                        <td>药物过敏史</td>
                                                        <td colspan="10" class="my_tl my_disease_history">
                                                            <p>1、阿斯顿发生的发生的发生的发生地发顺丰</p>
                                                        </td>
                                                        <td class="my_add">
                                                            <button type="button" class="btn btn-primary my_add_btn" data-toggle="modal" data-target=".bs-example-modal-lg"><i class="fa fa-plus-circle"></i></button>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>暴露史</td>
                                                        <td colspan="10" class="my_tl my_disease_history">
                                                            <p>1、阿斯顿发生的发生的发生的发生地发顺丰</p>
                                                            <p>2、阿斯顿发生的发生的发生的发生地发顺丰</p>
                                                        </td>
                                                        <td class="my_add">
                                                            <button type="button" class="btn btn-primary my_add_btn" data-toggle="modal" data-target=".bs-example-modal-lg"><i class="fa fa-plus-circle"></i></button>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>手术史</td>
                                                        <td colspan="10" class="my_tl my_disease_history">
                                                            <p>1、阿斯顿发生的发生的发生的发生地发顺丰</p>
                                                        </td>
                                                        <td class="my_add">
                                                            <button type="button" class="btn btn-primary my_add_btn" data-toggle="modal" data-target=".bs-example-modal-lg"><i class="fa fa-plus-circle"></i></button>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>外伤史</td>
                                                        <td colspan="10" class="my_tl my_disease_history">
                                                            <p>1、阿斯顿发生的发生的发生的发生地发顺丰</p>
                                                        </td>
                                                        <td class="my_add">
                                                            <button type="button" class="btn btn-primary my_add_btn" data-toggle="modal" data-target=".bs-example-modal-lg"><i class="fa fa-plus-circle"></i></button>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>输血史</td>
                                                        <td colspan="10" class="my_tl my_disease_history">
                                                            <!--<p>1、阿斯顿发生的发生的发生的发生地发顺丰</p>-->
                                                        </td>
                                                        <td class="my_add">
                                                            <button type="button" class="btn btn-primary my_add_btn" data-toggle="modal" data-target=".bs-example-modal-lg"><i class="fa fa-plus-circle"></i></button>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>家族史</td>
                                                        <td colspan="10" class="my_tl my_disease_history">
                                                            <p>1、阿斯顿发生的发生的发生的发生地发顺丰</p>
                                                            <p>2、阿斯顿发生的发生的发生的发生地发顺丰</p>
                                                            <p>3、阿斯顿发生的发生的发生的发生地发顺丰</p>
                                                            <p>4、阿斯顿发生的发生的发生的发生地发顺丰</p>
                                                            <p>5、阿斯顿发生的发生的发生的发生地发顺丰</p>
                                                        </td>
                                                        <td class="my_add">
                                                            <button type="button" class="btn btn-primary my_add_btn" data-toggle="modal" data-target=".bs-example-modal-lg"><i class="fa fa-plus-circle"></i></button>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>遗传病史</td>
                                                        <td colspan="10" class="my_tl my_disease_history">
                                                            <p>1、阿斯顿发生的发生的发生的发生地发顺丰阿斯顿发生的发生的发生的发生地发顺丰阿斯顿发生的发生的发生的发生地发顺丰阿斯顿发生的发生的发生的发生地发顺丰阿斯顿发生的发生的发生的发生地发顺丰阿斯顿发生的发生的发生的发生地发顺丰阿斯顿发生的发生的发生的发生地发顺丰阿斯顿发生的发生的发生的发生地发顺丰阿斯顿发生的发生的发生的发生地发顺丰</p>
                                                            <p>2、阿斯顿发生的发生的发生的发生地发顺丰</p>
                                                        </td>
                                                        <td class="my_add">
                                                            <button type="button" class="btn btn-primary my_add_btn" data-toggle="modal" data-target=".bs-example-modal-lg"><i class="fa fa-plus-circle"></i></button>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>残疾情况</td>
                                                        <td colspan="10" class="my_tl my_disease_history">
                                                            <p>1、阿斯顿发生的发生的发生的发生地发顺丰</p>
                                                        </td>
                                                        <td class="my_add">
                                                            <button type="button" class="btn btn-primary my_add_btn" data-toggle="modal" data-target=".bs-example-modal-lg"><i class="fa fa-plus-circle"></i></button>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>厨房排风</td>
                                                        <td><input type="text" value="" readonly="readonly"></td>
                                                        <td>燃料类型</td>
                                                        <td><input type="text" value="" readonly="readonly"></td>
                                                        <td>饮水</td>
                                                        <td colspan="2"><input type="text" value="" readonly="readonly"></td>
                                                        <td>厕所</td>
                                                        <td><input type="text" value="" readonly="readonly"></td>
                                                        <td>禽畜栏</td>
                                                        <td colspan="2"></td>
                                                    </tr>

                                                    </tbody>
                                                </table>
                                            </div>
                                            <div role="tabpanel" class="tab-pane fade my_sffs" id="tab_content2" aria-labelledby="profile-tab">
                                                <div class="id_sffs_list">
                                                    <table id="datatable-buttons" class="table table-striped table-bordered bulk_action my_table " style="width: 100%;">
                                                        <thead>
                                                        <tr>
                                                            <th><input type="checkbox" id="check-all" class="flat"></th>
                                                            <th>时间</th>
                                                            <th>姓名</th>
                                                            <th>性别</th>
                                                            <th>年龄</th>
                                                            <th>随访类别</th>
                                                            <th>结果分类</th>
                                                            <th>操作人</th>
                                                            <th>查看详情</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr>
                                                            <td><input type="checkbox" class="flat" name="table_records"></td>
                                                            <td>2017/01/04</td>
                                                            <td>张三</td>
                                                            <td>男</td>
                                                            <td>67</td>
                                                            <td>高血压随访</td>
                                                            <td>未见异常，控制满意</td>
                                                            <td>王东</td>
                                                            <td><a href="#" class="id_details">详情</a></td>
                                                        </tr>
                                                        <tr>
                                                            <td><input type="checkbox" class="flat" name="table_records"></td>
                                                            <td>2017/01/04</td>
                                                            <td>张三</td>
                                                            <td>男</td>
                                                            <td>67</td>
                                                            <td>高血压随访</td>
                                                            <td>未见异常，控制满意</td>
                                                            <td>王东</td>
                                                            <td><a href="#" class="id_details">详情</a></td>
                                                        </tr>
                                                        <tr>
                                                            <td><input type="checkbox" class="flat" name="table_records"></td>
                                                            <td>2017/01/04</td>
                                                            <td>张三</td>
                                                            <td>男</td>
                                                            <td>67</td>
                                                            <td>高血压随访</td>
                                                            <td>未见异常，控制满意</td>
                                                            <td>王东</td>
                                                            <td><a href="#" class="id_details">详情</a></td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div class="id_sffs_content" style="display: none; ">
                                                    <table class="table table-striped table-bordered my_table" style="width: 100%;">
                                                    <!--随访访视详情-->
                                                    <tbody>
                                                    <tr class="portrait_box">
                                                        <td class="portrait">头像</td>
                                                        <td colspan="11"></td>
                                                    </tr>
                                                    <tr>
                                                        <td>随访类别</td>
                                                        <td colspan="2">高血压随访</td>
                                                        <td>随访日期</td>
                                                        <td>2017/03/02</td>
                                                        <td>随访方式</td>
                                                        <td colspan="">家庭</td>
                                                        <td>症状</td>
                                                        <td colspan="4">头痛</td>
                                                    </tr>
                                                    <tr>
                                                        <td>体重（kg）</td>
                                                        <td>60</td>
                                                        <td>收缩压</td>
                                                        <td>XX</td>
                                                        <td>舒张压</td>
                                                        <td>XX</td>
                                                        <td>心率</td>
                                                        <td>XX</td>
                                                        <td>体质指数</td>
                                                        <td>XX</td>
                                                        <td>其他特征</td>
                                                        <td>XXX</td>
                                                    </tr>
                                                    <tr>
                                                        <td>日吸烟量</td>
                                                        <td colspan="2">XX</td>
                                                        <td>日饮酒量</td>
                                                        <td colspan="2">XX</td>
                                                        <td>运动情况</td>
                                                        <td colspan="2">XX</td>
                                                        <td>摄盐情况</td>
                                                        <td colspan="2">XX</td>
                                                    </tr>
                                                    <tr>
                                                        <td>心理调节</td>
                                                        <td colspan="5">XX</td>
                                                        <td>尊医行为</td>
                                                        <td colspan="5">XX</td>
                                                    </tr>
                                                    <tr>
                                                        <td>服药依从性</td>
                                                        <td colspan="11">XX</td>
                                                    </tr>
                                                    <tr>
                                                        <td>药物不良反应</td>
                                                        <td colspan="11">XX</td>
                                                    </tr>
                                                    <tr>
                                                        <td>此次随访分类</td>
                                                        <td colspan="11">XX</td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                                </div>
                                            </div>
                                            <div role="tabpanel" class="tab-pane fade" id="tab_content3" aria-labelledby="profile-tab">
                                                <div class="id_tj_list">
                                                    <table class="table table-striped table-bordered bulk_action my_table " style="width: 100%;">
                                                        <thead>
                                                        <tr>
                                                            <th><input type="checkbox" class="flat"></th>
                                                            <th>体检日期</th>
                                                            <th>项目类别</th>
                                                            <th>结果分类</th>
                                                            <th>健康指导</th>
                                                            <th>医生</th>
                                                            <th>新增</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr>
                                                            <td><input type="checkbox" class="flat" name="table_records"></td>
                                                            <td>2017/01/04</td>
                                                            <td>老年人免费体检</td>
                                                            <td>血压偏高</td>
                                                            <td>适量运动，饮食控油，低盐</td>
                                                            <td>李四</td>
                                                            <td>详情</td>
                                                        </tr>
                                                        <tr>
                                                            <td><input type="checkbox" class="flat" name="table_records"></td>
                                                            <td>2017/01/04</td>
                                                            <td>老年人免费体检</td>
                                                            <td>血压偏高</td>
                                                            <td>适量运动，饮食控油，低盐</td>
                                                            <td>李四</td>
                                                            <td>详情</td>
                                                        </tr>
                                                        <tr>
                                                            <td><input type="checkbox" class="flat" name="table_records"></td>
                                                            <td>2017/01/04</td>
                                                            <td>老年人免费体检</td>
                                                            <td>血压偏高</td>
                                                            <td>适量运动，饮食控油，低盐</td>
                                                            <td>李四</td>
                                                            <td>详情</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                            <div role="tabpanel" class="tab-pane fade" id="tab_content4" aria-labelledby="profile-tab">
                                                <div class="id_zl_list">
                                                    <table class="table table-striped table-bordered bulk_action my_table " style="width: 100%;">
                                                        <thead>
                                                        <tr>
                                                            <th><input type="checkbox" class="flat"></th>
                                                            <th>诊疗日期</th>
                                                            <th>诊断结果</th>
                                                            <th>诊断处置</th>
                                                            <th>医生</th>
                                                            <th>新增</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr>
                                                            <td><input type="checkbox" class="flat" name="table_records"></td>
                                                            <td>2017/01/04</td>
                                                            <td>痛风</td>
                                                            <td>口服XXX，控制高嘌呤食品摄入</td>
                                                            <td>李四</td>
                                                            <td>详情</td>
                                                        </tr>
                                                        <tr>
                                                            <td><input type="checkbox" class="flat" name="table_records"></td>
                                                            <td>2017/01/04</td>
                                                            <td>痛风</td>
                                                            <td>口服XXX，控制高嘌呤食品摄入</td>
                                                            <td>李四</td>
                                                            <td>详情</td>
                                                        </tr>
                                                        <tr>
                                                            <td><input type="checkbox" class="flat" name="table_records"></td>
                                                            <td>2017/01/04</td>
                                                            <td>痛风</td>
                                                            <td>口服XXX，控制高嘌呤食品摄入</td>
                                                            <td>李四</td>
                                                            <td>详情</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                            <div role="tabpanel" class="tab-pane fade" id="tab_content5" aria-labelledby="profile-tab">
                                                <div class="id_zl_list">
                                                    <table class="table table-striped table-bordered bulk_action my_table " style="width: 100%;">
                                                        <thead>
                                                        <tr>
                                                            <th><input type="checkbox" class="flat"></th>
                                                            <th>转诊日期</th>
                                                            <th>类别</th>
                                                            <th>上级医院</th>
                                                            <th>简况</th>
                                                            <th>医生</th>
                                                            <th>新增</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr>
                                                            <td><input type="checkbox" class="flat" name="table_records"></td>
                                                            <td>2017/01/04</td>
                                                            <td>转出</td>
                                                            <td>301医院眼科</td>
                                                            <td>视力模糊</td>
                                                            <td>张三</td>
                                                            <td>详情</td>
                                                        </tr>
                                                        <tr>
                                                            <td><input type="checkbox" class="flat" name="table_records"></td>
                                                            <td>2017/01/04</td>
                                                            <td>转出</td>
                                                            <td>301医院眼科</td>
                                                            <td>视力模糊</td>
                                                            <td>张三</td>
                                                            <td>详情</td>
                                                        </tr>
                                                        <tr>
                                                            <td><input type="checkbox" class="flat" name="table_records"></td>
                                                            <td>2017/01/04</td>
                                                            <td>转出</td>
                                                            <td>301医院眼科</td>
                                                            <td>视力模糊</td>
                                                            <td>张三</td>
                                                            <td>详情</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                            <div role="tabpanel" class="tab-pane fade" id="tab_content6" aria-labelledby="profile-tab">
                                                <div class="id_sffs_list">
                                                    <table class="table table-striped table-bordered bulk_action my_table " style="width: 100%;">
                                                        <thead>
                                                        <tr>
                                                            <th><input type="checkbox" class="flat"></th>
                                                            <th>姓名</th>
                                                            <th>性别</th>
                                                            <th>年龄</th>
                                                            <th>收缩压</th>
                                                            <th>舒张压</th>
                                                            <th>心率</th>
                                                            <th>结果分类</th>
                                                            <th>操作人</th>
                                                            <th>时间</th>
                                                            <th>新增</th>
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
                                                            <td>2017/02/08</td>
                                                            <td><a href="#" class="">详情</a></td>
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
                                                            <td>2017/02/08</td>
                                                            <td><a href="#" class="">详情</a></td>
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
                                                            <td>2017/02/08</td>
                                                            <td><a href="#" class="">详情</a></td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                            <div role="tabpanel" class="tab-pane fade" id="tab_content7" aria-labelledby="profile-tab">
                                                <table class="table table-striped table-bordered my_table">
                                                    <thead>
                                                    <tr>
                                                        <th colspan="12" class="my_table_title my_tc">咨询记录</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>

                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>

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

                                                <div class="x_content">
                                                <form class="form-horizontal form-label-left">
                                                <div class="form-group">
                                                    <label class="col-md-3 col-sm-3 col-xs-12 control-label">Checkboxes and radios
                                                        <br>
                                                        <small class="text-navy">Normal Bootstrap elements</small>
                                                    </label>

                                                    <div class="col-md-9 col-sm-9 col-xs-12">
                                                        <div class="checkbox">
                                                            <label>
                                                                <input type="checkbox" value=""> Option one. select more than one options
                                                            </label>
                                                        </div>
                                                        <div class="checkbox">
                                                            <label>
                                                                <input type="checkbox" value=""> Option two. select more than one options
                                                            </label>
                                                        </div>
                                                        <div class="radio">
                                                            <label>
                                                                <input type="radio" checked="" value="option1" id="optionsRadios1" name="optionsRadios"> Option one. only select one option
                                                            </label>
                                                        </div>
                                                        <div class="radio">
                                                            <label>
                                                                <input type="radio" value="option2" id="optionsRadios2" name="optionsRadios"> Option two. only select one option
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>
                                                </form>
                                                </div>

                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                                <button type="button" class="btn btn-primary id_message_save">保存</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!--=======================================-->
                                <div class="x_panel">
                                    <div class="x_title">
                                        <h2>Form Basic Elements <small>different form elements</small></h2>
                                        <ul class="nav navbar-right panel_toolbox">
                                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                            </li>
                                            <li class="dropdown">
                                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                                <ul class="dropdown-menu" role="menu">
                                                    <li><a href="#">Settings 1</a>
                                                    </li>
                                                    <li><a href="#">Settings 2</a>
                                                    </li>
                                                </ul>
                                            </li>
                                            <li><a class="close-link"><i class="fa fa-close"></i></a>
                                            </li>
                                        </ul>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="x_content">
                                        <br>
                                        <form class="form-horizontal form-label-left">

                                            <div class="form-group">
                                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Default Input</label>
                                                <div class="col-md-9 col-sm-9 col-xs-12">
                                                    <input type="text" class="form-control" placeholder="Default Input">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Disabled Input </label>
                                                <div class="col-md-9 col-sm-9 col-xs-12">
                                                    <input type="text" class="form-control" disabled="disabled" placeholder="Disabled Input">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Read-Only Input</label>
                                                <div class="col-md-9 col-sm-9 col-xs-12">
                                                    <input type="text" class="form-control" readonly="readonly" placeholder="Read-Only Input">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Date Of Birth <span class="required">*</span>
                                                </label>
                                                <div class="col-md-9 col-sm-9 col-xs-12">
                                                    <textarea class="form-control" rows="3" placeholder="rows=&quot;3&quot;"></textarea>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Password</label>
                                                <div class="col-md-9 col-sm-9 col-xs-12">
                                                    <input type="password" class="form-control" value="passwordonetwo">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3 col-sm-3 col-xs-12">AutoComplete</label>
                                                <div class="col-md-9 col-sm-9 col-xs-12">
                                                    <input type="text" name="country" id="autocomplete-custom-append" class="form-control col-md-10">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Select</label>
                                                <div class="col-md-9 col-sm-9 col-xs-12">
                                                    <select class="form-control">
                                                        <option>Choose option</option>
                                                        <option>Option one</option>
                                                        <option>Option two</option>
                                                        <option>Option three</option>
                                                        <option>Option four</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Select Custom</label>
                                                <div class="col-md-9 col-sm-9 col-xs-12">
                                                    <select class="select2_single form-control" tabindex="-1">
                                                        <option></option>
                                                        <option value="AK">Alaska</option>
                                                        <option value="HI">Hawaii</option>
                                                        <option value="CA">California</option>
                                                        <option value="NV">Nevada</option>
                                                        <option value="OR">Oregon</option>
                                                        <option value="WA">Washington</option>
                                                        <option value="AZ">Arizona</option>
                                                        <option value="CO">Colorado</option>
                                                        <option value="ID">Idaho</option>
                                                        <option value="MT">Montana</option>
                                                        <option value="NE">Nebraska</option>
                                                        <option value="NM">New Mexico</option>
                                                        <option value="ND">North Dakota</option>
                                                        <option value="UT">Utah</option>
                                                        <option value="WY">Wyoming</option>
                                                        <option value="AR">Arkansas</option>
                                                        <option value="IL">Illinois</option>
                                                        <option value="IA">Iowa</option>
                                                        <option value="KS">Kansas</option>
                                                        <option value="KY">Kentucky</option>
                                                        <option value="LA">Louisiana</option>
                                                        <option value="MN">Minnesota</option>
                                                        <option value="MS">Mississippi</option>
                                                        <option value="MO">Missouri</option>
                                                        <option value="OK">Oklahoma</option>
                                                        <option value="SD">South Dakota</option>
                                                        <option value="TX">Texas</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Select Grouped</label>
                                                <div class="col-md-9 col-sm-9 col-xs-12">
                                                    <select class="select2_group form-control">
                                                        <optgroup label="Alaskan/Hawaiian Time Zone">
                                                            <option value="AK">Alaska</option>
                                                            <option value="HI">Hawaii</option>
                                                        </optgroup>
                                                        <optgroup label="Pacific Time Zone">
                                                            <option value="CA">California</option>
                                                            <option value="NV">Nevada</option>
                                                            <option value="OR">Oregon</option>
                                                            <option value="WA">Washington</option>
                                                        </optgroup>
                                                        <optgroup label="Mountain Time Zone">
                                                            <option value="AZ">Arizona</option>
                                                            <option value="CO">Colorado</option>
                                                            <option value="ID">Idaho</option>
                                                            <option value="MT">Montana</option>
                                                            <option value="NE">Nebraska</option>
                                                            <option value="NM">New Mexico</option>
                                                            <option value="ND">North Dakota</option>
                                                            <option value="UT">Utah</option>
                                                            <option value="WY">Wyoming</option>
                                                        </optgroup>
                                                        <optgroup label="Central Time Zone">
                                                            <option value="AL">Alabama</option>
                                                            <option value="AR">Arkansas</option>
                                                            <option value="IL">Illinois</option>
                                                            <option value="IA">Iowa</option>
                                                            <option value="KS">Kansas</option>
                                                            <option value="KY">Kentucky</option>
                                                            <option value="LA">Louisiana</option>
                                                            <option value="MN">Minnesota</option>
                                                            <option value="MS">Mississippi</option>
                                                            <option value="MO">Missouri</option>
                                                            <option value="OK">Oklahoma</option>
                                                            <option value="SD">South Dakota</option>
                                                            <option value="TX">Texas</option>
                                                            <option value="TN">Tennessee</option>
                                                            <option value="WI">Wisconsin</option>
                                                        </optgroup>
                                                        <optgroup label="Eastern Time Zone">
                                                            <option value="CT">Connecticut</option>
                                                            <option value="DE">Delaware</option>
                                                            <option value="FL">Florida</option>
                                                            <option value="GA">Georgia</option>
                                                            <option value="IN">Indiana</option>
                                                            <option value="ME">Maine</option>
                                                            <option value="MD">Maryland</option>
                                                            <option value="MA">Massachusetts</option>
                                                            <option value="MI">Michigan</option>
                                                            <option value="NH">New Hampshire</option>
                                                            <option value="NJ">New Jersey</option>
                                                            <option value="NY">New York</option>
                                                            <option value="NC">North Carolina</option>
                                                            <option value="OH">Ohio</option>
                                                            <option value="PA">Pennsylvania</option>
                                                            <option value="RI">Rhode Island</option>
                                                            <option value="SC">South Carolina</option>
                                                            <option value="VT">Vermont</option>
                                                            <option value="VA">Virginia</option>
                                                            <option value="WV">West Virginia</option>
                                                        </optgroup>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Select Multiple</label>
                                                <div class="col-md-9 col-sm-9 col-xs-12">
                                                    <select class="select2_multiple form-control" multiple="multiple">
                                                        <option>Choose option</option>
                                                        <option>Option one</option>
                                                        <option>Option two</option>
                                                        <option>Option three</option>
                                                        <option>Option four</option>
                                                        <option>Option five</option>
                                                        <option>Option six</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Input Tags</label>
                                                <div class="col-md-9 col-sm-9 col-xs-12">
                                                    <input id="tags_1" type="text" class="tags form-control" value="social, adverts, sales" data-tagsinput-init="true" style="display: none;"><div id="tags_1_tagsinput" class="tagsinput" style="width: auto; min-height: 100px; height: 100px;"><span class="tag"><span>social&nbsp;&nbsp;</span><a href="#" title="Removing tag">x</a></span><span class="tag"><span>adverts&nbsp;&nbsp;</span><a href="#" title="Removing tag">x</a></span><span class="tag"><span>sales&nbsp;&nbsp;</span><a href="#" title="Removing tag">x</a></span><div id="tags_1_addTag"><input id="tags_1_tag" value="" data-default="add a tag" style="color: rgb(102, 102, 102); width: 72px;"></div><div class="tags_clear"></div></div>
                                                    <div id="suggestions-container" style="position: relative; float: left; width: 250px; margin: 10px;"></div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 col-sm-3 col-xs-12 control-label">Checkboxes and radios
                                                    <br>
                                                    <small class="text-navy">Normal Bootstrap elements</small>
                                                </label>

                                                <div class="col-md-9 col-sm-9 col-xs-12">
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" value=""> Option one. select more than one options
                                                        </label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" value=""> Option two. select more than one options
                                                        </label>
                                                    </div>
                                                    <div class="radio">
                                                        <label>
                                                            <input type="radio" checked="" value="option1" id="optionsRadios1" name="optionsRadios"> Option one. only select one option
                                                        </label>
                                                    </div>
                                                    <div class="radio">
                                                        <label>
                                                            <input type="radio" value="option2" id="optionsRadios2" name="optionsRadios"> Option two. only select one option
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-md-3 col-sm-3 col-xs-12 control-label">Checkboxes and radios
                                                    <br>
                                                    <small class="text-navy">Normal Bootstrap elements</small>
                                                </label>

                                                <div class="col-md-9 col-sm-9 col-xs-12">
                                                    <div class="checkbox">
                                                        <label class="">
                                                            <div class="icheckbox_flat-green checked" style="position: relative;"><input type="checkbox" class="flat" checked="checked" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins></div> Checked
                                                        </label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label class="">
                                                            <div class="icheckbox_flat-green" style="position: relative;"><input type="checkbox" class="flat" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins></div> Unchecked
                                                        </label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label>
                                                            <div class="icheckbox_flat-green disabled" style="position: relative;"><input type="checkbox" class="flat" disabled="disabled" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins></div> Disabled
                                                        </label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label>
                                                            <div class="icheckbox_flat-green checked disabled" style="position: relative;"><input type="checkbox" class="flat" disabled="disabled" checked="checked" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins></div> Disabled &amp; checked
                                                        </label>
                                                    </div>
                                                    <div class="radio">
                                                        <label class="">
                                                            <div class="iradio_flat-green checked" style="position: relative;"><input type="radio" class="flat" checked="" name="iCheck" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins></div> Checked
                                                        </label>
                                                    </div>
                                                    <div class="radio">
                                                        <label class="">
                                                            <div class="iradio_flat-green" style="position: relative;"><input type="radio" class="flat" name="iCheck" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins></div> Unchecked
                                                        </label>
                                                    </div>
                                                    <div class="radio">
                                                        <label>
                                                            <div class="iradio_flat-green disabled" style="position: relative;"><input type="radio" class="flat" name="iCheck" disabled="disabled" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins></div> Disabled
                                                        </label>
                                                    </div>
                                                    <div class="radio">
                                                        <label>
                                                            <div class="iradio_flat-green checked disabled" style="position: relative;"><input type="radio" class="flat" name="iCheck3" disabled="disabled" checked="" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins></div> Disabled &amp; Checked
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Switch</label>
                                                <div class="col-md-9 col-sm-9 col-xs-12">
                                                    <div class="">
                                                        <label>
                                                            <input type="checkbox" class="js-switch" checked="" data-switchery="true" style="display: none;"><span class="switchery switchery-default" style="background-color: rgb(38, 185, 154); border-color: rgb(38, 185, 154); box-shadow: rgb(38, 185, 154) 0px 0px 0px 11px inset; transition: border 0.4s, box-shadow 0.4s, background-color 1.2s;"><small style="left: 12px; background-color: rgb(255, 255, 255); transition: background-color 0.4s, left 0.2s;"></small></span> Checked
                                                        </label>
                                                    </div>
                                                    <div class="">
                                                        <label>
                                                            <input type="checkbox" class="js-switch" data-switchery="true" style="display: none;"><span class="switchery switchery-default" style="box-shadow: rgb(223, 223, 223) 0px 0px 0px 0px inset; border-color: rgb(223, 223, 223); background-color: rgb(255, 255, 255); transition: border 0.4s, box-shadow 0.4s;"><small style="left: 0px; transition: background-color 0.4s, left 0.2s;"></small></span> Unchecked
                                                        </label>
                                                    </div>
                                                    <div class="">
                                                        <label>
                                                            <input type="checkbox" class="js-switch" disabled="disabled" data-switchery="true" readonly="" style="display: none;"><span class="switchery switchery-default" style="box-shadow: rgb(223, 223, 223) 0px 0px 0px 0px inset; border-color: rgb(223, 223, 223); background-color: rgb(255, 255, 255); transition: border 0.4s, box-shadow 0.4s; opacity: 0.5;"><small style="left: 0px; transition: background-color 0.4s, left 0.2s;"></small></span> Disabled
                                                        </label>
                                                    </div>
                                                    <div class="">
                                                        <label>
                                                            <input type="checkbox" class="js-switch" disabled="disabled" checked="checked" data-switchery="true" readonly="" style="display: none;"><span class="switchery switchery-default" style="background-color: rgb(38, 185, 154); border-color: rgb(38, 185, 154); box-shadow: rgb(38, 185, 154) 0px 0px 0px 11px inset; transition: border 0.4s, box-shadow 0.4s, background-color 1.2s; opacity: 0.5;"><small style="left: 12px; background-color: rgb(255, 255, 255); transition: background-color 0.4s, left 0.2s;"></small></span> Disabled Checked
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>


                                            <div class="ln_solid"></div>
                                            <div class="form-group">
                                                <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
                                                    <button type="button" class="btn btn-primary">Cancel</button>
                                                    <button type="reset" class="btn btn-primary">Reset</button>
                                                    <button type="submit" class="btn btn-success">Submit</button>
                                                </div>
                                            </div>

                                        </form>
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
<!--<script src="./js/resident_details.js"></script>-->


</body>
</html>
