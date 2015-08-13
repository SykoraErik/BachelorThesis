package cz.muni.proso.geography.test;

import java.util.concurrent.TimeUnit;

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

import cz.muni.proso.geography.fragment.NavigationMenu;
import cz.muni.proso.geography.page.Home;

@RunWith(Arquillian.class)
public class NavigationMenuTest {
	
    @Drone
    private WebDriver browser;
    
    @FindBy(css = "#wrap > div.navbar.navbar-inverse")
    private NavigationMenu navMenu;
    
    @Test
    public void testButtons(@InitialPage Home home) throws InterruptedException{
    	navMenu.clickWorldButton();
    	if(!browser.getCurrentUrl().equals("http://slepemapy.cz/view/world/")){
    		throw new InterruptedException("User clicked World map button, but did not get on the correct page");
    	}
    	
    	navMenu.clickHomeButton();
    	if(!browser.getCurrentUrl().equals("http://slepemapy.cz/")){
    		throw new InterruptedException("User clicked home button, but did not get on the correct page");
    	}
    	
    	navMenu.clickMapOverviewButton();
    	if(!browser.getCurrentUrl().equals("http://slepemapy.cz/overview/")){
    		throw new InterruptedException("User clicked map overview button, but did not get on the correct page");
    	}
    	
    	navMenu.clickLoginButton();
    	//Graphene.waitGui().until().element(navMenu.getLoginFragment().getLoginSubmitButton()).is().present();
    	if(!navMenu.getLoginFragment().getLoginRoot().isDisplayed()
    	){
    		throw new InterruptedException("User clicked login button, but login form did not open");
    	}
    	
    	/*
    	for(WebElement e: navMenuFragment.getListOfContinents()){
    		navMenuFragment.clickContinentButton();
    		e.click();
    		if(!browser.getCurrentUrl().contains(e.getAttribute("[href]"))){
    			throw new InterruptedException("Use clicked on a specific continent, but did not get on the correct page");
    		}
    		
    	for(WebElement e: navMenuFragment.getListOfContinents()){
    		navMenuFragment.clickStateButton();
    		e.click();
    		if(!browser.getCurrentUrl().contains(e.getAttribute("[href]"))){
    			throw new InterruptedException("Use clicked on a specific continent, but did not get on the correct page");
    		}	
    	}
    	
    	for(WebElement e: navMenuFragment.getListOfContinents()){
    		navMenuFragment.clickLanguageButton();
    		e.click();
    		if(!browser.getCurrentUrl().contains(e.getAttribute("[href]"))){
    			throw new InterruptedException("Use clicked on a specific continent, but did not get on the correct page");
    		}
    	*/
    	
    	
    }   
}
