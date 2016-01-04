package cz.muni.proso.geography.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cz.muni.proso.geography.page.WorldMap;

@RunWith(Arquillian.class)
public class NavigationMenuTest extends TestUtilityClass {

	@Page
	private WorldMap page;

	@Before
	public void openPage() {
		browser.get(BASE_URL);
		if (!page.getNavMenu().getActiveLanguage().equals("en")) {
			page.getNavMenu().switchLanguage("en");
		}

	}

	@Test
	public void testButtons() {

		page.getNavMenu().clickWorld();
		waitUntilPageLoaded();
		assertTrue(page.getWorldMap().getMapName().equals("World"));

		page.getNavMenu().clickHome();
		waitUntilPageLoaded();
		assertTrue(page.getWorldMap().getMapName().equals("Outline maps"));

		page.getNavMenu().clickMapOverview();
		waitUntilPageLoaded();
		assertTrue(page.getWorldMap().getMapName().equals("Maps overview"));

		page.getNavMenu().clickLogin();
		assertTrue(page.getLogin().isLoginFormPresent());
	}

	@Test
	public void testContinents() {
		for (WebElement continent : page.getNavMenu().getListOfContinents()) {
			page.getNavMenu().clickContinents();
			String continentName = continent.getText();
			continent.click();
			waitUntilPageLoaded();
			assertEquals(continentName, page.getWorldMap().getMapName());
		}
	}

	@Test
	public void testStates() {
		for (WebElement state : page.getNavMenu().getListOfStates()) {
			page.getNavMenu().clickStates();
			String stateName = state.getText();
			state.click();
			waitUntilPageLoaded();
			if (stateName.equals("United States")) {
				assertEquals("U.S.", page.getWorldMap().getMapName());
			} else {
				assertEquals(stateName, page.getWorldMap().getMapName());
			}
		}
	}

	@Test
	public void testLanguages() {
		for (WebElement language : page.getNavMenu().getListOfLanguages()) {
			page.getNavMenu().clickLanguages();
			String activeLang = language.findElement(By.xpath("./i"))
					.getAttribute("class").substring(5);
			language.click();
			assertEquals(activeLang, page.getNavMenu().getActiveLanguage());
		}
	}
}