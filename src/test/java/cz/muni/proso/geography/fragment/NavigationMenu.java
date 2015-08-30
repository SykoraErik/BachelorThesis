package cz.muni.proso.geography.fragment;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.GrapheneElement;

public class NavigationMenu {

	@FindBy(css = "#wrap > div.navbar.navbar-inverse > div > button")
	private Login login;
	
	@FindBy(css = "#wrap > div.navbar.navbar-inverse > div > a")
	private WebElement homeButton;
	
	@FindBy(css = "#nav-main > ul.nav.navbar-nav.pull-left > li:nth-child(1)")
	private WebElement worldButton;

	@FindBy(css = "#drop-continents")
	private WebElement continentButton;

	@FindBy(css = "#drop-states")
	private WebElement stateButton;
	
	@FindBy(css = "#nav-main > ul.nav.navbar-nav.pull-left > li:nth-child(4)")
	private WebElement mapOverviewButton;
	
	@FindBy(css = "#nav-main > ul.nav.navbar-nav.pull-right > li:nth-child(1) > a")
	private WebElement signInButton;
	
	@FindBy(xpath = "//*[@id='nav-main']/ul[2]/li[2]")
	private GrapheneElement loggedInButton;
	
	@FindBy(xpath = "//*[@id='nav-main']/ul[2]/li[2]/ul/li[1]/a")
	private WebElement myProfileButton;
	
	@FindBy(xpath = "//*[@id='nav-main']/ul[2]/li[2]/ul/li[2]/a")
	private WebElement signOutButton;
	
	@FindBy(css = "#nav-main > ul.nav.navbar-nav.pull-right > li:nth-child(3) > a")
	private WebElement languageButton;
	
	@FindBy(css = "ul#menu-list-contents > li > a")
	private List<WebElement> listOfContinents;
	
	@FindBy(css = "ul#menu-list-states > li > a")
	private List<WebElement> listOfStates;
	
	@FindBy(css = "ul#menu-list-languages > li > a")
	private List<WebElement> listOfLanguages;

	public Login getLogin(){
		return login;
	}
	
	public GrapheneElement getLoggedInButton(){
		return loggedInButton;
	}
	
	public WebElement getHomeButton() {
		return homeButton;
	}

	public WebElement getWorldButton() {
		return worldButton;
	}

	public WebElement getContinentButton() {
		return continentButton;
	}

	public WebElement getStateButton() {
		return stateButton;
	}

	public WebElement getMapOverviewButton() {
		return mapOverviewButton;
	}

	public WebElement getSignInButton() {
		return signInButton;
	}

	public WebElement getMyProfileButton() {
		return myProfileButton;
	}

	public WebElement getSignOutButton() {
		return signOutButton;
	}

	public WebElement getLanguageButton() {
		return languageButton;
	}

	public void clickHomeButton() {
		Graphene.waitGui().until().element(homeButton).is().present();
		homeButton.click();
	}

	public void clickWorldButton() {
		Graphene.waitGui().until().element(worldButton).is().present();
		worldButton.click();
	}

	public void clickContinentButton() {
		Graphene.waitGui().until().element(continentButton).is().present();
		continentButton.click();
	}
	
	public void clickStateButton() {
		Graphene.waitGui().until().element(stateButton).is().present();
		stateButton.click();
	}
	
	public void clickMapOverview() {
		Graphene.waitGui().until().element(mapOverviewButton).is().present();
		mapOverviewButton.click();
	}

	public void clickLoginButton(){
		Graphene.waitGui().until().element(signInButton).is().present();
		signInButton.click();
	}
	
	public void clickLanguageButton(){
		Graphene.waitGui().until().element(languageButton).is().present();
		languageButton.click();
	}
	
	public void clickLoggedInButton(){
		Graphene.waitGui().until().element(languageButton).is().present();
		loggedInButton.click();
	}
	
	public void clickSignOutButton(){
		Graphene.waitGui().until().element(languageButton).is().present();
		signOutButton.click();
	}
	
	public void clickMyProfileButton(){
		Graphene.waitGui().until().element(languageButton).is().present();
		myProfileButton.click();
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
	
	//this method selects language by its button text	
	public void clickSpecificLanguage(String language){
		for(WebElement e: listOfLanguages){
			if(e.getText().equalsIgnoreCase(language)){
				e.click();
				break;
			}
		}
	}
	
	//this method selects state by its button text	
	public void clickSpecificState(String state){
		for(WebElement e: listOfStates){
			if(e.getText().equalsIgnoreCase(state)){
				e.click();
				break;
			}
		}
	}
	
	//this method selects continents by its button text
	public void clickSpecificContinent(String continent){
		for(WebElement e: listOfContinents){
			if(e.getText().equalsIgnoreCase(continent)){
				e.click();
				break;
			}
		}
	}
}
