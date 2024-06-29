package extentReports;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrgUsingExtentReportTest {
	
	public class CreateOrgTest extends BaseClass {

		@Test(groups="smokeTest")
		
		public void createOrgTest() throws EncryptedDocumentException, IOException {
			 
			UtilityClassObject.getTest().log(Status.INFO, "Read the data from excel"); //ListenerImpClass.test.log(Status.INFO, "Read the data from excel");
			// read testScript data from Excel file
			String orgName = elib.getDataFromExcel("contact", 1, 2) + jlib.getRandomNumber();

			// step2: navigate to an organization module
			UtilityClassObject.getTest().log(Status.INFO, "navigate to org page");
			HomePage hp = new HomePage(driver);
			hp.getOrglink().click();

			// step3. click on "create organization" button
			UtilityClassObject.getTest().log(Status.INFO, "navigate to create org page");
			OrganizationsPage op = new OrganizationsPage(driver);
			op.getCreateorgbtn().click();

			// step4: Creating New Organization by entering details

			UtilityClassObject.getTest().log(Status.INFO, "create a new org");
			CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
			cnop.createorg(orgName);

			// verify header msg expected result
			UtilityClassObject.getTest().log(Status.INFO, "=====>create a new org");
			OrganizationInfoPage ip = new OrganizationInfoPage(driver);
			String htext = ip.getHeadertxt().getText();
			if (htext.contains(orgName)) {
				System.out.println(orgName + "name is verified");
			} else {
				System.out.println(orgName + "name is not verified");
				
				
				
			}

		}
	}
}
