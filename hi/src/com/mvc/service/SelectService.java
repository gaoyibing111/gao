package com.mvc.service;

import java.util.List;


import com.mvc.service.UserExistsException;



import org.springframework.web.bind.annotation.RequestParam;

import com.mvc.dao.SelectDao;
import com.mvc.domain.Admin;
import com.mvc.domain.PageBean;
import com.mvc.domain.User;
import com.sun.xml.internal.bind.v2.model.core.ID;


public interface SelectService {
	
	List<User> findAll(PageBean page,User user);
	/**
	 * get total of rows
	 * @param page
	 * @return
	 */
	public Long getTotal(PageBean page);
	
	/**
	 * select byId
	 */
	void save(User user) throws UserExistsException;
	/**
	 * 保存用户
	 * @param user
	 * @throws UserExistsException 用户名存在
	 */
	
	void update(User user);
	
	void delete(User user);
	
//	public User findById(User user);
	Admin loadUserByUsernameAndPassword(String username,String password);
		
	List excl(Admin admin);
	List<User> exclUser(User user);

	
	   /**
     * 依据ID集合批量删除记录
     * 
     * @param id
     * @return
     */
	void deleteAll(List<User> list);
  
	
}
