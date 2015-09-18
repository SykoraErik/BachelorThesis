package cz.muni.proso.geography.fragment;

import static org.jboss.arquillian.graphene.Graphene.guardAjax;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login {

	@FindBy(className = "alert")
	private AlertMessage alertMsg;

	@FindBy(xpath = "/html/body/div[6]/div/div/div[2]/a[1]")
	private WebElement loginGoogleButton;

	@FindBy(xpath = "/html/body/div[6]/div/div/div[2]/a[2]")
	private WebElement loginFacebookButton;

	@FindBy(xpath = "/html/body/div[6]/div/div/div[2]/form/div[1]/input")
	private GrapheneElement loginUsername;

	@FindBy(xpath = "/html/body/div[6]/div/div/div[2]/form/div[2]/input")
	private WebElement loginPassword;

	@FindBy(xpath = "/html/body/div[6]/div/div/div[2]/form/button")
	private WebElement loginSubmitButton;

	@FindBy(xpath = "/html/body/div[6]/div/div/div[3]/a")
	private WebElement signUpButton;

	@FindBy(xpath = "/html/body/div[6]/div/div/div[1]/button")
	private WebElement closeLoginButton;

	public AlertMessage getAlertMsg() {
		return alertMsg;
	}

	public void inputUsername(String username) {
		Graphene.waitAjax().until().element(loginUsername).is().present();
		loginUsername.clear();
		loginUsername.sendKeys(username);
	}

	public void inputPassword(String password) {
		Graphene.waitAjax().until().element(loginPassword).is().present();
		loginPassword.clear();
		loginPassword.sendKeys(password);
	}

	public void loginSubmit() {
		Graphene.waitAjax().until().element(loginSubmitButton).is().present();
		guardAjax(loginSubmitButton).click();
	}

	public void closeLoginForm() {
		Graphene.waitAjax().until().element(closeLoginButton).is().present();
		closeLoginButton.click();
	}

	public void clickLoginGoogle() {
		Graphene.waitAjax().until().element(loginGoogleButton).is().present();
		loginGoogleButton.click();
	}

	public void clickLoginFacebook() {
		Graphene.waitAjax().until().element(loginFacebookButton).is().present();
		loginFacebookButton.click();
		Graphene.waitModel().until().element(loginFacebookButton).is().not()
				.visible();
	}

	public void loginWithEmail(String username, String pwd) {
		inputUsername(username);
		inputPassword(pwd);
		loginSubmit();
	}

	public void clickSignUp() {
		Graphene.waitAjax().until().element(signUpButton).is().present();
		signUpButton.click();
	}

	public boolean isLoginFormPresent() {
		return loginUsername.isPresent();
	}
}