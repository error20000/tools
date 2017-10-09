package com.tools.auto;

public class Config {
	
	private String basePackge = "com.auto";
	
	//包地址
	private static final String ENTITY_PATH = "entity";
	private static final String DAO_PATH = "dao";
	private static final String DAO_IMPL_PATH = "dao.impl";
	private static final String DAO_UTIL_PATH = "dao.util";
	private static final String SERVICE_PATH = "service";
	private static final String SERVICE_IMPL_PATH = "service.impl";
	private static final String SERVLET_PATH = "servlet";
	private static final String CONTROLLER_PATH = "controller";
	
	
	public Config(){
		
	}
	
	public Config(String basePackge){
		basePackge = basePackge.trim();
		if(basePackge.endsWith(".")){
			basePackge = basePackge.substring(0, basePackge.length() - 1);
		}
		this.basePackge = basePackge;
	}
	
	public String getBasePath(){
		return basePackge;
	}
	
	public String getEntityPath(){
		return basePackge+"."+ENTITY_PATH;
	}
	
	public String getDaoPath(){
		return basePackge+"."+DAO_PATH;
	}
	
	public String getDaoImplPath(){
		return basePackge+"."+DAO_IMPL_PATH;
	}
	
	public String getDaoUtilPath(){
		return basePackge+"."+DAO_UTIL_PATH;
	}

	public String getServicePath(){
		return basePackge+"."+SERVICE_PATH;
	}

	public String getServiceImplPath(){
		return basePackge+"."+SERVICE_IMPL_PATH;
	}
	
	public String getServletPath(){
		return basePackge+"."+SERVLET_PATH;
	}

	public String getControllerPath(){
		return basePackge+"."+CONTROLLER_PATH;
	}

}
