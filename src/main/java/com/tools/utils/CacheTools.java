package com.tools.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class CacheTools {
	
	private static Map<String, CacheObject> objMap = new ConcurrentHashMap<String, CacheObject>();
	private static ReentrantLock lock = new ReentrantLock(); 
	private static boolean timerStart = false; 
	private static AtomicInteger count = new AtomicInteger(1);
	private static Timer timer = null;
	private static int runTime = 2 * 3600; //定时清理时间。单位（秒）
	private static long outTime = 2 * 3600 * 1000; //资源超时时间。单位（毫秒）
	
	private static List<Map<String, String>> sortMap = new ArrayList<Map<String, String>>();
	
	static{
		autoClear();
	}
	
	public static void initSetCacheObj(CacheObject obj) {
		objMap.put(obj.getKey(), obj);
	}

	public static CacheObject initGetCacheObj(String key) {
		return objMap.get(key);
	}

	public static void initClearCacheObj(String key) {
		objMap.remove(key);
	}

	
	public static void initAutoClear() {
		System.out.println("clear ing ...");
		long cur = System.currentTimeMillis();
		String toKey = cur + "" + 1000;
		//待清理数据
		List<Map<String, String>> clearList = sortMap.stream()
			.filter(e -> e.get("sortKey").compareTo(toKey) < 0)
			.collect(Collectors.toList());
		//剩余数据
		synchronized (sortMap) {
			sortMap = sortMap.stream()
					.filter(e -> e.get("sortKey").compareTo(toKey) >= 0)
					.collect(Collectors.toList());
		}
		//清理
		for (Map<String, String> map : clearList) {
			String key = map.get("key");
			initClearCacheObj(key);
		}
		clearList = null;
		System.out.println("clear end ...");
	}
	
	public static final void autoClear() {
		if(!timerStart){
			System.out.println("start cache clear...");
			runTime = runTime <= 0 ? 2 * 3600 * 1000 : runTime * 1000;
			timer = new Timer(true); 
			timer.schedule(new TimerTask() {
				
				@Override
				public void run() {
					lock.lock();
					initAutoClear();
					lock.unlock();
				}
			}, 0, runTime);
			timerStart = true;
		}
	}
	
	
	
	
	public static final void setCacheObj(String key, Object value){
		setCacheObj(key, value, outTime);
	}
	
	public static final void setCacheObj(String key, Object value, long timeOut){
		CacheObject obj = new CacheObject(key, value);
		long cur = System.currentTimeMillis(); //加上超时时间
		obj.setMillis(cur);
		initSetCacheObj(obj);
		//清理：排序key
		int index = count.getAndAdd(1);
		count.compareAndSet(1000, 1);
		cur += timeOut; //加上超时时间
		String sortKey = cur +""+(index < 10 ? "000"+index : index < 100 ? "00"+index : index < 1000 ? "0"+index : index);
		Map<String, String> node = new HashMap<String, String>();
		node.put("sortKey", sortKey);
		node.put("key", key);
		sortMap.add(node);
	}
	
	public static final CacheObject getCacheObj(String key){
		return initGetCacheObj(key);
	}
	
	public static final boolean checkTimeout(String key){
		return checkTimeout(key, 2 * 3600 * 1000 );
	}
	
	public static final boolean checkTimeout(String key, long outTime){
		CacheObject tmp = getCacheObj(key);
		if(tmp == null){ 
			return true;
		}
		long cur = System.currentTimeMillis();
		if((tmp.getMillis() + outTime) < cur){
			//超时移除
			clearCacheObj(key);
			return true;
		}
		return false;
	}
	
	public static final void clearCacheObj(String key){
		initClearCacheObj(key);
	}
	
	
	public static void main(String[] args) {
		/*CacheTools.setCacheObj("1", "2");
		try {
			Thread.sleep(10 * 3600 * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		for (int i = 0; i < 2000; i++) {
			int count = i;
			new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					setCacheObj("key_"+count, "value_"+count);
				}
			}).start();
		}
		
	}
}
