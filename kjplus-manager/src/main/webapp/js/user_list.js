var base = $("#base").attr("href");
var oUserTable = null;

User = {
	init:function(){
		this.initTable();
		this.initData();
		this.bindEvent();
	},
	initTable:function(){
		oUserTable = $('#userlist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/mgr_userlistjson.html",
			"columnDefs" :[
				{
				    "targets": 6,
				    "data": null,
				    "render": function(data, type, row) {
				    	if(data[6] == "Y")
				    		return "有效";
				    	else
				    		return "无效";
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
				    "targets": 7,
				    "data": null,
				    "render": function(data, type, row) {
				    	if(data[7] == "U")
				    		return "普通用户";
				    	else
				    		return "医生用户";
				    }
				},{
				    "targets": 11,
				    "data": null,
				    "render": function(data, type, row) {
				    	if(data[11] == "W")
				    		return "微信";
				    	else
				    		return "自注册";
				    }
				},{
				    "targets": 1,
				    "data": null,
				    "render": function(data, type, row) {
				    		return '<img src="'+data[1]+'" style="width:40px;height:60px">';
				    	
				    }
				},{
				    "targets": 9,
				    "data": null,
				    "render": function(data, type, row) {
			    		return data[9].substring(0,16);
				    }
				},{
				    "targets": 10,
				    "data": null,
				    "render": function(data, type, row) {
			    		return data[10].substring(0,16);
				    }
				},{
				    "targets": 12,
				    "data": null,
				    "render": function(data, type, row) {
			    		return data[12].substring(5,16);
				    }
				},{
				    "targets": 13,
				    "data": null,
				    "render": function(data, type, row) {
				    	var html = "";
				    	html += '<button class="btn btn-xs btn-info" id="uploadImage'+ data[0] + '"><i class="icon-cogs bigger-120">头像</i></button>';
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
			          	{ "sName": "用户ID", "bSortable": false, "bVisible": true},
			          	{ "sName": "face", "bSortable": false, "bVisible": true},
			          	{ "sName": "用户昵称", "bSortable": false, "bVisible": true},
						{ "sName": "用户名", "bSortable": false, "bVisible": true},
						{ "sName": "邮箱", "bSortable": false, "bVisible": true},
						{ "sName": "电话号码", "bSortable": false, "bVisible": true},
						{ "sName": "电话状态", "bSortable": false, "bVisible": false},
						{ "sName": "用户类型", "bSortable": false, "bVisible": true},
						{ "sName": "状态", "bSortable": false, "bVisible": true},
						{ "sName": "账号开始时间", "bSortable": false, "bVisible": true},
						{ "sName": "账号终止时间", "bSortable": false, "bVisible": true},
						{ "sName": "来源", "bSortable": false, "bVisible": true},
						{ "sName": "创建时间", "bSortable": false, "bVisible": true},
						{ "sName": "操作", "bSortable": false, "bVisible": true}
			],
			"fnInitComplete": function() {
				$("#userlist-table_filter label").detach();				
				var condition = "";
				condition += "<label>昵称:<input type=\"text\" placeholder=\"请输入昵称\" id=\"user_nickname\" /></label>";
				condition += "<label>电话号码:<input type=\"text\" placeholder=\"请输入电话号码\" id=\"user_mobilenum\" /></label>";
				condition += "<label>用户类型:<select id=\"qh_usertype\" name=\"qh_usertype\" style=\"width:80px;\">";
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="D">医生用户</option>';
				condition += '<option value="U">普通用户</option>';
				condition += "</select></label>";
				condition += "<label>状态:<select id=\"info_status\" name=\"info_status\" style=\"width:60px;\">";
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="Y">有效</option>';
				condition += '<option value="N">无效</option>';
				condition += "</select></label>";
				condition += " <label><input type=\"button\" id=\"btnSearch\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnempty\" value=\"重置\"></label> ";
				
				$("#userlist-table_filter").append(condition);
				User.initData();
				
				$("#btnSearch").click(function(){
					var u = base + '/mgr_userlistjson.html?a=a';
					u += "&status=" + $('#info_status option:selected').attr("value");
					u += "&userType=" + $('#qh_usertype option:selected').attr("value");
					u += "&mobileNum=" + $("#user_mobilenum").val();
					u += "&nickName=" + $("#user_nickname").val();
					oUserTable.ajax.url(u).load();
					
				});
				$("#btnempty").click(function(){
					$("#user_nickname").val("");
					$("#user_mobilenum").val("");
					$("#info_status").val("");
					$("#qh_usertype").val("");
					
					var u = base + '/mgr_userlistjson.html';
					oUserTable.ajax.url(u).load();
				});
				
			}
		});
	},
	initData:function(){
		//初始化用户类型列表
		$.ajax({
			url: base + '/mgr_userlistjson.html',
			type: 'GET',
			dataType: 'json',
			success: function (resp) {
				console.log(resp);
				var html = "";
				html += "<option value=\"\">全部</option>";
				for(var i=0;i<resp.aaData.length;i++){
					html += '<option value="' + resp.aaData[i][0] + '">' + resp.aaData[i][2] + '</option>';
				}
				$("#qh_userType option").remove();
				$("#qh_userType").append(html);
				
			}
		});
	},
	bindEvent:function(){
		$('#userlist-table').on('xhr.dt', function(e, settings, json, xhr) {
            setTimeout(User.rebindEvent, 300);
        });
		
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
        						$("#th_uface").val(resp.data.url);
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
		
		$("[id^='uploadImage']").unbind().click(function(){
			var thisuid = $(this).attr("id").substring("uploadImage".length);
			$.ajax({
				url: base + '/mgr_getimgjson.html?uid=' + thisuid,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					User.uploadImageDialog("上传头像", resp.data,thisuid);
				}
			});
		});
	},
	//上传头像
	uploadImageDialog:function(title,resp,uid){
			
			$("#th_uface").val(resp.face);
			$("#imgfileform .icon-remove").click();
			$("#uploadImageDialog").removeClass('hide').dialog({
				height:300, 
				width:300,
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
									var face = $("#th_uface").val();
									var reqData = {};
									reqData.face = face;
									reqData.uid = uid;
									$.ajax({
										url: base + '/mgr_addormodifyfacejson.html',
										data: reqData,
										type: 'POST',
										dataType: 'json',
										success: function (resp) {
											var u = base + '/mgr_userlistjson.html?a=a';
											oUserTable.ajax.url(u).load();
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
	User.init();	
});