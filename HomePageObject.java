package org.arquillian.example;

import static org.jboss.arquillian.graphene.Graphene.guardAjax;

import org.jboss.arquillian.graphene.page.Location;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


@Location("http://slepemapy.cz/")
public class HomePageObject {
	
	@Drone
	private WebDriver browser;
	
	@FindBy(className = "modal-content")
	private LoginFragment login;
	
	@FindBy(css="#nav-main > ul.nav.navbar-nav.pull-right > li:nth-child(1) > a")
	private	WebElement loginButton;
	
	private boolean userLoginStatus = false;

	//constructor that sets user as logged in or logged out
	public HomePageObject(boolean loggedIn){
		userLoginStatus = loggedIn;	
	}
	// empty constructor
	public HomePageObject(){
	}
	
	public WebElement getLoginButton(){
		return loginButton;
	}
	
	public boolean getLoginStatus(){
		return userLoginStatus;
	}
	
	public LoginFragment clickLogin(){
		loginButton.click();
    	guardAjax(login);
		return login;
	}
}
