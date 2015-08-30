package cz.muni.proso.geography.test;

import java.util.concurrent.TimeUnit;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class MyTestClass {
	
	protected String baseUrl = System.getProperty("baseUrl");
	protected String password = System.getProperty("password");
	protected String username = System.getProperty("username");
	protected String email = System.getProperty("email");
	protected String facebookEmail = System.getProperty("facebookEmail");
	protected String facebookPassword = System.getProperty("facebookPassword");
	protected String googleUsername = System.getProperty("googleUsername");
	protected String googlePassword = System.getProperty("googlePassword");
	
    @Drone
	protected WebDriver browser;
    
    @FindBy(className = "loading-indicator")
    private WebElement loadingIndicator;
    
    public void waitUntilPageLoaded(){
    	if(loadingIndicator.isDisplayed()){
    		Graphene.waitModel().withTimeout(15, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS).until().element(loadingIndicator).is().not().visible();
    	}
    }
}
