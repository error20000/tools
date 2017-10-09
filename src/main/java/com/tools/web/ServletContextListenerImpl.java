package com.tools.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.tools.utils.LogsTools;
import com.tools.utils.Tools;

public class ServletContextListenerImpl implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.out.println("contextInitialized............");
		ServletContextListener.super.contextInitialized(sce);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.out.println("contextDestroyed............");
		Tools.shutdown();
		LogsTools.shutdown();
		ServletContextListener.super.contextDestroyed(sce);
	}

	
	
}
