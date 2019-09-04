$('.id_personage_type')
$('.id_personage_list li').click(function () {
    $('.id_personage_type').html($(this).find('a').html() + ' <span class="caret"></span>');
});