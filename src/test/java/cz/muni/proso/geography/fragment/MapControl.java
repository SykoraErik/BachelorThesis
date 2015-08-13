package cz.muni.proso.geography.fragment;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.fragment.Root;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MapControl {
	
	@Root
	private WebElement mapControlRoot;
	
	@Drone
	private WebDriver browser;
	
	//political map
	@FindBy(css = "#container > div.layers-tabs > div:nth-child(1)")
	private WebElement politicalMapTab;

	@FindBy(css = "#container > ul:nth-child(4) > div > li:nth-child(1) > div.collapse-control")
	private WebElement showStatesTab;
	
	@FindBy(css = "#container > ul:nth-child(4) > div > li:nth-child(1) > div.collapse-control > a")
	private WebElement practiceStatesButton;
	
	@FindBy(css = "#container > ul:nth-child(4) > div > li:nth-child(2) > div.collapse-control")
	private WebElement showCitiesTab;
	
	@FindBy(css = "#container > ul:nth-child(4) > div > li:nth-child(2) > div.collapse-control > a")
	private WebElement practiceCitiesButton;
	
	//water map
	@FindBy(css = "#container > div.layers-tabs > div:nth-child(2)")
	private WebElement waterTab;
	
	@FindBy(css = "#container > ul:nth-child(5) > div > li:nth-child(1) > div.collapse-control")
	private WebElement showRiversTab;
	
	@FindBy(css = "#container > ul:nth-child(5) > div > li:nth-child(1) > div.collapse-control > a")
	private WebElement practiceRiversButton;
	
	@FindBy(css = "#container > ul:nth-child(5) > div > li:nth-child(2) > div.collapse-control")
	private WebElement showLakesTab;
	
	@FindBy(css = "#container > ul:nth-child(5) > div > li:nth-child(2) > div.collapse-control > a")
	private WebElement practiceLakesButton;
	
	//surface map
	@FindBy(css = "#container > div.layers-tabs > div:nth-child(3)")
	private WebElement surfaceTab;
	
	@FindBy(css = "#container > ul:nth-child(6) > div > li:nth-child(1) > div.collapse-control")
	private WebElement showMountainsTab;
	
	@FindBy(css = "#container > ul:nth-child(6) > div > li:nth-child(1) > div.collapse-control > a")
	private WebElement practiceMountainsButton;
	
	@FindBy(css = "#container > ul:nth-child(6) > div > li:nth-child(2) > div.collapse-control")
	private WebElement showIslandsTab;
	
	@FindBy(css = "#container > ul:nth-child(6) > div > li:nth-child(2) > div.collapse-control > a")
	private WebElement practiceIslandsButton;
	
	public WebElement getPoliticalMapTab() {
		return politicalMapTab;
	}

	public WebElement getShowStatesTab() {
		return showStatesTab;
	}

	public WebElement getPracticeStatesButton() {
		return practiceStatesButton;
	}

	public WebElement getShowCitiesTab() {
		return showCitiesTab;
	}

	public WebElement getPracticeCitiesButton() {
		return practiceCitiesButton;
	}

	public WebElement getWaterTab() {
		return waterTab;
	}

	public WebElement getShowRiversTab() {
		return showRiversTab;
	}

	public WebElement getPracticeRiversButton() {
		return practiceRiversButton;
	}

	public WebElement getShowLakesTab() {
		return showLakesTab;
	}

	public WebElement getPracticeLakesButton() {
		return practiceLakesButton;
	}

	public WebElement getSurfaceTab() {
		return surfaceTab;
	}

	public WebElement getShowMountainsTab() {
		return showMountainsTab;
	}

	public WebElement getPracticeMountainsButton() {
		return practiceMountainsButton;
	}

	public WebElement getShowIslandsTab() {
		return showIslandsTab;
	}

	public WebElement getPracticeIslandsButton() {
		return practiceIslandsButton;
	}
}
