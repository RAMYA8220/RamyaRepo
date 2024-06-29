package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwitchingToWindowPage {

	@FindBy(id="search_txt")
	private WebElement searchtextfield;
	
	@FindBy(name="search")
	private WebElement serchnwbtn;
	
	WebDriver driver;
	public SwitchingToWindowPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public WebElement getSearchText()
	{
		return searchtextfield;
	}
	public WebElement getSearchbtn()
	{
		return serchnwbtn;
	}
}
