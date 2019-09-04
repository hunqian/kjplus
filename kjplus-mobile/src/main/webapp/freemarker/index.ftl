<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <title>首页</title>
    <base id="base" href="${base}">
    <link rel="stylesheet" href="${base}/css/base.css">
    <link rel="stylesheet" href="${base}/css/swiper.min.css">
    <link rel="stylesheet" href="${base}/css/index.css">
</head>
<body>
    <div class="box">
        <!--header=========================================start-->
        <div class="header">
            <h1>首页</h1>
        </div>
        <!--header===========================================end-->

        <!--swiper=========================================start-->
        <div class="swiper swiper-container">
            <div class="swiper-wrapper swiper_view">
                <div class="swiper-slide"><img src="${base}/imgs/index/swiper_1.png" alt=""></div>
                <div class="swiper-slide"><img src="${base}/imgs/index/swiper_1.png" alt=""></div>
                <div class="swiper-slide"><img src="${base}/imgs/index/swiper_1.png" alt=""></div>
            </div>
            <div class="swiper-pagination"></div>
        </div>
        <!--swiper===========================================end-->

        <!--tab============================================start-->
        <div class="tab tab-container">
            <div class="swiper-wrapper tab_view">
                <div class="swiper-slide">
                    <ul class="clearfix">
                        <li><a href="${base}/myfamily.html"><img src="${base}/imgs/index/filing_service.png" alt=""><span>档案管理</span></a></li>
                        <li><a href="${base}/family_doctor.html"><img src="${base}/imgs/index/family_doctor.png" alt=""><span>家庭医生</span></a></li>
                        <li><a href="${base}/inoculation.html"><img src="${base}/imgs/index/vaccine.png" alt=""><span>预防接种</span></a></li>
                        <li><a href="${base}/appoint_service.html"><img src="${base}/imgs/index/reserve_service.png" alt=""><span>预约服务</span></a></li>
                        <li><a href="${base}/schedule.html"><img src="${base}/imgs/index/activity_calendar.png" alt=""><span>活动日历</span></a></li>
                        <li><a href="${base}/cn_doctor_service.html"><img src="${base}/imgs/index/cn_doctor.png" alt=""><span>中医服务</span></a></li>
                        <li><a href="${base}/drug_service.html"><img src="${base}/imgs/index/drug_service.png" alt=""><span>药事服务</span></a></li>
                        <li><a href="${base}/health_tool.html"><img src="${base}/imgs/index/health_tool.png" alt=""><span>健康工具</span></a></li>
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
                <h3 class="clearfix">健康资讯<a href="${base}/health_information.html" class="fr">更多>></a></h3>
                <div class="banner">
                    <img src="${base}/imgs/index/banner_1.png" alt="banner1">
                </div>
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
            <!--健康讲堂-->
            <div class="classroom">
                <h3 class="clearfix">健康讲堂<a href="${base}/health_classroom.html" class="fr">更多>></a></h3>
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
        <div class="footer clearfix">
            <div style="color: rgb(11, 209, 107);" name="index">
                <span><i class="iconfont">&#xe620;</i></span>
                <span>首页</span>
            </div>
            <div style="color: rgb(149, 149, 149);" name="myhealth">
                <span><i class="iconfont">&#xe679;</i></span>
                <span>我的健康</span>
            </div>
            <div style="color: rgb(149, 149, 149);" name="mycenter">
                <span><i class="iconfont">&#xe652;</i></span>
                <span>个人中心</span>
            </div>
        </div>
        <!--footer===========================================end-->

    </div>
</body>
<script src="${base}/js/jquery-1.12.2.min.js"></script>
<script src="${base}/js/less.min.js"></script>
<script src="${base}/js/swiper.jquery.min.js"></script>
<script src="${base}/js/index.js"></script>
<script>
    var mySwiper = new Swiper('.swiper-container', {
        autoplay: 3000,
        loop: true,
        pagination : '.swiper-pagination',
//        paginationType : 'fraction'
    })
    var myTab = new Swiper('.tab-container', {
        autoplay: 0,
        resistanceRatio: 0
    })
</script>
</html>