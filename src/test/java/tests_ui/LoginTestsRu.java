package tests_ui;

import config.ApplicationManager;
import dto.UserDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePageRu;
import pages.LoginUserPageRu;

import static pages.BasePage.clickBtnLoginHeader;
import static pages.BasePage.pause;

public class LoginTestsRu extends ApplicationManager {
    LoginUserPageRu loginUserPageRu;

    @BeforeMethod
    public void goToLoginUserPageRu() {
        new HomePageRu(getDriver());
        loginUserPageRu = clickBtnLoginHeader();
    }

    @Test
    public void loginPositiveTest() {
        UserDto userDto = UserDto.builder()
                .email("email")
                .password("password")
                .build();
        pause(3);
        loginUserPageRu.typeLoginForm(userDto);
    }
}
