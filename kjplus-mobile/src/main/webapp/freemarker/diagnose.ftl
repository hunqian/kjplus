<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <title>预约诊疗</title>
    <base id="base" href="${base}">
    <link rel="stylesheet" href="${base}/css/base.css">
    <link rel="stylesheet" href="${base}/css/diagnose.css">
    <link rel="stylesheet" href="${base}/css/calendar.css" />
</head>
<body>
<div class="box">
    <!--header=========================================start-->
    <div class="header">
        <h1><i class="iconfont" onclick="javascript:history.back(-1);">&#xe61f;</i>预约诊疗</h1>
        <div>
            <span><em>本人</em><i class="iconfont">&#xe606;</i></span>
            <ul>
            
            	<li>用户名</li>
            	
            	<#list prsns as p>
            		<li data-code="${p.code!''}">${p.name!''}</li>
            	</#list>
               <#-- <li>本人</li>
                <li>本人本</li>
                <li>本人本人</li>   -->
                <li class="id_add">添加成员</li>
            </ul>
        </div>
    </div>
    <!--header===========================================end-->

    <div class="vaccination_type clearfix">
        <div class="fl left">
            医生
        </div>
        <div class="fr right">
            内科专家 王东
            <i class="iconfont">&#xe504;</i>
        </div>
        <ul>
            <li>内科专家 张三</li>
            <li>妇科专家 李四</li>
            <li>骨科专家 王五</li>
        </ul>
    </div>

    <!--chart==========================================start-->
    <div class="calendar">

        <div class="wrap">
            <div id="calendar">

            </div>
            <button class="btn_l" id="prevMonth"></button>
            <button class="btn_r" id="nextMonth"></button>
        </div>

        <!--<button class="add_activity"></button>-->
    </div>
    <!--chart============================================end-->

    <!--tab============================================start-->
    <!--tab==============================================end-->

    <!--main===========================================start-->
  
    <div class="main">
        <h3>
            <i></i>
            <span>医生 部门名（医生名）</span>
            <em>- 预约年月日 -</em>
        </h3>
        <div class="appoint">
            <p><i style="background-image: url(${base}/imgs/vaccination/select_1.png);"></i><em>预约</em>时间：开始时间-结束时间 已约人数：13</p>
            <p><i style="background-image: url(${base}/imgs/vaccination/select_1.png);"></i><em>预约</em>时间：11:00-12:00 已约人数：13</p>
            <p class="red"><i style="background-image: url(${base}/imgs/vaccination/select_1.png);"></i><em>满</em>时间：11:00-12:00 已约满</p>
        </div>
        <div class="btn_box clearfix">
            <button class="off fl">取&nbsp;消</button>
            <button class="confirm fr">立即预约</button>
        </div>
    </div>
    
    
    <div class="main">
        <h3>
            <i></i>
            <span>医生 内科专家（王东）</span>
            <em>- 2017.09.23 -</em>
        </h3>
        <div class="appoint">
            <p><i style="background-image: url(${base}/imgs/vaccination/select_1.png);"></i><em>预约</em>时间：11:00-12:00 已约人数：13</p>
            <p><i style="background-image: url(${base}/imgs/vaccination/select_1.png);"></i><em>预约</em>时间：11:00-12:00 已约人数：13</p>
            <p class="red"><i style="background-image: url(${base}/imgs/vaccination/select_1.png);"></i><em>满</em>时间：11:00-12:00 已约满</p>
        </div>
        <div class="btn_box clearfix">
            <button class="off fl">取&nbsp;消</button>
            <button class="confirm fr">立即预约</button>
        </div>
    </div>
    
    
    
    <!--main=============================================end-->

    <!--footer=========================================start-->
    <!--footer===========================================end-->

</div>

<div class="alert">
    <div class="alert_box">
        <h3>提示<i class="off">X</i></h3>
        <div class="alert_content">
            <p>姓名：张三</p>
            <p>类别：免疫接种</p>
            <p>预约内容：乙肝疫苗</p>
            <p>预约时间：2017年9月1日 18:00-20:00</p>
        </div>
        <button class="grey confirm">确&nbsp;&nbsp;&nbsp;定</button>
    </div>
</div>
<div class="success">
    <div class="success_box">
        <p>预约成功</p>
    </div>
</div>



</body>
<script src="${base}/js/jqmobi.js"></script>
<script src="${base}/js/calendar.js"></script>
<script src="${base}/js/jquery-1.12.2.min.js"></script>
<script src="${base}/js/diagnose_ftl.js"></script>
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