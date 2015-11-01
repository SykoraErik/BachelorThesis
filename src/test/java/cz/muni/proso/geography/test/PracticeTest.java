package cz.muni.proso.geography.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.google.common.base.Predicate;

import cz.muni.proso.geography.fragment.Practice;
import cz.muni.proso.geography.page.PracticePage;

@RunWith(Arquillian.class)
public class PracticeTest extends TestUtilityClass {

	@FindBy(xpath = "/html/body")
	private PracticePage page;

	@Before
	public void openPage() throws InterruptedException {
		browser.get(BASE_URL + "/practice/us/state");
		if (!page.getNavMenu().getActiveLanguage().equals("en")) {
			page.getNavMenu().switchLanguage("en");
		}
		waitUntilPageLoaded();
		Practice.setQuestionCounter(1);
	}

	@Test
	public void testAnswering() throws InterruptedException {
		while (Practice.getQuestionCounter() <= 10) {
			int progressBarWidth = page.getPractice()
					.getProgressBarWidthPercentage();
			String currentPracticeItem = "";
			Thread.sleep(3000);
			String currentQuestionType = page.getMap().getQuestionType();
			
			if (currentQuestionType.equals("one highlighted")) {

				page.getMap().waitForHighlightedAnswers();
				currentPracticeItem = page.getMap().getHighlightedAnswers()
						.get(0);
				page.getPractice().clickAnswerOption(currentPracticeItem);

				page.getMap().waitForCorrectAnswers();

				assertTrue(page.getMap().getCorrectAnswers()
						.contains(currentPracticeItem));
			} else {
				page.getPractice().waitForPracticeItem();

				currentPracticeItem = page.getPractice().getPracticeItemName();
				page.getMap().clickPlace(currentPracticeItem);

				page.getMap().waitForCorrectAnswers();

				assertTrue(page.getMap().getCorrectAnswers()
						.contains(currentPracticeItem));
			}

			assertTrue(progressBarWidth < page.getPractice()
					.getProgressBarWidthPercentage());
			assertTrue(page.getMap().getWrongAnswers().isEmpty());
			Practice.incrementQuestionCounter();

		}

		if (page.getPracticeFeedback().isPresent()) {
			page.getPracticeFeedback().clickAppropriateButton();
			assertTrue(page.getPracticeFeedback().getAlertMessage()
					.isSuccessAlert());
			page.getPracticeFeedback().closeForm();
			assertFalse(page.getPracticeFeedback().isPresent());
		}

		assertTrue(page.getPractice().getProgressBarWidthPercentage() == 100);

		for (String practicedPlace : page.getPractice().getPracticeSummary()
				.getFlashcardList()) {
			assertTrue(page.getMap().getCorrectAnswers()
					.contains(practicedPlace));
		}

		page.getPractice().getPracticeSummary().clickKnowledgeMap();
		waitUntilPageLoaded();
		assertTrue(browser
				.findElement(By.xpath("//*[@id='ng-view']/div[1]/h1"))
				.getText().equals("U.S."));
	}

	@Test
	public void testControls() throws InterruptedException {
		int progressBarWidth = page.getPractice()
				.getProgressBarWidthPercentage();
		page.getPractice().waitForPracticeItem();
		String currentPracticeItem = page.getPractice().getPracticeItemName();
		Thread.sleep(3000);
		String currentQuestionType = page.getMap().getQuestionType();

		if (!currentQuestionType.equals("no highlighted")) {
			int placeWidth = page.getMap().getPlaceSize(currentPracticeItem)
					.getWidth();
			page.getPractice().highlightAgain();
			assertTrue(page.getMap().getPlaceSize(currentPracticeItem)
					.getWidth() != placeWidth);
			page.getPractice().clickDontKnow();

			if (currentQuestionType.equals("one highlighted")) {
				page.getPractice().waitForAnswerNames();
				assertTrue(page.getPractice().getAnswersNames()
						.contains(currentPracticeItem));
			} else {
				page.getPractice().waitForPracticeItem();
				assertTrue(page.getMap().getCorrectAnswers()
						.contains(page.getPractice().getPracticeItemName()));
			}
			page.getPractice().highlightAgain();
			assertTrue(page.getMap().getPlaceSize(currentPracticeItem)
					.getWidth() == placeWidth);
		} else {
			page.getPractice().clickDontKnow();
			page.getMap().waitForCorrectAnswers();
			assertTrue(page.getMap().getCorrectAnswers()
					.contains(page.getPractice().getPracticeItemName()));
		}

		page.getPractice().clickContinue();
		assertTrue(page.getPractice().getProgressBarWidthPercentage() > progressBarWidth);
		assertFalse(page.getPractice().getPracticeItemName()
				.equals(currentPracticeItem));
	}
}
