package cz.muni.proso.geography.fragment;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.GrapheneElement;

public class GoogleAuth {
	
	@FindBy(id = "Email")
	private WebElement email;
	
	@FindBy(id="Passwd")
	private WebElement password;
	
	@FindBy(id = "next")
	private WebElement emailNextButton;
	
	@FindBy(id = "signIn")
	private WebElement signInButton;

	@FindBy(id = "account-chooser-add-account")
	private WebElement addEmail;
	
	@FindBy(id = "account-list")
	private GrapheneElement accountsForm;
	
	@FindBy(xpath= "/html/body/div/div[2]/div[2]")
	private GrapheneElement gaiaForm;
	
	@FindBy(name = "Email")
	private List<WebElement> userAccounts;

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

	public GrapheneElement getAccountsForm(){
		return accountsForm;
	}
	
	public GrapheneElement getGaiaForm(){
		return gaiaForm;
	}
	
	public void inputEmail(String emailAddress){
		Graphene.waitModel().until().element(email).is().present();
		email.clear();
		email.sendKeys(emailAddress);
	}
	
	public void clickEmailNextButton(){
		Graphene.waitAjax().until().element(emailNextButton).is().present();
		emailNextButton.click();
	}
	
	public void inputPassword(String pwd){
		Graphene.waitAjax().until().element(password).is().present();
		password.clear();
		password.sendKeys(pwd);
	}
	
	public void clickSignInButton(){
		Graphene.waitAjax().until().element(signInButton).is().present();
		signInButton.click();
	}
	
	public boolean isGaiaFormPresent(){
		return gaiaForm.isPresent();
	}
	
	public boolean isAccountsFormPresent(){
		return accountsForm.isPresent();
	}
	
	public WebElement getAccount(String email){
		WebElement userAccount = null;
		for(WebElement e: userAccounts){
			if(e.getAttribute("value").equals(email)){
				userAccount = e;
			}
		}
		return userAccount;
	}
	
	public void login(String emailAddress, String pwd){
		if(isAccountsFormPresent()){
			getAccount(emailAddress).click();
			Graphene.waitAjax().until().element(email).is().visible();
			inputEmail(emailAddress);
			Graphene.waitAjax().until().element(emailNextButton).is().visible();
			clickEmailNextButton();
			Graphene.waitAjax().until().element(password).is().visible();
			inputPassword(pwd);
			Graphene.waitAjax().until().element(signInButton).is().visible();
			clickSignInButton();
		}
		if(isGaiaFormPresent()){
			inputEmail(emailAddress);
			Graphene.waitAjax().until().element(emailNextButton).is().visible();
			clickEmailNextButton();
			Graphene.waitAjax().until().element(password).is().visible();
			inputPassword(pwd);
			Graphene.waitAjax().until().element(signInButton).is().visible();
			clickSignInButton();
		}
		else{
			System.out.println("No google form was found.");
		}
	}
}
