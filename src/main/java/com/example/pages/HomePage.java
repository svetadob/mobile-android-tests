package com.example.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;

public class HomePage extends BasePage {
    @AndroidFindBy(id = "btnSkip")
    private MobileElement skipButton;

    @AndroidFindBy(id = "finishButton")
    private MobileElement gotItButton;

    @AndroidFindBy(xpath = "(//*[contains(@resource-id, 'bottomNavigationView')]//android.widget.ImageView)[3]")
    private MobileElement walletButton;

    public HomePage(AppiumDriver<MobileElement> aDriver) {
        super(aDriver);
    }

    @Step("Skip setting passcode and tips popup")
    public HomePage skipPasscodeAndTips() {
        skipButton.click();
        gotItButton.click();
        gotItButton.click();
        return this;
    }

    @Step("Go to Wallet page")
    public WalletPage goToWallet() {
        walletButton.click();

        return new WalletPage(getDriver());
    }
}
