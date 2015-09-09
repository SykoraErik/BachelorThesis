package cz.muni.proso.geography.fragment;

import java.util.List;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public abstract class Map {

	@Drone
	private WebDriver browser;

	@FindBy(id = "zoom-in")
	private WebElement zoomInButton;

	@FindBy(id = "zoom-out")
	private WebElement zoomOutButton;

	@FindBy(className = "state")
	private List<WebElement> states;

	public void zoomIn() {
		zoomInButton.click();
	}

	public void zoomOut() {
		zoomOutButton.click();
	}

	protected WebElement getPlace(String placeName) {
		return browser.findElement(By.xpath("//*[@data-name='" + placeName
				+ "']"));
	}

	public void mouseOverPlace(String placeName) {
		WebElement place = getPlace(placeName);
		Graphene.waitModel().until().element(place).is().visible();
		Actions builder = new Actions(browser);
		Actions mouseOver = builder.moveToElement(place);
		mouseOver.perform();
	}

	public String getPlaceColour(String placeName) {
		WebElement place = getPlace(placeName);
		if (place.getAttribute("class").equals("river")) {
			return place.getAttribute("stroke");
		} else {
			return place.getAttribute("fill");
		}
	}
}
