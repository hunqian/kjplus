<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <title>随访访视</title>
    <base id="base" href="${base}">
    <link rel="stylesheet" href="${base}/css/base.css">
    <link rel="stylesheet" href="${base}/css/measure_record.css">
</head>
<body>
<div class="box">
    <!--header=========================================start-->
    <div class="header">
        <h1><i class="iconfont" onclick="javascript:history.back(-1);">&#xe61f;</i>随访访视</h1>
    </div>
    <!--header===========================================end-->

    <!--main===========================================start-->
    <div class="main">
    
    	<div class="record clearfix">
            <img src="${base}/imgs/measure_record/img_1.png" alt="1" class="fl">
            <div class="name_box fl">
                <span class="name">用户名</span>
                <span>项目：随访表格名</span>
            </div>
            <div class="time_box fr">
                <span class="date">年月日</span>
                <span>具体时间</span>
            </div>
        </div>
    
    	<#list listData as f>
			<div class="record clearfix">
            	<img src="${f.url!''}" alt="1" class="fl">
            	<div class="name_box fl">
                	<span class="name">${f.name!''}</span>
                	<span>项目：${f.memo!'未注明'}</span>
            	</div>
            	<div class="time_box fr">
                	<span class="date">${f.day!''}</span>
                	<span>${f.time!''}</span>
           		</div>
       	 	</div>
		</#list>
    	
    <#--	
        <div class="record clearfix">
            <img src="${base}/imgs/measure_record/img_1.png" alt="1" class="fl">
            <div class="name_box fl">
                <span class="name">刘月英-妻子</span>
                <span>项目：血压测量</span>
            </div>
            <div class="time_box fr">
                <span class="date">2017-06-23</span>
                <span>19:30</span>
            </div>
        </div>
        
        <div class="record clearfix">
            <img src="${base}/imgs/measure_record/img_2.png" alt="2" class="fl">
            <div class="name_box fl">
                <span class="name">欧阳倩-女儿</span>
                <span>项目：血压测量</span>
            </div>
            <div class="time_box fr">
                <span class="date">2017-06-23</span>
                <span>19:30</span>
            </div>
        </div>
        <div class="record clearfix">
            <img src="${base}/imgs/measure_record/img_3.png" alt="3" class="fl">
            <div class="name_box fl">
                <span class="name">李曼诗-母亲</span>
                <span>项目：老年人血压测量</span>
            </div>
            <div class="time_box fr">
                <span class="date">2017-06-23</span>
                <span>19:30</span>
            </div>
        </div>  -->
    </div>
    <!--main=============================================end-->

    <!--footer=========================================start-->
    <!--footer===========================================end-->

</div>
</body>
<script src="${base}/js/jquery-1.12.2.min.js"></script>
<script src="${base}/js/measure_record.js"></script>
</html>