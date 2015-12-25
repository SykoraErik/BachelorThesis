package cz.muni.proso.geography.fragment;

import static org.jboss.arquillian.graphene.Graphene.guardAjax;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login {

	@FindBy(className = "alert")
	private AlertMessage alertMsg;

	@FindBy(xpath = "./div[2]/a[1]")
	private WebElement loginGoogleButton;

	@FindBy(xpath = "./div[2]/a[2]")
	private WebElement loginFacebookButton;

	@FindBy(xpath = "./div[2]/form/div[1]/input")
	private GrapheneElement loginUsername;

	@FindBy(xpath = "./div[2]/form/div[2]/input")
	private WebElement loginPassword;

	@FindBy(xpath = "./div[2]/form/button")
	private WebElement loginSubmitButton;

	@FindBy(xpath = "./div[3]/a")
	private WebElement signUpButton;

	@FindBy(xpath = "./div[1]/button")
	private WebElement closeLoginButton;

	public AlertMessage getAlertMsg() {
		return alertMsg;
	}

	/**
	 * Writes the specified username into the username field. Clears the field
	 * first.
	 * 
	 * @param username
	 *            username to be written
	 */
	public void inputUsername(String username) {
		Graphene.waitAjax().until().element(loginUsername).is().present();
		loginUsername.clear();
		loginUsername.sendKeys(username);
	}

	/**
	 * Writes the specified password into the password field. Clears the field
	 * first.
	 * 
	 * @param password
	 *            password to be written
	 */
	public void inputPassword(String password) {
		Graphene.waitAjax().until().element(loginPassword).is().present();
		loginPassword.clear();
		loginPassword.sendKeys(password);
	}

	/**
	 * Clicks the submit login button. Used after filling in the email and
	 * password.
	 */
	public void loginSubmit() {
		Graphene.waitAjax().until().element(loginSubmitButton).is().present();
		guardAjax(loginSubmitButton).click();
	}

	/**
	 * Closes login form by clicking on the login form close button.
	 */
	public void closeLoginForm() {
		Graphene.waitAjax().until().element(closeLoginButton).is().present();
		closeLoginButton.click();
	}

	/**
	 * Clicks on the google login button. The google login is handled by
	 * {@link GoogleAuth} and {@link GoogleConfirmAuth} fragments.
	 */
	public void clickLoginGoogle() {
		Graphene.waitAjax().until().element(loginGoogleButton).is().present();
		loginGoogleButton.click();
	}

	/**
	 * Clicks on the facebook login button. The facebook login is handled by
	 * {@link FacebookAuth} and {@link FacebookConfirmAuth} fragments.
	 */
	public void clickLoginFacebook() {
		Graphene.waitAjax().until().element(loginFacebookButton).is().present();
		loginFacebookButton.click();
		Graphene.waitModel().until().element(loginFacebookButton).is().not()
				.visible();
	}

	/**
	 * Writes the username and password into their fields and confirms email
	 * login by clicking the login submit button.
	 * 
	 * @param username
	 * @param pwd
	 */
	public void loginWithEmail(String username, String pwd) {
		inputUsername(username);
		inputPassword(pwd);
		loginSubmit();
	}

	/**
	 * Clicks the sign up button. Sign up process is handled by {@link SignUp} fragment.
	 */
	public void clickSignUp() {
		Graphene.waitAjax().until().element(signUpButton).is().present();
		signUpButton.click();
	}

	/**
	 * Returns <code>true</code> if login form is present.
	 * 
	 * @return <code>true</code> if login form is present
	 *         <code>false</code> otherwise
	 */
	public boolean isLoginFormPresent() {
		return loginUsername.isPresent();
	}
}