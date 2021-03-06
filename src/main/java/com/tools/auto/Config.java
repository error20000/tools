package com.tools.auto;

import com.tools.utils.Tools;

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
	private static final String CONFIG_PATH = "config";
	
	//配置
	private String dbPropertiesName = "autodb.properties"; //数据库配置文件名
	private String dbSecondPropertiesName = "autodb2.properties"; //数据库配置文件名
	private boolean overWrite = false; //文件已存在是否覆盖
	private String reqPrefix = "/api"; //请求前缀
	private String chartset = "utf-8";//文件字符集
	
	
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
	
	public String getConfigPath(){
		return basePackge+"."+CONFIG_PATH;
	}

	
	
	
	public String getBasePackge() {
		return basePackge;
	}

	/*
	 * 设置包名
	 */
	public void setBasePackge(String basePackge) {
		basePackge = basePackge.trim();
		if(!Tools.isNullOrEmpty(basePackge)){
			this.basePackge = basePackge;
		}
	}

	public String getDbPropertiesName() {
		return dbPropertiesName;
	}
	
	/*
	 * 数据库配置文件名，默认"autodb.properties"
	 */
	public void setDbPropertiesName(String dbPropertiesName) {
		dbPropertiesName = dbPropertiesName.trim();
		if(!Tools.isNullOrEmpty(dbPropertiesName)){
			this.dbPropertiesName = dbPropertiesName;
		}
	}

	public String getDbSecondPropertiesName() {
		return dbSecondPropertiesName;
	}

	/*
	 * 数据库配置文件名，默认"autodb2.properties"
	 */
	public void setDbSecondPropertiesName(String dbSecondPropertiesName) {
		dbSecondPropertiesName = dbSecondPropertiesName.trim();
		if(!Tools.isNullOrEmpty(dbSecondPropertiesName)){
			this.dbSecondPropertiesName = dbSecondPropertiesName;
		}
	}

	public boolean isOverWrite() {
		return overWrite;
	}

	/*
	 * 文件已存在是否覆盖，默认"false"
	 */
	public void setOverWrite(boolean overWrite) {
		this.overWrite = overWrite;
	}

	public String getReqPrefix() {
		return reqPrefix;
	}

	/*
	 * 请求前缀，默认"/api"
	 */
	public void setReqPrefix(String reqPrefix) {
		reqPrefix = reqPrefix.trim();
		if(!Tools.isNullOrEmpty(reqPrefix)){
			reqPrefix = reqPrefix.replace("../", "");
			if(!reqPrefix.startsWith("/")){
				reqPrefix = "/"+reqPrefix;
			}
			if(reqPrefix.endsWith("/")){
				reqPrefix = reqPrefix.substring(0, reqPrefix.length() - 1);
			}
		}
		this.reqPrefix = reqPrefix;
	}

	public String getChartset() {
		return chartset;
	}
	
	/*
	 * 文件字符集，默认"utf-8"
	 */
	public void setChartset(String chartset) {
		chartset = chartset.trim();
		if(!Tools.isNullOrEmpty(chartset)){
			this.chartset = chartset;
		}
	}
	

}
