package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage {

	
	@FindBy(name="accountname")
	private WebElement orgnameEdit;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement savebtn;
	
	@FindBy(xpath="//select[@name='industry']")
	private WebElement industrydrop;
	
	@FindBy(xpath="//select[@name='accounttype']")
	private WebElement industryType;
	
	@FindBy(id="phone")
	private WebElement Phonenum;
	
	WebDriver driver;
	public CreatingNewOrganizationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	public WebElement getOrgname() {
		return orgnameEdit;
	}

	public WebElement getSavebtn() {
		return savebtn;
	}
	
	
	public WebElement getIndustrydrop() {
		return industrydrop;
	}


	public WebElement getIndustryType()
	{
		return industryType;
	}

    public WebElement getPhoneNum()
    {
		return Phonenum;
    	
    }
	public void createorg(String orgName)
	{
		orgnameEdit.sendKeys(orgName);
		savebtn.click();
	}
	
	public void createorg(String orgName, String industry)
	{
		orgnameEdit.sendKeys(orgName);
		
		Select sel=new Select(industrydrop);
		sel.selectByVisibleText(industry);
		
		savebtn.click();
	}
	
	public void createorg(String orgName, String industry, String type)
	{
		orgnameEdit.sendKeys(orgName);
		
		Select sel=new Select(industrydrop);
		sel.selectByVisibleText(industry);
		
		Select sel1=new Select(industryType);
		sel1.selectByVisibleText(type);
		
		savebtn.click();
	}
	
	public void createorgWithPhone(String orgName, String phoneNumber)
	{
		orgnameEdit.sendKeys(orgName);
		Phonenum.sendKeys(phoneNumber);
		
		
		savebtn.click();
	}
	
	
}
