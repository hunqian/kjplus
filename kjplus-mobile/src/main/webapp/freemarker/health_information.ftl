<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <title>健康资讯</title>
    <base id="base" href="${base}">
    <link rel="stylesheet" href="${base}/css/base.css">
    <link rel="stylesheet" href="${base}/css/swiper.min.css">
    <link rel="stylesheet" href="${base}/css/health_information.css">
</head>
<body>
<div class="box">
    <!--header=========================================start-->
    <div class="header">
        <h1><i class="iconfont" onclick="javascript:history.back(-1);">&#xe61f;</i>健康资讯</h1>
    </div>
    <!--header===========================================end-->

    <!--tab============================================start-->
    <div class="tab">
        <ul class="clearfix" id="catglist" data-catgid="${catgid!0}">
        	<#list catgs as c>
        		<li><a href="${base}/health_information.html?catgid=${c.id!0}"><span data-id="${c.id!0}">${c.name!''}</span></a></li>
        	</#list>
        </ul>
    </div>
    <!--tab==============================================end-->

    <!--search=========================================start-->
    <div class="search">
        <div class="clearfix">
            <i class="fl iconfont">&#xe628;</i>
            <input type="text" placeholder="请输入关键字" class="fl">
        </div>
    </div>
    <!--search===========================================end-->

    <!--swiper=========================================start-->
    <div class="swiper_box">
        <div class="swiper swiper-container">
            <div class="swiper-wrapper swiper_view id_swiper_view">
                <div class="swiper-slide"><img src="${base}/imgs/health_information/swiper_1.png" alt=""></div>
                <div class="swiper-slide"><img src="${base}/imgs/health_information/swiper_1.png" alt=""></div>
                <div class="swiper-slide"><img src="${base}/imgs/health_information/swiper_1.png" alt=""></div>
            </div>
            <div class="swiper-pagination"></div>
        </div>
    </div>
    <!--swiper===========================================end-->

    <!--main===========================================start-->
    <div class="main">
        <div class="message">
            <div class="news_box" id="info_box">
            </div>
        </div>
    </div>
    <!--main=============================================end-->

    <!--footer=========================================start-->
    <!--footer===========================================end-->

</div>
</body>
<script src="${base}/js/jquery-1.12.2.min.js"></script>
<script src="${base}/js/swiper.jquery.min.js"></script>
<script src="${base}/js/health_information.js"></script>
<script>
    var mySwiper = new Swiper('.swiper-container', {
        autoplay: 3000,
        loop: true,
        pagination : '.swiper-pagination'
    })
</script>
</html>