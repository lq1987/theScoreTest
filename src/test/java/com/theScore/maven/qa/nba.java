package com.theScore.maven.qa;

import org.testng.annotations.Test;



import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import io.appium.java_client.android.AndroidDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.By;

public class nba extends baseClass{
	AndroidDriver<WebElement> driver ;
	String getStart; 
    String next;	
    String done;
    String league;
    String nba;
    String leader;
    String Apackage;
    String activity;
    String title;
    String lead;
    String lead2;
	String name;
	String fullname;
  @Test
  public void activate() throws IOException, Exception {
	 
	  Properties prop = new Properties();
	  FileInputStream input = new FileInputStream("src/test/resource/devint.properties");

	  prop.load(input);
	getStart = prop.getProperty("getStart");
	next = prop.getProperty("next");
	done = prop.getProperty("done");
	league = prop.getProperty("league");
	leader = prop.getProperty("leader");
	lead = prop.getProperty("lead");
	lead2 = prop.getProperty("lead2");
	name = prop.getProperty("name");
	fullname = prop.getProperty("fullname");
	  driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
	  driver.findElement(By.id(prop.getProperty("getStart"))).click();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.findElement(By.id(prop.getProperty("next"))).click();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.findElement(By.id(prop.getProperty("next"))).click();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.findElement(By.id(prop.getProperty("done"))).click();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.findElement(By.id(prop.getProperty("league"))).click();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.findElement(By.xpath("//android.widget.TextView[@text='NBA Basketball']/../..")).click();
	  	  //verify it is on nba page
	  Assert.assertEquals(driver.findElement(By.id(prop.getProperty("title"))).getText(), "NBA");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.findElementByXPath(prop.getProperty("leader")).click();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  //verify user is on correct random player page
	  int x = 1;
	  while (x <8)
	  {
	  pickRandomPlayer(driver, lead, lead2,name,fullname);
	  driver.pressKeyCode(4);
	  Thread.sleep(2000);
	  swipe(driver);
	  x++;
	  }
  }
  
  @BeforeMethod
  public void beforeMethod() throws Exception {
	  Properties prop = new Properties();
	  FileInputStream input = new FileInputStream("src/test/resource/devint.properties");
       prop.load(input);
       Apackage = prop.getProperty("Apackage");
       activity = prop.getProperty("activity");
	  String appPath = prop.getProperty("appPath"); 
		 File app = new File(appPath);
		  DesiredCapabilities capabilities=new DesiredCapabilities();
		  capabilities.setCapability("BROWSER_NAME", "");
		  capabilities.setCapability("VERSION","7.0");
		  capabilities.setCapability("deviceName","Samsung Galaxy S7");
		  capabilities.setCapability("platformName","Android");
		  capabilities.setCapability("appPackage",Apackage);
		  capabilities.setCapability("appActivity",activity);
		  capabilities.setCapability("autoGrantPermissions","true");
		  capabilities.setCapability("appWaitActivity",activity);
		  capabilities.setCapability("automationName","UiAutomator2");
		  capabilities.setCapability("newCommandTimeout","60000");
		  capabilities.setCapability("app", app.getAbsolutePath());
		  capabilities.setCapability("noSign",true);
		  driver=new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities); 
  }
//if test fail take screenshot, save image on shot folder
  @AfterMethod
  public void tearDown(ITestResult result) {
    if (ITestResult.FAILURE==result.getStatus())
    {
    	getScreenshot(driver,"shot");
    }
   // driver.quit();
  }

}


