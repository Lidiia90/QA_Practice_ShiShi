package pages;

import dto.UserDto;
import helpers.PropertiesReader;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
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
    WebElement loginButton;

    @FindBy(xpath = "//span[contains(text(),'Мои текущие заказы')]")
    WebElement ordersTextLocator;
    @FindBy(xpath = "//p[contains(text(),'Логин/пароль не верен')]")
    WebElement errorMessage;

    @FindBy(id = "remember")
    WebElement rememberCheckbox;
    @FindBy(css = "#remember")
    WebElement rememberCheckboxByCss;

    @FindBy(xpath = "//a[contains(text(),'Забыли свой пароль?')]")
    WebElement forgotPasswordLink;
    @FindBy(xpath = "//input[@id='identity']")
    WebElement typeForgotPassword;
    @FindBy(xpath = " //input[@name='submit']")
    WebElement sendButton;

    @Step("Step 2 annotation")
    public LoginUserPageRu typeLoginForm(UserDto userDto) {
        fieldEmail.sendKeys(userDto.getEmail());
        fieldPassword.sendKeys(userDto.getPassword());
        return this;
    }

    @Step("Step 3 annotation")
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
    public void checkPolicyXY() {
        if (!rememberCheckbox.isSelected()) {
            Rectangle rect = rememberCheckboxByCss.getRect();
            int w = rect.getWidth();
            int xOffSet = -w / 2;
            Actions actions = new Actions(driver);
            actions.moveToElement(rememberCheckboxByCss, xOffSet, 0).click().release().perform();
        }
    }
    public void clickForgotPasswordLink() {
        clickWait(forgotPasswordLink, 10);
    }

    public void typeForgotPasswordForm() {
        String email = PropertiesReader.getProperty("data.properties", "email");
        typeForgotPassword.sendKeys(email);
    }
    public void typeForgotPasswordFormUnregisteredEmail() {
        String email = PropertiesReader.getProperty("data.properties", "unregisteredEmail");
        typeForgotPassword.sendKeys(email);
    }
    public void typeForgotPasswordFormWithoutEmail() {
        String email = PropertiesReader.getProperty("data.properties", "withoutEmail");
        typeForgotPassword.sendKeys(email);
    }

    public void clickButtonSend() {
        clickWait(sendButton, 10);
    }
}

