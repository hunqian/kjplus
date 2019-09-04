<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<base id="base" href="${base}">
		<title>严重精神障碍患者个人信息补充表</title>
		<meta name="keywords" content="严重精神障碍患者个人信息补充表" />
		<meta name="description" content="严重精神障碍患者个人信息补充表" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<!-- basic styles -->

		<link href="${base}/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="${base}/assets/css/font-awesome.min.css" />

		<!--[if IE 7]>
		  <link rel="stylesheet" href="${base}/assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

		<!-- page specific plugin styles -->

		<link rel="stylesheet" href="${base}/assets/css/jquery-ui-1.10.3.custom.min.css" />
		<link rel="stylesheet" href="${base}/assets/css/jquery.gritter.css" />

		<!-- fonts -->
		
		<link rel="stylesheet" href="${base}/assets/css/jquery-ui-1.10.3.custom.min.css" />
		<link rel="stylesheet" href="${base}/assets/css/jquery.gritter.css" />
		<link rel="stylesheet" href="${base}/assets/css/select2.css" />
		<link rel="stylesheet" href="${base}/assets/css/bootstrap-editable.css" />

		<!-- ace styles -->

		<link rel="stylesheet" href="${base}/assets/css/ace.min.css" />
		<link rel="stylesheet" href="${base}/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${base}/assets/css/ace-skins.min.css" />

		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="${base}/assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<style>
			b{
				color: black;
			}
			#editfollowuppage{
			position: relative;
			top: 20;
			left: 20;
			z-index: 16;
			opacity: 1.0;
			}
			.center [class*="col-"] {
				position:relative;
				text-overflow:ellipsis;
				height: 40px;
				line-height: 40px;
				border: 1px solid #ccc;
			}
			.center [class*="col-"]  span{
			  position:relative;
			  z-index:2;
			  display: inline-block;
			  text-overflow: ellipsis;
			  color: #336199;
			  width: 100%;
			  height: 100%;
			}
			input{
				border: none !important;
				padding:0 !important;
				width: 100%;
				color: black !important;
			}
			.floatLeft{
				float: left;
				border: 1px solid #DDD;
				width: 8.336%;
				color: #336199;
			}
			.nav-tabs{border-bottom: none !important;}
			.medicine{
			    height: 280px;
			    line-height: 45px;
			}
			.programme{
				height: 320px;
				line-height: 80px;
			}
			.hospital{
				height: 200px;
				line-height: 40px;
			}
			.treat{
				height: 120px;
				line-height: 120px;
			}
			em{
				position: absolute;
				top: 0;
				left: 100px;
				color: black;
			}
			strong{
				position: absolute;
				left: 70px;
				top: 125px;
				color: #000;
			}
			.black{
				color: #000 !important;
			}
			.addheight{
				height: 160px !important;
			}
			#cover{
				position: fixed;
				top: 0;
				left: 0;
				z-index: 14;
				width: 100%;
				height: 100%;
				background: #ccc none repeat scroll 0% 0%;
				opacity: 0.5;
			}
			.symptom, .dangerous,.examine{
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
			.symptom input ,.dangerous input,.examine input{
				width: auto !important;
			}
			.coloradd{
				background-color: #edf3f4;
			}
			i{
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
		<![endif]-->
	

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
						<li class="active">随访访视</li>
						<li class="active"><#if prsnName??>${prsnName!''}</#if></li>
					</ul>
					<!-- .breadcrumb -->

					
				</div>

					<div class="page-content">
						<div class="page-header">
							<h1>
								随访详情
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<div class="row">
									<div class="col-sm-9">
										<div class="tabbable">
											<div class="tab-content" style="width: 1000px;">
												<div id="followup" class="tab-pane in active">
													<div class="center">
														<div class="row">
														<div class="col-xs-12">
                                                        	<span><font size="5" color="blue">严重精神障碍患者信息</font>
                                                        	</span>
                                                    	</div>
                                                    	</div>
                                                    	<input type="hidden" id="followupid" value="<#if followupid??>${followupid!''}</#if>">
										              <input type="hidden" id="tableCode" value="<#if tableCode??>${tableCode!''}</#if>">
										              <input type="hidden" id="prsnId" value="<#if prsnId??>${prsnId!''}</#if>">
													  <input type="hidden" id="activepage" value="<#if activepage??>${activepage!''}</#if>">
                                                    	<div class="row text" style="text-align: initial;">
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
					                   <span><input class="col-xs-12" type="text" placeholder="系统自动生成" readonly="readonly" style="border: none;height: 22px;" value ="<#if followCode??>${followCode!''}</#if>"/></span> 
					                  </div> 
                                        <div class="coloradd col-xs-2">
											<span>监护人姓名</span>
										</div>
										<div class="col-xs-4">
											<span><input type="text" placeholder="请输入" id="cfgline_1_1"></span>
										</div>
										<div class="coloradd col-xs-2">
											<span>与患者关系</span>
										</div>
										<div class="col-xs-4">
											<span><input type="text" placeholder="请输入" id="cfgline_1_2"></span>
										</div>
										<div class="coloradd col-xs-2">
											<span>监护人住址</span>
										</div>
										<div class="col-xs-4">
											<span><input type="text" placeholder="请输入" id="cfgline_2_1"></span>
										</div>
										<div class="coloradd col-xs-2">
											<span>监护人电话</span>
										</div>
										<div class="col-xs-4">
											<span><input type="text" placeholder="请输入" id="cfgline_2_2"></span>
										</div>
										<div class="col-xs-4 coloradd">
											<span>辖区村（居）委会联系人、电话</span>
										</div>
										<div class="col-xs-8">
											<span><input type="text" placeholder="请输入" id="cfgline_3"></span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>户别</span>
										</div>
										<div class="col-xs-4">
											<span class="editable" style="color: #000;" id="cfgline_4"></span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>就业情况</span>
										</div>
										<div class="col-xs-4">
											<span class="editable" style="color: #000;" id="cfgline_5"></span>
										</div>
										<div class="col-xs-2 coloradd" style="height: 80px;line-height: 80px;">
											<span>知情同意</span>
										</div>
										<div class="col-xs-10">
											<span class="editable" style="color: #000;" id="cfgline_6_1"></span>
										</div>
										<div class="col-xs-10">
											<span>
												<i>签字：</i><input type="text" placeholder="请签字" style="width: 35%;" id="cfgline_6_2">
												<i>签字时间</i><input type="date" style="width: 49%;" id="cfgline_6_3_2">
											</span>
										</div>
										<div class="coloradd col-xs-2">
											<span>初次发病时间</span>
										</div>
										<div class="col-xs-10">
											<span><input type="date" id="cfgline_7"></span>	
										</div>
										<div class="coloradd col-xs-2">
											<span>既往主要症状</span>
										</div>
										<div class="col-xs-10">
											<span><input type="text" placeholder="请选择" id="cfgline_8"></span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>既往关锁情况</span>
										</div>
										<div class="col-xs-10">
											<span class="editable" style="color: #000;" id="cfgline_9"></span>
										</div>
										<div class="coloradd floatLeft treat">
											<span>既往治疗情况</span>
										</div>
										<div class="col-xs-1 coloradd" style="height: 80px;line-height: 80px;">
											<span>门诊</span>
										</div>
										<div class="col-xs-10">
											<span class="editable" style="color: #000;" id="cfgline_10_1_1"></span>
										</div>
										<div class="col-xs-10">
											<span><i>首次抗精神病药治疗时间</i><input type="date" style="width: 75%;"  id="cfgline_10_1_2"></span>
										</div>
										<div class="coloradd col-xs-1">
											<span>住院</span>
										</div>
										<div class="col-xs-10">
											<span>
												<i>曾住精神专科医院/综合医院精神专科</i><input type="text" placeholder="请输入" style="width: 30%;" id="cfgline_10_2">次
											</span>
										</div>
										<div class="coloradd col-xs-2">
											<span>目前诊断情况</span>
										</div>
										<div class="col-xs-10">
											<span>
												<i>诊断</i><input type="text" placeholder="请输入" style="width: 25%;"  id="cfgline_11_1">
												<i>确诊医院</i><input type="text" placeholder="请输入" style="width: 25%;"   id="cfgline_11_2">
												<i>确诊日期</i><input type="date" style="width: 25%;"  id="cfgline_11_3">
											</span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>最近一次治疗效果</span>
										</div>
										<div class="col-xs-10">
											<span class="editable" style="color: #000;" id="cfgline_12"></span>
										</div>
										<div class="coloradd col-xs-2" style="height:120px;line-height:120px;">
											<span>危险行为</span>
										</div>
										<div class="col-xs-5 textInitial">
											<span id="cfgline_3_5">
												轻度滋事：<input type="text" placeholder="请填写" style="width:74%;" id="cfgline_13_1">次
											</span>	
										</div>
										<div class="col-xs-5 textInitial">
											<span id="cfgline_3_5">
												肇事：<input type="text" placeholder="请填写" style="width:80%;" id="cfgline_13_2">次
											</span>	
										</div>
										<div class="col-xs-5 textInitial">
											<span id="cfgline_3_5">
												肇祸：<input type="text" placeholder="请填写" style="width:80%;" id="cfgline_13_3">次
											</span>	
										</div>
										<div class="col-xs-5 textInitial">
											<span id="cfgline_3_5">
												其他危险行为：<input type="text" placeholder="请填写" style="width:67%;" id="cfgline_13_4">次
											</span>	
										</div>
										<div class="col-xs-5 textInitial">
											<span id="cfgline_3_5">
												自伤：<input type="text" placeholder="请填写" style="width:80%;" id="cfgline_13_5">次
											</span>	
										</div>
										<div class="col-xs-5 textInitial">
											<span id="cfgline_3_5">
												自杀未遂：<input type="text" placeholder="请填写" style="width:74%;" id="cfgline_13_6">次
											</span>	
										</div>
										<div class="coloradd col-xs-2">
											<span>经济状况</span>	
										</div>
										<div class="col-xs-10">
											<span class="editable" style="color: #000;" id="cfgline_14"></span>
										</div>
										<div class="coloradd col-xs-3">
											<span>专科医生的意见（如果有请记录）</span>	
										</div>
										<div class="col-xs-9">
											<span><input type="text" placeholder="请输入" id="cfgline_15"></span>
										</div>
										<div class="coloradd col-xs-2">
											<span>填表日期</span>	
										</div>
										<div class="col-xs-4">
											<span><input type="date" id="cfgline_16_1"></span>
										</div>
										<div class="coloradd col-xs-2">
											<span>医生签字</span>	
										</div>
										<div class="col-xs-4">
											<select class="doctor_16_2" id="cfgline_16_2" style="width: 140px">
													<option value ="0">全部</option>
												<#list listStaffDto as l>
													<option value ="${l.stafId}">${l.stafName}</option>
												</#list>
											</select>
											</div>
										
										<div class="btn btn-primary" style="top:5px;left:43.5%;" id="save-followup-btn">
												保存
											</div>
										<div class="btn" style="top:5px;left:43.5%;" id="back-followup-btn">取消</div>
										
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>	
</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->

		<script src="${base}/assets/js/jquery-1.10.2.min.js"></script>

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

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='${base}/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
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
			
			jQuery(function($) {
				$.fn.editable.defaults.mode = 'inline';
				$.fn.editableform.loading = "<div class='editableform-loading'><i class='light-blue icon-2x icon-spinner icon-spin'></i></div>";
			    $.fn.editableform.buttons = '<button type="submit" class="btn btn-info editable-submit"><i class="icon-ok icon-white"></i></button>'+
			                                '<button type="button" class="btn editable-cancel"><i class="icon-remove"></i></button>';    
				
     			
     			Followup.init();
			});
		</script>
	</body>
</html>
