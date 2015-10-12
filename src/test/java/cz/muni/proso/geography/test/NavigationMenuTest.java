package cz.muni.proso.geography.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import cz.muni.proso.geography.fragment.Login;
import cz.muni.proso.geography.fragment.NavigationMenu;

@RunWith(Arquillian.class)
public class NavigationMenuTest extends TestUtilityClass {

	@FindBy(css = "#wrap > div.navbar.navbar-inverse")
	private NavigationMenu navMenu;

	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div")
	private Login login;

	@Before
	public void openPage() {
		browser.get(BASE_URL);
		if (!navMenu.getActiveLanguage().equals("en")) {
			navMenu.switchLanguage("en");
		}
		if(navMenu.isUserLoggedIn()){
			navMenu.signOut();
		}
	}

	@Test
	public void testButtons(){

		navMenu.clickWorld();
		waitUntilPageLoaded();
		assertTrue((browser.findElement(
				By.xpath("//*[@id='ng-view']/div[1]/h1")).getText()
				.equals("World")));

		navMenu.clickHome();
		waitUntilPageLoaded();
		assertTrue((browser.findElement(
				By.xpath("//*[@id='ng-view']/div[1]/h1")).getText()
				.equals("Outline maps")));

		navMenu.clickMapOverview();
		waitUntilPageLoaded();
		assertTrue((browser.findElement(By.xpath("//*[@id='ng-view']/div/h1"))
				.getText().equals("Maps overview")));

		navMenu.clickLogin();
		assertTrue(login.isLoginFormPresent());
	}

	@Test
	public void testContinents() {
		for (WebElement continent : navMenu.getListOfContinents()) {
			navMenu.clickContinents();
			String continentName = continent.getText();
			continent.click();
			waitUntilPageLoaded();
			assertEquals(continentName, browser.findElement(By.xpath("//h1"))
					.getText());
		}
	}

	@Test
	public void testStates() {
		for (WebElement state : navMenu.getListOfStates()) {
			navMenu.clickStates();
			String stateName = state.getText();
			state.click();
			waitUntilPageLoaded();
			if (stateName.equals("United States")
					|| stateName.equals("Spojen� st�ty americk�")
					|| stateName.equals("Estados Unidos")) {
				assertEquals("U.S.", browser.findElement(By.xpath("//h1"))
						.getText());
			} else {
				assertEquals(stateName, browser.findElement(By.xpath("//h1"))
						.getText());
			}
		}
	}

	@Test
	public void testLanguages() {
		for (WebElement language : navMenu.getListOfLanguages()) {
			navMenu.clickLanguages();
			String activeLang = language.findElement(By.xpath("./i"))
					.getAttribute("class");
			language.click();
			assertEquals(
					activeLang,
					browser.findElement(
							By.xpath("//*[@id='nav-main']/ul[2]/li[3]/a/i"))
							.getAttribute("class"));
		}
	}
}