package com.mvc.constroller;

import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.RegionUtil;
/**
 * 导出Excel文档工具类
 * @author 那位先生
 * @date 2014-8-6
 * */
public class ExcelUtil {

    /**
     * 创建excel文档，
     * @param list 数据
     * @param keys list中map的key数组集合
     * @param columnNames excel的列名
     * */
    public static Workbook createWorkBook(List<Map<String, Object>> list,String []keys,String columnNames[],String tableName) {
        // 创建excel工作簿
        Workbook wb = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet(list.get(0).get("sheetName").toString());
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for(int i=0;i<keys.length;i++){
            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
        }

        // 创建第一行
        Row row = sheet.createRow((short) 1);

        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();//列单元格样式
        CellStyle cs2 = wb.createCellStyle();//内容单元格样式
        CellStyle cstitle = wb.createCellStyle();//标题单元格样式
        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 20);  //字体大小
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());

      Font f3=wb.createFont();
      f3.setFontHeightInPoints((short) 25);
      f3.setColor(IndexedColors.RED.getIndex());

        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);
        cs.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index); //前景色 LIME
     //   cs.setFillBackgroundColor(HSSFColor.GREY_40_PERCENT.index); //背景色
        cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);			//填充样式，全部填满    前景色和，填充样式不可少
        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);
        
        
        
        //设置 *标题 单元格样式（用于值）
        cstitle.setFont(f3);


        cstitle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cstitle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cstitle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cstitle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cstitle.setAlignment(CellStyle.ALIGN_CENTER);


        
        //设置标题tableName
        Row rowtable = sheet.createRow((short) 0); //从第几行创建标题行
        Cell cells = rowtable.createCell(0);//从第几个单元格开始
        /**
         * 里面的参数是行,列,行,列。刚刚我写的那个就是合并第一行从
		*	第0个单元格到底7个单元格的合并语句
         */
       
      //  sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, (short) 0, 0, (short) 5));//合并第一行六列
        CellRangeAddress region = new CellRangeAddress(0, (short) 0, 0, (short) 5); 
        sheet.addMergedRegion(region); 
        int border = 1;   
            RegionUtil.setBorderBottom(border,region, sheet, wb);   //合并单元格后重新设置边框
           RegionUtil.setBorderLeft(border,region, sheet, wb);   
           RegionUtil.setBorderRight(border,region, sheet, wb);   
            RegionUtil.setBorderTop(border,region, sheet, wb);  
 

        
        cells.setCellStyle(cstitle);
        cells.setCellValue(tableName);


        
        
        //设置列名
        for(int i=0;i<columnNames.length;i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(cs);
        }
        //设置每行每列的值
        for (short i = 2; i < list.size(); i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            Row row1 = sheet.createRow((short) i);
            // 在row行上创建一个方格
            for(short j=0;j<keys.length;j++){
                Cell cell = row1.createCell(j);
                cell.setCellValue(list.get(i).get(keys[j]) == null?" ": list.get(i).get(keys[j]).toString());
                cell.setCellStyle(cs2);
            }
        }
        return wb;
    }

	private static Date setHSSFRichTextString(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}