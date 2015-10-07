package cz.muni.proso.geography.page;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import cz.muni.proso.geography.fragment.Practice;
import cz.muni.proso.geography.fragment.PracticeMap;

public class PracticePage extends CommonPageFragments {

	@Drone
	private WebDriver browser;

	@FindBy(xpath = "//*[@id='ng-view']")
	private PracticeMap map;

	@FindBy(id = "container")
	private Practice practice;

	public PracticeMap getMap() {
		return map;
	}

	public Practice getPractice() {
		return practice;
	}

}
