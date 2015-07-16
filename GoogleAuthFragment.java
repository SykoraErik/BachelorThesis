package org.arquillian.example;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.jboss.arquillian.graphene.fragment.Root;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.WebDriver;

public class GoogleAuthFragment {

	@Root
	private WebElement googleAuthRoot;
	
	@Drone
	private WebDriver browser;
	
	//Google sign up page fragment elements
	
	@FindBy(id = "Email")
	private WebElement email;
	
	@FindBy(id="Passwd")
	private WebElement password;
	
	@FindBy(id = "next")
	private WebElement emailNextButton;
	
	@FindBy(id = "signIn")
	private WebElement signInButton;

	//Google sign up/sign in methods
	
	public WebElement getGoogleAuthRoot() {
		return googleAuthRoot;
	}

	public WebElement getEmail() {
		return email;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getEmailNextButton() {
		return emailNextButton;
	}

	public WebElement getSignInButton() {
		return signInButton;
	}

	public void inputEmail(String emailAddress){
		email.sendKeys(emailAddress);
	}
	
	public void clickEmailNextButton(){
		emailNextButton.click();
	}
	
	public void inputPassword(String pwd){
		password.sendKeys(pwd);
	}
	
	public void clickSignInButton(){
		signInButton.click();
	}
	
	//this method tries to log in or sign up a user who does not have saved credentials
	//users that have saved their credentials are logged in instantly. This is accounted for in LoginFragment and SignUpFragment
	
	public HomePageObject login(String emailAddress, String pwd){
		inputEmail(emailAddress);
		clickEmailNextButton();
		inputPassword(pwd);
		clickSignInButton();
		return new HomePageObject(true);
	}
	
}
