package cz.muni.proso.geography.fragment;

import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MapControl {
	
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

	public void clickPoliticalMapTab() {
		politicalMapTab.click();
	}

	public void clickShowStatesTab() {
		showStatesTab.click();
	}

	public void clickPracticeStatesButton() {
		practiceStatesButton.click();
	}

	public void clickShowCitiesTab() {
		showCitiesTab.click();
	}

	public void clickPracticeCitiesButton() {
		practiceCitiesButton.click();
	}

	public void clickWaterTab() {
		Graphene.waitModel().until().element(waterTab).is().visible();
		waterTab.click();
	}

	public void clickShowRiversTab() {
		showRiversTab.click();
	}

	public void clickPracticeRiversButton() {
		practiceRiversButton.click();
	}

	public void clickShowLakesTab() {
		showLakesTab.click();
	}

	public void clickPracticeLakesButton() {
		practiceLakesButton.click();
	}

	public void clickSurfaceTab() {
		surfaceTab.click();
	}

	public void clickShowMountainsTab() {
		showMountainsTab.click();
	}

	public void clickPracticeMountainsButton() {
		practiceMountainsButton.click();
	}

	public void clickShowIslandsTab() {
		showIslandsTab.click();
	}

	public void clickPracticeIslandsButton() {
		practiceIslandsButton.click();
	}
}
