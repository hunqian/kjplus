var base = $("#base").attr("href");
$(function () {
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
        location.href = './video.html';
    });
    $('.news_box .news').click(function () {
        location.href = './video.html';
    });
});