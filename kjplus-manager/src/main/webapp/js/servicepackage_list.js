var base = $("#base").attr("href");
var oSrvPackageTable = null;
var orgId = null;
var periodTypeId = null;

SrvPackage = {
	init:function(){
		this.initTable();
		this.initData();
		this.bindEvent();
	},
	initTable:function(){
		oSrvPackageTable = $('#srvpackage-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/mgr_srvpackagelistjson.html",
			"columnDefs" :[
				{
				    "targets": 5,
				    "data": null,
				    "render": function(data, type, row) {
				    	if(data[5] == "Y")
				    		return "是";
				    	else
				    		return "否";
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
				    "targets": 11,
				    "data": null,
				    "render": function(data, type, row) {
			    		return data[11].substring(0,11);
				    }
				},{
				    "targets": 12,
				    "data": null,
				    "render": function(data, type, row) {
				    	var html = "";
						html += '<button class="btn btn-xs btn-info" id="editsrvpackage'+ data[1] + '"><i class="icon-cogs bigger-120">修改</i></button>';
						if(data[5] == "N")
							html += '<button class="btn btn-xs btn-info" id="editpackageisdefault'+ data[1] + '"><i class="icon-cogs bigger-120">设为默认</i></button>';
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
			"aLengthMenu": [[10, 15, 25], ["10", "15", "25"]],
			"aoColumns": [
			          	{ "sName": "ID", "bSortable": false, "bVisible": true},
			          	{ "sName": "编码", "bSortable": false, "bVisible": false},
						{ "sName": "名称", "bSortable": false, "bVisible": true,"width":"13%"},
						{ "sName": "简称", "bSortable": false, "bVisible": true,"width":"4%"},
						{ "sName": "价格", "bSortable": false, "bVisible": true},
						{ "sName": "默认服务包", "bSortable": false, "bVisible": true},
						{ "sName": "状态", "bSortable": false, "bVisible": true},
						{ "sName": "服务周期类型ID", "bSortable": false, "bVisible": false},
						{ "sName": "周期值", "bSortable": false, "bVisible": true},
						{ "sName": "服务周期类型", "bSortable": false, "bVisible": true},
						{ "sName": "备注", "bSortable": false, "bVisible": true},
						{ "sName": "创建时间", "bSortable": false, "bVisible": true},
						{ "sName": "操作", "bSortable": false, "bVisible": true}
						
			],
			"fnInitComplete": function() {
				$("#srvpackage-table_filter label").detach();				
				var condition = "";
				condition = "<label>关键字:<input type=\"text\" placeholder=\"名称或简称\" id=\"qh_name\" /></label>"; //第一种
				condition += "<label>查询时间段:<input type=\"text\" placeholder=\"开始时间\" id=\"qh_startday\" style=\"width:80px;\"/>";
				condition += "<i class=\"icon-calendar\"></i>";
				condition += "<label><input type=\"text\" placeholder=\"结束时间\" id=\"qh_finishday\" style=\"width:80px;\"/>";
				condition += "<i class=\"icon-calendar\"></i>";
				condition += "</label>";
				condition += "<label>状态:<select id=\"qh_status\" name=\"qh_status\" style=\"width:100px;\">";//第二种
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="Y">有效</option>';
				condition += '<option value="N">无效</option>';
				condition += "</select></label>";
				condition += " <label><input type=\"button\" id=\"btnSearch\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnempty\" value=\"重置\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnadd\" value=\"添加\"></label> ";
				
				$("#srvpackage-table_filter").append(condition);
				SrvPackage.rebindEvent();
				
				$( "#qh_startday" ).datepicker({
					dateFormat: 'yy-mm-dd',
					/*format: 'yyyy-mm-dd',*/
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
					/*format: 'yyyy-mm-dd',*/
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
					console.log($("#qh_startday").val());
					var u = base + '/mgr_srvpackagelistjson.html?a=a';
					u += "&name="+ $("#qh_name").val();
					u += "&orgid="+ $('#qh_orgid option:selected').attr("value");
					u += "&startday=" + $("#qh_startday").val();
					u += "&finishday=" + $("#qh_finishday").val();
					u += "&status=" + $("#qh_status").val();
					oSrvPackageTable.ajax.url(u).load();
				});
				//重置按钮
				$("#btnempty").click(function(){
					$("#qh_name").val("");
					$("#qh_orgid").val("");
					$("#qh_startday").val("");
					$("#qh_finishday").val("");
					$("#qh_status").val("");
					var u = base + '/mgr_srvpackagelistjson.html';
					oSrvPackageTable.ajax.url(u).load();
				});
				//添加按钮
				$("#btnadd").click(function(){
					$("#th_servName").val("");
					$("#th_alias").val("");
					$("#th_memo").val("");
					$("#th_price").val("");
					//$("#th_default").val("");
					$("#th_status").val("");
					$("#th_periodCode").val("");
					$("#th_val").val("");
					
					SrvPackage.addSrvPackageDialog("添加居民服务包");
				});
				
				
			}
		});
	},
	initData:function(){
		// 获取orgCode
	/*	$.ajax({
			url: base + '/mgr_srvpackagelistjson.html',
			type: 'GET',
			dataType: 'json',
			success: function (resp) {
				var orgid = resp.orgId;
				$.ajax({
					url: base + '/getorgcodebyidjson.html?orgid='+orgid,
					type: 'GET',
					dataType: 'json',
					success: function (resp) {
						orgCode = resp.orgCode;
						console.log(orgCode);
					}
				});
			}
		});*/
	},
	bindEvent:function(){
		$('#srvpackage-table').on('xhr.dt', function (e,settings,json,xhr) {
			setTimeout(SrvPackage.rebindEvent,300);
		});
	},
	rebindEvent:function(){
		
		//修改居民服务包
		$("[id^='editsrvpackage']").unbind().click(function(){
			var code = $(this).attr("id").substring("editsrvpackage".length);
			$.ajax({
				url: base + '/mgr_getsrvpackagejson.html?code=' + code,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					SrvPackage.editSrvPackageDialog("修改资讯", resp.data);
				}
			});
		});
		
		//设置默认居民服务包
		$("[id^='editpackageisdefault']").unbind().click(function(){
			var code = $(this).attr("id").substring("editpackageisdefault".length);
			$.ajax({
				url: base + '/mgr_addormodifysrvpackageisdefaultjson.html?code=' + code,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					var u = base + '/mgr_srvpackagelistjson.html?a=a';
					oSrvPackageTable.ajax.url(u).load();
					alert(resp.message);
				}
			});
		});
		
	},
	addSrvPackageDialog:function(titleMsg){
		$("#addSrvPackageDialog").removeClass('hide').dialog({
			height:400, 
			width:600,
			modal: true,
			title: titleMsg,
			title_html: true,
			buttons: [
						{
							html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
							"class" : "btn btn-info btn-xs",
							click: function() {
								var name = $("#th_servName").val();
								var alias = $("#th_alias").val();
								var memo = $("#th_memo").val();
								var price = $("#th_price").val();
								//var defa = $("#th_default").val();
								var status = $("#th_status").val();
								var periodCode = $('#th_periodCode option:selected').attr("value");
								var val = $("#th_val").val();
								var reqData = {};
								reqData.name = name;
								reqData.alias = alias;
								reqData.memo = memo;
								//reqData.orgCode = orgCode;
								reqData.price = price;
								//reqData.defa = defa;
								reqData.status = status;
								reqData.periodCode = periodCode;
								reqData.val = val;
								$.ajax({
									url: base + '/mgr_addormodifysrvpackagejson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										var u = base + '/mgr_srvpackagelistjson.html?a=a';
										oSrvPackageTable.ajax.url(u).load();
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
	editSrvPackageDialog:function(titleMsg,servCatgData){
		//修改时数据回显
		if(servCatgData != undefined){
			$("#th_servName").val(servCatgData.name);
			$("#th_alias").val(servCatgData.alias);
			$("#th_memo").val(servCatgData.memo);
			$("#th_price").val(servCatgData.price);
			//orgId = servCatgData.orgid;
			//periodTypeId = servCatgData.periodTypeId;
			$('#th_periodCode option[value="'+ servCatgData.periodTypeCode +'"]').attr("selected","selected");
			$("#th_periodCode").trigger("change",servCatgData.periodTypeCode);
			/*$('#th_default option[value="'+servCatgData.defa+'"]').attr("selected","selected");
			$("#th_default").trigger("change",servCatgData.defa);*/
			$('#th_status option[value="'+servCatgData.status+'"]').attr("selected","selected");
			$("#th_status").trigger("change",servCatgData.status);
			$("#th_val").val(servCatgData.periodVal);
			
			/*$.ajax({
				url: base + '/getorgbyidjson.html?orgId=' + orgId,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					
				}
			});*/
			
/*			$.ajax({
				url: base + '/getrefvaluebyidjson.html?refid=' + periodTypeId,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					$('#th_periodCode option[value="'+resp.refCode+'"]').attr("selected","selected");
					$("#th_periodCode").trigger("change",resp.refCode);
				}
			});
*/			
		}

		//添加和修改时页面跳转
		$("#addSrvPackageDialog").removeClass('hide').dialog({
			height:450, 
			width:600,
			modal: true,
			//title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='icon-ok'></i>新增课程</h4></div>",
			title: titleMsg,
			title_html: true,
			buttons: [
						{
							html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
							"class" : "btn btn-info btn-xs",
							click: function() {
								var name = $("#th_servName").val();
								var alias = $("#th_alias").val();
								var memo = $("#th_memo").val();
								var price = $("#th_price").val();
								//var defa = $("#th_default").val();
								var periodCode = $("#th_periodCode").val();
								var status = $("#th_status").val();
								var val = $("#th_val").val();
								var reqData = {};
								reqData.code = servCatgData.code;
								reqData.name = name;
								reqData.alias = alias;
								reqData.memo = memo;
								reqData.price = price;
								//reqData.orgCode = orgCode;
								reqData.periodCode = periodCode;
								//reqData.defa = defa;
								reqData.status = status;
								reqData.periodid = periodTypeId;
								reqData.val = val;
								console.log(reqData);
								$.ajax({
									url: base + '/mgr_addormodifysrvpackagejson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										var u = base + '/mgr_srvpackagelistjson.html?a=a';
										oSrvPackageTable.ajax.url(u).load();
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
	
	}
};

$(function(){ 
	SrvPackage.init();	
});