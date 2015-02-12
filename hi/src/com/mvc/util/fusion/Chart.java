package com.mvc.util.fusion;





/**
 * chart标签
 * 
 * @author jobin
 */
public class Chart extends Attribute{
	/**
     * 主标题
     */
    private String caption;
    private String xAxisName;
    private String yAxisName;
    private String numberPrefix;
    private String showValues="0";
    private String showNames="1";
    private String palette="4";
    private String decimals="0";
    private String enableSmartLabels="1";
    private String enableRotation="0";
    private String bgAlpha="40,100";
    private String showBorder="0";
    private String showFCMenuItem="0";
    private String bgColor="99CCFF,FFFFFF";
    private String baseFont="Arial";
    private String baseFontSize="12";
    private String bgRatio="0,100";
    /**
     * 标题
     * @return
     */
    public String getCaption() {
		return caption;
	}
    /**
     * 标题
     * @param caption
     */
	public void setCaption(String caption) {
		this.caption = caption;
	}
	/**
	 * x轴名称
	 * @return
	 */
	public String getxAxisName() {
		return xAxisName;
	}
	/**
	 * x轴名称
	 * @param xAxisName
	 */
	public void setxAxisName(String xAxisName) {
		this.xAxisName = xAxisName;
	}
	/**
	 * y轴名称
	 * @return
	 */
	public String getyAxisName() {
		return yAxisName;
	}
	/**
	 * y轴名称
	 * @param yAxisName
	 */
	public void setyAxisName(String yAxisName) {
		this.yAxisName = yAxisName;
	}
	/**
	 * 增加数字前缀
	 * @return
	 */
	public String getNumberPrefix() {
		return numberPrefix;
	}
	/**
	 * 增加数字前缀
	 * @param numberPrefix
	 */
	public void setNumberPrefix(String numberPrefix) {
		this.numberPrefix = numberPrefix;
	}
	/**
	 * 是否显示对应的数据值，默认为1(True)
	 * @return
	 */
	public String getShowValues() {
		return showValues;
	}
	/**
	 * 是否显示对应的数据值，默认为1(True)
	 * @param showValues
	 */
	public void setShowValues(String showValues) {
		this.showValues = showValues;
	}
	/**
	 * 是否显示横向坐标轴(x轴)标签名称
	 * @return
	 */
	public String getShowNames() {
		return showNames;
	}
	/**
	 * 是否显示横向坐标轴(x轴)标签名称
	 * @param shownames
	 */
	public void setShowNames(String showNames) {
		this.showNames = showNames;
	}
	public String getPalette() {
		return palette;
	}
	public void setPalette(String palette) {
		this.palette = palette;
	}
	public String getDecimals() {
		return decimals;
	}
	public void setDecimals(String decimals) {
		this.decimals = decimals;
	}
	public String getEnableSmartLabels() {
		return enableSmartLabels;
	}
	public void setEnableSmartLabels(String enableSmartLabels) {
		this.enableSmartLabels = enableSmartLabels;
	}
	public String getEnableRotation() {
		return enableRotation;
	}
	public void setEnableRotation(String enableRotation) {
		this.enableRotation = enableRotation;
	}
	public String getBgAlpha() {
		return bgAlpha;
	}
	public void setBgAlpha(String bgAlpha) {
		this.bgAlpha = bgAlpha;
	}
	public String getShowBorder() {
		return showBorder;
	}
	public void setShowBorder(String showBorder) {
		this.showBorder = showBorder;
	}
	public String getShowFCMenuItem() {
		return showFCMenuItem;
	}
	public void setShowFCMenuItem(String showFCMenuItem) {
		this.showFCMenuItem = showFCMenuItem;
	}
	public Categories getCategories() {
		return categories;
	}
	public void setCategories(Categories categories) {
		this.categories = categories;
	}
	public String getBgColor() {
		return bgColor;
	}
	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}
	public String getBaseFont() {
		return baseFont;
	}
	public void setBaseFont(String baseFont) {
		this.baseFont = baseFont;
	}
	public String getBaseFontSize() {
		return baseFontSize;
	}
	public void setBaseFontSize(String baseFontSize) {
		this.baseFontSize = baseFontSize;
	}
	public String getBgRatio() {
		return bgRatio;
	}
	public void setBgRatio(String bgRatio) {
		this.bgRatio = bgRatio;
	}
	/**
     * Categories
     */
    private Categories categories=new Categories();
    public void addCategory(String... labels){
    	for(String label : labels){
    		Category category=new Category(label);
        	categories.add(category);
    	}
    }
    /**
     * Datasets
     */
    private Datasets datasets=new Datasets();
    public void addDataset(Dataset dataset){
    	datasets.add(dataset);
    }
    /**
     * Sets
     */
    private Sets sets=new Sets();
    public void addSet(String label,String value){
    	Set set=new Set(label,value);
    	addSet(set);
    }
    public void addSet(Set set){
    	sets.add(set);
    }
	@Override
	public String toString() {
		StringBuffer strings=new StringBuffer();
		strings.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		strings.append("\n");
		strings.append("<chart");
		strings.append(this.getAttribute(this));
		strings.append(">");
		strings.append(categories.toString());
		strings.append(datasets.toString());
		strings.append(sets.toString());
		strings.append("</chart>");
		return strings.toString();
	}
}
