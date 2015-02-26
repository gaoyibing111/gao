package com.mvc.imp;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.dao.SysMenuDao;
import com.mvc.domain.PageBean;
import com.mvc.domain.SysMenu;
import com.mvc.service.SysMenuService;
import com.mvc.util.Result;

@Service(value="sysMenuService")
public class SysMenuServiceImpl implements SysMenuService  {
	@Resource
	private SysMenuDao sysMenuDao;
	
	public List<SysMenu> findAll() {
		return this.sysMenuDao.findAll();
	}
	

/*	public List<SysMenu> findAllPage(PageBean page,SysMenu sysMenu) {
		sysMenu.setPage(page.getPage());
		sysMenu.setPageSize(page.getPageSize());
		return this.sysMenuDao.findAllPage(sysMenu);
	}
	
	public Long getTotal(PageBean page) {
		
		return this.sysMenuDao.getTotal(page);
	}*/
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
			menu.setParentMenuId("root");					///////以前设置顶级节点为null，现在设置为root，以便于数据库查询顶级节点
		}
	
	int a=new Random().nextInt();
	String b= Integer.toString(a);
		menu.setMenuId(b);
		
		this.sysMenuDao.save(menu);
	}
/*
	public List<SysMenu> findAllChi(){
		return this.sysMenuDao.findAllChi();
	}*/
		public void update(SysMenu menu){
			this.sysMenuDao.update(menu);
		}
		
		
		@Override
		@Transactional
		public void delete(String menuId) {
			if (menuId == null) {
				return;
			}
			
			this.sysMenuDao.delete(menuId);
			
		
		}
}
