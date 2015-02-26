package com.mvc.constroller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.mvc.domain.Admin;
import com.mvc.domain.PageBean;
import com.mvc.domain.SysMenu;
import com.mvc.service.SysMenuService;
import com.mvc.util.EasyuiTreeNode;
import com.mvc.util.Result;
import com.mvc.util.WebFrontHelper;
@Controller

public class SysMenuController {

	  @RequestMapping(value = "/menu", method = {RequestMethod.GET,RequestMethod.POST})  
		
		public String findMenus(Model model) {
		
			return "/menu";
	  }
	  
	  
	  
	  

		@Resource
		private SysMenuService sysMenuService;

		@RequestMapping("/menu/list")
		@ResponseBody
		public List<SysMenu> list(SysMenu sysMenu) {
			WebFrontHelper webtree = new WebFrontHelper();
			SysMenu menu = webtree.buildMenuTree(this.sysMenuService.findAll());	
			return menu.getChildren();
			
		
		}
		
	/*	@RequestMapping(value ="/menu/findMenuSon", method = {RequestMethod.GET,RequestMethod.POST})
		@ResponseBody
		public List<EasyuiTreeNode> findMenu(Model model) {
			  WebFrontHelper webtree = new WebFrontHelper();
	    		EasyuiTreeNode node = webtree.buildTreeForEasyuiTree(this.sysMenuService.findAll());	
	    		
	    		
	    		model.addAttribute("treeJsonG", new Gson().toJson(node.getChildren()));	
			return node.getChildren();
			
		}*/
		
	  /*  public PageBean list(@RequestParam(value="page", defaultValue="1") int pageNo, 
				@RequestParam(value="rows", defaultValue="10") int pageSize , SysMenu sysMenu) {
			
			PageBean page=new PageBean();
			int start = (pageNo-1)*pageSize;  //每页的起始索引=(页号-1)*每页显示的条数，
			page.setPage(start); //当前页的首条记录索引   **设置分页当前页的必须元素
			page.setPageSize((Integer)pageSize);//当前页的显示多少条记录  **设置分页当前页的必须元素
			//get list
			List<SysMenu> list=sysMenuService.findAllPage(page,sysMenu);
			
			//get total
			Long count = sysMenuService.getTotal(page);//多少条数据，共多少页
			
			page.setTotal(count.intValue()); 
			page.setRows(list); 
			return page;
		}*/
		
		/*public  List<SysMenu> listParentId(){
			return this.sysMenuService.findAll();
		}*/
		
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
		
		@RequestMapping(value = "/menu/update", method = RequestMethod.POST)
		@ResponseBody
		public Result update(SysMenu menu) {
			boolean a = false;
			try{
			this.sysMenuService.update(menu);
			a=true;
			return new Result();
			}catch(Exception e){
			if(a!=true){
				return new Result("菜单名不能重复");
			}
			}
			return null;
		}
	
		@RequestMapping(value = "/menu/delete", method = RequestMethod.POST)
		@ResponseBody
		public Result delete(@RequestParam(value="menuId", required=true) String menuId) {
			this.sysMenuService.delete(menuId);
			return new Result();
		}
		
		
		
		
		
}
