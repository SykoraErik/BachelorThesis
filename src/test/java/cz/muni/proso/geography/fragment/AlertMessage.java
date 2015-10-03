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

	/**
	 * @return <code>String</code> containing alert message text.
	 */
	public String getAlertText() {
		Graphene.waitGui().until().element(alertText).is().present();
		return alertText.getText();
	}

	/**
	 * Checks if alert message is displayed and tries to close it. Waits until
	 * alert message is closed.
	 */
	public void closeAlert() {
		if (isDisplayed()) {
			closeButton.click();
		}
		Graphene.waitGui().until().element(root).is().not().visible();
	}

	/**
	 * @return Whether or not the alert message is displayed.
	 */
	public boolean isDisplayed() {
		try {
			return root.isDisplayed();
		} catch (NoSuchElementException ex) {
			return false;
		}
	}

	/**
	 * @return <code>true</code> if the alert message type is "success".
	 *         <code>false</code> otherwise
	 * */
	public boolean isSuccessAlert() {
		Graphene.waitAjax().until().element(root).is().visible();
		return root.getAttribute("type").equals("success");
	}

	/**
	 * @return <code>true</code> if the alert message type is "danger".
	 *         <code>false</code> otherwise
	 * */
	public boolean isDangerAlert() {
		Graphene.waitAjax().until().element(root).is().visible();
		return root.getAttribute("type").equals("danger");
	}
}