<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <base id="base" href="${base}">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <title>建档服务</title>
    <link rel="stylesheet" href="${base}/css/base.css">
    <link href="${base}/css/mobiscroll_002.css" rel="stylesheet" type="text/css">
    <link href="${base}/css/mobiscroll.css" rel="stylesheet" type="text/css">
    <link href="${base}/css/mobiscroll_003.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${base}/css/create_document.css">
</head>
<body>
<div class="box">
    <!--header=========================================start-->
    <div class="header">
        <h1><i class="iconfont" onclick="javascript:history.back(-1);">&#xe61f;</i><span class="id_save" id="save-docinfo-btn">保存</span>建档服务</h1>
    </div>
    <!--header===========================================end-->

    <!--portrait=======================================start-->
    <div class="portrait">
        <img src="./imgs/create_document/portrait.png" alt="portrait">
        <p>你哼着不同的调调儿 <i class="woman"></i></p>
    </div>
    <!--portrait======================================== end-->

    <!--tab===========================================start-->
    <div class="tab clearfix">
        <div class="fl green id_base">基本信息</div>
        <div class="fl id_detailed">详细信息</div>
    </div>
    <!--tab============================================end-->

    <!--main===========================================start-->
    <div class="main">
        <!--基本信息-->
        <div class="data id_base_data">
            <div class="clearfix">
                <span class="fl">姓名</span>
                <input type="text" placeholder="请输入" class="fr id_name" id="dataline_0" >
            </div>
            <div class="clearfix ">
                <span class="fl">性别</span>
                <em class="fr id_sex" id="dataline_1_1"><i class="iconfont">&#xe687;</i></em>
            </div>
            <div class="clearfix">
                <span class="fl">生日</span>
                <em class="fr clearfix">
                    <i class="iconfont fr">&#xe687;</i>
                    <input type="text" placeholder="1990-01-01" class="fr my_date" readonly="readonly" id="dataline_1_2" >
                </em>
            </div>
            <div class="clearfix">
                <span class="fl">手机号码</span>
                <input type="number" placeholder="未设置" class="fr" id="dataline_3_1">
            </div>
            <div class="clearfix">
                <span class="fl">身份证号</span>
                <input type="text" placeholder="未设置" class="fr " maxlength="18" id="dataline_2_1" >
            </div>
            <div class="clearfix">
                <span class="fl">家庭住址</span>
                <input type="text" placeholder="未设置" class="fr id_site" id="dataline_0_1">
            </div>
            <div class="disease_history">
                <div class="clearfix">
                    <span class="fl">既往病史</span>
                    <em class="fr clearfix id_add"><i class="add fr" id="s_dataline_12_1"></i></em>
                </div>
            </div>
            <div class="physical_history" id="append_15">
	            <div class="clearfix">
	                <span class="fl">残疾情况</span>
	                <input type="text" class="fr clearfix id_physical_add" readonly="readonly" placeholder="请选择" id="dataline_15" >
	            </div>
	         </div>
             <div class="pregnant_date">
                 <div class="clearfix">
                    <span class="fl">孕产情况（暂无）</span>
                    <em class="fr id_pregnant_date">无/孕期/产褥期<i class="iconfont">&#xe687;</i></em>
                    <!--<em class="fr id_pregnant_date"><b>产褥期</b><input type="text" value="2017-09-08" class=""><i class="iconfont ">&#xe687;</i></em>-->
                </div>  
            </div>  
        </div>

        <!--详细信息-->
        <div class="data id_detailed_data" style="display: none">
            <div class="clearfix id_health_tab">
                <span class="fl">健康信息</span>
                <em class="fr"><i class="iconfont">&#xe687;</i></em>
            </div>
            <div class="clearfix id_live_tab">
                <span class="fl">行为信息</span>
                <em class="fr"><i class="iconfont">&#xe687;</i></em>
            </div>
            <div class="clearfix id_other_tab">
                <span class="fl">其他信息</span>
                <em class="fr"><i class="iconfont">&#xe687;</i></em>
            </div>
        </div>
    </div>
    <!--main=============================================end-->

    <!--footer=========================================start-->
    <!--footer===========================================end-->

</div>

