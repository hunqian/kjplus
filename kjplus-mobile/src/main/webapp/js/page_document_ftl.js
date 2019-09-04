var base = $("#base").attr("href");
var TBRefs = {};
var initData = {};

PageDoc = {
	init : function(prsnCode) {
		this.initData(prsnCode);
		this.bindEvent();
	},
	initData : function(prsnCode) {
		
		//获取该用户档案信息
		$.ajax({
			url: base + '/get_document_json.html?prsnCode='+prsnCode,
			type: 'POST',
			dataType: 'json',
			success: function (resp) {
				initData = resp.dataHash;
				console.log(initData);
				//数据显示
				$.ajax({
					url : base + '/tablecfgrefjson.html?code=R1001',
					type : 'GET',
					dataType : 'json',
					success : function(resp) {
						$("#save-docinfo-btn").attr("value","编辑");	
						console.log(resp.alldata);
						for (k in resp.alldata){
							//TODO 动态添加属性
							//将多选有输入独立出来
							var myid = null;
							if(resp.alldata[k].lineMultiRef == "S")//多选有输入
								myid = "s_dataline_"+ resp.alldata[k].lineCode;
							else//多选   单选  输入
								myid = "dataline_"+ resp.alldata[k].lineCode;
							//动态添加标签属性
							//$("#" + myid).attr("value",disabled);
							$("#" + myid).attr("data-req",resp.alldata[k].isReq);
							$("#" + myid).attr("data-title",resp.alldata[k].lineTitle);
							$("#" + myid).attr("data-reftypeid",resp.alldata[k].lineRefTypeId);
		 					$("#" + myid).attr("data-multiref",resp.alldata[k].lineMultiRef);
		 					if((k in initData) && resp.alldata[k].lineRefTypeId == 0){// 输入数据回显
		 						var inputVl =  initData[k].lines[0].inputVl;
		 						console.log(inputVl);
		 	 					if(inputVl != '' && inputVl !=null){
		 	 						$("#" + myid).attr("value",inputVl);
		 	 					}
		 					}
						}
						
						// 遍历有引用的列
						for (k in resp.data) {
							var html = "";
							for (var i = 0; i < resp.data[k].refVls.length; i++) {
								if (resp.data[k].lineMultiRef == "N"){
									html += '<div class="clearfix" data-id="'
										+ resp.data[k].lineCode
										+ '" data-req="'
										+ resp.data[k].isReq
										+ '" data-title="'
										+ resp.data[k].lineTitle
										+ '"  data-refvlid="'
										+ resp.data[k].refVls[i].refVlId
										+ '">'
										+ resp.data[k].refVls[i].refVlName
										+ '</div>';

								}
								else if (resp.data[k].lineMultiRef == "Y") {
									var refVlName = resp.data[k].refVls[i].refVlName;
									//TODO 判断参照名中是否含有“无”
									if (refVlName.indexOf("无") == 0) {
										html += ' <div class="clearfix id_select" refid="'+ resp.data[k].refVls[i].refVlId+ '" id="no"> ';
										html += '   		<i class="fl select"></i> ';
										html += '  			<span class="fl ">'+ resp.data[k].refVls[i].refVlName+ '</span>';
										html += '   </div>';
									} else {
										html += ' <div class="clearfix id_select" refid="'+ resp.data[k].refVls[i].refVlId+ '"> ';
										html += '   		<i class="fl"></i> ';
										html += '  			<span class="fl">'+ resp.data[k].refVls[i].refVlName+ '</span>';
										html += '   </div>';
									}
								}
							}
							var myid = "dataline_" + resp.data[k].lineCode;
							$("#" + myid).attr("data-req", resp.data[k].isReq);
							$("#" + myid).attr("data-title",resp.data[k].lineTitle);
							$("#" + myid).attr("data-reftypeid",resp.data[k].lineRefTypeId);
							$("#" + myid).attr("data-multiref",resp.data[k].lineMultiRef);

							//$('#cfgline_' + resp.data[k].lineCode+ "_dialog .alert_content").append(html);
							if(resp.data[k].lineMultiRef == "Y"){
								if(resp.data[k].lineInputVlType == null || resp.data[k].lineInputVlType == "")//多选无输入
									$('#cfgline_dialog_'+resp.data[k].lineCode+' .alert_content').append(html);
								else{//多选有输入
									$('#s_cfgline_dialog_'+resp.data[k].lineCode+' .alert_content').append(html);
									var myid = "s_dataline_" + resp.data[k].lineCode;
									$("#" + myid).attr("data-req", resp.data[k].isReq);
									$("#" + myid).attr("data-title",resp.data[k].lineTitle);
									$("#" + myid).attr("data-reftypeid",resp.data[k].lineRefTypeId);
									$("#" + myid).attr("data-multiref",resp.data[k].lineMultiRef);
								}
							}else if(resp.data[k].lineMultiRef == "N"){//单选
								$('#cfgline_' + resp.data[k].lineCode+ "_dialog .alert_content").append(html);
								if(k in initData ){ //判断单选是否有数据  单选数据回显
									//console.log(initData[k]);
			 						refid =  initData[k].lines[0].refId;
			 						//获取参照内容
			 						var text = $("div[data-refvlid="+refid+"]").text();
			 						//单选数据回显
			 						$("#dataline_"+resp.data[k].lineCode).text(text);
			 						//样式定义
			 						$("#dataline_"+resp.data[k].lineCode).attr("style","color: rgb(85, 85, 85);");
								}
							}
							
						}
						PageDoc.rebindEvent();
					}
				});
				
			}
		});
		
	},
	bindEvent : function() {
		// 基本信息、详细信息tab切换
		$('.id_base').click(function() {
			$('.id_base').css({
				'color' : '#0bd16b',
				'border-bottom' : '0.125rem solid #0bd16b'
			});
			$('.id_detailed').css({
				'color' : '#333',
				'border' : 'none'
			});
			$('.id_base_data').show();
			$('.id_detailed_data').hide();
		});
		$('.id_detailed').click(function() {
			$('.id_detailed').css({
				'color' : '#0bd16b',
				'border-bottom' : '0.125rem solid #0bd16b'
			});
			$('.id_base').css({
				'color' : '#333',
				'border' : 'none'
			});
			$('.id_detailed_data').show();
			$('.id_base_data').hide();
		});

		// TODO 各种输入参数判断
		// 手机号输入判断
		$('#dataline_3_1').bind('input propertychange', function() {
			console.log("手机号输入");
			if ($(this).val().length > 11) {
				$(this).val($(this).val().substring(0, 11));
			}
		});

		// 生日日期选择    TODO 编辑与保存状态判断
 		previousDate("dataline_1_2");
		
		// 身份证号输入正确性判断
		$('#dataline_2_1').bind('input propertychange', function() {
			// var re = /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
			if ($(this).val().length === 0) {
				val = '';
			}
			if ($(this).val().length === 1) {
				var result = /[0-9]/.exec($(this).val());
				if (result !== null) {
					$(this).val(result.input);
					val = result.input;
				} else {
					$(this).val(val);
				}
			}
			if ($(this).val().length > 1 && $(this).val().length < 18) {
				var result1 = /[0-9]$/.exec($(this).val());
				if (result1 !== null) {
					$(this).val(result1.input);
					val = result1.input;
				} else {
					$(this).val(val);
				}
			}
			if ($(this).val().length === 18) {
				var result2 = /([0-9]|X|x)$/.exec($(this).val());
				if (result2 !== null) {
					$(this).val(result2.input);
					val = result2.input;
				} else {
					$(this).val(val);
				}
			}
		});
		
		// 关闭弹框按钮汇总 TODO 后期总结通用
		// 关闭消息提示
		$('.alert_message .alert_box button').click(function() {
			$('.alert_message').hide();
		});
		// 关闭性别选择
		$('.alert .off').click(function() {
			$('.alert').hide();
		});
		// 既往病史
		$('.alert_1 .off').click(function() {
			$('.alert_1').hide();
		});
		// 残疾情况
		$('.alert_11 .off').click(function() {
			$('.alert_11').hide();
		});
		// 暴露史
		$('.alert_2 .off').click(function() {
			$('.alert_2').hide();
		});
		
		//页面切换
		//健康信息
		$('.id_health_tab').click(function() {
			$('.health_box').show();
		});
	    $('.id_health_finish').click(function () {
	        $('.health_box').hide();
	    });
		//行为信息
		$('.id_live_tab').click(function() {
			$('.live_box').show();
		});
	    $('.id_live_finish').click(function () {
	        $('.live_box').hide();
	    });
	    // 其他信息
		$('.id_other_tab').click(function() {
			$('.other_box').show();
		});
	    $('.id_other_finish').click(function () {
	        $('.other_box').hide();
	    });
	    
	    //编辑状态与保存状态的切换
		$("#edit-docinfo-btn").click(function() {
			
			$(this).html("保存");
			$(this).attr("id","save-docinfo-btn");
			document.getElementById("dataline_0").readOnly = false;
			
			//$("#dataline_0").removeAttribute("readonly");
			//document.getElementById(id).removeAttribute("readonly");
			
			// 必须输入的信息验证
			/*PageDoc.saveOrModifyDocInfo();*/
		});

	    
	},
	rebindEvent : function() {
		/*
		 * 单选 处理 
		 * */
		// 通用   点击显示弹框
		$("[id^='dataline_']").click(function() {
			//用于编辑状态与保存状态的切换
			var status = $('.id_save').html(); 
			if(status != "编辑"){
				var thisid = $(this).attr("id");
				thisid = thisid.substring("data".length);
				$('#cfg' + thisid + '_dialog').show();	
			}
		});

		$('[id^="cfgline_"] .alert_content div').click(function() {
			var myid = $(this).attr("data-id");
			var myrefvlid = $(this).attr("data-refvlid");
			myid = "line_" + myid;
			$('#cfg' + myid + '_dialog').hide();
			// 赋值 保存时提取数据 
			$("#data" + myid).attr("data-refvlid", myrefvlid);
			//var html = $(this).html() + '<i class="iconfont">&#xe687;</i>';
			//数据回想
			var html = $(this).html();
			$("#data" + myid).html(html).css('color', '#555');
		});
		$('[id^="cfgline_"] .off').click(function() {
			$(this).parents().find("[id^='cfgline_']").last().hide();
		});

		// TODO 尽量使用通用方法
		/*
		 * 多选   有输入 处理 
		 * */
		// 添加疾病史弹框
		$('.id_add').click(function() {
			$('.alert_1').show();
		});
		// 选择疾病史的点击事件
		$('.alert_1 .alert_content div').click(function() {
			if ($(this).attr('class') !== 'other_input clearfix') {
				if ($(this).attr('id') === 'no') {
					$('.alert_1 .alert_content div i').removeClass('select');
					$(this).find('i').toggleClass('select');
				} else {
					$(this).find('i').toggleClass('select');
					$('#no i').removeClass('select');
				}
			}
		});
		// 添加疾病史的点击事件 (多选有输入)
		$('.id_add_disease').click(function() {
							$('.alert_1').hide();
							if ($('.disease_box').length) {
								$('.disease_box').remove();
							}
							var selectDiv = $('.alert_1 .alert_content div');
							var number = 0;
							var refids = [];
							var html = '<div class="disease_box id_disease_box">';
							for (var i = 0; i < selectDiv.length; i++) {
								if (selectDiv.eq(i).find('i').attr('class') === 'fl select') {
									// 数组形式 一个一个分别添加
									refids.push(selectDiv.eq(i).attr('refid'));

									// TODO 输入日期保存
									var inputId = selectDiv.eq(i).attr('refid')
											+ 'Date';
									++number;
									html += '<div>';
									html += '<p>'
											+ number
											+ '、'
											+ selectDiv.eq(i).find('span')
													.html() + '</p>';
									html += '<input placeholder="请输入疾病史日期" readonly="readonly" id="'
											+ inputId + '" type="text">';
									html += '</div>';
									previousDate(inputId);
								}
							}
							// TODO 选择其他疾病后需要输入
							/*
							 * if ($('#otherDisease i').attr('class') === 'fl
							 * select') { for (var i = 0; i < $('.alert_1
							 * .other_input').length; i++) { if ($('.alert_1
							 * .other_input').eq(i).find( 'input').val() !== '') {
							 * var otherId = $('.alert_1 .other_input')
							 * .eq(i).find('input').attr('id') + 'Date';
							 * ++number; html += '<div>'; html += '<p>' +
							 * number + '、' + $('.alert_1 .other_input')
							 * .eq(i).find('input') .val() + '</p>'; html += '<input
							 * placeholder="请输入疾病史日期" readonly="readonly" id="' +
							 * otherId + '" type="text">'; html += '</div>';
							 * previousDate(otherId); } else { $('.alert_2
							 * p').html('请输入其他疾病史'); $('.alert_2').show();
							 * $('.alert_1').show(); } } }
							 */
							html += '</div>';
							if (html !== '<div class="disease_box"></div>') {
								
								$('.disease_history').append(html);
								for (var i = 0, l = $('.disease_box input').length; i < l; i++) {
									previousDate($('.disease_box input').eq(i)
											.attr('id'));
								}
							}
							// TODO 变通用
							$("#dataline_12_1").attr("data-refvlid", refids);
						});
		
		/*
		 * 多选处理
		 * */
		// 残疾情况页面弹窗显示
		$("[id^='dataline_']").click(function() {
			var thisid = $(this).attr("id");
			thisid = thisid.substring("dataline_".length);
			$('#cfgline_dialog_' + thisid).show();
		});

		// 选择无残疾情况时的的页面显示
		$('[id^="cfgline_dialog_"] .alert_content div').click(function() {
			if ($(this).attr('class') !== 'other_input clearfix') {
				if ($(this).attr('id') === 'no') {
					$('.alert_11 .alert_content div i').removeClass('select');
					$(this).find('i').toggleClass('select');
				} else {
					$(this).find('i').toggleClass('select');
					$('#no i').removeClass('select');
				}
			}
		});

		// 添加残疾情况的点击事件 (多选)
		$("[id^='cfgline_dialog_add_']").click(function() {
							var thisid = $(this).attr("id");
							thisid = thisid.substring("cfgline_dialog_add_".length);
							$('#cfgline_dialog_'+thisid).hide();
							if ($('#box_'+thisid).length) {
								  $('#box_'+thisid).remove();
							}
							
							var selectDiv = $('#cfgline_dialog_'+thisid+' .alert_content div');
							var number = 0;
							var refids = [];
							var html = '<div class="physical_box id_physical_box" id="box_'+thisid+'">';
							
							for (var i = 0; i < selectDiv.length; i++) {
								if (selectDiv.eq(i).find('i').attr('class') === 'fl select') {

									// TODO 数组形式 一个一个分别添加
									refids.push(selectDiv.eq(i).attr('refid'));

									++number;
									html += '<div>';
									html += '<p>'
											+ number
											+ '、'
											+ selectDiv.eq(i).find('span')
													.html() + '</p>';
									/*
									 * html += '<input placeholder="请输入疾病史日期"
									 * readonly="readonly" id="' + inputId + '"
									 * type="text">';
									 */
									html += '</div>';
								}
							}
							html += '</div>';
							if (html !== '<div class="physical_box" id="box_'+thisid+'"></div>') {
								$('#append_'+thisid).append(html);
								for (var i = 0, l = $('#box_'+thisid+' input').length; i < l; i++) {
									previousDate($('#box_'+thisid+' input').eq(i).attr('id'));
								}
							}
							// TODO 变通用
							$("#dataline_"+thisid).attr("data-refvlid", refids);
						});
		
			

	},

	saveOrModifyDocInfo : function() {
		var reqDatas = [];
		var cfgs = $("[id^='dataline_']");
		for (var index = 0; index < cfgs.length; index++) {
			var isreq = "";
			var vl = "";
			var title = "";
			var id = "";
			var code = "";
			var reftypeid = "";
			var refid = "";
			var multiref = "";

			id = $(cfgs[index]).attr("id");

			var data = document.getElementById(id);// 遍历dataset
			isreq = data.dataset.req;// 是否必须
			title = data.dataset.title;// 用于提示(不传递)
			reftypeid = data.dataset.reftypeid;// 判断 是否引用(不传递)
			refid = data.dataset.refvlid;// 数组或单个
			multiref = data.dataset.multiref;// 判断单选多选
			code = id.substring("dataline_".length);// 列号
			vl = $(cfgs[index]).val();// 输入值
			console.log(vl);
			if (parseInt(reftypeid) == 0) {
				if (isreq == "Y" && vl == "") {
					$('.alert_message').show();
					$('.alert_message p').html("请输入" + title + "");
					return;
					// alert("请输入'" + title + "'");
					// return;
				}
			} else {
				if (isreq == "Y"
						&& (refid == "" || refid == null || refid.length == 0)) {
					$('.alert_message').show();
					$('.alert_message p').html("请选择" + title + "");
					// alert("请选择'" + title + "'");
					return;
				}
			}
			if (multiref == "N") {// 单选或者输入
				var rq = {};
				rq.code = code;
				rq.refid = refid;
				rq.vl = vl;
				if (!((refid == "" || refid == null) && (vl == "" || vl == null)))// 若全部为空，就不进行存储
					reqDatas.push(rq);
			} else if (multiref == "Y") {// 多选 refid为 "219,220,221"
				var arr = [];
				if (refid != null && refid != "")
					arr = refid.split(',');
				for (var i = 0; i < arr.length; i++) {
					var rq = {};
					rq.code = code;
					// TODO 输入值暂定。
					rq.vl = vl;
					rq.refid = arr[i];
					reqDatas.push(rq);
				}
			}
		}
		var reqData = {};
		reqData.docinfojson = JSON.stringify(reqDatas);
		// docinfojson:"[{"code":"0","vl":"请问请问"},{"code":"1_1","refid":"161","vl":""}
		// ,{"code":"12_1","vl":"","refid":"218"},{"code":"12_1","vl":"","refid":"219"},{"code":"12_1","vl":"","refid":"220"},{"code":"12_1","vl":"","refid":"221"}]"
		reqData.docinfoid = $("#docinfoid").val();
		$.ajax({
			url : base + '/saveormodifydocinfojson.html',
			data : reqData,
			type : 'POST',
			dataType : 'json',
			success : function(resp) {
				alert(resp.message);
			}
		});

	}
};

function previousDate(id) {
		var id = '#' + id;
		var date = new Date();
		var y = date.getFullYear();
		var m = date.getMonth();
		var d = date.getDate();
		var opt = {};
		opt.date = {
			preset : 'date'
		};
		opt.datetime = {
			preset : 'datetime'
		};
		opt.time = {
			preset : 'time'
		};
		opt.defaultdate = {
			theme : 'android-ics light',
			display : 'modal',
			mode : 'scroller',
			dateFormat : 'yyyy-mm-dd',
			lang : 'zh',
			showNow : true,
			nowText : "今天",
			startYear : y - 150,
			endYear : y,
			endMonth : m,
			endDay : d
		};
		$(id).mobiscroll($.extend(opt['date'], opt['defaultdate']));
}

/*$(function() {
	PageDoc.init();
});*/
