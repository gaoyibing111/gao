package com.mvc.util.fusion;

public class Set extends Attribute{
	private String name;
	private String label;
	private String value;
	private String isSliced;
	public Set(){
		
	}
	public Set(String label,String value){
		this.label=label;
		this.value=value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Set(String value){
		this.value=value;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getIsSliced() {
		return isSliced;
	}
	public void setIsSliced(String isSliced) {
		this.isSliced = isSliced;
	}
	@Override
	public String toString() {
		StringBuffer strings=new StringBuffer();
		strings.append("<set");
		strings.append(this.getAttribute(this));
		strings.append("/>");
		strings.append("\n");
		return strings.toString();
	} 
}
