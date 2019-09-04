

var base = $("#base").attr("href");
var oAppTable = null;

Appointment = {//ģ��Appointment
	init:function(){
		this.initTable();
		this.bindEvent();
	},
	initTable:function(){
		oAppTable = $('#appointment-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/mgr_appointsjson.html",
			"columnDefs" :[
				{
				    "targets": 4,
				    "data": null,
				    "render": function(data, type, row) {
				    	console.log(data[4]);
				    	var tagObjs = JSON.parse(data[4]);
				    	var html = "";
				    	for(var i=0;i<tagObjs.length;i++){
				    		html += '<span class="green">'+tagObjs[i].refValName+'</span>';
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
			"aoColumns": [
			          	{ "sName": "id", "bSortable": false, "bVisible": true},
			          	{ "sName": "姓名", "bSortable": false, "bVisible": true},
						{ "sName": "性别", "bSortable": false, "bVisible": true},
						{ "sName": "年龄", "bSortable": false, "bVisible": true},
						{ "sName": "人群标签", "bSortable": false, "bVisible": true},
						{ "sName": "身份证号", "bSortable": false, "bVisible": true},
						{ "sName": "电话号码", "bSortable": false, "bVisible": true},
						{ "sName": "地址", "bSortable": false, "bVisible": true},
						{ "sName": "家庭医生", "bSortable": false, "bVisible": true}
						//{ "sName": "编辑查看","bSortable": false, "bVisible": true}
			],
			"fnInitComplete": function() {
				$("#appointment-table_filter label").detach();
				
				var condition = "";
				condition = "<label><input type=\"text\" placeholder=\"关键字\" id=\"info_keyWord\" /></label>"; //第一种
				condition += "<label>状态:<select id=\"info_status\" name=\"info_status\" style=\"width:100px;\">";//第二种
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="Y">有效</option>';
				condition += '<option value="N">无效</option>';
				condition += "</select></label>";
				condition += " <label><input type=\"button\" id=\"btnSearch\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnempty\" value=\"重置\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnadd\" value=\"添加\"></label> ";
				
				//$("#appointment-table_filter").append(condition);
				//aaaa查询按钮  
				$("#btnSearch").click(function(){
					var u = base + '/mgr_infojson.html?title=' + $("#info_keyWord").val();
					u += "&status=" + $("#info_status").val();
					oInfoTable.ajax.url(u).load();
				});
				//aaaa重置按钮
				$("#btnempty").click(function(){
					$("#keyWord").val("");
					var u = base + '/mgr_infojson.html';
					oInfoTable.ajax.url(u).load();
				});
	
			}
		});
	},
	bindEvent:function(){
	}
};

$(function(){ 
	Appointment.init();	
});