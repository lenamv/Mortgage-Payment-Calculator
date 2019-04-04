package ia.ca.MortgagePaymentCalculator;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ia.ca.base.TestBase;
import ia.ca.pages.MortgagePaymentCalculatorPage;
import ia.ca.util.TestUtil;

public class MortgagePaymentCalculatorPage_End_to_End_Test extends TestBase{

		MortgagePaymentCalculatorPage mortgageCalcPage;
		TestUtil testUtil = new TestUtil();
		
		@BeforeMethod
		public void setUp(){
			initialization();
			
			driver.get(prop.getProperty("URL")+prop.getProperty("MORTGAGE_PAYMENT_CALCULATOR_URL"));
			mortgageCalcPage=new MortgagePaymentCalculatorPage();
			scrollIntoViewPurchasePrise();
		}
		
		private void scrollIntoViewPurchasePrise(){
			// scroll down the page to Purchase price radio button
			WebElement priceBtn = mortgageCalcPage.scrollIntoViewPurchasePriceRadioButton();
			//verify that Purchase price radio button is selected
			Assert.assertTrue(priceBtn.isSelected(), "Purchase Price Radio Button is not selected");
		}
		
		private void scrollIntoViewPaymentFrequency(){
			//scroll down the page to the Payment frequency field
			WebElement frequency = mortgageCalcPage.scrollIntoViewPaymentFrequencyLabel();
			//verify that the Payment frequency field is displayed
			Assert.assertTrue(frequency.isDisplayed());
		}
		
		private void scrollIntoViewResultsPanel(){
			//scroll the page to the results panel
			WebElement results = mortgageCalcPage.scrollIntoViewResults();
			//verify that the results data is displayed
			Assert.assertTrue(results.isDisplayed());
		}
		
