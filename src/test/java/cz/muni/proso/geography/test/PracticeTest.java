package cz.muni.proso.geography.test;

import static org.junit.Assert.assertTrue;

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
	public void testFragments() throws InterruptedException {

		while (page.getPractice().getProgressBarWidthPercentage() < 100)
		{
//			System.out.println(page.getPractice().getQuestionCounter());
			if (page.getMap().getQuestionType()
					.equals("choose one highlighted")) {
//				System.out.println(page.getPractice()
//						.getAnswerOptions());
				page.getPractice().clickAnswerOption(
						page.getMap().getHighlightedAnswers().get(0));
			}
			else {
				String currentPracticeItem = page.getPractice().getPracticeItemName();
//				System.out.println(currentPracticeItem);
//				System.out.println(page.getMap().getListOfAnswers());
				page.getMap().clickPlace(currentPracticeItem);
			}
			
			assertTrue(page.getMap().getWrongAnswer().equals(""));
		}
	}
}
