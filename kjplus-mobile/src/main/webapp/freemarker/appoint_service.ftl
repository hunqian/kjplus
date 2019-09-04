<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <title>预约服务</title>
    <base id="base" href="${base}">
    <link rel="stylesheet" href="${base}/css/base.css">
    <link rel="stylesheet" href="${base}/css/swiper.min.css">
    <link rel="stylesheet" href="${base}/css/appoint_service.css">
</head>
<body>
<div class="box">
    <!--header=========================================start-->
    <div class="header">
        <h1><i class="iconfont" onclick="javascript:history.back(-1);">&#xe61f;</i>预约服务</h1>
    </div>
    <!--header===========================================end-->

    <!--top=========================================start-->
    <div class="top">
        设置预约
    </div>
    <!--top==========================================end-->

    <!--tab============================================start-->
    <div class="tab tab-container">
        <div class="swiper-wrapper tab_view">
            <div class="swiper-slide">
                <ul class="clearfix">
                    <li><a href="${base}/vaccination.html"><img src="${base}/imgs/index/filing_service.png" alt=""><span>预约接种</span></a></li>
                    <li><a href="${base}/diagnose.html"><img src="${base}/imgs/index/family_doctor.png" alt=""><span>预约诊疗</span></a></li>
                    <li><a href="${base}/check.html"><img src="${base}/imgs/index/vaccine.png" alt=""><span>预约体验</span></a></li>
                    <li><a href="${base}/schedule.html"><img src="${base}/imgs/index/reserve_service.png" alt=""><span>预约活动</span></a></li>
                </ul>
            </div>
            <!--<div class="swiper-slide">slider2</div>-->
            <!--<div class="swiper-slide">slider3</div>-->
        </div>
    </div>
    <!--tab==============================================end-->

    <!--main===========================================start-->
    <div class="main">
        <h3><span>预约记录</span></h3>
        <div class="content">
			
			  <div>
                <h4>user关系者 <span>预约年月日</span></h4>
                <p>全部预约简述 <span>预约时间</span></p>
                <button class="green">变更预约</button>
                <button class="off">取消预约</button>
                <i class="green_border"></i>
            </div>
            
            <#list appInfoList as u>
            	<div>
	                <h4>${u.name!''}-${u.relationTypeName!''}<span>${u.day!''}</span></h4>
	                <p>${u.memo!''}<span>${u.time!''}</span></p>
	                <button class="green" data-code="${u.appointCode!''}">变更预约</button>
	                <button class="off" data-code="${u.appointCode!''}">取消预约</button>
	                <i class="green_border"></i>
           		</div>  
            </#list> 
            
            <#--
             <div>
                <h4>张晓晓-女儿 <span>17-07-16</span></h4>
                <p>注射百白破疫苗，老年人体检，健康讲堂，老年人体检，健康讲堂 <span>18:00</span></p>
                <button class="green">变更预约</button>
                <button class="off">取消预约</button>
                <i class="green_border"></i>
            </div>
            
            <div>
                <h4>张老太-母亲 <span>17-07-16</span></h4>
                <p>注射百白破疫苗，老年人体检，健康讲堂 <span>18:00</span></p>
                <button class="green">变更预约</button>
                <button class="off">取消预约</button>
                <i></i>
            </div>
            
            <div>
                <h4>张军军-儿子 <span>17-07-16</span></h4>
                <p>注射百白破疫苗，老年人体检，健康讲堂 <span>18:00</span></p>
                <button class="green">变更预约</button>
                <button class="off">取消预约</button>
                <i></i>
            </div>
             -->
        </div>
    </div>
    <!--提示1-->
	<div class="alert">
	    <div class="alert_box">
	        <h3>提示<i class="off">X</i></h3>
	        <div class="alert_content">
	            <p>申请人：张三</p>
	            <p>签约团队：XXX</p>
	            <p>所选服务包：XXX</p>
	            <p>请耐心等待医生的审核，感谢您的支持与选择！</p>
	        </div>
	        <button class="grey confirm">确&nbsp;&nbsp;&nbsp;定</button>
	    </div>
	</div>
	<div class="success">
	    <div class="success_box">
	        <p>预约成功</p>
	    </div>
	</div>
	
    <!--main=============================================end-->

    <!--footer=========================================start-->
    <!--footer===========================================end-->

</div>
</body>
<script src="${base}/js/jquery-1.12.2.min.js"></script>
<script src="${base}/js/swiper.jquery.min.js"></script>
<script src="${base}/js/appoint_service.js"></script>
<script>
    var mySwiper = new Swiper('.swiper-container', {
        autoplay: 3000,
        loop: true
    })
    var myTab = new Swiper('.tab-container', {
        autoplay: 0,
        resistanceRatio: 0
    })
</script>
</html>