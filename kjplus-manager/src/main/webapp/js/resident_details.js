
// $(function () {
//     //编辑/保存
//     $('.id_my_edit').click(function () {
//         $('.id_my_save').css('display', 'block');
//         $(this).css('display', 'none');
//         $('.id_my_table input').removeAttr('readonly');
//         $('.id_my_table input').eq(0).focus();
//     });
//     $('.id_my_save').click(function () {
//         $('.id_my_edit').css('display', 'block');
//         $(this).css('display', 'none');
//         $('.id_my_table input').attr('readonly', 'readonly');
//     });
//
//     //alert
//     var _this;
//     $('.my_add_btn').click(function () {
//         _this = $(this);
//         $('#myModalLabel').html(_this.parent().parent().find('td').eq(0).html());
//     });
//     //alert 添加病历
//     $('.id_message_save').click(function () {
//         var pNodes = _this.parent().parent().find('td').eq(1).find('p');
//         var html = '<p>';
//         if (pNodes.eq(pNodes.length - 1).html()) {
//             var no = pNodes.eq(pNodes.length - 1).html().substring(0, 1) - 0 + 1;
//             html += no + '、' +$('.id_message').val();
//             html += '</p>';
//             _this.parent().parent().find('td').eq(1).append(html);
//             $('.id_message').val('');
//         } else {
//             html += '1、' +$('.id_message').val();
//             html += '</p>';
//             _this.parent().parent().find('td').eq(1).append(html);
//             $('.id_message').val('');
//         }
//     });
//     //查看详情
//     $('.id_details').click(function () {
//         console.log(111)
//         $('.id_sffs_list').hide();
//         $('.id_sffs_content').show();
//     });
// });















