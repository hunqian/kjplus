var base = $("#base").attr("href");
var oHealthExamTable = null;

HealthExam = {
	init:function(){
		this.initTable();
		this.initData();
		this.bindEvent();
	},
	initTable:function(){
		oHealthExamTable = $('#healthexamlist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/mgr_healthexamlistjson.html",
			"columnDefs" :[
				{
				    "targets": 20,
				    "data": null,
				    "render": function(data, type, row) {
				    	if(data[20] == "Y")
				    		return "有效";
				    	else
				    		return "无效";
				    }
				},{
				    "targets": 21,
				    "data": null,
				    "render": function(data, type, row) {
			    		return data[21].substring(5,11);
				    }
				},{
				    "targets": 23,
				    "data": null,
				    "render": function(data, type, row) {
				    	var html = "";
						html += '<button class="btn btn-xs btn-info" id="healthDetails'+ data[0] + '"><i class="icon-cogs bigger-120">详情</i></button>';
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
						{ "sName": "编码", "bSortable": false, "bVisible": false},
						{ "sName": "居民ID", "bSortable": false, "bVisible": false},
						{ "sName": "居民姓名", "bSortable": false, "bVisible": true},
						{ "sName": "检查医生ID", "bSortable": false, "bVisible": false},
						{ "sName": "检查医生姓名", "bSortable": false, "bVisible": true},
						{ "sName": "组织ID", "bSortable": false, "bVisible": false},
						{ "sName": "组织名称", "bSortable": false, "bVisible": true},
						{ "sName": "表格ID", "bSortable": false, "bVisible": false},
						{ "sName": "体温", "bSortable": false, "bVisible": true},
						{ "sName": "呼吸频率", "bSortable": false, "bVisible": true},
						{ "sName": "脉率", "bSortable": false, "bVisible": true},
						{ "sName": "左侧高压", "bSortable": false, "bVisible": true},
						{ "sName": "左侧低压", "bSortable": false, "bVisible": true},
						{ "sName": "右侧高压", "bSortable": false, "bVisible": true},
						{ "sName": "右侧低压", "bSortable": false, "bVisible": true},
						{ "sName": "身高", "bSortable": false, "bVisible": true},
						{ "sName": "体重", "bSortable": false, "bVisible": true},
						{ "sName": "腰围", "bSortable": false, "bVisible": true},
						{ "sName": "体质指数", "bSortable": false, "bVisible": true},
						{ "sName": "是否有效", "bSortable": false, "bVisible": true},
						{ "sName": "体检时间", "bSortable": false, "bVisible": true},
						{ "sName": "体检结果", "bSortable": false, "bVisible": true},
						{ "sName": "操作", "bSortable": false, "bVisible": true}
			],
			"fnInitComplete": function() {
				$("#healthexamlist-table_filter label").detach();				
				var condition = "";
				condition += "<label>医生:<select id=\"qh_staffid\" name=\"qh_staffid\" style=\"width:120px;\">";
				condition += "</select></label>";
				condition += "<label>居民:<select id=\"qh_prsnid\" name=\"qh_prsnid\" style=\"width:120px;\">";
				condition += "</select></label>";
				condition += "<label>查询时间:<input type=\"text\" id=\"qh_startday\" style=\"width:80px;\"/>—";
				condition += "<input type=\"text\" id=\"qh_finishday\" style=\"width:80px;\"/>";
				condition += "<i class=\"icon-calendar\"></i>";
				condition += "</label>";
				condition += "<label>状态:<select id=\"qh_status\" name=\"qh_status\" style=\"width:100px;\">";//第二种
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="Y">有效</option>';
				condition += '<option value="N">无效</option>';
				condition += "</select></label>";
				condition += " <label><input type=\"button\" id=\"btnSearch\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnempty\" value=\"重置\"></label> ";
				
				$("#healthexamlist-table_filter").append(condition);
				
				$( "#qh_startday" ).datepicker({
					dateFormat: 'yy-mm-dd',
					//format: 'yyyy/mm/dd',
					weekdaysLetter: ['日', '一', '二', '三', '四', '五', '六'],
					today: '今天',
					clear: '清除',
					close: '关闭',
					monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
					monthsShort: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
					dayNamesMin: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
					dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六']
				});
				$( "#qh_finishday" ).datepicker({
					dateFormat: 'yy-mm-dd',
					//format: 'yyyy/mm/dd',
					weekdaysLetter: ['日', '一', '二', '三', '四', '五', '六'],
					today: '今天',
					clear: '清除',
					close: '关闭',
					monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
					monthsShort: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
					dayNamesMin: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
					dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六']
				});
				
				//查询按钮  
				$("#btnSearch").click(function(){
					var u = base + '/mgr_healthexamlistjson.html?a=a';
					u += "&staffId="+ $('#qh_staffid option:selected').attr("value");
					u += "&prsnId="+ $('#qh_prsnid option:selected').attr("value");
					u += "&startDay=" + $("#qh_startday").val();
					u += "&finishDay=" + $("#qh_finishday").val();
					u += "&flag=" + $("#qh_status").val();
					console.log(u);
					oHealthExamTable.ajax.url(u).load();
				});
				//重置按钮
				$("#btnempty").click(function(){
					$("#qh_staffid").val("");
					$("#qh_prsnid").val("");
					$("#qh_startday").val("");
					$("#qh_finishday").val("");
					$("#qh_status").val("");
					var u = base + '/mgr_healthexamlistjson.html';
					oHealthExamTable.ajax.url(u).load();
				});
				HealthExam.initData();
			}
		});
	},
	initData:function(){
		//初始化医生列表
		$.ajax({
			url: base + '/mgr_stafflistjson.html',
			type: 'GET',
			dataType: 'json',
			success: function (resp) {
				var html = "";
				html += "<option value=\"\">全部</option>";
				for(var i=0;i<resp.aaData.length;i++){
					html += '<option value="' + resp.aaData[i][0] + '">' + resp.aaData[i][4] + '</option>';
				}
				$("#qh_staffid option").remove();
				$("#qh_staffid").append(html);
			}
		});
		//初始化居民列表
		$.ajax({
			url: base + '/docinfolistjson.html',
			type: 'GET',
			dataType: 'json',
			success: function (resp) {
				var html = "";
				html += "<option value=\"\">全部</option>";
				for(var i=0;i<resp.aaData.length;i++){
					html += '<option value="' + resp.aaData[i][0] + '">' + resp.aaData[i][5] + '</option>';
				}
				$("#qh_prsnid option").remove();
				$("#qh_prsnid").append(html);
			}
		});
	},
	bindEvent:function(){
		$('#healthexamlist-table').on('xhr.dt', function(e, settings, json, xhr) {
            setTimeout(HealthExam.rebindEvent, 300);
        });
	},rebindEvent:function(){
		//健康详情页跳转
		$("[id^='healthDetails']").unbind().click(function(){
			var healthId = $(this).attr("id").substring("healthDetails".length);
			window.location.href=base+"/healthexampage.html?healthId="+ healthId;
		});
	}
};

$(function(){ 
	HealthExam.init();	
});