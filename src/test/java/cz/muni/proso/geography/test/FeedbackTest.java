package cz.muni.proso.geography.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.jboss.arquillian.junit.Arquillian;

import cz.muni.proso.geography.fragment.Feedback;

@RunWith(Arquillian.class)
public class FeedbackTest extends TestUtilityClass {

	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div")
	private Feedback feedback;

	@FindBy(css = "#feedback-btn > span > a.main-btn")
	private WebElement feedbackButton;

	@Before
	public void openPage(){
		browser.get(BASE_URL);
		Graphene.waitModel().until().element(feedbackButton).is().visible();
		feedbackButton.click();
	}

	@Test
	public void testSendFeedback(){
		feedback.sendFeedback("graphene-test", EMAIL);
		assertTrue(feedback.getAlertMsg().isSuccessAlert());
	}

	@Test
	public void testCloseFeedback(){
		feedback.closeFeedbackForm();
		assertFalse(feedback.isFeedbackFormPresent());
	}

	@Test
	public void testAlertDisplay() {
		feedback.sendFeedback("graphene-test", EMAIL);
		feedback.clickSendFeedback();
		assertTrue(feedback.getAlertMsg().isDisplayed());
		feedback.getAlertMsg().closeAlert();
		assertFalse(feedback.getAlertMsg().isDisplayed());
	}
}