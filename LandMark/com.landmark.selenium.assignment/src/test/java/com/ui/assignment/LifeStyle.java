package com.ui.assignment;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.lifestyle.helpers.Constants;
import com.lifestyle.pageobjects.CategoryAndFilterObject;
import com.lifestyle.pageobjects.DepartmentObjects;

public class LifeStyle extends DriverBaseTest {
	
  @Test
  public void verifyLifeStyleCheckoutPage() throws Exception {
	  
		try {
			
			extendTest.info("Step : Launching Lifestyle Online Shopping WebPage");

			driver.get(Constants.url);

			driver.manage().window().maximize();

			PageFactory.initElements(driver, DepartmentObjects.class);

			extendTest.info("Step : Select Deparment");
			
			DepartmentObjects.MensDepartment.click();

			PageFactory.initElements(driver, CategoryAndFilterObject.class);
			
			extendTest.info("Step : Select Wearable Type");

			selectCategory("Topwear");
			
			extendTest.info("Step : Filter price wise");

			CategoryAndFilterObject.PriceSelector.click();

			setPrice();
			
			extendTest.info("Step : Apply Brand Filter");

			selectFilter("Brand").click();
			
			selectBrand("CODE");
			
			selectFilter("Brand").click();
			
			extendTest.info("Step : Apply Discount Filter");

			selectFilter("Browse").click();

			selectOffer("BEST BUY");

			Actions action = new Actions(driver);
			
			extendTest.info("Step : Select the product");

			selectSecondProductAndDetails(action);
			
			extendTest.info("Step : Add product to the basket");

			selectSizeContinueToAddToBasket();
			
			getPriceandProductDetailsInCart();
			
			extendTest.info("Step : Verify the product Name Price Quantity Details");

			Assert.assertEquals(ExpectedPrice, productPriceInCart);

			Assert.assertEquals(ExpectedName, productNameInCart);

			Assert.assertEquals(Constants.ONE, quantityInCart);

			CategoryAndFilterObject.checkoutButton.click();
			
			extendTest.log(Status.PASS, "Mens Wearable procut is checked out successfully");

		} catch (Exception e) {

			e.printStackTrace();
			extendTest.log(Status.FAIL , "Exception occured while checkout");
	  }
  }

}
