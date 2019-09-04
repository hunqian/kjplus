<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <base id="base" href="${base}"/>
    <title>产后访视记录表</title>
    <meta name="keywords" content="产后访视记录表"/>
    <meta name="description" content="产后访视记录表"/>
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
		.adjective input{
			width:auto;
		}
		#editfollowuppage{
			position: relative;
			top: 20;
			left: 20;
			z-index: 16;
			opacity: 1.0;
		}
		.adjective{
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
        .center {
            text-align: center;
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
            white-space: nowrap;
            color: #336199;
            width: 100%;
        }

        input {
            border: none !important;
            padding: 0 !important;
            width: 100%;
            color: black !important;
        }
.nav-tabs{border-bottom: none !important;}
      
        .floatLeft {
            float: left;
            border: 1px solid #DDD;
            width: 16.6666%;
            color: #336199;
        }

        .medicine {
            height: 560px;
            line-height: 170px;
        }

        .programme {
            height: 160px;
            padding-top: 60px;
        }

        .hospital {
            height: 240px;
            line-height: 40px;
        }

        em {
            position: absolute;
            left: 100px;
            color: black;
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
</head>
    <!-- ace settings handler -->
    <script src="${base}/assets/js/ace-extra.min.js"></script>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="${base}/assets/js/html5shiv.js"></script>
    <script src="${base}/assets/js/respond.min.js"></script>


    <body>
    <![endif]-->    <#include 'nav_ace.ftl'/>
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
                        <h1> 产后访视记录表 </h1>
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
								              <span><font size="5" color="blue">产后访视记录</font> 
								              </span>
                                                </div>
                                                <div class="center">
                                                    <div class="row text">
                                                     <div class="col-xs-2 coloradd">
                                                            <span>姓名</span>
                                                        </div>
                                                        <div class="col-xs-4">
                                                            <span><input type="text" placeholder="请输入" value ="<#if prsnName??>${prsnName!''}</#if>"/></span>
                                                        </div>
                                                        <div class="col-xs-2 coloradd">
                                                            <span>编号</span>
                                                        </div>
                                                        <div class="col-xs-4">
                                                            <span><input type="text" readonly="readonly" placeholder="系统自动生成" value ="<#if followCode??>${followCode!''}</#if>"/></span>
                                                        </div>
                                                        <div class="col-xs-2 coloradd">
                                                            <span>随访日期</span>
                                                        </div>
                                                        <div class="col-xs-10">
                                                            <span><input type="date" id="cfgline_1"/></span>
                                                        </div>
                                                        <div class="col-xs-2 coloradd">
                                                            <span>分娩日期</span>
                                                        </div>
                                                        <div class="col-xs-4">
                                                            <span><input type="date" id="cfgline_2_1"/></span>
                                                        </div>
                                                        <div class="col-xs-2 coloradd">
                                                            <span>出院日期</span>
                                                        </div>
                                                        <div class="col-xs-4">
                                                            <span><input type="date" id="cfgline_2_2"/></span>
                                                        </div>
                                                        <div class="col-xs-2 coloradd">
                                                            <span>体温（&deg;C）</span>
                                                        </div>
                                                        <div class="col-xs-10">
                                                            <span><input type="text" placeholder="请输入" id="cfgline_3"/></span>
                                                        </div>
                                                        <div class="col-xs-2 coloradd">
                                                            <span>一般健康情况</span>
                                                        </div>
                                                        <div class="col-xs-10">
                                                            <span><input type="text" placeholder="请输入" id="cfgline_4"/></span>
                                                        </div>
                                                        <div class="col-xs-2 coloradd">
                                                            <span>一般心理状况</span>
                                                        </div>
                                                        <div class="col-xs-10">
                                                            <span><input type="text" placeholder="请输入" id="cfgline_5"/></span>
                                                        </div>
                                                        <div class="col-xs-2 coloradd">
                                                            <span>血压（mmHg）</span>
                                                        </div>
                                                        <div class="col-xs-10">
                                                            <span><input type="text" placeholder="请输入" id="cfgline_6"/></span>
                                                        </div>
                                                        <div class="col-xs-2 coloradd">
                                                            <span>乳房</span>
                                                        </div>
                                                        <div class="col-xs-10">
                                                            <span class="editable" style="color: #000;" id="cfgline_7"></span>
                                                        </div>
                                                        <div class="col-xs-2 coloradd">
                                                            <span>恶露</span>
                                                        </div>
                                                        <div class="col-xs-10">
                                                            <span class="editable" style="color: #000;" id="cfgline_8"></span>
                                                        </div>
                                                        <div class="col-xs-2 coloradd">
                                                            <span>子宫</span>
                                                        </div>
                                                        <div class="col-xs-10">
                                                            <span class="editable" style="color: #000;" id="cfgline_9"></span>
                                                        </div>
                                                        <div class="col-xs-2 coloradd">
                                                            <span>伤口</span>
                                                        </div>
                                                        <div class="col-xs-10">
                                                            <span class="editable" style="color: #000;" id="cfgline_10"></span>
                                                        </div>
                                                        <div class="col-xs-2 coloradd">
                                                            <span>其他</span>
                                                        </div>
                                                        <div class="col-xs-10">
                                                            <span><input type="text" placeholder="请输入" id="cfgline_11"/></span>
                                                        </div>
                                                        <div class="col-xs-2 coloradd">
                                                            <span>分类</span>
                                                        </div>
                                                        <div class="col-xs-10">
                                                            <span class="editable" style="color: #000;" id="cfgline_12"></span>
                                                        </div>
                                                        <div class="col-xs-2 coloradd">
                                                            <span>指导</span>
                                                        </div>
                                                        <div class="col-xs-10">
                                                            <span><input type="text" class="show_cfgline_13" placeholder="请选择" id="cfgline_13"/></span>
                                                        </div>
                                                        <div class="floatLeft coloradd" style="height: 80px;line-height: 40px;">
															<span>转<br>诊</span>
														</div>
                                                        <div class="coloradd col-xs-2">
															<span>转诊建议</span>
														</div>
														<div class="col-xs-2">
															<span>
																<input type="text" class="show_cfgline_14_1" placeholder="请选择" id="cfgline_14_1">
															</span>
														</div>
														<div class="coloradd col-xs-2">
															<span>机构及科别</span>
														</div>
														<div class="col-xs-4">
														<span><input type="text" class="input_14_1" placeholder="请输入" id="cfgline_14_3" readonly='readonly'></span>
														</div>
														<div class="coloradd col-xs-2">
															<span>原因</span>
														</div>
														<div class="col-xs-8">
															<span><input type="text" class="input_14_1" placeholder="请输入" id="cfgline_14_2" readonly='readonly'></span>
														</div>
                                                        <div class="col-xs-2 coloradd">
                                                            <span>下次随访日期</span>
                                                        </div>
                                                        <div class="col-xs-10">
                                                            <span><input type="date" id="cfgline_15"/></span>
                                                        </div>
                                                        <div class="col-xs-2 coloradd">
                                                            <span>随访医生签字</span>
                                                        </div>
                                                        <div class="col-xs-10">
															<select class="doctor_16" id="cfgline_16" style="width: 140px">
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
                                        </div>
                                        <div style="display: none;" id="cover"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    							
    							<!-- 覆盖层 --> 
             					 <div style="display: none;" id="cover"></div> 
   	 							<!--健康指导弹出框-->
								<div class="adjective  adjective_cfgline_13" id ="cfgline_13_dialog" >
									<p>健康指导选择</p>
									<div class = "show" >
									</div>
									<button class="adjectiveBut_cfgline_13">确定</button>
								</div>
								
								 <!-- 转诊建议 --> 
					              <div class="adjective adjective_cfgline_14_1" id ="cfgline_14_1_dialog"> 
					               <p>转诊建议</p> 
					               	<div class = "show" >
									</div>
									 <br /> 
					               	<input name="有" class="inputYes_14_1" type="text" value="" placeholder="请输入" style="display: none;border: none;border-bottom:black solid 1px;width:480px;" id="cfgline_14_2"  />
					               <br /> 
					               	<input name="有" class="inputYes_14_1" type="text" value="" placeholder="请输入" style="display: none;border: none;border-bottom:black solid 1px;width:480px;" id="cfgline_14_3"  />
					               <br /> 
					               <button class="adjectiveBut_cfgline_14_1">确定</button> 
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
