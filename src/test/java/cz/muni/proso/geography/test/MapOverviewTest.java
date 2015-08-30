package cz.muni.proso.geography.test;

import static org.junit.Assert.assertTrue;

import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import cz.muni.proso.geography.fragment.NavigationMenu;
import cz.muni.proso.geography.fragment.ProgressButton;
import static org.jboss.arquillian.graphene.Graphene.guardHttp;
import cz.muni.proso.geography.page.MapOverview;

@RunWith(Arquillian.class)
public class MapOverviewTest extends MyTestClass  {
    
    @Page
    private MapOverview page;
    
	@FindBy(css = "#wrap > div.navbar.navbar-inverse")
	private NavigationMenu navMenu;
    
	@Before
	public void openPage() throws InterruptedException{
		browser.get(baseUrl+"/overview/");
		if(!navMenu.getLoggedInButton().isDisplayed()){
	    	navMenu.clickLoginButton();
			navMenu.getLogin().loginWithEmail(username, password);
	    	browser.get(baseUrl+"/overview/");
		}
    	waitUntilPageLoaded();
		Thread.sleep(3000);
	}
	
    @Test
    public void testWorld() throws InterruptedException{
    	page.getWorld().getProgressBar().mouseOverProgressBar();
    	assertTrue(page.getWorld().isTooltipDisplayed());
    	System.out.println(page.getWorld().getProgressBar().getLearnedBar().getAttribute("style"));
    	
    	for(ProgressButton place: page.getWorld().getProgressButtonList()){ 
    		place.getProgressBar().mouseOverProgressBar();
    		assertTrue(page.getWorld().isTooltipDisplayed());
    	}
    	
    	String placeTitle = page.getWorld().getPlaceTitle();
    	page.getWorld().clickViewPlace();
    	assertTrue(browser.findElement(By.xpath("//*[@id='ng-view']/div[1]/h1")).getText().equals(placeTitle));
    	browser.get(baseUrl+"/overview/");
    	waitUntilPageLoaded();
    	page.getWorld().getProgressButtonList().get(0).clickButton();
    	waitUntilPageLoaded();
    	assertTrue(browser.findElement(By.className("practice")).isDisplayed());
    }
    
    @Test
    public void testContinents() throws InterruptedException{
    	page.getListOfContinents().get(0).getProgressBar().mouseOverProgressBar();
    	assertTrue(page.getListOfContinents().get(0).isTooltipDisplayed());
        
    	for(ProgressButton place: page.getListOfContinents().get(0).getProgressButtonList()){ 
    		place.getProgressBar().mouseOverProgressBar();
    		assertTrue(page.getWorld().isTooltipDisplayed());
    	}
    	
    	String continentTitle = page.getListOfContinents().get(0).getPlaceTitle();
        page.getListOfContinents().get(0).clickViewPlace();
        assertTrue(browser.findElement(By.xpath("//*[@id='ng-view']/div[1]/h1")).getText().equals(continentTitle));
    	browser.get(baseUrl+"/overview/");
    	waitUntilPageLoaded();
    	page.getListOfContinents().get(0).getProgressButtonList().get(0).clickButton();
    	waitUntilPageLoaded();
    	assertTrue(browser.findElement(By.className("practice")).isDisplayed());
   	}
    
    
    @Test
    public void testStates() throws InterruptedException{
    	page.getListOfStates().get(0).getProgressBar().mouseOverProgressBar();
    	assertTrue(page.getListOfStates().get(0).isTooltipDisplayed());
        
    	for(ProgressButton place: page.getListOfStates().get(0).getProgressButtonList()){ 
    		place.getProgressBar().mouseOverProgressBar();
    		assertTrue(page.getWorld().isTooltipDisplayed());
    	}
    	
    	String stateTitle = page.getListOfStates().get(0).getPlaceTitle();
        page.getListOfStates().get(0).clickViewPlace();
        assertTrue(browser.findElement(By.xpath("//*[@id='ng-view']/div[1]/h1")).getText().equals(stateTitle));
    	guardHttp(browser).get(baseUrl+"/overview/");
    	waitUntilPageLoaded();
    	page.getListOfStates().get(0).getProgressButtonList().get(0).clickButton();
    	waitUntilPageLoaded();
    	assertTrue(browser.findElement(By.className("practice")).isDisplayed());
    }
}
