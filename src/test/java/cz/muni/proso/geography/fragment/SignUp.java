package cz.muni.proso.geography.fragment;

import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.google.common.base.Predicate;

public class SignUp {

	@FindBy(className = "alert")
	private AlertMessage alertMsg;

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
	private WebElement signUpPasswordAgain;

	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div:nth-child(2) > form > button")
	private WebElement submitButton;

	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-header.text-center.ng-scope > button")
	private WebElement closeButton;

	public AlertMessage getAlertMsg() {
		return alertMsg;
	}

	/**
	 * Clicks email button. Waits until the button is visible.
	 */
	public void clickEmailButton() {
		Graphene.waitGui().until().element(emailButton).is().visible();
		emailButton.click();
	}

	/**
	 * Clicks facebook sign up button. Waits until the button is visible.
	 */
	public void clickFacebookButton() {
		Graphene.waitGui().until().element(emailButton).is().visible();
		facebookButton.click();
	}

	/**
	 * Clicks google sign up button. Waits until the button is visible.
	 */
	public void clickGoogleButton() {
		Graphene.waitGui().until().element(emailButton).is().visible();
		googleButton.click();
	}

	/**
	 * Inputs the specified email into its corresponding text field. Clears the
	 * field first.
	 * 
	 * @param email
	 */
	public void inputEmail(String email) {
		signUpEmail.clear();
		signUpEmail.sendKeys(email);
	}

	/**
	 * Inputs the specified username into its corresponding text field. Clears
	 * the field first.
	 * 
	 * @param username
	 */
	public void inputUsername(String username) {
		signUpUsername.clear();
		signUpUsername.sendKeys(username);
	}

	/**
	 * Inputs the specified password into its corresponding text field. Clears
	 * the field first.
	 * 
	 * @param password
	 */
	public void inputPassword(String password) {
		signUpPassword.clear();
		signUpPassword.sendKeys(password);
	}

	/**
	 * Inputs the specified password into its corresponding text field. Clears
	 * the field first. This method is used to provide a different password than
	 * for the first time. This allows testing of handling of incorrect
	 * password.
	 * 
	 * @param password
	 */
	public void inputPasswordAgain(String password) {
		signUpPasswordAgain.clear();
		signUpPasswordAgain.sendKeys(password);
	}

	/**
	 * Waits until the sign up button is visible and clicks it.
	 */
	public void submitSignUp() {
		Graphene.waitGui().until().element(submitButton).is().visible();
		submitButton.click();
		Graphene.waitModel().until(new Predicate<WebDriver>() {
			@Override
			public boolean apply(WebDriver browser) {
				return browser.findElement(By.id("drop1")).isDisplayed();
			}
		});
	}

	/**
	 * Closes the form by clicking on the close form button. Waits until the
	 * form is not visible.
	 */
	public void closeForm() {
		Graphene.waitGui().until().element(closeButton).is().visible();
		closeButton.click();
		Graphene.waitAjax().until().element(By.className("modal-content")).is()
				.not().visible();
	}

	/**
	 * Performs a complete sign up process with the attributes provided.
	 * 
	 * @param email
	 * @param username
	 * @param pwd
	 * @param pwdAgain
	 */
	public void signUpWithEmail(String email, String username, String pwd,
			String pwdAgain) {
		inputEmail(email);
		inputUsername(username);
		inputPassword(pwd);
		inputPasswordAgain(pwdAgain);
		submitSignUp();
	}
}