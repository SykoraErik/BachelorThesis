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
public class FeedbackTest extends MyTestClass {

	@FindBy(className = "modal-content")
	private Feedback feedback;

	@FindBy(id = "feedback-btn")
	private WebElement feedbackButton;

	@Before
	public void openPage() {
		browser.get(baseUrl);
	}

	@Test
	public void testSendFeedback() {
		Graphene.waitModel().until().element(feedbackButton).is().visible();
		feedbackButton.click();
		guardAjax(feedback).sendFeedback("graphene-test", email);
		assertTrue(feedback.getAlertMsg().isSuccessAlert());
	}

	@Test
	public void testCloseFeedback() {
		Graphene.waitModel().until().element(feedbackButton).is().visible();
		feedbackButton.click();
		feedback.clickCloseFeedbackButton();
		assertFalse(feedback.isFeedbackFormPresent());
	}

	@Test
	public void testErrorDisplay() {
		Graphene.waitModel().until().element(feedbackButton).is().visible();
		feedbackButton.click();
		feedback.inputOptionalEmail("@");
		feedback.clickSendFeedbackButton();
		assertFalse(feedback.getAlertMsg().isSuccessAlert());
		feedback.getAlertMsg().closeAlert();
		assertFalse(feedback.getAlertMsg().isPresent());
	}
}
