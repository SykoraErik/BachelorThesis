package cz.muni.proso.geography.fragment;

import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProgressBarTooltip {

	@FindBy(xpath = "./div[2]/div[1]/span")
	private WebElement learned;

	@FindBy(xpath = "./div[2]/div[2]/span")
	private WebElement practiced;

	private int parseTooltip(WebElement item) {
		int slashPosition = item.getText().indexOf("/");
		return Integer.parseInt(item.getText().substring(0, slashPosition - 1)
				.trim());
	}

	public int itemsCount() {
		int slashPosition = learned.getText().indexOf("/");
		return Integer.parseInt(learned.getText()
				.substring(slashPosition + 1, learned.getText().length())
				.trim());
	}

	public int itemsLearnedCount() {
		Graphene.waitGui().until().element(learned).is().present();
		return parseTooltip(learned);
	}

	public int itemsPracticedCount() {
		Graphene.waitGui().until().element(practiced).is().present();
		return parseTooltip(practiced);
	}

	public boolean isDisplayed() {
		try {
			return learned.isDisplayed();
		} catch (NoSuchElementException ex) {
			return false;
		}
	}
}
