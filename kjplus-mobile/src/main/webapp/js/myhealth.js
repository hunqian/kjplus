var base = $("#base").attr("href");
$('.footer div').click(function () {
    if ($(this).attr('style') == 'color: rgb(149, 149, 149);') {
        location.href = './' + $(this).attr('name') + '.html';
    }
});
//右上角下拉框
var headerFlag = true;
$('.header span').click(function () {
    if (headerFlag) {
        headerFlag = false;
        var ulHeight = $('.header li').eq(0).height() * $('.header li').length + 'px';
        $('.header ul').css('height', ulHeight);
        $('.header i').css('transform', 'rotate(180deg)')
    } else {
        headerFlag = true;
        $('.header ul').css('height', 0);
        $('.header i').css('transform', 'rotate(0deg)')
    }
});
$('.header li').click(function () {
    if ($(this).attr('class') != 'id_add') {
        headerFlag = true;
        $('.header em').html($(this).html());
        $('.header ul').css('height', 0);
        $('.header i').css('transform', 'rotate(0deg)')
    }
});
