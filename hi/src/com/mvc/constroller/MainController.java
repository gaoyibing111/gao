package com.mvc.constroller;  
import java.lang.annotation.Annotation;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;  
import org.springframework.web.bind.annotation.PathVariable;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;  

import com.google.gson.Gson;
import com.mvc.domain.Admin;
import com.mvc.service.SelectService;
import com.mvc.service.SysMenuService;
import com.mvc.util.EasyuiTreeNode;
import com.mvc.util.WebFrontHelper;

@Controller  
public class MainController{  
    public MainController() {}  
    @Resource
 		private SysMenuService sysMenuService;

    @RequestMapping(value = "/main", method = {RequestMethod.GET,RequestMethod.POST})  
    
    public String registPostsss(Model model){  
    	
    	
    	  WebFrontHelper webtree = new WebFrontHelper();
    		EasyuiTreeNode node = webtree.buildTreeForEasyuiTree(this.sysMenuService.findAll());	
    		
    		
    		model.addAttribute("treeJson", new Gson().toJson(node.getChildren()));
    	
        return "/main";  
		
    }


		
  
   
	
 
} 