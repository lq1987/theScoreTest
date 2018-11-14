package com.theScore.maven.qa;

import io.appium.java_client.android.AndroidDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class baseClass {
    AndroidDriver driver;
    String output;
    String text;
    String Screenshot;
    boolean flag = true;
    public void getScreenshot(AndroidDriver driver, String output)
    {
    	try
    	{
    		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    		String destPath = File.separator + output + File.separator;
    		FileUtils.moveFile(srcFile, new File(destPath));
    	}
    	catch (Exception e)
    	{
    		
    	}
    	
    	File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    	DateFormat dataFormat;
    	dataFormat = new SimpleDateFormat("dd-MMM-yyyy_hh_mm_ssaa");
    	new File(output).mkdirs();
    	String destFile = dataFormat.format(new Date()) + ".png";
    	try
    	{
    		FileUtils.copyFile(srcFile, new File(output + "/" + destFile));
    	}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    	}
    }
    
    public void assertionText(String output, String text, String Screenshot)
    {
    
    	
    }
    
   
    
    public void pickRandomPlayer(AndroidDriver driver, String output, String small, String text, String text1)
    {
    	Random rand = new Random();
    	int randonNum = rand.nextInt(5) + 1;
    	
    	if (randonNum == 1)
    	{
    		WebElement player = driver.findElement(By.id(output));
    		String name = player.findElement(By.id(text)).getText();
    		String nam1 = name.toString().substring(3);
    		player.click();
    		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    		String full = driver.findElement(By.id(text1)).getText();
    		boolean check = full.contains(nam1);
    		Assert.assertTrue(check);
    		//Assert.assertEquals(check,"true");
    	}
    	else
    	{
    		
    	   String num = Integer.toString(randonNum);
    		String element = small + num;
    		WebElement player = driver.findElement(By.id(element));
    		String name = player.findElement(By.id(text)).getText();
    		String nam1 = name.toString().substring(3);
    		player.click();
    		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    		String full = driver.findElement(By.id(text1)).getText();
    		boolean check = full.contains(nam1);
    		Assert.assertTrue(check);
    	}
    	}

    public void swipe(AndroidDriver driver)
    {
    	driver.swipe(600, 1500, 600, 750, 1000);
    }
}
