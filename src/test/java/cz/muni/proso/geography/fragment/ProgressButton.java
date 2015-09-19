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

	public String getButtonLabel() {
		Graphene.waitGui().until().element(button).is().present();
		return button.getText();
	}

	public String getButtonLink() {
		Graphene.waitGui().until().element(button).is().present();
		return button.getAttribute("href");
	}

	public void clickButton() {
		Graphene.waitGui().until().element(button).is().present();
		button.click();
	}
}