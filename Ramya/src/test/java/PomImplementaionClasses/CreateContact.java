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
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreateNewContactpage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class CreateContact {

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

		

		// read testScript data from Excelutility
		
		String lastName=elib.getDataFromExcel("contact", 4,2) +jlib.getRandomNumber();
		
		
		//polymorphism program : in property file if we change browser name then also it should run.
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
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp("admin", "admin");
		
//step2: navigate to organization link
		HomePage hp=new HomePage(driver);
		hp.getContactlink().click();
		
// step3: click on create contact btn
		ContactsPage cp=new ContactsPage(driver);
		cp.getcreatecontactbtn().click();
		
//step4: creating contact by entering deatails
		
		CreateNewContactpage cnp=new CreateNewContactpage(driver);
		cnp.createContact(lastName);
		
		//verification of contact header
		ContactInfoPage cinfo=new ContactInfoPage(driver);
		String chead=cinfo.getContactHeader().getText();
		if(chead.contains(lastName))
		{
			System.out.println(lastName +" is verified");
		}
		else {
			System.out.println(lastName +" is not verified");
		}
		
//step5: logout
		hp.logout();
		driver.quit();

	}

}
