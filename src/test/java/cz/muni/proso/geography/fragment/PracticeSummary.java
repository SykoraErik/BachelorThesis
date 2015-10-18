package cz.muni.proso.geography.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PracticeSummary {

	@FindBy(xpath = "./div/div/div/div[1]")
	private WebElement successBar;

	@FindBy(xpath = "./div/div/div/div[2]")
	private WebElement unlearnedBar;

	@FindBy(xpath = "./span/a[1]")
	private WebElement practiceAgainButton;

	@FindBy(xpath = "./span/a[2]")
	private WebElement knowledgeMapButton;

	@FindBy(xpath = "./div/div/span")
	private List<WebElement> summaryFlashcards;

	public List<String> getFlashcardList() {
		List<String> flashcardList = new ArrayList<String>();
		Graphene.waitAjax().until().element(successBar).is().visible();
		for (WebElement flashcard : summaryFlashcards) {
			flashcardList.add(flashcard.getText());
		}
		return flashcardList;
	}
	
	private int getProgressBarWidthPercentage(WebElement progressBar) {
		String styleAttribute = progressBar.getAttribute("style");
		return Integer.parseInt(styleAttribute.substring(7,
				styleAttribute.length() - 2));
	}

	public int getSuccessBarWidthPercentage() {
		return getProgressBarWidthPercentage(successBar);
	}

	public int getUnlearnedBarWidthPercentage() {
		return getProgressBarWidthPercentage(successBar);
	}

	public void practiceAgain() {
		practiceAgainButton.click();
	}

	public void clickKnowledgeMap() {
		knowledgeMapButton.click();
	}

	/**
	 * Returns <code>true</code> if the specified flashcard is in the list of
	 * flashcards.
	 * 
	 * @param placeName
	 * @return <code>true</code> if the specified flashcard status is learned
	 *         <code>false</code> if the specified flashcard status is unlearned
	 *         throws <code>NoSuchElementException</code> if the specified
	 *         flashcard is not in the list of flashcards
	 */
	public boolean isFlashcardLearned(String placeName) {
		for (WebElement flashcard : summaryFlashcards) {
			if (flashcard.getText().trim().equals(placeName)) {
				return flashcard.getAttribute("class")
						.contains("label-learned");
			} else {
				return false;
			}
		}
		throw new NoSuchElementException();
	}
}
