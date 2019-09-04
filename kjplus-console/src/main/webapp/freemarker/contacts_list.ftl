<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
	<base id="base" href="${base}">
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<link rel="stylesheet" type="text/css" href="${base}/assets/css/style.css">
	<title>管理员聊天console</title>
	<link href="${base}/assets/css/bootstrap.min.css" rel="stylesheet" />
			<!-- basic styles -->

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
	
	
	
</head>

<style>
	.mai{bottom:0; border-top:1px solid darkgrey; position:absolute;padding:16px 4px 4px 7px;width: 100%;height:95px;}
    .Input_text{border-radius: 4px; min-height:30px;max-height:50px;overflow: hidden; line-height:18px; border: 1px solid #b8b8b8;position:relative;width: 80%;margin-left: 40px;}
    #enter{font: 18px 'Microsoft YaHei';bottom: 48px;  position: absolute;left: 89%; cursor:pointer;}
    .wechat{position:absolute;bottom:48px;}
    .webq{position: absolute;bottom: 90px;left: 10px;border: 1px solid #ccc;background-color:#fff;}
    .addcolor{background:#3a3f45;}
</style>

<body>
<div class="main">
	<div class="main_inner">
		<div class="panel">
			<div class="header">
				<div class="img">
					<img src="${userById.face!''}" alt="">
				</div>
				<div class="info">${userById.nickName}</div>
				<div class="nav nav-tabs" id="myTab">
					<div class="active">
						<a data-toggle="tab" href="#home">
							最近联系人
						</a>
					</div>
					<div>
						<a data-toggle="tab" href="#profile">
							联系人
						</a>
					</div>
				</div>							
			</div>		
			<div class="tab-content">
					<div id="home" class="tab-pane in active">	>
						<#list rContacts as r>
							<div class="list" id="restcontact_${r.contSessionCode}">
								<div class="img">
									<img src="${r.contFace!''}" alt="">
								</div>
								<div class="info" id = "${r.contSessionCode}contantNickName" value ="${r.contNickName}">${r.contNickName}</div>
							</div>
						</#list> 	 
					</div>
					<div id="profile" class="tab-pane">
							<#list contacts as c>
							<div class="list" id="listcontact_${c.contSessionCode}">
								<div class="img">
									<img src="${c.contFace!''}" alt="">
								</div>
								<div class="info" id = "${c.contSessionCode}contantNickName" value ="${c.contNickName}">${c.contNickName}</div>
							</div>
						</#list> 	 	
					</div>
				</div>	
		</div>
		<div class="page2">
			<div class="p2_1">
				<div class="title_wrap">
					<span id="contantingSessionCode" value ="<#if (firstcontact.contSessionCode)??>${firstcontact.contSessionCode!''}</#if>">"></span>
					<span id="contantingNickName" value = "<#if (firstcontact.contNickName)??>${firstcontact.contNickName!''}</#if>"><#if (firstcontact.contNickName)??>${firstcontact.contNickName!''}</#if></span>
				</div>
				<div class="p2_1_1" id="messagePage">
					<div class="p2_1_1_1" id="message">
						<div class="time">
							<span id= "curTime">20:29</span>
						</div>
					</div>
				</div>
			</div>
			<div class="button">
				<div class="mai">
					<div class="wechat"><img src="${base}/assets/images/wxbq.png" alt="微信表情" style="width:30px;"/></div>
					<div class="webq" style="display:none;">						
					</div>	
			        <div contenteditable="true" class="Input_text">		        	
			        </div>
			        <button id="enter">发送</button>
			    </div>
			</div>
		</div>
	</div>
</div>


<!--[if !IE]> -->
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
	
	
	<script src="${base}/assets/js/jquery.hotkeys.min.js"></script>
	<script src="${base}/assets/js/bootstrap-wysiwyg.js"></script>
	<script src="${base}/assets/js/bootbox.min.js"></script>

	<!-- 小黄脸 -->
	<script src="${base}/assets/js/emoji-list-with-image.js"></script>
	<script src="${base}/assets/js/punycode.js"></script>
	<script src="${base}/assets/js/emoji.js"></script>

<script type="text/javascript" src="${base}/assets/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${base}/assets/js/rem.js"></script>
<script type="text/javascript" src="${base}/assets/js/preloadjs-0.6.0.min.js"></script>
<script type="text/javascript" src="${base}/assets/js/TweenMax.min.js"></script>
<script type="text/javascript" src="${base}/assets/js/iscroll.js"></script>

	<!--my javascript-->
	<script src="${base}/js/contacts_list.js"></script>
	<script src="${base}/js/websocket.js"></script>
	<script src="${base}/js/main.js"></script>

<!--<script src="//res.wx.qq.com/open/${base}/assets/js/jweixin-1.2.0.js"></script>-->
<!--<script type="text/javascript" src="${base}/assets/js/weixin.js?111"></script>-->

<!--<script type="text/javascript" src="${base}/assets/js/heartBeat.js"></script>-->
</body>
</html>