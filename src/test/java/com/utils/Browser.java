package com.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.base.BaseClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Browser extends BaseClass{


	public WebDriver setUp(String browserName) {
		log.info("Launching " + configProp.getProperty("browserName")+ " Browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("ie")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		return driver;

	}
	public void launchApplication(String url) {
		driver.get(url);
		log.info("Entered URL : " + url);
	}

	public void tearDown(){
		driver.close();
		driver.quit();
		log.info("Closing Browser");
	}


}
