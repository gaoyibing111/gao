package com.mvc.dao;

import java.util.List;

import com.mvc.domain.PageBean;
import com.mvc.domain.SysMenu;



public interface SysMenuDao {
	/*List<SysMenu> findAllPage(SysMenu sysMenu);*/
	List<SysMenu> findAll();
	/*public Long getTotal(PageBean page);  */
	List<SysMenu> findByChildId(List<String> menuId);
	void save(SysMenu menu);
	/*List<SysMenu> findAllChi();*/
	void update(SysMenu menu);
	void delete(String menuId);
}
