package com.mvc.constroller;  


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

import com.mvc.util.AppHelper;
import com.mvc.util.Result;


import org.springframework.stereotype.Controller;  
import org.springframework.ui.ModelMap;  
import org.springframework.web.bind.annotation.PathVariable;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;  

import com.lowagie.text.pdf.codec.Base64.InputStream;
import com.lowagie.text.pdf.hyphenation.TernaryTree.Iterator;
import com.mvc.domain.Admin;
import com.mvc.domain.PageBean;
import com.mvc.domain.User;
import com.mvc.service.AdminService;
import com.mvc.service.SelectService;
import com.mysql.jdbc.PreparedStatement;
import com.sun.jndi.ldap.Connection;
@Controller  
public class AdminController  {  
    public AdminController() {}  
  
    @RequestMapping(value = "/admin", method = RequestMethod.GET)  
    public String registPost() {  
        return "/admin";  
    }


	
	
    @Resource
    private AdminService adminService;
    public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	

	/*
	 * select
	 */
	
	
	@ResponseBody
    @RequestMapping("/admin")  

    public PageBean list(@RequestParam(value="page", defaultValue="1") int pageNo, 
			@RequestParam(value="rows", defaultValue="10") int pageSize , Admin admin) {
		
		PageBean page=new PageBean();
		int start = (pageNo-1)*pageSize;  //每页的起始索引=(页号-1)*每页显示的条数，
		page.setPage(start); //当前页的首条记录索引   **设置分页当前页的必须元素
		page.setPageSize((Integer)pageSize);//当前页的显示多少条记录  **设置分页当前页的必须元素
		//get list
		List<Admin> list=adminService.findAllAdmin(page,admin);
		
		//get total
		Long count = adminService.getTotal(page);//多少条数据，共多少页
		
		page.setTotal(count.intValue()); 
		page.setRows(list); 
		return page;
	}
	
	@RequestMapping("/addAdmin")
	@ResponseBody
	public Result save(Admin admin) {
		
		
		Long count1=adminService.getAdminCount(admin);
		this.adminService.save(admin);
		Long count2=adminService.getAdminCount(admin);
		try{
			if(count1==count2){
				return new Result("已注册，请重新输入用户名");
			}}catch(Exception e)
			{e.printStackTrace();}
		return new Result();
	}
	@RequestMapping("/deleteAdmin")
	@ResponseBody
	public Result delete(Admin admin) {
		this.adminService.delete(admin);
		return new Result();
	}
	
	@RequestMapping("/updateAdmin")
	@ResponseBody
	public Result updatePassword(
			@RequestParam("username") String username,
			@RequestParam(value = "oldPassword", defaultValue = "") String oldPassword,
			@RequestParam(value = "newPassword", defaultValue = "") String newPassword) {

		oldPassword = oldPassword.trim();//去掉字符串左右两边的空格
		newPassword = newPassword.trim();
		
		if (oldPassword.isEmpty()) {
			throw new IllegalArgumentException("缺少旧密码");
		}
		
		if (newPassword.isEmpty()) {
			throw new IllegalArgumentException("缺少新密码");
		}
		
	/*	if (newPassword.equals(oldPassword)) {
			throw new IllegalArgumentException("新旧密码不能相同");
		}*/
		
		Admin user = this.adminService.findByUserName(username);
		if (user != null && AppHelper.encryptPassword(oldPassword).equals(user.getPassword())) {
			this.adminService.updatePassword(username, newPassword.trim());
			return new Result();
		}
		
		return new Result("原密码输入错误");
		
		
	}
	
	
	
		/*@RequestMapping(value="/download_customer.do" , method = RequestMethod.GET)
	    public String download(HttpServletRequest request,HttpServletResponse response,Admin admin) throws IOException{
	        String fileName="excel文件";
	        //填充projects数据
	        List<Admin> projects=createData(admin);
	        List<Map<String,Object>> list=createExcelRecord(projects);
	        String columnNames[]={"ID","用户名","密码"};//列名
	        String keys[]   =    {"id","username","password"};//map中的key
	        ByteArrayOutputStream os = new ByteArrayOutputStream();
	        try {
	            ExcelUtil.createWorkBook(list,keys,columnNames, fileName).write(os);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        byte[] content = os.toByteArray();
	        ByteArrayInputStream is = new ByteArrayInputStream(content);//////
	        // 设置response参数，可以打开下载页面
	        response.reset();
	        response.setContentType("application/vnd.ms-excel;charset=utf-8");
	        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
	        ServletOutputStream out = response.getOutputStream();
	        BufferedInputStream bis = null;
	        BufferedOutputStream bos = null;
	        try {
	            bis = new BufferedInputStream(is);
	            bos = new BufferedOutputStream(out);
	            byte[] buff = new byte[2048];
	            int bytesRead;
	            // Simple read/write loop.
	            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
	                bos.write(buff, 0, bytesRead);
	            }
	        } catch (final IOException e) {
	            throw e;
	        } finally {
	            if (bis != null)
	                bis.close();
	            if (bos != null)
	                bos.close();
	        }
	        return null;
	    }
	    private List<Admin> createData(Admin admin) {
	        // TODO Auto-generated method stub
	        //自己实现,填充数据
	    	List<Admin> list=this.selectService.excl(admin);
	    	List<Admin> sta=new ArrayList<Admin>();
	    	sta.addAll(list);	    	
	        return sta;
	    }
	    private List<Map<String, Object>> createExcelRecord(List<Admin> projects) {
	        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("sheetName", "sheet1");
	        listmap.add(map);
	        Admin project=null;
	        for (int j = 0; j < projects.size(); j++) {
	            project=projects.get(j);
	            Map<String, Object> mapValue = new HashMap<String, Object>();
	            mapValue.put("id", project.getId());
	            mapValue.put("username", project.getUsername());
	            mapValue.put("password", project.getPassword());
	          
	            listmap.add(mapValue);
	        }
	        return listmap;
	    }
	*/
	
	/**
	 * JSON批量删除Action处理
	 * 
	 * @return
	 */
	@RequestMapping(value = "jsonBatchRemoveAdmin.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Result jsonBatchRemove(HttpServletRequest request) {
		
		String[] array = request.getParameterValues("array[]");  
		
		List<Admin> list = new ArrayList<Admin>(); 
		for (int i = 0; i < array.length; i++) { 
			Admin totrecords = new Admin(); 
		totrecords.setId(Integer.valueOf(array[i])); 
		list.add(totrecords); 
		} 
		this.adminService.deleteAll(list);
		return  new Result();
	}
	
	
	
	
	
	
	
	
	
	
	

 
} 