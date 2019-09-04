<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <base id="base" href="${base}">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <title>家庭医生签约</title>
    <base id="base" href="${base}">
    <link rel="stylesheet" href="${base}/css/base.css">
    <link rel="stylesheet" href="${base}/css/family_doctor_appoint.css">
</head>
<body>
<div class="box">
    <!--header=========================================start-->
    <div class="header">
        <h1><i class="iconfont" onclick="javascript:history.back(-1);">&#xe61f;</i>家庭医生签约</h1>
        <!-- <div>
            <span><em>本人</em><i class="iconfont">&#xe606;</i></span>
            <ul>
                <li>本人</li>
                <li>本人本</li>
                <li>本人本人</li>
                <li class="id_add">添加成员</li>
            </ul>
        </div> -->
    </div>
    <!--header===========================================end-->

    <!--inhabitant=====================================start-->
    <div class="inhabitant_box">
        <div class="img">
            <img src="${base}/imgs/family_doctor_appoint/appoint_1.png" alt="#">
        </div>
        <#--
        <div class="inhabitant">
            <p class="clearfix">
                <span class="fl">签约居民</span>
                <em class="fr">欧阳娜（妹妹）</em>
            </p>
            <p class="clearfix">
                <span class="fl">家庭医生团队</span>
                <em class="fr">雷柏特住医师院</em>
            </p>
            <p class="clearfix">
                <span class="fl">家庭医生</span>
                <em class="fr">医生1</em>
            </p>
        </div>  --> 
        <#if assign??>
       	 <div class="inhabitant">
            <p class="clearfix">
                <span class="fl">签约居民</span>
                <em class="fr" id="prsnName" data-prsnName="${assign.prsnName}" data-prsnCode="${assign.prsnCode}">${assign.prsnName}（${assign.relationTypeName}）</em>
            </p>
            <p class="clearfix">
                <span class="fl">家庭医生团队</span>
                <em class="fr" id="deptName" data-deptCode="${assign.deptCode}">${assign.deptName}</em>
            </p>
            <p class="clearfix">
                <span class="fl">家庭医生</span>
                <em class="fr" id="staffName" data-staffCode="${assign.staffCode}">${assign.staffName}</em>
            </p>
        </div>
       </#if> 
        
     </div>
    <!--inhabitant=======================================end-->

    <!--main===========================================start-->
    <div class="main">
        <h3>家庭医生服务介绍</h3>
      
		  
		  <#--
        	<#list servHeadList as shl>
	        	<#list shl.srvHeads as sh>
	        		<div>
			            <h4><span style="background: #16df78">01</span>${sh.title!''}</h4>
			            <p>${sh.contentBody!''}</p>
			        </div>
			        <p><i style="background-image: url(${base}/imgs/family_doctor_appoint/appoint_3.png);"></i></p>
			    </#list>
		    </#list> -->
		    
		<div>
            <h4><span style="background: #16df78">01</span>协议总则</h4>
            <p>协议总则内容协议总则内容协议总则内容协议总则内容协议总则内容协议总则内容协议总则内容协议总则内容协议总则内容协议总则内容协议总则内容协议总则内容协议总则内容协议总则内容协议总则内容协议总则内容协议总则内容协议总则内容协议总则内容协议总则内容协议总则内容协议总则内容协议总则内容协议总则内容协议总则内容协议总则内容协议总则内容协议总则内容协议总则内容协议总则内容</p>
        </div>
        <div>
            <h4><span style="background: #50d2c2">02</span>健康信息早知道</h4>
            <p>健康信息早知道内容健康信息早知道内容健康信息早知道内容健康信息早知道内容健康信息早知道内容健康信息早知道内容健康信息早知道内容健康信息早知道内容健康信息早知道内容</p>
        </div>
        <div>
            <h4><span style="background: #fcab53">03</span>安全防护</h4>
            <p>安全防护内容安全防护内容安全防护内容安全防护内容安全防护内容安全防护内容安全防护内容安全防护内容</p>
        </div>        
        
    </div>
    <!--main=============================================end-->

    <!--footer=========================================start-->
    <div class="footer">
        <div class="appoint">
	  		
	  		<#--  <#list servCatList as sc>
		        <#if sc.isDefault??> 
	            	<#if "Y" == sc.isDefault>  
		    			<p><i style="background-image: url(${base}/imgs/family_doctor_appoint/appoint_2.png);"></i><span id="serviceName" data-service-code="${sc.code}">${sc.name}</span></p>
		    		<#else>  
					    <p><i style="background-image: url(${base}/imgs/family_doctor_appoint/appoint_3.png);"></i><span data-service-code="${sc.code}">${sc.name}</span></p>
	                </#if>
	            </#if>
	        </#list>  
	  		<p><i style="background-image: url(./imgs/family_doctor_appoint/appoint_3.png);"></i>居民服务包名（居民服务包简称）</p>
	  		-->
	  		
			<#if listPackageData??>
				<#list listPackageData as sc>
		        <#if sc.isDefault??> 
	            	<#if "Y" == sc.isDefault>  
		    			<p><i  data-selected="selected" style="background-image: url(${base}/imgs/family_doctor_appoint/appoint_2.png);"></i><span id="serviceName" data-service-code="${sc.code!''}"  data-service-name="${sc.name!''}" data-service-memo="${sc.memo!''}" data-service-alias="${sc.alias!''}">${sc.name!''}</span></p>
		    		<#else>  
					    <p><i  data-selected="unselected"  style="background-image: url(${base}/imgs/family_doctor_appoint/appoint_3.png);"></i><span data-service-code="${sc.code!''}" data-service-name="${sc.name!''}" data-service-memo="${sc.memo!''}" data-service-alias="${sc.alias!''}">${sc.name!''}</span></p>
	                </#if>
	            </#if>
	        	</#list> 	  		
	  		</#if>
	  		<#--
	        <p><i style="background-image: url(./imgs/family_doctor_appoint/appoint_3.png);"></i>签约家庭医生基础服务（A）</p>
            <p><i style="background-image: url(./imgs/family_doctor_appoint/appoint_2.png);"></i>签约家庭医生金牌服务（B）</p>
            <p><i style="background-image: url(./imgs/family_doctor_appoint/appoint_3.png);"></i>签约家庭医生XX服务（C）</p>
            -->
        </div>
        <div class="btn_box clearfix">
            <button class="off fl">取&nbsp;消</button>
            <button class="confirm fr">立即签约</button>
        </div>
    </div>
    <!--footer===========================================end-->

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
        <p>签约成功</p>
    </div>
</div>

</body>
<script src="${base}/js/jquery-1.12.2.min.js"></script>
<script src="${base}/js/family_doctor_appoint.js"></script>
</html>