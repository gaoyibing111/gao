package com.mvc.util.fusion;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dataset extends ArrayList<Set>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String seriesName;
	private String renderAs;
	private String showValues;
	public String getSeriesName() {
		return seriesName;
	}
	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}
	public String getRenderAs() {
		return renderAs;
	}
	public void setRenderAs(String renderAs) {
		this.renderAs = renderAs;
	}
	public String getShowValues() {
		return showValues;
	}
	public void setShowValues(String showValues) {
		this.showValues = showValues;
	}
	/**
	 * 
	 * @param value
	 */
	public void addSet(String value){
		Set set=new Set(value);
		this.add(set);
	}
	public void addSet(String... values){
		addSet(Arrays.asList(values));
	}
	public void addSet(List<String> values){
		for(String value : values){
			Set set=new Set(value);
			this.add(set);
		}
	}
	@Override
	public String toString() {
		Attribute attribute=new Attribute();
		StringBuffer strings=new StringBuffer();
		strings.append("\n");
		strings.append("<dataset ");
		strings.append(attribute.getAttribute(this));
		strings.append(">");
		strings.append("\n");
		for(Set set : this){
			strings.append(set.toString());
		}
		strings.append("</dataset>");
		return strings.toString();
	} 
	
}
