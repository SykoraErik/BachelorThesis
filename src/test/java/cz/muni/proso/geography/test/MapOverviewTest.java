package cz.muni.proso.geography.test;

import static org.junit.Assert.assertTrue;

import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import cz.muni.proso.geography.fragment.AlertMessage;
import cz.muni.proso.geography.fragment.ProgressButton;
import cz.muni.proso.geography.page.MapOverview;
import cz.muni.proso.geography.page.WorldMap;

@RunWith(Arquillian.class)
public class MapOverviewTest extends TestUtilityClass {

	@Page
	private MapOverview mapOverviewPage;

	@Page
	private WorldMap worldPage;

	@FindBy(className = "bottom-alert")
	private AlertMessage alert;

	@Before
	public void openPage() {
		browser.get(BASE_URL + "/overview/");
		if (!mapOverviewPage.getNavMenu().isUserLoggedIn()) {
			mapOverviewPage.getNavMenu().clickLogin();
			mapOverviewPage.getLogin().loginWithEmail(USERNAME, PASSWORD);
		}
		browser.get(BASE_URL + "/overview/");
		waitUntilPageLoaded();

		if (alert.isDisplayed()) {
			alert.closeAlert();
		}
	}

	@Test
	public void testWorld() {
		mapOverviewPage.getWorld().getProgressBar().mouseOver();
		assertTrue(mapOverviewPage.getTooltip().isDisplayed());
		assertTrue(Math.abs(mapOverviewPage.getTooltip().learnedPercentage()
				- mapOverviewPage.getWorld().getProgressBar()
						.getLearnedBarWidth()) < 0.001);

		for (ProgressButton place : mapOverviewPage.getWorld()
				.getProgressButtonList()) {
			place.getProgressBar().mouseOver();
			assertTrue(mapOverviewPage.getTooltip().isDisplayed());
			assertTrue(Math.abs(mapOverviewPage.getTooltip()
					.learnedPercentage()
					- place.getProgressBar().getLearnedBarWidth()) < 0.001);
		}

		String placeTitle = mapOverviewPage.getWorld().getPlaceTitle();
		mapOverviewPage.getWorld().clickViewPlace();
		assertTrue(worldPage.getWorldMap().getMapName().equals(placeTitle));

		browser.get(BASE_URL + "/overview/");
		waitUntilPageLoaded();
		mapOverviewPage.getWorld().getProgressButtonList().get(0).clickButton();
		waitUntilPageLoaded();
		assertTrue(browser.findElement(By.className("practice")).isDisplayed());
	}

	@Test
	public void testContinents() {
		mapOverviewPage.getListOfContinents().get(0).getProgressBar()
				.mouseOver();
		assertTrue(mapOverviewPage.getTooltip().isDisplayed());

		for (ProgressButton place : mapOverviewPage.getListOfContinents()
				.get(0).getProgressButtonList()) {
			place.getProgressBar().mouseOver();
			assertTrue(mapOverviewPage.getTooltip().isDisplayed());
		}

		String continentTitle = mapOverviewPage.getListOfContinents().get(0)
				.getPlaceTitle();
		mapOverviewPage.getListOfContinents().get(0).clickViewPlace();
		assertTrue(worldPage.getWorldMap().getMapName().equals(continentTitle));

		browser.get(BASE_URL + "/overview/");
		waitUntilPageLoaded();
		mapOverviewPage.getListOfContinents().get(0).getProgressButtonList()
				.get(0).clickButton();
		waitUntilPageLoaded();
		assertTrue(browser.findElement(By.className("practice")).isDisplayed());
	}

	@Test
	public void testStates() {
		mapOverviewPage.getListOfStates().get(0).getProgressBar().mouseOver();
		assertTrue(mapOverviewPage.getTooltip().isDisplayed());

		for (ProgressButton place : mapOverviewPage.getListOfStates().get(0)
				.getProgressButtonList()) {
			place.getProgressBar().mouseOver();
			assertTrue(mapOverviewPage.getTooltip().isDisplayed());
		}

		String stateTitle = mapOverviewPage.getListOfStates().get(0)
				.getPlaceTitle();
		mapOverviewPage.getListOfStates().get(0).clickViewPlace();
		assertTrue(worldPage.getWorldMap().getMapName().equals(stateTitle));

		browser.get(BASE_URL + "/overview/");
		waitUntilPageLoaded();
		mapOverviewPage.getListOfStates().get(0).getProgressButtonList().get(0)
				.clickButton();
		waitUntilPageLoaded();
		assertTrue(browser.findElement(By.className("practice")).isDisplayed());
	}
}