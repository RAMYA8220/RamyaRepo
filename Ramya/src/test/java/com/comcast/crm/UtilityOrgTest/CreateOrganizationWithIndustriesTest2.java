package com.comcast.crm.UtilityOrgTest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateOrganizationWithIndustriesTest2 {

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
				String industry=elib.getDataFromExcel("org", 4,3) +jlib.getRandomNumber();
				String type=elib.getDataFromExcel("org", 4,4) +jlib.getRandomNumber();
				String orgName=elib.getDataFromExcel("org", 4,2) +jlib.getRandomNumber();
				

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
		driver.findElement(By.linkText("Organizations")).click();

		// Step 3: Click on "create Organization" Button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// Step 4: enter all the details and create new organization
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		
		//additional program to select from dropdown
		WebElement wbsele1=driver.findElement(By.name("industry"));
		Select sel1=new Select(wbsele1);
		sel1.selectByVisibleText(industry);//from excel
		
		
		WebElement wbsele2=driver.findElement(By.name("accounttype"));
		Select sel2=new Select(wbsele2);
		sel2.selectByVisibleText(type);//from excel
		
		driver.findElement(By.className("save")).click();
       
		//verify the industries and type info
		String actindustries=driver.findElement(By.id("dtlview_Industry")).getText();
		if(actindustries.contains(industry))
		{
			System.out.println(industry+"info verified==pass");
			
		}
		else 
		{
			System.out.println(industry+"info not verified==fail");
		}
		
		
		String acttype=driver.findElement(By.id("dtlview_Type")).getText();
		if(acttype.contains(acttype))
		{
			System.out.println(acttype+"info verified==pass");
			
		}
		else 
		{
			System.out.println(acttype+"info not verified==fail");
		}
		
		
		// Step 5: LogOut
		Actions act = new Actions(driver);
		Thread.sleep(2000);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Sign Out")).click();

		driver.quit();

	}

}
