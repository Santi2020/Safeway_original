package testcases;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.google.common.base.Verify;

import Utilities.BasicUtil;



public class TC_Registration_002 extends BaseClass{
	//RegistrationPage regPage = new RegistrationPage(); 
	
	@AfterMethod
	public void tearDown() {
		System.out.println("Over written Running tearDown..inside @@AfterMethod not clossing the browser");
		//driver.quit();
		//System.out.println("Closing the window");
		//driver.close();
	}
	
	
	
	@Test
	public void testWithExistingData() throws InterruptedException {
		String expectedMessage ="You already have an account linked to the email entered, please sign in or reset your password if needed";
		String actualMessage=new String();
		driver.get("https://www.safeway.com/");
		
		WebElement signUp=driver.findElement(By.xpath("//span[@class=\"button__item menu-nav__profile-button-sign-in-up d-none d-lg-inline-block dst-sign-in-up\"]"));
		signUp.click();
		
		Thread.sleep(1000);
		//click on sign in/sign up
		driver.findElement(By.id("creat-account-modal-link")).click();
		
		//Start registration
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		Thread.sleep(1000);
		closeCookieConsent();
		String randomString=BasicUtil.randomString();
		
		String firstName="Sara"  ;
		String lastName="checkinh";
		String email ="sara2022@gmail.com" ;
		String password= "iamanewcustomer";
		String mobileNo="40" + BasicUtil.randomNumber(8);
		
		populateData(firstName,lastName,email,password,mobileNo);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement btnCreate =  driver.findElement(By.id("create-account-btn-unified"));

		js.executeScript("arguments[0].scrollIntoView();", btnCreate);
		Thread.sleep(1000);
		btnCreate.click();
		Thread.sleep(3000);
		btnCreate.click();
//		Actions act = new Actions (driver);
//		act.doubleClick(btnCreate).build().perform();

		
		
		try {
			System.out.println("start Try block");
			WebElement errorElement = driver.findElement(By.xpath("//div[@class='help-block with-errors text-left backend-errors']/span"));
			actualMessage = errorElement.getText();
			System.out.println("actualMessage = " + actualMessage);
			System.out.println("End Try block");
		}
		catch(Exception e) {
			System.out.println("error occured");
		}
		
		//basicAssertion(expectedMessage, actualMessage);
	}

	
	@Test
	public void testWithValidData() throws InterruptedException {
		String expectedMessage ="You already have an account linked to the email entered, please sign in or reset your password if needed";
		String actualMessage=new String();
		driver.get("https://www.safeway.com/");
		
		WebElement signUp=driver.findElement(By.xpath("//span[@class=\"button__item menu-nav__profile-button-sign-in-up d-none d-lg-inline-block dst-sign-in-up\"]"));
		signUp.click();
		
		Thread.sleep(1000);
		//click on sign in/sign up
		driver.findElement(By.id("creat-account-modal-link")).click();
		
		//Start registration
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		Thread.sleep(1000);
		closeCookieConsent();
		String randomString=BasicUtil.randomString();
		
		String firstName="San"+randomString  ;
		String lastName="checkinh";
		String email ="san"+ randomString +"@gmail.com" ;
		String password= "iamanewcustomer";
		String mobileNo="40" + BasicUtil.randomNumber(8);
		
		populateData(firstName,lastName,email,password,mobileNo);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement btnCreate =  driver.findElement(By.id("create-account-btn-unified"));

		js.executeScript("arguments[0].scrollIntoView();", btnCreate);
		Thread.sleep(1000);
		btnCreate.click();
		Thread.sleep(3000);
		btnCreate.click();
//		Actions act = new Actions (driver);
//		act.doubleClick(webelement);

		
		
		try {
			System.out.println("start Try block");
			WebElement errorElement = driver.findElement(By.xpath("//div[@class='help-block with-errors text-left backend-errors']/span"));
			actualMessage = errorElement.getText();
			System.out.println("actualMessage = " + actualMessage);
			System.out.println("End Try block");
		}
		catch(Exception e) {
			System.out.println("error occured");
		}
		
		basicAssertion(expectedMessage, actualMessage);
	}
	

