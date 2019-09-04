var base = $("#base").attr("href");

FamilyDoctor = {
	init:function(){
		this.initOtherThing();
		this.bindEvent();
	},
	initOtherThing:function(){
		
	},
	bindEvent:function(){
		//去签约   页面跳转
	    $('.id_go_assign').click(function () {
	    	var prsnCode = $(this).parent().find("[data-prsnCode]").attr("data-prsnCode");
	    	//TODO  后台获取
	    	var relationTypeName = $(this).parent().find("[data-relation]").attr("data-relation");
	    	var deptSels = $('.id_select_team');
	    	//团队
	    	var deptCode = '';
	    	deptSels.each(function(index){
	    		var sels = $(deptSels[index]).attr("select");
	    		if(sels == "true"){
	    			deptCode = $(deptSels[index]).parent().attr("data-deptCode");
	    		}
	    	});
	    	//医生
	    	var staffCode = '';
	    	deptSels.each(function(index){
	    		var sels = $(deptSels[index]).attr("select");
	    		if(sels == "true"){
	    			staffCode = $(deptSels[index]).parent().attr("data-staffCode");
	    		}
	    	});
	    	console.log(prsnCode+","+deptCode+","+staffCode);
	    	
	    	//没有选择签约团队时
	    	if (deptCode == null || deptCode == "") {
	                $('.alert_6').show();
	                $('.alert_6 button').click(function () {
	                    $('.alert_6').hide();
	                });
	        }else{
	        	location.href = base + '/family_doctor_appoint.html' + '?prsnCode=' + prsnCode + '&deptCode=' + deptCode+ '&staffCode=' + staffCode+ '&relationTypeName=' + relationTypeName;
	        }
	    	
	      /*  for(var i = 0; i < $('.id_select_team').length; i++) {
	            if ($('.id_select_team').eq(i).attr('select') === 'true') {
	                location.href = base + '/family_doctor_appoint.html' + '?orgId=' + '1001' + '&personId=' + prsnCode + '&deptId=' + deptCode;
	                break;
	            }
	            if (i == $('.id_select_team').length - 1) {
	                $('.alert_6').show();
	                $('.alert_6 button').click(function () {
	                    $('.alert_6').hide();
	                });
	            }
	        }*/
	    });
	    // $('.id_go_team').click(function () {
	    //     location.href = './doctor_team.html';
	    // });
	    $('.team>div').click(function () {
	    	var deptId = $(this).attr("data-deptid");
//	    	console.log(deptId);
	        location.href = base + '/doctor_team.html?deptId=' + deptId;
	    });
	    $('.id_go_consult').click(function () {
	        location.href = base + '/consult.html';
	    });

	    $('.family').eq($('.family').length - 1).css('border', 'none');
	    //右上角下拉框
	    var headerFlag = true;
	    $('.header span').click(function () {
	        if (headerFlag) {
	            headerFlag = false;
	            var ulHeight = $('.header li').eq(0).height() * $('.header li').length + 'px';
	            $('.header ul').css('height', ulHeight);
	            $('.header span i').css('transform', 'rotate(180deg)');
	        } else {
	            headerFlag = true;
	            $('.header ul').css('height', 0);
	            $('.header span i').css('transform', 'rotate(0deg)');
	        }
	    });
	    $('.header li').click(function () {
	        if ($(this).attr('class') != 'id_add') {
	            headerFlag = true;
	            $('.header em').html($(this).html());
	            $('.header ul').css('height', 0);
	            $('.header span i').css('transform', 'rotate(0deg)');
	        }
	    });

	    //选择团队按钮
	    $('.id_select_team').click(function (e) {
	    	
	        $('.id_select_team').css({'background': '#fff', 'color': '#7a7a7a', 'marginRight': '0'}).html('选择团队').removeAttr('select');
	        $(this).css({'background': '#0bd16b', 'color': '#fff', 'marginRight': '0.3125rem'}).html('已选择').attr('select', 'true');
	        e.stopPropagation();
	    });
	}
}

$(function () {
	FamilyDoctor.init();
});