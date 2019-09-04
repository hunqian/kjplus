<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<base id="base" href="${base}">
		<title>第二次产前随访记录表</title>
		<meta name="keywords" content="第二次产前随访记录表" />
		<meta name="description" content="第二次产前随访记录表" />
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
			.input-medium{
				width: 82px !important;
			}
			.spinner-preview {
				width:100px;
				height:100px;
				text-align:center;
				margin-top:60px;
			}
			.floatLeft{
				float: left;
				border: 1px solid #DDD;
				width: 8.3%;
				color: #336199;
			}
			#editfollowuppage{
			position: relative;
			top: 20;
			left: 20;
			z-index: 16;
			opacity: 1.0;
			}
			.dropdown-preview {
				margin:0 5px;
				display:inline-block;
			}
			.nav-tabs{border-bottom: none !important;}
			.dropdown-preview  > .dropdown-menu {
				display: block;
				position: static;
				margin-bottom: 5px;
			}
			.center {
				text-align:center;
			}
			.center [class*="col-"] {
				position:relative;
				text-overflow:ellipsis;
				height: 40px;
				line-height: 40px;
				border: 1px solid #ccc;
			}
			.row{
				margin-left: 0 !important;
				margin-right: 0 !important;
			}
			.center [class*="col-"]  span{
			  position:relative;
			  z-index:2;
				
			  display: inline-block;
			  overflow: hidden;
			  text-overflow: ellipsis;
			  white-space: nowrap;
			  color: #336199;
			  width: 100%;
			}
			input{
				border: none !important;
				padding:0 !important;
				width: 100%;
				color: black !important;
			}
			.show input{
				width:auto;
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
			.adjective, .expose, .disability, .family, .complexionUp, .familyMother, .familyBrothers, .familyChildren, .propose, .examine{
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
			.ace-settings-container{
				top: 5px !important;
			}
			.selectpicker{
				position: relative;
				left: 880px;
			}
			textarea{
				resize:none;
			}
			.send{
				background-color: #428bca!important;
	    		border-color: #428bca;
			}
			.send:hover{
				background-color: #1b6aaa!important;
	    		border-color: #428bca;
			}
			.coloradd{
				background-color: #edf3f4;
			}
			.ad{
				width: 8.3%;
				height:160px;
				float: left;
				border:1px solid #DDD;
				line-height: 35px;
				padding-left: 6px;
				color: #336199;
			}
			.examine input ,.disability input{
				width: auto !important;
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
       <h1>第二次产前随访服务记录表  </h1> 
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
                	<span><font size="5" color="blue">第二次产前随访服务信息</font>
                	</span>
            	</div>
                <div class="center"> 
                 <div class="row text"> 
									<div class="row">
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
									</div>
									<div class="row">
										<div class="col-xs-3 coloradd"><span>项目</span></div>
										<div class="col-xs-9 coloradd"><span>第2次</span></div>
									</div>
									<div class="row">
										<div class="col-xs-3 coloradd"><span>(随访/督促)日期</span></div>
										<div class="col-xs-9">
											<span><input type="date" id="cfgline_1"></span>
										</div>
									</div>
									<div class="row">
										<div class="col-xs-3 coloradd"><span>孕周</span></div>
										<div class="col-xs-9">
											<span><input type="text" placeholder="请输入" id="cfgline_2"></span>
										</div>
									</div>
									<div class="row">
										<div class="col-xs-3 coloradd"><span>主诉</span></div>
										<div class="col-xs-9">
											<span><input type="text" placeholder="请输入" id="cfgline_3"></span>
										</div>
									</div>
									<div class="row">
										<div class="col-xs-3 coloradd"><span>体重（kg）</span></div>
										<div class="col-xs-9">
											<span><input type="text" placeholder="请输入" id="cfgline_4"></span>
										</div>
									</div>
									<div class="row">
										<div class="ad coloradd">
											<span>产 <br/>科 <br/>检 <br/>查 <br/></span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>宫底高度（cm）</span>
										</div>
										<div class="col-xs-9">
											<span><input type="text" placeholder="请输入" id="cfgline_5_1"></span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>腹围（cm）</span>
										</div>
										<div class="col-xs-9">
											<span><input type="text" placeholder="请输入" id="cfgline_5_2"></span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>胎位</span>
										</div>
										<div class="col-xs-9">
											<span><input type="text" placeholder="请输入" id="cfgline_5_3"></span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>胎心率（次/分钟）</span>
										</div>
										<div class="col-xs-9">
											<span><input type="text" placeholder="请输入" id="cfgline_5_4"></span>
										</div>
									</div>
									<div class="row">
										<div class="col-xs-3 coloradd"><span>血压（mmHg）</span></div>
										<div class="col-xs-9">
											<span>
												<input type="text" placeholder="请输入" style="width: 40%" id="cfgline_6_1"><i>/</i>
												<input type="text" placeholder="请输入" style="width: 40%" id="cfgline_6_2">
											</span>
										</div>
										<div class="col-xs-3 coloradd"><span>血红蛋白（g/L）</span></div>
										<div class="col-xs-9">
											<span><input type="text" placeholder="请输入" id="cfgline_7"></span>
										</div>
										<div class="col-xs-3 coloradd"><span>尿蛋白</span></div>
										<div class="col-xs-9">
											<span><input type="text" placeholder="请输入" id="cfgline_8"></span>
										</div>
										<div class="col-xs-3 coloradd"><span>其他辅助检查*</span></div>
										<div class="col-xs-9">
											<span><input type="text" placeholder="请输入" id="cfgline_9"></span>
										</div>
										<div class="col-xs-3 coloradd"><span>分类</span></div>
										<div class="col-xs-9">
											<span class="editable" style="color: #757575;width: 40%;" id="cfgline_10"></span>
										</div>
										<div class="col-xs-3 coloradd"><span>指导</span></div>
										<div class="col-xs-9">
											<span><input type="text" class="show_cfgline_11" placeholder="请输入" id="cfgline_11"></span>
										</div>
										<div class="floatLeft coloradd" style="height: 80px;line-height: 40px;">
											<span>转<br>诊</span>
										</div>
										<div class="coloradd col-xs-2">
											<span>转诊建议</span>
										</div>
										<div class="col-xs-2">
											<span>
												<input type="text" class="show_cfgline_12_1" placeholder="请选择" id="cfgline_12_1">
											</span>
										</div>
										<div class="coloradd col-xs-2">
											<span>机构及科别</span>
										</div>
										<div class="col-xs-5">
										<span><input type="text" class="input_12_1" placeholder="请输入" id="cfgline_12_3" readonly='readonly'></span>
										</div>
										<div class="coloradd col-xs-2">
											<span>原因</span>
										</div>
										<div class="col-xs-9">
											<span><input type="text" class="input_12_1" placeholder="请输入" id="cfgline_12_2" readonly='readonly'></span>
										</div>
										<div class="col-xs-3 coloradd"><span>下次随访日期</span></div>
										<div class="col-xs-9">
											<span><input type="date" id="cfgline_13"></span>
										</div>
										<div class="col-xs-3 class  coloradd"><span>随访医生签名</span></div>
										 <div class="col-xs-9">
											<select id="cfgline_14" class="doctor_14" style="width: 140px">
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
    		 <!-- 转诊建议 --> 
              <div class="adjective adjective_cfgline_12_1" id ="cfgline_12_1_dialog"> 
               <p>转诊建议</p> 
               	<div class = "show" >
				</div>
				 <br /> 
               	<input name="有" class="inputYes_12_1" type="text" value="" placeholder="请输入" style="display: none;border: none;border-bottom:black solid 1px;width:480px;" id="cfgline_12_2"  />
               <br /> 
               	<input name="有" class="inputYes_12_1" type="text" value="" placeholder="请输入" style="display: none;border: none;border-bottom:black solid 1px;width:480px;" id="cfgline_12_3"  />
               <br /> 
               <button class="adjectiveBut_cfgline_12_1">确定</button> 
              </div> 
              <!-- 指导 --> 
              <div class="adjective adjective_cfgline_11" id ="cfgline_11_dialog"> 
               <p>指导</p> 
        			 <div class = "show" >
					</div>
               <br /> 
              <button class="adjectiveBut_cfgline_11">确定</button> 
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
