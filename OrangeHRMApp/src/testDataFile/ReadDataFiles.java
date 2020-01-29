package testDataFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mainscripts.SuperScript;
import commonUtilities.TakeSnapshots;

public class ReadDataFiles
{
	public static XSSFSheet ExcelWSheet,ExcelWSheet1;
	public static XSSFWorkbook ExcelWBook; 
	public static int i,j,RowCount,RowCount1,ColumnCount;
	protected static String WedgetType,TestData,Locator,FieldName;
	public static String TestSenario,RunMode,Sheet_Name;
	public static String PropertyFile="D:\\Java_Selenium_Branch\\OrangeHRMApplication\\Resource\\PropertyFile.properties";
	
	public static void GetExcelFile(String Path,String SheetName) throws Exception 
	 {
		 FileInputStream ExcelFile = new FileInputStream(Path);
		 ExcelWBook = new XSSFWorkbook(ExcelFile);
		 ExcelWSheet = ExcelWBook.getSheet(SheetName);
		 RowCount=ExcelWSheet.getLastRowNum();
		 for(i=1;i<=RowCount;i++)
		 {
			 RunMode=ExcelWSheet.getRow(i).getCell(3).getStringCellValue();
			 Sheet_Name=ExcelWSheet.getRow(i).getCell(2).getStringCellValue();
			 TestSenario=ExcelWSheet.getRow(i).getCell(1).getStringCellValue();
			 	if(RunMode.equals("Y"))
			 	{	
			 		ExcelWSheet1 = ExcelWBook.getSheet(Sheet_Name);
			 		RowCount1=ExcelWSheet1.getLastRowNum();
					 for(j=1;j<=RowCount1;j++)
					 {
						 WedgetType=ExcelWSheet1.getRow(j).getCell(0).getStringCellValue();
						 Locator=ExcelWSheet1.getRow(j).getCell(2).getStringCellValue();
						 TestData=ExcelWSheet1.getRow(j).getCell(3).getStringCellValue();
						 FieldName=ExcelWSheet1.getRow(j).getCell(1).getStringCellValue();					
			//			 System.out.println(WedgetType+"      "+Locator+"      "+TestData);
						 SuperScript.SelectWedgettype(Locator);
				
					 }
			 }
		 }
	 } 
	 public static void SetExcelValue(boolean Result) throws Exception
	 {
		 Properties  p = ReadDataFiles.GetPropertyData();
		 if(Result==true)
		 {
			 	TakeSnapshots.TakeSnapshot();
			 	System.out.println("Actual and Expected results are same");
				FileInputStream fis1 = new FileInputStream(p.getProperty("ExcelFilePath"));
				ExcelWBook = new XSSFWorkbook(fis1);
			    ExcelWSheet=ExcelWBook.getSheet(p.getProperty("SheetName"));
				Row row = ExcelWSheet.getRow(i);
				row.createCell(4).setCellValue("Pass");
				FileOutputStream fout = new FileOutputStream(new File(p.getProperty("ExcelFilePath")));
				ExcelWBook.write(fout);
		 }
		 else
		 {
			 	TakeSnapshots.TakeSnapshot();
			 	System.out.println("Actual and Expected results are not same");
				FileInputStream fis1 = new FileInputStream(p.getProperty("ExcelFilePath"));
				ExcelWBook = new XSSFWorkbook(fis1);
			    ExcelWSheet=ExcelWBook.getSheet(p.getProperty("SheetName"));
				Row row = ExcelWSheet.getRow(i);
				row.createCell(4).setCellValue("Fail");
				FileOutputStream fout = new FileOutputStream(new File(p.getProperty("ExcelFilePath")));
				ExcelWBook.write(fout);
		 }	
	 }
	 public static Properties GetPropertyData() throws Exception
	 {	
		FileInputStream fis = new FileInputStream(PropertyFile);
		Properties prop = new Properties();
		prop.load(fis);
		return prop;	
	}
	 

}
