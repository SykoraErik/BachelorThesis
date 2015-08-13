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
    public void testSendFeedback(@InitialPage Home home) throws InterruptedException{
    	Graphene.waitGui().until().element(feedbackButton).is().present();
    	feedbackButton.click();
    	Graphene.waitGui().until().element(feedbackFragment.getFeedbackTextBox()).is().present();
    	feedbackFragment.sendFeedback("graphene-test", "");	
    }
    
    @Test
    public void testCloseFeedback(@InitialPage Home home) throws InterruptedException{
    	Graphene.waitGui().until().element(feedbackButton).is().present();
    	feedbackButton.click();
    	Graphene.waitGui().until().element(feedbackFragment.getCloseFeedbackButton()).is().present();
    	feedbackFragment.clickCloseFeedbackButton();
    	Graphene.waitGui().until().element(feedbackButton).is().visible();
    	
    	try{
    	if(feedbackFragment.getFeedbackTextBox().isDisplayed()){
    		throw new InterruptedException("The feedback form is still open");
    		}
    	}catch(NoSuchElementException e){
    		//if the fragment root is not present, the test passes
    	}
    	
    }
    
    @Test
    public void testErrorDisplay(@InitialPage Home home) throws InterruptedException{
    	Graphene.waitGui().until().element(feedbackButton).is().present();
    	feedbackButton.click();
    	Graphene.waitGui().until().element(feedbackFragment.getFeedbackTextBox()).is().present();
    	feedbackFragment.setFeedbackText("");
    	feedbackFragment.clickSendFeedbackButton();
    	Graphene.waitGui().until().element(feedbackFragment.getErrorMessage()).is().present();
    	
    	if(!feedbackFragment.getErrorMessage().isDisplayed()){
    		throw new InterruptedException("The error message was not displayed");
    	}
    	try{
    	feedbackFragment.clickErrorMessageCloseButton();
    	
    	if(feedbackFragment.getErrorMessage().isDisplayed()){
    		throw new InterruptedException("The error message was not closed after clicking the close button");    	}
    	} catch(NoSuchElementException e){
    		//if the element is not present, test passes
    	}
    	
    	
    }
}
