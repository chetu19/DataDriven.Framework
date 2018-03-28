package DataDriven.DataDriven_FW;

import org.testng.annotations.Test;

import DataDriven.DataDriven_FW.base.BaseTest;

public class DummyTestA extends BaseTest{
	
	@Test(priority=1)
	public void TestA1() {
		openBrowser("Mozilla");
		navigate("appurl");
		type("email_xpath","chetushines");
		click("nextButton_xpath");
		
	}
	
	@Test(priority=2, dependsOnMethods= {"TestA1"})
	public void TestA2() {
		
	}

	@Test(priority=3, dependsOnMethods= {"TestA1", "TestA2"})
	public void TestA3() {
	
	}
}
   
