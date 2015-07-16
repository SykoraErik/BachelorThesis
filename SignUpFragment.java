package org.arquillian.example;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.jboss.arquillian.graphene.fragment.Root;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.WebDriver;

public class SignUpFragment {

	@Root
	private WebElement signUpRoot;
	
	@Drone
	private WebDriver browser;
	
	//Google sign up fragment 
	@FindBy(css = "body > div > div.main.content.clearfix")
	private GoogleAuthFragment signUpGoogleFragment;
	
	//Facebook sign up fragment
	@FindBy(id = "loginform")
	private FacebookAuthFragment signUpFacebookFragment;
	
	//sign up page fragment elements
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div:nth-child(2) > div > a.btn.btn-danger.btn-lg.btn-block.ng-binding")
	private WebElement signUpGoogleButton;

	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div:nth-child(2) > div > a.btn.btn-primary.btn-lg.btn-block.ng-binding")
	private WebElement signUpFacebookButton;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div:nth-child(2) > div > a.btn.btn-info.btn-lg.btn-block.ng-binding")
	private WebElement signUpEmailButton;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div:nth-child(2) > form > div:nth-child(1) > input")
	private WebElement signUpEmail;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div:nth-child(2) > form > div:nth-child(2) > input")
	private WebElement signUpUsername;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div:nth-child(2) > form > div:nth-child(3) > input")
	private WebElement signUpPassword;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div:nth-child(2) > form > div:nth-child(4) > input")
	private WebElement signUpPasswordAgain;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div:nth-child(2) > form > button")
	private WebElement submitButton;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-header.text-center.ng-scope > button")
	private WebElement closeSignUpButton;
	
	//sign up page fragment methods	
	
	public WebElement getSignUpRoot() {
		return signUpRoot;
	}

	public GoogleAuthFragment getSignUpGoogleFragment() {
		return signUpGoogleFragment;
	}

	public FacebookAuthFragment getSignUpFacebookFragment() {
		return signUpFacebookFragment;
	}

	public WebElement getSignUpGoogleButton() {
		return signUpGoogleButton;
	}

	public WebElement getSignUpFacebookButton() {
		return signUpFacebookButton;
	}

	public WebElement getSignUpEmailButton() {
		return signUpEmailButton;
	}

	public WebElement getSignUpEmail() {
		return signUpEmail;
	}

	public WebElement getSignUpUsername() {
		return signUpUsername;
	}

	public WebElement getSignUpPassword() {
		return signUpPassword;
	}

	public WebElement getSignUpPasswordAgain() {
		return signUpPasswordAgain;
	}

	public WebElement getSubmitButton() {
		return submitButton;
	}

	public WebElement getCloseSignUpButton() {
		return closeSignUpButton;
	}
	
	public void inputEmail(String email){
		signUpEmail.sendKeys(email);
	}
	
	public void inputUsername(String username){
		signUpUsername.sendKeys(username);
	}
	
	public void inputPassword(String password){
		signUpPassword.sendKeys(password);
	}
	
	public void inputPasswordAgain(String password){
		signUpPasswordAgain.sendKeys(password);
	}
	
	public void clickSubmitButton(){
		submitButton.click();
	}
	
	public void clickCloseSignUpButton(){
		closeSignUpButton.click();
	}
	
	// TO DO - should simulate clicking outside the login form
	public void closeLoginWithClick(){
		signUpRoot.click(); // clicks inside login form, should do the opposite
	}
	
	// this method tries to log in user with saved Google credentials
	public HomePageObject signUpWithGoogle(){
		signUpGoogleButton.click();
		return new HomePageObject(true);
	}
	
	//tries to log in a user without saved Google credentials
	public HomePageObject signUpWithGoogle(String emailAddress, String pwd){
		signUpGoogleButton.click();
		return signUpGoogleFragment.login(emailAddress, pwd);
	}
	
	// this method tries to log in user with saved Facebook credentials
	public HomePageObject signUpWithFacebook(){
		signUpFacebookButton.click();
		return new HomePageObject(true);
	}
	
	//tries to log in a user without saved Google credentials
	public HomePageObject signUpWithFacebook(String emailAddressOrPhone, String pwd){
		signUpFacebookButton.click();
		return signUpFacebookFragment.login(emailAddressOrPhone, pwd);
	}
	
	public void signUpWithEmail(String email, String username, String pwd, String pwdAgain){
		inputEmail(email);
		inputUsername(username);
		inputPassword(pwd);
		inputPasswordAgain(pwdAgain);
		signUpEmailButton.click();
	}
}
