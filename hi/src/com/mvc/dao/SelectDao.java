package com.mvc.dao;

import java.util.List;



import org.springframework.web.bind.annotation.RequestParam;



import com.mvc.domain.*;
import com.sun.xml.internal.bind.v2.model.core.ID;



public interface SelectDao {

	/**
	 * get list
	 * @param page
	 * @return
	 */
	List<User> findAll(User user);  //显示当前页的所有数据

	/**
	 * get total of rows
	 * @param page
	 * @return
	 */
	public Long getTotal(PageBean page);  //统计数据库有多少条数据

	void save(User user);
	
	void update(User user);

	void delete(User user);
	
//	查找账号密码
	Admin loadUserByUsernameAndPassword(String username,String password);
	//导出到excel
	List excl(Admin admin);
	List<User> exclUser(User user); 
	
	
	

    
	
}
