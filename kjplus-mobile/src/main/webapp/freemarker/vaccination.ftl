<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <title>预防接种</title>
    <base id="base" href="${base}">
    <link rel="stylesheet" href="${base}/css/base.css">
    <link rel="stylesheet" href="${base}/css/vaccination.css">
    <link rel="stylesheet" href="${base}/css/calendar.css" />
</head>
<body>
<div class="box">
    <!--header=========================================start-->
    <div class="header">
   		 <#--  变更预约 cancel-code 用于变更预约  -->
        <h1><i class="iconfont" onclick="javascript:history.back(-1);" cancel-code="${cancelAppointCode!''}" >&#xe61f;</i>预防接种</h1>
        <div>
            <span><em personCode="">本人</em><i class="iconfont">&#xe606;</i></span>
            <ul>
            	<#if persons??>
            		<#list persons as up>
			    		<li personCode="${up.code}">${up.name}</li>
			    	</#list>  
			    </#if>
                <li class="id_add">添加成员</li>
            </ul>
        </div>
    </div>
    <!--header===========================================end-->
	
    <div class="vaccination_type clearfix">
        <div class="fl left">
            接种项目
        </div>
        <div class="fr right">
             -请选择-
            <i class="iconfont">&#xe504;</i>
        </div>
        <ul>
        	<li>全部</li>
           <#-- <li>麻疹疫苗麻疹疫苗</li>
            <li>风疹疫苗</li>   -->
            <#if vacs??>
            		<#list vacs as vac>
			    		<li typeCode="${vac.code}">${vac.name}</li>
			    	</#list>  
			</#if>
            
        <#--	<#list mains as m>
	        	<#list m.sons as ms>
			    	<li data-code="${ms.srvCode}">${ms.srvName}</li>
			    </#list>	
		    </#list>   -->
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
            <span>预约接种(全部项目)</span>
            <em>- 年月日 -</em>
        </h3>
        <div class="appoint">
           <#--  <p><i style="background-image: url(${base}/imgs/vaccination/select_1.png);"></i><em>预约</em>时间：2017-12-11 11:00- 2017-12-11 12:00 已约人数：13</p>
            <p><i style="background-image: url(${base}/imgs/vaccination/select_1.png);"></i><em>预约</em>时间：2017-12-12 11:00- 2017-12-12 12:00 已约人数：13</p> 
            <p class="red"><i style="background-image: url(${base}/imgs/vaccination/select_1.png);"></i><em>满</em>时间：2017-12-10 11:00 - 2017-12-10 12:00 已约满</p> -->
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

<#-- 预约接种提示	-->
<div class="alert">
    <div class="alert_box">
        <h3>提示<i class="off">X</i></h3>
        <div class="alert_content">
            <p>姓名：张三</p>
            <p>类别：免疫接种</p>
            <p>预约内容：乙肝疫苗</p>
            <p>预约时间：2017年9月1日 18:00-20:00</p>
        </div>
        <button class="green confirm">确&nbsp;&nbsp;&nbsp;定</button>
    </div>
</div>
<#-- 预约成功后数据显示	-->
<div class="success">
    <div class="success_box">
        <p>预约成功</p>
    </div>
</div>
<!--提示框-->
<div class="alert_2">
    <div class="alert_box">
        <h3>提示</h3>
        <div class="alert_content">
            <p>请选择预约活动</p>
        </div>
        <button class="green">确&nbsp;&nbsp;&nbsp;定</button>
    </div>
</div>


</body>
<script src="${base}/js/jqmobi.js"></script>
<script src="${base}/js/calendar.js"></script>
<script src="${base}/js/jquery-1.12.2.min.js"></script>
<script src="${base}/js/vaccination.js"></script>
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
		$("#calendar ul.day li").on( 'click', function() {
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