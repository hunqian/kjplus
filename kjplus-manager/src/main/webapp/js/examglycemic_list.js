var base = $("#base").attr("href");
var oExamGlycemicTable = null;

ExamGlycemic = {
	init:function(){
		this.initTable();
		this.initData();
		this.bindEvent();
	},
	initTable:function(){
		oExamGlycemicTable = $('#examglycemiclist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/mgr_examglycemiclistjson.html",
			"columnDefs" :[
				{
				    "targets": 12,
				    "data": null,
				    "render": function(data, type, row) {
			    		return data[12].substring(11,16);
				    }
				},{
				    "targets": 11,
				    "data": null,
				    "render": function(data, type, row) {
				    	if(data[11]=="K")
				    		return "空腹";
				    	if(data[11]=="C")
				    		return "餐后";
				    	else
				    		return "";
				    }
				},{
				    "targets": 13,
				    "data": null,
				    "render": function(data, type, row) {
			    		return data[13].substring(0,11);
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
						{ "sName": "参照类型名称", "bSortable": false, "bVisible": false},
						{ "sName": "随访ID", "bSortable": false, "bVisible": false},
						{ "sName": "居民ID", "bSortable": false, "bVisible": false},
						{ "sName": "居民名称", "bSortable": false, "bVisible": true},
						{ "sName": "医生ID", "bSortable": false, "bVisible": false},
						{ "sName": "医生名称", "bSortable": false, "bVisible": true},
						{ "sName": "血糖值", "bSortable": false, "bVisible": true},
						{ "sName": "测量状况", "bSortable": false, "bVisible": true},
						{ "sName": "检查时间", "bSortable": false, "bVisible": true},
						{ "sName": "检查日", "bSortable": false, "bVisible": true},
						{ "sName": "摘要", "bSortable": false, "bVisible": true}		
						
			],
			"fnInitComplete": function() {
				$("#examglycemiclist-table_filter label").detach();				
				var condition = "";
				condition += "<label>医生:<select id=\"qh_staffid\" name=\"qh_staffid\" style=\"width:120px;\">";
				condition += "</select></label>";
				condition += "<label>居民:<select id=\"qh_prsnid\" name=\"qh_prsnid\" style=\"width:120px;\">";
				condition += "</select></label>";
				condition += "<label>查询时间:<input type=\"text\" id=\"qh_startday\" style=\"width:80px;\"/>—";
				condition += "<input type=\"text\" id=\"qh_finishday\" style=\"width:80px;\"/>";
				condition += "<i class=\"icon-calendar\"></i>";
				condition += "</label>";
				condition += " <label><input type=\"button\" id=\"btnSearch\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnempty\" value=\"重置\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnadd\" value=\"添加\"></label> ";
				
				$("#examglycemiclist-table_filter").append(condition);
				
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
				
				ExamGlycemic.initData();
				
				//查询按钮  
				$("#btnSearch").click(function(){
					var u = base + '/mgr_examglycemiclistjson.html?a=a';
					u += "&staffId="+ $('#qh_staffid option:selected').attr("value");
					u += "&prsnId="+ $('#qh_prsnid option:selected').attr("value");
					u += "&startDay=" + $("#qh_startday").val();
					u += "&finishDay=" + $("#qh_finishday").val();
					oExamGlycemicTable.ajax.url(u).load();
				});
				//重置按钮
				$("#btnempty").click(function(){
					$("#qh_staffid").val("");
					$("#qh_prsnid").val("");
					$("#qh_orgid").val("");
					$("#qh_startday").val("");
					$("#qh_finishday").val("");
					var u = base + '/mgr_examglycemiclistjson.html';
					oExamGlycemicTable.ajax.url(u).load();
				});
				
				//添加按钮
				$("#btnadd").click(function(){
					$("#th_prsnid").val("");
					$("#th_stafid").val("");
					$('#th_glycemictype option:selected').attr("value");
					$("#th_glycemic").val("");
					ExamGlycemic.addExamGlycemicDialog("添加血糖测量记录");
				});		
				
				ExamGlycemic.initData();
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
		
		$('#examglycemiclist-table').on('xhr.dt', function (e,settings,json,xhr) {
			setTimeout(ExamGlycemic.rebindEvent,300);
		});
		
	},addExamGlycemicDialog:function(){
		$("#examGlycemicAddDialog").removeClass('hide').dialog({
			height:600, 
			width:600,
			modal: true,
			title: "添加血压测量记录",
			title_html: true,
			buttons: [
						{
							html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
							"class" : "btn btn-info btn-xs",
							click: function() {
								var glycemictype = $('#th_glycemictype option:selected').attr("value");
								var glycemic = $("#th_glycemic").val();
								var prsnid = $('#th_prsnid option:selected').atWtr("value");
								var staffid = $('#th_staffid option:selected').attr("value");
								var reqData = {};
								reqData.glycemictype = glycemictype;
								reqData.glycemic = glycemic;
								reqData.prsnid = prsnid;
								reqData.staffid = staffid;W
								$.ajax({
									url: base + '/mgr_addexamglycemicjson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										var u = base + '/mgr_examglycemiclistjson.html?a=a';
										oExamGlycemicTable.ajax.url(u).load();
										alert(resp.message);
									}
								});
								$(this).dialog("close");
							}
						},{
							htmsl: "<i class='icon-remove bigger-110'></i>&nbsp; 取消",
							"class" : "btn btn-xs",
							click: function() {
								$(this).dialog("close");
							}
						}
					]	
		
		});
	
	},rebindEvent:function(){
		//血糖测量详情页跳转
		$("[id^='jumpglycemicpage']").unbind().click(function(){
			var glycemicId = $(this).attr("id").substring("jumpglycemicpage".length);
			window.location.href=base+"/docinfopage.html?docinfoid="+glycemicId;
		});
	}
};

$(function(){ 
	ExamGlycemic.init();	
});