<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <base id="base" href="${base}">
    <title>二型糖尿病患者随访服务记录表</title>
    <meta name="keywords" content="二型糖尿病患者随访服务记录表"/>
    <meta name="description" content="二型糖尿病患者随访服务记录表"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <!-- basic styles -->

    <link href="${base}/assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${base}/assets/css/font-awesome.min.css"/>

    <!--[if IE 7]>
    <link rel="stylesheet" href="${base}/assets/css/font-awesome-ie7.min.css"/>
    <![endif]-->

    <!-- page specific plugin styles -->

    <link rel="stylesheet" href="${base}/assets/css/jquery-ui-1.10.3.custom.min.css"/>
    <link rel="stylesheet" href="${base}/assets/css/jquery.gritter.css"/>

    <!-- fonts -->

    <link rel="stylesheet" href="${base}/assets/css/jquery-ui-1.10.3.custom.min.css"/>
    <link rel="stylesheet" href="${base}/assets/css/jquery.gritter.css"/>
    <link rel="stylesheet" href="${base}/assets/css/select2.css"/>
    <link rel="stylesheet" href="${base}/assets/css/bootstrap-editable.css"/>

    <!-- ace styles -->

    <link rel="stylesheet" href="${base}/assets/css/ace.min.css"/>
    <link rel="stylesheet" href="${base}/assets/css/ace-rtl.min.css"/>
    <link rel="stylesheet" href="${base}/assets/css/ace-skins.min.css"/>

    <!--[if lte IE 8]>
    <link rel="stylesheet" href="${base}/assets/css/ace-ie.min.css"/>
    <![endif]-->

    <!-- inline styles related to this page -->

    <style>
        b {
            color: black;
        }

        .center [class*="col-"] {
            position: relative;
            text-overflow: ellipsis;
            height: 40px;
            line-height: 40px;
            border: 1px solid #ccc;
        }

        .center [class*="col-"] span {
            position: relative;
            z-index: 2;
            display: inline-block;
            text-overflow: ellipsis;
            color: #336199;
            width: 100%;
            height: 100%;
        }
		.adjective input{
			width:auto;
			text-align: initial !important;
		}
		.adjective {text-align: initial !important;}
		.show{text-align: initial !important;}
		.adjective p{
			width:auto;
			text-align: initial;
		}
        input {
            border: none !important;
            padding: 0 !important;
            width: 100%;
            color: black !important;
        }

		#editfollowuppage{
			position: relative;
			top: 20;
			left: 20;
			z-index: 16;
			opacity: 1.0;
		}
        .floatLeft {
            float: left;
            border: 1px solid #DDD;
            width: 8.334%;
            color: #336199;
        }

        .medicine {
            height: 280px;
            line-height: 45px;
        }

        .programme {
            height: 320px;
            line-height: 80px;
        }
		.nav-tabs{border-bottom: none !important;}
        .hospital {
            height: 200px;
            line-height: 40px;
        }

        .assist {
            height: 80px;
            line-height: 20px;
        }

        em {
            position: absolute;
            top: 0;
            left: 100px;
            color: black;
        }

        strong {
            position: absolute;
            left: 70px;
            top: 125px;
            color: #000;
        }

        .black {
            color: #000 !important;
        }

        .addheight {
            height: 160px !important;
        }

        #cover {
            position: fixed;
            top: 0;
            left: 0;
            z-index: 14;
            width: 100%;
            height: 100%;
            background: #ccc none repeat scroll 0% 0%;
            opacity: 0.5;
        }

        .symptom, .dangerous, .examine {
            width: 66rem;
            height: 19.75rem;
            display: none;
            border: 1px solid #ccc;
            position: fixed;
            left: 25%;
            top: 9rem;
            z-index: 16;
            background: #fff;
            border-radius: 2.5rem;
            padding-left: 40px;
        }
		.adjective, .expose, .disability, .family, .complexionUp, .familyMother, .familyBrothers, .familyChildren, .propose, .examine{
			width: 66rem;
			height: 25.75rem;
			display: none;
			border: 1px solid #ccc;
			position: fixed;
			left: 25%;
			top: 9rem;
			z-index: 16;
			background: #fff;
			border-radius: 2.5rem;
			padding-left: 40px; 
		}
        .symptom input, .dangerous input, .examine input {
            width: auto !important;
        }

        .coloradd {
            background-color: #edf3f4;
        }

        i {
            color: black;
            margin-right: 10px;
        }
    </style>

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
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>
    <div class="main-container-inner">
        <a class="menu-toggler" id="menu-toggler" href="#"> <span class="menu-text"></span> </a>
        <div class="sidebar" id="sidebar">
        <#include 'menu_ace.ftl'/>
        </div>
        <div class="main-content">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try {
                        ace.settings.check('breadcrumbs', 'fixed')
                    } catch (e) {
                    }
                </script>
                <ul class="breadcrumb">
                    <li><i class="icon-home home-icon"></i> <a href="#">主页</a></li>
                    <li class="active">随访访视</li>
                    <li class="active"><#if prsnName??>${prsnName!''}</#if></li>
                </ul>
                <!-- .breadcrumb -->
            </div>
            <div class="page-content">
                <div class="page-header">
                    <h1> 二型糖尿病患者随访服务记录表 </h1>
                </div>
                <!-- /.page-header -->
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <div class="center">
                            <div class="row">

                                <div class="tabbable">
                                    <input type="hidden" id="followupid" value="<#if followupid??>${followupid!''}</#if>">
						              <input type="hidden" id="tableCode" value="<#if tableCode??>${tableCode!''}</#if>">
						              <input type="hidden" id="prsnId" value="<#if prsnId??>${prsnId!''}</#if>">
									  <input type="hidden" id="activepage" value="<#if activepage??>${activepage!''}</#if>">
                                    <div class="tab-content" style="width: 1000px;">
                                        <div id="followup" class="tab-pane in active">
                                            <div class="col-xs-12">
				                	<span><font size="5" color="blue">二型糖尿病患者随访信息</font>
				                	</span>
                                            </div>
                                            <div class="center">
                                                <div class="row text">
	                                            	<div class="col-xs-2 coloradd"> 
									                   <span>姓名</span> 
									                  </div> 
									                  <div class="col-xs-4" style="padding-top: 1px;padding-bottom: 0px;"> 
									                   <span><input class="col-xs-12" type="text" placeholder="请输入" style="border: none;height: 22px;" value ="<#if prsnName??>${prsnName!''}</#if>"/></span> 
									                  </div> 
									                  <div class="col-xs-2 coloradd"> 
									                   <span>编号</span> 
									                  </div> 
									                  <div class="col-xs-4" style="padding-top: 1px;padding-bottom: 0px;"> 
									                   <span><input class="col-xs-12" type="text" readonly="readonly" placeholder="系统自动生成" style="border: none;height: 22px;" value ="<#if followCode??>${followCode!''}</#if>"/></span> 
									                  </div> 
                                                    <div class="col-xs-3 coloradd">
                                                        <span>随访日期</span>
                                                    </div>
                                                    <div class="col-xs-9">
                                                        <input type="date" id="cfgline_1">
                                                    </div>
                                                    <div class="col-xs-3 coloradd">
                                                        <span>随访方式</span>
                                                    </div>
                                                    <div class="col-xs-9">
                                                        <span class="editable" id="cfgline_2"></span>
                                                    </div>
                                                    <div class="col-xs-3 coloradd" style="color: #000;height: 80px;line-height: 80px;">
                                                        <span>症状</span>
                                                    </div>
                                                    <div class="col-xs-9">
                                                        <span><input type="text" class=" show_cfgline_3" placeholder="请选择" style="border: none;padding-bottom: 2px;" id="cfgline_3" /></span>	
                                                    </div>
                                                    <div class="col-xs-9">
														<span style="text-align: initial;">
															<i>其他：</i>
															<input type="text" placeholder="请输入" style="width: 91%;" id="cfgline_4">
														</span>
                                                    </div>
                                                    <div class="floatLeft coloradd hospital">
                                                        <span>体<br>	征<br></span>
                                                    </div>
                                                    <div class="col-xs-2 coloradd">
                                                        <span>血压（mmHg）</span>
                                                    </div>
                                                    <div class="col-xs-9">
                                                        <input type="text" placeholder="请输入" style="width: 40%;" id="cfgline_4_1_1">/
														<input type="text" placeholder="请输入" style="width: 40%;" id="cfgline_4_1_2">
                                                    </div>
                                                    <div class="col-xs-2 coloradd">
                                                        <span>体重（kg）</span>
                                                    </div>
                                                    <div class="col-xs-9">
											<span>
												<input type="text" placeholder="请输入" style="width: 40%;" id="cfgline_4_2_1">/
												<input type="text" placeholder="请输入" style="width: 40%;" id="cfgline_4_2_2">
											</span>
                                                    </div>
                                                    <div class="col-xs-2	 coloradd">
                                                        <span>体质指数（kg/m<sup>2</sup>）</span>
                                                    </div>
                                                    <div class="col-xs-9">
											<span>
												<input type="text" placeholder="请输入" style="width: 40%;" id="cfgline_4_3_1">/
												<input type="text" placeholder="请输入" style="width: 40%;" id="cfgline_4_3_2">
											</span>
                                                    </div>
                                                    <div class="col-xs-2 coloradd">
                                                        <span>足杯动脉搏动</span>
                                                    </div>
                                                    <div class="col-xs-9">
                                                        <span><input type="text" placeholder="请输入" id="cfgline_4_4"></span>
                                                    </div> 
                                                    <div class="col-xs-2 coloradd">
                                                        <span>其他</span>
                                                    </div>                                              
                                                    <div class="col-xs-9">
                                                        <span>
                                                        	<input type="text" placeholder="请输入" id="cfgline_4_5">
                                                        </span>
                                                    </div>
                                                    <div class="medicine floatLeft coloradd">
                                                        <span>生<br>活<br>方<br>式<br>指<br>导<br></span>
                                                    </div>
                                                    <div class="col-xs-2 coloradd">
                                                        <span>日吸烟量</span>
                                                    </div>
                                                    <div class="col-xs-9">
											<span>
												<input type="text" placeholder="请输入" style="width: 40%;" id="cfgline_5_1_1">/
												<input type="text" placeholder="请输入" style="width: 40%;" id="cfgline_5_1_2">支
											</span>
                                                    </div>
                                                    <div class="col-xs-2 coloradd">
                                                        <span>日饮酒量</span>
                                                    </div>
                                                    <div class="col-xs-9">
											<span>
												<input type="text" placeholder="请输入" style="width: 40%;" id="cfgline_5_2_1">/
												<input type="text" placeholder="请输入" style="width: 40%;" id="cfgline_5_2_2">两
											</span>
                                                    </div>
                                                    <div class="col-xs-2 coloradd"
                                                         style="height: 80px;line-height: 80px;">
                                                        <span>运动</span>
                                                    </div>
                                                    <div class="col-xs-9" style="height: 80px;">
											<span>
												<input type="text" placeholder="请输入" style="width: 40%;" id="cfgline_5_3_1">次/周
												<input type="text" placeholder="请输入" style="width: 40%;" id="cfgline_5_3_2">次/分钟<br>
												<div style="border-bottom: 1px solid #000;"></div>
												<input type="text" placeholder="请输入" style="width: 40%;" id="cfgline_5_3_3">次/周
												<input type="text" placeholder="请输入" style="width: 40%;" id="cfgline_5_3_4">次/分钟
											</span>
                                                    </div>
                                                    <div class="col-xs-2 coloradd">
                                                        <span>主食（克/天）</span>
                                                    </div>
                                                    <div class="col-xs-9">
											<span>
												<input type="text" placeholder="请输入" style="width: 40%;" id="cfgline_5_4_1">
												<input type="text" placeholder="请输入" style="width: 40%;" id="cfgline_5_4_2">
											</span>
                                                    </div>
                                                    <div class="col-xs-2 coloradd">
                                                        <span>心理调整</span>
                                                    </div>
                                                    <div class="col-xs-9">
                                                        <span class="editable" style="color: #000;" id="cfgline_5_5"></span>
                                                    </div>
                                                    <div class="col-xs-2 coloradd">
                                                        <span>遵医行为</span>
                                                    </div>
                                                    <div class="col-xs-9">
                                                        <span class="editable" style="color: #000;" id="cfgline_5_6"></span>
                                                    </div>
                                                    <div class="coloradd floatLeft assist">
                                                        <span>辅<br>助<br>检<br>查<br></span>
                                                    </div>
                                                    <div class="col-xs-2 coloradd">
                                                        <span>空腹血糖值</span>
                                                    </div>
                                                    <div class="col-xs-9">
                                                        <span><input type="text" placeholder="请输入" id="cfgline_6_1"><em>mmol/L</em></span>
                                                    </div>
                                                    <div class="coloradd col-xs-2">
                                                        <span>其他检查*</span>
                                                    </div>
                                                    <div class="col-xs-9">
											<span>
												糖化血红蛋白<input type="text" placeholder="请输入" style="width: 30%;" id="cfgline_6_2_1">%
												检查日期：<input type="date" style="width: 30%;" id="cfgline_6_2_2">
											</span>
                                                    </div>
                                                    <div class="col-xs-3 coloradd">
                                                        <span>服药依从性</span>
                                                    </div>
                                                    <div class="col-xs-9">
                                                        <span class="editable" style="color: #000;" id="cfgline_7"></span>
                                                    </div>
                                                    <div class="col-xs-3 coloradd">
                                                        <span>药物不良反应</span>
                                                    </div>
                                                    <div class="col-xs-9">
                                                        <span class="editable" style="color: #000;" id="cfgline_8"></span>
                                                    </div>
                                                    <div class="col-xs-3 coloradd">
                                                        <span>低血糖反应</span>
                                                    </div>
                                                    <div class="col-xs-9">
                                                        <span class="editable" style="color: #000;" id="cfgline_9"></span>
                                                    </div>
                                                    <div class="col-xs-3 coloradd">
                                                        <span>此次随访分类</span>
                                                    </div>
                                                    <div class="col-xs-9">
                                                        <span class="editable" style="color: #000;" id="cfgline_10"></span>
                                                    </div>
                                                    <div class="floatLeft coloradd programme">
                                                        <span>用<br>药<br>情<br>况<br></span>
                                                    </div>
                                                    <div class="col-xs-2 coloradd">
                                                        <span>药物名称1</span>
                                                    </div>
                                                    <div class="col-xs-9">
                                                        <span><input type="text" placeholder="请输入"
                                                                     id="cfgline_11_1"></span>
                                                    </div>
                                                    <div class="col-xs-2 coloradd">
                                                        <span>用法用量</span>
                                                    </div>
                                                    <div class="col-xs-9">
											<span>
												每日<input type="text" placeholder="请输入" style="width: 10%" id="cfgline_11_2_1">次
												每次<input type="text" placeholder="请输入" style="width: 40%" id="cfgline_11_2_2">
											</span>
                                                    </div>
                                                    <div class="col-xs-2 coloradd">
                                                        <span>药物名称2</span>
                                                    </div>
                                                    <div class="col-xs-9">
                                                        <span><input type="text" placeholder="请输入" id="cfgline_11_3"></span>
                                                    </div>
                                                    <div class="col-xs-2 coloradd">
                                                        <span>用法用量</span>
                                                    </div>
                                                    <div class="col-xs-9">
											<span>
												每日<input type="text" placeholder="请输入" style="width: 10%" id="cfgline_11_4_1">次
												每次<input type="text" placeholder="请输入" style="width: 40%" id="cfgline_11_4_2">
											</span>
                                                    </div>
                                                    <div class="col-xs-2 coloradd">
                                                        <span>药物名称3</span>
                                                    </div>
                                                    <div class="col-xs-9">
                                                        <span><input type="text" placeholder="请输入"
                                                                     id="cfgline_11_5"></span>
                                                    </div>
                                                    <div class="col-xs-2 coloradd">
                                                        <span>用法用量</span>
                                                    </div>
                                                    <div class="col-xs-9">
														<span>
															每日<input type="text" placeholder="请输入" style="width: 10%" id="cfgline_11_6_1">次
															每次<input type="text" placeholder="请输入" style="width: 40%" id="cfgline_11_6_2">
														</span>
                                                    </div>
                                                    <div class="col-xs-2 coloradd"
                                                         style="height: 80px;line-height: 80px;">
                                                        <span>胰岛素</span>
                                                    </div>
                                                    <div class="col-xs-9">
														<span>
															种类：<input type="text" placeholder="请输入" style="width: 90%;" id="cfgline_11_7_1">
														</span>
                                                    </div>
                                                    <div class="col-xs-9">
											<span>
												用法用量：<input type="text" placeholder="请输入" style="width: 90%;" id="cfgline_11_7_2">
											</span>
                                                    </div>
                                                    <div class="floatLeft coloradd" style="height: 80px;line-height: 40px;">
													<span>转<br>诊</span>
													</div>
													<div class="coloradd col-xs-2">
														<span>转诊建议</span>
													</div>
													<div class="col-xs-2">
														<span>
															<input type="text" class="show_cfgline_12" placeholder="请选择" id="cfgline_12">
														</span>
													</div>
													<div class="coloradd col-xs-2">
														<span>机构及科别</span>
													</div>
													<div class="col-xs-5">
													<span><input type="text" class="input_12" placeholder="请输入" id="cfgline_12_2" readonly='readonly'></span>
													</div>
													<div class="coloradd col-xs-2">
														<span>原因</span>
													</div>
													<div class="col-xs-9">
														<span><input type="text" class="input_12" placeholder="请输入" id="cfgline_12_1" readonly='readonly'></span>
													</div>
                                                    <div class="col-xs-3 coloradd">
                                                        <span>下次随访日期</span>
                                                    </div>
                                                    <div class="col-xs-9">
                                                        <span><input type="date" id="cfgline_13"></span>
                                                    </div>
                                                    <div class="col-xs-3 coloradd">
                                                        <span>随访医生签字</span>
                                                    </div>
                                                    <div class="col-xs-9">
													<select class = "doctor_14" id="cfgline_14" style="width: 140px">
															<option value ="0">全部</option>
														<#list listStaffDto as l>
															<option value ="${l.stafId}">${l.stafName}</option>
														</#list>
													</select>
													</div>
                                                    <div class="btn btn-primary" style="top:5px;" id="save-followup-btn">
													保存
												</div>
												<div class="btn" style="top:5px;" id="back-followup-btn">取消</div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- /.col -->
                                    </div>
                                                  <!-- 覆盖层 --> 
									              <div style="display: none;" id="cover"></div> 
									                <!-- 症状多选弹窗 -->
													<div class="adjective adjective_cfgline_3"  id ="cfgline_3_dialog">
														<p>症状</p>
															<div class = "show">
															</div>
														<br/>
														<button class="adjectiveBut_cfgline_3">确定</button>
													</div>
													
													  <!-- 转诊建议 --> 
										              <div class="adjective adjective_cfgline_12" id ="cfgline_12_dialog"> 
										               <p>转诊建议</p> 
										               	<div class = "show">
														</div>
														 <br /> 
										               	<input name="有" class="inputYes_12" type="text" value="" placeholder="请填写原因" style="display: none;border: none;border-bottom:black solid 1px;width:480px;"  />
										               <br /> 
										               	<input name="有" class="inputYes_12" type="text" value="" placeholder="请填写组织及科室" style="display: none;border: none;border-bottom:black solid 1px;width:480px;" />
										               <br /> 
										               <button class="adjectiveBut_cfgline_12">确定</button> 
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

