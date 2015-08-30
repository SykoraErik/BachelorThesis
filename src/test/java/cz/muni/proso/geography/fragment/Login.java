package cz.muni.proso.geography.fragment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.WebDriver;
import static org.jboss.arquillian.graphene.Graphene.guardAjax;

public class Login {
	
	@Drone
	private WebDriver browser;
		
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div")
	private SignUp signUpFragment;
	
	@FindBy(xpath = "/html/body/div[6]/div/div/div[1]/h3")
	private GrapheneElement loginTitle;
	
	@FindBy(xpath = "/html/body/div[6]/div/div/div[2]/a[1]")
	private WebElement loginGoogleButton;

	@FindBy(xpath = "/html/body/div[6]/div/div/div[2]/a[2]")
	private WebElement loginFacebookButton;
	
	@FindBy(xpath = "/html/body/div[6]/div/div/div[2]/form/div[1]/input")
	private WebElement loginUsername;
	
	@FindBy(xpath = "/html/body/div[6]/div/div/div[2]/form/div[2]/input")
	private WebElement loginPassword;

	@FindBy(xpath = "/html/body/div[6]/div/div/div[2]/form/button")
	private WebElement loginSubmitButton;
			
	@FindBy(xpath = "/html/body/div[6]/div/div/div[3]/a")
	private WebElement signUpButton;
	
	@FindBy(xpath = "/html/body/div[6]/div/div/div[2]/form/div[3]/div/span")
	private GrapheneElement loginFailMsg;
	
	public SignUp getSignUpFragment() {
		return signUpFragment;
	}

	public GrapheneElement getLoginTitle() {
		return loginTitle;
	}

	public WebElement getLoginGoogleButton() {
		return loginGoogleButton;
	}

	public WebElement getLoginFacebookButton() {
		return loginFacebookButton;
	}

	public WebElement getLoginUsername() {
		return loginUsername;
	}

	public WebElement getLoginPassword() {
		return loginPassword;
	}

	public WebElement getLoginSubmitButton() {
		return loginSubmitButton;
	}

	public WebElement getSignUpButton() {
		return signUpButton;
	}

	public GrapheneElement getLoginFailMsg() {
		return loginFailMsg;
	}

	public WebElement getCloseLoginButton() {
		return closeLoginButton;
	}

	public WebElement getCloseLoginFailMsgButton() {
		return closeLoginFailMsgButton;
	}

	@FindBy(xpath = "/html/body/div[6]/div/div/div[1]/button")
	private WebElement closeLoginButton;
	
	@FindBy(xpath = "/html/body/div[6]/div/div/div[2]/form/div[3]/button")
	private WebElement closeLoginFailMsgButton;
	

	public String getLoginTitleText(){
		return loginTitle.getText();
	}
	
	public String getErrorMsgText(){
		return loginFailMsg.getText();
	}
	
	public boolean isLoginFormPresent(){
		Graphene.waitAjax().until().element(loginUsername).is().present();
		return loginTitle.isPresent();
	}
	
	public void inputUsername(String username){
		Graphene.waitAjax().until().element(loginUsername).is().present();
		loginUsername.clear();
		loginUsername.sendKeys(username);
	}
	
	public void inputPassword(String password){
		Graphene.waitAjax().until().element(loginPassword).is().present();
		loginPassword.clear();
		loginPassword.sendKeys(password);
	}
	
	public void clickloginSubmitButton(){
		Graphene.waitAjax().until().element(loginSubmitButton).is().present();
		loginSubmitButton.click();
	}
	
	public void clickCloseLoginButton(){
		Graphene.waitAjax().until().element(closeLoginButton).is().present();
		closeLoginButton.click();
	}
	
	public void clickLoginGoogleButton(){
		Graphene.waitAjax().until().element(loginGoogleButton).is().present();
		loginGoogleButton.click();
	}
	
	public void clickLoginFacebookButton(){
		Graphene.waitAjax().until().element(loginFacebookButton).is().present();
		loginFacebookButton.click();
	}
	
	public boolean isErrorMsgPresent(){
		return loginFailMsg.isPresent();
	}

	public void clickLoginFailMsgCloseButton(){
		if(closeLoginFailMsgButton.isDisplayed()){
		closeLoginFailMsgButton.click();
		}
	}
	
	public void loginWithEmail(String username, String pwd){
		inputUsername(username);
		inputPassword(pwd);
		guardAjax(loginSubmitButton).click();
	}
	
	public void clickSignUpButton(){
		Graphene.waitAjax().until().element(signUpButton).is().present();
		signUpButton.click();
	}
}
