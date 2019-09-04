var base = $("#base").attr("href");
var curOrgid = 0;
var curPrsnId = 0;
FollowupPagList = {
	init:function(){
		this.initTable();
		this.bindEvent();
	},
	initTable:function(){
		oFollowupPageListTable = $('#followuppage-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/mgr_followuppagelist.html",
			"columnDefs" :[
						],
			"oLanguage": {
				"sLengthMenu": "每页显示 _MENU_ 条记录",
				"sZeroRecords": "抱歉， 没有找到",
				"sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
				"sInfoEmpty": "没有数据",
				"sInfoFiltered": "(从 _MAX_ 条数据中检索)",
				"oPaginate": {  
				    "sFirst": "首页",  
				    "sPrevious": "前一页",
				    "sNext": "后一页",  
				    "sLast": "尾页"  
				}, 
				"sZeroRecords": "没有检索到数据"  
			},
			"aLengthMenu": [[15, 50, 100], ["15", "50", "100"]],
			"aoColumns": [
			          	{ "sData": "ID", "bSortable": false, "bVisible": true},
			          	{ "sName": "Code", "bSortable": false, "bVisible": true},
						{ "sName": "随访表格", "bSortable": false, "bVisible": true}
			]
			
		});
	},
	bindEvent:function(){
		$('#followuppage-table').on('xhr.dt', function(e, settings, json, xhr) {
            setTimeout(FollowupPagList.rebindEvent, 300);
        });
		
		$('#followuppage-table tbody').on('click','tr', function () {
			var data = oFollowupPageListTable.row(this).data();
			var prsnId = $("#prsnId").attr("value");
			window.location.href=base+'/followuppage.html?tableCode='+data[1]+'&prsnId='+prsnId+'&activepage=followup';
		});
	
	}
};

$(function(){
	FollowupPagList.init();
});