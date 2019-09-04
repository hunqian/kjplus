<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <title>我的收藏</title>
    <base id="base" href="${base}">
    <link rel="stylesheet" href="${base}/css/base.css">
    <link rel="stylesheet" href="${base}/css/mycollect.css">
</head>
<body>
<div class="box">
    <!--header=========================================start-->
    <div class="header">
        <h1><i class="iconfont" onclick="javascript:history.back(-1);">&#xe61f;</i>我的收藏</h1>
    </div>
    <!--header===========================================end-->

    <!--swiper=========================================start-->
    <!--swiper===========================================end-->

    <!--tab============================================start-->
    <!--tab==============================================end-->

    <!--main===========================================start-->
    <div class="main">
        <div class="message">
            <div class="news_box">
                <div class="news clearfix">
                    <img src="${base}/imgs/health_information/banner_1.png" alt="banner1" class="fl">
                    <div class="fr">
                        <p class="text">过年聚餐前再叨念一次，一定要注意身体</p>
                        <p class="time">
                            <span>刚刚 收藏</span>
                            <span>
                                <i class="off"></i>
                            </span>
                            <!--<span class="delete">取消</span>-->
                        </p>
                    </div>
                </div>
                <div class="news clearfix">
                    <img src="${base}/imgs/health_information/banner_2.png" alt="banner2" class="fl">
                    <div class="fr">
                        <p class="text">自动驾驶AI诊所或重塑21世纪医疗保健行业</p>
                        <p class="time">
                            <span>4分钟前 收藏</span>
                            <span>
                                <i class="off"></i>
                            </span>
                        </p>
                    </div>
                </div>
                <div class="news clearfix">
                    <img src="${base}/imgs/health_information/banner_3.png" alt="banner3" class="fl">
                    <div class="fr">
                        <p class="text">英国医疗科技项目路演北京专场拉开帷幕</p>
                        <p class="time">
                            <span>2017-06-21 收藏</span>
                            <span>
                                <i class="off"></i>
                            </span>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--main=============================================end-->

    <!--footer=========================================start-->
    <!--footer===========================================end-->

</div>
</body>
<script src="${base}/js/jquery-1.12.2.min.js"></script>
<script src="${base}/js/mycollect.js"></script>
</html>