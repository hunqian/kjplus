var base = $("#base").attr("href");
var oInfoCatalogTable = null;

InfoCatalog = {
	init:function(){
		this.initTable();
		this.bindEvent();
	},
	initTable:function(){
		oInfoCatalogTable = $('#infocataloglist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/infocataloglistjson.html",
			"columnDefs" :[
				{
				    "targets": 9,
				    "data": null,
				    "render": function(data, type, row) {
				    	if(data[9] == "Y")
				    		return "有效";
				    	else
				    		return "失效";
				    }
				},{
				    "targets": 10,
				    "data": null,
				    "render": function(data, type, row) {
				    	var html = "";
						html += '<button class="btn btn-xs btn-info" id="editinfocatalog'+ data[1] + '"><i class="icon-cogs bigger-120">修改</i></button>';
						if(data[3] == 0)
							html += '<button class="btn btn-xs btn-info" id="addcatalog'+data[0]+'"><i class="icon-cogs bigger-120">增加</i></button>';
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
			          	{ "sName": "ID", "bSortable": false, "bVisible": true},
						{ "sName": "分类编码", "bSortable": false, "bVisible": true, "width": "20%"},
						{ "sName": "名字", "bSortable": false, "bVisible": true},
						{ "sName": "PID", "bSortable": false, "bVisible": true, "bVisible": false},
						{ "sName": "上级分类", "bSortable": false, "bVisible": true, "bVisible": true},
						{ "sName": "类型ID", "bSortable": false, "bVisible": true, "bVisible": false},
						{ "sName": "类型名称", "bSortable": false, "bVisible": true},
						{ "sName": "orgID", "bSortable": false, "bVisible": true, "bVisible": false},
						{ "sName": "组织名称", "bSortable": false, "bVisible": true},
						{ "sName": "状态", "bSortable": false, "bVisible": true},
						{ "sName": "操作", "bSortable": false, "bVisible": true, "width":"15%"}
			],
			"fnInitComplete": function() {
				$("#infocataloglist-table_filter label").detach();				
				var condition = "";
				condition += "<label>名字:<input type=\"text\" placeholder=\"关键字\" id=\"catalog_name\" /></label>"; //第一种
				condition += " <label>组织：<select id=\"qh_orgid\" name=\"qh_orgid\" style=\"width:120px;\"></select></label>";
				condition += "<label>状态:<select id=\"catalog_flag\" name=\"catalog_flag\" style=\"width:100px;\">";//第二种
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="Y">有效</option>';
				condition += '<option value="N">失效</option>';
				condition += "</select></label>";
				condition += " <label><input type=\"button\" id=\"btnSearch\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnempty\" value=\"重置\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnadd\" value=\"添加\"></label> ";
				
				$("#infocataloglist-table_filter").append(condition);
				InfoCatalog.rebindEvent();
				//查询按钮  
				$("#btnSearch").click(function(){
					var u = base + '/infocataloglistjson.html?a=a';
					u += "&name="+ $("#catalog_name").val();
					u += "&flag=" + $("#catalog_flag").val();
					u += "&orgid=" + $("#qh_orgid option:selected").attr("value");
					oInfoCatalogTable.ajax.url(u).load();
				});
				//重置按钮
				$("#btnempty").click(function(){
					var u = base + '/infocataloglistjson.html';
					oInfoCatalogTable.ajax.url(u).load();
				});
				
				//添加按钮
				$("#btnadd").click(function(){
					InfoCatalog.addCatalogDialog(0, true);
				});
				
				//组织名字筛选
				$("#qh_orgname").change(function(){
					console.log("组织筛选");
					var orgname = $("#qh_orgname").val(); 
					$.ajax({
						url: base + '/orglistjson.html?orgname=' + orgname,
						type: 'GET',
						dataType: 'json',
						success: function (resp) {
							if(resp.result == 1){
								var html = "";
								for(var i=0;i<resp.data.length;i++){
									html += '<option value="'+resp.data[i].id+'">'+resp.data[i].name+'</option>';
								}
								$("#qh_orgid option").remove();
								$("#qh_orgid").append(html);
							}
						}
					});
				});
					//组织筛选信息显示
					$.ajax({
						url: base + '/orglistjson.html?orgname=',
						type: 'GET',
						dataType: 'json',
						success: function (resp) {
							if(resp.result == 1){
								var html = "";
								html += '<option value="0">平台</option>';
								for(var i=0;i<resp.data.length;i++){
									html += '<option value="'+resp.data[i].id+'">'+resp.data[i].name+'</option>';
								}
								$("#qh_orgid option").remove();
								$("#qh_orgid").append(html);
							}
						}
					});
				InfoCatalog.rebindEvent();
			}
		});
	},
	bindEvent:function(){
		$('#infocataloglist-table').on('xhr.dt', function (e,settings,json,xhr) {
			setTimeout(InfoCatalog.rebindEvent,300);
		});
	},
	rebindEvent:function(){
		$("[id^='addcatalog']").unbind().click(function(){
			var ids = $(this).attr('id');
			var pid = ids.substring("addcatalog".length);
			InfoCatalog.addCatalogDialog(pid, false);
		});
		$("[id^='editinfocatalog']").unbind().click(function(){
			var thiscode = $(this).attr("id").substring("editinfocatalog".length);
			InfoCatalog.editInfoCatalogDialog(thiscode);
		});
	},
	//添加子类menu
	addCatalogDialog:function(pid, showOrgSel){
		if(showOrgSel == true){
			$("#showOrgSel").show();
		}else{
			$("#showOrgSel").hide();
		}
		$("#infoCatalogAddDialog").removeClass('hide').dialog({
			height:600, 
			width:600,
			modal: true,
			title: "新增资讯分类",
			title_html: true,
			buttons: [
						{
							html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
							"class" : "btn btn-info btn-xs",
							click: function() {
								//获取页面数据
								var name = $("#th_name").val();
								var typeid = $("#th_typeid").val();
								var orgid = $("#qh_orgid option:selected").attr("value");
								var reqData = {};
								reqData.name = name;
								reqData.typeid = typeid;
								reqData.orgid = orgid;
								reqData.pid = pid;
								console.log(reqData);
								$.ajax({
									url: base + '/addinfocatalogjson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										var u = base + '/infocataloglistjson.html?a=a';
										u += "&name="+ $("#catalog_name").val();
										u += "&flag=" + $("#catalog_flag").val();
										u += "&orgid=" + $("#qh_orgid option:selected").attr("value");
										oInfoCatalogTable.ajax.url(u).load();
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
	editInfoCatalogDialog:function(code){//修改menu
 		$.ajax({
			url:  base + '/getinfocatalogjson.html?code=' + code,
			type: 'POST',
			dataType: 'json',
			success: function (resp) {
				console.log(resp);
				//修改回显数据 
				$("#th_upid").val(resp.data.pid);
				$("#th_uname").val(resp.data.name);
				$("#th_ucode").val(resp.data.code);
				if(resp.data.infoTypeId != 0)
					$('#th_utypeid option[value="'+resp.data.infoTypeId+'"]').prop("selected",true);
				else{
					$('#th_utypeid option').prop("selected",false);
				}
					
				$("#infoCatalogUpdateDialog").removeClass('hide').dialog({
					height:280, 
					width:420,
					modal: true,
					title: "修改资讯分类",
					title_html: true,
					buttons: [
								{
									html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
									"class" : "btn btn-info btn-xs",
									click: function() {
										var thatdialog = this;
										//获取页面数据
										var code = $("#th_ucode").val();
										var name = $("#th_uname").val();
										var infotypeid = $('#th_utypeid option:selected').attr("value");
										
										var reqData = {};
										reqData.code = code;
										reqData.name = name;
										reqData.infotypeid = infotypeid;
										$.ajax({
											url: base + '/updateinfocatalogjson.html',
											data: reqData,
											type: 'POST',
											dataType: 'json',
											success: function (resp) {
												var u = base + '/infocataloglistjson.html?a=a';
												u += "&name="+ $("#catalog_name").val();
												u += "&flag=" + $("#catalog_flag").val();
												u += "&orgid=" + $("#qh_orgid").val();
												oInfoCatalogTable.ajax.url(u).load();
												oInfoCatalogTable.ajax.url(u).load();
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
	InfoCatalog.init();	
});