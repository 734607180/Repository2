package com.jk.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class BaseAction extends ActionSupport
		implements ServletRequestAware, ServletResponseAware, RequestAware, SessionAware {
	private static final long serialVersionUID = 35772106234216051L;

	public HttpServletResponse response;
	public HttpServletRequest request;
	public HttpSession session;
	public Map requestMap;
	public Map sessionMap;

	public BaseAction() {
	}

	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		this.session = this.request.getSession(true);
	}

	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
		this.response.setCharacterEncoding("utf-8");

	}

	public void setRequest(Map arg0) {
		this.requestMap = arg0;
	}

	public void setSession(Map arg0) {
		this.sessionMap = arg0;
	}

	// 处理hibernate懒加载
	protected void ajaxOut(Object obj) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "handler", "hibernateLazyInitializer" });
		String jsonStr = JSONSerializer.toJSON(obj, jsonConfig).toString();
		ajaxOut(jsonStr);
	}

	// 处理后的字符串输出到jsp页面
	protected void ajaxOut(String jsonStr) {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(jsonStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ajaxMapOut(Map map){
		JsonConfig config = new JsonConfig();
		config.setIgnoreDefaultExcludes(false);
		config.registerJsonValueProcessor(java.util.Date.class,new JsonValueProcessor() {
            private final String format="yyyy-MM-dd";
            public Object processObjectValue(String key, Object value,JsonConfig arg2){
              if(value==null)
                    return "";
              if (value instanceof java.util.Date) {
                    String str = new SimpleDateFormat(format).format((java.util.Date) value);
                    return str;
              }
                    return value.toString();
            }
      
            public Object processArrayValue(Object value, JsonConfig arg1){
                       return null;
            }
            
         });
		JSONObject json = new JSONObject();
		json.putAll(map, config);
		ajaxOut(json.toString());
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {

		return response;
	}

	public HttpSession getSession() {
		return session;
	}

	public Map getRequestMap() {
		return requestMap;
	}

	public Map getSessionMap() {
		return sessionMap;
	}

}
