package cz.muni.proso.geography.fragment;

import java.util.List;
import java.util.NoSuchElementException;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.fragment.Root;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PracticedPlace {
	
	@Root
	private WebElement root;

	@FindBy(xpath = "./h3/a")
	private WebElement viewPlace;
	
	@FindBy(xpath = "./h3/div")
	private ProgressBar progressBar;
	
	@FindBy(xpath = "//*[@role='tooltip']")
	private ProgressBarTooltip tooltip;
	
	@FindBy(xpath = "./ul/li")
	private List<ProgressButton> progressButtonList;

	public WebElement getViewPlace() {
		return viewPlace;
	}

	public ProgressBar getProgressBar() {
		return progressBar;
	}

	public String getPlaceTitle(){
		Graphene.waitGui().until().element(viewPlace).is().visible();
		return viewPlace.getText();
	}
	
	public ProgressBarTooltip getTooltip(){
		return tooltip;
	}
	
	public WebElement getRoot(){
		return root;
	}
	
	public void clickViewPlace() {
		Graphene.waitGui().until().element(viewPlace).is().present();
		viewPlace.click();
	}
	
	public List<ProgressButton> getProgressButtonList(){
		return progressButtonList;
	}
	
	public ProgressButton getProgressButton(String placeToGet){
		for(ProgressButton progBtn: progressButtonList){
			if(progBtn.getButtonLabel().equals(placeToGet)){
				return progBtn;
			}
		}
		throw new NoSuchElementException("The ProgressButton was not found.");
	}
	
	public void clickProgressButton(String buttonToClick){
		getProgressButton(buttonToClick).clickButton();
	}
	
	public boolean isTooltipDisplayed(){
		return tooltip.isDisplayed();
	}
}
