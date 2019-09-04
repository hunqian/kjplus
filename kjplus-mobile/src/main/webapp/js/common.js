var base = $("#base").attr("href");
//截止到今天，以前的日期可选
function previousDate(id) {
    var id = '#' + id;
    var date = new Date();
    var y = date.getFullYear();
    var m = date.getMonth();
    var d = date.getDate();
    var opt = {};
    opt.date = {preset: 'date'};
    opt.datetime = {preset: 'datetime'};
    opt.time = {preset: 'time'};
    opt.default = {
        theme: 'android-ics light',
        display: 'modal',
        mode: 'scroller',
        dateFormat: 'yyyy-mm-dd',
        lang: 'zh',
        showNow: true,
        nowText: "今天",
        startYear: y - 150,
        endYear: y,
        endMonth: m,
        endDay: d
    };
    $(id).mobiscroll($.extend(opt['date'], opt['default']));
}

//截止到今天，以后的日期可选
function afterDate(id) {
    var id = '#' + id;
    var date = new Date();
    var y = date.getFullYear();
    var m = date.getMonth();
    var d = date.getDate();
    var opt = {};
    opt.date = {preset: 'date'};
    opt.datetime = {preset: 'datetime'};
    opt.time = {preset: 'time'};
    opt.default = {
        theme: 'android-ics light',
        display: 'modal',
        mode: 'scroller',
        dateFormat: 'yyyy-mm-dd',
        lang: 'zh',
        showNow: true,
        nowText: "今天",
        startYear: y,
        endYear: y + 20,
        startMonth: m,
        startDay: d
    };
    $(id).mobiscroll($.extend(opt['date'], opt['default']));
}

//时间选择
function time(id) {
    var id = '#' + id;
    var opt = {};
    opt.date = {preset: 'date'};
    opt.datetime = {preset: 'datetime'};
    opt.time = {preset: 'time'};
    opt.default = {
        theme: 'android-ics light',
        display: 'modal',
        mode: 'scroller',
        dateFormat: 'yyyy-mm-dd',
        lang: 'zh',
        showNow: true,
        nowText: "今天",
    };
    // $(id).mobiscroll($.extend(opt['date'], opt['default']));
    var optTime = $.extend(opt['time'], opt['default']);
    $(id).mobiscroll(optTime).time(optTime);
}

//添加手术史、外伤史、输血史
function addHistory(addIcon, alert, addButton, id, append) {
    //添加疾病史弹框
    $(addIcon).click(function () {
        $(alert).show();
    });
    //选择疾病史的点击事件
    $(alert + ' .alert_content .id_select').click(function () {
        $(alert + ' .alert_content .id_select i').removeClass('select');
        $(this).find('i').toggleClass('select');
        if ($(this).attr('class') === 'clearfix id_select id_no') {
            $(id + ' button').hide();
            $(id + 'Box input').parent().hide();
        } else {
            $(id + ' button').show();
            $(id + 'Box input').parent().show();
        }
    });
    //添加疾病史的点击事件
    $(addButton).click(function () {
        $(alert).hide();
        if ($(append + ' .disease_box').length) {
            $(append + ' .disease_box').remove();
        }
        // var selectDiv = $(alert + '.alert_content .id_select');
        var number = 0;
        var html = '<div class="disease_box">';
        // for (var i = 0; i < selectDiv.length; i++) {
        if ($(alert + ' .id_no i').attr('class') === 'fl select') {
            html += '<div style="padding-bottom: 0.5rem;">';
            html += '<p>无</p>';
            html += '</div>';
            // break;
        } else {
            for (var j = 0; j < $(id+ 'Box input').length; j++) {
                if ($(id + 'Box input').eq(j).val() !== '') {
                    var dateId = $(id + 'Box input').eq(j).attr('id') + 'Date';
                    ++number;
                    html += '<div>';
                    html += '<p>' + number + '、' + $(id + 'Box input').eq(j).val() + '</p>';
                    html += '<input placeholder="请输入日期" readonly="readonly" id="' + dateId + '" type="text">';
                    html += '</div>';
                    // previousDate(dateId);
                } else {
                    $('.alert_2 p').html('请输入' + $(id).find('span').html());
                    $('.alert_2').show();
                    $(alert).show();
                }
            }
        }
        // }
        html += '</div>';
        if (html !== '<div class="disease_box"></div>') {
            $(append).append(html);
            for (var i = 0, l = $('.health_disease_history .disease_box input').length; i < l; i++) {
                previousDate($('.health_disease_history .disease_box input').eq(i).attr('id'));
            }
        }
    });
    var No = 0;
    $(id + ' button').click(function (e) {
        ++No;
        var html = '<div class="other_input clearfix">\n' + '<s></s>' +
            '                <input type="text" class="fl" placeholder="请输入" id="' + $(id + 'Box input').eq(0).attr('id') + No + '">\n' +
            '            </div>';
        $(id + 'Box').append(html);
        $(id + 'Box s').click(function () {
            $(this).parent().remove();
        });
        e.stopPropagation();
    });
    //关闭弹框按钮
    $(alert + ' .off').click(function () {
        $(alert).hide();
    });
    $('.alert_2 button').click(function () {
        $('.alert_2').hide();
    });
}

