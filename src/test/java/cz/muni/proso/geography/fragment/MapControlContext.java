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

	private String parseColour(WebElement element) {
		return Color.fromString(element.getCssValue("border-bottom-color"))
				.asHex();
	}

	public WebElement getListItem(String itemName) {
		return browser.findElement(By.xpath(".//span[contains(text(),'"
				+ itemName + "')]"));
	}

	public String getListItemColour(String itemName) {
		return parseColour(getListItem(itemName));
	}

	public boolean isContextListDisplayed() {
		for (WebElement place : contextList) {
			if (place.isDisplayed()) {
				return true;
			}
		}
		return false;
	}

	public boolean isListItemDisplayed(String itemName) {
		return getListItem(itemName).isDisplayed();
	}

	public int learnedContextCount() {
		int learned = 0;
		for (WebElement element : contextList) {
			if (parseColour(element).equals("#00dd00")) {
				learned++;
			}
		}
		return learned;
	}

	public int practicedContextCount() {
		return contextList.size();
	}

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

	public void practice() {
		practiceButton.click();
	}

	public void mouseOverListItem(String listItem) {
		WebElement item = getListItem(listItem);
		Actions builder = new Actions(browser);
		Actions mouseOver = builder.moveToElement(item);
		Graphene.waitAjax().until().element(item).is().visible();
		mouseOver.perform();
	}
}