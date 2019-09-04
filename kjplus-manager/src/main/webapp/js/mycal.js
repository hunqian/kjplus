var base = $("#base").attr("href");
var calendar = null;
var allEvents = [];
var addparam = 1;
Mycal = {
	init:function(){
		this.initCalendar();
		//this.initData();
		//this.rebindEvent();
	},
	initCalendar:function(){
		//保存日历信息
		$("#save-calendar-btn").click(function (){
			var calevents = [];
			for(var k in allEvents){
				if(allEvents[k].modify != "N"){
					var ce = {};
					ce.infoid = allEvents[k].id;
					ce.title = allEvents[k].title;	
					ce.defid = allEvents[k].defid;
					ce.memo = allEvents[k].memo;
					ce.maxperson = allEvents[k].maxperson;
					ce.timeinterval = allEvents[k].timeinterval;
					ce.starttime = allEvents[k].start;
					ce.endtime = allEvents[k].end;
					ce.modify = allEvents[k].modify;
					ce.trace = k;
					calevents.push(ce);
				}
			}
			var reqData = {};
			reqData.calinfojson = JSON.stringify(calevents);
			if(calevents.length == 0)
				return;
			console.log(reqData);
			$.ajax({
				url: base + '/mgr_addormodifycalinfojson.html',
				data: reqData,
				type: 'POST',
				dataType: 'json',
				success: function (resp) {
					alert(resp.message);
					var k = null;
					for(var i=0;i<resp.data.length;i++){ 
						if(resp.data[i].oper == "MODIFY"){
							k = resp.data[i].trace;
							allEvents[k].modify = "N";
						}else if(resp.data[i].oper == "DEL"){
							k = resp.data[i].trace;
							delete allEvents[k];
						}else if(resp.data[i].oper == "ADD"){
							var ce = allEvents[resp.data[i].trace];
							ce.modify = "N";
							k = ce.defid + "." + resp.data[i].id;
							allEvents[k] = ce;
							delete allEvents[resp.data[i].trace];
						}
					}
				}
			});
		});
		//选择日历类型显示
		$("[id^='entry_sel_']").click(function (){
			var sels = $("[id^='entry_sel_']");
			var defIds = [];
			sels.each(function(index){
				if($(sels[index]).prop("checked") == true )
					defIds.push($(sels[index]).attr("value"));
			});
			
			$.ajax({
	            url: base + '/mgr_listcalentrytypeson.html?defids=' + defIds.join(),
	            dataType: 'json',
	            success: function(resp) {
	                var html = "";
	                var htmlactivity = "";
	                for(var i=0;i<resp.data.length;i++){
	                	var memo = "时长"+resp.data[i].timeInterval + "分" +"---"+resp.data[i].title;
	                	html += '<div class="external-event '+resp.data[i].showClass+' ui-draggable" data-class="'+resp.data[i].showClass
	                		+'" data-memo="' + resp.data[i].memo + '"  data-defid="' + resp.data[i].defid 
	                		+ '"  data-maxperson ="' + resp.data[i].maxPerson + '"  data-typeid="' + resp.data[i].typeid 
	                		 + '"  data-timeinterval ="' + resp.data[i].timeInterval+'" style="position: relative;">';
	                	html += '	<i class="icon-move"></i>';
	                	html +=  memo;
						html += '</div>';
	                }
	                for(var j=0;j<resp.actdata.length;j++){
	                	htmlactivity += '<div class="external-event '+resp.actdata[j].showClass+' ui-draggable"  data-class="'+ resp.actdata[j].showClass 
	                			  +'" data-memo="' + resp.actdata[j].memo + '" data-maxperson ="' + resp.actdata[j].maxPerson + '" data-defid="' + resp.actdata[j].defid 
	                			  + '"  data-timeinterval ="' + resp.actdata[j].timeInterval+'"  style="position: relative;">';
	                	htmlactivity += '	<i class="icon-move"></i>';
	                	htmlactivity +=  resp.actdata[j].title;
	                	htmlactivity += '</div>';
	                }
	                $("#external-events>div").remove();
	                $("#external-events").append(html);
	                
	                $("#external-events-activity>div").remove();
	                $("#external-events-activity").append(htmlactivity);
	                
	                Mycal.rebindEntryEvent();
	            }
	        });
			
			var view = $('#calendar').fullCalendar('getView');
			var starttime = $.fullCalendar.formatDate(view.start, "yyyy-MM-dd HH:mm:ss");//2017-11-27 00:00:00
			var endtime = $.fullCalendar.formatDate(view.end, "yyyy-MM-dd HH:mm:ss"); //2018-01-08 00:00:00
			var reqData = {};
			reqData.startday = starttime.substring(0,10);
			reqData.endday = endtime.substring(0,10);
			reqData.calids = defIds.join();
			var k = "";
			$.ajax({
				url: base + '/mgr_calinfosjson.html',
				type: 'GET',
				data:reqData,
				dataType: 'json',
				success: function (resp) {
					for(var i=0; i<resp.data.length; i++){
						k = resp.data[i].defid + "." + resp.data[i].id; //k包含所有的cal_info信息
						if(allEvents[k])
							continue;
						resp.data[i].modify = "N"; //赋初始值
						allEvents[k] = resp.data[i];  //allEvents所有日历信息。
	                }
					$('#calendar').fullCalendar('removeEvents');
					for(k in allEvents){
						for(var d=0; d<defIds.length; d++){
							if(k.indexOf(defIds[d]+".") == 0){//将defIds中包含k的显示出来 
								var ev = allEvents[k];
								var copiedEventObject = $.extend({}, $(this).data('eventObject'));
			                    copiedEventObject.title = ev.title;
			                    copiedEventObject.memo = ev.memo; 
			                    copiedEventObject.infoid = ev.id;
			    				copiedEventObject.defid = ev.defid;
			    				copiedEventObject.start = ev.start;
			    				copiedEventObject.className = ev.showclass;
			    				copiedEventObject.end = ev.end;
			    				copiedEventObject.maxperson = ev.maxperson;
			    				copiedEventObject.allDay = false;
			    				copiedEventObject.modify = ev.modify;
			    				$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
							}
						}
					}
					
				}
			});
			
		});
		
		/* initialize the calendar
		-----------------------------------------------------------------*/
		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();
	
		//初始化日历信息
		calendar = $('#calendar').fullCalendar({
			 buttonText: {
				prev: '<i class="icon-chevron-left"></i>',
				next: '<i class="icon-chevron-right"></i>'
			},
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			monthNames:["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
			monthNamesShort:["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
			dayNames:["周日","周一","周二","周三","周四","周五","周六"],
			dayNamesShort:["周日","周一","周二","周三","周四","周五","周六"],
			today:["今天"],
			firstDay:1,
			buttonText:{
			today:'本月(周天)',
			month:'月',
			week:'周',
			day:'日',
			prev:'上一月(周天)',
			next:'下一月(周天)'
			}
			,editable: true
			,droppable: true // this allows things to be dropped onto the calendar !!!
			,drop: function(date, allDay) {
				var originalEventObject = $(this).data('eventObject');
				var $extraEventClass = $(this).attr('data-class');
				var defid = $(this).attr('data-defid');
				
				var copiedEventObject = $.extend({}, originalEventObject);
				copiedEventObject.start = date;
				
				if($extraEventClass) 
					copiedEventObject['className'] = [$extraEventClass];
				
				copiedEventObject.infoid = 0;
				copiedEventObject.defid = defid;
				copiedEventObject.allDay = false;
				copiedEventObject.modify = "A";
				var k = defid + "."  + Mycal.random();
				//保证唯一
				while(allEvents[k]){
					k = defid + "."  + Mycal.random();
				}
				copiedEventObject.trace = k;
				
				var ce = {};
				ce.infoid = 0;
				ce.title = $.trim($(this).text());
				ce.memo = $.trim($(this).attr("data-memo"));
				ce.defid = defid;
				//判断是否有添加     默认A;
				ce.modify = "A";
				
				ce.timeinterval = $.trim($(this).attr("data-timeinterval")); 
				ce.trace = k;
				allEvents[k] = ce;
				
				// render the event on the calendar
				// the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
				$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
				
				// is the "remove after drop" checkbox checked?
				if ($('#drop-remove').is(':checked')) {
					// if so, remove the element from the "Draggable Events" list
					$(this).remove();
				}
			}
			,selectable: true
			,selectHelper: true
			,select: function(start, end, allDay) {
				bootbox.prompt("New Event Title:", function(title) {
					if (title !== null) {
						calendar.fullCalendar('renderEvent',
							{
								title: title,
								start: start,
								end: end,
								allDay: allDay
							},
							true // make the event "stick"
						);
					}
				});
				calendar.fullCalendar('unselect');
			}
			//点击日历信息时
			,eventClick: function(calEvent, jsEvent, view) {
					var start = $.fullCalendar.formatDate(calEvent.start, "yyyy-MM-dd HH:mm:ss");
					var end = $.fullCalendar.formatDate(calEvent.end, "yyyy-MM-dd HH:mm:ss");
					//var form = $("<form class='form-inline'><label>修改info或Type &nbsp;</label></form>");
					var form = $("<form class='form-inline'></form>");
					form.append("<input type='hidden'  id='th_infoid' value = '" + calEvent.infoid + "' />");
					//form.append("<input type='hidden'  id='th_ismodify' value = '" + calEvent.ismodify + "' />");
					//form.append("<input class='middle' autocomplete=off type=text value='" + calEvent.title + "' />  </br>");
					form.append("</br><strong>标题：</strong> <input type='title'  class='text' id='th_title' value = '" + calEvent.title + "' /></br> ");
					form.append("<strong>备注：</strong> <input type='memo'  class='text' id='th_memo' value = '" + calEvent.memo + "' /></br> ");
					form.append("<strong>最大人数：</strong> <input type='maxperson'  class='text' id='th_maxperson' value = '" + calEvent.maxperson + "' /></br> ");
					form.append("<strong>开始时间：</strong> <input type='start'  class='text' id='th_starttime' value = '" +   start + "' /></br> ");
					form.append("<strong>结束时间：</strong> <input type='end'  class='text' id='th_endtime' value = '" + end + "' /></br> ");
					var div = bootbox.dialog({
						message: form,
						buttons: {
							"delete" : {
								"label" : "<i class='icon-trash'></i>删除",
								"className" : "btn-sm btn-danger",
								"callback": function() {
									if(calEvent.infoid != 0){
										var k = calEvent.defid + "." + calEvent.infoid;
										//设置删除标志
										allEvents[k].modify = "D";
									}else{
										delete allEvents[calEvent.trace];
									}					
									calendar.fullCalendar('removeEvents' , function(ev){
										return (ev._id == calEvent._id);
									});
								}
							} ,
							"update" : {
								"label" : "<i class='icon-trash'></i>修改",
								"className" : "btn-sm btn-info",
								"callback": function() {
									if(calEvent.infoid != 0){
										var k = calEvent.defid + "." + calEvent.infoid;
										//设置修改标志
										allEvents[k].modify = "Y";
										allEvents[k].title = $("#th_title").val();
										calEvent.title = $("#th_title").val();
										allEvents[k].memo = $("#th_memo").val();
										calEvent.memo = $("#th_memo").val();
										allEvents[k].maxperson = $("#th_maxperson").val();
										calEvent.maxperson = $("#th_maxperson").val();
										allEvents[k].start = $("#th_starttime").val();
										calEvent.start = $("#th_starttime").val();
										allEvents[k].end = $("#th_endtime").val();
										calEvent.end = $("#th_endtime").val();
										$('#calendar').fullCalendar( 'updateEvent', calEvent );
									}
								}
							} ,
							"close" : {
								"label" : "<i class='icon-remove'></i>关闭",
								"className" : "btn-sm"
							} 
						}
					});
				/*	
					form.on('submit', function(){
						console.log(1212121);
							console.log(calEvent);
							infoid = $("#th_infoid").val();
							calEvent.title = $("#th_title").val();
							calEvent.memo = $("#th_memo").val();
							calEvent.maxperson = $("#th_maxperson").val();
							calEvent.start = $("#th_starttime").val();
							calEvent.end = $("#th_endtime").val();
								calEvent.ismodify = $("#th_ismodify").val();
							if(calEvent.ismodify == 0 )//区别对添加信息的修改
								calEvent.ismodify = 2;
							calendar.fullCalendar('updateEvent', calEvent);
							div.modal("hide");
							return false;
						});	*/					
					}
				/*});*/
			
				// change the border color just for fun
				//$(this).css('border-color', 'red');
		});	
		Mycal.rebindEvent();
		Mycal.rebindCalEvent();
		Mycal.initData();
	},
	initData : function(){
		$("[id^='entry_sel_']").click();
	}
	,rebindEntryEvent:function(){
		
		//拖日志类型
		$('#external-events div.external-event').each(function() {
			// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
			// it doesn't need to have a start or end
			var eventObject = {
				title: $.trim($(this).text()),
				typeid :$.trim($(this).attr("data-typeid")),
				defid :$.trim($(this).attr("data-defid")),
				memo :$.trim($(this).attr("data-memo")),
				maxperson :$.trim($(this).attr("data-maxperson")),
				timeinterval :$.trim($(this).attr("data-timeinterval")),
				//ismodify : $.trim($(this).attr("data-ismodify"))
			};
			// store the Event Object in the DOM element so we can get to it later
			$(this).data('eventObject', eventObject);
	
			// make the event draggable using jQuery UI
			$(this).draggable({
				zIndex: 999,
				revert: true,      // will cause the event to go back to its
				revertDuration: 0  //  original position after the drag
			});
		});	
		
		//拖活动类型
		$('#external-events-activity div.external-event').each(function() {
			// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
			// it doesn't need to have a start or end
			var eventObject = {
				title: $.trim($(this).text()),
				memo : $.trim($(this).attr("data-memo")),
				defid :$.trim($(this).attr("data-defid")),
				memo :$.trim($(this).attr("data-memo")),
				maxperson :$.trim($(this).attr("data-maxperson")),
				timeinterval :$.trim($(this).attr("data-timeinterval")),
			};
			// store the Event Object in the DOM element so we can get to it later
			$(this).data('eventObject', eventObject);
	
			// make the event draggable using jQuery UI
			$(this).draggable({
				zIndex: 999,
				revert: true,      // will cause the event to go back to its
				revertDuration: 0  //  original position after the drag
			});
		});	
	}
	,rebindCalEvent:function(){
		//$("#calendar [class*='fc-button-next']").click(function(){
		$("#calendar [class*='fc-button']").click(function(){//点击上月和下月时	 
			console.log(11111);
			//获取时间的点击事件刷新页面  ， eg：上一月 
			var view = $('#calendar').fullCalendar('getView');
			var starttime = $.fullCalendar.formatDate(view.start, "yyyy-MM-dd HH:mm:ss");//2017-11-27 00:00:00
			var endtime = $.fullCalendar.formatDate(view.end, "yyyy-MM-dd HH:mm:ss"); //2018-01-08 00:00:00
			var reqData = {};
			reqData.startday = starttime.substring(0,10);
			reqData.endday = endtime.substring(0,10);
			
			var sels = $("[id^='entry_sel_']");
			var defIds = [];
			sels.each(function(index){
				if($(sels[index]).prop("checked") == true )
					defIds.push($(sels[index]).attr("value"));
			});
			reqData.calids = defIds.join(),
			$.ajax({
				url: base + '/mgr_calinfosjson.html',
				type: 'GET',
				data:reqData,
				dataType: 'json',
				success: function (resp) {
					for(var i=0; i<resp.data.length; i++){
						k = resp.data[i].defid + "." + resp.data[i].id; //k包含所有的cal_info信息
						if(allEvents[k])
							continue;
						resp.data[i].modify = "N"; //赋初始值
						allEvents[k] = resp.data[i];  //allEvents所有日历信息。
	                }
					$('#calendar').fullCalendar('removeEvents');
					for(k in allEvents){
						for(var d=0; d<defIds.length; d++){
							if(k.indexOf(defIds[d]+".") == 0){//将defIds中包含k的显示出来 
								var ev = allEvents[k];
								var copiedEventObject = $.extend({}, $(this).data('eventObject'));
			                    copiedEventObject.title = ev.title;
			                    copiedEventObject.memo = ev.memo; 
			                    copiedEventObject.infoid = ev.id;
			    				copiedEventObject.defid = ev.defid;
			    				copiedEventObject.start = ev.start;
			    				copiedEventObject.className = ev.showclass;
			    				copiedEventObject.end = ev.end;
			    				copiedEventObject.maxperson = ev.maxperson;
			    				copiedEventObject.allDay = false;
			    				copiedEventObject.modify = ev.modify;
			    				$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
							}
						}
					}
					
				}
			});
		});
	}
	,rebindEvent:function(){
		//添加日历类型
		$('#add-calendarentry-btn').click(function(){
			$('.entrycover').fadeIn("slow");
			$('.timeIpt').val('');
			$('.cover-input').val('');
			$('#entrycove').show();
			
		});	
		$('.btn-entryclose').click(function(){
			$('.entrycover').hide();
			$('#entrycove').hide();
		});
		$('.btn-entrypri').click(function(){
			$('.entrycover').hide();
			$('#entrycove').hide();
			
			var caltypeid = $("#entry_refId").val();
			var name = $("#entry_title").val();
			var reqData = {};
			reqData.caltypeid = caltypeid;
			reqData.name = name;
			$.ajax({
				url: base + '/mgr_addcalentryson.html',
				data: reqData,
				type: 'POST',
				dataType: 'json',
				success: function (resp) {
					alert(resp.message);
				}
			});
			//TODO 待定　刷新页面日历信息
			location.reload();
			$('#calendar').fullCalendar( 'refetchEvents' );
			
		});	
		
			//编辑日历类型
			$('.coverShow').click(function(){
				$("#ty_defId").val("");
				$("#ty_title").val("");
				$("#ty_timeInterval").val("");
				$("#ty_maxPerson").val("");
				$("#ty_showClass").val("");
				$("#ty_memo").val("");
				
				$('.cover').fadeIn("slow");
				$('.timeIpt').val('');
				$('.cover-input').val('');
				$('#cove').show();
			});
			
			$('.btn-close').click(function(){
				$('.cover').hide();
				$('#cove').hide();
			});
			
			$('.btn-pri').click(function(){
				$('.cover').hide();
				$('#cove').hide();
				
				var defId = $("#ty_defId").val();
				var title = $("#ty_title").val();
				var timeInterval = $("#ty_timeInterval").val();
				var maxPerson = $("#ty_maxPerson").val();
				var showClass = $("#ty_showClass").val();
				var memo = $("#ty_memo").val();
				var reqData = {};
				reqData.defid =defId ;
				reqData.title = title;
				reqData.timeinterval = timeInterval;
				reqData.maxperson = maxPerson;
				reqData.showclass = showClass;
				reqData.memo = memo;
				$.ajax({
					url: base + '/mgr_addcalentrytypejson.html',
					data: reqData,
					type: 'POST',
					dataType: 'json',
					success: function (resp) {
						alert(resp.message);
					}
				});
				//TODO 待定
				location.reload();
				$('#calendar').fullCalendar( 'refetchEvents' );
				//$('#calendar').fullCalendar('updateEvent', event);
				//calendar.fullCalendar('updateEvent', true);
			});
			$('.btn-sm btn-success').click(function(){
				console.log("修info");
			});	
			
			$('.form-inline').click(function(){
				console.log("修info");
			});	
	},
	bindExtEvent:function(){
		 
		$('#external-events div.external-event').each(function() {
		
		// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
		// it doesn't need to have a start or end
		var eventObject = {
		title: $.trim($(this).text()) // use the element's text as the event title
		};
		/**/
		// store the Event Object in the DOM element so we can get to it later
		$(this).data('eventObject', eventObject);
		
		// make the event draggable using jQuery UI
		$(this).draggable({
			zIndex: 999,
			revert: true,      // will cause the event to go back to its
			revertDuration: 0  //  original position after the drag
		});
		});
		
	}
	,random: function(){
		var num = Math.random()*1000000000;
		return Math.floor(num);
	}
};

$(function(){ 
	Mycal.init();	
});