<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<base id="base" href="${base}">
		<title>第一次产前检查记录表</title>
		<meta name="keywords" content="第一次产前检查记录表" />
		<meta name="description" content="第一次产前检查记录表" />
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
			
			.floatLeft{
				float: left;
				border: 1px solid #DDD;
				width: 16.6666%;
				color: #336199;
			}
			.medicine{
			    height: 560px;
			    line-height: 560px;
			}
			.programme{
				height: 160px;
				padding-top: 60px;
			}
			.hospital{
				height: 240px;
				line-height: 40px;
			}
			em{
				position: absolute;
				left: 100px;
				color: black;
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
			i{
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
       <h1> 第一次产前检查记录表 </h1> 
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
                	<span><font size="5" color="blue">第一次产前检查信息</font>
                	</span>
            	</div>
                <div class="center"> 
                 <div class="row text"> 
                  						<div class="col-xs-2 coloradd"> 
					                   <span>姓名</span> 
					                  </div> 
					                  <div class="col-xs-4" style="padding-top: 1px;padding-bottom: 0px;"> 
					                   <span><input class="col-xs-12" type="text" placeholder="请输入" style="border: none;height: 22px;"/></span> 
					                  </div> 
					                  <div class="col-xs-2 coloradd"> 
					                   <span>编号</span> 
					                  </div> 
					                  <div class="col-xs-4" style="padding-top: 1px;padding-bottom: 0px;"> 
					                   <span><input class="col-xs-12" type="text" placeholder="系统自动生成" readonly="readonly" style="border: none;height: 22px;" value ="<#if followCode??>${followCode!''}</#if>"/></span> 
					                  </div> 
										<div class="col-xs-2 coloradd">
											<span>填表日期</span>
										</div>
										<div class="col-xs-4">
											<span><input type="date" id="cfgline_1_1"></span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>孕周</span>
										</div>
										<div class="col-xs-4">
											<span><input type="text" placeholder="请输入" id="cfgline_1_2" ><em>周</em></span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>孕妇年龄</span>
										</div>
										<div class="col-xs-4">
											<span><input type="text" placeholder="请输入" id="cfgline_2" ></span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>妇产科手术史</span>
										</div>
										<div class="col-xs-4">
											<span class="editable" style="color: #757575;" placeholder="请输入" id="cfgline_9" ></span>
										</div>
										<div class="col-xs-1 coloradd">
											<span>丈夫姓名</span>
										</div>	
										<div class="col-xs-3">
											<span><input type="text" placeholder="请输入" id="cfgline_3_1" ></span>
										</div>
										<div class="col-xs-1 coloradd">
											<span>丈夫年龄</span>
										</div>	
										<div class="col-xs-3">
											<span><input type="text" placeholder="请输入" id="cfgline_3_2" ></span>
										</div>
										<div class="col-xs-1 coloradd">
											<span>丈夫电话</span>
										</div>	
										<div class="col-xs-3">
											<span><input type="text" placeholder="请输入" id="cfgline_3_3" ></span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>孕次</span>		
										</div>	
										<div class="col-xs-4">
											<span><input type="text" placeholder="请输入" id="cfgline_4_1" ></span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>产次</span>	
										</div>
										<div class="col-xs-4">
											<span>
												<i>阴道分娩</i><input type="text" placeholder="请输入" style="width: 20%;" id="cfgline_4_2_1" ><i>次</i>
												<i>剖宫产</i><input type="text" placeholder="请输入" style="width: 20%;"  id="cfgline_4_2_2" ><i>次</i>
											</span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>末次月经</span>
										</div>
										<div class="col-xs-4">
											<span><input type="date" id="cfgline_5_1" ></span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>预产期</span>	
										</div>
										<div class="col-xs-4">
											<span><input type="date" id="cfgline_5_2" ></span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>身高</span>
										</div>
										<div class="col-xs-4">
											<span><input type="text" placeholder="请输入" id="cfgline_11_1" ><em>cm</em></span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>体重</span>
										</div>
										<div class="col-xs-4">
											<span><input type="text" placeholder="请输入" id="cfgline_11_2" ><em>Kg</em></span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>体质指数（BMI）</span>
										</div>
										<div class="col-xs-4">
											<span><input type="text" placeholder="请输入" id="cfgline_12_1" ><em>KG/m<sup>2</sup></em></span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>血压</span>
										</div>
										<div class="col-xs-4">
											<span>
												<input type="text" placeholder="请输入" style="width: 20%;" id="cfgline_12_2_1" ><i>/</i>
												<input type="text" placeholder="请输入" style="width: 20%;" id="cfgline_12_2_2"  ><i>mmHg</i>
											</span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>既往史</span>
										</div>	
										<div class="col-xs-10">
											<span><input type="text"  class=" show_cfgline_6" placeholder="请选择" id="cfgline_6" ></span>	
										</div>
										<div class="col-xs-2 coloradd">
											<span>家族史</span>
										</div>	
										<div class="col-xs-10">
											<span><input type="text" class=" show_cfgline_7" placeholder="请选择" id="cfgline_7" ></span>	
										</div>
										<div class="col-xs-2 coloradd">
											<span>个人史</span>
										</div>	
										<div class="col-xs-10">
											<span><input type="text"  class=" show_cfgline_8" placeholder="请选择" id="cfgline_8" ></span>	
										</div>
										<div class="col-xs-2 coloradd">
											<span>孕产史</span>
										</div>	
										<div class="col-xs-10">
											<span>
												<i>自然流产：</i><input type="text" placeholder="请输入" style="width: 7%;" id="cfgline_10_1" >
												<i>人工流产：</i><input type="text" placeholder="请输入" style="width: 7%;" id="cfgline_10_2" >
												<i>死胎：</i><input type="text" placeholder="请输入" style="width: 7%;" id="cfgline_10_3" >
												<i>死产：</i><input type="text" placeholder="请输入" style="width: 7%;" id="cfgline_10_4" >
												<i>新生儿死亡：</i><input type="text" placeholder="请输入" style="width: 7%;" id="cfgline_10_5" >
												<i>出生缺陷儿：</i><input type="text" placeholder="请输入" style="width: 7%;" id="cfgline_10_6" >
											</span>	
										</div>
										<div class="col-xs-2 coloradd">
											<span>听诊</span>	
										</div>
										<div class="col-xs-1 coloradd">
											<span>心脏：</span>
										</div>
										<div class="col-xs-4">
											<span>
												<span class="editable" style="color: #757575;" id="cfgline_13_1_1" ></span>
											</span>
										</div>
										<div class="col-xs-1 coloradd">
											<span>肺部：</span>
										</div>
										<div class="col-xs-4">
											<span class="editable" style="color: #757575;width: 70%;" id="cfgline_13_2" ></span>
										</div>
										<div class="col-xs-2 coloradd" style="height: 120px;line-height: 120px;">
											<span>妇科检查</span>
										</div>
										<div class="col-xs-1 coloradd">
											<span>外阴：</span>	
										</div>
										<div class="col-xs-4">
											<span>
												<span class="editable" style="color: #757575;" id="cfgline_14_1_1_1" ></span>
											</span>
										</div>
										<div class="col-xs-1 coloradd">
											<span>阴道：</span>	
										</div>
										<div class="col-xs-4">
											<span class="editable" style="color: #757575;" id="cfgline_14_1_2" ></span>
										</div>
										<div class="col-xs-1 coloradd">
											<span>宫颈：</span>	
										</div>
										<div class="col-xs-4">
											<span>
												<span class="editable" style="color: #757575;" id="cfgline_14_2_1_1" ></span>
											</span>
										</div>
										<div class="col-xs-1 coloradd">
											<span>子宫：</span>	
										</div>
										<div class="col-xs-4">
											<span class="editable" style="color: #757575; "id="cfgline_14_2_2" ></span>
										</div>
										<div class="col-xs-1 coloradd">
											<span>附件：</span>	
										</div>
										<div class="col-xs-9">
											<span class="editable" style="color: #757575;" id="cfgline_14_3" ></span>
										</div>
										<div class="floatLeft medicine coloradd">
											<span>辅助检查</span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>血常规</span>	
										</div>
										<div class="col-xs-8">
											<span>
												<i>血红蛋白值</i><input type="text" placeholder="请输入" style="width: 10%;" id="cfgline_15_1_1" ><i>g/L</i>
												<i>白细胞计数值</i><input type="text" placeholder="请输入" style="width: 10%;" id="cfgline_15_1_2" ><i>/L</i>
												<i>血小板计数值</i><input type="text" placeholder="请输入" style="width: 10%;" id="cfgline_15_1_3" ><i>/L</i>
												<i>其他</i><input type="text" placeholder="请输入" style="width: 10%;" id="cfgline_15_1_4" >
											</span>	
										</div>
										<div class="col-xs-2 coloradd">
											<span>尿常规</span>	
										</div>
										<div class="col-xs-8">
											<span>
												<i>尿蛋白</i><input type="text" placeholder="请输入" style="width: 12%;" id="cfgline_15_2_1" >
												<i>尿糖</i><input type="text" placeholder="请输入" style="width: 12%;"  id="cfgline_15_2_2" >
												<i>尿酮体</i><input type="text" placeholder="请输入" style="width: 12%;" id="cfgline_15_2_3"  >
												<i>尿潜血</i><input type="text" placeholder="请输入" style="width: 12%;" id="cfgline_15_2_4"  >
												<i>其他</i><input type="text" placeholder="请输入" style="width: 12%;"  id="cfgline_15_2_5" >
											</span>	
										</div>
										<div class="col-xs-1 coloradd" style="height: 80px;line-height: 80px;">
											<span>血型</span>
										</div>
										<div class="col-xs-1 coloradd">
											<span>ABO</span>	
										</div>
										<div class="col-xs-8">
											<span><input type="text" placeholder="请输入" id="cfgline_15_3_1" ></span>
										</div>
										<div class="col-xs-1 coloradd">
											<span>Rh*</span>	
										</div>
										<div class="col-xs-8">
											<span><input type="text" placeholder="请输入" id="cfgline_15_3_2" ></span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>血糖*</span>	
										</div>	
										<div class="col-xs-8">
											<span><input type="text" placeholder="请输入" id="cfgline_15_4" ><em>mmol/L</em></span>
										</div>
										<div class="col-xs-2 coloradd" style="height: 80px;line-height: 80px;">
											<span>肝功能</span>	
										</div>
										<div class="col-xs-8" style="height: 80px;">
											<span>
												<i>血清谷丙转氨酶</i><input type="text" placeholder="请输入" style="width: 10%;" id="cfgline_15_5_1" ><i>U/L</i>
												<i>血清谷草转氨酶</i><input type="text" placeholder="请输入" style="width: 10%;" id="cfgline_15_5_2" ><i>U/L</i>
												<i>白蛋白</i><input type="text" placeholder="请输入" style="width: 10%;" id="cfgline_15_5_3" ><i>g/L</i><br>
												<i>总胆红素</i><input type="text" placeholder="请输入" style="width: 10%;" id="cfgline_15_5_4" ><i>&#181;mol/L</i>
												<i>结合胆红素</i><input type="text" placeholder="请输入" style="width: 10%;" id="cfgline_15_5_5"><i>&#181;mol/L</i>
											</span>	
										</div>
										<div class="col-xs-2 coloradd">
											<span>肾功能</span>
										</div>
										<div class="col-xs-8">
											<span>
												<i>血清肌酐</i><input type="text" placeholder="请输入" style="width: 30%;" id="cfgline_15_6_1" ><i>&#181;mol/L</i>
												<i>血尿素</i><input type="text" placeholder="请输入" style="width: 30%;" id="cfgline_15_6_2" ><i>mmol/L</i>
											</span>
										</div>
										<div class="col-xs-2 coloradd" style="height: 80px;line-height: 80px;">
											<span>阴道分泌物*</span>
										</div>
										<div class="col-xs-8">
											<span><input type="text" class=" show_cfgline_15_7_1" placeholder="请选择" id="cfgline_15_7_1" ></span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>阴道清洁程度：</span>
										</div>
										<div class="col-xs-6">
											<span class="editable" style="color: #757575;" id="cfgline_15_7_2" ></span>
										</div>
										<div class="col-xs-2 coloradd" style="height: 80px;line-height: 80px;">
											<span>乙型肝炎</span>
										</div>
										<div class="col-xs-8" style="height: 80px;">
											<span>
												<i>乙型肝炎表面抗原</i><input type="text" placeholder="请输入" style="width: 10%;" id="cfgline_15_8_1" >
												<i>乙型肝炎表面抗体*</i><br>	
												<i>乙型肝炎e抗原*</i><input type="text" placeholder="请输入" style="width: 10%;" id="cfgline_15_8_2"  >
												<i>乙型肝炎e抗体*</i>
												<i>乙型肝炎核心抗体*</i>
											</span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>梅毒血清学实验*</span>
										</div>	
										<div class="col-xs-3">
											<span class="editable" style="color: #757575;" id="cfgline_15_9" ></span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>HIV抗体检测*</span>
										</div>	
										<div class="col-xs-3">
											<span class="editable" style="color: #757575;" id="cfgline_15_10" ></span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>B超*</span>
										</div>
										<div class="col-xs-3">
											<span><input type="text" placeholder="请输入" id="cfgline_15_11" ></span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>其他*</span>
										</div>
										<div class="col-xs-3">
											<span><input type="text" placeholder="请输入" id="cfgline_15_12" ></span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>总体评估</span>
										</div>
										<div class="col-xs-10">
											<span class="editable" style="color: #757575;" id="cfgline_16" ></span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>保健指导</span>
										</div>
										<div class="col-xs-10">
											<span><input type="text" class="show_cfgline_17" placeholder="请选择" id="cfgline_17" ></span>
										</div>
										<div class="floatLeft coloradd" style="height: 80px;line-height: 40px;">
											<span>转<br>诊</span>
										</div>
										<div class="coloradd col-xs-2">
											<span>转诊建议</span>
										</div>
										<div class="col-xs-2">
											<span>
												<input type="text" class="show_cfgline_18_1_1" placeholder="请选择" id="cfgline_18_1_1">
											</span>
										</div>
										<div class="coloradd col-xs-2">
											<span>机构及科别</span>
										</div>
										<div class="col-xs-4">
										<span><input type="text" class="input_18_1_1" placeholder="请选择是否转诊" id="cfgline_18_1_3" readonly='readonly'></span>
										</div>
										<div class="coloradd col-xs-2">
											<span>原因</span>
										</div>
										<div class="col-xs-8">
											<span><input type="text" class="input_18_1_1" placeholder="请选择是否转诊" id="cfgline_18_1_2" readonly='readonly'></span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>下次随访日期</span>
										</div>
										<div class="col-xs-4">
											<span><input type="date" id="cfgline_19_1" ></span>
										</div>
										<div class="col-xs-2 coloradd">
											<span>随访医生签字</span>
										</div>
										   <div class="col-xs-4">
											<select class="doctor_19_2" id="cfgline_19_2" style="width: 140px">
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
   	
   	 							<!-- 覆盖层 --> 
              					<div style="display: none;" id="cover"></div>
   								<!--既往史弹出框-->
								<div class="adjective adjective_cfgline_6" id ="cfgline_6_dialog" >
									<p>既往史选择</p>
									<div class = "show" >
									</div>
									<button class="adjectiveBut_cfgline_6">确定</button>
								</div>
								<!--家族史弹出框-->
								<div class="adjective  adjective_cfgline_7" id ="cfgline_7_dialog" >
									<p>家族史选择</p>
									<div class = "show" >
									</div>
									<button class="adjectiveBut_cfgline_7">确定</button>
								</div>
								<!--个人史弹出框-->
								<div class="adjective  adjective_cfgline_8" id ="cfgline_8_dialog" >
									<p>个人史选择</p>
									<div class = "show" >
									</div>
									<button class="adjectiveBut_cfgline_8">确定</button>
								</div>
								<!--阴道分泌物弹出框-->
								<div class="adjective adjective_cfgline_15_7_1" id ="cfgline_15_7_1_dialog" >
									<p>阴道分泌物选择</p>
									<div class = "show" >
									</div>
									<button class="adjectiveBut_cfgline_15_7_1">确定</button>
								</div>
								<!-- 转诊建议 --> 
					              <div class="adjective adjective_cfgline_18_1_1" id ="cfgline_18_1_1_dialog"> 
					               <p>转诊建议</p> 
					               	<div class = "show" >
									</div>
									 <br /> 
					               	<input name="有" class="inputYes_18_1_1" type="text" value="" placeholder="请填写原因" style="display: none;border: none;border-bottom:black solid 1px;width:480px;" id="cfgline_18_1_2"  />
					               <br /> 
					               	<input name="有" class="inputYes_18_1_1" type="text" value="" placeholder="请填写组织及科室" style="display: none;border: none;border-bottom:black solid 1px;width:480px;" id="cfgline_18_1_3"  />
					               <br /> 
					               <button class="adjectiveBut_cfgline_18_1_1">确定</button> 
					              </div> 
					              <!--保健指导弹出框-->
								<div class="adjective adjective_cfgline_17" id ="cfgline_17_dialog" >
									<p>保健指导选择</p>
									<div class = "show" >
									</div>
									<button class="adjectiveBut_cfgline_17">确定</button>
								</div>
		
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

