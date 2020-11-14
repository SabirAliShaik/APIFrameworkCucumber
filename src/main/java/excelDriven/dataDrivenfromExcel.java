package excelDriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class dataDrivenfromExcel {

	//Identify the test case column by scanning entire 1st row
	//Once column is identified then scan entire testcase column to  identify purchase test case row
	//After that pull all the data from row
	public ArrayList<String> getData(String FilePath,  String SheetName, String ColumnName,  String ColumnValue) throws Exception {
		ArrayList<String> list = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(FilePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheets = workbook.getNumberOfSheets();
		for(int i=0;i<sheets;i++) {
			if(workbook.getSheetName(i).equalsIgnoreCase(SheetName)) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rows =  sheet.iterator();
				Row firstRow = rows.next();	//Going through first  row of excel, which is considered as column headers
				Iterator<Cell> cell = firstRow.cellIterator();
				int k=0;
				int column = 0;
				//Identify the test case column by scanning entire 1st row
				while(cell.hasNext()) {
					Cell value = cell.next();
					if(value.getStringCellValue().equalsIgnoreCase(ColumnName)) {
						column =  k;	//Identifying required column index from column headers
					}
					k++;
				}
				//Once column is identified then scan entire test case column to  identify purchase test case row
				while(rows.hasNext()) {
					Row r = rows.next();
					if(r.getCell(column).getStringCellValue().equalsIgnoreCase(ColumnValue)) {
						//After that pull all the data from row
						Iterator<Cell> cv = r.cellIterator();
						while(cv.hasNext()) {
							Cell c = cv.next();
							if(c.getCellType() == CellType.STRING) {
								list.add(c.getStringCellValue());
							}
							else {
								list.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							}
						}
					}
				}
			}
		}
		return list;
	}
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		dataDrivenfromExcel dd = new dataDrivenfromExcel();
		ArrayList data = dd.getData("C:\\Users\\S K SABIR ALI\\Desktop\\Book1.xlsx", "testData", "TestCases", "Add Profile");
		System.out.println(data);
		System.out.println(data.get(0));
	
	}

}
