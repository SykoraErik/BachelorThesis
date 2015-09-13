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
import cz.muni.proso.geography.fragment.MapControl;
import cz.muni.proso.geography.fragment.NavigationMenu;
import cz.muni.proso.geography.fragment.OverviewMap;
import cz.muni.proso.geography.fragment.ProgressBarTooltip;
import cz.muni.proso.geography.fragment.SignUp;

public class WorldMap {

	@Drone
	private WebDriver browser;

	@FindBy(xpath = "/html/body")
	private OverviewMap worldMap;

	@FindBy(id = "container")
	private MapControl mapControl;

	@FindBy(css = "#wrap > div.navbar.navbar-inverse")
	private NavigationMenu navMenu;

	@FindBy(css = "#wrap > div.navbar.navbar-inverse > div > button")
	private Login login;

	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div")
	private SignUp signUp;

	@FindBy(id = "platformDialogForm")
	private FacebookConfirmAuth facebookConfirm;

	@FindBy(id = "login_form")
	private FacebookAuth facebook;

	@FindBy(css = "body > div > div.main.content.clearfix")
	private GoogleAuth google;

	@FindBy(id = "approval_container")
	private GoogleConfirmAuth googleConfirm;

	@FindBy(className = "modal-content")
	private Feedback feedback;

	@FindBy(id = "feedback-btn")
	private WebElement feedbackButton;

	@FindBy(id = "footer")
	private Footer footer;

	@FindBy(xpath = "//*[@role='tooltip']")
	private ProgressBarTooltip progBarTooltip;

	public OverviewMap getWorldMap() {
		return worldMap;
	}

	public MapControl getMapControl() {
		return mapControl;
	}

	public NavigationMenu getNavMenu() {
		return navMenu;
	}

	public Login getLogin() {
		return login;
	}

	public SignUp getSignUp() {
		return signUp;
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

	public Feedback getFeedback() {
		return feedback;
	}

	public WebElement getFeedbackButton() {
		return feedbackButton;
	}

	public Footer getFooter() {
		return footer;
	}

	public ProgressBarTooltip getProgBarTooltip() {
		return progBarTooltip;
	}

	public void openFeedback() {
		feedbackButton.click();
	}
}
