package cz.muni.proso.geography.fragment;

import java.util.concurrent.TimeUnit;

import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MapControl {

	@FindBy(css = "#container > div.layers-tabs > div:nth-child(1)")
	private WebElement politicalMapTab;

	@FindBy(xpath = "//*[@id='container']/ul[1]/div/li[1]")
	private MapControlContext states;

	@FindBy(xpath = "//*[@id='container']/ul[1]/div/li[2]")
	private MapControlContext cities;

	@FindBy(css = "#container > div.layers-tabs > div:nth-child(2)")
	private WebElement waterTab;

	@FindBy(xpath = "//*[@id='container']/ul[2]/div/li[1]")
	private MapControlContext rivers;

	@FindBy(xpath = "//*[@id='container']/ul[2]/div/li[2]")
	private MapControlContext lakes;

	@FindBy(css = "#container > div.layers-tabs > div:nth-child(3)")
	private WebElement surfaceTab;

	@FindBy(xpath = "//*[@id='container']/ul[3]/div/li[1]")
	private MapControlContext mountains;

	@FindBy(xpath = "//*[@id='container']/ul[3]/div/li[2]")
	private MapControlContext islands;

	public MapControlContext getStates() {
		return states;
	}

	public MapControlContext getCities() {
		return cities;
	}

	public MapControlContext getRivers() {
		return rivers;
	}

	public MapControlContext getLakes() {
		return lakes;
	}

	public MapControlContext getMountains() {
		return mountains;
	}

	public MapControlContext getIslands() {
		return islands;
	}

	public void showPoliticalTab() {
		Graphene.waitModel().withTimeout(10, TimeUnit.SECONDS).until().element(politicalMapTab).is().visible();
		politicalMapTab.click();
	}

	public void showWaterTab() {
		Graphene.waitModel().until().element(waterTab).is().visible();
		waterTab.click();
	}

	public void showSurfaceTab() {
		Graphene.waitModel().until().element(surfaceTab).is().visible();
		surfaceTab.click();
	}
}