<!--健康信息列表-->
<div class="health_box">
    <div class="header">
        <h1><span class="id_health_finish">完成</span>健康信息</h1>
    </div>
    <div class="health_main">
        <div class="clearfix">
            <span class="fl">血型</span>
           <!-- <input type="text" class="fr id_bloodType_add" readonly="readonly" placeholder="请选择" id="dataline_5"> -->
            <em class="fr id_sex" id="dataline_5">请选择</em>
        </div>
        <div class="health_disease_history id_operation_history" id="append_12_2">
            <div class="clearfix">
                <span class="fl">手术史</span>
                <em class="fr clearfix id_operation_add"><i class="add fr" placeholder="请选择" id="dataline_12_2"></i></em>
            </div>
        </div>
        <div class="health_disease_history id_trauma_history">
            <div class="clearfix">
                <span class="fl">外伤史</span>
                <em class="fr clearfix id_trauma_add"><i class="add fr" placeholder="请选择" id="dataline_12_3"></i></em>
            </div>
        </div>
        <div class="health_disease_history id_blood_history">
            <div class="clearfix">
                <span class="fl">输血史</span>
                <em class="fr clearfix id_blood_add"><i class="add fr" placeholder="请选择" id = "dataline_12_4" ></i></em>
            </div>
        </div>
        <div class="clearfix">
            <span class="fl">暴露史</span>
            <input type="text" class="fr id_expose_add" readonly="readonly" placeholder="请选择" id = "dataline_111111">
        </div>
        <div class="family_history clearfix">
            <div class="text fl">家族史</div>
            <div class="fr clearfix">
                <span class="fl">父亲</span>
                <input type="text" class="fr id_father_add" readonly="readonly" placeholder="请选择" id = "dataline_13_1_1">
            </div>
            <div class="fr clearfix">
                <span class="fl">母亲</span>
                <input type="text" class="fr id_mother_add" readonly="readonly" placeholder="请选择" id = "dataline_13_1_2">
            </div>
            <div class="fr clearfix">
                <span class="fl">兄弟姐妹</span>
                <input type="text" class="fr id_brother_add" readonly="readonly" placeholder="请选择" id = "dataline_13_2_1">
            </div>
            <div class="fr clearfix">
                <span class="fl">子女</span>
                <input type="text" class="fr id_child_add" readonly="readonly" placeholder="请选择" id = "dataline_13_2_2">
            </div>
        </div>
        <div class="clearfix">
            <span class="fl">遗传病史</span>
            <input type="text" class="fr" placeholder="请选择" id = "dataline_14">
        </div>
        <!--<div class="clearfix">-->
            <!--<span class="fl">残疾情况</span>-->
            <!--<input type="text" class="fr id_disabled_add" readonly="readonly" placeholder="请选择" id = "dataline_15">-->
        <!--</div>-->
        <div class="clearfix">
            <span class="fl">药物过敏史</span>
            <input type="text" class="fr id_allergy_add" readonly="readonly" placeholder="请选择" id = "dataline_10">
        </div>
    </div>
