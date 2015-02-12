package com.mvc.util.fusion;



import java.util.ArrayList;

public class Datasets extends ArrayList<Dataset>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void addDataset(Dataset dataset){
		this.add(dataset);
	}
	@Override
	public String toString() {
		StringBuffer strings=new StringBuffer();
		if(this.size()==0){
			return strings.toString();
		}
		for(Dataset dataset : this){
			strings.append(dataset.toString());
		}
		return strings.toString();
	} 
}
