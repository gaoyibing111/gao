package com.mvc.dao;

import java.util.List;

import com.mvc.domain.Admin;
import com.mvc.domain.PageBean;





public interface AdminDao {
	List<Admin> findAllAdmin(Admin admin);  //显示当前页的所有数据
	public Long getTotal(PageBean page);  //统计数据库有多少条数据
	/**
	 * add
	 */
	void save(Admin admin);
	void delete(Admin admin);
	long getAdminCount(Admin admin);//取多少个用户
	
	Admin findByUserName(String username);//用于修改密码时判断用户ID
	
	void updatePassword(String username, String password);
}
