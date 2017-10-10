package com.tools.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class CacheTools {
	
	private static Map<String, CacheObject> objMap = new ConcurrentHashMap<String, CacheObject>();
	private static ReentrantLock lock = new ReentrantLock(); 
	private static boolean timerStart = false; 
	private static AtomicInteger count = new AtomicInteger(1);
	private static Timer timer = null;
	private static int runTime = 2 * 3600; //定时清理时间。单位（秒）
	private static long outTime = 2 * 3600 * 1000; //资源超时时间。单位（毫秒）
	
	private static SortedMap<String, String> sortMap = new TreeMap<String, String>();
	
	static{
		autoClear();
	}
	
	public static void initSetCacheObj(CacheObject obj) {
		objMap.put(obj.getKey(), obj);
	}

	public static CacheObject initGetCacheObj(String key) {
		return objMap.get(key);
	}

	public static CacheObject initClearCacheObj(String key) {
		return objMap.remove(key);
	}

	/*public static void initAutoClear() {
		System.out.println("defult clear...");
		long cur = System.currentTimeMillis();
		Set<String> keys = objMap.keySet();
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {
			String key = it.next();
			CacheObject obj = objMap.get(key);
			if((obj.getMillis() + outTime) < cur){ //超时清理
				objMap.remove(key);
			}
		}
	}*/
	
	public static void initAutoClear() {
		System.out.println("defult new clear...");
		long cur = System.currentTimeMillis();
		String toKey = (cur - outTime) + "" + 1000;
		SortedMap<String, String> clearMap = sortMap.headMap(toKey);
		Set<String> keys = clearMap.keySet();
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {
			String sortKey = it.next();
			objMap.remove(clearMap.get(sortKey));
		}
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
		CacheObject obj = new CacheObject(key, value);
		long cur = System.currentTimeMillis();
		int index = count.getAndAdd(1);
		count.compareAndSet(1000, 1);
		obj.setMillis(cur);
		//排序key
		String sortKey = cur +""+(index < 10 ? "000"+index : index < 100 ? "00"+index : index < 1000 ? "0"+index : index);
		sortMap.put(sortKey, key);
		initSetCacheObj(obj);
	}
	
	public static final CacheObject getCacheObj(String key){
		return initGetCacheObj(key);
	}
	
	public static final boolean checkTimeout(String key, String obj){
		return checkTimeout(key, obj, 2 * 3600 * 1000 );
	}
	
	public static final boolean checkTimeout(String key, String obj, long outTime){
		CacheObject tmp = getCacheObj(key);
		if(tmp == null){ 
			return false;
		}
		long cur = System.currentTimeMillis();
		if((tmp.getMillis() + outTime) < cur){
			//超时移除
			clearCacheObj(key);
			return false;
		}
		return true;
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
