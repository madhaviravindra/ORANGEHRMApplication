package commonUtilitiesNew;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReportUtility
{
	static ExtentReports report;
	static ExtentTest test;
	static WebDriver driver;

	public static void GenerateReport(String Verification,String Actual, String Expected) throws IOException
	{
		report = new ExtentReports("D:\\Madhavi\\HtmlTestResults\\automationreport1.html", true);
		test = report.startTest(Verification);
		test.log(LogStatus.INFO, Verification);
		if (Actual.equals(	Expected)) 
		{
			test.log(LogStatus.PASS, Verification);
			report.flush();
		} 
		else {
			test.log(LogStatus.FAIL, Verification);
			report.flush();
			/*File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile,
					new File("D:\\Madhavi\\Snapshots\\img.jpg"));
			String image = test
					.addScreenCapture("D:\\Madhavi\\Snapshots\\img.jpg")*/;
//			test.log(LogStatus.FAIL, Verification);
		}
	//	report.endTest(test);
		report.flush();
	}

}
