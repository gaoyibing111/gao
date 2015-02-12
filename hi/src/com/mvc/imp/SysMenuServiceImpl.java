package com.mvc.imp;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mvc.dao.SysMenuDao;
import com.mvc.domain.SysMenu;
import com.mvc.service.SysMenuService;

@Service(value="sysMenuService")
public class SysMenuServiceImpl implements SysMenuService  {
	@Resource
	private SysMenuDao sysMenuDao;
	
	@Override
	public List<SysMenu> findAll() {
		return this.sysMenuDao.findAll();
	}
	@Override
	public 	List<SysMenu> findByChildId(List<String> menuId){
	return this.sysMenuDao.findByChildId(menuId);
	}
	
	@Override
	public void save(SysMenu menu){
		if (menu == null) {
			return;
		}
		
		if (menu.getMenuName() == null || menu.getMenuName().isEmpty()) {
			throw new IllegalArgumentException("menu.name.required");
		}
		
		if (menu.getParentMenuId() != null && 
				"".equals(menu.getParentMenuId().trim())) {
			menu.setParentMenuId(null);
		}
	int a=new Random().nextInt();
	String b= Integer.toString(a);
		menu.setMenuId(b);
		
		this.sysMenuDao.save(menu);
	}
	

		
	
}
