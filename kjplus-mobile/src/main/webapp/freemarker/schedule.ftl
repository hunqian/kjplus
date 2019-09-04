<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <title>活动日历</title>
    <base id="base" href="${base}">
    <link rel="stylesheet" href="${base}/css/base.css">
    <link rel="stylesheet" href="${base}/css/schedule.css">
    <link rel="stylesheet" href="${base}/css/calendar.css" />
</head>
<body>
<div class="box">
    <!--header=========================================start-->
    <div class="header">
        <h1><i class="iconfont" onclick="javascript:history.back(-1);">&#xe61f;</i>活动日历</h1>
        <div>
        
            <span><em>本人</em><i class="iconfont">&#xe606;</i></span>
            <ul>
            	<#list listperson as p >
                <li id="listcalinfo" value = "${p.id}">${p.name}</li>
                </#list>
                <li class="id_add">添加成员</li>
            </ul>
        </div>
    </div>
    <!--header===========================================end-->

    <!--chart==========================================start-->
    <div class="calendar">

        <div class="wrap">
            <div id="calendar">

            </div>
            <button class="btn_l" id="prevMonth"></button>
            <button class="btn_r" id="nextMonth"></button>
        </div>

        <button class="add_activity"></button>
    </div>
    <!--chart============================================end-->

    <!--tab============================================start-->
    <!--tab==============================================end-->

    <!--main===========================================start-->
    <div class="main">
        <h3>我的活动</h3>
        <div>
        	<#list listcalinfo as calinfo >
	            <p><i class="event"></i>${calinfo.calTitle}</p>
	            <p>${calinfo.startTime}开始时间</p>
	            <p>${calinfo.endTime}结束时间</p>
	            <p><button>立即参与</button></p>
            </#list>
        
        </div>
        
   <!--    <div>
            <p><i class="event"></i>家庭医生提醒你10点00分发红包了</p>
            <p>2017-07-16 17: 00发表</p>
            <p><i class="time"></i>2017-07-16 20:27-21:27</p>
            <p><button>立即参与</button></p>
        </div>    -->
    </div>
    <!--main=============================================end-->

    <!--footer=========================================start-->
    <!--footer===========================================end-->

</div>
</body>
<script src="${base}/js/jqmobi.js"></script>
<script src="${base}/js/calendar.js"></script>
<script src="${base}/js/jquery-1.12.2.min.js"></script>
<script src="${base}/js/schedule.js"></script>
<script>
    (function() {
        calendarIns = new calendar.calendar( {
            count: 1,
            selectDate: new Date(),
            selectDateName: '',
            minDate: new Date(),
            maxDate: new Date( +new Date() + 100 * 86400000 ),
            isShowHoliday: true,
            isShowWeek: true
        } );

        $.bind( calendarIns, 'afterSelectDate', function( event ) {
            console.log( 'after select date' );
            var curItem = event.curItem,
                date = event.date,
                dateName = event.dateName;

            calendarIns.setSelectDate( date );
        } );

        $( '#prevMonth' ).on( 'click', function() {
            calendarIns.prevMonth();
        } );

        $( '#nextMonth' ).on( 'click', function() {
            calendarIns.nextMonth();
        } );
    })();
</script>
</html>