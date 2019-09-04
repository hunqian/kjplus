var base = $("#base").attr("href");


$(function () {
	//数据初始化
//	console.log($('.footer .appoint').eq(0).html());
	$('.footer .appoint').eq(0).html();
    //右上角下拉框
    var headerFlag = true;
    $('.header span').click(function () {
        if (headerFlag) {
            headerFlag = false;
            var ulHeight = $('.header li').eq(0).height() * $('.header li').length + 'px';
            $('.header ul').css('height', ulHeight);
            $('.header span i').css('transform', 'rotate(180deg)')
        } else {
            headerFlag = true;
            $('.header ul').css('height', 0);
            $('.header span i').css('transform', 'rotate(0deg)')
        }
    });
    $('.header li').click(function () {
        if ($(this).attr('class') != 'id_add') {
            headerFlag = true;
            $('.header em').html($(this).html());
            $('.header ul').css('height', 0);
            $('.header span i').css('transform', 'rotate(0deg)')
        }
    });
    
	//居民服务包选择
    $('.appoint i').click(function (e) {
		console.log("居民服务包切换");
        //$('.appoint i').css('backgroundImage', 'url(' + base + '/imgs/family_doctor_appoint/appoint_3.png)');
		//获取当前服务包是否被选择
		var select = $(this).attr("data-selected");
		console.log(select);
		if(select == "selected"){
			$(this).css('backgroundImage', 'url(' + base + '/imgs/family_doctor_appoint/appoint_3.png)');
			$(this).attr("data-selected","unselected");
		}
		else{
			$(this).css('backgroundImage', 'url(' + base + '/imgs/family_doctor_appoint/appoint_2.png)');
			$(this).attr("data-selected","selected");
		}
        //serviceName = $(this).parent().find('span').html();
        //serviceCode = $(this).parent().find('span').attr('data-service-code');
        e.stopPropagation();
    });
    $('.appoint p').click(function () {
    	//TODO  获取服务包描述  走后台  
    	var name = $(this).find("[data-service-name]").attr("data-service-name");
    	console.log(name);
    	var alias = $(this).find("[data-service-alias]").attr("data-service-alias");
    	var memo = $(this).find("[data-service-memo]").attr("data-service-memo");
       
    	var html = '<div class="alert_box">\n' +
            '        <h3>'+name+'（'+alias+'）详情<i class="off">X</i></h3>\n' +
            '        <div class="alert_content">\n' +
            '            <p>'+memo+'</p>\n' +
            '        </div>\n' +
            '        <button class="green confirm">确&nbsp;&nbsp;&nbsp;定</button>\n' +
            '    </div>';

        $('.alert').html(html).show();
        $('.alert .alert_box').css('marginTop', -$('.alert .alert_box').height()/2 + 'px');
        $('.alert .confirm').click(function () {
            $('.alert').hide();
        });
        $('.alert .off').click(function () {
            $('.alert').hide();
        });
    });

    //签约及取消签约提示框
    $('.footer .confirm').click(function () {
    	//获取签约服务包
        var serviceName = [];
        var serviceCode = [];
    	$(".appoint i").each(function(){
    		  if($(this).attr("data-selected") == "selected"){
    			  serviceCode.push($(this).parent().find('span').attr('data-service-code'));
        		  serviceName.push($(this).parent().find('span').attr('data-service-name'));
    		  }
    	});
    	
        var html = '';
        html += '<div class="alert_box">';
        html += '<h3>提示<i class="off">X</i></h3>';
        html += '<div class="alert_content">';
        html += '<p>申请人：' +$('#prsnName').attr("data-prsnName")+ '</p>';
        html += '<p>签约团队：' + $('#deptName').html() + '</p>';
        html += '<p>签约医生：' +$('#staffName').html()+ '</p>';
        html += '<p>所选服务包：' + serviceName.join(",") + '</p>';
        html += '<p>请耐心等待医生的审核，感谢您的支持与选择！</p>';
        html += '</div>';
        html += '<button class="green confirm">确&nbsp;&nbsp;&nbsp;定</button>';
        html += '</div>';
        $('.alert').html(html).show();
        $('.alert .alert_box').css('marginTop', -$('.alert .alert_box').height()/2 + 'px');
        $('.success p').html('申请签约成功');
        var timeout;
        //确定
        $('.alert .confirm').click(function () {
            clearTimeout(timeout);
            $('.alert').hide();
            timeout = setTimeout(function () {
                // $.ajax();
                var srvAssign = {};
                srvAssign.prsnCode = $('#prsnName').attr('data-prsnCode');
                srvAssign.deptCode = $('#deptName').attr('data-deptCode');
                srvAssign.staffCode = $('#staffName').attr('data-staffCode');
                srvAssign.serviceCode = serviceCode.join(",");
                console.log(srvAssign);
                $.ajax({
        			url : base + '/service_assign.html',
        			type : 'POST',
        			data : srvAssign,
        			dataType : 'json',
        			success : function(data) {
        				$('.success').show();
        				//页面显示签约成功后再进行页面跳转
        				setTimeout(function(){ 
        					$('.success').hide();
        					location.href = base + '/family_doctor.html';
        				}, 3000);
        			}
        		});
                
            }, 1500);
        });
        $('.alert .off').click(function () {
            $('.alert').hide();
        });
    });
    $('.footer .off').click(function () {
        var html = '';
        html += '<div class="alert_box">';
        html += '<h3>提示<i class="off">X</i></h3>';
        html += '<div class="alert_content">';
        html += '<p style="text-align: center">是否取消本次签约</p>';
        html += '</div>';
        html += '<button class="green confirm">确&nbsp;&nbsp;&nbsp;定</button>';
        html += '</div>';
        $('.alert').html(html).show();
        $('.alert .alert_box').css('marginTop', -$('.alert .alert_box').height()/2 + 'px');
        $('.success p').html('取消成功');
        var timeout;
        $('.alert .confirm').click(function () {
            clearTimeout(timeout);
            $('.alert').hide();
            $('.success').show();
            timeout = setTimeout(function () {
                $('.success').hide();
                // $.ajax();
                location.href = './family_doctor.html';
            }, 1500);
        });
        $('.alert .off').click(function () {
            $('.alert').hide();
        });
    });
});