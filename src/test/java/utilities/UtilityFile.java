package utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UtilityFile {
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook wb;
	public XSSFSheet ws;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
	public UtilityFile(String pa)//Constructor...
	{
		this.path=pa;
	}

	public int getRowCount(String sheet) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheet);
		int rowcount = ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;
	}

	public int GetCellCount(String Sheet, int rownum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(Sheet);
		row= ws.getRow(rownum);
		int cellcount =row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
	}

	public String GetCellData(String sheet, int rownum, int column) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheet);
		row= ws.getRow(rownum);
		cell=row.getCell(column);
		String Data;
//		String data=cell.toString();
		try {
			// data=cell.toString();
			DataFormatter formatter = new DataFormatter();
			Data = formatter.formatCellValue(cell);// return formatted value of cell as String regardless of CellType
		} catch (Exception e) {
			Data = ""; // If cell doesn't have data, catch block will handle it & make dataValue is
					// empty...ReturnNothing.
		}
		wb.close();
		fi.close();
		return Data;
	}
//File & Workbook is not separate Entity,along with File, Workbook will be created

	public void SetCellValue(String sheet, int rownum, int column, String data) throws IOException {
		File xlfile=new File(path);//if File not exist,then Create new File...
		if(!xlfile.exists())
		{
			wb=new XSSFWorkbook(fi);
			fo=new FileOutputStream(path);//file path
			wb.write(fo);
		}
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		if(wb.getSheetIndex(sheet)==-1)//If sheet is not exist,then create new Sheet...
		{
			wb.createSheet(sheet);
			ws = wb.getSheet(sheet);
		}
		if(ws.getRow(rownum)==null)//If Row is not exist then create new Row...
		{
			ws.createRow(rownum);
			row=ws.getRow(rownum);
			cell=row.createCell(column);
			cell.setCellValue(data);
			fo=new FileOutputStream(path);
			wb.write(fo);
			wb.close();	fi.close();
			fo.close();
		}
	}

	public void FillGreenColor(String sheet, int rownum, int column) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheet);
		row = ws.getRow(rownum);
		cell = row.getCell(column);

		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.index);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);

		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}

	public void RedGreenColor(String XlFile, String XlSheet, int rownum, int column) throws IOException {
		fi = new FileInputStream(XlFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(XlSheet);
		row = ws.getRow(rownum);
		cell = row.getCell(column);

		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.index);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);

		fo = new FileOutputStream(XlFile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
}

