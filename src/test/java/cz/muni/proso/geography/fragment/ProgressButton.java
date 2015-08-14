package cz.muni.proso.geography.fragment;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.fragment.Root;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProgressButton {

	@Root
	private WebElement root;
	
	@Drone
	private WebDriver browser;

	@FindBy(xpath ="./a")
	private WebElement button;
	
	@FindBy(xpath = "./div")
	private WebElement progressBar;
	
	public WebElement getRoot() {
		return root;
	}

	public WebElement getButton() {
		return button;
	}

	public WebElement getProgressBar() {
		return progressBar;
	}
}