</div>
<!--行为信息列表-->
<div class="live_box">
    <div class="header">
        <h1><span class="id_live_finish">完成</span>行为信息</h1>
    </div>
    <div class="live_main">
        <div class="clearfix">
            <span class="fl">饮食习惯</span>
            <input type="text" class="fr id_eating_add" readonly="readonly" placeholder="请选择" id = "dataline_16_1_3">
        </div>
        <div class="live_habits clearfix id_exercise">
            <div class="text fl">体育锻炼</div>
            <div class="fr clearfix">
                <span class="fl">锻炼频率</span>
                <input type="text" class="fr id_exercise_add" placeholder="请选择" id = "dataline_16_1_3">
            </div>
            <div class="fr clearfix">
                <span class="fl">每次锻炼时间</span>
                <span class="fr">分钟</span>
                <input type="number" class="fr" placeholder="请输入" style="width: 40%" id = "dataline_16_1_2_1">
            </div>
            <div class="fr clearfix">
                <span class="fl">坚持锻炼时间</span>
                <span class="fr">年</span>
                <input type="number" class="fr" placeholder="请输入" style="width: 40%" id = "dataline_16_1_2_2">
            </div>
            <div class="fr clearfix">
                <span class="fl">锻炼方式</span>
                <input type="text" class="fr" placeholder="请输入" id = "dataline_16_1_3">
            </div>
        </div>
        <div class="live_habits clearfix smoke id_smoke">
            <div class="text fl">吸烟情况</div>
            <div class="fr clearfix">
                <span class="fl">吸烟状况</span>
                <input type="text" class="fr id_smoke_add" readonly="readonly" placeholder="请选择" id = "dataline_16_3_1">
            </div>
            <div class="fr clearfix">
                <span class="fl">日吸烟量</span>
                <span class="fr">支</span>
                <input type="number" class="fr" placeholder="请输入" style="width: 40%" id = "dataline_16_3_2">
            </div>
            <div class="fr clearfix">
                <span class="fl">开始吸烟年龄</span>
                <span class="fr">岁</span>
                <input type="number" class="fr" placeholder="请输入" style="width: 40%" id = "dataline_16_3_3_1">
            </div>
            <div class="fr clearfix">
                <span class="fl">戒烟年龄</span>
                <span class="fr">岁</span>
                <input type="number" class="fr" placeholder="请输入" style="width: 40%" id = "dataline_16_3_3_2"> 
            </div>
        </div>
        <div class="live_habits clearfix wine id_wine">
            <div class="text fl">饮酒情况</div>
            <div class="fr clearfix">
                <span class="fl">饮酒频率</span>
                <input type="text" class="fr id_wine_add" readonly="readonly" placeholder="请选择" id = "dataline_16_4_1">
            </div>
            <div class="fr clearfix">
                <span class="fl">日饮酒量</span>
                <span class="fr">两</span>
                <input type="number" class="fr" placeholder="请输入" style="width: 40%" id = "dataline_16_4_2">
            </div>
            <!--<div class="fr clearfix">-->
                <!--<span class="fl">是否戒酒</span>-->
                <!--<input type="text" class="fr" readonly="readonly" placeholder="请选择" id = "dataline_16_4_3">-->
            <!--</div>-->
            <div class="fr clearfix">
                <span class="fl">饮酒种类</span>
                <input type="text" class="fr id_wineType_add" readonly="readonly" placeholder="请选择" id = "dataline_16_4_5">
            </div>
            <div class="fr clearfix">
                <span class="fl">近一年内是否曾醉酒</span>
                <input type="text" class="fr id_drunk_add" readonly="readonly" placeholder="请选择" style="width: 30%" id = "dataline_16_4_4_2">
            </div>
            <div class="fr clearfix">
                <span class="fl">开始饮酒年龄</span>
                <span class="fr">岁</span>
                <input type="number" class="fr" placeholder="请输入" style="width: 40%" id = "dataline_16_4_4_1">
            </div>
            <div class="fr clearfix">
                <span class="fl">戒酒年龄</span>
                <span class="fr">岁</span>
                <input type="number" class="fr" placeholder="请输入" style="width: 40%" >
            </div>
        </div>
    </div>
</div>
<!--其他信息列表-->
<div class="other_box">
    <div class="header">
        <h1><span class="id_other_finish">完成</span>其他信息</h1>
    </div>
    <div class="other_main">
        <div class="clearfix">
            <span class="fl">工作单位</span>
            <input type="text" class="fr" placeholder="请输入" id = "dataline_2_2">
        </div>
        <div class="clearfix">
            <span class="fl">职业</span>
            <input type="text" class="fr id_job_add" readonly="readonly" placeholder="请选择" id = "dataline_7">
        </div>
        <div class="clearfix">
            <span class="fl">其他联系人姓名</span>
            <input type="text" class="fr" placeholder="请输入" style="width: 40%" id = "dataline_3_2"> 
        </div>
        <div class="clearfix">
            <span class="fl">其他联系人电话</span>
            <input type="number" class="fr" placeholder="请输入" style="width: 40%" id = "dataline_3_3">
        </div>
        <div class="clearfix">
            <span class="fl">常住类型</span>
            <input type="text" class="fr id_habitancy_add" readonly="readonly" placeholder="请选择" id = "dataline_4_1">
        </div>
        <div class="clearfix">
            <span class="fl">民族</span>
            <input type="text" class="fr" placeholder="请输入" id = "dataline_4_2">
        </div>
        <div class="clearfix">
            <span class="fl">文化程度</span>
            <input type="text" class="fr id_education_add" readonly="readonly" placeholder="请选择" id = "dataline_6">
        </div>
        <div class="clearfix">
            <span class="fl">婚姻状况</span>
            <input type="text" class="fr id_marriage_add" readonly="readonly" placeholder="请选择" id = "dataline_8">
        </div>
        <div class="clearfix">
            <span class="fl">医疗费用支付方式</span>
            <input type="text" class="fr id_pay_add" readonly="readonly" placeholder="请选择" style="width: 55%" id = "dataline_9">
        </div>
        <div class="clearfix">
            <span class="fl">厨房排风设施</span>
            <input type="text" class="fr id_exhaust_add" readonly="readonly" placeholder="请选择" id = "dataline_17_1">
        </div>
        <div class="clearfix">
            <span class="fl">燃料类型</span>
            <input type="text" class="fr id_fuel_add" readonly="readonly" placeholder="请选择" id = "dataline_17_2">
        </div>
        <div class="clearfix">
            <span class="fl">饮水</span>
            <input type="text" class="fr id_water_add" readonly="readonly" placeholder="请选择" id = "dataline_17_3"> 
        </div>
        <div class="clearfix">
            <span class="fl">厕所</span>
            <input type="text" class="fr id_toilet_add" readonly="readonly" placeholder="请选择" id = "dataline_17_4">
        </div>
        <div class="clearfix">
            <span class="fl">禽畜栏</span>
            <input type="text" class="fr id_livestock_add" readonly="readonly" placeholder="请选择" id = "dataline_17_5">
        </div>

    </div>
