package com.testyantra.eshopping.pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.testyantra.eshopping.genericlibs.WebActionUtil;

public class ProductDetailsPage extends BasePage {
	
	@FindBy(className="icon-minus")
	private WebElement minusIcon;
	
	@FindBy(className="icon-plus")
	private WebElement plusIcon;
	
	@FindBy(id="quantity_wanted")
	private WebElement quantityTextField;
		
	@FindBy(id="group_1")
	private WebElement sizeListBox;
	
	@FindBy(xpath="//ul[@id='color_to_pick_list']//a")
	private List<WebElement> colorPickerList;
	
	@FindBy(name="Submit")
	private WebElement addToKartButton;
	
	@FindBy(linkText="Proceed to checkout")
	private WebElement proceedToCheckoutButton;
	
	@FindBy(xpath="//span[@title='Continue shopping']")
	private WebElement continueToShoppingButton;
	
	@FindBy(className="cross")
	private WebElement closeIcon;
	
	@FindBy(xpath="//li[@class='selected']/a")
	private WebElement selectedColor;
	
	public WebElement getMinusIcon() {
		return minusIcon;
	}

	public WebElement getPlusIcon() {
		return plusIcon;
	}
	
	public WebElement getQuantityTextField() {
		return quantityTextField;
	}

	public WebElement getSizeListBox() {
		return sizeListBox;
	}

	public List<WebElement> getColorPickerList() {
		return colorPickerList;
	}
	
	public WebElement getSelectedColor() {
		return selectedColor;
	}

	public WebElement getAddToKartButton() {
		return addToKartButton;
	}

	public WebElement getProceedToCheckoutButton() {
		return proceedToCheckoutButton;
	}

	public WebElement getContinueToShoppingButton() {
		return continueToShoppingButton;
	}

	public WebElement getCloseIcon() {
		return closeIcon;
	}

	public ProductDetailsPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver, webActionUtil);
	}
	
	public void increaseQuantity(int increaseQuantity) {
		for(int i=1;i<=increaseQuantity;i++) {
			webActionUtil.click(plusIcon);
		}
	}
	
	public void decreaseQuantity(int decreaseQuantity) {
		for(int i=1;i<=decreaseQuantity;i++) {
			webActionUtil.click(minusIcon);
		}
	}
	
	public int getQuantity() {
		return Integer.parseInt(webActionUtil.getTextFieldsValue(quantityTextField));
	}
	
	public void selectColor(String colorName) {
		for(WebElement ele:colorPickerList) {
			if(ele.getAttribute("name").equalsIgnoreCase(colorName)) {
				webActionUtil.click(ele);
				break;
			}
		}
	}
	
	public String getSelectedColorName() {
		return selectedColor.getAttribute("name");
	}
	
	public OrderDetailsPage addItemToKart(int increaseQuantity, int decreaseQuantity, String size, String colorName) {
		increaseQuantity(increaseQuantity);
		decreaseQuantity(decreaseQuantity);
		webActionUtil.selectByVisibleText(sizeListBox, size);
		selectColor(colorName);
		webActionUtil.click(addToKartButton);
		webActionUtil.click(proceedToCheckoutButton);
		return new OrderDetailsPage(driver, webActionUtil);
	}
}