//弹框多选添加
function moreSelect(addInput, obj) {
    $(addInput).click(function () {
        var html = '<div class="alert_box">';
        html += '<h3>' + obj.title + '<i class="off">X</i></h3>';
        html += '<div class="alert_content">';
        for (var i = 0; i < obj.select.length; i++) {
            if (obj.select[i] === '无') {
                html += '<div class="clearfix id_select id_no">\n' +
                    '                <i class="fl select"></i>\n' +
                    '                <span class="fl">' + obj.select[i] + '</span>\n' +
                    '            </div>'
            } else {
                html += '<div class="clearfix id_select">\n' +
                    '                <i class="fl"></i>\n' +
                    '                <span class="fl">' + obj.select[i] + '</span>\n' +
                    '            </div>'
            }
        }
        html += '</div>' + '<button class="green id_add_btn">立即添加</button>' + '</div>';
        $('.alert_9').html(html);
        $('.alert_9').show();
        $('.alert_9 .alert_content .id_select').click(function () {
            // console.log('i样式事件');
            if ($(this).attr('class') === 'clearfix id_select id_no') {
                $('.alert_9 .alert_content div i').removeClass('select');
                $(this).find('i').toggleClass('select');
            } else {
                $(this).find('i').toggleClass('select');
                $('.alert_9 .id_no i').removeClass('select');
            }
        });
        $('.alert_9 .id_add_btn').click(function () {
            // console.log('btn添加事件');
            var selectDiv = $('.alert_9 .alert_content .id_select');
            var html = '';
            for (var i = 0; i < selectDiv.length; i++) {
                if (selectDiv.eq(i).find('i').attr('class') === 'fl select') {
                    html += selectDiv.eq(i).find('span').html() + '/';
                }
            }
            html = html.substring(0, html.length - 1);
            $(addInput).val(html);
            $('.alert_9').hide();
        });
        //关闭弹框按钮
        $('.alert_9 .off').click(function () {
            $('.alert_9').hide();
        });
        $('.alert_9 .alert_box').css('marginTop', (-$('.alert_9 .alert_box').height() / 2) + 'px');
    });
}

//弹框单选添加
function singleSelect(addInput, alert, obj, fun) {
    $(addInput).click(function () {
        var html = '<div class="alert_box">';
        html += '<h3>' + obj.title + '<i class="off">X</i></h3>';
        html += '<div class="alert_content">';
        for (var i = 0; i < obj.select.length; i++) {
            html += '<div class="clearfix">' + obj.select[i] + '</div>';
        }
        html += '</div></div>';
        $(alert).html(html);
        $(alert + ' .alert_content div').click(function () {
            $(alert).hide();
            $(addInput).val($(this).html());
        });
        $(alert + ' .off').click(function () {
            $(alert).hide();
        });
        $(alert).show();
        if (fun !== undefined) {
            $(alert + ' .alert_content div').click(fun);
        }
        $(alert + ' .alert_box').css('marginTop', (-$(alert + ' .alert_box').height() / 2) + 'px');
    });
}

//获取路径
function getLocation(htm) {
    var locationHref=window.document.location.href;
    return locationHref.substring(0, locationHref.lastIndexOf('/', locationHref.indexOf('.html')) + 1) + htm;
}