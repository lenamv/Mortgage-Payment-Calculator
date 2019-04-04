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


public class MortgagePaymentCalculator_ComponentsFunctions_Test extends TestBase{

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
			WebElement frequency =mortgageCalcPage.scrollIntoViewPaymentFrequencyLabel();
			//verify that the Payment frequency field is displayed
			Assert.assertTrue(frequency.isDisplayed());
		}
		
		private void scrollIntoViewResultsPanel(){
			//scroll the page to the results panel
			WebElement results = mortgageCalcPage.scrollIntoViewResults();
			//verify that the results data is displayed
			Assert.assertTrue(results.isDisplayed());
		}
	
		
	//------------------------------------------Purchase Price-------------------------------------------------//
		@Test
		public void test_MPC01_PP_enterPurchasePrice(){
			
			int expectedPrice = 1000000;
			
			//enter Purchase Price 1000000 in Purchase price field
			mortgageCalcPage.setPurchasePrice(expectedPrice);
			
			//verify that the price is 1000000 in the Purchase Price field
			int price = mortgageCalcPage.getPurchasePrice();
			Assert.assertEquals(price, expectedPrice, "The Purchase Price is not correct.");
		}
		
		@Test
		public void test_MPC02_PP_movePurchasePriceSlider(){
			
			int expectedPrice = 2000000;
			float expPricePerc = 100;
			
			//move Purchase price slider handle to end
			mortgageCalcPage.movePurchasePriceSlider(1);
			
			//verify that the price is 2,000,000 in the Purchase Price field
			int price = mortgageCalcPage.getPurchasePrice();
			Assert.assertEquals(price, expectedPrice, "The Purchase Price is not correct.");
			
			//verify that the slider percentage is 100%
			String expectedPricePerc = testUtil.convertPercentToString(expPricePerc);
			String sliderPerc = mortgageCalcPage.getPositionPurchasePriceSlider();
			Assert.assertEquals(sliderPerc, expectedPricePerc, "The Purchase price slider percentage does not equal to the expected value.");
		}
		
		@Test
		public void test_MPC03_PP_clickPlusButtonPurchasePriceSlider(){
			
			int expectedPrice = 500000;
			float expPricePerc = (float)25;
			
			//click plus button of Purchase Price slider twice
			mortgageCalcPage.clickPlusButtonPurchasePriceSlider();
			mortgageCalcPage.clickPlusButtonPurchasePriceSlider();
			
			//verify that the price is 500,000 in the Purchase Price field
			int price = mortgageCalcPage.getPurchasePrice();
			Assert.assertEquals(price, expectedPrice, "The Purchase Price is not correct.");
			
			//verify that the slider percentage is 25%
			String expectedPricePerc = testUtil.convertPercentToString(expPricePerc);
			String sliderPerc = mortgageCalcPage.getPositionPurchasePriceSlider();
			Assert.assertEquals(sliderPerc, expectedPricePerc, "The Purchase price slider percentage does not equal to the expected value.");
		}
		
		@Test
		public void test_MPC04_PP_clickMinusButtonPurchasePriceSlider(){
			
			int expectedPrice = 1500000;
			float expPricePerc = (float)75;
			
			//move Purchase price slider handle to end
			mortgageCalcPage.movePurchasePriceSlider(1);
			
			//click minus button of Purchase Price slider twice
			mortgageCalcPage.clickMinusButtonPurchasePriceSlider();
			mortgageCalcPage.clickMinusButtonPurchasePriceSlider();
			
			//verify that the price is 1,500,000 in the Purchase Price field
			int price = mortgageCalcPage.getPurchasePrice();
			Assert.assertEquals(price, expectedPrice, "The Purchase Price is not correct.");
			
			//verify that the slider percentage is 75%
			String expectedPricePerc = testUtil.convertPercentToString(expPricePerc);
			String sliderPerc = mortgageCalcPage.getPositionPurchasePriceSlider();
			Assert.assertEquals(sliderPerc, expectedPricePerc, "The Purchase price slider percentage does not equal to the expected value.");
		}
		
	//------------------------------------------Down Payment-------------------------------------------------//
		@Test
		public void test_MPC05_PP_enterDownPayment(){
			//---------------------preconditions--------------------------	
			
			int purchPrice = 1000000;
			float expectedPayment = (float)500000;
			
			//enter Purchase Price 1,000,000 in Purchase price field
			mortgageCalcPage.setPurchasePrice(purchPrice);
			//---------------------end preconditions--------------------------
			
			//enter Down Payment 500,000 in Down payment field
			mortgageCalcPage.setDownPayment(expectedPayment);
			
			//verify that the payment is 500,000 in the Down payment field
			float payment = mortgageCalcPage.getDownPayment();
			Assert.assertEquals(payment, expectedPayment, "The Down payment is not correct.");
		}
		
		@Test
		public void test_MPC06_PP_moveDownPaymentSlider(){
			
			float expectedPayment = (float)1000000;
			float expMortgagePerc = (float)100;
			
			//move Down payment slider handle to end
			mortgageCalcPage.moveDownPaymentSlider(1);
			
			//verify that the payment is 1,000,000 in the Down payment field
			float payment = mortgageCalcPage.getDownPayment();
			Assert.assertEquals(payment, expectedPayment, "The Down payment is not correct.");
			
			//verify that the slider percentage is 100%
			String expectedMortgagePerc = testUtil.convertPercentToString(expMortgagePerc);
			String sliderPerc = mortgageCalcPage.getPositionDownPaymenSlider();
			Assert.assertEquals(sliderPerc, expectedMortgagePerc, "The Down payment slider percentage does not equal to the expected value.");
		}
		
		@Test
		public void test_MPC07_PP_clickPlusButtonDownPaymentSlider(){
			int purchPrice = 1000000;
			float expectedPrice = (float)200000;
			float expPricePerc = 20;
			
			//---------------------preconditions--------------------------	
			
			//enter Purchase Price 1,000,000 in Purchase price field
			mortgageCalcPage.setPurchasePrice(purchPrice);
			//---------------------end preconditions--------------------------
			
			//click plus button of Purchase Price slider twice
			mortgageCalcPage.clickPlusButtonDownPaymentSlider();
			mortgageCalcPage.clickPlusButtonDownPaymentSlider();
			
			//verify that the price is 200000 in the Purchase Price field
			float price = mortgageCalcPage.getDownPayment();
			Assert.assertEquals(price, expectedPrice, "The Purchase Price is not correct.");
			
			//verify that the slider percentage is 20%
			String expectedPricePerc = testUtil.convertPercentToString(expPricePerc);
			String sliderPerc = mortgageCalcPage.getPositionDownPaymenSlider();
			Assert.assertEquals(sliderPerc, expectedPricePerc, "The Down payment slider percentage does not equal to the expected value.");
		}
		
		@Test
		public void test_MPC08_PP_clickMinusButtonDownPaymentSlider(){
			
			int purchPprice = 1000000;
			float expectedPrice = (float)800000;
			float expPricePerc = 80;
			
			//---------------------preconditions--------------------------	
			//enter Purchase Price 1000000 in Purchase price field
			
			mortgageCalcPage.setPurchasePrice(purchPprice);
			
			//move Down payment slider handle to end
			mortgageCalcPage.moveDownPaymentSlider(1);
			//---------------------end preconditions-----------------------		
			
			//click minus button of Purchase Price slider twice
			mortgageCalcPage.clickMinusButtonDownPaymentSlider();
			mortgageCalcPage.clickMinusButtonDownPaymentSlider();
			
			//verify that the price is 800000 in the Purchase Price field
			float price = mortgageCalcPage.getDownPayment();
			Assert.assertEquals(price, expectedPrice, "The Purchase Price is not correct.");
			
			//verify that the slider percentage is 80%
			String expectedPricePerc = testUtil.convertPercentToString(expPricePerc);
			String sliderPerc = mortgageCalcPage.getPositionDownPaymenSlider();
			Assert.assertEquals(sliderPerc, expectedPricePerc, "The Down payment slider percentage does not equal to the expected value.");
		}
		
		
		
		//------------------------------------------Amortization-------------------------------------------------//
		@Test
		public void test_MPC09_PP_selectAmortizationFromDropDown(){
			
			int amotrtization = 20;
			
			//select Amortization "20 years" from the drop down list
			mortgageCalcPage.selectAmortizationOptionFromList(amotrtization);
			
			//verify that Amortization is "20 years" in the Amortization field 
			String expectedAmortization = amotrtization + " years";
			String amortization = mortgageCalcPage.getAmortizationYears();
			Assert.assertEquals(amortization, expectedAmortization, "Amortization is not correct.");
		}
		
		//------------------------------------------Interest Rate-------------------------------------------------//
		@Test
		public void test_MPC10_PP_enterInterestRate(){
			
			float expectedRate = (float)3.88;
			
			//enter Interest rate "3.88" in Interest rate field
			mortgageCalcPage.setInterestRate(expectedRate);
			//verify Interest rate is "3.88"
			float rate = mortgageCalcPage.getInterestRate();
			Assert.assertEquals(rate, expectedRate, "Interest Rate is not correct");
		}
		
		//------------------------------------------Payment Frequency-------------------------------------------------//
		@Test
		public void test_MPC11_PP_selectPaymentFrequencyFromDropDown(){
			
			String expectedPaymentFrequency = "Biweekly";
			
			//scroll down the page to the Payment frequency field
			scrollIntoViewPaymentFrequency();
			
			//select Payment Frequency "Biweekly" from the drop down list
			mortgageCalcPage.selectPaymentFrequencyOptionFromList(expectedPaymentFrequency);
						
			//verify that Payment Frequency is "Biweekly" in the Payment Frequency field 
			String paymentFrequency = mortgageCalcPage.getPaymentFrequency();
			Assert.assertEquals(paymentFrequency, expectedPaymentFrequency, "Payment Frequency is not correct.");
		}
		
		@Test
		public void test_MPC12_PP_clickCulculateButton(){
			
			int purchPrice = 1000000;
			float expectedPayment = (float)200000;
			float expectedResult = (float)4160.59;
			
			//---------------------preconditions--------------------------	
			//enter Purchase Price 1,000,000 in Purchase price field
			mortgageCalcPage.setPurchasePrice(purchPrice);
			
			//verify that the price is 1000000 in the Purchase Price field
			int price = mortgageCalcPage.getPurchasePrice();
			Assert.assertEquals(price, purchPrice, "The Purchase Price is not correct.");
			
			//enter Down Payment 200,000 in Down payment field
			mortgageCalcPage.setDownPayment(expectedPayment);
			
			//verify that the payment is 200,000 in the Down payment field
			float payment = mortgageCalcPage.getDownPayment();
			Assert.assertEquals(payment, expectedPayment, "The Down payment is not correct.");
			
			//---------------------end preconditions--------------------------
			
			//scroll down the page to the Payment frequency field
			scrollIntoViewPaymentFrequency();
			//click the Calculate button
			mortgageCalcPage.clickCalculateButton();
			
			//scroll the page to the results panel
			scrollIntoViewResultsPanel();
		
			//verify that Payment result is 4,160.59
			float result = mortgageCalcPage.getPaymentResults();
			Assert.assertEquals(result, expectedResult, "Payment result is not correct.");	
		}
		
		@AfterMethod
		public void closeBrowser(ITestResult result) throws IOException {
			tearDown(result);
		}
		
	}
