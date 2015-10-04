package cz.muni.proso.geography.fragment;

import java.util.List;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Practice {

	@Drone
	private WebDriver browser;

	@FindBy(xpath = "//*[@id='container']/div[3]/div[11]")
	private PracticeSummary summary;
	
	@FindBy(xpath = "//*[@id='container']/div[3]/div/div[1]/span[2]")
	private WebElement practiceItemName;

	@FindBy(xpath = "//*[@id='ng-view']/div[2]")
	private WebElement progressBar;

	@FindBy(xpath = "//*[@id='container']/div[3]/div/span[2]/button[1]")
	private WebElement dontKnowButton;

	@FindBy(xpath = "//*[@id='container']/div[3]/div/span[2]/button[2]")
	private WebElement continueButton;

	@FindBy(xpath = "//*[@id='container']/div[3]/div/span[2]/button[3]")
	private WebElement highlightButton;

	@FindBy(xpath = "//*[@id='container']/div[3]/div[2]/span[1]/span/button")
	private List<WebElement> answerOptions;

	public PracticeSummary getPracticeSummary(){
		return summary;
	}
	
	/**
	 * Returns name of item that is currently practiced.
	 * 
	 * @return name of item that is currently practiced
	 */
	public String getPracticeItemName() {
		return practiceItemName.getText();
	}

	/**
	 * Returns value of progress bar width in percentage.
	 * 
	 * @return <code>int</code> value of progress bar width in percentage
	 */
	public int getProgressBarWidthPercentage() {
		String styleAttribute = progressBar.getAttribute("style");
		return Integer.parseInt(styleAttribute.substring(7,
				styleAttribute.length() - 2));
	}

	public void clickDontKnow() {
		dontKnowButton.click();
	}

	public void clickContinue() {
		continueButton.click();
	}

	public void highlightAgain() {
		highlightButton.click();
	}

	/**
	 * Returns true if the specified place is present in the list of answer
	 * options.
	 * 
	 * @param place
	 * @return <code>true</code> if the specified place is present in the list
	 *         of answer options. <code>false</code> otherwise
	 */
	public boolean isPresentInAnswerList(String place) {
		for (WebElement listItem : answerOptions) {
			if (listItem.getText().trim().equals(place)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Finds the specified answer option in the list of possible answers and
	 * clicks its button.
	 * 
	 * @param optionName
	 */
	public void clickAnswerOption(String optionName) {
		for (WebElement listItem : answerOptions) {
			if (listItem.getText().trim().equals(optionName)) {
				listItem.click();
			}
		}
	}
}