package DataDriven.DataDriven_FW;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import DataDriven.DataDriven_FW.base.BaseTest;

public class DummyTestC extends BaseTest{

	
	@Test
	public void TestC() {
		test=rep.startTest("DummytestB");
		test.log(LogStatus.INFO, "Starting the Test B");
		
		test.log(LogStatus.INFO, "Opening the browser");
		test.log(LogStatus.FAIL, "Failing the TestCase");
		//Attaching the Screenshot file
		test.log(LogStatus.FAIL, "Screenshot of Failed Testcase -->"+test.addScreenCapture("X:\\CREA\\Pers\\Canada day pics\\Naveen\\IMG_8241.jpg"));
	}
	
	@AfterTest
	public void quit() {
		
		// Mandatory for extent reports to be generated
		rep.endTest(test);
		rep.flush();
	}
	
}
