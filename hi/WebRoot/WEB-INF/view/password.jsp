<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
</head>
<body>

<div id="updatepass" title="修改密码" class="easyui-dialog" closed="true" style="padding-top:10px;padding-left:10px;width:200px;height:430" iconcls="icon-edit_pencil">
<form id="user_password_form" method="post" modelAttribute="voModel">
<div class="fitem" style="margin-top:15px">
          
      <div class="fitem" style="margin-top:15px">
        <label>账号:</label>
         <input type="text" id ="username" name="username" />
        </div>
        </div>
    <div class="fitem">
        <label>旧密码:</label>
        <input type="password" id="oldPasswordInput" name="oldPassword" />
    </div>
    <div class="fitem">
        <label>新密码:</label>
        <input type="password" id="newPasswordInput" name="newPassword" />
    </div>
    <div class="fitem">
        <label>确认密码:</label>
        <input type="password" id="newPasswordConfirmInput" name="newPasswordConfirm" />
    </div>
    

    
    <div class="fitem">
   
    	
    </div>
</form>
</div>

<script type="text/javascript">
function openEditWin() {
	$('#username').attr("readonly", "readonly");     //取值到文本框 

	$('#user_password_form').form('clear');
	
	var row = $('#select_dg').datagrid('getSelected');
	if (! row) {
		alert('请选择用户'); 
		return;
	}
	
	$('#user_password_form').form('load', row);	

	 $('#updatepass').dialog('open'); 
	 url = UrlConfig.SysUserUpdate;
	
}
$('#updatepass').dialog({
	title: '账号信息',
    width: 250,
    height: 350,
    closed: true,
    cache: false,
    modal: true,
    buttons: [{
    	text: '保存',
    	handler:function savePassword() {
    			$('#user_password_form').form('submit', {
		url: '<%=request.getContextPath() %>/updateAdmin',
		onSubmit: function(){
			if ($.trim($('#oldPasswordInput').val()).length == 0) {
				$.messager.show({ title: '操作结果', msg: '请输入旧密码' });
				return false;
			}
			
			if ($.trim($('#newPasswordInput').val()).length == 0) {
				$.messager.show({ title: '操作结果', msg: '请输入新密码' });
				return false;
			}

		/* 	if ($.trim($('#newPasswordInput').val()) == $.trim($('#oldPasswordInput').val())) {
				$.messager.show({ title: '操作结果', msg: '新密码和旧密码不能相同' });
				return false;
			} */
			
			if ($.trim($('#newPasswordConfirmInput').val()).length == 0) {
				$.messager.show({ title: '操作结果', msg: '请输入确认新密码' });
				return false;
			}
			
			if ($.trim($('#newPasswordInput').val()) != $.trim($('#newPasswordConfirmInput').val())) {
				$.messager.show({ title: '操作结果', msg: '新密码和确认密码输入不同' });
				return false;
			}
			
			return true;
			
        },
        success: function(result){
        	result = eval('(' + result + ')');
        	var msg = result.msg;
            if (result.successful) {
				msg = '操作成功';
				$('#select_dg').datagrid('reload');
				$('#updatepass').dialog('close');
			
            }
               
            $.messager.show({ title: '操作结果', msg: msg });
            clearPasswordForm();
        
        }
	});
	}
},{
	text: '取消',
	handler: function() {
		$('#updatepass').dialog('close');
    	$('#user_password_form').form('clear');
	}
}]
});

function clearPasswordForm() {
	$('#oldPasswordInput').val('');
	$('#newPasswordInput').val('');
	$('#newPasswordConfirmInput').val('');
}
</script>
</body>
</html>