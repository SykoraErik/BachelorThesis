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

	public void confirm() {
		Graphene.waitAjax().until().element(confirmButton).is().present();
		confirmButton.click();
	}

	public void cancel() {
		Graphene.waitAjax().until().element(cancelButton).is().present();
		cancelButton.click();
	}

	public boolean isPresent() {
		return confirmButton.isPresent();
	}
}