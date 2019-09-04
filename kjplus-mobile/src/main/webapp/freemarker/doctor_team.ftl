<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <title>家庭医生团队</title>
    <base id="base" href="${base}">
    <link rel="stylesheet" href="${base}/css/base.css">
    <link rel="stylesheet" href="${base}/css/doctor_team.css">
</head>
<body>
<div class="box">
    <!--header=========================================start-->
    <div class="header">
        <h1><i class="iconfont" onclick="javascript:history.back(-1);">&#xe61f;</i>家庭医生团队</h1>
        <!--<div>-->
            <!--<span><em>本人</em><i class="iconfont">&#xe606;</i></span>-->
            <!--<ul>-->
                <!--<li>本人</li>-->
                <!--<li>本人本</li>-->
                <!--<li>本人本人</li>-->
                <!--<li class="id_add">添加成员</li>-->
            <!--</ul>-->
        <!--</div>-->
    </div>
    <!--header===========================================end-->

    <!--swiper=========================================start-->
    <!--swiper===========================================end-->

    <!--main===========================================start-->
    <div class="main">
        <div class="doctor_box">

			<#list staffList as s>
				<div class="doctor_team">
                <img src="${base}/imgs/doctor_team/doctor_1.png" alt="#">
                <div>
                    <h4>${s.name}</h4>
                    <span>${s.orgName}</span>
                    <span>${s.staffDeptName}</span>
                    <p>${s.memo}</p>
                </div>
            </div>
			</#list>
        </div>
        <div class="service">
            <h3>
                <span>服务区域和服务内容</span>
            </h3>
            <div>
                <p class="clearfix">
                    <span class="fl">服务区域：</span>
                    <em class="fl">回龙观龙腾苑一区 - 龙腾苑九区</em>
                </p>
                <p class="clearfix">
                    <span class="fl">服务内容：</span>
                    <em class="fl">
	                    <#list staffList as s>
	                    	<#list s.depts as d>
	                    		${d.deptName}
	                    	</#list>
	                    </#list>
                    </em>
                    <!-- <em class="fl">老年人、孕产妇、儿童、高血压、糖尿病、精神病、肺结核、残疾人体检</em> -->
                </p>
            </div>
        </div>
    </div>
    <!--main=============================================end-->

    <!--footer============================================start-->
        <!--<div class="footer">-->
            <!--<button>立即签约</button>-->
        <!--</div>-->
    <!--footer=============================================end-->

</div>

<!--<div class="alert">-->
<!--<div class="alert_box">-->
<!--<h3>提&nbsp;&nbsp;&nbsp;示</h3>-->
<!--<p>是否确定取消</p>-->
<!--<div class="clearfix">-->
<!--<button class="off fl">取消</button>-->
<!--<button class="confirm fr">确定</button>-->
<!--</div>-->
<!--</div>-->
<!--</div>-->
<!--<div class="success">-->
<!--<div class="success_box">
    <!--<p>签约成功</p>-->
<!--</div>-->
<!--</div>-->

</body>
<script src="${base}/js/jquery-1.12.2.min.js"></script>
<script src="${base}/js/less.min.js"></script>
<script src="${base}/js/doctor_team.js"></script>
</html>