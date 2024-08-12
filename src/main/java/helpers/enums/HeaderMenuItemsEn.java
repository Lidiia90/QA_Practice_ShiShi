package helpers.enums;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public enum HeaderMenuItemsEn {
    ABOUT("//ul[@class='vertical medium-horizontal menu align-right show-for-medium show-for-large']/li[1]/a[@title='About Us']"),
    ACTIVITIES("//ul[@class='vertical medium-horizontal menu align-right show-for-medium show-for-large']/li[2]/a[@title='Activities']"),
    OUR_TEAM("//ul[@class='vertical medium-horizontal menu align-right show-for-medium show-for-large']/li[3]/a[@title='Our Team']"),
    OUR_PARTNERS("//ul[@class='vertical medium-horizontal menu align-right show-for-medium show-for-large']/li[4]/a[@title='Our Partners']"),
    PHOTO("//ul[@class='vertical medium-horizontal menu align-right show-for-medium show-for-large']/li[5]/a[@title='Photo']"),
    VIDEO("//ul[@class='vertical medium-horizontal menu align-right show-for-medium show-for-large']/li[6]/a[@title='Video']"),
    CONTACT_US("//ul[@class='vertical medium-horizontal menu align-right show-for-medium show-for-large']/li[Last()]/a[@title='Contact Us']");
    private final String locator;

    HeaderMenuItemsEn(String locator) {
        this.locator = locator;
    }
    public String getLocator(){
        return locator;
    }
}
