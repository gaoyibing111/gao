package com.mvc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mvc.domain.SysMenu;


@Service(value="sysMenuService")
public interface SysMenuService {

	List<SysMenu> findAll();
	List<SysMenu> findByChildId(List<String> menuId);
	void save(SysMenu menu);

	
}
