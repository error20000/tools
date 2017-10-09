package com.tools.utils;

import java.util.ArrayList;
import java.util.List;

public class ListTools {
	
	public static final List<Object> DEFAULT = new Builder<Object>().build();
	
	public static <T> ListTools.Builder<T> custom(T type){
		return new Builder<T>();
	}
	
	public static class Builder<T> {

		private List<T> list = null;

        Builder() {
            this.list =  new ArrayList<>();
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
	}
	
	public static void main(String[] args) {
		List<String> list = ListTools.custom(new String()).add("test1").add("test2").add("test3").add("test4").build();
		List<String> list2 = ListTools.custom(new String()).build();
		ListTools.DEFAULT.add("2");
		List<Object> list3 = ListTools.DEFAULT;
		list3.add("3");
		List<Object> list4 = ListTools.DEFAULT;
		System.out.println(list.toString());
		System.out.println(list2.toString());
		System.out.println(list3.toString());
		System.out.println(list4.toString());
	}
}
