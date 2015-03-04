package com.mvc.service;

import java.util.List;

import com.mvc.domain.Admin;
import com.mvc.domain.PageBean;
import com.mvc.domain.User;
import com.mvc.util.Result;

public interface AdminService {
	List<Admin> findAllAdmin(PageBean page, Admin admin);  //显示当前页的所有数据
	public Long getTotal(PageBean page);  //统计数据库有多少条数据
	
	void save(Admin admin);
	void delete(Admin admin);
	
	long getAdminCount(Admin admin);//取多少个用户
	
	
	Admin findByUserName(String username);//用于修改密码时判断用户ID
	
	void updatePassword(String username, String newPassword);
	
	   /**
  * 依据ID集合批量删除记录
  * 
  * @param id
  * @return
  */
	void deleteAll(List<Admin> list);
}
