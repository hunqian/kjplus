var base = $("#base").attr("href");
var oMenuListTable = null;

WxMenu = {
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
		oMenuListTable = $('#wxmenulist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/wxmenulistjson.html",
			"columnDefs" :[
			               { 
				            	 "targets": 6,
							    "data": null,
							    "render": function(data, type, row) {
							    	if(data[6] == "view")
							    		return "视图";
							    	else if(data[6] == "click")
							    		return "点击";
							    	else 
							    		return "未设置";
							    	}
							    },
			               { 
			            	 "targets": 7,
						    "data": null,
						    "render": function(data, type, row) {
						    	if(data[7] == "Y")
						    		return "有效";
						    	else
						    		return "无效";
						    	}
						    },
						    {
						    "targets": 8,
						    "data": null,
						    "render": function(data, type, row) {
						    	var html = "";
								html += '链接';
								return html;
						    }
						},
							{
							    "targets": 11,
							    "data": null,
							    "render": function(data, type, row) {
							    	var html = "";
									html += '<button class="btn btn-xs btn-danger" id="updatemenu'+ data[0] + '"><i class="icon-cogs bigger-120">修改</i></button>';
									if(data[4] == 0)
										html += '<button class="btn btn-xs btn-danger" id="addmenu'+ data[0] + '"><i class="icon-cogs bigger-120">增加</i></button>';
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
			"aLengthMenu": [[50], [50]],
			"aoColumns": [
			          	{ "sName": "菜单ID", "bSortable": false, "bVisible": true},
			          	{ "sName": "公众号ID", "bSortable": false, "bVisible": true},
			          	{ "sName": "序号", "bSortable": false, "bVisible": true, "bVisible": false},
						{ "sName": "名称", "bSortable": false, "bVisible": true},
						{ "sName": "父Id", "bSortable": false, "bVisible": true},
						{ "sName": "父名称", "bSortable": false, "bVisible": true},
						{ "sName": "类型", "bSortable": false, "bVisible": true},
						{ "sName": "状态", "bSortable": false, "bVisible": true},
						{ "sName": "URL", "bSortable": false, "bVisible": true},
						{ "sName": "KEY", "bSortable": false, "bVisible": true},
						{ "sName": "NOTE", "bSortable": false, "bVisible": true, "width" : "15%"},
						{ "sName": "操作", "bSortable": false, "bVisible": true}						
				],
				"fnInitComplete": function() {
					$("#wxmenulist-table_filter label").detach();				
					var condition = "";
					condition = "<label><input type=\"text\" placeholder=\"名称筛选\" id=\"qh_accname\" /></label>";
					condition += "<label>公共号:<select id=\"qh_accid\" name=\"qh_accid\" style=\"width:100px;\">";
					condition += "</select></label>";
					
					condition += "<label>状态:<select id=\"qh_flag\" name=\"qh_flag\" style=\"width:60px;\">";
					condition += "<option value=\"\">全部</option>";
					condition += '<option value="Y">有效</option>';
					condition += '<option value="N">无效</option>';
					condition += "</select></label>";
					condition += " <label><input type=\"button\" id=\"btnSearch\" value=\"查询\"></label> ";
					condition += " <label><input type=\"button\" id=\"btnempty\" value=\"重置\"></label> ";
					//添加一级菜单
					condition += " <label><input type=\"button\" id=\"btnadd\" value=\"添加\"></label> ";
					
					$("#wxmenulist-table_filter").append(condition);
					
					$("#qh_accname").change(function(){
						WxMenu.alterAccData();
					});
					
					$("#btnSearch").click(function(){
						var u = base + '/wxmenulistjson.html?a=a';
						u += "&accid=" + $('#qh_accid option:selected').attr("value");
						u += "&flag=" + $('#qh_flag').val();
						console.log(u);
						oMenuListTable.ajax.url(u).load();
					});
					
					$("#btnempty").click(function(){
						$("#qh_accname").val("");
						$("#qh_accid").val("");
						$("#qh_status").val("");
						WxMenu.alterAccData();
						var u = base + '/wxmenulistjson.html?a=a';
						oMenuListTable.ajax.url(u).load();
					});
					WxMenu.alterAccData();
					$("#btnadd").click(function(){
						//必选先选择公众号才可以添加
					//	var accid =  $('#th_accid option:selected').attr("value");
						WxMenu.addMenuListDialog();
					});
					
				}
		});
	},
	bindEvent:function(){
		$('#wxmenulist-table').on('xhr.dt', function (e,settings,json,xhr) {
			setTimeout(WxMenu.rebindEvent,300);
		});
	},
	rebindEvent:function(){
		//添加二级menu
		$("[id^='addmenu']").unbind().click(function(){
			var ids = $(this).attr('id');
			var id = ids.substring("addmenu".length);
			//var accid =  $('#qh_accid option:selected').attr("value");
			WxMenu.addMenuDialog(id);
		});
		//修改menu
		$("[id^='updatemenu']").unbind().click(function(){
			var id = $(this).attr('id');
			WxMenu.updateMenuDialog(id.substring("updatemenu".length));
		});
	},
	//添加父类menu
	addMenuListDialog:function(){
		$("#menuListAddDialog").removeClass('hide').dialog({
			height:600, 
			width:600,
			modal: true,
			//title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='icon-ok'></i>新增课程</h4></div>",
			title: "添加menu一级菜单",
			title_html: true,
			buttons: [
						{
							html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
							"class" : "btn btn-danger btn-xs",
							click: function() {
								//获取页面数据
								var accid = $("#th_laccid").val();
								var name = $("#th_lname").val();
								var url = $("#th_lurl").val();
								var key = $("#th_lkey").val();
								var type = $("#th_ltype").val();
								var note = $("#th_lnote").val();
								var orderSeq = $("#th_lorderSeq").val();
								var reqData = {};
								
								reqData.accid = accid;
								reqData.name = name;
								reqData.url = url;
								reqData.key = key;
								reqData.type = type;
								reqData.note = note;
								reqData.orderSeq = orderSeq;
								$.ajax({
									url: base + '/addmenujson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										var u = base + '/wxmenulistjson.html?accid='+accid;
										//带组织
										oMenuListTable.ajax.url(u).load();
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
	//添加子类menu
	addMenuDialog:function(id){
		$("#menuAddDialog").removeClass('hide').dialog({
			height:600, 
			width:600,
			modal: true,
			//title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='icon-ok'></i>新增课程</h4></div>",
			title: "新增二级菜单",
			title_html: true,
			buttons: [
						{
							html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
							"class" : "btn btn-danger btn-xs",
							click: function() {
								//获取页面数据
								var name = $("#th_name").val();
								var url = $("#th_url").val();
								var key = $("#th_key").val();
								var type = $("#th_type").val();
								var note = $("#th_note").val();
								var orderSeq = $("#th_orderSeq").val();
								var reqData = {};
								reqData.pid = id;
								reqData.name = name;
								reqData.url = url;
								reqData.key = key;
								reqData.type = type;
								reqData.note = note;
								reqData.orderSeq = orderSeq;
								$.ajax({
									url: base + '/addmenujson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										console.log(resp);
										var u = base + '/wxmenulistjson.html?accid='+resp.accid;
										//带组织
										oMenuListTable.ajax.url(u).load();
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
	//修改menu
	updateMenuDialog:function(id){
		var reqData = {};
		reqData.id = id;
 		$.ajax({
			url: base + '/wxmenujson.html',
			data: reqData,
			type: 'POST',
			dataType: 'json',
			success: function (resp) {
				console.log(resp);
				//修改回显数据  数组显示 TODO 完善
				$("#th_uname").val(resp.aaData[0][2]);
				$("#th_uurl").val(resp.aaData[0][6]);
				$("#th_ukey").val(resp.aaData[0][7]);
				$("#th_unote").val(resp.aaData[0][8]);
				$("#th_uorderSeq").val(resp.aaData[0][1]);
				
				//$("#th_addr").val(resp.data.addr);
				//$('#th_orgtypeid option[value="'+resp.data.orgtypeid+'"]').attr("selected","selected");
					
				$("#menuUpdateDialog").removeClass('hide').dialog({
					height:600, 
					width:600,
					modal: true,
					title: "修改menuList",
					title_html: true,
					buttons: [
								{
									html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
									"class" : "btn btn-danger btn-xs",
									click: function() {
										var thatdialog = this;
										//获取页面数据
										var name = $("#th_uname").val();
										var url = $("#th_uurl").val();
										var key = $("#th_ukey").val();
										var note = $("#th_unote").val();
										var orderSeq = $("#th_uorderSeq").val();
										
										var reqData = {};
										console.log(reqData)
										reqData.name = name;
										reqData.url = url;
										reqData.key = key;
										reqData.note = note;
										reqData.orderSeq = orderSeq;
										
										reqData.id = id;
										//reqData.accid = accid;
										
										$.ajax({
											url: base + '/updatemenujson.html',
											data: reqData,
											type: 'POST',
											dataType: 'json',
											success: function (resp) {
												var u = base + '/wxmenulistjson.html?a=a';
												u += "&accid=" + $('#qh_accid option:selected').attr("value");
												oMenuListTable.ajax.url(u).load();
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
		});
	},
	
};

$(function(){ 
	WxMenu.init();	
});