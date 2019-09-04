var base = $("#base").attr("href");
var curOrgid = 0;
var curPrsnId = 0;
DocInfo = {
	init:function(){
		this.initTable();
		this.bindEvent();
	},
	initTagData:function(){
		    $.ajax({
                url: base + '/taglistjson.html',
                type: 'GET',
                dataType: 'json',
                success: function (resp) {
                	var tags=resp.tags;
                    if(resp.result == 1){
    					var html = "";
    					for(var i=0;i<resp.tags.length;i++){
    						var id = "tagid_"+resp.tags[i].id;
    						html += '	<li><div>';				
    						html += '&nbsp;<input type="checkbox" class="ace ace-checkbox-2" id="'+id+'" value="'+resp.tags[i].refValId + '"/>';
    						html += '<label class="lbl" for="ace-settings-navbar">'+resp.tags[i].refValName+'</label>';
    						html += '</div></li>';
    					}
    					$("#tag_ul").append(html);
    					DocInfo.rebindTagCheck();
    				}
                }
            });
	},
	rebindTagCheck:function(){
		$("[id^='tagid_']").click(function(){
			var tagIds = $("[id^='tagid_']");
			var tags = [];
			tagIds.each(function(index){
				if($(tagIds[index]).prop("checked") == true )
					tags.push($(tagIds[index]).attr("value"));
			});
			var u = base + '/docinfolistjson.html?a=a';
			u += "&name=" + $('#qh_name').val();
			u += "&idcard=" + $('#qh_idcard').val();
			u += "&flag=" + $('#qh_flag').val();
			u += "&tags="+ tags.join() ;
			oDocInfoTable.ajax.url(u).load();
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
							    "targets": 6,
							    "data": null,
							    "render": function(data, type, row) {
							    	if(data[6] == "DS_SEX_M"){
							    		return "男";
							    	}else if(data[6] == "DS_SEX_F"){
							    		return "女";
							    	}else if(data[6] == "DS_SEX_NOSAY"){
							    		return "未说明";
							    	}else if(data[6] == "DS_SEX_UNKNOWN"){
							    		return "未知";
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
							    "targets": 20,
							    "data": null,
							    "render": function(data, type, row) {
							    	if(data[20] == "DS_BLOOD_A"){
							    		return "A型";
							    	}else if(data[20] == "DS_BLOOD_B"){
							    		return "B型";
							    	}else if(data[20] == "DS_BLOOD_O"){
							    		return "O型";
							    	}else if(data[20] == "DS_BLOOD_AB"){
							    		return "AB型";
							    	}else
							    		return "";
							    }
							},{
							    "targets": 25,
							    "data": null,
							    "render": function(data, type, row) {
									return data[25].substring(5,10);
							    }
							},{
							    "targets": 26,
							    "data": null,
							    "render": function(data, type, row) {
									return data[26].substring(5,10);
							    }
							},{
							    "targets": 29,
							    "data": null,
							    "render": function(data, type, row) {
							    	var html = "";
									html += '<button class="btn btn-xs btn-info" id="jumpdocpage'+ data[0] + '"><i class="icon-list bigger-120">详情</i></button>';
									html += '<button class="btn btn-xs btn-info" id="addservass'+ data[1] + '"><i class="icon-edit bigger-120">签约</i></button>';
									html += '<div class="btn-group" style="padding:1px 2px;">';
									html += '	<button data-toggle="dropdown" class="btn dropdown-toggle btn-xs btn-info" style="height:28px;">';
									html += '		测量';
									html += '		<span class="icon-caret-down icon-on-right"></span>';
									html += '	</button>';
									html += '	<ul class="dropdown-menu dropdown-default">';
									html += '		<li>';
									html += '			<li class="exam" id="examblood'+ data[0] + '">血压测量</li>';
									html += '		</li>';
									html += '		<li>';
									html += '			<li class="exam" id="examsugar'+ data[0] + '">血糖测量</li>';
									html += '		</li>';
									html += '		<li>';
									html += '			<li class="exam" id="examurine'+ data[0] + '">尿液测量</li>';
									html += '		</li>';
									html += '	</ul>';
									html += '</div>';
									html += '<div class="btn-group" style="padding:1px 2px;">';
									html += '	<button data-toggle="dropdown" class="btn dropdown-toggle btn-xs btn-info" style="height:28px;">';
									html += '		添加';
									html += '		<span class="icon-caret-down icon-on-right"></span>';
									html += '	</button>';
									html += '	<ul class="dropdown-menu dropdown-default">';
									html += '		<li>';
									html += '			<li class="exam" id="modifydoctag'+ data[0] + '">标签</li>';
									html += '		</li>';
									html += '		<li>';
									html += '			<li class="exam" id="uploadImage'+ data[1] + '">头图</li>';
									html += '		</li>';
									html += '		<li>';
									html += '			<li class="exam" id="followuppage'+ data[1] + '"><a href="'+base+'/followuppage.html?prsnId='+data[0]+'" style="padding-left:0;">随访</a></li>';
									html += '		</li>';
									html += '		<li>';
									html += '			<li class="exam" id="examinationpage'+ data[1] + '"><a href="'+base+'/healthexampage.html?prsnId='+data[0]+'" style="padding-left:0;">体检</a></li>';
									html += '		</li>';
									html += '	</ul>';
									html += '</div>';
									if(data[16] == 0){
										html += '<button class="btn btn-xs btn-info" id="bindinguser'+ data[0] + '"><i class="icon-lock bigger-120">绑定</i></button>';
									}
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
			"aLengthMenu": [[15, 25, 50], ["15", "25", "50"]],
			"aoColumns": [
			          	{ "sData": "ID", "bSortable": false, "bVisible": true},
						{ "sName": "Code", "bSortable": false, "bVisible": false},
						{ "sName": "状态", "bSortable": false, "bVisible": true},
						{ "sName": "头图", "bSortable": false, "bVisible": true},
						{ "sName": "prsnCode", "bSortable": false, "bVisible": false},
						{ "sName": "姓名", "bSortable": false, "bVisible": true},
						{ "sName": "性别", "bSortable": false, "bVisible": true},
						{ "sName": "生日", "bSortable": false, "bVisible": true},
						{ "sName": "身份证号", "bSortable": false, "bVisible": true ,"width":"12%"},
						{ "sName": "手机号", "bSortable": false, "bVisible": false},
						{ "sName": "民族", "bSortable": false, "bVisible": true},
						{ "sName": "家庭住址", "bSortable": false, "bVisible": true},
						{ "sName": "家庭医生", "bSortable": false, "bVisible": true},
						{ "sName": "服务包", "bSortable": false, "bVisible": true},
						{ "sName": "建档日", "bSortable": false, "bVisible": true},
						{ "sName": "居民标签", "bSortable": false, "bVisible": true,"width":"10%"},
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
						{ "sName": "操作", "bSortable": false, "bVisible": true,"width":"12%"}
			],
			"fnInitComplete": function() {
				$("#docinfolist-table_filter label").detach();						
				var condition = "";
				condition = "<label>关键字：<input type=\"text\" placeholder=\"用户名或昵称\" id=\"qh_name\" /></label>"; //第一种
				condition += "<label>身份证：<input type=\"text\" placeholder=\"身份证\" id=\"qh_idcard\" /></label>";

				condition += '<div class="inline position-relative align-left">';
				condition += '<a href="#" class="btn-message btn btn-xs dropdown-toggle" data-toggle="dropdown">';
				condition += '	<span class="bigger-110" id="person_tags">居民标签</span>';
				condition += '	<i class="icon-caret-down icon-on-right"></i>';
				condition += '</a>';
				condition += '<ul class="dropdown-menu dropdown-lighter dropdown-caret dropdown-125" id="tag_ul">';
				condition += '	</ul>';
				condition += '</div>';
				
				condition += "<label>状态:<select id=\"qh_flag\" name=\"qh_flag\" style=\"width:100px;\">";//第二种
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="Y">有效</option>';
				condition += '<option value="N">无效</option>';
				condition += "</select></label>";
				condition += " <label><input type=\"button\" id=\"btnSearch\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnempty\" value=\"重置\"></label> ";
				$("#docinfolist-table_filter").append(condition);
				
				//初始化tag
				DocInfo.initTagData();
				
				//查询按钮  
				$("#btnSearch").click(function(){
					var sels = $("[id^='tag_']");
					var tags = [];
					//TODO 无法获取是否被选择
					sels.each(function(index){
						if($(sels[index]).prop("checked") == true ){
							tags.push($(sels[index]).attr("value"));
						}
					});
					var u = base + '/docinfolistjson.html?a=a';
					u += "&name=" + $('#qh_name').val();
					u += "&idcard=" + $('#qh_idcard').val();
					u += "&flag=" + $('#qh_flag').val();
					u += "&tags="+ tags ;
					oDocInfoTable.ajax.url(u).load(null,false);
				});
				
				//重置
				$("#btnempty").click(function(){
					$("#qh_name").val("");
					$("#qh_flag").val("");
					$("#qh_idcard").val("");
					var u = base + '/docinfolistjson.html';
					oDocInfoTable.ajax.url(u).load();
				});
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
		
		//级联显示医生列表
		$("#th_deptid").unbind().change(function(){
            var deptid = $('#th_deptid option:selected').attr("value");
            $.ajax({
                url: base + '/mgr_stafflistjson.html?deptid=' + deptid +'&orgid = '+ curOrgid,
                type: 'GET',
                dataType: 'json',
                success: function (resp) {
                    var data=resp.aaData;
                    var html = "";
                    html += "<option value=\"\">全部</option>";
                    for(var i=0;i<data.length;i++){
                        html += '<option value="' + data[i][0] + '">' + data[i][2]+ '</option>';
                    }
                    $("#th_stafid option").remove();
                    $("#th_stafid").append(html);
                }
            });
        });
		
		//绑定用户
		$("[id^='bindinguser']").unbind().click(function(){
			var prsnId = $(this).attr("id").substring("bindinguser".length);
			DocInfo.bindingUser(prsnId);
		});
		//详情页跳转
		$("[id^='jumpdocpage']").unbind().click(function(){
			var prsnId = $(this).attr("id").substring("jumpdocpage".length);
			window.location.href=base+"/docinfopage.html?docinfoid="+prsnId;
		});
		//血压测量
		$("[id^='examblood']").unbind().click(function(){
			var prsnId = $(this).attr("id").substring("examblood".length);
			DocInfo.addExamBloodDialog(prsnId);
		});
		//血糖测量
		$("[id^='examsugar']").unbind().click(function(){
			var prsnId = $(this).attr("id").substring("examsugar".length);
			DocInfo.addExamGlycemicDialog(prsnId);
		});
		//尿液检测
		$("[id^='examurine']").unbind().click(function(){
			var prsnId = $(this).attr("id").substring("examurine".length);
			DocInfo.addExamBloodDialog(prsnId);
		});
		
        //添加签约记录
		$("[id^='addservass']").unbind().click(function(){
			$("#th_srvid").val("");
			$("#th_deptid").val("");
			$("#th_stafid").val("");
			var code = $(this).attr("id").substring("addservass".length);
			var  reqData = {};
			reqData.code = code;
			$.ajax({
				url: base + '/mgr_getdocinfojson.html',
				data: reqData,
				type: 'POST',
				dataType: 'json',
				success: function (resp) {
					curOrgid = resp.data.orgid;
					curPrsnid = resp.data.prsnid;
					DocInfo.addServAssDialog(resp.data);
				}
			});
			
		});
		
		$("[id^='modifydoctag']").unbind().click(function(){
			var thisdocid = $(this).attr("id").substring("modifydoctag".length);
			$.ajax({
				url: base + '/mgr_getdoctagjson.html?docid=' + thisdocid,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
                	$("#doc_tag").html("");
                    var html = "<tr><td style='width:20px'>&nbsp;&nbsp;</td><td style='width:80px'>标签编号</td><td style='margin-left:15px'>角色名称</td></tr>";
                    for (var i = 0; i < resp.data.length; i++) {
                    	html += "<tr>";
                    	html += "<td><input type='checkbox' name='doctags" + resp.data[i].refValId + "' value=" + resp.data[i].refValId + " maxlength='30'";
                    	if("Y" == resp.data[i].checked)
                    		html += ' checked="checked"';
                    	html += '></td>';
                        html += "<td><strong>" + (i+1) + "</strong></td>";
                        html += "<td>" + resp.data[i].refValName + "</td>";
                        html += "</tr>";
                    }
                    $("#doc_tag").append(html);
                    $("#doc_tag").removeClass("hide").dialog({
                        modal: true,
                        title: '居民标签',
                        title_html: true,
                        buttons: [{
                            html: "<i id='save' class='icon-save bigger-110'></i>&nbsp; 保存",
                            "class": "btn btn-info btn-xs",
                            click: function() {
                               var doctags = $("input[name^='doctags']");
                               var tagidList = [];
                               var thatDialog = this;
                               doctags.each(function(index){
                            	   if($(this).prop("checked") == true){
                            		   tagidList.push($(this).attr("value"));
                            	   }
                               });
                               var reqData = {};
                               reqData.docid = thisdocid;
                               reqData.refvalids = tagidList.join();
                                $.ajax({
                                    url: base + '/mgr_defdoctagjson.html',
                                    data: reqData,
                                    type: 'POST',
                                    dataType: 'json',
                                    success: function(resp) {
                                    	alert(resp.message);
                                    	var u = base + '/docinfolistjson.html?a=a';
                                    	oDocInfoTable.ajax.url(u).load();
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
                      
				}
			});
		});
		
		//上传图片
		$("[id^='uploadImage']").unbind().click(function(){
			var thiscode = $(this).attr("id").substring("uploadImage".length);
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
	addServAssDialog:function(data){
		
		$("#servAssAddDialog").removeClass('hide').dialog({
			height:600, 
			width:600,
			modal: true,
			title: "添加签约记录",
			title_html: true,
			buttons: [
					{
						html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
						"class" : "btn btn-save btn-xs",
						click: function() {
							var srvId = $('#th_srvid option:selected').attr("value");
							var deptId = $('#th_deptid option:selected').attr("value");
							var stafId = $('#th_stafid option:selected').attr("value");
							var reqData = {};
							reqData.prsnId = curPrsnid;
							reqData.srvId = srvId;
							reqData.deptId = deptId;
							reqData.stafId = stafId;
							$.ajax({
								url: base + '/addormodifyservassjson.html',
								data: reqData,
								type: 'POST',
								dataType: 'json',
								success: function (resp) {
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
		},
		bindingUser:function(prsnId){
		  $("#th_phonenum_btn").click(function(){
				var phoneNum = $("#th_phonenum").val();
					$.ajax({
						url: base + '/mgr_userlistbyphonenumjson.html?mobileNum=' + phoneNum,
						type: 'POST',
						dataType: 'json',
						success: function (resp) {
							var html = "";
							for(var i=0;i<resp.aaData.length;i++){
								html += "<dl class='uData'>";
								html += '<dd><input type="radio" name = "uname" id="th_phonenum_check_'+resp.aaData[i][0]+'"></dd>';
								html += "<dd>"+resp.aaData[i][1]+"</dd>";
								html += "<dd>"+resp.aaData[i][2]+"</dd>";
								html += "</dl>";
							}
							$("#bindingUserDialog>dl:gt(1)").remove();
							$(html).insertAfter("#bindingUserDialog>dl:eq(1)");
						}
					});
				
			});
			$("#bindingUserDialog").removeClass('hide').dialog({
				height:400, 
				width:400,
				modal: true,
				title: "绑定用户",
				title_html: true,
				buttons: [
						{	
							html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
							"class" : "btn btn-info btn-xs",
							click: function() {
								
								var uname= document.getElementsByName('uname');
								var uid = 0;
								for (var i = 0; i < uname.length; i++) {

									if (uname[i].checked == true) {//如果选中，下面的alert就会弹出选中的值
										uid = uname[i].id.substring("th_phonenum_check_".length);
									}
								}
								var reqData = {};
								reqData.uid = uid;
								reqData.prsnId = prsnId;
								$.ajax({
									url: base + '/mgr_addUserprsnReljson.html',
									data: reqData,
									type: 'POST',
									dataType: 'json',
									success: function (resp) {
										alert(resp.message);
										$.ajax({
											url: base + '/mgr_addUserprsnReljson.html',
											data: reqData,
											type: 'POST',
											dataType: 'json',
											success: function (resp) {
												var u = base + '/docinfolistjson.html';
												oDocInfoTable.ajax.url(u).load();
											}
										});
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
		addExamBloodDialog:function(prsnId){
			$("#examBloodAddDialog").removeClass('hide').dialog({
				height:600, 
				width:600,
				modal: true,
				title: "添加血压测量记录",
				title_html: true,
				buttons: [
							{
								html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
								"class" : "btn btn-info btn-xs",
								click: function() {
									var shrinkPress = $("#th_shrinkpress").val();
									var diastolePress = $("#th_diastolepress").val();
									var heartRate = $("#th_heartrate").val();
									var prsnid = prsnId;
									var staffid = $('#th_staffid option:selected').attr("value");
									var reqData = {};
									reqData.shrinkPress = shrinkPress;
									reqData.diastolePress = diastolePress;
									reqData.heartRate = heartRate;
									reqData.prsnid = prsnid;
									reqData.staffid = staffid;
									$.ajax({
										url: base + '/mgr_addormodifyexambloodjson.html',
										data: reqData,
										type: 'POST',
										dataType: 'json',
										success: function (resp) {
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
		addExamGlycemicDialog:function(prsnId){
			$("#examGlycemicAddDialog").removeClass('hide').dialog({
				height:600, 
				width:600,
				modal: true,
				title: "添加血糖测量记录",
				title_html: true,
				buttons: [
							{
								html: "<i class='icon-save bigger-110'></i>&nbsp; 保存",
								"class" : "btn btn-info btn-xs",
								click: function() {
									var glycemictype = $('#th_glycemictype option:selected').attr("value");
									var glycemic = $("#th_glycemic").val();
									var prsnid = prsnId;
									var staffid = $('#th_staffid option:selected').attr("value");
									var reqData = {};
									reqData.glycemictype = glycemictype;
									reqData.glycemic = glycemic;
									reqData.prsnid = prsnid;
									reqData.staffid = staffid;
									$.ajax({
										url: base + '/mgr_addexamglycemicjson.html',
										data: reqData,
										type: 'POST',
										dataType: 'json',
										success: function (resp) {
											alert(resp.message);
										}
									});
									$(this).dialog("close");
								}
							},{
								htmsl: "<i class='icon-remove bigger-110'></i>&nbsp; 取消",
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