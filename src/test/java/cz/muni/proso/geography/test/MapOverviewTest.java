package cz.muni.proso.geography.test;

import static org.jboss.arquillian.graphene.Graphene.guardHttp;
import static org.junit.Assert.assertTrue;

import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;

import cz.muni.proso.geography.fragment.ProgressButton;
import cz.muni.proso.geography.page.MapOverview;

@RunWith(Arquillian.class)
public class MapOverviewTest extends TestUtilityClass {

	@Page
	private MapOverview page;

	@Before
	public void openPage() {
		browser.get(BASE_URL + "/overview/");
		if (!page.getNavMenu().isUserLoggedIn()) {
			page.getNavMenu().clickLogin();
			page.getLogin().loginWithEmail(USERNAME, PASSWORD);
			browser.get(BASE_URL + "/overview/");
		}
		waitUntilPageLoaded();
	}

	@Test
	public void testWorld() {
		page.getWorld().getProgressBar().mouseOver();
		assertTrue(page.getTooltip().isDisplayed());
		assertTrue(Math.abs(page.getTooltip().learnedPercentage()
				- page.getWorld().getProgressBar().getLearnedBarWidth()) < 0.001);

		for (ProgressButton place : page.getWorld().getProgressButtonList()) {
			place.getProgressBar().mouseOver();
			assertTrue(page.getTooltip().isDisplayed());
			assertTrue(Math.abs(page.getTooltip().learnedPercentage()
					- place.getProgressBar().getLearnedBarWidth()) < 0.001);
		}

		String placeTitle = page.getWorld().getPlaceTitle();
		page.getWorld().clickViewPlace();
		assertTrue(browser
				.findElement(By.xpath("//*[@id='ng-view']/div[1]/h1"))
				.getText().equals(placeTitle));

		browser.get(BASE_URL + "/overview/");
		waitUntilPageLoaded();
		page.getWorld().getProgressButtonList().get(0).clickButton();
		waitUntilPageLoaded();
		assertTrue(browser.findElement(By.className("practice")).isDisplayed());
	}

	@Test
	public void testContinents() {
		page.getListOfContinents().get(0).getProgressBar().mouseOver();
		assertTrue(page.getTooltip().isDisplayed());

		for (ProgressButton place : page.getListOfContinents().get(0)
				.getProgressButtonList()) {
			place.getProgressBar().mouseOver();
			assertTrue(page.getTooltip().isDisplayed());
		}

		String continentTitle = page.getListOfContinents().get(0)
				.getPlaceTitle();
		page.getListOfContinents().get(0).clickViewPlace();
		assertTrue(browser
				.findElement(By.xpath("//*[@id='ng-view']/div[1]/h1"))
				.getText().equals(continentTitle));
		browser.get(BASE_URL + "/overview/");
		waitUntilPageLoaded();
		page.getListOfContinents().get(0).getProgressButtonList().get(0)
				.clickButton();
		waitUntilPageLoaded();
		assertTrue(browser.findElement(By.className("practice")).isDisplayed());
	}

	@Test
	public void testStates() {
		page.getListOfStates().get(0).getProgressBar().mouseOver();
		assertTrue(page.getTooltip().isDisplayed());

		for (ProgressButton place : page.getListOfStates().get(0)
				.getProgressButtonList()) {
			place.getProgressBar().mouseOver();
			assertTrue(page.getTooltip().isDisplayed());
		}

		String stateTitle = page.getListOfStates().get(0).getPlaceTitle();
		page.getListOfStates().get(0).clickViewPlace();
		assertTrue(browser
				.findElement(By.xpath("//*[@id='ng-view']/div[1]/h1"))
				.getText().equals(stateTitle));
		guardHttp(browser).get(BASE_URL + "/overview/");
		waitUntilPageLoaded();
		page.getListOfStates().get(0).getProgressButtonList().get(0)
				.clickButton();
		waitUntilPageLoaded();
		assertTrue(browser.findElement(By.className("practice")).isDisplayed());
	}
}
