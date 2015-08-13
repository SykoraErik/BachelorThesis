package cz.muni.proso.geography.fragment;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.fragment.Root;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Feedback {

	@Root
	private WebElement feedbackRoot;
	
	@Drone
	private WebDriver browser;
	
	public WebElement getFeedbackFragmentRoot() {
		return feedbackRoot;
	}

	public void setFeedbackFragmentRoot(WebElement feedbackFragmentRoot) {
		this.feedbackRoot = feedbackFragmentRoot;
	}

	public WebDriver getBrowser() {
		return browser;
	}

	@FindBy(id = "feedback-btn")
	private WebElement feedbackButton;
	
	@FindBy(css= "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-body.ng-scope > textarea")
	private WebElement feedbackTextBox;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-body.ng-scope > input")
	private WebElement optionalEmail;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-footer.ng-scope > button.btn.btn-primary.ng-binding")
	private WebElement sendFeedbackButton;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-footer.ng-scope > button.btn.btn-danger.ng-binding")
	private WebElement closeFeedbackButton;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-body.ng-scope > div")
	private WebElement errorMessage;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-body.ng-scope > div > button")
	private WebElement errorMessageCloseButton;
	
	
	//get and set methods
	public WebElement getFeedbackButton(){
		return feedbackButton;
	}
	
	public WebElement getFeedbackTextBox() {
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
	
	public WebElement getErrorMessage(){
		return errorMessage;
	}
	
	public WebElement getErrorMessageCloseButton(){
		return errorMessageCloseButton;
	}
	
	public void setFeedbackText(String feedback){
		feedbackTextBox.sendKeys(feedback);
	}
	
	public void setEmail(String email){
		optionalEmail.sendKeys(email);
	}
	
	//methods for working with feedback fragment
	
	public void inputFeedbackTextBox(String feedback) {
		feedbackTextBox.sendKeys(feedback);
	}
	
	public void inputOptionalEmail(String email) {
		optionalEmail.sendKeys(email);
	}
	
	public void clickFeedbackButton(){
		feedbackButton.click();
	}
	
	public void clickSendFeedbackButton(){
		sendFeedbackButton.click();
	}
	
	public void clickCloseFeedbackButton(){
		closeFeedbackButton.click();
	}
	
	public void clickErrorMessageCloseButton(){
		errorMessageCloseButton.click();
	}
	
	public void sendFeedback(String feedbackText, String email){
		inputFeedbackTextBox(feedbackText);
		inputOptionalEmail(email);
		clickSendFeedbackButton();
	}
	
	
}
