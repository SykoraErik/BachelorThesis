package cz.muni.proso.geography.fragment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MapTooltip {
	
	@FindBy(xpath = "./div[1]")
	private WebElement title;
	
	@FindBy(xpath = "./div[2]/span[1]")
	private WebElement knowledgeEstimate;

	@FindBy(xpath = "./div[2]/span[2]")
	private WebElement population;
	
	public WebElement getTitle() {
		return title;
	}

	public WebElement getKnowledgeEstimate() {
		return knowledgeEstimate;
	}

	public WebElement getPopulation() {
		return population;
	}
	
	public String getTitleText(){
		return title.getText();
	}
	
	public String getKnowledgeEstimateText(){
		return knowledgeEstimate.getText();
	}
	
	public String getPopulationText(){
		return population.getText();
	}
	
	public boolean isTooltipDisplayed(){
		return title.isDisplayed();
	}
}
