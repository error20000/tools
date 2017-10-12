package com.tools.web;

import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;

import com.tools.annotation.API;
import com.tools.controller.AbstractBaseController;
import com.tools.web.annotation.Controller;

/**
 * 继承AbstractBaseController的类、或者注解为Controller的类，才加入Servlet。
 * @author liujian
 *
 * @see com.tools.controller.AbstractBaseController
 * @see com.tools.web.annotation.Controller
 */
@HandlesTypes({AbstractBaseController.class, Controller.class, API.class})
public class ServletContainerInitializerImpl implements ServletContainerInitializer {
	
	@Override
	public void onStartup(Set<Class<?>> clsses, ServletContext context) throws ServletException {
		context.log("ServletContainerInitializerImpl onStartup....");
		Set<Class<?>> ctrlClsses = new HashSet<Class<?>>();
		Set<Class<?>> apiClsses = new HashSet<Class<?>>();
		//listener
		listener(context);
		//servlet、api
		if(clsses != null){
			for (Class<?> clss : clsses) {
				//继承AbstractBaseController的类、或者注解为Controller的类，才加入Servlet。
				if (!clss.isInterface() && !Modifier.isAbstract(clss.getModifiers()) &&
						AbstractBaseController.class.isAssignableFrom(clss) || clss.isAnnotationPresent(Controller.class)) {
					ctrlClsses.add(clss);
					apiClsses.add(clss);
				//api
				}else if (!clss.isInterface() && !Modifier.isAbstract(clss.getModifiers()) || clss.isAnnotationPresent(API.class)) {
					apiClsses.add(clss);
				}
			}
			//解析
			new ServletInitializerController().onStartup(ctrlClsses, context);
			new ServletInitializerApi().onStartup(apiClsses, context);
		}
	}
	
	private void listener(ServletContext context) throws ServletException{
		context.addListener(new ServletContextListenerImpl());
	}
    
}
