package com.mvc.constroller;

import org.springframework.beans.propertyeditors.PropertiesEditor;



public class IntegerEditor extends PropertiesEditor {    
          
    @Override
	public void setAsText(String arg0) throws IllegalArgumentException {
    	System.out.println("id:"+arg0+"*****************************************************************");
    	 if (arg0 == null || arg0.equals("")) {    
    		 arg0 = "0";    
         }else{
        	 System.out.println("setvalue:"+arg0+"*****************************************************************");
         	setValue(Integer.parseInt(arg0));    
         }
	}

	@Override
	public String getAsText() {
		return getValue().toString();
	}
 
} 