package com.mvc.util.fusion;



import java.util.ArrayList;

public class Sets extends ArrayList<Set>{
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
		for(Set set : this){
			strings.append(set.toString());
		}
		return strings.toString();
	} 
}
