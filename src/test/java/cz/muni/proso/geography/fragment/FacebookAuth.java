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

	@Override
	public void enterEmail(String emailOrPhoneNo) {
		Graphene.waitGui().until().element(emailOrPhone).is().present();
		emailOrPhone.clear();
		emailOrPhone.sendKeys(emailOrPhoneNo);
	}

	@Override
	public void enterPassword(String pwd) {
		Graphene.waitGui().until().element(password).is().present();
		password.clear();
		password.sendKeys(pwd);
	}

	@Override
	public void submitLogin() {
		Graphene.waitGui().until().element(loginButton).is().present();
		loginButton.click();
	}

	public boolean isPresent() {
		return emailOrPhone.isPresent();
	}

	@Override
	public void login(String emailAddressOrPhone, String pwd) {
		enterEmail(emailAddressOrPhone);
		enterPassword(pwd);
		submitLogin();
	}
}
