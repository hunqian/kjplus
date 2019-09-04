var base = $("#base").attr("href");

Info = {
	init:function(){
		this.initData();
		this.bindEvent();
	},
	initData:function(){
		var catgid = $("#catglist").attr("data-catgid");
		if(catgid == 0){
			var childs = $("#catglist span:eq(0)");
			if(childs.length > 0){
				catgid = $("#catglist span:eq(0)").attr("data-id");
			}
		}
		if(catgid == 0){
			return;
		}
		
		var reqData = {};
		reqData.page = 0;
		reqData.paging = 10;
		reqData.catgid = catgid;
		$.ajax({
			url: base + '/health_info_json.html',
			data: reqData,
			type: 'GET',
			dataType: 'json',
			success: function (resp) {
				
				var html = "";
				console.log(resp);
				for(var i=0;i<resp.infos.length;i++){
					html +='<div class="news clearfix">';
					html +='    <img src="${base}/imgs/health_information/banner_1.png" alt="banner1" class="fl">';
					html +='    <div class="fr">';
					html +='        <p class="text"><a href="'+base+'/showinfo.html?inforefcode='+resp.infos[i].refCode+'">'+resp.infos[i].infoTitle+'</p>';
					html +='        <p class="time">';
					html +='            <span>刚刚</span>';
					html +='            <span>';
					html +='                <i class="collect" id="fz_z'+resp.infos[i].refCode+'"></i>';
					html +='                <em>'+resp.infos[i].zanNum+'</em>';
					html +='                <i class="see" id="fz_f'+resp.infos[i].refCode+'"></i>';
					html +='                <em>'+resp.infos[i].focusNum+'</em>';
					html +='            </span>';
					html +='        </p>';
					html +='    </div>';
					html +='</div>';
				}
				$("#info_box").append(html);
				Info.rebindEvent();
			}
		});
	},
	bindEvent:function(){
	    //顶部tab
	    var ulWidth = 0;
	    for (var i = 0; i < $('.tab li').length; i++) {
	        ulWidth += $('.tab li').eq(i).width();
	    }
	    $('.tab li span').eq(0).css({'color': '#fff', 'background': '#36d592'})
	    $('.tab ul').css('width', ulWidth + 'px');
	    $('.tab li span').click(function () {
	        $('.tab li span').css({'color': '#999', 'background': '#f5f5f5'});
	        $(this).css({'color': '#fff', 'background': '#36d592'});
	    });
	    //页面跳转
	    $('.id_swiper_view div').click(function () {
	        location.href = './news.html';
	    });
	    $('.news_box .news').click(function () {
	        location.href = './news.html';
	    });
	},
	rebindEvent:function(){
		//重新绑定事件，对于新动态生成的dom需要重新绑定时间
		$("[id^='fz_']").unbind().click(function(){
			var thatele = this;
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
					var curNum = $(thatele).next().text();
					$(thatele).next().text(parseInt(curNum) + resp.data);
					//注意修改赞的信息提示变化
				}
			});
		});
	}
}

$(function () {
	Info.init();
});