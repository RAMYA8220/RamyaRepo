package PomImplementaionClasses;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreateNewContactpage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
import com.comcast.crm.objectrepositoryutility.SwitchingToWindowPage;

public class CreateContactWithOrg {

	public static void main(String[] args) throws Throwable {
		
		//create object
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
		WebDriverUtility wlib=new WebDriverUtility();
		//read common data from lib

		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");

		// read testScript data from Excelutility
		
				String orgName=elib.getDataFromExcel("contact", 7,2) +jlib.getRandomNumber();
				String contactLastName=elib.getDataFromExcel("contact", 7, 3)+jlib.getRandomNumber();
				
				// polymorphism program : in property file if we change browser name then also
				// it should run.
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
		   
//----------------------------------------------------------------------------------------
				
		     		Thread.sleep(1000);
		          hp.getContactlink().click();  
		             
		// step6: click on create contact btn
				ContactsPage cp=new ContactsPage(driver);
				cp.getcreatecontactbtn().click();
				
		// step7: creating new contact with organization icon
				CreateNewContactpage cnp=new CreateNewContactpage(driver);
				cnp.getLastName().sendKeys(contactLastName);
				cnp.getLookupbtn().click();
				
				Thread.sleep(1000);
				wlib.switchToTabOnURL(driver, "module=Accounts");
				
		// step8: switching to window
				SwitchingToWindowPage sw=new SwitchingToWindowPage(driver);
				sw.getSearchText().sendKeys(orgName);
				sw.getSearchbtn().click();
				
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		wlib.switchToTabOnURL(driver, "module=Contacts&action");
		
		cnp.getSavebtn().click();
		
		ContactInfoPage cip=new ContactInfoPage(driver);
		String actorg1=cip.getorglinkincontact().getText();
		if(actorg1.contains(orgName))
		{
			System.out.println(orgName +"verified");
			
		}
		else {
			System.out.println(orgName +"not verified");
		}
		
		// step9: logout
		hp.logout();
		driver.quit();

	}

}
