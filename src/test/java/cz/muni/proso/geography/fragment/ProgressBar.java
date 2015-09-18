package cz.muni.proso.geography.fragment;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.jboss.arquillian.graphene.fragment.Root;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.google.common.base.Predicate;

public class ProgressBar {

	@Drone
	private WebDriver browser;

	@Root
	private WebElement root;

	@FindBy(xpath = "./div/div[1]")
	private GrapheneElement learnedBar;

	@FindBy(xpath = "./div/div[2]")
	private GrapheneElement practicedBar;

	private Double parseStyleAttribute(WebElement elementToParse) {
		String style = elementToParse.getAttribute("style");
		if(style.isEmpty()){
			return Double.NaN;
		}
		style = style.substring(7, style.length() - 2).trim();
		return Double.parseDouble(style);
	}

	public Double getLearnedBarWidth() {
		return parseStyleAttribute(learnedBar);
	}

	public Double getPracticedBarWidth() {
		return parseStyleAttribute(practicedBar);
	}
	
	public void mouseOver() {
		Actions builder = new Actions(browser);
		Actions mouseOver = builder.moveToElement(root);
		
		Graphene.waitModel().until(new Predicate<WebDriver>() {
			@Override
			public boolean apply(WebDriver browser) {
				return !(getLearnedBarWidth().isNaN()
						|| getPracticedBarWidth().isNaN());
			}
		});
		
		mouseOver.perform();
	}
}
