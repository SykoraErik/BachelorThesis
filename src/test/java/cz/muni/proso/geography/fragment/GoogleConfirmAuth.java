package cz.muni.proso.geography.fragment;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleConfirmAuth {

	@FindBy(id = "submit_approve_access")
	private GrapheneElement approveAccessButton;

	@FindBy(id = "submit_deny_access")
	private WebElement denyAccessButton;

	public void approveAccess() {
		Graphene.waitModel().until().element(approveAccessButton).is().enabled();
		approveAccessButton.click();
	}

	public void denyAccess() {
		Graphene.waitAjax().until().element(approveAccessButton).is().present();
		denyAccessButton.click();
	}

	public boolean isPresent() {
		return approveAccessButton.isPresent();
	}
}