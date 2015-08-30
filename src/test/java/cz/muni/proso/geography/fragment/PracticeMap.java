package cz.muni.proso.geography.fragment;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.WebDriver;

public class PracticeMap {
	
	@Drone
	private WebDriver browser;
	
	@FindBy(id = "zoom-in")
	private WebElement zoomInButton;
	
	@FindBy(id = "zoom-out")
	private WebElement zoomOutButton;

	@FindBy(className = "state")
	private List<WebElement> listOfStates;
	
	@FindBy(className = "city")
	private List<WebElement> listOfCities;
	
	@FindBy(className = "river")
	private List<WebElement> listOfRivers;
	
	@FindBy(className = "lake")
	private List<WebElement> listOfLakes;
	
	@FindBy(className = "mountains")
	private List<WebElement> listOfMountains;
	
	@FindBy(className = "island")
	private List<WebElement> listOfIslands;
	
	public void clickZoomInButton() {
		zoomInButton.click();
	}

	public void clickZoomOutButton() {
		zoomOutButton.click();
	}
	
	public List<WebElement> getListOfStates(){
		return listOfStates;
	}
	
	public List<WebElement> getListOfCities(){
		return listOfCities;
	}
	
	public List<WebElement> getListOfRivers(){
		return listOfRivers;
	}
	
	public List<WebElement> getListOfLakes(){
		return listOfLakes;
	}
	
	public List<WebElement> getListOfMountains(){
		return listOfMountains;
	}
	
	public List<WebElement> getListOfIslands(){
		return listOfIslands;
	}
	
	public WebElement getSpecificPlace(String placeName, List<WebElement> placeList){
		for(WebElement place: placeList){
			if(place.getAttribute("data-name").equalsIgnoreCase(placeName)){
				return place;
			}
		}
		throw new NoSuchElementException();
	}
	
	public void clickSpecificPlace(String placeName, List<WebElement> placeList){
		getSpecificPlace(placeName, placeList).click();
	}
	
	public void mouseOverSpecificPlace(String placeName, List<WebElement> placeList){
		for(WebElement place: placeList){
			if(place.getAttribute("data-name").equalsIgnoreCase(placeName)){
				Graphene.waitGui().until().element(place).is().present();				
				Actions builder = new Actions(browser);
				Actions mouseOver = builder.moveToElement(place);
				mouseOver.perform();
				break;
			}
		}
	}
}
