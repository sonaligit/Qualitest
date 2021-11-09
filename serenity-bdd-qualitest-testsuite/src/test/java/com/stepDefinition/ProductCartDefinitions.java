package com.stepDefinition;

import java.util.List;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.steps.ProductCartSteps;

public class ProductCartDefinitions {

ProductCartSteps objProductCartSteps;
	
	@Given("I add four different products to my wish list")
	public void I_add_four_different_products_to_my_wish_list() {
		objProductCartSteps.getUrl();
		objProductCartSteps.addItemsToWishlist();
		

	}

	@When("I view my wishlist table")
	public void I_view_my_wishlist_table() {
		objProductCartSteps.navigateToWishList();
				
	}

	@Then("I find total four selected items in my wishlist")
	public void I_find_total_four_selected_items_in_my_wishlist() {
	
		objProductCartSteps.addItemsToCart();
	}

	@When("I search for lowest price product")
	public void I_search_for_lowest_price_product() throws InterruptedException {
		objProductCartSteps.selectLowestPriceFromCart();
	}

	@And("I am able to add the lowest price item to my cart")
	public void I_am_able_to_add_the_lowest_price_item_to_my_cart() {
		objProductCartSteps.navigateToCartPage();
	}

	@Then("I am able to verify the item in my cart")
	public void I_am_able_to_verify_the_item_in_my_cart() {
		objProductCartSteps.closeBrowser();
	   
	}
}
