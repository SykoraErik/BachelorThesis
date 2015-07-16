package org.arquillian.example;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.jboss.arquillian.graphene.fragment.Root;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.WebDriver;

public class LoginFragment {
	
	@Root
	private WebElement loginScreenRoot;
	
	@Drone
	private WebDriver browser;
	
	//Google sign up fragment 
	@FindBy(css = "body > div > div.main.content.clearfix")
	private GoogleAuthFragment googleFragment;
	
	//Facebook sign up fragment
	@FindBy(id = "loginform")
	private FacebookAuthFragment facebookFragment;
	
	//login page fragment elements
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-body.ng-scope > a.btn.btn-danger.btn-lg.btn-block.ng-binding")
	private WebElement loginGoogleButton;

	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-body.ng-scope > a.btn.btn-primary.btn-lg.btn-block.ng-binding")
	private WebElement loginFacebookButton;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-body.ng-scope > form > div:nth-child(1) > input")
	private WebElement loginUsername;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-body.ng-scope > form > div:nth-child(2) > input")
	private WebElement loginPassword;

	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-body.ng-scope > form > button")
	private WebElement loginSubmitButton;
			
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-footer.ng-scope > a")
	private WebElement signUpButton;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-body.ng-scope > form > div.alert.ng-isolate-scope.alert-danger.alert-dismissable > div > span")
	private WebElement loginFailMsg;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-header.text-center.ng-scope > button")
	private WebElement closeLoginButton;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-body.ng-scope > form > div.alert.ng-isolate-scope.alert-danger.alert-dismissable > button")
	private WebElement closeLoginFailMsgButton;
	
	//getter methods for WebElements
	
	public GoogleAuthFragment getGoogleFragment() {
		return googleFragment;
	}

	public FacebookAuthFragment getFacebookFragment() {
		return facebookFragment;
	}

	public WebElement getLoginGoogleButton() {
		return loginGoogleButton;
	}

	public WebElement getLoginFacebookButton() {
		return loginFacebookButton;
	}

	public WebElement getLoginUsername() {
		return loginUsername;
	}

	public WebElement getLoginPassword() {
		return loginPassword;
	}

	public WebElement getLoginSubmitButton() {
		return loginSubmitButton;
	}

	public WebElement getSignUpButton() {
		return signUpButton;
	}

	public WebElement getLoginFailMsg() {
		return loginFailMsg;
	}
	
	public WebElement getCloseLoginButton() {
		return closeLoginButton;
	}

	public WebElement getCloseLoginFailMsgButton() {
		return closeLoginFailMsgButton;
	}
	
	//login page fragment methods
	
	public void inputUsername(String username){
		loginUsername.sendKeys(username);
	}
	
	public void inputPassword(String password){
		loginPassword.sendKeys(password);
	}
	
	public void loginSubmit(){
		loginSubmitButton.click();
	}
	
	//TO DO - should close the login form and return previous page
	public void closeLoginWithButton(){
		closeLoginButton.click();
	}
	
	// TO DO - should simulate clicking outside the login form
	public void closeLoginFormWithClick(){
		loginScreenRoot.click(); // clicks inside login form, should do opposite
	}
	
	public String getErrorMsg(){
		return loginFailMsg.getText();
	}
	
	//simulates closing login form by clicking on the close button
	public void closeLoginFailMsg(){
		if(closeLoginFailMsgButton.isDisplayed()){
		closeLoginFailMsgButton.click();
		}
	}
	
	// this method tries to log in user with saved Google credentials
	public HomePageObject loginWithGoogle(){
		loginGoogleButton.click();
		return new HomePageObject(true);
	}
	
	// this method tries to log in user without saved Google credentials
	public HomePageObject loginWithGoogle(String email, String pwd){
		loginGoogleButton.click();
		return googleFragment.login(email, pwd);
	}
	
	// this method tries to log in user with saved Facebook credentials
	public HomePageObject loginWithFacebook(){
		loginFacebookButton.click();
		return new HomePageObject(true);
	}
	
	// this method tries to log in user without saved Facebook credentials
	public HomePageObject loginWithFacebook(String emailOrPhone, String pwd){
		loginFacebookButton.click();
		return facebookFragment.login(emailOrPhone, pwd);
	}
	
	public HomePageObject loginWithEmail(String username, String pwd){
		inputUsername(username);
		inputPassword(pwd);
		loginSubmitButton.click();
		return new HomePageObject(true);
	}
	
	public SignUpFragment signUp(){
		signUpButton.click();
		return new SignUpFragment();
	}
	
}
