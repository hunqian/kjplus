<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <title>新闻详情页</title>
    <base id="base" href="${base}">
    <link rel="stylesheet" href="${base}/css/base.css">
    <link rel="stylesheet" href="${base}/css/news.css">
</head>
<body>
<div class="box">
    <!--header=========================================start-->
    <div class="header">
        <h1><i class="iconfont" onclick="javascript:history.back(-1);">&#xe61f;</i>新闻详情页</h1>
    </div>
    <!--header===========================================end-->

    <!--main===========================================start-->
    <div class="main">
            <div class="banner">
                <img src="./imgs/news/banner_1.png" alt="#">
            </div>
            <div class="title">
                <h3> ${info.infoTitle!''}</h3>
                <p class="clearfix">
                  
                    <span class="fl"><em style="position: relative; top: 0.25rem;">4分钟前</em></span>
                    <span class="fr">
                        <i class="collect id_collect" id="fz_z'+${refCode!0}+'"></i>
                        <em>${info.zanNum!0}</em>
                        <i class="see"  id="fz_f'+${refCode!0}+'"></i>
                        <em>${info.focusNum!0}</em>
                    </span>
                </p>
            </div>
            <div class="content">
                ${info.content!''}
            </div>
        </div>
    <!--main=============================================end-->
</div>

<div class="alert_onload">
    <div>
        <img src="${base}/imgs/index/onload.png" alt="">
    </div>
</div>

</body>
<script src="${base}/js/jquery-1.12.2.min.js"></script>
<script src="${base}/js/news.js"></script>
<script src="${base}/js/infopage.js"></script>
</html>