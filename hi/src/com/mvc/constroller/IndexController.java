package com.mvc.constroller;  
import java.lang.annotation.Annotation;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;  
import org.springframework.web.bind.annotation.PathVariable;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;  

import com.google.gson.Gson;
import com.mvc.service.SysMenuService;
import com.mvc.util.EasyuiTreeNode;

@Controller  
public class IndexController {  
    public IndexController() {}  
  

    @RequestMapping(value = "/index", method = RequestMethod.GET)  
    public String registPost() {  
        return "/index";  
    }


 
} 