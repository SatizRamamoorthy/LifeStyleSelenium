package com.lifestyle.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CategoryAndFilterObject {
	
	public static String sizePath = null;
	
	@FindBy(xpath="//*/div[@id='category-menu-1']/div/div/a/span[@class='MuiButton-label']")
	public static List<WebElement> categoryies;
	
	@FindBy(id="price-range-selector")
	public static WebElement PriceSelector;
	
	@FindBy(className="dot")
	public static List<WebElement> PriceRange;
	
	@FindBy(xpath="//Button/span/div[contains(@class,'MuiBox-root')]")
	public static List<WebElement> filterElement;
	
	@FindBy(xpath="(//div[contains(@class,'MuiBox-root')]/div /div[contains(@class,'MuiCollapse')])[8] //span/div")
	public static List<WebElement> browseOffers;
	
	@FindBy(xpath="(//div[contains(@class,'MuiBox-root')]/div /div[contains(@class,'MuiCollapse')])[7] //span/div")
	public static List<WebElement> brandSelect;
	
	@FindBy(xpath="//div[contains(@id,'main-part')]/div/div[4] //div[contains(@class,'MuiGrid-container')]/div[2]") //"(//div[contains(@class,'MuiBox-root')]/div/div[contains(@class,'MuiGrid-item')][2])[1]"
	public static WebElement addProduct;
	
	@FindBy(xpath="//div[contains(@id,'main-part')]/div/div[4] //div[contains(@class,'MuiGrid-item')][2]/div/div[3]/a")
	public static WebElement productName;
	
	@FindBy(xpath="//div[contains(@id,'main-part')]/div/div[4] //div[contains(@class,'MuiGrid-item')][2]/div/div[2]/div[1]")
	public static WebElement price;
	
	@FindBy(xpath="//div[contains(@class,'quickViewWrapper')] //div[contains(@class,'select-size')]")
	public static WebElement size;
	
	@FindBy(xpath = "//div[@class='MuiCollapse-wrapper'] //ul[contains(@class,'MuiList-padding')]/div[text()='42']")
	public static WebElement chooseSize;
	
	@FindBy(xpath="//div[contains(@class,'quickViewWrapper')] //div[contains(@class,'add-to-basket')]")
	public static WebElement Basket;
	
	@FindBy(xpath="//button/span/div[contains(text(),'Basket')]")
	public static WebElement BasketButton;
	
	@FindBy(xpath="(//div[@class='MuiCollapse-wrapperInner']//div/div/div[3])[1]/div[2]/div[2]/div/div[1]")
	public static WebElement ActualPriceInCart;
	
	@FindBy(xpath="(//div[@class='MuiCollapse-wrapperInner']//div/div/div[3])[1]/div[2]/div[2]/div[2]/a")
	public static WebElement productNameInCart;
	
	@FindBy(xpath="(//div[@class='MuiCollapse-wrapperInner']//div/div/div[3])[1]/div[2]/div[2]/div[3]")
	public static WebElement Quantity;
	
	@FindBy(id="mini-basket-checkout")
	public static WebElement checkoutButton;
	
}
