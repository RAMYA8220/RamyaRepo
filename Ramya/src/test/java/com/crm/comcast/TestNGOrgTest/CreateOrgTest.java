package com.crm.comcast.TestNGOrgTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.listenerUtility.ListenerImpClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;


@Listeners(com.comcast.crm.generic.listenerUtility.ListenerImpClass.class)
public class CreateOrgTest extends BaseClass {

	@Test(groups="smokeTest")
	
	public void createOrgTest() throws EncryptedDocumentException, IOException {
		 
		
		// read testScript data from Excel file
		String orgName = elib.getDataFromExcel("contact", 1, 2) + jlib.getRandomNumber();

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
			System.out.println(orgName + "name is verified");
		} else {
			System.out.println(orgName + "name is not verified");
			
			
			
		}

	}

//	@Test(groups="regressionTest")
//	public void CreateOrgWithIndustry() throws Throwable, IOException {
//		// read testScript data from Excel file
//		String industry = elib.getDataFromExcel("org", 4, 3);
//		String type = elib.getDataFromExcel("org", 4, 4);
//		String orgName = elib.getDataFromExcel("org", 4, 2) + jlib.getRandomNumber();
//
//		// step2: navigate to organization link
//		HomePage hp = new HomePage(driver);
//		hp.getOrglink().click();
//
////step3: click on organization link icon
//
//		OrganizationsPage op = new OrganizationsPage(driver);
//		op.getCreateorgbtn().click();
//
////step4: create new organization by entering values
//
//		CreatingNewOrganizationPage cnp = new CreatingNewOrganizationPage(driver);
//		// cnp.createorg(orgName);
//		// cnp.createorg(orgName, industry);
//		cnp.createorg(orgName, industry, type);
//
////verify orgname and industry
//
//		OrganizationInfoPage ip = new OrganizationInfoPage(driver);
//		String htext = ip.getHeadertxt().getText();
//		if (htext.contains(orgName)) {
//			System.out.println(orgName + "name is verified");
//		} else {
//			System.out.println(orgName + "name is not verified");
//		}
//
////-----------
//		String actIndus = ip.getIndustrytxt().getText();
//		if (actIndus.contains(industry)) {
//			System.out.println("industry" +industry + " is verified");
//		} else {
//			System.out.println("industry" +industry + " is not verified");
//		}
//
////--------
//
//		String actIndustype = ip.getIndustrytype().getText();
//		if (actIndustype.contains(type)) {
//			System.out.println("industry type" +type + " is verified");
//		} else {
//			System.out.println("industry type" +type + " is not verified");
//		}
//
//	}
//
//	@Test(groups="regressionTest")
//	public void CreateOrgWithPhoneNumber() throws Throwable, IOException {
//		// read testScript data from Excel file
//
//		String orgName = elib.getDataFromExcel("org", 4, 2) + jlib.getRandomNumber();
//		String phoneNumber = elib.getDataFromExcel("org", 7, 3);
//
//		// step2: navigate to organization link
//		HomePage hp = new HomePage(driver);
//		hp.getOrglink().click();
//
////step3: click on organization link icon
//
//		OrganizationsPage op = new OrganizationsPage(driver);
//		op.getCreateorgbtn().click();
//
////step4: create new organization by entering values
//		CreatingNewOrganizationPage cnp = new CreatingNewOrganizationPage(driver);
//		cnp.createorgWithPhone(orgName, phoneNumber);
//
////verification of orgname and phonenum
//
//		OrganizationInfoPage ip = new OrganizationInfoPage(driver);
//		String htext = ip.getHeadertxt().getText();
//		if (htext.contains(orgName)) {
//			System.out.println(orgName + "orgname is verified");
//		} else {
//			System.out.println(orgName + "orgname is not verified");
//		}
//
//		String actphone = ip.getPhoneNumber().getText();
//		if (actphone.contains(phoneNumber)) {
//			System.out.println(phoneNumber + " num is verified");
//		} else {
//			System.out.println(phoneNumber + " num is not verified");
//		}
//
//	}

}
