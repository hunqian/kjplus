var base = $("#base").attr("href");

WxAccInfo = {
init : function() {
		this.initInfo();
		this.bindEvent();
},
	initInfo : function(){
		console.log("1");
		$(".main-content").css('width', '100%');
		
	},bindEvent : function() {
		$("#btnsave").unbind().click(function() {
			WxAccInfo.editWxAccInfo();
		});
	},
	editWxAccInfo : function() {
		var id = $("#th_id").val();
		var account = $("#th_account").val();
		var name = $("#th_nickname").val();
		var token = $("#th_token").val();
		var encoaseskeyding = $("#th_encoaseskeyding").val();
		var appid = $("#th_appid").val();
		var introduction = $("#th_introduce").val();
		var appsecret = $("#th_appsecret").val();
		var url = $("#th_url").val();
		var type = $('#th_typeid option:selected').attr("value");
		var mode = $('#th_modeid option:selected').attr("value");
		var reqData = {};
		reqData.id = id;
		reqData.account = account;
		reqData.name = name;
		reqData.introduction = introduction;
		reqData.appid = appid;
		reqData.url = url;
		reqData.type = type;
		reqData.appsecret = appsecret;
		reqData.token = token;
		reqData.encoaseskeyding = encoaseskeyding;
		reqData.mode = mode;
		$.ajax({
			url : base+ '/mgr_addormodifywxaccinfojson.html',
			data : reqData,
			type : 'POST',
			dataType : 'json',
			success : function(resp) {
				
				alert(resp.message);
//				var u = base+ '/mgr_wxacclistjson.html?a=a';
//				oWxAccInfo.ajax.url(u).load();
			}
		});
	}
		
};

$(function() {
	WxAccInfo.init();
});