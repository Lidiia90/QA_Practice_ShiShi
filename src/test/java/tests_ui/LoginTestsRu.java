package tests_ui;

import config.ApplicationManager;
import dto.UserDto;
import helpers.PropertiesReader;
import io.qameta.allure.*;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
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

    @Description("Test login with valid email and password")
    @Test
    public void loginPositiveTest() {
        HomePageRu homePageRu = new HomePageRu(getDriver());
        LoginUserPageRu loginUserPageRu = homePageRu.navigateToLoginPage();
        UserDto userDto = UserDto.builder()
                .email(getProperty("data.properties", "email"))
                .password(getProperty("data.properties", "password"))
                .build();
        pause(3);
        loginUserPageRu.typeLoginForm(userDto);
        loginUserPageRu.clickButtonSubmit();
        Assert.assertTrue(loginUserPageRu.isOrdersTextDisplayed(), "Мои текущие заказы");
    }

    @Test
    public void loginWithCheckBoxPositiveTest() {
        HomePageRu homePageRu = new HomePageRu(getDriver());
        LoginUserPageRu loginUserPageRu = homePageRu.navigateToLoginPage();
        UserDto userDto = UserDto.builder()
                .email(getProperty("data.properties", "email"))
                .password(getProperty("data.properties", "password"))
                .build();
        pause(3);
        loginUserPageRu.typeLoginForm(userDto);
        loginUserPageRu.checkPolicyXY();
        loginUserPageRu.clickButtonSubmit();
        Assert.assertTrue(loginUserPageRu.isOrdersTextDisplayed(), "Мои текущие заказы");
    }

    @Test
    public void WrongEmailLoginTest() {
        HomePageRu homePageRu = new HomePageRu(getDriver());
        LoginUserPageRu loginUserPageRu = homePageRu.navigateToLoginPage();
        UserDto userDto = UserDto.builder()
                .email(getProperty("data.properties", "wrongEmail"))
                .password(getProperty("data.properties", "password"))
                .build();
        BasePage.pause(3);
        loginUserPageRu.typeLoginForm(userDto);
        loginUserPageRu.checkPolicyXY();
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
                .password(getProperty("data.properties", "wrongPassword"))
                .build();
        BasePage.pause(3);
        loginUserPageRu.typeLoginForm(userDto);
        loginUserPageRu.checkPolicyXY();
        loginUserPageRu.clickButtonSubmit();
        Assert.assertTrue(
                BasePage.textTobePresentElement(
                        getDriver().findElement(By.xpath("//p[contains(text(),'Логин/пароль не верен')]")),
                        "Логин/пароль не верен",
                        10),
                "Error: Expected error message not found.");
    }


    @Test
    public void WithoutEmailLoginTest() {
        HomePageRu homePageRu = new HomePageRu(getDriver());
        LoginUserPageRu loginUserPageRu = homePageRu.navigateToLoginPage();

        UserDto userDto = UserDto.builder()
                .email(getProperty("data.properties", "withoutEmail"))
                .password(getProperty("data.properties", "password"))
                .build();
        BasePage.pause(3);
        loginUserPageRu.typeLoginForm(userDto);
        loginUserPageRu.clickButtonSubmit();
        Assert.assertTrue(
                BasePage.textTobePresentElement(
                        getDriver().findElement(By.xpath("//p[contains(text(),'Поле \"Email\" обязательно для заполнения.')]")),
                        "Поле \"Email\" обязательно для заполнения.",
                        10),
                "Error: Expected error message not found.");
    }

    @Test
    public void WithoutPasswordLoginTest() {
        HomePageRu homePageRu = new HomePageRu(getDriver());
        LoginUserPageRu loginUserPageRu = homePageRu.navigateToLoginPage();

        UserDto userDto = UserDto.builder()
                .email(getProperty("data.properties", "email"))
                .password(getProperty("data.properties", "withoutPassword"))
                .build();
        BasePage.pause(3);
        loginUserPageRu.typeLoginForm(userDto);
        loginUserPageRu.clickButtonSubmit();
        Assert.assertTrue(
                BasePage.textTobePresentElement(
                        getDriver().findElement(By.xpath("//p[contains(text(),'Поле \"Пароль\" обязательно для заполнения.')]")),
                        "Поле \"Пароль\" обязательно для заполнения.",
                        10),
                "Error: Expected error message not found.");
    }

    @Test
    public void ForgotPasswordTest() {
        HomePageRu homePageRu = new HomePageRu(getDriver());
        LoginUserPageRu loginUserPageRu = homePageRu.navigateToLoginPage();
        BasePage.pause(3);
        loginUserPageRu.clickForgotPasswordLink();
        loginUserPageRu.typeForgotPasswordForm();
        loginUserPageRu.clickButtonSend();
        Assert.assertTrue(
                BasePage.textTobePresentElement(
                        getDriver().findElement(By.xpath("//p[contains(text(),'Пароль сброшен. На электронную почту отправлено сообщение')]")),
                        "Пароль сброшен. На электронную почту отправлено сообщение",
                        10),
                "Error: Expected error message not found.");
    }


    @Test
    public void ForgotPasswordUnregisteredEmailNegativeTest() {
        HomePageRu homePageRu = new HomePageRu(getDriver());
        LoginUserPageRu loginUserPageRu = homePageRu.navigateToLoginPage();
        BasePage.pause(3);
        loginUserPageRu.clickForgotPasswordLink();
        loginUserPageRu.typeForgotPasswordFormUnregisteredEmail();
        loginUserPageRu.clickButtonSend();
        Assert.assertTrue(
                BasePage.textTobePresentElement(
                        getDriver().findElement(By.xpath("//p[normalize-space()='No record of that email address.']")),
                        "No record of that email address.",
                        10),
                "Error: Expected error message not found.");
    }


    @Test
    public void ForgotPasswordWithoutEmailNegativeTest() {
        HomePageRu homePageRu = new HomePageRu(getDriver());
        LoginUserPageRu loginUserPageRu = homePageRu.navigateToLoginPage();
        BasePage.pause(3);
        loginUserPageRu.clickForgotPasswordLink();
        loginUserPageRu.typeForgotPasswordFormUnregisteredEmail();
        loginUserPageRu.clickButtonSend();
        Assert.assertTrue(
                BasePage.textTobePresentElement(
                        getDriver().findElement(By.xpath("//p[contains(text(),'Пожалуйста введите ваш email и мы сможем отправить вам email с новым паролем.')]")),
                        "Пожалуйста введите ваш email и мы сможем отправить вам email с новым паролем.",
                        10),
                "Error: Expected error message not found.");
    }
}

