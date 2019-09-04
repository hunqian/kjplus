var base = $("#base").attr("href");
var Login = {
    init: function() {
        this.bindEvent();
    },
    bindEvent: function() {
        //登陆
    	console.log("00000");
    	//换二维码
    	$("#vericodeimg").click(function() {
    		$("#vericodeimg").attr("src" , base + "/vericode.html?veritype=login&v=" + (new Date()).getTime());
    	});
    	
        $("#toLogin").click(function() {
            var username = $("#username").val();
            var password = $("#password").val();
            var vericode = $("#vericode").val();
            if (username == "") {
                alert("请输入用户名或者手机号!");
                return false;
            }
            if (password == "") {
                alert("请输入密码!");
                return false;
            }
            if (vericode == "") {
                alert("请输入验证码!");
                return false;
            }
            var reqData = {};
            reqData.username = username;
    		reqData.password = password;
    		reqData.vericode = vericode;
    		reqData.returnUrl = returnUrl;
    		   $.ajax({
       			url: base + "/loginj.html",
       			data: reqData,
       			type: 'POST',
       			dataType: 'json',
       			success: function (resp) {
       				if(resp.result == 1){
       					var u = resp.returnUrl;
       					if(u.indexOf("/") != 0 && u.indexOf("http") != 0)
       						u = "/" + u;
       					u = base + u;
       					window.location.href = u;
       				}else{
       					$("#vericodeimg").attr("src" , base + "/vericode.html?veritype=login&v=" + (new Date()).getTime());
       					$("#errmsg").text(resp.message);
       					$("#errmsg").show();
       				}
       			},
       			error: function (response) {
       			}
       		});
        });
    }
};