package tests_ui;

import config.ApplicationManager;
import dto.UserDto;
import helpers.PropertiesReader;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePageRu;
import pages.LoginUserPageRu;

import static helpers.PropertiesReader.getProperty;
import static pages.BasePage.*;

public class LoginTestsRu extends ApplicationManager {
    LoginUserPageRu loginUserPageRu;

    @BeforeMethod
    public void goToLoginUserPageRu() {
        new HomePageRu(getDriver());
        loginUserPageRu = clickBtnLoginHeader();
    }

    @Test
    public void loginPositiveTest() {
        HomePageRu homePageRu = new HomePageRu(getDriver());
        LoginUserPageRu loginUserPageRu = homePageRu.navigateToLoginPage();
        UserDto userDto = UserDto.builder()
                .email(getProperty("data.properties", "email"))
                .password(getProperty("data.properties","password"))
                .build();
        pause(3);
        loginUserPageRu.typeLoginForm(userDto);
        loginUserPageRu.clickButtonSubmit();
        Assert.assertTrue(loginUserPageRu.isOrdersTextDisplayed(), "Мои текущие заказы");
    }

    @Test
    public void WrongEmailLoginTest() {
        HomePageRu homePageRu = new HomePageRu(getDriver());
        LoginUserPageRu loginUserPageRu = homePageRu.navigateToLoginPage();
        UserDto userDto = UserDto.builder()
                .email(getProperty("data.properties", "wrongEmail"))
                .password(getProperty("data.properties","password"))
                .build();
        BasePage.pause(3);
        loginUserPageRu.typeLoginForm(userDto);
        loginUserPageRu.clickButtonSubmit();
        Assert.assertTrue(
                BasePage.textTobePresentElement(
                        getDriver().findElement(By.xpath("//p[contains(text(),'Логин/пароль не верен')]")),
                        "Логин/пароль не верен",
                        10),
                "Error: Expected error message not found.");
    }

    @Test
    public void WrongPasswordLoginTest() {
        HomePageRu homePageRu = new HomePageRu(getDriver());
        LoginUserPageRu loginUserPageRu = homePageRu.navigateToLoginPage();

        UserDto userDto = UserDto.builder()
                .email(getProperty("data.properties", "email"))
                .password(getProperty("data.properties","wrongPassword"))
                .build();
        BasePage.pause(3);
        loginUserPageRu.typeLoginForm(userDto);
        loginUserPageRu.clickButtonSubmit();
        Assert.assertTrue(
                BasePage.textTobePresentElement(
                        getDriver().findElement(By.xpath("//p[contains(text(),'Логин/пароль не верен')]")),
                        "Логин/пароль не верен",
                        10),
                "Error: Expected error message not found.");
    }
}
