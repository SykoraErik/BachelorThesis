package cz.muni.proso.geography.fragment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Footer {
  
  @FindBy(xpath = "./div[1]/span[1]/ul/li[1]/a")
  private WebElement feedbackLink;
	
  @FindBy(xpath = "./div[1]/span[1]/ul/li[2]/a")
  private WebElement googleLink;
  
  @FindBy(xpath = "./div[1]/span[1]/ul/li[3]/a")
  private WebElement facebookLink;
  
  @FindBy(xpath = "./div[1]/span[3]/a[1]/img")
  private WebElement adaptiveLearningLink;
  
  @FindBy(xpath = "./div[1]/span[3]/a[2]/img")
  private WebElement fiMuniLink;

	public WebElement getFeedbackLink() {
		return feedbackLink;
	}

	public WebElement getGoogleLink() {
		return googleLink;
	}

	public WebElement getFacebookLink() {
		return facebookLink;
	}

	public WebElement getAdaptiveLearningLink() {
		return adaptiveLearningLink;
	}

	public WebElement getFiMuniLink() {
		return fiMuniLink;
	}
	
	public void clickFeedbackLink() {
		feedbackLink.click();
	}
	
	public void clickGoogleLink() {
		googleLink.click();
	}
	
	public void clickFacebookLink() {
		facebookLink.click();
	}
	
	public void clickAdaptiveLearningLink() {
		adaptiveLearningLink.click();
	}
	
	public void clickFiMuniLink() {
		fiMuniLink.click();
	}
}
