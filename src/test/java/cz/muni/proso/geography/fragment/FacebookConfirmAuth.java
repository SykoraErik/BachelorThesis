package cz.muni.proso.geography.fragment;

import org.jboss.arquillian.graphene.GrapheneElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FacebookConfirmAuth {
	
	@FindBy(xpath = "./div[2]/table/tbody/tr/td[2]/button[2]")
	private GrapheneElement confirmButton;
	
	@FindBy(xpath = "./div[2]/table/tbody/tr/td[2]/button[1]")
	private WebElement cancelButton;
	
	public WebElement getConfirmButton() {
		return confirmButton;
	}

	public WebElement getCancelButton() {
		return cancelButton;
	}

	public void clickConfirmButton(){
		confirmButton.click();
	}
	
	public void clickCancelButton(){
		cancelButton.click();
	}
	
	public boolean isPresent(){
		return confirmButton.isPresent();
	}
}
