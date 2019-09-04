var base = $("#base").attr("href");
var oRefTypeTable = null;
var oRefValueTable = null;
var curRefCode = "";
var curRefId = "";

RefType = {
	init:function(){
		this.initTable();
		this.bindEvent();
	},
	initTable:function(){
		oRefTypeTable = $('#reftype-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/mgr_reftypelistjson.html",
			"columnDefs" :[
				{
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
			"aLengthMenu": [[5, 10, 15], ["5", "10", "15"]],
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
			          	{ "sName": "ID", "bSortable": false, "bVisible": true},
			          	{ "sName": "类型编码", "bSortable": false, "bVisible": true},
						{ "sName": "类型名称", "bSortable": false, "bVisible": true},
						{ "sName": "类型类别", "bSortable": false, "bVisible": true},
						{ "sName": "类型说明", "bSortable": false, "bVisible": true},
						{ "sName": "是否有效", "bSortable": false, "bVisible": true}
			],
			"fnInitComplete": function() {
				$("#reftype-table_filter label").detach();				
				var condition = "";
				condition = "<label>code:<input type=\"text\" placeholder=\"关键字\" id=\"type_code\" /></label>";
				condition += "<label>类型名称:<input type=\"text\" placeholder=\"关键字\" id=\"type_keyWord\" /></label>";
				condition += "<label>状态:<select id=\"type_status\" name=\"type_status\" style=\"width:60px;\">";
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="Y">有效</option>';
				condition += '<option value="N">无效</option>';
				condition += "</select></label>";
				condition += " <label><input type=\"button\" id=\"btnSearch\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnempty\" value=\"重置\"></label> ";
				
				$("#reftype-table_filter").append(condition);
				
				$("#btnSearch").click(function(){
					var u = base + '/mgr_reftypelistjson.html?a=a';
					u += "&refcode=" + $('#type_code').val();
					u += "&refname=" + $('#type_keyWord').val();
					u += "&flag=" + $('#type_status option:selected').attr("value");
					oRefTypeTable.ajax.url(u).load();
				});
				$("#btnempty").click(function(){
					$("#type_code").val("");
					$("#type_keyWord").val("");
					$("#type_status").val("");
					var u = base + '/mgr_reftypelistjson.html';
					oRefTypeTable.ajax.url(u).load();
				});
			}
		});
		
		oRefValueTable = $('#refvalue-table').DataTable({

			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/mgr_refvaluelistjson.html",
			"columnDefs" :[
								{
								    "targets": 4,
								    "data": null,
								    "render": function(data, type, row) {
								    	if(data[4] == "Y")
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
			
			"aoColumns": [
			          	{ "sName": "参数值ID", "bSortable": false, "bVisible": true},
			          	{ "sName": "参数值编码", "bSortable": false, "bVisible": true},
						{ "sName": "参数值", "bSortable": false, "bVisible": true},
						{ "sName": "参数值名称", "bSortable": false, "bVisible": true},
						{ "sName": "是否有效", "bSortable": false, "bVisible": true}
			],
			"fnInitComplete": function() {
				$("#refvalue-table_filter label").detach();				
				
			}
		});
	},
	bindEvent:function(){
		$('#reftype-table tbody').on('click','tr', function () {
			var data = oRefTypeTable.row(this).data();
			curRefId = data[0];
			var u = base + '/refvaluelist.html?refid='+curRefId;
			oRefValueTable.ajax.url(u).load();
		});
		$('#reftype-table').on('xhr.dt', function (e,settings,json,xhr) {
			setTimeout(RefType.rebindTypeEvent,300);
		});
		$('#refvalue-table').on('xhr.dt', function (e,settings,json,xhr) {
			setTimeout(RefType.rebindValueEvent,300);
		});
	},
	rebindTypeEvent:function(){
		console.log("bindType");
	},
	rebindValueEvent:function(){
		console.log("bindValue");
	}
};

$(function(){ 
	RefType.init();	
});