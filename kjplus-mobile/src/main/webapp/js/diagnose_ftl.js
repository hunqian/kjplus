var base = $("#base").attr("href");
$(function () {
    //右上角下拉框
    var headerFlag = true;
    $('.header span').click(function () {
        if (headerFlag) {
            headerFlag = false;
            var ulHeight = $('.header li').eq(0).height() * $('.header li').length + 'px';
            $('.header ul').css('height', ulHeight);
            $('.header span i').css('transform', 'rotate(180deg)')
        } else {
            headerFlag = true;
            $('.header ul').css('height', 0);
            $('.header span i').css('transform', 'rotate(0deg)')
        }
    });
    $('.header li').click(function () {
        if ($(this).attr('class') != 'id_add') {
            headerFlag = true;
            $('.header em').html($(this).html());
            $('.header ul').css('height', 0);
            $('.header span i').css('transform', 'rotate(0deg)')
        }
    });

    //接种项目
    var ulHeight = $('.vaccination_type li').eq(0).height() * $('.vaccination_type li').length + 4;
    var vaccinationTypeFlag = true;
    $('.vaccination_type .right').click(function () {
        if (vaccinationTypeFlag) {
            $('.vaccination_type ul').css('height', ulHeight + 'px');
            vaccinationTypeFlag = false;
        } else {
            $('.vaccination_type ul').css('height', 0);
            vaccinationTypeFlag = true;
        }
    });
    $('.vaccination_type li').click(function () {
        $('.vaccination_type .right').html($(this).html() + ' <i class="iconfont">&#xe504;</i>');
        $('.vaccination_type ul').css('height', 0);
        vaccinationTypeFlag = true;
    });

    //签约服务
    $('.appoint p').click(function () {
        if ($(this).attr('class') != 'red') {
            if ($(this).attr('class') != 'bgc') {
                $(this).addClass('bgc');
                $(this).find('i').css('backgroundImage', 'url('+base+'/imgs/vaccination/select_2.png)');
            } else {
                $(this).removeClass();
                $(this).find('i').css('backgroundImage', 'url('+base+'/imgs/vaccination/select_1.png)');
            }
        }
    });

    //签约及取消签约提示框
    $('.main .confirm').click(function () {
        var html = '';
        html += '<p>姓名：张三</p>';
        html += '<p>类别：免疫接种</p>';
        html += '<p>预约内容：乙肝疫苗</p>';
        html += '<p>预约时间：2017年9月1日 18:00-20:00</p>';
        $('.alert').show();
        $('.alert .alert_content').html(html);
        $('.success p').html('预约成功');
    });
    $('.main .off').click(function () {
        $('.alert').show();
        $('.alert .alert_content').html('<p style="text-align: center; margin: 1rem 0;">是否确定取消本次预约</p>');
        $('.success p').html('取消成功');
    });
    var timeout;
    $('.alert .confirm').click(function () {
        clearTimeout(timeout);
        $('.alert').hide();
        $('.success').show();
        timeout = setTimeout(function () {
            $('.success').hide();
            // $.ajax();
            // location.href = './family_doctor.html';
        }, 1500);

    });
    $('.alert .off').click(function () {
        $('.alert').hide();
    });
});