package cz.muni.proso.geography.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;

import cz.muni.proso.geography.fragment.Feedback;
import cz.muni.proso.geography.fragment.Login;
import cz.muni.proso.geography.fragment.NavigationMenu;
import cz.muni.proso.geography.page.Home;

@RunWith(Arquillian.class)
public class LoginTest {
	
    @Drone
    private WebDriver browser;
    
    @Page
    private Home home;
    
	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div")
	private Login login;
    
	@FindBy(css = "#nav-main > ul.nav.navbar-nav.pull-right > li:nth-child(1) > a")
	private WebElement loginButton;
	
	@FindBy(css = "#drop1 > span")
	private WebElement userLoggedIn;
	
    @Test
    public void testLoginViaEmail(@InitialPage Home home) throws InterruptedException{
    	loginButton.click();
    	Graphene.waitGui().until().element(login.getLoginUsername()).is().present();
    	login.loginWithEmail("Erik", "pwd");
    	
    	try{
    	Graphene.waitGui().until().element(userLoggedIn).is().visible();
    	if(userLoggedIn.isDisplayed()){
    			System.out.println("User was logged in using email. Test passes.");
    		}
    	}
    	catch(NoSuchElementException ex){
    		throw new InterruptedException("The user is not logged in");
    	}
    }
    /*
    @Test
    public void testLoginViaFacebookFail(@InitialPage Home home) throws InterruptedException{
    	
    	loginButton.click();
    	login.loginWithFacebook("", "");
    	if(!login.getFacebookFragment().getErrorMsg().isDisplayed()){
    		throw new InterruptedException("Login failed but no message is displayed");
    	}
    
    }
    */
    
    
    
    
}
