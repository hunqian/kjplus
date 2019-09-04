var base = $("#base").attr("href");
$(function () {
    //页面跳转
    $('.footer div').click(function () {
        if ($(this).attr('style') == 'color: rgb(149, 149, 149);') {
            location.href = './' + $(this).attr('name') + '.html';
        }
    });
    $('.score').click(function () {
        location.href = './myscore.html'
    });
    $('.site').click(function () {
        location.href = './drug_delivery_site.html'
    });
    $('.family').click(function () {
        location.href = './myfamily.html';
    });
    $('.collect').click(function () {
        location.href = './mycollect.html';
    });
    $('.help').click(function () {
        location.href = './help.html';
    });

    //二维码
    $('.portrait em').click(function () {
        $('.QR_code').show();
    });
    $('.QR_code').click(function () {
        $('.QR_code').hide();
    });
});