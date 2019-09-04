var base = $("#base").attr("href");
var oAppTable = null;

Appointment = {
	init:function(){
		this.initTable();
		this.bindEvent();
		
	},
	
	//用于医生信息的筛选
	alterOrgData:function(){
		console.log("进入医生筛选");
		var staffname = $("#qh_staffname").val();
		var reqData = {};
		reqData.staffname = staffname;
		$.ajax({
			url: base + '/ajaxstafflistjson.html',
			type: 'GET',
			data: reqData,
			dataType: 'json',
			success: function (resp) {
				if(resp.result == 1){
					var html = "";
					html += '<option value="0" selected="selected">全部</option>';
					for(var i=0;i<resp.data.length;i++){
						html += '<option value="'+resp.data[i].id+'">'+resp.data[i].name+'</option>';
					}
					$("#qh_staffid option").remove();
					$("#qh_staffid").append(html);
				}
			}
		});
	},
	
	initTable:function(){
		
		oAppointTable = $('#appointment-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/mgr_listappointmentjson.html",
			"columnDefs" :[
				{
				    "targets": 7,
				    "data": null,
				    "render": function(data, type, row) {
			    		return data[7].substring(5,16);
				    }
				},{
                    "targets": 8,
                    "data": null,
                    "render": function(data, type, row) {
                        return data[8].substring(5,16);
                    }
                },{
				    "targets": 9,
				    "data": null,
				    "render": function(data, type, row) {
				    	if(data[9] == "C")
				    		return "确认";
				    	else if(data[9] == "D")
                            return "拒绝";
                        else if(data[9] == "R")
				    		return "撤销";
                        else if(data[9] == "Y")
				    		return "待审核";
				    }
				},{
				    "targets": 10,
				    "data": null,
				    "render": function(data, type, row) {
				    	var html = "";
						if(data[9] == 'C'){//已确认，可修改信息
							html += '<button class="btn btn-xs btn-info" id="editappoint'+ data[0] + '"><i class="icon-cogs bigger-120">修改</i></button>';	
						}else if(data[9] == 'Y'){//有效申请 得审核
							html += '<button class="btn btn-xs btn-info" id="editappointstatus'+ data[0] + '"><i class="icon-cogs bigger-120">审核</i></button>';	
						}else if(data[9] == 'D'){//拒绝后，仍然可以修改
							html += '<button class="btn btn-xs btn-info" id="editappoint'+ data[0] + '"><i class="icon-cogs bigger-120">修改</i></button>';	
						}else if(data[9] == 'R'){//撤销不可再操作
							html += '';	
						}
						return html;
				    }
				}
			],
			"oLanguage": {
				"sLengthMenu": "每页显示 _MENU_ 条记录",
				"sZeroRecords": "抱歉， 没有找到",
				"sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
				"sInfoEmpty": "没有数据",
				"sInfoFiltered": "(从 _MAX_ 条数据中检索)",
				"oPaginate": {  
				    "sFirst": "首页",  
				    "sPrevious": "前一页",
				    "sNext": "后一页",  
				    "sLast": "尾页"  
				}, 
				"sZeroRecords": "没有检索到数据"  
			},
			"aLengthMenu": [[10, 15, 25], ["10", "15", "25"]],
			"aoColumns": [
			          	{ "sName": "id", "bSortable": false, "bVisible": true},
			          	{ "sName": "code", "bSortable": false, "bVisible": true,"bVisible": false},
						{ "sName": "用户名", "bSortable": false, "bVisible": true},
						{ "sName": "医生", "bSortable": false, "bVisible": true},
						{ "sName": "部门", "bSortable": false, "bVisible": true},
						{ "sName": "预约项目", "bSortable": false, "bVisible": true},
						{ "sName": "描述", "bSortable": false, "bVisible": true},
						{ "sName": "开始时间", "bSortable": false, "bVisible": true},
						{ "sName": "结束时间", "bSortable": false, "bVisible": true},
						{ "sName": "状态", "bSortable": false, "bVisible": true},
						{ "sName": "操作","bSortable": false, "bVisible": true}
			],
			"fnInitComplete": function() {
				$("#appointment-table_filter label").detach();
				var condition = "";
				condition += "<label>医生:<input type=\"text\" placeholder=\"关键字\" id=\"qh_staffname\" /></label>";
				condition += "<label><select id=\"qh_staffid\" name=\"qh_staffid\" style=\"width:100px;\">";
				condition += "<option value=\"\">全部</option>";
				condition += "</select></label>";
				condition += "<label>预约状态:<select id=\"qh_status\" name=\"qh_status\" style=\"width:100px;\">";				condition += "<option value=\"\">全部</option>";
				condition += '<option value="C">已确认</option>';
				condition += '<option value="Y">待审核</option>';
                condition += '<option value="D">拒绝</option>';
                condition += '<option value="R">已撤销</option>';
				condition += "</select></label>";
				condition += " <label><input type=\"button\" id=\"btnSearch\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnempty\" value=\"重置\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnadd\" readonly=\"readonly\" value=\"添加\"></label> ";
				
				$("#appointment-table_filter").append(condition);
				//Appointment.rebindEvent();
				
				//查询按钮  	
				$("#btnSearch").click(function(){
					console.log($("#qh_status").val());
					var u = base + '/mgr_listappointmentjson.html?a=a';
					u += "&name="+ $("#qh_name").val();
					u += "&status=" + $("#qh_status").val();
					console.log(u);
                    oAppointTable.ajax.url(u).load();
				});
				//重置按钮
				$("#btnempty").click(function(){
					$("#qh_name").val("");
					$("#qh_status").val("");
					var u = base + '/mgr_listAppointmentjson.html?status=Y';
					oAppointTable.ajax.url(u).load();
				});
				//TODO 添加按钮
				/*$("#btnadd").click(function(){
					$("#th_prsnid").val("");
					$("#th_srvid").val("");
					$("#th_deptid").val("");
					$("#th_stafid").val("");
					$("#th_beginday").val("");
					$("#th_endday").val("");
					$("#th_status").val("");
					$("#th_memo").val("");
					Appointment.addAppointmentDialog("添加签约");
				});*/
				
				$("#qh_staffname").change(function(){
					Appointment.alterOrgData();
				});
				
				Appointment.alterOrgData();
			}
		});
	},
	bindEvent:function(){
			$('#appointment-table').on('xhr.dt', function (e,settings,json,xhr) {
				setTimeout(Appointment.rebindEvent,300);
			});
	},
	
	rebindEvent:function(){

        //修改签约记录
		$("[id^='editappoint']").unbind().click(function(){
			var id = $(this).attr("id").substring("editappoint".length);
			console.log(id);
			$.ajax({
				url: base + '/mgr_getappointmentjson.html?id=' + id,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					Appointment.editAppointDialog("修改签约", resp.data,id);
				}
			});
		});
		
		//审核签约状态
		$("[id^='editappointstatus']").unbind().click(function(){
			var id = $(this).attr("id").substring("editappointstatus".length);
			$.ajax({
				url: base + '/mgr_getappointmentjson.html?id=' + id,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					Appointment.editStatusDialog("审核签约", resp.data,id);
				}
			});
		});
		
	},
	
	//修改签约记录基本信息
	editAppointDialog:function(titleMsg,appointData,id){
		console.log(appointData);
		if(appointData != undefined){		
			$('#th_status option[value="'+appointData.status+'"]').attr("selected","selected");
			$("#th_memo").val(appointData.memo);
		}

		$("#appointEditDialog").removeClass('hide').dialog({
			height:500, 
			width:600,
			modal: true,
			//title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='icon-ok'></i>新增课程</h4></div>",
			title: titleMsg,
			title_html: true,
			buttons: [
						{
							html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
							"class" : "btn btn-info btn-xs",
							click: function() {																		
								
								var status = $('#th_status option:selected').attr("value");
								var memo = $('#th_memo1').val();
								
								var reqData = {};
								reqData.id = id;							
								reqData.status = status;
								reqData.memo = memo;
								console.log(reqData);
								$.ajax({
									url: base + '/mgr_addormodifyappointjson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										alert(resp.message);
										var u = base + '/mgr_listappointmentjson.html?a=a';
										oAppointTable.ajax.url(u).load();
									}
								});
								$(this).dialog("close");
							}
								
						},{
							html: "<i class='icon-remove bigger-110'></i>&nbsp; 取消",
							"class" : "btn btn-xs",
							click: function() {
								$(this).dialog("close");
							}
						}
					]	
		});
	
	},
	//审核预约状态
	editStatusDialog:function(titleMsg,appointData,id){
		console.log(appointData);
		if(appointData != undefined){		
			$('#th_status option[value="'+appointData.status+'"]').attr("selected","selected");
			$("#th_memo").val(appointData.memo);
		}

		$("#appointEditDialog").removeClass('hide').dialog({
			height:500, 
			width:600,
			modal: true,
			//title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='icon-ok'></i>新增课程</h4></div>",
			title: titleMsg,
			title_html: true,
			buttons: [
						{
							html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
							"class" : "btn btn-info btn-xs",
							click: function() {																		
								
								var status = $('#th_status option:selected').attr("value");
								var memo = $('#th_memo1').val();
								
								var reqData = {};
								reqData.id = id;							
								reqData.status = status;
								reqData.memo = memo;
								console.log(reqData);
								$.ajax({
									url: base + '/mgr_modifyappointstatus.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										var u = base + '/mgr_listappointmentjson.html?a=a';
										oAppointTable.ajax.url(u).load();
										alert(resp.message);
									}
								});
								$(this).dialog("close");
							}
								
						},{
							html: "<i class='icon-remove bigger-110'></i>&nbsp; 取消",
							"class" : "btn btn-xs",
							click: function() {
								$(this).dialog("close");
							}
						}
					]	
		});
	
	}
};

$(function(){ 
	Appointment.init();	
});