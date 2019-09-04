var base = $("#base").attr("href");
$(function () {
    $('.character').click(function () {
        $(this).toggleClass('voice');
        if ($(this).attr('class') == 'character') {
            $('.press_talk').hide();
            $('.input_box input').show();
            if ($('.input_box input').val()) {
                $('.more').hide();
                $('.send').show();
            } else {
                $('.more').show();
                $('.send').hide();
            }
        } else {
            $('.press_talk').show();
            $('.input_box input').hide();
            $('.more').show();
            $('.send').hide();
        }
    });
    $('.input_box input').bind('input propertychange', function () {
        if ($('.input_box input').val()) {
            $('.more').hide();
            $('.send').show();
        } else {
            $('.more').show();
            $('.send').hide();
        }
    })
    var moreFlag = true;
    $('.more').click(function () {
        if (moreFlag) {
            $('.photo').css('height', '5rem');
            moreFlag = false;
        } else {
            $('.photo').css('height', '0');
            moreFlag = true;
        }
    });
    //按住说话
    var pressTalk = document.querySelector('.press_talk');
    pressTalk.addEventListener('touchstart', function(ev) {
        ev.preventDefault();
        // this.css({'color': '#fff', 'backgroundColor': '#0bd16b'});
        this.style.color = '#fff';
        this.style.backgroundColor = '#0bd16b';
    }, false);
    pressTalk.addEventListener('touchend', function(ev) {
        ev.preventDefault();
        // this.css({'color': '#aaa', 'backgroundColor': '#eee'});
        this.style.color = '#aaa';
        this.style.backgroundColor = '#eee';
    }, false);
    //播放语音
    var audioInterval;
    $('.audio_box').click(function () {
        clearInterval(audioInterval);
        var _this = $(this);
        var audio = this.querySelector('audio');
        if (_this.attr('name')) {
            _this.removeAttr('name');
        }
        if (document.getElementsByName('true')[0]) {
            var audioNameBox =  document.getElementsByName('true')[0];
            var audioName = audioNameBox.getElementsByTagName('audio')[0];
            var audioNameBoxI = audioNameBox.getElementsByTagName('i');
            audioName.pause();
            audioName.currentTime = 0;
            for(var i = 0, l = audioNameBoxI.length; i < l; i++) {
                audioNameBoxI[i].style.display = 'block';
            }
            audioNameBox.removeAttribute('state');
            audioNameBox.removeAttribute('name');
        }
        if (!_this.attr('state')) {
            _this.attr('state', 'true');
            _this.attr('name', 'true');
            var indexs = 0;
            audio.play();
            _this.find('em').hide();
            _this.find('i').hide();
            _this.find('i').eq(0).show();
            audioInterval = setInterval(function () {
                indexs += 1;
                if (indexs == 1) {
                    _this.find('i').eq(1).show();
                }
                if (indexs == 2) {
                    _this.find('i').eq(2).show();
                }
                if (indexs == 3) {
                    _this.find('i').hide();
                    _this.find('i').eq(0).show();
                    indexs = 0;
                }
            }, 400);
            audio.addEventListener('ended',function () {
                clearInterval(audioInterval);
                _this.find('i').show();
                _this.removeAttr('state');
                _this.removeAttr('name');
            })
        } else {
            _this.find('i').show();
            audio.pause();
            audio.currentTime = 0;
            _this.removeAttr('state');
            _this.removeAttr('name');
        }
    });
    //发送输入文字
    $('.send').click(function () {
        var html = '';
        html += '<div class="clearfix my_talk">';
        html += '<img src="imgs/consult/my.png" alt="my" class="fr">';
        html += '<div class="fr clearfix">';
        html += '<i class="fr"></i>';
        html += '<p class="fr">' + $(".text").val() + '</p>';
        html += '</div>';
        html += '</div>';
        $('.talk').append(html);
        $('.text').val('');
        $('.more').show();
        $('.send').hide();
        $(window).scrollTop($('.box').height());
    });
    //查看大图
    $('.id_img').click(function () {
        // $.ajax();
        var imgObj = new Image();
        imgObj.src = $('.alert img').attr('src');
        if (imgObj.width/imgObj.height > $(window).width()/$(window).height()) {
            $('.alert img').css({'width': $(window).width(), 'height': $(window).width()*imgObj.height/imgObj.width, 'position': 'absolute', 'top': '50%', 'marginTop': -$(window).width()*imgObj.height/imgObj.width/2});
        } else {
            $('.alert img').css({'height': $(window).height(), 'width': $(window).height()*imgObj.width/imgObj.height, 'position': 'absolute', 'left': '50%', 'marginLeft': -$(window).height()*imgObj.width/imgObj.height/2});
        }
        $('.alert').show();
    });
    $('.alert').click(function () {
        $(this).hide();
    });
});
