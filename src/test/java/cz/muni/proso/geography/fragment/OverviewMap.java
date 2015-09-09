package cz.muni.proso.geography.fragment;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OverviewMap extends Map {

	@FindBy(xpath = "//*[@id='ng-view']/div[1]/div[2]/a[1]")
	private WebElement myKnowledgeButton;

	@FindBy(xpath = "//*[@id='ng-view']/div[1]/div[2]/a[2]")
	private WebElement avgKnowledgeButton;

	@FindBy(className = "qtip-title")
	private List<MapTooltip> tooltips;


	public void switchToMyKnowledge() {
		myKnowledgeButton.click();
	}

	public void switchToAvgKnowledge() {
		avgKnowledgeButton.click();
	}

	public MapTooltip getTooltip() {
		for (MapTooltip tooltip : tooltips) {
			if (tooltip.isTooltipDisplayed()) {
				return tooltip;
			}
		}
		throw new NoSuchElementException("There is no tooltip displayed.");
	}

	public boolean isTooltipDisplayed() {
		for (MapTooltip tooltip : tooltips) {
			if (tooltip.isTooltipDisplayed()) {
				return true;
			}
		}
		return false;
	}
}
