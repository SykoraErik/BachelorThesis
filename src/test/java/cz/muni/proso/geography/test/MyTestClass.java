package cz.muni.proso.geography.test;

import java.util.concurrent.TimeUnit;
import java.util.Random;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class TestUtilityClass {

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

	private Random rng = new Random();
	
	public void waitUntilPageLoaded() {
		if (loadingIndicator.isDisplayed()) {
			Graphene.waitModel().withTimeout(15, TimeUnit.SECONDS)
					.pollingEvery(1, TimeUnit.SECONDS).until()
					.element(loadingIndicator).is().not().visible();
		}
	}
	
	public String randomizeEmail(String email){
		int atSignPosition = email.indexOf('@');
		String prefix = email.substring(0, atSignPosition);
		return prefix + rng.nextInt() + email.substring(atSignPosition, email.length());
	}
	
	public String randomizeUsername(String username){
		return username + rng.nextInt();
	}
}
