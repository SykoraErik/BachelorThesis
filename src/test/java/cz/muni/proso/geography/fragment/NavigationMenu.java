package cz.muni.proso.geography.fragment;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.google.common.base.Predicate;

public class NavigationMenu {

	@Drone
	private WebDriver browser;

	@FindBy(xpath = "//*[@id='wrap']/div[1]/div/a")
	private WebElement homeButton;

	@FindBy(xpath = "//*[@id='nav-main']/ul[1]/li[1]/a")
	private WebElement worldButton;

	@FindBy(xpath = "//*[@id='drop-continents']")
	private WebElement continentButton;

	@FindBy(xpath = "//*[@id='drop-states']")
	private WebElement stateButton;

	@FindBy(xpath = "//*[@id='nav-main']/ul[1]/li[4]/a")
	private WebElement mapOverviewButton;

	@FindBy(xpath = "//*[@id='nav-main']/ul[2]/li[1]/a")
	private WebElement signInButton;

	@FindBy(id = "drop1")
	private GrapheneElement loggedInButton;

	@FindBy(xpath = "//*[@id='nav-main']/ul[2]/li[2]/ul/li[1]/a")
	private WebElement myProfileButton;

	@FindBy(xpath = "//*[@id='nav-main']/ul[2]/li[2]/ul/li[2]/a")
	private WebElement signOutButton;

	@FindBy(css = "#nav-main > ul.nav.navbar-nav.pull-right > li:nth-child(3) > a")
	private WebElement languageButton;

	@FindBy(css = "ul#menu-list-contents > li > a")
	private List<WebElement> listOfContinents;

	@FindBy(css = "ul#menu-list-states > li > a")
	private List<WebElement> listOfStates;

	@FindBy(css = "ul#menu-list-languages > li > a")
	private List<WebElement> listOfLanguages;

	public List<WebElement> getListOfContinents() {
		return listOfContinents;
	}

	public List<WebElement> getListOfStates() {
		return listOfStates;
	}

	public List<WebElement> getListOfLanguages() {
		return listOfLanguages;
	}

	/**
	 * Clicks the navigation menu Outline maps button. Waits until the button is
	 * present on the page.
	 */
	public void clickHome() {
		Graphene.waitModel().until().element(homeButton).is().present();
		homeButton.click();
	}

	/**
	 * Clicks the navigation menu World button. Waits until the button is
	 * present on the page.
	 */
	public void clickWorld() {
		Graphene.waitModel().until().element(worldButton).is().present();
		worldButton.click();

		Graphene.waitModel().withTimeout(10, TimeUnit.SECONDS)
				.until(new Predicate<WebDriver>() {
					@Override
					public boolean apply(WebDriver browser) {
						return browser
								.findElement(
										By.xpath("//*[@id='ng-view']/div[1]/h1"))
								.getText().equals("World");
					}
				});
	}

	/**
	 * Clicks the navigation menu Continents button. Waits until the button is
	 * present on the page.
	 */
	public void clickContinents() {
		Graphene.waitModel().until().element(continentButton).is().present();
		continentButton.click();
	}

	/**
	 * Clicks the navigation menu States button. Waits until the button is
	 * present on the page.
	 */
	public void clickStates() {
		Graphene.waitModel().until().element(stateButton).is().present();
		stateButton.click();
	}

	/**
	 * Clicks the navigation menu Maps overview button. Waits until the button
	 * is present on the page.
	 */
	public void clickMapOverview() {
		Graphene.waitGui().until().element(mapOverviewButton).is().present();
		mapOverviewButton.click();
	}

	/**
	 * Clicks the navigation menu Login button. Waits until the button is
	 * present on the page. The login process is handled by {@link Login}
	 * fragment.
	 */
	public void clickLogin() {
		Graphene.waitModel().until().element(signInButton).is().visible();
		signInButton.click();

		Graphene.waitModel().until(new Predicate<WebDriver>() {
			@Override
			public boolean apply(WebDriver browser) {
				return browser.findElement(By.className("modal-content"))
						.isDisplayed();
			}
		});
	}

	/**
	 * Clicks the navigation menu Language button. Waits until the button is
	 * present on the page.
	 */
	public void clickLanguages() {
		Graphene.waitGui().until().element(languageButton).is().present();
		languageButton.click();
	}

	/**
	 * Clicks the navigation menu Logged in button. Waits until the button is
	 * present on the page. This button is visible only if there is a user
	 * logged in.
	 */
	public void clickLoggedIn() {
		Graphene.waitAjax().until().element(loggedInButton).is().visible();
		loggedInButton.click();
	}

	/**
	 * Signs out logged in user. This button is visible only if there is a user
	 * logged in. Clicks the logged in button to bring up the context menu and
	 * then clicks sign out button.
	 */
	public void signOut() {
		clickLoggedIn();
		Graphene.waitModel().until().element(signOutButton).is().visible();
		signOutButton.click();
		Graphene.waitModel().until().element(signInButton).is().visible();
	}

	/**
	 * Clicks the navigation menu My profile button. Waits until the button is
	 * present on the page. This button is visible only if there is a user
	 * logged in.
	 */
	public void clickMyProfile() {
		Graphene.waitGui().until().element(myProfileButton).is().visible();
		myProfileButton.click();
	}

	/**
	 * Returns <code>true</code> if user is logged in. This method is often used
	 * after a user logs in and the page refreshes, so it catches
	 * <code>StaleElementReferenceException</code> and finds new reference for
	 * the logged in button if necessary.
	 * 
	 * @return <code>true</code> if user is logged in. <code>false</code>
	 *         otherwise
	 */
	public boolean isUserLoggedIn() {
		try {
			return loggedInButton.isPresent() && loggedInButton.isDisplayed();
		} catch (StaleElementReferenceException ex) {
			WebElement freshReference = browser.findElement(By.id("drop1"));
			return freshReference.isDisplayed();
		}
	}

	/**
	 * Switches the currently active language to the specified language.
	 * 
	 * @param language
	 *            <code>String</code> containing abbreviation of language to
	 *            switch to: English - en, Czech - cs, Spanish - es.
	 */
	public void switchLanguage(String language) {
		clickLanguages();
		for (WebElement e : listOfLanguages) {
			if (e.findElement(By.xpath("./i")).getAttribute("class")
					.substring(5).equals(language)) {
				e.click();
				break;
			}
		}
	}

	/**
	 * Clicks a specific state in State dropdown menu
	 * 
	 * @param state
	 */
	public void clickSpecificState(String state) {
		for (WebElement e : listOfStates) {
			if (e.getText().equalsIgnoreCase(state)) {
				e.click();
				break;
			}
		}
	}

	/**
	 * Clicks a specific continent in Continent dropdown menu
	 * 
	 * @param continent
	 */
	public void clickSpecificContinent(String continent) {
		for (WebElement e : listOfContinents) {
			if (e.getText().equalsIgnoreCase(continent)) {
				e.click();
				break;
			}
		}
	}

	/**
	 * Returns <code>String</code> containing abbreviation of currently active
	 * language.
	 * 
	 * @return <code>String</code> containing abbreviation of currently active
	 *         language. Possible values: English - en, Czech - cs, Spanish -
	 *         es, Deutsch - de
	 */
	public String getActiveLanguage() {
		return languageButton.findElement(By.xpath("./i"))
				.getAttribute("class").substring(5);
	}
}