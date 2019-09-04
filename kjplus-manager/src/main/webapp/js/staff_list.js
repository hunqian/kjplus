var base = $("#base").attr("href");
var oStaffTable = null;

Staff = {
	init:function(){
		this.initTable();
		this.initData();
		this.bindEvent();
	},
	initTable:function(){
		oStaffTable = $('#stafflist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/mgr_stafflistjson.html",
			"columnDefs" :[
				{
				    "targets": 5,
				    "data": null,
				    "render": function(data, type, row) {
				    	if(data[5] == "F")
				    		return "男";
				    	else
				    		return "女";
				    }
				},{
				    "targets": 16,
				    "data": null,
				    "render": function(data, type, row) {
				    	if(data[16] == "Y")
				    		return "有效";
				    	else
				    		return "失效";
				    }
				},
				{
				    "targets": 17,
				    "data": null,
				    "render": function(data, type, row) {
				    	if(data[17] == "Y")
				    		return "服务中";
				    	else
				    		return "停止服务";
				    }
				},{
				    "targets": 3,
				    "data": null,
				    "render": function(data, type, row) {
				    		return '<img src="'+data[3]+'" style="width:40px;height:60px">';
				    	
				    }
				},{
				    "targets": 21,
				    "data": null,
				    "render": function(data, type, row) {
				    	var html = "";
						html += '<button class="btn btn-xs btn-info" id="editstaff'+ data[1] + '"><i class="icon-cogs bigger-120">修改</i></button>';
						html += '<button class="btn btn-xs btn-info" id="resetstaffpassword'+ data[0] + '"><i class="icon-cogs bigger-120">重置</i></button>';
						return html;
				    }
				}
			],
			"aLengthMenu": [[5, 10, 15], ["5", "10", "15"]],
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
			"aoColumns": [
			          	{ "sName": "ID", "bSortable": false, "bVisible": true},
			          	{ "sName": "编码", "bSortable": false, "bVisible": false},
			          	{ "sName": "医生职工号", "bSortable": false, "bVisible": true},
			          	{ "sName": "头像", "bSortable": false, "bVisible": true},
			          	{ "sName": "姓名", "bSortable": false, "bVisible": true},
						{ "sName": "性别", "bSortable": false, "bVisible": true},
						{ "sName": "生日", "bSortable": false, "bVisible": true},
						{ "sName": "电话", "bSortable": false, "bVisible": true},
						{ "sName": "类型ID", "bSortable": false, "bVisible": true, "bVisible": false},
						{ "sName": "类型", "bSortable": false, "bVisible": true},
						{ "sName": "部门ID", "bSortable": false, "bVisible": true, "bVisible": false},
						{ "sName": "部门名称", "bSortable": false, "bVisible": true},
						{ "sName": "组织ID", "bSortable": false, "bVisible": true, "bVisible": false},
						{ "sName": "组织名称", "bSortable": false, "bVisible": true, "bVisible": false},
						{ "sName": "身份证", "bSortable": false, "bVisible": true},
						{ "sName": "驾驶证注册时间", "bSortable": false, "bVisible": true, "bVisible": false},
						{ "sName": "状态", "bSortable": false, "bVisible": true},
						{ "sName": "服务状态", "bSortable": false, "bVisible": true},
						{ "sName": "staffDepName", "bSortable": false, "bVisible": true, "bVisible": false},
						{ "sName": "备忘录", "bSortable": false, "bVisible": true},
						{ "sName": "创建时间", "bSortable": false, "bVisible": true},
						{ "sName": "操作", "bSortable": false, "bVisible": true}
			],
			"fnInitComplete": function() {
				$("#stafflist-table_filter label").detach();				
				var condition = "";
				condition += "<label>部门:<select id=\"qh_deptid\" name=\"qh_deptid\" style=\"width:100px;\">";
				condition += "</select></label>";
				condition += "<label>状态:<select id=\"staff_flag\" name=\"staff_flag\" style=\"width:100px;\">";//第二种
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="Y">有效</option>';
				condition += '<option value="N">无效</option>';
				condition += "</select></label>";
				condition += "<label>服务状态:<select id=\"staff_status\" name=\"staff_status\" style=\"width:100px;\">";//第二种
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="Y">服务中</option>';
				condition += '<option value="N">停止服务</option>';
				condition += "</select></label>";
				condition += " <label><input type=\"button\" id=\"btnSearch\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnempty\" value=\"重置\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnadd\" value=\"添加\"></label> ";
				$("#stafflist-table_filter").append(condition);
				Staff.rebindEvent();
				//查询按钮  
				$("#btnSearch").click(function(){
					var u = base + '/mgr_stafflistjson.html?a=a';
					u += "&flag=" + $('#staff_flag option:selected').attr("value");
					u += "&status=" + $('#staff_status option:selected').attr("value");
					u += "&deptId=" + $('#qh_deptid option:selected').attr("value");
					console.log(u);
					oStaffTable.ajax.url(u).load();
				});
				//重置按钮
				$("#btnempty").click(function(){
					$("#keyWord").val("");
					$("#qh_deptid").val("");
					var u = base + '/mgr_stafflistjson.html';
					oStaffTable.ajax.url(u).load();
				});
				//添加按钮
				$("#btnadd").click(function(){
					$("#th_name").val("");
					$("#th_sex").val("");
					$("#th_birthday").val("");
					$("#th_typeid").val("");
					$("#th_orgid").val("");
					$("#th_deptid").val("");
					$("#th_idcard").val("");
					$("#th_memo").val("");
					$("#th_mobileNum").val("");
					$("#th_headiconurl").val("");
					$("#th_staffCode").val("");
					Staff.addStaffDialog("添加");
				});
				Staff.initData();
			}
		});
	},
	initData:function(){
		console.log("初始化数据");
		// 初始化部门列表
		$.ajax({
			url: base + '/mgr_deptlistjson.html',
			type: 'GET',
			dataType: 'json',
			success: function (resp) {
				var html = "";
				html += "<option value=\"\">全部</option>";
				for(var i=0;i<resp.aaData.length;i++){
					html += '<option value="' + resp.aaData[i][0] + '">' + resp.aaData[i][2] + '</option>';
				}
				$("#qh_deptid option").remove();
				$("#qh_deptid").append(html);
				Staff.rebindEvent();
			}
		});
	},
	bindEvent:function(){
		
		$('#stafflist-table').on('xhr.dt', function (e,settings,json,xhr) {
			setTimeout(Staff.rebindEvent,300);
		});
		
		//医生头像上传
		$('#imgfile').ace_file_input({
			style:'well',
			btn_choose:'用户头像',
			btn_change:null,
			no_icon:'icon-picture',
			thumbnail:'large',
			droppable:true,
			allowExt: ['jpg','jpg','bmp','png','gif'],//该属性只是对文件后缀的控制
			before_change: function(files, dropped) {
				console.log(files);
				var file = files[0];
				if(typeof file === "string") {//files is just a file name here (in browsers that don't support FileReader API)
					if(! (/\.(jpeg|jpg|png|gif)$/i).test(file) ) return false;
				} else {//file is a File object
					var type = $.trim(file.type);
					if( ( type.length > 0 && ! (/^image\/(jpeg|jpg|png|gif|bmp)$/i).test(type) )
							|| ( type.length == 0 && ! (/\.(jpeg|jpg|png|gif|bmp)$/i).test(file.name) )//for android default browser!
						) return false;
	
					if( file.size > 1100000 ) {//~100Kb
						return false;
					}
				}
				var ajax_option = {
        				url: base + "/uploadimgfile.html",//上传头像
        				dataType: "json",
        				success:function(resp){ 
        					if(resp.result == 1){
        						$("#th_headiconurl").val(resp.data.url);
        					}
        				}
        		};
        		$("#imgfileform").ajaxSubmit(ajax_option);
				return true;
			}
		})
		.end().find('button[type=reset]').on(ace.click_event, function(){
			$('#user-profile-3 input[type=file]').ace_file_input('reset_input');
		})
		.end().find('.date-picker').datepicker().next().on(ace.click_event, function(){
			$(this).prev().focus();
		});
	},
	rebindEvent:function(){
		//医生对应的管理员重置 重设为123456。
		$("[id^='resetstaffpassword']").unbind().click(function(){
			var thisstaffid = $(this).attr("id").substring("resetstaffpassword".length);
			$.ajax({
				url: base + '/mgr_resetstaffadminpsw.html?staffid=' + thisstaffid,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					alert(resp.message);
				}
			});
		});

		
		$("[id^='editstaff']").unbind().click(function(){
			var thisstaffcode = $(this).attr("id").substring("editstaff".length);
			$.ajax({
				url: base + '/mgr_getstaffjson.html?staffcode=' + thisstaffcode,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					Staff.editStaffDialog("修改医生信息", resp.data);
				}
			});
		});
		
		$("#th_orgid").unbind().change(function(){ 
			var orgId = $('#th_orgid option:selected').attr("value");
			$.ajax({
				url: base + '/mgr_deptlistjson.html?orgId=' + orgId,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					console.log(resp);
					var html = "";
					html += "<option value=\"\">全部</option>";
					for(var i=0;i<resp.aaData.length;i++){
						html += '<option value="' + resp.aaData[i][0] + '">' + resp.aaData[i][3] + '</option>';
					}
					$("#th_deptid option").remove();
					$("#th_deptid").append(html);
					
				}
			});
		});
		
	},
	addStaffDialog:function(titleMsg){
		$("#staffEditDialog").removeClass('hide').dialog({
			height:650, 
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
								var name = $("#th_name").val();
								var sex = $("#th_sex").val();
								var mobileNum = $("#th_mobileNum").val();
								var birthday = $("#th_birthday").val();
								var typeid = $('#th_typeid option:selected').attr("value");
								var idcard = $("#th_idcard").val();
								var memo = $("#th_memo").val();
								var deptid = $('#th_deptid option:selected').attr("value");
								//var orgid = $('#th_orgid option:selected').attr("value");
								var headiconurl = $("#th_headiconurl").val();
								var staffCode = $("#th_staffCode").val();
								var reqData = {};
								reqData.name = name;
								reqData.headiconurl = headiconurl;
								reqData.sex = sex;
								reqData.mobileNum = mobileNum;
								reqData.staffCode = staffCode;
								reqData.birthday = birthday;
								reqData.typeid = typeid;
								reqData.idcard = idcard;
								reqData.memo = memo;
								reqData.deptid = deptid;
								//reqData.orgid = orgid;
								$.ajax({
									url: base + '/mgr_addormodifystaffjson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										var u = base + '/mgr_stafflistjson.html?a=a';
										oStaffTable.ajax.url(u).load();
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
	editStaffDialog:function(titleMsg,staffData){
				$("#th_name").val(staffData.name);
				$("#th_sex").val(staffData.sex);
				$("#th_birthday").val(staffData.birthday);
				$('#th_typeid option[value="'+staffData.typeid+'"]').attr("selected","selected");
				$('#th_deptid option[value="'+staffData.deptid+'"]').attr("selected","selected");
				$('#th_orgid option[value="'+staffData.orgid+'"]').attr("selected","selected");
				$("#th_idcard").val(staffData.idcard);
				$("#th_memo").val(staffData.memo);
				$("#th_headiconurl").val(staffData.headiconurl);
				//$("#imgfileform .icon-remove").click();
				$("#th_code").val(staffData.staffcode);
				
		$("#staffEditDialog").removeClass('hide').dialog({
			height:650, 
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
								var name = $("#th_name").val();
								var sex = $("#th_sex").val();
								var birthday = $("#th_birthday").val();
								var typeid = $('#th_typeid option:selected').attr("value");
								var idcard = $("#th_idcard").val();
								var memo = $("#th_memo").val();
								var headiconurl = $("#th_headiconurl").val();
								var deptid = $('#th_deptid option:selected').attr("value");
								var orgid = $('#th_orgid option:selected').attr("value");
								var reqData = {};
								reqData.name = name;
								reqData.sex = sex;
								reqData.birthday = birthday;
								reqData.code = staffData.staffcode;
								reqData.typeid = typeid;
								reqData.idcard = idcard;
								reqData.memo = memo;
								reqData.headiconurl = headiconurl;
								reqData.deptid = deptid;
								reqData.orgid = orgid;
								console.log(reqData);
								$.ajax({
									url: base + '/mgr_addormodifystaffjson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										var u = base + '/mgr_stafflistjson.html?a=a';
										oStaffTable.ajax.url(u).load();
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
	Staff.init();	
});