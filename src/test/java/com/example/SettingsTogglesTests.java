package com.example;

import com.example.execution.AndroidAppInstance;
import com.example.pages.SettingsPage;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class SettingsTogglesTests extends BaseTest {
    @Test(dataProvider = DEFAULT_PROVIDER)
    public void verifyDefaultStateAndCancelEquityAlertTurningOff(AndroidAppInstance app) {
        SettingsPage settingsPage = openSettingsPage(app);

        //Verify that both toggles enabled by default
        checkToggleState(settingsPage, SettingsPage.Toggle.EQUITY_ALERT, true);
        checkToggleState(settingsPage, SettingsPage.Toggle.NEWS_FEED_UPDATE, true);

        //Try to disable Equity Alert but cancel in popup
        settingsPage.switchToggle(SettingsPage.Toggle.EQUITY_ALERT).approveTurnOff(false);
        checkToggleState(settingsPage, SettingsPage.Toggle.EQUITY_ALERT, true);

        //Reopen Settings Page and check that toggles state is not changed
        settingsPage = settingsPage.returnToWallet().goToSettings();
        checkToggleState(settingsPage, SettingsPage.Toggle.EQUITY_ALERT, true);
        checkToggleState(settingsPage, SettingsPage.Toggle.NEWS_FEED_UPDATE, true);
    }

    @Test(dataProvider = DEFAULT_PROVIDER)
    public void verifyBothTogglesCouldBeTurnedOff(AndroidAppInstance app) {
        SettingsPage settingsPage = openSettingsPage(app);

        //Disable Equity Alert and News Feed Update
        settingsPage.switchToggle(SettingsPage.Toggle.EQUITY_ALERT).approveTurnOff(true);
        settingsPage.switchToggle(SettingsPage.Toggle.NEWS_FEED_UPDATE);
        checkToggleState(settingsPage, SettingsPage.Toggle.NEWS_FEED_UPDATE, false);
        checkToggleState(settingsPage, SettingsPage.Toggle.EQUITY_ALERT, false);

        //Reopen Settings Page and check that toggles state is not changed
        settingsPage = settingsPage.returnToWallet().goToSettings();
        checkToggleState(settingsPage, SettingsPage.Toggle.NEWS_FEED_UPDATE, false);
        checkToggleState(settingsPage, SettingsPage.Toggle.EQUITY_ALERT, false);
    }

    @Test(dataProvider = DEFAULT_PROVIDER)
    public void verifyToggleChangeOneByOne(AndroidAppInstance app) {
        SettingsPage settingsPage = openSettingsPage(app);

        //Disable News Feed Update
        settingsPage.switchToggle(SettingsPage.Toggle.NEWS_FEED_UPDATE);
        checkToggleState(settingsPage, SettingsPage.Toggle.NEWS_FEED_UPDATE, false);
        checkToggleState(settingsPage, SettingsPage.Toggle.EQUITY_ALERT, true);

        //Reopen Settings Page and check that toggles state is not changed
        settingsPage = settingsPage.returnToWallet().goToSettings();
        checkToggleState(settingsPage, SettingsPage.Toggle.NEWS_FEED_UPDATE, false);
        checkToggleState(settingsPage, SettingsPage.Toggle.EQUITY_ALERT, true);

        //Disable Equity Alert and enable News Feed Update
        settingsPage.switchToggle(SettingsPage.Toggle.EQUITY_ALERT).approveTurnOff(true);
        settingsPage.switchToggle(SettingsPage.Toggle.NEWS_FEED_UPDATE);
        checkToggleState(settingsPage, SettingsPage.Toggle.NEWS_FEED_UPDATE, true);
        checkToggleState(settingsPage, SettingsPage.Toggle.EQUITY_ALERT, false);

        //Reopen Settings Page and check that toggles state is not changed
        settingsPage = settingsPage.returnToWallet().goToSettings();
        checkToggleState(settingsPage, SettingsPage.Toggle.NEWS_FEED_UPDATE, true);
        checkToggleState(settingsPage, SettingsPage.Toggle.EQUITY_ALERT, false);
    }

    @AfterMethod()
    public void tearDown(ITestResult result, Object[] params) {
        if (params.length > 0 && params[0] instanceof AndroidAppInstance) {
            AndroidAppInstance app = ((AndroidAppInstance) params[0]);
            if (app.isDriverExist() && result.isSuccess()) {
                new SettingsPage(app.getDriver()).enableToggles();
                app.cleanDriver();
            }
        }
    }

    @Step("Verify toggle {1} state is enabled {2}")
    private void checkToggleState(SettingsPage sPage, SettingsPage.Toggle toggle, boolean expected) {
        String state = expected ? "enabled" : "disabled";
        Assert.assertEquals(sPage.getToggleState(toggle), expected,
                String.format("Toggle %s should be [%s]", toggle, state));
    }

    @Step("Open Settings page")
    private SettingsPage openSettingsPage(AndroidAppInstance app) {
        return app.start()
                .skipPopups()
                .goToSignIn()
                .signIn(getEnvironment().getTestUser(), getEnvironment().getTestUserPassword())
                .skipPasscodeAndTips()
                .goToWallet()
                .goToSettings();
    }
}
