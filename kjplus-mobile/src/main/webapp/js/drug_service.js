var base = $("#base").attr("href");
$(function () {
    $('.message h3').click(function () {
        location.href = './health_information.html'
    });
    $('.message .news').click(function () {
        location.href = './news.html'
    });
});