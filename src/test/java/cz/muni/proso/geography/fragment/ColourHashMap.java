package cz.muni.proso.geography.fragment;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class ColourHashMap {

	public static final String WRONG = "#ee2233";
	public static final String SELECTION = "#dddddd";
	public static final String HIGHLIGHT = "#bbbbbb";
	public static final String CORRECT = "#00dd00";
	
	private static final Map<String, String> COLOUR_MAP;
	static {
		Map<String, String> tempMap = new HashMap<String, String>();
		tempMap.put("#ffffff", "0");
		tempMap.put("#f42f1e", "1");
		tempMap.put("#fb3d0a", "2");
		tempMap.put("#ff5800", "3");
		tempMap.put("#ff8100", "4");
		tempMap.put("#ffaa00", "5");
		tempMap.put("#ffc514", "6");
		tempMap.put("#ffe028", "7");
		tempMap.put("#cbea28", "8");
		tempMap.put("#65e314", "9");
		tempMap.put("#00dd00", "10");
		tempMap.put("#ee2233", "wrong");
		tempMap.put("#dddddd", "selection");
		tempMap.put("#bbbbbb", "highlight");
		COLOUR_MAP = Collections.unmodifiableMap(tempMap);
	}

	/**
	 * Reads from a hash map containing mapping of colours and their meanings.
	 * 
	 * @param colour a hexadecimal value representing colour
	 * @return <code>String</code> containing meaning of specified colour
	 */
	public static String getColourMeaning(String colour) {
		return COLOUR_MAP.get(colour);
	}
}