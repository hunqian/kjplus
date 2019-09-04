
//基本信息、详细信息tab切换
$(function () {
    $('.id_base').click(function () {
        $('.id_base').css({'color': '#0bd16b', 'border-bottom': '0.125rem solid #0bd16b'});
        $('.id_detailed').css({'color': '#333', 'border': 'none'});
        $('.id_base_data').show();
        $('.id_detailed_data').hide();
    });
    $('.id_detailed').click(function () {
        $('.id_detailed').css({'color': '#0bd16b', 'border-bottom': '0.125rem solid #0bd16b'});
        $('.id_base').css({'color': '#333', 'border': 'none'});
        $('.id_detailed_data').show();
        $('.id_base_data').hide();
    });
});

//性别选择弹框
$(function () {
    $('.id_sex').click(function () {
        $('.alert').show();
    });
    $('.alert .alert_content div').click(function () {
        $('.alert').hide();
        var html = $(this).html() + '<i class="iconfont">&#xe687;</i>';
        $('.id_sex').html(html).css('color', '#555');
    });
    $('.alert .off').click(function () {
        $('.alert').hide();
    });
});

//生日日期选择
previousDate("birthday");

//身份证号输入正确性判断
$(function () {
    var val = '';
    $('#idNumber').bind('input propertychange', function () {
        // var re = /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
        if ($(this).val().length === 0) {
            val = '';
        }
        if ($(this).val().length === 1) {
            var result = /[0-9]/.exec($(this).val());
            if (result !== null) {
                $(this).val(result.input);
                val = result.input;
            } else {
                $(this).val(val);
            }
        }
        if ($(this).val().length > 1 && $(this).val().length < 18) {
            var result1 = /[0-9]$/.exec($(this).val());
            if (result1 !== null) {
                $(this).val(result1.input);
                val = result1.input;
            } else {
                $(this).val(val);
            }
        }
        if ($(this).val().length === 18) {
            var result2 = /([0-9]|X|x)$/.exec($(this).val());
            if (result2 !== null) {
                $(this).val(result2.input);
                val = result2.input;
            } else {
                $(this).val(val);
            }
        }
    });
});

//手机号输入判断
$(function () {
    $('#phoneNumber').bind('input propertychange', function () {
        if ($(this).val().length > 11) {
            $(this).val($(this).val().substring(0,11))
        }
    })
});

//添加疾病史弹框及选择事件
$(function () {
    //添加疾病史弹框
    $('.id_add').click(function () {
        $('.alert_1').show();
    });
    //选择疾病史的点击事件
    $('.alert_1 .alert_content div').click(function () {
        if($(this).attr('class') !== 'other_input clearfix') {
            if ($(this).attr('id') === 'noDisease') {
                $('.alert_1 .alert_content div i').removeClass('select');
                $(this).find('i').toggleClass('select');
            } else {
                $(this).find('i').toggleClass('select');
                $('#noDisease i').removeClass('select');
            }
        }
    });
    //添加疾病史的点击事件
    $('.id_add_disease').click(function () {
        $('.alert_1').hide();
        if ($('.disease_box').length) {
            $('.disease_box').remove();
        }
        var selectDiv = $('.alert_1 .alert_content div');
        var number = 0;
        var html = '<div class="disease_box id_disease_box">';
        for (var i = 0; i < selectDiv.length; i++) {
            if ($('#noDisease i').attr('class') === 'fl select') {
                html += '<div style="padding-bottom: 0.5rem;">';
                html += '<p>' + selectDiv.eq(i).find('span').html() + '</p>';
                html += '</div>';
                break;
            }
            if (selectDiv.eq(i).find('i').attr('class') === 'fl select' && selectDiv.eq(i).attr('id') !== 'otherDisease') {
                var inputId = selectDiv.eq(i).attr('id') + 'Date';
                ++number;
                html += '<div>';
                html += '<p>' + number + '、' + selectDiv.eq(i).find('span').html() + '</p>';
                html += '<input placeholder="请输入疾病史日期" readonly="readonly" id="' + inputId + '" type="text">';
                html += '</div>';
                previousDate(inputId);
            }
        }
        if ($('#otherDisease i').attr('class') === 'fl select') {
            for (var i = 0; i < $('.alert_1 .other_input').length; i++) {
                if ($('.alert_1 .other_input').eq(i).find('input').val() !== '') {
                    var otherId = $('.alert_1 .other_input').eq(i).find('input').attr('id') + 'Date';
                    ++number;
                    html += '<div>';
                    html += '<p>' + number + '、' + $('.alert_1 .other_input').eq(i).find('input').val() + '</p>';
                    html += '<input placeholder="请输入疾病史日期" readonly="readonly" id="' + otherId + '" type="text">';
                    html += '</div>';
                    previousDate(otherId);
                } else {
                    $('.alert_2 p').html('请输入其他疾病史');
                    $('.alert_2').show();
                    $('.alert_1').show();
                }
            }
        }
        html += '</div>';
        if (html !== '<div class="disease_box"></div>') {
            $('.disease_history').append(html);
            for (var i = 0, l = $('.disease_box input').length; i < l; i++) {
                previousDate($('.disease_box input').eq(i).attr('id'));
            }
        }
    });
    //选择其他疾病史，显示添加其他疾病史输入框按钮
    $('#otherDisease').click(function () {
        if ($(this).find('i').attr('class') === 'fl select') {
            $('#otherDisease button').show();
        } else {
            $('#otherDisease button').hide();
        }
    });
    //其他疾病史添加输入框按钮
    var otherInputNo = 0;
    $('#otherDisease button').click(function (e) {
        ++otherInputNo;
        var html = '<div class="other_input clearfix">\n' + '<s></s>' +
            '                <input type="text" class="fl" placeholder="请输入其他疾病" id="otherInput' + otherInputNo + '">\n' +
            '            </div>';
        $('.alert_1 .alert_content').append(html);
        //其他疾病史删除输入框按钮
        $('.alert_1 .other_input s').click(function () {
            $(this).parent().remove();
        });
        e.stopPropagation();
    });
    //关闭弹框按钮
    $('.alert_1 .off').click(function () {
        $('.alert_1').hide();
    });
    $('.alert_2 button').click(function () {
        $('.alert_2').hide();
    });
});

