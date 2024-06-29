package com.comcast.crm.generic.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	//implicit wait

	public void waitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	//explicit wait
	public void waitForElementPresent( WebDriver driver ,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	
	
	///switch to child window
	public void switchToTabOnURL(WebDriver driver, String PartialURL)
	{
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();

		while (it.hasNext()) {
			String WindowId = it.next();
			driver.switchTo().window(WindowId);
			String actURL = driver.getCurrentUrl();
			if (actURL.contains(PartialURL)) {
				break;
			}
		}
	}
	
	public void switchToTabOnTitle(WebDriver driver, String PartialTitle)
	{
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();

		while (it.hasNext()) {
			String WindowId = it.next();
			driver.switchTo().window(WindowId);
			String actURL = driver.getCurrentUrl();
			if (actURL.contains(PartialTitle)) {
				break;
			}
		}
	}
	////frames//////
	public void switchToFrame(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);
		
	}
	public void switchToFrame(WebDriver driver, String nameID)
	{
		driver.switchTo().frame(nameID);
		
	}
	public void switchToFrame(WebDriver driver, WebElement element)
	{
		driver.switchTo().frame(element);
		
	}
	
	///alerts---//
	public void switchToAlertAndAccept(WebDriver Driver) {
		Driver.switchTo().alert().accept();
	}
	
	public void switchToAlertAndCancel(WebDriver Driver) {
		Driver.switchTo().alert().dismiss();
	}
	//---dropdowns
	public void select(WebElement element,String text)
	{
		Select sel=new Select(element);
		sel.selectByVisibleText(text);
	}
	public void select(WebElement element, int index)
	{
		Select sel=new Select(element);
		sel.selectByIndex(index);
	}
	
	///---actions
	public void mouseMoveOnElement(WebDriver driver, WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
		
	}
	public void doubleClick(WebDriver driver, WebElement element)
	{
		Actions act=new Actions(driver);
		act.doubleClick(element).perform();
		
	}
	public void rightClick(WebDriver driver, WebElement element)
	{
		Actions act=new Actions(driver);
		act.contextClick(element).perform();
	}
	
	
	
}
