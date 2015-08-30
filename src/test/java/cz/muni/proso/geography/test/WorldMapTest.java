package cz.muni.proso.geography.test;

import static org.junit.Assert.assertTrue;

import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import cz.muni.proso.geography.page.WorldMap;

@RunWith(Arquillian.class)
public class WorldMapTest extends MyTestClass{
	
	@Page
	private WorldMap page;

	@Before
	public void getWebpage() throws InterruptedException{
	browser.get(baseUrl+"/view/world/average");
    	waitUntilPageLoaded();
    	page.getWorldMap().clickAvgKnowledgeButton();
    	waitUntilPageLoaded();
	}
	
    @Test
    public void testButtons() throws InterruptedException {
    	page.getWorldMap().clickMyKnowledgeButton();
    	waitUntilPageLoaded();
    	page.getWorldMap().clickAvgKnowledgeButton();
    	waitUntilPageLoaded();
    	
    	page.getWorldMap().clickZoomInButton();
    	page.getWorldMap().clickZoomOutButton();
    }
    
    @Test
    public void testPoliticalMap() throws InterruptedException {
    	page.getMapControl().clickPoliticalTab();
    	page.getWorldMap().mouseOverSpecificPlace("Tokyo", page.getWorldMap().getListOfCities());
    	Thread.sleep(1000);
        assertTrue(page.getWorldMap().getTooltip().getTitleText().equals("Tokyo"));
    	page.getWorldMap().mouseOverSpecificPlace("Greenland", page.getWorldMap().getListOfStates());
    	Thread.sleep(1000);
        assertTrue(page.getWorldMap().getTooltip().getTitleText().equals("Greenland"));
    }
     
     @Test
     public void testWater() throws InterruptedException {
    	page.getMapControl().clickWaterTab();
    	page.getWorldMap().mouseOverSpecificPlace("Lake Victoria", page.getWorldMap().getListOfLakes());
    	Thread.sleep(1000);
        assertTrue(page.getWorldMap().getTooltip().getTitleText().equals("Lake Victoria"));
    }
     
     @Test
     public void testSurface() throws InterruptedException {
    	page.getMapControl().clickSurfaceTab();
    	page.getWorldMap().mouseOverSpecificPlace("Ethiopian Highlands", page.getWorldMap().getListOfMountains());
    	Thread.sleep(1000);
        assertTrue(page.getWorldMap().getTooltip().getTitleText().equalsIgnoreCase("Ethiopian Highlands"));
        page.getWorldMap().mouseOverSpecificPlace("Iceland", page.getWorldMap().getListOfIslands());
    	Thread.sleep(1000);
    	assertTrue(page.getWorldMap().getTooltip().getTitleText().equalsIgnoreCase("Iceland"));     
    }
}
