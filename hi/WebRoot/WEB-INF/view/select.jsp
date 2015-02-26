<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>select</title>

<script  type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-easyui-1.3.5/jquery.min.js"></script>
<script  type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>

<script  type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/jquery-easyui-1.3.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/jquery-easyui-1.3.5/themes/icon.css">
<script src="<%=request.getContextPath() %>/static/images/FusionCharts.js" type="text/javascript"></script><!-- 图表的JS -->

</head>
<body>

<div  id="search" style="padding:50px 50px 50px 30px;color:red;width:350px;height:400px "  class="easyui-dialog" title="搜索信息" closed="true">  
   <span style="line-height:50px"> 职工号: <input class="easyui-validatebox" type="text" name="user_id" id="user_id"  ">  
   <br><span style="margin-top:150px">名&nbsp;&nbsp;字: <input class="easyui-validatebox" type="text" name="user_name" id="user_name" ">
   </span></span> 
<br><span style="float:left;margin-top:20px;margin-bottom:20px;">
年&nbsp;&nbsp;龄: <input class="easyui-validatebox" style="width:40px" type="text" name="user_age1" id="user_age1" value="" onclick="this.value='' ">
一
<input class="easyui-validatebox" style="width:40px" type="text" name="user_age2" id="user_age2" value="" onclick="this.value='' "> * (区间查询)</span>
<br> <div id="check_button"  style="z-index:100;position:absolute;left:50px;top:250px;width:80px;height:30px;background-color:red;color:white;cursor:pointer;background:url(<%=request.getContextPath() %>/static/images/search.gif);"
onclick="check_user()">
   </div>

</div> 
<table id="select_dg"  style="width:auto; " title="人员列表" iconCls="icon-save"  
       rownumbers="true" pagination="true" singleSelect="true"> <!-- 在datagrid中设置了pagination为true，就会自己带上分页,
       分页时向后台传去两个参数，一个就是当前页数另一个就是每页显示行数；fitcolumns：自适应列宽；singleselected:单选。
        -->
</table>
<!--hehe！  <div id="user_list_dg_toolbar" style="float:left" >
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openNewWin()">添加</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="openEditWin()">修改</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteUser()">删除</a>
    
</div>-->


<div id="user_save_dialog" >
	<div class="inner-wrapper" style="padding:15px 15px;">
	<div style="color:red;">工号唯一，不能重复使用</div>
    <form id="user_info_form" method="post">
        <div class="fitem" style="margin-top:15px">
            <label>工号:</label>
            <input type="text" id ="id" name="id" />
        </div>
        <div class="fitem" style="margin-top:15px">
            <label>姓名:</label>
            <input type="text" id ="name" name="name" />
        </div>
          <div class="fitem" style="margin-top:15px">
            <label>年龄:</label>
            <input type="text" name="age" />
        </div>
          <div class="fitem" style="margin-top:15px">
            <label>邮址:</label>
            <input type="text" name="email" />
        </div>
          <div class="fitem" style="margin-top:15px">
            <label>电话:</label>
            <input type="text" name="telephone" />
        </div>
            <div class="fitem" style="margin-top:15px">
            <label>住址:</label>
            <input type="text" name="address" />
        </div>
       
 
    </form>
    </div>
</div>
<script type="text/javascript">



var UrlConfig = {
		SelectfinAll: '<%=request.getContextPath() %>/select',
		SysUserAdd: '<%=request.getContextPath() %>/add',
		SysUserUpdate:'<%=request.getContextPath() %>/update',
		SysUserDelete: '<%=request.getContextPath() %>/delete'
	
			
	};


$('#select_dg').datagrid({ 
	fit:true ,
    'url' : UrlConfig.SelectfinAll ,  //没指定url的，要刷新datagrid都会走 SelectfinAll下的/select
    toolbar:[{//正上方工具栏  
        text:'查询员工信息',  
        iconCls:'icon-search',  
        handler:function(){  
        openSearch();  
        }  
     },'-',{//正上方工具栏  
    	            text:'添加新员工',  
    	           iconCls:'icon-add',  
    	           handler:function(){  
                    //点击工具栏运行的js方法  
                   openNewWin();  
    	           }  
    	        },'-',{  
    	               text:'修改员工信息',  
    	               iconCls:'icon-edit',  
    	               handler:function(){  
    	            	   openEditWin();  
                   }  
    	        }
    	        ,'-',{  
 	               text:'删除员工信息',  
 	               iconCls:'icon-remove',  
 	               handler:function(){  
 	                 deleteUser();
 	                 }  
                }  
    	        ,'-',{  
 	               text:'导出EXCEL',  
 	               iconCls:'icon-undo',  
 	               handler:function(){  
 	            	 download();  
               		 }  
    	        }  
    	        ,'-',{  
  	               text:'显示图表',  
  	               iconCls:'icon-undo',  
  	               handler:function(){  
  	            	 doChart();  
                		 }  
     	        }  
 	        ] ,
    'columns' :[[     
        {field:'id',title:'职工号',width:100,align:'center'},     
        {field:'name',title:'名字',width:100,align:'center'},  
        {field:'age',title:'年龄',width:100,align:'center'},  
        {field:'email',title:'邮电',width:180,align:'center'}, 
        {field:'telephone',title:'电话',width:150,align:'center'}, 
        {field:'address',title:'住址',width:300,align:'center'}
    ]]  
});  

