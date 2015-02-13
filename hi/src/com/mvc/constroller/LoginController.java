package com.mvc.constroller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.mvc.domain.Admin;
import com.mvc.service.SelectService;
import com.mvc.service.SysMenuService;
import com.mvc.util.EasyuiTreeNode;
import com.mvc.util.WebFrontHelper;
@Controller
public class LoginController {

	   @RequestMapping(value = "/login", method = RequestMethod.GET)  
	    public String registPost() {  
	    	
	        return "/login";  
			
	    }
	   
	   
	   @Resource
		private SysMenuService sysMenuService;
	   @Resource
	   private SelectService selectService;
			
			@RequestMapping("/login")
			
				
			public String login(@RequestParam(value = "username", required=true)String username,
					@RequestParam(value = "password", required=true)String password,
					HttpSession session,Admin admin,HttpServletRequest request,HttpServletResponse response,Model model
					
					) {
		
			
				String code = (String)session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
				Admin user = this.selectService.loadUserByUsernameAndPassword(username,password);	
				String parm = (String)request.getParameter("kaptcha");
			
				if (user!=null) {
					if(code.equals(parm)){
						session.setAttribute("current", user.getUsername());
					//	public String loginPage(HttpSession session,Model model) {
						//原来加载树在这。
							//}

					return "redirect:/main";
					}else{
						request.setAttribute("msg","验证码错误");
						return "login";
					}
				} else {
					request.setAttribute("msg","登录失败");
					return "login";
				}
				
			
				
			}
			
			@RequestMapping("/logout")
			public String logout(HttpSession session) {
				if (session != null) {
					session.invalidate();
				
				}
				
				return "login";
			}
}
