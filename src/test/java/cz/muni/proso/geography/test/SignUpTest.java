package cz.muni.proso.geography.test;

import static org.junit.Assert.assertTrue;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.FindBy;

import cz.muni.proso.geography.fragment.Login;
import cz.muni.proso.geography.fragment.NavigationMenu;
import cz.muni.proso.geography.fragment.SignUp;

@RunWith(Arquillian.class)
public class SignUpTest extends TestUtilityClass {

	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div")
	private SignUp signUp;

	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div")
	private Login login;

	@FindBy(css = "#wrap > div.navbar.navbar-inverse")
	private NavigationMenu navMenu;

	@Before
	public void openPage() {
		browser.get(BASE_URL);
	}

	@Test
	public void testSignUpEmail() {
		navMenu.clickLogin();
		login.clickSignUp();
		signUp.clickEmailButton();
		signUp.signUpWithEmail(randomizeEmail(EMAIL),
				randomizeUsername(USERNAME), PASSWORD, PASSWORD);
		assertTrue(navMenu.isUserLoggedIn());
	}
}