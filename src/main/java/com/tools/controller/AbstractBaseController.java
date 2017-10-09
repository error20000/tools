package com.tools.controller;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.tools.service.AbstractBaseService;
import com.tools.utils.Tools;
import com.tools.web.annotation.RequestMapping;


public abstract class AbstractBaseController<T> {
	
	protected AbstractBaseService<T> baseService;

	public AbstractBaseController(){
		initService();
	}
	
	public abstract void initService();
	
	@SuppressWarnings("unchecked")
	@RequestMapping(name="save", path={"/add"})
	public String save(HttpServletRequest req, HttpServletResponse resp){
		JSONObject json = new JSONObject();
		try {
			T obj = Tools.getReqParamsToObject(req, (T) getObejctClass().newInstance());
			int res = baseService.save(obj);
			if(res > 0){
				json.put("code", 1);
				json.put("msg", "save success!");
			}else{
				json.put("code", 0);
				json.put("msg", "save faild!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json.toJSONString();
	}
	
	
	//获取泛型的Class类型。
	private Class<?> getObejctClass(){
		Type type = getClass().getGenericSuperclass();
		Class<?> clss = null;
		try {
			Class<?>[] clsses = Tools.getGenericClass((ParameterizedType) type);
			clss = clsses[0];
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return clss;
	}
	
}
