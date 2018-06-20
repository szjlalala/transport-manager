package com.tms.util.excel;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 利用开源组件POI3.0.2动态导出EXCEL文档 转载时请保留以下信息，注明出处！
 *
 * @param <T> 应用泛型，代表任意一个符合javabean风格的类
 *            注意这里为了简单起见，boolean型的属性xxx的get器方式为getXxx(),而不是isXxx()
 *            byte[]表jpg格式的图片数据
 */
public class ExportExcel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private HSSFWorkbook workbook;
    /**
     * 工作簿最大数据数
     */
    private int maxSheetNumber = 50000;

    public HSSFWorkbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(HSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    /**
     * 构造函数 创建单工作表的excel
     *
     * @param title   工作表名称
     * @param headers 表头: 格式[String[属性名][显示名]. 如： [name][张三], [email][example@example.com], [createTime][2014-09-04]]
     * @param dataset 数据: List<map>格式[key属性名, value显示值. 如： name=张三, email=example@example.com, createTime=2014-09-04]
     */
    public ExportExcel(String title, String[][] headers, List<Map<String, String>> dataset) {
        createExcel(title, headers, dataset);
    }

    public ExportExcel() {
        this.workbook = createWorkbook();
    }

    ;


    /**
     * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
     *
     * @param title   表格标题名
     * @param headers 表格属性列名数组
     * @param dataset 需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
     *                javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
     * @param pattern 如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
     */
    private void createExcel(String title, String[][] headers, List<Map<String, String>> dataset) {
        if (headers == null || headers.length < 1) {
            return;
        }
        /** 创建一个excel */
        this.workbook = createWorkbook();

        createSheet(title, headers, dataset);
    }


    public void createSheet(String title, String[][] headers, List<Map<String, String>> dataset) {

        /** 创建一个工作表sheet */
        HSSFSheet sheet = null;

        /** 给excel定义一个样式 */
        HSSFCellStyle style = createStyle(workbook);

        List<List<Map<String, String>>> groupDataList = getGroupData(dataset);
        int size = groupDataList.size();
        for (int i = 0; i < size; i++) {
            sheet = createSheet(size == 1 ? title : title + "_" + (i + 1));
            /** 创建标题行 并设置样式 */
            createCell(sheet, style, headers);
            /** 创建数据行 */
            createRow(sheet, groupDataList.get(i), headers);
        }

    }


    /**
     * 分组数据，保证数据可以在一个excel表中存储
     *
     * @param dataset
     * @return
     */
    private List<List<Map<String, String>>> getGroupData(List<Map<String, String>> dataset) {
        List<List<Map<String, String>>> data = new ArrayList<List<Map<String, String>>>();
        if (dataset.size() <= maxSheetNumber) {
            data.add(dataset);
            return data;
        }

        List<Map<String, String>> currentData = null;
        System.out.println("总数：" + dataset.size());

        for (int i = 0; i < dataset.size(); i++) {

            if (currentData == null || currentData.size() >= maxSheetNumber) {
                currentData = new ArrayList<Map<String, String>>();
                data.add(currentData);
            }
            currentData.add(dataset.get(i));
        }

        return data;
    }

    /**
     * 合并
     *
     * @param list
     */
    public void mergin(List<Integer[]> list) {

        HSSFSheet sheet = getWorkbook().getSheetAt(0);
        for (Integer[] i : list) {
            sheet.addMergedRegion(new CellRangeAddress(i[0], (short) i[1].intValue(), i[2], (short) i[3].intValue()));
        }
        //	this.workbook.getSheetAt(0).addMergedRegion(new Region(1, (short) 0, 2, (short) 0));
        //	this.workbook.getSheetAt(0).setColumnHidden(1, true);
    }

    /**
     * 隐藏列
     *
     * @param list
     */
    public void hiddenColumn(Integer[] cols) {

        HSSFSheet sheet = getWorkbook().getSheetAt(0);
        for (Integer col : cols) {
            sheet.setColumnHidden(col, true);
        }
        //	this.workbook.getSheetAt(0).addMergedRegion(new Region(1, (short) 0, 2, (short) 0));
        //	this.workbook.getSheetAt(0).setColumnHidden(1, true);
    }


    /**
     * 设置特殊数据行 居中/设置背景
     *
     * @param list
     */
    public void setSpecialRow(int sheetNum, String[] data) {

        HSSFSheet sheet = getWorkbook().getSheetAt(sheetNum);
        int column = sheet.getRow(0).getPhysicalNumberOfCells();
        sheet.shiftRows(0, sheet.getLastRowNum(), data.length);

        //要合并的数据 (数组: 起始行号，终止行号， 起始列号，终止列号 )
        List<Integer[]> merginList = new ArrayList<Integer[]>();

        for (int i = 0; i < data.length; i++) {
            sheet.createRow(i);
            sheet.getRow(i).createCell(0).setCellValue(data[i]);
            merginList.add(new Integer[]{i, i, 0, column});
        }

        mergin(merginList);
    }

    /**
     * 清除行格式
     *
     * @param rowNum
     */
    public void clearStyle(int rowNum) {

        HSSFSheet sheet;
        HSSFRow row;
        HSSFCellStyle style = workbook.createCellStyle();

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {//获取每个Sheet表
            sheet = getWorkbook().getSheetAt(i);
            row = sheet.getRow(i == 0 ? rowNum : 0);
            for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                row.getCell(j).setCellStyle(style);
            }
        }
    }

    public int getMaxSheetNum() {
        return getWorkbook().getNumberOfSheets();
    }


    private void createRow(HSSFSheet sheet, List<Map<String, String>> dataset, String[][] headers) {
        // 遍历集合数据，产生数据行
        int index = 0;
        HSSFRow row;
        //每行数据
        for (Map<String, String> dataMap : dataset) {
            index++;
            row = sheet.createRow(index);
            for (int j = 0; j < headers.length; j++) {

                HSSFCell cell2 = row.createCell(j);
                cell2.setCellValue(dataMap.get(headers[j][0]));
            }
            //	HSSFCell cell2 = row.createCell(headers.length);
            //		cell2.setCellValue(dataMap.get("createTime"));
        }
    }


    private HSSFRow createCell(HSSFSheet sheet, HSSFCellStyle style, String[][] headers) {
        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell1 = row.createCell(i);
            cell1.setCellStyle(style);
            HSSFRichTextString text;
            if (headers[i][1] == null || "".equals(headers[i][1].trim())) {
                text = new HSSFRichTextString(headers[i][0]);
            } else {
                text = new HSSFRichTextString(headers[i][1]);
            }
            cell1.setCellValue(text);
        }
        return row;
    }

    public static HSSFCellStyle createStyle(HSSFWorkbook workbook) {
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式  
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体  
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.WHITE.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式  
        style.setFont(font);
        // 生成并设置另一个样式  
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体  
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式  
        style2.setFont(font2);
        return style;
    }

    private HSSFSheet createSheet(String title) {
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节  
        sheet.setDefaultColumnWidth(10);

        return sheet;
    }

    // 声明一个工作薄
    private HSSFWorkbook createWorkbook() {
        return new HSSFWorkbook();
    }


    /**
     * 保存
     *
     * @param response
     * @param excelName
     * @throws IOException
     */
    public void save(HttpServletResponse response, String saveDir, String excelName) throws IOException {
		/*OutputStream ouputStream = response.getOutputStream();
		response.setContentType("application/vnd.ms-excel");    
        response.setHeader("Content-disposition", "attachment;filename=" + excelName);
    	getWorkbook().write(ouputStream);
    	ouputStream.flush();*/

        File file = new File(saveDir);
        file.mkdir();
        file = new File(saveDir + "/" + excelName);
        file.createNewFile();
        FileOutputStream outputStream = new FileOutputStream(file);
        getWorkbook().write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    public void save(String saveDir, String excelName) throws IOException {

        File file = new File(saveDir);
        file.mkdir();
        file = new File(saveDir + "/" + excelName);
        file.createNewFile();
        FileOutputStream outputStream = new FileOutputStream(file);
        getWorkbook().write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    /**
     * 导出
     *
     * @param response
     * @param excelName
     * @throws IOException
     */
    public void export(HttpServletResponse response, HttpServletRequest request, String excelName) throws IOException {
        OutputStream ouputStream = response.getOutputStream();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/vnd.ms-excel");

        String encodedFileName = null;
        String userAgentString = request.getHeader("User-Agent");
        String browser = UserAgent.parseUserAgentString(userAgentString).getBrowser().getGroup().getName();
        if (browser.equals("Chrome") || browser.equals("Internet Exploer") || browser.equals("Safari")) {
            encodedFileName = URLEncoder.encode(excelName, "utf-8").replaceAll("\\+", "%20");
        } else {
            encodedFileName = MimeUtility.encodeWord(excelName);
        }
        //设置Content-Disposition响应头，一方面可以指定下载的文件名，另一方面可以引导浏览器弹出文件下载窗口
        response.setHeader("Content-Disposition", "attachment;fileName=\"" + encodedFileName + ".xls\"");

        getWorkbook().write(ouputStream);
        ouputStream.flush();
    }

    /**
     * 导出
     *
     * @param response
     * @param excelName
     * @throws IOException
     */
    public static void export(HttpServletResponse response, String excelName, HSSFWorkbook workbook) throws IOException {
        OutputStream ouputStream = response.getOutputStream();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + excelName);
        workbook.write(ouputStream);
        ouputStream.flush();
    }
}

