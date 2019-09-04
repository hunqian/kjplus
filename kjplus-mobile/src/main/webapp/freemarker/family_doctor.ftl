<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <base id="base" href="${base}">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <title>家庭医生</title>
    <link rel="stylesheet" href="${base}/css/base.css">
    <link rel="stylesheet" href="${base}/css/swiper.min.css">
    <link rel="stylesheet" href="${base}/css/family_doctor.css">
</head>
<body>
<div class="box">
    <!--header=========================================start-->
    <div class="header">
        <h1><i class="iconfont" onclick="javascript:history.back(-1);">&#xe61f;</i>家庭医生</h1>
        <!-- <div>
            <span><em>本人</em><i class="iconfont">&#xe606;</i></span>
            <ul>
                <li>本人</li>
                <li>本人本</li>
                <li>本人本人</li>
                <li class="id_add">添加成员</li>
            </ul>
        </div>
        -->
    </div>
    <!--header===========================================end-->

    <!--swiper=========================================start-->
    <div class="swiper swiper-container">
        <div class="swiper-wrapper swiper_view">
            <div class="swiper-slide"><img src="${base}/imgs/family_doctor/swiper_1.png" alt=""></div>
            <div class="swiper-slide"><img src="${base}/imgs/family_doctor/swiper_1.png" alt=""></div>
            <div class="swiper-slide"><img src="${base}/imgs/family_doctor/swiper_1.png" alt=""></div>
        </div>
        <div class="swiper-pagination"></div>
    </div>
    <!--swiper===========================================end-->

    <!--main===========================================start-->
    <div class="main">
        <!--家庭成员签约-->
        <div class="family_box">
        <#--
        	<div class="family clearfix">
                <img src="./imgs/family_doctor/portrait_1.png" alt="#" class="fl">
                <div class="fl">
                    <h4>用户名<span>关系</span></h4>
                    <p>签约医生  姓名  服务包全称（简称）</p>
                </div>
                <button class="id_go_consult">立即咨询(待审核/去签约/拒签)</button>
            </div>
            -->
            <#if listPrsnData??>
        		<#list listPrsnData as prsn>
        		  <div class="family clearfix">
                	<img src="${prsn.url!''}" alt="头像" class="fl">
                	<div class="fl">
                    	<h4 data-prsnCode="${prsn.prsnCode!''}" data-relation="${prsn.relationTypeName!''}">${prsn.name!''}<span>${prsn.relationTypeName}</span>
                    	 	 <#if prsn.srvStatus??> 
				                   <#if "S" == prsn.srvStatus!'' >  
							  			<span class="grey">审核中</span>
					               </#if>
				             </#if>
                    	</h4>
                    	
                    	<#if prsn.srvStatus??> 
					           <#if "Y" == prsn.srvStatus >  
									<p>
									   	签约医生  ${prsn.staffName!''} ${prsn.srvPackageName!''}(${prsn.srvPackageAlias!''})
									</p>
							   <#elseif "S" == prsn.srvStatus!'' >  
									 <p>
									 	签约医生  ${prsn.staffName!''}  ${prsn.srvPackageName!''}(${prsn.srvPackageAlias!''})
									 </p>
							   <#elseif "R" == prsn.srvStatus!'' >  
									 <p>
									 	签约医生  ${prsn.staffName!''}  ${prsn.srvPackageName!''}(${prsn.srvPackageAlias!''})
									 </p>		 
								<#else>  
								      <p>该家庭成员尚未签约</p>
						</#if> 
						<#else>  
							<p>该家庭成员尚未签约</p>	
						</#if> 
                	</div>
                  	<#if prsn.srvStatus??> 
					        <#if "Y" == prsn.srvStatus>  
								    <button class="id_go_consult">立即咨询</button>
							<#elseif "S" == prsn.srvStatus>  
									<button class="grey" disabled="disabled">立即咨询</button>
							<#elseif "R" == prsn.srvStatus>  
									<button class="grey" disabled="disabled">拒签</button>		
							<#else>  
									<button class="id_go_assign">去签约</button>
							</#if> 
					<#else>  
						<button class="id_go_assign">去签约</button>
					</#if>   	
            	</div>
        	</#list>
        	</#if>
        	
        </div>
        <!--医生团队-->
        <div class="team_box">
            <div class="team_top">
                	本区家庭医生团队
            </div>
			
		<#--	<div class="team">
                <div>
                    <h4 class="clearfix">
                        <i class="fl"></i>
                        <span class="fl">医生名</span>
                        <img src="./imgs/family_doctor/portrait_4.png" alt="医生头像" class="fr">
                    </h4>
                    <p class="clearfix">
                        <span class="fl">科室</span>
                        <span class="fl">医生类型</span>
                        <button class="fr id_select_team" style="background-color: #0bd16b; margin-right: 0.3125rem; color: #fff;" select='true'>已选择</button>
                        <button class="fr id_select_team">选择团队</button>
                    </p>
                </div>
            </div>  -->
            <#if listDocData??>
            <#list listDocData as doc>
            	<div class="team">
                	<div>
                    	<h4 class="clearfix">
                        	<i class="fl"></i>
                        	<span class="fl">${doc.staffName!''}(${doc.gDeptName!''})</span>
                        	<img src="${doc.url!''}" alt="头像" class="fr">
                    	</h4>
                    	<p class="clearfix" data-deptCode="${doc.gDeptCode!''}" data-staffCode="${doc.staffCode!''}" >
                        	<span class="fl">${doc.deptName!''}</span>
                        	<span class="fl">${doc.typeName!''}</span>
                        	<#-- <button class="fr id_select_team" style="background-color: #0bd16b; margin-right: 0.3125rem; color: #fff;" select='true'>已选择</button> -->
                        	<button class="fr id_select_team">选择团队</button>
                    	</p>
                	</div>
          	  	</div>
            </#list>	
           </#if> 
            
     <#-- 	 <div class="team">
                <div>
                    <h4 class="clearfix">
                        <i class="fl"></i>
                        <span class="fl">Meeting with Janet</span>
                        <img src="./imgs/family_doctor/portrait_4.png" alt="#" class="fr">
                    </h4>
                    <p class="clearfix">
                        <span class="fl">内科</span>
                        <span class="fl">医院副院长</span>
                        <!--<button class="fr id_select_team" style="background-color: #0bd16b; margin-right: 0.3125rem; color: #fff;" select='true'>已选择</button>
                        <button class="fr id_select_team">选择团队</button>
                    </p>
                </div>
            </div>
            <div class="team">
                <div>
                    <h4 class="clearfix">
                        <i class="fl"></i>
                        <span class="fl">李明智</span>
                        <img src="./imgs/family_doctor/portrait_5.png" alt="#" class="fr">
                    </h4>
                    <p class="clearfix">
                        <span class="fl">内科</span>
                        <span class="fl">医院院长</span>
                        <button class="fr id_select_team">选择团队</button>
                    </p>
                </div>
            </div>  -->	
			
				
            </div>
        </div>
    </div>
    <!--main=============================================end-->
</div>

<!--提示2-->
<div class="alert_6">
    <div class="alert_box">
        <h3>提示</h3>
        <div class="alert_content">
            <p>请选择家庭医生团队</p>
        </div>
        <button class="grey">确&nbsp;&nbsp;&nbsp;定</button>
    </div>
</div>

</body>
<script src="${base}/js/jquery-1.12.2.min.js"></script>
<script src="${base}/js/less.min.js"></script>
<script src="${base}/js/swiper.jquery.min.js"></script>
<script src="${base}/js/family_doctor.js"></script>
<script>
    var mySwiper = new Swiper('.swiper-container', {
        autoplay: 3000,
        loop: true,
        pagination : '.swiper-pagination'
    })
</script>
</html>