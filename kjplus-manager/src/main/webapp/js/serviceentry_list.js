var base = $("#base").attr("href");
var oServerEntryTable = null;
var curEntryId = "";
var curEntryCode = "";

Server = {
	init:function(){
		this.initTable();
		this.bindEvent();
		this.initData();
	},
	initTable:function(){
		oServerEntryTable = $('#serventrylist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/mgr_srventrylistjson.html",
			"columnDefs" :[
					{
					    "targets": 2,
					    "data": null,
					    "render": function(data, type, row) {
					    	if(data[2] == "S")
					    		return "服务类";
					    	else
					    		return "其他类";
					    	}
					    },{
				    "targets": 7,
				    "data": null,
				    "render": function(data, type, row) {
				    	if(data[7] == "Y")
				    		return "有效";
				    	else
				    		return "无效";
				    	}
				    },{
					    "targets": 11,
					    "data": null,
					    "render": function(data, type, row) {
				    		return data[11].substring(5,16);
					    }
					},{
				    "targets": 14,
				    "data": null,
				    "render": function(data, type, row) {
				    	var html = "";
				    	if(data[1] == "0")
				    		html += '<button class="btn btn-xs btn-info" id="addserventry'+ data[0] + '"><i class="icon-cogs bigger-120">添子</i></button>';
						html += '<button class="btn btn-xs btn-info" id="editserventry'+ data[3] + '"><i class="icon-cogs bigger-120">修改</i></button>';
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
			"aLengthMenu": [[10, 20, 25], ["10", "20", "25"]],
			"aoColumns": [
			            { "sName": "ID", "bSortable": false, "bVisible": true},
			            { "sName": "PID", "bSortable": false, "bVisible": true},
			          	{ "sName": "类别", "bSortable": false, "bVisible": true},
						{ "sName": "编码", "bSortable": false, "bVisible": false},
						{ "sName": "名称", "bSortable": false, "bVisible": true},
						{ "sName": "参照编码", "bSortable": false, "bVisible": false},
						{ "sName": "服务类型", "bSortable": false, "bVisible": true},
						{ "sName": "状态", "bSortable": false, "bVisible": true},
						{ "sName": "描述", "bSortable": false, "bVisible": true},
						{ "sName": "接收方可得积分", "bSortable": false, "bVisible": true},
						{ "sName": "可获积分", "bSortable": false, "bVisible": true},
						{ "sName": "创建时间", "bSortable": false, "bVisible": true},
						{ "sName": "组织编码", "bSortable": false, "bVisible": false},
						{ "sName": "组织名称", "bSortable": false, "bVisible": false},
						{ "sName": "操作", "bSortable": false, "bVisible": true}
						
			],
			"fnInitComplete": function() {
				$("#serventrylist-table_filter label").detach();				
				var condition = "";
				condition = "<label>名称:<input type=\"text\" placeholder=\"请输入服务名称\" id=\"qh_servname\" /></label>"; 
				condition += "<label>类别:<select id=\"qh_servtype\" name=\"qh_servtype\" style=\"width:60px;\">";
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="S">服务类</option>';
				condition += '<option value="M">其它</option>';
				condition += "</select></label>";
				condition += "<label>类型:<select id=\"qh_cfgid\" name=\"qh_cfgid\" style=\"width:120px;\">";
				condition += "</select></label>";
				condition += "<label>状态:<select id=\"qh_status\" name=\"qh_status\" style=\"width:60px;\">";
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="Y">有效</option>';
				condition += '<option value="N">无效</option>';
				condition += "</select></label>";
				condition += " <label><input type=\"button\" id=\"btnSearch\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnempty\" value=\"重置\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnadd\" value=\"添加\"></label> ";
				
				$("#serventrylist-table_filter").append(condition);
				Server.initData();
				
				$("#btnSearch").click(function(){
					var u = base + '/mgr_srventrylistjson.html?a=a';
					u += "&name=" + $("#qh_servname").val();
					u += "&type=" + $("#qh_servtype").val();
					u += "&typeId="+ $('#qh_cfgid option:selected').attr("value");
					u += "&status=" + $("#qh_status").val();
					oServerEntryTable.ajax.url(u).load();
				});
				$("#btnempty").click(function(){
					$("#qh_servname").val("");
					$("#qh_servtype").val("");
					$("#qh_status").val("");
					$("#qh_cfgid").val("");
					var u = base + '/mgr_srventrylistjson.html';
					oServerEntryTable.ajax.url(u).load();
				});
				$("#btnadd").click(function(){
					$("#th_serventryname").val("");
					$("#th_serventrytype").val("");
					$("#th_refVId").val("");
					$("#th_status").val("");
					$("#th_serventrymemo").val("");
					Server.addServEntryDialog(0);
				});
			}
		});

	},
	initData:function(){
		// 初始化类型列表 服务类型
		$.ajax({
			url: base + '/mgr_refvaluelistjson.html?refcode=RT_SRVCATG',
			type: 'GET',
			dataType: 'json',
			success: function (resp) {
				console.log(resp);
				var html = "";
				html += "<option value=\"\">全部</option>";
				for(var i=0;i<resp.aaData.length;i++){
					html += '<option value="' + resp.aaData[i][0] + '">' + resp.aaData[i][3] + '</option>';
				}
				$("#qh_cfgid option").remove();
				$("#qh_cfgid").append(html);
				Server.rebindEvent();
			}
		});
	},
	bindEvent:function(){

		$('#serventrylist-table').on('xhr.dt', function (e,settings,json,xhr) {
			setTimeout(Server.rebindEvent,300);
		});
		
	},
	rebindEvent:function(){
		
		//添加子服务
		$("[id^='addserventry']").unbind().click(function(){
			var id = $(this).attr('id').substring("addserventry".length);
			Server.addServEntryDialog(id);
		});
		//修改服务信息
		$("[id^='editserventry']").unbind().click(function(){
			var code = $(this).attr('id').substring("editserventry".length);
			Server.editServEntryDialog(code);
		});
		
	},
	editServEntryDialog:function(code){
		$.ajax({
			url: base + '/mgr_getservEntryjson.html?code=' + code,
			type: 'POST',
			dataType: 'json',
			success: function (resp) {
				console.log(resp);
				var data = resp.data;
				$("#th_serventryname").val(data.srvName);
				$("#th_serventrytype").val(data.srvType);
				$('#th_refVId option[value="'+data.srvTypeId+'"]').attr("selected","selected");
				$('#th_status option[value="'+data.status+'"]').attr("selected","selected");
				$("#th_serventrymemo").val(data.srvMemo);
				$("#th_acceptorpoint").val(data.acceptorpoint);
				$("#th_providerpoint").val(data.providerpoint);
				
				$("#servEntryAddDialog").removeClass('hide').dialog({
					height:600, 
					width:600,
					modal: true,
					title: "修改服务",
					title_html: true,
					buttons: [
						{
							html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
							"class" : "btn btn-info btn-xs",
							click: function() {
								var serventryname = $("#th_serventryname").val();
								var serventrytype = $("#th_serventrytype").val();
								var refid = $('#th_refVId option:selected').attr("value");
								var status = $("#th_status").val();
								var serventrymemo = $("#th_serventrymemo").val();
								var acceptorpoint = $("#th_acceptorpoint").val();
								var providerpoint = $("#th_providerpoint").val();
								var reqData = {};
								reqData.code = code;
								reqData.serventryname = serventryname;
								reqData.serventrytype = serventrytype;
								reqData.refid = refid;
								reqData.status = status;
								reqData.serventrymemo = serventrymemo;
								reqData.acceptorpoint = acceptorpoint;
								reqData.providerpoint = providerpoint;
								$.ajax({
									url: base + '/mgr_addormodifyserventryjson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										var u = base + '/mgr_srventrylistjson.html?a=a';
										oServerEntryTable.ajax.url(u).load();
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
		});
	},
	addServEntryDialog:function(id){//添加服务，默认添加配置
		//清楚数据
		$("#th_serventryname").val("");
		$("#th_serventrytype").val("");
		$("#th_refVId").val("");
		$("#th_status").val("");
		$("#th_serventrymemo").val("");
		$("#th_acceptorpoint").val("0");
		$("#th_providerpoint").val("0");
		
		$("#servEntryAddDialog").removeClass('hide').dialog({
			height:600, 
			width:600,
			modal: true,
			//title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='icon-ok'></i>'"+新增课程+"'</h4></div>",
			title: "新增服务",
			title_html: true,
			buttons: [
						{
							html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
							"class" : "btn btn-info btn-xs",
							click: function() {
								var pid = id;
								var serventryname = $("#th_serventryname").val();
								var serventrytype = $("#th_serventrytype").val();
								var refid = $('#th_refVId option:selected').attr("value");
								var status = $("#th_status").val();
								var serventrymemo = $("#th_serventrymemo").val();
								var acceptorpoint = $("#th_acceptorpoint").val();
								var providerpoint = $("#th_providerpoint").val();
								
								var reqData = {};
								reqData.serventryname = serventryname;
								reqData.serventrytype = serventrytype;
								reqData.refid = refid;
								reqData.status = status;
								reqData.serventrymemo = serventrymemo;
								reqData.pid = pid;
								reqData.acceptorpoint = acceptorpoint;
								reqData.providerpoint = providerpoint;
								console.log(reqData);
								$.ajax({
									url: base + '/mgr_addormodifyserventryjson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										var u = base + '/mgr_srventrylistjson.html?a=a';
										oServerEntryTable.ajax.url(u).load();
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
	Server.init();	
});