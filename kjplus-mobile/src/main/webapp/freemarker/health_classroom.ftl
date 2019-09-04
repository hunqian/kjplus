<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <title>健康讲堂</title>
    <base id="base" href="${base}">
    <link rel="stylesheet" href="${base}/css/base.css">
    <link rel="stylesheet" href="${base}/css/swiper.min.css">
    <link rel="stylesheet" href="${base}/css/health_information.css">
</head>
<body>
<div class="box">
    <!--header=========================================start-->
    <div class="header">
        <h1><i class="iconfont" onclick="javascript:history.back(-1);">&#xe61f;</i>健康讲堂</h1>
    </div>
    <!--header===========================================end-->

    <!--tab============================================start-->
    <div class="tab">
        <ul class="clearfix">
            <li><span>卫生中心</span></li>
            <li><span>中医保健</span></li>
            <li><span>高血压</span></li>
            <li><span>糖尿病</span></li>
            <li><span>肺结核</span></li>
            <li><span>预防接种</span></li>
            <li><span>精神疾病</span></li>
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
<script src="${base}/js/health_classroom.js"></script>
<script>
    var mySwiper = new Swiper('.swiper-container', {
        autoplay: 3000,
        loop: true,
        pagination : '.swiper-pagination'
    })
</script>
</html>