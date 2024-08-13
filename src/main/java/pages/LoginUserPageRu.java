package pages;

import dto.UserDto;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginUserPageRu extends BasePage{
    public LoginUserPageRu(WebDriver driver){
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }
    @FindBy(xpath = "//input[@id='identity']")
    WebElement fieldEmail;
    @FindBy(xpath = "//input[@id='password']")
    WebElement fieldPassword;
    public LoginUserPageRu typeLoginForm(UserDto userDto) {
        fieldEmail.sendKeys(userDto.getEmail());
        fieldPassword.sendKeys(userDto.getPassword());
        return this;
    }
}
