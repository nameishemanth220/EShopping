package com.testyantra.eshopping.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.testyantra.eshopping.genericlibs.WebActionUtil;

public class OrderDetailsPage extends BasePage {
	
	String deleteIconXp="//td[@class='cart_product']/a[contains(@href,'id_product=pid') and contains(@href,'size-sizeValue') and contains(@href,'colorName')]/../..//i[@class='icon-trash']";
	
	@FindBy(xpath="//td[@class='cart_product']/a")
	private List<WebElement> allProductsList;
		
	
	public List<WebElement> getAllProductsList() {
		return allProductsList;
	}

	public OrderDetailsPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver, webActionUtil);
	}
	
	public boolean isProductDisplayed(int productId) {
		String product="id_product="+productId;
		for(WebElement ele:allProductsList) {
			if(ele.getAttribute("href").contains(product)) {
				return true;
			}
		}
		return false;
	}

	public void deleteProduct(int productId, String sizeValue, String colorName) {
		String xp=deleteIconXp.replace("pid", productId+"")
						.replace("sizeValue", sizeValue.toLowerCase())
											.replace("colorName", colorName.toLowerCase());
		WebElement deleteIcon = driver.findElement(By.xpath(xp));
		webActionUtil.click(deleteIcon);
		webActionUtil.waitForInvisibilityOfElement(deleteIcon);
	}
}
