package com.mvc.util.fusionCharts;

import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

/**
 * FusionCharts插件辅助工具
 * 
 * @author 魏晓亮
 */
public class FusionChartsUtil {

    /**
     * 将统计图对象转换为插件所需的xml
     * 
     * @param chart
     * @param appendChartTag 此字符串将追加到chart标签内
     * @param appendChartBody 此字符串将追加到chart标签体
     * @return
     * @author 赵映刚
     */
    public static String ChartTag2XML(Chart chart, String appendChartTag, String appendChartBody) {
        // 统计数据
        StringBuilder data = new StringBuilder();
        Long maxValue = 0L;
        Long minValue = Long.MAX_VALUE;
        for (Entry<String, String> setTag : chart.getSetTagMap().entrySet()) {
            data.append("<set label='" + setTag.getKey() + "' value='" + setTag.getValue() + "' />");
            // 筛选出y轴最大最小值
            maxValue = Math.max(maxValue, Long.parseLong(setTag.getValue()));
            minValue = Math.min(minValue, Long.parseLong(setTag.getValue()));
        }
        // 统计图
        StringBuilder chartXML = new StringBuilder();
        chartXML.append("<chart");
        // 主要参数
        if (StringUtils.isNotBlank(chart.getCaption())) {
            chartXML.append(" caption='" + chart.getCaption() + "'");
        }
        if (StringUtils.isNotBlank(chart.getSubCaption())) {
            chartXML.append(" subcaption='" + chart.getSubCaption() + "'");
        }
        if (StringUtils.isNotBlank(chart.getXAxisName())) {
            chartXML.append(" xAxisName='" + chart.getXAxisName() + "'");
        }
        if (StringUtils.isNotBlank(chart.getYAxisName())) {
            chartXML.append(" yAxisName='" + chart.getYAxisName() + "'");
        }
        if (StringUtils.isNotBlank(chart.getNumberSuffix())) {
            chartXML.append(" numberSuffix='" + chart.getNumberSuffix() + "'");
        }
        if (StringUtils.isNotBlank(chart.getDecimals())) {
            chartXML.append(" decimals='" + chart.getDecimals() + "'");
        }
        // 自动计算出合适的y轴规格
        chartXML.append(" yAxisMaxValue='" + getSuitableMaxY(maxValue) + "'");
        chartXML.append(" yAxisMinValue='" + getSuitableMinY(minValue) + "' ");
        chartXML.append(appendChartTag + " >");// 追加设置
        // 设置数据
        chartXML.append(data);
        chartXML.append(appendChartBody);// 追加设置
        chartXML.append("</chart>");
        return chartXML.toString();
    }

    /**
     * 算出合适的图表规格参数:y轴最大值
     * 
     * @param maxValue y轴实际数据的最大值
     * @return y轴最大值(yMaxValue参数)
     * @author 赵映刚
     */
    public static Long getSuitableMaxY(Long maxValue) {
        double yAxisMaxValue = 10;// 最大值
        double[] times = {2, 2, 1.25, 1.6, 1.25};// 增长倍数
        int i = 0;
        while (yAxisMaxValue < maxValue) {
            yAxisMaxValue = yAxisMaxValue * times[i % 5];// 最大值设为10、20、40、50、80、100...
            i++;// i在0、1、2、3、4之间交替
        }
        return Long.valueOf((long) yAxisMaxValue);
    }

    /**
     * 算出合适的图表规格参数:y轴最小值
     * 
     * @param minValue y轴实际数据的最大值
     * @return y轴最小值(yMinValue参数)
     * @author 赵映刚
     */
    public static Long getSuitableMinY(Long minValue) {
        double yAxisMinValue = 0;// 最小值
        double explorer = 10;// 试探值
        double[] times = {5, 2};// 增长倍数
        int i = 0;
        while (explorer < minValue) {
            yAxisMinValue = explorer;// 试探成功，进行赋值
            explorer = explorer * times[i % 2];// 最小值设为0、10、50、100、500...
            i++;// i在0、1、2、3、4之间交替
        }
        return Long.valueOf((long) yAxisMinValue);
    }
}