//孕产期事件
$(function () {
    // 弹出弹框
    $('.id_pregnant_date').click(function () {
        $('.alert_3').show();
    });
    //选择孕产期的点击事件
    $('.alert_3 .alert_content div').click(function () {
        $('.alert_3 .alert_content div i').removeClass('select');
        $(this).find('i').toggleClass('select');
    });
    //添加孕产期的点击事件
    $('.alert_3 button').click(function () {
        $('.alert_3').hide();
        var html = '';
        if ($('#noPregnant i').attr('class') === 'fl select') {
            $('.pregnant_date span').html('孕产情况');
            html += '<b>无</b>';
            html += '<i class="iconfont ">&#xe687;</i>';
        }
        if ($('#yunQi i').attr('class') === 'fl select') {
            $('.pregnant_date span').html('孕期');
            html += '<b>预产日期</b>';
            html += '<input type="text" placeholder="请输入日期" readonly="readonly" id="pregnantDate"><i class="iconfont ">&#xe687;</i>';
        }
        if ($('#chanRuQi i').attr('class') === 'fl select') {
            $('.pregnant_date span').html('产褥期');
            html += '<b>生产日期</b>';
            html += '<input type="text" placeholder="请输入日期" readonly="readonly" id="pregnantDate"><i class="iconfont ">&#xe687;</i>';
        }
        if (html !== '') {
            $('.id_pregnant_date').html(html).css('color', '#444');
            previousDate("pregnantDate");
        }
    });
    //关闭弹框
    $('.alert_3 .off').click(function () {
        $('.alert_3').hide();
    });
});

