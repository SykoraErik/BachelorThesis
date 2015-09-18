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

	public void clickEmailButton() {
		Graphene.waitGui().until().element(emailButton).is().visible();
		emailButton.click();
	}
	
	public void clickFacebookButton() {
		Graphene.waitGui().until().element(emailButton).is().visible();
		facebookButton.click();
	}
	
	public void clickGoogleButton() {
		Graphene.waitGui().until().element(emailButton).is().visible();
		googleButton.click();
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
		signUpPasswordAgain.clear();
		signUpPasswordAgain.sendKeys(password);
	}

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
	
	public void closeForm() {
		Graphene.waitGui().until().element(closeButton).is().visible();
		closeButton.click();
		Graphene.waitAjax().until().element(By.className("modal-content")).is().not().visible();
	}

	public void signUpWithEmail(String email, String username, String pwd,
			String pwdAgain) {
		inputEmail(email);
		inputUsername(username);
		inputPassword(pwd);
		inputPasswordAgain(pwdAgain);
		submitSignUp();
	}
}
