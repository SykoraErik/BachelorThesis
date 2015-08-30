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
import cz.muni.proso.geography.fragment.ViewMap;

public class WorldMap {

	@Drone
	private WebDriver browser;

	@FindBy(xpath = "/html/body")
	private ViewMap worldMap;
	
	@FindBy(id = "container")
	private MapControl mapControl;
	
    @FindBy(css = "#wrap > div.navbar.navbar-inverse")
    private NavigationMenu navMenu;
	
	@FindBy(css = "#wrap > div.navbar.navbar-inverse > div > button")
	private Login login;
	
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
	
	
	public ViewMap getWorldMap() {
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
	
	public void clickFeedbackButton() {
		feedbackButton.click();
	}
}
