package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {

	
	@FindBy(xpath="//img[@alt='Create Contact...']")
	private WebElement createcontactbtn;
	
	

WebDriver driver;
public ContactsPage(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver,this);
	
}

public WebElement getcreatecontactbtn()
{
	return createcontactbtn;
}


}