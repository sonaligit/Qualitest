package com.steps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;


import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import com.pageObject.WishListPage;
import com.reusable.reusable;

public class ProductCartSteps extends PageObject {
	WishListPage objWishlistPage;
	reusable objResuable = new reusable();
	public final static String Framework_specifications_property_path = "testproperties/";
	public final static String Application_url_property_file = "testSettings.properties";


	@Step
	public void getUrl() {
		String appurl = objResuable.readProperty(Application_url_property_file, "url");
		getDriver().get(appurl);
	}

	@Step
	public void addItemsToWishlist() {

		objWishlistPage.btnAcceptAll();
		List<WebElementFacade> lstPrdcts = findAll("//span[@class='price']//ins");
		System.out.println("Number of elements:" + lstPrdcts.size());
		for (int i = 0; i <= 2; i++) {
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			objWishlistPage.addItemtoWishlist();

		}

	}

	@Step
	public void navigateToWishList() {
		objWishlistPage.optionWishList();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Step
	public void addItemsToCart() {
		objWishlistPage.selectedItems();

	}

	@Step
	public void selectLowestPriceFromCart() {
		objWishlistPage.amountsFromWishListAndAddLowestToCart();

	}

	@Step
	public void navigateToCartPage() {
		objWishlistPage.optionCartPage();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void closeBrowser() {
		 getDriver().quit();

	}

}
