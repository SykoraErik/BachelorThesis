package cz.muni.proso.geography.fragment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PracticeFeedback {

	@FindBy(className = "alert")
	private AlertMessage message;

	@FindBy(xpath = "/html/body/div[68]/div/div/div[2]/div/a[1]")
	private WebElement tooEasyButton;

	@FindBy(xpath = "/html/body/div[68]/div/div/div[2]/div/a[1]")
	private WebElement appropriateButton;

	@FindBy(xpath = "/html/body/div[68]/div/div/div[2]/div/a[1]")
	private WebElement tooDifficultButton;

	@FindBy(xpath = "/html/body/div[68]/div/div/div[2]/div/a[4]")
	private WebElement dontKnowButton;

	@FindBy(xpath = "/html/body/div[68]/div/div/div[3]/button")
	private WebElement closeButton;

	public AlertMessage getAlertMessage() {
		return message;
	}

	public void clickTooEasyButton() {
		tooEasyButton.click();
	}

	public void clickAppropriateButton() {
		appropriateButton.click();
	}

	public void clickTooDifficultButton() {
		tooDifficultButton.click();
	}

	public void clickDontKnowButton() {
		tooEasyButton.click();
	}

	public void closeForm() {
		closeButton.click();
	}
}