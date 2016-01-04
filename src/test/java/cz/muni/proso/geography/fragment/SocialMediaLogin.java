package cz.muni.proso.geography.fragment;

public interface SocialMediaLogin {

	/**
	 * Enter the specified email into its corresponding field to use for login
	 * or sign up.
	 * 
	 * @param email
	 */
	void enterEmail(String email);

	/**
	 * Enter the specified password into its corresponding field to use for
	 * login or sign up.
	 * 
	 * @param password
	 */
	void enterPassword(String password);

	/**
	 * Submits the entered email and password by clicking submit button.
	 */
	void submitLogin();

	/**
	 * Takes the provided email and password and does a complete login or sign
	 * up.
	 * 
	 * @param email
	 * @param password
	 * @throws InterruptedException
	 */
	void login(String email, String password) throws InterruptedException;
}