package ia.ca.pages;

import java.util.List;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import ia.ca.base.TestBase;


public class MortgagePaymentCalculatorPage extends TestBase{
	
	WebDriverWait wait;
	//-------------------Purchase price--------------------------//
	@FindBy(id="par_valeur")
	WebElement purchasePriceRd;
	
	@FindBy(id="PrixPropriete")
	WebElement purchasePriceFld;
	
	@FindBy(xpath="//input[@id='MiseDeFond']//preceding::div/div[@class='slider-track']")
	WebElement purchasePriceSlider;
	
	@FindBy(xpath="//input[@id='MiseDeFond']//preceding::div//div[@class='slider-handle min-slider-handle custom']")
	WebElement purchasePriceSliderHandle;
		
	@FindBy(id="PrixProprieteMinus")
	WebElement purchasePriceMinusBtn;
	
	@FindBy(id="PrixProprietePlus")
	WebElement purchasePricePlusBtn;
	
	//-------------------Down payment--------------------------//
	@FindBy(id="MiseDeFond")
	WebElement downPaymentFld;
		
	@FindBy(xpath="//input[@id='MiseDeFond']//following::div/div[@class='slider-track']")
	WebElement downPaymenSlider;
	
	@FindBy(xpath="//input[@id='MiseDeFond']//following::div//div[@class='slider-handle min-slider-handle custom']")
	WebElement downPaymenSliderHandle;
		
	@FindBy(id="MiseDeFondMinus")
	WebElement downPaymenMinusBtn;
	
	@FindBy(id="MiseDeFondPlus")
	WebElement downPaymenPlusBtn;
	
	//--------------------Amortization-------------------------//
	@FindBy(xpath="//span[contains(text(), 'years')]/following::b[@class='button'][1]")
	WebElement amortizationArrow;
	
	@FindBy(xpath="//span[@class='label'][contains(text(),'years')]")
	WebElement amortizationFld;
	
	@FindBy(xpath="//li[contains(text(),'years')]")
	List<WebElement> listAmortizationOptions;
	//--------------------Interest rate------------------------//
	@FindBy(id="TauxInteret")
	WebElement interestRateFld;
	
	//--------------------Payment frequency--------------------//
	@FindBy(xpath="//label[contains(text(),'Payment frequency')]")
	WebElement paymentFrequencyLbl;
	
	@FindBy(id="FrequenceVersement")
	WebElement paymentFrequencySelect;
	
	@FindBy(xpath="//span[contains(text(), 'Monthly')]/following::b[@class='button']")
	WebElement paymentFrequencyArrow;
	
	//@FindBy(id="FrequenceVersement")
	@FindBy(xpath="//div//*[@id='FrequenceVersement']//following::span[1]")
	WebElement paymentFrequencyFld;
	
	@FindBy(xpath="//li[contains(text(),'weekly')]")
	List<WebElement> listPaymentFrequencyOptions;
	//--------------------Calculate button---------------------//
	@FindBy(id="btn_calculer")
	WebElement calculateBtn;
	
	//-------------------Results------------------------------//
	@FindBy(xpath="//span[@class='icone-info-centre icone-calculateur']")
	WebElement infoCalculaterIcn;
	
	@FindBy(id="phrase-resultats")
	WebElement resultsFrequencyTxt;
	
	@FindBy(id="paiement-resultats")
	WebElement paymentResultTxt;
	
	@FindBy(xpath="//p[@id='versementsEconomies']/span[1]")
	WebElement mortgageBalanceTxt;
	
	@FindBy(xpath="//p[@id='versementsEconomies']/span[2]")
	WebElement totalAmountTxt;
	
	@FindBy(xpath="//p[@id='versementsEconomies']/span[3]")
	WebElement overYearsTxt;
	
	//-------------------Results links------------------------------//
	@FindBy(id="imprimer")
	WebElement downloadResultsLnk;
	
	@FindBy(id="sendParEmail")
	WebElement sendMeResultsLnk;
	
	@FindBy(id="courriel")
	WebElement emailFld;
	
	@FindBy(id="inputAccepteMarketing")
	WebElement accepteMarketingChbox;
	
	@FindBy(id="//form[@id='formSendEmail']/button")
	WebElement sendEmailBtn;
	
	@FindBy(xpath="//a[contains(text(),'Print the results')]")
	WebElement printResultsLnk;
	
	@FindBy(id="btnEnvoyerResultConseiller")
	WebElement sendResultsAdvisorBtn;
	
