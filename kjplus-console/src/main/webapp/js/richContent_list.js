
var base = $("#base").attr("href");
var oRichContentTable = null;
var curOrgid = 0;
var curDeptid = 0;
var curCatgid = 0;

RichContent = {
	init:function(){
		this.initTable();
		this.initData();
		this.bindEvent();
	},
	initTable:function(){
		oRichContentTable = $('#richcontentlist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/mgr_richcontentlistjson.html",
			"columnDefs" :[
               {	
				    "targets": 5,
				    "data": null,
				    "render": function(data, type, row) {
				    		return '<img src="'+data[5]+'" style="width:40px;height:60px">';
				    	
				    }
				},{
				    "targets": 6,
				    "data": null,
				    "render": function(data, type, row) {
				    		return '<img src="'+data[6]+'" style="width:40px;height:60px">';
				    	
				    }
				},{
				    "targets": 7,
				    "data": null,
				    "render": function(data, type, row) {
				    		return '<img src="'+data[7]+'" style="width:40px;height:60px">';
				    	
				    }
				},{
				    "targets": 8,
				    "data": null,
				    "render": function(data, type, row) {
				    		return '<img src="'+data[8]+'" style="width:40px;height:60px">';
				    	
				    }
				},{
				    "targets": 9,
				    "data": null,
				    "render": function(data, type, row) {
				    		return '<img src="'+data[9]+'" style="width:40px;height:60px">';
				    	
				    }
				},{
				    "targets": 11,
				    "data": null,
				    "render": function(data, type, row) {
						return data[11].substring(5,10);
				    }
				}
				
			],
			"oLanguage": {
				"sLengthMenu": "每页显示 _MENU_ 条记录",
				"sZeroRecords": "抱歉， 没有找到",
				"sRichContent": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
				"sRichContentEmpty": "没有数据",
				"sRichContentFiltered": "(从 _MAX_ 条数据中检索)",
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
						{ "sName": "类型名称ID", "bSortable": false, "bVisible": true},
						{ "sName": "类型名称", "bSortable": false, "bVisible": true},
						{ "sName": "标题", "bSortable": false, "bVisible": true},
						{ "sName": "轮播图1", "bSortable": false, "bVisible": true},
						{ "sName": "轮播图2", "bSortable": false, "bVisible": true},
						{ "sName": "轮播图3", "bSortable": false, "bVisible": true},
						{ "sName": "轮播图4", "bSortable": false, "bVisible": true},
						{ "sName": "轮播图5", "bSortable": false, "bVisible": true},
						{ "sName": "内容", "bSortable": false, "bVisible": true},
						{ "sName": "创建时间", "bSortable": false, "bVisible": true},
						{ "sName": "组织ID", "bSortable": false, "bVisible": true}
			],
			"fnInitComplete": function() {
				$("#richcontentlist-table_filter label").detach();				
				var condition = "";
				condition = "<label>标题:<input type=\"text\" placeholder=\"关键字\" id=\"qh_title\" /></label>"; //第一种
				condition += "<label>类别筛选:</label>";
				condition += "<select id=\"qh_typeid\" name=\"qh_typeid\" style=\"width:100px;\">";
				condition += "<option value=\"\">全部</option>";
				condition += "</select>";
				condition += " <label><input type=\"button\" id=\"btnSearch\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnempty\" value=\"重置\"></label> ";
				
				$("#richcontentlist-table_filter").append(condition);
				RichContent.rebindEvent();

				//查询按钮  
				$("#btnSearch").click(function(){
					var u = base + '/mgr_richcontentlistjson.html?a=a';
					u += "&title="+ $("#qh_title").val();
					u += "&richType="+ $('#qh_typeid option:selected').attr("value");
					oRichContentTable.ajax.url(u).load();
				});
				//重置按钮
				$("#btnempty").click(function(){
					$("#qh_title").val("");
					$("#qh_typeid").val("");
					var u = base + '/mgr_richcontentlistjson.html';
					oRichContentTable.ajax.url(u).load();
				});
				//添加按钮
				$("#btnadd").click(function(){
					$("#th_title").val("");
					$("#th_deptid").val("");
					$("#th_staffid").val("");
					$("#th_content").val("");
					RichContent.addRichContentDialog("添加详情");
				});	
				RichContent.initData();
			}
		});
	},
	initData:function(){
		// 初始化类型列表
		$.ajax({
			url: base + '/refvaluelist.html?refid=62',
			type: 'GET',
			dataType: 'json',
			success: function (resp) {
				console.log(resp.aaData);
				var html = "";
				html += "<option value=\"\">全部</option>";
				for(var i=0;i<resp.aaData.length;i++){
					html += '<option value="' + resp.aaData[i][1] + '">' + resp.aaData[i][3] + '</option>';
				}
				$("#qh_typeid option").remove();
				$("#qh_typeid").append(html);
				RichContent.rebindEvent();
			}
		});
	},
	bindEvent:function(){
		$('#richcontentlist-table').on('xhr.dt', function (e,settings,json,xhr) {
			setTimeout(RichContent.rebindEvent,300);
		});
		
		$('#imgfile1').ace_file_input({
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
        						$("#th_uface1").val(resp.data.url);
        					}
        				}
        		};
        		$("#imgfileform1").ajaxSubmit(ajax_option);
				return true;
			}
		})
		
		$('#imgfile2').ace_file_input({
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
        						$("#th_uface2").val(resp.data.url);
        					}
        				}
        		};
        		$("#imgfileform2").ajaxSubmit(ajax_option);
				return true;
			}
		})
		
		$('#imgfile3').ace_file_input({
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
        						$("#th_uface3").val(resp.data.url);
        					}
        				}
        		};
        		$("#imgfileform3").ajaxSubmit(ajax_option);
				return true;
			}
		})
		
		$('#imgfile4').ace_file_input({
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
        						$("#th_uface4").val(resp.data.url);
        					}
        				}
        		};
        		$("#imgfileform4").ajaxSubmit(ajax_option);
				return true;
			}
		})
		
		$('#imgfile5').ace_file_input({
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
        						$("#th_uface5").val(resp.data.url);
        					}
        				}
        		};
        		$("#imgfileform5").ajaxSubmit(ajax_option);
				return true;
			}
		})
		$('#imgfile6').ace_file_input({
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
								$("#th_uface6").val(resp.data.url);
							}
						}
				};
				$("#imgfileform6").ajaxSubmit(ajax_option);
				return true;
			}
		})
		$('#imgfile7').ace_file_input({
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
								$("#th_uface7").val(resp.data.url);
							}
						}
				};
				$("#imgfileform7").ajaxSubmit(ajax_option);
				return true;
			}
		})
		$('#imgfile8').ace_file_input({
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
								$("#th_uface8").val(resp.data.url);
							}
						}
				};
				$("#imgfileform8").ajaxSubmit(ajax_option);
				return true;
			}
		})
		$('#imgfile9').ace_file_input({
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
								$("#th_uface9").val(resp.data.url);
							}
						}
				};
				$("#imgfileform9").ajaxSubmit(ajax_option);
				return true;
			}
		})
		$('#imgfile10').ace_file_input({
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
								$("#th_uface10").val(resp.data.url);
							}
						}
				};
				$("#imgfileform10").ajaxSubmit(ajax_option);
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
		//动态绑定医生列表
		 $("#th_deptid").unbind().change(function(){
            var deptid = $('#th_deptid option:selected').attr("value");
            console.log(deptid);
            $.ajax({
                url: base + '/mgr_stafflistjson.html?deptid=' + deptid +'&orgid = '+ curOrgid,
                type: 'GET',
                dataType: 'json',
                success: function (resp) {
                    var data=resp.aaData;
                    var html = "";
                    html += "<option value=\"\">全部</option>";
                    for(var i=0;i<data.length;i++){
                        html += '<option value="' + data[i][0] + '">' + data[i][3]+ '</option>';
                    }
                    $("#th_staffid option").remove();
                    $("#th_staffid").append(html);

                }
            });
	    });
		
		$("[id^='editRichContent']").unbind().click(function(){
			var thisRichContentcode = $(this).attr("id").substring("editRichContent".length);
			$.ajax({
				url: base + '/mgr_getRichContentjson.html?richContentcode=' + thisRichContentcode,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					RichContent.editRichContentDialog("修改详情", resp.data);
				}
			});
		});
		
	},
	editRichContentDialog:function(titleMsg,RichContentData){
		if(RichContentData != undefined){
			
			$("#th_title1").val(RichContentData.title);
			$("#imgfileform6 .icon-remove").click();
			$("#th_uface6").val("");
			$("#imgfileform7 .icon-remove").click();
			$("#th_uface7").val("");
			$("#imgfileform8 .icon-remove").click();
			$("#th_uface8").val("");
			$("#imgfileform9 .icon-remove").click();
			$("#th_uface9").val("");
			$("#imgfileform10 .icon-remove").click();
			$("#th_uface10").val("");
			$("#th_content1").val(RichContentData.content);
		}
		$("#richContentEditDialog").removeClass('hide').dialog({
			height:600, 
			width:1000,
			modal: true,
			//title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='icon-ok'></i>新增课程</h4></div>",
			title: titleMsg,
			title_html: true,
			buttons: [
						{
							html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
							"class" : "btn btn-info btn-xs",
							click: function() {
								var title = $("#th_title1").val();
								var showPic6 = $("#th_uface6").val();
								var showPic7 = $("#th_uface7").val();
								var showPic8 = $("#th_uface8").val();
								var showPic9 = $("#th_uface9").val();
								var showPic10 = $("#th_uface10").val();
								var richContent = $("#th_content1").val();
								var reqData = {};
								reqData.title = title;
								reqData.code = RichContentData.code;
								reqData.showPic1 = showPic6;
								reqData.showPic2 = showPic7;
								reqData.showPic3 = showPic8;
								reqData.showPic4 = showPic9;
								reqData.showPic5 = showPic10;
								reqData.richContent = richContent;
								$.ajax({
									url: base + '/mgr_addormodifyRichContentjson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										var u = base + '/mgr_richcontentlistjson.html?a=a';
										oRichContentTable.ajax.url(u).load();
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
	addRichContentDialog:function(titleMsg){
		$("#richContentAddDialog").removeClass('hide').dialog({
			height:600, 
			width:1000,
			modal: true,
			//title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='icon-ok'></i>新增课程</h4></div>",
			title: titleMsg,
			title_html: true,
			buttons: [
						{
							html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
							"class" : "btn btn-info btn-xs",
							click: function() {
								var deptId = $('#th_deptid option:selected').attr("value");
								var staffId = $('#th_staffid option:selected').attr("value");
								var mainId = null;
								var mainTypeCode = null;
								if(deptId != 0){
									if(staffId != 0){
										mainId = staffId;
										mainTypeCode = "RT_STAFF";
									}
									mainId = deptId;
									mainTypeCode = "RT_DEPT";
								}else{
									mainId = curOrgid;
									mainTypeCode = "RT_ORG";
								}
								console.log("1");
								console.log(mainId);
								console.log(mainTypeCode);
								var title = $("#th_title").val();
								var showPic1 = $("#th_uface1").val();
								var showPic2 = $("#th_uface2").val();
								var showPic3 = $("#th_uface3").val();
								var showPic4 = $("#th_uface4").val();
								var showPic5 = $("#th_uface5").val();
								var richContent = $("#th_content").val();
								var reqData = {};
								reqData.title = title;
								reqData.mainId = mainId;
								reqData.mainTypeCode = mainTypeCode;
								reqData.showPic1 = showPic1;
								reqData.showPic2 = showPic2;
								reqData.showPic3 = showPic3;
								reqData.showPic4 = showPic4;
								reqData.showPic5 = showPic5;
								reqData.richContent = richContent;
								console.log(reqData);
								$.ajax({
									url: base + '/mgr_addormodifyRichContentjson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										var u = base + '/mgr_richcontentlistjson.html?a=a';
										oRichContentTable.ajax.url(u).load();
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
	RichContent.init();	
});