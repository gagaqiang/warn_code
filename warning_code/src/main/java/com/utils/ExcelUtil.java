package com.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelUtil {

    private static final org.slf4j.Logger log = LoggerFactory
            .getLogger(ExcelUtil.class);

    /** 字段行标识 */
    private final static String FIELD = "FIELD";
    /** 数据行标识 */
    private final static String DATA = "DATA";
    /** 标题行标识 */
    private final static String TITLE = "TITLE";
    /** 日期时间类型名称 */
    private final static String DATE_TYPE_NAME = "Date";
    /** 数字类型名称 */
    private final static String BIGDECIMAL_TYPE_NAME = "BigDecimal";
    /** 数字类型名称 */
    private final static String INTEGER_TYPE_NAME = "Integer";


    /**
     * 导出获取excel流文件
     *
     * @return
     */
    public static void writeDataToExcel(String titleDesc, String sheetName,  String[] fields, String[] titles, List<?> dataList, OutputStream os)
    {
        HSSFWorkbook workbook = null;
        try
        {
            workbook = new HSSFWorkbook();

            HSSFSheet sheet = workbook.createSheet(sheetName);

            setSheetColumnWidth(titles, sheet);

            HSSFCellStyle style = createTitleStyle(workbook);

            if ((dataList != null) && (titles != null) && (fields != null) && (fields.length == titles.length))
            {
                HSSFRow row = sheet.createRow(0);
                for (int i = 0; i < titles.length; i++) {
                    createCell(row, i, null, 1, titles[i]);
                }

                for (int i = 0; i < dataList.size(); i++)
                {
                    Object item = dataList.get(i);

                    HSSFRow newRow = sheet.createRow((short)(i + 1));
                    for (int j = 0; j < fields.length; j++) {
                        StringBuilder getMethod = new StringBuilder("get");
                        getMethod.append(StringUtils.capitalize(fields[j]));
                        Method m = item.getClass().getMethod(getMethod.toString(), new Class[0]);
                        createCell(newRow, j, style, 1, m.invoke(item, new Object[0]));
                    }
                }
            } else {
                createCell(sheet.createRow(0), 0, style, 1, "查无数据");
            }

            workbook.write(os);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            log.error("导出失败>>>" + e.getMessage(), e);
        } finally {
            if (os != null)
                try {
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    log.error("导出失败>>>" + e.getMessage(), e);
                }
        }
    }



    private static void setSheetColumnWidth(String[] titles, Sheet sheet)
    {
        for (int i = 0; i < titles.length; i++)
            sheet.setColumnWidth((short)i, 3000);
    }

    private static HSSFCellStyle createTitleStyle(HSSFWorkbook wb)
    {
        HSSFFont boldFont = wb.createFont();
        boldFont.setFontHeight((short)200);
        HSSFCellStyle style = wb.createCellStyle();
        style.setFont(boldFont);
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("###,##0.00"));
        return style;
    }

    private static void createCell(Row row, int column, CellStyle style, int cellType, Object value)
    {
        Cell cell = row.createCell((short)column);
        if (style != null) {
            cell.setCellStyle(style);
        }
        if ((value instanceof Date)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            cell.setCellValue(sdf.format(value));
        } else {
            switch (cellType) {
                case 3:
                    break;
                case 1:
                    String cellValue = value != null ? new StringBuilder().append(value.toString()).append("").toString() : "";
                    cell.setCellValue(cellValue);
                    break;
                case 0:
                    cell.setCellType(0);
                    if (value != null) {
                        BigDecimal cellNumValue = new BigDecimal(value.toString());
                        cell.setCellValue(cellNumValue.toString());
                    } else {
                        cell.setCellValue("");
                    }
                    break;
                case 2:
            }
        }
    }



    /**
     * 获取解析文件行数据
     * @param fileName : 文件地址
     * @param isTitle  : 是否过滤第一行解析
     * @return
     * @throws Exception
     */
    public static List<Row> getExcelRead(String fileName, InputStream is, boolean isTitle) throws Exception{
        try {
            //判断其兼容版本 调用了判断版本的方法
            Workbook workbook = getWorkbook(fileName,is);
            Sheet sheet = workbook.getSheetAt(0);
            int count = 0;
            List<org.apache.poi.ss.usermodel.Row> list = new ArrayList<Row>();
            for (Row row : sheet) {
                 //跳过第一行的目录
                if (count == 0 && isTitle) {
                    count++;
                    continue;
                }
                list.add(row);
            }
            return list;
        } catch (Exception e) {
            throw e;
        }
    }

    //判断版本的方法
    public static Workbook getWorkbook(String fileName,InputStream is) throws Exception{
        Workbook workbook = null;
        try {
            /** 判断文件的类型，是2003还是2007 */
            if (fileName.endsWith("xls")) {
                workbook = new HSSFWorkbook(is);
            }else if(fileName.endsWith("xlsx")){
                workbook = new XSSFWorkbook(is);
            }
        } catch (Exception e) {
            throw e;
        }
        return workbook;
    }

    //得到celL值的方法：
    public static String getValue(Cell cell){
        if(cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN){
            return String.valueOf(cell.getBooleanCellValue());
        }else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
            double value = cell.getNumericCellValue();
            return new BigDecimal(value).toString();
        }else if (cell.getCellType() ==HSSFCell.CELL_TYPE_STRING){
            return String.valueOf(cell.getStringCellValue());
        }else{
            return String.valueOf(cell.getStringCellValue());
        }
    }


    public static void main(String[] args){
        File file = new File("D:\\work\\auxgroup\\123.xlsx");
        InputStream in = null;
        try {
            System.out.println("以字节为单位读取文件内容，一次读一个字节：");
            // 一次读一个字节
            in = new FileInputStream(file);
            System.out.println(file.getName());

            List<Row> li = getExcelRead(file.getName(), in, false);
            if(CollectionUtils.isNotEmpty(li)){
                for(Row r: li){
                    //得到列的值，也就是你需要解析的字段的值
                    String shopId = ExcelUtil.getValue(r.getCell(0));
                    String refundId = ExcelUtil.getValue(r.getCell(1));
                   System.out.println(shopId + " : "+ refundId);
                }
            }
            in.close();
        } catch (IOException e) {
        } catch (Exception e) {
        }
    }

}
