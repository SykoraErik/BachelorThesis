package cz.muni.proso.geography.fragment;

import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProgressBarTooltip {
    
	@FindBy(xpath = "./div[2]/div[1]/span")
	private WebElement learned;
	
	@FindBy(xpath = "./div[2]/div[2]/span")
	private WebElement practiced;
	
	public WebElement getLearned() {
		return learned;
	}

	public WebElement getPracticed() {
		return practiced;
	}

	public String getNumberOfItemsLearned(){
		Graphene.waitGui().until().element(learned).is().present();
		return learned.getText();
	}
	
	public String getNumberOfItemsPracticed(){
		Graphene.waitGui().until().element(practiced).is().present();
		return practiced.getText();
	}
	
	public boolean isDisplayed(){
		Graphene.waitModel().until().element(learned).is().visible();
		return learned.isDisplayed();
	}
}
