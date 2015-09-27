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

import static org.jboss.arquillian.graphene.Graphene.guardAjax;
import cz.muni.proso.geography.fragment.Feedback;

@RunWith(Arquillian.class)
public class FeedbackTest extends TestUtilityClass {

	@FindBy(className = "modal-content")
	private Feedback feedback;

	@FindBy(id = "feedback-btn")
	private WebElement feedbackButton;

	@Before
	public void openPage() {
		browser.get(BASE_URL);
	}

	@Test
	public void testSendFeedback() {
		Graphene.waitModel().until().element(feedbackButton).is().visible();
		feedbackButton.click();
		feedback.sendFeedback("graphene-test", EMAIL);
		assertTrue(feedback.getAlertMsg().isSuccessAlert());
	}

	@Test
	public void testCloseFeedback() {
		Graphene.waitModel().until().element(feedbackButton).is().visible();
		feedbackButton.click();
		feedback.closeFeedbackForm();
		assertFalse(feedback.isFeedbackFormPresent());
	}

	@Test
	public void testErrorDisplay() {
		Graphene.waitModel().until().element(feedbackButton).is().visible();
		feedbackButton.click();
		feedback.inputOptionalEmail("@");
		feedback.clickSendFeedback();
		assertFalse(feedback.getAlertMsg().isSuccessAlert());
		feedback.getAlertMsg().closeAlert();
		assertFalse(feedback.getAlertMsg().isPresent());
	}
}