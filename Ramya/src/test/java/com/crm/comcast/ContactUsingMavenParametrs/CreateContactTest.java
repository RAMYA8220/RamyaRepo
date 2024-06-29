package com.crm.comcast.ContactUsingMavenParametrs;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.basetest.BaseClassForCMDParameter;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreateNewContactpage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
import com.comcast.crm.objectrepositoryutility.SwitchingToWindowPage;

public class CreateContactTest extends BaseClassForCMDParameter {

	@Test(groups={"smokeTest"})
	public void createContactTest() throws Throwable, IOException {
		// read testscript data from excel
		String lastName = elib.getDataFromExcel("contact", 1, 2) + jlib.getRandomNumber();

		// step2: navigate to organization link
		HomePage hp = new HomePage(driver);
		hp.getContactlink().click();

		// step3: click on create contact btn
		ContactsPage cp = new ContactsPage(driver);
		cp.getcreatecontactbtn().click();

		// step4: creating contact by entering deatails

		CreateNewContactpage cnp = new CreateNewContactpage(driver);
		cnp.createContact(lastName);

		// verification of contact header
		ContactInfoPage cinfo = new ContactInfoPage(driver);
		
		String chead = cinfo.getContactHeader().getText();
		boolean status=chead.contains(lastName);
		Assert.assertTrue(status);
		
//		if (chead.contains(lastName)) {
//			System.out.println(lastName + " is verified");
//		} else {
//			System.out.println(lastName + " is not verified");
//		}
//		
		String Clastname=cinfo.getLastname().getText();
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(Clastname, lastName);
	}

	@Test(groups="regressionTest")
	public void CreateContactWithSuppportDate() throws EncryptedDocumentException, IOException {
		String lastName = elib.getDataFromExcel("contact", 4, 2) + jlib.getRandomNumber();
		String startDate = jlib.getRequiredDateYYYYDDMM(30);
		String endDate = jlib.getSystemDateYYYYDDMM();

		// step2: navigate to organization link
		HomePage hp = new HomePage(driver);
		hp.getContactlink().click();

// step3: click on create contact btn
		ContactsPage cp = new ContactsPage(driver);
		cp.getcreatecontactbtn().click();

//		step4: creating contact by entering all the details
		CreateNewContactpage cnp = new CreateNewContactpage(driver);

		cnp.createContact(lastName, startDate, endDate);

//verification

		ContactInfoPage cinfo = new ContactInfoPage(driver);
		String actstartdate = cinfo.getStartDate().getText();
		
		
		if (actstartdate.contains(startDate)) {
			System.out.println(startDate + "info verified ==pass");
		} else {
			System.out.println(startDate + " info  is not verified==fail");
		}

		String actenddate = cinfo.getEndDate().getText();
		if (actenddate.contains(endDate)) {
			System.out.println(endDate + "info verified ==pass");
		} else {
			System.out.println(endDate + " info  is not verified==fail");
		}

	}

	@Test(groups="regressionTest")
	public void CreateContactWithOrg() throws Throwable {

		// read testScript data from Excelutility

		String orgName = elib.getDataFromExcel("contact", 7, 2) + jlib.getRandomNumber();
		String contactLastName = elib.getDataFromExcel("contact", 7, 3) + jlib.getRandomNumber();

		// step2: navigate to an organization module
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

		// step3. click on "create organization" button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateorgbtn().click();

		// step4: Creating New Organization by entering details

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createorg(orgName);

		// verify header msg expected result

		OrganizationInfoPage ip = new OrganizationInfoPage(driver);
		String htext = ip.getHeadertxt().getText();
		if (htext.contains(orgName)) {
			System.out.println(orgName + " name is verified");
		} else {
			System.out.println(orgName + " name is not verified");
		}

//----------------------------------------------------------------------------------------

		Thread.sleep(1000);
		hp.getContactlink().click();

// step6: click on create contact btn
		ContactsPage cp = new ContactsPage(driver);
		cp.getcreatecontactbtn().click();

// step7: creating new contact with organization icon
		CreateNewContactpage cnp = new CreateNewContactpage(driver);
		cnp.getLastName().sendKeys(contactLastName);
		cnp.getLookupbtn().click();

		Thread.sleep(1000);
		wlib.switchToTabOnURL(driver, "module=Accounts");

// step8: switching to window
		SwitchingToWindowPage sw = new SwitchingToWindowPage(driver);
		sw.getSearchText().sendKeys(orgName);
		sw.getSearchbtn().click();

		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();
		wlib.switchToTabOnURL(driver, "module=Contacts&action");

		cnp.getSavebtn().click();

		ContactInfoPage cip = new ContactInfoPage(driver);
		String actorg1 = cip.getorglinkincontact().getText();
		if (actorg1.contains(orgName)) {
			System.out.println(orgName + "verified");

		} else {
			System.out.println(orgName + "not verified");
		}

	}
}
