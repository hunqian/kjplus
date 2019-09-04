var base = $("#base").attr("href");
var oMsgtmpTable = null;
Msgtmp = {
	init:function(){
		this.initTable();
		//this.initData();
		this.bindEvent();
	},
	alterAccData:function(){
		var account = $("#qh_accname").val();
		var reqData = {};
		reqData.account = account;
		$.ajax({
			url: base + '/wxacclistjson.html',
			type: 'GET',
			data: reqData,
			dataType: 'json',
			success: function (resp) {
				if(resp.result == 1){
					var html = "";
					html += '<option value="0" selected="selected">全部</option>';
					for(var i=0;i<resp.data.length;i++){
						html += '<option value="'+resp.data[i].id+'">'+resp.data[i].name+'</option>';
					}
					$("#qh_accid option").remove();
					$("#qh_accid").append(html);
				}
			}
		});
	},
	initTable:function(){
		
		oMsgtmpTable = $('#msgtmplist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/wxmsgtmplistjson.html",
			"columnDefs" :[
							{
							    "targets": 8,
							    "data": null,
							    "render": function(data, type, row) {
							    	var html = "";
									html += '<button class="btn btn-xs btn-info" id="editmsgtmp'+ data[0] + '"><i class="icon-cogs bigger-120">标题</i></button>';
									html += '<button class="btn btn-xs btn-info" id="getmsgcontent'+ data[1] + '"><i class="icon-cogs bigger-120">内容</i></button>';
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
			"aLengthMenu": [[-1], ["全部"]],
			"aoColumns": [
			          	{ "sData": "ID", "bSortable": false, "bVisible": true},
			          	{ "sData": "编号", "bSortable": false, "bVisible": true},
			          	{ "sData": "标题", "bSortable": false, "bVisible": true},
			          	{ "sData": "主行业", "bSortable": false, "bVisible": true},
			          	{ "sData": "副行业", "bSortable": false, "bVisible": true},
			          	{ "sData": "用户数量", "bSortable": false, "bVisible": true},
			          	{ "sData": "信息内容", "bSortable": false, "bVisible": true,"bVisible": false},
			          	{ "sData": "模板", "bSortable": false, "bVisible": true, "bVisible": false},
			          	{ "sData": "操作", "bSortable": false, "bVisible": true}
				],
			"fnInitComplete": function() {
				$("#msgtmplist-table_filter label").detach();						
				var condition = "";
				condition = "<label>编码：<input type=\"text\" placeholder=\"编码\" id=\"qh_code\" style=\"width:60px;\"/></label>"; 
				condition += "<label>标题：<input type=\"text\" placeholder=\"关键字\" id=\"qh_title\" style=\"width:100px;\"/></label>";
				condition += " <label><input type=\"button\" id=\"btnSearch\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnempty\" value=\"重置\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnaddtitle\" value=\"添加标题\"></label> ";

				$("#msgtmplist-table_filter").append(condition);
				
				//查询按钮  
				$("#btnSearch").click(function(){
					var u = base + '/wxmsgtmplistjson.html?a=a';
					u += "&title=" + $('#qh_title').val();
					u += "&code=" + $('#qh_code').val();
					oMsgtmpTable.ajax.url(u).load();
				});
				
				//重置
				$("#btnempty").click(function(){
					$('#qh_title').val("");
					$('#qh_code').val("");
					var u = base + '/wxmsgtmplistjson.html';
					oMsgtmpTable.ajax.url(u).load();
				});
				
				//添加按钮
				$("#btnaddtitle").click(function(){
					Msgtmp.addmsgtmplDialog();
				});
				
				Msgtmp.rebindEvent();
			}
		});
		
		oMsgtmpUseTable = $('#msgtmp_userlist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/listmsgtmplusejson.html",
			"columnDefs" :[{ 
				           	 "targets": 7,
							    "data": null,
							    "render": function(data, type, row) {
							    	if(data[7] == "Y")
							    		return "有效";
							    	else
							    		return "无效";
							    	}
							    },
							{/*
							    "targets": 8,
							    "data": null,
							    "render": function(data, type, row) {
							    	var html = "";
									html += '<button class="btn btn-xs btn-info" id="editmagtmp'+ data[0] + '"><i class="icon-cogs bigger-120">标题</i></button>';
									html += '<button class="btn btn-xs btn-info" id="getmsgcontent'+ data[1] + '"><i class="icon-cogs bigger-120">内容</i></button>';
									return html;
							    }
							*/}
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
			          	{ "sData": "ID", "bSortable": false, "bVisible": true},
			          	{ "sData": "accID", "bSortable": false, "bVisible": true},
			          	{ "sData": "account", "bSortable": false, "bVisible": true},
			          	{ "sData": "模板编号", "bSortable": false, "bVisible": true},
			          	{ "sData": "标题", "bSortable": false, "bVisible": true},
			          	{ "sData": "内容", "bSortable": false, "bVisible": true},
			          	{ "sData": "TmplId", "bSortable": false, "bVisible": true},
			          	{ "sData": "状态", "bSortable": false, "bVisible": true}
				],
			"fnInitComplete": function() {
				$("#msgtmp_userlist-table_filter label").detach();						
				var condition = "";
				condition = "<label><input type=\"text\" placeholder=\"名称筛选\" id=\"qh_accname\" /></label>";
				condition += "<label>公共号:<select id=\"qh_accid\" name=\"qh_accid\" style=\"width:100px;\">";
				condition += "</select></label>";
				condition += "<label>模板code：<input type=\"text\" placeholder=\"关键字\" id=\"qh_usecode\" style=\"width:100px;\"/></label>";
				condition += "<label>状态:<select id=\"qh_useflag\" name=\"qh_useflag\" style=\"width:60px;\">";
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="Y">有效</option>';
				condition += '<option value="N">无效</option>';
				condition += " <label><input type=\"button\" id=\"btnUseSearch\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnUseempty\" value=\"重置\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnadduse\" value=\"添加模板使用\"></label> ";

				$("#msgtmp_userlist-table_filter").append(condition);
				
				$("#qh_accname").change(function(){
					Msgtmp.alterAccData();
				});
				
				//查询按钮  
				$("#btnUseSearch").click(function(){
					console.log(111);
					var u = base + '/listmsgtmplusejson.html?a=a';
					u += "&accid=" + $('#qh_accid option:selected').attr("value");
					u += "&tmplcode=" + $('#qh_usecode').val();
					u += "&flag=" + $('#qh_useflag').val();
					console.log(u);
					oMsgtmpUseTable.ajax.url(u).load();
				});
				
				//重置
				$("#btnUseempty").click(function(){
					console.log(111);
					$("#qh_accname").val("");
					$("#qh_accid").val("");
					$('#qh_usecode').val("");
					$('#qh_useflag').val("");
					Msgtmp.alterAccData();
					var u = base + '/listmsgtmplusejson.html';
					oMsgtmpUseTable.ajax.url(u).load();
				});
				Msgtmp.alterAccData();
				//添加按钮
				$("#btnadduse").click(function(){
					Msgtmp.addmsgtmpluseDialog();
				});
				
				Msgtmp.rebindEvent();
			}
		});		
	},
	bindEvent:function(){
		$('#msgtmplist-table').on('xhr.dt', function(e, settings, json, xhr) {
            setTimeout(Msgtmp.rebindEvent, 300);
        });
	},
	rebindEvent:function(){
		
		$("[id^='editmsgtmp']").unbind().click(function(){
			var id = $(this).attr("id").substring("editmsgtmp".length);
			console.log(id);
			$.ajax({
				url: base + '/wxmsgtmpltjson.html?libid=' + id,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					Msgtmp.updateMsgTmplDialog("修改消息模板", resp.msgTmpl);
				}
			});
		});
		
		$("[id^='getmsgcontent']").unbind().click(function(){
			var thiscode = $(this).attr("id").substring("getmsgcontent".length);
			$.ajax({
				url: base + '/wxgetmsgtmplcontentjson.html?code=' + thiscode,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					console.log(resp);
					$("#dh_content").val(resp.data.content);
					$("#dh_demo").val(resp.data.demo);
					$("#showMsgtmpContentDialog").removeClass('hide').dialog({
						height:400, 
						width:400,
						modal: true,
						title: "模板内容",
						title_html: true,
						buttons: [
						          {
										html: "<i class='icon-remove bigger-110'></i>&nbsp; 关闭",
										"class" : "btn btn-xs",
										click: function() {
											$(this).dialog("close");
										}
									}
								]
					});
				}
			});
		});
	},
	//添加消息模板
	addmsgtmplDialog:function(accid){
		$("#addMsgtmpDialog").removeClass('hide').dialog({
			height:600, 
			width:600,
			modal: true,
			//title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='icon-ok'></i>新增课程</h4></div>",
			title: "添加消息模板",
			title_html: true,
			buttons: [
						{
							html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
							"class" : "btn btn-info btn-xs",
							click: function() {
								//获取页面数据
								var title = $("#th_title").val();
								var mainindustry = $("#th_mainindustry").val();
								var subindustry = $("#th_subindustry").val();
								var content = $("#th_content").val();
								var demosample = $("#th_demosample").val();
								var reqData = {};
								reqData.title = title;
								reqData.mainindustry = mainindustry;
								reqData.subindustry = subindustry;
								reqData.content = content;
								reqData.demosample = demosample;
								$.ajax({
									url: base + '/addlibjson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										console.log(resp);
										var u = base + '/wxmsgtmplistjson.html';
										//带组织
										oMsgtmpTable.ajax.url(u).load();
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
	
	//添加消息模板使用
	addmsgtmpluseDialog:function(){
		$("#addMsgtmpUseDialog").removeClass('hide').dialog({
			height:600, 
			width:600,
			modal: true,
			title: "添加消息模板使用",
			title_html: true,
			buttons: [
						{
							html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
							"class" : "btn btn-info btn-xs",
							click: function() {
								//获取页面数据
								var accid = $("#th_accid").val();
								var code = $("#th_usecode").val();
								var reqData = {};
								reqData.accid = accid;
								reqData.code = code;
								console.log(reqData);
								$.ajax({
									url: base + '/addmsgtmplusejson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										console.log(resp);
										var u = base + '/listmsgtmplusejson.html';
										//带组织
										oMsgtmpUseTable.ajax.url(u).load();
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
	
	//修改adminuser
	updateMsgTmplDialog:function(title,resp){
			$("#th_uid").val(resp.id);
			$("#th_ucode").val(resp.code);
			$("#th_utitle").val(resp.title);
			$("#th_umainindustry").val(resp.mainIndustry);
			$("#th_usubindustry").val(resp.subIndustry);
			$("#th_ucontent").val(resp.content);
			$("#th_udemosample").val(resp.demoSample);
			
			$("#msgTmplUpdateDialog").removeClass('hide').dialog({
				height:600, 
				width:600,
				modal: true,
				title: title,
				title_html: true,
				buttons: [
							{
								html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
								"class" : "btn btn-info btn-xs",
								click: function() {
									var thatdialog = this;
									//获取页面数据
									var libid = $("#th_uid").val();
									var code  = $("#th_ucode").val();
									var title = $("#th_utitle").val();
									var mainIndustry = $("#th_umainindustry").val();
									var subIndustry = $("#th_usubindustry").val();
									var content  =  $("#th_ucontent").val();
									var demoSample =$("#th_udemosample").val();
									var reqData = {};
									reqData.libid = libid;
									reqData.code = code;
									reqData.title = title;
									reqData.mainIndustry = mainIndustry;
									reqData.subIndustry = subIndustry;
									reqData.content = content;
									reqData.demoSample = demoSample;
									console.log(reqData);
									$.ajax({
										url: base + '/updateMsgTmpljson.html',
										data: reqData,
										type: 'POST',
										dataType: 'json',
										success: function (resp) {
											var u = base + '/wxmsgtmplistjson.html?a=a';
											oMsgtmpTable.ajax.url(u).load();
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
	
};

$(function(){ 
	Msgtmp.init();
});