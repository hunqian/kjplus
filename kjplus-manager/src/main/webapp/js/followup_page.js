var base = $("#base").attr("href");
var initDate = 0;
var tableCode = $("#tableCode").attr("value");
var followupid = $("#followupid").attr("value");
var prsnId = $("#prsnId").attr("value");

Followup = {
	init:function(){
		this.initData();
		this.bindEvent();
		
	},
	bindEvent:function(){
		$("#save-followup-btn").click(function(){
			Followup.saveOrModifyDocInfo();
		});
		$("#back-followup-btn").click(function(){
			var prsnId = $("#prsnId").attr("value");
			window.location.href=base+"/docinfopage.html?activepage=followup"+"&docinfoid="+prsnId; 
		});
	},
	initData:function(){
		//随访信息
		$.ajax({
			url: base + '/followuppagedata.html?followupid='+followupid+'&prsnId='+prsnId,
			type: 'POST',
			dataType: 'json',
			success: function (resp) {
				initDate = resp.dataHash;
				if(resp.show == "Y"){
					$("#cover1").hide();
				}else{
					var html = "";
					html += "<div class='btn btn-xs btn-info' id='editfollowuppage'><i class='icon-edit bigger-120'>修改</a></i></div>";
					$("#show").append(html);
					$("#cover1").show();
				}
			}
		});
		
		//获取详情表格配置  及进行数据初始化
		$.ajax({
			url: base + '/followuptablecfgrefjson.html?tableCode='+tableCode,
			type: 'GET',
			dataType: 'json',
			success: function (resp) {
				console.log(resp);
				for (k in resp.alldata){
					//动态添加属性
					var myid = "cfgline_"+ resp.alldata[k].lineCode;
					//动态添加标签属性
					$("#" + myid).attr("data-req",resp.alldata[k].isReq);
					$("#" + myid).attr("data-title",resp.alldata[k].lineTitle);
					$("#" + myid).attr("data-reftypeid",resp.alldata[k].lineRefTypeId);
 					$("#" + myid).attr("data-multiref",resp.alldata[k].lineMultiRef);
 					if(initDate != null){
 						if((k in initDate) && resp.alldata[k].lineRefTypeId == 0){//输入数据回显
 	 						var inputVl =  initDate[k].lines[0].inputVl;
 	 	 					if(inputVl != '' && inputVl !=null){
 	 	 						$("#" + myid).attr("value",inputVl);
 	 	 					}
 	 					}
 					}
				}
				// 引用数据信息回显
				for (k in resp.data){
					var sels = [];
					var html = "";
					if(resp.data[k].lineMultiRef == "N"){//单选
						for(var i=0;i<resp.data[k].refVls.length;i++){
						var s = {"id":resp.data[k].refVls[i].refVlId,"text":resp.data[k].refVls[i].refVlName};
							sels.push(s);
						}
						var refid = 0;
						if(k in initDate ){ //判断单选是否有数据
	 						refid =  initDate[k].lines[0].refId;
						}
						//单选弹框显示
						var show =  $("[class*='show_cfgline_']");
						for(i=0;i<show.length;i++){
							var thisid = show[i].id;
							var html1 ="";
							if(thisid == ("cfgline_"+k)){
								var showid = thisid.substring("cfgline_".length);
								for(var j=0;j<resp.data[showid].refVls.length;j++){
									html1 += '<input type="radio" name="'+resp.data[showid].lineCode+'" data-value = "'+resp.data[showid].refVls[j].refVlId+'"  value = "'+resp.data[showid].refVls[j].refVlName+'" /> '+resp.data[showid].refVls[j].refVlName+'<br />';
								}
								if(initDate[showid] != null){
									$("#"+thisid).attr("value",initDate[showid].lines[0].refVl);
									$("#"+thisid).attr("data-value",initDate[showid].lines[0].refId);
								}

								$('#cfgline_' + showid + "_dialog .show").append(html1);
							}
						}
						
						if(refid == 0){
							//单选显示
							$('#cfgline_' + resp.data[k].lineCode).text("请选择").editable({
								type: 'select2',
								value : refid,
						        source: sels
						    });
						}else{
							$('#cfgline_' + resp.data[k].lineCode).editable({
								type: 'select2',
								value : refid,
						        source: sels
						    });
						}
						
					}else if(resp.data[k].lineMultiRef == "Y"){ //多选
						var check = null;
						for(var i=0;i<resp.data[k].refVls.length;i++){
							check = resp.data[k].refVls[i].refVlName;
							if(check.indexOf("无") >= 0){
								html += '<input class="checkNo_'+resp.data[k].lineCode+'" type="checkbox" name="'+resp.data[k].lineCode+'" data-value = "'+resp.data[k].refVls[i].refVlId+'"value = "'+resp.data[k].refVls[i].refVlName+'" /> '+resp.data[k].refVls[i].refVlName+'<br />';
							}else if(check.indexOf("未见异常")>=0){
								html += '<input class="checkNo_'+resp.data[k].lineCode+'" type="checkbox" name="'+resp.data[k].lineCode+'" data-value = "'+resp.data[k].refVls[i].refVlId+'"value = "'+resp.data[k].refVls[i].refVlName+'" /> '+resp.data[k].refVls[i].refVlName+'<br />';
							}else if(check.indexOf("没有症状")>=0){
								html += '<input class="checkNo_'+resp.data[k].lineCode+'" type="checkbox" name="'+resp.data[k].lineCode+'" data-value = "'+resp.data[k].refVls[i].refVlId+'"value = "'+resp.data[k].refVls[i].refVlName+'" /> '+resp.data[k].refVls[i].refVlName+'<br />';
							}else{
								html += '<input class="checkYes_'+resp.data[k].lineCode+'" type="checkbox" name="'+resp.data[k].lineCode+'" data-value = "'+resp.data[k].refVls[i].refVlId+'"value = "'+resp.data[k].refVls[i].refVlName+'" /> '+resp.data[k].refVls[i].refVlName+'<br />';
							}
						}
						var refVls = [];
						var refIds = [];
						if(k in initDate ){ //判断多选是否有数据
	 						for(var i=0;i<initDate[k].lines.length;i++){
		 						var refVl = initDate[k].lines[i].refVl;
			 					var refId = initDate[k].lines[i].refId;
		 						refIds.push(refId);
		 						refVls.push(refVl);
		 						}
							}
							var myid = "cfgline_"+resp.data[k].lineCode;
							//多选数据回显
							$("#" + myid).attr("value",refVls);
							$("#" + myid).attr("data-refvlid",refIds);
							console.log(refVls);
							console.log(refIds);
						}
						//多选页面绑定
						$('#cfgline_' + resp.data[k].lineCode + "_dialog .show").append(html);
				}
				Followup.rebindEvent();
			}
		});
	},
	rebindEvent:function(){
		 
		var id = 0;
		//显示弹窗
	    $("[class*='show_cfgline_']").click(function(){
	    	$('input:radio:checked').attr("checked",false);
	    	var thisid = $(this).attr("id");
	    	id = thisid.substring("cfgline_".length);
	    	$('.show_cfgline_'+id).attr("id",thisid); 
	    	$("[class*='inputYes_"+id+"']").css('display','none');
			$('#cover').show();
			$('.adjective_cfgline_'+id).fadeIn("slow");
		});
	   // 显示灰色覆盖层
	    $('#cover').click(function(){
			$('.adjective_cfgline_'+id).hide();
			$('#cover').hide();
		});
	    
	   // 选择+输入弹框，显示输入
	    $("[class*='show']").click(function(){
	    	$.each($('input:radio:checked'),function(){
	    		var refName = "";
	    		var inputValue = "";
	    		refName = $(this).attr("value");
    			inputValue = $("[class *='inputYes_"+id+"']").attr("name");
    			if(inputValue != null){
    				if(refName == inputValue){
        				$("[class*='input_"+id+"']").removeAttr('readonly');//移除readonly属性
        			}else{
        				$("[class*='input_"+id+"']").val("");
        				$("[class*='input_"+id+"']").attr('readonly','readonly');//添加readonly属性
        			}
    			}
	    	}); 
	    });
	    
	    $("#editfollowuppage").click(function(){
	    	$("#cover1").hide();
	    });
	    
	    // 防止多选时选项冲突
	    $("[class^='checkNo_']").click(function(){
		  $("[class^='checkYes_"+id+"']").removeAttr("checked");
	    });
	  
		  $("[class^='checkYes_']").click(function(){
			  $("[class^='checkNo_"+id+"']").removeAttr("checked");
		  });
		  // 死亡原因--躯体疾病
		  $("[class^='display_']").click(function(){
			  console.log($(this));
		  });
	    
	    //点击确定按钮
		$("[class*='adjectiveBut_cfgline_']").click(function(){
			console.log(3);
			$('.show_cfgline_'+id).attr("value","");
			var examine = $("[name*="+id+"]");
			for (var i = 0; i < examine.length; i++) {
                if (examine[i].checked == true) {
                	$('.show_cfgline_'+id).attr('value',examine[i].value);
                	var refid = examine.eq(i).attr("data-value");
                	$(".show_cfgline_"+id).attr("data-value",refid); 
                    $('.adjective_cfgline_'+id).hide();
        			$('#cover').hide();
                }
            }
			
			//处理多选
			var refids = [];
			var check_val = [];
			for (var i = 0; i < examine.length; i++) {
                if (examine[i].checked == true) {
                	var refid = examine.eq(i).attr("data-value");
                	check_val.push(examine[i].value);
                	refids.push(refid);
                }
            }
			$('.show_cfgline_'+id).attr('value',check_val);
        	$(".show_cfgline_"+id).attr("data-value",refids); 
            $('.adjective_cfgline_'+id).hide();
			$('#cover').hide();
		});
	},
	saveOrModifyDocInfo:function(){
		console.log(2);
		var reqDatas = [];
		var cfgs = $("[id^='cfgline_']");
		var staffId = 0;
		for(var index=0; index<cfgs.length; index++){
			var isreq = "";
			var vl = "";
			var title = "";
			var id = "";
			var code = "";
			var reftypeid = "";
			var refid = ""; 
			var multiref = ""; 
			
			var staffid = $("[class*='doctor_'] option:selected").attr("value");
			if(staffid != 0 && staffid != null){
				staffId = $("[class*='doctor_'] option:selected").attr("value");
			}
			id = $(cfgs[index]).attr("id");
			var thisid = id.substring("cfgline_".length);
			
			var staffName = $("[name*='"+thisid+"']").find("option:selected").text();
			$("#"+id).html(staffName);
			
			var data = document.getElementById(id);//遍历dataset
			if(data != null){
				isreq = data.dataset.req;//是否必须
				title = data.dataset.title;//用于提示(不传递)
				reftypeid = data.dataset.reftypeid;//判断 是否引用(不传递)
				multiref = data.dataset.multiref;//判断单选多选
				if(multiref == "N" && parseInt(reftypeid) != 0){
					refid = $("#" + id).editable('getValue',true);//单选
					var refvlid = $("#" + id).attr("data-value");
					if(refvlid!=0 && refvlid != null){ 
						refid = refvlid;
					}
				}
				else if(multiref == "Y"){
					refid = data.dataset.refvlid;//多选
				}
			}
				//TODO 获取弹窗的value
			code = id.substring("cfgline_".length);//列号
			vl = $(cfgs[index]).val();//输入值
			
			if(parseInt(reftypeid) == 0){
				if(isreq == "Y" && vl == ""){//输入值判断
					alert("请输入'" + title + "'");
					return;
				}
			}else{
				if(isreq == "Y" && (refid == "" || refid == null || refid.length == 0) ){//参数值判断
					alert("请选择'" + title + "'");
					return;
				}
			}
			//有值得进行保存
			if(multiref == "N"){//单选或者输入
				var rq = {};
				rq.code = code;
				rq.refid = refid;
				rq.vl = vl;
				if(!((refid == "" || refid == null ) && (vl == "" || vl == null)))
					reqDatas.push(rq);
			}else if(multiref == "Y"){//多选     refid为 "219,220,221"
				if(refid.length != 0 ){//保存有选项的
					var arr = refid.split(',');
					for(var i=0 ;i<arr.length;i++){
						if(arr[i] != null && arr[i] != 0 && arr[i] != ""){
							var rq = {};
							rq.code = code;
							rq.vl = vl;
							rq.refid = arr[i];
							reqDatas.push(rq);
						}
					}
				}
			}
			}

		var reqData = {};
		var prsnId = $("#prsnId").attr("value");
		var activepage = $("#activepage").attr("value");
		reqData.followupjson = JSON.stringify(reqDatas);
		reqData.followupid = $("#followupid").val();
		reqData.prsnId = prsnId;
		reqData.tableCode = tableCode;
		reqData.staffId = staffId;
		reqData.prsnId = prsnId;
		$.ajax({
			url: base + '/saveormodifyfollowupjson.html',
			data: reqData,
			type: 'POST',
			dataType: 'json',
			success: function (resp) {
				console.log(resp.message);
				if(resp.message.indexOf("随访记录成功")>0){
					alert(resp.message);
					window.location.href=base+"/docinfopage.html?activepage="+activepage+"&docinfoid="+prsnId; 
				}else if((resp.message.indexOf("空对象"))>0){
					alert("空对象!");
				}else{
					alert("保存失败");
				}
				
			}
		});
		
	}
};
