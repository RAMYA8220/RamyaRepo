package PomImplementaionClasses;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrganization {
	public static void main(String[] args) throws Throwable {
		//create object
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();

		//read common data from lib

		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");

		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");

		// read testScript data from Excel file
		String orgName=elib.getDataFromExcel("contact", 1,2) +jlib.getRandomNumber();
		
		WebDriver driver = null;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		// Step 1: login to app
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(URL);


		//step1: login to an application

		//LoginPage lp=PageFactory.initElements(driver, LoginPage.class);
		LoginPage lp=new LoginPage(driver);

		//before  making WE private
		//		lp.usernameEdit.sendKeys("admin");	
		//		lp.passwordEdit.sendKeys("admin");
		//		lp.Loginbtn.click();		

		//after making WE private
		//		lp.getusernameEdit().sendKeys("admin");	
		//		lp.getpasswordEdit().sendKeys("admin");
		//		lp.getLoginbtn().click();

		// after creating bsns library

		lp.loginToApp("admin", "admin");

		//step2: navigate to an organization module
		HomePage hp=new HomePage(driver);
		hp.getOrglink().click();


		//step3. click on "create organization" button
		OrganizationsPage op=new OrganizationsPage(driver);
		op.getCreateorgbtn().click();

		// step4: Creating New Organization by entering details

		CreatingNewOrganizationPage cnop= new CreatingNewOrganizationPage(driver);
		cnop.createorg(orgName);
					
             
    // verify header msg  expected result
             
             OrganizationInfoPage ip=new OrganizationInfoPage(driver);
             String htext =ip.getHeadertxt().getText();
             if(htext.contains(orgName))
             {
            	 System.out.println(orgName+"name is verified");
             }
             else {
            	 System.out.println(orgName+"name is not verified");
             }
   
             
             
      // step5: logout 
             
             hp.logout();
		driver.quit();
	}
}
