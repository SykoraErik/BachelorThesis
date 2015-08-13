package cz.muni.proso.geography.fragment;

import org.openqa.selenium.WebElement;
import org.jboss.arquillian.graphene.fragment.Root;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.WebDriver;

public class Footer {
		
	@Root
	private WebElement footerRoot;
	
	@Drone
	private WebDriver browser;
	
}
