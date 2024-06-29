package com.comcast.crm.UtilityContactTest;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactWithOrgTest {

	public static void main(String[] args) throws Throwable {
		//create object
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
		WebDriverUtility wlib=new WebDriverUtility();
		
		//read common data from flib

		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");

		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");

		

		// read testScript data from Excelutility
		
		String orgName=elib.getDataFromExcel("contact", 7,2) +jlib.getRandomNumber();
		String contactLastName=elib.getDataFromExcel("contact", 7, 3);

		// polymorphism program : in property file if we change browser name then also
		// it should run.
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
		wlib.waitForPageToLoad(driver);
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
		driver.findElement(By.className("save")).click();

		// verify header info expected
		String HeaderInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (HeaderInfo.contains(orgName)) {
			System.out.println(orgName + "is created==pass");

		} else {
			System.out.println(orgName + "is not created==fail");
		}

		// Step 5: navigate to contact module

		driver.findElement(By.linkText("Contacts")).click();

		// Step 6: Click on "create Organization" Button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// Step 7: enter all the details and create new organization
		driver.findElement(By.name("lastname")).sendKeys(contactLastName);// from excel
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

		// switch to child window
		
		wlib.switchToTabOnURL(driver, "module=Accounts");
		

		driver.findElement(By.name("search_text")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

		// switch to parent window
		
		wlib.switchToTabOnURL(driver, "module=Contacts&parenttab");
		

		driver.findElement(By.className("save")).click();

		// verify Header msg expected result
		HeaderInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (HeaderInfo.contains(contactLastName)) {
			System.out.println(contactLastName + "header is created==pass");

		} else {
			System.out.println(contactLastName + "header is not created==fail");
		}

		// verify Header orgname info expected result
		String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		if (actOrgName.trim().equals(orgName)) {
			System.out.println(orgName + "is verified==pass");

		} else {
			System.out.println(orgName + "is not verified==fail");
		}

		driver.quit();

	}

}
