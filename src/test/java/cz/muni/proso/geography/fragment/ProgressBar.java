package cz.muni.proso.geography.fragment;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.fragment.Root;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class ProgressBar {

	@Drone
	private WebDriver browser;
	
	@Root
	private WebElement root;
	
	@FindBy(xpath = "./div/div[1]")
	private WebElement learnedBar;
	
	@FindBy(xpath = "./div/div[2]")
	private WebElement practicedBar;
	
	public WebElement getLearnedBar() {
		return learnedBar;
	}

	public WebElement getPracticedBar() {
		return practicedBar;
	}

	public WebElement getRoot(){
		return root;
	}
	
	public double parseStyleAttribute(WebElement elementToParse){
		String style = elementToParse.getAttribute("style").substring(7);
		style = style.replace("%", "");
		Double width = Double.parseDouble(style);
		return width;
	}
	
	public double getLearnedBarWidth(){
		return parseStyleAttribute(learnedBar);
	}
	
	public double getPracticedBarWidth(){
		return parseStyleAttribute(practicedBar);
	}
	
	public void mouseOverProgressBar(){
		Actions builder = new Actions(browser);
		Actions mouseOver = builder.moveToElement(root);
		Graphene.waitAjax().until().element(root).is().visible();
		mouseOver.perform();
	}	
}
