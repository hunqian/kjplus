<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<base id="base" href="${base}">
		<title>老年人生活自理能力评估表</title>
		<meta name="keywords" content="老年人生活自理能力评估表" />
		<meta name="description" content="老年人生活自理能力评估表" />
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
			  color: #336199;
			  width: 100%;
			  height: 100%;
			}
			.nav-tabs{border-bottom: none !important;}
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
			    line-height: 170px;
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
				top: 125px;
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
						<li class="active">老年人生活自理能力评估表</li>
						<li class="active"><#if prsnName??>${prsnName!''}</#if></li>
					</ul>
					<!-- .breadcrumb -->

					
				</div>

					<div class="page-content">
						<div class="page-header">
							<h1>
								老年人生活自理能力详情
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<div class="row">
									<div class="col-sm-9">
										<div class="tabbable">
											<ul class="nav nav-tabs" id="myTab">
												<li class="active">
                                                    <a data-toggle="tab" href="#home">个人基本信息</a>
                                                </li>

                                                <li>
                                                    <a data-toggle="tab" href="#profile">随访访视</a>
                                                </li>

                                                <li>
                                                    <a data-toggle="tab" href="#profile">体检</a>
                                                </li>

                                                <li>
                                                    <a data-toggle="tab" href="#profile">诊疗</a>
                                                </li>

                                                <li>
                                                    <a data-toggle="tab" href="#profile">转诊</a>
                                                </li>

                                                <li>
                                                    <a data-toggle="tab" href="#profile">测量记录</a>
                                                </li>

                                                <li>
                                                    <a data-toggle="tab" href="#profile">咨询记录</a>
                                                </li>
											</ul>

											<div class="tab-content" style="width: 1000px;">
												<div id="home" class="tab-pane in active">
													<div class="center">
														<div class="row">
														<div class="col-xs-12">
                                                        	<span><font size="5" color="blue">老年人生活自理能力</font>
                                                        	<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="save-followup-btn">
																<i class="icon-cog">保存</i>
															</div>
                                                        	</span>
                                                    	</div>
                                                    	</div>
                                                    	<input type="hidden" id="followupid" value="<#if followInfo??>${followInfo.code!''}</#if>">
														<input type="hidden" id="activepage" value="<#if activepage??>${activepage!''}</#if>">
                                                    	<div class="row text" style="text-align: initial;">
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
                                        <div class="col-xs-3 coloradd" style="height: 120px;line-height: 120px;">
											<span>评估事项、内容与评分</span>
										</div>
										<div class="col-xs-9 coloradd">
											<span>程度等级</span>
										</div>
										<div class="col-xs-2 coloradd" style="height: 80px;line-height: 80px;">
											<span>可自理</span>
										</div>
										<div class="col-xs-2 coloradd" style="height: 80px;line-height: 80px;">
											<span>轻度依赖</span>
										</div>
										<div class="col-xs-2 coloradd" style="height: 80px;line-height: 80px;">
											<span>中度依赖</span>
										</div>
										<div class="col-xs-2 coloradd" style="height: 80px;line-height: 80px;">
											<span>不能自理</span>
										</div>
										<div class="col-xs-1 coloradd" style="height: 80px;line-height: 80px;">
											<span>判断评分</span>
										</div>
										<div class="col-xs-3 coloradd addheight">
											<span>
												进餐：使用餐具将饭菜松紧口、咀嚼、吞咽等活动
												<em>评分</em>
											</span>
										</div>
										<div class="col-xs-2 addheight">
											<span class="black">
												独立完成
												<strong>0</strong>
											</span>
										</div>
										<div class="col-xs-2 addheight">
											<span class="black">
												————
												<strong>0</strong>
											</span>
										</div>
										<div class="col-xs-2 addheight">
											<span class="black">
												需要协助，如切碎，搅拌食物等
												<strong>3</strong>
											</span>
										</div>
										<div class="col-xs-2 addheight">
											<span class="black">
												完全需要帮助
												<strong>5</strong>
											</span>
										</div>
										<div class="col-xs-1 addheight">
											<span>
												<input type="text" placeholder="请评分">
											</span>
										</div>
										<div class="col-xs-3 coloradd addheight">
											<span>
												梳洗：梳头、洗脸、刷牙、剃须、洗澡等活动
												<em>评分</em>
											</span>
										</div>
										<div class="col-xs-2 addheight">
											<span class="black">
												独立完成
												<strong>0</strong>
											</span>
										</div>
										<div class="col-xs-2 addheight">
											<span class="black">
												能独立地洗头、梳头、洗脸、刷牙、剃须等；洗澡需要协助
												<strong>1</strong>
											</span>
										</div>
										<div class="col-xs-2 addheight">
											<span class="black">
												在协助下和适当的时间内，能完成部分梳洗活动
												<strong>3</strong>
											</span>
										</div>
										<div class="col-xs-2 addheight">
											<span class="black">
												完全需要帮助
												<strong>7</strong>
											</span>
										</div>
										<div class="col-xs-1 addheight">
											<span>
												<input type="text" placeholder="请评分">
											</span>
										</div>
										<div class="col-xs-3 coloradd addheight">
											<span>
												穿衣：穿衣裤、袜子、鞋子等活动
												<em>评分</em>
											</span>
										</div>
										<div class="col-xs-2 addheight">
											<span class="black">
												独立完成
												<strong>0</strong>
											</span>
										</div>
										<div class="col-xs-2 addheight">
											<span class="black">
												————
												<strong>0</strong>
											</span>
										</div>
										<div class="col-xs-2 addheight">
											<span class="black">
												需要协助，在适当的时间内完成部分穿衣
												<strong>3</strong>
											</span>
										</div>
										<div class="col-xs-2 addheight">
											<span class="black">
												完全需要帮助
												<strong>5</strong>
											</span>
										</div>
										<div class="col-xs-1 addheight">
											<span>
												<input type="text" placeholder="请评分">
											</span>
										</div>
										<div class="col-xs-3 coloradd addheight">
											<span>
												如厕：小便、大便等活动及自控
												<em>评分</em>
											</span>
										</div>
										<div class="col-xs-2 addheight">
											<span class="black">
												不需协助、可自控
												<strong>0</strong>
											</span>
										</div>
										<div class="col-xs-2 addheight">
											<span class="black">
												偶尔失禁，但基本上能如厕或使用便具
												<strong>1</strong>
											</span>
										</div>
										<div class="col-xs-2 addheight">
											<span class="black">
												经常失禁，在很多提示和协助下尚能如厕或使用便具
												<strong>5</strong>
											</span>
										</div>
										<div class="col-xs-2 addheight">
											<span class="black">
												完全失禁，完全需要帮助
												<strong>10</strong>
											</span>
										</div>
										<div class="col-xs-1 addheight">
											<span>
												<input type="text" placeholder="请评分">
											</span>
										</div>
										<div class="col-xs-3 coloradd addheight">
											<span>
												活动：站立、室内行走、上下楼梯、户外活动
												<em>评分</em>
											</span>
										</div>
										<div class="col-xs-2 addheight">
											<span class="black">
												独立完成所有活动
												<strong>0</strong>
											</span>
										</div>
										<div class="col-xs-2 addheight">
											<span class="black">
												借助较小的外力或辅助装置能完成站立、行走、上下楼梯等
												<strong>1</strong>
											</span>
										</div>
										<div class="col-xs-2 addheight">
											<span class="black">
												借助较大的外力才能完成站立、行走，不能上下楼梯等
												<strong>5</strong>
											</span>
										</div>
										<div class="col-xs-2 addheight">
											<span class="black">
												卧床不起，活动完全需要帮助
												<strong>10</strong>
											</span>
										</div>
										<div class="col-xs-1 addheight">
											<span>
												<input type="text" placeholder="请评分">
											</span>
										</div>
										<div class="col-xs-11 coloradd">
											<span>总得分</span>
										</div>
										<div class="col-xs-1">
											<span>
												<input type="text" placeholder="请评分">
											</span>
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
		<script src="${base}/js/aged_page.js"></script>

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
				
     			
     			Aged.init(${followupid});
			});
		</script>
	</body>
</html>

