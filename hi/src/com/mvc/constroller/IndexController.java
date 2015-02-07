package com.mvc.constroller;  
import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import org.springframework.stereotype.Controller;  
import org.springframework.ui.ModelMap;  
import org.springframework.web.bind.annotation.PathVariable;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.servlet.ModelAndView;  
@Controller  
public class IndexController implements Controller {  
    public IndexController() {}  
  
    @RequestMapping(value = "/index", method = RequestMethod.GET)  
    public String registPost() {  
        return "/index";  
    }

	@Override
	public Class<? extends Annotation> annotationType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String value() {
		// TODO Auto-generated method stub
		return null;
	}

 
} 