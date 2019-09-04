var base = $("#base").attr("href");
var initData = 0;

DocInfoPage = {
	init:function(docinfoid){
		this.initData(docinfoid);
		this.bindEvent();
	},
	bindEvent:function(){
		$("#save-docinfo-btn").click(function(){
			DocInfoPage.saveOrModifyDocInfo();
		});
	},
	initData:function(docinfoid){
		var reqData = {};
		reqData.docinfojson = docinfoid;
		//获取该用户档案信息
		$.ajax({
			url: base + '/docinfopagedata.html?docinfoid='+docinfoid,
			type: 'POST',
			dataType: 'json',
			success: function (resp) {
				initData = resp.dataHash;
			}
		});
		
		//获取该用户建档配置  及进行数据初始化
		$.ajax({
			url: base + '/tablecfgrefjson.html?code=R1001',
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
 					if((k in initData) && resp.alldata[k].lineRefTypeId == 0){//输入数据回显
 						var inputVl =  initData[k].lines[0].inputVl;
 	 					if(inputVl != '' && inputVl !=null){
 	 						$("#" + myid).attr("value",inputVl);
 	 					}
 					}
				}
				// 引用数据信息回显
				for (k in resp.data){
					var sels = [];
					var html = "";
					if(resp.data[k].lineMultiRef == "N"){//TODO 输入处理 单选
						for(var i=0;i<resp.data[k].refVls.length;i++){//
						var s = {"id":resp.data[k].refVls[i].refVlId,"text":resp.data[k].refVls[i].refVlName};
							sels.push(s);
						}
						var refid = 0;
						if(k in initData ){ //判断单选是否有数据  单选数据回显
	 						refid =  initData[k].lines[0].refId;
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
						if(k in initData ){ //判断多选是否有数据
	 						for(var i=0;i<initData[k].lines.length;i++){
		 						var refVl = initData[k].lines[i].refVl;
			 					var refId = initData[k].lines[i].refId;
		 						refIds.push(refId);
		 						refVls.push(refVl);
		 						}
							}
							var myid = "cfgline_"+resp.data[k].lineCode;
							//多选数据回显
							$("#" + myid).attr("value",refVls);
							$("#" + myid).attr("data-refvlid",refIds);
						}
						//多选页面绑定
						$('#cfgline_' + resp.data[k].lineCode + "_dialog .show").append(html);
				}
				DocInfoPage.rebindEvent();
			}
		});
	},
	rebindEvent:function(){
	    //药物过敏史弹出框
		var id = 0;
	    $("[class*='show_cfgline_']").click(function(){
	    	var thisid = $(this).attr("id");
	    	id = thisid.substring("cfgline_".length);
	    	console.log(id);
			$('.cover_cfgline_10').show();
			$('.adjective_cfgline_'+id).fadeIn("slow");
		});
		$("[class*='cover_cfgline_']").click(function(){
			$('.adjective_cfgline_'+id).hide();
			$('.cover_cfgline_10').hide();
		});
		$("[class*='adjectiveBut_cfgline_']").click(function(){
			var refids = [];
			$.each($('input:checkbox:checked'),function(){
			var	refid = $(this).attr('data-value');
			refids.push(refid);
			
            obj = document.getElementsByName(id);
            console.log($(this).attr("name"));
              
				check_val = [];
				for(k in obj){
					if(obj[k].checked)
    					check_val.push(obj[k].value);
				}
			$('.show_cfgline_'+id).attr('value',check_val);	
            $('.adjective_cfgline_'+id).hide();
			$('.cover_cfgline_10').hide();
			});
			//TODO 变通用
	        $("#cfgline_"+id).attr("data-refvlid",refids); 
		});
	},
	
	saveOrModifyDocInfo:function(){
		var reqDatas = [];
		var cfgs = $("[id^='cfgline_']");
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
			
			var data = document.getElementById(id);//遍历dataset
			isreq = data.dataset.req;//是否必须
			title = data.dataset.title;//用于提示(不传递)
			reftypeid = data.dataset.reftypeid;//判断 是否引用(不传递)
			multiref = data.dataset.multiref;//判断单选多选
			console.log(multiref);
			if(multiref == "N" && parseInt(reftypeid) != 0){
				refid = $("#" + id).editable('getValue',true);//单选
				console.log(refid);
			}
			else if(multiref == "Y")
				refid = data.dataset.refvlid;//多选
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
				console.log(rq);
				if(!((refid == "" || refid == null ) && (vl == "" || vl == null)))
					reqDatas.push(rq);
			}else if(multiref == "Y"){//多选     refid为 "219,220,221"
				if(refid.length != 0 ){//保存有选项的
					var arr = refid.split(',');
					for(var i=0 ;i<arr.length;i++){
						var rq = {};
						rq.code = code;
						//TODO 输入值暂定。
						rq.vl = vl;
						rq.refid = arr[i];
						reqDatas.push(rq);
					}
				}
			}
			}
		var reqData = {};
		reqData.docinfojson = JSON.stringify(reqDatas);
		reqData.docinfoid = $("#docinfoid").val();
		$.ajax({
			url: base + '/saveormodifydocinfojson.html',
			data: reqData,
			type: 'POST',
			dataType: 'json',
			success: function (resp) {
				alert(resp.message);
			}
		});
		
	}
};

/*
$(function(){ 
	DocInfoPage.init();
});
*/