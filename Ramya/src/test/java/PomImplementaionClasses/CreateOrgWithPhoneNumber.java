package PomImplementaionClasses;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
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

public class CreateOrgWithPhoneNumber {

	public static void main(String[] args) throws Throwable {
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();

		
		//read common data from lib

				String BROWSER = flib.getDataFromPropertiesFile("browser");
				String URL = flib.getDataFromPropertiesFile("url");

    	// read testScript data from Excel file
				
				String orgName=elib.getDataFromExcel("org", 4,2) +jlib.getRandomNumber();
				String phoneNumber=elib.getDataFromExcel("org", 7, 3);
		
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
				hp.getOrglink().click();
				
		//step3: click on organization link icon
				
				OrganizationsPage op=new OrganizationsPage(driver);
				op.getCreateorgbtn().click();
				
		//step4: create new organization by entering values
				CreatingNewOrganizationPage cnp=new CreatingNewOrganizationPage(driver);
				cnp.createorgWithPhone(orgName, phoneNumber);
				
				
		//verification of orgname and phonenum
				
				OrganizationInfoPage ip=new OrganizationInfoPage(driver);
	             String htext =ip.getHeadertxt().getText();
	             if(htext.contains(orgName))
	             {
	            	 System.out.println(orgName+"name is verified");
	             }
	             else {
	            	 System.out.println(orgName+"name is not verified");
	             }
	             
	             
	            String actphone=ip.getPhoneNumber().getText();
				if(actphone.contains(phoneNumber))
				{
					System.out.println(phoneNumber + "num is verified");
	             }
	             else {
	            	 System.out.println(phoneNumber + "num is not verified");
	             }
				
	
       //step5: logout
	       hp.logout();
	       driver.quit();
	}

}
