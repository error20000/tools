package com.tools.utils;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class ResultTools {
	
	public static final Map<String, Object> DEFAULT = new Builder().build();
	
	public static ResultTools.Builder custom(){
		return custom(Tips.ERROR1, "");
	}
	
	public static ResultTools.Builder custom(Tips tips){
		return custom(tips, "");
	}
	
	public static ResultTools.Builder custom(Tips tips, String param){
		Map<String, Object> map =  new HashMap<String, Object>();
		map.put(ResultKey.CODE, tips.getCode());
		map.put(ResultKey.MSG, tips.getDesc(param));
		return new Builder(map);
	}
	
	public static String toJSONString(Map<?, ?> map) {
    	return JSONObject.toJSONString(map);
    }

	public static class Builder {

		private Map<String, Object> map = null;

        Builder() {
            super();
            this.map =  new HashMap<String, Object>();
        }
        
        Builder(Map<String, Object> map) {
            super();
            this.map =  map;
        }
        
        public Builder put(String key, Object value){
    		this.map.put(key, value);
            return this;
        }
        
        public Builder remove(String key){
    		this.map.remove(key);
            return this;
        }
        
        public Builder clear(){
    		this.map.clear();
            return this;
        }
        
        public Map<String, Object> build() {
        	return this.map;
        }
        
        public String toJSONString() {
        	return JSONObject.toJSONString(this.map);
        }
	}
	
	public static void main(String[] args) {
		Map<String, Object> map = ResultTools.custom().put("test1", "test1").put("test2", "test2").put("test3", "test3").put("test1", "test4").build();
		Map<String, Object> map2 = ResultTools.custom().build();
		ResultTools.DEFAULT.put("2", "3");
		Map<String, Object> map3 = ResultTools.DEFAULT;
		map3.put("1", "2");
		Map<String, Object> map4 = ResultTools.DEFAULT;
		System.out.println(map.toString());
		System.out.println(map2.toString());
		System.out.println(map3.toString());
		System.out.println(map4.toString());
	}
}
