var base = $("#base").attr("href");
var oServAssTable = null;
var curOrgid = 0;
var curDeptid = 0;
var curServid = 0;

ServAss = {
	init:function(){
		this.initTable();
		this.initData();
		this.bindEvent();
	},
	initTable:function(){
		oServAssTable = $('#servasslist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/mgr_servasslistjson.html",
			"columnDefs" :[
				{
				    "targets": 11,
				    "data": null,
				    "render": function(data, type, row) {
				    	if(data[11] == "R")
				    		return "实体";
				    	else
				    		return "虚拟";
				    }
				},{
				    "targets": 17,
				    "data": null,
				    "render": function(data, type, row) {
			    		return data[17].substring(5,16);
				    }
				},{
                    "targets": 21,
                    "data": null,
                    "render": function(data, type, row) {
                        return data[21].substring(5,16);
                    }
                },{
                    "targets": 22,
                    "data": null,
                    "render": function(data, type, row) {
                        return data[22].substring(5,16);
                    }
                },{
				    "targets": 18,
				    "data": null,
				    "render": function(data, type, row) {
				    	if(data[18] == "S")
				    		return "申请";
				    	else if(data[18] == "R")
                            return "驳回";
                        else
				    		return "确认";
				    }
				},{
				    "targets": 23,
				    "data": null,
				    "render": function(data, type, row) {
				    	var html = "";
						html += '<button class="btn btn-xs btn-danger" id="editservass'+ data[0] + '"><i class="icon-cogs bigger-120">修改</i></button>';
						if(data[18] == 'S'){
							html += '<button class="btn btn-xs btn-danger" id="editstatus'+ data[0] + '"><i class="icon-cogs bigger-120">审核</i></button>';	
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
			"aLengthMenu": [[5, 10, 15], ["5", "10", "15"]],
			"aoColumns": [
			          	{ "sName": "ID", "bSortable": false, "bVisible": true},
			          	{ "sName": "居民ID", "bSortable": false, "bVisible": false},
						{ "sName": "居民姓名", "bSortable": false, "bVisible": true},
						{ "sName": "手机号码", "bSortable": false, "bVisible": true},
						{ "sName": "医生ID", "bSortable": false, "bVisible": false},
						{ "sName": "医生编码", "bSortable": false, "bVisible": false},
						{ "sName": "医生姓名", "bSortable": false, "bVisible": true},
						{ "sName": "医生手机号码", "bSortable": false, "bVisible": true},
						{ "sName": "组织ID", "bSortable": false, "bVisible": false},
						{ "sName": "组织名称", "bSortable": false, "bVisible": true},
						{ "sName": "科室ID", "bSortable": false, "bVisible": false},
						{ "sName": "科室类型", "bSortable": false, "bVisible": true},
						{ "sName": "科室名称", "bSortable": false, "bVisible": true},
						{ "sName": "服务ID", "bSortable": false, "bVisible": false},
						{ "sName": "服务编号", "bSortable": false, "bVisible": false},
						{ "sName": "服务名称", "bSortable": false, "bVisible": true},
						{ "sName": "服务价格", "bSortable": false, "bVisible": true},
						{ "sName": "请求时间", "bSortable": false, "bVisible": true},
						{ "sName": "申请状态", "bSortable": false, "bVisible": true},
                		{ "sName": "审核结果", "bSortable": false, "bVisible": true},
						{ "sName": "操作时间", "bSortable": false, "bVisible": true},
						{ "sName": "服务开始时间", "bSortable": false, "bVisible": true},
						{ "sName": "服务结束时间", "bSortable": false, "bVisible": true},
						{ "sName": "操作", "bSortable": false, "bVisible": true}

			],
			"fnInitComplete": function() {
				$("#servasslist-table_filter label").detach();
				var condition = "";
				condition = "<label>姓名:<input type=\"text\" placeholder=\"关键字\" id=\"qh_name\" /></label>"; //第一种
				condition += "<label>组织:<select id=\"qh_orgid\" name=\"qh_orgid\" style=\"width:120px;\">";
				condition += "</select></label>";
				condition += "<label>状态:<select id=\"info_status\" name=\"qh_status\" style=\"width:100px;\">";//第二种
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="S">申请</option>';
				condition += '<option value="R">驳回</option>';
                condition += '<option value="Y">确认</option>';
				condition += "</select></label>";
				condition += " <label><input type=\"button\" id=\"btnSearch\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnempty\" value=\"重置\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnadd\" value=\"添加\"></label> ";
				
				$("#servasslist-table_filter").append(condition);
				ServAss.rebindEvent();
				
				//查询按钮  
				$("#btnSearch").click(function(){
					var u = base + '/mgr_servasslistjson.html?a=a';
					u += "&name="+ $("#qh_name").val();
					u += "&status=" + $("#qh_status").val();
					u += "&orgId="+ $('#qh_orgid option:selected').attr("value");
                    oServAssTable.ajax.url(u).load();
				});
				//重置按钮
				$("#btnempty").click(function(){
					$("#qh_name").val("");
					$("#qh_status").val("");
					$("#qh_orgid").val("");
					$("#qh_orgid").val("");
					var u = base + '/mgr_servasslistjson.html';
					oServAssTable.ajax.url(u).load();
				});
				//添加按钮
				$("#btnadd").click(function(){
					$("#th_title").val("");
					$("#th_catgid").val("");
					$("#th_pubid").val("");
					$("#th_infotype").val("");
					$("#th_deptid").val("");
					$("#th_orgid").val("");
                    ServAss.addServAssDialog("增加签约记录");
				});
                ServAss.initData();
			}
		});
	},
	initData:function(){
		// 初始化组织列表
		$.ajax({
			url: base + '/mgr_orglistjson.html',
			type: 'GET',
			dataType: 'json',
			success: function (resp) {
				var html = "";
				html += "<option value=\"\">全部</option>";
				for(var i=0;i<resp.aaData.length;i++){
					html += '<option value="' + resp.aaData[i][0] + '">' + resp.aaData[i][2] + '</option>';
				}
				$("#qh_orgid option").remove();
				$("#qh_orgid").append(html);
                ServAss.rebindEvent();
			}
		});
	},
	bindEvent:function(){
		
		$('#servasslist-table').on('xhr.dt', function (e,settings,json,xhr) {
			setTimeout(ServAss.rebindEvent,300);
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
		$("[id^='editservass']").unbind().click(function(){
			var id = $(this).attr("id").substring("editservass".length);
			$.ajax({
				url: base + '/getservassjson.html?id=' + id,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					ServAss.editServAssDialog("修改签约", resp.data,id);
				}
			});
		});
		//审核签约状态
		$("[id^='editstatus']").unbind().click(function(){
			var id = $(this).attr("id").substring("editstatus".length);
			$.ajax({
				url: base + '/getservassjson.html?id=' + id,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					ServAss.editStatusDialog("审核", resp.data,id);
				}
			});
		});
		
	},
	addServAssDialog:function(titleMsg){
		$("#servAssAddDialog").removeClass('hide').dialog({
			height:600, 
			width:600,
			modal: true,
			title: titleMsg,
			title_html: true,
			buttons: [
						{
							html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
							"class" : "btn btn-danger btn-xs",
							click: function() {
								var prsnId = $('#th_prsnid option:selected').attr("value");
								var srvId = $('#th_srvid option:selected').attr("value");
								var deptId = $('#th_deptid option:selected').attr("value");
								var stafId = $('#th_stafid option:selected').attr("value");
								var reqData = {};
								reqData.prsnId = prsnId;
								reqData.srvId = srvId;
								reqData.deptId = deptId;
								reqData.stafId = stafId;
								$.ajax({
									url: base + '/addormodifyservassjson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										var u = base + '/mgr_servasslistjson.html?a=a';
										oServAssTable.ajax.url(u).load();
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
	editServAssDialog:function(titleMsg,servAssData,id){
		if(servAssData != undefined){
			
			$("#th_opertime1").val(servAssData.operTime);
			$("#th_memo1").val(servAssData.memo);
			$('#th_prsnid1 option[value="'+servAssData.prsnId+'"]').attr("selected","selected");
			$('#th_srvid1 option[value="'+servAssData.srvId+'"]').attr("selected","selected");
			$('#th_deptid1 option[value="'+servAssData.deptId+'"]').attr("selected","selected");
			$('#th_stafid1 option[value="'+servAssData.stafId+'"]').attr("selected","selected");
		}

		$("#servAssEditDialog").removeClass('hide').dialog({
			height:600, 
			width:600,
			modal: true,
			//title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='icon-ok'></i>新增课程</h4></div>",
			title: titleMsg,
			title_html: true,
			buttons: [
						{
							html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
							"class" : "btn btn-danger btn-xs",
							click: function() {
								var prsnId1 = $('#th_prsnid1 option:selected').attr("value");
								var srvId1 = $('#th_srvid1 option:selected').attr("value");
								var deptId1 = $('#th_deptid1 option:selected').attr("value");
								var stafId1 = $('#th_stafid1 option:selected').attr("value");
								var operTime1 = $('#th_opertime1 option:selected').attr("value");
							
								var reqData = {};
								reqData.servAsgnId = id;
								reqData.prsnId = prsnId1;
								reqData.srvId = srvId1;
								reqData.deptId = deptId1;
								reqData.stafId = stafId1;
								reqData.operTime = operTime1;
							
								$.ajax({
									url: base + '/addormodifyservassjson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										var u = base + '/mgr_servasslistjson.html?a=a';
										oServAssTable.ajax.url(u).load();
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
	editStatusDialog:function(titleMsg,servAssData,id){
		if(servAssData != undefined){
			$("#th_memo").val(servAssData.memo);
			$('#th_status option[value="'+servAssData.status+'"]').attr("selected","selected");

		}

		$("#servStatusDialog").removeClass('hide').dialog({
			height:600, 
			width:600,
			modal: true,
			//title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='icon-ok'></i>新增课程</h4></div>",
			title: titleMsg,
			title_html: true,
			buttons: [
						{
							html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
							"class" : "btn btn-danger btn-xs",
							click: function() {
								var memo = $("#th_memo").val();
								var status = $('#th_status option:selected').attr("value");
							
								var reqData = {};
								reqData.servAsgnId = id;
								reqData.memo = memo;
								reqData.status = status;
							
								$.ajax({
									url: base + '/editstatusjson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										var u = base + '/mgr_servasslistjson.html?a=a';
										oServAssTable.ajax.url(u).load();
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
	ServAss.init();
});