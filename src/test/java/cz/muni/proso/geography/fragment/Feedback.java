package cz.muni.proso.geography.fragment;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;

public class Feedback {
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-body.ng-scope > textarea")
	private GrapheneElement feedbackTextBox;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-body.ng-scope > input")
	private WebElement optionalEmail;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-footer.ng-scope > button.btn.btn-primary.ng-binding")
	private WebElement sendFeedbackButton;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-footer.ng-scope > button.btn.btn-danger.ng-binding")
	private WebElement closeFeedbackButton;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-body.ng-scope > div > div > span")
	private GrapheneElement feedbackSentMessage;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-body.ng-scope > div > button")
	private WebElement feedbackSentMessageCloseButton;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-body.ng-scope > div")
	private GrapheneElement errorMessage;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-body.ng-scope > div > button")
	private WebElement errorMessageCloseButton;
	
	public GrapheneElement getFeedbackTextBox() {
		return feedbackTextBox;
	}

	public WebElement getOptionalEmail() {
		return optionalEmail;
	}

	public WebElement getSendFeedbackButton() {
		return sendFeedbackButton;
	}

	public WebElement getCloseFeedbackButton() {
		return closeFeedbackButton;
	}

	public GrapheneElement getFeedbackSentMessage() {
		return feedbackSentMessage;
	}

	public WebElement getFeedbackSentMessageCloseButton() {
		return feedbackSentMessageCloseButton;
	}

	public WebElement getErrorMessageCloseButton() {
		return errorMessageCloseButton;
	}

	public GrapheneElement getErrorMessage(){
		return errorMessage;
	}
	
	public void inputFeedbackText(String feedback){
		Graphene.waitAjax().until().element(feedbackTextBox).is().present();
		feedbackTextBox.clear();
		feedbackTextBox.sendKeys(feedback);
	}
	
	public void inputOptionalEmail(String email) {
		Graphene.waitAjax().until().element(optionalEmail).is().present();
		optionalEmail.clear();
		optionalEmail.sendKeys(email);
	}
	
	public void clickSendFeedbackButton(){
		Graphene.waitAjax().until().element(sendFeedbackButton).is().present();
		sendFeedbackButton.click();
		Graphene.waitAjax().until().element(feedbackSentMessage).is().present();
	}
	
	public void clickCloseFeedbackButton(){
		Graphene.waitAjax().until().element(closeFeedbackButton).is().present();
		closeFeedbackButton.click();
		Graphene.waitAjax().until().element(feedbackTextBox).is().not().present();
	}
	
	public void clickErrorMessageCloseButton(){
		Graphene.waitAjax().until().element(errorMessageCloseButton).is().present();
		errorMessageCloseButton.click();
		Graphene.waitAjax().until().element(errorMessage).is().not().present();
	}
	
	public boolean isFeedbackFormPresent(){
		return feedbackTextBox.isPresent();
	}
	
	public boolean isErrorMsgPresent(){
		return errorMessage.isPresent();
	}
	
	public boolean isFeedbackSentMessagePresent(){
		return feedbackSentMessage.isPresent();
	}
	
	public void sendFeedback(String feedbackText, String email){
		inputFeedbackText(feedbackText);
		inputOptionalEmail(email);
		clickSendFeedbackButton();
	}
}
