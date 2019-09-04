<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<base id="base" href="${base}">
		<title>肺结核患者随访服务记录表</title>
		<meta name="keywords" content="肺结核患者随访服务记录表" />
		<meta name="description" content="肺结核患者随访服务记录表" />
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
			.center [class*="col-"] {
				position:relative;
				text-overflow:ellipsis;
				height: 40px;
				line-height: 40px;
				border: 1px solid #ccc;
			}
			#editfollowuppage{
			position: relative;
			top: 20;
			left: 20;
			z-index: 16;
			opacity: 1.0;
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

			.nav-tabs{border-bottom: none !important;}
			.floatLeft{
				float: left;
				border: 1px solid #DDD;
				width: 8.333%;
				color: #336199;
			}
			.medicine{
			    height: 80px;
			    line-height: 80px;
			}
			.adjective input{
			width:auto;
		}
		.adjective{
			width: 66rem;
			height: 27.75rem;
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
			.programme{
				height: 120px;
				line-height: 120px;
			}
			.hospital{
				height: 160px;
				line-height: 160px;
			}
			.assist{
				height: 80px;
				line-height: 20px;
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
			.editable-container{
				width: 40% !important;
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
			try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script> 
   <div class="main-container-inner"> 
    <a class="menu-toggler" id="menu-toggler" href="#"> <span class="menu-text"></span> </a> 
    <div class="sidebar" id="sidebar">
      <#include 'menu_ace.ftl'/>
    </div> 
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
       <h1> 肺结核患者随访服务记录表 </h1> 
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
                	<span><font size="5" color="blue">肺结核患者随访信息</font>
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
					                   <span><input class="col-xs-12" type="text" placeholder="系统自动生成" readonly="readonly" style="border: none;height: 22px;" value ="<#if followCode??>${followCode!''}</#if>"/></span> 
					                  </div> 
										<div class="coloradd col-xs-3">
											<span>随访时间</span>
										</div>
										<div class="col-xs-9">
											<span><input type="date" id="cfgline_1"></span>
										</div>
										<div class="coloradd col-xs-3">
											<span>治疗月序</span>
										</div>
										<div class="col-xs-9">
											<span>
												第<input type="text" placeholder="请输入" style="width: 10%;" id="cfgline_2">月
											</span>
										</div>
										<div class="coloradd col-xs-3">
											<span>督导人员</span>
										</div>
										<div class="col-xs-9">
											<span class="editable" style="color: #000;" id="cfgline_3"></span>
										</div>
										<div class="coloradd col-xs-3">
											<span>随访方式</span>
										</div>
										<div class="col-xs-9">
											<span class="editable" style="color: #000;" id="cfgline_4"></span>
										</div>
										<div class="coloradd col-xs-3" style="height: 80px;line-height: 80px;">
											<span>症状及体征</span>
										</div>
										<div class="col-xs-9">
											<span><input type="text" class=" show_cfgline_5" placeholder="请选择" id="cfgline_5"></span>
										</div>
										<div class="col-xs-9">
											<span>
												<i>其他：</i><input type="text" placeholder="请输入" style="width: 90%;">
											</span>
										</div>
										<div class="floatLeft coloradd medicine">
											<span>生活方式指导</span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>吸烟</span>	
										</div>
										<div class="col-xs-9">
											<span>
												<input type="text" placeholder="请输入" style="width: 10%;" id="cfgline_6_1_1">/
												<input type="text" placeholder="请输入" style="width: 10%;" id="cfgline_6_1_2">
												支/天
											</span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>饮酒</span>	
										</div>
										<div class="col-xs-9">
											<span>
												<input type="text" placeholder="请输入" style="width: 10%;" id="cfgline_6_2_1">/
												<input type="text" placeholder="请输入" style="width: 10%;" id="cfgline_6_2_2">
												两/天
											</span>
										</div>
										<div class="floatLeft coloradd hospital">
											<span>用药</span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>化疗方案</span>
										</div>
										<div class="col-xs-9">
											<span><input type="text" placeholder="请输入" id="cfgline_7_1"></span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>用法</span>
										</div>
										<div class="col-xs-9">
											<span class="editable" style="color: #000;" id="cfgline_7_2"></span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>药品剂型</span>
										</div>
										<div class="col-xs-9">
											<span><input type="text" class="show_cfgline_7_3" placeholder="请选择" id="cfgline_7_3"></span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>漏服药次数</span>
										</div>
										<div class="col-xs-9">
											<span><input type="text" placeholder="请输入" id="cfgline_7_4"><em>次</em></span>
										</div>
										<div class="col-xs-3 coloradd">
											<span>药物不良反应</span>
										</div>
										<div class="col-xs-3">
											<span><input type="text" class="show_cfgline_8_1" placeholder="请选择" id="cfgline_8_1"></span>
										</div>
										<div class="col-xs-6">
											<span><input type="text" class="input_8_1" placeholder="请输入不良反应" id="cfgline_8_2" readonly='readonly'></span>
										</div>
										<div class="col-xs-3 coloradd">
											<span>并发症或合并症</span>
										</div>
										<div class="col-xs-3">
											<span><input type="text" class="show_cfgline_9_1" placeholder="请选择" id="cfgline_9_1"></span>
										</div>
										<div class="col-xs-6">
											<span><input type="text" class="input_9_1" placeholder="请输入并发症或合并症" id="cfgline_9_2" readonly='readonly'></span>
										</div>
										<div class="programme floatLeft coloradd">
											<span>转诊</span>
										</div>
										<div class="coloradd col-xs-2">
														<span>转诊建议</span>
													</div>
													<div class="col-xs-2">
														<span>
															<input type="text" class="show_cfgline_10" placeholder="请选择" id="cfgline_10">
														</span>
													</div>
													<div class="coloradd col-xs-2">
														<span>机构及科别</span>
													</div>
													<div class="col-xs-5">
													<span><input type="text" class="input_10" placeholder="请输入" id="cfgline_10_1" readonly='readonly'></span>
													</div>
													<div class="coloradd col-xs-2">
														<span>原因</span>
													</div>
													<div class="col-xs-9">
														<span><input type="text" class="input_10" placeholder="请输入" id="cfgline_10_2" readonly='readonly'></span>
													</div>
													<div class="col-xs-2 coloradd">
														<span>2周内随访，随访结果</span>
													</div>
													<div class="col-xs-9">
														<span><input type="text" placeholder="请输入" readonly='readonly' id="cfgline_10_3"></span>
													</div>
										<div class="col-xs-3 coloradd">
											<span>处理意见</span>
										</div>
										<div class="col-xs-9">
											<span><input type="text" placeholder="请输入" id="cfgline_12"></span>
										</div>
										<div class="col-xs-3 coloradd">
											<span>下次随访时间</span>
										</div>
										<div class="col-xs-9">
											<span><input type="date" id="cfgline_13"></span>
										</div>
										<div class="col-xs-3 coloradd">
											<span>随访医生签字</span>
										</div>
										<div class="col-xs-9">
											<select id="cfgline_14" class="doctor_14" class="doctor_14" style="width: 140px">
													<option value ="0">全部</option>
												<#list listStaffDto as l>
													<option value ="${l.stafId}">${l.stafName}</option>
												</#list>
											</select>
											</div>
										<div class="col-xs-3 coloradd">
											<span>停止治疗及原因</span>
										</div>
										<div class="col-xs-9">
											<span>
												<i>1 出现停止治疗时间</i> <input type="date" style="width: 20%;" id="cfgline_15_1">
												<i>2 停止治疗原因： </i><span class="editable" style="width: 40%;color: #000;" id="cfgline_15_2"></span>
											</span>
										</div>
										<div class="col-xs-3 coloradd" style="height: 120px;line-height: 120px;">
											<span>全程管理情况</span>
										</div>
										<div class="col-xs-9" style="height: 80px;">
											<span>
												<i>应访视患者</i><input type="text" placeholder="请输入" style="width: 8%;" id="cfgline_16_1_1"><i>次，</i>
												<i>实际访视</i><input type="text" placeholder="请输入" style="width: 8%;" id="cfgline_16_1_2"><i>次；</i>
												<br>
												<i>患者在疗程中，应服药</i><input type="text" placeholder="请输入" style="width: 8%;" id="cfgline_16_2_1"><i>次</i>，
												<i>实际服药</i><input type="text" placeholder="请输入" style="width: 8%;" id="cfgline_16_2"_2><i>次</i>
												<i>服药率</i><input type="text" placeholder="请输入" style="width: 8%;" id="cfgline_16_2_3"><i>%</i>
											</span>
										</div>
										<div class="col-xs-9">
											<select id="cfgline_16_3" style="width: 140px">
													<option value ="0">全部</option>
												<#list listStaffDto as l>
													<option value ="${l.stafId}">${l.stafName}</option>
												</#list>
											</select>
											</div>>
										
										<div class="btn btn-primary" style="top:5px;" id="save-followup-btn">
												保存
											</div>
										<div class="btn" style="top:5px;" id="back-followup-btn">取消</div>
													
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
     </div> 
   
   
   												<!-- 覆盖层 --> 
									              <div style="display: none;" id="cover"></div> 
									                <!-- 症状多选弹窗 -->
													<div class="adjective adjective_cfgline_5"  id ="cfgline_5_dialog">
														<p>症状</p>
															<div class = "show">
															</div>
														<br/>
														<button class="adjectiveBut_cfgline_5">确定</button>
													</div>
													
													  <!-- 转诊建议 --> 
										              <div class="adjective adjective_cfgline_10" id ="cfgline_10_dialog"> 
										               <p>转诊建议</p> 
										               	<div class = "show">
														</div>
														 <br /> 
										               	<input name="有" class="inputYes_10" type="text" value="" placeholder="请输入" style="display: none;border: none;border-bottom:black solid 1px;width:480px;"  />
										               <br /> 
										               	<input name="有" class="inputYes_10" type="text" value="" placeholder="请输入" style="display: none;border: none;border-bottom:black solid 1px;width:480px;" />
										               <br /> 
										               	<input name="有" class="inputYes_10" type="text" value="" placeholder="请输入" style="display: none;border: none;border-bottom:black solid 1px;width:480px;" />
										               <br /> 
										               <button class="adjectiveBut_cfgline_10">确定</button> 
										              </div> 
										              
										              <!-- 药品剂型多选弹窗 -->
													<div class="adjective adjective_cfgline_7_3"  id ="cfgline_7_3_dialog">
														<p>药品剂型</p>
															<div class = "show">
															</div>
														<br/>
														<button class="adjectiveBut_cfgline_7_3">确定</button>
													</div>
													
													  <!-- 药物不良反应弹窗 -->
													<div class="adjective adjective_cfgline_8_1"  id ="cfgline_8_1_dialog">
														<p>药物不良反应</p>
															<div class = "show">
															</div>
														<br/>
														<input name="有" class="inputYes_8_1" type="text" value="" placeholder="请输入" style="display: none;border: none;border-bottom:black solid 1px;width:480px;" />
														<button class="adjectiveBut_cfgline_8_1">确定</button>
													</div>
													
													  <!-- 合并症或并发症弹窗 -->
													<div class="adjective adjective_cfgline_9_1"  id ="cfgline_9_1_dialog">
														<p>合并症或并发症</p>
															<div class = "show">
															</div>
														<br/>
														<input name="有" class="inputYes_9_1" type="text" value="" placeholder="请输入" style="display: none;border: none;border-bottom:black solid 1px;width:480px;" />
														<button class="adjectiveBut_cfgline_9_1">确定</button>
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
