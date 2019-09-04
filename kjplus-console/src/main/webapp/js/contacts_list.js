var base = $("#base").attr("href");
//var contuctsName = null;
ChatContacts = {
    init: function () {
        this.initChatContacts();
        function calendar_date() {
            document.body.appendChild("<script type='text/javascript' src='Include/main.js'></script>"); 
            alert("test!");
            return calendar();
        }
    },
    initChatContacts:function () {
    	var toSessionCode = $("#contantingSessionCode").attr("value");
    	var reqData = {};
    	reqData.toSessionCode = toSessionCode;
    	$.ajax({
			url: base + '/getchatslistjson.html',
			data: reqData,
			type: 'GET',
			dataType: 'json',
			success: function (resp) {
				var chats = resp.data;
				if(chats != "没有相关消息"){
					for(var i=0;i<chats.length;i++){
						if(chats[i].frmSessionCode == toSessionCode){
							 var html = '<div class="goup">';
					            html+='<div class="avatar">';
					            html+='<img src="'+chats[i].frmFace+'" style="width:80px;height:80px;"/>';
					            html+='</div>';
					            html+='<div class="content avatar">';
					            html+='<p class="author_name">'+chats[i].frmNickName+'</p>';
					            html+='<div class="bubble bord">';
					            html+='<div class="bubble_cont avatar">';
					            html+='<div class="before"></div>';
					            html+='<div class="plain">';
					            html+='<pr>'+chats[i].msgBody+'</pr>';
					            html+='</div>';
					            html+='</div>';
					            html+='</div>';
					            html+='</div>';
					            html+='</div>';
					            $('.p2_1_1_1').append(html);
						}else{
							var html1 = '<div class="goup">';
					            html1+='<div class="avatar1">';
						        html1+='<img src="'+chats[i].frmFace+'" style="width:80px;height:80px;"/>';
					            html1+='</div>';
					            html1+='<div class="content avatar1">';
					            html1+='<p class="author_name">'+chats[i].frmNickName+'</p>';
					            html1+='<div class="bubble bord">';
					            html1+='<div class="bubble_cont avatar1">';
					            html1+='<div class="before"></div>';
					            html1+='<div class="plain">';
					            html1+='<pr>'+chats[i].msgBody+'</pr>';
					            html1+='</div>';
					            html1+='</div>';
					            html1+='</div>';
					            html1+='</div>';
					            html1+='</div>';
					            $('.p2_1_1_1').append(html1);
						}
						
					}
				}
			}
		});
    	
		ChatContacts.rebindEvent();
		
    },
	rebindEvent:function(){
		$(".Input_text").html("");
		$("#enter").unbind().click(function (){//发送消息
			var reqData = {};
			var message = $(".Input_text").html();
			if(message == null || message == "")
				return ;
			var contantingSessionCode = $("#contantingSessionCode").attr("value");
			if(contantingSessionCode == null || contantingSessionCode == "")
				return ;
			reqData.message = message;
			reqData.sessionCode = contantingSessionCode;
			$.ajax({
				url: base + '/chatmsg.html',
				data: reqData,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					var msg = resp.msg;
					var html = '<div class="goup">';
		            html+='<div class="avatar1">';
		            html+='<img src="'+msg.frFace+'" style="width:80px;height:80px;"/>';
		            html+='</div>';
		            html+='<div class="content avatar1">';
		            html+='<p class="author_name">'+msg.frName+'</p>';
		            html+='<div class="bubble bord">';
		            html+='<div class="bubble_cont avatar1">';
		            html+='<div class="before"></div>';
		            html+='<div class="plain">';
		            html+='<pr>'+msg.msgInfo+'</pr>';
		            html+='</div>';
		            html+='</div>';
		            html+='</div>';
		            html+='</div>';
		            html+='</div>';
		            $('.p2_1_1_1').append(html);
		            $(".Input_text").html("");
		            $(".Input_text").focus();
		            $('.webq').hide();
				}
			});
			ChatContacts.initLatelyCntacts(contantingSessionCode);
		});
		//联系人列表
		$("[id^='listcontact_']").unbind().click(function (){//发送消息
				var id = $(this).attr('id');
				var sessionCode = id.substring("listcontact_".length);
				$('#profile>div').removeClass('addcolor');
				$(this).addClass('addcolor');
				if(sessionCode != null){
					var nickNameId = sessionCode + "contantNickName";
					var contantingName = $("#"+nickNameId).attr('value');
					$("#contantingNickName").html(contantingName);
					$("#contantingNickName").attr('value',contantingName);
					$("#contantingSessionCode").attr('value',sessionCode);
				}
				
				//获取当前时间
				function p(s) {
				    return s < 10 ? '0' + s: s;
				}
				var myDate = new Date();
				var h=myDate.getHours();       //获取当前小时数(0-23)
				var m=myDate.getMinutes();     //获取当前分钟数(0-59)
				var now=p(h)+':'+p(m);
				$("#curTime").html(now);
				$(".goup").remove();
				ChatContacts.initChatContacts();
				
		});
		//最近联系人列表
		$("[id^='restcontact_']").unbind().click(function (){
			var id = $(this).attr('id');
			var rSessionCode = id.substring("restcontact_".length);
			$('#home>div').removeClass('addcolor');
			$(this).addClass('addcolor');
			if(rSessionCode != null){
				var nickNameId = rSessionCode + "contantNickName";
				var contantingName = $("#"+nickNameId).attr('value');
				$("#contantingNickName").html(contantingName);
				$("#contantingNickName").attr('value',contantingName);
				$("#contantingSessionCode").attr('value',rSessionCode);
			}
			
			//获取当前时间
			function p(s) {
			    return s < 10 ? '0' + s: s;
			}
			var myDate = new Date();
			var h=myDate.getHours();       //获取当前小时数(0-23)
			var m=myDate.getMinutes();     //获取当前分钟数(0-59)
			var now=p(h)+':'+p(m);
			$("#curTime").html(now);
			$(".goup").remove();
			ChatContacts.initChatContacts();
			
	});
	},
	initLatelyCntacts:function(contSessionCode){
		
		var cont = $("#restcontact_"+contSessionCode);
		$("#restcontact_"+contSessionCode).remove();
		$("#home>div:first-child").before(cont);
	}
};

$(function(){
    ChatContacts.init();
});




//聊天表情
var emos = getEmojiList()[0];//此处按需是否生成所有emoji
var html = '<ul style="list-style:none;width:300px;margin:10px 10px !important;">';
for (var j = 0; j < emos.length; j++) {
    var emo = emos[j];
    var data = 'data:image/png;base64,' + emo[2];
    if (j % 20 == 0) {
        html += '<li class="" style="float: left;">';
    } else {
        html += '<li style="float: left;">';
    }
    html += '<img style="display: inline;vertical-align: middle;" src="' + data + '"  unicode16="' + emo[1] + '" title="img'+j+'" /></li>';
}
$('.webq').html(html);
$('.wechat').click(function(){
	$('.webq').toggle();
});
$('.webq ul>li').click(function (){
	var src = $(this).children().attr("src");
	var html1 = '<img style="display: inline;vertical-align: middle;width:20px;" src="'+src+'" />';
	console.log(src);
	
	$(".Input_text").append(html1);
	$('.webq').hide();
	$(".Input_text").focus();
});
