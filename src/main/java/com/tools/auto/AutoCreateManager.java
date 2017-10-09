package com.tools.auto;

import com.tools.utils.Tools;

/**
 * 自动生成管理器。
 * @author liujian
 *
 * @see com.tools.auto.AutoCreate
 */
public class AutoCreateManager {
	
	//数据库配置
	private AutoCreate autoCreate = null;
	
	public AutoCreateManager(){
		autoCreate = new AutoCreate();
	}
	
	public AutoCreateManager(Config config, ConfigDB dbConfig){
		autoCreate = new AutoCreate(config, dbConfig);
	}
	
	public AutoCreateManager(String packge, String dbPath){
		autoCreate = new AutoCreate(packge, dbPath);
	}
	
	public AutoCreateManager(String packge, String dbPath, String prefix, String separator){
		autoCreate = new AutoCreate(packge, dbPath, prefix, separator);
	}
	
	/**
	 * 
	 * @param packge
	 */
	public void setPackge(String packge){
		autoCreate.setPackge(packge);
	} 
	
	/**
	 * 数据库配置
	 * @param dbPath	数据库地址
	 */
	public void setDbPath(String dbPath){
		autoCreate.setDbPath(dbPath);
	}
	
	/**
	 * 数据库从库配置
	 * @param dbPathSecond	数据库从库地址
	 */
	public void setDbPathSecond(String dbPathSecond){
		autoCreate.setDbPathSecond(dbPathSecond);
	}
	
	/**
	 * 数据表前缀
	 * @param prefix	数据表前缀
	 */
	public void setPrefix(String prefix){
		autoCreate.setPrefix(prefix);
	} 
	
	/**
	 * 数据表分隔符
	 * @param separator	数据表分隔符
	 */
	public void setSeparator(String separator){
		autoCreate.setSeparator(separator);
	} 
	
	/**
	 * 文件字符集
	 * @param chartset 文件字符集，默认：utf-8
	 */
	public void setChartset(String chartset){
		autoCreate.setChartset(chartset);
	} 
	
	/**
	 * 是否重写
	 * @param overWrite	已生成文件是否重新生成
	 */
	public void setOverWrite(boolean overWrite){
		autoCreate.setOverWrite(overWrite);
	} 
	
	/**
	 * request请求前缀
	 * @param reqPrefix 请求前缀，默认：api
	 */
	public void setReqPrefix(String reqPrefix){
		autoCreate.setReqPrefix(reqPrefix);
	}
	
	/**
	 * 整库创建
	 */
	public void start(){
		if(autoCreate == null){
			return;
		}
		autoCreate.start();
	} 
	
	/**
	 * 整表创建
	 * @param tableName	表名
	 */
	public void start(String tableName){
		if(autoCreate == null){
			return;
		}
		if(Tools.isNullOrEmpty(tableName)){
			return;
		}
		autoCreate.createTable(tableName);
	}

}
