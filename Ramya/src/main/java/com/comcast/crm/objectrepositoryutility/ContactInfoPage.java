package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {

	@FindBy(xpath="//span[@class='dvHeaderText']")  
	private WebElement contactheader;
	
	@FindBy(id="dtlview_Last Name")
	private WebElement lastname;
	
	@FindBy(id="dtlview_Support Start Date")
	private WebElement startDate;
	
	@FindBy(id="dtlview_Support End Date")
	private WebElement endDate;
	
	@FindBy(xpath="//td[@id='mouseArea_Organization Name']")
	private WebElement orgnamelink;
	
	
	
	WebDriver driver;
	public ContactInfoPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getContactHeader()
	{
		return contactheader;
		
	}
	public WebElement getLastname() {
		return lastname;
	}
	
	public WebElement getStartDate()
	{
		return startDate;
	}
	
	public WebElement getEndDate()
	{
		return endDate;
	}
	public WebElement getorglinkincontact()
	{
		return orgnamelink;
	}
	
	
}

