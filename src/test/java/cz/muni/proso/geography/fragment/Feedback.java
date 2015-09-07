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

	public void inputFeedbackText(String feedback) {
		Graphene.waitAjax().until().element(feedbackTextBox).is().present();
		feedbackTextBox.clear();
		feedbackTextBox.sendKeys(feedback);
	}

	public void inputOptionalEmail(String email) {
		Graphene.waitAjax().until().element(optionalEmail).is().present();
		optionalEmail.clear();
		optionalEmail.sendKeys(email);
	}

	public void clickSendFeedbackButton() {
		Graphene.waitAjax().until().element(sendFeedbackButton).is().present();
		sendFeedbackButton.click();
	}

	public void clickCloseFeedbackButton() {
		Graphene.waitAjax().until().element(closeFeedbackButton).is().present();
		closeFeedbackButton.click();
		Graphene.waitAjax().until().element(feedbackTextBox).is().not()
				.present();
	}

	public boolean isFeedbackFormPresent() {
		return feedbackTextBox.isPresent();
	}

	public void sendFeedback(String feedbackText, String email) {
		inputFeedbackText(feedbackText);
		inputOptionalEmail(email);
		clickSendFeedbackButton();
	}
}
