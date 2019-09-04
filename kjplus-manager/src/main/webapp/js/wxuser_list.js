var base = $("#base").attr("href");
var oWxUserTable = null;

WxUser = {
	init:function(){
		this.initTable();
		//this.initData();
		this.bindEvent();
	},
	initTable:function(){
		oWxUserTable = $('#wxuserlist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/mgr_wxuserlistjson.html",
			"columnDefs" :[
			            {
						    "targets": 12,
						    "data": null,
						    "render": function(data, type, row) {
						    		return '<img src="'+data[12]+'" style="width:40px;height:60px">';
						    	
						    }
						},{
					    "targets": 17,
					    "data": null,
					    "render": function(data, type, row) {
							return data[17].substring(5,16);
					    }
						},{
						    "targets": 18,
						    "data": null,
						    "render": function(data, type, row) {
						    	var html = "";
						    	html += '<button class="btn btn-xs btn-info" id="uploadface'+ data[0] + '"><i class="icon-cogs bigger-120">上传头像</i></button>';
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
						{ "sName": "昵称", "bSortable": false, "bVisible": true},
						{ "sName": "手机号", "bSortable": false, "bVisible": true},
						{ "sName": "邮箱", "bSortable": false, "bVisible": true},
						{ "sName": "年龄", "bSortable": false, "bVisible": true},
						{ "sName": "性别", "bSortable": false, "bVisible": true},
						{ "sName": "地址", "bSortable": false, "bVisible": true},
						{ "sName": "区域", "bSortable": false, "bVisible": true},
						{ "sName": "城市", "bSortable": false, "bVisible": true},
						{ "sName": "省", "bSortable": false, "bVisible": true},
						{ "sName": "国家", "bSortable": false, "bVisible": true},
						{ "sName": "语言", "bSortable": false, "bVisible": true, "bVisible": false},
						{ "sName": "照片", "bSortable": false, "bVisible": true},
						{ "sName": "上级id", "bSortable": false, "bVisible": false},
						{ "sName": "上级昵称", "bSortable": false, "bVisible": false},
						{ "sName": "组织id", "bSortable": false, "bVisible": true, "bVisible": false},
						{ "sName": "组织", "bSortable": false, "bVisible": false},
						{ "sName": "创建时间", "bSortable": false, "bVisible": true},						
						{ "sName": "操作", "bSortable": false, "bVisible": true}						
				],
			"fnInitComplete": function() {
				$("#wxuserlist-table_filter label").detach();						
				var condition = "";
				condition += "<label>昵称：<input type=\"text\" placeholder=\"关键字\" id=\"qh_nickname\" /></label>";
				condition += "</select></label>";
				condition += " <label><input type=\"button\" id=\"btnSearch\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnempty\" value=\"重置\"></label> ";
				
				$("#wxuserlist-table_filter").append(condition);
				WxUser.rebindEvent();
				
				$("#btnSearch").click(function(){
					var u = base + '/mgr_wxuserlistjson.html?a=a';
					u += "&nickname=" + $('#qh_nickname').val();
					oWxUserTable.ajax.url(u).load();
				});
				
				$("#btnempty").click(function(){
					$("#qh_nickname").val("");
					var u = base + '/mgr_wxuserlistjson.html';
					oWxUserTable.ajax.url(u).load();
				});
			}
		});
	},
	bindEvent:function(){
		$('#wxuserlist-table').on('xhr.dt', function (e,settings,json,xhr) {
			setTimeout(WxUser.rebindEvent,300);
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
        						$("#th_wxuserface").val(resp.data.url);
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
		$("[id^='uploadface']").unbind().click(function(){
			var thisuid = $(this).attr("id").substring("uploadface".length);
			WxUser.editWxUserDialog(thisuid);
		});	
	},
	editWxUserDialog:function(thisuid){
		$.ajax({
			url: base + '/mgr_getwximgjson.html?uid=' + thisuid,
			type: 'POST',
			dataType: 'json',
			success: function (resp) {
				$("#imgfileform .icon-remove").click();
				$("#th_wxuserface").val(resp.data.face);
				$("#imgfileform .icon-remove").click();
				$("#uploadImageDialog").removeClass('hide').dialog({
					height:300, 
					width:300,
					modal: true,
					title: "上传微信用户头像",
					title_html: true,
					buttons: [
								{
									html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
									"class" : "btn btn-info btn-xs",
									click: function() {
										var thatdialog = this;
										var face = $("#th_wxuserface").val();
										var reqData = {};
										reqData.face = face;
										reqData.uid = thisuid;
										$.ajax({
											url: base + '/mgr_addwxuserinfofacelistjson.html',
											data: reqData,
											type: 'POST',
											dataType: 'json',
											success: function (resp) {
												var u = base + '/mgr_wxuserlistjson.html?a=a';
												oWxUserTable.ajax.url(u).load();
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
	}
	
};

$(function(){ 
	WxUser.init();
});