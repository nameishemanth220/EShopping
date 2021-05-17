package com.testyantra.eshopping.genericlibs;

import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//A wrapper class which contains wrapper methods to perform actions on webelements of webpage
public class WebActionUtil {
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	public WebActionUtil(WebDriver driver, long explict) {
		this.driver = driver;
		wait = new WebDriverWait(driver, explict);
	}
	
	public void enterData(WebElement targetElement, String text) {
		wait.until(ExpectedConditions.visibilityOf(targetElement));
		targetElement.clear();
		targetElement.sendKeys(text);
	}
	
	public void click(WebElement targetElement) {
		wait.until(ExpectedConditions.elementToBeClickable(targetElement));
		targetElement.click();
	}
	
	public void jsClick(WebElement targetElement) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", targetElement);
	}
	
	public void scrollDownOrUp(int pixels, boolean scrollUp) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		if(scrollUp) {
			jse.executeScript("window.scrollBy(0,arguments[0]);", -pixels);
		} else {
			jse.executeScript("window.scrollBy(0,arguments[0]);", pixels);
		}		
	}
	
	public void enterDataUsingJS(WebElement targetElement, String text) {
		wait.until(ExpectedConditions.visibilityOf(targetElement));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].value=arguments[1];", targetElement, text);
	}
	
	public void moveToElement(WebElement targetElement) {
		Actions actions = new Actions(driver);
		actions.moveToElement(targetElement).perform();
	}
	
	public void dragAndDrop(WebElement source, WebElement target) {
		Actions actions = new Actions(driver);
		actions.dragAndDrop(source, target).perform();
	}
	
	public void doubleClick(WebElement targetElement) {
		Actions actions = new Actions(driver);
		actions.doubleClick(targetElement).perform();
	}
	
	public void selectByVisibleText(WebElement targetElement, String visibleText) {
		Select select = new Select(targetElement);
		select.selectByVisibleText(visibleText);
	}
	
	public String getSelectedOption(WebElement targetElement) {
		Select select = new Select(targetElement);
		return select.getFirstSelectedOption().getText();
	}
	
	public void switchToFrame(String indexNameOrId) {
		try {
			int index = Integer.parseInt(indexNameOrId);
			driver.switchTo().frame(index);
		} catch(NumberFormatException e) {
			driver.switchTo().frame(indexNameOrId);
		}
	}
	
	public void switchToTitledWindow(String windowTitle) {
		Set<String> allWindowIds=driver.getWindowHandles();
		for(String wid:allWindowIds) {
			driver.switchTo().window(wid);
			if(driver.getTitle().contains(windowTitle)) {
				return;
			}
		}
	}
	
	public void closeAllChildWindows() {
		String parentWindowId=driver.getWindowHandle();
		
		Set<String> allWindowIds=driver.getWindowHandles();
		allWindowIds.remove(parentWindowId);
		
		for(String wid:allWindowIds) {
			driver.switchTo().window(wid);
			driver.close();
		}
		
		driver.switchTo().window(parentWindowId);
	}
	
	public void switchToAlertAndAccept(boolean accept) {
		wait.until(ExpectedConditions.alertIsPresent());
		if(accept) {
			driver.switchTo().alert().accept();
		} else {
			driver.switchTo().alert().dismiss();
		}
	}
	
	public String getTextFieldsValue(WebElement targetElement) {
		if(targetElement.getTagName().equalsIgnoreCase("input")) {
			return targetElement.getAttribute("value");
		} else {
			return null;
		}
	}

	public void waitForInvisibilityOfElement(WebElement targetElement) {
		wait.until(ExpectedConditions.invisibilityOf(targetElement));		
	}
}
