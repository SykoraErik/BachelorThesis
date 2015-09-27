package cz.muni.proso.geography.page;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import cz.muni.proso.geography.fragment.FacebookAuth;
import cz.muni.proso.geography.fragment.FacebookConfirmAuth;
import cz.muni.proso.geography.fragment.Feedback;
import cz.muni.proso.geography.fragment.Footer;
import cz.muni.proso.geography.fragment.GoogleAuth;
import cz.muni.proso.geography.fragment.GoogleConfirmAuth;
import cz.muni.proso.geography.fragment.Login;
import cz.muni.proso.geography.fragment.NavigationMenu;
import cz.muni.proso.geography.fragment.SignUp;

public abstract class CommonPageFragments {
	
	@Drone
	private WebDriver browser;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div")
	private Feedback feedback;

	@FindBy(id = "feedback-btn")
	private WebElement feedbackButton;

	@FindBy(css = "#wrap > div.navbar.navbar-inverse")
	private NavigationMenu navMenu;

	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div")
	private Login login;

	@FindBy(id = "platformDialogForm")
	private FacebookConfirmAuth facebookConfirm;

	@FindBy(id = "login_form")
	private FacebookAuth facebook;

	@FindBy(css = "body > div > div.main.content.clearfix")
	private GoogleAuth google;

	@FindBy(id = "approval_container")
	private GoogleConfirmAuth googleConfirm;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div")
	private SignUp signUp;
	
	@FindBy(id = "footer")
	private Footer footer;

	public Feedback getFeedback() {
		return feedback;
	}

	public NavigationMenu getNavMenu() {
		return navMenu;
	}

	public Login getLogin() {
		return login;
	}

	public FacebookConfirmAuth getFacebookConfirm() {
		return facebookConfirm;
	}

	public FacebookAuth getFacebook() {
		return facebook;
	}

	public GoogleAuth getGoogle() {
		return google;
	}

	public GoogleConfirmAuth getGoogleConfirm() {
		return googleConfirm;
	}

	public SignUp getSignUp() {
		return signUp;
	}

	public Footer getFooter() {
		return footer;
	}
	
	public void openFeedback() {
		feedbackButton.click();
	}
}
