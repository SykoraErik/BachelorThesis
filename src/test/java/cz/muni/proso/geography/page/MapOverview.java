package cz.muni.proso.geography.page;

import java.util.List;
import java.util.NoSuchElementException;
import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import com.google.common.base.Predicate;
import cz.muni.proso.geography.fragment.PracticedContext;
import cz.muni.proso.geography.fragment.ProgressBarTooltip;

public class MapOverview extends CommonPageFragments {

	@FindBy(xpath = "//*[@role='tooltip']")
	private ProgressBarTooltip tooltip;

	@FindBy(xpath = "//*[@id='ng-view']/div/div[2]/ul/li")
	private PracticedContext world;

	@FindBy(xpath = "//*[@id='ng-view']/div/div[3]/ul/li")
	private List<PracticedContext> listOfContinents;

	@FindBy(xpath = "//*[@id='ng-view']/div/div[4]/ul/li")
	private List<PracticedContext> listOfStates;

	public PracticedContext getWorld() {
		return world;
	}

	public List<PracticedContext> getListOfContinents() {
		return listOfContinents;
	}

	public List<PracticedContext> getListOfStates() {
		return listOfStates;
	}

	/**
	 * Return page fragment containing specified continent.
	 * 
	 * @param continentToGet
	 * @return page fragment containing specified continent. Throws
	 *         <code>NoSuchElementException</code> if the continent was not
	 *         found.
	 */
	public PracticedContext getContinent(String continentToGet) {
		for (PracticedContext continent : listOfContinents) {
			if (continent.getPlaceTitle().equals(continentToGet)) {
				return continent;
			}
		}
		throw new NoSuchElementException("Continent was not found.");
	}

	/**
	 * Return page fragment containing specified state.
	 * 
	 * @param stateToGet
	 * @return page fragment containing specified state. Throws
	 *         <code>NoSuchElementException</code> if the state was not found.
	 */
	public PracticedContext getState(String stateToGet) {
		for (PracticedContext state : listOfStates) {
			if (state.getPlaceTitle().equals(stateToGet)) {
				return state;
			}
		}
		throw new NoSuchElementException("State was not found.");
	}

	/**
	 * Return tooltip currently displayed on Map Overview page.
	 * 
	 * @return page fragment containing currently displayed tooltip. Waits 10
	 *         seconds or until the tooltip is displayed.
	 */
	public ProgressBarTooltip getTooltip() {
		Graphene.waitModel().until(new Predicate<WebDriver>() {
			@Override
			public boolean apply(WebDriver browser) {
				return tooltip.isDisplayed();
			}
		});
		return tooltip;
	}
}