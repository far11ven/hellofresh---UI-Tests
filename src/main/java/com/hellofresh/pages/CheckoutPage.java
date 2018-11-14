package com.hellofresh.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*
 * This class provides instance of CheckoutPage and provides instantiation for all the web elements and their actions
*/

public class CheckoutPage {

	WebDriver driver;

	@FindBy(css="td.cart_description > p.product-name")
	@CacheLookup
	public WebElement productNamelabel ;

	@FindBy(css="td.cart_total > span.price")
	@CacheLookup
	public WebElement productPriceLabel ;

	@FindBy(css="input.grey[name^='quantity_']")
	public WebElement productQuantityTxtbox ;
	
	@FindBy(css="td.cart_description > p.product-name")
	@CacheLookup
	public WebElement paymentPageProductNamelabel ;

	@FindBy(css="td.cart_total > span.price")
	@CacheLookup
	public WebElement paymentPageProductPriceLabel ;

	@FindBy(css="td.cart_quantity > span")
	@CacheLookup
	public WebElement paymentPageProductQuantityTxtbox ;

	@FindBy(css="a.standard-checkout[title='Proceed to checkout']")
	@CacheLookup
	public WebElement proceedToCheckout ;

	@FindBy(xpath="//a[@title='Proceed to checkout']")
	@CacheLookup
	public WebElement cartProceedToCheckout ;

	@FindBy(name="processAddress")
	public WebElement cartProcssAddressProceedToCheckout ;

	@FindBy(name="processCarrier")
	public WebElement cartProcessCarrierProceedToCheckout ;

	@FindBy(id="uniform-cgv")
	@CacheLookup
	public WebElement checkboxTnC ;

	@FindBy(className="bankwire")
	@CacheLookup
	public WebElement bankwirePaymentMode ;

	@FindBy(xpath="//*[@id='cart_navigation']/button")
	@CacheLookup
	public WebElement confirmOrderBtn ;

	@FindBy(css="h1")
	@CacheLookup
	public WebElement orderConfirmationHeader ;
	
	@FindBy(css="li.step_current")
	public WebElement currentStepTitle ;
	
	@FindBy(id="address_delivery")
	@CacheLookup
	public WebElement deliveryAddress ;
	
	@FindBy(id="address_invoice")
	@CacheLookup
	public WebElement billingAddress ;
	
	@FindBy(css="p.carrier_title")
	@CacheLookup
	public WebElement chooseShippingAddressLabel ;

	@FindBy(xpath="//li[@class='step_done step_done_last four']")
	@CacheLookup
	public WebElement shippingStepTitle ;

	@FindBy(xpath="//li[@id='step_end' and @class='step_current last']")
	@CacheLookup
	public WebElement paymentStepTitle ;

	@FindBy(xpath="//*[@class='cheque-indent']/strong")
	@CacheLookup
	public WebElement orderCompletionLabel ;

	public CheckoutPage(WebDriver driver) {           
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	public String getAddedProductName() {

		return productNamelabel.getText();
	}

	public String getAddedProductPrice() {

		return productPriceLabel.getText();
	}

	public String getAddedProductQty() {

		return productQuantityTxtbox.getAttribute("value");
	}
	
	public String getCurrentCheckoutStepTitle() {

		return currentStepTitle.getText();
	}

	public void clickProceedToCheckout() {

		proceedToCheckout.click();

	}


	public void clickCartProceedToCheckout() {

		cartProceedToCheckout.click();

	}

	public void clickProceedToCheckoutAfterAddressSelection() {

		cartProcssAddressProceedToCheckout.click();

	}

	public void clickProceedToCheckoutAfterCarrierSelection() {

		cartProcessCarrierProceedToCheckout.click();

	}

	public void clickTnC() {

		checkboxTnC.click();

	}

	public void clickBankwirePaymentMode() {

		bankwirePaymentMode.click();

	}

	public void clickConfirmOrder() {

		confirmOrderBtn.click();

	}

	public String getConfirmOrderHeaderText() {

		return orderConfirmationHeader.getText();

	}

	public void getShippingStepTitleText() {

		shippingStepTitle.click();

	}

	public void getPaymentStepTitleText() {

		paymentStepTitle.click();

	}

	public String getOrderCompletionLabelText() {

		return orderCompletionLabel.getText();

	}
	
	public String getPaymentPageProductName() {

		return paymentPageProductNamelabel.getText();
	}

	public String getPaymentPageProductPrice() {

		return paymentPageProductPriceLabel.getText();
	}

	public String getPaymentPageProductQty() {

		return paymentPageProductQuantityTxtbox.getText();
	}


}
