package cz.muni.proso.geography.fragment;

import java.util.List;

import org.openqa.selenium.support.Color;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.google.common.base.Predicate;

public class MapControlContext {

	@Drone
	private WebDriver browser;

	@FindBy(xpath = "./div[1]")
	private WebElement showTab;

	@FindBy(xpath = "./div[1]/a")
	private WebElement practiceButton;

	@FindBy(xpath = "./div[3]/span")
	private List<WebElement> contextList;

	@FindBy(xpath = "./div[2]")
	private ProgressBar progressBar;

	public ProgressBar getProgressBar() {
		return progressBar;
	}

	/**
	 * Returns <code>String</code> containing hexadecimal color value of the
	 * specified <code>WebElement</code>. Used for getting colour of places in
	 * contextList.
	 * 
	 * @param element
	 *            <code>WebElement</code> to parse colour from
	 * @return <code>String</code> containing hexadecimal color value of the
	 *         specified <code>WebElement</code>
	 */
	private String parseColour(WebElement element) {
		return Color.fromString(element.getCssValue("border-bottom-color"))
				.asHex();
	}

	/**
	 * Returns <code>WebElement</code> representing an item in contextList.
	 * 
	 * @param itemName
	 *            name of item in contextList
	 * @return <code>WebElement</code> representing an item in contextList
	 */
	public WebElement getListItem(String itemName) {
		return browser.findElement(By.xpath(".//span[contains(text(),'"
				+ itemName + "')]"));
	}

	/**
	 * Returns <code>String</code> containing hexadecimal color value of the
	 * specified contextList item.
	 * 
	 * @param itemName
	 *            name of item in contextList
	 * @return <code>String</code> containing hexadecimal color value of the
	 *         specified contextList item
	 */
	public String getListItemColour(String itemName) {
		return parseColour(getListItem(itemName));
	}

	/**
	 * Returns <code>true</code> if contextList is displayed. Checks visibility
	 * of every element in contextList and if any item is displayed, the
	 * contextList is displayed. If none of the list items is displayed,
	 * contextList is not displayed.
	 * 
	 * @return <code>true</code> if contextList is displayed <code>false</code>
	 *         otherwise
	 */
	public boolean isContextListDisplayed() {
		for (WebElement place : contextList) {
			if (place.isDisplayed()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns <code>true</code> if list item from contextList is displayed.
	 * 
	 * @param itemName
	 * @return <code>true</code> if list item from contextList is displayed
	 *         <code>false</code> otherwise
	 */
	public boolean isListItemDisplayed(String itemName) {
		return getListItem(itemName).isDisplayed();
	}

	/**
	 * Returns a number of contextList items which have been learned. This means
	 * that their knowledge estimate is 10/10 and this method checks if coulour
	 * of list items corresponds with the colour representing 10/10 knowledge
	 * estimate.
	 * 
	 * @return <code>int</code> number of contextList items which have been
	 *         learned
	 */
	public int learnedContextCount() {
		int learned = 0;
		for (WebElement element : contextList) {
			if (parseColour(element).equals("#00dd00")) {
				learned++;
			}
		}
		return learned;
	}

	/**
	 * Return <code>int</code> number of contextList items which have been
	 * practiced. The method checks the size of contextList, since it contains
	 * any item that has been practiced at least once. Learned items count as
	 * practiced.
	 * 
	 * @return <code>int</code> number of contextList items which have been
	 *         practiced
	 */
	public int practicedContextCount() {
		return contextList.size();
	}

	/**
	 * Shows/hides all items associated with this context(e.g. all states) from
	 * both the contextList and map.
	 */
	public void toggleContext() {
		if (isContextListDisplayed()) {
			showTab.click();
			Graphene.waitModel().until(new Predicate<WebDriver>() {
				@Override
				public boolean apply(WebDriver browser) {
					return !(isContextListDisplayed());
				}
			});
		} else {
			showTab.click();
			Graphene.waitModel().until(new Predicate<WebDriver>() {
				@Override
				public boolean apply(WebDriver browser) {
					return isContextListDisplayed();
				}
			});
		}
	}

	/**
	 * Clicks on practice button.
	 */
	public void practice() {
		Graphene.waitAjax().until().element(practiceButton).is().visible();
		practiceButton.click();
	}

	/**
	 * Gets specified list item, waits until it is visible and performs a 'mouse
	 * over' action.
	 * 
	 * @param listItem
	 */
	public void mouseOverListItem(String listItem) {
		WebElement item = getListItem(listItem);
		Actions builder = new Actions(browser);
		Actions mouseOver = builder.moveToElement(item);
		Graphene.waitAjax().until().element(item).is().visible();
		mouseOver.perform();
	}
}