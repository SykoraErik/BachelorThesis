package cz.muni.proso.geography.fragment;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.jboss.arquillian.graphene.fragment.Root;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MapTooltip {

	@Root
	private GrapheneElement root;

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

	/**
	 * Returns <code>int</code> number representing knowledge estimate. Possible
	 * values are in range of 0-10. 0 means that the place was not yet
	 * practiced.
	 * 
	 * @return <code>int</code> number representing knowledge estimate
	 */
	public int getKnowledgeEstimate() {
		if (root.getText().equals("Unpracticed")) {
			return 0;
		}
		int slashPosition = knowledgeEstimate.getText().indexOf("/");
		return Integer.parseInt(knowledgeEstimate.getText()
				.substring(0, slashPosition - 1).trim());
	}

	/**
	 * 
	 * @return <code>int</code> number representing population
	 */
	public int getPopulation() {
		return Integer.parseInt(population.getText());
	}

	/**
	 * Return <code>true</code> if map tooltip is displayed. Catches
	 * <code>NoSuchElementException</code> to determine if the tooltip is
	 * displayed or not.
	 * 
	 * @return <code>true</code> if the map tooltip is displayed
	 *         <code>false</code> otherwise
	 */
	public boolean isTooltipDisplayed() {
		try {
			return root.isDisplayed();
		} catch (NoSuchElementException ex) {
			return false;
		}
	}
}