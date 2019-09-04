$(function(){
    //创建WebSocket对象
<<<<<<< .mine
    var url = "ws://localhost:8808/kjplus-console/ws/msgsocket";
=======
    var url = "ws://localhost:8080/kjplus-console/ws/msgsocket";
>>>>>>> .r12460

    var ws = "";
    $.fn.createws = function(){
        if('WebSocket' in window){
            ws = new WebSocket(url);
        }
        else if('MozWebSocket' in window){
            ws = new MozWebSocket(url);
        }
        ws.onopen = function(){
            console.log("已经打开了websocket连接，可以进行实时通信了");
        };
        ws.onmessage = function(e){
        	console.log("接受到来自服务器端的数据:"+e.data);
			var info=eval('('+e.data+')');
			 var html = '<div class="goup">';
	            html+='<div class="avatar">';
	            html+='<img src="'+info.frFace+'"/>';
	            html+='</div>';
	            html+='<div class="content avatar">';
	            html+='<p class="author_name">'+info.frName+'</p>';
	            html+='<div class="bubble bord">';
	            html+='<div class="bubble_cont avatar">';
	            html+='<div class="before"></div>';
	            html+='<div class="plain">';
	            html+='<pr>'+info.msgInfo+'</pr>';
	            html+='</div>';
	            html+='</div>';
	            html+='</div>';
	            html+='</div>';
	            html+='</div>';
	            $('.p2_1_1_1').append(html);
	            $('#import').val('');
	            $('#import').focus();
        };
        ws.onerror = function(e){
            console.log("websocket连接错误"+e.error);
            ws.close(3000,"websocket连接异常导致的关闭");
        };
        ws.onclose = function(e){
            console.log("websocket关闭连接:"+e.error);
        };
    };
    $.fn.createws();
});