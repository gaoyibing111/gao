package com.mvc.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.session.Constants;


import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.Sys;

public class SystemInterceptor implements HandlerInterceptor{

	private Object systemLoggerService;
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// 4.void afterCompletion()在呈现视图之后调用，可用于清理资源等；
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// 3.void postHandle()方法在controller被调用之后调用，可在modelandview中加入数据，比如当前时间；
		
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
// 2.boolean preHandle()方法在controller被调用之前调用；5.当设置多个拦截器时，先按顺序调用preHandle方法，然后逆序调用每个拦截器的postHandle和afterCompletion方法
		
		String uri = request.getRequestURI();
//		logger.info("uri={}", uri);
		
		uri = uri.replaceFirst(request.getContextPath(), "");

 		if (! uri.startsWith("/select")) {  //监控的路径
 					if (! uri.startsWith("/main"))
		
			
			return true;
		}
		
		if (request.getSession().getAttribute(Sys.USER_SESSION_KEY) == null) { // "current"  查看会话的字符标识是否匹配 ****Sys.USER_SESSION_KEY==session当时定义的字串
			// 未登录
			PrintWriter out = response.getWriter();
			StringBuilder builder = new StringBuilder();
			builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");
			builder.append("window.top.location.href=\"login");
	//		builder.append(request.getContextPath());
			builder.append("/\";</script>");
			out.print(builder.toString());
			out.close();
			return false;//如果返回false，则不再调用之后的方法
		} else {
			return true;
		}
	}
		
		

}
