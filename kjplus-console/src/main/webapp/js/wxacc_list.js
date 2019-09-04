var base = $("#base").attr("href");
var oWxAccTable = null;

WxAcc = {
	init : function() {
		this.initTable();
		
		this.bindEvent();
	},
	initTable : function() {
		oWxAccTable = $('#wxacclist-table')
				.DataTable(
						{
							"bServerSide" : true,
							"bPaginate" : true,
							"sPaginationType" : "full_numbers",
							"sAjaxSource" : base + "/mgr_wxacclistjson.html",
							"columnDefs" : [
									{
										"targets" : 10,
										"data" : null,
										"render" : function(data, type, row) {
											if (data[10] == "A") {
												return "订阅号";
											} else if (data[10] == "B") {
												return "订阅订证号";
											} else if (data[10] == "R") {
												return "服务号";
											} else {
												return "服务认证号";
											}

										}
									},
									{
										"targets" : 11,
										"data" : null,
										"render" : function(data, type, row) {
											if (data[11] == "S") {
												return "安全";
											} else if (data[11] == "T") {
												return "明文";
											} else {
												return "兼容";
											}

										}
									},
									{
										"targets" : 14,
										"data" : null,
										"render" : function(data, type, row) {
											var html = "";
											html += '<button class="btn btn-xs btn-danger" id="editwxacc'+ data[4]+ '"><i class="icon-cogs bigger-120">修改</i></button>';
											return html;
										}
									} ],
							"oLanguage" : {
								"sLengthMenu" : "每页显示 _MENU_ 条记录",
								"sZeroRecords" : "抱歉， 没有找到",
								"sInfo" : "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
								"sInfoEmpty" : "没有数据",
								"sInfoFiltered" : "(从 _MAX_ 条数据中检索)",
								"oPaginate" : {
									"sFirst" : "首页",
									"sPrevious" : "前一页",
									"sNext" : "后一页",
									"sLast" : "尾页"
								},
								"sZeroRecords" : "没有检索到数据"
							},
							"aLengthMenu": [[5, 10, 15], ["5", "10", "15"]],
							"aoColumns" : [ 
							   {"sName" : "ID","bSortable" : false,"bVisible" : true},
							   {"sName" : "名称","bSortable" : false,"bVisible" : true}, 
							   {"sName" : "昵称","bSortable" : false,"bVisible" : true}, 
							   {"sName" : "URL","bSortable" : false,"bVisible" : false}, 
							   {"sName" : "编码","bSortable" : false,"bVisible" : false}, 
							   {"sName" : "介绍","bSortable" : false,"bVisible" : true}, 
							   {"sName" : "appid","bSortable" : false,"bVisible" : true}, 
							   {"sName" : "appsecret","bSortable" : false,"bVisible" : false}, 
							   {"sName" : "token","bSortable" : false,"bVisible" : false}, 
							   {"sName" : "encoaseskeyding","bSortable" : false,"bVisible" : false}, 
							   {"sName" : "类型","bSortable" : false,"bVisible" : true}, 
								{"sName" : "模式","bSortable" : false,"bVisible" : true}, 
								{"sName" : "组织ID","bSortable" : false,"bVisible" : true,"bVisible" : false}, 
								{"sName" : "组织","bSortable" : false,"bVisible" : true}, 
								{"sName" : "操作","bSortable" : false,"bVisible" : true} ],
							"fnInitComplete" : function() {
								$("#wxacclist-table_filter label").detach();
								var condition = "";
								condition += " <label>名称:<input type=\"text\" placeholder=\"关键字\" id=\"wxacc_keyWord\" /></label>";
								condition += " <label>组织：<select id=\"qh_orgid\" name=\"qh_orgid\" style=\"width:60px;\"></select></label>";
								condition += " <label><input type=\"button\" id=\"btnSearch\" value=\"查询\"></label> ";
								condition += " <label><input type=\"button\" id=\"btnempty\" value=\"重置\"></label> ";
								condition += " <label><input type=\"button\" id=\"btnadd\" value=\"添加\"></label> ";

								$("#wxacclist-table_filter").append(condition);
								WxAcc.rebindEvent();
								
								$("#qh_orgid").css('width', '150');
								
								$("#btnSearch").click(
									function() {
										var u = base+ '/mgr_wxacclistjson.html?a=a';
										u += "&orgid="+ $('#qh_orgid option:selected').attr("value");
										u += "&account="+ $('#wxacc_keyWord').val();
										oWxAccTable.ajax.url(u).load();
												});
								$("#btnempty").click(function() {
									$('#wxacc_keyWord').val("");
									$("#qh_orgid").val("");
									var u = base + '/mgr_wxacclistjson.html';
									oWxAccTable.ajax.url(u).load();
								});
								$("#btnadd").click(function() {
									$("#th_account").val("");
									$("#th_name").val("");
									$("#th_typeid").val("");
									$("#th_modeid").val("");
									$("#th_orgid").val("");
									$("#th_introduction").val("");
									$("#th_appid").val("");
									$("#th_appsecret").val("");
									$("#th_token").val("");
									$("#th_encoaseskeyding").val("");
									WxAcc.addWxAccDialog();
								});
								WxAcc.initData();
							}
						});
	},
	initData:function(){
		// 初始化组织列表
		$.ajax({
			url: base + '/mgr_orglistjson.html',
			type: 'GET',
			dataType: 'json',
			success: function (resp) {
				var html = "";
				html += "<option value=\"\">全部</option>";
				for(var i=0;i<resp.aaData.length;i++){
					html += '<option value="' + resp.aaData[i][0] + '">' + resp.aaData[i][2] + '</option>';
				}
				$("#qh_orgid option").remove();
				$("#qh_orgid").append(html);
				WxAcc.rebindEvent();
			}
		});
	},
	bindEvent : function() {
		$('#wxacclist-table').on('xhr.dt', function (e,settings,json,xhr) {
			setTimeout(WxAcc.rebindEvent,300);
		});
	},
	rebindEvent : function() {
		$("[id^='editwxacc']").unbind().click(function() {
			var accCode = $(this).attr('id').substring("editwxacc".length);
			$("#th_orgid").attr("disabled","disabled").css("background-color","#EEEEEE;");
			WxAcc.editWxAccDialog(accCode);
		});
	},
	editWxAccDialog : function(accCode) {
		$.ajax({
			url : base + '/mgr_getywxaccjson.html?wxacccode=' + accCode,
			type : 'POST',
			dataType : 'json',
			success : function(resp) {
				$("#th_account").val(resp.data.account);
				$("#th_name").val(resp.data.name);
				$("#th_appid").val(resp.data.appid);
				$("#th_appsecret").val(resp.data.appsecret);
				$("#th_token").val(resp.data.token);
				$("#th_encoaseskeyding").val(resp.data.encoaseskeyding);
				$('#th_orgid option[value="'+resp.data.orgid+'"]').attr("selected","selected");
				$('#th_typeid option[value="' + resp.data.type + '"]').attr("selected", "selected");
				$('#th_modeid option[value="' + resp.data.mode + '"]').attr("selected", "selected");
				$("#th_introduction").val(resp.data.introduction);
				$("#th_url").val(resp.data.url);
				$("#th_id").val(resp.data.id);
				$("#wxAccAddDialog").removeClass('hide').dialog(
					{
						height : 600,
						width : 600,
						modal : true,
						title : "修改微信账号",
						title_html : true,
						buttons : [
								{
									html : "<i class='icon-save bigger-110'></i>&nbsp; 保存",
									"class" : "btn btn-danger btn-xs",
									click : function() {
										var thatdialog = this;
										var id = $("#th_id").val();
										var code = accCode;
										var account = $("#th_account").val();
										var name = $("#th_name").val();
										var appid = $("#th_appid").val();
										var appsecret = $("#th_appsecret").val();
										var token = $("#th_token").val();
										var encoaseskeyding = $("#th_encoaseskeyding").val();
										var introduction = $("#th_introduction").val();
										var orgid = $('#th_orgid option:selected').attr("value");
										var typeid = $('#th_typeid option:selected').attr("value");
										var modeid = $('#th_modeid option:selected').attr("value");
										var reqData = {};
										reqData.id = id;
										reqData.account = account;
										reqData.name = name;
										reqData.introduction = introduction;
										reqData.orgid = orgid;
										reqData.typeid = typeid;
										reqData.code = code;
										reqData.appid = appid;
										reqData.appsecret = appsecret;
										reqData.token = token;
										reqData.encoaseskeyding = encoaseskeyding;
										reqData.modeid = modeid;
										console.log(reqData);
										$.ajax({
											url : base+ '/mgr_addormodifywxaccjson.html',
											data : reqData,
											type : 'POST',
											dataType : 'json',
											success : function(resp) {
												var u = base+ '/mgr_wxacclistjson.html?a=a';
												oWxAccTable.ajax.url(u).load();
												alert(resp.message);
												$(thatdialog).dialog("close");
											}
										});
							}
										},{
											html : "<i class='icon-remove bigger-110'></i>&nbsp; 取消",
											"class" : "btn btn-xs",
											click : function() {
												$(this).dialog("close");
											}
										} ]

							});
					}
				});
	},
	addWxAccDialog : function() {

		$("#wxAccAddDialog").removeClass('hide').dialog({
			height : 600,
			width : 600,
			modal : true,
			title : "添加微信账号",
			title_html : true,
			buttons : [ {
				html : "<i class='icon-save bigger-110'></i>&nbsp; 保存",
				"class" : "btn btn-danger btn-xs",
				click : function() {
					var thatdialog = this;
					var id = $("#th_id").val();
					var account = $("#th_account").val();
					var name = $("#th_name").val();
					//var token = $("#th_token").val();
					//var encoaseskeyding = $("#th_encoaseskeyding").val();
					var appid = $("#th_appid").val();
					var introduction = $("#th_introduction").val();
					var appsecret = $("#th_appsecret").val();
					var orgid = $('#th_orgid option:selected').attr("value");
					var type = $('#th_typeid option:selected').attr("value");
					var mode = $('#th_modeid option:selected').attr("value");
					var reqData = {};
					reqData.id = id;
					reqData.account = account;
					reqData.name = name;
					reqData.introduction = introduction;
					reqData.orgid = orgid;
					reqData.appid = appid;
					reqData.type = type;
					reqData.appsecret = appsecret;
					//reqData.token = token;
					//reqData.encoaseskeyding = encoaseskeyding;
					reqData.mode = mode;
					console.log(reqData);
					$.ajax({
						url : base + '/mgr_addormodifywxaccjson.html',
						data : reqData,
						type : 'POST',
						dataType : 'json',
						success : function(resp) {
							var u = base + '/mgr_wxacclistjson.html?a=a';
							oWxAccTable.ajax.url(u).load();
							alert(resp.message);
							$(thatdialog).dialog("close");
						}
					});
				}
			}, {
				html : "<i class='icon-remove bigger-110'></i>&nbsp; 取消",
				"class" : "btn btn-xs",
				click : function() {
					$(this).dialog("close");
				}
			} ]

		});
	}

};

$(function() {
	WxAcc.init();
});