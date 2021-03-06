package cz.muni.proso.geography.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import cz.muni.proso.geography.fragment.AlertMessage;
import cz.muni.proso.geography.page.WorldMap;

@RunWith(Arquillian.class)
public class WorldMapTest extends TestUtilityClass {

	@Page
	private WorldMap page;

	@FindBy(className = "bottom-alert")
	private AlertMessage alert;

	@Before
	public void getWebpage() {
		browser.get(BASE_URL + "/view/world/average");

		if (!page.getNavMenu().getActiveLanguage().equals("en")) {
			page.getNavMenu().switchLanguage("en");
		}

		if (alert.isDisplayed()) {
			alert.closeAlert();
		}
		
		waitUntilPageLoaded();
	}

	@Test
	public void testZoom() {
		String placeToTest = "Australia";

		int heightBefore = page.getWorldMap().getPlaceSize(placeToTest)
				.getHeight();

		for (int i = 0; i < 5; i++) {
			page.getWorldMap().zoomIn();
		}

		int heightAfter = page.getWorldMap().getPlaceSize(placeToTest)
				.getHeight();
		assertTrue(heightBefore < heightAfter);

		heightBefore = heightAfter;
		for (int i = 0; i < 5; i++) {
			page.getWorldMap().zoomOut();
		}
		heightAfter = page.getWorldMap().getPlaceSize(placeToTest).getHeight();
		assertTrue(heightAfter < heightBefore);
	}

	@Test
	public void testMapKnowledge() {
		String placeToTest = "Greenland";

		page.getMapControl().showPoliticalTab();
		page.getWorldMap().mouseOverPlace(placeToTest);

		assertTrue(page.getWorldMap().getTooltip().getTitle()
				.equals(placeToTest));
		assertTrue(page.getWorldMap().getKnowledgeEstimate(placeToTest) == (page
				.getWorldMap().getTooltip().getKnowledgeEstimate()));
		assertTrue(page.getMapControl().getStates()
				.isListItemDisplayed(placeToTest));
		assertTrue(page.getMapControl().getStates()
				.getListItemColour(placeToTest)
				.equals(page.getWorldMap().getPlaceColour(placeToTest)));
		assertTrue(page.getWorldMap().compareKnowledgeEstimate(placeToTest,
				"Guinea") == 1);

		for (WebElement state : page.getWorldMap().getListOf("state")) {
			assertFalse(state.getAttribute("fill").equals("#ffffff"));
		}
	}

	@Test
	public void testMapControl(){
		page.getMapControl().showPoliticalTab();
		page.getMapControl().getCities().getProgressBar().mouseOver();
		assertTrue(Math.abs(page.getProgBarTooltip().learnedPercentage()
				- page.getMapControl().getCities().getProgressBar()
						.getLearnedBarWidth()) < 0.001);
		assertTrue(Math.abs(page.getProgBarTooltip().practicedPercentage()
				- page.getMapControl().getCities().getProgressBar()
						.getPracticedBarWidth()) < 0.001);

		page.getMapControl().getCities().practice();
		waitUntilPageLoaded();
		assertTrue(browser.findElement(By.className("practice")).isDisplayed());
	}

	@Test
	public void testMapControlToggle(){
		final String placeToTest = "Cairo";

		page.getMapControl().showPoliticalTab();
		page.getMapControl().getCities().toggleContext();
		assertFalse(page.getWorldMap().isPlaceDisplayed(placeToTest));
		assertFalse(page.getMapControl().getCities()
				.isListItemDisplayed(placeToTest));

		page.getMapControl().getCities().toggleContext();
		assertTrue(page.getWorldMap().isPlaceDisplayed(placeToTest));
		assertTrue(page.getMapControl().getCities()
				.isListItemDisplayed(placeToTest));
	}
	
	@Test
	public void testTabSwitching() {
		String placeToTest = "Lake Victoria";

		page.getMapControl().showWaterTab();
		page.getWorldMap().mouseOverPlace(placeToTest);
		assertTrue(page.getWorldMap().isPlaceDisplayed(placeToTest));
		assertTrue(page.getWorldMap().getTooltip().getTitle()
				.equalsIgnoreCase(placeToTest));

		placeToTest = "ICELAND";

		page.getMapControl().showSurfaceTab();
		page.getWorldMap().mouseOverPlace(placeToTest);
		assertTrue(page.getWorldMap().isPlaceDisplayed(placeToTest));
		assertTrue(page.getWorldMap().getTooltip().getTitle()
				.equalsIgnoreCase(placeToTest));
	}
}