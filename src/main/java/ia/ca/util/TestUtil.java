package ia.ca.util;

import ia.ca.base.TestBase;


public class TestUtil extends TestBase{
	
	
	public static long PAGE_LOAD_TIME=40;
	public static long IMPLICITLY_WAIT=40;
	
	
	
	//convert float value of percent to a string "left: " + percent + "%;" 
	//if percent is integer, it is converted from float to integer  
	public String convertPercentToString(float expPricePerc){
		String price;
		int priceInt = (int)expPricePerc;
		float diff = expPricePerc - priceInt;
			
			if(diff ==0){
				price = "left: " + priceInt + "%;";
			} else {
				price = "left: " + expPricePerc + "%;";
			}
			return price;
		}
		

}
