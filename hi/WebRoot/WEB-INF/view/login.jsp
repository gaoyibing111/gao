<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link type="favicon" rel="shortcut icon" href="<%=request.getContextPath() %>/favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Login</title>

<script  type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-easyui-1.3.5/jquery.min.js"></script>
<script  type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>

<script  type="text/javascript" src="<%=request.getContextPath() %>/static/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/jquery-easyui-1.3.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/jquery-easyui-1.3.5/themes/icon.css">
<script type="text/javascript">

 
function userLogin(){   /**用户登录，其中需要判断是否选择记住密码**/  
    //简单验证一下  
    var userName = document.getElementById("username").value;  
    if(userName == ''){  
      //  alert("请输入用户名。");  
        var name = document.getElementById("1"); 
  	        name.style.display="block";
  	  	 return;
       
    } 
    if(userName != ''){  
        //  alert("请输入用户名。");  
          var name = document.getElementById("1"); 
    	        name.style.display="none"; 
         } 
    //判断username结束
    var userPass = document.getElementById("password").value;  
    if(userPass == ''){  
    	  var name = document.getElementById("2"); 
	        name.style.display="block";
        return;  
    } 
    if(userPass != ''){  
        //  alert("请输入用户名。");  
          var name = document.getElementById("2"); 
    	        name.style.display="none"; 
         } 
    //判断pass结束
    var kaptcha = document.getElementById("kaptcha").value;  
    if(kaptcha == ''){  
    	  var name = document.getElementById("3"); 
	        name.style.display="block";
        return;  
    } 
    if(kaptcha != ''){  
        //  alert("请输入用户名。");  
          var name = document.getElementById("3"); 
    	        name.style.display="none"; 
         } 
    //判断验证码结束
     
    
    document.getElementById('loginform').submit();//提交表单
    
 
    
    
    
}

     </script>
</head>
<body>

<div id="west" style="margin-left:300px;margin-top:100px;float:left;"><font size=14>网站人员管理中心</font></br><h3>该系统是一个采用J2EE和MySQL数据库构建的一个小型企业</br>站程序</h3></div>
<div id="north" style="width:300px;margin:0 auto;float:left;margin-top:100px;margin-left:50px;line-height:40pt">
 <form id="loginform" method="post" action="<%=request.getContextPath() %>/login">
 <div>
 <div>
 	用户名：<input id="username" name="username" type="text" style="width:250px;"/><div id="1" style="display: none;color:red;">请输入用户名</div>
 </div>
 <div>
 	密　码：<input id="password" name="password" type="password" style="width:250px;"/><div id="2" style="display: none;color:red;"">请输入密码</div>
 </div>
 <div>       <div class="chknumber">  
           <span>验证码：          
           <input name="kaptcha" type="text" id="kaptcha" maxlength="4" class="chknumber_input" style="width:40px"/>               
            <img src="<%=request.getContextPath() %>/captcha-image" width="55" height="25" id="kaptchaImage"  style="margin-bottom:-6px"/>   
           </span>
          <label style="color:red"> <%if(request.getAttribute("msg")!=null){out.println(request.getAttribute("msg"));}%></label><div id="3" style="display: none;color:red;"">请输入验证码</div>
           <script type="text/javascript">      
            $(function(){           
                $('#kaptchaImage').click(function () {//生成验证码  
                 $(this).hide().attr('src', '<%=request.getContextPath() %>/captcha-image?' + Math.floor(Math.random()*100) ).fadeIn(); })      
                      });   
            </script> 
         </div> 

 </div>
 </div>
 </form><input type="submit" name="Submit" value="提交" onclick="userLogin()" /> 
 
</div>
  
</div>
</body>
</html>