var base = $("#base").attr("href");
DvcParam = {
		
	init:function(){
		this.initTable();
		//this.initData();
		this.bindEvent();
	},
	
	initTable:function(){
		DvcParamTable = $('#dvcparamlist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/dvcparamlistjson.html",
			"columnDefs" :[{
						    "targets": 1,
						    "data": null,
						    "render": function(data, type, row) {
						    	if(data[1] == "M")
						    		return "手机";
						    	else if(data[1] == "I")
						    		return "IPAD";
						    	else if(data[1] == "S")
						    		return "血糖仪";
						    	else if(data[1] == "P")
						    		return "血压仪";
						    	else
						    		return data[1];
						    }
						},
							{
							    "targets": 4,
							    "data": null,
							    "render": function(data, type, row) {
							    	if(data[4] == "Y")
							    		return "有效";
							    	else
							    		return "无效";
							    }
							}
							/*,{
							    "targets": 15,
							    "data": null,
							    "render": function(data, type, row) {
							    	var html = "";
									html += '<button class="btn btn-xs btn-info" id="editadminuser'+ data[1] + '"><i class="icon-cogs bigger-120">改</i></button>';
									html += '<button class="btn btn-xs btn-info" id="modifyrole'+ data[0] + '"><i class="icon-cogs bigger-120">角</i></button>';
									html += '<button class="btn btn-xs btn-info" id="rolemenu'+ data[0] + '"><i class="icon-cogs bigger-120">菜</i></button>';								
									return html;
							    }
							}
						*/],
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
						{ "sName": "设别类型", "bSortable": false, "bVisible": true},
						{ "sName": "参数类型", "bSortable": false, "bVisible": true,},
						{ "sName": "参数值", "bSortable": false, "bVisible": true,},
						{ "sName": "状态", "bSortable": false, "bVisible": true},
						{ "sName": "创建时间", "bSortable": false, "bVisible": true},
						//{ "sName": "操作", "bSortable": false, "bVisible": true},
				],
			"fnInitComplete": function() {
				$("#dvcparamlist-table_filter label").detach();						
				var condition = "";
				condition = "<label>关键字：<input type=\"text\" placeholder=\"设备编号\" id=\"qh_dvcparam\" /></label>"; //第一种
				
				condition += "<label>设备类型:<select id=\"qh_dvctype\" name=\"qh_dvctype\" style=\"width:100px;\">";//第二种
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="M">手机</option>';
				condition += '<option value="I">IPAD</option>';
				condition += '<option value="S">血糖仪</option>';
				condition += '<option value="P">血压仪</option>';
				condition += "</select></label>";
				condition += "<label>参数类型:<select id=\"qh_paramtype\" name=\"qh_paramtype\" style=\"width:100px;\">";//第二种
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="MAC">MAC</option>';
				condition += '<option value="SN">SN</option>';
				condition += "</select></label>";
				condition += "<label>状态:<select id=\"qh_flag\" name=\"qh_flag\" style=\"width:100px;\">";//第二种
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="Y">有效</option>';
				condition += '<option value="N">无效</option>';
				condition += "</select></label>";
				condition += " <label><input type=\"button\" id=\"btnSearch\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnempty\" value=\"重置\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnadd\" value=\"添加\"></label> ";
				
				$("#dvcparamlist-table_filter").append(condition);
				//重新捆绑事件 
				DvcParam.rebindEvent();
				//查询按钮  
				$("#btnSearch").click(function(){
					var u = base + '/dvcparamlistjson.html?a=a';
					u += "&dvcparam="+ $('#qh_dvcparam').val();
					u += "&dvctype=" + $('#qh_dvctype option:selected').attr("value");
					u += "&paramtype=" + $('#qh_paramtype option:selected').attr("value");
					u += "&flag=" + $('#qh_flag option:selected').attr("value");
					console.log(u);
					DvcParamTable.ajax.url(u).load();
				});
				
				//重置
				$("#btnempty").click(function(){
					$("#qh_dvcparam").val("");
					$("#qh_dvctype").val("");
					$("#qh_paramtype").val("");
					$("#qh_flag").val("");
					var u = base + '/dvcparamlistjson.html';
					DvcParamTable.ajax.url(u).load();
				});
				//添加按钮
				$("#btnadd").click(function(){
					DvcParam.addDvcParamDialog();
				});	
				DvcParam.rebindEvent();
			}
		});
	},
	bindEvent:function(){
		$('#dvcparamlist-table').on('xhr.dt', function(e, settings, json, xhr) {
            setTimeout(DvcParam.rebindEvent, 300);
        });
	},
	rebindEvent:function(){
	},
	//添加设备
	addDvcParamDialog:function(){
		
		$("#th_dvcparam").val("");
		$("#th_dvctype").val("");
		$("#th_paramtype").val("");
		
		$("#addDvcParamDialog").removeClass('hide').dialog({
			height:320, 
			width:480,
			modal: true,
			title: "添加设备参数",
			title_html: true,
			buttons: [
						{
							html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
							"class" : "btn btn-info btn-xs",
							click: function() {
								var dvcparam = $("#th_dvcparam").val();
								var dvctype=$('#th_dvctype option:selected').attr("value");
								var paramtype=$('#th_paramtype option:selected').attr("value");
								
								var reqData = {};
								reqData.dvcparam = dvcparam;
								reqData.dvctype = dvctype;
								reqData.paramtype = paramtype;
								var thatdialog = this;
								if(dvctype == null || dvctype == ""){
									alert("设备类型不能为空");
								}else if(paramtype == null || paramtype == ""){
									alert("参数类型不能为空");
								}else if(dvcparam == null || dvcparam == ""){
									alert("参数类型不能为空");
								}else if(dvcparam.length > 120){
									alert("设备参数格式不对");
								}else{
									$.ajax({
										url: base + '/adddvcparam.html',
										data: reqData,
										type: 'POST',
										dataType: 'json',
										success: function (resp) {
											alert(resp.message);
											if(resp.result == 1){
												var u = base + '/dvcparamlistjson.html?a=a';
												DvcParamTable.ajax.url(u).load();
												$(thatdialog).dialog("close");
											}
										}
									});
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
	
	
	//修改adminuser
	updateAdminUserDialog:function(title,resp){
			$("#th_uusername").val(resp[0][1]);
			//$("#th_upassword").val("");
			$("#th_uemail").val(resp[0][2]);
			$("#th_umobilenum").val(resp[0][3]);
			$("#th_ustatus").val(resp[0][5]);
			$("#th_uface").val(resp[0][7]);
			$("#th_unickname").val(resp[0][10]);
				$("#adminUserUpdateDialog").removeClass('hide').dialog({
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
										var username = $("#th_uusername").val();
										var password = $("#th_upassword").val();
										var email = $("#th_uemail").val();
										var mobilenum = $("#th_umobilenum").val();
										var face = $("#th_uface").val();
										var status = $("#th_ustatus").val();
										var nickname = $("#th_unickname").val();
										
										var reqData = {};
										reqData.username = username;
										reqData.password = password;
										reqData.email = email;
										reqData.mobilenum = mobilenum;
										reqData.face = face;
										reqData.status = status;
										reqData.nickname = nickname;
										
										$.ajax({
											url: base + '/updateDvcParam.html',
											data: reqData,
											type: 'POST',
											dataType: 'json',
											success: function (resp) {
												var u = base + '/dvcparamlistjson.html?a=a';
												DvcParamTable.ajax.url(u).load();
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
	DvcParam.init();
});