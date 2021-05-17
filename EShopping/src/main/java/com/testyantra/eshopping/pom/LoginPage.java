package com.testyantra.eshopping.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.testyantra.eshopping.genericlibs.WebActionUtil;

public class LoginPage extends BasePage {
	
	@FindBy(linkText="Sign in")
	private WebElement signInLink;
	
	@FindBy(id="email")
	private WebElement emailIdTextField;
	
	@FindBy(id="passwd")
	private WebElement passwordTextField;
	
	@FindBy(id="SubmitLogin")
	private WebElement signInButton;
		
	public WebElement getSignInLink() {
		return signInLink;
	}

	public WebElement getEmailIdTextField() {
		return emailIdTextField;
	}

	public WebElement getPasswordTextField() {
		return passwordTextField;
	}

	public WebElement getSignInButton() {
		return signInButton;
	}

	public LoginPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver, webActionUtil);
	}
	
	public HomePage signIn(String emailId, String emailPassword) {
		webActionUtil.click(signInLink);
		webActionUtil.enterData(emailIdTextField, emailId);
		webActionUtil.enterData(passwordTextField, emailPassword);
		webActionUtil.click(signInButton);
		return new HomePage(driver, webActionUtil);
	}
	
	
}
