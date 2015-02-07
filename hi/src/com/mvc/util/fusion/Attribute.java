package com.mvc.util.fusion;
import java.lang.reflect.Field;

public class Attribute {
	/**
	 * 
	 * @return
	 */
	protected String getAttribute(Object object){
		StringBuffer strings=new StringBuffer();
		Field[] fields = object.getClass().getDeclaredFields();
		try {
			for(Field field : fields){
				if(field.getType()==String.class){
					field.setAccessible(true);
					Object obj = field.get(object);
					if(obj==null){
						continue;
					}
					strings.append(" ");
					strings.append(field.getName());
					strings.append("=");
					strings.append("'").append(obj).append("'");
					field.setAccessible(false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strings.toString();
	}
}