<!-- basic scripts -->

<!--[if !IE]> -->

<script src="${base}/assets/js/jquery-1.10.2.min.js"></script>

<!-- <![endif]-->

<!--[if IE]>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<![endif]-->

<!--[if !IE]> -->

<script type="text/javascript">
    window.jQuery || document.write("<script src='${base}/assets/js/jquery-2.0.3.min.js'>" + "<" + "/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='${base}/assets/js/jquery-1.10.2.min.js'>" + "<" + "/script>");
</script>
<![endif]-->

<script type="text/javascript">
    if ("ontouchend" in document) document.write("<script src='${base}/assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
</script>
<script src="${base}/assets/js/bootstrap.min.js"></script>
<script src="${base}/assets/js/typeahead-bs2.min.js"></script>

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
<script src="${base}/assets/js/excanvas.min.js"></script>
<![endif]-->

<script src="${base}/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="${base}/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="${base}/assets/js/jquery.gritter.min.js"></script>
<script src="${base}/assets/js/bootbox.min.js"></script>
<script src="${base}/assets/js/jquery.slimscroll.min.js"></script>
<script src="${base}/assets/js/jquery.easy-pie-chart.min.js"></script>
<script src="${base}/assets/js/jquery.hotkeys.min.js"></script>
<script src="${base}/assets/js/bootstrap-wysiwyg.min.js"></script>
<script src="${base}/assets/js/select2.min.js"></script>
<script src="${base}/assets/js/date-time/bootstrap-datepicker.min.js"></script>
<script src="${base}/assets/js/fuelux/fuelux.spinner.min.js"></script>
<script src="${base}/assets/js/x-editable/bootstrap-editable.min.js"></script>
<script src="${base}/assets/js/x-editable/ace-editable.min.js"></script>
<script src="${base}/assets/js/jquery.maskedinput.min.js"></script>
<script src="${base}/js/followup_page.js"></script>

<!-- ace scripts -->

<script src="${base}/assets/js/ace-elements.min.js"></script>
<script src="${base}/assets/js/ace.min.js"></script>

<!-- inline scripts related to this page -->
<script type="text/javascript">
    jQuery(function ($) {
            $.fn.editable.defaults.mode = 'inline';
            $.fn.editableform.loading = "<div class='editableform-loading'><i class='light-blue icon-2x icon-spinner icon-spin'></i></div>";
            $.fn.editableform.buttons = '<button type="submit" class="btn btn-info editable-submit"><i class="icon-ok icon-white"></i></button>' +
                    '<button type="button" class="btn editable-cancel"><i class="icon-remove"></i></button>';

            Followup.init();
       });
</script>
</body>
</html>
