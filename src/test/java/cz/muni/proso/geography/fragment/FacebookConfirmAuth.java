package cz.muni.proso.geography.fragment;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FacebookConfirmAuth {

	@FindBy(xpath = "./div[2]/table/tbody/tr/td[2]/button[2]")
	private GrapheneElement confirmButton;

	@FindBy(xpath = "./div[2]/table/tbody/tr/td[2]/button[1]")
	private WebElement cancelButton;

	/**
	 * Clicks confirm button to let facebook use currently used facebook account
	 * for login or sign up
	 */
	public void confirm() {
		Graphene.waitAjax().until().element(confirmButton).is().present();
		confirmButton.click();
	}

	/**
	 * Clicks deny button to deny facebook use of currently used facebook
	 * account for login or sign up
	 */
	public void cancel() {
		Graphene.waitAjax().until().element(cancelButton).is().present();
		cancelButton.click();
	}

	/**
	 * Returns true if facebook confirm form element is present.
	 * 
	 * @return <code>true</code> if facebook confirm form element is present.
	 *         <code>false</code> otherwise.
	 */
	public boolean isPresent() {
		return confirmButton.isPresent();
	}
}