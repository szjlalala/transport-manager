package com.tms.util.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelUtil {

	public static Workbook createXssfworkbook(InputStream inputStream){
		try {
			return new XSSFWorkbook(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Workbook createHssfworkbook(InputStream inputStream){
		try {
			return new HSSFWorkbook(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Sheet getSheet(Workbook workbook ){
		Sheet sheet=null;
		int index=-1;
		if(workbook!=null){
			index=workbook.getActiveSheetIndex();
			sheet=workbook.getSheetAt(index);
		}
		return sheet;
	}
	
	public static List<Map<String,Object>> readExcel(Workbook workbook){
		Map<String,Object> recordMap=null;
		List<Map<String,Object>> recordList=new ArrayList<Map<String,Object>>();
		Sheet sheet=getSheet(workbook);
		int totalRows=-1;
		int totalColumns=-1;
		Row row=null;
		Cell cell=null;
		if(sheet!=null){
			totalRows=sheet.getPhysicalNumberOfRows();
			for(int i=0;i<totalRows;i++){
				row=sheet.getRow(i);
				if(row!=null){
					recordMap=new HashMap<String, Object>();
					for(int j=0;j<totalColumns;j++){
						cell=row.getCell(j);
						if( cell.getCellType()==Cell.CELL_TYPE_BLANK){
							recordMap.put(""+j, "");
						}
						if(cell.getCellType()==Cell.CELL_TYPE_BOOLEAN){
							recordMap.put(""+j, cell.getBooleanCellValue());
						}
						if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
							recordMap.put(""+j, cell.getNumericCellValue());
						}
						if(cell.getCellType()==Cell.CELL_TYPE_STRING){
							recordMap.put(""+j, cell.getStringCellValue());
						}
					}
					recordList.add(recordMap);
				}
			}
		}
		return recordList;
	}
	
	/** 
     * 根据模板生成Excel文件. 
     * @param templateFileName 模板文件. 
     * @param list 模板中存放的数据. 
     * @param resultFileName 生成的文件. 
     */
	public static String writeExcel(HSSFWorkbook workbook, HttpServletRequest request,
                                    String filePath) throws IllegalStateException, IOException {
		File file = new File(Thread.currentThread().getContextClassLoader().getResource("").getFile());
		//文件保存目录路径
		String savePath = file.getParentFile().getParentFile().getAbsolutePath() + File.separator + "static" +  File.separator + "v0" +filePath;
	 
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + File.separator;
		//saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		String newFileName =  "export_"
				+ new Random().nextInt(1000) + ".xls";
		File uploadFile = new File(savePath, newFileName);
		if (!uploadFile.exists()) {
			uploadFile.createNewFile();
		}
		FileOutputStream os = new FileOutputStream(uploadFile);
		workbook.write(os);
		os.close();
		return savePath;
    }  
  
}  

