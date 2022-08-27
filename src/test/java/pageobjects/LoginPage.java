package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver lDriver;
	
	public LoginPage(WebDriver rDriver){
		lDriver=rDriver;
		PageFactory.initElements(rDriver, this);
	}
	
	
	@FindBy(id="label-email")
	@CacheLookup
	WebElement txtUserName;
	
	@FindBy(id ="label-password")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(id ="btnSignIn")
	@CacheLookup
	WebElement btnLogin;
	
	//Logout link
//	@FindBy(xpath = "/html/body/div[3]/div/ul/li[15]/a")
//	@CacheLookup
//	WebElement lnkLogout;
	
	public void setUserName(String uname) {
		txtUserName.sendKeys(uname);
	}
	
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	public void clickSubmit() {
		btnLogin.click();
	}
	
//	public void clickLogout() {
//		lnkLogout.click();
//	}
}
