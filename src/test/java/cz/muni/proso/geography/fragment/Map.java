package cz.muni.proso.geography.fragment;

import java.util.List;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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

	public void zoomIn() {
		zoomInButton.click();
	}

	public void zoomOut() {
		zoomOutButton.click();
	}

	/**
	 * Returns <code>WebElement</code> representing the specified place on the
	 * map.
	 * 
	 * @param placeName
	 *            name of place to find
	 * @return <code>WebElement</code> representing the specified place on the
	 *         map
	 */
	protected WebElement getPlace(String placeName) {
		return browser.findElement(By.xpath("//*[@data-name='" + placeName
				+ "']"));
	}

	/**
	 * Returns <code>String</code> containing hexadecimal color value of the
	 * specified place. If the place is river, the method takes the 'stroke'
	 * attribute of WebElement corresponding with the specified place. If the
	 * place is not a river, the method takes the 'fill' attribute of
	 * corresponding WebElement.
	 * 
	 * @param placeName
	 *            place on the map to get colour of
	 * @return <code>String</code> containing hexadecimal color value of the
	 *         specified place.
	 */
	public String getPlaceColour(String placeName) {
		WebElement place = getPlace(placeName);
		if (place.getAttribute("class").equals("river")) {
			return place.getAttribute("stroke");
		} else {
			return place.getAttribute("fill");
		}
	}

	/**
	 * Returns <code>Dimension</code> object containing the height and width of
	 *         the <code>WebElement</code> representing the specified place.
	 * 
	 * @param placeName
	 * @return <code>Dimension</code> object containing the height and width of
	 *         the <code>WebElement</code> representing the specified place
	 */
	public Dimension getPlaceSize(String placeName) {
		return getPlace(placeName).getSize();
	}

	/**
	 * Return a list of <code>WebElement</code> representing specified places on the map.
	 * 
	 * @param place
	 *            Possible values: state, city, river, lake, mountains, island
	 * @return A list of <code>WebElement</code> representing specified places on the map
	 */
	public List<WebElement> getListOf(String place) {
		return browser.findElements(By.className(place));
	}

	/**
	 * Return <code>true</code> if the specified place is present on the map.
	 * 
	 * @param place
	 * @return <code>true</code> if the specified place is present on the map
	 *         <code>false</code> otherwise
	 */
	public boolean isPlaceDisplayed(String place) {
		return getPlace(place).isDisplayed();
	}

	/**
	 * Waits until specified place is visible and does a 'mouse over' action.
	 * 
	 * @param placeName
	 */
	public void mouseOverPlace(String placeName) {
		WebElement place = getPlace(placeName);
		Graphene.waitModel().until().element(place).is().visible();
		Actions builder = new Actions(browser);
		Actions mouseOver = builder.moveToElement(place);
		mouseOver.perform();
	}
}