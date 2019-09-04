var base = $("#base").attr("href");
var oOrgTable = null;
var oDeptTable = null;
var curCityId = "";
var curAreaId = "";
var curOrgId = "";

Org = {
	init:function(){
		this.initTable();
		this.initData();
		this.bindEvent();
	},
	initTable:function(){
		oOrgTable = $('#orglist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/mgr_orglistjson.html",
			"columnDefs" :[
				{
				    "targets": 9,
				    "data": null,
				    "render": function(data, type, row) {
				    	if(data[9] == "Y")
				    		return "有效";
				    	else
				    		return "无效";
				    }
				},{
				    "targets": 10,
				    "data": null,
				    "render": function(data, type, row) {
			    		return data[10].substring(5,16);
				    }
				},{
				    "targets": 11,
				    "data": null,
				    "render": function(data, type, row) {
				    	var html = "";
						html += '<button class="btn btn-xs btn-info" id="addorgline'+ data[0] + '"><i class="icon-cogs bigger-120">添加</i></button>';
						html += '<button class="btn btn-xs btn-info" id="editorg'+ data[1] + '"><i class="icon-cogs bigger-120">修改</i></button>';
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
			          	{ "sName": "编码", "bSortable": false, "bVisible": true},
						{ "sName": "名称", "bSortable": false, "bVisible": true},
						{ "sName": "别名", "bSortable": false, "bVisible": true},
						{ "sName": "上级", "bSortable": false, "bVisible": true},
						{ "sName": "地区", "bSortable": false, "bVisible": true,"width":"8%"},
						{ "sName": "城市", "bSortable": false, "bVisible": true},
						{ "sName": "省份", "bSortable": false, "bVisible": true},
						{ "sName": "地址", "bSortable": false, "bVisible": true},
						{ "sName": "状态", "bSortable": false, "bVisible": true},
						{ "sName": "创建", "bSortable": false, "bVisible": true},
						{ "sName": "操作", "bSortable": false, "bVisible": true}
						
			],
			"fnInitComplete": function() {
				$("#orglist-table_filter label").detach();				
				var condition = "";
				condition = "<label>名称:<input type=\"text\" placeholder=\"请输入组织名称\" id=\"org_keyWord\" /></label>"; //第一种
				condition += "<label>省:<select id=\"qh_prvnid\" name=\"qh_prvnid\" style=\"width:80px;\">";
				condition += "</select></label>";
				condition += "<label>市:<select id=\"qh_cityid\" name=\"qh_cityid\" style=\"width:80px;\">";
				condition += "</select></label>";
				condition += "<label>状态:<select id=\"org_status\" name=\"org_status\" style=\"width:60px;\">";
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="Y">有效</option>';
				condition += '<option value="N">无效</option>';
				condition += "</select></label>";
				condition += " <label><input type=\"button\" id=\"btnSearch\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnempty\" value=\"重置\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnadd\" value=\"添加\"></label> ";
				
				$("#orglist-table_filter").append(condition);
				Org.initData();
				
				$("#btnSearch").click(function(){
					console.log(base);
					var u = base + '/mgr_orglistjson.html?a=a';
					u += "&status=" + $("#org_status").val();
					u += "&orgname=" + $("#org_keyWord").val();
					u += "&prvnid=" + $('#qh_prvnid option:selected').attr("value");
					u += "&cityid=" + $('#qh_cityid option:selected').attr("value");
					oOrgTable.ajax.url(u).load();
				});
				$("#btnempty").click(function(){
					$("#qh_prvnid").val("");
					$("#qh_cityid").val("");
					$("#org_keyWord").val("");
					$("#org_status").val("");
					var u = base + '/mgr_orglistjson.html';
					oOrgTable.ajax.url(u).load();
				});
				$("#btnadd").click(function(){
					$("#th_name").val("");
					$("#th_alias").val("");
					$("#th_prvnid").val("");
					$("#th_cityid").val("");
					$("#th_areaid").val("");
					$("#th_orgtypeid").val("");
					$("#th_addr").val("");
					Org.addOrgDialog(0);
				});
			}
		});
		oDeptTable = $('#deptlist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/mgr_deptlistjson.html",
			"columnDefs" :[
				{
				    "targets": 4,
				    "data": null,
				    "render": function(data, type, row) {
				    	if(data[4] == "R")
				    		return "实体";
				    	else
				    		return "虚拟";
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
			          	{ "sName": "ID", "bSortable": false, "bVisible": true},
			          	{ "sName": "pid", "bSortable": false, "bVisible": false},
						{ "sName": "编码", "bSortable": false, "bVisible": false},
						{ "sName": "名称", "bSortable": false, "bVisible": true},
						{ "sName": "类型", "bSortable": false, "bVisible": true},
						{ "sName": "是否有效", "bSortable": false, "bVisible": true},
						{ "sName": "组织ID", "bSortable": false, "bVisible": true},
						{ "sName": "组织名称", "bSortable": false, "bVisible": true},
						{ "sName": "描述备注", "bSortable": false, "bVisible": true}
			],
			"fnInitComplete": function() {
				$("#deptlist-table_filter label").detach();				
				var condition = "";
				condition = "<label>名称：<input type=\"text\" placeholder=\"关键字\" id=\"qh_deptname\" /></label>"; 
				condition += "<label>状态:<select id=\"qh_status\" name=\"qh_status\" style=\"width:60px;\">";
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="Y">有效</option>';
				condition += '<option value="N">无效</option>';
				condition += "</select></label>";
				condition += " <label><input type=\"button\" id=\"btnSearch1\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnempty1\" value=\"重置\"></label> ";
				
				$("#deptlist-table_filter").append(condition);
				Org.initData();
				
				$("#btnSearch1").click(function(){
					var u = base + '/mgr_deptlistjson.html?a=a';
					u += "&deptname=" + $('#qh_deptname').val();
					u += "&orgId=" + curOrgId;
					u += "&status=" + $("#qh_status").val();
					oDeptTable.ajax.url(u).load();
				});
				$("#btnempty1").click(function(){
					$("#qh_deptname").val("");
					$("#qh_status").val("");
					var u = base + '/mgr_deptlistjson.html';
					oDeptTable.ajax.url(u).load();
				});
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
				Org.rebindEvent();
			}
		});
	},
	bindEvent:function(){
		$('#orglist-table tbody').on('click','tr', function () {
			var data = oOrgTable.row(this).data();
			curOrgId = data[0];
			var u = base + '/mgr_deptlistjson.html?a=a';
			u += "&orgId=" + curOrgId;
			oDeptTable.ajax.url(u).load();
		});
		$('#orglist-table').on('xhr.dt', function (e,settings,json,xhr) {
			setTimeout(Org.rebindEvent,300);
		});
		$('#deptlist-table').on('xhr.dt', function (e,settings,json,xhr) {
			setTimeout(Org.rebindEvent,300);
		});
		
	},
	rebindEvent:function(){
		
		$("[id^='addorgline']").unbind().click(function(){
			var id = $(this).attr('id').substring("addorgline".length);
			Org.addOrgDialog(id);
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
		
		$("#th_prvnid").unbind().change(function(){ 
			var pid = $("#th_prvnid").val();
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
					$("#th_cityid option").remove();
					$("#th_cityid").append(html);
					if(curCityId != ""){
						$('#th_cityid option[value="'+curCityId+'"]').attr("selected", "selected");
						curCityId = "";
					}
					if(curCityId == ""){
						$("#th_areaid").unbind();
					}
					//移去所有区的
					$("#th_areaid option").remove();
					$("#th_cityid").trigger("change",resp.data.cityid);
				}
			});
		});
		
		$("#th_cityid").unbind().change(function(){ 
			var pid = $("#th_cityid").val();
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
					$("#th_areaid option").remove();
					$("#th_areaid").append(html);
					if(curAreaId != ""){
						$('#th_areaid option[value="'+curAreaId+'"]').attr("selected", "selected");
						curAreaId = "";
					}
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
									"class" : "btn btn-info btn-xs",
									click: function() {
										var thatdialog = this;
										var code = $("#th_code").val();
										var name = $("#th_name").val();
										var alias = $("#th_alias").val();
										var prvnid = $('#th_prvnid option:selected').attr("value");
										var cityid = $('#th_cityid option:selected').attr("value");
										if(title == null || title == ""){
											alert("名字不能为空!");
										}else if(infoType == null || infoType == ""){
											alert("咨询类别不能为空!");
										}else if(orgid <= 0){
											alert("组织不能为空!");
										}else{
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
							"class" : "btn btn-info btn-xs",
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
	Org.init();	
});