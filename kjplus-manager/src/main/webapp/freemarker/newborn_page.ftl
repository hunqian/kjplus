<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<base id="base" href="${base}">
		<title>新生儿家庭访视记录表</title>
		<meta name="keywords" content="新生儿家庭访视记录表" />
		<meta name="description" content="新生儿家庭访视记录表" />
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
			.spinner-preview {
				width:100px;
				height:100px;
				text-align:center;
				margin-top:60px;
			}
			
			.dropdown-preview {
				margin:0 5px;
				display:inline-block;
			}
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
		#cover1{
			position: fixed;
			top: 0;
			left: 190px;
			z-index: 15;
			width: 100%;
			height: 100%;
			background: #transparent none repeat scroll 0% 0%;
			opacity: 0.5;
		}
		#editfollowuppage{
			position: relative;
			top: 20;
			left: 20;
			z-index: 16;
			opacity: 1.0;
		}
		.adjective input{
			width:auto;
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
		em{
			position: absolute;
			left: 100px;
			color: black;
		}
		.coloradd{
			background-color: #edf3f4;
		}
		.send{
			background-color: #428bca!important;
    		border-color: #428bca;
		}
		.send:hover{
			background-color: #1b6aaa!important;
			border-color: #428bca;
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
       <h1> 新生儿家庭访视记录表 </h1> 
      </div> 
      <!-- /.page-header --> 
      <div class="row"> 
       <div class="col-xs-12"> 
        <!-- PAGE CONTENT BEGINS --> 
        <div class="center"> 
         <div class="row" id = "show"> 
             <div class="tabbable"> 
              <input type="hidden" id="followupid" value="<#if followupid??>${followupid!''}</#if>">
              <input type="hidden" id="tableCode" value="<#if tableCode??>${tableCode!''}</#if>">
              <input type="hidden" id="prsnId" value="<#if prsnId??>${prsnId!''}</#if>">
			  <input type="hidden" id="activepage" value="<#if activepage??>${activepage!''}</#if>">
              <div class="tab-content" style="width: 1000px;height: 1230px;"> 
              
               <div id="followup" class="tab-pane in active"> 
                <div class="col-xs-12">
                	<span><font size="5" color="blue">新生儿家庭访视信息</font>
                	</span>
            	</div>
            	
                <div class="center"> 
                <div clsaa="row">
                 <div class="text"> 
                  <div class="col-xs-2 coloradd"> 
                   <span>姓名</span> 
                  </div> 
                  <div class="col-xs-4" > 
                   <span><input class="" type="text" placeholder="请输入" value ="<#if prsnName??>${prsnName!''}</#if>"></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>编号</span> 
                  </div> 
                  <div class="col-xs-4" > 
                   <span><input class="" type="text" placeholder="系统自动生成" readonly = "readonly" value ="<#if followCode??>${followCode!''}</#if>"/></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>性别</span> 
                  </div> 
                  <div class="col-xs-4" > 
                   <span class="editable" style="color: #757575;" id="cfgline_1"</span>
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>出生日期</span> 
                  </div> 
                  <div class="col-xs-4" > 
                   <span><input type="date" placeholder="请输入" id="cfgline_1_1" /></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>身份证号</span> 
                  </div> 
                  <div class="col-xs-4" > 
                   <span><input class="" type="text" placeholder="请输入" " id="cfgline_2"/></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>家庭住址</span> 
                  </div> 
                  <div class="col-xs-4" > 
                   <span><input class="" type="text" placeholder="请输入" " id="cfgline_2_1"/></span> 
                  </div> 
                  <div class="col-xs-1 coloradd"> 
                   <span>父亲:姓名</span> 
                  </div> 
                  <div class="col-xs-1" > 
                   <span><input class="" type="text" placeholder="请输入" " id="cfgline_3_1" /></span> 
                  </div> 
                  <div class="col-xs-1 coloradd"> 
                   <span>职业</span> 
                  </div> 
                  <div class="col-xs-3" > 
                   <span><input class="" type="text" placeholder="请输入" " id="cfgline_3_1_1"/></span> 
                  </div> 
                  <div class="col-xs-1 coloradd"> 
                   <span>联系电话</span> 
                  </div> 
                  <div class="col-xs-2" > 
                   <span><input class="" type="text" placeholder="请输入" " id="cfgline_3_1_1_1" /></span> 
                  </div> 
                  <div class="col-xs-1 coloradd"> 
                   <span>出生日期</span> 
                  </div> 
                  <div class="col-xs-2" > 
                   <span><input type="date" placeholder="请输入"id="cfgline_3_1_1_1_1" /></span> 
                  </div> 
                  <div class="col-xs-1 coloradd"> 
                   <span>母亲:姓名</span> 
                  </div> 
                  <div class="col-xs-1" > 
                   <span><input class="" type="text" placeholder="请输入" " id="cfgline_4_1" /></span> 
                  </div> 
                  <div class="col-xs-1 coloradd"> 
                   <span>职业</span> 
                  </div> 
                  <div class="col-xs-3" > 
                   <span><input type="text" placeholder="请输入" " id="cfgline_4_1_1" /></span> 
                  </div> 
                  <div class="col-xs-1 coloradd"> 
                   <span>联系电话</span> 
                  </div> 
                  <div class="col-xs-2" > 
                   <span><input type="text" placeholder="请输入" " id="cfgline_4_1_1_1" /></span> 
                  </div> 
                  <div class="col-xs-1 coloradd"> 
                   <span>出生日期</span> 
                  </div> 
                  <div class="col-xs-2" > 
                   <span><input type="date" placeholder="请输入" id="cfgline_4_1_1_1_1" /></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>出生孕周</span> 
                  </div> 
                  <div class="col-xs-4" > 
                   <span><input type="text" placeholder="请输入" " id="cfgline_5" /><em>周</em></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>母亲妊娠期患病情况</span> 
                  </div> 
                  <div class="col-xs-4"> 
                   <span class="editable" style="color: #757575;" id="cfgline_5_1"></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>助产机构名称</span> 
                  </div> 
                  <div class="col-xs-4" > 
                  	<span><input type="text" placeholder="请输入" " id="cfgline_6"></span>  
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>出生情况</span> 
                  </div> 
                  <div class="col-xs-4" > 
                   <span><input type="text" class=" show_cfgline_6_1" placeholder="请选择" id="cfgline_6_1" /></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>新生儿窒息</span> 
                  </div> 
                  <div class="col-xs-4" > 
                   <span class="editable" style="color: #757575;" id="cfgline_7"></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>畸形</span> 
                  </div> 
                  <div class="col-xs-4" > 
                   <span class="editable" style="color: #757575;" id="cfgline_7_1"></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>新生儿听力筛查</span> 
                  </div> 
                  <div class="col-xs-4" > 
                   <span class="editable" style="color: #757575;" id="cfgline_8"></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>新生儿疾病筛查</span> 
                  </div> 
                  <div class="col-xs-4" > 
                   <span><input type="text" class=" show_cfgline_9" placeholder="请选择" id="cfgline_9" /></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>新生儿出生体重</span> 
                  </div> 
                  <div class="col-xs-2" > 
                   <span><input type="text" placeholder="请输入" " id="cfgline_10"/><em>kg</em></span> 
                  </div> 
                  <div class="col-xs-1 coloradd"> 
                   <span>目前体重</span> 
                  </div> 
                  <div class="col-xs-3" > 
                   <span><input type="text" placeholder="请输入" " id="cfgline_10_1" /><em>kg</em></span> 
                  </div> 
                  <div class="col-xs-1 coloradd"> 
                   <span>出生身长</span> 
                  </div> 
                  <div class="col-xs-3" > 
                   <span><input type="text" placeholder="请输入" " id="cfgline_10_1_1" /><em>cm</em></span> 
                  </div> 
                  <div class="col-xs-1 coloradd"> 
                   <span>喂养方式</span> 
                  </div> 
                  <div class="col-xs-3" > 
                   <span class="editable" style="color: #757575;" id="cfgline_11"></span> 
                  </div> 
                  <div class="col-xs-1 coloradd"> 
                   <span>吃奶量</span> 
                  </div> 
                  <div class="col-xs-3" > 
                   <span><input type="text" placeholder="请输入" " id="cfgline_11_1" /><em>ml/次</em></span> 
                  </div> 
                  <div class="col-xs-1 coloradd"> 
                   <span>吃奶次数</span> 
                  </div> 
                  <div class="col-xs-3" > 
                   <span><input type="text" placeholder="请输入" " id="cfgline_11_1_1" /><em>次/日</em></span> 
                  </div> 
                  <div class="col-xs-1 coloradd"> 
                   <span>呕吐</span> 
                  </div> 
                  <div class="col-xs-3" > 
                   <span class="editable" style="color: #757575;" id="cfgline_12"></span> 
                  </div> 
                  <div class="col-xs-1 coloradd"> 
                   <span>大便</span> 
                  </div> 
                  <div class="col-xs-3" > 
                   <span class="editable" style="color: #757575;" id="cfgline_12_1"></span> 
                  </div> 
                  <div class="col-xs-1 coloradd"> 
                   <span>大便次数</span> 
                  </div> 
                  <div class="col-xs-3" > 
                   <span><input type="text" placeholder="请输入" " id="cfgline_12_1_1" /><em>次/日</em></span> 
                  </div> 
                  <div class="col-xs-1 coloradd"> 
                   <span>体温</span> 
                  </div> 
                  <div class="col-xs-3" > 
                   <span><input type="text" placeholder="请输入" " id="cfgline_13" /><em>&deg;C</em></span> 
                  </div> 
                  <div class="col-xs-1 coloradd"> 
                   <span>心率</span> 
                  </div> 
                  <div class="col-xs-3" > 
                   <span><input type="text" placeholder="请输入" " id="cfgline_13_1" /><em>次/分钟</em></span> 
                  </div> 
                  <div class="col-xs-1 coloradd"> 
                   <span>呼吸频率</span> 
                  </div> 
                  <div class="col-xs-3" > 
                   <span><input type="text" placeholder="请输入" " id="cfgline_13_1_1" /><em>次/分钟</em></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>面色</span> 
                  </div> 
                  <div class="col-xs-2" > 
                   <span>
                   	<input type="text" class="show_cfgline_14_1_1" placeholder="请输入" id="cfgline_14_1_1"/>
                   </span>                    
                  </div> 
                  <div class="col-xs-8" style="padding-top: 1px;padding-bottom: 0px;background-color:#fff;"> 
                  <span>
                  <input type="text" name="其他" class="input_14_1_1" placeholder="请填写其他症状" style=";border: none;padding-bottom: 2px;" readonly="readonly" id="cfgline_14_1_2"/>
                  </span>
                  </div>
                  </div>
                  <div class="text"> 
                  <div class="col-xs-2 coloradd"> 
                   <span>黄疸部位</span> 
                  </div> 
                  <div class="col-xs-10" > 
                   <span><input type="text" class=" show_cfgline_14_2" placeholder="请选择" id="cfgline_14_2" /></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>前囟</span> 
                  </div> 
                  <div class="col-xs-5">
                	<span>
                		<input type="text" class="input_15_1" placeholder="请输入" style="width: 10%;" id="cfgline_15_1_1">cm &times;
                		<input type="text" class="input_15_1" placeholder="请输入" style="width: 10%;" id="cfgline_15_1_1_1">cm
                  	</span>
                  </div> 
                  <div class="col-xs-5">
                  	<span><input type="text" class=" show_cfgline_15_1" placeholder="请选择" id="cfgline_15_1" /></span></span>
                  </div>
                  <div class="col-xs-2 coloradd"> 
                   <span>眼睛</span> 
                  </div> 
                  <div class="col-xs-4" > 
                   <span class="editable"style="color: #757575;" id="cfgline_16"></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>四肢活动度</span> 
                  </div> 
                  <div class="col-xs-4" > 
                   <span class="editable" style="color: #757575;" id="cfgline_16_1"></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>耳外观</span> 
                  </div> 
                  <div class="col-xs-4" > 
                   <span class="editable" style="color: #757575;" id="cfgline_17"></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>颈部包块</span> 
                  </div> 
                  <div class="col-xs-4" > 
                   <span class="editable" style="color: #757575;" id="cfgline_17_1"></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>鼻</span> 
                  </div> 
                  <div class="col-xs-4" > 
                   <span class="editable" style="color: #757575;" id="cfgline_18"></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>皮肤</span> 
                  </div> 
                  <div class="col-xs-4" > 
                   <span class="editable" style="color: #757575;" id="cfgline_18_1"></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>口腔</span> 
                  </div> 
                  <div class="col-xs-4" > 
                   <span class="editable" style="color: #757575;" id="cfgline_19"></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>肛门</span> 
                  </div> 
                  <div class="col-xs-4" > 
                   <span class="editable" style="color: #757575;" id="cfgline_19_1"></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>心肺听诊</span> 
                  </div> 
                  <div class="col-xs-4" > 
                   <span class="editable" style="color: #757575;" id="cfgline_20"></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>胸部</span> 
                  </div> 
                  <div class="col-xs-4" > 
                   <span class="editable" style="color: #757575;" id="cfgline_20_1"></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>腹部触诊</span> 
                  </div> 
                  <div class="col-xs-4" > 
                   <span class="editable" style="color: #757575;" id="cfgline_21"></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>脊柱</span> 
                  </div> 
                  <div class="col-xs-4" > 
                   <span class="editable" style="color: #757575;" id="cfgline_21_1"></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>外生殖器</span> 
                  </div> 
                  <div class="col-xs-4" > 
                   <span class="editable" style="color: #757575;" id="cfgline_22"></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>脐带</span> 
                  </div> 
                  <div class="col-xs-4" > 
                   <span class="editable" style="color: #757575;" id="cfgline_23"></span> 
                  </div>
                  <div class="col-xs-1 coloradd" style="padding-top: 1px;padding-bottom: 0px;height:80px;line-height:80px;">
                  <span>转诊</span>
                  </div> 
                  </div>
                  <div class="text"> 
                  <div class="col-xs-2 coloradd"> 
                   <span>转诊建议</span> 
                  </div> 
                  <div class="col-xs-2" > 
                   <span><input type="text" class=" show_cfgline_24_1" placeholder="请选择" id="cfgline_24_1" /></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>科室及科别</span> 
                  </div> 
                  <div class="col-xs-5" > 
                   <span><input type="text" class=" input_24_1" placeholder="请输入" readonly="readonly" id="cfgline_24_3" /></span> 
                  </div>
                  <div class="col-xs-2 coloradd"> 
                   <span>原因</span> 
                  </div> 
                  <div class="col-xs-9" > 
                   <span><input type="text" class=" input_24_1" placeholder="请输入" readonly="readonly" id="cfgline_24_2" /></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>指导</span> 
                  </div> 
                  <div class="col-xs-10" > 
                   <span><input type="text" class=" show_cfgline_26" placeholder="请选择" id="cfgline_26" /></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>本次随访日期</span> 
                  </div> 
                  <div class="col-xs-4" > 
                   <span><input type="date" placeholder="请输入" style="border: none;height: 26px;" id="cfgline_27" /></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>下次随访地点</span> 
                  </div> 
                  <div class="col-xs-4" > 
                   <span><input class="" type="text" placeholder="请输入" " id="cfgline_27_1" /></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>下次随访日期</span> 
                  </div> 
                  <div class="col-xs-4" > 
                   <span><input type="date" placeholder="请输入" style="border: none;height: 26px;" id="cfgline_28" /></span> 
                  </div> 
                  <div class="col-xs-2 coloradd"> 
                   <span>随访医生签字</span> 
                  </div> 
                  <div class="col-xs-4">
					<select class="doctor_28_1" id="cfgline_28_1" style="width: 140px">
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
     
     
              <!-- /.row --> 
              <!-- 覆盖层 --> 
              <div style="display: none;" id="cover"></div> 
              <div style="display: none;" id="cover1"></div> 
              
              <!-- 面色弹出框 --> 
              <div class="adjective adjective_cfgline_14_1_1" id ="cfgline_14_1_1_dialog"> 
               <p>面色</p> 
	              	<div class = "show">
					</div>
               	<br /> 
               	<button class="adjectiveBut_cfgline_14_1_1">确定</button>
              </div> 
              
              	<!-- 黄疸部位 -->
				<div class="adjective adjective_cfgline_14_2"  id ="cfgline_14_2_dialog">
					<p>黄疸部位</p>
						<div class = "show" >
						</div>
					<br/>
					<input name="其他" class="inputYes_14_1_1" type="text" value="" placeholder="请填写面色症状" style="display: none;border: none;border-bottom:black solid 1px;width:480px;"/>
              		 <button class="adjectiveBut_cfgline_14_1">确定</button> 
				</div>
				
				<!-- 前囟 -->
				<div class="adjective adjective_cfgline_15_1"  id ="cfgline_15_1_dialog">
					<p>前囟</p>
						<div class = "show" >
						</div>
					<br/>
					<button class="adjectiveBut_cfgline_15_1">确定</button>
				</div>
				
				<!-- 出生情况 -->
				<div class="adjective adjective_cfgline_6_1"  id ="cfgline_6_1_dialog">
					<p>出生情况</p>
						<div class = "show" >
						</div>
					<br/>
					<button class="adjectiveBut_cfgline_6_1">确定</button>
				</div>
				
				<!-- 新生儿疾病筛查 -->
				<div class="adjective adjective_cfgline_9"  id ="cfgline_9_dialog">
					<p>新生儿疾病筛查</p>
						<div class = "show" >
						</div>
					<br/>
					<button class="adjectiveBut_cfgline_9">确定</button>
				</div>
              
              <!-- 转诊建议 --> 
              <div class="adjective adjective_cfgline_24_1" id ="cfgline_24_1_dialog"> 
               <p>转诊建议</p> 
               	<div class = "show" >
				</div>
				 <br /> 
               	<input name="有" class="inputYes_24_1" type="text" value="" placeholder="请填写原因" style="display: none;border: none;border-bottom:black solid 1px;width:480px;" />
               <br /> 
               	<input name="有" class="inputYes_24_1" type="text" value="" placeholder="请填写组织及科室" style="display: none;border: none;border-bottom:black solid 1px;width:480px;" />
               <br /> 
               <button class="adjectiveBut_cfgline_24_1">确定</button> 
              </div> 
              
              <!-- 指导 --> 
              <div class="adjective adjective_cfgline_26" id ="cfgline_26_dialog"> 
               <p>指导</p> 
        			 <div class = "show" >
					</div>
               <br /> 
              <button class="adjectiveBut_cfgline_26">确定</button> 
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
