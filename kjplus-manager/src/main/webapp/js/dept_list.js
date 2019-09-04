var base = $("#base").attr("href");
var oOrgTable = null;
var oDeptTable = null;
var curCityId = "";
var curAreaId = "";
var curOrgId = "";

Dept = {
	init:function(){
		this.initTable();
		this.bindEvent();
	},
	initTable:function(){
		oDeptTable = $('#deptlist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/mgr_deptlistjson.html",
			"columnDefs" :[
				{
				    "targets": 5,
				    "data": null,
				    "render": function(data, type, row) {
				    	if(data[5] == "R")
				    		return "实体";
				    	else if(data[5] == "G")
				    		return "团队";
				    	else
				    		return "虚拟";
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
				    	var html = "";
				    	if(data[5] != "G")//不是团体,团体没有下级
							html += '<button class="btn btn-xs btn-info" id="adddepartment'+ data[0] + '"><i class="icon-cogs bigger-120">添加</i></button>';
						html += '<button class="btn btn-xs btn-info" id="editdepartment'+ data[1] + '"><i class="icon-cogs bigger-120">修改</i></button>';
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
			"aLengthMenu": [[15, 25, 50], ["15", "25", "50"]],
			"aoColumns": [
			          	{ "sName": "ID", "bSortable": false, "bVisible": true},
						{ "sName": "编码", "bSortable": false, "bVisible": false},
						{ "sName": "名称", "bSortable": false, "bVisible": true},
						{ "sName": "pid", "bSortable": false, "bVisible": false},
						{ "sName": "所属部门", "bSortable": false, "bVisible": true},
						{ "sName": "类型", "bSortable": false, "bVisible": true},
						{ "sName": "是否有效", "bSortable": false, "bVisible": true},
						{ "sName": "描述备注", "bSortable": false, "bVisible": true},
						{ "sName": "操作", "bSortable": false, "bVisible": true}
						
			],
			"fnInitComplete": function() {
				$("#deptlist-table_filter label").detach();				
				var condition = "";
				condition = "<label>名称：<input type=\"text\" placeholder=\"关键字\" id=\"qh_deptname\" /></label>"; 
				condition += "<label>类型:<select id=\"qh_type\" name=\"qh_type\" style=\"width:120px;\">";
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="R">实体</option>';
				condition += '<option value="G">团体</option>';
				condition += '<option value="V">虚拟</option>';
				condition += "</select></label>";
				condition += "<label>状态:<select id=\"qh_status\" name=\"qh_status\" style=\"width:60px;\">";
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="Y">有效</option>';
				condition += '<option value="N">无效</option>';
				condition += "</select></label>";
				condition += " <label><input type=\"button\" id=\"btnSearch1\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnempty1\" value=\"重置\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnadd\" value=\"添加\"></label> ";
				
				$("#deptlist-table_filter").append(condition);
				
				$("#btnSearch1").click(function(){
					var u = base + '/mgr_deptlistjson.html?a=a';
					u += "&deptname=" + $('#qh_deptname').val();
					u += "&status=" + $("#qh_status").val();
					u += "&type=" + $("#qh_type").val();
					oDeptTable.ajax.url(u).load();
				});
				$("#btnempty1").click(function(){
					$("#qh_deptname").val("");
					$("#qh_status").val("");
					$("#qh_typeid").val("");
					var u = base + '/mgr_deptlistjson.html';
					oDeptTable.ajax.url(u).load();
				});
				
				//添加按钮
				$("#btnadd").click(function(){
					$("#th_deptname").val("");
					$("#th_depttype").val("");
					$("#th_flag").val("");
					$("#th_memo").val("");
					Dept.addDeptDialog();
				});	
			}
		});
		
	},
	bindEvent:function(){
		$('#deptlist-table').on('xhr.dt', function (e,settings,json,xhr) {
			setTimeout(Dept.rebindEvent,300);
		});
	},
	rebindEvent:function(){
		
		$("[id^='adddepartment']").unbind().click(function(){
			$("#th_deptname").val("");
			$("#th_depttype").val("");
			$("#th_flag").val("");
			$("#th_memo").val("");
			var pid = $(this).attr('id').substring("adddepartment".length);
			Dept.addDeptDialog(pid);
		});
		
		$("[id^='editdepartment']").unbind().click(function(){
			var code = $(this).attr('id').substring("editdepartment".length);
			Dept.editDeptDialog(code);
		});
		
	},
	editDeptDialog:function(code){
		console.log(code);
		$.ajax({
			url: base + '/mgr_getdeptjson.html?code=' + code,
			type: 'POST',
			dataType: 'json',
			success: function (resp) {
				
				$('#th_deptid1 option[value="'+resp.data.pid+'"]').attr("selected","selected");
				$("#th_deptname1").val(resp.data.deptName);
				$('#th_depttype1 option[value="'+resp.data.deptType+'"]').attr("selected","selected");
				$('#th_flag1 option[value="'+resp.data.flag+'"]').attr("selected","selected");
				$("#th_memo1").val(resp.data.memo);
					
				$("#editDeptDialog").removeClass('hide').dialog({
					height:450, 
					width:400,
					modal: true,
					title: "修改部门信息",
					title_html: true,
					buttons: [
								{
									html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
									"class" : "btn btn-info btn-xs",
									click: function() {
										var thatdialog = this;
										var memo = $("#th_memo1").val();
										var deptName = $("#th_deptname1").val();
										var pid = $('#th_deptid1 option:selected').attr("value");
										var deptType = $('#th_depttype1 option:selected').attr("value");
										var flag = $('#th_flag1 option:selected').attr("value");
										
										var reqData = {};
										reqData.code = code;
										reqData.pid = pid;
										reqData.deptName = deptName;
										reqData.deptType = deptType;
										reqData.memo = memo;
										reqData.flag = flag;
										$.ajax({
											url: base + '/mgr_addormodifydeptjson.html',
											data: reqData,
											type: 'POST',
											dataType: 'json',
											success: function (resp) {
												var u = base + '/mgr_deptlistjson.html?a=a';
												oDeptTable.ajax.url(u).load();
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
	addDeptDialog:function(pid1){
		$("#addDeptDialog").removeClass('hide').dialog({
			height:400, 
			width:400,
			modal: true,
			title: "新增部门",
			title_html: true,
			buttons: [
						{
							html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
							"class" : "btn btn-info btn-xs",
							click: function() {
								var memo = $("#th_memo").val();
								var deptName = $("#th_deptname").val();
								var deptType = $('#th_depttype option:selected').attr("value");
								var flag = $('#th_flag option:selected').attr("value");
								
								var reqData = {};
								reqData.pid = pid1;
								console.log(pid1);
								reqData.deptName = deptName;
								reqData.deptType = deptType;
								reqData.memo = memo;
								reqData.flag = flag;
								$.ajax({
									url: base + '/mgr_addormodifydeptjson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										var u = base + '/mgr_deptlistjson.html?a=a';
										oDeptTable.ajax.url(u).load();
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
	Dept.init();	
});