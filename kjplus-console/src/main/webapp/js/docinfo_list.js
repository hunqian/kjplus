var base = $("#base").attr("href");
DocInfo = {
		
	init:function(){
		this.initTable();
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
		oDocInfoTable = $('#docinfolist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/docinfolistjson.html",
			"columnDefs" :[
							{
							    "targets": 23,
							    "data": null,
							    "render": function(data, type, row) {
									return data[23].substring(5,10);
							    }
							},{
							    "targets": 24,
							    "data": null,
							    "render": function(data, type, row) {
									return data[24].substring(5,10);
							    }
							},{
							    "targets": 6,
							    "data": null,
							    "render": function(data, type, row) {
							    	if(data[6] == "DS_SEX_M"){
							    		return "男";
							    	}else if(data[6] == "DS_SEX_F"){
							    		return "女";
							    	}else if(data[6] == "DS_SEX_NOSAY"){
							    		return "未说明性别";
							    	}else if(data[6] == "DS_SEX_UNKNOWN"){
							    		return "未知的性别";
							    	}else
							    		return "";
							    }
							},{
							    "targets": 18,
							    "data": null,
							    "render": function(data, type, row) {
							    	if(data[18] == "DS_BLOOD_A"){
							    		return "A型";
							    	}else if(data[18] == "DS_BLOOD_B"){
							    		return "B型";
							    	}else if(data[18] == "DS_BLOOD_O"){
							    		return "O型";
							    	}else if(data[18] == "DS_BLOOD_AB"){
							    		return "AB型";
							    	}else
							    		return "";
							    }
							},{
							    "targets": 10,
							    "data": null,
							    "render": function(data, type, row) {
							    	if(data[10] == "DS_NATION_HAN"){
							    		return "汉族";
							    	}else if(data[10] == "DS_NATION_NHAN"){
							    		return "少数民族";
							    	}else
							    		return "";
							    }
							},{
							    "targets": 2,
							    "data": null,
							    "render": function(data, type, row) {
							    	if(data[2] == "Y"){
							    		return "有效";
							    	}else if(data[2] == "N"){
							    		return "无效";
							    	}
							    }
							},{
							    "targets": 3,
							    "data": null,
							    "render": function(data, type, row) {
							    		return '<img src="'+data[3]+'" style="width:45px;height:45px">';
							    }
							},{
							    "targets": 27,
							    "data": null,
							    "render": function(data, type, row) {
							    	var html = "";
									html += '<button class="btn btn-xs btn-info" id="uploadImage'+ data[1] + '"><i class="icon-cogs bigger-120">上传图片</i></button>';
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
			          	{ "sData": "ID", "bSortable": false, "bVisible": true},
						{ "sName": "Code", "bSortable": false, "bVisible": false},
						{ "sName": "状态", "bSortable": false, "bVisible": true},
						{ "sName": "头图", "bSortable": false, "bVisible": true},
						{ "sName": "prsnCode", "bSortable": false, "bVisible": false},
						{ "sName": "姓名", "bSortable": false, "bVisible": true},
						{ "sName": "性别", "bSortable": false, "bVisible": true},
						{ "sName": "生日", "bSortable": false, "bVisible": true},
						{ "sName": "身份证号", "bSortable": false, "bVisible": true},
						{ "sName": "手机号", "bSortable": false, "bVisible": false},
						{ "sName": "民族", "bSortable": false, "bVisible": true},
						{ "sName": "家庭住址", "bSortable": false, "bVisible": true},
						{ "sName": "建档日", "bSortable": false, "bVisible": true},
						{ "sName": "居民标签", "bSortable": false, "bVisible": true,"width":"12%"},
						{ "sName": "UID", "bSortable": false, "bVisible": false},
						{ "sName": "UNickName", "bSortable": false, "bVisible": false},
						{ "sName": "固定电话", "bSortable": false, "bVisible": false},
						{ "sName": "工作单位", "bSortable": false, "bVisible": false},
						{ "sName": "血型", "bSortable": false, "bVisible": false},
						{ "sName": "联系人姓名", "bSortable": false, "bVisible": false},
						{ "sName": "联系方式", "bSortable": false, "bVisible": false},
						{ "sName": "住址", "bSortable": false, "bVisible": false},
						{ "sName": "组织ID", "bSortable": false, "bVisible": false},
						{ "sName": "组织名称", "bSortable": false, "bVisible": false},
						{ "sName": "创建人", "bSortable": false, "bVisible": false},
						{ "sName": "创建人ID", "bSortable": false, "bVisible": false},
						{ "sName": "创建时间", "bSortable": false, "bVisible": false},
						{ "sName": "操作", "bSortable": false, "bVisible": true}
				],
			"fnInitComplete": function() {
				$("#docinfolist-table_filter label").detach();						
				var condition = "";
				condition = "<label>关键字：<input type=\"text\" placeholder=\"用户名或昵称\" id=\"qh_name\" /></label>"; //第一种
				condition += "<label>身份证：<input type=\"text\" placeholder=\"身份证\" id=\"qh_idcard\" /></label>"; 
				condition += "<label>组织筛选:<input type=\"text\" placeholder=\"关键字\" id=\"qh_orgname\" /></label>";
				condition += "<label><select id=\"qh_orgid\" name=\"qh_orgid\" style=\"width:100px;\">";
				condition += "<option value=\"\">全部</option>";
				condition += "</select></label>";
				condition += "<label>状态:<select id=\"qh_flag\" name=\"qh_flag\" style=\"width:100px;\">";//第二种
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="Y">有效</option>';
				condition += '<option value="N">无效</option>';
				condition += "</select></label>";
				condition += " <label><input type=\"button\" id=\"btnSearch\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnempty\" value=\"重置\"></label> ";
				
				$("#docinfolist-table_filter").append(condition);
				//重新捆绑事件 
				DocInfo.rebindEvent();
				//查询按钮  
				$("#btnSearch").click(function(){
					var u = base + '/docinfolistjson.html?a=a';
					u += "&orgid=" + $('#qh_orgid option:selected').attr("value");
					u += "&name=" + $('#qh_name').val();
					u += "&idcard=" + $('#qh_idcard').val();
					u += "&flag=" + $('#qh_flag').val();
					oDocInfoTable.ajax.url(u).load();
				});
				
				$("#qh_orgname").change(function(){
					DocInfo.alterOrgData();
				});
				//重置
				$("#btnempty").click(function(){
					$("#qh_name").val("");
					$("#qh_orgname").val("");
					$("#qh_orgid").val("");
					$("#qh_idcard").val("");
					$("#qh_flag").val("");
					DocInfo.alterOrgData();
					var u = base + '/docinfolistjson.html';
					oDocInfoTable.ajax.url(u).load();
				});
				DocInfo.alterOrgData();
			}
		});
	},
	bindEvent:function(){
		$('#docinfolist-table').on('xhr.dt', function(e, settings, json, xhr) {
            setTimeout(DocInfo.rebindEvent, 300);
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
		$("[id^='editdocinfo']").unbind().click(function(){
			var code = $(this).attr("id").substring("editdocinfo".length);
			$.ajax({
				url: base + '/mgr_getdocinfojson.html?code=' + code,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					console.log(resp);
					DocInfo.editAdminUserDialog("修改adminUser", resp.data);
				}
			});
		});
		
		//上传图片
		$("[id^='uploadImage']").unbind().click(function(){
			var thiscode = $(this).attr("id").substring("uploadImage".length);
			console.log(thiscode);
			$.ajax({
				url: base + '/mgr_getdocinfojson.html?code=' + thiscode,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					DocInfo.uploadImageDialog("上传头像", resp.aadata,thiscode);
				}
			});
		});
	},
	//添加建档信息
	addDocInfoDialog:function(titleMsg){
		$("#addDocInfoDialog").removeClass('hide').dialog({
			height:400, 
			width:600,
			modal: true,
			title: "添加建档信息",
			title_html: true,
			buttons: [
						{
							html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
							"class" : "btn btn-info btn-xs",
							click: function() {
								var prsnname = $("#th_prsnname").val();
								var birthday = $("#th_birthday").val();
								var creatday = $("#th_creatday").val();
								var bloodvl = $('#th_bloodvl option:selected').attr("value");
								var contactnum= $("#th_contactnum").val();
								var familyaddr = $("#th_familyaddr").val();
								var houseaddr = $("#th_houseaddr").val();
								var idnumber = $("#th_idnumber").val();
								var mobilenum = $("#th_mobilenum").val();
								var nationvl = $('#th_nationvl option:selected').attr("value");
								var creatorid = $('#th_creatorid option:selected').attr("value");
								var orgid = $('#th_orgid option:selected').attr("value");
								var personnum = $("#th_personnum").val();
								var persionsex = $('#th_persionsex option:selected').attr("value");
								var workunit = $("#th_workunit").val();
								var reqData = {};
								reqData.prsnname = prsnname;
								reqData.birthday = birthday;
								reqData.creatday = creatday;
								reqData.bloodvl = bloodvl;
								reqData.contactnum = contactnum;
								reqData.familyaddr = familyaddr;
								reqData.houseaddr = houseaddr;
								reqData.idnumber = idnumber;
								reqData.mobilenum = mobilenum;
								reqData.nationvl = nationvl;
								reqData.creatorid = creatorid;
								reqData.orgid = orgid;
								reqData.personnum = personnum;
								reqData.persionsex = persionsex;
								reqData.workunit = workunit;
								$.ajax({
									url: base + '/addormodifydocinfojson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										console.log(resp);
										var u = base + '/docinfolistjson.html?a=a';
										oDocInfoTable.ajax.url(u).load();
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
	
	},//上传头像
	uploadImageDialog:function(title,data,thiscode){
		$("#th_uface").val(data.face);
		$("#imgfileform .icon-remove").click();
		$("#uploadImageDialog").removeClass('hide').dialog({
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
								var face = $("#th_uface").val();
								var reqData = {};
								reqData.face = face;
								reqData.code = thiscode;
								$.ajax({
									url: base + '/addormodifydocinfojson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										var u = base + '/docinfolistjson.html?a=a';
										oDocInfoTable.ajax.url(u).load();
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
	DocInfo.init();
});