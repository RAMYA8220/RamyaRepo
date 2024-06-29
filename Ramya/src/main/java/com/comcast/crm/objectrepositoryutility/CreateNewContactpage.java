package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContactpage {

	
	@FindBy(name="lastname")
	private WebElement lastnameEdit;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement savebtn;
	
	@FindBy(xpath="//input[@name='support_start_date']")
	private WebElement startDatetxt;
	
	@FindBy(xpath="//input[@name='support_end_date']")
	private WebElement endDatetxt;
	
	@FindBy(xpath="//input[@name='account_name']/following-sibling::img")
	private WebElement lookupbtn;
	
	WebDriver driver;
	public CreateNewContactpage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getLastName() {
		return lastnameEdit;
		}
	
	public WebElement getSavebtn() {
		return savebtn;
	}
	
	public WebElement getstartDate()
	{
		return startDatetxt;
		
	}
	public WebElement getEndDate()
	{
		return endDatetxt;
	}
	
	public WebElement getLookupbtn()
	{
		return lookupbtn;
	}
	
	public void createContact(String LastName)
	{
		lastnameEdit.sendKeys(LastName);
		savebtn.click();
	}
	
	public void createContact(String LastName, String startDate, String endDate)
	{
		lastnameEdit.sendKeys(LastName);
		startDatetxt.clear();
		startDatetxt.sendKeys(startDate);
		endDatetxt.clear();
		endDatetxt.sendKeys(endDate);
		savebtn.click();
	}
	
	

}
