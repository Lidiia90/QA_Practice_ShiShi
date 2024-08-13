package tests_ui;

import config.ApplicationManager;
import helpers.enums.HeaderMenuItemsEn;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.EventsPageEn;
import pages.HomePageEn;

import static pages.BasePage.clickButtonsOnHeaderEn;

public class EventsTestsEn extends ApplicationManager {
    EventsPageEn eventsPageEn;

    @BeforeMethod
    public void goToEventsPageEn() {
        new HomePageEn(getDriver());
        eventsPageEn = clickButtonsOnHeaderEn(HeaderMenuItemsEn.ACTIVITIES);
    }

    @Test
    public void startTest() {
        System.out.println("==============");
    }
}
