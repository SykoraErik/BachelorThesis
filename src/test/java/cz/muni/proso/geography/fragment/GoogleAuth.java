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

	/**
	 * Writes the specified email address into the email field. Clears the field
	 * first.
	 * 
	 * @param emailAddress
	 *            email address to be written
	 */
	@Override
	public void enterEmail(String emailAddress) {
		Graphene.waitModel().until().element(email).is().present();
		email.clear();
		email.sendKeys(emailAddress);
	}

	/**
	 * Clicks the next button. Used after entering email and before entering
	 * password.
	 */
	public void clickNext() {
		Graphene.waitAjax().until().element(emailNextButton).is().present();
		emailNextButton.click();
	}

	/**
	 * Writes the specified password into the password field. Clears the field
	 * first.
	 * 
	 * @param pwd
	 *            password to be written
	 */
	@Override
	public void enterPassword(String pwd) {
		Graphene.waitAjax().until().element(password).is().present();
		password.clear();
		password.sendKeys(pwd);
	}

	/**
	 * Clicks the submit login button. Used after filling in the email and
	 * password.
	 */
	@Override
	public void submitLogin() {
		Graphene.waitAjax().until().element(signInButton).is().present();
		signInButton.click();
	}

	/**
	 * Returns <code>true</code> if gaia login form is present. This login form
	 * requires user to fill in email, click next to fill in password and then
	 * submit the login.
	 * 
	 * @return <code>true</code> if gaia login form is present
	 *         <code>false</code> otherwise
	 */
	public boolean isGaiaFormPresent() {
		return gaiaForm.isPresent();
	}

	/**
	 * Returns <code>true</code> if accounts login form is present. This login
	 * form requires user to choose email used for login or sign up from a list.
	 * 
	 * @return <code>true</code> if accounts login form is present
	 *         <code>false</code> otherwise
	 */
	public boolean isAccountsFormPresent() {
		return accountsForm.isPresent();
	}

	/**
	 * Returns <code>WebElement</code> representing the specified email.
	 * 
	 * @param email
	 *            email to choose from the list
	 * @return <code>WebElement</code> representing the specified email
	 */
	private WebElement getAccount(String email) {
		WebElement userAccount = null;
		for (WebElement e : userAccounts) {
			if (e.getAttribute("value").equals(email)) {
				userAccount = e;
			}
		}
		return userAccount;
	}

	/**
	 * Handles login of gaia login form.
	 * 
	 * @param emailAddress
	 *            email to login with
	 * @param pwd
	 *            password to login with
	 */
	private void gaiaFormLogin(String emailAddress, String pwd) {
		enterEmail(emailAddress);
		clickNext();
		enterPassword(pwd);
		submitLogin();
	}

	/**
	 * Login or sign up a user with email address and password.
	 * 
	 * @param emailAddress the email or phone number to be used for login or sign up
	 * @param pwd the password to be used for login or sign up
	 */
	@Override
	public void login(String emailAddress, String pwd) {
		if (isAccountsFormPresent()) {
			getAccount(emailAddress).click();
			gaiaFormLogin(emailAddress, pwd);
		}

		if (isGaiaFormPresent()) {
			gaiaFormLogin(emailAddress, pwd);
		} else {
			throw new NoSuchElementException("No google form present");
		}
	}
}