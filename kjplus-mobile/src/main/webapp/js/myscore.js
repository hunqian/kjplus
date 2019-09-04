var base = $("#base").attr("href");
$('.tab .score').css({'color': '#36d592', 'borderBottom': '0.0625rem solid #36d592'});
$('.tab span').click(function () {
    $('.tab span').css({'color': '#333', 'border': 'none'});
    $(this).css({'color': '#36d592', 'borderBottom': '0.0625rem solid #36d592'});
    $('.score_box').hide();
    $('.expense_box').hide();
    $('.' + $(this).attr('class') + '_box').show();
});