package com.mvc.constroller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.domain.SysMenu;
import com.mvc.service.SysMenuService;
import com.mvc.util.Result;
import com.mvc.util.WebFrontHelper;
@Controller

public class SysMenuController {

	  @RequestMapping(value = "/menu", method = RequestMethod.GET)  
	    public String registPost() {  
	        return "/menu";  
	    }

		@Resource
		private SysMenuService sysMenuService;

		@RequestMapping("/menu/list")
		@ResponseBody
		public List<SysMenu> list() {
			WebFrontHelper webtree = new WebFrontHelper();
			SysMenu menu = webtree.buildMenuTree(this.sysMenuService.findAll());	
			return menu.getChildren();
			
		}
		public  List<SysMenu> listParentId(){
			return this.sysMenuService.findAll();
		}
		
//		@RequestMapping(value="/menu" , method=RequestMethod.GET)
//		@ResponseBody
//		public List<EasyuiTreeNode> menu(Model model) {
//	
//			EasyuiTreeNode node = WebFrontHelper.buildTreeForEasyuiTree(this.sysMenuService.findAll());	
//		//	model.addAttribute("treeJson", new Gson().toJson(node.getChildren()));
//			return node.getChildren();
//		}
		
		
/*	@RequestMapping(value="/login", method=RequestMethod.GET)
		public String loginPage(HttpSession session,Model model) {
				
			EasyuiTreeNode node = WebFrontHelper.buildTreeForEasyuiTree(this.sysMenuService.findAll());	
			
			model.addAttribute("treeJson", new Gson().toJson(node.getChildren()));

			return "index";
		}	*/
		
		@RequestMapping(value = "/menu/add", method = RequestMethod.POST)
		@ResponseBody
		public Result add(SysMenu menu) {
			try{
			this.sysMenuService.save(menu);
			}catch(Exception e){
				e.printStackTrace();
				
			}
			return new Result();
		}
		
		
	
		
		
		
		
		
		
}
