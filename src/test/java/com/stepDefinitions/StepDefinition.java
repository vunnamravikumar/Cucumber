package com.stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import com.base.BaseClass;
import com.manager.PageObjectManager;
import com.utils.Browser;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.*;
import cucumber.api.java.en.*;

public class StepDefinition extends BaseClass {

	@Before
	public void configSetUp() throws IOException {
		FileInputStream fis = new FileInputStream("config.properties");
		configProp = new Properties();
		configProp.load(fis);
		log = Logger.getLogger("");
		PropertyConfigurator.configure("log4j.properties");
		browser = new Browser();
	}

	@After
	public void tearDown(Scenario scenario){
		if(scenario.isFailed()){
			try {
				log.info(scenario.getName() + "is Failed");
				final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
			} catch (WebDriverException e) {
				e.printStackTrace();
			}

		} else{
			log.info(scenario.getName() + " is Passed");
			scenario.embed(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png");
		}
		browser.tearDown();
	}

	@Given("^User launch \"([^\"]*)\" Browser$")
	public void user_launch_browser(String browserName) throws IOException {
		driver = browser.setUp(configProp.getProperty("browserName"));
		pageObjectManager = new PageObjectManager(driver);
	}

	@Given("^User open URL \"([^\"]*)\"$")
	public void user_open_url(String url) {
		browser.launchApplication(url);
	}

	@Then("^verify the page title$")
	public void verify_the_page_title() throws Throwable {
		homePage = pageObjectManager.getHomePage();
		String pageTitle = homePage.getPageTitle();
		log.info(pageTitle);
	}


	@Then("^User search the product \"([^\"]*)\"$")
	public void user_search_the_product(String inputValue) {
		homePage = pageObjectManager.getHomePage();
		homePage.setSearchBoxValue(inputValue);
	}

	@Then("Close browser")
	public void close_browser() {
		//browser.tearDown();
		System.out.println("temporarily suspended");
	}

	@Then("User navigate to HomePage")
	public void user_navigate_to_home_page() {
		homePage = pageObjectManager.getHomePage();
		String pageTitile = homePage.getPageTitle();
		log.info("Page Title is : "+pageTitile);
	}

	@When("User clicks on Moblies link")
	public void user_clicks_on_moblies_link() {
		homePage.clickOnMobileLink();
	}

	@Then("System navigates to Mobiles page")
	public void system_navigates_to_mobiles_page() {
		mobilesPage = pageObjectManager.getMobilesPage();
		String  pageTitle = mobilesPage.getPageTitle();
		log.info("Page Title is : "+pageTitle);
	}

	@Then("click on Made for Amazon")
	public void click_on_made_for_amazon() {
		mobilesPage.clickOnMadeForAmazon();
	}

	@Then("System navigates to list of Items page")
	public void system_navigates_to_list_of_items_page() {
		resultsPage = pageObjectManager.getResultsPage();
		String  pageTitle = resultsPage.getPageTitle();
		log.info("Page Title is : "+pageTitle);
	}
	@Then("^verify the page title \"([^\"]*)\"$")
	public void verify_the_page_title(String expectedTitle) throws Throwable {
		String actualTitle=driver.getTitle();
		Assert.assertEquals(expectedTitle,actualTitle);
	}
	
	@Given("^User open URL$")
	public void user_open_URL(DataTable applicationURL) throws Throwable {
		for(Map<String, String> data : applicationURL.asMaps(String.class, String.class)){
			browser.launchApplication(data.get("applicationURL"));
		}
		
	}

	@Then("^User search the product$")
	public void user_search_the_product(DataTable products) throws Throwable {
		for(Map<String,String> inputValue : products.asMaps(String.class, String.class)){
		homePage.setSearchBoxValue(inputValue.get("products"));
		}
	}
	
	@Then("^verify the All Menu option$")
	public void verify_the_All_Menu_option() throws Throwable {
		homePage = pageObjectManager.getHomePage();
		homePage.clickOnAllMenu();
	}

}
