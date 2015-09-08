package cz.muni.proso.geography.fragment;

import java.util.List;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

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

	public void show() {
		showTab.click();
	}

	public void practice() {
		practiceButton.click();
	}

	private String parseColour(WebElement element) {
		return element.getAttribute("style").substring(25,
				element.getAttribute("style").length() - 1);
	}

	public WebElement getListItem(String itemName) {
		for (WebElement element : contextList) {
			if (element.findElement(By.xpath("./i")).getText()
					.equalsIgnoreCase(itemName)) {
				return element;
			}
		}
		throw new NoSuchElementException("Specified item was not found.");
	}

	public String getListItemColour(String itemName) {
		return parseColour(getListItem(itemName));
	}

	public boolean isContextListEmpty() {
		return contextList.isEmpty();
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

	public void mouseOverListItem(String listItem) {
		WebElement item = getListItem(listItem);
		Actions builder = new Actions(browser);
		Actions mouseOver = builder.moveToElement(item);
		Graphene.waitAjax().until().element(item).is().visible();
		mouseOver.perform();
	}
}
