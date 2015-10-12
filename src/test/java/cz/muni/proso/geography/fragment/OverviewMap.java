package cz.muni.proso.geography.fragment;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.google.common.base.Predicate;

public class OverviewMap extends Map {

	@Drone
	private WebDriver browser;

	@FindBy(xpath = "//*[@id='ng-view']/div[1]/div[2]/a[1]")
	private WebElement myKnowledgeButton;

	@FindBy(xpath = "//*[@id='ng-view']/div[1]/div[2]/a[2]")
	private WebElement avgKnowledgeButton;

	@FindBy(xpath = "//div[contains(@class, 'qtip-focus') and contains(@style, 'display: block')]")
	private MapTooltip tooltip;

	/**
	 * Clicks My knowledge button.
	 */
	public void switchToMyKnowledge() {
		myKnowledgeButton.click();
	}

	/**
	 * Clicks Average new user button.
	 */
	public void switchToAvgKnowledge() {
		avgKnowledgeButton.click();
	}

	/**
	 * Returns number representing the knowledge estimate of specified place.
	 * Finds the corresponding knowledge estimate from {@link ColourHashMap},
	 * based on place colour.
	 * 
	 * @param place
	 * @return <code>int</code> number representing the knowledge estimate of
	 *         specified place. Possible values are in range of 0-10. 0 means
	 *         that the place has not been practiced yet.
	 */
	public int getKnowledgeEstimate(String place) {
		return Integer.parseInt(ColourHashMap
				.getColourMeaning(getPlaceColour(place)));
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
	 * Compares two places and returns which has better knowledge estimate.
	 * 
	 * @param place1
	 * @param place2
	 * @return Returns 1 if the first place is easier, 0 if both places have
	 *         same difficulty¨ and -1 if the second place is easier.
	 */
	public int compareKnowledgeEstimate(String place1, String place2) {
		return Integer.compare(getKnowledgeEstimate(place1),
				getKnowledgeEstimate(place2));
	}

	/**
	 * Returns currently displayed map tooltip.
	 * 
	 * @return currently displayed map tooltip
	 */
	public MapTooltip getTooltip() {
		Graphene.waitModel().until(new Predicate<WebDriver>() {
			@Override
			public boolean apply(WebDriver browser) {
				return tooltip.isTooltipDisplayed();
			}
		});
		return tooltip;
	}
}