package scriptExecutor;

import java.util.Properties;

import org.testng.annotations.Test;

import com.mainscripts.SuperScript;

import testDataFiles.ReadDataFiles;

public class TestClass extends ReadDataFiles
{
	@Test
	public void ExecuteTest() throws Exception 
	{
			Properties  p = ReadDataFiles.GetPropertyData();
			SuperScript.navigatetoURL(p.getProperty("ApplicationURL"));
			ReadDataFiles.GetExcelFile(p.getProperty("ExcelFilePath"), p.getProperty("SheetName"));		
	}
}