//easyui右下角的操作提醒弹出框
$('#user_save_dialog').dialog({
	title: '职工信息',
    width: 250,
    height: 350,
    closed: true,
    cache: false,
    modal: true,
    buttons: [{
    	text: '保存',
    	handler: function() {
    		$('#user_info_form').form('submit',{
    	        url: url,
    	        onSubmit: function(){
    	            var success = $(this).form('validate');
    	            if (! success) {
    	            	return false;
    	            }
    	            
    	            return true;
    	        },
    	        success: function(result){
    	        	result = eval('(' + result + ')');
    	            if (! result.successful) {
    	            	$.messager.show({ title: '操作结果', msg: result.msg });
    	            } else {
    	            	$.messager.show({ title: '操作结果', msg: '操作成功' });
    	                $('#select_dg').datagrid('reload');    // reload the user data
    	            	$('#user_save_dialog').dialog('close');   // close the dialog
    	                $('.validatebox-tip').remove();
    	            	$('#user_info_form').form('clear');
    	            }
    	        }
    	    });
    	}
    },{
    	text: '取消',
    	handler: function() {
    		$('#user_save_dialog').dialog('close');
        	$('#user_info_form').form('clear');
    	}
    }]
});

//add
function openNewWin() {
	$('#id').attr("readonly", false);
	$('#user_save_dialog').dialog('open');
	url = UrlConfig.SysUserAdd;
}


//edit,update
function openEditWin() {
	$('#id').attr("readonly", "readonly");

	$('#user_info_form').form('clear');
	
	var row = $('#select_dg').datagrid('getSelected');
	if (! row) {
		alert('请选择用户'); 
		return;
	}
	
	$('#user_info_form').form('load', row);	

	
	$('#user_save_dialog').dialog('open');
	
	url = UrlConfig.SysUserUpdate;
}

//delete
function deleteUser() {
	var row = $('#select_dg').datagrid('getSelected');
    if (row){
        $.messager.confirm('Confirm','请确认是否删除该用户?',function(r){
            if (r){
                $.post(UrlConfig.SysUserDelete, {id:row.id}, function(result){
                    $.messager.show({ title: '操作结果', msg: '操作成功' });
                	$('#select_dg').datagrid('reload');
                },'json');
            }
        });
    } else {
    	alert('请选择用户');
    }
    
    
}




//设置分页控件  
var p = $('#select_dg').datagrid('getPager');  
p.pagination({  
   // pageSize: 100,//每页显示的记录条数，默认为10  
  //  pageList: [5, 10, 15],//可以设置每页记录条数的列表    //在控制层参数已初始化
    beforePageText: '第',//页数文本框前显示的汉字  
    afterPageText: '页    共 {pages} 页',  
    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'  

}); 

/**var param={
		name: "",
		sex : "",
		addr : {
			sheng : "",
			shi : ""
		}
};

var params=new Object();
  **/


//select 
function openSearch(){
	  $('#user_info_form').form('clear');
		$('#search').dialog('open');
  }
function check_user(){
	
		var userId=  $('#user_id').val();
		var userName= $('#user_name').val();
		var userAge1=$('#user_age1').val();
		var userAge2=$('#user_age2').val();
		
		if(userId == null || userId == ""){
			userId=-1;
		}
		$('#select_dg').datagrid('load',{
			'id' : userId,
			'name' : userName,
			'userAge1' : userAge1,
			'userAge2' : userAge2
		});

}

  

	//导出EXCEL
  function download(){
	    var url="<%=request.getContextPath() %>/download_user.do";
	    window.open(url);
	}
  
  
  
//搜索单个图表显示图表
  function doChart(){
	<%--   var url="<%=request.getContextPath() %>/installCountChart.do";
	 open(url);
	  //  alert("999");
  	showChart(); --%>
  
  	//return false;
 //	var formParamsString=$("#formQuery").serialize();
 // 	var formParamsJson = paramString2json(decodeURI(formParamsString));
  	//Ajax请求生成xml
  	$.ajax({
  		type : "post",
  		url : "installCountChart.do",
  //		data : formParamsJson,  //多个参数用&连接 
  	//	dataType : "json", 			//返回类型 可选 XML JSON JSONP SCRIPT HTML TEXT等 
  		success : function(result) {
  			if(result.success){
  				showChart(result.size);
  			}else{
  				$.messager.alert('系统提示','生成图表失败！','error');
  			}
  		},
  		error : function(request) {
  			$.messager.alert('系统提示','异步提交数据发生错误！','error');
  		}
  	});
  	return false;
  } 
//显示图表的div
  function showChart(size){
	//  alert("9999");
  	var s = parseInt(size);
  	if(s==null || s=='undefined'){
  		s=1000;
  	}else if(s<20){
  		s=1000;
  	}else{
  		s=s*50;
  	}
  	 
  	initChart(s);
 
  	$('#divChart').dialog({
  		title:"年龄直方图",
  		modal : true,
  		resizable : true,
  		cache: false,
  		fit:true,
  		closed: true,
  		buttons : [{
  			text : '关闭',
  			iconCls : 'icon-cancel',
  			handler : function() {
  				$('#divChart').dialog("close");
  			}
  		} ]
  	});
  	$('#divChart').dialog("open");
  	$('#divChart').dialog("resize",{
  		width: 770,
  		height: 300
  	});
  }
  
//图表显示结束
//图表的flash文件位置 
function initChart(size){
	$('#chartdiv').html('');

	var myChart = new FusionCharts("<%=request.getContextPath() %>/static/images/MSColumnLine3D.swf", "mychart1", size, "500");
	
	myChart.setDataURL("<%=request.getContextPath()%>/installCountByIns.xml");
	myChart.render("chartdiv");
	//alert("9999999988");
}
</script>
<!-- 图表层 -->
<div id="divChart" maximizable="true"  style="padding-top:10px;padding-left:10px;width:820px;height:500px"  class="easyui-dialog"  closed="true"  iconcls="icon-edit_add">

	<div id="chartdiv"  align="center" style="overflow: scroll-x;"></div>
</div>


</body>
</html>