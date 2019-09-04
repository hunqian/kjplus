var base = $("#base").attr("href");
var oIntegralTable = null;
var oIntegralListTable = null;
var curOrgid = 0;
var curDeptid = 0;
var curServid = 0;

Integral = {
	init:function(){
		this.initTable();
		this.bindEvent();
	},
	initTable:function(){
		oIntegralTable = $('#Integrallist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/mgr_listintegraljson.html",
			"columnDefs" :[/*
				{
				    "targets": 9,
				    "data": null,
				    "render": function(data, type, row) {
			    		return data[9].substring(0,10);
				    }
				},{
				    "targets": 18,
				    "data": null,
				    "render": function(data, type, row) {
				    	var html = "";
						if(data[15] == 'S'){
							html += '<button class="btn btn-xs btn-info" id="editstatus'+ data[0] + '"><i class="icon-cogs bigger-120">审核</i></button>';	
						}else{
							html += '<button class="btn btn-xs btn-info" id="editsrvassign'+ data[0] + '"><i class="icon-cogs bigger-120">修改</i></button>';	
						}
						return html;
				    }
				}
			*/],
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
			            { "sName": "id", "bSortable": false, "bVisible": true },  
			            { "sName": "积分ID", "bSortable": false, "bVisible": true }, 
			            { "sName": "用户名", "bSortable": false, "bVisible": true },
						{ "sName": "总赚取积分", "bSortable": false, "bVisible": true},
						{ "sName": "总消费积分", "bSortable": false, "bVisible": true},
						{ "sName": "剩余积分", "bSortable": false, "bVisible": true}
			],
			"fnInitComplete": function() {
				$("#Integrallist-table_filter label").detach();
				var condition = "";
				condition += "<label>用户类型:<select id=\"qh_bodytype\" name=\"qh_bodytype\" style=\"width:100px;\">";				
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="U">普通用户</option>';
				condition += '<option value="A">医生</option>';
				condition += "</select></label>";
				condition += " <label><input type=\"button\" id=\"btnSearch\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnempty\" value=\"重置\"></label> ";
				
				$("#Integrallist-table_filter").append(condition);
				Integral.rebindEvent();
				
				//查询按钮  
				$("#btnSearch").click(function(){
					var u = base + '/mgr_listintegraljson.html?a=a';
					u += "&name="+ $("#qh_name").val();
					u += "&bodyType=" + $("#qh_bodytype").val();
                    oIntegralTable.ajax.url(u).load();
				});
				//重置按钮
				$("#btnempty").click(function(){
					$("#qh_name").val("");
					$("#qh_bodytype").val("");
					var u = base + '/mgr_listintegraljson.html?a=a';
					oIntegralTable.ajax.url(u).load();
				});
			}
		});
		
		//积分详情
		oIntegralListTable = $('#integrallist-table').DataTable({

			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/mgr_listintegrallistjson.html",
			"columnDefs" :[
			               {
			            	   "targets": 3,
			            	   "data": null,
			            	   "render": function(data, type, row) {
			            		   return data[3].substring(0,16);
				    		}
			               	},{
								    "targets": 5,
								    "data": null,
								    "render": function(data, type, row) {
								    	if(data[5] == "Y")
								    		return "有效";
								    	else
								    		return "无效";
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
			"aLengthMenu": [[5, 10, 15], ["5", "10", "15"]],
			"aoColumns": [
			          	{ "sName": "id", "bSortable": false, "bVisible": true},
			          	{ "sName": "sumId", "bSortable": false, "bVisible": true},
			          	{ "sName": "使用项目", "bSortable": false, "bVisible": true},
						{ "sName": "时间", "bSortable": false, "bVisible": true},
						{ "sName": "积分数", "bSortable": false, "bVisible": true},
						{ "sName": "是否有效", "bSortable": false, "bVisible": true},
						{ "sName": "备注", "bSortable": false, "bVisible": true}
			],
			"fnInitComplete": function() {
				$("#integrallist-table_filter label").detach();				
				
			}
		});
		
	},
	bindEvent:function(){
		//积分数据详情显示
		$('#Integrallist-table tbody').on('click','tr', function () {
			console.log("Integrallist-table");
			var data = oIntegralTable.row(this).data();
			curSumId = data[0];
			console.log(curSumId);
			var u = base + '/mgr_getintegrallistjson.html?sumId='+curSumId;
			oIntegralListTable.ajax.url(u).load();
		});
		
		//服务积分汇总 	页面刷新
		$('#Integrallist-table').on('xhr.dt', function (e,settings,json,xhr) {
			setTimeout(Integral.rebindEvent,300);
		});
		
		
	},
	rebindEvent:function(){

        //一些需要重新绑定的事件
		//级联显示部门列表
        $("#th_prsnid").unbind().change(function(){
            var prsnid = $('#th_prsnid option:selected').attr("value");
            console.log(prsnid);
            $.ajax({
                url: base + '/getprsnbyidjson.html?prsnid=' + prsnid,
                type: 'GET',
                dataType: 'json',
                success: function (resp) {
                	var orgId = resp.orgid;
                    curOrgid = orgId;
                    $.ajax({
                        url: base + '/mgr_deptlistjson.html?orgId=' + orgId,
                        type: 'GET',
                        dataType: 'json',
                        success: function (resp) {
                            var aaData=resp.aaData;
                            var html = "";
                            html += "<option value=\"\">全部</option>";
                            for(var i=0;i<aaData.length;i++){
                                html += '<option value="' + aaData[i][0] + '">' + aaData[i][3]+ '</option>';
                            }
                            $("#th_deptid option").remove();
                            $("#th_deptid").append(html);
                            //$('#th_deptid option[value="' + curDeptid + '"]').attr("selected", "selected");
                            
                        }
                    });

                    $.ajax({
                        url: base + '/mgr_servcatglistjson.html?orgid=' + orgId,
                        type: 'GET',
                        dataType: 'json',
                        success: function (resp) {
                            var data=resp.data;
                            var html = "";
                            html += "<option value=\"\">全部</option>";
                            for(var i=0;i<data.length;i++){
                                html += '<option value="' + data[i][0] + '">' + data[i][2]+ '</option>';
                            }
                            $("#th_srvid option").remove();
                            $("#th_srvid").append(html);
                        }
                    });
                }
            });
        });

        $("#th_deptid").unbind().change(function(){
            var deptid = $('#th_deptid option:selected').attr("value");
            $.ajax({
                url: base + '/mgr_stafflistjson.html?deptid=' + deptid +'&orgid = '+ curOrgid,
                type: 'GET',
                dataType: 'json',
                success: function (resp) {
                    var data=resp.aaData;
                    console.log(data);
                    var html = "";
                    html += "<option value=\"\">全部</option>";
                    for(var i=0;i<data.length;i++){
                        html += '<option value="' + data[i][0] + '">' + data[i][2]+ '</option>';
                    }
                    $("#th_stafid option").remove();
                    $("#th_stafid").append(html);

                }
            });
        });
        
        $("#th_prsnid1").unbind().change(function(){
            var prsnid = $('#th_prsnid1 option:selected').attr("value");
            console.log(prsnid);
            $.ajax({
                url: base + '/getprsnbyidjson.html?prsnid=' + prsnid,
                type: 'GET',
                dataType: 'json',
                success: function (resp) {
                	var orgId = resp.orgid;
                    curOrgid = orgId;
                    $.ajax({
                        url: base + '/mgr_deptlistjson.html?orgId=' + orgId,
                        type: 'GET',
                        dataType: 'json',
                        success: function (resp) {
                            var aaData=resp.aaData;
                            var html = "";
                            html += "<option value=\"\">全部</option>";
                            for(var i=0;i<aaData.length;i++){
                                html += '<option value="' + aaData[i][0] + '">' + aaData[i][3]+ '</option>';
                            }
                            $("#th_deptid1 option").remove();
                            $("#th_deptid1").append(html);
                            //$('#th_deptid option[value="' + curDeptid + '"]').attr("selected", "selected");
                            
                        }
                    });

                    $.ajax({
                        url: base + '/mgr_servcatglistjson.html?orgid=' + orgId,
                        type: 'GET',
                        dataType: 'json',
                        success: function (resp) {
                            var data=resp.data;
                            var html = "";
                            html += "<option value=\"\">全部</option>";
                            for(var i=0;i<data.length;i++){
                                html += '<option value="' + data[i][0] + '">' + data[i][2]+ '</option>';
                            }
                            $("#th_srvid1 option").remove();
                            $("#th_srvid1").append(html);
                        }
                    });
                }
            });
        });

        $("#th_deptid1").unbind().change(function(){
            var deptid = $('#th_deptid1 option:selected').attr("value");
            $.ajax({
                url: base + '/mgr_stafflistjson.html?deptid=' + deptid +'&orgid = '+ curOrgid,
                type: 'GET',
                dataType: 'json',
                success: function (resp) {
                    var data=resp.aaData;
                    console.log(data);
                    var html = "";
                    html += "<option value=\"\">全部</option>";
                    for(var i=0;i<data.length;i++){
                        html += '<option value="' + data[i][0] + '">' + data[i][2]+ '</option>';
                    }
                    $("#th_stafid1 option").remove();
                    $("#th_stafid1").append(html);

                }
            });
        });

        //修改签约记录
		$("[id^='editsrvassign']").unbind().click(function(){
			var id = $(this).attr("id").substring("editsrvassign".length);
			console.log(id);
			$.ajax({
				url: base + '/mgr_getIntegraljson.html?id=' + id,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					console.log(resp);
					Integral.editIntegralDialog("修改签约", resp.data,id);
				}
			});
		});
		
		//审核签约状态
		$("[id^='editstatus']").unbind().click(function(){
			var id = $(this).attr("id").substring("editstatus".length);
			$.ajax({
				url: base + '/mgr_getIntegraljson.html?id=' + id,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					Integral.editStatusDialog("审核签约", resp.data,id);
				}
			});
		});
		
	},
	//添加签约记录
	addIntegralDialog:function(titleMsg){
		$("#IntegralAddDialog").removeClass('hide').dialog({
			height:600, 
			width:600,
			modal: true,
			title: titleMsg,
			title_html: true,
			buttons: [
						{
							html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
							"class" : "btn btn-info btn-xs",
							click: function() {
								
								var prsnId = $('#th_prsnid option:selected').attr("value");
								var srvIds = [];
								$("#th_srvid option:selected").each(function(){
						            console.log($(this).attr("value"));
						            srvIds.push($(this).attr("value"));
						        });
								/*console.log(srvIds);
								var srvIdStr = {};
								for(var i=0;i<srvIds.length;i++){
									if(i!=0)
										srvIdStr + ',';
									srvIdStr + srvIds[i];
								}
								console.log(srvIdStr);*/
								
								var deptId = $('#th_deptid option:selected').attr("value");
								var stafId = $('#th_stafid option:selected').attr("value");
								var beginDay = $("#th_beginday").val();
								var endDay = $("#th_endday").val();
								//TODO 名称修订
								var status = $('#th_status2 option:selected').attr("value");
								var memo = $("#th_memo2").val();
								
								var reqData = {};
								reqData.prsnId = prsnId;
								reqData.srvIdStr = srvIds.join();
								reqData.deptId = deptId;
								reqData.stafId = stafId;
								reqData.beginDay = beginDay;
								reqData.endDay = endDay;
								reqData.status = status;
								reqData.memo = memo;
								console.log(reqData);
								$.ajax({
									url: base + '/mgr_addIntegraljson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										var u = base + '/mgr_listIntegraljson.html?status=Y&a=a';
										oIntegralTable.ajax.url(u).load();
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
	
	},
	//修改签约记录基本信息
	editIntegralDialog:function(titleMsg,IntegralData,id){
		console.log(IntegralData);
		if(IntegralData != undefined){
			//数据回显
			$("#th_opertime1").val(IntegralData.operTime);
			$('#th_prsnid1 option[value="'+IntegralData.prsnId+'"]').attr("selected","selected");
			//$("#th_prsnid1").trigger("change",IntegralData.prsnId);
			$('#th_srvid1 option[value="'+IntegralData.srvId+'"]').attr("selected","selected");
			$('#th_deptid1 option[value="'+IntegralData.deptId+'"]').attr("selected","selected");
			$('#th_stafid1 option[value="'+IntegralData.stafId+'"]').attr("selected","selected");
			$("#th_beginday1").val(IntegralData.beginDay);
			$("#th_endday1").val(IntegralData.endDay);
			
			$('#th_status1 option[value="'+IntegralData.status+'"]').attr("selected","selected");
			$("#th_memo1").val(IntegralData.memo);
		}

		$("#IntegralEditDialog").removeClass('hide').dialog({
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
								//var prsnId1 = $('#th_prsnid1 option:selected').attr("value");
								var srvId1 = $('#th_srvid1 option:selected').attr("value");
								//var deptId1 = $('#th_deptid1 option:selected').attr("value");
								//var stafId1 = $('#th_stafid1 option:selected').attr("value");
								//var operTime1 = $('#th_opertime1 option:selected').attr("value");
								var endDay = $('#th_endday1').val();
								var status = $('#th_status1 option:selected').attr("value");
								var memo = $('#th_memo1').val();
								
								var reqData = {};
								reqData.servAsgnId = id;
								//reqData.prsnId = prsnId1;
								reqData.srvId = srvId1;
								//reqData.deptId = deptId1;
								//reqData.stafId = stafId1;
								//reqData.operTime = operTime1;
								reqData.endDay = endDay;
								reqData.status = status;
								reqData.memo = memo;
								console.log(reqData);
								$.ajax({
									url: base + '/mgr_modifyIntegraljson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										var u = base + '/mgr_listIntegraljson.html?status=Y&a=a';
										oIntegralTable.ajax.url(u).load();
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
	
	},
	//审核签约状态
	editStatusDialog:function(titleMsg,IntegralData,id){
		if(IntegralData != undefined){
			$("#th_memo").val(IntegralData.memo);
			$('#th_status option[value="'+IntegralData.status+'"]').attr("selected","selected");

		}

		$("#servStatusDialog").removeClass('hide').dialog({
			height:350, 
			width:500,
			modal: true,
			//title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='icon-ok'></i>新增课程</h4></div>",
			title: titleMsg,
			title_html: true,
			buttons: [
						{
							html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
							"class" : "btn btn-info btn-xs",
							click: function() {
								var memo = $("#th_memo").val();
								var status = $('#th_status option:selected').attr("value");
							
								var reqData = {};
								reqData.servAsgnId = id;
								reqData.memo = memo;
								reqData.status = status;
								console.log(reqData);
								$.ajax({
									url: base + '/mgr_editIntegraljson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										console.log(resp);
										var u = base + '/mgr_listIntegraljson.html?status=Y';
										oIntegralTable.ajax.url(u).load();
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
	Integral.init();
});