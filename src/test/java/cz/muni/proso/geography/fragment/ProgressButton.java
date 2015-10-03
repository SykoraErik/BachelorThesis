package cz.muni.proso.geography.fragment;

import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProgressButton {

	@FindBy(xpath = "./a")
	private WebElement button;

	@FindBy(xpath = "./div")
	private ProgressBar progressBar;

	public ProgressBar getProgressBar() {
		return progressBar;
	}

	/**
	 * 
	 * @return text on this button
	 */
	public String getButtonLabel() {
		Graphene.waitGui().until().element(button).is().present();
		return button.getText();
	}

	/**
	 * 
	 * @return link on this button
	 */
	public String getButtonLink() {
		Graphene.waitGui().until().element(button).is().present();
		return button.getAttribute("href");
	}

	/**
	 * Clicks the progress button. Waits until it is present.
	 */
	public void clickButton() {
		Graphene.waitGui().until().element(button).is().present();
		button.click();
	}
}