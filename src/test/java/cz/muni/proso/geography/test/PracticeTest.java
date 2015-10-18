package cz.muni.proso.geography.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import cz.muni.proso.geography.page.PracticePage;

@RunWith(Arquillian.class)
public class PracticeTest extends TestUtilityClass {

	@FindBy(xpath = "/html/body")
	private PracticePage page;

	@Before
	public void openPage(){
		browser.get(BASE_URL + "/practice/us/state");
		if (!page.getNavMenu().getActiveLanguage().equals("en")) {
			page.getNavMenu().switchLanguage("en");
		}
		waitUntilPageLoaded();
	}

	@Test
	public void testAnswering() throws InterruptedException {
		while (page.getPractice().getProgressBarWidthPercentage() < 100) {
			int progressBarWidth = page.getPractice()
					.getProgressBarWidthPercentage();
			if (page.getMap().getQuestionType().equals("one highlighted")) {
				page.getPractice().clickAnswerOption(
						page.getMap().getHighlightedAnswers().get(0));
			} else {
				Thread.sleep(10000);
				String currentPracticeItem = page.getPractice()
						.getPracticeItemName();
				page.getMap().clickPlace(currentPracticeItem);
			}

			assertTrue(progressBarWidth < page.getPractice()
					.getProgressBarWidthPercentage());
			assertTrue(page.getMap().getWrongAnswers().isEmpty());
		}

		if(page.getPracticeFeedback().isPresent()){
			page.getPracticeFeedback().clickAppropriateButton();
			assertTrue(page.getPracticeFeedback().getAlertMessage().isSuccessAlert());
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
		assertTrue(browser.findElement(By.xpath("//*[@id='ng-view']/div[1]/h1")).equals("U.S."));
	}

	public void testControls() {
		int progressBarWidth = page.getPractice()
				.getProgressBarWidthPercentage();
		String currentPracticeItem = page.getPractice().getPracticeItemName();

		page.getPractice().clickContinue();
		assertTrue(page.getPractice().getProgressBarWidthPercentage() == progressBarWidth);
		assertTrue(page.getPractice().getPracticeItemName()
				.equals(currentPracticeItem));

		if (!page.getMap().getQuestionType().equals("no highlighted")) {
			String correctAnswer = page.getMap().getCorrectAnswers().get(0);
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

		assertTrue(page.getMap().getCorrectAnswers()
				.equals(page.getPractice().getPracticeItemName()));
		assertTrue(progressBarWidth < page.getPractice()
				.getProgressBarWidthPercentage());

		page.getPractice().clickContinue();
		assertFalse(page.getPractice().getPracticeItemName()
				.equals(currentPracticeItem));
	}
}
