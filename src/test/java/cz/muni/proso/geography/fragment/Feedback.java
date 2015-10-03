package cz.muni.proso.geography.fragment;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
		Graphene.waitAjax().until().element(feedbackTextBox).is().present();
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
		Graphene.waitAjax().until().element(optionalEmail).is().present();
		optionalEmail.clear();
		optionalEmail.sendKeys(email);
	}

	/**
	 * Clicks send feedback button. Used after filling in the feedback text and
	 * optional email.
	 */
	public void clickSendFeedback() {
		Graphene.waitAjax().until().element(sendFeedbackButton).is().present();
		sendFeedbackButton.click();
	}

	/**
	 * Closes the feedback form by clicking feedback form close button. Waits
	 * until the feedback form is not present.
	 */
	public void closeFeedbackForm() {
		Graphene.waitAjax().until().element(closeFeedbackButton).is().present();
		closeFeedbackButton.click();
		Graphene.waitAjax().until().element(feedbackTextBox).is().not()
				.present();
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