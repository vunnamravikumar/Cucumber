package com.base;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.manager.PageObjectManager;
import com.pageObjects.HomePage;
import com.pageObjects.MobilesPage;
import com.pageObjects.ResultsPage;
import com.utils.Browser;

public class BaseClass {
	
	public WebDriver driver;
	public Browser browser;
	public static Logger log;
	public static Properties configProp;
	public PageObjectManager pageObjectManager;
	public HomePage homePage;
	public MobilesPage mobilesPage;
	public ResultsPage resultsPage;

}
