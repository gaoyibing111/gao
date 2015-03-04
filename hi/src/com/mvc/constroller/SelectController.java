package com.mvc.constroller;  
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.domain.PageBean;
import com.mvc.domain.User;
import com.mvc.service.SelectService;
import com.mvc.util.Result;
import com.mvc.util.fusion.Chart;
import com.mvc.util.fusion.Dataset;
import com.mysql.jdbc.Messages;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;


 
@Controller  

public class SelectController {  
    public SelectController() {}  

    
    @RequestMapping(value = "/select", method = RequestMethod.GET)  
    public String registPost() {  
        return "/select";  
    }
    
    @Resource
    private SelectService selectService;
    public SelectService getSelectService() {
		return selectService;
	}
	public void setSelectService(SelectService selectService) {
		this.selectService = selectService;
	}
//	@InitBinder 
//	  protected void initBinder(WebDataBinder binder){  
//	        
//		  binder.registerCustomEditor(int.class, new IntegerEditor());  
//		  
//	    } 
	@ResponseBody
    @RequestMapping("/select")  

    public PageBean list(@RequestParam(value="page", defaultValue="1") int pageNo, 
			@RequestParam(value="rows", defaultValue="10") int pageSize , User user) {
		
		PageBean page=new PageBean();
		int start = (pageNo-1)*pageSize;  //每页的起始索引=(页号-1)*每页显示的条数，
		page.setPage(start); //当前页的首条记录索引   **设置分页当前页的必须元素
		page.setPageSize((Integer)pageSize);//当前页的显示多少条记录  **设置分页当前页的必须元素
		//get list
		List<User> list=selectService.findAll(page,user);
		
		//get total
		Long count = selectService.getTotal(page);//多少条数据，共多少页
		
		page.setTotal(count.intValue()); 
		page.setRows(list); 
		return page;
	}
	
	
	@RequestMapping("/add")
	@ResponseBody
	public Result add(User user) throws Exception {
		this.selectService.save(user);
		return new Result();
	}

	@RequestMapping("/update")
	@ResponseBody
	public Result update(User user) {
		this.selectService.update(user);
		return new Result();
	}


	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(User user) {
		this.selectService.delete(user);
		return new Result();
	}
	
	/**
	 * JSON批量删除Action处理
	 * 
	 * @return
	 */
	@RequestMapping(value = "jsonBatchRemove.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Result jsonBatchRemove(User user, HttpServletResponse response,HttpServletRequest request) {
		
		String[] array = request.getParameterValues("array[]");  
		
		List<User> list = new ArrayList<User>(); 
		for (int i = 0; i < array.length; i++) { 
		User totrecords = new User(); 
		totrecords.setId(Integer.valueOf(array[i])); 
		list.add(totrecords); 
		} 
		this.selectService.deleteAll(list);
		return  new Result();
	}
	
		
	
		
		
	/**
	 * 导出EXCL	
	 */
	    @RequestMapping(value="/download_user.do" , method = RequestMethod.GET)
	    public String download(HttpServletRequest request,HttpServletResponse response,User user) throws IOException{
	        String fileName="excel文件";
	        //填充projects数据
	        List<User> projects=createData(user);
	        List<Map<String,Object>> list=createExcelRecord(projects);
	        String tableName="人员表";
	        String columnNames[]={"ID","姓名","年龄","邮箱","电话","地址"};//列名
	        String keys[]   =    {"id","name","age","email","telephone","address"};//map中的key
	        ByteArrayOutputStream os = new ByteArrayOutputStream();
	        try {
	            ExcelUtil.createWorkBook(list,keys,columnNames,tableName).write(os);
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
	    private List<User> createData(User user) {
	        // TODO Auto-generated method stub
	        //自己实现,填充数据
	    	List<User> list=this.selectService.exclUser(user);
	    	List<User> sta=new ArrayList<User>();
	    	sta.addAll(list);	    	
	        return sta;
	    }
	    private List<Map<String, Object>> createExcelRecord(List<User> projects) {
	        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("sheetName", "sheet1");
	        listmap.add(map);
	        User project=null;
	        for (int j = 0; j < projects.size(); j++) {
	            project=projects.get(j);
	            Map<String, Object> mapValue = new HashMap<String, Object>();
	            mapValue.put("id", project.getId());
	            mapValue.put("name", project.getName());
	            mapValue.put("age", project.getAge());
	            mapValue.put("email", project.getEmail());
	            mapValue.put("telephone", project.getTelephone());
	            mapValue.put("address", project.getAddress());
	            listmap.add(mapValue);
	        }
	        return listmap;
	    }
		
		
	   /**
		 * 图表显示
		 * @param 
		 * @param
		 */
		@RequestMapping(value="installCountChart.do", method = { RequestMethod.GET, RequestMethod.POST })
		public void installCountChart(User user, HttpServletResponse response, HttpServletRequest request){
			String success = "false";
			user.setPageSize(10000);
			user.setPage(0);
			List<User> cusList = selectService.exclUser(user);
			
			
			
			Chart chart=new Chart();
			chart.setCaption("人员年龄统计表");//图表里面的标题
			chart.setxAxisName("在职人员");//图表里面底端的介绍   x轴    //*用的是柱状图flash插件
			chart.setyAxisName("年龄");//图表Z左端标题 y轴
			//part one
			for(User infoSub : cusList){
				chart.addCategory(infoSub.getName());
			}
			if(cusList.isEmpty()){
				chart.addCategory("没有数据");
			}
			
			//输入每个人的年龄
			Dataset termDataset=new Dataset();
			termDataset.setSeriesName("统计在职人员的年龄");
			termDataset.setShowValues("1");//在图中显示具体数值
			for(User user2 : cusList){
				termDataset.addSet(user2.getAge().toString());
			}
			chart.addDataset(termDataset);
			
			// 存放路径
			String pathXml = request.getSession().getServletContext().getRealPath("/") + File.separator + "installCountByIns.xml";
			try {
				FileOutputStream fos = new FileOutputStream(pathXml);
				fos.write(chart.toString().getBytes("GBK"));
				fos.close();
				success = "true";
			} catch (FileNotFoundException e) {
			//	logger.error("FileNotFoundException");
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
			//	logger.error("UnsupportedEncodingException");
				e.printStackTrace();
			} catch (IOException e) {
			//	logger.error("IOException");
				e.printStackTrace();
			}
			try {
				response.setContentType("application/json;charset=UTF-8");  
				response.getWriter().write("{\"success\":\""+success+"\",\"size\":\""+cusList.size()+"\"}");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
		
		
		
		
	
} 