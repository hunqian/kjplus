<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<base id="base" href="${base}">
		<title>健康体检表</title>
		<meta name="keywords" content="健康体检表" />
		<meta name="description" content="健康体检表" />
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
			  overflow: hidden;
			  text-overflow: ellipsis;
			  white-space: nowrap;
			  color: #336199;
			  width: 100%;
			}
			.row{
			margin-right: 0 !important;
    		 margin-left: 0 !important;
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
			.floatLeft{
				float: left;
				border: 1px solid #DDD;
				width: 8.3333%;
				color: #336199;
			}
			.same{
			    height: 280px;
			    line-height: 58px;
			    color: #336199;
			}
			.check{
			    height: 600px;
			    line-height: 290px;
			    color: #336199;
			}
			.viscera{
				height: 160px;
				line-height: 50px;
				color: #336199;
			}
			.auxiliary{
				height: 160px;
				line-height: 40px;
				color: #336199;
			}
			
			.life{
				height: 600px;
				line-height: 150px;
				color: #336199;
			}
			em{
				position: absolute;
				left: 100px;
				color: black;
			}
			.assist{
			    height: 520px;
			    line-height: 130px;
			}
			.question{
				height: 240px;
				line-height: 40px;
			}
			.hospital{
				height: 160px;
				line-height: 25px;
			}
			.medicine{
			    height: 80px;
			    line-height: 80px;
			}
			.programme{
				height: 80px;
				line-height:40px;
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
			.symptom,.food, .drinkAlcohol,.examine{
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
		.nav-tabs>li.active>a{z-index:11;}
			.symptom input,.food input,.drinkAlcohol input,.examine input{
				width: auto !important;
			}
			.colorHead>div:nth-child(odd){
				background-color: #edf3f4;
			}
			.adjective{
				width: 30rem;
				height: 55rem;
				display: none;
				border: 1px solid #ccc;
				position: fixed;
				left: 35%;
				top: 9rem;
				z-index: 16;
				background: #fff;
				border-radius: 2.5rem;
				padding-left: 40px; 
			}
			.coloradd{
				background-color: #edf3f4;
			}
			i{
				color: black;
				padding-right: 15px;
			}
			.textInitial{
				 text-align: initial !important;
			}
			.addForm{
				position:absolute;
				left:100%;
			}
			.removeForm{
				position:absolute;
				left:103%;
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
					<li class="active">健康体检</li>
					<li class="active"><#if prsnName??>${prsnName!''}</#if></li>
				</ul>
				<!-- .breadcrumb -->
			</div>

			<div class="page-content">
				<div class="page-header">
					<h1>
						体检详情
					</h1>
				</div><!-- /.page-header -->

						
								<!-- PAGE CONTENT BEGINS -->

				<div class="row">
					<div class="col-xs-9">
						<div class="tabbable">
							<ul class="nav nav-tabs" id="myTab">
								<li  class="active">
									<a data-toggle="tab" href="#health1">健康体检1</a>
								</li>

								<li>
									<a data-toggle="tab" href="#health2">健康体检2</a>
								</li>

								<li>
									<a data-toggle="tab" href="#health3">健康体检3</a>
								</li>

								<li>
									<a data-toggle="tab" href="#health4">健康体检4</a>
								</li>

							</ul>
							<input type="hidden" id="prsnId" value="<#if prsnId??>${prsnId!0}</#if>">
							<input type="hidden" id="activepage" value="<#if activepage??>${activepage!''}</#if>">
							<div class="tab-content">
								<!-- 健康体检表第一页 -->
								<input type="hidden" id="healthId" value="<#if healthId??>${healthId!0}</#if>">
								<div id="health1" class="tab-pane in active">
									<div class="center">
										<div class="row">
										<div class="col-xs-12">
											<span><font size="5" color="blue">体检信息</font>
											</span>
										</div>
										</div>
										<div class="row">
											<div class="row colorHead">
												<div class="col-xs-2">
													<span>体检日期</span>
												</div>
												<div class="col-xs-4" style="padding-top: 1px;padding-bottom: 0px;">
													<span><input type="date" placeholder="请输入" style="border: none;height: 26px;"></span>
												</div>
												<div class="col-xs-2">
													<span>责任医生</span>
												</div>
												<div class="col-xs-4">
												<select class="doctor_28_1" id="cfgline_28_1" style="width: 140px">
														<option value ="0">全部</option>
													<#list listStaffDto as l>
														<option value ="${l.stafId}">${l.stafName}</option>
													</#list>
												</select>
												</div>
												<div class="col-xs-1" style="background-color: white;">
													<span><b>内&nbsp;&nbsp;容</b></span>
												</div>
												<div class="col-xs-11">
													<span>
														<b>
															检&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															查&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															项&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															目
														</b>
													</span>
												</div>
												<div class="col-xs-1">
													<span>症状</span>
												</div>
												<div class="col-xs-11">
													<span><input class="show_cfgline_1 symptomIpt" type="text" placeholder="请选择" id="cfgline_1"></span>
												</div>
											</div>
											<!-- 一般状况 -->
											<div class="row">
												<div class="floatLeft same coloradd">
													<span>一<br/>般<br/>状<br/>况<br/></span>
												</div>
												<div class="col-xs-2 coloradd">
													<span>体温</span>
												</div>
												<div class="col-xs-3">
													<span><input type="text" placeholder="请输入" id="cfgline_2_1"><em>&#8451;</em></span>
												</div>
												<div class="col-xs-2 coloradd">
													<span>脉率</span>
												</div>
												<div class="col-xs-4">
													<span><input type="text" placeholder="请输入" id="cfgline_2_1_1"><em>次/分钟</em></span>
												</div>
												<div class="col-xs-2 coloradd" style="height: 80px;line-height: 80px;">
													<span>呼吸频率</span>
												</div>
												<div class="col-xs-3" style="height: 80px;line-height: 80px;">
													<span><input type="text" placeholder="请输入" id="cfgline_2_2"><em>次/分钟</em></span>
												</div>
												<div class="col-xs-1 coloradd" style="height: 80px;line-height: 80px;">
													<span>血压</span>
												</div>
												<div class="col-xs-1 coloradd">
													<span>左侧</span>
												</div>
												<div class="col-xs-4" style="text-align: initial;">
													<span>
														<input type="text" placeholder="请输入" style="width: 13%" id="cfgline_2_2_2_1_1"><i>/</i>
														<input type="text" placeholder="请输入" style="width: 13%" id="cfgline_2_2_2_2">
														<em>mmHg</em>
													</span>
												</div>
												<div class="col-xs-1 coloradd">
													<span>右侧</span>
												</div>
												<div class="col-xs-4" style="text-align: initial;">
													<span>
														<input type="text" placeholder="请输入" style="width: 13%" id="cfgline_2_2_2_2_1"><i>/</i>
														<input type="text" placeholder="请输入" style="width: 13%" id="cfgline_2_2_2_2_2">
														<em>mmHg</em>
													</span>
												</div>
												<div class="col-xs-2 coloradd">
													<span>身高</span>
												</div>
												<div class="col-xs-3">
													<span><input type="text" placeholder="请输入" id="cfgline_2_3"><em>cm</em></span>
												</div>
												<div class="col-xs-2 coloradd">
													<span>体重</span>
												</div>
												<div class="col-xs-4">
													<span><input type="text" placeholder="请输入" id="cfgline_2_3_3"><em>kg</em></span>
												</div>
												<div class="col-xs-2 coloradd">
													<span>腰围</span>
												</div>
												<div class="col-xs-3">
													<span><input type="text" placeholder="请输入" id="cfgline_2_4"><em>cm</em></span>
												</div>
												<div class="col-xs-2 coloradd">
													<span>体质指数（BMI）</span>
												</div>
												<div class="col-xs-4">
													<span><input type="text" placeholder="请输入" id="cfgline_2_4_4"><em>Kg/m<sup>2</sup></em></span>
												</div>
												<div class="col-xs-2 coloradd">
													<span>老年人健康状态自我评估</span>
												</div>
												<div class="col-xs-3">
													<span class="editable" style="color: #757575;" id="cfgline_2_5"></span>
												</div>
												<div class="col-xs-3 coloradd">
													<span>老年人生活自理能力自我评估</span>
												</div>
												<div class="col-xs-3">
													<span class="editable" style="color: #757575;" id="cfgline_2_6"></span>
												</div>
												<div class="col-xs-2 coloradd">
													<span>老年人认知功能</span>
												</div>
												<div class="col-xs-3">
													<span class="editable" style="color: #757575;" id="cfgline_2_7"></span>
												</div>
												<div class="col-xs-2 coloradd">
													<span>老年人情感状态</span>
												</div>
												<div class="col-xs-4">
													<span class="editable" style="color: #757575;" id="cfgline_2_8"></span>
												</div>	
											</div>
											<!-- 生活方式 -->
											<div class="row">
												<div class="floatLeft life coloradd">
													<span>生<br/>活<br/>方<br/>式<br/></span>
												</div>
												<div class="col-xs-3 coloradd">
													<span>饮食习惯</span>
												</div>
												<div class="col-xs-8">
													<span><input class="show_cfgline_3_2 foodIpt" type="text" placeholder="请选择" id="cfgline_3_2"></span>
												</div>
												<!-- 体育锻炼 -->
												<div class="col-xs-1 coloradd" style="height: 80px;line-height: 80px;">
													<span>体育锻炼</span>
												</div>
												<div class="col-xs-2 coloradd">
													<span>锻炼频率</span>
												</div>
												<div class="col-xs-3">
													<span class="editable" style="color: #757575;" id="cfgline_3_1_1"></span>
												</div>
												<div class="col-xs-2 coloradd">
													<span>每次锻炼时间</span>
												</div>
												<div class="col-xs-3">
													<span><input type="text" placeholder="请输入" id="cfgline_3_1_2_1"><em>分钟</em></span>
												</div>
												<div class="col-xs-2 coloradd">
													<span>坚持锻炼时间</span>
												</div>
												<div class="col-xs-3">
													<span><input type="text" placeholder="请输入" id="cfgline_3_2_2_2"><em>年</em></span>
												</div>
												<div class="col-xs-2 coloradd">
													<span>锻炼方式</span>
												</div>
												<div class="col-xs-3">
													<span><input type="text" placeholder="请输入" id="cfgline_3_1_3"></span>
												</div>
												<!-- 吸烟情况 -->
												<div class="col-xs-1 coloradd" style="height: 80px;line-height: 80px;">
													<span>吸烟情况</span>
												</div>
												<div class="col-xs-2 coloradd">
													<span>吸烟状况</span>
												</div>
												<div class="col-xs-3">
													<span class="editable" style="color: #757575;" id="cfgline_3_3_1"></span>
												</div>
												<div class="col-xs-2 coloradd">
													<span>日吸烟量</span>
												</div>
												<div class="col-xs-3">
													<span><i>平均&nbsp;</i><input type="text" placeholder="请输入" id="cfgline_3_3_2"><em>支</em></span>
												</div>
												<div class="col-xs-2 coloradd">
													<span>开始吸烟年龄</span>
												</div>
												<div class="col-xs-3">
													<span><input type="text" placeholder="请输入" id="cfgline_3_3_3_1"><em>岁</em></span>
												</div>
												<div class="col-xs-2 coloradd">
													<span>戒烟年龄</span>
												</div>
												<div class="col-xs-3">
													<span><input type="text" placeholder="请输入" id="cfgline_3_3_3_2"><em>岁</em></span>
												</div>
												<!-- 饮酒情况 -->
												<div class="col-xs-1 coloradd" style="height: 120px;line-height: 120px;">
													<span>饮酒情况</span>
												</div>
												<div class="col-xs-2 coloradd">
													<span>饮酒频率</span>
												</div>
												<div class="col-xs-3">
													<span class="editable" style="color: #757575;" id="cfgline_3_4_1"></span>
												</div>
												<div class="col-xs-2 coloradd">
													<span>日饮酒量</span>
												</div>
												<div class="col-xs-3">
													<span><i>平均&nbsp;</i><input type="text" placeholder="请输入" id="cfgline_3_4_2"><em>两</em></span>
												</div>
												<div class="col-xs-2 coloradd">
													<span>是否戒酒</span>
												</div>
												<div class="col-xs-3">
													<span class="editable" style="color: #757575;" id="cfgline_3_4_3"></span>
												</div>
												<div class="col-xs-2 coloradd">
													<span>开始饮酒年龄</span>
												</div>
												<div class="col-xs-3">
													<span><input type="text" placeholder="请输入" id="cfgline_3_4_4_1"><em>岁</em></span>
												</div>
												<div class="col-xs-2 coloradd">
													<span>近一年是否曾醉酒</span>
												</div>
												<div class="col-xs-3">
													<span class="editable" style="color: #757575;" id="cfgline_3_4_4_2"></span>
												</div>
												<div class="col-xs-2 coloradd">
													<span>饮酒种类</span>
												</div>
												<div class="col-xs-3">
													<span class="show_cfgline_3_4_5 editable" style="color: #757575;" id="cfgline_3_4_5"></span>
												</div>
												<!-- 职业病危害因素接触史 -->
												<div class="col-xs-3 coloradd" style="height:280px;line-height:280px;">
													<span>职业病危害因素接触史</span>
												</div>
												<div class="col-xs-8">
													<span class="editable" id="cfgline_3_5">弹出框</span>	
												</div>	
												<div class="col-xs-8 textInitial">
													<span id="cfgline_3_5">
														工种：<input type="text" placeholder="请填写" style="width:44%;" id="cfgline_3_5_1_1">
														从业时间：<input type="text" placeholder="请填写" style="width:36%;" id="cfgline_3_5_1_1_1">
													</span>	
												</div>
												<div class="col-xs-4 textInitial">
													<span id="cfgline_3_5">
														粉尘：<input type="text" placeholder="请填写" style="width:80%;" id="cfgline_3_5_2_">
													</span>	
												</div>
												<div class="col-xs-4 textInitial">
													<span id="cfgline_3_5">
														防护措施：<input type="text" placeholder="请填写" style="width:80%;" id="cfgline_3_5_2_1">
													</span>	
												</div>
												<div class="col-xs-4 textInitial">
													<span id="cfgline_3_5">
														放射物质：<input type="text" placeholder="请填写" style="width:80%;" id="cfgline_3_5_3">
													</span>	
												</div>
												<div class="col-xs-4 textInitial">
													<span id="cfgline_3_5">
														防护措施：<input type="text" placeholder="请填写" style="width:80%;" id="cfgline_3_5_3_1">
													</span>	
												</div>
												<div class="col-xs-4 textInitial">
													<span id="cfgline_3_5">
														物理因素：<input type="text" placeholder="请填写" style="width:80%;" id="cfgline_3_5_4">
													</span>	
												</div>
												<div class="col-xs-4 textInitial">
													<span id="cfgline_3_5">
														防护措施：<input type="text" placeholder="请填写" style="width:80%;" id="cfgline_3_5_4_1">
													</span>	
												</div>
												<div class="col-xs-4 textInitial">
													<span id="cfgline_3_5">
														化学物质：<input type="text" placeholder="请填写" style="width:80%;" id="cfgline_3_5_5">
													</span>	
												</div>
												<div class="col-xs-4 textInitial">
													<span id="cfgline_3_5">
														防护措施：<input type="text" placeholder="请填写" style="width:80%;" id="cfgline_3_5_5_1">
													</span>	
												</div>
												<div class="col-xs-4 textInitial">
													<span id="cfgline_3_5">
														其他：<input type="text" placeholder="请填写" style="width:80%;" id="cfgline_3_5_6">
													</span>	
												</div>
												<div class="col-xs-4 textInitial">
													<span id="cfgline_3_5">
														防护措施：<input type="text" placeholder="请填写" style="width:80%;" id="cfgline_3_5_6_1">
													</span>	
												</div>
												
											</div>
										</div>
									</div>
								</div>
								
								<!-- 健康体检表第二页 -->	
								<div id="health2" class="tab-pane">
									<div class="center">
										<!-- 脏器功能 -->
										<div class="row">
											<div class="floatLeft viscera coloradd" >
												<span>脏<br/>器<br/>功<br/>能<br/></span>
											</div>
											<div class="col-xs-2 coloradd" style="height: 80px;line-height:80px;">
												<span>口腔</span>
											</div>
											<div class="col-xs-1 coloradd">
												<span>口唇</span>
											</div>
											<div class="col-xs-3">
												<span class="editable" style="color: #757575;" id="cfgline_4_1_1"></span>
											</div>
											<div class="col-xs-1 coloradd">
												<span>齿列</span>	
											</div>
											<div class="col-xs-4">
												<span><input class="show_cfgline_4_1_2 toothIpt" type="text" placeholder="请选择" id="cfgline_4_1_2"></span>
											</div>	
											<div class="col-xs-1 coloradd">
												<span>咽部</span>
											</div>
											<div class="col-xs-8">
												<span class="editable" style="color: #757575;" id="cfgline_4_1_3"></span>
											</div>
											<div class="col-xs-2 coloradd">
												<span>视力</span>
											</div>
											<div class="col-xs-9" style="text-align: initial;">
												<span>
													<i>左眼：</i><input type="text" placeholder="请输入" style="width: 10%;" id="cfgline_4_2_1">
													<i>右眼：</i><input type="text" placeholder="请输入" style="width: 10%;" id="cfgline_4_2_2">
													(<i>
														矫正视力：左眼：<input type="text" placeholder="请输入" style="width: 10%;" id="cfgline_4_2_1_1_1">
																右眼：<input type="text" placeholder="请输入" style="width: 10%;" id="cfgline_4_2_1_1_2">
													</i>)
												</span>	
											</div>
											<div class="col-xs-2 coloradd">
												<span>听力</span>
											</div>
											<div class="col-xs-3">
												<span class="editable" style="color: #757575;" id="cfgline_4_3"></span>
											</div>
											<div class="col-xs-2 coloradd">
												<span>运动功能</span>
											</div>
											<div class="col-xs-4">
												<span class="editable" style="color: #757575;" id="cfgline_4_4"></span>
											</div>
										</div>
										<!-- 查体 -->
										<div class="row">
											<div class="floatLeft check coloradd">
												查<br/>体<br/>
											</div>
											<div class="col-xs-2 coloradd">
												<span>眼底*</span>
											</div>
											<div class="col-xs-3">
												<span class="editable" style="color: #757575;" id="cfgline_5_1"></span>
											</div>
											<div class="col-xs-2 coloradd">
												<span>皮肤</span>
											</div>
											<div class="col-xs-4">
												<span class="editable" style="color: #757575;" id="cfgline_5_2"></span>
											</div>
											<div class="col-xs-2 coloradd">
												<span>巩膜</span>
											</div>
											<div class="col-xs-3">
												<span class="editable" style="color: #757575;" id="cfgline_5_3"></span>
											</div>
											<div class="col-xs-2 coloradd">
												<span>淋巴结</span>	
											</div>
											<div class="col-xs-4">
												<span class="editable" style="color: #757575;" id="cfgline_5_4"></span>
											</div>
											<div class="col-xs-2 coloradd" style="height: 80px;line-height: 80px;">
												<span>肺</span>
											</div>
											<div class="col-xs-1 coloradd">
												<span>桶状胸：</span>
											</div>
											<div class="col-xs-3">
												<span class="editable" style="color: #757575;" id="cfgline_5_5_1"></span>
											</div>
											<div class="col-xs-1 coloradd">
												<span>呼吸音：</span>
											</div>
											<div class="col-xs-4">
												<span class="editable" style="color: #757575;" id="cfgline_5_5_2"></span>
											</div>
											<div class="col-xs-1 coloradd">
												<span>罗&nbsp;&nbsp;&nbsp;音：</span>
											</div>
											<div class="col-xs-8">
												<span class="editable" style="color: #757575;" id="cfgline_5_5_3"></span>
											</div>
											<div class="col-xs-2 coloradd" style="height: 80px;line-height: 80px;">
												<span>心脏</span>
											</div>
											<div class="col-xs-1 coloradd">
												<span>心率：</span>
											</div>
											<div class="col-xs-3">
												<span><input type="text" placeholder="请输入" id="cfgline_5_6_1"></span>
											</div>
											<div class="col-xs-1 coloradd">
												<span>心律：</span>
											</div>	
											<div class="col-xs-4">
												<span class="editable" style="color: #757575;height: 40px;" id="cfgline_5_6_2"></span>
											</div>
											<div class="col-xs-1 coloradd">
												<span>杂音：</span>
											</div>
											<div class="col-xs-8">
													<span class="editable" style="color: #757575;" id="cfgline_5_6_3"></span>
											</div>
											<div class="col-xs-2 coloradd" style="height: 120px;line-height: 120px;">
												<span>腹部</span>
											</div>
											<div class="col-xs-1 coloradd">
												<span>压痛：</span>
											</div>
											<div class="col-xs-3">
												<span class="editable" style="color: #757575;" id="cfgline_5_7_1"></span>
											</div>
											<div class="col-xs-1 coloradd">
												<span>包块：</span>
											</div>
											<div class="col-xs-4">
												<span class="editable" style="color: #757575;" id="cfgline_5_7_2"></span>
											</div>
											<div class="col-xs-1 coloradd">
												<span>肝大：</span>
											</div>
											<div class="col-xs-3">
												<span class="editable" style="color: #757575;" id="cfgline_5_7_3"></span>
											</div>
											<div class="col-xs-1 coloradd">
												<span>脾大：</span>
											</div>
											<div class="col-xs-4">
												<span class="editable" style="color: #757575;" id="cfgline_5_7_4"></span>
											</div>
											<div class="col-xs-2 coloradd">
												<span>移动性浊音：</span>
											</div>
											<div class="col-xs-7">
												<span class="editable" style="color: #757575;" id="cfgline_5_7_5"></span>		
											</div>
											<div class="col-xs-2 coloradd">
												<span>下肢水肿</span>
											</div>
											<div class="col-xs-3">
												<span class="editable" style="color: #757575;" id="cfgline_5_8"></span>
											</div>
											<div class="col-xs-2 coloradd">
												<span>足背动脉搏动*</span>
											</div>
											<div class="col-xs-4">
												<span class="editable" style="color: #757575;" id="cfgline_5_9"></span>
											</div>
											<div class="col-xs-2 coloradd">
												<span>肛门指诊*</span>
											</div>
											<div class="col-xs-3">
												<span class="editable" style="color: #757575;" id="cfgline_5_10"></span>
											</div>
											<div class="col-xs-2 coloradd">
												<span>乳&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;腺*</span>
											</div>
											<div class="col-xs-4">
												<span class="show_cfgline_5_11 editable" style="color: #757575;" id="cfgline_5_11"></span>
											</div>
											<div class="col-xs-2 coloradd" style="height: 120px;line-height: 120px;">
												<span>妇科*</span>
											</div>
											<div class="col-xs-1 coloradd">
												<span>外阴</span>
											</div>
											<div class="col-xs-3">
												<span class="editable" style="color: #757575;" id="cfgline_5_12_1"></span>
											</div>
											<div class="col-xs-1 coloradd">
												<span>阴道</span>
											</div>
											<div class="col-xs-4">
												<span class="editable" style="color: #757575;" id="cfgline_5_12_2"></span>
											</div>
											<div class="col-xs-1 coloradd">
												<span>宫颈</span>
											</div>
											<div class="col-xs-3">
												<span class="editable" style="color: #757575;" id="cfgline_5_12_3"></span>
											</div>
											<div class="col-xs-1 coloradd">
												<span>宫体</span>
											</div>
											<div class="col-xs-4">
												<span class="editable" style="color: #757575;" id="cfgline_5_12_4"></span>
											</div>
											<div class="col-xs-1 coloradd">
												<span>附件</span>
											</div>
											<div class="col-xs-8">
												<span class="editable" style="color: #757575;" id="cfgline_5_12_5"></span>
											</div>
											<div class="col-xs-2 coloradd">
												<span>其他*</span>
											</div>
											<div class="col-xs-9">
												<span><input type="text" placeholder="请输入" id="cfgline_5_13"></span>
											</div>
										</div>
										<!-- 辅助检查 -->
										<div class="row">
											<div class="floatLeft auxiliary coloradd">
												<span>辅<br/>助<br/>检<br/>查<br/></span>
											</div>
											<div class="col-xs-2 coloradd">
												<span>血&nbsp;常&nbsp;规*</span>
											</div>
											<div class="col-xs-9" style="text-align: initial;">
												<span>
													<i>血红蛋白</i><input type="text" placeholder="请输入" style="width: 10%;" id="cfgline_6_1_1"><i>g/L</i>
													<i>白细胞</i><input type="text" placeholder="请输入" style="width: 10%;" id="cfgline_6_1_2"><i>g/L</i>
													<i>血小板</i><input type="text" placeholder="请输入" style="width: 10%;" id="cfgline_6_1_3"><i>g/L</i>
													<i>其他</i><input type="text" placeholder="请输入" style="width: 30%;" id="cfgline_6_1_4">
												</span>
											</div>
											<div class="col-xs-2 coloradd">
												<span>尿&nbsp;常&nbsp;规*</span>
											</div>
											<div class="col-xs-9" style="text-align: initial;">
												<span>
													<i>尿蛋白</i><input type="text" placeholder="请输入" style="width: 10%;" id="cfgline_6_2_1">
													<i>糖尿</i><input type="text" placeholder="请输入" style="width: 10%;" id="cfgline_6_2_2">
													<i>尿酮体</i><input type="text" placeholder="请输入" style="width: 10%;" id="cfgline_6_2_3">
													<i>尿潜血</i><input type="text" placeholder="请输入" style="width: 10%;" id="cfgline_6_2_4">
													<i>其他</i><input type="text" placeholder="请输入" style="width: 30%;" id="cfgline_6_2_5">
												</span>
											</div>
											<div class="col-xs-2 coloradd">
												<span>空腹血糖*</span>
											</div>
											<div class="col-xs-9" style="text-align: initial;">
												<span>
													<input type="text" placeholder="请输入" style="width: 10%;" id="cfgline_6_3_1"><i>mmol/L</i>
													<i>或</i><input type="text" placeholder="请输入" style="width: 10%;" id="cfgline_6_3_1_1"><i>mg/dL</i>
												</span>
											</div>
											<div class="col-xs-2 coloradd">
												<span>心&nbsp;电&nbsp;图*</span>
											</div>
											<div class="col-xs-9">
												<span class="editable" style="color: #757575;" id="cfgline_6_4"></span>
											</div>
										</div>
									</div>
								</div>
								
								<!-- 健康体检表第三页 -->	
								<div id="health3" class="tab-pane">
									<div class="center">
										<div class="row">
											<div class="floatLeft assist coloradd">
												<span>辅<br/>助<br/>检<br/>查<br/></span>
											</div>
											<div class="col-xs-2 coloradd">
												<span>尿微量蛋白*</span>
											</div>	
											<div class="col-xs-3">
												<span><input type="text" placeholder="请输入" id="cfgline_6_5"><em>mg/dL</em></span>	
											</div>
											<div class="col-xs-2 coloradd">
												<span>大便潜血*</span>	
											</div>
											<div class="col-xs-4">
												<span class="editable" style="color: #757575;" id="cfgline_6_6"></span>
											</div>
											<div class="col-xs-2 coloradd">
												<span>糖化血红蛋白*</span>
											</div>	
											<div class="col-xs-3">
												<span><input type="text" placeholder="请输入" id="cfgline_6_7"><em>%</em></span>	
											</div>
											<div class="col-xs-2 coloradd">
												<span>乙型肝炎表面抗原*</span>	
											</div>
											<div class="col-xs-4">
												<span class="editable" style="color: #757575;" id="cfgline_6_8"></span>		
											</div>
											<div class="col-xs-2 coloradd" style="height: 120px;line-height: 120px;">
												<span>肝功能*</span>
											</div>
											<div class="col-xs-9" style="text-align: initial;height: 120px;">
												<span>
													<i>血清谷丙转氨酶</i><input type="text" placeholder="请输入" style="width: 15%;" id="cfgline_6_9_1"><i>U/L</i>
													<i>血清谷草转氨酶</i><input type="text" placeholder="请输入" style="width: 15%;" id="cfgline_6_9_2"><i>U/L</i><br/>
													<i>白蛋白</i><input type="text" placeholder="请输入" style="width: 15%;" id="cfgline_6_9_3"><i>g/L</i>
													<i>总胆红素</i><input type="text" placeholder="请输入" style="width: 15%;" id="cfgline_6_9_4"><i>&micro;mol/L</i><br/>
													<i>结合胆红素</i><input type="text" placeholder="请输入" style="width: 15%;" id="cfgline_6_9_5"><i>&micro;mol/L</i>
												</span>
											</div>
											<div class="col-xs-2 coloradd" style="height: 80px;line-height:80px;">
												<span>肾功能*</span>
											</div>
											<div class="col-xs-9" style="text-align: initial;height: 80px;">
												<span>
													<i>血清肌酐</i><input type="text" placeholder="请输入" style="width: 15%;" id="cfgline_6_10_1"><i>&micro;mol/L</i>
													<i>血尿素</i><input type="text" placeholder="请输入" style="width: 15%;" id="cfgline_6_10_2"><i>mmol/L</i><br/>
													<i>血钾浓度</i><input type="text" placeholder="请输入" style="width: 15%;" id="cfgline_6_10_3"><i>mmol/L</i>
													<i>血钠浓度</i><input type="text" placeholder="请输入" style="width: 15%;" id="cfgline_6_10_4"><i>mmol/L</i>
												</span>
											</div>	
											<div class="col-xs-2 coloradd" style="height: 80px;line-height:80px;">
												<span>血&nbsp;&nbsp;脂*</span>
											</div>
											<div class="col-xs-9" style="text-align: initial;height: 80px;">
												<span>
													<i>总胆固醇</i><input type="text" placeholder="请输入" style="width: 15%;" id="cfgline_6_11_1"><i>mmol/L</i>
													<i>甘油三酯</i><input type="text" placeholder="请输入" style="width: 15%;" id="cfgline_6_11_2"><i>mmol/L</i><br/>
													<i>血清低密度脂蛋白胆固醇</i><input type="text" placeholder="请输入" style="width: 15%;" id="cfgline_6_11_3"><i>mmol/L</i>
													<i>血清高密度脂蛋白胆固醇</i><input type="text" placeholder="请输入" style="width: 15%;" id="cfgline_6_11_4"><i>mmol/L</i>
												</span>
											</div>
											<div class="col-xs-2 coloradd">
												<span>胸部X线片*</span>
											</div>
											<div class="col-xs-9">
												<span class="editable" style="color: #757575;" id="cfgline_6_12"></span>	
											</div>
											<div class="col-xs-2 coloradd" >
												<span>B&nbsp;超*</span>
											</div>
											<div class="col-xs-1 coloradd">
												<span>腹部B超</span>
											</div>
											<div class="col-xs-3">
												<span class="editable" style="color: #757575;" id="cfgline_6_13_1"></span>
											</div>
											<div class="col-xs-1 coloradd">
												<span>其他</span>
											</div>
											<div class="col-xs-4">
												<span class="editable" style="color: #757575;" id="cfgline_6_13_2"></span>	
											</div>	
											<div class="col-xs-2 coloradd">
												<span>宫颈涂片*</span>
											</div>
											<div class="col-xs-9">
												<span class="editable" style="color: #757575;" id="cfgline_6_14"></span>
											</div>
											<div class="col-xs-2 coloradd">
												<span>其他*</span>
											</div>
											<div class="col-xs-9">
												<span><input type="text" placeholder="请输入" id="cfgline_6_15"></span>
											</div>
										</div>
										<div class="row">
											<div class="floatLeft question coloradd">
												<span>现<br/>存<br/>主<br/>要<br/>问<br/>题<br/></span>	
											</div>	
											<div class="col-xs-2 coloradd">
												<span>脑血管疾病</span>
											</div>
											<div class="col-xs-9">
												<span><input class="show_cfgline_7_1 symptomIpt" type="text" placeholder="请选择" id="cfgline_7_1"></span>
											</div>
											<div class="col-xs-2 coloradd">
												<span>肾脏疾病</span>
											</div>
											<div class="col-xs-9">
												<span><input class="show_cfgline_7_2 kidneyIpt" type="text" placeholder="请选择" id="cfgline_7_2"></span>	
											</div>
											<div class="col-xs-2 coloradd">
												<span>心脏疾病</span>
											</div>
											<div class="col-xs-9">
												<span><input class="show_cfgline_7_3 heartIpt" type="text" placeholder="请选择" id="cfgline_7_3"></span>
											</div>
											<div class="col-xs-2 coloradd">
												<span>血管疾病</span>
											</div>
											<div class="col-xs-9">
												<span><input class="show_cfgline_7_4 bloodvesselIpt" type="text" placeholder="请选择" id="cfgline_7_4"></span>
											</div>
											<div class="col-xs-2 coloradd">
												<span>眼部疾病</span>
											</div>
											<div class="col-xs-9">
												<span><input class="show_cfgline_7_5 eyesIpt" type="text" placeholder="请选择" id="cfgline_7_5"></span>
											</div>
											<div class="col-xs-2 coloradd">
												<span>神经系统疾病</span>	
											</div>
											<div class="col-xs-3">
												<span class="editable" style="color: #757575;" id="cfgline_7_6"></span>
											</div>
											<div class="col-xs-2 coloradd">
												<span>其他系统疾病</span>	
											</div>
											<div class="col-xs-4">
												<span class="editable" style="color: #757575;" id="cfgline_7_7"></span>
											</div>
										</div>
										<div class="row">
											<div class="floatLeft hospital coloradd">
												<span>住<br/>院<br/>治<br/>疗<br/>情<br/>况<br/></span>	
											</div>
											<div class="col-xs-2 coloradd" style="height: 80px;line-height: 80px;">
												<span>住院史</span>
											</div>
											<div class="col-xs-4 coloradd">
												<span>入/出院日期</span>
											</div>
											<div class="col-xs-2 coloradd">
												<span>原因</span>
											</div>
											<div class="col-xs-2 coloradd">
												<span>医疗机构名称</span>
											</div>
											<div class="col-xs-1 coloradd">
												<span>病案号</span>
											</div>
											<div  id="">
												<div class="col-xs-4" style="text-align: initial;">
													<span>
														<input type="date" style="width: 40%;margin-left: 50px;" id="cfgline_8_1_2">/
														<input type="date" style="width: 40%;" id="cfgline_8_1_2_1">
													</span>
												</div>
												<div class="col-xs-2">
													<span><input type="text" placeholder="请输入" id="cfgline_8_1_2_1_1"></span>
												</div>
												<div class="col-xs-2">
													<span><input type="text" placeholder="请输入" id="cfgline_8_1_2_1_1_1"></span>
												</div>
												<div class="col-xs-1">
													<span><input type="text" placeholder="请输入" id="cfgline_8_1_2_1_1_1_1"></span>
												</div>
											</div>
											<div class="col-xs-2 coloradd" style="height: 80px;line-height: 80px;">
												<span>家庭病史</span>
											</div>
											<div class="col-xs-4 coloradd">
												<span>建/撤床日期</span>
											</div>
											<div class="col-xs-2 coloradd">
												<span>原因</span>
											</div>
											<div class="col-xs-2 coloradd">
												<span>医疗机构名称</span>
											</div>
											<div class="col-xs-1 coloradd">
												<span>病案号</span>
											</div>
											<div id="">
												<div class="col-xs-4" style="text-align: initial;">
													<span>
														<input type="date" style="width: 40%;margin-left: 50px;" id="cfgline_8_1_2">/
														<input type="date" style="width: 40%;" id="cfgline_8_1_2_1">
													</span>
												</div>
												<div class="col-xs-2">
													<span><input type="text" placeholder="请输入" id="cfgline_8_1_2_1_1"></span>
												</div>
												<div class="col-xs-2">
													<span><input type="text" placeholder="请输入" id="cfgline_8_1_2_1_1_1"></span>
												</div>
												<div class="col-xs-1">
													<span><input type="text" placeholder="请输入" id="cfgline_8_1_2_1_1_1_1"></span>
												</div>
											</div>
										</div>
									</div>
								</div>
								
								<!-- 健康体检表第四页 -->	
								<div id="health4" class="tab-pane">
									<div class="center">
										<div class="row addOne">
											<div class="floatLeft medicine coloradd">
												<span>主要用药情况</span>
											</div>
											<div class="col-xs-2 coloradd">
												<span>药物名称</span>
											</div>
											<div class="col-xs-2 coloradd">
												<span>用法</span>	
											</div>
											<div class="col-xs-2 coloradd">
												<span>用量</span>
											</div>
											<div class="col-xs-2 coloradd">
												<span>用药时间</span>
											</div>	
											<div class="col-xs-3 coloradd">
												<span>服药依从性</span>	
											</div>
											<div id="list_9">
												<div class="col-xs-2">
												<span><input type="text" placeholder="请输入" id="cfgline_9_2"></span>
												</div>
												<div class="col-xs-2">
													<span><input type="text" placeholder="请输入" id="cfgline_9_2_1"></span>
												</div>
												<div class="col-xs-2">
													<span><input type="text" placeholder="请输入" id="cfgline_9_2_1_1"></span>
												</div>
												<div class="col-xs-2">
													<span><input type="text" placeholder="请输入" id="cfgline_9_2_1_1_1"></span>
												</div>	
												<div class="col-xs-3">
													<span class="editable" style="color: #757575;" id="cfgline_9_2_1_1_1_1"></span>
												</div>
											</div>		
											<img class="addForm" src="${base}/assets/images/add.png" alt="添加">	
											<img class="removeForm" src="${base}/assets/images/remove.png" alt="删减">									
										</div>
										<div class="row">
											<div class="floatLeft programme coloradd">
												<span>非免疫规划预防接种史</span>
											</div>
											<div id="">
												<div class="col-xs-2 coloradd">
												<span>名称</span>
												</div>
												<div class="col-xs-2 coloradd">
													<span>接种日期</span>
												</div>
												<div class="col-xs-7 coloradd">
													<span>接种机构</span>
												</div>
												<div class="col-xs-2">
													<span><input type="text" placeholder="请输入" id="cfgline_10_2"></span>
												</div>
												<div class="col-xs-2">
													<span><input type="date" id="cfgline_10_2_1"></span>
												</div>
												<div class="col-xs-7">
													<span><input type="text" placeholder="请输入" id="cfgline_10_2_1_1"></span>
												</div>
											</div>									
										</div>
										<div class="row">
											<div class="col-xs-3 coloradd">
												<span>健康评价</span>
											</div>
											<div class="col-xs-9">
												<span><input class="drinking" type="text" placeholder="请选择" id="cfgline_11"></span>
											</div>
											<div class="col-xs-3 coloradd">
												<span>健康指导</span>
											</div>
											<div class="col-xs-9">
												<span><input class="show_cfgline_12 symptomIpt" type="text" placeholder="请选择" id="cfgline_12"></span>	
											</div>
											<div class="col-xs-3 coloradd">
												<span>危险因素控制</span>
											</div>
											<div class="col-xs-9">
												<span><input class="show_cfgline_12_1_1 dangerousIpt" type="text" placeholder="请选择" id="cfgline_12_1_1"></span>	
											</div>
											
											<div class="btn btn-primary" style="top:5px;" id="save-healthexam-btn">
												保存
											</div>
											<div class="btn" style="top:5px;" id="back-healthexam-btn">取消</div>
										</div>										
									</div>
								</div>
								
								  <!-- 覆盖层 --> 
              					<div style="display: none;" id="cover"></div> 

								<!--症状弹出框-->
								<div class="adjective  adjective_cfgline_1" id ="cfgline_1_dialog" >
									<p>症状选择</p>
									<div class = "show" >
									</div><!-- /.main-container -->
									<button class="adjectiveBut_cfgline_1">确定</button>
								</div>
								
								<!--饮食习惯弹出框-->
								<div class="adjective  adjective_cfgline_3_2" id ="cfgline_3_2_dialog" >
									<p>饮食习惯选择</p>
									<div class = "show" >
									</div><!-- /.main-container -->
									<button class="adjectiveBut_cfgline_3_2">确定</button>
								</div>
								
								<!--饮酒种类弹出框-->
								<div class="adjective  adjective_cfgline_3_4_5" id ="cfgline_3_4_5_dialog" >
									<p>饮酒种类选择</p>
									<div class = "show" >
									</div><!-- /.main-container -->
									<button class="adjectiveBut_cfgline_3_4_5">确定</button>
								</div>
								
								<!--齿列弹出框-->
								<div class="adjective  adjective_cfgline_4_1_2" id ="cfgline_4_1_2_dialog" >
									<p>齿列选择</p>
									<div class = "show" >
									</div><!-- /.main-container -->
									<button class="adjectiveBut_cfgline_4_1_2">确定</button>
								</div>
								
								<!--乳腺弹出框-->
								<div class="adjective  adjective_cfgline_5_11" id ="cfgline_5_11_dialog" >
									<p>乳腺选择</p>
									<div class = "show" >
									</div><!-- /.main-container -->
									<button class="adjectiveBut_cfgline_5_11">确定</button>
								</div>
								
								<!--脑血管疾病弹出框-->
								<div class="adjective  adjective_cfgline_7_1" id ="cfgline_7_1_dialog" >
									<p>脑血管疾病选择</p>
									<div class = "show" >
									</div><!-- /.main-container -->
									<button class="adjectiveBut_cfgline_7_1">确定</button>
								</div>
								
								<!--肾脏疾病出框-->
								<div class="adjective  adjective_cfgline_7_2" id ="cfgline_7_2_dialog" >
									<p>肾脏疾病选择</p>
									<div class = "show" >
									</div><!-- /.main-container -->
									<button class="adjectiveBut_cfgline_7_2">确定</button>
								</div>
								
								<!--心脏弹出框-->
								<div class="adjective  adjective_cfgline_7_3" id ="cfgline_7_3_dialog" >
									<p>心脏选择</p>
									<div class = "show" >
									</div><!-- /.main-container -->
									<button class="adjectiveBut_cfgline_7_3">确定</button>
								</div>
								
								<!--血管弹出框-->
								<div class="adjective  adjective_cfgline_7_4" id ="cfgline_7_4_dialog" >
									<p>血管选择</p>
									<div class = "show" >
									</div><!-- /.main-container -->
									<button class="adjectiveBut_cfgline_7_4">确定</button>
								</div>
								
								<!--眼部弹出框-->
								<div class="adjective  adjective_cfgline_7_5" id ="cfgline_7_5_dialog" >
									<p>眼部选择</p>
									<div class = "show" >
									</div>
									<button class="adjectiveBut_cfgline_7_5">确定</button>
								</div>
								
								<!--健康指导弹出框-->
								<div class="adjective  adjective_cfgline_12" id ="cfgline_12_dialog" >
									<p>健康指导选择</p>
									<div class = "show" >
									</div>
									<button class="adjectiveBut_cfgline_12">确定</button>
								</div>
								
								<!--危险因素控制弹出框-->
								<div class="adjective  adjective_cfgline_12_1_2" id ="cfgline_12_1_2_dialog" >
									<p>眼部选择</p>
									<div class = "show" >
									</div><!-- /.main-container -->
									<button class="adjectiveBut_cfgline_12_1_2">确定</button>
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
		<!-- page specific plugin scripts -->
		<script src="${base}/assets/js/jquery.dataTables.min.js"></script>
		<script src="${base}/js/healthexam_page.js"></script>

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
				
     			HealthExam.init(${healthId});
			});
		</script>
	</body>
</html>