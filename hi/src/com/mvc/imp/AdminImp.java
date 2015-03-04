package com.mvc.imp;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;


import org.apache.commons.validator.Msg;

import org.springframework.stereotype.Service;

import com.mvc.dao.AdminDao;

import com.mvc.domain.Admin;
import com.mvc.domain.PageBean;
import com.mvc.domain.User;

import com.mvc.service.AdminService;
import com.mvc.util.AppHelper;
import com.mvc.util.Result;
@Service
public class AdminImp implements AdminService{
	
	@Resource
	private AdminDao adminDao;

	public AdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	public List<Admin> findAllAdmin(PageBean page, Admin admin){
		try {
			admin.setPage(page.getPage());
			admin.setPageSize(page.getPageSize());
			List<Admin> userList= this.adminDao.findAllAdmin(admin);
			return userList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Long getTotal(PageBean page) {
	
		return this.adminDao.getTotal(page);
	}

	public void save(Admin admin) {
		try{
		admin.setPassword(AppHelper.encryptPassword(admin.getPassword().toString()));
		this.adminDao.save(admin);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public long getAdminCount(Admin admin){
		return this.adminDao.getAdminCount(admin);//取多少个用户
	}

	public void delete(Admin admin){
		this.adminDao.delete(admin);
	}
	
	@Override
	public Admin findByUserName(String username) {
		return this.adminDao.findByUserName(username);
	}
	public void updatePassword(String username, String newPassword){
		newPassword = AppHelper.encryptPassword(newPassword);
		this.adminDao.updatePassword(username, newPassword);
	}
	
	
	   /**
     * 依据ID集合批量删除记录
     * 
     * @param id
     * @return
     */
	public void deleteAll(List<Admin> list){
		for (Admin to : list) { 
			this.adminDao.delete(to); //调用单个删除
			} 
	}
	
	
}
