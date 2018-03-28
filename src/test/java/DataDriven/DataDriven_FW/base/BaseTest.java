package DataDriven.DataDriven_FW.base;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import datadriven_fw.util.ExtentManager;
import junit.framework.Assert;

public class BaseTest {
	public WebDriver driver;
	public Properties prop;
	public ExtentReports rep= ExtentManager.getInstance();
	public ExtentTest test;

	//Selecting the browser type 
	public void openBrowser(String bType) {
		
		// Initialize the properties file
				if(prop==null) {
					prop= new Properties();
					try {
						FileInputStream fs= new FileInputStream(System.getProperty("user.dir")+"//crc//test//resources//projectconfig.properties");
						prop.load(fs);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			System.out.println(prop.getProperty("appurl"));
				
		if(bType.equals("Mozilla")) {
			System.setProperty("webdriver.gecko.driver", prop.getProperty("firefoxdriver.exe"));
			driver= new FirefoxDriver();}  
		
		else if(bType.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", prop.getProperty("chromedriver.exe"));
			driver= new ChromeDriver();}
		
		else if(bType.equals("IE")) {
			System.setProperty("webdriver.ie.driver", prop.getProperty("iedriver.exe"));
			driver= new InternetExplorerDriver(); }
			
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);	
	
		
	}
	
	
	public void navigate(String appurl) {
		driver.get(prop.getProperty(appurl));
	}
	
	public void click(String locatorKey) {
		getElement(locatorKey).click();
	}
	
	//Entering data which accepts the target location and the data
	public void type(String locatorKey, String data) {
		getElement(locatorKey).sendKeys(data);
	}
	
	//Finding element on the page
	public WebElement getElement(String locatorKey) {
		WebElement e= null;
		try {
		if(locatorKey.endsWith("_id"))
			e= driver.findElement(By.id(prop.getProperty(locatorKey)));
		else if(locatorKey.endsWith("_name"))
			e=driver.findElement(By.name(prop.getProperty(locatorKey)));
		else if(locatorKey.endsWith("_xpath"))
			e=driver.findElement(By.xpath(prop.getProperty(locatorKey)));
	
		else {
			reportFailure("Element not correct " +locatorKey);
			Assert.fail("Failed the test  " +locatorKey);
		}}
	
		catch(Exception ex) {
		
		//Fail the test and report the Error
		reportFailure(ex.getMessage());
		ex.printStackTrace();
		Assert.fail("Failed the test  "+ex.getMessage());
	}
		return e;
}
	/**************Validations***********************/
	
	public boolean verifyTitle() {
		return false;
	}
	
	public boolean isElementPresent() {
		return false;
		
	}
	
	public void verifyText() {
	
	} 
	
	/**************Reporting***********************/
	
	public void reportPass(String msg) {
		test.log(LogStatus.PASS, msg);
	}

	public void reportFailure(String msg) {
		
		test.log(LogStatus.FAIL, msg);
		Assert.fail();
	}
	
	public void takeScreenshot() {
		//Filename of the Screenshot
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		
		//Store screenshot in the file
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {   
        	//If FileUtils throws an error, then import the apache io maven dependency 
        	FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"//screenshots//"+screenshotFile));
       }catch(IOException e) {
        	e.printStackTrace();
        }
        //put screenshot file in the report
		test.log(LogStatus.INFO	, "Screenshot of Failed Testcase -->"+test.addScreenCapture(System.getProperty("user.dir")+"//screenshots//"+screenshotFile));

	}
}
