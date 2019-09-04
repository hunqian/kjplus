var base = $("#base").attr("href");
var oTextMsgTable = null;

TextMsg = {
	init:function(){
		this.initTable();
		//this.initData();
		this.bindEvent();
	},
	alterAccData:function(){
		var account = $("#qh_account").val();
		var reqData = {};
		reqData.account = account;
		$.ajax({
			url: base + '/wxacclistjson.html',
			type: 'GET',
			data: reqData,
			dataType: 'json',
			success: function (resp) {
				var html = "";
				html += "<option value=\"\">全部</option>";
				for(var i=0;i<resp.data.length;i++){
					html += '<option value="' + resp.data[i].id + '">' + resp.data[i].name + '</option>';
				}
				$("#qh_accid option").remove();
				$("#qh_accid").append(html);
			}
		});
	},
	initTable:function(){
		oTextMsgTable = $('#wxtextmsglist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/mgr_textmsglistjson.html",
			"columnDefs" :[
				/*{
				    "targets": 7,
				    "data": null,
				    "render": function(data, type, row) {
				    	if(data[7] == "Y")
				    		return "有效";
				    	else
				    		return "无效";
				    }
				},{
				    "targets": 12,
				    "data": null,
				    "render": function(data, type, row) {
				    	var html = "";
						html += '<button class="btn btn-xs btn-info" id="editinfo'+ data[1] + '"><i class="icon-cogs bigger-120">改</i></button>';
						html += '<button class="btn btn-xs btn-info" id="editcontent'+ data[0] + '"><i class="icon-cogs bigger-120">内容</i></button>';
						return html;
				    }
				}*/
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
						{ "sName": "用户信息ID", "bSortable": false, "bVisible": false},
						{ "sName": "用户昵称", "bSortable": false, "bVisible": true},
						{ "sName": "账户ID", "bSortable": false, "bVisible": false},
			          	{ "sName": "账户名称", "bSortable": false, "bVisible": true},
						{ "sName": "消息文本", "bSortable": false, "bVisible": true},
						{ "sName": "创建时间", "bSortable": false, "bVisible": true}
			],
			"fnInitComplete": function() {
				$("#wxtextmsglist-table_filter label").detach();				
				var condition = "";
				condition += "<label>内容:<input type=\"text\" placeholder=\"关键字\" id=\"qh_msg\" /></label>";
				condition += "<label>查询时间:<input type=\"text\" id=\"qh_queryday\" style=\"width:80px;\"/>";
				condition += "<i class=\"icon-calendar\"></i>";
				condition += "</label>";
				condition += "<label>微信号:<input type=\"text\" placeholder=\"关键字\" id=\"qh_account\" /></label>";
				condition += " <label><select id=\"qh_accid\" name=\"qh_accid\" style=\"width:100px;\"></select></label>";
				condition += " <label><input type=\"button\" id=\"btnSearch\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnempty\" value=\"重置\"></label> ";
				
				$("#wxtextmsglist-table_filter").append(condition);
				
				$( "#qh_queryday" ).datepicker({
					//showOtherMonths: true,
					//selectOtherMonths: false,
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
				
				$("#qh_account").change(function(){
					TextMsg.alterAccData();
				});
				
				//查询按钮  
				$("#btnSearch").click(function(){
					var u = base + '/mgr_textmsglistjson.html?a=a';
					u += "&msg="+ $("#qh_msg").val();
					u += "&queryday=" + $("#qh_queryday").val();
					u += "&accid="+ $('#qh_accid option:selected').attr("value");
					oTextMsgTable.ajax.url(u).load();
				});
				
				//重置按钮
				$("#btnempty").click(function(){
					$("#qh_msg").val("");
					$("#qh_queryday").val("");
					$("#qh_accid").val("");
					$("#qh_account").val("");
					TextMsg.alterAccData();
					var u = base + '/mgr_textmsglistjson.html';
					oTextMsgTable.ajax.url(u).load();
				});
				
				TextMsg.alterAccData();
			}
		});
	},
	bindEvent:function(){
		$('#wxtextmsglist-table').on('xhr.dt', function (e,settings,json,xhr) {
			setTimeout(TextMsg.rebindEvent,300);
		});
	}
};

$(function(){ 
	TextMsg.init();	
});