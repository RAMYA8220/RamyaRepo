package com.comcast.crm.UtilityContactTest;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateContactTest {
	
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

				

				// read testScript data from Excelutility
				
				String lastName=elib.getDataFromExcel("contact", 1,2) +jlib.getRandomNumber();
				
				
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
				driver.findElement(By.name("lastname")).sendKeys(lastName);//from excel
				driver.findElement(By.className("save")).click();

				//verify Header msg expected result
				
				
				//validate header orgname info expected result
				String Actlastname=driver.findElement(By.id("dtlview_Last Name")).getText();
				if(Actlastname.contains(lastName))
				{
					System.out.println(lastName+ "info verified ==pass");
					
				}
				else 
				{
					System.out.println(lastName+" info  is not verified==fail");
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





	


