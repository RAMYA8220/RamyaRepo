package practice.test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;

import junit.framework.Assert;

//@Listeners(com.comcast.crm.listenerUtility.ListenerImpClass.class)
public class InvoiceTest extends BaseClass{

	@Test
	public void createInvoiceTest()
	{
		System.out.println("execute createInvoiceTest");
		String actTitle=driver.getTitle();
		Assert.assertEquals(actTitle, "Login");
		System.out.println("step1");
		System.out.println("step2");
		System.out.println("step3");
		System.out.println("step4");
	}
	@Test
	public void createInvoiceWithContactTest()
	{
		System.out.println(" execute createInvoicewithcontacttest");
		System.out.println("step1");
		System.out.println("step2");
		System.out.println("step3");
		System.out.println("step4");
	}
	
	
//	@Test(retryAnalyzer=com.comcast.crm.listenerUtility.RetryListenerImp.class)
//	public void activateSim()
//	{
//		System.out.println("execute simactivateTest");
//		
//		Assert.assertEquals(" ", "Login");
//		System.out.println("step1");
//		System.out.println("step2");
//		System.out.println("step3");
//		System.out.println("step4");
//	}
}
