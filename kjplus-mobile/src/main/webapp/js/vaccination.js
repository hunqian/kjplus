var base = $("#base").attr("href");
var vac_html = "";
var entryMainName = "";
var entryName = "";
//全局接种类型
var infoTypeCode = "";
var infoTypeName = "";
var infoCalDate = "";
VaccinationClass = {
	init: function() {
		this.bindEvent();
	},
	initData: function() {
		$('.alert_2').hide();
		var date = $('.cur').attr('data-date');
		var data_json = {};
		data_json.datestr = date;
		console.log(date);
		infoCalDate = date;
		$('.main h3 em').html("-"+date+"-");
		var typeName = "预约接种";
		infoTypeName = typeName;
		$('.main h3 span').html(infoTypeName);
		
		//初始化日历显示
		$.ajax({
			url : base + '/vaccination_dailylist.html',
			type : 'POST',
			//data : data_json,
			dataType : 'json',
			success : function(data) {
				//日历中有预约服务的日历样式变化
				if(data.vacList.length != 0){
					for(var i=0;i<data.vacList.length;i++){
						var startDay = data.vacList[i].startDay;
						console.log(startDay);
						//样式调整
						$("[data-date = '"+startDay+"']").css('background', '#ff3366');
						$("[data-date = '"+startDay+"']").css('borderRadius', '1.375rem');
						$("[data-date = '"+startDay+"']").css('color', '#fff');
						//$("[data-date = '"+startDay+"']").style.background = '#0bd16b';
						//$("[data-date = '"+startDay+"']").style.borderRadius = '1.375rem';
						//$("[data-date = '"+startDay+"']").style.color = '#fff';
					}
				}
			}
		});
		//$("[data-date = '2018-03-04']").css('background', '#ff3366');
		
		//可预约信息展示'
		$.ajax({
			url : base + '/vaccination_dailylist.html',
			type : 'POST',
			data : data_json,
			dataType : 'json',
			success : function(data) {
				//预约信息
				if(data.vacList.length != 0){
					//预约接种具体信息	下部
					var html = '';
					for(var i=0;i<data.vacList.length;i++){
						var infoCode = data.vacList[i].calInfoCode;
						//日历信息   时间
						var startDay = data.vacList[i].startDay;
						var startTime = data.vacList[i].startTime;
						var finishDay = data.vacList[i].finishDay;
						var finishTime = data.vacList[i].finishTime;
						var joinPerson = data.vacList[i].joinPerson;
						var maxPerson = data.vacList[i].maxPerson;
						//目前预约接种状态
						//var time = startDay+''+" "+''+startTime+''+" - "+''+finishDay+''+" "+''+finishTime;
						var time = startTime+''+" - "+''+finishTime;
						var status = data.vacList[i].status;
						if(status == "N"){//不可预约接种
							html += '<p infoCode='+infoCode+' class="red"><i style="background-image: url(' +base+ '/imgs/vaccination/select_1.png);">';
							html += '</i><em>满</em>时间：'+time+' 已约满</p>';
						}else if (status == "Y"){//可预约接种
							html += '<p  infoCode='+infoCode+' startDay='+startDay+' startTime='+startTime+' endTime='+finishTime+'>';
							html += '<i style="background-image: url(' +base+ '/imgs/vaccination/select_1.png);">';
							html += '</i><em>预约</em>时间：'+time+' 已约人数：'+joinPerson+'</p>';
						}
						
					}
					
					//预约信息在日历上显示 改变日历颜色
			        $('.appoint').append(html);
			        //将日历信息显示出来
			        $('.main h3 em').show();
					$('.appoint').show();
					$('.btn_box').show();
					$('.main h3 i').show();
				}else{
					$('.main h3 i').hide();
					$('.main h3 span').html('当日无活动');
					$('.main h3 em').hide();
					$('.appoint').hide();
					$('.btn_box').hide();
				}
				 //重新绑定新生成页面的事件
		        VaccinationClass.rebindEvent();
			}
		});
		
	},
	bindEvent: function() {
		$('.green').click(function(){
			$('.alert_2').hide();
		})
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
	            $('.header em').attr("personCode",$(this).attr("personCode"));
	            $('.header ul').css('height', 0);
	            $('.header span i').css('transform', 'rotate(0deg)');
	        }
	    });

	    //接种项目
	    var ulHeight = $('.vaccination_type li').eq(0).height() * $('.vaccination_type li').length + 4;
	    var vaccinationTypeFlag = true;
	    $('.vaccination_type .right').click(function () {
	        if (vaccinationTypeFlag) {
	            $('.vaccination_type ul').css('height', ulHeight + 'px');
	            vaccinationTypeFlag = false;
	        } else {
	            $('.vaccination_type ul').css('height', 0);
	            vaccinationTypeFlag = true;
	        }
	    });
	    var appointType = "";
	    //点击下拉接种项目切换
	    $('.vaccination_type li').click(function () {
	    	var typeCode = $(this).attr("typeCode");//预约接种类型
	    	var typeName = $(this).html();
	    	infoTypeCode = typeCode;
	    	infoTypeName = typeName;
	        $('.vaccination_type .right').html($(this).html() + ' <i class="iconfont">&#xe504;</i>');
	        $('.vaccination_type ul').css('height', 0);
	        vaccinationTypeFlag = true;
	        $('.main h3 span').html(vac_html +"  "+ $(this).html());
	        appointType = $(this).html();
	        $('.main').show();
	        //TODO 预约接种内容信息 刷新
	        
			$('.alert_2').hide();
			var date = $('.cur').attr('data-date');
			var data_json = {};
			data_json.date = date;
			data_json.typeCode = typeCode;
			console.log(data_json);
			$('.main h3 em').html("-"+infoCalDate+"-");
	    });

	    
	    //预约提示框
	    $('.main .confirm').click(function () {
	    	if($('.bgc').length == 0){
	    		$('.alert_2').show();
	    	}else{
	    		var day = $('.bgc').eq(0).attr('startDay');
		    	var startTime = $('.bgc').eq(0).attr('startTime');
		    	var endTime = $('.bgc').eq(0).attr('endTime');
		    	var calInfoCode = $('.bgc').eq(0).attr('infoCode');
		    	console.log(day);
		    	console.log(startTime);
		    	console.log(endTime);
		    	console.log(calInfoCode);
		        var html = '';
		        console.log(infoTypeCode);
		        console.log(infoTypeName);
		        
		        var personCode = $('.header span em').attr("personCode");
		        var personName = $('.header span em').html();
		        //变更预约
		        var cancelAppointCode = $('.header .iconfont').attr("cancel-code");
		        
		        //内容替换
		        html += '<p personCode = "'+personCode+'">姓名：'+personName+'</p>';
		        html += '<p>类别：'+"免疫接种"+'</p>';
		        html += '<p calInfoCode = '+calInfoCode+'>预约内容：'+infoTypeName+'</p>';
		        html += '<p>预约时间：'+ day +' '+ startTime +'-'+endTime+'</p>';
		        $('.alert').show();
		        $('.alert .alert_content').html(html);
		        //按钮替换
		        console.log(cancelAppointCode);
		        if(cancelAppointCode !=null && cancelAppointCode != "")
		        	$('.alert .alert_box .green').html("确认变更预约");
		        
	    	}
	    });
	    
	    $('.main .off').click(function () {
	        $('.alert').show();
	        $('.alert .alert_content').html('<p style="text-align: center; margin: 1rem 0;">是否确定取消本次预约</p>');
	        $('.success p').html('取消成功');
	    });
	    
	    var timeout;
	    $('.alert .confirm').click(function () {
	    	console.log("确定预约");
	        clearTimeout(timeout);
	        $('.alert').hide();
	        timeout = setTimeout(function () {
	        	var cancelAppointCode = $('.header .iconfont').attr("cancel-code");
	        	
	        	var vac_appoint = {};
	        	vac_appoint.appTypeCode = infoTypeCode;
	        	vac_appoint.calInfoCode = $('.bgc').eq(0).attr('infoCode');
	        	vac_appoint.personCode = $('.header em').attr('personCode');//预约人
	        	vac_appoint.cancelAppointCode = cancelAppointCode;
	        	
	        	console.log(vac_appoint)
	        	$.ajax({
	    			url : base + '/vaccination_appoint.html',
	    			type : 'POST',
	    			data : vac_appoint,
	    			dataType : 'json',
	    			success : function(data) {
	    				$('.success p').html('预约成功');
	    		        $('.success_box p').html(data.message);
	    		        $('.success').show();
	    		        setTimeout("location.href = base+'/appoint_service.html';",2000);
	    			}
	    		});
	        	$('.success').hide();
	        }, 500);
	        
	    });
	    $('.alert .off').click(function () {
	        $('.alert').hide();
	    });
    },
	rebindEvent: function() {
		//获取日期
		$('#calendar ul.day li').unbind().click(function () {
			date = this.getAttribute('data-date');
			console.log(date);
			var data_json = {};
			data_json.datestr = date;
			//接种信息类型
			data_json.typeCode = infoTypeCode;
			//获取当天的接种信息(接种开始时间时当天00:00:00    结束时间23:59:59)
			$('.main h3 em').show();
			var typeName = infoTypeName;
			
			$('.main h3 span').html(typeName);
			$('.main h3 em').html("-"+date+"-");
			
			//预约日历信息样式修改
			$.ajax({
				url : base + '/vaccination_dailylist.html',
				type : 'POST',
				//data : data_json,
				dataType : 'json',
				success : function(data) {
					//日历中有预约服务的日历样式变化
					if(data.vacList.length != 0){
						for(var i=0;i<data.vacList.length;i++){
							var startDay = data.vacList[i].startDay;
							if(startDay != date){
								//样式调整
								$("[data-date = '"+startDay+"']").css('background', '#ff3366');
								$("[data-date = '"+startDay+"']").css('borderRadius', '1.375rem');
								$("[data-date = '"+startDay+"']").css('color', '#fff');
							}
						}
					}
				}
			});
			
			//预约信息展示
			$.ajax({
				url : base + '/vaccination_dailylist.html',
				type : 'POST',
				data : data_json,
				dataType : 'json',
				success : function(data) {
					//预约信息
					if(data.vacList.length != 0){
						
						//预约接种具体信息	下部
						var html = '';
						for(var i=0;i<data.vacList.length;i++){
							var infoCode = data.vacList[i].calInfoCode;
							//日历信息   时间
							var startDay = data.vacList[i].startDay;
							var startTime = data.vacList[i].startTime;
							var finishDay = data.vacList[i].finishDay;
							var finishTime = data.vacList[i].finishTime;
							var joinPerson = data.vacList[i].joinPerson;
							var maxPerson = data.vacList[i].maxPerson;
							//目前预约接种状态
							//var time = startDay+''+" "+''+startTime+''+" - "+''+finishDay+''+" "+''+finishTime;
							var time = startTime+''+" - "+''+finishTime;
							var status = data.vacList[i].status;
							if(status == "N"){//不可预约接种
								html += '<p infoCode='+infoCode+' class="red"><i style="background-image: url(' +base+ '/imgs/vaccination/select_1.png);">';
								html += '</i><em>满</em>时间：'+time+' 已约满</p>';
							}else if (status == "Y"){//可预约接种
								html += '<p  infoCode='+infoCode+'  startDay='+startDay+' startTime='+startTime+' endTime='+finishTime+'>';
								html += '<i style="background-image: url(' +base+ '/imgs/vaccination/select_1.png);">';
								html += '</i><em>预约</em>时间：'+time+' 已约人数：'+joinPerson+'</p>';
							}
						}
						//移除原始数据
						$('.appoint p').remove();
						$('.appoint').append(html);
						  //将日历信息显示出来
				        $('.main h3 em').show();
						$('.appoint').show();
						$('.btn_box').show();
						$('.main h3 i').show();
					}else{
						$('.main h3 i').hide();
						$('.main h3 span').html('当日无活动');
						$('.main h3 em').hide();
						$('.appoint').hide();
						$('.btn_box').hide();
					}
					 //重新绑定新生成页面的事件
			        VaccinationClass.rebindEvent();
					
				}
			});
			
		});
		//签约服务
	    $('.appoint p').click(function () {
			//单选
	    	if ($(this).attr('class') == 'red') {return}
			var aps= $('.appoint p');
			aps.each(function(index){
				if ($(this).attr('class') != 'red') {
					$(aps[index]).removeClass();
		            $(aps[index]).find('i').css('backgroundImage', 'url(' +base+ '/imgs/vaccination/select_1.png)');
				}
			});
			if ($(this).attr('class') != 'red') {
				$(this).addClass('bgc');
				$(this).find('i').css('backgroundImage', 'url(' +base+ '/imgs/vaccination/select_2.png)');
			}
			//多选
	        /*if ($(this).attr('class') != 'red') {
	            if ($(this).attr('class') != 'bgc') {
	                $(this).addClass('bgc');
	                $(this).find('i').css('backgroundImage', 'url(' +base+ '/imgs/vaccination/select_2.png)');
	            } else {
	                $(this).removeClass();
	                $(this).find('i').css('backgroundImage', 'url(' +base+ '/imgs/vaccination/select_1.png)');
	            }
	        }*/
	    });
	    
	  //添加成员按钮	跳转的新建档案页面
	    $('.id_add').click(function () {
	    	console.log("id_add");
	    	location.href = base + '/create_document.html';
	    });
	    
    }
}

$(function () {
	VaccinationClass.init();
	VaccinationClass.initData();
    
});