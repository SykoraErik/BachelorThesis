package cz.muni.proso.geography.fragment;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.fragment.Root;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MapTooltip {

	@Root
	private WebElement root;

	@Drone
	protected WebDriver browser;

	@FindBy(xpath = "./div[2]/div[1]/div[1]")
	private WebElement title;

	@FindBy(xpath = "./div[2]/div[1]/div[2]/span[1]")
	private WebElement knowledgeEstimate;

	@FindBy(xpath = "./div[2]/div[1]/div[2]/span[2]")
	private WebElement population;

	public String getTitle() {
		return title.getText();
	}

	public int getKnowledgeEstimate() {
		if (root.getText().equals("Unpracticed")) {
			return 0;
		}
		int slashPosition = knowledgeEstimate.getText().indexOf("/");
		return Integer.parseInt(knowledgeEstimate.getText()
				.substring(0, slashPosition - 1)
				.trim());
	}

	public int getPopulation() {
		return Integer.parseInt(population.getText());
	}

	public boolean isTooltipDisplayed() {
		return root.isDisplayed();
	}
}
