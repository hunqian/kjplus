var base = $("#base").attr("href");
var oInfoTable = null;
var curOrgid = 0;
var curDeptid = 0;
var curCatgid = 0;

Info = {
	init:function(){
		this.initTable();
		this.initData();
		this.bindEvent();
	},
	initTable:function(){
		oInfoTable = $('#infolist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/mgr_infolistjson.html",
			"columnDefs" :[
				{
				    "targets": 10,
				    "data": null,
				    "render": function(data, type, row) {
				    	if(data[10] == "Y")
				    		return "有效";
				    	else
				    		return "无效";
				    }
				},{
				    "targets": 14,
				    "data": null,
				    "render": function(data, type, row) {
				    		return '<img src="'+data[14]+'" style="width:40px;height:60px">';
				    	
				    }
				},{
				    "targets": 16,
				    "data": null,
				    "render": function(data, type, row) {
			    		return data[16].substring(5,16);
				    }
				},{
				    "targets": 4,
				    "data": null,
				    "render": function(data, type, row) {
				    	console.log(data);
				    	if(data[4] == "A")
				    		return "活动";
				    	else
				    		return "资讯";
				    }
				},{
				    "targets": 17,
				    "data": null,
				    "render": function(data, type, row) {
				    	var html = "";
						html += '<button class="btn btn-xs btn-info" id="editinfo'+ data[1] + '"><i class="icon-cogs bigger-120">改</i></button>';
						html += '<button class="btn btn-xs btn-info" id="editcontent'+ data[0] + '"><i class="icon-cogs bigger-120">内容</i></button>';
						html += '<button class="btn btn-xs btn-info" id="infodesc'+ data[1] + '"><i class="icon-cogs bigger-120">简介</i></button>';
				    	if(data[10] == "Y"){
				    		html += '<button class="btn btn-xs btn-info" id="infodown'+ data[1] + '"><i class="icon-cogs bigger-120">下线</i></button>';
				    	}else{
				    		html += '<button class="btn btn-xs btn-info" id="infoup'+ data[1] + '"><i class="icon-cogs bigger-120">上线</i></button>';
				    		
				    	};
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
						{ "sName": "标题", "bSortable": false, "bVisible": true,"width":"20%"},
						{ "sName": "简介", "bSortable": false, "bVisible": false},
						{ "sName": "类别", "bSortable": false, "bVisible": true},
						{ "sName": "类型ID", "bSortable": false, "bVisible": false},
						{ "sName": "类型名称", "bSortable": false, "bVisible": true},
						{ "sName": "总查看数", "bSortable": false, "bVisible": true},
						{ "sName": "总赞数", "bSortable": false, "bVisible": true},
						{ "sName": "总关注数", "bSortable": false, "bVisible": true},
						{ "sName": "状态", "bSortable": false, "bVisible": true},
						{ "sName": "发布人", "bSortable": false, "bVisible": false},
						{ "sName": "对应部门", "bSortable": false, "bVisible": true},
						{ "sName": "组织名称", "bSortable": false, "bVisible": false},
						{ "sName": "头图", "bSortable": false, "bVisible": true},
						{ "sName": "内容", "bSortable": false, "bVisible": false},
						{ "sName": "发布时间", "bSortable": false, "bVisible": true},
						{ "sName": "操作", "bSortable": false, "bVisible": true}
						
			],
			"fnInitComplete": function() {
				$("#infolist-table_filter label").detach();				
				var condition = "";
				condition = "<label>标题:<input type=\"text\" placeholder=\"关键字\" id=\"info_keyWord\" /></label>"; //第一种
				condition += "<label>组织:<select id=\"qh_orgid\" name=\"qh_orgid\" style=\"width:120px;\">";
				condition += "</select></label>";
				condition += "<label>查询时间:<input type=\"text\" id=\"qh_queryday\" style=\"width:80px;\"/>";
				condition += "<i class=\"icon-calendar\"></i>";
				condition += "</label>";
				condition += "<label>类别:<select id=\"info_Type\" name=\"info_Type\" style=\"width:100px;\">";//第二种
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="I">资讯</option>';
				condition += '<option value="A">活动</option>';
				condition += "</select></label>";
				condition += "<label>资讯类型:<select id=\"qh_cfgid\" name=\"qh_cfgid\" style=\"width:120px;\">";
				condition += "</select></label>";
				condition += "<label>状态:<select id=\"info_status\" name=\"info_status\" style=\"width:100px;\">";//第二种
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="Y">有效</option>';
				condition += '<option value="N">无效</option>';
				condition += "</select></label>";
				condition += " <label><input type=\"button\" id=\"btnSearch\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnempty\" value=\"重置\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnadd\" value=\"添加\"></label> ";
				
				$("#infolist-table_filter").append(condition);
				Info.rebindEvent();
				
				$( "#qh_queryday" ).datepicker({
					format: 'yyyy/mm/dd',
					weekdaysLetter: ['日', '一', '二', '三', '四', '五', '六'],
					today: '今天',
					clear: '清除',
					close: '关闭',
					monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
					monthsShort: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
					dayNamesMin: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
					dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六']
				});
				
				//查询按钮  
				$("#btnSearch").click(function(){
					var u = base + '/mgr_infolistjson.html?a=a';
					u += "&title="+ $("#info_keyWord").val();
					u += "&infoType="+ $("#info_Type").val();
					u += "&status=" + $("#info_status").val();
					u += "&queryday=" + $("#qh_queryday").val();
					u += "&orgId="+ $('#qh_orgid option:selected').attr("value");
					u += "&cfgId="+ $('#qh_cfgid option:selected').attr("value");
					oInfoTable.ajax.url(u).load();
				});
				//重置按钮
				$("#btnempty").click(function(){
					$("#info_keyWord").val("");
					$("#info_status").val("");
					$("#info_Type").val("");
					$("#qh_orgid").val("");
					$("#qh_queryday").val("");
					$("#qh_cfgid").val("");
					var u = base + '/mgr_infolistjson.html';
					oInfoTable.ajax.url(u).load();
				});
				//添加按钮
				$("#btnadd").click(function(){
					$("#th_title").val("");
					$("#th_catgid").val("");
					$("#th_pubid").val("");
					$("#th_infotype").val("");
					$("#th_deptid").val("");
					$("#th_orgid").val("");
					Info.addInfoDialog("增加资讯");
				});
				Info.initData();
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
				Info.rebindEvent();
			}
		});
		
		$.ajax({
			url: base + '/infocataloglistjson.html',
			type: 'GET',
			dataType: 'json',
			success: function (resp) {
				console.log(resp);
				var html = "";
				html += "<option value=\"\">全部</option>";
				for(var i=0;i<resp.aaData.length;i++){
					html += '<option value="' + resp.aaData[i][0] + '">' + resp.aaData[i][2] + '</option>';
				}
				$("#qh_cfgid option").remove();
				$("#qh_cfgid").append(html);
				Info.rebindEvent();
			}
		});
	},
	bindEvent:function(){
		
		$('#infolist-table').on('xhr.dt', function (e,settings,json,xhr) {
			setTimeout(Info.rebindEvent,300);
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
		$("[id^='infodesc']").unbind().click(function(){
			var thisinfocode = $(this).attr("id").substring("infodesc".length);
			$.ajax({
				url: base + '/mgr_getinfojson.html?infocode=' + thisinfocode,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					console.log(resp.data);
					bootbox.alert("资讯简介："+resp.data.desc, function() {});
				}
			});
			
		});
		
		$("[id^='editinfo']").unbind().click(function(){
			var thisinfocode = $(this).attr("id").substring("editinfo".length);
			$.ajax({
				url: base + '/mgr_getinfojson.html?infocode=' + thisinfocode,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					Info.editInfoDialog("修改资讯", resp.data);
				}
			});
		});
		
		$("[id^='infodown']").unbind().click(function(){
			var id = $(this).attr('id').substring("infodown".length);
			console.log(id);
			bootbox.confirm("是否下线该条资讯？",function(re){
				if(re) {
					$.ajax({
						url: base + '/downinfojson.html?infocode=' + id,
						type: 'GET',
						dataType: 'json',
						success: function (resp) {
							if(resp.result == 1){
								alert(resp.message);
								var u = base + '/mgr_infolistjson.html?a=a';
								oInfoTable.ajax.url(u).load();
							}
						}
					});
				  }
				 });
			
		});
		$("[id^='infoup']").unbind().click(function(){
			var id = $(this).attr('id').substring("infoup".length);
			bootbox.confirm("是否上线该条资讯？",function(re){
				if(re) {
					$.ajax({
						url: base + '/downinfojson.html?infocode=' + id,
						type: 'GET',
						dataType: 'json',
						success: function (resp) {
							
								alert(resp.message);
								var u = base + '/mgr_infolistjson.html?a=a';
								oInfoTable.ajax.url(u).load();
							
						}
					});
				}
			});
			
		});
		
		
		
		$("#th_orgid").unbind().change(function(){ 
			var orgId = $("#th_orgid").val();
			$("#th_deptid option").remove();
			$.ajax({
				url: base + '/mgr_deptlistjson.html?orgId=' + orgId,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					var aaData=resp.aaData;
					var html = "";
					html += "<option value=\"\">全部</option>";
					for(var i=0;i<aaData.length;i++){
						html += '<option value="' + aaData[i][0] + '">' + aaData[i][3]+ '</option>';
					}
					$("#th_deptid option").remove();
					$("#th_deptid").append(html);
					$('#th_deptid option[value="' + curDeptid + '"]').attr("selected", "selected");
					curDeptid = 0;
				}
			});
			
			$.ajax({
				url: base + '/infocataloglistjson.html?orgid=' + orgId,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					var aaData=resp.aaData;
					var html = "";
					html += "<option value=\"\">全部</option>";
					for(var i=0;i<aaData.length;i++){
						html += '<option value="' + aaData[i][0] + '">' + aaData[i][2]+ '</option>';
					}
					$("#th_catgid option").remove();
					$("#th_catgid").append(html);
					$('#th_catgid option[value="' + curCatgid + '"]').attr("selected", "selected");
					curCatgid = 0;
				}
			});
		});
		
		
		$("[id^='editcontent']").unbind().click(function(){
			var thisinfoid = $(this).attr("id").substring("editcontent".length);
			$.ajax({
				url: base + '/mgr_getinfocontentjson.html?infoid=' + thisinfoid,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
				$('#editor_content').html(resp.content);
				$( "#infoContentDialog" ).removeClass('hide').dialog({
					height:620, 
					width:820,
					autoOpen: true,
					resizable: false,
					modal: true,
					title: "<div class='widget-header'><h4 class='smaller'><i class='icon-save-sign red'></i>修改资讯</h4></div>",
					title_html: true,
					buttons: [
						{
							html: "<i class='icon-save bigger-110'></i>&nbsp; 确定",
							"class" : "btn btn-info btn-xs",
							click: function() {
								var thatdialog = this;
								var reqData = {};
								reqData.infoid = thisinfoid;
								reqData.content = $('#editor_content').html();
								$.ajax({
									url: base + '/mgr_addinfocontentjson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
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
		});
		
	},
	addInfoDialog:function(titleMsg){
		$("#imgfileform .icon-remove").click();
		$("#infoAddDialog").removeClass('hide').dialog({
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
								var title = $("#th_title").val();
								var desc = $("#th_desc").val();
								var infoType = $("#th_infotype").val();
								var catgid = $('#th_catgid option:selected').attr("value");
								var deptid = $('#th_deptid option:selected').attr("value");
								var orgid = $('#th_orgid option:selected').attr("value");
								var iconImgUrl = $("#th_uface").val();
								var reqData = {};
								reqData.title = title;
								reqData.desc = desc;
								reqData.pubid = pubid;
								reqData.catgid = catgid;
								reqData.deptid = deptid;
								reqData.iconImgUrl = iconImgUrl;
								reqData.orgid = orgid;
								reqData.infoType = infoType;
								
								$.ajax({
									url: base + '/addormodifyinfojson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										var u = base + '/mgr_infolistjson.html?a=a';
										u += "&prvnid=" + $('#qh_prvnid option:selected').attr("value");
										u += "&cityid=" + $('#qh_cityid option:selected').attr("value");
										oInfoTable.ajax.url(u).load();
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
	editInfoDialog:function(titleMsg,infoData){
		if(infoData != undefined){
			$("#th_title").val(infoData.title);
			$("#th_desc").val(infoData.desc);
			$("#th_uface").val(infoData.iconImgUrl);
			$("#imgfileform .icon-remove").click();
			$('#th_infotype option[value="'+infoData.infoType+'"]').attr("selected","selected");
			$('#th_orgid option[value="'+infoData.orgid+'"]').attr("selected","selected");
			//$('#th_catgid option[value="'+infoData.catg+'"]').attr("selected","selected");
			curCatgid = infoData.catgid;
			curDeptid = infoData.deptid;
			$("#th_orgid").trigger("change",infoData.orgid);
		}

		$("#infoAddDialog").removeClass('hide').dialog({
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
								var title = $("#th_title").val();
								var desc = $("#th_desc").val();
								var infoType = $("#th_infotype").val();
								var catgid = $('#th_catgid option:selected').attr("value");
								var deptid = $('#th_deptid option:selected').attr("value");
								var orgid = $('#th_orgid option:selected').attr("value");
								var iconImgUrl = $("#th_uface").val();
								var reqData = {};
								reqData.title = title;
								reqData.code = infoData.code;
								reqData.desc = desc;
								reqData.catgid = catgid;
								reqData.iconImgUrl = iconImgUrl;
								reqData.deptid = deptid;
								reqData.orgid = orgid;
								reqData.infoType = infoType;
								$.ajax({
									url: base + '/addormodifyinfojson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										var u = base + '/mgr_infolistjson.html?a=a';
										oInfoTable.ajax.url(u).load();
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
	Info.init();	
});