	@Test
	public void testWithBlankFirstname() throws InterruptedException {
		String expectedMessage ="Please enter your first name.";
		String actualMessage=new String();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.safeway.com/");
		
		WebElement signUp=driver.findElement(By.xpath("//span[@class=\"button__item menu-nav__profile-button-sign-in-up d-none d-lg-inline-block dst-sign-in-up\"]"));
		signUp.click();
		
		Thread.sleep(1000);
		//click on sign in/sign up
		driver.findElement(By.id("creat-account-modal-link")).click();
		
		closeCookieConsent();
		
		String firstName="";
		String lastName="checkinh";
		String email ="checking"+ BasicUtil.randomString() +"@gmail.com" ;
		String password= "iamanewcustomer";
		String mobileNo="40" + BasicUtil.randomNumber(8);
		
		
		populateData(firstName, lastName, email, password,mobileNo);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement btnCreate =  driver.findElement(By.id("create-account-btn-unified"));

		js.executeScript("arguments[0].scrollIntoView();", btnCreate);
		Thread.sleep(1000);
		btnCreate.click();
		Thread.sleep(3000);
		btnCreate.click();
		
		try {
			System.out.println("start Try block");
			WebElement errorElement = driver.findElement(By.xpath("//ul[@class='list-unstyled']"));
			actualMessage = errorElement.getText();
			System.out.println("actualMessage = " + actualMessage);
			System.out.println("End Try block");
		}
		catch(Exception e) {
			System.out.println("error occured");
		}
		
		basicAssertion(expectedMessage, actualMessage);
	}


	@Test
	public void testWithBlankLastname() throws InterruptedException {
		String expectedMessage ="Please enter your last name.";
		String actualMessage=new String();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.safeway.com/");
		
		WebElement signUp=driver.findElement(By.xpath("//span[@class=\"button__item menu-nav__profile-button-sign-in-up d-none d-lg-inline-block dst-sign-in-up\"]"));
		signUp.click();
		
		Thread.sleep(1000);
		//click on sign in/sign up
		driver.findElement(By.id("creat-account-modal-link")).click();
		
		closeCookieConsent();
		
		String firstName="Samantha";
		String lastName="";
		String email ="checking"+ BasicUtil.randomString() +"@gmail.com" ;
		String password= "iamanewcustomer";
		String mobileNo="40" + BasicUtil.randomNumber(8);
		
		
		populateData(firstName, lastName, email, password,mobileNo);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement btnCreate =  driver.findElement(By.id("create-account-btn-unified"));

		js.executeScript("arguments[0].scrollIntoView();", btnCreate);
		Thread.sleep(1000);
		btnCreate.click();
		Thread.sleep(3000);
		btnCreate.click();
		
		
		try {
			System.out.println("start Try block");
			WebElement errorElement = driver.findElement(By.xpath("//div[@id='error-lname']/ul[@class='list-unstyled']"));
			actualMessage = errorElement.getText();
			System.out.println("actualMessage = " + actualMessage);
			System.out.println("End Try block");
		}
		catch(Exception e) {
			System.out.println("error occured");
		}
		
		basicAssertion(expectedMessage, actualMessage);
	}

	
	
	@Test
	public void testWithBlankEmail() throws InterruptedException {
		String expectedMessage ="Please enter your email address.";
		String actualMessage=new String();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.safeway.com/");
		
		WebElement signUp=driver.findElement(By.xpath("//span[@class=\"button__item menu-nav__profile-button-sign-in-up d-none d-lg-inline-block dst-sign-in-up\"]"));
		signUp.click();
		
		Thread.sleep(1000);
		//click on sign in/sign up
		driver.findElement(By.id("creat-account-modal-link")).click();
		
		closeCookieConsent();
		
		String firstName="Samantha";
		String lastName="KFGPKMNNK";
		String email ="";
		String password= "iamanewcustomer";
		String mobileNo="40" + BasicUtil.randomNumber(8);
		
		
		populateData(firstName, lastName, email, password,mobileNo);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement btnCreate =  driver.findElement(By.id("create-account-btn-unified"));

		js.executeScript("arguments[0].scrollIntoView();", btnCreate);
		Thread.sleep(1000);
		btnCreate.click();
		Thread.sleep(3000);
		//btnCreate.click();
		
		
		try {
			System.out.println("start Try block");
			WebElement errorElement = driver.findElement(By.xpath("//div[@id='error-email']/ul[@class='list-unstyled']"));
			actualMessage = errorElement.getText();
			System.out.println("actualMessage = " + actualMessage);
			System.out.println("End Try block");
		}
		catch(Exception e) {
			System.out.println("error occured");
		}
		
		basicAssertion(expectedMessage, actualMessage);
	}


	/**
	 * 
	 */
	public void closeCookieConsent() {
		try {
			System.out.println("Closing cookie consent window");
			driver.findElement(By.xpath("//button[@id='cookieConsentClose']")).click();
			System.out.println("Closed cookie consent window");
		}
		catch (Exception e){
			System.out.println("Executing catch block for cookie consent");
		}
	}

