var base = $("#base").attr("href");
var oRoleMenuTable = null;
var aceMenutree = null;

RoleMenu = {
	init:function(){
		this.initTable();
		this.bindEvent();
	},
	initTable:function(){
		oRoleMenuTable = $('#rolelist-table').DataTable({
			"bServerSide": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
			"sAjaxSource": base + "/rolelistjson.html",
			"columnDefs" :[],
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
			"aoColumns": [
			          	{ "sData": "ID", "bSortable": false, "bVisible": true},
						{ "sName": "CODE", "bSortable": false, "bVisible": false},
						{ "sName": "名称", "bSortable": false, "bVisible": true,"bVisible": true},
						{ "sName": "类型", "bSortable": false, "bVisible": true,"bVisible": true}
				],
			"fnInitComplete": function() {
				$("#rolelist-table_filter label").detach();						
				var condition = "";
				condition += "<label>状态:<select id=\"RoleMenu_status\" name=\"RoleMenu_status\" style=\"width:100px;\">";//第二种
				condition += "<option value=\"\">全部</option>";
				condition += '<option value="Y">有效</option>';
				condition += '<option value="N">无效</option>';
				condition += "</select></label>";
				/*condition += "<label>昵称：<input type=\"text\" placeholder=\"关键字\" id=\"qh_nickname\" /></label>";*/
				condition += " <label><input type=\"button\" id=\"btnSearch\" value=\"查询\"></label> ";
				condition += " <label><input type=\"button\" id=\"btnempty\" value=\"重置\"></label> ";
				
				$("#rolelist-table_filter").append(condition);
				//查询按钮  
				$("#btnSearch").click(function(){
					var u = base + '/rolelistjson.html?a=a';
					oRoleMenuTable.ajax.url(u).load();
				});
				
				$("#btnempty").click(function(){
					var u = base + '/rolelistjson.html';
					oRoleMenuTable.ajax.url(u).load();
				});
			}
		});
	},
	bindEvent:function(){
		/*$('#rolelist-table').on('xhr.dt', function(e, settings, json, xhr) {
            setTimeout(RoleMenu.rebindEvent, 300);
        });*/
		
		$('#rolelist-table tbody').on('click','tr', function () {
			var data = oRoleMenuTable.row(this).data();			
			$.ajax({
				url: base + '/rolemenujson.html?roleid=' + data[0],
				type: 'GET',
				dataType: 'json',
				success: function (resp) {
					var tree_data = {};
					for(var i=0;i<resp.data.length;i++){
						var f = {};
						f.name = resp.data[i].name;
						if(resp.data[i].leaf == "Y")
							f.type = "item";
						else{
							f.type = "folder";
						}
						tree_data[resp.data[i].name] = f;
						if(resp.data[i].leaf == "N"){
							var ctree_data = {};
							var subs = resp.data[i].subs;
							for(var j=0;j<subs.length;j++){
								var f2 = {};
								f2.name = subs[j].name;
								if(subs[j].leaf == "Y")
									f2.type = "item";
								else{
									f2.type = "folder";
								}
								ctree_data[subs[j].name] = f2;
							}
							var ctree_data2 = {};
							ctree_data2['children'] = ctree_data;
							f['additionalParameters'] = ctree_data2;
						}
					}
					var treeDataSource = new DataSourceTree({data: tree_data});
					$("#roleMenuTreeWrapper>div").remove();
					var html = '<div id="roleMenuTree" class="tree"></div>';
					$("#roleMenuTreeWrapper").append(html);
					$('#roleMenuTree').ace_tree({
						dataSource: treeDataSource ,
						multiSelect:true,
						loadingHTML:'<div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div>',
						'open-icon' : 'icon-minus',
						'close-icon' : 'icon-plus',
						'selectable' : false,
						'selected-icon' : 'icon-ok',
						'unselected-icon' : 'icon-remove',
						'cacheItems' : false,
		                'folderSelect' : false
					});
				}
			});
		});
	},
	rebindEvent:function(){}
};

$(function(){ 
	RoleMenu.init();
});