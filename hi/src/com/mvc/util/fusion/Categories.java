package com.mvc.util.fusion;




import java.util.ArrayList;

public class Categories extends ArrayList<Category> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		StringBuffer strings=new StringBuffer();
		if(this.size()==0){
			return strings.toString();
		}
		strings.append("\n");
		strings.append("<categories>");
		strings.append("\n");
		for(Category category : this){
			strings.append(category.toString());
		}
		strings.append("</categories>");
		return strings.toString();
	} 
}
