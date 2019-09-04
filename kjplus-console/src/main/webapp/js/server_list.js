var base = $("#base").attr("href");
var oServerEntryTable = null;
var oServerConfigTable = null;
var curOrgCode = "";
var curEntryId = "";
var curEntryCode = "";

Server = {
	init:function(){
		this.initTable();
		this.initData();
		this.bindEvent();
	},
	initTable:function(){
		oServerEntryTable = $('#serventrylist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/mgr_serventrylistjson.html",
			"columnDefs" :[
					{
					    "targets": 1,
					    "data": null,
					    "render": function(data, type, row) {
					    	if(data[1] == "S")
					    		return "服务类";
					    	else
					    		return "其他类";
					    	}
					    },{
				    "targets": 6,
				    "data": null,
				    "render": function(data, type, row) {
				    	if(data[6] == "Y")
				    		return "有效";
				    	else
				    		return "无效";
				    	}
				    },{
					    "targets": 8,
					    "data": null,
					    "render": function(data, type, row) {
				    		return data[8].substring(5,16);
					    }
					},{
				    "targets": 11,
				    "data": null,
				    "render": function(data, type, row) {
				    	var html = "";
						html += '<button class="btn btn-xs btn-info" id="addserventry'+ data[0] + '"><i class="icon-cogs bigger-120">添加</i></button>';
						html += '<button class="btn btn-xs btn-info" id="editserventry'+ data[2] + '"><i class="icon-cogs bigger-120">修改</i></button>';
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
			          	{ "sName": "类别", "bSortable": false, "bVisible": true},
						{ "sName": "编码", "bSortable": false, "bVisible": false},
						{ "sName": "名称", "bSortable": false, "bVisible": true},
						{ "sName": "参照编码", "bSortable": false, "bVisible": false},
						{ "sName": "参照类型", "bSortable": false, "bVisible": true},
						{ "sName": "状态", "bSortable": false, "bVisible": true},
						{ "sName": "描述", "bSortable": false, "bVisible": true},
						{ "sName": "创建时间", "bSortable": false, "bVisible": true},
						{ "sName": "组织编码", "bSortable": false, "bVisible": false},
						{ "sName": "组织名称", "bSortable": false, "bVisible": true},
						{ "sName": "操作", "bSortable": false, "bVisible": true}
						
			],
			"fnInitComplete": function() {
				$("#serventrylist-table_filter label").detach();				
				var condition = "";
				condition = "<label>名称:<input type=\"text\" placeholder=\"请输入组织名称\" id=\"qh_servname\" /></label>"; //第一种
				condition += "<label>组织:<select id=\"qh_orgid\" name=\"qh_orgid\" style=\"width:120px;\">";
				condition += "</select></label>";
				condition += "<label>类别:<select id=\"qh_servtype\" name=\"qh_servtype\" style=\"width:60px;\">";
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="S">服务类</option>';
				condition += '<option value="M">其它</option>';
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
				//Server.initData();
				
				$("#btnSearch").click(function(){
					var u = base + '/mgr_serventrylistjson.html?a=a';
					u += "&name=" + $("#qh_servname").val();
					u += "&orgId=" + $('#qh_orgid option:selected').attr("value");
					u += "&type=" + $("#qh_servtype").val();
					u += "&status=" + $("#qh_status").val();
					oServerEntryTable.ajax.url(u).load();
				});
				$("#btnempty").click(function(){
					$("#qh_servname").val("");
					$("#qh_orgid").val("");
					$("#qh_servtype").val("");
					$("#qh_status").val("");
					var u = base + '/mgr_serventrylistjson.html';
					oServerEntryTable.ajax.url(u).load();
				});
				$("#btnadd").click(function(){
					$("#th_serventryname").val("");
					$("#th_serventrytype").val("");
					$("#th_refVId").val("");
					$("#th_status").val("");
					$("#th_orgId").val("");
					$("#th_serventrymemo").val("");
					Server.addServEntryDialog(0);
				});
			}
		});
		oServerConfigTable = $('#servconfiglist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/mgr_serverconfiglistjson.html",
			"columnDefs" :[
	               {
					    "targets": 12,
					    "data": null,
					    "render": function(data, type, row) {
				    		return data[12].substring(5,16);
					    }
					},{
				    "targets": 13,
				    "data": null,
				    "render": function(data, type, row) {
				    	var html = "";
						html += '<button class="btn btn-xs btn-info" id="editserconfig'+ data[0] + '"><i class="icon-cogs bigger-120">修改</i></button>';
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
			          	{ "sName": "服务ID", "bSortable": false, "bVisible": false},
						{ "sName": "服务名称", "bSortable": false, "bVisible": true},
						{ "sName": "服务类别", "bSortable": false, "bVisible": false},
						{ "sName": "服务编码", "bSortable": false, "bVisible": false},
						{ "sName": "服务描述", "bSortable": false, "bVisible": false},
						{ "sName": "参照ID", "bSortable": false, "bVisible": false},
						{ "sName": "参照名称", "bSortable": false, "bVisible": false},
						{ "sName": "服务接受方积分", "bSortable": false, "bVisible": true},
						{ "sName": "服务提供方积分", "bSortable": false, "bVisible": true},
						{ "sName": "组织ID", "bSortable": false, "bVisible": false},
						{ "sName": "组织名称", "bSortable": false, "bVisible": true},
						{ "sName": "创建时间", "bSortable": false, "bVisible": true},
						{ "sName": "操作", "bSortable": false, "bVisible": true}						
			],
			"fnInitComplete": function() {
				$("#servconfiglist-table_filter label").detach();				
				var condition = "";
				condition += " <label><input type=\"button\" id=\"btnadd1\" value=\"添加\"></label> ";
				
				$("#servconfiglist-table_filter").append(condition);
				Server.initData();
				
				$("#btnadd1").click(function(){
					$("#th_acceptorpoint").val("");
					$("#th_providerpoint").val("");
					Server.addServConfigDialog();
				});
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
				Server.rebindEvent();
			}
		});
	},
	bindEvent:function(){
		$('#serventrylist-table tbody').on('click','tr', function () {
			var data = oServerEntryTable.row(this).data();
			curEntryId = data[0];
			curOrgCode = data[9];
			var u = base + '/mgr_serverconfiglistjson.html?a=a';
			u += "&srvId=" + curEntryId;
			oServerConfigTable.ajax.url(u).load();
		});
		$('#serventrylist-table').on('xhr.dt', function (e,settings,json,xhr) {
			setTimeout(Server.rebindEvent,300);
		});
		$('#servconfiglist-table').on('xhr.dt', function (e,settings,json,xhr) {
			setTimeout(Server.rebindEvent,300);
		});
		
	},
	rebindEvent:function(){
		
		$("[id^='addserventry']").unbind().click(function(){
			var id = $(this).attr('id').substring("addserventry".length);
			Server.addServEntryDialog(id);
		});
		
		$("[id^='editserventry']").unbind().click(function(){
			var id = $(this).attr('id').substring("editserventry".length);
			Server.editServEntryDialog(id);
		});
		
		$("[id^='editserconfig']").unbind().click(function(){
			var id = $(this).attr('id').substring("editserventry".length);
			Server.editServConfigDialog(id);
		});
		
	},
	editServEntryDialog:function(code){
		$.ajax({
			url: base + '/mgr_getyservEntryjson.html?code=' + code,
			type: 'POST',
			dataType: 'json',
			success: function (resp) {
				var data = resp.data;
				console.log(data);
				$("#th_serventryname").val(data.srvName);
				$("#th_serventrytype").val(data.srvType);
				$('#th_refVId option[value="'+data.srvTypeId+'"]').attr("selected","selected");
				$('#th_status option[value="'+data.status+'"]').attr("selected","selected");
				$('#th_orgId option[value="'+data.orgid+'"]').attr("selected","selected");
				$("#th_serventrymemo").val(data.srvMemo);
				//$("#th_providerpoint").trigger("change",resp.data.prvnid);

				$("#servEntryAddDialog").removeClass('hide').dialog({
					height:600, 
					width:600,
					modal: true,
					title: "修改组织",
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
								var orgid = $('#th_orgId option:selected').attr("value");
								var serventrymemo = $("#th_serventrymemo").val();
								var reqData = {};
								reqData.code = code;
								reqData.serventryname = serventryname;
								reqData.serventrytype = serventrytype;
								reqData.refid = refid;
								reqData.status = status;
								reqData.orgid = orgid;
								reqData.serventrymemo = serventrymemo;
								$.ajax({
									url: base + '/mgr_addormodifyserventryjson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										var u = base + '/mgr_serventrylistjson.html?a=a';
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
	addServEntryDialog:function(id){
		$("#servEntryAddDialog").removeClass('hide').dialog({
			height:600, 
			width:600,
			modal: true,
			//title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='icon-ok'></i>新增课程</h4></div>",
			title: "新增服务",
			title_html: true,
			buttons: [
						{
							html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
							"class" : "btn btn-info btn-xs",
							click: function() {
								var srvId = id;
								var acceptorpoint = $("#th_acceptorpoint").val();
								var providerpoint = $("#th_providerpoint").val();
								var reqData = {};
								reqData.providerpoint = providerpoint;
								reqData.acceptorpoint = acceptorpoint;
								reqData.srvId = srvId;
								$.ajax({
									url: base + '/mgr_addormodifyserventryjson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										var u = base + '/mgr_serventrylistjson.html?a=a';
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
	
	},addServConfigDialog:function(){
		$("#servConfigAddDialog").removeClass('hide').dialog({
			height:600, 
			width:600,
			modal: true,
			//title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='icon-ok'></i>新增课程</h4></div>",
			title: "新增服务配置",
			title_html: true,
			buttons: [
						{
							html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
							"class" : "btn btn-info btn-xs",
							click: function() {
								var srvId = $('#th_srvid option:selected').attr("value");
								var acceptorpoint = $("#th_acceptorpoint1").val();
								var providerpoint = $("#th_providerpoint1").val();
								var reqData = {};
								reqData.srvId = srvId;
								$.ajax({
									url: base + '/getorgidjson.html?srvId ='+srvId,
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										reqData.orgId = resp.orgid;
									}
								});
								reqData.acceptorpoint = acceptorpoint;
								reqData.providerpoint = providerpoint;
								$.ajax({
									url: base + '/mgr_addormodifyservconfigjson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										var u = base + '/mgr_serverconfiglistjson.html?a=a';
										oServerConfigTable.ajax.url(u).load();
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
	
	},editServConfigDialog:function(id1){
		
		$.ajax({
			url: base + '/mgr_addormodifyservconfigjson.html?id='+id1,
			data: reqData,
			type: 'POST',
			dataType: 'json',
			success: function (resp) {
				var data = resp.data;
				$("#th_acceptorpoint1").val(data.acceptorpoint);
				$("#th_providerpoint1").val(data.providerpoint);
			}
		});
		
		$("#servConfigEditDialog").removeClass('hide').dialog({
			height:600, 
			width:600,
			modal: true,
			//title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='icon-ok'></i>新增课程</h4></div>",
			title: "修改服务配置",
			title_html: true,
			buttons: [
						{
							html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
							"class" : "btn btn-info btn-xs",
							click: function() {
								var id = id1;
								var acceptorpoint = $("#th_acceptorpoint").val();
								var providerpoint = $("#th_providerpoint").val();
								var reqData = {};
								reqData.acceptorpoint = acceptorpoint;
								reqData.providerpoint = providerpoint;
								reqData.id = id;
								$.ajax({
									url: base + '/mgr_addormodifyservconfigjson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										var u = base + '/mgr_serverconfiglistjson.html?a=a';
										oServerConfigTable.ajax.url(u).load();
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