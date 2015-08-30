package cz.muni.proso.geography.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.jboss.arquillian.junit.Arquillian;

import cz.muni.proso.geography.fragment.SignUp;

@RunWith(Arquillian.class)
public class SignUpTest extends MyTestClass{
	
    @Drone
    private WebDriver browser;
    
    @FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div")
    private SignUp signUp;
    
	@FindBy(css = "#nav-main > ul.nav.navbar-nav.pull-right > li:nth-child(1) > a")
	private WebElement loginButton;
	
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-footer.ng-scope > a")
	private WebElement signUpButton;
	
	@FindBy(css = "#drop1 > span")
	private GrapheneElement userLoggedIn;
    
	@Before
	public void openPage(){
		browser.get(baseUrl);
	}
	
	@Test
	public void testSignUpEmail() throws InterruptedException{
    	loginButton.click();
    	Graphene.waitGui().until().element(signUpButton).is().present();
    	signUpButton.click();
    	Graphene.waitGui().until().element(signUp.getEmailButton()).is().present();
    	signUp.getEmailButton().click();
    	signUp.signUpWithEmail(email, username, password, password);
    	assertTrue(userLoggedIn.isPresent());
	}
}
