package com.mvc.util.fusionCharts;



import java.util.Map;

/**
 * chart标签。统计图配置文件的根节点
 * 
 * @author 魏晓亮
 */
public class Chart {

    /**
     * 主标题
     */
    private String caption = "";
    /**
     * 副标题
     */
    private String subCaption = "";
    /**
     * x轴名称
     */
    private String xAxisName = "";
    /**
     * y轴名称
     */
    private String yAxisName = "";
    /**
     * y轴数字前缀
     */
    private String numberPrefix = "";
    /**
     * y轴数字后缀。例如“条”，则数值实际显示为“16条”
     */
    private String numberSuffix = "";
    /**
     * 指定小数位的位数。默认0。可填0-10 例如：='0' 代表取整
     */
    private String decimals = "";

    /**
     * 数据
     */
    private Map<String, String> setTagMap = null;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getSubCaption() {
        return subCaption;
    }

    public void setSubCaption(String subCaption) {
        this.subCaption = subCaption;
    }

    public String getXAxisName() {
        return xAxisName;
    }

    public void setXAxisName(String axisName) {
        xAxisName = axisName;
    }

    public String getYAxisName() {
        return yAxisName;
    }

    public void setYAxisName(String axisName) {
        yAxisName = axisName;
    }

    public String getNumberPrefix() {
        return numberPrefix;
    }

    public void setNumberPrefix(String numberPrefix) {
        this.numberPrefix = numberPrefix;
    }

    public String getNumberSuffix() {
        return numberSuffix;
    }

    public void setNumberSuffix(String numberSuffix) {
        this.numberSuffix = numberSuffix;
    }

    public String getDecimals() {
        return decimals;
    }

    public void setDecimals(String decimals) {
        this.decimals = decimals;
    }

    public Map<String, String> getSetTagMap() {
        return setTagMap;
    }

    public void setSetTagMap(Map<String, String> setTagMap) {
        this.setTagMap = setTagMap;
    }

}
