package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {

	@FindBy(xpath="//img[@alt='Create Organization...']")
	private WebElement createorgbtn;
	
	@FindBy(name="search_text")
	private WebElement searchtextbox;
	
    @FindBy(name="search_field")
    private WebElement searchDD;
    
    @FindBy(name="submit")
    private WebElement searchbtn;
    
	
	WebDriver driver;
	public OrganizationsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public WebElement getCreateorgbtn() {
		return createorgbtn;
	}
	
	public WebElement getsearchtextbox()
	{
		return searchtextbox;
	}
	
	public WebElement getSearchDD() {
		return searchDD;
	}
	
	public WebElement getSearchBtn()
	{
		return searchbtn;
	}
}
