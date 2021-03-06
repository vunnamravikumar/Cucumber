package com.utils;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.base.BaseClass;

public class Action extends BaseClass {
	static Select select;
	
	public static void textBox(WebElement element, String inputValue){
		clickOn(element);
		element.clear();
		element.sendKeys(inputValue);
		log.info("Entered Textbox Value is : "+inputValue);
		
	}
	
	public static void clickOn(WebElement element){
		Assert.assertEquals(true, element.isDisplayed());
		element.click();
		log.info("Clicked on Element");
	}
	
	public static String getText(WebElement element){
		log.info("Value is : "+element.getText());
		return element.getText();
	}
	
	public static void selectDropdownValue(WebElement element, String inputValue){
		select = new Select(element);
		select.selectByValue(inputValue);
	}
	
	public static void selectVisitibleText(){

	}
	
	public static void selectValueByIndex(){

	}

}
