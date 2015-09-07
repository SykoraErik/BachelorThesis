package cz.muni.proso.geography.fragment;

import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
	private WebElement passwordAgain;

	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div:nth-child(2) > form > button")
	private WebElement submitButton;

	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-header.text-center.ng-scope > button")
	private WebElement closeButton;

	public AlertMessage getAlertMsg() {
		return alertMsg;
	}

	public void inputEmail(String email) {
		signUpEmail.clear();
		signUpEmail.sendKeys(email);
	}

	public void inputUsername(String username) {
		signUpUsername.clear();
		signUpUsername.sendKeys(username);
	}

	public void inputPassword(String password) {
		signUpPassword.clear();
		signUpPassword.sendKeys(password);
	}

	public void inputPasswordAgain(String password) {
		passwordAgain.clear();
		passwordAgain.sendKeys(password);
	}

	public void submitSignUp() {
		Graphene.waitGui().until().element(submitButton).is().present();
		submitButton.click();
	}

	public void closeForm() {
		Graphene.waitGui().until().element(closeButton).is().present();
		closeButton.click();
	}

	public void clickEmailButton() {
		Graphene.waitGui().until().element(emailButton).is().present();
		emailButton.click();
	}

	public void signUpWithEmail(String email, String username, String pwd,
			String pwdAgain) {
		inputEmail(email);
		inputUsername(username);
		inputPassword(pwd);
		inputPasswordAgain(pwdAgain);
		submitSignUp();
		closeForm();
	}
}
