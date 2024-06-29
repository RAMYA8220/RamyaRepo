package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {

	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement headertxt;
	
	@FindBy(xpath="//span[@id='dtlview_Industry']")
	private WebElement industrytxt;
	
	@FindBy(xpath="//span[@id='dtlview_Type']")
	private WebElement Industrytype;
	
	@FindBy(id="dtlview_Phone")
	private WebElement phonenumber;
	
	WebDriver driver;
	public OrganizationInfoPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}

	public WebElement getHeadertxt() {
		return headertxt;
	}
	
	public WebElement getIndustrytxt() {
		return industrytxt;
		
	}

	public WebElement getIndustrytype() {
		
		return Industrytype;
	}
	public WebElement getPhoneNumber() {
		return phonenumber;
		
	}
	
}
