<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <title>药事服务</title>
    <base id="base" href="${base}">
    <link rel="stylesheet" href="${base}/css/base.css">
    <link rel="stylesheet" href="${base}/css/swiper.min.css">
    <link rel="stylesheet" href="${base}/css/drug_service.css">
</head>
<body>
<div class="box">
    <!--header=========================================start-->
    <div class="header">
        <h1><i class="iconfont" onclick="javascript:history.back(-1);">&#xe61f;</i>药事服务</h1>
    </div>
    <!--header===========================================end-->

    <!--swiper=========================================start-->
    <div class="swiper swiper-container">
        <div class="swiper-wrapper swiper_view">
            <div class="swiper-slide"><img src="${base}/imgs/drug_service/swiper_1.png" alt=""></div>
            <div class="swiper-slide"><img src="${base}/imgs/drug_service/swiper_1.png" alt=""></div>
            <div class="swiper-slide"><img src="${base}/imgs/drug_service/swiper_1.png" alt=""></div>
        </div>
        <div class="swiper-pagination"></div>
    </div>
    <!--swiper===========================================end-->

    <!--tab============================================start-->
    <div class="tab tab-container">
        <div class="swiper-wrapper tab_view">
            <div class="swiper-slide">
                <ul class="clearfix">
                    <li><a href="${base}/drug_detail.html"><img src="${base}/imgs/drug_service/nav_1.png" alt=""><span>中心药房</span></a></li>
                    <li><a href="drug_prescription.html"><img src="${base}/imgs/drug_service/nav_2.png" alt=""><span>药品处方</span></a></li>
                    <li><a href="#"><img src="${base}/imgs/drug_service/nav_3.png" alt=""><span>用药提醒</span></a></li>
                    <li><a href="${base}/drug_delivery.html"><img src="${base}/imgs/drug_service/nav_4.png" alt=""><span>药品配送</span></a></li>
                </ul>
            </div>
            <!--<div class="swiper-slide">slider2</div>-->
            <!--<div class="swiper-slide">slider3</div>-->
        </div>
    </div>
    <!--tab==============================================end-->

    <!--main===========================================start-->
    <div class="main">
        <!--健康资讯-->
        <div class="message">
            <h3 class="clearfix">用药知识<a href="${base}/health_information.html" class="fr">更多>></a></h3>
            <div class="news_box">
                <div class="news clearfix">
                    <img src="${base}/imgs/drug_service/banner_1.png" alt="banner2" class="fl">
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
                    <img src="${base}/imgs/drug_service/banner_2.png" alt="banner3" class="fl">
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
<script src="${base}/js/drug_service.js"></script>
<script>
    var mySwiper = new Swiper('.swiper-container', {
        autoplay: 3000,
        loop: true,
        pagination : '.swiper-pagination'
    })
    var myTab = new Swiper('.tab-container', {
        autoplay: 0,
        resistanceRatio: 0
    })
</script>
</html>