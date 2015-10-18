package cz.muni.proso.geography.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.FindBy;

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
		Thread.sleep(3000);
	}

	@Test
	public void testAnswering() {
		while (page.getPractice().getProgressBarWidthPercentage() < 100) {
			int progressBarWidth = page.getPractice()
					.getProgressBarWidthPercentage();

			if (page.getMap().getQuestionType().equals("one highlighted")) {
				page.getPractice().clickAnswerOption(
						page.getMap().getHighlightedAnswers().get(0));
			} else {
				String currentPracticeItem = page.getPractice()
						.getPracticeItemName();
				page.getMap().clickPlace(currentPracticeItem);
			}

			assertTrue(progressBarWidth < page.getPractice()
					.getProgressBarWidthPercentage());
			assertTrue(page.getMap().getWrongAnswer().equals(""));
		}
	}

	public void testControls() {
		int progressBarWidth = page.getPractice()
				.getProgressBarWidthPercentage();
		String currentPracticeItem = page.getPractice()
				.getPracticeItemName();

		page.getPractice().clickContinue();
		assertTrue(page.getPractice().getProgressBarWidthPercentage() == progressBarWidth);
		assertTrue(page.getPractice().getPracticeItemName().equals(currentPracticeItem));

		if (!page.getMap().getQuestionType().equals("no highlighted")) {
			String correctAnswer = page.getMap().getCorrectAnswer();
			int placeWidth = page.getMap().getPlaceSize(correctAnswer)
					.getWidth();
			page.getPractice().highlightAgain();
			assertTrue(page.getMap().getPlaceSize(correctAnswer).getWidth() != placeWidth);
			page.getPractice().clickDontKnow();
			page.getPractice().highlightAgain();
			assertTrue(page.getMap().getPlaceSize(correctAnswer).getWidth() == placeWidth);
		} else {
			page.getPractice().clickDontKnow();
		}

		assertTrue(page.getMap().getCorrectAnswer()
				.equals(page.getPractice().getPracticeItemName()));
		assertTrue(progressBarWidth < page.getPractice()
				.getProgressBarWidthPercentage());

		page.getPractice().clickContinue();
		assertFalse(page.getPractice().getPracticeItemName().equals(currentPracticeItem));
	}
}
