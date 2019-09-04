var base = $("#base").attr("href");
var oAdminUserTable = null;
var aceMenutree = null;
AdminUser = {
		
	init:function(){
		this.initTable();
		//this.initData();
		this.bindEvent();
	},
	
	alterOrgData:function(){
		var orgname = $("#qh_orgname").val();
		var reqData = {};
		reqData.orgname = orgname;
		$.ajax({
			url: base + '/orglistjson.html',
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
					$("#qh_orgid option").remove();
					$("#qh_orgid").append(html);
				}
			}
		});
	},
	
	initTable:function(){
		oAdminUserTable = $('#adminuserlist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/adminuserlistjson.html",
			"columnDefs" :[
							{
							    "targets": 4,
							    "data": null,
							    "render": function(data, type, row) {
									return data[4].substring(5,16);
							    }
							},
							{
							    "targets": 5,
							    "data": null,
							    "render": function(data, type, row) {
							    	if(data[5] == "Y")
							    		return "有效";
							    	else
							    		return "无效";
							    }
							},{
							    "targets": 6,
							    "data": null,
							    "render": function(data, type, row) {
							    	if(data[6] == "A")
							    		return "管理员";
							    	else
							    		return "普通用户";
							    }
							},{
							    "targets": 7,
							    "data": null,
							    "render": function(data, type, row) {
							    	return '<img src="'+data[7]+'" style="width:40px;height:60px">';
							    }
							},{
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
			"aoColumns": [
			          	{ "sData": "uid", "bSortable": false, "bVisible": true},
						{ "sName": "用户名", "bSortable": false, "bVisible": true},
						{ "sName": "邮箱", "bSortable": false, "bVisible": true,"bVisible": false},
						{ "sName": "手机号", "bSortable": false, "bVisible": true,"bVisible": false},
						{ "sName": "注册时间", "bSortable": false, "bVisible": true},
						{ "sName": "状态", "bSortable": false, "bVisible": true},
						{ "sName": "用户类型", "bSortable": false, "bVisible": true},
						{ "sName": "头像", "bSortable": false, "bVisible": true,"bVisible": true},
						{ "sName": "有效开始", "bSortable": false, "bVisible": true,"bVisible": false},
						{ "sName": "失效时间", "bSortable": false, "bVisible": true,"bVisible": false},
						{ "sName": "昵称", "bSortable": false, "bVisible": true},
						{ "sName": "组织ID", "bSortable": false, "bVisible": true,"bVisible": false},
						{ "sName": "组织名称", "bSortable": false, "bVisible": true},
						{ "sName": "创建时间", "bSortable": false, "bVisible": true,"bVisible": false},
						{ "sName": "对应角色", "bSortable": false, "bVisible": true},
						{ "sName": "操作", "bSortable": false, "bVisible": true}
				],
			"fnInitComplete": function() {
				$("#adminuserlist-table_filter label").detach();						
				var condition = "";
				condition = "<label>关键字：<input type=\"text\" placeholder=\"用户名或昵称\" id=\"qh_name\" /></label>"; //第一种
				
				condition += "<label>组织筛选:<input type=\"text\" placeholder=\"关键字\" id=\"qh_orgname\" /></label>";
				condition += "<label><select id=\"qh_orgid\" name=\"qh_orgid\" style=\"width:100px;\">";
				condition += "<option value=\"\">全部</option>";
				condition += "</select></label>";
				
				condition += "<label>状态:<select id=\"qh_status\" name=\"adminuser_status\" style=\"width:100px;\">";//第二种
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="Y">有效</option>';
				condition += '<option value="N">无效</option>';
				condition += "</select></label>";
				condition += " <label><input type=\"button\" id=\"btnSearch\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnempty\" value=\"重置\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnadd\" value=\"添加\"></label> ";
				
				$("#adminuserlist-table_filter").append(condition);
				//重新捆绑事件 
				AdminUser.rebindEvent();
				//查询按钮  
				$("#btnSearch").click(function(){
					var u = base + '/adminuserlistjson.html?a=a';
					u += "&orgid=" + $('#qh_orgid option:selected').attr("value");
					u += "&name=" + $('#qh_name').val();
					u += "&status=" + $('#qh_status').val();
					console.log(u);
					oAdminUserTable.ajax.url(u).load();
				});
				
				$("#qh_orgname").change(function(){
					AdminUser.alterOrgData();
				});
				//重置
				$("#btnempty").click(function(){
					$("#qh_name").val("");
					$("#qh_orgname").val("");
					$("#qh_orgid").val("");
					$("#qh_status").val("");
					AdminUser.alterOrgData();
					var u = base + '/adminuserlistjson.html';
					oAdminUserTable.ajax.url(u).load();
				});
				AdminUser.alterOrgData();
				//添加按钮
				$("#btnadd").click(function(){
					AdminUser.addAdminUserDialog("增加adminuser",null);
				});	
				
				AdminUser.rebindEvent();
			}
		});
	},
	bindEvent:function(){
		$('#adminuserlist-table').on('xhr.dt', function(e, settings, json, xhr) {
            setTimeout(AdminUser.rebindEvent, 300);
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
		
		$('#imgfile1').ace_file_input({
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
        						$("#th_uface1").val(resp.data.url);
        					}
        				}
        		};
        		$("#imgfileform1").ajaxSubmit(ajax_option);
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
		$("[id^='rolemenu']").unbind().click(function(){
			var thisuid = $(this).attr("id").substring("rolemenu".length);
			$.ajax({
				url: base + '/adminusermenujson.html?uid=' + thisuid,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					var tree_data = {};
					for(var i=0;i<resp.data.length;i++){
						var f = {};
						f.name = resp.data[i].name;
						if(resp.data[i].leaf == "Y")
							f.type = "item";
						else{
							f.type = "folder";
						}
						tree_data[resp.data[i].name] = f;
						if(resp.data[i].leaf == "N"){
							var ctree_data = {};
							var subs = resp.data[i].subs;
							for(var j=0;j<subs.length;j++){
								var f2 = {};
								f2.name = subs[j].name;
								if(subs[j].leaf == "Y")
									f2.type = "item";
								else{
									f2.type = "folder";
								}
								ctree_data[subs[j].name] = f2;
							}
							var ctree_data2 = {};
							ctree_data2['children'] = ctree_data;
							f['additionalParameters'] = ctree_data2;
						}
					}
					var treeDataSource = new DataSourceTree({data: tree_data});
					if(aceMenutree){
						//$("#roleMenuTree").destroy();
		                $("#roleMenuTree").removeData();
		                $("#roleMenuTree").removeData("fu.tree");
		                $("#roleMenuTree").removeData("tree");
					}
					aceMenutree = $('#roleMenuTree').ace_tree({
						dataSource: treeDataSource ,
						multiSelect:true,
						loadingHTML:'<div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div>',
						'open-icon' : 'icon-minus',
						'close-icon' : 'icon-plus',
						'selectable' : false,
						'selected-icon' : 'icon-ok',
						'unselected-icon' : 'icon-remove',
						'cacheItems' : false,
		                'folderSelect' : false
					});
					$("#user_role_menu").removeClass("hide").dialog({
		                modal: true,
		                title: '角色菜单对应',
		                title_html: true,
		                buttons: [{
		                    html: "<i class='icon-remove bigger-110'></i>&nbsp; 关闭",
		                    "class": "btn btn-xs",
		                    click: function() {
		                        $(this).dialog("close");
		                    }
		                }]            
					});
				}
			});
		});
		
		$("[id^='modifyrole']").unbind().click(function(){
			var thisuid = $(this).attr("id").substring("modifyrole".length);
			$.ajax({
				url: base + '/getadminuserolesjson.html?uid=' + thisuid,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
                	$("#user_role_def").html("");
                    var html = "<tr><td style='width:20px'>&nbsp;&nbsp;</td><td style='width:80px'>角色编号</td><td style='margin-left:15px'>角色名称</td></tr>";
                    for (var i = 0; i < resp.data.length; i++) {
                    	html += "<tr>";
                    	html += "<td><input type='checkbox' name='roleids" + resp.data[i].id + "' value=" + resp.data[i].id + " maxlength='30'";
                    	if("Y" == resp.data[i].flag)
                    		html += ' checked="checked"';
                    	html += '></td>';
                        html += "<td><strong>" + resp.data[i].id + "</strong></td>";
                        html += "<td>" + resp.data[i].name + "</td>";
                        html += "</tr>";
                    }
                    $("#user_role_def").append(html);
                    var user_role_def = $("#user_role_def").removeClass("hide").dialog({
                        modal: true,
                        title: '角色对应',
                        title_html: true,
                        buttons: [{
                            html: "<i id='save' class='icon-save bigger-110'></i>&nbsp; 保存",
                            "class": "btn btn-info btn-xs",
                            click: function() {
                               var roleids = $("input[name^='roleids']");
                               var roleidList = [];
                               var crelList = [];
                               var thatDialog = this;
                               roleids.each(function(index){
                            	   if($(this).prop("checked") == true){
                            		   roleidList.push($(this).attr("value"));
                            	   }
                               });
                               var reqData = {};
                               reqData.uid = thisuid;
                               reqData.roleids = roleidList.join();
                                $.ajax({
                                    url: base + '/defadminuserolesjson.html',
                                    data: reqData,
                                    type: 'POST',
                                    dataType: 'json',
                                    success: function(resp) {
                                    	alert(resp.message);
                                    	var u = base + '/adminuserlistjson.html?a=a';
                    					oAdminUserTable.ajax.url(u).load();
                                    	$(thatDialog).dialog("close");
                                    }
                                });                  
                            }
                        },
                        {
                            html: "<i class='icon-remove bigger-110'></i>&nbsp; 取消",
                            "class": "btn btn-xs",
                            click: function() {
                                $(this).dialog("close");
                            }
                        }]
                    
                    });
                    //CourseTable.rebindEvent();                
				}
			});
		});
		
		$("[id^='editadminuser']").unbind().click(function(){
			var thisusername = $(this).attr("id").substring("editadminuser".length);
			$.ajax({
				url: base + '/getadminuserjson.html?username=' + thisusername,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					AdminUser.updateAdminUserDialog("修改adminUser", resp.aaData);
				}
			});
		});
	},
	//添加管理员
	addAdminUserDialog:function(titleMsg,infoData){
		if(infoData != undefined){
			$("#th_title").val(infoData.title);
		}
		$("#th_username").val("");
		$("#th_password").val("");
		$("#th_email").val("");
		$("#th_mobilenum").val("");
		$("#imgfileform .icon-remove").click();
		$("#th_nickname").val("");
		$("#th_orgid").val("");
		
		$("#addAdminUserDialog").removeClass('hide').dialog({
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
								var username = $("#th_username").val();
								var password = $("#th_password").val();
								var email = $("#th_email").val();
								var mobilenum = $("#th_mobilenum").val();
								var face = $("#th_uface1").val();
								var nickname = $("#th_nickname").val();
								var orgid = $('#th_orgid option:selected').attr("value");
								var reqData = {};
								reqData.username = username;
								reqData.password = password;
								reqData.email = email;
								reqData.mobilenum = mobilenum;
								reqData.face = face;
								reqData.nickname = nickname;
								reqData.orgid = orgid;
								$.ajax({
									url: base + '/addadminuser.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										console.log(resp);
										var u = base + '/adminuserlistjson.html?a=a';
										oAdminUserTable.ajax.url(u).load();
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
	updateAdminUserDialog:function(title,resp){
			$("#th_uusername").val(resp[0][1]);
			$("#th_uemail").val(resp[0][2]);
			$("#th_umobilenum").val(resp[0][3]);
			$('#th_ustatus option[value="'+resp[0][5]+'"]').attr("selected","selected");
			$("#imgfileform .icon-remove").click();
			$("#th_uface").val(resp[0][7]);
			$("#imgfileform .icon-remove").click();
			$("#th_unickname").val(resp[0][10]);
			$("#th_orgid1").val(resp[0][11]);
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
										var status = $('#th_ustatus option:selected').attr("value");
										var nickname = $("#th_unickname").val();
										var orgid = $('#th_orgid1 option:selected').attr("value");
										var reqData = {};
										reqData.username = username;
										reqData.password = password;
										reqData.email = email;
										reqData.mobilenum = mobilenum;
										reqData.face = face;
										reqData.status = status;
										reqData.nickname = nickname;
										reqData.orgid = orgid;
										
										$.ajax({
											url: base + '/updateadminuser.html',
											data: reqData,
											type: 'POST',
											dataType: 'json',
											success: function (resp) {
												var u = base + '/adminuserlistjson.html?a=a';
												oAdminUserTable.ajax.url(u).load();
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
	AdminUser.init();
});