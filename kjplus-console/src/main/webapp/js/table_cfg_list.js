var base = $("#base").attr("href");
var oTableCfgHeadTable = null;
var oTableCfgLineTable = null;
var curCityId = "";
var curAreaId = "";
var curOrgId = "";

Table = {
	init:function(){
		this.initTable();
		//this.initData();
		this.bindEvent();
	},
	initTable:function(){
		oTableCfgHeadTable = $('#tablecfgheadlist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/tablecfgheadlistjson.html",
			"columnDefs" :[
				{
				    "targets": 5,
				    "data": null,
				    "render": function(data, type, row) {
				    	if(data[5] == "Y")
				    		return "有效";
				    	else
				    		return "失效";
				    }
				},{
				    "targets": 6,
				    "data": null,
				    "render": function(data, type, row) {
				    	return data[6].substring(5,16);
				    }
				},{
				    "targets": 7,
				    "data": null,
				    "render": function(data, type, row) {
				    	var html = "";
						/*html += '<button class="btn btn-xs btn-danger" id="edithead'+ data[0] + '"><i class="icon-cogs bigger-120">修改</i></button>';*/
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
			"aLengthMenu": [[-1], ["全部"]],
			"aoColumns": [
			          	{ "sName": "ID", "bSortable": false, "bVisible": true},
			          	{ "sName": "编码", "bSortable": false, "bVisible": true},
						{ "sName": "名称", "bSortable": false, "bVisible": true},
						{ "sName": "类型ID", "bSortable": false, "bVisible": true,"bVisible": false},
						{ "sName": "类型名称", "bSortable": false, "bVisible": true},
						{ "sName": "是否有效", "bSortable": false, "bVisible": true},
						{ "sName": "创建时间", "bSortable": false, "bVisible": true},
						{ "sName": "操作", "bSortable": false, "bVisible": true}
			],
			"fnInitComplete": function() {
				$("#tablecfgheadlist-table_filter label").detach();				
				var condition = "";
				condition += "<label>编码:<input type=\"text\" placeholder=\"关键字\" id=\"s_code\" /></label>";
				/*condition += "<label>标题:<input type=\"text\" placeholder=\"关键字\" id=\"s_title\" /></label>";*/
				condition += "</select></label>";
			/*	condition += "<label>状态:<select id=\"head_flag\" name=\"head_flag\" style=\"width:60px;\">";
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="Y">有效</option>';
				condition += '<option value="N">失效</option>';
				condition += "</select></label>";*/
				condition += " <label><input type=\"button\" id=\"btnSearch\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnempty\" value=\"重置\"></label> ";
				/*condition += " <label><input type=\"button\" id=\"btnadd\" value=\"添加\"></label> ";*/
				
				$("#tablecfgheadlist-table_filter").append(condition);
				Table.initData();
				
				$("#btnSearch").click(function(){
					var u = base + '/tablecfgheadlistjson.html?a=a';
					u += "&code=" + $("#s_code").val();
					//TODO flag name
					u += "&flag=" + $('#head_flag option:selected').attr("value");
					oTableCfgHeadTable.ajax.url(u).load();
				});
				$("#btnempty").click(function(){
					$("#s_code").val("");
					var u = base + '/tablecfgheadlistjson.html';
					oTableCfgHeadTable.ajax.url(u).load();
				});
				$("#btnadd").click(function(){/*
					$("#th_name").val("");
					$("#th_alias").val("");
					$("#th_prvnid").val("");
					$("#th_cityid").val("");
					$("#th_areaid").val("");
					$("#th_orgtypeid").val("");
					$("#th_addr").val("");
					Org.addOrgDialog(0);
				*/});
			}
		});
		
		oTableCfgLineTable = $('#tablecfglinelist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/tablecfglinelistjson.html",
			"columnDefs" :[
				{
				    "targets": 7,
				    "data": null,
				    "render": function(data, type, row) {
				    	if(data[6] == 0)
				    		return  "无";
				    	else
				    		return data[7];
				    }
				},
				{
				    "targets": 7,
				    "data": null,
				    "render": function(data, type, row) {
				    	if(data[6] == 0)
				    		return  "无";
				    	else
				    		return data[7];
				    }
				},
				{
				    "targets": 8,
				    "data": null,
				    "render": function(data, type, row) {
				    	if(data[8] == "N")
				    		return  "否";
				    	else
				    		return "是";
				    }
				},
				{
				    "targets": 11,
				    "data": null,
				    "render": function(data, type, row) {
				    	if(data[11] == "Y")
				    		return "有效";
				    	else
				    		return "失效";
				    }
				},
				{
				    "targets": 15,
				    "data": null,
				    "render": function(data, type, row) {
				    	if(data[14] == 0)
				    		return "无";
				    	else
				    		return data[15];
				    }
				},{
				    "targets": 17,
				    "data": null,
				    "render": function(data, type, row) {
				    	var html = "";
						/*html += '<button class="btn btn-xs btn-danger" id="editline'+ data[0] + '"><i class="icon-cogs bigger-120">修改</i></button>';*/
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
			"aLengthMenu": [[-1], [ "全部"]],
			"aoColumns": [
			          	{ "sName": "ID", "bSortable": false, "bVisible": false},
			          	{ "sName": "编码", "bSortable": false, "bVisible": true, "width":"8%"},
						{ "sName": "标题", "bSortable": false, "bVisible": true, "width":"10%"},
						{ "sName": "行序号", "bSortable": false, "bVisible": true},
						{ "sName": "pos", "bSortable": false, "bVisible": true},
						{ "sName": "pos2", "bSortable": false, "bVisible": true},
						{ "sName": "参数类型ID", "bSortable": false, "bVisible": false, "width":"8%"},
						{ "sName": "参数类型名称", "bSortable": false, "bVisible": true, "width":"8%"},
						{ "sName": "多选", "bSortable": false, "bVisible": true},
						{ "sName": "值类型", "bSortable": false, "bVisible": true},
						{ "sName": "占位符", "bSortable": false, "bVisible": true},
						{ "sName": "标题宽度", "bSortable": false, "bVisible": true},
						{ "sName": "内容宽度", "bSortable": false, "bVisible": true},
						{ "sName": "标准类型ID", "bSortable": false, "bVisible": false},
						{ "sName": "标准类型", "bSortable": false, "bVisible": true},
						{ "sName": "IsReq", "bSortable": false, "bVisible": true},
						{ "sName": "状态", "bSortable": false, "bVisible": true},
						{ "sName": "操作", "bSortable": false, "bVisible": true}
						
			],
			"fnInitComplete": function() {
				$("#tablecfglinelist-table_filter label").detach();				
				var condition = "";
				/*condition += "<label>列名:<input type=\"text\" placeholder=\"关键字\" id=\"s_title\" /></label>";*/
				/*condition += "<label>状态:<select id=\"line_flag\" name=\"line_flag\" style=\"width:60px;\">";
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="Y">有效</option>';
				condition += '<option value="N">失效</option>';
				condition += "</select></label>";*/
				/*condition += " <label><input type=\"button\" id=\"btnSearchLine\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnempty\" value=\"重置\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnadd\" value=\"添加\"></label> ";*/
				
				$("#tablecfglinelist-table_filter").append(condition);
				//Table.initData();
				
				$("#btnSearchLine").click(function(){
					console.log("sLIne");
					var u = base + '/tablecfglinelistjson.html?a=a';
					//TODO title  flag
					u += "&title=" + $("#s_title").val();
					u += "&flag=" + $('#line_flag option:selected').attr("value");
					console.log(u);
					oTableCfgLineTable.ajax.url(u).load();
				});
				$("#btnempty").click(function(){/*
					$("#org_keyWord").val("");
					$("#org_status").val("");
					var u = base + '/mgr_orglistjson.html';
					oTabellineTable.ajax.url(u).load();
				*/});
				$("#btnadd").click(function(){/*
					$("#th_name").val("");
					
					Org.addOrgDialog(0);
				*/});
			}
		});
		
	},
	initData:function(){
		// 初始化省列表
		$.ajax({
			url: base + '/listregions.html',
			type: 'GET',
			dataType: 'json',
			success: function (resp) {
				var html = "";
				html += "<option value=\"\">全部</option>";
				for(var i=0;i<resp.data.length;i++){
					html += '<option value="' + resp.data[i].regionId + '">' + resp.data[i].localName + '</option>';
				}
				$("#qh_prvnid option").remove();
				$("#qh_prvnid").append(html);
				Table.rebindEvent();
			}
		});
	},
	bindEvent:function(){
		$('#tablecfgheadlist-table tbody').on('click','tr', function () {
			var data = oTableCfgHeadTable.row(this).data();
			cfgId = data[0];
			console.log(cfgId);
			var u = base + '/tablecfglinelistjson.html?cfgId='+cfgId;
			oTableCfgLineTable.ajax.url(u).load();
		});
		$('#tablecfgheadlist-table').on('xhr.dt', function (e,settings,json,xhr) {
			setTimeout(Table.rebindTypeEvent,300);
		});
		$('#tablecfglinelist-table').on('xhr.dt', function (e,settings,json,xhr) {
			setTimeout(Table.rebindValueEvent,300);
		});
		
	},
	rebindEvent:function(){
		
		$("[id^='addorgline']").unbind().click(function(){
			var id = $(this).attr('id');
			Org.addOrgDialog(id.substring("addorgline".length));
		});
		
		$("[id^='editorg']").unbind().click(function(){
			var id = $(this).attr('id');
			Org.editOrgDialog(id.substring("editorg".length));
		});
		
		//一些需要重新绑定的事件
		$("#qh_prvnid").unbind().change(function(){ 
			var pid = $("#qh_prvnid").val();
			$.ajax({
				url: base + '/listregions.html?pid=' + pid,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					var html = "";
					html += "<option value=\"\">全部</option>";
					for(var i=0;i<resp.data.length;i++){
						html += '<option value="' + resp.data[i].regionId + '">' + resp.data[i].localName + '</option>';
					}
					$("#qh_cityid option").remove();
					$("#qh_cityid").append(html);
				}
			});
		});
		
	},
	editOrgDialog:function(code){
		$.ajax({
			url: base + '/mgr_getyorgjson.html?orgcode=' + code,
			type: 'POST',
			dataType: 'json',
			success: function (resp) {
				$("#th_code").val(resp.data.code);
				$("#th_name").val(resp.data.name);
				$("#th_alias").val(resp.data.alias);
				$("#th_addr").val(resp.data.addr);
				$('#th_orgtypeid option[value="'+resp.data.orgtypeid+'"]').attr("selected","selected");
				$('#th_prvnid option[value="'+resp.data.prvnid+'"]').attr("selected","selected");
				$('#th_cityid option[value="'+resp.data.cityid+'"]').attr("selected","selected");
				$("#th_prvnid").trigger("change",resp.data.prvnid);
				curCityId = resp.data.cityid;
				curAreaId = resp.data.areaid;
					
				$("#orgAddDialog").removeClass('hide').dialog({
					height:600, 
					width:600,
					modal: true,
					title: "修改组织",
					title_html: true,
					buttons: [
								{
									html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
									"class" : "btn btn-danger btn-xs",
									click: function() {
										var thatdialog = this;
										var code = $("#th_code").val();
										var name = $("#th_name").val();
										var alias = $("#th_alias").val();
										var prvnid = $('#th_prvnid option:selected').attr("value");
										var cityid = $('#th_cityid option:selected').attr("value");
										var reqData = {};
										reqData.orgcode = code;
										reqData.orgname = name;
										reqData.orgalias = alias;
										reqData.orgaddr = $("#th_addr").val();;
										reqData.prvnid = prvnid;
										reqData.cityid = cityid;
										reqData.areaid = $('#th_areaid option:selected').attr("value");;
										reqData.orgtypeid = $('#th_orgtypeid option:selected').attr("value");;
										$.ajax({
											url: base + '/mgr_addormodifyorgjson.html',
											data: reqData,
											type: 'POST',
											dataType: 'json',
											success: function (resp) {
												var u = base + '/mgr_orglistjson.html?a=a';
												u += "&prvnid=" + $('#qh_prvnid option:selected').attr("value");
												u += "&cityid=" + $('#qh_cityid option:selected').attr("value");
												oOrgTable.ajax.url(u).load();
												alert(resp.message);
												$(thatdialog).dialog("close");
											}
										});
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
	addOrgDialog:function(pid){
		$("#orgAddDialog").removeClass('hide').dialog({
			height:600, 
			width:600,
			modal: true,
			//title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='icon-ok'></i>新增课程</h4></div>",
			title: "新增组织",
			title_html: true,
			buttons: [
						{
							html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
							"class" : "btn btn-danger btn-xs",
							click: function() {
								var code = $("#th_code").val();
								var name = $("#th_name").val();
								var alias = $("#th_alias").val();
								var prvnid = $('#th_prvnid option:selected').attr("value");
								var cityid = $('#th_cityid option:selected').attr("value");
								var reqData = {};
								reqData.orgcode = code;
								reqData.orgname = name;
								reqData.orgalias = alias;
								reqData.orgaddr = $("#th_addr").val();;
								reqData.pid = pid;
								reqData.prvnid = prvnid;
								reqData.cityid = cityid;
								reqData.areaid = $('#th_areaid option:selected').attr("value");;
								reqData.orgtypeid = $('#th_orgtypeid option:selected').attr("value");;
								$.ajax({
									url: base + '/mgr_addormodifyorgjson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										var u = base + '/mgr_orglistjson.html?a=a';
										u += "&prvnid=" + $('#qh_prvnid option:selected').attr("value");
										u += "&cityid=" + $('#qh_cityid option:selected').attr("value");
										oOrgTable.ajax.url(u).load();
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
};

$(function(){ 
	Table.init();	
});