</div> 

<!--提示框-->
<div class="alert_message">
    <div class="alert_box">
        <h3>提示</h3>
        <div class="alert_content">
            <p>提示内容</p>
        </div>
        <button class="green">确&nbsp;&nbsp;&nbsp;定</button>
    </div>
</div>

<!-- 单选汇总  -->
<!--选择性别弹框-->
<div class="alert" id="cfgline_1_1_dialog">
    <div class="alert_box">
        <h3>选择性别<i class="off">X</i></h3>
        <div class="alert_content">
        </div>
    </div>
</div>
<!--TODO  选择血型弹框-->
<div class="alert" id="cfgline_5_dialog">
    <div class="alert_box">
        <h3>选择血型<i class="off">X</i></h3>
        <div class="alert_content">
        </div>
    </div>
</div>
<!--TODO  选择血型弹框-->
<div class="alert" id="cfgline_5_dialog">
    <div class="alert_box">
        <h3>选择血型<i class="off">X</i></h3>
        <div class="alert_content">
        </div>
    </div>
</div>


<!--添加既往病史    多选有输入 -->
<div class="alert_1" id="s_cfgline_dialog_12_1">
    <div class="alert_box">
        <h3>添加既往病史<i class="off">X</i></h3>
        <div class="alert_content">
         </div>  
        <button class="green id_add_disease">立即添加</button>
    </div>
</div>

<!--添加残疾情况-->
<div class="alert_11" id="cfgline_dialog_15">
    <div class="alert_box">
        <h3>添加残疾情况<i class="off">X</i></h3>
        <div class="alert_content">
         </div>  
        <button class="green id_add_physical" id="cfgline_dialog_add_15">立即添加</button>
    </div>
</div>

<!--添加暴露史-->
<div class="alert_2" id="cfgline_11_dialog">
    <div class="alert_box">
        <h3>添加暴露史<i class="off">X</i></h3>
        <div class="alert_content">
         </div>  
        <button class="green id_add_disease" >立即添加</button>
    </div>
</div>

<!--添加孕产期-->
<div class="alert_3">
    <div class="alert_box">
        <h3>添加孕产情况<i class="off">X</i></h3>
        <div class="alert_content">
            <div class="clearfix" id="noPregnant">
                <i class="fl select"></i>
                <span class="fl">无</span>
            </div>
            <div class="clearfix" id="yunQi">
                <i class="fl"></i>
                <span class="fl">孕期</span>
            </div>
            <div class="clearfix" id="chanRuQi">
                <i class="fl"></i>
                <span class="fl">产褥期</span>
            </div>
        </div>
        <button class="green">立即添加</button>
    </div>
