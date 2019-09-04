var base = $("#base").attr("href");
var oFollowUpTable = null;

FollowUp = {
	init:function(){
		this.initTable();
		this.initData();
		this.bindEvent();
	},
	initTable:function(){
		oFollowUpTable = $('#followuplist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/mgr_followuplistjson.html?prsnId="+$("#prsnId").attr("value"),
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
						html += '<button class="btn btn-xs btn-info" id="followupdetails'+ data[0] + '"prsn_id = '+data[3]+'><i class="icon-save bigger-120">详情</a></i></button>';
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
			          	{ "sName": "本次随访编码", "bSortable": false, "bVisible": false},
						{ "sName": "编码", "bSortable": false, "bVisible": false},
						{ "sName": "居民ID", "bSortable": false, "bVisible": false},
						{ "sName": "居民姓名", "bSortable": false, "bVisible": true},
						{ "sName": "医生ID", "bSortable": false, "bVisible": false},
						{ "sName": "医生名称", "bSortable": false, "bVisible": true},
						{ "sName": "组织ID", "bSortable": false, "bVisible": false},
						{ "sName": "组织名称", "bSortable": false, "bVisible": true},
						{ "sName": "表格配置Code", "bSortable": false, "bVisible": false},
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
				condition += "<label>居民:<select id=\"qh_prsnid\" name=\"qh_prsnid\" style=\"width:120px;\">";
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
				
				$("#followuplist-table_filter").append(condition);
				
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
					var u = base + '/mgr_followuplistjson.html?a=a';
					u += "&staffId="+ $('#qh_staffid option:selected').attr("value");
					u += "&prsnId="+ $('#qh_prsnid option:selected').attr("value");
					u += "&startDay=" + $("#qh_startday").val();
					u += "&finishDay=" + $("#qh_finishday").val();
					u += "&flpeMiscType=" + $("#qh_flpemisctype").val();
					u += "&flpeTypeId="+ $('#qh_flpetypeid option:selected').attr("value");
					console.log($('#qh_flpetypeid option:selected').attr("value"));
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
					var u = base + '/mgr_followuplistjson.html';
					oFollowUpTable.ajax.url(u).load();
				});
				FollowUp.initData();
			}
		});
	},
	initData:function(){
		
		// 初始化类型列表
		$.ajax({
			url: base + '/refvaluelist.html?refid=216',
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
	bindEvent:function(){
		$('#followuplist-table').on('xhr.dt', function(e, settings, json, xhr) {
            setTimeout(FollowUp.rebindEvent, 300);
        });
	},rebindEvent:function(){
		//随访详情页跳转 
		$("[id^='followupdetails']").unbind().click(function(){
			var followupid = $(this).attr("id").substring("followupdetails".length);
			var prsnId = $(this).attr("prsn_id");
			window.location.href=base+"/followuppage.html?followupid="+ followupid +"&prsnId="+prsnId;
		});
	}
};

$(function(){ 
	FollowUp.init();	
});