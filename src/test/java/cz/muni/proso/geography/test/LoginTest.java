package cz.muni.proso.geography.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.FindBy;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.junit.Arquillian;

import static org.jboss.arquillian.graphene.Graphene.*;
import cz.muni.proso.geography.fragment.FacebookAuth;
import cz.muni.proso.geography.fragment.FacebookConfirmAuth;
import cz.muni.proso.geography.fragment.GoogleAuth;
import cz.muni.proso.geography.fragment.GoogleConfirmAuth;
import cz.muni.proso.geography.fragment.Login;
import cz.muni.proso.geography.fragment.NavigationMenu;

@RunWith(Arquillian.class)
public class LoginTest extends MyTestClass{
    
	@FindBy(css = "#wrap > div.navbar.navbar-inverse > div > button")
	private Login login;
	
	@FindBy(id = "platformDialogForm")
	private FacebookConfirmAuth facebookConfirm;
    
	@FindBy(id = "login_form")
	private FacebookAuth facebook;
	
	@FindBy(css = "body > div > div.main.content.clearfix")
	private GoogleAuth google;
	
	@FindBy(id = "approval_container")
	private GoogleConfirmAuth googleConfirm;
	
    @FindBy(css = "#wrap > div.navbar.navbar-inverse")
    private NavigationMenu navMenu;

	@Before
	public void openPage(){
		browser.get(baseUrl);
	}
	
	@After
	public void logOff() throws InterruptedException{
    	Thread.sleep(1000);
    	navMenu.clickLoggedInButton();
    	navMenu.clickSignOutButton();
	}
	
    @Test
    public void testEmailLogin() throws InterruptedException{
    	navMenu.clickLoginButton();
    	login.loginWithEmail(username, password);
    	browser.get(baseUrl);
    	assertTrue(navMenu.getLoggedInButton().isDisplayed());
    }
    
    @Test
    public void testFacebook() throws InterruptedException {
    	navMenu.clickLoginButton();
    	login.clickLoginFacebookButton();
    	Thread.sleep(500);
    	if(facebook.isPresent()){
    		facebook.loginOrSignUp(facebookEmail, facebookPassword);
    	}
    	if(facebookConfirm.isPresent()){
    		Graphene.waitAjax().until().element(facebookConfirm.getConfirmButton()).is().present();
    		guardAjax(facebookConfirm).clickConfirmButton();
    	}
    	assertTrue(navMenu.getLoggedInButton().isDisplayed());
    }
    
    @Test
    public void testGoogle() throws InterruptedException{
    	navMenu.clickLoginButton();
    	login.clickLoginGoogleButton();
    	guardAjax(google).login(email, googlePassword);
    	assertTrue(navMenu.getLoggedInButton().isPresent());
    }
}
