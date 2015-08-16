package cz.muni.proso.geography.test;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.jboss.arquillian.graphene.*;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.junit.Arquillian;

import cz.muni.proso.geography.fragment.Feedback;
import cz.muni.proso.geography.page.Home;

@RunWith(Arquillian.class)
public class FeedbackTest {
	
    @Drone
    private WebDriver browser;
    
    @FindBy(className = "modal-content")
	private Feedback feedbackFragment;
	
    @FindBy(id = "feedback-btn")
	private WebElement feedbackButton;
    
    @Test
    public void testSendFeedback(@InitialPage Home home){
    	Graphene.waitGui().until().element(feedbackButton).is().present();
    	feedbackButton.click();
    	Graphene.waitGui().until().element(feedbackFragment.getFeedbackTextBox()).is().present();
    	feedbackFragment.sendFeedback("graphene-test", "outlinemaps.test@gmail.com");	
    }
    
    @Test
    public void testCloseFeedback(@InitialPage Home home){
    	Graphene.waitGui().until().element(feedbackButton).is().present();
    	feedbackButton.click();
    	Graphene.waitGui().until().element(feedbackFragment.getCloseFeedbackButton()).is().present();
    	feedbackFragment.clickCloseFeedbackButton();
    	Graphene.waitGui().until().element(feedbackButton).is().visible();
    	
    	assertFalse(feedbackFragment.getFeedbackTextBox().isPresent());
    }
    
    @Test
    public void testErrorDisplay(@InitialPage Home home){
    	Graphene.waitGui().until().element(feedbackButton).is().present();
    	feedbackButton.click();
    	Graphene.waitGui().until().element(feedbackFragment.getFeedbackTextBox()).is().present();
    	feedbackFragment.setFeedbackText("");
    	feedbackFragment.clickSendFeedbackButton();
    	Graphene.waitGui().until().element(feedbackFragment.getErrorMessage()).is().present();
    	
    	assertTrue(feedbackFragment.getErrorMessage().isPresent());
    	feedbackFragment.clickErrorMessageCloseButton();
    	assertFalse(feedbackFragment.getErrorMessage().isPresent());    	    	
    }
}
