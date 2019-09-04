<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<base id="base" href="${base}">
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<link rel="stylesheet" href="${base}/css/base.css">
	<link rel="stylesheet" href="${base}/css/login.css">
	<title>登录</title>
</head>
<body>
<form name="loginfrm" id="loginfrm" action="${base}/mblogin.html" method="POST">
<input type="hidden" name="returnUrl" id="returnUrl" value="${returnUrl!''}">
	<div class="login_validate">
			<div class="input_item">
				<i class="icon_un">用户名:</i>
					<input type="text" placeholder="请输入手机号" id="username" name="username" maxlength="11" class="loginName">
					<!--限制输入数字为11位-->
			</div>
			<div class="input_item">
				<i class="icon_pw">密 &nbsp码:</i>
					<input type="password" placeholder="请输入密码" id="password" name="password" maxlength="11" class="loginName">
					<!--限制输入密码为11位-->
			</div>
			
			<div class="input_item">
				<i class="icon_pw">验证码:</i>
				<input type="tel" placeholder="请输入验证码" id="vericode" name="vericode" maxlength="6" class="loginName">
					<!--限制输入验证码为6位-->
				<span class="yzword" ><img id="Img" src="${base}/vericode.html?veritype=mblogin" alt="验证码"  /> </span>
			</div>

			<div class="cue">错误提示</div>
			<div class="input_item">
				<input type="button" class="loginBtn" value="登  录">
			</div>
			<div class="input_enroll">
				<div class="login_wjmm">忘记密码</div>
				<div class="login_zhuce">注册</div>
			</div>
		</div>
	</div>
	</form>

<script src="${base}/js/jquery-1.12.2.min.js"></script>
<script type="text/javascript">
	//错误提示
	$(".loginBtn").click(function(){
        var username = $('#username').val();
        if(username == ""){
        $('.cue').text('请输入用户名').fadeIn(function(){
                setTimeout(function () {
                    $(".cue ").fadeOut();
                },1000);//提示1秒后隐藏。
            });
            return false;
        }
        /*if(username.match(/^1\d{10}$/) == null){
            allowClick = true;
            $('.cue').text('请输入正确的手机号').fadeIn(function(){
                setTimeout(function () {
                    $(".cue ").fadeOut();
                },1000);//提示1秒后隐藏。
            });
            return false;
        }*/
        var password = $('#password').val();
        if(password == ''){
            allowClick = true;
            $('.cue').text('请输密码').fadeIn(function(){
                setTimeout(function(){
                    $(".cue ").fadeOut();
                },1000);//提示1秒后隐藏。
            });
            return false;
        }
        var vericode = $('#vericode').val();
        if(vericode == ''){
            allowClick = true;
            $('.cue').text('请输验证码').fadeIn(function(){
                setTimeout(function(){
                    $(".cue ").fadeOut();
                },1000);//提示1秒后隐藏。
            });
            return false;
        }
        $("#loginfrm").submit();
	});
	//登录识别码
	$('#yzword').click(function(){
	  var img = document.getElementById("Img");
	  var base = $("#base").attr("href");  
	  img.src = base + "/vericode.html?veritype=mblogin&rnd=" + Math.random();
	});

	//跳转注册页面
	$('.login_zhuce').click(function(){
		setTimeout(function(){
			location.href ="${base}/register.htm";
		},300)
	});

	//跳转找回密码页面
	$('.login_wjmm').click(function(){
		setTimeout(function(){
			location.href ="${base}/back.htm";
		},300)
	});
	
	$(function () {
		var msg = "${message!''}"; 
		if(msg != ''){
			alert(msg);
		}
	});

</script>
</body>
</html>