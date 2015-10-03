package cz.muni.proso.geography.fragment;

public class PracticeMap extends Map {

	/**
	 * Finds the specified place on the map and clicks it.
	 * 
	 * @param place
	 */
	public void clickPlace(String place) {
		getPlace(place).click();
	}
}