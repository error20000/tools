package com.tools.db.dao;

/**
 * 接口，继承{@code AbstractBaseDao}
 * @author liujian
 *
 * @param <T>
 */
public interface MysqlThreadBaseDao<T> extends AbstractBaseDao<T> {
	
	public void exeJob(Runnable runnable);
	
}
