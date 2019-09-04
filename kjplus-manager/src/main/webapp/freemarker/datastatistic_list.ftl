<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
	<base id="base" href="${base}">
	<meta charset="utf-8" />
	<title>数据汇总</title>
	<meta name="courses" content="数据汇总" />
	<meta name="description" content="数据汇总" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />

	<!-- basic styles -->
	<link rel="stylesheet" href="${base}/assets/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${base}/assets/css/font-awesome.min.css" />
	<!--[if IE 7]>
	<link rel="stylesheet" href="${base}/assets/css/font-awesome-ie7.min.css" />
	<![endif]-->
	<link rel="stylesheet" href="${base}/assets/css/jquery-ui-1.10.3.full.min.css" />
	<!-- page specific plugin styles -->

	<!-- fonts -->
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />
	<!-- ace styles -->

	<link rel="stylesheet" href="${base}/assets/css/ace.min.css" />
	<link rel="stylesheet" href="${base}/assets/css/ace-rtl.min.css" />
	<link rel="stylesheet" href="${base}/assets/css/ace-skins.min.css" />
	<link rel="stylesheet" href="${base}/assets/css/jquery.timepicker.css" />
	<link rel="stylesheet" href="${base}/assets/css/jquery-ui-timepicker-addon-1.6.3.css" />

	<!--[if lte IE 8]>
	<link rel="stylesheet" href="${base}/assets/css/ace-ie.min.css" />
	<![endif]-->

	<!-- inline styles related to this page -->
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
						<li class="active">数据汇总</li>
					</ul>
					<!-- .breadcrumb -->
				</div>
	
				<!-- /.page-content -->
				
				
				<div class="page-content">
					
					<div class="main">
						<div class="col-xs-12">
							<div class="widget-header header-color-blue">
								<h4 class="lighter smaller">
									<i class="icon-bar-chart"></i>
									健康档案管理
								</h4>
							</div>
							<div class="widget-body">
								<div id="chartmain" style="height: 300px;width: 100%;"></div>
							</div>
						</div>
						
						<div class="col-xs-6">
							<div class="widget-header header-color-blue">
								<h4 class="lighter smaller">
									<i class="icon-group"></i>
									健康档案更新
								</h4>
							</div>
							<div class="widget-body">
								<div id="chartmainup" style="height: 300px;width: 100%;"></div>
							</div>
						</div>
						
						<div class="col-xs-6">
							<div class="widget-header header-color-blue">
								<h4 class="lighter smaller">
									<i class="icon-exclamation-sign"></i>
									预防接种服务
								</h4>
							</div>
							<div class="widget-body">
								<div id="chartma" style="height: 300px;width: 100%;display: inline-block;"></div>
							</div>
						</div>
					</div>
				</div>
				
				<!-- /.page-content -->
				
				
				
			</div>
			<!-- /.main-content -->

		</div>
		<!-- /.main-container-inner -->

		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="icon-double-angle-up icon-only bigger-110"></i>
		</a>
	</div>
	
	<div id="user_role_def" class="hide">

	<!-- /.main-container -->
	<div id="user_role_menu" class="hide">
		<div id="roleMenuTree" class="tree"></div>
	</div><!-- #dialog-rolemenu -->
	
	<!-- basic scripts -->

	
	<script src="${base}/assets/js/jquery-2.0.3.min.js"></script>
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

	<script src="${base}/assets/js/jquery-ui-1.10.3.full.min.js"></script>
	<script src="${base}/assets/js/jquery.timepicker.min.js"></script>
	<script src="${base}/assets/js/jquery-ui-timepicker-addon-1.6.3.js"></script>
	<script type="text/javascript">
		if("ontouchend" in document) document.write("<script src='${base}/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
	</script>
	<script src="${base}/assets/js/bootstrap.min.js"></script>
	<script src="${base}/assets/js/typeahead-bs2.min.js"></script>

	<!-- page specific plugin scripts -->
	<script src="${base}/assets/js/jquery.dataTables.min.js"></script>

	<!-- ace scripts -->
	<script src="${base}/assets/js/ace-elements.min.js"></script>
	<script src="${base}/assets/js/ace.min.js"></script>
	<script src="${base}/assets/js/jquery.hotkeys.min.js"></script>
	<script src="${base}/assets/js/bootstrap-wysiwyg.js"></script>
	<script src="${base}/assets/js/bootbox.min.js"></script>
	<!-- 异步上传图片 -->
	<script src="${base}/assets/js/jquery.form.js" type="text/javascript"></script>
	<script src="${base}/js/fuelux.menutree.js"></script>
	<script src="${base}/assets/js/fuelux/fuelux.tree.min.js"></script>
	<!--my javascript-->
	<script src="${base}/js/datastatistic_list.js"></script>

	<!-- 数据统计  --> 
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=pKMkmtyS5iM6nTga1SBWWtcBIiXXVbKX"></script>
	<script src="${base}/assets/js/echarts.js"></script>
	
	
	<!-- inline scripts related to this page -->
	<script type="text/javascript">
			
	
	//指定图标的配置和数据
	var option = {
	    title:{
	        // text:'任务统计',
	    },
	    tooltip:{},
	    legend:{
	    	x:"center",
	    	y:"top",
	    	// backgroundColor:"red",
	        data:['已完成','已开始','未开始']
	    },
	    xAxis:{
	        data:["巡视"]
	    },
	    yAxis:{ 
			splitLine:{  
				show:false  
			}//横线
	    },
	    series:[
	    {
	        name:'已完成',
	        type:'bar',
	        data:[419],
	        barWidth : 30,
	       	itemStyle: {   
	        //通常情况下：
	        normal:{  
	　　　　　　　　　　　　//每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
	            color: function (params){
	                var colorList = ['rgb(164,205,238)'];
	                return colorList[params.dataIndex];
	            }
	        }
	    }
	    },
	    {
	    name:'已开始',
	        type:'bar',
	        data:[687],
	        barWidth : 30,
	       	itemStyle: {   
	        //通常情况下：
	        normal:{  
	　　　　　　　　　　　　//每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
	            color: function (params){
	                var colorList = ['rgb(42,170,227)'];
	                return colorList[params.dataIndex];
	            }
	        }
	    }	
	    },
	    {
	    name:'未开始',
	        type:'bar',
	        data:[16],
	        barWidth : 30,
	       	itemStyle: {   
	        //通常情况下：
	        normal:{  
	　　　　　　　　　　　　//每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
	            color: function (params){
	                var colorList = ['rgb(25,46,94)'];
	                return colorList[params.dataIndex];
	            }
	        }
	    }	
	    }
	    ],
	    label: {
			normal: {
				show: true,
				position: 'top',
				textStyle: {
					color: 'black'
				}
			}
		}
	};
	//初始化echarts实例
	var myChart = echarts.init(document.getElementById('chartmain'));
	//使用制定的配置项和数据显示图表
	myChart.setOption(option);
	
	
	</script>
</body>
</html>
