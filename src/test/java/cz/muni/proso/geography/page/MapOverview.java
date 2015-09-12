package cz.muni.proso.geography.page;

import java.util.List;
import java.util.NoSuchElementException;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import cz.muni.proso.geography.fragment.Feedback;
import cz.muni.proso.geography.fragment.Footer;
import cz.muni.proso.geography.fragment.NavigationMenu;
import cz.muni.proso.geography.fragment.PracticedContext;
import cz.muni.proso.geography.fragment.ProgressBarTooltip;

public class MapOverview {

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

	@FindBy(xpath = "//*[@role='tooltip']")
	private ProgressBarTooltip tooltip;

	@FindBy(xpath = "//*[@id='ng-view']/div/div[2]/ul/li")
	private PracticedContext world;

	@FindBy(xpath = "//*[@id='ng-view']/div/div[3]/ul/li")
	private List<PracticedContext> listOfContinents;

	@FindBy(xpath = "//*[@id='ng-view']/div/div[4]/ul/li")
	private List<PracticedContext> listOfStates;

	public ProgressBarTooltip getTooltip() {
		return tooltip;
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public NavigationMenu getNavMenu() {
		return navMenu;
	}

	public Footer getFooter() {
		return footer;
	}

	public PracticedContext getWorld() {
		return world;
	}

	public List<PracticedContext> getListOfContinents() {
		return listOfContinents;
	}

	public List<PracticedContext> getListOfStates() {
		return listOfStates;
	}

	public PracticedContext getContinent(String continentToGet) {
		for (PracticedContext continent : listOfContinents) {
			if (continent.getPlaceTitle().equals(continentToGet)) {
				return continent;
			}
		}
		throw new NoSuchElementException("Continent was not found.");
	}

	public PracticedContext getState(String stateToGet) {
		for (PracticedContext state : listOfStates) {
			if (state.getPlaceTitle().equals(stateToGet)) {
				return state;
			}
		}
		throw new NoSuchElementException("State was not found.");
	}

	public void openFeedback() {
		feedbackButton.click();
	}

	public boolean isTooltipDisplayed() {
		return tooltip.isDisplayed();
	}
}
