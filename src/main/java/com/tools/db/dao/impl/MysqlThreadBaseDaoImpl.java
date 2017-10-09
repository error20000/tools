package com.tools.db.dao.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.tools.db.dao.MysqlThreadBaseDao;

/**
 * 继承{@code MysqlBaseDaoImpl} ，提供异步执行任务。线程池：固定，默认： 20。
 * 
 * <p>{@code MysqlThreadBaseDaoImpl}是{@code MysqlBaseDaoImpl}的升级版，只多新增了异步执行接口：exeJob接口。
 * 
 * <p>用法：<pre>{@code MysqlThreadBaseDaoImpl<T> dao = new MysqlThreadBaseDaoImpl<T>();
		dao.exeJob(new Runnable() {
			public void run() {
				dao.save(T);
			}
		});}</pre>
 *
 * @author liujian
 *
 * @param <T>
 * @see com.tools.db.dao.impl.MysqlBaseDaoImpl
 * @see com.tools.jdbc.JdbcOperate
 */
public abstract class MysqlThreadBaseDaoImpl<T> extends MysqlBaseDaoImpl<T> implements MysqlThreadBaseDao<T> {

	/**
	 * 线程数，默认：20
	 */
	protected int count = 20;
	
	private static ExecutorService service = null;

	public MysqlThreadBaseDaoImpl() {
		initJdbcOperate();
		service = Executors.newFixedThreadPool(count);
	}
	
	/**
	 * 使用线程池执行CRUD
	 * @param runnable CRUD任务
	 */
	@Override
	public void exeJob(Runnable runnable) {
		service.submit(runnable);
	}
	

	
}
