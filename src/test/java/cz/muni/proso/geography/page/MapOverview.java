package cz.muni.proso.geography.page;

import org.jboss.arquillian.graphene.page.Location;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import cz.muni.proso.geography.fragment.Feedback;
import cz.muni.proso.geography.fragment.Footer;
import cz.muni.proso.geography.fragment.NavigationMenu;
import cz.muni.proso.geography.fragment.PracticedPlace;


@Location("http://slepemapy.cz/overview/")
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

	@FindBy(xpath = "//*[@id='ng-view']/div/div[2]/ul/li")	
	private PracticedPlace world;
	
	@FindBy(xpath = "//*[@id='ng-view']/div/div[3]/ul/li[1]")	
	private PracticedPlace africa;
	
	@FindBy(xpath = "//*[@id='ng-view']/div/div[3]/ul/li[2]")	
	private PracticedPlace asia;
	
	@FindBy(xpath = "//*[@id='ng-view']/div/div[3]/ul/li[3]")	
	private PracticedPlace australiaOceania;
	
	@FindBy(xpath = "//*[@id='ng-view']/div/div[3]/ul/li[4]")	
	private PracticedPlace europe;
	
	@FindBy(xpath = "//*[@id='ng-view']/div/div[3]/ul/li[5]")	
	private PracticedPlace northAmerica;
	
	@FindBy(xpath = "//*[@id='ng-view']/div/div[3]/ul/li[6]")	
	private PracticedPlace southAmerica;

	@FindBy(xpath = "//*[@id='ng-view']/div/div[4]/ul/li[1]")	
	private PracticedPlace argentina;
	
	@FindBy(xpath = "//*[@id='ng-view']/div/div[4]/ul/li[2]")	
	private PracticedPlace australia;
	
	@FindBy(xpath = "//*[@id='ng-view']/div/div[4]/ul/li[3]")	
	private PracticedPlace austria;
	
	@FindBy(xpath = "//*[@id='ng-view']/div/div[4]/ul/li[4]")	
	private PracticedPlace brazil;
	
	@FindBy(xpath = "//*[@id='ng-view']/div/div[4]/ul/li[5]")	
	private PracticedPlace canada;
	
	@FindBy(xpath = "//*[@id='ng-view']/div/div[4]/ul/li[6]")	
	private PracticedPlace czech;
	
	@FindBy(xpath = "//*[@id='ng-view']/div/div[4]/ul/li[7]")	
	private PracticedPlace france;
	
	@FindBy(xpath = "//*[@id='ng-view']/div/div[4]/ul/li[8]")	
	private PracticedPlace germany;
	
	@FindBy(xpath = "//*[@id='ng-view']/div/div[4]/ul/li[9]")	
	private PracticedPlace china;
	
	@FindBy(xpath = "//*[@id='ng-view']/div/div[4]/ul/li[10]")	
	private PracticedPlace india;
	
	@FindBy(xpath = "//*[@id='ng-view']/div/div[4]/ul/li[11]")	
	private PracticedPlace italy;
	
	@FindBy(xpath = "//*[@id='ng-view']/div/div[4]/ul/li[12]")	
	private PracticedPlace mexico;
	
	@FindBy(xpath = "//*[@id='ng-view']/div/div[4]/ul/li[13]")	
	private PracticedPlace slovakia;
	
	@FindBy(xpath = "//*[@id='ng-view']/div/div[4]/ul/li[14]")	
	private PracticedPlace spain;
	
	@FindBy(xpath = "//*[@id='ng-view']/div/div[4]/ul/li[15]")	
	private PracticedPlace unitedKingdom;
	
	@FindBy(xpath = "//*[@id='ng-view']/div/div[4]/ul/li[16]")	
	private PracticedPlace unitedStates;
	
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

	public PracticedPlace getWorld() {
		return world;
	}

	public PracticedPlace getAfrica() {
		return africa;
	}

	public PracticedPlace getAsia() {
		return asia;
	}

	public PracticedPlace getAustraliaOceania() {
		return australiaOceania;
	}

	public PracticedPlace getEurope() {
		return europe;
	}

	public PracticedPlace getNorthAmerica() {
		return northAmerica;
	}

	public PracticedPlace getSouthAmerica() {
		return southAmerica;
	}

	public PracticedPlace getArgentina() {
		return argentina;
	}

	public PracticedPlace getAustralia() {
		return australia;
	}

	public PracticedPlace getAustria() {
		return austria;
	}

	public PracticedPlace getBrazil() {
		return brazil;
	}

	public PracticedPlace getCanada() {
		return canada;
	}

	public PracticedPlace getCzech() {
		return czech;
	}

	public PracticedPlace getFrance() {
		return france;
	}

	public PracticedPlace getGermany() {
		return germany;
	}

	public PracticedPlace getChina() {
		return china;
	}

	public PracticedPlace getIndia() {
		return india;
	}

	public PracticedPlace getItaly() {
		return italy;
	}

	public PracticedPlace getMexico() {
		return mexico;
	}

	public PracticedPlace getSlovakia() {
		return slovakia;
	}

	public PracticedPlace getSpain() {
		return spain;
	}

	public PracticedPlace getUnitedKingdom() {
		return unitedKingdom;
	}

	public PracticedPlace getUnitedStates() {
		return unitedStates;
	}
}
