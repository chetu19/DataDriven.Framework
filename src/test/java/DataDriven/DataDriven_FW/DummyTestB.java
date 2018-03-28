package DataDriven.DataDriven_FW;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import DataDriven.DataDriven_FW.base.BaseTest;

public class DummyTestB extends BaseTest{
	
	SoftAssert sAssert= new SoftAssert();
	
	@Test(priority=1)
	public void TestB() {
		
		test=rep.startTest("DummytestB");
		test.log(LogStatus.INFO, "Starting the Test B");
		
		openBrowser("Mozilla");
		test.log(LogStatus.INFO, "Opening the browser");
		navigate("appurl");
		//Critical 
		//if(!isElementPresent("email_id"))
			reportFailure("Email field not present");
		
		type("email_id","chetushines");
		takeScreenshot();
		test.log(LogStatus.INFO, "Entered the username");
		click("nextButton_xpath");
		
	}
	
	@AfterTest
	public void quit() {
		
		// Mandatory for extent reports to be generated
		rep.endTest(test);
		rep.flush();
	}
}
