package com.example.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;

public class SettingsPage extends BasePage {
    @AndroidFindBy(xpath = "//*[contains(@resource-id, 'toolbar')]/android.widget.ImageButton")
    private MobileElement backButton;

    @AndroidFindBy(id = "pushAlertsSwitch")
    private MobileElement equityAlertToggle;

    @AndroidFindBy(id = "pushFeedSwitch")
    private MobileElement newsFeedUpdateToggle;

    public SettingsPage(AppiumDriver<MobileElement> aDriver) {
        super(aDriver);
    }

    @Step("Return to Wallet page")
    public WalletPage returnToWallet() {
        backButton.click();

        return new WalletPage(getDriver());
    }

    @Step("Switch toggle {0}")
    public SettingsPage switchToggle(Toggle toggle) {
        MobileElement element = Toggle.EQUITY_ALERT.equals(toggle) ? equityAlertToggle : newsFeedUpdateToggle;
        element.click();
        return this;
    }

    @Step("Approve turning off Equity Alert {0}")
    public void approveTurnOff(boolean approve) {
        if (approve) {
            getDriver().switchTo().alert().accept();
        } else {
            getDriver().switchTo().alert().dismiss();
        }
    }

    @Step("Enable both toggles")
    public void enableToggles() {
        if (!getToggleState(Toggle.EQUITY_ALERT)) {
            equityAlertToggle.click();
        }
        if (!getToggleState(Toggle.NEWS_FEED_UPDATE)) {
            newsFeedUpdateToggle.click();
        }
    }

    public boolean getToggleState(Toggle toggle) {
        MobileElement element = Toggle.EQUITY_ALERT.equals(toggle) ? equityAlertToggle : newsFeedUpdateToggle;
        return Boolean.parseBoolean(element.getAttribute("checked"));
    }

    public enum Toggle {
        EQUITY_ALERT, NEWS_FEED_UPDATE;
    }
}
