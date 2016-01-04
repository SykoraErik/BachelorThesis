package cz.muni.proso.geography.fragment;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FacebookAuth implements SocialMediaLogin {

	@FindBy(id = "email")
	private GrapheneElement emailOrPhone;

	@FindBy(id = "pass")
	private WebElement password;

	@FindBy(name = "login")
	private WebElement loginButton;

	@FindBy(css = "#login_form > div.pam.login_error_box.uiBoxRed")
	private WebElement errorMsg;

	/**
	 * Writes an email or phone number into the email or phone field. Clears the
	 * field first.
	 * 
	 * @param emailOrPhoneNo
	 *            the email or phone number to be written
	 */
	@Override
	public void enterEmail(String emailOrPhoneNo) {
		Graphene.waitGui().until().element(emailOrPhone).is().present();
		emailOrPhone.clear();
		emailOrPhone.sendKeys(emailOrPhoneNo);
	}

	/**
	 * Writes a password into the password field. Clears the field first.
	 * 
	 * @param pwd
	 *            password to be written
	 */
	@Override
	public void enterPassword(String pwd) {
		Graphene.waitGui().until().element(password).is().present();
		password.clear();
		password.sendKeys(pwd);
	}

	/**
	 * Clicks on the login submit button.
	 */
	@Override
	public void submitLogin() {
		Graphene.waitGui().until().element(loginButton).is().present();
		loginButton.click();
	}

	/**
	 * Returns <code>true</code> if facebook login form element is present.
	 * 
	 * @return <code>true</code> if facebook login form element is present.
	 *         <code>false</code> otherwise.
	 */
	public boolean isPresent() {
		return emailOrPhone.isPresent();
	}

	/**
	 * Login or sign up a user with either email address or phone number and
	 * password.
	 * 
	 * @param emailAddressOrPhone
	 *            the email or phone number to be used for login or sign up
	 * @param pwd
	 *            password to be used for login or sign up
	 */
	@Override
	public void login(String emailAddressOrPhone, String pwd) {
		enterEmail(emailAddressOrPhone);
		enterPassword(pwd);
		submitLogin();
	}
}