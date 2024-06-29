package com.comcast.crm.UtilityContactTest;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateContactWithSupportDateTest {
	
			public static void main(String[] args) throws Throwable {
				
			
				
				//create object
				FileUtility flib=new FileUtility();
				ExcelUtility elib=new ExcelUtility();
				JavaUtility jlib=new JavaUtility();
				
				//read common data from lib

				String BROWSER = flib.getDataFromPropertiesFile("browser");
				String URL = flib.getDataFromPropertiesFile("url");

				String USERNAME = flib.getDataFromPropertiesFile("username");
				String PASSWORD = flib.getDataFromPropertiesFile("password");

				// read testScript data from Excel file
				String LastName=elib.getDataFromExcel("contact", 4,2) +jlib.getRandomNumber();

				//polymorphism program : in property file if we change browser name then also it should run.
				WebDriver driver = null;
				if (BROWSER.equals("chrome")) {
					driver = new ChromeDriver();
				} else if (BROWSER.equals("firefox")) {
					driver = new FirefoxDriver();
				} else if (BROWSER.equals("edge")) {
					driver = new EdgeDriver();
				} else {
					driver = new ChromeDriver();
				}

				// Step 1: login to app
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				driver.get(URL);
				
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();

				// Step 2: navigate to organization module
				driver.findElement(By.linkText("Contacts")).click();

				// Step 3: Click on "create Organization" Button
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

				// Step 4: enter all the details and create new organization
				
				String startDate=jlib.getRequiredDateYYYYDDMM(30);
				String endDate=jlib.getSystemDateYYYYDDMM();
				
				
				driver.findElement(By.name("lastname")).sendKeys(LastName);//from excel
				
				driver.findElement(By.name("support_start_date")).clear();
				driver.findElement(By.name("support_start_date")).sendKeys(startDate);
				
				driver.findElement(By.name("support_end_date")).clear();
				driver.findElement(By.name("support_end_date")).sendKeys(endDate);
				
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				//verify start date and end date
				String actstartdate=driver.findElement(By.id("dtlview_Support Start Date")).getText();
				if(actstartdate.contains(startDate)){
					System.out.println(startDate+ "info verified ==pass");
				}else 
				{
					System.out.println(startDate+" info  is not verified==fail");
				}
				
				String actenddate=driver.findElement(By.id("dtlview_Support End Date")).getText();
				if(actenddate.contains(endDate)){
					System.out.println(endDate+ "info verified ==pass");
				}else 
				{
					System.out.println(endDate+" info  is not verified==fail");
				}
				
				// Step 5: LogOut
//				Actions act = new Actions(driver);
//				Thread.sleep(2000);
//				act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
//				Thread.sleep(2000);
//				driver.findElement(By.linkText("Sign Out")).click();

				driver.quit();

			}
}





	


