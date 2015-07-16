package org.arquillian.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;

import static org.jboss.arquillian.graphene.Graphene.guardAjax;

@RunWith(Arquillian.class)
public class LoginFragmentTest {
	
    @Drone
    private WebDriver browser;
    
    @Page
    private HomePageObject home;
	
    @Test
    public void testSearch(@InitialPage HomePageObject home) throws InterruptedException{
    	WebDriverWait wait = new WebDriverWait(browser, 5);
    	wait.until(ExpectedConditions.elementToBeClickable(home.getLoginButton()));
    	home.clickLogin().loginWithEmail("Erik", "pwd");
    	guardAjax(home);
    }
 
}
