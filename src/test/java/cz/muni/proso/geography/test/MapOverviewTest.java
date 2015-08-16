package cz.muni.proso.geography.test;

import static org.junit.Assert.*;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import cz.muni.proso.geography.page.MapOverview;

@RunWith(Arquillian.class)
public class MapOverviewTest {

    @Drone
    private WebDriver browser;
    
    @Test
    public void testViewWorld(@InitialPage MapOverview page){
    	Graphene.waitModel().until().element(page.getWorld().getViewPlace()).is().visible();
    	page.getWorld().getViewPlace().click();
    	assertEquals("http://slepemapy.cz/view/world/", browser.getCurrentUrl());
    }
	
    @Test
    public void testWorldStates(@InitialPage MapOverview page){
    	Graphene.waitModel().until().element(page.getWorld().getFirstProgressButton().getButton()).is().visible();
    	page.getWorld().getFirstProgressButton().getButton().click();
    	assertEquals("http://slepemapy.cz/practice/world/state", browser.getCurrentUrl());
    }
    
    @Test
    public void testWorldCities(@InitialPage MapOverview page){
    	Graphene.waitModel().until().element(page.getWorld().getSecondProgressButton().getButton()).is().visible();
    	page.getWorld().getSecondProgressButton().getButton().click();
    	assertEquals("http://slepemapy.cz/practice/world/city", browser.getCurrentUrl());
    }
    
    @Test
    public void testWorldRivers(@InitialPage MapOverview page){
    	Graphene.waitModel().until().element(page.getWorld().getThirdProgressButton().getButton()).is().visible();
    	page.getWorld().getThirdProgressButton().getButton().click();
    	assertEquals("http://slepemapy.cz/practice/world/river", browser.getCurrentUrl());
    }
    
    @Test
    public void testWorldLakes(@InitialPage MapOverview page){
    	Graphene.waitModel().until().element(page.getWorld().getFourthProgressButton().getButton()).is().visible();
    	page.getWorld().getFourthProgressButton().getButton().click();
    	assertEquals("http://slepemapy.cz/practice/world/lake", browser.getCurrentUrl());
    }
    
    @Test
    public void testWorldMountains(@InitialPage MapOverview page){
    	Graphene.waitModel().until().element(page.getWorld().getFifthProgressButton().getButton()).is().visible();
    	page.getWorld().getFifthProgressButton().getButton().click();
    	assertEquals("http://slepemapy.cz/practice/world/mountains", browser.getCurrentUrl());
    }
    
    @Test
    public void testWorldIslands(@InitialPage MapOverview page){
    	Graphene.waitModel().until().element(page.getWorld().getSixthProgressButton().getButton()).is().visible();
    	page.getWorld().getSixthProgressButton().getButton().click();
    	assertEquals("http://slepemapy.cz/practice/world/island", browser.getCurrentUrl());
    }
    
    @Test
    public void testViewAfrica(@InitialPage MapOverview page){
    	Graphene.waitModel().until().element(page.getAfrica().getViewPlace()).is().visible();
    	page.getAfrica().getViewPlace().click();
    	assertEquals("http://slepemapy.cz/view/africa/", browser.getCurrentUrl());
    }
    
    @Test
    public void testAfricaStates(@InitialPage MapOverview page){
    	Graphene.waitModel().until().element(page.getAfrica().getFirstProgressButton().getButton()).is().visible();
    	page.getAfrica().getFirstProgressButton().getButton().click();
    	assertEquals("http://slepemapy.cz/practice/africa/state", browser.getCurrentUrl());
    }
    
    @Test
    public void testAfricaCities(@InitialPage MapOverview page){
    	Graphene.waitModel().until().element(page.getAfrica().getSecondProgressButton().getButton()).is().visible();
    	page.getAfrica().getSecondProgressButton().getButton().click();
    	assertEquals("http://slepemapy.cz/practice/africa/city", browser.getCurrentUrl());
    }
    
    @Test
    public void testAfricaRivers(@InitialPage MapOverview page){
    	Graphene.waitModel().until().element(page.getAfrica().getThirdProgressButton().getButton()).is().visible();
    	page.getAfrica().getThirdProgressButton().getButton().click();
    	assertEquals("http://slepemapy.cz/practice/africa/river", browser.getCurrentUrl());
    }
}
