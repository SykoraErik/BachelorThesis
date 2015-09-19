package cz.muni.proso.geography.fragment;

import java.util.List;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleAuth implements SocialMediaLogin {

	@FindBy(id = "Email")
	private WebElement email;

	@FindBy(id = "Passwd")
	private WebElement password;

	@FindBy(id = "next")
	private WebElement emailNextButton;

	@FindBy(id = "signIn")
	private WebElement signInButton;

	@FindBy(id = "account-chooser-add-account")
	private WebElement addEmail;

	@FindBy(id = "account-list")
	private GrapheneElement accountsForm;

	@FindBy(xpath = "/html/body/div/div[2]/div[2]")
	private GrapheneElement gaiaForm;

	@FindBy(name = "Email")
	private List<WebElement> userAccounts;

	@Override
	public void enterEmail(String emailAddress) {
		Graphene.waitModel().until().element(email).is().present();
		email.clear();
		email.sendKeys(emailAddress);
	}

	public void clickNext() {
		Graphene.waitAjax().until().element(emailNextButton).is().present();
		emailNextButton.click();
	}

	@Override
	public void enterPassword(String pwd) {
		Graphene.waitAjax().until().element(password).is().present();
		password.clear();
		password.sendKeys(pwd);
	}

	@Override
	public void submitLogin() {
		Graphene.waitAjax().until().element(signInButton).is().present();
		signInButton.click();
	}

	public boolean isGaiaFormPresent() {
		return gaiaForm.isPresent();
	}

	public boolean isAccountsFormPresent() {
		return accountsForm.isPresent();
	}

	public WebElement getAccount(String email) {
		WebElement userAccount = null;
		for (WebElement e : userAccounts) {
			if (e.getAttribute("value").equals(email)) {
				userAccount = e;
			}
		}
		return userAccount;
	}

	@Override
	public void login(String emailAddress, String pwd) {
		if (isAccountsFormPresent()) {
			getAccount(emailAddress).click();
			Graphene.waitAjax().until().element(email).is().visible();
			enterEmail(emailAddress);
			Graphene.waitAjax().until().element(emailNextButton).is().visible();
			clickNext();
			Graphene.waitAjax().until().element(password).is().visible();
			enterPassword(pwd);
			Graphene.waitAjax().until().element(signInButton).is().visible();
			submitLogin();
		}

		if (isGaiaFormPresent()) {
			enterEmail(emailAddress);
			Graphene.waitAjax().until().element(emailNextButton).is().visible();
			clickNext();
			Graphene.waitAjax().until().element(password).is().visible();
			enterPassword(pwd);
			Graphene.waitAjax().until().element(signInButton).is().visible();
			submitLogin();
		} else {
			throw new NoSuchElementException("No google form present");
		}
	}
}