<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <title>咨询</title>
    <base id="base" href="${base}">
    <link rel="stylesheet" href="${base}/css/base.css">
    <link rel="stylesheet" href="${base}/css/consult.css">
</head>
<body>
<div class="box">
    <!--header=========================================start-->
    <div class="header">
        <h1><i class="iconfont" onclick="javascript:history.back(-1);">&#xe61f;</i>某某医生-欧阳倩</h1>
    </div>
    <!--header===========================================end-->

    <!--main===========================================start-->
    <div class="main">
        <!--<div class="time">2017/04/09&nbsp;&nbsp;12:10</div>-->
        <div class="talk">
            <div class="time">2017/04/09&nbsp;&nbsp;12:10</div>
            <div class="clearfix my_talk">
                <img src="${base}/imgs/consult/my.png" alt="my" class="fr">
                <div class="fr clearfix">
                    <i class="fr"></i>
                    <p class="fr">医生，您看看我得的是什么病，能看？医生，您看看我得的是什么病，能看？医生，您看看我得的是什么病，能看？</p>
                </div>
            </div>

            <div class="clearfix other_talk">
                <img src="${base}/imgs/consult/doctor.png" alt="doctor" class="fl">
                <div class="fl clearfix">
                    <i class="fl"></i>
                    <p class="fl">某女士，您不用担心，您的病不严重</p>
                </div>
            </div>

            <div class="clearfix other_talk">
                <img src="${base}/imgs/consult/doctor.png" alt="doctor" class="fl">
                <div class="fl clearfix id_img">
                    <i class="fl"></i>
                    <p class="fl"><img src="${base}/imgs/consult/talk_img.png" alt="talk" class="height_1"></p>
                </div>
            </div>

            <div class="clearfix other_talk">
                <img src="${base}/imgs/consult/doctor.png" alt="doctor" class="fl">
                <div class="fl clearfix">
                    <i class="fl"></i>
                    <p class="fl audio_box">
                        <audio src="${base}/audio/LeeSin.mp3" class="audio"></audio>
                        <i class="i_1"></i>
                        <i class="i_2"></i>
                        <i class="i_3"></i>
                        <span>4 s</span>
                        <em></em>
                    </p>
                </div>
            </div>

            <div class="clearfix other_talk">
                <img src="${base}/imgs/consult/doctor.png" alt="doctor" class="fl">
                <div class="fl clearfix">
                    <i class="fl"></i>
                    <p class="fl audio_box">
                        <audio src="${base}/audio/JarvanIV.mp3" class="audio"></audio>
                        <i class="i_1"></i>
                        <i class="i_2"></i>
                        <i class="i_3"></i>
                        <span>5 s</span>
                        <em></em>
                    </p>
                </div>
            </div>

            <div class="clearfix my_talk">
                <img src="${base}/imgs/consult/my.png" alt="doctor" class="fr">
                <div class="fr clearfix">
                    <i class="fr"></i>
                    <p class="fr audio_box">
                        <audio src="${base}/audio/Ahri.mp3" class="audio"></audio>
                        <i class="i_1"></i>
                        <i class="i_2"></i>
                        <i class="i_3"></i>
                        <span>5 s</span>
                        <em></em>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <!--main=============================================end-->

    <!--footer=========================================start-->
    <div class="footer">
        <div class="input_box">
            <i class="character"></i>
            <input type="text" class="text">
            <button class="press_talk" id="pressTalk">按住说话</button>
            <i class="more"></i>
            <button class="send">发送</button>
        </div>
        <div class="photo">
            <ul class="clearfix">
                <li class="fl clearfix"><a href="#" class="fl photograph"></a><span class="fl">相机</span></li>
                <li class="fl clearfix"><a href="#" class="fl image"></a><span class="fl">图片</span></li>
            </ul>
        </div>
    </div>
    <!--footer===========================================end-->

</div>

<!--大图弹框-->
<div class="alert">
       <img src="${base}/imgs/consult/talk_img.png" alt="big">
</div>


</body>
<script src="${base}/js/jquery-1.12.2.min.js"></script>
<script src="${base}/js/touch.js"></script>
<script src="${base}/js/consult.js"></script>
</html>