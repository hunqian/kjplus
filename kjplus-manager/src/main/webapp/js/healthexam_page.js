var base = $("#base").attr("href");
var initDate = 0;
var healthId = $("#healthId").attr("value");

HealthExam = {
	init:function(){
		this.initData();
		this.bindEvent();
	},
	bindEvent:function(){
		$("#save-health-btn").click(function(){
			HealthExam.saveOrModifyHealthExam();
		});
	},
	initData:function(){
		//健康体检详信息
		$.ajax({
			url: base + '/healthexampagedata.html?healthId='+healthId,
			type: 'POST',
			dataType: 'json',
			success: function (resp) {
				initDate = resp.dataHash;
			}
		});
		
		//获取详情表格配置  及进行数据初始化
		$.ajax({
			url: base + '/healthtablecfgrefjson.html',
			type: 'GET',
			dataType: 'json',
			success: function (resp) {
				//TODO 只需输入数据     输入数据回显
				
				for (k in resp.alldata){
					//动态添加属性
					var myid = "cfgline_"+ resp.alldata[k].lineCode;
					//动态添加标签属性
					$("#" + myid).attr("data-req",resp.alldata[k].isReq);
					$("#" + myid).attr("data-title",resp.alldata[k].lineTitle);
					$("#" + myid).attr("data-reftypeid",resp.alldata[k].lineRefTypeId);
 					$("#" + myid).attr("data-multiref",resp.alldata[k].lineMultiRef);
 					if((k in initDate) && resp.alldata[k].lineRefTypeId == 0){//输入数据回显
 						var inputVl =  initDate[k].lines[0].inputVl;
 	 					if(inputVl != '' && inputVl !=null){
 	 						$("#" + myid).attr("value",inputVl);
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
									html1 += '<input type="radio" id = '+resp.data[showid].lineCode+' name="'+resp.data[showid].lineCode+'" data-value = "'+resp.data[showid].refVls[j].refVlId+'"  value = "'+resp.data[showid].refVls[j].refVlName+'" /> '+resp.data[showid].refVls[j].refVlName+'<br />';
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
						for(var i=0;i<resp.data[k].refVls.length;i++){
							html += '<input type="checkbox" name="'+resp.data[k].lineCode+'" data-value = "'+resp.data[k].refVls[i].refVlId+'"  value = "'+resp.data[k].refVls[i].refVlName+'" /> '+resp.data[k].refVls[i].refVlName+'<br />';
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
						}else{
							
						}
						//多选页面绑定
						$('#cfgline_' + resp.data[k].lineCode + "_dialog .show").append(html);
				}
				HealthExam.rebindEvent();
			}
		});
	},
	rebindEvent:function(){
		var num = 10;
		//添加表格
		$('.addForm').click(function(){
			var height = $('.medicine').outerHeight();
			$('.medicine').css({'height':height+40+'px','line-height':height+40+'px'});
//			var html = '<div class="col-xs-2"><span><input type="text" placeholder="请输入"></span></div>';
//			html+=html;
//			html+=html;
//			html=html+'<div class="col-xs-3"><span class="editable" id="" style="color: #757575;">规律</span></div>';
			var html = $('#list_9').html();
			html='<div id="list_'+num+'">'+html+'</div>';
			$('.addOne').append(html);
			num=num+1;
		});
		
		//删除表格
		$('.removeForm').click(function(){
			var length = $('.addOne>div').length;
			var leng = $('.addOne>div').length-1;
//			if(length>11){
//				$('.addOne>div').slice(leng,length).remove();
//				var height = $('.medicine').outerHeight();
//				$('.medicine').css({'height':height-40+'px','line-height':height-40+'px'});
//			}else if(length<12){
//				alert('最后一行不能删除!');
//			}
			console.log(length);
				if(length<=7){
					alert('最后一行不能删除！');
				} else if(length>7){
					$('.addOne>div').slice(leng,length).remove();
					var height = $('.medicine').outerHeight();
					$('.medicine').css({'height':height-40+'px','line-height':height-40+'px'});
				}
			
		});

		
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
    			if(refName == inputValue){
    				$("[class*='input_"+id+"']").removeAttr('readonly');//移除readonly属性
    			}else{
    				$("[class*='input_"+id+"']").val("");
    				$("[class*='input_"+id+"']").attr('readonly','readonly');//添加readonly属性
    			}
	    	}); 
	    });
	    //点击确定按钮
		$("[class*='adjectiveBut_cfgline_']").click(function(){
			$('.show_cfgline_'+id).attr("value","");
			var refids = [];
			var examine = document.getElementsByName(id);
			for (var i = 0; i < examine.length; i++) {
				var input = "";
                if (examine[i].checked == true) {
                	input = $("[class*='inputYes_"+id+"']").val();
        			
                	$('.show_cfgline_'+id).attr('value',examine[i].value);	
                	refids.push($("#"+id).attr("data-value"));
                    $('.adjective_cfgline_'+id).hide();
        			$('#cover').hide();
        			 $("#cfgline_"+id).attr("data-refvlid",refids); 
                }
            }
			//处理多选
			$.each($('input:checkbox:checked'),function(){
			var	refid = $(this).attr('data-value');
			refids.push(refid);
			
            obj = document.getElementsByName(id);
				check_val = [];
				for(k in obj){
					if(obj[k].checked)
    					check_val.push(obj[k].value);
				}
			$('.show_cfgline_'+id).attr('value',check_val);	
            $('.adjective_cfgline_'+id).hide();
			$('#cover').hide();
			
			 $("#cfgline_"+id).attr("data-refvlid",refids); 
			});
			
		});
	},
	saveOrModifyHealthExam:function(){
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
			
			id = $(cfgs[index]).attr("id");
			var thisid = id.substring("cfgline_".length);
			
			staffId = $("[name*='"+thisid+"'] option:selected").attr("value");
			var staffName = $("[name*='"+thisid+"']").find("option:selected").text();
			$("#"+id).html(staffName);
			
			var data = document.getElementById(id);//遍历dataset
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
		reqData.healthExamJson = JSON.stringify(reqDatas);
		reqData.healthId = $("#healthId").val();
		reqData.prsnId = prsnId;
		reqData.staffId = staffId;
		reqData.prsnId = prsnId;
		$.ajax({
			url: base + '/saveormodifyhealthexamjson.html',
			data: reqData,
			type: 'POST',
			dataType: 'json',
			success: function (resp) {
				alert(resp.message);
				//window.location.href=base+"/healthexamlist.html"; 
			}
		});
		
	}
};

/*
$(function(){ 
	FollowUpPage.init();
});
*/