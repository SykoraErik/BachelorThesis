package cz.muni.proso.geography.page;

import org.jboss.arquillian.graphene.page.Location;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import cz.muni.proso.geography.fragment.Feedback;
import cz.muni.proso.geography.fragment.Footer;
import cz.muni.proso.geography.fragment.NavigationMenu;


@Location("http://slepemapy.cz/")
public class Home {
	
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
	
	public Feedback getFeedbackFragment(){
		return feedback;
	}
	
	public NavigationMenu getNavMenuFragment(){
		return navMenu;
	}
	
	public Footer getFooterFragment(){
		return footer;
	}
	
	public WebElement getFeedbackButton(){
		return feedbackButton;
	}
	
}
