<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>职员管理</title>

<script  type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-easyui-1.3.5/jquery.min.js"></script>
<script  type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>

<script  type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/jquery-easyui-1.3.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/jquery-easyui-1.3.5/themes/icon.css">

<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/tabs.js"></script>
<script type="text/javascript">
/*  $(function(){
	 $("#help_tree a").click(function(){
	        var title=$(this).text();
	        var url=$(this).attr("rel");
	        var icon=$(this).attr("icon")
	        OpenTab(title,url,icon);
	        return false;    //使超链接的单击事件失效
	    });
}); 
  */



var treeData = <%=request.getAttribute("treeJson") %>;

$(function(){
	$('#help_tree').tree({
		checkbox: false,
		animate:true,
		lines:true,
		data: treeData,
		
		onClick:function(node) {
			
			if (node.attributes && node.attributes.menuUrl) {
				
				
				<%-- 			 $('#tabs').panel('refresh','<%=request.getContextPath() %>' + node.attributes.menuUrl);
			
			  			$('body').layout('panel', 'center').panel('setTitle', node.text);
			  		
			  		--%>			
		 				
					//	$('#help_tree li').click(function(){
			        var title=node.text;
			        var url='<%=request.getContextPath() %>' + node.attributes.menuUrl;
			        var icon=$(this).attr("icon");
			        if(node.attributes.menuUrl==0){
			        	//alert("0");
			        	return true;
			        }else{
			        OpenTab(title,url,icon);
			        }
			        
			        
			        return false;    //使超链接的单击事件失效
			        
			        
			        
			        
			//   });
			
			} 
		},
		formatter: function(node) {
			return node.text;
		}
	});
/* 	$('#tabs').panel({
				fit:true,
				border:false,
				noheader:false,
				
			
	}); */
});
</script>

</head>
<body class="easyui-layout" >
<div region="north" style="height:80px;">
    <!--页面头部--><span style="font-size:14px;width:15px">欢迎你：${sessionScope.current}<a href="<%=request.getContextPath() %>/logout">&nbsp&nbsp退出</a></span>
    <h1 style="margin-left:800px;margin-top:20px;font-size:20px">职员管理系统 V1.0</h1>
</div>
<div data-options="region:'west',split:true,title:'导航窗口',iconCls:'icon-help'" style="width:248px;padding:5px; text-align:left;">

	<ul id="help_tree" class="easyui-tree" data-options="state:closed"></ul>
</div>
<%-- <div region="west" split="true" style="width:220px;" title="导航菜单">
<div id="menu" style="font-size:20px;padding-top:20px;padding-left:40px;line-height:45px"  >
<a  href="#" rel="<%=request.getContextPath() %>/select" >员工信息</a><br />

<a  href="#" rel="<%=request.getContextPath() %>/admin" >账号管理</a><br />

</div>
</div> --%>
<div region="center">

<div id="tabs" class="easyui-tabs" fit="true" border="false" >
        <!--欢迎标签 START-->
        <div title="欢迎使用">
        
            <h1 style="font-size: 24px;margin-top:100px;margin-left:100px;line-height:85px">Hi,Sir.<br>Good morning.<br />Can I help you?</h1>
        </div>
        <!--欢迎标签 END-->
    </div>
</div>

</body>


</html>