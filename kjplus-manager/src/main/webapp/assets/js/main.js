$(function(){
    var mobile   = /Android|webOS|iPhone|iPad|iPod|BlackBerry/i.test(navigator.userAgent);
    var touchstart = mobile ? "touchstart" : "mousedown";
    var touchend = mobile ? "touchend" : "mouseup";
    var touchmove = mobile ? "touchmove" : "mousemove";
    var Android=false;
    if (/(Android)/i.test(navigator.userAgent)) Android=true;

    // var ws = new WebSocket('ws://localhost:8080');

    //定义图片路径
    var loadingPath='images/';
    //定义聊天图片路径
    var _imgUrl = "images/head/";
    /*获取用户授权信息*/
    var nickname = '';
    var headimgurl = '';
    /*定义群聊信息*/
    var _members,biaoqin,lianjietu,_top,cuntent;
    /*定义群聊滚动距离*/
    var moveNum = 0;

    //
    var Conduct=true;

    //

    // 定义背景音乐播放
    var bgSound = document.getElementById("bgsound");
    var questionSound = document.getElementById("sound");
	// 是否允许选项
    var allowClick = false;
   

 
    // 可调节的参数
    var motionObj = {};
    var manifest=[
        {src:loadingPath+'bg1.jpg'},
       ];

	init();
     // 初始化函数
    function init(){
    	iniListenSound();
    	startLoad();
    	iniBtn();
    	$('html,body').on(touchmove,function(e){
            e.preventDefault();
        });

         initQlInfo();
    };

    function startLoad(){
        var loader = new createjs.LoadQueue(false);
        loader.installPlugin(createjs.Sound);
        //loader.addEventListener("progress", handleOverallProgress);
        //loader.addEventListener("complete", handleOverallComplete);
        loader.loadManifest(manifest);
    }
    //微信端背景音乐播放
    function iniListenSound(){
        document.addEventListener("WeixinJSBridgeReady", function(){
			$('#sound').load();
        }, false);
    }



    // loading
    // function handleOverallProgress(event){
    //     var comnum=Math.ceil(event.loaded*100);
    //     $('.load3').css('width',3*comnum/100+"rem");
    //     $('.loadingtxt').text(Math.ceil(event.loaded*100)+"%");
    // }
    
    // function handleOverallComplete(){
    //     for(var i=1;i<=4;i++){
    //         motionObj["page"+i] = new TimelineMax();
    //     }
    //     initPageMotion();
    // }
    
    // function initPageMotion(){
    //     $(".main").fadeIn(500,function(){
    //         //clearInterval(waver);//清除loading循环
    //          $('.loading').remove();
    //          // motionObj['page'+1].play();
           

    //     });
    // }

    //点击事件
    function iniBtn(){
        $('.p1_8>img').on(touchstart,function(){
            $('.page2').fadeIn(function(){
                qunLiao(0);
            });
        });
       
    }
    /*滚动函数*/
    $.fn.scrollSmooth = function(scrollHeight, duration) {
        var $el = this;
        var el  = $el[0];
        	// console.log($el);
        var startPosition = el.scrollTop;

        var delta = scrollHeight  - startPosition;
        var startTime = Date.now();

        function scroll() {
            var fraction = Math.min(1, (Date.now() - startTime) / duration);
            el.scrollTop = delta*fraction+startPosition;
            //		console.log(el.scrollTop);
            if(fraction < 1) {
                setTimeout(scroll, 10);
            }
        }
        scroll();
    }
    /*群聊天控制*/
    function qunLiao(start){

        var i = start ? start : 0;

        var tempClass = '';
        var bordClass = '';
        var borderClass = '';
        var noneClass = '';
        if(cuntent.liaotian[i].isme)
        {//我说的话
            tempClass = 'avatar1';
            bordClass = 'bord';
        }
        var duihua ="";
        if(cuntent.liaotian[i].type == 'link')
        { //链接
            duihua = '<div class="goup">';
                duihua += '<div class="avatar">'+cuntent.liaotian[i].author.avatar+'</div>';
                    duihua += '<div class="content '+tempClass+'">';
                        duihua += '<p class="author_name">'+cuntent.liaotian[i].author.name+'</p>';
                            duihua += '<div class="bubble">';
                                duihua += '<div class="bubble_cont">';
                                duihua += '<div class="before"></div>';
                            duihua += '<div class="plain"><pre>'+cuntent.liaotian[i].content+'</pre></div>';
                        duihua += '</div>';
                    duihua += '</div>';
                duihua += '</div>';
            duihua += '</div>';
        }
        else if(cuntent.liaotian[i].type == 'img') {
            //表情
            borderClass = 'bor';
            noneClass = 'none';
            duihua = '<div class="goup">';
                duihua += '<div class="avatar '+tempClass+'"">'+cuntent.liaotian[i].author.avatar+'</div>';
                    duihua += '<div class="content '+tempClass+'">';
                        duihua += '<p class="author_name">'+cuntent.liaotian[i].author.name+'</p>';
                            duihua += '<div class="bubble '+borderClass+'">';
                                duihua += '<div class="bubble_cont">';
                                duihua += '<div class="plain '+noneClass+'"><pre>'+cuntent.liaotian[i].content+'</pre></div>';
                            duihua += '</div>';
                    duihua += '</div>';
                duihua += '</div>';
            duihua += '</div>';
        }
        else if(cuntent.liaotian[i].type == 'plain')
        {//文章
            duihua = '<div class="goup">';
                duihua += '<div class="avatar '+tempClass+'">'+cuntent.liaotian[i].author.avatar+'</div>';
                    duihua += '<div class="content '+tempClass+'">';
                        duihua += '<p class="author_name">'+cuntent.liaotian[i].author.name+'</p>';
                            duihua += '<div class="bubble '+bordClass+'">';
                                duihua += '<div class="bubble_cont '+tempClass+'">';
                                duihua += '<div class="before"></div>';
                            duihua += '<div class="plain"><pre>'+cuntent.liaotian[i].content+'</pre></div>';
                        duihua += '</div>';
                    duihua += '</div>';
                duihua += '</div>';
            duihua += '</div>';
        }
        $(".p2_1_1_1").append(duihua);

        // $('.p2_1_1_1').scrollTop( $('.p2_1_1_1')[0].scrollHeight );
        // $('.p2_1').scrollTop=$('.p2_1_1_1').scrollHeight;

        // 自动滚动
        var viewH = $('.p2_1_1').height();
        var contentH = $('.p2_1_1_1').height();
        if (contentH > viewH) {
            moveNum = $('.p2_1_1').scrollSmooth(contentH - viewH + 130,800);
            $('.p2_1_1').scrollTop(moveNum);

         }
         if(i == 6){
             // console.log(121);
             $('.circle,.point').delay(800).fadeIn(function(){
                 $('.p2_2').delay(800).fadeIn(function(){
                     setTimeout(function(){
                         $('.p2_2_3').fadeIn(function(){
                             setTimeout(function(){
                                 $('.p2_2').hide();
                                 // $('.p2_1').show(function(){
                                     // console.log(111);
                                      qunLiao(7);
                                 // });
                             },1500)
                         });
                         $('.p2_2_2,.p2_2_1').fadeOut();
                     },1000);
                 });
             });


        }
        else if(i != cuntent.liaotian.length-1){
            console.log(i);
            setTimeout(function(){
                qunLiao(i+1);
                //sound.play(i+1);
            },cuntent.liaotian[i].pause);
        }
        else{
            $('.hongbaoClick,.point ').on(touchstart,function(){
                // bgsound.play();
                // $('.page1').fadeOut();
            });
        }
    }
    var val = $('#import').val();
    $('#enter').click(function (){
        if($('#import').val()){
            var html = '<div class="goup">';
            html+='<div class="avatar avatar1">';
            html+='<img src="images/head/p1.jpg"/>';
            html+='</div>';
            html+='<div class="content avatar1">';
            html+='<p class="author_name">萝卜君</p>';
            html+='<div class="bubble bord">';
            html+='<div class="bubble_cont avatar1">'
            html+='<div class="before"></div>';
            html+='<div class="plain">';
            html+='<pre>'+$('#import').val()+'</pre>';
            html+='</div>';
            html+='</div>';
            html+='</div>';
            html+='</div>';
            html+='</div>';
            $('.p2_1_1_1').append(html);  
            $('#import').val('');
        } 
        $('#import').focus();
    });
    /*初始化微信群用户信息*/
    function initQlInfo(){
        _members={
            luobojun: {
                id: "lb",
                name: "萝卜君",
                avatar:'<img src="' + _imgUrl + 'p1.jpg">',
            },
            zhang: {
                id: "zhang",
                name: "主持人张绍刚",
                avatar:'<img src="' + _imgUrl + 'p2.jpg">',
            },
            lidan: {
                id: "lidan",
                name: "戒了酒的李诞",
                avatar:'<img src="' + _imgUrl + 'p3.jpg">',
            },
            chizi: {
                id: "chizi",
                name: "池子爱美食",
                avatar:'<img src="' + _imgUrl + 'p4.jpg">',
            },
            pangbo: {
                id: "pangbo",
                name: "野生小鲜肉庞博",
                avatar:'<img src="' + _imgUrl + 'p5.jpg">',
            },
            siwen: {
                id: "siwen",
                name: "野生小鲜肉庞博",
                avatar:'<img src="' + _imgUrl + 'p6.jpg">',
            }
        }
        /*初始化微信群聊天表情*/
        biaoqin = {
            'guzhang':'<img class="biaoqing" src="' + _imgUrl + 'bq1.png">',
            'se':'<img class="biaoqing" src="' + _imgUrl + 'bq2.png">',
        }
        /*初始化微信群聊天文章图片*/
        lianjietu = {
            'luobojun':'<img class="lianjie" src="' + _imgUrl + 'lianjie_1.jpg">',
            'zhang':'<img class="lianjie" src="' + _imgUrl + 'lianjie_2.jpg">',
        }
        /*初始化微信群聊天文章标题及内容*/
        _top = {
            'luobojun':'<div class="top">吐槽大会明星拉锯战，投上你宝贵的一票</div>',
            'luobojun_1':'<div class="top1">谁是最后的胜利者，你来决定</div>',
            'zhang':'<div class="top">新闻：吐槽大会第二期明星反目成仇，只为赢取威马汽车使用权</div>',
            'zhang_1':'<div class="top1">本报讯最新消息，竞争已至白热化，究竟鹿死谁手？</div>',
        }
        /*初始化微信群聊天内容*/
        cuntent = {
            liaotian:[
                {
                    type:"plain",//状态  表情 图片表情 红包 文字  链接
                    author: _members.luobojun,
                    content:"你好，@<span>"+nickname+"</span> ，欢迎你加入威马神吐槽明星群。", //内容  <img src="" />
                    pause: 1500  //1
                },
                {
                    type:"plain",//状态  表情 图片表情 红包 文字  链接
                    author: _members.zhang,
                    content:'欢迎新朋友'+nickname+biaoqin.guzhang, //内容  <img src="" />
                    pause: 1500,   //2
                },
                {
                    type:"plain",//状态  表情 图片表情 红包 文字  链接
                    author: _members.lidan,
                    content:'哎呀邵刚老师不要总是动嘴，来点实际行动啊', //内容  <img src="" />
                    pause: 1500,//3
                },
                {
                    type:"plain",//状态  表情 图片表情 红包 文字  链接
                    author: _members.chizi,
                    content:'为什么我的右手开始颤抖，是因为他闻到了红包的气味吗？', //内容  <img src="" />
                    pause: 1500,//4
                },
                {
                    type:"img",//状态  表情 图片表情 红包 文字  链接
                    author: _members.pangbo,
                    imglink: _imgUrl + 'bq2.png',
                    content:'<img class="imgl" src="' + _imgUrl + 'bq2.png">', //内容  <img src="" />
                    pause: 1500,//5
                },
                {
                    type:"img",//状态  表情 图片表情 红包 文字  链接
                    author: _members.siwen,
                    imglink: _imgUrl + 'bq3.png',
                    content:'<img class="imgl" src="' + _imgUrl + 'bq3.png">', //内容  <img src="" />
                    pause: 1500,//6
                },
                {
                    type:"img",//状态  表情 图片表情 红包 文字  链接
                    author: _members.luobojun,
                    imglink :_imgUrl + 'bq4.png',
                    content:'<img class="hongbaoClick" src="' + _imgUrl + 'bq4.png">'+'<img class="circle" src="' + _imgUrl + 'p1.png">'+'<img class="point" src="' + _imgUrl + 'p2.png">',
                    pause: 2000,//7
                },
                {
                    type:"plain",//状态  表情 图片表情 红包 文字  链接
                    author: _members.luobojun,
                    content:"恭喜张老师，哈哈哈哈", //内容  <img src="" />
                    pause: 2000,   //8
                    isme : true
                },
                {
                    type:"img",//状态  表情 图片表情 红包 文字  链接
                    author: _members.zhang,
                    imglink :_imgUrl + 'bq5.png',
                    content:'<img class="hongbaoClick" src="' + _imgUrl + 'bq5.png">'+'<img class="circle" src="' + _imgUrl + 'p1.png">'+'<img class="point" src="' + _imgUrl + 'p2.png">',
                    pause: 2000,//9
                },
                {
                    type:"link",//状态  表情 图片表情 红包 文字  链接
                    author: _members.luobojun,
                    content:_top.luobojun+_top.luobojun_1+lianjietu.luobojun, //内容  <img src="" />, //内容  <img src="" />
                    pause: 2200   //10
                },
                {
                    type:"link",//状态  表情 图片表情 红包 文字  链接
                    author: _members.zhang,
                    content:_top.zhang+_top.zhang_1+lianjietu.zhang, //内容  <img src="" />, //内容  <img src="" />
                    pause: 2200   //11
                }

            ]
        }
    }


})