	//-------------------Results Cost breakdown---------------------//
	@FindBy(xpath="//div[@id='repartition']/h3")
	WebElement costBreakdownTxt;
	
	@FindBy(xpath="//li[@id='legend_montant']/span")
	WebElement loanAmountTxt;
	
	@FindBy(xpath="//li[@id='legend_fonds']/span")
	WebElement dounPaymentTxt;
	
	@FindBy(xpath="//li[@id='legend_interets']/span")
	WebElement interestTxt;
	
	@FindBy(xpath="//li[@id='legend_schl']/span")
	WebElement mortgageInsuranceTxt;
	
	@FindBy(xpath="//div[@id='astuce']/h3")
	WebElement tipHeaderTxt;
	
	@FindBy(xpath="//div[@id='astuce']/p")
	WebElement tipTxt;
	
	public MortgagePaymentCalculatorPage(){
		PageFactory.initElements(driver, this);
	}
	
	//------------------Methods: scrolling a page to view of WebElement----------------------------//
	public WebElement scrollIntoViewPurchasePriceRadioButton(){
		return scrollIntoViewWebElement(purchasePriceRd);
	}
	
	public WebElement scrollIntoViewPaymentFrequencyLabel(){
		return scrollIntoViewWebElement(paymentFrequencyLbl);
	}
	
	public WebElement scrollIntoViewResults(){
		return scrollIntoViewWebElement(resultsFrequencyTxt);
	}
	
	//---------------------------Methods: Purchase Price Field-----------------------------------//	
	public int getPurchasePrice(){
		String price = purchasePriceFld.getAttribute("value");
		return Integer.parseInt(price);
	}
	
	public void setPurchasePrice(int price){
		purchasePriceFld.clear();
		purchasePriceFld.sendKeys(Integer.toString(price));
	}
	//---------------------------Methods: Purchase Price slider-----------------------------------//
	public void movePurchasePriceSlider(int num){
		movePriceSlider(purchasePriceSlider,purchasePriceSliderHandle, num);
	}
	
	public void movePurchasePriceSlider(double num){
		movePriceSlider(purchasePriceSlider,purchasePriceSliderHandle, num);
	}
	
	public void clickMinusButtonPurchasePriceSlider(){
		purchasePriceMinusBtn.click();
	}
	
	public void clickPlusButtonPurchasePriceSlider(){
		purchasePricePlusBtn.click();
		}
	
	public String getPositionPurchasePriceSlider(){
		 return purchasePriceSliderHandle.getAttribute("style");
	}
	//---------------------------Methods: Down Payment Field-------------------------------------//
	public float getDownPayment(){
		String payment = downPaymentFld.getAttribute("value");
		return Float.parseFloat(payment);
	}
	
	public void setDownPayment(float price){
		downPaymentFld.click();
		downPaymentFld.clear();
		downPaymentFld.sendKeys(Float.toString(price));
	}
	
	//---------------------------Methods: Down Payment slider-------------------------------------//
	public void moveDownPaymentSlider(int num){
		movePriceSlider(downPaymenSlider, downPaymenSliderHandle, num);
	}
	public void clickMinusButtonDownPaymentSlider(){
		downPaymenMinusBtn.click();
	}
	public void clickPlusButtonDownPaymentSlider(){
		downPaymenPlusBtn.click();
		}
	public String getPositionDownPaymenSlider(){
		return downPaymenSliderHandle.getAttribute("style");
	}
	
	//---------------------------Methods: Amortization------------------------------------------//
	public void selectAmortizationOptionFromList(int years){
		String yearAmort = Integer.toString(years);
		String yearStr = String.format("%s years", yearAmort);
		selectOptionsFromList(amortizationFld, listAmortizationOptions, yearStr);
	}
	public String getAmortizationYears(){
		return amortizationFld.getText();
	}
	
	//---------------------------Methods: Interest Rate-------------------------------------//
	public float getInterestRate(){
		String inter = interestRateFld.getAttribute("value");
		return Float.parseFloat(inter);
	}
	
	public void setInterestRate(float rate){
		String rateStr = Float.toString(rate);
		interestRateFld.clear();
		interestRateFld.sendKeys(rateStr);
	}
	
	//---------------------------Methods: Payment Frequency-------------------------------------//
	public void selectPaymentFrequencyOptionFromList(String option){
		selectOptionsFromList(paymentFrequencyFld, listPaymentFrequencyOptions, option);
	}
	
