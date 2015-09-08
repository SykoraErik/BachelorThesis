package cz.muni.proso.geography.fragment;

public interface SocialMediaLogin {

	void enterEmail(String email);

	void enterPassword(String password);

	void submitLogin();

	void login(String email, String password);
}
