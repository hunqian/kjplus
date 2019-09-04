<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <title>我的积分</title>
    <base id="base" href="${base}">
    <link rel="stylesheet" href="${base}/css/base.css">
    <link rel="stylesheet" href="${base}/css/myscore.css">
</head>
<body>
<div class="box">
    <!--header=========================================start-->
    <div class="header">
        <h1><i class="iconfont" onclick="javascript:history.back(-1);">&#xe61f;</i>我的积分</h1>
    </div>
    <!--header===========================================end-->
    <div class="myscore">
        <span>当前积分：</span>
        <em>${sum!0}</em>
    </div>
    <!--tab============================================start-->
    <div class="tab clearfix">
        <div class="fl">
            <span class="score">我的积分</span>
        </div>
        <div class="fl">
            <span class="expense">兑奖记录</span>
        </div>
    </div>
    <!--tab==============================================end-->

    <!--main===========================================start-->
    <div class="main">
        <div class="score_box">
           
           	<div class="clearfix">
	            <span class="fl">
	                <b>获得积分缘由</b>
	                <small>年月日</small>
	            </span>
	                <em class="fr">积分数</em>
            </div>
            <#list listIncrData as incr>
            	<div class="clearfix">
		            <span class="fl">
		                <b>${incr.memo!'增加'}</b>
		                <small>${incr.day!''}</small>
		            </span>
		                <em class="fr">${incr.point!''}</em>
		        </div>
           </#list>
            
            
            <!-- <div class="clearfix">
            <span class="fl">
                <b>每日登陆</b>
                <small>2017-07-26</small>
            </span>
                <em class="fr">+1</em>
            </div>
            <div class="clearfix">
            <span class="fl">
                <b>每日登陆</b>
                <small>2017-07-26</small>
            </span>
                <em class="fr">+1</em>
            </div>
            <div class="clearfix">
            <span class="fl">
                <b>每日登陆</b>
                <small>2017-07-26</small>
            </span>
                <em class="fr">+1</em>
            </div>
            <div class="clearfix">
            <span class="fl">
                <b>每日登陆</b>
                <small>2017-07-26</small>
            </span>
                <em class="fr">+1</em>
            </div>
            <div class="clearfix">
            <span class="fl">
                <b>每日登陆</b>
                <small>2017-07-26</small>
            </span>
                <em class="fr">+1</em>
            </div>  -->
        </div>
        <div class="expense_box">
        
          	<div class="clearfix">
	            <span class="fl">
	                <b>积分消费缘由</b>
	                <small>年月日</small>
	            </span>
	                <em class="fr">积分数目</em>
	        </div>
            
            <#list listDecrData as decr>
	            <div class="clearfix">
		            <span class="fl">
		                <b>${decr.memo!'减少'}</b>
		                <small>${decr.day!''}</small>
		            </span>
		                <em class="fr">${decr.point!''}</em>
		        </div>
            </#list>
            
        <!--    <div class="clearfix">
            <span class="fl">
                <b>兑换</b>
                <small>2017-07-26</small>
            </span>
                <em class="fr">-1</em>
            </div>
            <div class="clearfix">
            <span class="fl">
                <b>兑换</b>
                <small>2017-07-26</small>
            </span>
                <em class="fr">-1</em>
            </div>
            <div class="clearfix">
            <span class="fl">
                <b>兑换</b>
                <small>2017-07-26</small>
            </span>
                <em class="fr">-1</em>
            </div>
            <div class="clearfix">
            <span class="fl">
                <b>兑换</b>
                <small>2017-07-26</small>
            </span>
                <em class="fr">-1</em>
            </div>
            <div class="clearfix">
            <span class="fl">
                <b>兑换</b>
                <small>2017-07-26</small>
            </span>
                <em class="fr">-1</em>
            </div>  -->
        </div>
    </div>
    <!--main=============================================end-->

    <!--footer=========================================start-->
    <!--footer===========================================end-->

</div>
</body>
<script src="${base}/js/jquery-1.12.2.min.js"></script>
<script src="${base}/js/myscore.js"></script>
</html>