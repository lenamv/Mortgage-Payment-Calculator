package ia.ca.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import ia.ca.util.TestUtil;

public class TestBase {

		
		public static WebDriver driver;
		public static Properties prop;
		TestUtil testUtil;
		
				
		public TestBase(){
			try{
				prop = new Properties();
				FileInputStream fl=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/ia/ca/config/config.properties");
				
				prop.load(fl);
			} catch (FileNotFoundException e){
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

		public void initialization(){
			String browserName = prop.getProperty("browser");
			
			if (browserName.equalsIgnoreCase("firefox")){
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/browsers/geckodriver.exe");
				driver=new FirefoxDriver();
			} else
				
			if (browserName.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/browsers/chromedriver.exe");
				driver=new ChromeDriver();
			} else
				
			if (browserName.equalsIgnoreCase("ie")){
				System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/browsers/IEDriverServer.exe");
				driver=new InternetExplorerDriver();	
			} else
				
			if (browserName.equalsIgnoreCase("edge")){
				System.setProperty("webdriver.edge.driver",System.getProperty("user.dir")+"/browsers/MicrosoftWebDriver.exe");
				driver=new EdgeDriver();
			}
			
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIME, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICITLY_WAIT, TimeUnit.SECONDS);
			
		}	
		
		public void tearDown(ITestResult result) throws IOException {
			takeScreenshot(result);
			driver.quit();
		}
		
		public void takeScreenshot(ITestResult result) {
			LocalDateTime dateTime = LocalDateTime.now();
			String date = dateTime.format(DateTimeFormatter.ofPattern("_dd-MM-yyyy_HH.mm.ss.SSS"));
			
			ITestNGMethod instance = result.getMethod();
			String testName = instance.getMethodName();
			
			try {
				File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				String dir = System.getProperty("user.dir");
				FileHandler.copy(scr, new File(dir + "/screenshots/" + testName + date +".png"));
			} catch (IOException e){
				e.printStackTrace();
			}
		}
		
		
		
		
	}

