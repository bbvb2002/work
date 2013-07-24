package com.example.database;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class Util {
	public static List<String> getFields(Class<?> c){
		Field[] fields = c.getDeclaredFields();
		List<String> result = new ArrayList<String>();
		
		for(int i=0;i<fields.length;i++){
			Field f = fields[i];
			String name =f.getName();
			if(f.getAnnotation(DBAnnotation.class) != null){
				result.add(name);
			}
		}
		
		return result;
	}

}