	@Test
	public void testWithBlankPassword() throws InterruptedException {
		String expectedMessage ="Please enter a password.";
		String actualMessage=new String();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.safeway.com/");
		
		WebElement signUp=driver.findElement(By.xpath("//span[@class=\"button__item menu-nav__profile-button-sign-in-up d-none d-lg-inline-block dst-sign-in-up\"]"));
		signUp.click();
		
		Thread.sleep(1000);
		//click on sign in/sign up
		driver.findElement(By.id("creat-account-modal-link")).click();
		
		closeCookieConsent();
		
		String firstName="Samantha";
		String lastName="KFGPKMNNK";
		String email ="checking"+ BasicUtil.randomString() +"@gmail.com" ;
		String password= "";
		String mobileNo="40" + BasicUtil.randomNumber(8);
		
		
		populateData(firstName, lastName, email, password,mobileNo);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement btnCreate =  driver.findElement(By.id("create-account-btn-unified"));

		js.executeScript("arguments[0].scrollIntoView();", btnCreate);
		Thread.sleep(1000);
		btnCreate.click();
		Thread.sleep(3000);
		//btnCreate.click();
		
		
		try {
			System.out.println("start Try block");
			WebElement errorElement = driver.findElement(By.xpath("//div[@id='error-password']/ul[@class='list-unstyled']"));
			actualMessage = errorElement.getText();
			System.out.println("actualMessage = " + actualMessage);
			System.out.println("End Try block");
		}
		catch(Exception e) {
			System.out.println("error occured");
		}
		
		basicAssertion(expectedMessage, actualMessage);
	}

	
	@Test
	public void testWithBlankMobileNo() throws InterruptedException {
		String expectedMessage ="Please enter a valid mobile phone number";
		String actualMessage=new String();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.safeway.com/");
		
		WebElement signUp=driver.findElement(By.xpath("//span[@class=\"button__item menu-nav__profile-button-sign-in-up d-none d-lg-inline-block dst-sign-in-up\"]"));
		signUp.click();
		
		Thread.sleep(1000);
		//click on sign in/sign up
		driver.findElement(By.id("creat-account-modal-link")).click();
		
		closeCookieConsent();
		
		String firstName="Samantha";
		String lastName="LastnameisABC";
		String email ="checking"+ BasicUtil.randomString() +"@gmail.com" ;
		String password= "iamanewcustomer";
		String mobileNo="";  
		//"40" + BasicUtil.randomNumber(8);
		
		
		populateData(firstName, lastName, email, password,mobileNo);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement btnCreate =  driver.findElement(By.id("create-account-btn-unified"));

		js.executeScript("arguments[0].scrollIntoView();", btnCreate);
		Thread.sleep(1000);
		btnCreate.click();
		Thread.sleep(3000);
		btnCreate.click();
		
		
		try {
			WebElement errorElement = driver.findElement(By.xpath("//div[@id='error-phoneOrClubcard']/ul[@class='list-unstyled']"));
			actualMessage = errorElement.getText();
			System.out.println("actualMessage = " + actualMessage);
		}
		catch(Exception e) {
			System.out.println("error occured");
		}
		
		
		basicAssertion(expectedMessage, actualMessage);
	}

	
	/**
	 * 
	 */
	public void populateData(String fName, String lName, String email, String password, String mobilsNo) {
		WebElement eleFirstName=  driver.findElement(By.id("input-firstName"));
		eleFirstName.sendKeys(fName);
		
		WebElement eleLastName=  driver.findElement(By.id("input-lastName"));
		eleLastName.sendKeys(lName);
		
		WebElement eleEmail=  driver.findElement(By.id("input-email"));
		//eleEmail.sendKeys("s" + BasicUtil.randomString() +"@gmail.com");
		eleEmail.sendKeys(  email);
		
		WebElement elePassword =  driver.findElement(By.id("showPass"));
		elePassword.sendKeys(password);
		
		WebElement eleMobilePhone=  driver.findElement(By.id("input-clubCardNumber"));
		eleMobilePhone.sendKeys(mobilsNo);
		
		WebElement terms = driver.findElement(By.id("terms-nc"));
		if (terms.isEnabled()) {
			System.out.println("Checkbox terms is enabled");
			//terms.click();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.getElementById('terms-nc').click()");
			//js.executeAsyncScript("arguments[0].click()",terms );
			System.out.println("Terms accepted");
		}else {
			System.out.println("Checkbox terms is disabled");
		}
	}

	public void basicAssertion(String expectedMessage, String actualMessage) {
		System.out.println("actualMessage   =" + actualMessage);
		System.out.println("expectedMessage = " + expectedMessage);
		Assert.assertEquals(actualMessage, expectedMessage);
	}

}
