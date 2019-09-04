<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <title>中医服务</title>
    <base id="base" href="${base}">
    <link rel="stylesheet" href="${base}/css/base.css">
    <link rel="stylesheet" href="${base}/css/swiper.min.css">
    <link rel="stylesheet" href="${base}/css/cn_doctor_service.css">
</head>
<body>
<div class="box">
    <!--header=========================================start-->
    <div class="header">
        <h1><i class="iconfont" onclick="javascript:history.back(-1);">&#xe61f;</i>中医服务</h1>
    </div>
    <!--header===========================================end-->

    <!--swiper=========================================start-->
    <div class="swiper swiper-container">
        <div class="swiper-wrapper swiper_view">
            <div class="swiper-slide"><img src="${base}/imgs/cn_doctor_service/swiper_1.png" alt=""></div>
            <div class="swiper-slide"><img src="${base}/imgs/cn_doctor_service/swiper_1.png" alt=""></div>
            <div class="swiper-slide"><img src="${base}/imgs/cn_doctor_service/swiper_1.png" alt=""></div>
        </div>
        <div class="swiper-pagination"></div>
    </div>
    <!--swiper===========================================end-->

    <!--tab============================================start-->
    <div class="tab">
        <div class="clearfix">
            <span class="fl family"><i></i>中医体质辨识</span>
            <span class="fl score"><i></i>中医药养生保健</span>
        </div>
    </div>
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
                            <span>刚刚</span>
                            <span>
                                <i class="collect"></i>
                                <em>23</em>
                                <i class="see"></i>
                                <em>5</em>
                            </span>
                        </p>
                    </div>
                </div>
                <div class="news clearfix">
                    <img src="${base}/imgs/health_information/banner_2.png" alt="banner2" class="fl">
                    <div class="fr">
                        <p class="text">过年聚餐前再叨念一次，一定要注意身体</p>
                        <p class="time">
                            <span>刚刚</span>
                            <span>
                                <i class="collect"></i>
                                <em>23</em>
                                <i class="see"></i>
                                <em>5</em>
                            </span>
                        </p>
                    </div>
                </div>
                <div class="news clearfix">
                    <img src="${base}/imgs/health_information/banner_3.png" alt="banner3" class="fl">
                    <div class="fr">
                        <p class="text">过年聚餐前再叨念一次，一定要注意身体</p>
                        <p class="time">
                            <span>刚刚</span>
                            <span>
                                <i class="collect"></i>
                                <em>23</em>
                                <i class="see"></i>
                                <em>5</em>
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
<script src="${base}/js/swiper.jquery.min.js"></script>
<script src="${base}/js/cn_doctor_service.js"></script>
<script>
    var mySwiper = new Swiper('.swiper-container', {
        autoplay: 3000,
        loop: true,
        pagination : '.swiper-pagination'
    })
</script>
</html>