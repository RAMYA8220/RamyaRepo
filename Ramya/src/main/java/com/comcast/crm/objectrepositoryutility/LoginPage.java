package com.comcast.crm.objectrepositoryutility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

/**
 * 
 * @author ramya
 * contains Login page elemets &business libraries like login()
 * 
 */
//rule1: create seperate classes
public class LoginPage extends WebDriverUtility{
	
	//2. object creation
	
	@FindBy(name="user_name")
	 private WebElement usernameEdit;
	
	@FindBy(name="user_password")
	private WebElement passwordEdit;
	
	@FindBy(id="submitButton")
	private WebElement Loginbtn;

	
	
	//3. objecct initialization 
	
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//4. object encapsulation
	public WebElement getusernameEdit() {
		return usernameEdit;
	}

	public WebElement getpasswordEdit() {
		return passwordEdit;
	}

	public WebElement getLoginbtn() {
		return Loginbtn;
	}
	
	
	/**
	 * login to application based on username, password, url arguments
	 * @param url
	 * @param username
	 * @param password
	 */
	
	//5. we can provide action
	public void loginToApp(String url,String username, String password)
	{
		waitForPageToLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		usernameEdit.sendKeys(username);
		passwordEdit.sendKeys(password);
		Loginbtn.click();
	}
	
	public void loginToApp(String username, String password)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.manage().window().maximize();
		usernameEdit.sendKeys(username);
		passwordEdit.sendKeys(password);
		Loginbtn.click();
	}

}
