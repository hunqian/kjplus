<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<base id="base" href="${base}">
		<title>新建档案</title>
		<meta name="keywords" content="新建档案" />
		<meta name="description" content="新建档案" />
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
			.nav-tabs{border-bottom: none !important;}
			.center {
				text-align:center;
			}
			.center [class*="col-"] {
				margin-top:2px;
				margin-bottom:2px;
				padding-top:4px;
				padding-bottom:4px;
			
				position:relative;
				text-overflow:ellipsis;
			}
			.center [class*="show_cfgline"]{background-color:#fff !important}
			.center [class*="col-"]  span{
			  position:relative;
			  z-index:9;
				
			  display: inline-block;
			  overflow: hidden;
			  text-overflow: ellipsis;
			  white-space: nowrap;
			  color: #336199;	
			  width: 100%;
			}
			.center [class*="col-"]:before {
				position:absolute;
				top:0; bottom:0;
				left:2px; right:2px;
				content:"";
				display:block;
				border:1px solid #DDD;
				z-index: 1;
			}
			.text div:nth-child(even){
				background: #edf3f4;
			}
			.cover{
				position: fixed;
				top: 0;
				left: 0;
				z-index: 14;
				width: 100%;
				height: 100%;
				background: #ccc none repeat scroll 0% 0%;
				opacity: 0.5;
			}
			.adjective{
				width: 20rem;
				height: 30rem;
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
			input{background-color:#fff !important;}
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
						<li class="active">新建档案</li>
					<!--	 <li class="active"><#if prsnName??>${prsnName!''}</#if></li>   -->
					</ul>
					<!-- .breadcrumb -->

					
				</div>

					<div class="page-content">
						<div class="page-header">
							<h1>
								新建档案
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-9">
								<!-- PAGE CONTENT BEGINS -->

								<div class="row">
									<div class="col-xs-12">
										<div class="tabbable">
											<div class="tab-content">
												<!-- 个人档案表 -->
												<div id="home" class="tab-pane in active">
													<div class="center">
														<div class="row">
														<div class="col-xs-12">
                                                        	<span><font size="5" color="blue">个人信息</font>
                                                        	
                                                        	</span>
                                                    	</div>
                                                    	</div>
                                                    	<input type="hidden" id="docinfoid" value="<#if docInfo??>${docInfo.prsnId!0}</#if>">
                                                    	<div class="row text" style="text-align: initial;">
                                                    		<div class="col-xs-2" style="height: 208px;">
                                                    			<span class="profile-picture">
																<img id="avatar" class="editable img-responsive" alt="Alex's Avatar" src="${base}/assets/avatars/profile-pic.jpg" />
																</span>
                                                    		</div>
                                                    		<div class="col-xs-2">
                                                    			<span>姓名</span>
                                                    		</div>
                                                    		<div class="col-xs-3" style="padding-top: 1px;padding-bottom: 0px;">
                                                    			<span><input type="text" placeholder="请输入" style="border: none;" id="cfgline_0" ></span>
                                                    		</div>
                                                    		<div class="col-xs-2">
                                                    			<span>编号</span>
                                                    		</div>
                                                    		<div class="col-xs-3" style="padding-top: 1px;padding-bottom: 0px;">
                                                    			<span><input type="text" readonly = "readonly" placeholder="系统自动生成" style="border: none;" value="<#if docInfo??>${docInfo.code!''}</#if>" ></span>
                                                    		</div>
                                                    		<div class="col-xs-2">
                                                    			<span>性别</span>
                                                    		</div>
                                                    		<div class="col-xs-3" style="padding-top: 1px;padding-bottom: 0px;height: 31px;">
                                                    			<span class="editable" style="color: #757575;" id="cfgline_1_1" ></span>
                                                    		</div>
                                                    		<div class="col-xs-2">
                                                    			<span>出生日期</span>
                                                    		</div>
                                                    		<div class="col-xs-3" style="padding-top: 1px;padding-bottom: 0px;">
                                                    			<span><input type="date" placeholder="请输入" style="border: none;height: 26px;" id="cfgline_1_2" ></span>
                                                    		</div>
                                                    		<div class="col-xs-2">
                                                    			<span>本人电话</span>
                                                    		</div>
                                                    		<div class="col-xs-3" style="padding-top: 1px;padding-bottom: 0px;">
                                                    			<span><input type="text" placeholder="请输入" style="border: none;" id="cfgline_3_1" ></span>
                                                    		</div>
                                                    		<div class="col-xs-2">
                                                    			<span>身份证号码</span>
                                                    		</div>
                                                    		<div class="col-xs-3" style="padding-top: 1px;padding-bottom: 0px;">
                                                    			<span><input type="text" placeholder="请输入" style="border: none;" id="cfgline_2_1" ></span>
                                                    		</div>
                                                    		<div class="col-xs-2">
                                                    			<span>民族</span>
                                                    		</div>
                                                    		<div class="col-xs-3" style="padding-top: 1px;padding-bottom: 0px;height: 32px;">
                                                    			<span class="editable" id="cfgline_4_2" ></span>
                                                    		</div>
                                                    		<div class="col-xs-2">
                                                    			<span>文化程度</span>
                                                    		</div>
                                                    		<div class="col-xs-3" style="padding-top: 1px;padding-bottom: 0px;height: 32px;">
                                                    			<span class="editable" id="cfgline_6" ></span>
                                                    		</div>
                                                    		<div class="col-xs-2">
                                                    			<span>联系人</span>
                                                    		</div>
                                                    		<div class="col-xs-3" style="padding-top: 1px;padding-bottom: 0px;">
                                                    			<span><input type="text" placeholder="请输入" style="border: none;" id="cfgline_3_2" ></span>
                                                    		</div>
                                                    		<div class="col-xs-2">
                                                    			<span>联系人电话</span>
                                                    		</div>
                                                    		<div class="col-xs-3" style="padding-top: 1px;padding-bottom: 0px;">
                                                    			<span><input type="text" placeholder="请输入" style="border: none;" id="cfgline_3_3" ></span>
                                                    		</div>
                                                    		<div class="col-xs-2">
                                                    			<span>血型</span>
                                                    		</div>
                                                    		<div class="col-xs-3" style="padding-top: 1px;padding-bottom: 0px;height: 32px;">
                                                    			<span class="editable" id="cfgline_5" ></span>
                                                    		</div>
                                                    		<div class="col-xs-2">
                                                    			<span>RH阴性</span>
                                                    		</div>
                                                    		<div class="col-xs-3" style="padding-top: 1px;padding-bottom: 0px;height: 32px;">
                                                    			<span class="editable" id="negative" style="color: #757575;"></span>
                                                    		</div>
                                                    		<div class="col-xs-2">
                                                    			<span>常驻类型</span>
                                                    		</div>
                                                    		<div class="col-xs-4" style="padding-top: 1px;padding-bottom: 0px;height: 32px;">
                                                    			<span class="editable" id="cfgline_4_1" ></span>
                                                    		</div>
                                                    		<div class="col-xs-2">
                                                    			<span>婚姻状况</span>
                                                    		</div>
                                                    		<div class="col-xs-4" style="padding-top: 1px;padding-bottom: 0px;height: 32px;">
                                                    			<span class="editable" id="cfgline_8" ></span>
                                                    		</div>
                                                    		<div class="col-xs-2">
                                                    			<span>医疗费用支付方式</span>
                                                    		</div>
                                                    		<div class="col-xs-4" style="padding-top: 1px;padding-bottom: 0px;height: 32px;">
                                                    			<span><input type="text" class="col-xs-12 show_cfgline_9" placeholder="请选择" style="border: none;padding-bottom: 2px;" id="cfgline_9"></span>
                                                    		</div>
                                                    		<div class="col-xs-2">
                                                    			<span>职业</span>
                                                    		</div>
                                                    		<div class="col-xs-4" style="padding-top: 1px;padding-bottom: 0px;height: 32px;">
                                                    			<span class="editable" id="cfgline_7"></span>
                                                    		</div>
                                                    		<div class="col-xs-2">
                                                    			<span>地址</span>
                                                    		</div>
                                                    		<div class="col-xs-10" style="padding-top: 1px;padding-bottom: 0px;">
                                                    			<span><input type="text" class="col-xs-12" placeholder="请输入" style="border: none;padding-bottom: 2px;"></span>
                                                    		</div>
                                                    		<div class="col-xs-2">
                                                    			<span>药物过敏史</span>
                                                    		</div>
                                                    		<div class="col-xs-10" style="padding-top: 1px;padding-bottom: 0px;">
                                                    			<span><input type="text" class="col-xs-12 show_cfgline_10" placeholder="请选择" style="border: none;padding-bottom: 2px;" id="cfgline_10"></span>
                                                    		</div>
                                                    		
                                                    		<div class="col-xs-2">
                                                    			<span>暴露史</span>
                                                    		</div>
                                                    		<div class="col-xs-10" style="padding-top: 1px;padding-bottom: 0px;">
                                                    			<span><input type="text" class="col-xs-12 show_cfgline_11" placeholder="请选择" style="border: none;padding-bottom: 2px;"  id="cfgline_11" ></span>
                                                    		</div>
                                                    		
                                                    		<div class="col-xs-2">
                                                    			<span>既往史</span>
                                                    		</div>
                                                    		<div class="col-xs-10" style="padding-top: 1px;padding-bottom: 0px;">
                                                    			<span><input type="text" class="col-xs-12" placeholder="请输入" style="border: none;padding-bottom: 2px;"  id="cfgline_12" ></span>
                                                    		</div>
                                      						<div class="col-xs-2">
                                                    			<span>残疾情况</span>
                                                            </div>
                                                    		<div class="col-xs-10" style="padding-top: 1px;padding-bottom: 0px;">
                                                    			<span><input type="text" class="col-xs-12" placeholder="请输入" style="border: none;padding-bottom: 2px;"  id="cfgline_15" ></span>
                                                    		</div>
                                                    		 <div class="col-xs-2">
                                                    			<span>遗传病史</span>
                                                    		</div>
                                                    		<div class="col-xs-10" style="padding-top: 1px;padding-bottom: 0px;">
                                                    			<span><input type="text" class="col-xs-12" placeholder="请输入" style="border: none;padding-bottom: 2px;"  id="cfgline_14" ></span>
                                                    		</div>
                                                    		<div class="col-xs-2">
                                                    			<span>家族史</span>
                                                    		</div>
                                                    		<div class="col-xs-10" style="padding-top: 1px;padding-bottom: 0px;">
                                                    			<span><input type="text" class="col-xs-12" placeholder="请输入" style="border: none;padding-bottom: 2px;"  id="cfgline_13" ></span>
                                                    		</div>
                                                    		<div class="col-xs-2">
                                                    			<span>厨房排风设施</span>
                                                    		</div>
                                                    		<div class="col-xs-4" style="padding-top: 1px;padding-bottom: 0px;height: 32px;">
                                                    			<span class="editable" id="cfgline_17_1" ></span>
                                                    		</div>
                                                    		<div class="col-xs-2">
                                                    			<span>燃料类型</span>
                                                    		</div>
                                                    		<div class="col-xs-4" style="padding-top: 1px;padding-bottom: 0px;height: 32px;">
                                                    			<span class="editable" id="cfgline_17_2" ></span>
                                                    		</div>
                                                    		<div class="col-xs-1">
                                                    			<span>饮水</span>
                                                    		</div>
                                                    		<div class="col-xs-3" style="padding-top: 1px;padding-bottom: 0px;height: 32px;">
                                                    			<span class="editable" id="cfgline_17_3" ></span>
                                                    		</div>
                                                    		<div class="col-xs-1">
                                                    			<span>厕所</span>
                                                    		</div>
                                                    		<div class="col-xs-3" style="padding-top: 1px;padding-bottom: 0px;height: 32px;">
                                               
                                                    			<span class="editable" id="cfgline_17_4" ></span>
                                                    		</div>
                                                    		<div class="col-xs-1">
                                                    			<span>禽畜栏</span>
                                                    		</div>
                                                    		<div class="col-xs-3" style="padding-top: 1px;padding-bottom: 0px;height: 32px;">
                                                   
                                                    			<span class="editable" id="cfgline_17_5" ></span>
                                                    		</div>
                                                    		<div class="btn btn-primary" style="top:5px;left:40%;" id="save-docinfo-btn">
																保存
															</div>
															<div class="btn" style="top:5px;left:40%;" id="back-docinfolist-btn">取消</div>
                                                    	</div>
                                                    </div>
												</div>

												<!-- 覆盖层 -->	
												<div style="display: none;" class="cover cover_cfgline_10"></div>
												<!--药物过敏史弹出框-->
												<div class="adjective  adjective_cfgline_10" id ="cfgline_10_dialog" >
												<!--暴露史弹出框-->
												<div class="adjective  adjective_cfgline_11" id ="cfgline_11_dialog" >
													<p>暴露史选择</p>
													<div class = "show" >
													</div><!-- /.main-container -->
													<button class="adjectiveBut_cfgline_11">确定</button>
												</div>
												
												<!--医疗费用支付方式-->
												<div class="adjective  adjective_cfgline_9" id ="cfgline_9_dialog" >
													<p>医疗费用支付方式选择</p>
													<div class = "show" >
													</div>
													<button class="adjectiveBut_cfgline_9">确定</button>
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
		<script src="${base}/js/docinfo_new.js"></script>

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
				
				
			    //RH选项
			    var  negative = [{"id":"1","text":"阴性"},{"id":"2","text":"阳性"},{"id":"3","text":"不详"}];
				$('#negative').editable({
					type: 'select2',
					value : '1',
			        source: negative,
			    });

			    try {//ie8 throws some harmless exception, so let's catch it
					//it seems that editable plugin calls appendChild, and as Image doesn't have it, it causes errors on IE at unpredicted points
					//so let's have a fake appendChild for it!
					if( /msie\s*(8|7|6)/.test(navigator.userAgent.toLowerCase()) ) Image.prototype.appendChild = function(el){}
			
					var last_gritter
					$('#avatar').editable({
						type: 'image',
						name: 'avatar',
						value: null,
						image: {
							//specify ace file input plugin's options here
							btn_choose: 'Change Avatar',
							droppable: true,
							/**
							//this will override the default before_change that only accepts image files
							before_change: function(files, dropped) {
								return true;
							},
							*/
			
							//and a few extra ones here
							name: 'avatar',//put the field name here as well, will be used inside the custom plugin
							max_size: 110000,//~100Kb
							on_error : function(code) {//on_error function will be called when the selected file has a problem
								if(last_gritter) $.gritter.remove(last_gritter);
								if(code == 1) {//file format error
									last_gritter = $.gritter.add({
										title: 'File is not an image!',
										text: 'Please choose a jpg|gif|png image!',
										class_name: 'gritter-error gritter-center'
									});
								} else if(code == 2) {//file size rror
									last_gritter = $.gritter.add({
										title: 'File too big!',
										text: 'Image size should not exceed 100Kb!',
										class_name: 'gritter-error gritter-center'
									});
								}
								else {//other error
								}
							},
							on_success : function() {
								$.gritter.removeAll();
							}
						},
					    url: function(params) {
							// ***UPDATE AVATAR HERE*** //
							//You can replace the contents of this function with examples/profile-avatar-update.js for actual upload				
							var deferred = new $.Deferred;
							console.log(params);
							//if value is empty, means no valid files were selected
							//but it may still be submitted by the plugin, because "" (empty string) is different from previous non-empty value whatever it was
							//so we return just here to prevent problems
							var value = $('#avatar').next().find('input[type=hidden]:eq(0)').val();
							if(!value || value.length == 0) {
								deferred.resolve();
								return deferred.promise();
							}
							//dummy upload
							setTimeout(function(){
								if("FileReader" in window) {
									//for browsers that have a thumbnail of selected image
									var thumb = $('#avatar').next().find('img').data('thumb');
									if(thumb) $('#avatar').get(0).src = thumb;
								}
								
								deferred.resolve({'status':'OK'});
								
							 } , parseInt(Math.random() * 800 + 800))
			
							return deferred.promise();
						},
						
						success: function(response, newValue) {
						}
					})
				}catch(e) {}

				var oldie = /msie\s*(8|7|6)/.test(navigator.userAgent.toLowerCase());
				$('.easy-pie-chart.percentage').each(function(){
					$(this).easyPieChart({
						barColor: $(this).data('color'),
						trackColor: '#EEEEEE',
						scaleColor: false,
						lineCap: 'butt',
						lineWidth: 8,
						animate: oldie ? false : 1000,
						size:75
					}).css('color', $(this).data('color'));
				});
			
				$('[data-rel=tooltip]').tooltip();
				$('[data-rel=popover]').popover({html:true});
			
			
				$('#gritter-regular').on(ace.click_event, function(){
					$.gritter.add({
						title: 'This is a regular notice!',
						text: 'This will fade out after a certain amount of time. Vivamus eget tincidunt velit. Cum sociis natoque penatibus et <a href="#" class="blue">magnis dis parturient</a> montes, nascetur ridiculus mus.',
						image: $path_assets+'/avatars/avatar1.png',
						sticky: false,
						time: '',
						class_name: (!$('#gritter-light').get(0).checked ? 'gritter-light' : '')
					});
			
					return false;
				});
			
				$('#gritter-sticky').on(ace.click_event, function(){
					var unique_id = $.gritter.add({
						title: 'This is a sticky notice!',
						text: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus eget tincidunt velit. Cum sociis natoque penatibus et <a href="#" class="red">magnis dis parturient</a> montes, nascetur ridiculus mus.',
						image: $path_assets+'/avatars/avatar.png',
						sticky: true,
						time: '',
						class_name: 'gritter-info' + (!$('#gritter-light').get(0).checked ? ' gritter-light' : '')
					});
			
					return false;
				});
			
			
				$('#gritter-without-image').on(ace.click_event, function(){
					$.gritter.add({
						// (string | mandatory) the heading of the notification
						title: 'This is a notice without an image!',
						// (string | mandatory) the text inside the notification
						text: 'This will fade out after a certain amount of time. Vivamus eget tincidunt velit. Cum sociis natoque penatibus et <a href="#" class="orange">magnis dis parturient</a> montes, nascetur ridiculus mus.',
						class_name: 'gritter-success' + (!$('#gritter-light').get(0).checked ? ' gritter-light' : '')
					});
			
					return false;
				});
			
				$('#gritter-max3').on(ace.click_event, function(){
					$.gritter.add({
						title: 'This is a notice with a max of 3 on screen at one time!',
						text: 'This will fade out after a certain amount of time. Vivamus eget tincidunt velit. Cum sociis natoque penatibus et <a href="#" class="green">magnis dis parturient</a> montes, nascetur ridiculus mus.',
						image: $path_assets+'/avatars/avatar3.png',
						sticky: false,
						before_open: function(){
							if($('.gritter-item-wrapper').length >= 3)
							{
								return false;
							}
						},
						class_name: 'gritter-warning' + (!$('#gritter-light').get(0).checked ? ' gritter-light' : '')
					});
			
					return false;
				});
				
				$('#gritter-center').on(ace.click_event, function(){
					$.gritter.add({
						title: 'This is a centered notification',
						text: 'Just add a "gritter-center" class_name to your $.gritter.add or globally to $.gritter.options.class_name',
						class_name: 'gritter-info gritter-center' + (!$('#gritter-light').get(0).checked ? ' gritter-light' : '')
					});
			
					return false;
				});
				
				$('#gritter-error').on(ace.click_event, function(){
					$.gritter.add({
						title: 'This is a warning notification',
						text: 'Just add a "gritter-light" class_name to your $.gritter.add or globally to $.gritter.options.class_name',
						class_name: 'gritter-error' + (!$('#gritter-light').get(0).checked ? ' gritter-light' : '')
					});
			
					return false;
				});
					
				$("#gritter-remove").on(ace.click_event, function(){
					$.gritter.removeAll();
					return false;
				});
				
				$("#bootbox-regular").on(ace.click_event, function() {
					bootbox.prompt("What is your name?", function(result) {
						if (result === null) {
							//Example.show("Prompt dismissed");
						} else {
							//Example.show("Hi <b>"+result+"</b>");
						}
					});
				});
					
				$("#bootbox-confirm").on(ace.click_event, function() {
					bootbox.confirm("Are you sure?", function(result) {
						if(result) {
							//
						}
					});
				});
					
				$("#bootbox-options").on(ace.click_event, function() {
					bootbox.dialog({
						message: "<span class='bigger-110'>I am a custom dialog with smaller buttons</span>",
						buttons: 			
						{
							"success" :
							 {
								"label" : "<i class='icon-ok'></i> Success!",
								"className" : "btn-sm btn-success",
								"callback": function() {
									//Example.show("great success");
								}
							},
							"danger" :
							{
								"label" : "Danger!",
								"className" : "btn-sm btn-danger",
								"callback": function() {
									//Example.show("uh oh, look out!");
								}
							}, 
							"click" :
							{
								"label" : "Click ME!",
								"className" : "btn-sm btn-primary",
								"callback": function() {
									//Example.show("Primary button");
								}
							}, 
							"button" :
							{
								"label" : "Just a button...",
								"className" : "btn-sm"
							}
						}
					});
				});
			
				$('#spinner-opts small').css({display:'inline-block', width:'60px'})
			
				var slide_styles = ['', 'green','red','purple','orange', 'dark'];
				var ii = 0;
				$("#spinner-opts input[type=text]").each(function() {
					var $this = $(this);
					$this.hide().after('<span />');
					$this.next().addClass('ui-slider-small').
					addClass("inline ui-slider-"+slide_styles[ii++ % slide_styles.length]).
					css({'width':'125px'}).slider({
						value:parseInt($this.val()),
						range: "min",
						animate:true,
						min: parseInt($this.data('min')),
						max: parseInt($this.data('max')),
						step: parseFloat($this.data('step')),
						slide: function( event, ui ) {
							$this.attr('value', ui.value);
							spinner_update();
						}
					});
				});
				
				$.fn.spin = function(opts) {
					this.each(function() {
					  var $this = $(this),
						  data = $this.data();
			
					  if (data.spinner) {
						data.spinner.stop();
						delete data.spinner;
					  }
					  if (opts !== false) {
						data.spinner = new Spinner($.extend({color: $this.css('color')}, opts)).spin(this);
					  }
					});
					return this;
				};
			
				function spinner_update() {
					var opts = {};
					$('#spinner-opts input[type=text]').each(function() {
						opts[this.name] = parseFloat(this.value);
					});
					$('#spinner-preview').spin(opts);
				}
				$('#id-pills-stacked').removeAttr('checked').on('click', function(){
					$('.nav-pills').toggleClass('nav-stacked');
				});
				var dataHash = {};
				<#list dataHash?keys as key>
					dataHash['${key}'] = ${dataHash[key].refTypeId};
     			</#list>
			
     			DocInfoPage.init(${docinfoid});
			});
		</script>
	</body>
</html>