package com.testyantra.eshopping.pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.testyantra.eshopping.genericlibs.WebActionUtil;

public class ProductsListPage extends BasePage {
	
	@FindBy(xpath="//a[@class='product_img_link']")
	private List<WebElement> allProductsList;
	
	public List<WebElement> getAllProductsList() {
		return allProductsList;
	}

	public ProductsListPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver, webActionUtil);
	}
	
	public ProductDetailsPage clickOnProduct(int productId) {
		String product="id_product="+productId;
		for(WebElement ele:allProductsList) {
			if(ele.getAttribute("href").contains(product)) {
				webActionUtil.jsClick(ele);
				break;
			}
		}
		return new ProductDetailsPage(driver, webActionUtil);
	}
}
