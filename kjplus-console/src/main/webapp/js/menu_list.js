var base = $("#base").attr("href");
var oMenuListTable = null;
var oMenuTable = null;

Menu = {
	init:function(){
		this.initTable();
		//this.initData();
		this.bindEvent();
	},
	initTable:function(){
		oMenuListTable = $('#menulist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/mgr_menulistjson.html",
			"columnDefs" :[/*
							{
							    "targets": 5,
							    "data": null,
							    "render": function(data, type, row) {
							    	if(data[5] == "Y")
							    		return "有效";
							    	else
							    		return "无效";
							    }
							},{
							    "targets": 6,
							    "data": null,
							    "render": function(data, type, row) {
							    	if(data[6] == "A")
							    		return "管理员";
							    	else
							    		return "普通用户";
							    }
							},{
							    "targets": 12,
							    "data": null,
							    "render": function(data, type, row) {
						    		return data[12].substring(5,16);
							    }
							},{
							    "targets": 14,
							    "data": null,
							    "render": function(data, type, row) {
							    	var html = "";
									html += '<button class="btn btn-xs btn-info" id="editadminuser'+ data[0] + '"><i class="icon-cogs bigger-120">修改</i></button>';
									html += '<button class="btn btn-xs btn-info" id="editadminuser'+ data[0] + '"><i class="icon-cogs bigger-120">权限修改</i></button>';
									html += '<button class="btn btn-xs btn-info" id="modifyrole'+ data[0] + '"><i class="icon-cogs bigger-120">角色</i></button>';
									html += '<button class="btn btn-xs btn-info" id="editadminuser'+ data[1] + '"><i class="icon-cogs bigger-120">改</i></button>';
									html += '<button class="btn btn-xs btn-info" id="modifyrole'+ data[0] + '"><i class="icon-cogs bigger-120">角</i></button>';
									html += '<button class="btn btn-xs btn-info" id="rolemenu'+ data[0] + '"><i class="icon-cogs bigger-120">菜</i></button>';
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
			"aoColumns": [
			          	{ "sName": "菜单ID", "bSortable": false, "bVisible": true},
			          	{ "sName": "父Id", "bSortable": false, "bVisible": true},
						{ "sName": "公众号ID", "bSortable": false, "bVisible": true},
						{ "sName": "别名", "bSortable": false, "bVisible": true},
						{ "sName": "状态", "bSortable": false, "bVisible": true},
						{ "sName": "路径", "bSortable": false, "bVisible": true},
						{ "sName": "类型", "bSortable": false, "bVisible": true},
						{ "sName": "创建时间", "bSortable": false, "bVisible": true},
						{ "sName": "消息推送", "bSortable": false, "bVisible": true},
						{ "sName": "备注", "bSortable": false, "bVisible": true},
						{ "sName": "组织Seq", "bSortable": false, "bVisible": true}  
				],
				"fnInitComplete": function() {
					$("#menulist-table_filter label").detach();				
					var condition = "";
					condition = "<label><input type=\"text\" placeholder=\"关键字\" id=\"qh_deptname\" /></label>"; 
					condition += "<label>状态:<select id=\"info_status\" name=\"qh_status\" style=\"width:60px;\">";
					condition += "<option value=\"\">全部</option>";
					condition += '<option value="Y">有效</option>';
					condition += '<option value="N">无效</option>';
					condition += "</select></label>";
					condition += " <label><input type=\"button\" id=\"btnSearch1\" value=\"查询\"></label> ";
					condition += " <label><input type=\"button\" id=\"btnempty1\" value=\"重置\"></label> ";
					//condition += " <label><input type=\"button\" id=\"btnadd\" value=\"添加\"></label> ";
					
					$("#mennulist-table_filter").append(condition);
		/*			Org.initData();
					
					$("#btnSearch1").click(function(){
						var u = base + '/mgr_deptlistjson.html?a=a';
						u += "&deptname=" + $('#qh_deptname').val();
						u += "&status=" + $('#qh_status option:selected').attr("value");
						console.log("1");
						oDeptTable.ajax.url(u).load();
					});
					$("#btnempty1").click(function(){
						$("#keyWord").val("");
						var u = base + '/mgr_deptlistjson.html';
						oDeptTable.ajax.url(u).load();
					});*/
				}
		});
		oMenuTable = $('#menu-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/mgr_menujson.html",
			"columnDefs" :[/*
							{
							    "targets": 5,
							    "data": null,
							    "render": function(data, type, row) {
							    	if(data[5] == "Y")
							    		return "有效";
							    	else
							    		return "无效";
							    }
							},{
							    "targets": 6,
							    "data": null,
							    "render": function(data, type, row) {
							    	if(data[6] == "A")
							    		return "管理员";
							    	else
							    		return "普通用户";
							    }
							},{
							    "targets": 12,
							    "data": null,
							    "render": function(data, type, row) {
						    		return data[12].substring(5,16);
							    }
							},{
							    "targets": 14,
							    "data": null,
							    "render": function(data, type, row) {
							    	var html = "";
									html += '<button class="btn btn-xs btn-info" id="editadminuser'+ data[0] + '"><i class="icon-cogs bigger-120">修改</i></button>';
									html += '<button class="btn btn-xs btn-info" id="editadminuser'+ data[0] + '"><i class="icon-cogs bigger-120">权限修改</i></button>';
									html += '<button class="btn btn-xs btn-info" id="modifyrole'+ data[0] + '"><i class="icon-cogs bigger-120">角色</i></button>';
									html += '<button class="btn btn-xs btn-info" id="editadminuser'+ data[1] + '"><i class="icon-cogs bigger-120">改</i></button>';
									html += '<button class="btn btn-xs btn-info" id="modifyrole'+ data[0] + '"><i class="icon-cogs bigger-120">角</i></button>';
									html += '<button class="btn btn-xs btn-info" id="rolemenu'+ data[0] + '"><i class="icon-cogs bigger-120">菜</i></button>';
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
			"aoColumns": [
			          	{ "sName": "菜单ID", "bSortable": false, "bVisible": true},
			          	{ "sName": "父Id", "bSortable": false, "bVisible": true},
						{ "sName": "公众号ID", "bSortable": false, "bVisible": true},
						{ "sName": "别名", "bSortable": false, "bVisible": true},
						{ "sName": "状态", "bSortable": false, "bVisible": true},
						{ "sName": "路径", "bSortable": false, "bVisible": true, "bVisible": false},
						{ "sName": "类型", "bSortable": false, "bVisible": true},
						{ "sName": "创建时间", "bSortable": false, "bVisible": true},
						{ "sName": "消息推送", "bSortable": false, "bVisible": true},
						{ "sName": "备注", "bSortable": false, "bVisible": true, "bVisible": false},
						{ "sName": "组织Seq", "bSortable": false, "bVisible": true}  
				],
				"fnInitComplete": function() {
					$("#menulist-table_filter label").detach();				
					var condition = "";
					condition = "<label><input type=\"text\" placeholder=\"关键字\" id=\"qh_deptname\" /></label>"; 
					condition += "<label>状态:<select id=\"info_status\" name=\"qh_status\" style=\"width:60px;\">";
					condition += "<option value=\"\">全部</option>";
					condition += '<option value="Y">有效</option>';
					condition += '<option value="N">无效</option>';
					condition += "</select></label>";
					condition += " <label><input type=\"button\" id=\"btnSearch1\" value=\"查询\"></label> ";
					condition += " <label><input type=\"button\" id=\"btnempty1\" value=\"重置\"></label> ";
					//condition += " <label><input type=\"button\" id=\"btnadd\" value=\"添加\"></label> ";
					
			/*		$("#mennulist-table_filter").append(condition);
					Org.initData();
					
					$("#btnSearch1").click(function(){
						var u = base + '/mgr_deptlistjson.html?a=a';
						u += "&deptname=" + $('#qh_deptname').val();
						u += "&status=" + $('#qh_status option:selected').attr("value");
						console.log("1");
						oDeptTable.ajax.url(u).load();
					});
					$("#btnempty1").click(function(){
						$("#keyWord").val("");
						var u = base + '/mgr_deptlistjson.html';
						oDeptTable.ajax.url(u).load();
					});*/
				}
		});
		
	},
	initData:function(){},
	bindEvent:function(){
		$('#menulist-table tbody').on('click','tr', function () {
			var data = oMenuListTable.row(this).data();
			console.log(data);
			var id = data[0];
			var u = base + '/mgr_menujson.html?a=a';
			u += "&id=" + id;
			console.log(u);
			oMenuTable.ajax.url(u).load();
		});
	
	},
	rebindEvent:function(){},
	editOrgDialog:function(code){},
	addOrgDialog:function(pid){},
};

$(function(){ 
	Menu.init();	
});