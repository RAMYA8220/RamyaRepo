package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	@FindBy(linkText="Organizations")
	private WebElement orglink;
	
	@FindBy(linkText="Contacts")
	private WebElement contactlink;
	
	@FindBy(linkText="More")
	private WebElement morelinks;
	
	@FindBy(linkText="Campaigns")
	private WebElement campaignlink;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminimg;
	
	@FindBy(xpath="//a[text()='Sign Out']")
	private WebElement signoutlink;
	
	
	
	//--------------
	
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	public WebElement getOrglink() {
		return orglink;
	}

	public WebElement getContactlink() {
		return contactlink;
	}

	public WebElement getMorelinks() {
		return morelinks;
	}

	public WebElement getCampaignlink() {
		return campaignlink;
	}
	
	
	
 public void navigateToCampaign()
 {
	Actions act=new Actions(driver);
	act.moveToElement(morelinks).perform();
	campaignlink.click();
 }
 
	public void logout()
	{
		Actions act=new Actions(driver);
		act.moveToElement(adminimg).perform();
		
		signoutlink.click();
	}
	
}
