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
	public void getWebpage(){
    	browser.get(baseUrl+"/view/world/");
	}
	
    @Test
    public void testButtons() throws InterruptedException {
    	page.getWorldMap().clickAvgKnowledgeButton();
    	waitUntilPageLoaded();
    	page.getWorldMap().clickMyKnowledgeButton();
    	waitUntilPageLoaded();
    	
    	page.getWorldMap().clickZoomInButton();
    	page.getWorldMap().clickZoomOutButton();
    }
    
    @Test
    public void testPoliticalMap() throws InterruptedException {
    	
    	Thread.sleep(3000);    	
    	page.getWorldMap().mouseOverSpecificPlace("Tokyo", page.getWorldMap().getListOfCities());
    	Thread.sleep(2000);
        assertTrue(page.getWorldMap().getTooltip().getTitle().equals("Tokyo"));
    	System.out.println(page.getWorldMap().getTooltip().getTitle());
    	
    	page.getWorldMap().mouseOverSpecificPlace("Greenland", page.getWorldMap().getListOfStates());
    	Thread.sleep(2000);
    	System.out.println(page.getWorldMap().getTooltip().getTitle());
        assertTrue(page.getWorldMap().getTooltip().getTitle().equals("Greenland"));
    }
     
     @Test
     public void testWater() throws InterruptedException {

    	Thread.sleep(3000);
    	page.getMapControl().clickWaterTab();
    	Thread.sleep(3000);
    	page.getWorldMap().mouseOverSpecificPlace("Lake Victoria", page.getWorldMap().getListOfLakes());
    	Thread.sleep(2000);
    	System.out.println(page.getWorldMap().getTooltip().getTitle());
        assertTrue(page.getWorldMap().getTooltip().getTitle().equals("Lake Victoria"));
    }
     
     @Test
     public void testSurface() throws InterruptedException {
    	Thread.sleep(3000);
    	page.getMapControl().clickSurfaceTab();
    	
    	Thread.sleep(3000);
    	page.getWorldMap().mouseOverSpecificPlace("Ethiopian Highlands", page.getWorldMap().getListOfMountains());
    	Thread.sleep(2000);
    	System.out.println(page.getWorldMap().getTooltip().getTitle());
        assertTrue(page.getWorldMap().getTooltip().getTitleText().equalsIgnoreCase("Ethiopian Highlands"));

        page.getWorldMap().mouseOverSpecificPlace("Iceland", page.getWorldMap().getListOfIslands());
    	Thread.sleep(2000);
    	System.out.println(page.getWorldMap().getTooltip().getTitle());
    	assertTrue(page.getWorldMap().getTooltip().getTitleText().equalsIgnoreCase("Iceland"));      
    }
}
