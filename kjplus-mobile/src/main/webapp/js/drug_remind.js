$(function () {
    //添加提醒
    // $('.header span').click(function () {
    //     var alertHtml = '<div class="alert_box">\n' +
    //         '        <h3>添加提醒<i class="off">X</i></h3>\n' +
    //         '        <div class="alert_content">\n' +
    //         '            <div class="clearfix id_family">\n' +
    //         '                <span class="fl">家庭成员</span>\n' +
    //         '                <i class="fr iconfont">&#xe687;</i>\n' +
    //         '                <span class="fr id_name" style="width: 50%; text-align: right;">请选择</span>\n' +
    //         '            </div>\n' +
    //         '            <div class="clearfix">\n' +
    //         '                <span class="fl">药品名称</span>\n' +
    //         '                <input type="text" class="fl id_drug_name">\n' +
    //         '            </div>\n' +
    //         '            <div class="clearfix date">\n' +
    //         '                <input class="fl" style="border-right: 0.0625rem solid #e6e6e6;" placeholder="开始日期" readonly="readonly" id="remindStartDate">\n' +
    //         '                <input class="fl" placeholder="结束日期" readonly="readonly" id="remindEndDate">\n' +
    //         '                <i>至</i>\n' +
    //         '            </div>\n' +
    //         '            <div class="clearfix time id_time">\n' +
    //         '                <span class="fl">服药时间</span>\n' +
    //         '                <input type="text" class="fr" readonly="readonly" id="remindTime" placeholder="请选择时间">\n' +
    //         '                <i class="id_add"></i>\n' +
    //         '            </div>\n' +
    //         '            <div class="clearfix remind id_remind">\n' +
    //         '                <span class="fl" style="width: 5.5rem;">提前提醒时间</span>\n' +
    //         '                <span class="fr minute id_minute">请选择</span>\n' +
    //         '            </div>\n' +
    //         '        </div>\n' +
    //         '        <button class="green">立即添加</button>\n' +
    //         '    </div>';
    //     $('.alert_remind').html(alertHtml).show();
    //
    //     //关闭添加弹框
    //     $('.alert_remind .off').click(function () {
    //         $('.alert_remind').hide();
    //     });
    //
    //     //立即添加
    //     $('.alert_remind button').click(function () {
    //         //家庭成员是否添加判断
    //         if ($('.alert_remind .id_name').html() === '选择家庭成员') {
    //             $('.alert').show();
    //             $('.alert p').html('请填写家庭成员');
    //             $('.alert button').click(function () {
    //                 $('.alert').hide();
    //             });
    //             return false;
    //         }
    //         //药品名称是否添加判断
    //         if ($('.alert_remind .id_drug_name').val() === '') {
    //             $('.alert').show();
    //             $('.alert p').html('请填写药品名称');
    //             $('.alert button').click(function () {
    //                 $('.alert').hide();
    //             });
    //             return false;
    //         }
    //         //开始、结束日期是否添加判断
    //         if ($('#remindStartDate').val() === '') {
    //             $('.alert').show();
    //             $('.alert p').html('请填写开始日期');
    //             $('.alert button').click(function () {
    //                 $('.alert').hide();
    //             });
    //             return false;
    //         }
    //         if ($('#remindEndDate').val() === '') {
    //             $('.alert').show();
    //             $('.alert p').html('请填写结束日期');
    //             $('.alert button').click(function () {
    //                 $('.alert').hide();
    //             });
    //             return false;
    //         }
    //         //服药时间是否添加判断
    //         for (var i = 0, l = $('.alert_remind .id_time input').length; i < l; i++) {
    //             if ($('.alert_remind .id_time input').eq(i).val() === '') {
    //                 $('.alert').show();
    //                 $('.alert p').html('请填写服药时间');
    //                 $('.alert button').click(function () {
    //                     $('.alert').hide();
    //                 });
    //                 return false;
    //             }
    //         }
    //         //提前提醒时间是否添加判断
    //         if ($('.alert_remind .id_minute').html() === '请选择') {
    //             $('.alert').show();
    //             $('.alert p').html('请填写提前提醒时间');
    //             $('.alert button').click(function () {
    //                 $('.alert').hide();
    //             });
    //             return false;
    //         }
    //
    //         // $.ajax();
    //         var remindHtml = '<div class="clearfix remind_box">\n' +
    //             '            <img src="./imgs/measure_record/img_1.png" alt="" class="fl">\n' +
    //             '            <div class="fr">\n' +
    //             '                <p class="name" r-name="' + $('.alert_remind .id_name').html() + '" r-minute="' + $('.alert_remind .id_minute').html() + '">' + $('.alert_remind .id_name').html() + ' ' + $('.alert_remind .id_minute').html() + '</p>\n' +
    //             '                <p class="drug" r-drug="' + $('.alert_remind .id_drug_name').val() + '">药品：' + $('.alert_remind .id_drug_name').val() + '</p>\n' +
    //             '                <p class="date" r-start="' + $('#remindStartDate').val() + '" r-end="' + $('#remindEndDate').val() + '">起止日期：' + $('#remindStartDate').val() + '至' + $('#remindEndDate').val() + '</p>\n' +
    //             '                <p class="time clearfix">\n' ;
    //         var timeInput = [];
    //         for(var i = 0, l = $('.alert_remind .id_time input').length; i < l; i++) {
    //             timeInput.push($('.alert_remind .id_time input').eq(i).val());
    //         }
    //         timeInput = $.unique(timeInput);
    //         for(var i = 0, l = timeInput.length; i < l; i++) {
    //             remindHtml += '<span class="fl">' + timeInput[i] + '</span>';
    //         }
    //         remindHtml += '                </p>\n' +
    //             '                <p class="btn">\n' +
    //             '                    <span class="edit">修改提醒</span>\n' +
    //             '                    <span class="off">取消提醒</span>\n' +
    //             '                </p>\n' +
    //             '            </div>\n' +
    //             '        </div>';
    //         $('.main').prepend(remindHtml);
    //         $('.alert_remind').hide();
    //     });
    //
    //    //家庭成员选择
    //     var objFamily = {
    //         'title': '家庭成员',
    //         'select': ['欧阳询-父亲', '欧阳询-母亲', '欧阳询-儿子', '欧阳询-女儿']
    //     };
    //     singleSelect('.id_family', '.alert_radio', objFamily, family);
    //     function family() {
    //         $('.id_family .id_name').html($(this).html());
    //     }
    //     //开始日期
    //     afterDate('remindStartDate');
    //     //结束日期
    //     afterDate('remindEndDate');
    //     //时间
    //     time('remindTime');
    //     var timeNO = 0;
    //     $('.alert_remind .id_time .id_add').click(function () {
    //         for (var i = 0, l = $('.id_time input').length; i < l; i++) {
    //             if ($('.alert_remind .id_time input').eq(i).val() === '') {
    //                 $('.alert').show();
    //                 $('.alert p').html('请选择本次服药时间，再增加下次服药时间');
    //                 $('.alert button').click(function () {
    //                     $('.alert').hide();
    //                 });
    //                 return false;
    //             }
    //         }
    //         ++timeNO;
    //         var html = '<p class="fr clearfix">\n' +
    //             '                    <input type="text" class="fr" readonly="readonly" id="remindTime' + timeNO + '" placeholder="请选择时间">\n' +
    //             '                    <i class="delete id_delete"></i>\n' +
    //             '                </p>';
    //         $('.alert_remind .id_time').append(html);
    //         time('remindTime' + timeNO);
    //         $('.alert_remind .id_delete').click(function () {
    //             $(this).parent().remove();
    //         });
    //     });
    //     //提前提醒时间
    //     var objremind = {
    //         'title': '提前提醒时间',
    //         'select': ['0分钟','5分钟', '10分钟', '15分钟', '20分钟', '30分钟']
    //     };
    //     singleSelect('.id_remind', '.alert_radio', objremind, remindTime);
    //     function remindTime() {
    //         $('.id_remind .id_minute').html('提前' + $(this).html() + '提醒');
    //     }
    // });


    //添加提醒
    var add = alertRemindBox('add');
    $('.header span').click(function () {
        alertRemind(add);
    });



    function alertRemindBox(e) {
        if (e === 'add') {
            var alertHtml = '<div class="alert_box">\n' +
                '        <h3>添加提醒<i class="off">X</i></h3>\n' +
                '        <div class="alert_content">\n' +
                '            <div class="clearfix id_family">\n' +
                '                <span class="fl">家庭成员</span>\n' +
                '                <i class="fr iconfont">&#xe687;</i>\n' +
                '                <span class="fr id_name" style="width: 50%; text-align: right;">请选择</span>\n' +
                '            </div>\n' +
                '            <div class="clearfix">\n' +
                '                <span class="fl">药品名称</span>\n' +
                '                <input type="text" class="fl id_drug_name">\n' +
                '            </div>\n' +
                '            <div class="clearfix date">\n' +
                '                <input class="fl" style="border-right: 0.0625rem solid #e6e6e6;" placeholder="开始日期" readonly="readonly" id="remindStartDate">\n' +
                '                <input class="fl" placeholder="结束日期" readonly="readonly" id="remindEndDate">\n' +
                '                <i>至</i>\n' +
                '            </div>\n' +
                '            <div class="clearfix time id_time">\n' +
                '                <span class="fl">服药时间</span>\n' +
                '                <input type="text" class="fr" readonly="readonly" id="remindTime" placeholder="请选择时间">\n' +
                '                <i class="id_add"></i>\n' +
                '            </div>\n' +
                '            <div class="clearfix remind id_remind">\n' +
                '                <span class="fl" style="width: 5.5rem;">提前提醒时间</span>\n' +
                '                <span class="fr minute id_minute">请选择</span>\n' +
                '            </div>\n' +
                '        </div>\n' +
                '        <button class="green">立即添加</button>\n' +
                '    </div>';
        }
        else {
            var thisParent = e.parent().parent();
            var times = thisParent.find('.time').find('span');
            // console.log(thisParent)
            var alertHtml = '<div class="alert_box">\n' +
                '        <h3>添加提醒<i class="off">X</i></h3>\n' +
                '        <div class="alert_content">\n' +
                '            <div class="clearfix id_family">\n' +
                '                <span class="fl">家庭成员</span>\n' +
                '                <i class="fr iconfont">&#xe687;</i>\n' +
                '                <span class="fr id_name" style="width: 50%; text-align: right;">' + thisParent.find(".name").attr("r-name") + '</span>\n' +
                '            </div>\n' +
                '            <div class="clearfix">\n' +
                '                <span class="fl">药品名称</span>\n' +
                '                <input type="text" class="fl id_drug_name" value="' + thisParent.find(".drug").attr("r-drug") + '">\n' +
                '            </div>\n' +
                '            <div class="clearfix date">\n' +
                '                <input class="fl" style="border-right: 0.0625rem solid #e6e6e6;" placeholder="开始日期" readonly="readonly" id="remindStartDate" value="' + thisParent.find(".date").attr("r-start") + '">\n' +
                '                <input class="fl" placeholder="结束日期" readonly="readonly" id="remindEndDate" value="' + thisParent.find(".date").attr("r-end") + '">\n' +
                '                <i>至</i>\n' +
                '            </div>\n' +
                '            <div class="clearfix time id_time">\n' +
                '                <span class="fl">服药时间</span>\n' +
                '                <input type="text" class="fr" readonly="readonly" id="remindTime" value="' + times.eq(0).html() + '">\n' +
                '                <i class="id_add"></i>\n';
                var timesNo = 0;
                for (var i = 1, l = times.length; i < l; i++) {
                    ++timesNo;
                    alertHtml +=  '<p class="fr clearfix">\n' +
                        '                    <input type="text" class="fr" readonly="readonly" id="remindTime' + timesNo + '" value="' + times.eq(i).html() + '">\n' +
                        '                    <i class="delete id_delete"></i>\n' +
                        '                </p>';
                }
            alertHtml += '            </div>\n' +
                '            <div class="clearfix remind id_remind">\n' +
                '                <span class="fl" style="width: 5.5rem;">提前提醒时间</span>\n' +
                '                <span class="fr minute id_minute">' + thisParent.find(".name").attr("r-minute") + '</span>\n' +
                '            </div>\n' +
                '        </div>\n' +
                '        <button class="green">立即添加</button>\n' +
                '    </div>';
        }
        return alertHtml;
    }
    function alertRemind(html) {
        var eHtml = html;
        $('.alert_remind').html(eHtml).show();

        //关闭添加弹框
        $('.alert_remind .off').click(function () {
            $('.alert_remind').hide();
        });

        //立即添加
        $('.alert_remind button').click(function () {
            //家庭成员是否添加判断
            if ($('.alert_remind .id_name').html() === '选择家庭成员') {
                $('.alert').show();
                $('.alert p').html('请填写家庭成员');
                $('.alert button').click(function () {
                    $('.alert').hide();
                });
                return false;
            }
            //药品名称是否添加判断
            if ($('.alert_remind .id_drug_name').val() === '') {
                $('.alert').show();
                $('.alert p').html('请填写药品名称');
                $('.alert button').click(function () {
                    $('.alert').hide();
                });
                return false;
            }
            //开始、结束日期是否添加判断
            if ($('#remindStartDate').val() === '') {
                $('.alert').show();
                $('.alert p').html('请填写开始日期');
                $('.alert button').click(function () {
                    $('.alert').hide();
                });
                return false;
            }
            if ($('#remindEndDate').val() === '') {
                $('.alert').show();
                $('.alert p').html('请填写结束日期');
                $('.alert button').click(function () {
                    $('.alert').hide();
                });
                return false;
            }
            //服药时间是否添加判断
            for (var i = 0, l = $('.alert_remind .id_time input').length; i < l; i++) {
                if ($('.alert_remind .id_time input').eq(i).val() === '') {
                    $('.alert').show();
                    $('.alert p').html('请填写服药时间');
                    $('.alert button').click(function () {
                        $('.alert').hide();
                    });
                    return false;
                }
            }
            //提前提醒时间是否添加判断
            if ($('.alert_remind .id_minute').html() === '请选择') {
                $('.alert').show();
                $('.alert p').html('请填写提前提醒时间');
                $('.alert button').click(function () {
                    $('.alert').hide();
                });
                return false;
            }

            // $.ajax();
        var remindHtml = '<div class="clearfix remind_box">\n' +
                        '            <img src="./imgs/measure_record/img_1.png" alt="" class="fl">\n' +
                        '            <div class="fr">\n' +
                        '                <p class="name" r-name="' + $('.alert_remind .id_name').html() + '" r-minute="' + $('.alert_remind .id_minute').html() + '">' + $('.alert_remind .id_name').html() + ' ' + $('.alert_remind .id_minute').html() + '</p>\n' +
                        '                <p class="drug" r-drug="' + $('.alert_remind .id_drug_name').val() + '">药品：' + $('.alert_remind .id_drug_name').val() + '</p>\n' +
                        '                <p class="date" r-start="' + $('#remindStartDate').val() + '" r-end="' + $('#remindEndDate').val() + '">起止日期：' + $('#remindStartDate').val() + '至' + $('#remindEndDate').val() + '</p>\n' +
                        '                <p class="time clearfix">\n' ;
                    var timeInput = [];
                    for(var i = 0, l = $('.alert_remind .id_time input').length; i < l; i++) {
                        timeInput.push($('.alert_remind .id_time input').eq(i).val());
                    }
                    timeInput = $.unique(timeInput);
                    for(var i = 0, l = timeInput.length; i < l; i++) {
                        remindHtml += '<span class="fl">' + timeInput[i] + '</span>';
                    }
                    remindHtml += '                </p>\n' +
                        '                <p class="btn">\n' +
                        '                    <span class="edit id_edit_btn">修改提醒</span>\n' +
                        '                    <span class="off id_off_btn">取消提醒</span>\n' +
                        '                </p>\n' +
                        '            </div>\n' +
                        '        </div>';
                    $('.main').prepend(remindHtml);
                    $('.alert_remind').hide();
                    //修改提醒
                    $('.main .id_edit_btn').click(function () {
                        var _this = $(this);
                        var edit = alertRemindBox(_this);
                        alertRemind(edit);
                    });
                    //取消提醒
                    $('.main .id_off_btn').click(function () {
                        console.log(111);
                        var _this = $(this);
                        var html = '<div class="alert_box">\n' +
                            '        <h3>提示<i class="off">X</i></h3>\n' +
                            '        <div class="alert_content">\n' +
                            '            <p>确定取消提醒</p>\n' +
                            '        </div>\n' +
                            '        <button class="green">确&nbsp;&nbsp;&nbsp;定</button>\n' +
                            '    </div>';
                        $('.alert').html(html).show();
                        $('.alert h3 i').click(function () {
                            $('.alert').hide();
                        });
                        $('.alert button').click(function () {
                            _this.parent().parent().parent().remove();
                            $('.alert').hide();
                        });
                    });
        });

        //家庭成员选择
        var objFamily = {
            'title': '家庭成员',
            'select': ['欧阳询-父亲', '欧阳询-母亲', '欧阳询-儿子', '欧阳询-女儿']
        };
        singleSelect('.id_family', '.alert_radio', objFamily, family);
        function family() {
            $('.id_family .id_name').html($(this).html());
        }
        //开始日期
        afterDate('remindStartDate');
        //结束日期
        afterDate('remindEndDate');
        //时间
        time('remindTime');
        var timeNO = 0;
        $('.alert_remind .id_time .id_add').click(function () {
            for (var i = 0, l = $('.id_time input').length; i < l; i++) {
                if ($('.alert_remind .id_time input').eq(i).val() === '') {
                    $('.alert').show();
                    $('.alert p').html('请选择本次服药时间，再增加下次服药时间');
                    $('.alert button').click(function () {
                        $('.alert').hide();
                    });
                    return false;
                }
            }
            ++timeNO;
            var html = '<p class="fr clearfix">\n' +
                '                    <input type="text" class="fr" readonly="readonly" id="remindTime' + timeNO + '" placeholder="请选择时间">\n' +
                '                    <i class="delete id_delete"></i>\n' +
                '                </p>';
            $('.alert_remind .id_time').append(html);
            time('remindTime' + timeNO);
            $('.alert_remind .id_delete').click(function () {
                $(this).parent().remove();
            });
        });
        //提前提醒时间
        var objremind = {
            'title': '提前提醒时间',
            'select': ['0分钟','5分钟', '10分钟', '15分钟', '20分钟', '30分钟']
        };
        singleSelect('.id_remind', '.alert_radio', objremind, remindTime);
        function remindTime() {
            $('.id_remind .id_minute').html('提前' + $(this).html() + '提醒');
        }
    };
});










