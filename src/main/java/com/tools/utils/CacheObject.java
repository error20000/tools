package com.tools.utils;

public class CacheObject {

	private String key;		//key
	private long millis;	//缓存时间
	private Object value;	//value
	
	public CacheObject(String key, Object value) {
		this.key = key;
		this.value = value;
	}
	
	public CacheObject() {
	}
	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public long getMillis() {
		return millis;
	}
	public void setMillis(long millis) {
		this.millis = millis;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
}
