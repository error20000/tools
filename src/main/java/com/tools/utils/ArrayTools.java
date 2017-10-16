package com.tools.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSONArray;

public class ArrayTools {
	
	public static final List<Object> DEFAULT = new Builder<Object>().build();
	
	public static <T> ArrayTools.Builder<T> custom(T type){
		return new Builder<T>();
	}
	
	public static <T> T[] concat(T[] dist, T[]... array){
		List<T> list = new ArrayList<T>();
		list.addAll(Arrays.asList(dist));
		
		for (int i = 0; i < array.length; i++) {
			list.addAll(Arrays.asList(array[i]));
		}
		return list.toArray(dist);
	}
	
	public static String toJSONString(List<?> list){
		return JSONArray.toJSONString(list);
	}
	
	public static class Builder<T> {

		private List<T> list = null;

        Builder() {
            this.list =  new ArrayList<>();
        }
		
        Builder(List<T> list) {
            this.list = list;
        }
        
        public Builder<T> add(T e){
    		this.list.add(e);
            return this;
        }
        
		public Builder<T> remove(T e){
    		this.list.remove(e);
            return this;
        }
        
        public Builder<T> clear(){
    		this.list.clear();
            return this;
        }
        
        public List<T> build() {
        	return this.list;
        }
        
        public String toJSONString() {
        	return JSONArray.toJSONString(this.list);
        }
	}
	
	public static void main(String[] args) {
		List<String> list = ArrayTools.custom(new String()).add("test1").add("test2").add("test3").add("test4").build();
		List<String> list2 = ArrayTools.custom(new String()).build();
		ArrayTools.DEFAULT.add("2");
		List<Object> list3 = ArrayTools.DEFAULT;
		list3.add("3");
		List<Object> list4 = ArrayTools.DEFAULT;
		System.out.println(list.toString());
		System.out.println(list2.toString());
		System.out.println(list3.toString());
		System.out.println(list4.toString());
	}
}
