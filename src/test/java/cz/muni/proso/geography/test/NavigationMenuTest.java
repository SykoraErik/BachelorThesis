package cz.muni.proso.geography.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.jboss.arquillian.junit.Arquillian;

import cz.muni.proso.geography.fragment.NavigationMenu;

@RunWith(Arquillian.class)
public class NavigationMenuTest extends MyTestClass{
    
    @FindBy(css = "#wrap > div.navbar.navbar-inverse")
    private NavigationMenu navMenu;
    
	@Before
	public void openPage(){
		browser.get(baseUrl);
	}
    
    @Test
    public void testButtons(){
    	
    	navMenu.clickWorldButton();
    	waitUntilPageLoaded();
    	assertTrue((browser.findElement(By.xpath("//*[@id='ng-view']/div[1]/h1")).getText().equals("World")));
    	
    	navMenu.clickHomeButton();
    	assertTrue((browser.findElement(By.xpath("//*[@id='ng-view']/div[1]/h1")).getText().equals("Outline maps")));
    	
    	navMenu.clickMapOverview();
    	waitUntilPageLoaded();
    	assertTrue((browser.findElement(By.xpath("//*[@id='ng-view']/div/h1")).getText().equals("Maps overview")));
    	
    	navMenu.clickLoginButton();
    	assertTrue(navMenu.getLogin().isLoginFormPresent());
    	navMenu.getLogin().clickCloseLoginButton();
    	}
    
    @Test
    public void testContinents(){
    	for(WebElement continent: navMenu.getListOfContinents()){
    		navMenu.clickContinentButton();
    		String continentName = continent.getText();
    		continent.click();
    		waitUntilPageLoaded();
    		assertEquals(continentName, browser.findElement(By.xpath("//h1")).getText());
    	}
    }
    
    @Test
    public void testStates(){
    	for(WebElement state: navMenu.getListOfStates()){
    		navMenu.clickStateButton();
    		String stateName = state.getText();
    		state.click();
    		waitUntilPageLoaded();
    		if(stateName.equals("United States")||stateName.equals("Spojenďż˝ stďż˝ty americkďż˝")||stateName.equals("Estados Unidos")){
    			assertEquals("U.S.", browser.findElement(By.xpath("//h1")).getText());
    		}
    		else{
    			assertEquals(stateName, browser.findElement(By.xpath("//h1")).getText());
    		}
    	}
    }
    
    @Test
    public void testLanguages(){
    	for(WebElement language: navMenu.getListOfLanguages()){
    		navMenu.clickLanguageButton();
    		String flagClass = language.findElement(By.xpath("./i")).getAttribute("class");
    		language.click();
    		assertEquals(flagClass, browser.findElement(By.xpath("//*[@id='nav-main']/ul[2]/li[3]/a/i")).getAttribute("class"));
    	}
    	navMenu.clickLanguageButton();
    	navMenu.clickSpecificLanguage("English");
    }
}
