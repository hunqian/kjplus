var base = $("#base").attr("href");
$(function () {
    //设置默认地址
    $('.delivery .bottom i').click(function () {
        $('.delivery .bottom i').removeClass();
        $('.delivery .bottom .left').html('设为默认');
        $(this).addClass('default');
        $(this).parent().find('.left').html('默认地址')
    });
    //新建地址按钮
    $('.footer button').click(function () {
        $('.alert').show();
        $('.alert .id_name').val('');
        $('.alert .id_phone').val('');
        $('.alert .id_site').val('');
    });
    //立即添加新建按钮
    $('.alert .id_add_site').click(function () {
        var name = $('.alert .id_name').val();
        var phone = $('.alert .id_phone').val();
        var site = $('.alert .id_site').val();
        var html = '';
        html += '<div class="delivery">\n' +
            '            <div class="top clearfix">\n' +
            '                <span class="fl">' + name + '</span>\n' +
            '                <em class="fr">' + phone + '</em>\n' +
            '            </div>\n' +
            '            <div class="site">\n' +
            '                <p> ' + site + ' </p>\n' +
            '            </div>\n' +
            '            <div class="bottom clearfix">\n' +
            '                <i></i>\n' +
            '                <span class="fl left">设为默认</span>\n' +
            '                <span class="fr right">\n' +
            '                    <button class="id_edit">编辑</button>\n' +
            '                    <button class="id_delete">删除</button>\n' +
            '                </span>\n' +
            '            </div>\n' +
            '        </div>';
        $('.main').append(html);
        $('.alert').hide();
    });
    //编辑按钮
    $('.id_edit').click(function () {
        var thisParent = $(this).parent().parent().parent();
        console.log(111)
        $('.alert_1').show();
        $('.alert_1 .id_name').val(thisParent.find('.id_name').html());
        $('.alert_1 .id_phone').val(thisParent.find('.id_phone').html());
        $('.alert_1 .id_site').val(thisParent.find('.id_site').html());
    });
    //保存编辑按钮
    $('.alert_1 .id_add_site').click(function () {
        $('.alert_1').hide();
    });
    //删除按钮
    $('.id_delete').click(function () {
        $('.alert_2').show();
    });
    //确定删除按钮
    $('.alert_2 button').click(function () {
        $('.alert_2').hide();
    });
    //关闭弹框按钮
    $('.alert .off').click(function () {
        $('.alert').hide();
    });
    $('.alert_1 .off').click(function () {
        $('.alert_1').hide();
    });
    $('.alert_2 .off').click(function () {
        $('.alert_2').hide();
    });

});