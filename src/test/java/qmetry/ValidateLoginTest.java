package qmetry;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ValidateLoginTest {
	
	//author
	
	@Test
	public void validateLogin() throws IOException
	{
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("D:\\San\\Testing Guide\\E2E\\Reports\\extent.html");
		htmlReporter.setAppendExisting(false);
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		ExtentTest test = extent.createTest("MyFirstTest");
		System.out.println("Login Validated");
		System.setProperty("webdriver.gecko.driver","D:\\San\\Testing Guide\\E2E\\exe files\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		//comment the above 2 lines and uncomment below 2 lines to use Chrome
		//System.setProperty("webdriver.chrome.driver","G:\\chromedriver.exe");
		//WebDriver driver = new ChromeDriver();
    	
        String baseUrl = "http://demo.guru99.com/test/newtours/";
        String expectedTitle = "Welcome: Mercury Tours1";
        String actualTitle = "";

        // launch Fire fox and direct it to the Base URL
        driver.get(baseUrl);
        
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        // get the actual value of the title
        actualTitle = driver.getTitle();

        /*
         * compare the actual title of the page with the expected one and print
         * the result as "Passed" or "Failed"
         */
        if (actualTitle.contentEquals(expectedTitle)){
            System.out.println("Test is passing!");
            Assert.assertTrue(true);
            test.pass("Test is passing!");
            FileUtils.copyFile(scrFile, new File("D:\\San\\Testing Guide\\E2E\\Reports\\Snapshots\\screenshot.png"));
            test.addScreenCaptureFromPath("screenshot.png");
        } else {
        	Assert.assertTrue(false);
            System.out.println("Test Failed");
            test.fail("Test Failed");
        }
       
        extent.flush();
        //close Fire fox
        driver.close();
	}
	
	
	

}
