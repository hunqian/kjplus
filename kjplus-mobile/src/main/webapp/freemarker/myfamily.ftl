<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <title>我的家庭</title>
    <base id="base" href="${base}">
    <link rel="stylesheet" href="${base}/css/base.css">
    <link rel="stylesheet" href="${base}/css/myfamily.css">
</head>
<body>
<div class="box">
    <!--header=========================================start-->
    <div class="header">
        <h1><i class="iconfont" onclick="javascript:history.back(-1);">&#xe61f;</i>我的家庭 <span class="create_doc">建档</span></h1>
    </div>
    <!--header===========================================end-->

    <!--swiper=========================================start-->
        <div class="banner">
            <img src="${base}/imgs/myfamily/bgi.png" alt="#">
        </div>
    <!--swiper===========================================end-->

    <!--main===========================================start-->
    <div class="main">
    	<div class="family" >
            <img src="${base}/imgs/myfamily/portrait_1.png" alt="#">
            <div>
                <h4>
                    <span>用户名-关系</span>
                    <i class="woman"></i>
                    <em>年龄</em>
                </h4>
                <p>签约医生，服务包（简称）</p>
                <p>身份证号</p>
            </div>
        </div>
       
    	<#list listData as data>
	        <div class="family" data-code="${data.prsnCode}">
	            <img src="${data.url!''}" alt="#">
	            <div>
	                <h4>
	                    <span>${data.name!''}-${data.relationTypeName!''}</span>
	                    <i class="${data.sex!''}"></i>
	                    <em>${data.age!''}</em>
	                </h4>
	                <p>${data.staffName!''}，${data.srvPackageName!''}（${data.srvPackageAlias!''}）</p>
	                <p>${data.idNumber!''}</p>
	            </div>
	        </div>
	    </#list>
    <!--
        
        <div class="family">
            <img src="${base}/imgs/myfamily/portrait_1.png" alt="#">
            <div>
                <h4>
                    <span>欧阳询-儿子</span>
                    <i class="man"></i>
                    <em>28</em>
                </h4>
                <p>签约家庭医生王爱英，基础服务（A）</p>
                <p>140420xxxxxxxx0014</p>
            </div>
        </div>
        
        <div class="family">
            <img src="${base}/imgs/myfamily/portrait_2.png" alt="#">
            <div>
                <h4>
                    <span>欧阳倩-女儿</span>
                    <i class="woman"></i>
                    <em>26</em>
                </h4>
                <p>签约家庭医生张军民，基础服务（A）</p>
                <p>140420xxxxxxxx0014</p>
            </div>
        </div>
        -->
    </div>
    <!--main=============================================end-->

    <!--footer=========================================start-->
    <!--footer===========================================end-->

</div>
</body>
<script src="${base}/js/jquery-1.12.2.min.js"></script>
<script src="${base}/js/myfamily.js"></script>
</html>