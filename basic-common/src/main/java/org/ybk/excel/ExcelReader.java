package org.ybk.excel;

import java.io.*;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ybk.basic.util.Util;

// 处理excel数据文件，返回一个list，每一行内容用一个List<String>保存
public class ExcelReader {

	// public static final String SEPERATOR = "###";
	private String filePath;
	private String encoding = "GBK";
	private String sheetName;
	private int startDataPos = 1;

	public ExcelReader(String filePath, String sheetName, int startDataPos) {
		this.filePath = filePath;
		this.sheetName = sheetName;
		this.startDataPos = startDataPos;
	}

	public ExcelReader(String filePath, String sheetName) {
		this.filePath = filePath;
		this.sheetName = sheetName;
	}

	public ExcelReader(String filePath, String sheetName, String encoding,int startDataPos) {
		this.filePath = filePath;
		this.sheetName = sheetName;
		this.encoding = encoding;
		this.startDataPos = startDataPos;
	}

	public ExcelReader(String filePath, String sheetName, String encoding) {
		this.filePath = filePath;
		this.sheetName = sheetName;
		this.encoding = encoding;
	}

	public Map<Integer, List<List<String>>> loadCSVData() {

		FileInputStream inStream = null;
		BufferedReader br = null;
		Map<Integer, List<List<String>>> datas = new HashMap<Integer, List<List<String>>>();
		try {
			inStream = new FileInputStream(new File(filePath));
			InputStreamReader reader = new InputStreamReader(inStream,encoding);
			br = new BufferedReader(reader);

			String line = null;
			StringTokenizer st = null;
			List<List<String>> sdatas = new ArrayList<List<String>>();
			int totalColumn = 0;
			int p = 0;
			String ele = null;
			while ((line = br.readLine()) != null) {
				if(Util.isNull(line))
					continue;
				if(line.startsWith("订单编号")){
					st = new StringTokenizer(line,",");
					while(st.hasMoreElements()){
						st.nextElement();
						totalColumn++;
					}
					continue;
				}
				int curColumn = 0;
				p = 0;
				List<String> lineData = new ArrayList<String>();
				while(p>=0){
					p = line.indexOf(",");
					if(p>=0){
						ele = line.substring(0,p);
						line = line.substring(p+1);
					}else{
						ele = line;
					}
					lineData.add(ele);
					curColumn++;
				}
				for(int i=curColumn;i<totalColumn;i++)
					lineData.add("#EMPTY#");
				sdatas.add(lineData);
			}
			//为了保持数据结构统一
			datas.put(0, sdatas);
		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (inStream != null) {
					inStream.close();
				}
			} catch (IOException e) {
				// e.printStackTrace();
			}
		}
		return datas;
	}

	public Map<Integer, List<List<String>>> loadData() {
		FileInputStream inStream = null;
		Sheet s = null;
		Map<Integer, List<List<String>>> datas = new HashMap<Integer, List<List<String>>>();
		try {
			inStream = new FileInputStream(new File(filePath));
			HSSFWorkbook workBook = new HSSFWorkbook(inStream);
			if (Util.isNull(sheetName)) {
				int sheetNum = workBook.getNumberOfSheets();
				for (int j = 0; j < sheetNum; j++) {
					s = workBook.getSheetAt(j);
					List<List<String>> lineData = getSheetData(s, startDataPos);
					datas.put(j, lineData);
				}
			} else {
				s = workBook.getSheet(sheetName);
				if (s == null)
					return datas;
			}
		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			try {
				if (inStream != null) {
					inStream.close();
				}
			} catch (IOException e) {
				// e.printStackTrace();
			}
		}
		return datas;
	}

	private String getCellValue(Cell cell) {
		String cellValue = "";
		DataFormatter formatter = new DataFormatter();
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					// cellValue = formatter.formatCellValue(cell);
					cellValue = formatter.formatRawCellContents(cell.getNumericCellValue(), 0, "yyyy-MM-dd HH:mm:ss");
					// cellValue = cell.getStringCellValue();
				} else {
					double value = cell.getNumericCellValue();
					long intValue = (long) value;
					cellValue = value - intValue == 0 ? String.valueOf(intValue) : String.valueOf(value);
				}
				break;
			case Cell.CELL_TYPE_STRING:
				cellValue = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				cellValue = String.valueOf(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_FORMULA:
				cellValue = String.valueOf(cell.getCellFormula());
				break;
			case Cell.CELL_TYPE_BLANK:
				cellValue = "";
				break;
			case Cell.CELL_TYPE_ERROR:
				cellValue = "";
				break;
			default:
				cellValue = cell.toString().trim();
				break;
			}
		}
		return cellValue.trim();
	}

	// startDataRow数据项其起始位置，第一行伟1
	private List<List<String>> getSheetData(Sheet sheet, int startDataRow) {
		if (startDataRow <= 0)
			startDataRow = 0;
		int numOfRows = sheet.getLastRowNum() + 1;
		List<List<String>> listData = new ArrayList<List<String>>();
		for (int i = startDataRow - 1; i < numOfRows; i++) {
			Row row = sheet.getRow(i);
			List<String> list = new ArrayList<String>();
			if (row != null) {
				for (int j = 0; j < row.getLastCellNum(); j++) {
					Cell cell = row.getCell(j);
					list.add(this.getCellValue(cell));
				}
			}
			listData.add(list);
		}
		return listData;
	}

	//获得一个excel所有sheet的内容
	public static List<List<List<String>>> parseContent(String path){
		return parseContent(path,null,null);
	}
	
	//获得一个excel所有sheet的内容
	public static List<List<List<String>>> parseContent(String path,String startTag,int[] posArr){
		
		List<List<List<String>>> allRows = new ArrayList<List<List<String>>>();
		List<SheetHead> heads = parseHeadLines(path,startTag);
		if(heads == null || heads.size() == 0)
			return allRows;
		
		Hashtable<Integer,String> posHash = new Hashtable<Integer,String>();
		if(posArr != null){
			for(int i=0;i<posArr.length;i++)
				posHash.put(posArr[i], "Y");
		}
		
		int sheetType = 0;
		if(path.endsWith(".xlsx")){
			sheetType = 0;
		}else
			sheetType = 1;
		
		InputStream input = null;
		try {
			input = new FileInputStream(path);
			//POIFSFileSystem fs = new POIFSFileSystem(input);
			HSSFWorkbook xwb = null;
			XSSFWorkbook xxwb = null;
			int sheetNum = 0;
			if(path.endsWith(".xlsx")){
				xxwb = new XSSFWorkbook(input);
				sheetNum = xxwb.getNumberOfSheets();
			}else{
				xwb = new HSSFWorkbook(input);
				sheetNum = xwb.getNumberOfSheets();
			}
			
			Sheet sheet = null;
			Row row = null;
			Cell cell = null;
			String cellStr = null;
			for (int s = 0; s < sheetNum; s++) {
				
				if(s>=heads.size())
					continue;
				
				SheetHead head = heads.get(s);
				if(sheetType == 0)
					sheet =  xxwb.getSheetAt(s);
				else
					sheet = xwb.getSheetAt(s);
				List<List<String>> sheetLines = new ArrayList<List<String>>();
				for (int r = head.getStartRow()+1; r < sheet.getLastRowNum(); r++) {
					List<String> lines = new ArrayList<String>();
					row = sheet.getRow(r);
					if (row == null)
						continue;
					for (int c = head.getStartCell(); c < row.getLastCellNum(); c++) {
						cell = row.getCell(c);
						
						if(posHash.size() == 0 || posHash.containsKey(c - head.getStartCell())){
							if (cell == null){
								lines.add("");
							}else{
								cellStr = getCellStrValue(cell);
								lines.add(cellStr);
							}
						}
					}
					sheetLines.add(lines);
				}
				allRows.add(sheetLines);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(input != null)
					input.close();
			}catch(IOException ioe){}
		}
		return allRows;
	}
	
	//获得一个excel所有sheet的第一行
	public static List<SheetHead> parseHeadLines(String path,String startTag){
		
		List<SheetHead> allHeadLines = new ArrayList<SheetHead>();
		if (Util.isNull(path))
			return allHeadLines;
		if (!path.endsWith(".xls") && !path.endsWith(".xlsx"))
			return allHeadLines;
		
		if(Util.isNull(startTag))
			startTag = "";

		int sheetType = 0;
		if(path.endsWith(".xlsx")){
			sheetType = 0;
		}else
			sheetType = 1;
		
		InputStream input = null;
		try {
			input = new FileInputStream(path);
			HSSFWorkbook xwb = null;
			XSSFWorkbook xxwb = null;
			int sheetNum = 0;
			if(path.endsWith(".xlsx")){
				xxwb = new XSSFWorkbook(input);
				sheetNum = xxwb.getNumberOfSheets();
			}else{
				//POIFSFileSystem fs = new POIFSFileSystem(input);
				xwb = new HSSFWorkbook(input);
				sheetNum = xwb.getNumberOfSheets();
			}
			
			Sheet sheet = null;
			Row row = null;
			Cell cell = null;
			String cellStr = null;
			for (int s = 0; s < sheetNum; s++) {
				
				if(sheetType == 0)
					sheet =  xxwb.getSheetAt(s);
				else
					sheet = xwb.getSheetAt(s);
				int rowStart = 0;
				int cellStart = 0;
				boolean foundCellStart = false;
				
				int firstNum = sheet.getFirstRowNum();
				int lastNum = sheet.getLastRowNum();
				for (rowStart = firstNum; rowStart < lastNum; rowStart++) {
					row = sheet.getRow(rowStart);
					if (row == null)
						continue;
					cellStart = row.getFirstCellNum();
					int cellEnd = row.getLastCellNum();
					for (int c = cellStart; c < cellEnd; c++) {
						cell = row.getCell(c);
						if (cell == null)
							continue;

						cellStr = getCellStrValue(cell);
						/*if (Util.isNotNull(cellStr)) {
							cellStart = c;
							foundCellStart = true;
							break;
						}*/
						if(startTag.equals(cellStr)) {
							cellStart = c;
							foundCellStart = true;
							break;
						}
					}
					if (foundCellStart)
						break;
				}
				
				SheetHead head = new SheetHead(rowStart,cellStart);
				row = sheet.getRow(rowStart);
				if(row == null)
					continue;
				
				// 假设头开始，下一行就是数据
				for (int c = cellStart; c < row.getLastCellNum(); c++) {
					cell = row.getCell(c);
					if (cell == null){
						cellStr = "";
					}else{
						cellStr = getCellStrValue(cell);
					}
					head.getHeads().add(cellStr);
				}
				allHeadLines.add(head);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
		}finally{
			try{
				if(input != null)
					input.close();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
		}
		return allHeadLines;
	}
	
	//获得一个excel所有sheet的第一行
	public static List<SheetHead> parseHeadLines(String path){
		return parseHeadLines(path,"");
	}

	// 获得cell的值
	private static String getCellStrValue(Cell cell) {
		if (cell == null)
			return "";
		String cellStr = "";
		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			cellStr = cell.getStringCellValue();
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			double cellVl = cell.getNumericCellValue();
			if (cellVl != 0.0) {
				if (Math.abs(cellVl) >= 1)
					cellStr = Double.valueOf(cellVl).intValue() + "";
				else
					cellStr = cellVl + "";
			} else {
				cellStr = "";
			}
		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			boolean cellVl = cell.getBooleanCellValue();
			if (cellVl)
				cellStr = "true";
			else
				cellStr = "false";
		}
		return cellStr;
	}
		
	public static void main(String[] args) {
		String path = "E:\\docs\\题库大王\\证券\\证券.xlsx";
		ExcelReader eh = new ExcelReader(path, null);
		List<List<List<String>>> datas = eh.parseContent(path);
		for (List<List<String>> shdata : datas) {
			for (List<String> shdata2 : shdata) {
				for (String shdata3 : shdata2) {
					System.out.print(shdata3);
					System.out.print("\t");
				}
				System.out.println();
			}
		}
		/*Map<Integer, List<List<String>>> datas = eh.loadData();
		int sheetNum = 0;
		while (datas.containsKey(sheetNum)) {
			List<List<String>> sheetData = datas.get(sheetNum);
			for (List<String> lndata : sheetData) {
				StringBuffer buf = new StringBuffer();
				for (int i = 0; i < lndata.size(); i++) {
					buf.append(lndata.get(i)).append(",");
				}
				System.out.println(buf);
			}
			sheetNum++;
		}*/
	}
}