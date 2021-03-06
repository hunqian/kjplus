<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <title>我的健康</title>
    <base id="base" href="${base}">
    <link rel="stylesheet" href="${base}/css/base.css">
    <link rel="stylesheet" href="${base}/css/swiper.min.css">
    <link rel="stylesheet" href="${base}/css/myhealth.css">
</head>
<body>
<div class="box">
    <!--header=========================================start-->
    <div class="header">
        <h1>我的健康</h1>
        <div>
            <span><em>本人</em><i class="iconfont">&#xe606;</i></span>
            <ul>
                <#if listData??>
                	<#list listData as data>
                		<li data-prsnCode="${data.prsnCode}">${data.name}</li>
                	</#list>
                </#if>
                <li class="id_add">添加成员</li>
            </ul>
        </div>
    </div>
    <!--header===========================================end-->

    <!--chart==========================================start-->
    <div class="chart">

    </div>
    <!--chart============================================end-->

    <!--tab============================================start-->
    <div class="tab tab-container">
        <div class="swiper-wrapper tab_view">
            <div class="swiper-slide">
                <ul class="clearfix">
                    <li><a href="${base}/create_document.html"><img src="${base}/imgs/myhealth/tab_1.png" alt=""><span>基本信息</span></a></li>
                    <li><a href="${base}/measure_record_1.html"><img src="${base}/imgs/myhealth/tab_2.png" alt=""><span>随访访视</span></a></li>
                    <li><a href="${base}/measure_record_2.html"><img src="${base}/imgs/myhealth/tab_3.png" alt=""><span>体检报告</span></a></li>
                    <li><a href="${base}/measure_record.html"><img src="${base}/imgs/myhealth/tab_4.png" alt=""><span>测量记录</span></a></li>
                    <li><a href="${base}/measure_record_3.html"><img src="${base}/imgs/myhealth/tab_5.png" alt=""><span>诊疗记录</span></a></li>
                    <li><a href="${base}/referral.html"><img src="${base}/imgs/myhealth/tab_6.png" alt=""><span>转诊记录</span></a></li>
                </ul>
            </div>
            <!--<div class="swiper-slide">slider2</div>-->
            <!--<div class="swiper-slide">slider3</div>-->
        </div>
    </div>
    <!--tab==============================================end-->

    <!--main===========================================start-->
    <div class="main">
        <div class="message">
            <h3>消息提醒</h3>
            <div>
                <div class="appoint clearfix">
                    <i class="fl"></i>
                    <div class="fr">
                        <h4>预约</h4>
                        <p>当前暂无信息</p>
                        <em>2</em>
                    </div>
                </div>
                <div class="appoint drug clearfix">
                    <i class="fl"></i>
                    <div class="fr">
                        <h4>用药信息</h4>
                        <p>当前暂无信息</p>
                        <!--<em>2</em>-->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--main=============================================end-->

    <!--footer=========================================start-->
    <div class="footer clearfix">
        <div style="color: rgb(149, 149, 149);" name="index">
            <span><i class="iconfont">&#xe620;</i></span>
            <span>首页</span>
        </div>
        <div style="color: rgb(11, 209, 107);" name="myhealth">
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
<script src="${base}/js/swiper.jquery.min.js"></script>
<script src="${base}/js/myhealth_ftl.js"></script>
<script>
    var myTab = new Swiper('.tab-container', {
        autoplay: 0,
        resistanceRatio: 0
    })
</script>
</html>