package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
//1
	public int getRandomNumber()
	{
		Random r=new Random();
		int randomnumber=r.nextInt(50000);
		return randomnumber;
	}
	////2
	public String getSystemDateYYYYDDMM()
	{
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(d);
		return date;
	}
	/////3
	public String getRequiredDateYYYYDDMM(int days)
	{
		
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
        
        
        Calendar cal= sim.getCalendar();
        cal.add(Calendar.DAY_OF_MONTH, days);
       String reqDate= sim.format(cal.getTime());
	   return reqDate;

	}
}


