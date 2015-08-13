package cz.muni.proso.geography.fragment;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.fragment.Root;
import org.openqa.selenium.WebDriver;

public class NavigationMenu {
	
	@Drone
	private WebDriver browser;
	
	@Root
	private WebElement navMenuRoot;

	@FindBy(className = "modal-content")
	private Login loginFragment;
	
	@FindBy(css = "#wrap > div.navbar.navbar-inverse > div > a")
	private WebElement homeButton;
	
	@FindBy(css = "#nav-main > ul.nav.navbar-nav.pull-left > li:nth-child(1)")
	private WebElement worldButton;
	
	//TO DO - fill list of continents
	@FindBy(css = "#drop-continents")
	private WebElement continentButton;
	
	//TO DO - fill list of continents
	@FindBy(css = "#drop-states")
	private WebElement stateButton;
	
	@FindBy(css = "#nav-main > ul.nav.navbar-nav.pull-left > li:nth-child(4)")
	private WebElement mapOverviewButton;
	
	@FindBy(css = "#nav-main > ul.nav.navbar-nav.pull-right > li:nth-child(1) > a")
	private WebElement loginButton;
	
	@FindBy(css = "#nav-main > ul.nav.navbar-nav.pull-right > li.dropdown.open > a")
	private WebElement languageButton;
	
	//selector should be ul#IdOfContinentsMenu > li
	@FindBy(css = "ul.dropdown-menu > li")
	private List<WebElement> listOfContinents;
	
	//selector should be ul#IdOfStatesMenu > li
	@FindBy(css = "ul.dropdown-menu > li")
	private List<WebElement> listOfStates;
	
	//selector should be ul#IdOfLanguagesMenu > li
	@FindBy(css = "ul.dropdown-menu > li")
	private List<WebElement> listOfLanguages;

	public Login getLoginFragment(){
		return loginFragment;
	}
	
	public WebElement getHomeButton() {
		return homeButton;
	}

	public WebElement getWorld() {
		return worldButton;
	}

	public WebElement getContinentButton() {
		return continentButton;
	}
	
	public WebElement getStateButton() {
		return stateButton;
	}
	
	public WebElement getMapOverview() {
		return mapOverviewButton;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

	public WebElement getLanguageButton() {
		return languageButton;
	}
	
	public List<WebElement> getListOfContinents(){
		return listOfContinents;
	}
	
	public List<WebElement> getListOfStates(){
		return listOfStates;
	}
	
	public List<WebElement> getListOfLanguages(){
		return listOfLanguages;
	}
	
	public void clickHomeButton(){
		homeButton.click();
	}
	
	public void clickWorldButton(){
		worldButton.click();
	}
	
	public void clickContinentButton(){
		continentButton.click();
	}
	
	public void clickStateButton(){
		stateButton.click();
	}
	
	public void clickMapOverviewButton(){
		mapOverviewButton.click();
	}
	
	public void clickLoginButton(){
		loginButton.click();
	}
	
	public void clickLanguageButton(){
		languageButton.click();
	}
	
	//this method selects language by its abbreviation
	//languages:
	//english = "en", czech = "cs", spanish = "es"
	
	public void clickLanguage(String language){
		for(WebElement e: listOfLanguages){
			if(e.getAttribute("href").contains(language)){
				e.click();
				break;
			}
		}
	}
	
	//this method selects state by its abbreviation
	//states: 
	//Argentina = "ar", Australia = "au", Austria = "at", Brazil = "br", Canada = "ca"
	//Czech Republic = "cz", France = "fr", Germany = "de", China = "cn", India = "in"
	//Italy = "it", Mexico = "mx", Slovakia = "sk", Spain = "es", United Kingdom = "gb"
	//United States = "us"
	
	public void clickState(String state){
		for(WebElement e: listOfStates){
			if(e.getAttribute("href").contains(state)){
				e.click();
				break;
			}
		}
	}
	
	//this method selects continent by its abbreviation
	//continents:
	//Africa = "africa", Asia = "asia", Australia and Oceania = "oceania", Europe = "europe"
	//North America = "namerica", South America = "samerica"
	
	public void clickContinent(String continent){
		for(WebElement e: listOfContinents){
			if(e.getAttribute("href").contains(continent)){
				e.click();
				break;
			}
		}
	}

	
}
