package cz.muni.proso.geography.fragment;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PracticeFeedback {

	@FindBy(className = "alert")
	private AlertMessage message;

	@FindBy(xpath = "/html/body/div[68]/div/div/div[2]/div/a[1]")
	private GrapheneElement tooEasyButton;

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
		Graphene.waitAjax().until().element(tooEasyButton).is().not().visible();
		tooEasyButton.click();
	}

	public void clickAppropriateButton() {
		Graphene.waitAjax().until().element(appropriateButton).is().not().visible();
		appropriateButton.click();
	}

	public void clickTooDifficultButton() {
		Graphene.waitAjax().until().element(tooDifficultButton).is().not().visible();
		tooDifficultButton.click();
	}

	public void clickDontKnowButton() {
		Graphene.waitAjax().until().element(tooEasyButton).is().not().visible();
		tooEasyButton.click();
	}

	public void closeForm() {
		Graphene.waitAjax().until().element(closeButton).is().not().visible();
		closeButton.click();
		Graphene.waitAjax().until().element(closeButton).is().not().visible();
	}
	
	public boolean isPresent(){
		return tooEasyButton.isPresent();
	}
}