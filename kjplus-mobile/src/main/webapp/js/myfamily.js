var base = $("#base").attr("href");
$(function () {
    $('.family').click(function () {
    	var prsnCode = $(this).attr("data-code");
    	location.href = base + '/page_document.html?prsnCode='+prsnCode;
    });
    $('.create_doc').click(function () {
    	location.href = base + '/create_document.html';
    });
});


