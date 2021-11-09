package com.pageObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import com.reusable.customEnsure;
import com.reusable.sortByValue;

public class WishListPage extends PageObject {
	// reusable objReusable = new reusable();

	WebDriver driver = null;

	@FindBy(xpath = "//span[@class='price']//ins")
	protected List<WebElementFacade> listProducts;

	@FindBy(xpath = "//span[@class='price']//ins/../../..//span[contains(text(),'Add to wishlist')]")
	protected WebElementFacade btnItemAddtoWishlist;

	@FindBy(xpath = "(//i[@class=\"lar la-heart\"])[1]")
	protected WebElementFacade optionWishlist;

	@FindBy(xpath = "//a[contains(text(),'Accept all')]")
	protected WebElementFacade btnAcceptAll;

	@FindBy(xpath = "(//i[@class='la la-shopping-bag'])[1]")
	protected WebElementFacade cartPage;

	List<String> lstPriceOfItems = new ArrayList<String>();

	public void addItemtoWishlist() {
		if (!btnItemAddtoWishlist.isVisible()) {
			customEnsure.customLogWithoutScreenShot("Add to wish list button has been clicked for the product", "Pass");
		} else {
			scrollWindowDown();
		}

		btnItemAddtoWishlist.click();
	}

	public void btnAcceptAll() {
		if (!btnAcceptAll.isDisabled()) {
			btnAcceptAll.click();
			customEnsure.customLogWithoutScreenShot("Accept All button has been clicked", "Pass");
		}
	}

	public void optionWishList() {
		scrollWindowTop();
		optionWishlist.click();

	}

	public void amountsFromWishListAndAddLowestToCart() {
		List<WebElement> lstItems = selectedItems();
		searchForLowestItem(lstItems);
	}

	public List<WebElement> selectedItems() {
		List<WebElement> lstItems = getDriver().findElements(By.xpath("//tbody//tr/td[4]"));
		lstPriceOfItems = new ArrayList<String>();
		return lstItems;
	}

	public void searchForLowestItem(List<WebElement> lstItems) {
		String price;
		Integer dblPrice;
		String productName;
		HashMap<String, Integer> productAndPrice = new HashMap<String, Integer>();
		for (int i = 1; i <= lstItems.size(); i++) {
			if (getDriver().findElement(By.xpath("//tbody//tr[" + i + "]/td[4]//ins//bdi")).isDisplayed()) {
				price = getDriver().findElement(By.xpath("//tbody//tr[" + i + "]/td[4]//ins//bdi")).getText();
				dblPrice = (int) Double.parseDouble((price.replaceAll("£", "")));
				productName = getDriver().findElement(By.xpath("//tbody//tr[" + i + "]/td[3]//a")).getText().trim();

			} else {
				price = getDriver().findElement(By.xpath("//tbody//tr[" + i + "]/td[4]//bdi")).getText();
				dblPrice = (int) Double.parseDouble((price.replaceAll("£", "")));
				productName = getDriver().findElement(By.xpath("//tbody//tr[" + i + "]/td[3]//a")).getText().trim();
			}

			productAndPrice.put(productName, dblPrice);
		}

		System.out.println(lstPriceOfItems);
		Map<String, Integer> sortedMap = sortByValue.sortByValue(productAndPrice);
		Entry<String, Integer> entry = sortedMap.entrySet().iterator().next();
		String key = entry.getKey();
		Integer value = entry.getValue();
		System.out.println(key + "================" + value);
		getDriver().findElement(By.xpath("//a[contains(text(),'" + key + "')]/../../td[6]")).click();
		customEnsure.customLogWithoutScreenShot("Lowset price item has been selected successfully", "Pass");
		scrollWindowTop();
	}

	public void optionCartPage() {
		scrollWindowTop();
		cartPage.click();
		customEnsure.customLogWithoutScreenShot("Cart Option has been clicked successfully", "Pass");
	}

	public void scrollWindowDown() {
		JavascriptExecutor je = (JavascriptExecutor) getDriver();
		je.executeScript("window.scrollBy(0,280)", "");
	}

	public void scrollWindowTop() {
		JavascriptExecutor je = (JavascriptExecutor) getDriver();
		je.executeScript("scroll(0,0)");
	}

}
