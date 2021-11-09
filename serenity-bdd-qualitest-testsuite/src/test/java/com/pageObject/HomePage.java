package com.pageObject;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class HomePage extends PageObject {
	
	@FindBy(xpath="//div[@class='yith-wcwl-add-button']/a/span[text()='Add to wishlist']")
	protected WebElementFacade btnAddtoWishList;
	
	@FindBy(xpath="//h2[text()='Black pants']/following::span[text()='Add to wishlist'][1]")
	protected WebElementFacade btnBlackPantsAddToWishList;
	
	@FindBy(xpath="//span[@class='price']//ins")
	protected List<WebElementFacade> listProducts;
	
	
}
