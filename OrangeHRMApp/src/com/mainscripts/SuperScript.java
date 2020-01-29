package com.mainscripts;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import testDataFile.ReadDataFiles;

public class SuperScript extends ReadDataFiles
{
	public static WebDriver driver;
	public static WebElement ElementAddress;
	public static String ActualResult,ExpectedResult,ElementLocator1,ButtonTxt;
	public static boolean FinalResult;
	public static int a,b,c,d,e,f,g,h,i,j,k,l,m,n;
	static boolean elementExist = false;
	 public static void navigatetoURL(String URL)
	 { 
		 System.setProperty("webdriver.chrome.driver", "D:\\D-Drive\\Softwares\\chromedriver.exe");
		 driver=new ChromeDriver();
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.get(URL);
		 driver.manage().window().maximize();
	//	 driver.findElement(By.xpath(".//*[@id='userName']"))
	 }
	 public static boolean checkNull(String sValue)
	 {
			return (sValue != null && sValue.compareTo("") != 0 && sValue
					.compareTo("NULL") != 0);
		}
	 public static void SelectWedgettype(String ElementLocator) throws Exception
	 {	 
		 if(ElementLocator.contains("xpath="))
		 { 
			  ElementLocator1=ElementLocator.replace("xpath=", "");			 
//			 System.out.println(ElementLocator1);
			 ElementAddress=driver.findElement(By.xpath(ElementLocator1));
			
		 }
		 else if(ElementLocator.contains("name="))
		 {
			  ElementLocator1=ElementLocator.replace("name=", "");			 
//			 System.out.println(ElementLocator1);
			 ElementAddress=driver.findElement(By.name(ElementLocator1));
		 }
		 else if(ElementLocator.contains("id="))
		 {
			  ElementLocator1=ElementLocator.replace("id=", "");		 
//			 System.out.println(ElementLocator1);
			 ElementAddress=driver.findElement(By.id(ElementLocator1));
		 }
		 else if(ElementLocator.contains("class="))
		 {
             ElementLocator1=ElementLocator.replace("class=", "");			 
//			 System.out.println(ElementLocator1);
			 ElementAddress=driver.findElement(By.className(ElementLocator1));
		 }
		 else if(ElementLocator.contains("linktext="))
		 {
			  ElementLocator1=ElementLocator.replace("linkText=", "");			 
//			 System.out.println(ElementLocator1);
			 ElementAddress=driver.findElement(By.linkText(ElementLocator1));
		 }
//*************************************************************		 
		try {
			if (WedgetType.equalsIgnoreCase("Label")) {
				elementExist = ElementAddress.isDisplayed();
				if (elementExist==true) {
					System.out.println("Verification pass for the field " + FieldName
							+ " is getting displayed on screen " + TestData);
				}
				else
				{
					System.out.println("Verification failed for the field " + FieldName
							+ " is not displayed on screen " + TestData);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
//******************************************************		 
		 if(WedgetType.equalsIgnoreCase("TextBox"))
		 {
			 ElementAddress.sendKeys(TestData); 
		 }
		 else if(WedgetType.equalsIgnoreCase("TextBoxValue"))
		 {
			  String Actual=ElementAddress.getText();
			  ActualResult=Actual.replace(",", "");
			 System.out.println("ÄctualValue  :"+ActualResult);
			 if(ActualResult.equals(TestData))
			 {
				 System.out.println("Actual same as Expected");
			 }
			 else
			 {
				 System.out.println("Actual is not same as expected");
			 }
		 }
		 else if(WedgetType.equalsIgnoreCase("Button"))
		 {
			ElementAddress.click();
			Thread.sleep(8000);
		 }
		 
		 else if(WedgetType.equalsIgnoreCase("Select"))
		 {
			 Select a1=new Select(ElementAddress);
			 a1.selectByVisibleText(TestData);		 
		 }
		 else if(WedgetType.equalsIgnoreCase("DropDown"))
		 {
			 ElementAddress.click();
			 ElementAddress.sendKeys((Keys.ENTER));
		 }
		 else if(WedgetType.equalsIgnoreCase("ListBox"))
		 {
			ElementAddress.click();
			ElementAddress.sendKeys(TestData);
			ElementAddress.sendKeys((Keys.ENTER));
		 }
		 else if(WedgetType.equalsIgnoreCase("linktext"))
		 {
			ElementAddress.click();
			Thread.sleep(20000);
		 }
		else if(WedgetType.equalsIgnoreCase("Title"))
		 {
			 String ActTitle =driver.getTitle();
			 if(ActTitle.equals(TestData))
			 {
				 System.out.println("Displayed title as per the requirement");
			 }
			 else
			 {
				 System.out.println("Title not Displayed as per the requirement");
			 }
		 }
		 else if(WedgetType.equalsIgnoreCase("Label"))
		 {
			String LabelTxt=ElementAddress.getText();
			if(LabelTxt.equalsIgnoreCase(TestData))
			{
				System.out.println("Selected record deleted successfully");
			}
		 }
		 else if(WedgetType.equalsIgnoreCase("ButtonText"))
		 {
			 ButtonTxt=ElementAddress.getAttribute("value");
			 if(ButtonTxt.equals(TestData))
			 {
				 System.out.println(TestData+" button present on screen");
				 if(ElementAddress.isEnabled())
				 {
					System.out.println(TestData+" button is enabled "); 
				 }
				 else
				 {
					 System.out.println(TestData +" button is disabled");
				 }
			 }
			 else
			 {
				 System.out.println(TestData+" Button not present on screen");
			 }
		 }
		 else if(WedgetType.equalsIgnoreCase("Default drop down"))
		 {
			 Select s= new Select(ElementAddress);
			 WebElement dropdownSelected=s.getFirstSelectedOption();
			 String seleted_option=dropdownSelected.getText();
			 if(seleted_option.equals(TestData))
			 {
				 System.out.println(FieldName+" Default drop down selected value is correct :" +seleted_option);
			 }
			 else
			 {
				 System.out.println(FieldName+" Default drop down selected value is in correct:" +seleted_option);
			 }
		 }
		 else if(WedgetType.equalsIgnoreCase("Drpdwn Not Empty"))
		 {
			 List<WebElement> drpdwnList=driver.findElements(By.xpath(ElementLocator1));		 
			 if(drpdwnList.size()>0)
			 {
				 System.out.println(FieldName+" drop down is not empty on "+TestData);
			 }
			 else
			 {
				 System.out.println(FieldName+" drop down is empty on "+TestData);
			 }
		 }
		 
		 else if(WedgetType.equalsIgnoreCase("WaitFor"))
		 {		
			WebDriverWait wait = new WebDriverWait(driver,100);
			wait.until(ExpectedConditions.elementToBeClickable(ElementAddress));
		 }
		 else if(WedgetType.equalsIgnoreCase("UploadFile"))
		 {
			ElementAddress.click();
			Thread.sleep(3000);
			try {	
				StringSelection s = new StringSelection(TestData);
				Toolkit.getDefaultToolkit().getSystemClipboard()//like control c
						.setContents(s, null);
				// native key strokes for CTRL, V and ENTER keys
				Robot robot = new Robot();

				robot.keyPress(KeyEvent.VK_CONTROL);//press control 
				robot.keyPress(KeyEvent.VK_V);//press v
				robot.keyPress(KeyEvent.VK_ENTER);//press enter
			} 
			catch (Exception exp) 
			{
				exp.printStackTrace();
			}
		 }
		 else if (WedgetType.equalsIgnoreCase("Checkbox")) {
				if (checkNull(TestData)) {
					boolean currentChkBoxState = false;
					currentChkBoxState = ElementAddress.isSelected();
				
					if (currentChkBoxState == false
							&& (TestData.equalsIgnoreCase("Yes")
									|| TestData.equalsIgnoreCase("1"))) {		
						ElementAddress.click();
						System.out
						.println("Current Checkbox State for the field '"
								+ FieldName
								+ "' is - "
								+ currentChkBoxState);
					}
					if (currentChkBoxState == true
							&& (TestData.equalsIgnoreCase("No")                           
									|| TestData.equalsIgnoreCase("0"))) {
						ElementAddress.click();
						System.out
						.println("Current Checkbox State for the field '"
								+ FieldName
								+ "' is - "
								+ currentChkBoxState);
					}
				}
	
		 }
		 else if (WedgetType.equalsIgnoreCase("Frame"))
		 {
             if (checkNull(TestData))
             {
                 driver.switchTo().frame(TestData);
                 System.out.println("Successfully switched to requested frame");
             }
		 }
		 else if (WedgetType.equalsIgnoreCase("NewTab")) {
				if (checkNull(TestData)) {
					
					 ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
					 int windowcount=newTab.size();
					    driver.switchTo().window(newTab.get(windowcount-1));
					    System.out.println("control move to new tab");
				}
		 }
		 
		 else if(WedgetType.contains("Verify Add Employee"))
		 {
			 if (checkNull(TestData)) {
			 List <WebElement> Emps=driver.findElements(By.xpath(ElementLocator1));
			// System.out.println(Emps.size());
			 for(a=0;a<Emps.size();a++)
			 {
				// System.out.println(a);
				 if(Emps.get(a).getText().equalsIgnoreCase(TestData.toString()))
				 {
					 System.out.println("New employee added successfully");
				 }
			 }
		 }
		}
	 }
	
	 public static void VerificationPoint(String Senario,String Actual,String Expected) throws Exception
	 {
		 FinalResult=Actual.endsWith(Expected);
	//	 GeneraateReport.getResult(FinalResult);
		 SetExcelValue(FinalResult);
	 } 

}
