package cz.muni.proso.geography.fragment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUp {
	
	@FindBy(id = "gaia_loginform")
	private GoogleAuth google;
	
	@FindBy(id = "loginform")
	private FacebookAuth facebook;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div:nth-child(2) > div > a.btn.btn-danger.btn-lg.btn-block.ng-binding")
	private WebElement googleButton;

	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div:nth-child(2) > div > a.btn.btn-primary.btn-lg.btn-block.ng-binding")
	private WebElement facebookButton;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div:nth-child(2) > div > a.btn.btn-info.btn-lg.btn-block.ng-binding")
	private WebElement emailButton;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div:nth-child(2) > form > div:nth-child(1) > input")
	private WebElement signUpEmail;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div:nth-child(2) > form > div:nth-child(2) > input")
	private WebElement signUpUsername;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div:nth-child(2) > form > div:nth-child(3) > input")
	private WebElement signUpPassword;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div:nth-child(2) > form > div:nth-child(4) > input")
	private WebElement passwordAgain;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div:nth-child(2) > form > button")
	private WebElement submitButton;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-header.text-center.ng-scope > button")
	private WebElement closeButton;
	
	//sign up page fragment methods	
	
	public GoogleAuth getGoogleFragment() {
		return google;
	}

	public FacebookAuth getFacebookFragment() {
		return facebook;
	}

	public WebElement getGoogleButton() {
		return googleButton;
	}

	public WebElement getFacebookButton() {
		return facebookButton;
	}

	public WebElement getEmailButton() {
		return emailButton;
	}

	public WebElement getEmail() {
		return signUpEmail;
	}

	public WebElement getUsername() {
		return signUpUsername;
	}

	public WebElement getPassword() {
		return signUpPassword;
	}

	public WebElement getPasswordAgain() {
		return passwordAgain;
	}

	public WebElement getSubmitButton() {
		return submitButton;
	}

	public WebElement getCloseButton() {
		return closeButton;
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
		passwordAgain.sendKeys(password);
	}
	
	public void clickSubmitButton(){
		submitButton.click();
	}
	
	public void clickCloseButton(){
		closeButton.click();
	}
	
	//tries to log in a user without saved Google credentials
	public void signUpWithGoogle(String emailAddress, String pwd){
		google.login(emailAddress, pwd);
	}
	
	//tries to log in a user without saved Google credentials
	public void signUpWithFacebook(String emailAddressOrPhone, String pwd){
		facebook.loginOrSignUp(emailAddressOrPhone, pwd);
	}
	
	public void signUpWithEmail(String email, String username, String pwd, String pwdAgain){
		inputEmail(email);
		inputUsername(username);
		inputPassword(pwd);
		inputPasswordAgain(pwdAgain);
		submitButton.click();
		clickCloseButton();
	}
}
