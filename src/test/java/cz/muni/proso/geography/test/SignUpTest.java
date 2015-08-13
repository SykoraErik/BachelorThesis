package cz.muni.proso.geography.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;

import cz.muni.proso.geography.fragment.Feedback;
import cz.muni.proso.geography.fragment.Login;
import cz.muni.proso.geography.fragment.NavigationMenu;
import cz.muni.proso.geography.fragment.SignUp;
import cz.muni.proso.geography.page.Home;

@RunWith(Arquillian.class)
public class SignUpTest {
	
    @Drone
    private WebDriver browser;
    
    @Page
    private Home home;
    
    @FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div")
    private SignUp signUp;
    
	@FindBy(css = "#nav-main > ul.nav.navbar-nav.pull-right > li:nth-child(1) > a")
	private WebElement loginButton;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-footer.ng-scope > a")
	private WebElement signUpButton;
	
	@FindBy(css = "#drop1 > span")
	private WebElement userLoggedIn;
    /*
    @Test
    public void testSignUpFacebook(@InitialPage Home home) throws InterruptedException{
    	loginButton.click();
    	signUpButton.click();
    	signUp.getSignUpFacebookButton().click();

    	try{
    	if(signUp.getSignUpFacebookFragment().getConfirmForm().isDisplayed()){
    		//User is logged into facebook account, needs to confirm to use the currently used account
    		signUp.getSignUpFacebookFragment().getConfirmButton().click();
    		if(userLoggedIn.isDisplayed()){
    			//test passes
    			System.out.println("User was logged in after he confirmed his currently logged in FB account.");
    			}
    		}
    	}
    	catch(NoSuchElementException ex){
			//throw new InterruptedException("Error. User tried to sign up with facebook, but was not logged in after confirming to use their account");
    	}
    	
    	try{
    		if(userLoggedIn.isDisplayed()){
    		//User was already registered with facebook and will get automatically logged in
    		System.out.println("User was logged into his account automatically, since the account details were saved");
    		}
    	}
    	catch(NoSuchElementException ex){
    		System.out.println("Not an error. User did not have his account saved.");
    	}
    	
    	signUp.signUpWithFacebook("genericepiceric@gmail.com", "slepemapy");
			try{
				if(userLoggedIn.isDisplayed()){
					//test passes
					System.out.println("User was logged in after he signed up with specific FB account.");
				}
			}
			catch(NoSuchElementException ex){
				throw new InterruptedException("Error. User tried to sign up with facebook, but was not logged in after confirming to use their account.");
			}
    	}
    */
	
    @Test
    public void testSignUpGoogle(@InitialPage Home home) throws InterruptedException{
    	loginButton.click();
    	Graphene.waitGui().until().element(signUpButton).is().present();
    	signUpButton.click();
    	Graphene.waitGui().until().element(signUp.getSignUpGoogleButton()).is().present();
    	signUp.getSignUpGoogleButton().click();
    	/*try{
    	if(signUp.getSignUpGoogleFragment().getAccountsForm().isDisplayed()){
    		System.out.println("Account form is present");
    		}
    	}
    	catch(NoSuchElementException e){
    		System.out.println("Account form not present");
    	}
    	*/
    	signUp.getSignUpGoogleFragment().login("genericepiceric@gmail.com", "slepemapy");
    	try{
    		if(userLoggedIn.isDisplayed()){
    			System.out.println("User was successfuly logged in");
    		}
    	}
    	catch(NoSuchElementException e){
    		throw new InterruptedException("User was not logged in after he signed up with google");
    	}
    }
    /*
	@Test
	public void testSignUpEmail(@InitialPage Home home) throws InterruptedException{
    	loginButton.click();
    	Graphene.waitGui().until().element(signUpButton).is().present();
    	signUpButton.click();
    	Graphene.waitGui().until().element(signUp.getSignUpEmailButton()).is().present();
    	signUp.getSignUpEmailButton().click();
    	signUp.signUpWithEmail("genericepiceric@gmail.com", "eric", "pwd", "pwd");
    	try{
    	Graphene.waitGui().until().element(userLoggedIn).is().visible();
    	}
    	catch(NoSuchElementException ex){
    		throw new InterruptedException("The user tried to register but is not logged in after registration. Test failed.");
    	}
	}*/
}
