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
import cz.muni.proso.geography.fragment.PracticedPlace;


public class MapOverview{
	
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

	@FindBy(xpath = "//*[@id='ng-view']/div/div[2]/ul/li")	
	private PracticedPlace world;
	
	@FindBy(xpath = "//*[@id='ng-view']/div/div[3]/ul/li")
	private List<PracticedPlace> listOfContinents;

	@FindBy(xpath = "//*[@id='ng-view']/div/div[4]/ul/li")
	private List<PracticedPlace> listOfStates;

	public void clickFeedbackButton() {
		feedbackButton.click();
	}
	
	public PracticedPlace getWorld(){
		return world;
	}
	
	public List<PracticedPlace> getListOfContinents(){
		return listOfContinents;
	}
	
	public List<PracticedPlace> getListOfStates(){
		return listOfStates;
	}
	
	public PracticedPlace getContinent(String continentToGet) {
		for(PracticedPlace continent: listOfContinents){
			if(continent.getPlaceTitle().equals(continentToGet)){
				return continent;
			}
		}
		throw new NoSuchElementException("Continent was not found.");
	}
	
	public PracticedPlace getState(String stateToGet) {
		for(PracticedPlace state: listOfStates){
			if(state.getPlaceTitle().equals(stateToGet)){
				return state;
			}
		}
		throw new NoSuchElementException("State was not found.");
	}
}
