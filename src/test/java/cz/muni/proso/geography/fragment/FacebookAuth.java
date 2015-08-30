package cz.muni.proso.geography.fragment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.WebDriver;

public class FacebookAuth {
	
	@Drone
	private WebDriver browser;
	
	@FindBy(id = "email")
	private GrapheneElement emailOrPhone;
	
	@FindBy(id = "pass")
	private WebElement password;
	
	@FindBy(id = "persist_box")
	private WebElement keepLoggedInCheckbox;
	
	@FindBy(name = "login")
	private WebElement loginButton;
	
	@FindBy(css = "#login_form > div.pam.login_error_box.uiBoxRed")
	private WebElement errorMsg;
	
	public void enterEmailOrPhone(String emailOrPhoneNo){
		Graphene.waitGui().until().element(emailOrPhone).is().present();
		emailOrPhone.clear();
		emailOrPhone.sendKeys(emailOrPhoneNo);
	}
	
	public void enterPassword(String pwd){
		Graphene.waitGui().until().element(password).is().present();
		password.clear();
		password.sendKeys(pwd);
	}
	
	public void clickKeepLoggedIn(){
		keepLoggedInCheckbox.click();
	}
	
	public void clickLoginButton(){
		Graphene.waitGui().until().element(loginButton).is().present();
		loginButton.click();
	}
	
	public boolean isPresent(){
		return emailOrPhone.isPresent();
	}
	
	//this method tries to log in or sign up a user who is not logged in their Facebook account
	public void loginOrSignUp(String emailAddressOrPhone, String pwd){
		enterEmailOrPhone(emailAddressOrPhone);
		enterPassword(pwd);
		clickLoginButton();
	}
}
