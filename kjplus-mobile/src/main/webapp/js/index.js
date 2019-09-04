var base = $("#base").attr("href");
console.log(base);

Index = {
	init:function(){
		this.bindEvent();
	},
	bindEvent:function(){

	    //页面跳转
	    $('.footer div').click(function () {
	        if ($(this).attr('style') == 'color: rgb(149, 149, 149);') {
	            location.href = base + '/' + $(this).attr('name') + '.html';
	        }
	    });
	    //健康资讯
	    $('.message h3').click(function () {
	        window.location.href = base+'/health_information.html';
	    });
	    $('.banner').click(function () {
	    	window.location.href = base+'/health_information.html';
	    });
	    $('.message .news').click(function () {
	        location.href = base+'/news.html';
	    });
	    //健康讲堂
	    $('.classroom h3').click(function () {
	        location.href = base + '/health_classroom.html';
	    });
	    $('.classroom .news').click(function () {
	        location.href = base + '/video.html';
	    });
	}
}

$(function () {
	Index.init();
});