		@Test
		public void test_MPC_S01_PP_calculatePaymentsAndCheckResults(){
		
		
		// ----------------------------entering data to calculate payments-------------------//	
			//-------------------------------Test data---------------------------------------//
			int expPrice = 1250000;					//Purchase price
			float expPers = (float)62.5; 			//Assertion: Purchase price slider %
			float setPayment = (float)250000;		//Set 1st Down Payment  
			float expPayment = (float)300000; 		//Down Payment
			int expAmortization = 20;				//Amortization
			float expRate = (float)3.88;			//Interest rate
			String expectedPaymentFrequency = "Biweekly"; //Payment frequency
			
			//move Purchase price slider handle to 1,25M
			mortgageCalcPage.movePurchasePriceSlider(0.625);
			
			//click plus button of Purchase Price slider to match the handler to 1,25M sign
			mortgageCalcPage.clickPlusButtonPurchasePriceSlider();
			
			//verify that the price is 1,250,000 in the Purchase Price field
			int price = mortgageCalcPage.getPurchasePrice();
			Assert.assertEquals(price, expPrice, "The Purchase Price is not correct.");
			
			//verify that the slider percentage is 62.5%
			String expPricePerc = testUtil.convertPercentToString(expPers);
			//String expPricePerc = "left: "+Float.toString(expPers)+"%;";
			String sliderPerc = mortgageCalcPage.getPositionPurchasePriceSlider();
			Assert.assertEquals(sliderPerc, expPricePerc, "The Purchase price slider percentage does not equal to the expected value.");
	
			//enter 250000 into  the Down payment field
			mortgageCalcPage.setDownPayment(setPayment);
			
			//click plus button of Down Payment slider to adjust down payment to 300000
			mortgageCalcPage.clickPlusButtonDownPaymentSlider();
			
			//verify that the price is 300000 in the Down payment field
			float payment = mortgageCalcPage.getDownPayment();
			Assert.assertEquals(payment, expPayment, "The Down payment is not correct.");
			
			//select Amortization "20 years" from the drop down list
			mortgageCalcPage.selectAmortizationOptionFromList(20);
			
			//verify that Amortization is "20 years" in the Amortization field 
			String expectedAmortizationStr = expAmortization+" years";
			String amortization = mortgageCalcPage.getAmortizationYears();
			Assert.assertEquals(amortization, expectedAmortizationStr, "Amortization is not correct.");
				
			//enter Interest rate "3.88" in Interest rate field
			mortgageCalcPage.setInterestRate(expRate);
			//verify Interest rate is "3.88"
			float rate = mortgageCalcPage.getInterestRate();
			Assert.assertEquals(rate, expRate, "Interest Rate is not correct");
		
			//scroll down the page to the Payment frequency field
			scrollIntoViewPaymentFrequency();
			
			//select Payment Frequency "Biweekly" from the drop down list
			mortgageCalcPage.selectPaymentFrequencyOptionFromList("Biweekly");
			
			//verify that Payment Frequency is "Biweekly" in the Payment Frequency field 
			String paymentFrequency = mortgageCalcPage.getPaymentFrequency();
			Assert.assertEquals(paymentFrequency, expectedPaymentFrequency, "Payment Frequency is not correct.");
			
			//click the Calculate button
			mortgageCalcPage.clickCalculateButton();
			
		// ----------------end entering data to calculate payments-----------------------//
			
		
		// ----------------------------checking the results-------------------------------//		
			//-----------------------Test data-------------------------------------------//
			String expPaymentFrequency = "bi-weekly";	//Payment frequency
			float expectedResult = (float)2619.97;		//Payments
			float expAmount = (float)1362384.40; 		//The total amount paid
			
			//scroll the page to the results panel
			scrollIntoViewResultsPanel();
			
			//verify that payment frequency is "bi-weekly"
			String resultFrequency = mortgageCalcPage.getPaymentFrequencyResults();
			Assert.assertTrue(resultFrequency.contains(expPaymentFrequency), "Results: Payment frequency is not correct.");
			
			//verify that Payment result is 2,619.97
			float result = mortgageCalcPage.getPaymentResults();
			Assert.assertEquals(result, expectedResult, "Results: Payment result is not correct.");
			
			//verify that Mortgage Balance is 950,000.00 (Purchase price minus Down payment)
			float resultBalance = mortgageCalcPage.getMortgageBalanceResults();
			Assert.assertEquals(resultBalance, expPrice-expPayment, "Results: Mortgage balance is not correct.");
			
			//verify that the total amount paid is 1,362,384.40
			float resultAmount = mortgageCalcPage.getTotalAmountResults();
			Assert.assertEquals(resultAmount, expAmount, "Results: Total amount is not correct.");
			
			//verify that payments calculated over 20 over 
			int resultYears = mortgageCalcPage.getOverYearsResults();
			Assert.assertEquals(resultYears, expAmortization, "Results: Years of payment are not correct.");
			
			//verify that download, send by email, print results links display  		
			Assert.assertTrue(mortgageCalcPage.getDownloadResults());
			Assert.assertTrue(mortgageCalcPage.getSendMeResults());
			Assert.assertTrue(mortgageCalcPage.getPrintResults());
			
			//verify that Send the results to an advisor button is clickable
          	Assert.assertTrue(mortgageCalcPage.sendResultsToEdvisorIsClickable());
         
        // ------------------------------end checking the results-------------------------------//	
        
        // ------------------------------checking Cost breakdown-------------------------------//
          	          	
          	
          	float expInterest = (float)412384.40; 	//Interest
          	
          	//verify that Loan amount is 950,000.00 (Purchase price minus Down payment)
          	float loan = mortgageCalcPage.getCostLoanAmount();
          	Assert.assertEquals(loan, expPrice-expPayment, "Loan is not correct.");
			
          	//verify that Down payment is 300,000.00
          	float dnPayment = mortgageCalcPage.getCostDownPayment();
			Assert.assertEquals(dnPayment, expPayment, "Down payment is not correct.");
			
			//verify that Interest is 412,384.40
			float interest = mortgageCalcPage.getInterest();
			Assert.assertEquals(interest, expInterest, "Interest is not correct.");
			
		// ------------------------------end checking Cost breakdown------------------------------//
				
		}
	
		
		@AfterMethod
		public void closeBrowser(ITestResult result) throws IOException {
			tearDown(result);
		}
		
		
	}
