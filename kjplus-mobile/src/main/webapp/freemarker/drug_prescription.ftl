<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <title>药品处方</title>
    <base id="base" href="${base}">
    <link rel="stylesheet" href="${base}/css/base.css">
    <link rel="stylesheet" href="${base}/css/drug_prescription.css">
</head>
<body>
<div class="box">
    <!--header=========================================start-->
    <div class="header">
        <h1><i class="iconfont" onclick="javascript:history.back(-1);">&#xe61f;</i>药品处方</h1>
    </div>
    <!--header===========================================end-->

    <!--main===========================================start-->
     <div class="main">
         <div class="drug_box">
            <div class="top">
                <div class="doctor clearfix">
                    <img src="${base}/imgs/drug_prescription/doctor.png" alt="#" class="fl">
                    <span class="fl">欧阳娜娜医生</span>
                    <span class="fr">2017-03-29</span>
                </div>
                <div class="drug">
                    <p class="drug_name">氨咖黄敏胶囊（伤风胶囊）100g</p>
                    <p class="drug_dose">一日两次&nbsp;&nbsp;一次2g</p>
                </div>
                <div class="drug">
                    <p class="drug_name">甲硝唑芬布芬 50g</p>
                    <p class="drug_dose">一日两次&nbsp;&nbsp;一次2g</p>
                </div>
            </div>
            <div class="bottom clearfix">
                <button class="fl " style="border-right: 0.0625rem solid #e6e6e6">设置提醒</button>
                <button class="fr">查看配送</button>
            </div>
        </div>
         <div class="drug_box">
             <div class="top">
                 <div class="doctor clearfix">
                     <img src="${base}/imgs/drug_prescription/doctor.png" alt="#" class="fl">
                     <span class="fl">欧阳娜娜医生</span>
                     <span class="fr">2017-03-29</span>
                 </div>
                 <div class="drug">
                     <p class="drug_name">氨咖黄敏胶囊（伤风胶囊）100g</p>
                     <p class="drug_dose">一日两次&nbsp;&nbsp;一次2g</p>
                 </div>
                 <div class="drug">
                     <p class="drug_name">甲硝唑芬布芬 50g</p>
                     <p class="drug_dose">一日两次&nbsp;&nbsp;一次2g</p>
                 </div>
             </div>
             <div class="bottom clearfix">
                 <button class="fl " style="border-right: 0.0625rem solid #e6e6e6">设置提醒</button>
                 <button class="fr">查看配送</button>
             </div>
         </div>
         <div class="drug_box">
             <div class="top">
                 <div class="doctor clearfix">
                     <img src="${base}/imgs/drug_prescription/doctor.png" alt="#" class="fl">
                     <span class="fl">欧阳娜娜医生</span>
                     <span class="fr">2017-03-29</span>
                 </div>
                 <div class="drug">
                     <p class="drug_name">氨咖黄敏胶囊（伤风胶囊）100g</p>
                     <p class="drug_dose">一日两次&nbsp;&nbsp;一次2g</p>
                 </div>
                 <div class="drug">
                     <p class="drug_name">甲硝唑芬布芬 50g</p>
                     <p class="drug_dose">一日两次&nbsp;&nbsp;一次2g</p>
                 </div>
             </div>
             <div class="bottom clearfix">
                 <button class="fl " style="border-right: 0.0625rem solid #e6e6e6">设置提醒</button>
                 <button class="fr">查看配送</button>
             </div>
         </div>
     </div>
    <!--main=============================================end-->

    <!--footer=========================================start-->
    <!--footer===========================================end-->

</div>
</body>
<script src="${base}/js/jquery-1.12.2.min.js"></script>
<script src="${base}/js/drug_prescription.js"></script>
</html>