package com.example.database;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.util.Pair;


public class Util {
	public static List<Pair<String,String>> getFields(Class<?> c){
		Field[] fields = c.getDeclaredFields();
		List<Pair<String,String>> result = new ArrayList<Pair<String,String>>();
		
		for(int i=0;i<fields.length;i++){
			Field f = fields[i];
			String name = f.getName();
			String type = ParseType(f);
			
			DBAnnotation info = f.getAnnotation(DBAnnotation.class);
			
			if(info != null){
				if("_default".equals(info.name())==false){
					name = info.name();
					
				}
				if("_default".equals(info.type())==false){
					type=info.type();
				}
				Pair<String,String> pair = Pair.create(name, type);	
				result.add(pair);
			}
		}
		
		return result;
	}

	public static final String SQLITE_INT = "INTEGER";
	public static final String SQLITE_TEXT = "TEXT";
	public static final String SQLITE_REAL = "REAL";
	public static final String SQLITE_BLOB = "BLOB";
	
	private static String ParseType(Field field){
		Class<?> cls = field.getType();
		
		if(cls == String.class){
			return SQLITE_TEXT;
		}
		else if(cls==short.class || cls==int.class || cls==long.class || cls==boolean.class){
			return SQLITE_INT;
		}
		else if(cls==float.class || cls==double.class){
			return SQLITE_REAL;
		}
		else if(cls==byte[].class){
			return SQLITE_BLOB;
		}
		return null;
	}
	
	public static ContentValues parseContentValues(Object obj){
		List<Pair<String,String>> fields = getFields(obj.getClass());
		Class<?> cls = fields.getClass();
		ContentValues contentvalues = new ContentValues();
		
		for(Pair<String,String> field : fields){
			try {
				Field f = cls.getDeclaredField(field.first);
				Class<?> type = f.getType();
				if(type == int.class)
				{
					contentvalues.put(field.first,f.getInt(obj));
				}
				else if(type == String.class)
				{
					contentvalues.put(field.first,(String) f.get(obj));
				}
				else if(type == double.class)
				{
					contentvalues.put(field.first,f.getDouble(obj));
				}
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		return null;
	}
}

