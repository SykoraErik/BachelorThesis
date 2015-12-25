package cz.muni.proso.geography.test;

import static org.jboss.arquillian.graphene.Graphene.guardAjax;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.FindBy;

import cz.muni.proso.geography.fragment.FacebookAuth;
import cz.muni.proso.geography.fragment.FacebookConfirmAuth;
import cz.muni.proso.geography.fragment.GoogleAuth;
import cz.muni.proso.geography.fragment.GoogleConfirmAuth;
import cz.muni.proso.geography.fragment.Login;
import cz.muni.proso.geography.fragment.NavigationMenu;

@RunWith(Arquillian.class)
public class LoginTest extends TestUtilityClass {

	@FindBy(css = "body > div.modal.fade.ng-isolate-scope.in > div > div")
	private Login login;

	@FindBy(id = "platformDialogForm")
	private FacebookConfirmAuth facebookConfirm;

	@FindBy(id = "login_form")
	private FacebookAuth facebook;

	@FindBy(css = "body > div > div.main.content.clearfix")
	private GoogleAuth google;

	@FindBy(id = "approval_container")
	private GoogleConfirmAuth googleConfirm;

	@FindBy(css = "#wrap > div.navbar.navbar-inverse")
	private NavigationMenu navMenu;

	@Before
	public void openPage() {
		browser.get(BASE_URL);
		if (navMenu.isUserLoggedIn()) {
			navMenu.signOut();
		}
	}

	@After
	public void logOff() {
		navMenu.signOut();
		assertFalse(navMenu.isUserLoggedIn());
	}

	@Test
	public void testEmailLogin() {
		navMenu.clickLogin();
		login.loginWithEmail(USERNAME, PASSWORD);
		browser.get(BASE_URL);
		assertTrue(navMenu.isUserLoggedIn());
	}

	@Test
	public void testFacebook() {
		navMenu.clickLogin();
		login.clickLoginFacebook();
		if (facebook.isPresent()) {
			facebook.login(FACEBOOK_EMAIL, FACEBOOK_PASSWORD);
		}
		if (facebookConfirm.isPresent()) {
			guardAjax(facebookConfirm).confirm();
		}
		assertTrue(navMenu.isUserLoggedIn());
	}

	@Test
	public void testGoogle() {
		navMenu.clickLogin();
		login.clickLoginGoogle();
		if (google.isAccountsFormPresent() || google.isGaiaFormPresent()) {
			google.login(EMAIL, GOOGLE_PASSWORD);
		}
		if (googleConfirm.isPresent()) {
			guardAjax(googleConfirm).approveAccess();
		}
		assertTrue(navMenu.isUserLoggedIn());
	}
}