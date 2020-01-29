package commonUtilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import testDataFile.ReadDataFiles;

import com.mainscripts.*;
public class TakeSnapshots extends SuperScript
{
	public static void TakeSnapshot() throws Exception
	{
		 Properties  p = ReadDataFiles.GetPropertyData();
		 File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 File dest= new File(p.getProperty("ScreenShotFolderPath")+TestSenario+"_"+FinalResult+"_"+timestamp()+".png");
		 FileUtils.copyFile(scr, dest);
	}
	public static String timestamp() {
	    return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}


}
