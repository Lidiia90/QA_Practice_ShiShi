package pages;

import dto.UserDto;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginUserPageRu extends BasePage {
    public LoginUserPageRu(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//input[@id='identity']")
    WebElement fieldEmail;
    @FindBy(xpath = "//input[@id='password']")
    WebElement fieldPassword;

    @FindBy(xpath = "//input[@name='submit']")
    public WebElement loginButton;

    @FindBy(xpath = "//span[contains(text(),'Мои текущие заказы')]")
    WebElement ordersTextLocator;
    @FindBy(xpath = "//p[contains(text(),'Логин/пароль не верен')]")
    public WebElement errorMessage;

    public LoginUserPageRu typeLoginForm(UserDto userDto) {
        fieldEmail.sendKeys(userDto.getEmail());
        fieldPassword.sendKeys(userDto.getPassword());
        return this;
    }

    public void clickButtonSubmit() {
        clickWait(loginButton, 10);
    }

    public boolean isOrdersTextDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(ordersTextLocator)).isDisplayed();
            //  ordersTextLocator.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
    }

