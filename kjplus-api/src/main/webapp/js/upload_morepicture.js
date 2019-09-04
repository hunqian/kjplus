var base = $("#base").attr("href");

Info = {
		init:function(){
			this.initTable();
			this.initData();
			this.bindEvent();
		},bindEvent:function(){
			$('#imgfile').ace_file_input({
				style:'well',
				btn_choose:'用户头像',
				btn_change:null,
				no_icon:'icon-picture',
				thumbnail:'large',
				droppable:true,
				allowExt: ['jpg','jpg','bmp','png','gif'],//该属性只是对文件后缀的控制
				before_change: function(files, dropped) {
					var file = files[0];
					if(typeof file === "string") {//files is just a file name here (in browsers that don't support FileReader API)
						if(! (/\.(jpeg|jpg|png|gif)$/i).test(file) ) return false;
					} else {//file is a File object
						var type = $.trim(file.type);
						if( ( type.length > 0 && ! (/^image\/(jpeg|jpg|png|gif|bmp)$/i).test(type) )
								|| ( type.length == 0 && ! (/\.(jpeg|jpg|png|gif|bmp)$/i).test(file.name) )//for android default browser!
							) return false;
		
						if( file.size > 1100000 ) {//~100Kb
							return false;
						}
					}
					var ajax_option = {
	        				url: base + "/uploadimgfile.html",//上传头像
	        				dataType: "json",
	        				success:function(resp){ 
	        					if(resp.result == 1){
	        						$("#th_uface").val(resp.data.url);
	        					}
	        				}
	        		};
	        		$("#imgfileform").ajaxSubmit(ajax_option);
					return true;
				}
			});
			
		/*	.end().find('button[type=reset]').on(ace.click_event, function(){
				$('#user-profile-3 input[type=file]').ace_file_input('reset_input');
			})
			.end().find('.date-picker').datepicker().next().on(ace.click_event, function(){
				$(this).prev().focus();
			});*/
		},
};	

$(function(){
	Info.init();
});