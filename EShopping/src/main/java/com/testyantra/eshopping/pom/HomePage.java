package com.testyantra.eshopping.pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.testyantra.eshopping.genericlibs.WebActionUtil;

public class HomePage extends BasePage {
	
	@FindBy(linkText="Sign out")
	private WebElement signOutLink;
	
	@FindBy(xpath="//div[@id='block_top_menu']/ul/li/a")
	private List<WebElement> menuLinksList;
	
	public WebElement getSignOutLink() {
		return signOutLink;
	}

	public List<WebElement> getMenuLinksList() {
		return menuLinksList;
	}

	public HomePage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver, webActionUtil);
	}
	
	public void signOut() {
		webActionUtil.click(signOutLink);
	}
	
	public ProductsListPage clickOnMenu(String menuName) {
		for(WebElement ele:menuLinksList) {
			if(ele.getText().equalsIgnoreCase(menuName)) {
				webActionUtil.click(ele);
				break;
			}
		}
		return new ProductsListPage(driver, webActionUtil);
	}
}
