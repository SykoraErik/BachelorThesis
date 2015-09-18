package cz.muni.proso.geography.test;

import java.util.concurrent.TimeUnit;
import java.util.Random;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class TestUtilityClass {

	public final static String BASE_URL = System.getProperty("baseUrl");
	public final static String PASSWORD = System.getProperty("password");
	public final static String USERNAME = System.getProperty("username");
	public final static String EMAIL = System.getProperty("email");
	public final static String FACEBOOK_EMAIL = System
			.getProperty("facebookEmail");
	public final static String FACEBOOK_PASSWORD = System
			.getProperty("facebookPassword");
	public final static String GOOGLE_USERNAME = System
			.getProperty("googleUsername");
	public final static String GOOGLE_PASSWORD = System
			.getProperty("googlePassword");

	@Drone
	protected WebDriver browser;

	@FindBy(className = "loading-indicator")
	private WebElement loadingIndicator;

	public void waitUntilPageLoaded() {
		if (loadingIndicator.isDisplayed()) {
			Graphene.waitModel().withTimeout(15, TimeUnit.SECONDS)
					.pollingEvery(1, TimeUnit.SECONDS).until()
					.element(loadingIndicator).is().not().visible();
		}
	}

	public String randomizeEmail(String email) {
		Random rng = new Random();
		int atSignPosition = email.indexOf('@');
		String prefix = email.substring(0, atSignPosition);
		return prefix + rng.nextInt()
				+ email.substring(atSignPosition, email.length());
	}

	public String randomizeUsername(String username) {
		Random rng = new Random();
		return username + rng.nextInt();
	}
}