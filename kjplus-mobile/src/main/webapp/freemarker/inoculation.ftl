<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
     <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <title>预防接种</title>
    <link rel="stylesheet" href="${base}/css/base.css">
    <link rel="stylesheet" href="${base}/css/swiper.min.css">
    <link rel="stylesheet" href="${base}/css/inoculation.css">
</head>
<body>
<div class="box">
    <!--header=========================================start-->
    <div class="header">
        <h1><i class="iconfont" onclick="javascript:history.back(-1);">&#xe61f;</i>预防接种</h1>
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
            <a href="${base}/vaccination.html"><span class="fl family" style="border-right: 0.125rem solid #e6e6e6;"><i></i>预约接种</span></a>
            <a href="${base}/appoint_service.html"><span class="fl score"><i></i>预约记录</span></a>
        </div>
    </div>
    <!--tab==============================================end-->

    <!--main===========================================start-->
    <div class="main">
        <!--内容须知-->
        <div class="message">
            <h3 class="clearfix">内容须知</h3>
            <div class="news_box">
                <div class="news clearfix">
                    <img src="${base}/imgs/index/banner_2.png" alt="banner2" class="fl">
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
                    <img src="${base}/imgs/index/banner_3.png" alt="banner3" class="fl">
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
        <!--相关咨询-->
        <div class="classroom">
            <h3 class="clearfix">相关咨询</h3>
            <div class="news">
                <p>震惊！生活中你不注意的小细节可能给你造成不可预测的伤害</p>
                <ul class="clearfix">
                    <li style="margin-left: 0"><img src="${base}/imgs/index/banner_4.png" alt="banner4"></li>
                    <li><img src="${base}/imgs/index/banner_5.png" alt="banner5"></li>
                    <li><img src="${base}/imgs/index/banner_6.png" alt="banner6"></li>
                </ul>
            </div>
            <div class="news">
                <p>震惊！生活中你不注意的小细节可能给你造成不可预测的伤害</p>
                <ul class="clearfix">
                    <li style="margin-left: 0"><img src="${base}/imgs/index/banner_7.png" alt="banner7"></li>
                    <li><img src="${base}/imgs/index/banner_8.png" alt="banner8"></li>
                    <li><img src="${base}/imgs/index/banner_9.png" alt="banner9"></li>
                </ul>
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
<script src="${base}/js/inoculation.js"></script>
<script>
    var mySwiper = new Swiper('.swiper-container', {
        autoplay: 3000,
        loop: true,
        pagination : '.swiper-pagination'
    })
</script>
</html>