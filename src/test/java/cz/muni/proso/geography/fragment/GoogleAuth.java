package cz.muni.proso.geography.fragment;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.fragment.Root;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.WebDriver;

public class GoogleAuth {

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

	@FindBy(css = "#account-chooser-add-account")
	private WebElement addEmail;
	
	@FindBy(id = "account-list")
	private WebElement accountsForm;
	
	@FindBy(name = "Email")
	private List<WebElement> userAccounts;
	
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

	public WebElement getAccountsForm(){
		return accountsForm;
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
	
	public WebElement getAccount(String email){
		WebElement userAccount = null;
		for(WebElement e: userAccounts){
			if(e.getAttribute("value")==email){
				userAccount = e;
			}
		}
		return userAccount;
	}
	
	public void login(String emailAddress, String pwd){
		try{
		if(accountsForm.isDisplayed()){
			getAccount(emailAddress).click();
		}
		}
		catch(NoSuchElementException ex){
		Graphene.waitGui().until().element(email).is().present();
		inputEmail(emailAddress);
		Graphene.waitGui().until().element(emailNextButton).is().present();
		clickEmailNextButton();
		Graphene.waitGui().until().element(password).is().present();
		inputPassword(pwd);
		Graphene.waitGui().until().element(signInButton).is().present();
		clickSignInButton();
		}
	}
	
}
