package cz.muni.proso.geography.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import cz.muni.proso.geography.fragment.Practice;
import cz.muni.proso.geography.page.PracticePage;
import cz.muni.proso.geography.page.WorldMap;

@RunWith(Arquillian.class)
public class PracticeTest extends TestUtilityClass {

	@Page
	private PracticePage practicePage;

	@Page
	private WorldMap worldPage;

	@Before
	public void openPage() {
		browser.get(BASE_URL + "/practice/us/state");

		if (!practicePage.getNavMenu().getActiveLanguage().equals("en")) {
			practicePage.getNavMenu().switchLanguage("en");
		}

		waitUntilPageLoaded();

		Practice.setQuestionCounter(1);
	}

	@Test
	public void testAnswering() {
		List<String> allPracticedItems = new ArrayList<String>();

		while (Practice.getQuestionCounter() <= 10) {
			int progressBarWidth = practicePage.getPractice()
					.getProgressBarWidthPercentage();
			String currentPracticeItem = "";
			String currentQuestionType = practicePage.getMap()
					.getQuestionType();

			if (currentQuestionType.equals("one highlighted")) {

				practicePage.getMap().waitForHighlightedAnswers();
				currentPracticeItem = practicePage.getMap()
						.getHighlightedAnswers().get(0);
				practicePage.getPractice().clickAnswerOption(
						currentPracticeItem);

				practicePage.getMap().waitForCorrectAnswers();

				assertTrue(practicePage.getMap().getCorrectAnswers()
						.contains(currentPracticeItem));
			} else {
				practicePage.getPractice().waitForPracticeItem();
				currentPracticeItem = practicePage.getPractice()
						.getPracticeItemName();
				practicePage.getMap().clickPlace(currentPracticeItem);
				practicePage.getMap().waitForCorrectAnswers();
				assertTrue(practicePage.getMap().getCorrectAnswers()
						.contains(currentPracticeItem));
			}

			assertTrue(progressBarWidth < practicePage.getPractice()
					.getProgressBarWidthPercentage());
			assertTrue(practicePage.getMap().getWrongAnswers().isEmpty());
			Practice.incrementQuestionCounter();

		}

		if (practicePage.getPracticeFeedback().isPresent()) {
			practicePage.getPracticeFeedback().clickAppropriateButton();
			assertTrue(practicePage.getPracticeFeedback().getAlertMessage()
					.isSuccessAlert());
			practicePage.getPracticeFeedback().closeForm();
			assertFalse(practicePage.getPracticeFeedback().isPresent());
		}

		assertTrue(practicePage.getPractice().getProgressBarWidthPercentage() == 100);

		for (String practicedPlace : practicePage.getPractice()
				.getPracticeSummary().getFlashcardList()) {
			assertTrue(practicePage.getMap().getCorrectAnswers()
					.contains(practicedPlace));
		}

		allPracticedItems.addAll(practicePage.getPractice()
				.getPracticeSummary().getFlashcardList());
		practicePage.getPractice().getPracticeSummary().clickKnowledgeMap();
		waitUntilPageLoaded();
		assertTrue(worldPage.getWorldMap().getMapName().equals("U.S."));
		worldPage.getMapControl().showPoliticalTab();
		assertTrue(worldPage.getMapControl().getCities().getContextNamesList()
				.containsAll(allPracticedItems));
	}

	@Test
	public void testControls() {
		int progressBarWidth = practicePage.getPractice()
				.getProgressBarWidthPercentage();
		practicePage.getPractice().waitForPracticeItem();
		String currentPracticeItem = practicePage.getPractice()
				.getPracticeItemName();
		String currentQuestionType = practicePage.getMap().getQuestionType();

		if (!currentQuestionType.equals("no highlighted")) {
			int placeWidth = practicePage.getMap()
					.getPlaceSize(currentPracticeItem).getWidth();
			practicePage.getPractice().highlightAgain();
			assertTrue(practicePage.getMap().getPlaceSize(currentPracticeItem)
					.getWidth() != placeWidth);
			practicePage.getPractice().clickDontKnow();

			if (currentQuestionType.equals("one highlighted")) {
				practicePage.getPractice().waitForAnswerNames();
				assertTrue(practicePage.getPractice().getAnswersNames()
						.contains(currentPracticeItem));
			} else {
				practicePage.getPractice().waitForPracticeItem();
				assertTrue(practicePage
						.getMap()
						.getCorrectAnswers()
						.contains(
								practicePage.getPractice()
										.getPracticeItemName()));
			}
			practicePage.getPractice().highlightAgain();
			assertTrue(practicePage.getMap().getPlaceSize(currentPracticeItem)
					.getWidth() == placeWidth);
		} else {
			practicePage.getPractice().clickDontKnow();
			practicePage.getMap().waitForCorrectAnswers();
			assertTrue(practicePage.getMap().getCorrectAnswers()
					.contains(practicePage.getPractice().getPracticeItemName()));
			practicePage.getPractice().clickContinue();
		}

		assertTrue(practicePage.getPractice().getProgressBarWidthPercentage() > progressBarWidth);
	}
}
