package com.mvc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mvc.domain.PageBean;
import com.mvc.domain.SysMenu;


@Service(value="sysMenuService")
public interface SysMenuService {

	/*List<SysMenu> findAllPage(PageBean page,SysMenu sysMenu);*/
	List<SysMenu> findAll();
/*	public Long getTotal(PageBean page);*/
	List<SysMenu> findByChildId(List<String> menuId);
	void save(SysMenu menu);
/*	List<SysMenu> findAllChi();*/
	
	void update(SysMenu menu);
	void delete(String menuId);
}
