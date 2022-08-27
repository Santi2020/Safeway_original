package testcases;

import static org.testng.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.LoginPage;



public class TC_Order_003 extends BaseClass {
	
	public boolean login() throws InterruptedException {
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
		if (actualMessage.equals(expectedMessage)) {
			return true;
		}
		else {
			return false;
		}
		
		//basicAssertion(expectedMessage, actualMessage);
	}
	
	
	
	public void clearCart() throws InterruptedException {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		
		openMiniCart();
		
		//List<WebElement> itemList = driver.findElements(By.cssSelector("h2[class='cart-product-title']"));
				
		List <WebElement> removeElementList;
		removeElementList =driver.findElements(By.cssSelector("div[class='close-btn']"));
		
		//System.out.println("No of items in the cart  =" +itemList.size());
		System.out.println("Size of remove list  =" + removeElementList.size());
		
		int i=0;
		
		if (removeElementList.size()>0)
			Thread.sleep(2000);
			WebElement removeItemElement;
			
			System.out.println("\n\n\n======Removing items from the cart======");
			//removing items from the cart
			for(i = removeElementList.size()-1 ; i>=0;i--) {  
				removeItemElement = removeElementList.get(i);
				
				try {
					WebElement xElement =driver.findElement(By.cssSelector("div[class='close-btn']"));
					
					try {
						System.out.println("clearCart--Inside try block");
						Thread.sleep(1000);
						//removeItemElement.click();
						xElement.click();
						System.out.println(i+"  -- Click worked\n\n");
					  } 
					catch(StaleElementReferenceException e) {
						System.out.println("StaleElementReferenceException occured");
					}
					catch (Exception e) {
						 System.out.println("clearCart--Inside catch block");
						 Thread.sleep(1000);
					     //executor.executeScript("arguments[0].click();", removeItemElement);
						 executor.executeScript("arguments[0].click();", xElement);
					     System.out.println(i+"  -- JavascriptExecutor worked\n\n");
					     Thread.sleep(2000);
					  }
				}
				catch(NoSuchElementException e)
				{
					System.out.println("No such element exception occured");
				}
				catch (Exception e) {
					// TODO: handle exception
					System.out.println("Exception occured "+ e.getMessage());
				}
			}
			System.out.println("\n===============================================\n");
		
	
	//closing the minicart window
		WebElement closeButton= driver.findElement(By.cssSelector("div[class='menu-nav__icon-container d-block d-lg-none activeMenu']"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", closeButton);
		
		//openMiniCart();	
	}



	/**
	 * @throws InterruptedException
	 */
	public void openMiniCart() throws InterruptedException {
		WebElement miniCart= driver.findElement(By.cssSelector("path[class='mini-cart-icon']"));
		miniCart.click();
		Thread.sleep(2000);
	}
		
	@Test
	public void testValidOrder() throws InterruptedException {
		
		JavascriptExecutor js =(JavascriptExecutor)driver;
		boolean result = login();
		String expectedTitle ="Checkout";
		
		if(result) {
			System.out.println("Login successful");
		}
		else
		{
			Assert.assertTrue(false);
		}
		
		Thread.sleep(2000);
		//clearCart();
		
		Thread.sleep(1000);
		//click on shop
		WebElement ShopElement = driver.findElement(By.xpath("//li[@class='menu-nav__list-item shop-class ']/a[@class='menu-nav__sub-item shop-link']"));
		Thread.sleep(1000);
		ShopElement.click();  
		
		//click on deals
		Thread.sleep(1000);
		WebElement dealsElement = driver.findElement(By.xpath("//*[@id=\"shopFlyOutModal\"]/div/div/div/nav/div/ul/span[1]/li[2]/a"));
		Thread.sleep(1000);
		dealsElement.click();
		
		List <WebElement>  productList; 
		productList = driver.findElements(By.xpath("//h3/a[@class='product-title']"));
		System.out.println("No of items = " + productList.size() );
		
		WebElement prod;
		StringBuilder sbAddProdXpath= new StringBuilder();
		
		for(int i=0;i< 2;i++) {
			System.out.println(i + "-" + productList.get(i).getText() );//+" $ " + priceList.get(i).getText());
			prod = productList.get(i);
			System.out.println( "Atribute id = " + prod.getAttribute("id"));
			String itemId=prod.getAttribute("id").replace("pg", "");
			sbAddProdXpath.append("//div[contains(@id,'addButton_" + itemId +"')]");
			System.out.println("xpath =" + sbAddProdXpath);
			try {
				Thread.sleep(1000);
				WebElement addBtn = driver.findElement(By.xpath(sbAddProdXpath.toString()));
				//addBtn.click();
				js.executeScript("arguments[0].click();", addBtn);
				System.out.println("Add button clicked Added item no " +i);
			}catch(Exception e) {
				//WebElement incQty = driver.findElement(By.xpath("//button[@aria-label='Increase Quantity' and @id='inc_qtyInfo_"+itemId + "']"));
				WebElement incQty = driver.findElement(By.xpath("//button[@role='button' and @id='inc_qtyInfo_"+itemId + "']"));
				js.executeScript("arguments[0].scrollIntoView();", incQty);
				Thread.sleep(1000);		
				js.executeScript("arguments[0].click();", incQty);
				//incQty.click();
				System.out.println("JavaExecutor Added item no " +i);
			}
			sbAddProdXpath.setLength(0);
		}
		
		checkout();
		
		//click on order info
		Thread.sleep(1000);
		WebElement arrowOrderInfo =driver.findElement(By.cssSelector("div[id='dugOrderInfo']>h2[class='h-2-heading d-inline-block']"));
		arrowOrderInfo.click() ;
		String contactEmail="";
		try {
			System.out.println("Finding email");
			Thread.sleep(2000);
			WebElement txtEmail=driver.findElement(By.cssSelector("input[id='contactEmail']"));
			System.out.println(" email element found");
			Thread.sleep(3000);
			//contactEmail= txtEmail.getText();
			contactEmail= txtEmail.getAttribute("value");
			System.out.println("reading the email value. email =" +contactEmail);
		}catch(Exception e) {
			System.out.println("Exception occured -- " + e.getMessage() );
		}
		System.out.println("contactEmail = " + contactEmail);
		
		Thread.sleep(2000);
		System.out.println("Clicking on continue");
		WebElement btnContinue=driver.findElement(By.cssSelector("button[class='btn btn-block btn-default primary-background-color']"));
		btnContinue.click();
		
		System.out.println("finding radio button");
		Thread.sleep(5000);
		WebElement radio11=driver.findElement(By.xpath("//input[contains(@id,'EGULARDUG1100')]"));
		//radio11.click();
		js.executeScript("arguments[0].click()", radio11);
		
		
		Assert.assertEquals(userName, contactEmail);
		Assert.assertEquals(driver.getTitle(), expectedTitle);
//		Thread.sleep(5000);
//		WebElement txtFirstName=driver.findElement(By.cssSelector("input[id='firstName']"));
//		txtFirstName.sendKeys("Simba");
//		
//		WebElement txtLastName=driver.findElement(By.cssSelector("input[id='lastName']"));
//		txtLastName.sendKeys("Shetty");
//		
//		WebElement txtDeliveryInst=driver.findElement(By.cssSelector("input[id='deliveryInstructions']"));
//		txtDeliveryInst.sendKeys("Shetty");
		
//		WebElement btnContinue=driver.findElement(By.cssSelector("button[class='btn btn-block btn-default primary-background-color']"));
//		btnContinue.click();
		
		//nextPage
		
//		System.out.println("Clicking radio button");
//		WebElement radio11am=driver.findElement(By.xpath("//input[@name='slot' and @class='radioReSize automateSlotIdInput ng-untouched ng-pristine ng-valid' and @data-index='0' ]"));
//		radio11am.click();
//		System.out.println("Clicked radio button");
//		
//		
//		WebElement btnContinue=driver.findElement(By.xpath("//button[@id = 'reserveSlotsContinue']"));
//		btnContinue.click();
	}



	/**
	 * @throws InterruptedException
	 */
	public void checkout() throws InterruptedException {
		openMiniCart();
		WebElement btnCheckuut=driver.findElement(By.cssSelector("button[class='primary-btn btn btn-default modal-checkout modal-checkout-enabled']"));
		btnCheckuut.click();
	}
}
