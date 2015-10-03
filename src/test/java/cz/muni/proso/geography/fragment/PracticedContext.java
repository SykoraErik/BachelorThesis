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

	/**
	 * Returns name of practiced context(for example Africa).
	 * 
	 * @return name of practiced context(for example Africa).
	 */
	public String getPlaceTitle() {
		Graphene.waitGui().until().element(viewPlace).is().visible();
		return viewPlace.getText();
	}

	/**
	 * Clicks on a link that leads to overview map of practiced context. Waits
	 * until the link is present.
	 */
	public void clickViewPlace() {
		Graphene.waitGui().until().element(viewPlace).is().present();
		viewPlace.click();
	}

	/**
	 * Returns list of all progress buttons that are available for the current
	 * context.
	 * 
	 * @return list of all progress buttons that are available for the current
	 *         context
	 */
	public List<ProgressButton> getProgressButtonList() {
		return progressButtonList;
	}

	/**
	 * Returns specific progress button from the list of all progress buttons
	 * available for the current context.
	 * 
	 * @param placeToGet
	 * @return specific progress button from the list of all progress buttons
	 *         available for the current context. Throws <code>NoSuchElementException<code>
	 *         if the button was not found.
	 */
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