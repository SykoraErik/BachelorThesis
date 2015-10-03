package cz.muni.proso.geography.fragment;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProgressBarTooltip {

	@FindBy(xpath = "./div[2]/div[1]/span")
	private GrapheneElement learned;

	@FindBy(xpath = "./div[2]/div[2]/span")
	private WebElement practiced;

	/**
	 * Returns number of items learned or practiced.
	 * 
	 * @param item
	 *            name of <code>WebElement</code> to parse
	 * @return <code>int</code> number of items learned or practiced(for example
	 *         returns 3 if tooltip shows Learned 3/107)
	 */
	private int parseTooltip(WebElement item) {
		int slashPosition = item.getText().indexOf("/");
		return Integer.parseInt(item.getText().substring(0, slashPosition - 1)
				.trim());
	}

	/**
	 * Returns maximum number of items that the progress bar displays.
	 * 
	 * @return <code>int</code> number of items learned or practiced(for example
	 *         returns 107 if tooltip shows Learned 3/107)
	 */
	public int itemsCount() {
		int slashPosition = learned.getText().indexOf("/");
		String stringToParse = learned.getText()
				.substring(slashPosition + 1, learned.getText().length())
				.trim();
		return Integer.parseInt(stringToParse);
	}

	/**
	 * Returns number of learned items that the progress bar displays.
	 * 
	 * @return <code>int</code> number of items learned
	 */
	public int itemsLearnedCount() {
		Graphene.waitGui().until().element(learned).is().present();
		return parseTooltip(learned);
	}

	/**
	 * Returns number of practiced items that the progress bar displays.
	 * 
	 * @return <code>int</code> number of items practiced
	 */
	public int itemsPracticedCount() {
		Graphene.waitGui().until().element(practiced).is().present();
		return parseTooltip(practiced);
	}

	/**
	 * Returns percentage of items learned. Used to compare with width of
	 * progress bar.
	 * 
	 * @return percentage of items learned
	 */
	public double learnedPercentage() {
		return (itemsLearnedCount() / (double) itemsCount()) * 100;
	}

	/**
	 * Returns percentage of items practiced. Used to compare with width of
	 * progress bar.
	 * 
	 * @return percentage of items practiced
	 */
	public double practicedPercentage() {
		return (itemsPracticedCount() / (double) itemsCount()) * 100;
	}

	/**
	 * Return <code>true</code> if the progress bar tooltip is displayed.
	 * 
	 * @return <code>true</code> if the progress bar tooltip is displayed
	 *         <code>false</code> otherwise
	 */
	public boolean isDisplayed() {
		return learned.isDisplayed() && learned.isPresent();
	}
}