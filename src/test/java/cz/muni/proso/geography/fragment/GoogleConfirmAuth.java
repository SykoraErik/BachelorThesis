package cz.muni.proso.geography.fragment;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.jboss.arquillian.graphene.fragment.Root;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleConfirmAuth {

	@Root
	private GrapheneElement root;

	@FindBy(id = "submit_approve_access")
	private WebElement approveAccessButton;

	@FindBy(id = "submit_deny_access")
	private WebElement denyAccessButton;

	public void approveAccess() {
		Graphene.waitAjax().until().element(approveAccessButton).is().present();
		approveAccessButton.click();
	}

	public void denyAccess() {
		Graphene.waitAjax().until().element(approveAccessButton).is().present();
		denyAccessButton.click();
	}

	public boolean isPresent() {
		return root.isPresent();
	}
}