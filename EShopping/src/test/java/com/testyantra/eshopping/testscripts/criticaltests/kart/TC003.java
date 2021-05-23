package com.testyantra.eshopping.testscripts.criticaltests.kart;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.testyantra.eshopping.genericlibs.ExcelLibrary;
import com.testyantra.eshopping.pom.OrderDetailsPage;
import com.testyantra.eshopping.pom.ProductDetailsPage;
import com.testyantra.eshopping.pom.ProductsListPage;
import com.testyantra.eshopping.testscripts.BaseTest;

public class TC003 extends BaseTest {
	@Test
	public void testProductisNotDisplayedInKart() {
		
		//Read the data from the Excel Sheet
		String sheetName = "TC002";
		int rowNumber = 1;
		
		String menuLinkName=ExcelLibrary.getData(sheetName, rowNumber, 0);
		int productId=(int)(double)ExcelLibrary.getNumericData(sheetName, rowNumber, 1);
		int increaseQuantity=(int)(double)ExcelLibrary.getNumericData(sheetName, rowNumber, 2);
		int decreaseQuantity=(int)(double)ExcelLibrary.getNumericData(sheetName, rowNumber, 3);
		String size=ExcelLibrary.getData(sheetName, rowNumber, 4);
		String colorName=ExcelLibrary.getData(sheetName, rowNumber, 5);
		
		ProductsListPage plp = hp.clickOnMenu(menuLinkName);
		ProductDetailsPage pdp = plp.clickOnProduct(productId);
		OrderDetailsPage odp = pdp.addItemToKart(increaseQuantity, decreaseQuantity, size, colorName);
		Assert.assertFalse(odp.isProductDisplayed(productId),"The Product is Not Displayed in ODP");
		
		odp.deleteProduct(productId, size, colorName);
		Assert.assertFalse(odp.isProductDisplayed(productId),"The Product is Still Displayed in ODP");
		
	}
}
