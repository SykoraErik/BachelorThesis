package cz.muni.proso.geography.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.google.common.base.Predicate;

public class PracticeMap extends Map {

	@Drone
	private WebDriver browser;

	@FindBy(xpath = "//*[not(contains(@style, 'none')) and (contains(@class, 'mountains') or contains(@class, 'river') or contains(@class, 'state') or contains(@class, 'city') or contains(@class, 'island') or contains(@class, 'lake'))]")
	private List<WebElement> listOfAllMapPlaces;

	/**
	 * Finds the specified place on the map and clicks it.
	 * 
	 * @param place
	 */
	public void clickPlace(final String place) {
		WebElement placeToClick = getPlace(place);
		Graphene.waitModel().until().element(placeToClick).is().visible();
		placeToClick.click();
	}

	/**
	 * Return a list of names of all places visible on the map.
	 * 
	 * @return a list of names of all places visible on the map
	 */
	public List<String> getAllMapPlaces() {
		List<String> answerList = new ArrayList<String>();
		for (WebElement answer : listOfAllMapPlaces) {
			answerList.add(answer.getAttribute("data-name"));
		}
		return answerList;
	}

	/**
	 * Returns List of <code>String</code> names of the wrongly selected place.
	 * 
	 * @return List of <code>String</code> names of the wrongly selected place.
	 */
	public List<String> getWrongAnswers() {
		List<String> answerList = new ArrayList<String>();
		List<WebElement> elementList = browser.findElements(By
				.xpath("//*[contains(@fill, '" + ColourHashMap.WRONG + "')]"));
		for (WebElement answer : elementList) {
			answerList.add(answer.getAttribute("data-name"));
		}
		return answerList;
	}

	/**
	 * Returns <code>String</code> name of the selected place.
	 * 
	 * @return <code>String</code> name of the place that is the correct answer
	 *         to asked question.
	 */
	public List<String> getCorrectAnswers() {
		List<String> answerList = new ArrayList<String>();
		List<WebElement> elementList = browser
				.findElements(By.xpath("//*[contains(@fill, '"
						+ ColourHashMap.CORRECT + "')]"));
		for (WebElement answer : elementList) {
			answerList.add(answer.getAttribute("data-name"));
		}
		return answerList;
	}

	/**
	 * Return list of all highlighted answers.
	 * 
	 * @return list of all highlighted answers. Returns empty list if there is
	 *         no highlighted answer on the map.
	 */
	public List<String> getHighlightedAnswers() {
		List<String> answerList = new ArrayList<String>();
		List<WebElement> elementList = browser.findElements(By
				.xpath("//*[contains(@fill, '" + ColourHashMap.HIGHLIGHT
						+ "')]"));
		for (WebElement answer : elementList) {
			answerList.add(answer.getAttribute("data-name"));
		}
		return answerList;
	}

	/**
	 * 
	 * @return <code>String</code> representation of question. 3 possible
	 *         values, depending on the number of highlighted places on the map:
	 *         "no highlighted", "one highlighted", "multiple highlighted"
	 */
	public String getQuestionType() {
		switch (getHighlightedAnswers().size()) {
		case 0:
			return "no highlighted";
		case 1:
			return "one highlighted";
		default:
			return "multiple highlighted";
		}
	}

	public void waitForCorrectAnswers() {
		Graphene.waitModel().pollingEvery(100, TimeUnit.MILLISECONDS)
				.until(new Predicate<WebDriver>() {
					@Override
					public boolean apply(WebDriver browser) {
						return !(getCorrectAnswers().isEmpty());
					}
				});
	}

	public void waitForHighlightedAnswers() {
		Graphene.waitModel().until(new Predicate<WebDriver>() {
			@Override
			public boolean apply(WebDriver browser) {
				return !(getHighlightedAnswers().isEmpty());
			}
		});
	}
}