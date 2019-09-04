var base = $("#base").attr("href");
var oAdminUserTable = null;

DataStatistic = {
	init:function(){
		this.initTable();
		//this.initData();
		this.bindEvent();
	},
	initTable:function(){
		//档案数
		var totalDoc = null;//总建档数
		var totalDoc0to6 = null;//0-6岁建档数
		var totalDocOver50 = null;//65岁以上健康档案数
		var totalDocGXY = null;//高血压
		var totalDocTNB = null;//2型糖尿病
		var totalDocYCF = null;//孕产妇
		var totalDocZJSB = null;//严重精神障碍
		var totalDocJHB = null;//肺结核
		var totalDocCJR = null;//残疾人
		var totalDocTK = null;//特困
		var totalCommon = null;//普通建档数
		
		//档案更新数
		var uptotalDoc = null;//总建档更新数
		var uptotalDoc0to6 = null;//0-6岁建档更新数
		var uptotalDocOver50 = null;//65岁以上健康档案更新数
		var uptotalDocGXY = null;//高血压更新数
		var uptotalDocTNB = null;//2型糖尿病更新数
		var uptotalDocYCF = null;//孕产妇更新数
		var uptotalDocZJSB = null;//严重精神障碍更新数
		var uptotalDocJHB = null;//肺结核更新数
		var uptotalDocCJR = null;//残疾人更新数
		var uptotalDocTK = null;//特困更新数
		var uptotalCommon = null;//普通建档更新数
		
		$.ajax({
             url: base + '/datastatisticlistjson.html',
             //data: reqData,
             type: 'POST',
             dataType: 'json',
             success: function(resp) {
            	 console.log(resp);
            	 //档案数
            	 totalDoc = resp.totalDoc;
            	 totalDoc0to6 = resp.totalDoc0to6;
            	 totalDocOver50 = resp.totalDocOver50;
            	 totalDocGXY = resp.totalDocGXY;
            	 totalDocTNB = resp.totalDocTNB;
            	 totalDocYCF = resp.totalDocYCF;
            	 totalDocZJSB = resp.totalDocZJSB;
            	 totalDocJHB = resp.totalDocJHB;
            	 totalDocCJR = resp.totalDocCJR;
            	 totalDocTK = resp.totalDocTK;
            	 totalCommon = resp.totalCommon;
            	 
            	//档案更新数
            	 uptotalDoc = resp.uptotalDoc;
            	 uptotalDoc0to6 = resp.uptotalDoc0to6;
            	 uptotalDocOver50 = resp.uptotalDocOver50;
            	 uptotalDocGXY = resp.uptotalDocGXY;
            	 uptotalDocTNB = resp.uptotalDocTNB;
            	 uptotalDocYCF = resp.uptotalDocYCF;
            	 uptotalDocZJSB = resp.uptotalDocZJSB;
            	 uptotalDocJHB = resp.uptotalDocJHB;
            	 uptotalDocCJR = resp.uptotalDocCJR;
            	 uptotalDocTK = resp.uptotalDocTK;
            	 uptotalCommon = resp.uptotalCommon;
            	
            	//=================================档案数========================================
            	 
            	//健康档案管理
         		var option = {
         		    title:{
         		        // text:'任务统计',
         		    },
         		    tooltip:{},
         		    legend:{
         		    	x:"center",
         		    	y:"top",
         		    	// backgroundColor:"red",
         		        data:['总建档数','0-6岁','65岁以上','高血压','2型糖尿病','孕产妇','严重精神障碍','肺结核','残疾人','特困','普通建档数']
         		    },
         		    xAxis:{
         		        data:['建档统计']
         		    },
         		    yAxis:{ 
         				splitLine:{  
         					show:false  
         				}//横线
         		    },
         		    series:[
         		    {
         		        name:'总建档数',
         		        type:'bar',
         		        data:[totalDoc],
         		        barWidth : 30,
         		       	itemStyle: {   
         		        //通常情况下：
         		        normal:{  
         		        	//每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
         		            color: function (params){
         		                var colorList = ['rgb(164,205,238)'];
         		                return colorList[params.dataIndex];
         		            }
         		        }
         		    }
         		    },
         		    {
         		    name:'0-6岁',
         		        type:'bar',
         		        data:[totalDoc0to6],
         		        barWidth : 30,
         		       	itemStyle: {   
         		        //通常情况下：
         		        normal:{  
         		        	//每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
         		            color: function (params){
         		                var colorList = ['rgb(42,170,227)'];
         		                return colorList[params.dataIndex];
         		            }
         		        }
         		    }	
         		    },
         		    {
         		    name:'65岁以上',
         		        type:'bar',
         		        data:[totalDocOver50],
         		        barWidth : 30,
         		       	itemStyle: {   
         		        //通常情况下：
         		        normal:{  
         		        	//每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
         		            color: function (params){
         		                var colorList = ['rgb(25,46,94)'];
         		                return colorList[params.dataIndex];
         		            }
         		        }
         		    }
         		    },
         		    {
             		    name:'高血压',
             		        type:'bar',
             		        data:[totalDocGXY],
             		        barWidth : 30,
             		       	itemStyle: {   
             		        //通常情况下：
             		        normal:{  
             		        	//每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
             		            color: function (params){
             		                var colorList = ['rgb(42,170,227)'];
             		                return colorList[params.dataIndex];
             		            }
             		        }
             		    }	
             		    },
             		    {
                 		    name:'2型糖尿病',
                 		        type:'bar',
                 		        data:[totalDocTNB],
                 		        barWidth : 30,
                 		       	itemStyle: {   
                 		        //通常情况下：
                 		        normal:{  
                 		        	//每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
                 		            color: function (params){
                 		                var colorList = ['rgb(42,170,227)'];
                 		                return colorList[params.dataIndex];
                 		            }
                 		        }
                 		    }	
                 		    },
                 		    {
                     		    name:'孕产妇',
                     		        type:'bar',
                     		        data:[totalDocYCF],
                     		        barWidth : 30,
                     		       	itemStyle: {   
                     		        //通常情况下：
                     		        normal:{  
                     		        	//每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
                     		            color: function (params){
                     		                var colorList = ['rgb(42,170,227)'];
                     		                return colorList[params.dataIndex];
                     		            }
                     		        }
                     		    }	
                     		    },
                     		    {
                         		    name:'严重精神障碍',
                         		        type:'bar',
                         		        data:[totalDocZJSB],
                         		        barWidth : 30,
                         		       	itemStyle: {   
                         		        //通常情况下：
                         		        normal:{  
                         		        	//每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
                         		            color: function (params){
                         		                var colorList = ['rgb(42,170,227)'];
                         		                return colorList[params.dataIndex];
                         		            }
                         		        }
                         		    }	
                         		    },
                         		    {
                             		    name:'肺结核',
                             		        type:'bar',
                             		        data:[totalDocJHB],
                             		        barWidth : 30,
                             		       	itemStyle: {   
                             		        //通常情况下：
                             		        normal:{  
                             		        	//每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
                             		            color: function (params){
                             		                var colorList = ['rgb(42,170,227)'];
                             		                return colorList[params.dataIndex];
                             		            }
                             		        }
                             		    }	
                             		    },
                             		    {
                                 		    name:'残疾人',
                                 		        type:'bar',
                                 		        data:[totalDocCJR],
                                 		        barWidth : 30,
                                 		       	itemStyle: {   
                                 		        //通常情况下：
                                 		        normal:{  
                                 		        	//每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
                                 		            color: function (params){
                                 		                var colorList = ['rgb(42,170,227)'];
                                 		                return colorList[params.dataIndex];
                                 		            }
                                 		        }
                                 		    }	
                                 		    },
                                 		    {
                                     		    name:'特困',
                                     		        type:'bar',
                                     		        data:[totalDocTK],
                                     		        barWidth : 30,
                                     		       	itemStyle: {   
                                     		        //通常情况下：
                                     		        normal:{  
                                     		        	//每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
                                     		            color: function (params){
                                     		                var colorList = ['rgb(42,170,227)'];
                                     		                return colorList[params.dataIndex];
                                     		            }
                                     		        }
                                     		    }	
                                     		    },
                                     		    {
                                         		    name:'普通建档数',
                                         		        type:'bar',
                                         		        data:[totalCommon],
                                         		        barWidth : 30,
                                         		       	itemStyle: {   
                                         		        //通常情况下：
                                         		        normal:{  
                                         		        	//每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
                                         		            color: function (params){
                                         		                var colorList = ['rgb(42,170,227)'];
                                         		                return colorList[params.dataIndex];
                                         		            }
                                         		        }
                                         		    }	
                                         		    },
         		    ],
         		    label: {
         				normal: {
         					show: true,
         					position: 'top',
         					textStyle: {
         						color: 'black'
         					}
         				}
         			}
         		};
         		//初始化echarts实例
         		var myChart = echarts.init(document.getElementById('chartmain'));
         		//使用制定的配置项和数据显示图表
         		myChart.setOption(option);
            	
         		//=================================档案数========================================
         		
         		//=================================档案更新数========================================
         		
         		//健康档案更新数
         		var optionup = {
         		    title:{
         		        // text:'任务统计',
         		    },
         		    tooltip:{},
         		    legend:{
         		    	x:"center",
         		    	y:"top",
         		    	// backgroundColor:"red",
         		        data:['总建档更新数','0-6岁更新数','65岁以上更新数','高血压更新数','2型糖尿病更新数','孕产妇更新数','严重精神障碍更新数','肺结核更新数','残疾人更新数','特困更新数','普通建档更新数']
         		    },
         		    xAxis:{
         		        data:['档案更细统计']
         		    },
         		    yAxis:{ 
         				splitLine:{  
         					show:false  
         				}//横线
         		    },
         		    series:[
         		    {
         		        name:'总建档更新数',
         		        type:'bar',
         		        data:[uptotalDoc],
         		        barWidth : 30,
         		       	itemStyle: {   
         		        //通常情况下：
         		        normal:{  
         		        	//每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
         		            color: function (params){
         		                var colorList = ['rgb(164,205,238)'];
         		                return colorList[params.dataIndex];
         		            }
         		        }
         		    }
         		    },
         		    {
         		    name:'0-6岁更新数',
         		        type:'bar',
         		        data:[uptotalDoc0to6],
         		        barWidth : 30,
         		       	itemStyle: {   
         		        //通常情况下：
         		        normal:{  
         		        	//每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
         		            color: function (params){
         		                var colorList = ['rgb(42,170,227)'];
         		                return colorList[params.dataIndex];
         		            }
         		        }
         		    }	
         		    },
         		    {
         		    name:'65岁以上更新数',
         		        type:'bar',
         		        data:[uptotalDocOver50],
         		        barWidth : 30,
         		       	itemStyle: {   
         		        //通常情况下：
         		        normal:{  
         		        	//每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
         		            color: function (params){
         		                var colorList = ['rgb(25,46,94)'];
         		                return colorList[params.dataIndex];
         		            }
         		        }
         		    }
         		    },
         		    {
             		    name:'高血压更新数',
             		        type:'bar',
             		        data:[uptotalDocGXY],
             		        barWidth : 30,
             		       	itemStyle: {   
             		        //通常情况下：
             		        normal:{  
             		        	//每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
             		            color: function (params){
             		                var colorList = ['rgb(42,170,227)'];
             		                return colorList[params.dataIndex];
             		            }
             		        }
             		    }	
             		    },
             		    {
                 		    name:'2型糖尿病更新数',
                 		        type:'bar',
                 		        data:[uptotalDocTNB],
                 		        barWidth : 30,
                 		       	itemStyle: {   
                 		        //通常情况下：
                 		        normal:{  
                 		        	//每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
                 		            color: function (params){
                 		                var colorList = ['rgb(42,170,227)'];
                 		                return colorList[params.dataIndex];
                 		            }
                 		        }
                 		    }	
                 		    },
                 		    {
                     		    name:'孕产妇更新数',
                     		        type:'bar',
                     		        data:[uptotalDocYCF],
                     		        barWidth : 30,
                     		       	itemStyle: {   
                     		        //通常情况下：
                     		        normal:{  
                     		        	//每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
                     		            color: function (params){
                     		                var colorList = ['rgb(42,170,227)'];
                     		                return colorList[params.dataIndex];
                     		            }
                     		        }
                     		    }	
                     		    },
                     		    {
                         		    name:'严重精神障碍更新数',
                         		        type:'bar',
                         		        data:[uptotalDocZJSB],
                         		        barWidth : 30,
                         		       	itemStyle: {   
                         		        //通常情况下：
                         		        normal:{  
                         		        	//每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
                         		            color: function (params){
                         		                var colorList = ['rgb(42,170,227)'];
                         		                return colorList[params.dataIndex];
                         		            }
                         		        }
                         		    }	
                         		    },
                         		    {
                             		    name:'肺结核更新数',
                             		        type:'bar',
                             		        data:[uptotalDocJHB],
                             		        barWidth : 30,
                             		       	itemStyle: {   
                             		        //通常情况下：
                             		        normal:{  
                             		        	//每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
                             		            color: function (params){
                             		                var colorList = ['rgb(42,170,227)'];
                             		                return colorList[params.dataIndex];
                             		            }
                             		        }
                             		    }	
                             		    },
                             		    {
                                 		    name:'残疾人更新数',
                                 		        type:'bar',
                                 		        data:[uptotalDocCJR],
                                 		        barWidth : 30,
                                 		       	itemStyle: {   
                                 		        //通常情况下：
                                 		        normal:{  
                                 		        	//每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
                                 		            color: function (params){
                                 		                var colorList = ['rgb(42,170,227)'];
                                 		                return colorList[params.dataIndex];
                                 		            }
                                 		        }
                                 		    }	
                                 		    },
                                 		    {
                                     		    name:'特困更新数',
                                     		        type:'bar',
                                     		        data:[uptotalDocTK],
                                     		        barWidth : 30,
                                     		       	itemStyle: {   
                                     		        //通常情况下：
                                     		        normal:{  
                                     		        	//每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
                                     		            color: function (params){
                                     		                var colorList = ['rgb(42,170,227)'];
                                     		                return colorList[params.dataIndex];
                                     		            }
                                     		        }
                                     		    }	
                                     		    },
                                     		    {
                                         		    name:'普通建档更新数',
                                         		        type:'bar',
                                         		        data:[uptotalCommon],
                                         		        barWidth : 30,
                                         		       	itemStyle: {   
                                         		        //通常情况下：
                                         		        normal:{  
                                         		        	//每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
                                         		            color: function (params){
                                         		                var colorList = ['rgb(42,170,227)'];
                                         		                return colorList[params.dataIndex];
                                         		            }
                                         		        }
                                         		    }	
                                         		    },
         		    ],
         		    label: {
         				normal: {
         					show: true,
         					position: 'top',
         					textStyle: {
         						color: 'black'
         					}
         				}
         			}
         		};
         		//初始化echarts实例
         		var myChartup = echarts.init(document.getElementById('chartmainup'));
         		//使用制定的配置项和数据显示图表
         		myChartup.setOption(optionup);
         		
         		//=================================档案更新数========================================
         		
         		
            	 
             }
         }); 
		

		//档案更新
		
		$.ajax({
            url: base + '/datastatisticlistjson.html',
            //data: reqData,
            type: 'POST',
            dataType: 'json',
            success: function(resp) {
           	 console.log(resp);
           	 totalDoc = resp.totalDoc;
           	 totalDoc0to6 = resp.totalDoc0to6;
           	 totalDocOver50 = resp.totalDocOver50;
           	 totalDocGXY = resp.totalDocGXY;
           	 totalDocTNB = resp.totalDocTNB;
           	 totalDocYCF = resp.totalDocYCF;
           	 totalDocZJSB = resp.totalDocZJSB;
           	 totalDocJHB = resp.totalDocJHB;
           	 totalDocCJR = resp.totalDocCJR;
           	 totalDocTK = resp.totalDocTK;
           	 totalCommon = resp.totalCommon;
           	
            }
        }); 
	
		
		
		
		
	},
	bindEvent:function(){
		
		
		
	},
	rebindEvent:function(){
		
		
		
		
	},
	
};

$(function(){ 
	DataStatistic.init();
});