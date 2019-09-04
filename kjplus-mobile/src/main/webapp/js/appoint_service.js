//    $('.alert').hide();
//    $('.success').hide();
var base = $("#base").attr("href");

AppointServiceClass = {
		init: function() {
			this.bindEvent();
		},
		initData: function() {
			console.log($('#appoint i').eq(0));
			$('#appoint i').eq(0).attr('class','green_border');
		},
		bindEvent: function() {
			//取消预约
			$('.main .off').click(function () {
				var appointCode = $(this).attr("data-code"); 
				console.log(appointCode);
		        var html = '';
		        html += '<div class="alert_box">';
		        html += '<h3>取消预约<i class="off">X</i></h3>';
		        html += '<div class="alert_content">';
		        html += '<p style="text-align: center">是否取消本次预约</p>';
		        html += '</div>';
		        html += '<button class="green confirm">确&nbsp;&nbsp;&nbsp;定</button>';
		        html += '</div>';
		        $('.alert').html(html).show();
		        $('.alert .alert_box').css('marginTop', -$('.alert .alert_box').height()/2 + 'px');
		        $('.success p').html('取消成功');
		        var timeout;//秒
		        timeout = setTimeout(function () {//当取消预约时，长时间未操作自动关闭
		        	$('.alert').hide();
	            }, 5000);
		        
		        $('.alert .confirm').click(function () {
		            clearTimeout(timeout);
		            $('.alert').hide();
		            console.log(appointCode);
		        	$.ajax({
						url : base + '/editappointstatus.html?appointCode='+appointCode,
						type : 'GET',
						dataType : 'json',
						success : function(resp) {
							$('.success').show();
							//超时操作处理
				            timeout = setTimeout(function () {
				                $('.success').hide();
				                location.href = base + '/appoint_service.html';
				            }, 1500);
						}
		        	});
		        	
		        });
		        $('.alert .off').click(function () {
		            $('.alert').hide();
		        });
		    });
			
			//变更预约
			$('.main .green').click(function () {
				var cancelAppointCode = $(this).attr("data-code"); 
				console.log(cancelAppointCode);
				//跳转页面
				location.href = base + '/vaccination.html?cancelAppointCode='+cancelAppointCode;
		    });

		},
		rebindEvent: function() {
			
		},
}
$(function () {
	AppointServiceClass.init();
	AppointServiceClass.initData();
    
});