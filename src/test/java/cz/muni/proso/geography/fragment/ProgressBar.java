package cz.muni.proso.geography.fragment;

import java.util.concurrent.TimeUnit;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.jboss.arquillian.graphene.fragment.Root;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.google.common.base.Predicate;

public class ProgressBar {

	@Drone
	private WebDriver browser;

	@Root
	private WebElement root;

	@FindBy(xpath = "./div/div[1]")
	private GrapheneElement learnedBar;

	@FindBy(xpath = "./div/div[2]")
	private GrapheneElement practicedBar;

	/**
	 * Takes a <code>WebElement</code> and returns its width style attribute.
	 * 
	 * @param elementToParse
	 * @return <code>Double</code> number representing width of specified
	 *         <code>WebElement</code>. If specified element has empty style
	 *         attribute, returns <code>NaN</code>.
	 */
	private Double parseStyleAttribute(WebElement elementToParse) {
		String style = elementToParse.getAttribute("style");
		if (style.isEmpty()) {
			return Double.NaN;
		}
		int percentPosition = style.lastIndexOf("%");
		style = style.substring(7, percentPosition);
		return Double.parseDouble(style);
	}

	/**
	 * Returns width of progress bar representing number of learned items.
	 * 
	 * @return width of progress bar representing number of learned items
	 */
	public Double getLearnedBarWidth() {
		return parseStyleAttribute(learnedBar);
	}

	/**
	 * Returns width of progress bar representing number of practiced items.
	 * 
	 * @return width of progress bar representing number of practiced items
	 */
	public Double getPracticedBarWidth() {
		return parseStyleAttribute(practicedBar);
	}

	/**
	 * Waits until progress bar is visible and performs a 'mouse over' action.
	 */
	public void mouseOver() {
		Actions builder = new Actions(browser);
		Actions mouseOver = builder.moveToElement(root);

		Graphene.waitModel().withTimeout(10, TimeUnit.SECONDS)
				.until(new Predicate<WebDriver>() {
					@Override
					public boolean apply(WebDriver browser) {
						return !(getLearnedBarWidth().isNaN() || getPracticedBarWidth()
								.isNaN());
					}
				});

		mouseOver.perform();
	}
}