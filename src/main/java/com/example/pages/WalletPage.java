package com.example.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;

public class WalletPage extends BasePage {
    @AndroidFindBy(id = "ivSettings")
    private MobileElement settingsIcon;

    public WalletPage(AppiumDriver<MobileElement> aDriver) {
        super(aDriver);
    }

    @Step("Go to Settings page")
    public SettingsPage goToSettings() {
        settingsIcon.click();

        return new SettingsPage(getDriver());
    }
}
