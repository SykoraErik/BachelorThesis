package cz.muni.proso.geography.fragment;

import java.util.concurrent.TimeUnit;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.google.common.base.Predicate;

public class Feedback {

	@FindBy(className = "alert")
	private AlertMessage alertMsg;

	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-body.ng-scope > textarea")
	private GrapheneElement feedbackTextBox;

	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-body.ng-scope > input")
	private WebElement optionalEmail;

	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-footer.ng-scope > button.btn.btn-primary.ng-binding")
	private WebElement sendFeedbackButton;

	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-footer.ng-scope > button.btn.btn-danger.ng-binding")
	private WebElement closeFeedbackButton;

	public AlertMessage getAlertMsg() {
		return alertMsg;
	}

	/**
	 * Writes the specified text into the feedback text box. Clears the text box
	 * first.
	 * 
	 * @param feedback
	 *            text to be written into feedback text box
	 */
	public void inputFeedbackText(String feedback) {
		Graphene.waitAjax().until().element(feedbackTextBox).is().visible();
		feedbackTextBox.clear();
		feedbackTextBox.sendKeys(feedback);
	}

	/**
	 * Writes the specified email into the email field. Clears the field first.
	 * 
	 * @param email
	 *            email to be written into email field
	 */
	public void inputOptionalEmail(String email) {
		Graphene.waitAjax().until().element(optionalEmail).is().visible();
		optionalEmail.clear();
		optionalEmail.sendKeys(email);
	}

	/**
	 * Clicks send feedback button. Used after filling in the feedback text and
	 * optional email.
	 */
	public void clickSendFeedback() {
		Graphene.waitAjax().until().element(sendFeedbackButton).is().visible();
		sendFeedbackButton.click();
	}

	/**
	 * Closes the feedback form by clicking feedback form close button. Waits
	 * until the feedback form is not present.
	 */
	public void closeFeedbackForm() {
		Graphene.waitAjax().until().element(closeFeedbackButton).is().visible();
		closeFeedbackButton.click();
		Graphene.waitAjax().until().element(closeFeedbackButton).is().not().visible();
		
	}

	/**
	 * Returns true if feedback form element is present.
	 * 
	 * @return <code>true</code> if feedback form element is present.
	 *         <code>false</code> otherwise.
	 */
	public boolean isFeedbackFormPresent() {
		return feedbackTextBox.isPresent();
	}

	/**
	 * Sends feedback as specified by feedback text and email
	 * 
	 * @param feedbackText
	 *            text to write into feedback text box
	 * @param email
	 *            email to use for sending feedback
	 */
	public void sendFeedback(String feedbackText, String email) {
		inputFeedbackText(feedbackText);
		inputOptionalEmail(email);
		clickSendFeedback();
	}
}