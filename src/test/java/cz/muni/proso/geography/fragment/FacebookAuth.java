package cz.muni.proso.geography.fragment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.fragment.Root;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.WebDriver;

public class FacebookAuth {
	
	@Root
	private WebElement facebookAuthRoot;
	
	@Drone
	private WebDriver browser;
	
	//Facebook sign up/sign in page fragment elements
	
	@FindBy(css = "#email")
	private WebElement emailOrPhone;
	
	@FindBy(id = "pass")
	private WebElement password;
	
	@FindBy(id = "persist_box")
	private WebElement keepLoggedInCheckbox;
	
	@FindBy(name = "login")
	private WebElement loginButton;
	
	@FindBy(className = "pam login_error_box uiBoxRed")
	private WebElement errorMsg;
	
	@FindBy(id = "platformDialogForm")
	private WebElement confirmForm;
	
	@FindBy(css = "button._42ft:nth-child(2)")
	private WebElement confirmButton;
	
	@FindBy(css = "button._42ft:nth-child(1)")
	private WebElement cancelButton;
	
	//set methods for WebElements
	
	public WebElement getFacebookAuthRoot() {
		return facebookAuthRoot;
	}

	public WebElement getEmailOrPhone() {
		return emailOrPhone;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getKeepLoggedInCheckbox() {
		return keepLoggedInCheckbox;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}
	
	public WebElement getErrorMsg(){
		return errorMsg;
	}
	
	public WebElement getConfirmForm(){
		return confirmForm;
	}
	
	public WebElement getConfirmButton(){
		return confirmButton;
	}
	
	public WebElement getCancelButton(){
		return cancelButton;
	}
	
	public void enterEmailOrPhone(String emailOrPhoneNo){
		Graphene.waitGui().until().element(emailOrPhone).is().present();
		emailOrPhone.sendKeys(emailOrPhoneNo);
	}
	
	public void enterPassword(String pwd){
		password.sendKeys(pwd);
	}
	
	public void checkKeepLoggedIn(){
		keepLoggedInCheckbox.click();
	}
	
	public void clickLoginButton(){
		loginButton.click();
	}
	
	//this method tries to log in or sign up a user who does not have saved credentials
	//users that have saved their credentials are logged in instantly. This is accounted for in LoginFragment and SignUpFragment
	
	public void login(String emailAddressOrPhone, String pwd){
		emailOrPhone.click();
		enterEmailOrPhone(emailAddressOrPhone);
		password.click();
		enterPassword(pwd);
		clickLoginButton();
	}
}
