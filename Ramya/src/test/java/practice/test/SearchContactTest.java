package practice.test;
/**
 * test class for contact module
 * @author ramya
 * 
 */

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class SearchContactTest extends BaseClass{
   /**
    * scenarion: login()==>navigateconact==>createcontact()==>verify
    */
	
	
	@Test
	public void searchContactTest()
	{
		 /*step1: login to app*/
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp("url", "username", "password");
	}
	
}
