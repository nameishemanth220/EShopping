package com.testyantra.eshopping.genericlibs;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

// Generic Functions which can be across all the test scripts / POM
public class FrameworkUtility implements IAutoConstants {
	
	public static void sleepInSeconds(long seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static String takeScreenshot(WebDriver driver, String testCaseName) {
		String timeStamp = LocalDateTime.now().toString().replace(':', '-');
		TakesScreenshot ts = (TakesScreenshot)driver;
		File srcFile=ts.getScreenshotAs(OutputType.FILE);
		File destFile=new File(IMG_PATH+timeStamp+testCaseName+".png");
		try {
			FileUtils.copyFile(srcFile, destFile);
			return destFile.getAbsolutePath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
