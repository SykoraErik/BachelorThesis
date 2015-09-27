package cz.muni.proso.geography.page;

import org.openqa.selenium.support.FindBy;

import cz.muni.proso.geography.fragment.MapControl;
import cz.muni.proso.geography.fragment.OverviewMap;
import cz.muni.proso.geography.fragment.ProgressBarTooltip;

public class WorldMap extends CommonPageFragments{

	@FindBy(xpath = "/html/body")
	private OverviewMap worldMap;

	@FindBy(id = "container")
	private MapControl mapControl;

	@FindBy(xpath = "//*[@role='tooltip']")
	private ProgressBarTooltip progBarTooltip;

	public OverviewMap getWorldMap() {
		return worldMap;
	}

	public MapControl getMapControl() {
		return mapControl;
	}

	public ProgressBarTooltip getProgBarTooltip() {
		return progBarTooltip;
	}
}