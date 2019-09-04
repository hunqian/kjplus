var base = $("#base").attr("href");

Info = {
	init:function(){
		this.initData();
		this.bindEvent();
	},
	initData:function(){
		$("[id^='fz_']").unbind().click(function(){
			//获取该id的标签信息   <i class="collect id_collect" id="fz_z'+info.infoCode+'"></i>
			var thatele = this;
			//获取id的信息
			var id = $(this).attr("id");
			var reqData = {};
			reqData.inforefcode = id.substring(4);
			reqData.zanorfocus = id.substring(3,4); 
			$.ajax({
				url: base + '/infozanfucusjson.html',
				data: reqData,
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					//获取当前点赞关注数量
					var curNum = $(thatele).next().text();
					//将更新数量提交页面
					$(thatele).next().text(parseInt(curNum) + resp.data);
					//注意修改赞的信息提示变化
				}
			});
		});
	
	},
	bindEvent:function(){
	},
	rebindEvent:function(){
		//重新绑定事件，对于新动态生成的dom需要重新绑定时间
	}
}

$(function () {
	Info.init();
});