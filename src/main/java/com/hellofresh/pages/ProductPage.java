package com.hellofresh.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

	WebDriver driver;
	
	@FindBy(xpath="//a[@title='Faded Short Sleeve T-shirts']/ancestor::li")
	@CacheLookup
	public WebElement tShirtslink ;

	@FindBy(xpath="//a[@title='Faded Short Sleeve T-shirts']/ancestor::li")
	@CacheLookup
	public WebElement fadedTshirtProduct ;
	
	@FindBy(xpath="*//*[@name='Submit']")
	@CacheLookup
	public WebElement addToCartBtn ;
	
	@FindBy(css="h2")
	@CacheLookup
	public WebElement productAddedToCartMessage ;
	
	@FindBy(css="h1[itemprop='name']")
	@CacheLookup
	public WebElement productNameLabel ;
	
	@FindBy(id="our_price_display")
	@CacheLookup
	public WebElement productPriceLabel ;
	
	@FindBy(id="quantity_wanted")
	@CacheLookup
	public WebElement productQuantityTxtbox ;
	
	@FindBy(xpath="//a[@class and @title='Proceed to checkout']")
	@CacheLookup
	public WebElement proceedToCheckout ;
	
	

	public ProductPage(WebDriver driver) {           
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	
	public void clickWomensTshirtsLink() {

		tShirtslink.click();

	}

	public void clickFadedTshirtsLink() {

		fadedTshirtProduct.click();

	}
	
	public void clickAddToCart() {

		addToCartBtn.click();

	}
	
	public void getProductAddedToCartText() {

		productAddedToCartMessage.getText();

	}
	
	public void clickProceedToCheckout() {

		proceedToCheckout.click();

	}
	
	public String getProductName() {

		return productNameLabel.getText();

	}
	
	public String getProductPrice() {

		return productPriceLabel.getText();

	}
	
	public String getProductSelectedQty() {

		return productQuantityTxtbox.getText();

	}


}
