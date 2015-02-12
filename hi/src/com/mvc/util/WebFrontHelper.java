package com.mvc.util;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.mvc.constroller.SysMenuController;
import com.mvc.dao.SysMenuDao;
import com.mvc.domain.SysMenu;
import com.mvc.service.SysMenuService;
import com.mvc.util.EasyuiTreeNode;

public class WebFrontHelper {
@Resource
	private SysMenuService sysMenuService;

	
	public void setSysMenuService(SysMenuService sysMenuService) {
		this.sysMenuService = sysMenuService;
	}

	public static SysMenu buildMenuTree(List<SysMenu> menus) {

		Map<String, SysMenu> menuMap = new LinkedHashMap<String, SysMenu>();
		SysMenu rootMenu = new SysMenu();
		rootMenu.setMenuId("root");
		rootMenu.setMenuName("Root");
		menuMap.put("root", rootMenu);

		for (SysMenu menu : menus) {
			menuMap.put(menu.getMenuId(), menu);
		}

		for (SysMenu menu : menus) {
			String parentMenuId = menu.getParentMenuId();
			if (parentMenuId == null || "".equals(parentMenuId)) {
				parentMenuId = "root";
			}

			menuMap.get(parentMenuId).addChild(menu);
		}

		return rootMenu;
	}




	public  EasyuiTreeNode buildTreeForEasyuiTree(List<SysMenu> menus,
			List<String> menuIdsForChecked) {

		Map<String, EasyuiTreeNode> map = new LinkedHashMap<String, EasyuiTreeNode>();
		EasyuiTreeNode root = new EasyuiTreeNode();
		root.setId("root");
		root.setText("Root");
		map.put("root", root);
		
	//	SysMenuController	smc=new	SysMenuController();
	/*	List<SysMenu> list = this.sysMenuService.findAll();

		Set<String> keys=new HashSet<String>();
		for(SysMenu item : list){
			keys.add(item.getParentMenuId());
		}*/
		
		for (SysMenu menu : menus) {
			EasyuiTreeNode node = new EasyuiTreeNode();
			node.setId(menu.getMenuId());
			node.setText(menu.getMenuName());
			node.setParentId(menu.getParentMenuId());
			node.setAttributes(menu);
		//	List<SysMenu> all = sysMenuService.parentIdfindAll();
			
		/*	if(keys.contains(menu.getMenuId())){
				node.setState("closed");
			}else{
				
				node.setState("open");
			}
			*/
			
			
			
			/*for(SysMenu item : all){
				if (menu.getMenuId().equalsIgnoreCase(item.getParentMenuId())) { 
					// 判断是否是一级目录if(menu.getMenuId().equals("0")){
					node.setState("closed"); // 是目录的话如果目录有子菜单将树收起
				}
			}*/
			
			if (menu.getMenuUrl().equals("0")){
				
				node.setState("closed"); 
			}
			map.put(node.getId(), node);

		}

		for (Map.Entry<String, EasyuiTreeNode> entry : map.entrySet()) {
			EasyuiTreeNode node = entry.getValue();

			if ("root".equals(node.getId())) {
				continue;
			}

			String parentId = node.getParentId();

			if (parentId == null || "".equals(parentId)) {
				parentId = "root";
			}

			map.get(parentId).addChild(node);
		}

		if (menuIdsForChecked != null && menuIdsForChecked.size() > 0) {
			for (Map.Entry<String, EasyuiTreeNode> entry : map.entrySet()) {
				EasyuiTreeNode node = entry.getValue();

				if (menuIdsForChecked.contains(node.getId())) {
					node.setChecked(true);
				}

			}
		}

		return root;
	}

	public   EasyuiTreeNode buildTreeForEasyuiTree(List<SysMenu> menus) {
		return buildTreeForEasyuiTree(menus, null);
	}
	


}
