package cz.muni.proso.geography.page;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import cz.muni.proso.geography.fragment.Practice;
import cz.muni.proso.geography.fragment.PracticeFeedback;
import cz.muni.proso.geography.fragment.PracticeMap;

public class PracticePage extends CommonPageFragments {

	@Drone
	private WebDriver browser;

	@FindBy(xpath = "//*[@id='ng-view']")
	private PracticeMap map;

	@FindBy(id = "container")
	private Practice practice;

	@FindBy(className = "modal-content")
	private PracticeFeedback practiceFeedback;

	public PracticeMap getMap() {
		return map;
	}

	public Practice getPractice() {
		return practice;
	}

	public PracticeFeedback getPracticeFeedback() {
		return practiceFeedback;
	}
}
