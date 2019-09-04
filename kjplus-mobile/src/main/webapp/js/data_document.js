var base = $("#base").attr("href");
/**
 * 
 * 建档数据处理的js
 */
$(function() {	
	// 点击保存事件
	$("#save").on("click", function() {

		var userName = $("#user_name").val();// 获取姓名
		var sex = 1;// 性别
		var mobile = $("#phoneNumber").val();// 手机号
		var idNumber = $("#idNumber").val();// 身份证号
		var familyAddr = $("#family_addr").val();// 家庭地址
		var jsons = [];
		var pos = null;
		var refid = null;
		$("[name^='tbinpr']").each(function() {
			pos = $(this).attr("name").substring("tbinpr".length);
			refid = $(this).attr("data-refid");
			vl = $(this).val();
			console.log(refid);
			var r = {};
			r.pos = pos;
			r.refid = refid;
			r.vl = vl;
			console.log(r);
			jsons.push(r);
		});
		
	
		//姓名json
		var namejson = [];
		var name = {};
		name.inputVl = $('#user_name').val();
		namejson.push(name);
		console.log("name_json:"+JSON.stringify(namejson));

		//性别json
		var sexjson = [];
		var sex = {};
		sex.pos = $('.alert .alert_content div').find('span').attr('data-pos');
		sex.refid = $('.alert .alert_content div').find('span').attr('data-refid');
		sex.inputVl = $('.alert .alert_content div').find('span').html();
		sexjson.push(sex);
		
		//生日json
		var birthdayjson = [];
		var birthday = {};
		birthday.pos = $('#birthday').attr('data-pos');
		birthday.refid = $('#birthday').attr('data-refid');
		birthday.inputVl = $('#birthday').val();
		birthdayjson.push(birthday);
		
		//手机号json
		var phoneNumberjson = [];
		var phoneNumber = {};
		phoneNumber.pos = $('#phoneNumber').attr('data-pos');
		phoneNumber.refid = $('#phoneNumber').attr('data-refid');
		phoneNumber.inputVl = $('#phoneNumber').val();
		phoneNumberjson.push(phoneNumber);
		
		//身份证号json
		var idNumberjson = [];
		var idNumber = {};
		idNumber.pos = $('#idNumber').attr('data-pos');
		idNumber.refid = $('#idNumber').attr('data-refid');
		idNumber.inputVl = $('#idNumber').val();
		idNumberjson.push(idNumber);
		
		//家庭住址json
		var family_addrjson = [];
		var family_addr = {};
		family_addr.pos = $('#family_addr').attr('data-pos');
		family_addr.refid = $('#family_addr').attr('data-refid');
		family_addr.inputVl = $('#family_addr').val();
		family_addrjson.push(family_addr);
		
		//用户信息 json
		var userjson = [];
		var name = {};//放入用户名字
		name.pos = $('#user_name').attr('data-pos');
		name.inputVl = $('#user_name').val();
		userjson.push(name);
		var sex = {};//放入用户性别
		sex.pos = $('.alert .alert_content div').find('span').attr('data-pos');
		sex.refid = $('.alert .alert_content div').find('span').attr('data-refid');
		userjson.push(sex);
		var birthday = {};//放入用户生日
		birthday.pos = $('#birthday').val();
		userjson.push(birthday);
		var phoneNumber = {};//放入用户手机号
		phoneNumber.pos = $('#phoneNumber').val();
		userjson.push(phoneNumber);
		var idNumber = {};//放入用户身份证号
		idNumber.pos = $('#idNumber').val();
		userjson.push(idNumber);
		//家庭住址json
		var family_addr = {};//放入用户家庭住址
		family_addr.pos = $('#family_addr').val();
		userjson.push(family_addr);
			
		console.log("userjson:"+JSON.stringify(userjson));

		
		var reqData = {};
		reqData.linejson = JSON.stringify(jsons);
		reqData.namejson = JSON.stringify(namejson);
		reqData.sexjson = JSON.stringify(sexjson);
		reqData.birthdayjson = JSON.stringify(birthdayjson);
		reqData.phoneNumberjson = JSON.stringify(phoneNumberjson);
		reqData.idNumberjson = JSON.stringify(idNumberjson);
		reqData.family_addrjson = JSON.stringify(family_addrjson);
		reqData.userjson = JSON.stringify(userjson);
		console.log(reqData);

		$.ajax({
			url : base+'/createdocjson.html',
			type : 'POST',
			data : reqData,
			dataType : 'json',
			success : function(resp) {
				console.log(resp);
//				alert(resp.message);
				$('.alert_2 .alert_content p').html(resp.message);
				$('.alert_2').show();
			}
		});
	});
});

