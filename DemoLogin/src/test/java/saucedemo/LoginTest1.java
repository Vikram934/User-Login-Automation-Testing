package saucedemo;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class LoginTest1 {
	Properties prop;
	FileInputStream fis;
	WebDriver driver;
	ExtentTest test;
	ExtentReports report;
	
	
	@BeforeTest
	public void init() throws IOException {
		prop = new Properties();
		fis = new FileInputStream("C://Users//Vikram//Documents//VikChromedriver//DemoLogin//src//main//resources//Login.properties");
		prop.load(fis);
		
		String foldername = "C://Users//Vikram//Documents//VikChromedriver";
		report = new ExtentReports();
		ExtentSparkReporter reporter = new ExtentSparkReporter(foldername);
		reporter.config().setReportName("NewLogin");
		reporter.config().setDocumentTitle("Sheet2");
		report.attachReporter(reporter);
		test = report.createTest("login");

		
	}
	
   
    
    
	@Test(dataProvider = "getData")
	public void login(String username, String password) throws IOException, InterruptedException
	{
	
	System.setProperty("webdriver.chrome.driver", "C://Users//Vikram//Documents//VikChromedriver//chromedriver_win32//chromedriver.exe");
	driver = new ChromeDriver();
	test.log(Status.INFO, "Navigating to URL");
	driver.get(prop.getProperty("url"));
	
	test.log(Status.INFO, "Username is " + username);
	String user= prop.getProperty("username");
	driver.findElement(By.id(user)).sendKeys(username);
	
	test.log(Status.INFO, "Password is " + password);
	String pass= prop.getProperty("password");
	driver.findElement(By.id(pass)).sendKeys(password);
	
	test.log(Status.INFO, "Clicking on login button");
	String login=prop.getProperty("login");
	driver.findElement(By.id(login)).click();
	Thread.sleep(2000);
	
	 }
	
	 @AfterTest
	    public void report() {
	    	report.flush();
	    	driver.close();
	    	
	    }
	
	 
 	@DataProvider
 	public Object[][] getData() throws IOException
     {
     
		FileInputStream fiss = new FileInputStream("C://Users//Vikram//Documents//VikChromedriver//TestData.xlsx");
	    XSSFWorkbook wb = new XSSFWorkbook(fiss);
	    XSSFSheet sheet = wb.getSheet("Sheet1");
	    

		Object[][] dataset = new Object[2][2];
		dataset[0][0] = sheet.getRow(1).getCell(0).getStringCellValue();
		dataset[0][1] = sheet.getRow(1).getCell(1).getStringCellValue();
		dataset[1][0] = sheet.getRow(2).getCell(0).getStringCellValue();
		dataset[1][1] = sheet.getRow(2).getCell(1).getStringCellValue();
		wb.close();
		return dataset;
	 	
     } 
}
