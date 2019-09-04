var base = $("#base").attr("href");
var oServCatgTable = null;
var orgId = null;
var periodTypeId = null;

ServCatg = {
	init:function(){
		this.initTable();
		this.initData();
		this.bindEvent();
	},
	initTable:function(){
		oServCatgTable = $('#servcatg-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/mgr_servcatglistjson.html",
			"columnDefs" :[
				{
				    "targets": 7,
				    "data": null,
				    "render": function(data, type, row) {
				    	if(data[7] == "Y")
				    		return "是";
				    	else
				    		return "否";
				    }
				},{
				    "targets": 8,
				    "data": null,
				    "render": function(data, type, row) {
				    	if(data[8] == "Y")
				    		return "有效";
				    	else
				    		return "无效";
				    }
				},{
				    "targets": 9,
				    "data": null,
				    "render": function(data, type, row) {
			    		return data[9].substring(5,16);
				    }
				},{
				    "targets": 13,
				    "data": null,
				    "render": function(data, type, row) {
				    	var html = "";
						html += '<button class="btn btn-xs btn-info" id="editservcatg'+ data[1] + '"><i class="icon-cogs bigger-120">修改</i></button>';
						
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
						{ "sName": "名称", "bSortable": false, "bVisible": true,"width":"20%"},
						{ "sName": "注释", "bSortable": false, "bVisible": true},
						{ "sName": "组织ID", "bSortable": false, "bVisible": false},
						{ "sName": "组织名称", "bSortable": false, "bVisible": true},
						{ "sName": "服务价格", "bSortable": false, "bVisible": true},
						{ "sName": "是否默认", "bSortable": false, "bVisible": true},
						{ "sName": "状态", "bSortable": false, "bVisible": true},
						{ "sName": "创建时间", "bSortable": false, "bVisible": true},
						{ "sName": "服务周期类型ID", "bSortable": false, "bVisible": false},
						{ "sName": "服务周期类型", "bSortable": false, "bVisible": true},
						{ "sName": "周期值", "bSortable": false, "bVisible": true},
						{ "sName": "操作", "bSortable": false, "bVisible": true}
						
			],
			"fnInitComplete": function() {
				$("#servcatg-table_filter label").detach();				
				var condition = "";
				condition = "<label>标题:<input type=\"text\" placeholder=\"关键字\" id=\"servCatg_keyWord\" /></label>"; //第一种
				condition += "<label>组织:<select id=\"qh_orgid\" name=\"qh_orgid\" style=\"width:120px;\">";
				condition += "</select></label>";
				condition += "<label>状态:<select id=\"servcatg_status\" name=\"servcatg_status\" style=\"width:100px;\">";//第二种
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="Y">有效</option>';
				condition += '<option value="N">无效</option>';
				condition += "</select></label>";
				condition += " <label><input type=\"button\" id=\"btnSearch\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnempty\" value=\"重置\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnadd\" value=\"添加\"></label> ";
				
				$("#servcatg-table_filter").append(condition);
				ServCatg.rebindEvent();
				
				//查询按钮  
				$("#btnSearch").click(function(){
					var u = base + '/mgr_servcatglistjson.html?a=a';
					u += "&name="+ $("#servCatg_keyWord").val();
					u += "&orgid="+ $('#qh_orgid option:selected').attr("value");
					u += "&status=" + $("#servcatg_status").val();
					oServCatgTable.ajax.url(u).load();
				});
				//重置按钮
				$("#btnempty").click(function(){
					$("#servCatg_keyWord").val("");
					$("#qh_orgid").val("");
					$("#servcatg_status").val("");
					var u = base + '/mgr_servcatglistjson.html';
					oServCatgTable.ajax.url(u).load();
				});
				//添加按钮
				$("#btnadd").click(function(){
					$("#th_servName").val("");
					$("#th_memo").val("");
					$("#th_orgCode").val("");
					$("#th_price").val("");
					$("#th_default").val("");
					$("#th_status").val("");
					$("#th_periodCode").val("");
					$("#th_val").val("");
					ServCatg.addServCatgDialog("增加组织服务");
				});
				ServCatg.initData();
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
				ServCatg.rebindEvent();
			}
		});
	},
	bindEvent:function(){
		$('#servcatg-table').on('xhr.dt', function (e,settings,json,xhr) {
			setTimeout(ServCatg.rebindEvent,300);
		});
	},
	rebindEvent:function(){
		
		$("[id^='statusN']").unbind().click(function(){
			var code = $(this).attr("id").substring("statusN".length);
			bootbox.confirm("是否将该服务设为无效？",function(re){
				if(re) {
					var status = "N";
					var reqData = {};
					reqData.status = status;
					reqData.code = code;
					$.ajax({
						url: base + '/addormodifyservcatgjson.html',
						data: reqData,
						type: 'GET',
						dataType: 'json',
						success: function (resp) {
							if(resp.result == 1){
								alert(resp.message);
								var u = base + '/mgr_servcatglistjson.html?a=a';
								oServCatgTable.ajax.url(u).load();
							}
						}
					});
				  }
				 });
		}); 
		
		$("[id^='statusY']").unbind().click(function(){
			var code = $(this).attr("id").substring("statusY".length);
			bootbox.confirm("是否将该服务设为有效？",function(re){
				if(re) {
					var status = "Y";
					var reqData = {};
					reqData.status = status;
					reqData.code = code;
					$.ajax({
						url: base + '/addormodifyservcatgjson.html',
						data: reqData,
						type: 'GET',
						dataType: 'json',
						success: function (resp) {
							if(resp.result == 1){
								alert(resp.message);
								var u = base + '/mgr_servcatglistjson.html?a=a';
								oServCatgTable.ajax.url(u).load();
							}
						}
					});
				  }
				 });
		});
		
		$("[id^='editservcatg']").unbind().click(function(){
			var code = $(this).attr("id").substring("editservcatg".length);
			$.ajax({
				url: base + '/getservcatgjson.html?code=' + code,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					ServCatg.editServCatgDialog("修改资讯", resp.data);
				}
			});
		});
		
	},
	addServCatgDialog:function(titleMsg){
		$("#servCatgAddDialog").removeClass('hide').dialog({
			height:600, 
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
								var memo = $("#th_memo").val();
								var orgCode = $('#th_orgCode option:selected').attr("value");
								var price = $("#th_price").val();
								var defa = $("#th_default").val();
								var status = $("#th_status").val();
								var periodCode = $('#th_periodCode option:selected').attr("value");
								var val = $("#th_val").val();
								if(name == null || name == ""){
									alert("名字不能为空!");
								}else if(price < 0){
									alert("价格不能为空!");
								}else if(orgCode == null || orgCode == ""){
									alert("服务类别不能为空!");
								}else if(periodCode == null || periodCode == ""){
									alert("服务类别不能为空!");
								}else if(defa == null || defa == ""){
									alert("默认服务不能为空!");
								}else if(status == null || status == ""){
									alert("状态不能为空!");
								}else if(val <= 0){
									alert("服务周期不能为空!");
								}else{
								var reqData = {};
								reqData.name = name;
								reqData.memo = memo;
								reqData.orgCode = orgCode;
								reqData.price = price;
								reqData.defa = defa;
								reqData.status = status;
								reqData.periodCode = periodCode;
								reqData.val = val;
								$.ajax({
									url: base + '/addormodifyservcatgjson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										var u = base + '/mgr_servcatglistjson.html?a=a';
										oServCatgTable.ajax.url(u).load();
										alert(resp.message);
									}
								});
								$(this).dialog("close");
								}
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
	editServCatgDialog:function(titleMsg,servCatgData){
		if(servCatgData != undefined){
			$("#th_servName").val(servCatgData.name);
			$("#th_memo").val(servCatgData.memo);
			$("#th_price").val(servCatgData.price);
			orgId = servCatgData.orgid;
			periodTypeId = servCatgData.periodTypeId;
			$.ajax({
				url: base + '/getorgbyidjson.html?orgId=' + orgId,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					$('#th_orgCode option[value="'+resp.orgCode+'"]').attr("selected","selected");
					$("#th_orgCode").trigger("change",resp.orgCode);
				}
			});
			
			$('#th_default option[value="'+servCatgData.defa+'"]').attr("selected","selected");
			$("#th_default").trigger("change",servCatgData.defa);
			$('#th_status option[value="'+servCatgData.status+'"]').attr("selected","selected");
			$("#th_status").trigger("change",servCatgData.status);
			$.ajax({
				url: base + '/getrefvaluebyidjson.html?refid=' + periodTypeId,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					$('#th_periodCode option[value="'+resp.refCode+'"]').attr("selected","selected");
					$("#th_periodCode").trigger("change",resp.refCode);
				}
			});
			$("#th_val").val(servCatgData.periodVal);
			
			
			
		}

		$("#servCatgAddDialog").removeClass('hide').dialog({
			height:600, 
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
								var memo = $("#th_memo").val();
								var price = $("#th_price").val();
								var defa = $("#th_default").val();
								var orgCode = $("#th_orgCode").val();
								var periodCode = $("#th_periodCode").val();
								var status = $("#th_status").val();
								var val = $("#th_val").val();
								if(name == null || name == ""){
									alert("名字不能为空!");
								}else if(price < 0){
									alert("价格不能为空!");
								}else if(orgCode == null || orgCode == ""){
									alert("服务类别不能为空!");
								}else if(periodCode == null || periodCode == ""){
									alert("服务类别不能为空!");
								}else if(defa == null || defa == ""){
									alert("默认服务不能为空!");
								}else if(status == null || status == ""){
									alert("状态不能为空!");
								}else if(val <= 0){
									alert("服务周期不能为空!");
								}else{
									var reqData = {};
									reqData.code = servCatgData.code;
									reqData.name = name;
									reqData.memo = memo;
									reqData.price = price;
									reqData.orgCode = orgCode;
									reqData.periodCode = periodCode;
									reqData.defa = defa;
									reqData.status = status;
									reqData.val = val;
									$.ajax({
										url: base + '/addormodifyservcatgjson.html',
										data: reqData,
										type: 'POST',
										dataType: 'json',
										success: function (resp) {
											var u = base + '/mgr_servcatglistjson.html?a=a';
											oServCatgTable.ajax.url(u).load();
											alert(resp.message);
										}
									});
									$(this).dialog("close");
								}
								
								
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
	ServCatg.init();	
});