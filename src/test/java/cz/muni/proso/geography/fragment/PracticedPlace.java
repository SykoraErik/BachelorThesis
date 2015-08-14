package cz.muni.proso.geography.fragment;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.fragment.Root;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PracticedPlace {

	@Root
	private WebElement root;
	
	@Drone
	private WebDriver browser;

	@FindBy(xpath = "./h3/a")
	private WebElement viewPlace;
	
	@FindBy(xpath = "./h3/div")
	private WebElement progressBar;
	
	@FindBy(xpath = "./ul/li[1]")
	private ProgressButton firstProgressButton;
	
	@FindBy(xpath = "./ul/li[2]")
	private ProgressButton secondProgressButton;
	
	@FindBy(xpath = "./ul/li[3]")
	private ProgressButton thirdProgressButton;
	
	@FindBy(xpath = "./ul/li[4]")
	private ProgressButton fourthProgressButton;
	
	@FindBy(xpath = "./ul/li[5]")
	private ProgressButton fifthProgressButton;
	
	@FindBy(xpath = "./ul/li[6]")
	private ProgressButton sixthProgressButton;	
	
	
	public WebElement getRoot() {
		return root;
	}

	public WebElement getViewPlace() {
		return viewPlace;
	}

	public WebElement getProgressBar() {
		return progressBar;
	}

	public ProgressButton getFirstProgressButton() {
		return firstProgressButton;
	}

	public ProgressButton getSecondProgressButton() {
		return secondProgressButton;
	}

	public ProgressButton getThirdProgressButton() {
		return thirdProgressButton;
	}

	public ProgressButton getFourthProgressButton() {
		return fourthProgressButton;
	}

	public ProgressButton getFifthProgressButton() {
		return fifthProgressButton;
	}

	public ProgressButton getSixthProgressButton() {
		return sixthProgressButton;
	}
}