//保存并提交建档信息
$(function () {
    //右上角保存按钮
    $('.id_save').click(function () {
        if ($('.id_name').val() === '') {
            $('.alert_2 p').html('请输入姓名');
            $('.alert_2').show();
            return false;
        }
        if ($('#birthday').val() === '') {
            $('.alert_2 p').html('请输入生日');
            $('.alert_2').show();
            return false;
        }
        if ($('#phoneNumber').val() === '') {
            $('.alert_2 p').html('请输入手机号码');
            $('.alert_2').show();
            return false;
        }
        if ($('#phoneNumber').val().length !== 11) {
            $('.alert_2 p').html('手机号码输入有误');
            $('.alert_2').show();
            return false;
        }
        if ($('#idNumber').val() === '') {
            $('.alert_2 p').html('请输入身份证号');
            $('.alert_2').show();
            return false;
        }
        if ($('#idNumber').val().length !== 15 && $('#idNumber').val().length !== 18) {
            $('.alert_2 p').html('身份证号输入有误');
            $('.alert_2').show();
            return false;
        }
        if ($('.id_site').val() === '') {
            $('.alert_2 p').html('请输入家庭住址');
            $('.alert_2').show();
            return false;
        }
        if ($('.id_disease_box').length === 0) {
            $('.alert_2 p').html('请输入既往病史');
            $('.alert_2').show();
            return false;
        }
        for(var i = 0; i < $('.id_disease_box input').length; i++) {
            if ($('.id_disease_box input').eq(i).val() === '') {
                $('.alert_2 p').html('请输入既往病史日期');
                $('.alert_2').show();
                return false;
            }
        }
        if($('.id_pregnant_date').find('b').html() === undefined) {
            $('.alert_2 p').html('请输入孕产期');
            $('.alert_2').show();
            return false;
        }
        if($('.id_pregnant_date').find('input').val() === '') {
            $('.alert_2 p').html('请输入孕产期日期');
            $('.alert_2').show();
            return false;
        }
        $('.alert_4').show();
        var html = '';
        html += '<p class="clearfix"><span class="fl left">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</span><span class="fr right">' + $('.id_name').val() + '</span></p>';
        html += '<p class="clearfix"><span class="fl left">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</span><span class="fr right">' + $('.id_sex').html().substring(0,1) + '</span></p>';
        html += '<p class="clearfix"><span class="fl left">生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日：</span><span class="fr right">' + $('#birthday').val() + '</span></p>';
        html += '<p class="clearfix"><span class="fl left">手&nbsp;&nbsp;机&nbsp;&nbsp;号：</span><span class="fr right">' + $('#phoneNumber').val() + '</span></p>';
        html += '<p class="clearfix"><span class="fl left">身份证号：</span><span class="fr right">' + $('#idNumber').val() + '</span></p>';
        html += '<p class="clearfix"><span class="fl left">家庭住址：</span><span class="fr right">' + $('.id_site').val() + '</span></p>';
        html += '<p class="clearfix"><span class="fl left">既往病史：</span><span class="fr right">';
        for(var i = 0; i < $('.id_disease_box div').length; i++) {
            html += $('.id_disease_box div').eq(i).find('p').html();
            if ($('.id_disease_box div').eq(i).find('input').val() !== undefined) {
                html += '（' + $('.id_disease_box div').eq(i).find('input').val() + '）';
            }
            html += '<br>';
        }
        html += '</span></p>';
        html += '<p class="clearfix"><span class="fl left">孕产情况：</span><span class="fr right">' + $('.id_pregnant_date').find('b').html();
        if ($('.id_pregnant_date').find('input').val() !== undefined) {
            html += '（' + $('.id_pregnant_date').find('input').val() + '）';
        }
        html += '</span></p>';
        $('.alert_4 .alert_content').html(html);

        // console.log($('.id_name').val());//姓名
        // console.log($('.id_sex').html().substring(0,1));//性别
        // console.log($('#birthday').val())//生日
        // console.log($('#idNumber').val())//身份证号
        // console.log($('#phoneNumber').val());//手机号
        // console.log($('.id_site').val())//住址
        // var diseaseDivs = $('.id_disease_box div');
        // for(var i = 0; i < diseaseDivs.length; i++) {
        //     console.log(diseaseDivs.eq(i).find('p').html());
        //     console.log(diseaseDivs.eq(i).find('input').val());
        // }
        // console.log($('.id_pregnant_date').find('b').html());
        // console.log($('.id_pregnant_date').find('input').val());

    });
    $('.alert_4 button').click(function () {
        $('.alert_2 p').html('保存成功');
        $('.alert_2').show();
        $('.alert_4').hide();
    });
    //关闭弹框
    $('.alert_4 .off').click(function () {
        $('.alert_4').hide();
    });
});

//健康信息
$(function () {
    //弹出
    $('.id_health_tab').click(function () {
        $('.health_box').show();
    });
    //血型
    $(function () {
        //显示选择血型弹框
        $('.id_blood_type').click(function () {
            $('.alert_5').show();
        });
        $('.alert_5 .alert_content div').click(function () {
            $('.alert_5').hide();
            var html = $(this).html();
            $('.id_blood_type input').val(html).css('color', '#555');
        });
        $('.alert .off').click(function () {
            $('.alert').hide();
        });
        //关闭选择血型弹框
        $('.alert_5 .off').click(function () {
            $('.alert_5').hide();
        });
    });
    //手术史
    $(function () {

    });



    //完成
    $('.id_health_finish').click(function () {
        $('.health_box').hide();
    });
});
//行为信息
$(function () {
    //弹出
    $('.id_live_tab').click(function () {
        $('.live_box').show();
    });
    //完成
    $('.id_live_finish').click(function () {
        $('.live_box').hide();
    });
});
//其他信息
$(function () {
    //弹出
    $('.id_other_tab').click(function () {
        $('.other_box').show();
    });
    //完成
    $('.id_other_finish').click(function () {
        $('.other_box').hide();
    });
});



//截止到今天的时间插件配置
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
    opt.defaultdate = {
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
    $(id).mobiscroll($.extend(opt['date'], opt['defaultdate']));
    
   
}

