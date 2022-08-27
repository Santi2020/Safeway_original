package testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
//import org.junit.Assert;
//import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import pageobjects.LoginPage;


public class TC_Login_001 extends BaseClass {

	@Test
	public void loginTestValidData() throws InterruptedException, IOException {
		
		//System.out.println("Inside loginTestValidData ");
		//click on signup/in
		driver.findElement(By.xpath("//span[@class=\"button__item menu-nav__profile-button-sign-in-up d-none d-lg-inline-block dst-sign-in-up\"]")).click();
		
		//click on sign in
		driver.findElement(By.xpath("//a[@id='sign-in-modal-link']")).click();
		
		logger.info("Login-Loggin page");
		Thread.sleep(2000);
		LoginPage lp = new LoginPage(driver);
		
		String expectedMessage="Home - Online Grocery Delivery | Safeway";
		lp.setUserName(userName);
		logger.info("Login-Entered username");
		
		lp.setPassword(password);
		logger.info("Login-Entered password");
		
		lp.clickSubmit();
				
		String actualMessage=driver.getTitle();
		basicAssertion(expectedMessage, actualMessage);
	}

	@Test
	public void loginTestInvalidUser() throws InterruptedException, IOException {
		//System.out.println("Inside loginTestInvalidUser ");
		
		//click on signup/in
		driver.findElement(By.xpath("//span[@class=\"button__item menu-nav__profile-button-sign-in-up d-none d-lg-inline-block dst-sign-in-up\"]")).click();
		
		//click on sign in
		driver.findElement(By.xpath("//a[@id='sign-in-modal-link']")).click();
		
		logger.info("Login-Loggin page");
		Thread.sleep(2000);
		LoginPage lp = new LoginPage(driver);
		
		String expectedMessage="The email address or password entered doesn't match our records. Please make sure your email is correct or create a new account here.";
		//System.out.println("expectedMessage = " + expectedMessage);
		lp.setUserName(userName +"dummy");
		logger.info("Login-Entered username");
		
		lp.setPassword(password);
		logger.info("Login-Entered password");
		
		driver.findElement(By.xpath("//button[@id='cookieConsentClose']")).click();
		lp.clickSubmit();
		//System.out.println("Submit clicked");
		
		Thread.sleep(2000);
		String actualMessage = driver.findElement(By.xpath("//div[@id='error-message']")).getText();
		//System.out.println("errorMessage "+ actualMessage);
		basicAssertion(expectedMessage, actualMessage);	
	}
	
	
	@Test
	public void loginTestInvalidPassword() throws InterruptedException, IOException {
		//System.out.println("Inside loginTestInvalidPassword ");
		//click on signup/in
		driver.findElement(By.xpath("//span[@class=\"button__item menu-nav__profile-button-sign-in-up d-none d-lg-inline-block dst-sign-in-up\"]")).click();
		
		//click on sign in
		driver.findElement(By.xpath("//a[@id='sign-in-modal-link']")).click();
		
		logger.info("Login-Loggin page");
		Thread.sleep(2000);
		LoginPage lp = new LoginPage(driver);
		
		String expectedMessage="The email address or password entered doesn't match our records. Please make sure your email is correct or create a new account here.";
		//System.out.println("expectedMessage = " + expectedMessage);
		lp.setUserName(userName );
		logger.info("Login-Entered username");
		
		lp.setPassword(password+"dummy");
		logger.info("Login-Entered password");
		
		driver.findElement(By.xpath("//button[@id='cookieConsentClose']")).click();
		
		lp.clickSubmit();
		//System.out.println("Submit clicked");
		
		Thread.sleep(2000);
		String actualMessage = driver.findElement(By.xpath("//div[@id='error-message']")).getText();
		//System.out.println("errorMessage "+ actualMessage);
		basicAssertion(expectedMessage, actualMessage);	
	}
	
	
	@Test
	public void loginTestInvalidUserPassword() throws InterruptedException, IOException {
		//System.out.println("Inside loginTestInvalidUserPassword ");
		
		//click on signup/in
		driver.findElement(By.xpath("//span[@class=\"button__item menu-nav__profile-button-sign-in-up d-none d-lg-inline-block dst-sign-in-up\"]")).click();
		
		//click on sign in
		driver.findElement(By.xpath("//a[@id='sign-in-modal-link']")).click();
		
		logger.info("Login-Loggin page");
		Thread.sleep(2000);
		LoginPage lp = new LoginPage(driver);
		
		String expectedMessage="The email address or password entered doesn't match our records. Please make sure your email is correct or create a new account here.";
		//System.out.println("expectedMessage = " + expectedMessage);
		lp.setUserName(userName +"dummy" );
		logger.info("Login-Entered username");
		
		lp.setPassword(password+"dummy");
		logger.info("Login-Entered password");

		driver.findElement(By.xpath("//button[@id='cookieConsentClose']")).click();
		
		lp.clickSubmit();
		//System.out.println("Submit clicked");
		
		Thread.sleep(2000);
		String actualMessage = driver.findElement(By.xpath("//div[@id='error-message']")).getText();
		//System.out.println("errorMessage "+ actualMessage);
		basicAssertion(expectedMessage, actualMessage);
		
	}
	
	
	/**
	 * @param expectedMessage
	 * @param actualMessage
	 */
	public void basicAssertion(String expectedMessage, String actualMessage) {
		if(actualMessage.equals(expectedMessage)) {
			Assert.assertTrue(true);
			logger.info("Login-Login Test passed");
		}
		else {
			//captureScreen(driver, "loginTest");
			//System.out.println("Test case failed. Screenshot taken");
			Assert.assertTrue(false);
			logger.info("Login-Login Test failed");
		}
	}



}
