package cz.muni.proso.geography.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.google.common.base.Predicate;

public class Practice {

	@Drone
	private WebDriver browser;

	private static int questionCounter = 1;

	@FindBy(className = "summary")
	private PracticeSummary summary;

	@FindBy(xpath = "//*[@id='ng-view']/div[2]/div")
	private WebElement progressBar;

	public PracticeSummary getPracticeSummary() {
		return summary;
	}

	public static int getQuestionCounter() {
		return questionCounter;
	}

	public static void setQuestionCounter(int questionCounter) {
		Practice.questionCounter = questionCounter;
	}

	public static void incrementQuestionCounter() {
		questionCounter++;
	}

	public List<WebElement> getAnswersList() {
		return browser.findElements(By.xpath("//*[@id='container']/div[3]/div["
				+ questionCounter + "]/span[1]/span/button"));
	}

	/**
	 * Return list of all answer options in practice control panel.
	 * 
	 * @return list of all answer options in practice control panel
	 */
	public List<String> getAnswersNames() {
		List<String> answerList = new ArrayList<String>();
		List<WebElement> webElements = getAnswersList();
		for (WebElement answer : webElements) {
			answerList.add(answer.getText());
		}
		return answerList;
	}

	public void clickDontKnow() {
		WebElement dontKnowButton = browser.findElement(By
				.xpath("//*[@id='container']/div[3]/div[" + questionCounter
						+ "]/span[2]/button[1]"));
		dontKnowButton.click();
	}

	public void clickContinue() {
		WebElement continueButton = browser.findElement(By
				.xpath("//*[@id='container']/div[3]/div[" + questionCounter
						+ "]/span[2]/button[2]"));
		continueButton.click();
		questionCounter++;
	}

	public void highlightAgain() {
		browser.findElement(
				By.xpath("//*[@id='container']/div[3]/div[" + questionCounter
						+ "]/span[2]/button[3]")).click();
	}

	/**
	 * Returns true if the specified place is present in the list of answer
	 * options.
	 * 
	 * @param place
	 * @return <code>true</code> if the specified place is present in the list
	 *         of answer options. <code>false</code> otherwise
	 */
	public boolean isPresentInAnswerList(String place) {
		return getAnswersNames().contains(place);
	}

	/**
	 * Finds the specified answer option in the list of possible answers and
	 * clicks its button.
	 * 
	 * @param optionName
	 */
	public void clickAnswerOption(String optionName) {
		for (WebElement listItem : getAnswersList()) {
			if (listItem.getText().trim().equals(optionName)) {
				listItem.click();
			}
		}
	}

	/**
	 * Returns value of progress bar width in percentage.
	 * 
	 * @return <code>int</code> value of progress bar width in percentage
	 */
	public int getProgressBarWidthPercentage() {
		Graphene.waitModel().until().element(progressBar).is().present();
		String styleAttribute = progressBar.getAttribute("style");
		return Integer.parseInt(styleAttribute
				.substring(7, styleAttribute.length()).replace("%", "")
				.replace(";", "").trim());
	}

	/**
	 * Returns name of item that is currently practiced.
	 * 
	 * @return name of item that is currently practiced
	 */
	public String getPracticeItemName() {
		WebElement currentItem = browser.findElement(By
				.xpath("//*[@id='container']/div[3]/div[" + questionCounter
						+ "]/div[1]/span[2]"));
		return currentItem.getText();
	}

	public String getCorrectAnswer() {
		for (WebElement listItem : getAnswersList()) {
			if (listItem.getAttribute("class").contains("btn-success")) {
				return listItem.getText();

			}
		}
		throw new NoSuchElementException(
				"There is no correctly answered place in answer list");
	}

	public void waitForPracticeItem() {
		Graphene.waitModel().until(new Predicate<WebDriver>() {
			@Override
			public boolean apply(WebDriver browser) {
				return !(getPracticeItemName().isEmpty());
			}
		});
	}

	public void waitForAnswerNames() {
		Graphene.waitModel().until(new Predicate<WebDriver>() {
			@Override
			public boolean apply(WebDriver browser) {
				return !(getAnswersNames().isEmpty());
			}
		});
	}
}