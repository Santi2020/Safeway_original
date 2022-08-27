package testcases;

import java.io.File;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

//import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
//import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.GeckoDriverService;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
//import org.testng.log4testng.Logger;
import org.testng.annotations.Parameters;

import Utilities.ReadConfig;



import net.bytebuddy.implementation.FieldAccessor.PropertyConfigurable;

public class BaseClass {
	ReadConfig readConfig = new ReadConfig();
	
	public String baseURL = readConfig.getApplicationURL() ;
	public String userName =readConfig.getUserName() ;
	public String password =readConfig.getPassword();
	
	public static WebDriver driver;
	public static Logger logger;
	
	@Parameters("browser")
	//@BeforeClass
	@BeforeMethod
	public void setUp(@Optional("chrome") String br) {
		System.out.println("Running setUp..inside beforeTest");
//		if (br.isBlank()) {
//			br="chrome";
//		}
		logger = Logger.getLogger("safewayV1");
		PropertyConfigurator.configure("log4j.properties");
		
		System.out.println("Br=  "+br);
		Reporter.log("Logging in using broser = " + br);
		if(br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", readConfig.getChromepath());
			driver= new ChromeDriver();
		}
		else if(br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", readConfig.getFirefoxpath());
			driver= new  FirefoxDriver();
		}
		else if(br.equals("edge")) {
			System.out.print("Calling from edge browser");
			System.setProperty("webdriver.edge.driver", readConfig.getEdgepath());
			System.out.print("opened with edge browser");
			driver= new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(baseURL);
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		System.out.println("Running tearDown..inside @AfterTest");
		//driver.quit();
		//System.out.println("Closing the window");
		//driver.close();
	}
	
//	@AfterClass
//	public void tearDown() {
//		//driver.quit();
//		System.out.println("Closing the window");
//		driver.close();
//	}
	
//	public void captureScreen(WebDriver driver, String tname) throws IOException {
//		TakesScreenshot ts = (TakesScreenshot) driver;
//		File source = ts.getScreenshotAs(OutputType.FILE);
//		File target = new File(System.getProperty("user.dir")+"/Screenshots/"+tname +".png");
//		FileUtils.copyFile(source, target);
//		System.out.println("Screenshot tken");
//	}
			
	public String randomString() {
		String generatedString =RandomStringUtils.randomAlphabetic(8);
		return generatedString;
	}
	
	public static String RandomNum() {
		String gereratedNum = RandomStringUtils.randomNumeric(5);
		return gereratedNum;
	}
}
