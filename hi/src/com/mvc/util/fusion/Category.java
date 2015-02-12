package com.mvc.util.fusion;




public class Category extends Attribute{
	private String label;
	public Category(String label){
		this.label=label;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	@Override
	public String toString() {
		StringBuffer strings=new StringBuffer();
		strings.append("<category");
		strings.append(this.getAttribute(this));
		strings.append("/>");
		strings.append("\n");
		return strings.toString();
	} 
}
