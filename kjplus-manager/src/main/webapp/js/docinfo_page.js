var base = $("#base").attr("href");
var oFollowUpTable = null;
var oExamTable = null;
var oHealthExamTable = null;
var initData = 0;

DocInfoPage = {
	init:function(docinfoid){
		this.initData(docinfoid);
		this.bindEvent();
		this.initHealthExamTable();
		this.initHealthExamData();
		this.initFollowTable();
		this.initFollowData();
		this.initExamTable();
		this.initExamData();
	},
	initFollowTable:function(){
		oFollowUpTable = $('#followuplist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/mgr_followuplistjson.html?prsnId="+$("#docinfoid").attr("value"),
			"columnDefs" :[
				{
				    "targets": 10,
				    "data": null,
				    "render": function(data, type, row) {
				    	if(data[10] == "F")
				    		return "正规随访";
				    	else
				    		return "随机随访";
				    }
				},{
				    "targets": 13,
				    "data": null,
				    "render": function(data, type, row) {
			    		return data[13].substring(11,16);
				    }
				},{
				    "targets": 14,
				    "data": null,
				    "render": function(data, type, row) {
			    		return data[14].substring(0,11);
				    }
				},{
				    "targets": 20,
				    "data": null,
				    "render": function(data, type, row) {
				    	var html = "";
							html += '<button class="btn btn-xs btn-info" id="jumpfollowpage'+ data[0] + '" prsn_id = "'+data[3]+'" activepage = "followup"><i class="icon-cogs bigger-120">详情</i></button>';
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
			          	{ "sName": "本次随访编码", "bSortable": false, "bVisible":false},
						{ "sName": "编码", "bSortable": false, "bVisible": false},
						{ "sName": "居民ID", "bSortable": false, "bVisible": false},
						{ "sName": "居民姓名", "bSortable": false, "bVisible": true},
						{ "sName": "医生ID", "bSortable": false, "bVisible": false},
						{ "sName": "医生名称", "bSortable": false, "bVisible": true},
						{ "sName": "组织ID", "bSortable": false, "bVisible": false},
						{ "sName": "组织名称", "bSortable": false, "bVisible": true},
						{ "sName": "表格配置ID", "bSortable": false, "bVisible": false},
						{ "sName": "随访类型", "bSortable": false, "bVisible": true},
						{ "sName": "检查类型ID", "bSortable": false, "bVisible": false},
						{ "sName": "检查类型", "bSortable": false, "bVisible": true},
						{ "sName": "随访时间", "bSortable": false, "bVisible": true},
						{ "sName": "随访日", "bSortable": false, "bVisible": true},
						{ "sName": "随访意见分类ID", "bSortable": false, "bVisible": false},
						{ "sName": "随访意见分类", "bSortable": false, "bVisible": true},
						{ "sName": "随访结论", "bSortable": false, "bVisible": true},
						{ "sName": "结论医生ID", "bSortable": false, "bVisible": false},
						{ "sName": "结论医生名称", "bSortable": false, "bVisible": true},
						{ "sName": "操作", "bSortable": false, "bVisible": true}
			],
			"fnInitComplete": function() {
				$("#followuplist-table_filter label").detach();				
				var condition = "";
				condition += "<label>医生:<select id=\"qh_staffid\" name=\"qh_staffid\" style=\"width:120px;\">";
				condition += "</select></label>";
				condition += "<label>查询时间:<input type=\"text\" id=\"qh_startday\" style=\"width:80px;\"/>—";
				condition += "<input type=\"text\" id=\"qh_finishday\" style=\"width:80px;\"/>";
				condition += "<i class=\"icon-calendar\"></i>";
				condition += "</label>";
				condition += "<label>随访类型:<select id=\"qh_flpemisctype\" name=\"qh_flpemisctype\" style=\"width:100px;\">";//第二种
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="F">正规随访</option>';
				condition += '<option value="M">随机随访</option>';
				condition += "</select></label>";
				condition += "<label>随访检查类型:<select id=\"qh_flpetypeid\" name=\"qh_flpetypeid\" style=\"width:120px;\">";
				condition += "</select></label>";
				condition += " <label><input type=\"button\" id=\"btnSearch\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnempty\" value=\"重置\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnadd\" value=\"添加\"></label> ";
				
				$("#followuplist-table_filter").append(condition);
				
				$( "#qh_startday" ).datepicker({
					format: 'yyyy/mm/dd',
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
					format: 'yyyy/mm/dd',
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
					var u = base + '/mgr_followuplistjson.html?a=a';
					u += "&staffId="+ $('#qh_staffid option:selected').attr("value");
					u += "&prsnId="+ $("#docinfoid").attr("value");
					u += "&startDay=" + $("#qh_startday").val();
					u += "&finishDay=" + $("#qh_finishday").val();
					u += "&flpeMiscType=" + $("#qh_flpemisctype").val();
					u += "&flpeTypeId="+ $('#qh_flpetypeid option:selected').attr("value");
					oFollowUpTable.ajax.url(u).load();
				});
				//重置按钮
				$("#btnempty").click(function(){
					$("#qh_staffid").val("");
					$("#qh_prsnid").val("");
					$("#qh_orgid").val("");
					$("#qh_startday").val("");
					$("#qh_finishday").val("");
					$("#qh_flpemisctype").val("");
					$("#qh_flpetypeid").val("");
					var u = base + '/mgr_followuplistjson.html?prsnId='+$("#docinfoid").attr("value");
					oFollowUpTable.ajax.url(u).load();
				});
				
				//添加按钮
				$("#btnadd").click(function(){
					var prsnId = $("#docinfoid").attr("value");
					window.location.href=base+"/followuppage.html?prsnId=" + prsnId + "&activepage=followup";
				});
				DocInfoPage.initFollowData();
			}
		});
	},
	initFollowData:function(){
		// 初始化类型列表
		$.ajax({
			url: base + '/refvaluelist.html?refid=56',
			type: 'GET',
			dataType: 'json',
			success: function (resp) {
				var html = "";
				html += "<option value=\"\">全部</option>";
				for(var i=0;i<resp.aaData.length;i++){
					html += '<option value="' + resp.aaData[i][0] + '">' + resp.aaData[i][3] + '</option>';
				}
				$("#qh_flpetypeid option").remove();
				$("#qh_flpetypeid").append(html);
			}
		});
		//初始化医生列表
		$.ajax({
			url: base + '/mgr_stafflistjson.html',
			type: 'GET',
			dataType: 'json',
			success: function (resp) {
				var html = "";
				html += "<option value=\"\">全部</option>";
				for(var i=0;i<resp.aaData.length;i++){
					html += '<option value="' + resp.aaData[i][0] + '">' + resp.aaData[i][3] + '</option>';
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
	initHealthExamTable:function(){
		oHealthExamTable = $('#healthexamlist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/mgr_healthexamlistjson.html?prsnId="+$("#docinfoid").attr("value"),
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
							html += '<button class="btn btn-xs btn-info" id="healthexam'+ data[0] + '" activepage = physical><i class="icon-cogs bigger-120">详情</i></button>';
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
					format: 'yyyy/mm/dd',
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
					format: 'yyyy/mm/dd',
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
					u += "&status=" + $("#qh_status").val();
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
				DocInfoPage.initHealthExamData();
			}
		});
	},
	initHealthExamData:function(){
		//初始化医生列表
		$.ajax({
			url: base + '/mgr_stafflistjson.html',
			type: 'GET',
			dataType: 'json',
			success: function (resp) {
				var html = "";
				html += "<option value=\"\">全部</option>";
				for(var i=0;i<resp.aaData.length;i++){
					html += '<option value="' + resp.aaData[i][0] + '">' + resp.aaData[i][3] + '</option>';
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
	initExamTable:function(){
		oExamTable = $('#examlist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/mgr_examlistjson.html?prsnId="+$("#docinfoid").attr("value"),
			"columnDefs" :[
				{
				    "targets": 12,
				    "data": null,
				    "render": function(data, type, row) {
			    		return data[12].substring(11,16);
				    }
				},{
				    "targets": 13,
				    "data": null,
				    "render": function(data, type, row) {
			    		return data[13].substring(0,11);
				    }
				},{
				    "targets": 15,
				    "data": null,
				    "render": function(data, type, row) {
				    	var html = "";
						html += '<button class="btn btn-xs btn-info" id="jumpexampage'+ data[0] + '" activepage = exam><i class="icon-cogs bigger-120">详情</a></i></button>';
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
						{ "sName": "检测类型ID", "bSortable": false, "bVisible": false},
						{ "sName": "参照Code", "bSortable": false, "bVisible": false},
						{ "sName": "参照类型名称", "bSortable": false, "bVisible": true},
						{ "sName": "随访ID", "bSortable": false, "bVisible": false},
						{ "sName": "居民ID", "bSortable": false, "bVisible": false},
						{ "sName": "居民名称", "bSortable": false, "bVisible": true},
						{ "sName": "医生ID", "bSortable": false, "bVisible": false},
						{ "sName": "医生名称", "bSortable": false, "bVisible": true},
						{ "sName": "组织ID", "bSortable": false, "bVisible": false},
						{ "sName": "组织名称", "bSortable": false, "bVisible": true},
						{ "sName": "检查时间", "bSortable": false, "bVisible": true},
						{ "sName": "检查日", "bSortable": false, "bVisible": true},
						{ "sName": "摘要", "bSortable": false, "bVisible": true},
						{ "sName": "操作", "bSortable": false, "bVisible": true}					
			],
			"fnInitComplete": function() {
				$("#examlist-table_filter label").detach();				
				var condition = "";
				condition += "<label>医生:<select id=\"qh_staffid\" name=\"qh_staffid\" style=\"width:120px;\">";
				condition += "</select></label>";
				condition += "<label>查询时间:<input type=\"text\" id=\"qh_startday\" style=\"width:80px;\"/>—";
				condition += "<input type=\"text\" id=\"qh_finishday\" style=\"width:80px;\"/>";
				condition += "<i class=\"icon-calendar\"></i>";
				condition += "</label>";
				condition += "<label>测量类型:<select id=\"qh_reftypeCode\" name=\"qh_reftypeCode\" style=\"width:120px;\">";
				condition += "</select></label>";
				condition += " <label><input type=\"button\" id=\"btnSearch\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnempty\" value=\"重置\"></label> ";
				
				$("#examlist-table_filter").append(condition);
				
				$( "#qh_startday" ).datepicker({
					format: 'yyyy/mm/dd',
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
					format: 'yyyy/mm/dd',
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
					var u = base + '/mgr_examlistjson.html?a=a';
					u += "&staffId="+ $('#qh_staffid option:selected').attr("value");
					u += "&prsnId="+ $("#docinfoid").attr("value");
					u += "&startDay=" + $("#qh_startday").val();
					u += "&finishDay=" + $("#qh_finishday").val();
					u += "&refTypeCode="+ $('#qh_reftypeCode option:selected').attr("value");
					oExamTable.ajax.url(u).load();
				});
				//重置按钮
				$("#btnempty").click(function(){
					$("#qh_staffid").val("");
					$("#qh_orgid").val("");
					$("#qh_startday").val("");
					$("#qh_finishday").val("");
					$("#qh_reftypeCode").val("");
					var u = base + '/mgr_examlistjson.html?prsnId='+$("#docinfoid").attr("value");
					oExamTable.ajax.url(u).load();
				});
				DocInfoPage.initExamData();
			}
		});
	},
	initExamData:function(){
		// 初始化测量类型列表
		$.ajax({
			url: base + '/refvaluelist.html?refid=215',
			type: 'GET',
			dataType: 'json',
			success: function (resp) {
				var html = "";
				html += "<option value=\"\">全部</option>";
				for(var i=0;i<resp.aaData.length;i++){
					html += '<option value="' + resp.aaData[i][1] + '">' + resp.aaData[i][3] + '</option>';
				}
				$("#qh_reftypeCode option").remove();
				$("#qh_reftypeCode").append(html);
			}
		});
		//初始化医生列表
		$.ajax({
			url: base + '/mgr_stafflistjson.html',
			type: 'GET',
			dataType: 'json',
			success: function (resp) {
				var html = "";
				html += "<option value=\"\">全部</option>";
				for(var i=0;i<resp.aaData.length;i++){
					html += '<option value="' + resp.aaData[i][0] + '">' + resp.aaData[i][3] + '</option>';
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
					html += '<option value="' + resp.aaData[i][0] + '">' + resp.aaData[i][3] + '</option>';
				}
				$("#qh_prsnid option").remove();
				$("#qh_prsnid").append(html);
			}
		});
	},
	bindEvent:function(){
		$("#save-docinfo-btn").click(function(){
			DocInfoPage.saveOrModifyDocInfo();
		});
		$('#followuplist-table').on('xhr.dt', function (e,settings,json,xhr) {
			setTimeout(DocInfoPage.rebindEvent,300);
		});
		$('#healthexamlist-table').on('xhr.dt', function (e,settings,json,xhr) {
			setTimeout(DocInfoPage.rebindEvent,300);
		});
		$('#examlist-table').on('xhr.dt', function (e,settings,json,xhr) {
			setTimeout(DocInfoPage.rebindEvent,300);
		});
	},
	initData:function(docinfoid){
		//获取该用户档案信息
		$.ajax({
			url: base + '/docinfopagedata.html?docinfoid='+docinfoid,
			type: 'POST',
			dataType: 'json',
			success: function (resp) {
				initData = resp.dataHash;
			}
		});
		
		//获取该用户建档配置  及进行数据初始化
		$.ajax({
			url: base + '/tablecfgrefjson.html?code=R1001',
			type: 'GET',
			dataType: 'json',
			success: function (resp) {
				//TODO 只需输入数据     输入数据回显
				for (k in resp.alldata){
					//动态添加属性
					var myid = "cfgline_"+ resp.alldata[k].lineCode;
					//动态添加标签属性
					$("#" + myid).attr("data-req",resp.alldata[k].isReq);
					$("#" + myid).attr("data-title",resp.alldata[k].lineTitle);
					$("#" + myid).attr("data-reftypeid",resp.alldata[k].lineRefTypeId);
 					$("#" + myid).attr("data-multiref",resp.alldata[k].lineMultiRef);
 					if((k in initData) && resp.alldata[k].lineRefTypeId == 0){//输入数据回显
 						var inputVl =  initData[k].lines[0].inputVl;
 	 					if(inputVl != '' && inputVl !=null){
 	 						$("#" + myid).attr("value",inputVl);
 	 					}
 					}
				}
				
				// 引用数据信息 回显
				for (k in resp.data){
					var sels = [];
					var html = "";
					if(resp.data[k].lineMultiRef == "N"){//单选
						for(var i=0;i<resp.data[k].refVls.length;i++){
						var s = {"id":resp.data[k].refVls[i].refVlId,"text":resp.data[k].refVls[i].refVlName};
							sels.push(s);
						}
						var refid = 0;
						if(k in initData ){ //判断单选是否有数据
	 						refid =  initData[k].lines[0].refId;
						}
						if(refid == 0){
							//单选显示
							$('#cfgline_' + resp.data[k].lineCode).text("请选择").editable({
								type: 'select2',
								value : refid,
						        source: sels
						    });
						}else{
							$('#cfgline_' + resp.data[k].lineCode).editable({
								type: 'select2',
								value : refid,
						        source: sels
						    });
						}
					}else if(resp.data[k].lineMultiRef == "Y"){ //多选
						for(var i=0;i<resp.data[k].refVls.length;i++){
							html += '<input type="checkbox" name="'+resp.data[k].lineCode+'" data-value = "'+resp.data[k].refVls[i].refVlId+'"  value = "'+resp.data[k].refVls[i].refVlName+'" /> '+resp.data[k].refVls[i].refVlName+'<br />';
						}
						var refVls = [];
						var refIds = [];
						if(k in initData ){ //判断多选是否有数据
	 						for(var i=0;i<initData[k].lines.length;i++){
		 						var refVl = initData[k].lines[i].refVl;
			 					var refId = initData[k].lines[i].refId;
		 						refIds.push(refId);
		 						refVls.push(refVl);
		 						}
							}
							var myid = "cfgline_"+resp.data[k].lineCode;
							//多选数据回显
							$("#" + myid).attr("value",refVls);
							$("#" + myid).attr("data-refvlid",refIds);
						}
						//多选页面绑定
						$('#cfgline_' + resp.data[k].lineCode + "_dialog .show").append(html);
				}
				DocInfoPage.rebindEvent();
			}
		});
	},
	rebindEvent:function(){
		$("[id^='followupDialog']").unbind().click(function(){
			var followId = $(this).attr("id").substring("followupDialog".length);
			var prsnId = $(this).attr("prsn_id");
			var tableId = $(this).attr("table_id");
			
			$("#followupid").attr("value",followId);
			$("#tableCode").attr("value",tableId);
			$("#prsnId").attr("value",prsnId);
			Followup.init();
			DocInfoPage.addFollow();
		});
		
		// 随访详情页跳转
		$("[id^='jumpfollowpage']").unbind().click(function(){
			var followId = $(this).attr("id").substring("jumpfollowpage".length);
			var prsnId = $(this).attr("prsn_id");
			var activepage = $(this).attr("activepage");
			window.location.href=base+"/followuppage.html?prsnId="+prsnId+'&followupid='+followId +'&activepage='+activepage;
		});
		
		// 体检详情页跳转
		$("[id^='healthexam']").unbind().click(function(){
			var healthId = $(this).attr("id").substring("healthexam".length);
			var prsnId = $(this).attr("prsn_id");
			var activepage = $(this).attr("activepage");
			window.location.href=base+"/healthexampage.html?prsnId="+prsnId+'&healthId='+healthId +'&activepage='+activepage;
		});
		
	    //药物过敏史弹出框
		var id = 0;
	    $("[class*='show_cfgline_']").click(function(){
	    	var thisid = $(this).attr("id");
	    	id = thisid.substring("cfgline_".length);
			$('.cover_cfgline_10').show();
			$('.adjective_cfgline_'+id).fadeIn("slow");
		});
		$("[class*='cover_cfgline']").click(function(){
			$('.adjective_cfgline_'+id).hide();
			$('.cover_cfgline_10').hide();
		});
		$("[class*='adjectiveBut_cfgline_']").click(function(){
			var refids = [];
			$.each($('input:checkbox:checked'),function(){
			var	refid = $(this).attr('data-value');
			refids.push(refid);
			
            obj = document.getElementsByName(id);
              
				check_val = [];
				for(k in obj){
					if(obj[k].checked)
    					check_val.push(obj[k].value);
				}
			$('.show_cfgline_'+id).attr('value',check_val);	
            $('.adjective_cfgline_'+id).hide();
			$('.cover_cfgline_10').hide();
			});
			//TODO 变通用
	        $("#cfgline_"+id).attr("data-refvlid",refids); 
		});
	},
	saveOrModifyDocInfo:function(){
		var reqDatas = [];
		var cfgs = $("[id^='cfgline_']");
		for(var index=0; index<cfgs.length; index++){
			var isreq = "";
			var vl = "";
			var title = "";
			var id = "";
			var code = "";
			var reftypeid = "";
			var refid = ""; 
			var multiref = ""; 
			
			id = $(cfgs[index]).attr("id");
			
			var data = document.getElementById(id);//遍历dataset
			isreq = data.dataset.req;//是否必须
			title = data.dataset.title;//用于提示(不传递)
			reftypeid = data.dataset.reftypeid;//判断 是否引用(不传递)
			multiref = data.dataset.multiref;//判断单选多选
			if(multiref == "N" && parseInt(reftypeid) != 0)
				refid = $("#" + id).editable('getValue',true);//单选
			else if(multiref == "Y")
				refid = data.dataset.refvlid;//多选
			code = id.substring("cfgline_".length);//列号
			vl = $(cfgs[index]).val();//输入值
			
			
			if(parseInt(reftypeid) == 0){
				if(isreq == "Y" && vl == ""){//输入值判断
					alert("请输入'" + title + "'");
					return;
				}
			}else{
				if(isreq == "Y" && (refid == "" || refid == null || refid.length == 0) ){//参数值判断
					alert("请选择'" + title + "'");
					return;
				}
			}
			//有值得进行保存
			if(multiref == "N"){//单选或者输入
				var rq = {};
				rq.code = code;
				rq.refid = refid;
				rq.vl = vl;
				if(!((refid == "" || refid == null ) && (vl == "" || vl == null)))
					reqDatas.push(rq);
			}else if(multiref == "Y"){//多选     refid为 "219,220,221"
				if(refid.length != 0 ){//保存有选项的
					var arr = refid.split(',');
					for(var i=0 ;i<arr.length;i++){
						var rq = {};
						rq.code = code;
						//TODO 输入值暂定。
						rq.vl = vl;
						rq.refid = arr[i];
						reqDatas.push(rq);
					}
				}
			}
			}
		
		var reqData = {};
		reqData.docinfojson = JSON.stringify(reqDatas);
		reqData.docinfoid = $("#docinfoid").val();
		$.ajax({
			url: base + '/saveormodifydocinfojson.html',
			data: reqData,
			type: 'POST',
			dataType: 'json',
			success: function (resp) {
				alert(resp.message);
			}
		});
		
	}
};

