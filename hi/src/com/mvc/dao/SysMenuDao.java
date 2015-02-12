package com.mvc.dao;

import java.util.List;

import com.mvc.domain.SysMenu;



public interface SysMenuDao {
	List<SysMenu> findAll();
	List<SysMenu> findByChildId(List<String> menuId);
	void save(SysMenu menu);

}
