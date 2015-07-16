package org.arquillian.example;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.jboss.arquillian.graphene.fragment.Root;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.WebDriver;

public class FacebookAuthFragment {
	
	@Root
	private WebElement facebookAuthRoot;
	
	@Drone
	private WebDriver browser;
	
	//Facebook sign-up page fragment elements
	
	@FindBy(id = "email")
	private WebElement emailOrPhone;
	
	@FindBy(id = "pass")
	private WebElement password;
	
	@FindBy(id = "persist_box")
	private WebElement keepLoggedInCheckbox;
	
	@FindBy(name = "login")
	private WebElement loginButton;
	
	//set methods for WebElements
	
	public WebElement getEmailOrPhone(){
		return emailOrPhone;
	}
	
	public WebElement password(){
		return password;
	}
	
	public WebElement getKeepLoggedInCheckbox(){
		return keepLoggedInCheckbox;
	}
	
	public WebElement loginButton(){
		return loginButton;
	}
	
	public void enterEmailOrPhone(String emailOrPhoneNo){
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
	
	public HomePageObject login(String emailAddressOrPhone, String pwd){
		enterEmailOrPhone(emailAddressOrPhone);
		enterPassword(pwd);
		clickLoginButton();
		return new HomePageObject(true);
	}

}
