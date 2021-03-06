var base = $("#base").attr("href");
var eventObject =null;
var calendar = null;
Canlendar = {
		
	init:function(){
		this.initTable();
		//this.initData();
		this.bindEvent();
	},
	
	initTable:function(){
		console.log("init");
		/* initialize the external events
			-----------------------------------------------------------------*/
		jQuery(function($) {
			$('#external-events div.external-event').each(function() {

				eventObject = {
					title: $.trim($(this).text()) // use the element's text as the event title
				};
				$(this).data('eventObject', eventObject);
				$(this).draggable({
					zIndex: 999,
					revert: true,      // will cause the event to go back to its
					revertDuration: 0  //  original position after the drag
				});
				
			});
			/* initialize the calendar
			-----------------------------------------------------------------*/
			var date = new Date();
			var d = date.getDate();
			var m = date.getMonth();
			var y = date.getFullYear();

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
				events: [
				{
					title: 'All Day Event1111',
					start: new Date(y, m, 1),
					className: 'label-important'
				},
				{
					title: 'Long Event222',
					start: new Date(y, m, d-5),
					end: new Date(y, m, d-2),
					className: 'label-success'
				},
				{
					title: 'Some Event333',
					start: new Date(y, m, d-3, 16, 0),
					allDay: false
				}]
				,
				editable: true,
				droppable: true, // this allows things to be dropped onto the calendar !!!
				drop: function(date, allDay) { // this function is called when something is dropped
					// retrieve the dropped element's stored Event Object
					//时间信息
					var originalEventObject = $(this).data('eventObject');
					//时间信息
					var $extraEventClass = $(this).attr('data-class');
					
					
					// we need to copy it, so that multiple events don't have a reference to the same object
					var copiedEventObject = $.extend({}, originalEventObject);
					
					// assign it the date that was reported
					copiedEventObject.start = date;
					copiedEventObject.allDay = allDay;
					if($extraEventClass) copiedEventObject['className'] = [$extraEventClass];
					
					// render the event on the calendar
					// the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
					
					$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
					
					// is the "remove after drop" checkbox checked?
					
					if ($('#drop-remove').is(':checked')) {
						// if so, remove the element from the "Draggable Events" list
						$(this).remove();
					}
					
				}
				,
				selectable: true,
				selectHelper: true,
				select: function(start, end, allDay) {
					console.log("222"+title);
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
				,
				eventClick: function(calEvent, jsEvent, view) {

					var form = $("<form class='form-inline'><label>Change event name &nbsp;</label></form>");
					form.append("<input class='middle' autocomplete=off type=text value='" + calEvent.title + "' /> ");
					form.append("<button type='submit' class='btn btn-sm btn-success'><i class='icon-ok'></i> Save</button>");
					
					var div = bootbox.dialog({
						message: form,
					
						buttons: {
							"delete" : {
								"label" : "<i class='icon-save'></i> Delete Event",
								"className" : "btn-sm btn-info",
								"callback": function() {
									calendar.fullCalendar('removeEvents' , function(ev){
										return (ev._id == calEvent._id);
									})
								}
							} ,
							"close" : {
								"label" : "<i class='icon-remove'></i> Close",
								"className" : "btn-sm"
							} 
						}

					});
					
					form.on('submit', function(){
						calEvent.title = form.find("input[type=text]").val();
						calendar.fullCalendar('updateEvent', calEvent);
						div.modal("hide");
						return false;
					});
					
				
					//console.log(calEvent.id);
					//console.log(jsEvent);
					//console.log(view);
					// change the border color just for fun
					$(this).css('border-color', 'red');
				}
			});
		})
},
	bindEvent:function(){},
	rebindEvent:function(){},
};

$(function(){ 
	Canlendar.init();
});