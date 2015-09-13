package cz.muni.proso.geography.fragment;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
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

	@FindBy(xpath = "//div[contains(@class, 'qtip') and contains(@style, 'display: block')]")
	private MapTooltip tooltip;

	public void switchToMyKnowledge() {
		myKnowledgeButton.click();
	}

	public void switchToAvgKnowledge() {
		avgKnowledgeButton.click();
	}

	public int getKnowledgeEstimate(String place) {
		return Integer.parseInt(ColourHashMap
				.getColourMeaning(getPlaceColour(place)));
	}

	/**
	 * @param place1
	 * @param place2
	 * @return Return the place that has better knowledge estimate.
	 */
	public String compareKnowledgeEstimate(String place1, String place2) {
		if (getKnowledgeEstimate(place1) == getKnowledgeEstimate(place2)) {
			return place1 + " is as difficult as " + place2 + ".";
		}
		if (getKnowledgeEstimate(place1) > getKnowledgeEstimate(place2)) {
			return place1;
		} else
			return place2;
	}

	public MapTooltip getTooltip() {
		Graphene.waitGui().until(new Predicate<WebDriver>() {
			@Override
			public boolean apply(WebDriver browser) {
				return tooltip.isTooltipDisplayed();
			}
		});

		return tooltip;
	}
}