	public String getPaymentFrequency(){
		return paymentFrequencyFld.getText();
	}
	
	//---------------------------Methods: Calculate Button--------------------------------------//
	public void clickCalculateButton(){
		calculateBtn.click();
	}
	
	public boolean infoCalculaterIcon(){
		return infoCalculaterIcn.isDisplayed();
	}
	
	public WebElement getDownloadResultsLnk(){
		return downloadResultsLnk;
	}
	
	//---------------------------Methods: Results Panel: Numbers---------------------------------//
	public String getPaymentFrequencyResults(){
		return resultsFrequencyTxt.getText();
	}
	
	public float getPaymentResults(){
		return convertStringNumbersToFloat(paymentResultTxt);
	}
	
	public float getMortgageBalanceResults(){
		return convertStringNumbersToFloat(mortgageBalanceTxt);
	}
	
	public float getTotalAmountResults(){
		return convertStringNumbersToFloat(totalAmountTxt);
	}
	
	public int getOverYearsResults(){
		//return convertStringNumbersToFloat(overYearsTxt);
		String yearsStr= overYearsTxt.getText();
		int years = Integer.parseInt(yearsStr);
		return years;
	}
	//---------------------------Methods: Results Panel: Links---------------------------------//
	public boolean getDownloadResults(){
		String linkText = "Download the results (PDF)";
		return downloadResultsLnk.getText().equals(linkText);
	}
	
	public void clickDownloadResults(){
		downloadResultsLnk.click();
	}
	
	public boolean getSendMeResults(){
		String linkText = "Send me the results by email (PDF)";
		return sendMeResultsLnk.getText().equals(linkText);
	}
	
	public boolean getPrintResults(){
		String linkText = "Print the results";
		return printResultsLnk.getText().equals(linkText);
	}
	
	public boolean sendResultsToEdvisorIsClickable(){
		return sendResultsAdvisorBtn.isEnabled();
	}
	
	//---------------------------Methods: Results Panel: Coast breakdown-------------------------------//
	
	public float getCostLoanAmount(){
		return convertStringNumbersToFloat(loanAmountTxt);
	}
	
	public float getCostDownPayment(){
		return convertStringNumbersToFloat(dounPaymentTxt);
	}
	
	public float getInterest(){
		return convertStringNumbersToFloat(interestTxt);
	}
	
	public float getMortgageInsuranceTxt(){
		return convertStringNumbersToFloat(mortgageInsuranceTxt);
	}
	
	//---------------------------------Methods: Library------------------------------//
	
	private float convertStringNumbersToFloat(WebElement element){
		String loanStr =  element.getText();
		String loanStrSh = loanStr.substring(1,loanStr.length());
		String [] arr= new String [loanStrSh.length()-1];
		arr = loanStrSh.split(",");	
		String newStr = "";
		for(int i=0; i<arr.length; i++){
			newStr = newStr +arr[i];
		}
		
		float newStrInt  = Float.parseFloat(newStr);
		return newStrInt;
	}
	
	private void movePriceSlider(WebElement slider, WebElement handler, int num){
		Dimension sliderSize = slider.getSize();
		int sliderWidth = sliderSize.getWidth();
	  	
		int moveFuctor = (int)sliderWidth/num;
		Actions moveSld = new Actions(driver);
		moveSld.moveToElement(handler)
	         .click()
	         .dragAndDropBy(handler, moveFuctor, 0).build().perform();
	}
	
	
	private void movePriceSlider(WebElement slider, WebElement handler, double num){
		Dimension sliderSize = slider.getSize();
		int sliderWidthDbl = sliderSize.getWidth();
		double moveFuctorDbl = sliderWidthDbl * num;	
	  	int moveFuctor = (int) moveFuctorDbl;
		
		Actions moveSld = new Actions(driver);
		moveSld.moveToElement(handler)
	         .click()
	         .dragAndDropBy(handler, moveFuctor, 0).build().perform();
	}
	
		
	private void selectOptionsFromList(WebElement element, List<WebElement> listOptions, String option){
		element.click();
		for(int i=0; i<listOptions.size(); i++){
			if (listOptions.get(i).getText().trim().equals(option)){
				listOptions.get(i).click();
				break;
			} 
		 } 
	}
	
	private WebElement scrollIntoViewWebElement(WebElement element){
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].scrollIntoView(true);",element);
		return element;
	}
	

}	
