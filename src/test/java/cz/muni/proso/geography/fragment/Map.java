package cz.muni.proso.geography.fragment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.fragment.Root;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.WebDriver;

public class Map {

	@Root
	private WebElement mapRoot;
	
	@Drone
	private WebDriver browser;

	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div")
	private Feedback feedback;
	
	@FindBy(id = "feedback-btn")
	private WebElement feedbackButton;
	
	@FindBy(id = "nav-main")
	private NavigationMenu navMenu;
	
	@FindBy(id = "footer")
	private Footer footer;
	
	@FindBy(css = "#ng-view > div.map-container.kartograph > div.btn-group-vertical.map-switch > a:nth-child(1)")
	private WebElement myKnowledgeButton;
	
	@FindBy(css = "#ng-view > div.map-container.kartograph > div.btn-group-vertical.map-switch > a.btn.btn-default.active")
	private WebElement avgKnowledgeButton;
	
	@FindBy(id = "zoom-in")
	private WebElement zoomInButton;
	
	@FindBy(id = "zoom-out")
	private WebElement zoomOutButton;
	
	public Feedback getFeedback() {
		return feedback;
	}

	public WebElement getFeedbackButton() {
		return feedbackButton;
	}

	public NavigationMenu getNavMenu() {
		return navMenu;
	}

	public Footer getFooter() {
		return footer;
	}

	public WebElement getMyKnowledgeButton() {
		return myKnowledgeButton;
	}

	public WebElement getAvgKnowledgeButton() {
		return avgKnowledgeButton;
	}

	public WebElement getZoomInButton() {
		return zoomInButton;
	}

	public WebElement getZoomOutButton() {
		return zoomOutButton;
	}
	
}
