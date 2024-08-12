package helpers.enums;

public enum HeaderMenuItemsRu {
    EVENTS("//ul[@class='vertical medium-horizontal menu align-right show-for-medium show-for-large']/li[1]/a[@title='About Us']"),
    PHOTO("//ul[@class='vertical medium-horizontal menu align-right show-for-medium show-for-large']/li[2]/a[@title='Activities']"),
    VIDEO("//ul[@class='vertical medium-horizontal menu align-right show-for-medium show-for-large']/li[3]/a[@title='Our Team']"),
    ABOUT_US("//ul[@class='vertical medium-horizontal menu align-right show-for-medium show-for-large']/li[4]/a[@title='Our Partners']"),
    CONTACTS("//ul[@class='vertical medium-horizontal menu align-right show-for-medium show-for-large']/li[5]/a[@title='Photo']"),
    ;
    private final String locator;

    HeaderMenuItemsRu(String locator) {
        this.locator = locator;
    }
    public String getLocator(){
        return locator;
    }
}
