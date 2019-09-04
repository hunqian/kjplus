var base = $("#base").attr("href");
var oWxAdminUserTable = null;

WxAdminUser = {
	init:function(){
		this.initTable();
		//this.initData();
		this.bindEvent();
	},
	initTable:function(){
		oWxAdminUserTable = $('#wxadminuserlist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/mgr_wxadminuserlistjson.html",
			"columnDefs" :[
				
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
			          	{ "sData": "uid", "bSortable": false, "bVisible": true},
						{ "sName": "用户名", "bSortable": false, "bVisible": true},
						{ "sName": "邮箱", "bSortable": false, "bVisible": true},
						{ "sName": "手机号", "bSortable": false, "bVisible": true},
						{ "sName": "注册时间", "bSortable": false, "bVisible": true},
						{ "sName": "状态", "bSortable": false, "bVisible": true},
						{ "sName": "用户类型", "bSortable": false, "bVisible": true},
						{ "sName": "face", "bSortable": false, "bVisible": true},
						{ "sName": "有效开始", "bSortable": false, "bVisible": true},
						{ "sName": "失效时间", "bSortable": false, "bVisible": true},
						{ "sName": "组织ID", "bSortable": false, "bVisible": true},
						{ "sName": "组织名称", "bSortable": false, "bVisible": true},
						{ "sName": "创建时间", "bSortable": false, "bVisible": true},
				],
			"fnInitComplete": function() {
				$("#wxadminuserlist-table_filter label").detach();						
				var condition = "";
				condition += "<label>昵称：<input type=\"text\" placeholder=\"关键字\" id=\"qh_nickname\" /></label>";
				condition += " <label><input type=\"button\" id=\"btnSearch\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnempty\" value=\"重置\"></label> ";
				
				$("#wxadminuserlist-table_filter").append(condition);
				
				$("#btnSearch").click(function(){
					var u = base + '/mgr_wxadminuserlistjson.html?a=a';
					u += "&orgid=" + $('#qh_orgid option:selected').attr("value");
					u += "&nickname=" + $('#qh_nickname').val();
					oWxAdminUserTable.ajax.url(u).load();
				});
				$("#btnempty").click(function(){
					$("#keyWord").val("");
					var u = base + '/mgr_wxadminuserlistjson.html';
					oWxAdminUserTable.ajax.url(u).load();
				});
				$("#btnadd").click(function(){
					//WxUser.addWxUserDialog();
				});
			}
		});
	},
//	bindEvent:function(){
//	},
//	rebindEvent:function(){
//		$("[id^='editWxAdminUser']").unbind().click(function(){
//			var accCode = $(this).attr('accCode');
//			WxUser.editWxAdminUserDialog(accCode);
//		});	
//	}
};

$(function(){ 
	WxAdminUser.init();
});