package pages;

import helpers.enums.HeaderMenuItemsEn;
import helpers.enums.HeaderMenuItemsRu;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static helpers.enums.HeaderMenuItemsEn.*;

public class BasePage {
    protected static WebDriver driver;

    public static void setDriver(WebDriver wd) {
        driver = wd;
    }

    @FindBy(xpath = "//i[@class='fa fa-facebook']/..")
    WebElement btnFacebookHeader;

    @FindBy(xpath = "//a[text()='Вход']")
    static WebElement btnLoginHeader;
    @FindBy(xpath = "//a[text()='Регистрация']")
    static WebElement btnRegistrationHeader;

    public static LoginUserPageRu clickBtnLoginHeader() {
        btnLoginHeader.click();
        return new LoginUserPageRu(driver);
    }

    public static CreateUserPageRu clickBtnRegistrationHeader() {
        btnRegistrationHeader.click();
        return new CreateUserPageRu(driver);
    }

    public static <T extends BasePage> T clickButtonsOnHeaderEn(HeaderMenuItemsEn headerMenuItemsEn) {
        try {
            WebElement elementHeaderMenuItem = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(headerMenuItemsEn.getLocator())));
            elementHeaderMenuItem.click();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        switch (headerMenuItemsEn) {
            case ACTIVITIES:
                return (T) new EventsPageEn(driver);
            case ABOUT:
                return (T) new AboutPageEn(driver);
            case OUR_TEAM:
                return (T) new TeamPageEn(driver);
            case OUR_PARTNERS:
                return (T) new PartnersPageEn(driver);
            case PHOTO:
                return (T) new PhotoPageEn(driver);
            case VIDEO:
                return (T) new VideoPageEn(driver);
            case CONTACT_US:
                return (T) new ContactsPageEn(driver);
            default:
                throw new IllegalArgumentException("invalid parametr headerMenuItemsEn");
        }
    }

    public static <T extends BasePage> T clickButtonsOnHeaderRu(HeaderMenuItemsRu headerMenuItemsRu) {
        try {
            WebElement elementHeaderMenuItem = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(headerMenuItemsRu.getLocator())));
            elementHeaderMenuItem.click();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        switch (headerMenuItemsRu) {
            case EVENTS:
                return (T) new EventsPageRu(driver);
            case PHOTO:
                return (T) new PhotoPageRu(driver);
            case VIDEO:
                return (T) new VideoPageRu(driver);
            case ABOUT_US:
                return (T) new AboutUsPageRu(driver);
            case CONTACTS:
                return (T) new ContactsPageRu(driver);
            default:
                throw new IllegalArgumentException("invalid parametr headerMenuItemsRu");
        }
    }
    public static void pause(int time) {
        try {
            Thread.sleep(time * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickWait(WebElement element, int time){
        try {
            new WebDriverWait(driver, Duration.ofSeconds(time))
                    .until(ExpectedConditions.elementToBeClickable(element)).click();
        }catch (TimeoutException e){
            e.printStackTrace();
        }
    }

    public static boolean textTobePresentElement(WebElement element, String text, int time) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
            return wait.until(ExpectedConditions.textToBePresentInElement(element, text));

        }catch (TimeoutException e){
            return false;
        }
    }
}