</div>
<!--保存基本信息确认-->
<div class="alert_4">
    <div class="alert_box">
        <h3>基本信息确认<i class="off">X</i></h3>
        <div class="alert_content">
            <p class="clearfix"><span class="fl left">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</span><span class="fr right">张三</span></p>
            <p class="clearfix"><span class="fl left">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</span><span class="fr right">男</span></p>
            <p class="clearfix"><span class="fl left">生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日：</span><span class="fr right">2017-09-02</span></p>
            <p class="clearfix"><span class="fl left">手&nbsp;&nbsp;机&nbsp;&nbsp;号：</span><span class="fr right">15910771011</span></p>
            <p class="clearfix"><span class="fl left">身份证号：</span><span class="fr right">130888888888888888</span></p>
            <p class="clearfix"><span class="fl left">家庭住址：</span><span class="fr right">中关村创业大厦中关村创业大厦中关村创业大厦中关村创业大厦</span></p>
            <p class="clearfix"><span class="fl left">既往病史：</span><span class="fr right">1.高血压（2017-09-02）<br>2.心脏病（2017-09-02）<br>3.老年痴呆症（2017-09-02）<br></span></p>
            <p class="clearfix"><span class="fl left">孕产情况：</span><span class="fr right">产褥期（2017-09-02）</span></p>
        </div>
        <button class="green">确&nbsp;&nbsp;&nbsp;定</button>
    </div>
</div>
<!--选择血型弹框-->
<div class="alert_5"></div>
<!--添加手术史-->
<div class="alert_6">
    <div class="alert_box">
        <h3>添加手术史<i class="off">X</i></h3>
        <div class="alert_content">
            <div class="clearfix id_select id_no">
                <i class="fl select"></i>
                <span class="fl">无</span>
            </div>
            <div class="operation_box" id="operationBox">
                <div class="clearfix other id_select" id="operation">
                    <i class="fl"></i>
                    <span class="fl">手术史</span>
                    <button class="fr"></button>
                </div>
                <div class="other_input clearfix" style="background-color: #f6f6f6; display: none;">
                    <!--<s></s>-->
                    <input type="text" class="fl" placeholder="请输入" id="operationInupt">
                </div>
            </div>
        </div>
        <button class="green id_operation_btn">立即添加</button>
    </div>
</div>
<!--添加外伤史-->
<div class="alert_7">
    <div class="alert_box">
        <h3>添加外伤史<i class="off">X</i></h3>
        <div class="alert_content">
            <div class="clearfix id_select id_no">
                <i class="fl select"></i>
                <span class="fl">无</span>
            </div>
            <div class="trauma_box" id="traumaBox">
                <div class="clearfix other id_select" id="trauma">
                    <i class="fl"></i>
                    <span class="fl">外伤史</span>
                    <button class="fr"></button>
                </div>
                <div class="other_input clearfix" style="background-color: #f6f6f6; display: none;">
                    <!--<s></s>-->
                    <input type="text" class="fl" placeholder="请输入" id="traumaInupt">
                </div>
            </div>
        </div>
        <button class="green id_trauma_btn">立即添加</button>
    </div>
</div>
<!--添加输血史-->
<div class="alert_8">
    <div class="alert_box">
        <h3>添加输血史<i class="off">X</i></h3>
        <div class="alert_content">
            <div class="clearfix id_select id_no">
                <i class="fl select"></i>
                <span class="fl">无</span>
            </div>
            <div class="blood_box" id="bloodBox">
                <div class="clearfix other id_select" id="blood">
                    <i class="fl"></i>
                    <span class="fl">输血史</span>
                    <button class="fr"></button>
                </div>
                <div class="other_input clearfix" style="background-color: #f6f6f6; display: none;">
                    <!--<s></s>-->
                    <input type="text" class="fl" placeholder="请输入" id="bloodInupt">
                </div>
            </div>
        </div>
        <button class="green id_blood_btn">立即添加</button>
    </div>
</div>

</body>
<script src="${base}/js/jquery-1.12.2.min.js"></script>
<script src="${base}/js/mobiscroll_002.js" type="text/javascript"></script>
<script src="${base}/js/mobiscroll_004.js" type="text/javascript"></script>
<script src="${base}/js/mobiscroll.js" type="text/javascript"></script>
<script src="${base}/js/mobiscroll_003.js" type="text/javascript"></script>
<script src="${base}/js/mobiscroll_005.js" type="text/javascript"></script>
<script src="${base}/js/create_document_ftl.js"></script>
</html>