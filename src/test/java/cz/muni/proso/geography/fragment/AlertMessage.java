package cz.muni.proso.geography.fragment;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.fragment.Root;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AlertMessage {

	@Root
	private WebElement root;

	@FindBy(xpath = "./div/span")
	private WebElement alertText;

	@FindBy(xpath = "./button")
	private WebElement closeButton;

	public String getAlertText() {
		Graphene.waitGui().until().element(alertText).is().present();
		return alertText.getText();
	}

	public void closeAlert() {
		if (root.isDisplayed()) {
			closeButton.click();
		}
		Graphene.waitGui().until().element(root).is().not().visible();
	}

	public boolean isPresent() {
		try {
			return root.isDisplayed();
		} catch (NoSuchElementException ex) {
			return false;
		}
	}

	public boolean isSuccessAlert() {
		Graphene.waitAjax().until().element(root).is().visible();
		return root.getAttribute("type").equals("success");
	}
}
