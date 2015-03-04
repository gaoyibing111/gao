package com.mvc.imp;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.mvc.domain.*;



import com.mvc.service.UserExistsException;




import org.springframework.stereotype.Service;



import com.mvc.dao.SelectDao;
import com.mvc.service.*;
import com.mvc.util.AppHelper;
import com.sun.xml.internal.bind.v2.model.core.ID;

@Service
public class SelectImp implements SelectService {
	
	@Resource
	private SelectDao selectDao;
	public SelectDao getSelectDao() {
		return selectDao;
	}
	public void setSelectDao(SelectDao selectDao) {
		this.selectDao = selectDao;
	}
	/**
	 * 查询user集合
	 */
	public List<User> findAll(PageBean page,User user) {
		try {
			user.setPage(page.getPage());
			user.setPageSize(page.getPageSize());
			List<User> userList= this.selectDao.findAll(user);
			return userList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Long getTotal(PageBean page) {
		// TODO Auto-generated method stub
		return this.selectDao.getTotal(page);
	}
	
	public void save(User user) throws UserExistsException{
		this.selectDao.save(user);
	}
	
	@Override
	public void update(User user) {		
		this.selectDao.update(user);
	}
	
	@Override
	public void delete(User user) {
		this.selectDao.delete(user);
	}
	
//	@Override
//	public User findById(User user) {

	@Override
	public Admin loadUserByUsernameAndPassword(String username,String password) {
		
		
		password = AppHelper.encryptPassword(password);
		
		return  this.selectDao.loadUserByUsernameAndPassword(username,password);
	}
	
	public List excl(Admin admin){
		return (List) this.selectDao.excl(admin);
	}
	
	public List<User> exclUser(User user){
		return (List) this.selectDao.exclUser(user);
	}
	
	
	   /**
     * 依据ID集合批量删除记录
     * 
     * @param id
     * @return
     */
	public void deleteAll(List<User> list){
		for (User to : list) { 
			this.selectDao.delete(to); //调用单个删除
			} 
	}
	
}
