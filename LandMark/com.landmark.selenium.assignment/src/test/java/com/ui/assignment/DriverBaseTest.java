package com.ui.assignment;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.lifestyle.pageobjects.CategoryAndFilterObject;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverBaseTest {
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest extendTest;
	
	
	WebDriver driver = null;
	
	String ExpectedName, ExpectedPrice, productPriceInCart, quantityInCart, productNameInCart = null;
	
	@BeforeClass
	public void generateReport() {
		
		htmlReporter = new ExtentHtmlReporter("./reports/extent.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("LandMark Assignment Report");
		htmlReporter.config().setReportName("Life Style WebPage Product Checkout");
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.setSystemInfo("Browser Name", "Chrome");
		extent.attachReporter(htmlReporter);
		
		extendTest = extent.createTest("verifyLifeStyleCheckoutPage");
	}
	
	@BeforeTest
	public void setup() throws Exception {

		WebDriverManager.chromedriver().setup();
		
		//ScreenRecorderUtil.startRecord("verifyLifeStyleCheckoutPage");
		
		//System.setProperty("webdriver.chrome.driver","C:\\\\Users\\\\RAMAS067\\\\Desktop\\\\LearningSelenium\\\\Chrome\\\\chromedriver_win32\\\\chromedriver.exe");
		 
		ChromeOptions options = new ChromeOptions();

		options.addArguments("--disable-notifications");

		driver = new ChromeDriver(options);
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@AfterTest
	public void tearDown() {

		driver.quit();
		extent.flush();
	}
	
	public void selectCategory(String Category) {

		List<WebElement> collectCategory = CategoryAndFilterObject.categoryies.stream()
				.filter(a -> a.getText().contains(Category)).collect(Collectors.toList());

		collectCategory.get(0).click();
	}
	
	public void scrollDown(int x, int y) {

		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollBy(" + x + "," + y + ")");
	}
	
	public void setPrice() {
		
		try {
			List<WebElement> price = CategoryAndFilterObject.PriceRange.stream().collect(Collectors.toList());

			WebElement MinimumPrice = price.get(0);

			WebElement MaximumPrice = price.get(1);

			Actions actions = new Actions(driver);

			actions.clickAndHold(MaximumPrice).moveByOffset(-50, 0).click().perform();

			CategoryAndFilterObject.PriceSelector.click();
			
		} catch(Exception e) {
			extendTest.log(Status.FAIL ,"Unable to set price");
		}	
	}
	
	public WebElement selectFilter(String Filter) {
		List<WebElement> collectCategory =null;
		try {
			 collectCategory = CategoryAndFilterObject.filterElement.stream()
					.filter(a -> a.getText().contains(Filter)).collect(Collectors.toList());

			return collectCategory.get(0);
		} catch(Exception e) {
			extendTest.log(Status.FAIL ,"Unable to select the filter");
		}
		return collectCategory.get(0);
	}
	
	public void selectOffer(String OfferName) {

		List<WebElement> collectOffers = CategoryAndFilterObject.browseOffers.stream().collect(Collectors.toList());

		for (WebElement offer : collectOffers) {
			String offerText = offer.getText();
			if (offerText.contains(OfferName)) {
				offer.click();
				break;
			}
		}
		
		selectFilter("Browse").click();
	}
	
	public void selectBrand(String BrandName) throws InterruptedException {

		List<WebElement> collectBrands = CategoryAndFilterObject.brandSelect.stream().collect(Collectors.toList());

		for (WebElement Brand : collectBrands) {
			String BrandText = Brand.getText();
			if (BrandText.contains(BrandName)) {
				Brand.click();
				break;
			}
		}
	}
	
	public void selectSecondProductAndDetails(Actions action) {

		try {

			action.moveToElement(CategoryAndFilterObject.addProduct).build().perform();

			ExpectedName = CategoryAndFilterObject.productName.getText();

			String productPrice = CategoryAndFilterObject.price.getText();

			String[] ab = productPrice.split("\n");

			ExpectedPrice = ab[1].trim();
			
		} catch (Exception e) {
			extendTest.log(Status.FAIL , "Unable to select product or get product name and price");
		}
		
	}

	public void selectSizeContinueToAddToBasket() {
		
		try {

			CategoryAndFilterObject.size.click();

			CategoryAndFilterObject.chooseSize.click();

			CategoryAndFilterObject.Basket.click();

			WebDriverWait wait = new WebDriverWait(driver, 20);

			wait.until(ExpectedConditions.elementToBeClickable(CategoryAndFilterObject.BasketButton)).click();
		} catch (Exception e) {
			extendTest.log(Status.FAIL, "Exception occured while (selecting Size / Adding product to basket) ");
		}
	}
	
	public void getPriceandProductDetailsInCart() {
		
		try {

			String priceInCart = CategoryAndFilterObject.ActualPriceInCart.getText();
			String[] pc = priceInCart.split(" ");
			productPriceInCart = pc[1].trim();
			productNameInCart = CategoryAndFilterObject.productNameInCart.getText();
			String quantity = CategoryAndFilterObject.Quantity.getText();
			String[] qty = quantity.split(":");
			quantityInCart = qty[1].trim();

		} catch (Exception e) {
			extendTest.log(Status.FAIL ,"In Quick Checkout product name and product price is not displayed");
		}
	}

}
