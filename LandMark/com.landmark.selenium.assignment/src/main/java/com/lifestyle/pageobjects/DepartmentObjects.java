package com.lifestyle.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DepartmentObjects {
	
	@FindBy(xpath="//*[@id='dept-women']/a/span")
	public static WebElement WomenDepartment;

	@FindBy(xpath="//*[@id='dept-men']/a/span")
	public static WebElement MensDepartment;
	
	@FindBy(xpath="//*[@id='dept-kids']/a/span")
	public static WebElement KidsDepartment;
	
	@FindBy(xpath="//*[@id='dept-shoes bags']/a/span")
	public static WebElement ShoeBagDepartment;
	
}
