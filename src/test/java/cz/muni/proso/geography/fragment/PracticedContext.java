package cz.muni.proso.geography.fragment;

import java.util.List;
import java.util.NoSuchElementException;

import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PracticedContext {

	@FindBy(xpath = "./h3/a")
	private WebElement viewPlace;

	@FindBy(xpath = "./h3/div")
	private ProgressBar progressBar;

	@FindBy(xpath = "./ul/li")
	private List<ProgressButton> progressButtonList;

	public ProgressBar getProgressBar() {
		return progressBar;
	}

	public String getPlaceTitle() {
		Graphene.waitGui().until().element(viewPlace).is().visible();
		return viewPlace.getText();
	}

	public void clickViewPlace() {
		Graphene.waitGui().until().element(viewPlace).is().present();
		viewPlace.click();
	}

	public List<ProgressButton> getProgressButtonList() {
		return progressButtonList;
	}

	public ProgressButton getProgressButton(String placeToGet) {
		for (ProgressButton progBtn : progressButtonList) {
			if (progBtn.getButtonLabel().equals(placeToGet)) {
				return progBtn;
			}
		}
		throw new NoSuchElementException("The ProgressButton was not found.");
	}

	public void clickProgressButton(String buttonToClick) {
		getProgressButton(buttonToClick).clickButton();
	}
}
